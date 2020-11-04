package seedu.address.model.meeting;

import java.util.List;
import java.util.function.Predicate;

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
                .anyMatch(keyword -> {
                    final StringBuilder builder = new StringBuilder();
                    builder.append(meeting.getTitle())
                            .append(meeting.getDuration())
                            .append(meeting.getLocation())
                            .append(meeting.getDateTime().getDateOutputFormat().format(meeting.getDateTime().value))
                            .append(meeting.getDateTime())
                            .append(meeting.getDateTime().getDate());

                    System.out.println(meeting.getDateTime().getDateOutputFormat().format(meeting.getDateTime().value));
                    return builder
                            .toString()
                            .toLowerCase()
                            .contains(keyword.toLowerCase());
                });
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DataContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DataContainsKeywordsPredicate) other).keywords)); // state check
    }

}
