package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MeetingBuilder;

public class DataContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        DataContainsKeywordsPredicate firstPredicate = new DataContainsKeywordsPredicate(firstPredicateKeywordList);
        DataContainsKeywordsPredicate secondPredicate = new DataContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DataContainsKeywordsPredicate firstPredicateCopy =
                new DataContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_DataContainsKeywords_returnsTrue() {
        // One keyword
        DataContainsKeywordsPredicate predicate =
                new DataContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new MeetingBuilder().withTitle("Alice Bob").build()));

        // Multiple keywords
        predicate = new DataContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new MeetingBuilder().withTitle("Alice Bob").build()));

        // Only one matching keyword
        predicate = new DataContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new MeetingBuilder().withTitle("Alice Bob").build()));

        // Mixed-case keywords
        predicate = new DataContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new MeetingBuilder().withTitle("Alice Bob").build()));
    }

    @Test
    public void test_titleDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DataContainsKeywordsPredicate predicate = new DataContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new MeetingBuilder().withTitle("Alice").build()));

        // Non-matching keyword
        predicate = new DataContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new MeetingBuilder().withTitle("Alice Bob").build()));

        // Keywords match location, but does not match name
        predicate = new DataContainsKeywordsPredicate(Arrays.asList("Main", "Street"));
        assertFalse(predicate.test(new MeetingBuilder().withTitle("Alice").withLocation("Main Street").build()));
    }
}
