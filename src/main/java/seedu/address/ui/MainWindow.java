package seedu.address.ui;

import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.beans.binding.DoubleBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.CommandSession;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.scheduler.RefreshTask;
import seedu.address.logic.scheduler.ReminderPopupTask;
import seedu.address.logic.scheduler.Scheduler;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.memento.History;
import seedu.address.model.memento.StateManager;
import seedu.address.model.person.Person;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Stage primaryStage;
    private final Logic logic;
    private final CommandSession commandSession;
    private final StateManager stateManager;
    private final History history;
    private final Scheduler refreshScheduler;
    private final Scheduler reminderScheduler;
    private final ReminderPopup reminderPopup;
    private ReminderPopupTask reminderPopupTask;
    private RefreshTask refreshTask;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private MeetingListPanel meetingListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane meetingListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private SplitPane contentPanelPlaceholder;

    @FXML
    private Rectangle timelineBar;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());
        primaryStage.initStyle(StageStyle.UNDECORATED);

        setAccelerators();

        helpWindow = new HelpWindow();

        reminderPopup = new ReminderPopup(primaryStage);

        commandSession = new CommandSession();

        stateManager = logic.getStateManager();

        history = logic.getHistory();

        refreshScheduler = new Scheduler();

        refreshTask = new RefreshTask(refreshScheduler, this.logic, "refresh");

        refreshScheduler.update(refreshTask);

        reminderScheduler = new Scheduler();

        reminderPopupTask = new ReminderPopupTask(reminderScheduler, this.logic,
                "popupNotifications", this::handlePopup);

        reminderScheduler.update(reminderPopupTask);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     *
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        DoubleBinding timelineHeight = getRoot()
                .heightProperty()
                .subtract(resultDisplayPlaceholder.heightProperty())
                .subtract(commandBoxPlaceholder.heightProperty())
                .subtract(140);

        meetingListPanel = new MeetingListPanel(logic.getFilteredMeetingList(), logic.getPersonMap(), timelineHeight);
        meetingListPanelPlaceholder.getChildren().add(meetingListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand, commandSession);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        DoubleBinding halfScreenWidth = contentPanelPlaceholder.widthProperty().multiply(0.5);

        personListPanelPlaceholder.maxWidthProperty().bind(halfScreenWidth);
        personListPanelPlaceholder.minWidthProperty().bind(halfScreenWidth);

    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }
    /**
     * Shows the popup with the given meeting contents
     */
    public void handlePopup(Meeting meeting) {
        reminderPopup.setMeeting(meeting);
        if (!reminderPopup.isShowing()) {
            reminderPopup.show();
        } else {
            reminderPopup.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        refreshScheduler.cancel();
        reminderScheduler.cancel();
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    public MeetingListPanel getMeetingListPanel() {
        return meetingListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            saveCurrentState(commandText);

            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            commandSession.add(commandText);

            history.push(stateManager.createState());

            refreshTask = new RefreshTask(refreshScheduler, this.logic, "refresh");

            refreshScheduler.update(refreshTask);

            reminderPopupTask = new ReminderPopupTask(reminderScheduler, this.logic,
                    "popupNotifications", this::handlePopup);

            reminderScheduler.update(reminderPopupTask);

            return commandResult;

        } catch (CommandException | ParseException e) {

            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    private void saveCurrentState(String commandText) {
        ArrayList<Person> personArrayList = new ArrayList<>();
        logic
                .getAddressBook()
                .getPersonList()
                .stream()
                .map(Person::copy)
                .forEach(personArrayList::add);
        stateManager.setPersonList(personArrayList);

        ArrayList<Meeting> meetingArrayList = new ArrayList<>();
        logic
                .getAddressBook()
                .getMeetingList()
                .stream()
                .map(Meeting::copy)
                .forEach(meetingArrayList::add);
        stateManager.setMeetingList(meetingArrayList);

        stateManager.setCommandState(commandText);
    }
}
