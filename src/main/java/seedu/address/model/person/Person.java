package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.*;

import seedu.address.model.role.CompanyRole;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final UUID uuid;
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Company company;
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<CompanyRole> companyRoles = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Company company, Address address, Set<Tag> tags,
                  Set<CompanyRole> companyRoles) {
        requireAllNonNull(name, phone, email, company, address, tags, companyRoles);
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.company = company;
        this.address = address;
        this.tags.addAll(tags);
        this.companyRoles.addAll(companyRoles);
    }

    public UUID getUuid() {
        return uuid;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Company getCompany() {
        return company;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable company role set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<CompanyRole> getCompanyRoles() {
        return Collections.unmodifiableSet(companyRoles);
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && (otherPerson.getPhone().equals(getPhone()) || otherPerson.getEmail().equals(getEmail()));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getCompany().equals(getCompany())
                && otherPerson.getCompanyRoles().equals(getCompanyRoles());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, company, companyRoles);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Company: ")
                .append(getCompany())
                .append(" Address: ")
                .append(getAddress())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        builder.append(" Company roles: ");
        getCompanyRoles().forEach(builder::append);
        return builder.toString();
    }

}
