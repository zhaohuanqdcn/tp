package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_COMPANY = new Prefix("c/");
    public static final Prefix PREFIX_COMPANY_ROLE = new Prefix("r/");

    public static final Prefix PREFIX_DATETIME = new Prefix("d/");
    public static final Prefix PREFIX_DURATION = new Prefix("dur/");
    public static final Prefix PREFIX_TITLE = new Prefix("title/");
    public static final Prefix PREFIX_LOCATION = new Prefix("l/");
    public static final Prefix PREFIX_RECURRENCE = new Prefix("rec/");
    public static final Prefix PREFIX_ADD_PARTICIPANTS = new Prefix("add_part/");
    public static final Prefix PREFIX_DELETE_PARTICIPANTS = new Prefix("del_part/");

    public static final Prefix PREFIX_CONTACT_INDEX = new Prefix("ci/");
    public static final Prefix PREFIX_MEETING_INDEX = new Prefix("mi/");
}
