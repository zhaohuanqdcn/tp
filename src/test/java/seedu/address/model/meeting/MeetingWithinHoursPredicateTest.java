package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MeetingBuilder;
import seedu.address.testutil.TypicalMeetings;

public class MeetingWithinHoursPredicateTest {

    @Test
    public void equals() {
        MeetingWithinHoursPredicate predicateOne = new MeetingWithinHoursPredicate(10);
        MeetingWithinHoursPredicate predicateTwo = new MeetingWithinHoursPredicate(20);

        // same object -> returns true
        assertTrue(predicateOne.equals(predicateOne));

        // same values -> returns true
        MeetingWithinHoursPredicate predicateOneCopy = new MeetingWithinHoursPredicate(10);
        assertTrue(predicateOne.equals(predicateOneCopy));

        // different types -> returns false
        assertFalse(predicateOne.equals(1));

        // null -> returns false
        assertFalse(predicateOne.equals(null));

        // different person -> returns false
        assertFalse(predicateOne.equals(predicateTwo));
    }

    @Test
    public void test_dataWithinNormalValidHour_returnsTrue() {
        MeetingWithinHoursPredicate predicate = new MeetingWithinHoursPredicate(10000);
        String testDateTime = "10/1/21 1201";
        assertTrue(predicate.test(new MeetingBuilder().withDateTime(testDateTime).build()));
    }

    @Test
    public void test_dataWithinBoundaryHour_returnTrue() {
        MeetingWithinHoursPredicate predicate = new MeetingWithinHoursPredicate(10);
        assertTrue(predicate.test(TypicalMeetings.getLiveMeeting()));
    }

    @Test
    public void test_dataNotWithinHour_returnsFalse() {
        MeetingWithinHoursPredicate predicate = new MeetingWithinHoursPredicate(1);
        String testDateTime = "10/1/21 1201";

        // Meeting occur before current DateTime
        assertFalse(predicate.test(new MeetingBuilder().withDateTime(MeetingBuilder.DEFAULT_DATETIME).build()));

        // Meeting outside hour range from user input
        assertFalse(predicate.test(new MeetingBuilder().withDateTime(testDateTime).build()));
    }

    @Test
    public void test_dataNotWithinHourBoundary_returnFalse() {
        // The live meeting is scheduled to be 10 hours after current time
        MeetingWithinHoursPredicate predicate = new MeetingWithinHoursPredicate(10 - 1);
        assertFalse(predicate.test(TypicalMeetings.getLiveMeeting()));
    }


}
