package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.role.CompanyRole;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Company("KFC"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), getCompanyRoleSet("CEO", "Boss")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Company("KFC"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), getCompanyRoleSet("CTO")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Company("NUS"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"), getCompanyRoleSet("worker")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Company("NTU"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), getCompanyRoleSet("cleaner")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Company("SMU"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), getCompanyRoleSet("contractor")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Company("McDonald's"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), getCompanyRoleSet("secretary"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static Set<CompanyRole> getCompanyRoleSet(String... strings) {
        return Arrays.stream(strings)
                .map(CompanyRole::new)
                .collect(Collectors.toSet());
    }


    /**
     * Returns a persons set containing the list of persons given.
     */
    public static Set<UUID> getParticipantSet(UUID... persons) {
        return Arrays.stream(persons)
                .collect(Collectors.toSet());
    }

}
