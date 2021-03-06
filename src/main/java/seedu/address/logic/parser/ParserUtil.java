package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.EditUserPrefCommandParser.INTERVAL_KEYWORD;
import static seedu.address.logic.parser.RemindMeetingCommandParser.HOUR_KEYWORD;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Duration;
import seedu.address.model.meeting.Location;
import seedu.address.model.meeting.Recurrence;
import seedu.address.model.meeting.Title;
import seedu.address.model.meeting.UniqueMeetingList.Pair;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.role.CompanyRole;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_INPUT = " is not a valid positive integer within the range";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code interval} in String into an integer and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not none negative integer).
     */
    public static int parsePositiveInteger(String interval, String keyword) throws ParseException {
        requireNonNull(interval);
        requireNonNull(keyword);
        String trimmedInterval = interval.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedInterval)) {
            if (keyword.equals(INTERVAL_KEYWORD)) {
                throw new ParseException(INTERVAL_KEYWORD + MESSAGE_INVALID_INPUT);
            }
            throw new ParseException(HOUR_KEYWORD + MESSAGE_INVALID_INPUT);
        }
        return Integer.parseInt(trimmedInterval);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String company} into an {@code Company}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code String company} is invalid.
     */
    public static Company parseCompany(String company) throws ParseException {
        requireNonNull(company);
        String trimmedCompany = company.trim();
        if (!Company.isValidCompany(trimmedCompany)) {
            throw new ParseException(Company.MESSAGE_CONSTRAINTS);
        }
        return new Company(trimmedCompany);
    }



    /**
     * Parses a {@code String title} into a {@code Title}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code title} is invalid.
     */
    public static Title parseTitle(String title) throws ParseException {
        requireNonNull(title);
        String trimmedTitle = title.trim();
        if (!Title.isValidTitle(trimmedTitle)) {
            throw new ParseException(Title.MESSAGE_CONSTRAINTS);
        }
        return new Title(trimmedTitle);
    }

    /**
     * Parses a {@code String location} into a {@code Location}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code location} is invalid.
     */
    public static Location parseLocation(String location) throws ParseException {
        requireNonNull(location);
        String trimmedLocation = location.trim();
        if (!Location.isValidLocation(trimmedLocation)) {
            throw new ParseException(Location.MESSAGE_CONSTRAINTS);
        }
        return new Location(trimmedLocation);
    }

    /**
     * Parses a {@code String dateTime} into a {@code DateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dateTime} is invalid.
     */
    public static DateTime parseDateTime(String dateTime) throws ParseException {
        requireNonNull(dateTime);
        String trimmedDate = dateTime.trim();
        if (!DateTime.isValidDateTime(trimmedDate)) {
            throw new ParseException(DateTime.MESSAGE_CONSTRAINTS);
        }
        return new DateTime(trimmedDate);
    }

    /**
     * Parses a {@code String recur} into a {@coed Pair} of {@code Recurrence} and {@code recurringNumber}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code recur} is invalid.
     */
    public static Pair<Recurrence, Integer> parseRecurrence(String recur) throws ParseException {
        if (recur == null || recur.isEmpty()) {
            return new Pair<Recurrence, Integer>(Recurrence.NONE, 1);
        }
        String[] trimmedRecur = recur.toLowerCase().trim().split("/");
        if (trimmedRecur.length != 2) {
            throw new ParseException(Recurrence.MESSAGE_CONSTRAINTS);
        }
        if (!Recurrence.isValid(trimmedRecur[0])) {
            throw new ParseException(Recurrence.MESSAGE_CONSTRAINTS);
        }
        Recurrence recurrence = Recurrence.ofNullable(trimmedRecur[0]);
        int recurNumber = Integer.parseInt(trimmedRecur[1]);
        if (recurNumber <= 0 || recurNumber > 20) {
            throw new ParseException(Recurrence.MESSAGE_CONSTRAINTS);
        }
        return new Pair<Recurrence, Integer>(recurrence, recurNumber);
    }

    /**
     * Parses a {@code String flag} into a {@code boolean}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code flag} is invalid.
     */
    public static boolean parseBoolean(String flag) throws ParseException {
        requireNonNull(flag);
        String trimmedFlag = flag.toLowerCase().trim();
        switch (trimmedFlag) {
        case "true":
            return true;
        case "false":
            return false;
        default:
            throw new ParseException("");
        }
    }

    /**
     * Parses a {@code String duration} into a {@code Duration}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code duration} is invalid.
     */
    public static Duration parseDuration(String duration) throws ParseException {
        requireNonNull(duration);
        String trimmedDuration = duration.trim();
        Long hour;
        Long minutes;
        try {
            String[] dur = trimmedDuration.split(" ");
            hour = Long.parseLong(dur[0]);
            minutes = Long.parseLong(dur[1]);
        } catch (Exception e) {
            throw new ParseException(Duration.MESSAGE_CONSTRAINTS);
        }
        if (!Duration.isValidDuration(hour, minutes)) {
            throw new ParseException(Duration.MESSAGE_CONSTRAINTS);
        }

        return new Duration(hour, minutes);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String companyRole} into a {@code CompanyRole}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code companyRole} is invalid.
     */
    public static CompanyRole parseCompanyRole(String companyRole) throws ParseException {
        requireNonNull(companyRole);
        String trimmedCompanyRole = companyRole.trim();
        if (!CompanyRole.isValidCompanyRoleName(trimmedCompanyRole)) {
            throw new ParseException(CompanyRole.MESSAGE_CONSTRAINTS);
        }
        return new CompanyRole(trimmedCompanyRole);
    }

    /**
     * Parses {@code Collection<String> companyRoles} into a {@code Set<CompanyRole>}.
     */
    public static Set<CompanyRole> parseCompanyRoles(Collection<String> companyRoles) throws ParseException {
        requireNonNull(companyRoles);
        final Set<CompanyRole> companyRoleSet = new HashSet<>();
        for (String companyRoleName : companyRoles) {
            companyRoleSet.add(parseCompanyRole(companyRoleName));
        }
        return companyRoleSet;
    }

}
