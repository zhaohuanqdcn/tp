package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UUID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UUID_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withUuid("d924efec-24a7-48eb-9b2d-0873a3d2a50a")
            .withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withCompany("KFC")
            .withRoles("CEO")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withUuid("da9509e4-3fe0-4962-a3c6-42144251bc93")
            .withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withCompany("KFC")
            .withRoles("CEO")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withUuid("708acdf7-010b-40a6-95a8-dedc7a801c96")
            .withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withCompany("KFC").build();
    public static final Person DANIEL = new PersonBuilder().withUuid("e2d76129-6dea-49e6-b5d0-6b510542325c")
            .withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withCompany("KFC")
            .withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withUuid("be57d45d-1129-4e45-bd90-b9833b776566")
            .withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withCompany("KFC").withAddress("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withUuid("c816cca1-ab18-4439-a9b0-1da9e7657c8e")
            .withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withCompany("KFC").withAddress("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder().withUuid("11b1ef45-8170-4c06-8d8e-a87aceb69bc0")
            .withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withCompany("KFC").withAddress("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withCompany("KFC").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withCompany("KFC").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withUuid(VALID_UUID_AMY)
            .withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withCompany(VALID_COMPANY_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withUuid(VALID_UUID_BOB)
            .withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withCompany(VALID_COMPANY_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
