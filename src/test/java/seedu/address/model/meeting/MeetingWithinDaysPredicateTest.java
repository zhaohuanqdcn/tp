package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MeetingBuilder;

public class MeetingWithinDaysPredicateTest {

    @Test
    public void equals() {
        MeetingWithinDaysPredicate predicateOne = new MeetingWithinDaysPredicate(10);
        MeetingWithinDaysPredicate predicateTwo = new MeetingWithinDaysPredicate(20);

        // same object -> returns true
        assertTrue(predicateOne.equals(predicateOne));

        // same values -> returns true
        MeetingWithinDaysPredicate predicateOneCopy = new MeetingWithinDaysPredicate(10);
        assertTrue(predicateOne.equals(predicateOneCopy));

        // different types -> returns false
        assertFalse(predicateOne.equals(1));

        // null -> returns false
        assertFalse(predicateOne.equals(null));

        // different person -> returns false
        assertFalse(predicateOne.equals(predicateTwo));
    }

    @Test
    public void test_dataWithinHour_returnsTrue() {
        MeetingWithinDaysPredicate predicate = new MeetingWithinDaysPredicate(10000);
        String testDateTime = "10/1/21 1201";
        assertTrue(predicate.test(new MeetingBuilder().withDateTime(testDateTime).build()));
    }

    @Test
    public void test_dataDoesNotContainKeywords_returnsFalse() {
        MeetingWithinDaysPredicate predicate = new MeetingWithinDaysPredicate(10);
        String testDateTime = "10/1/21 1201";

        // Meeting occur before current DateTime
        assertFalse(predicate.test(new MeetingBuilder().withDateTime(MeetingBuilder.DEFAULT_DATETIME).build()));

        // Meeting outside hour range from user input
        assertFalse(predicate.test(new MeetingBuilder().withDateTime(testDateTime).build()));
    }


}
