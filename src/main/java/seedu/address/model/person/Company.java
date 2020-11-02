package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCompany(String)}
 */
public class Company {
    public static final String MESSAGE_CONSTRAINTS = "Company name should not be blank and less than 161 characters";
    public final String companyName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Company(String name) {
        requireNonNull(name);
        checkArgument(isValidCompany(name), MESSAGE_CONSTRAINTS);
        companyName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidCompany(String test) {
        int companyNameLength = test.trim().length();
        return companyNameLength > 0 && companyNameLength <= 160;
    }


    @Override
    public String toString() {
        return companyName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Company // instanceof handles nulls
                && companyName.equals(((Company) other).companyName)); // state check
    }

    @Override
    public int hashCode() {
        return companyName.hashCode();
    }

    public Company copy() {
        return new Company(companyName);
    }
}
