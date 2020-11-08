package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_USER_PREF;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditUserPrefCommand.EditUserPrefDescriptor;
import seedu.address.testutil.EditUserPrefDescriptorBuilder;

public class EditUserPrefDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditUserPrefDescriptor sameDescriptor = new EditUserPrefDescriptorBuilder(DESC_USER_PREF).build();
        EditUserPrefDescriptor differentDescriptor = new EditUserPrefDescriptorBuilder().withInterval(0).build();

        assertTrue(DESC_USER_PREF.equals(sameDescriptor));

        // same object -> returns true
        assertTrue(DESC_USER_PREF.equals(DESC_USER_PREF));

        // null -> returns false
        assertFalse(DESC_USER_PREF.equals(null));

        // different types -> returns false
        assertFalse(DESC_USER_PREF.equals("abc"));

        //different intervals -> returns false
        assertFalse(DESC_USER_PREF.equals(differentDescriptor));
    }

}
