package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.role.CompanyRole;

/**
 * Jackson-friendly version of {@link CompanyRole}.
 */
class JsonAdaptedCompanyRole {

    private final String companyRoleName;

    /**
     * Constructs a {@code JsonAdaptedCompanyRole} with the given {@code companyRoleName}.
     */
    @JsonCreator
    public JsonAdaptedCompanyRole(String companyRoleName) {
        this.companyRoleName = companyRoleName;
    }

    /**
     * Converts a given {@code CompanyRole} into this class for Jackson use.
     */
    public JsonAdaptedCompanyRole(CompanyRole source) {
        companyRoleName = source.companyRoleName;
    }

    @JsonValue
    public String getTagName() {
        return companyRoleName;
    }

    /**
     * Converts this Jackson-friendly adapted companyRole object into the model's {@code CompanyRole} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted companyRole.
     */
    public CompanyRole toModelType() throws IllegalValueException {
        if (!CompanyRole.isValidCompanyRoleName(companyRoleName)) {
            throw new IllegalValueException(CompanyRole.MESSAGE_CONSTRAINTS);
        }
        return new CompanyRole(companyRoleName);
    }

}

