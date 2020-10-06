package seedu.address.model.meeting;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Meetings}'s {@code data} matches any of the keywords given.
 */
public class DataContainsKeywordsPredicate implements Predicate<Meeting> {
    private final List<String> keywords;

    public DataContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Meeting meeting) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(meeting.toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DataContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DataContainsKeywordsPredicate) other).keywords)); // state check
    }

}
