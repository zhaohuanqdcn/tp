package seedu.address.model.role;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidCompanyRoleName(String)}
 */
public class CompanyRole {

    public static final String MESSAGE_CONSTRAINTS = "Company role names should be alphabetic";
    public static final String VALIDATION_REGEX = "[\\p{Alpha}][\\p{Alpha} ]*";

    public final String companyRoleName;

    /**
     * Constructs a {@code CompanyRole}.
     *
     * @param companyRoleName A valid company role name.
     */
    public CompanyRole (String companyRoleName) {
        requireNonNull(companyRoleName);
        checkArgument(isValidCompanyRoleName(companyRoleName), MESSAGE_CONSTRAINTS);
        this.companyRoleName = companyRoleName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidCompanyRoleName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompanyRole // instanceof handles nulls
                && companyRoleName.equals(((CompanyRole) other).companyRoleName)); // state check
    }

    @Override
    public int hashCode() {
        return companyRoleName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + companyRoleName + ']';
    }

}

