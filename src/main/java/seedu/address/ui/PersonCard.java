package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;


/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label nameBold;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label company;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane roles;

    @FXML
    private ImageView profilePicture;
    @FXML
    private ImageView companyIcon;
    @FXML
    private ImageView phoneIcon;
    @FXML
    private ImageView addressIcon;
    @FXML
    private ImageView emailIcon;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + "");

        String[] names = person.getName().fullName.split(" ", 2);
        nameBold.setText(names[0]);
        if (names.length > 1) {
            name.setText(names[1]);
        } else {
            name.setText("");
        }

        phone.setText(person.getPhone().value);
        company.setText(person.getCompany().companyName);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        person.getCompanyRoles().stream()
                .sorted(Comparator.comparing(companyRole -> companyRole.companyRoleName))
                .forEach(companyRole -> roles.getChildren().add(new Label(companyRole.companyRoleName)));


        companyIcon.setImage(new Image(getClass().getResourceAsStream("/images/work.png")));
        phoneIcon.setImage(new Image(getClass().getResourceAsStream("/images/phone.png")));
        addressIcon.setImage(new Image(getClass().getResourceAsStream("/images/home.png")));
        emailIcon.setImage(new Image(getClass().getResourceAsStream("/images/email.png")));

        profilePicture.setImage(new Image(getClass().getResourceAsStream("/images/default_profile.png")));

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
