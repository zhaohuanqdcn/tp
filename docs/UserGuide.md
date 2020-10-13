---
layout: page
title: User Guide
---

Recretary is a **desktop app for managing contacts and meetings, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Recretary can get your contact management tasks done faster than traditional GUI apps.

-   First Run
-   Features
    -   Contact Management
        -   Adding a person: `add_contact`
        -   Listing all persons: `list_contact`
        -   Editing a person: `edit_contact`
        -   Locating persons: `find_contact`
        -   Deleting a person: `delete_contact`
        -   Clearing all entries: `clear_contact`
    -   Meeting Management
        -   Adding a meeting: `add_meeting`
        -   Listing all meetings: `list_meeting`
        -   Editing a meeting: `edit_meeting`
        -   Locating meetings: `find_meeting`
        -   Deleting a meeting: `delete_meeting`
        -   Adding a participant into a meeting: `add_part`
        -   Clearing all entries: `clear_meeting`
    -   General
        -   Viewing help : `help`
        -   Exiting the program : `exit`
-   FAQ
-   Command summary


## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `recretary.jar` from [here](https://github.com/se-edu/recretary/releases).

1. Copy the file to the folder you want to use as the _home folder_ for Recretary.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.
   Some example commands you can try:

    - **`list_contact`** : Lists all contacts.

    - **`add_contact`** `add_contact n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 c/ABC Holdings Pte. Ltd` : Adds a contact named `John Doe` to the Address Book.

    - **`delete_meeting`**`3` : Deletes the 3rd meeting shown in the current list.

    - **`clear_meeting`** : Deletes all meetings.

    - **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

-   Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
    e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

-   Items in square brackets are optional.<br>
    e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

-   Items with `…`​ after them can be used multiple times including zero times.<br>
    e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

-   Parameters can be in any order.<br>
    e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</div>

### Contact Management

#### Adding a person: `add_contact`

Adds a person to the address book.

Format: `add_contact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/COMPANY [r/COMPANY_ROLE] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb:

**Tip:**
A person can have any number of tags (including 0)

</div>

Examples:

-   `add_contact n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 c/ABC Holdings Pte. Ltd`
-   `add_contact n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Mansion p/1234567 r/CEO c/DEF Company`

#### Listing all persons: `list_contact`

Shows a list of all persons in the address book.

Format: `list_contact`

#### Editing a person: `edit_contact`

Edits an existing person in the address book.

Format: `edit_contact INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [c/COMPANY] [t/TAG] [r/COMPANY_ROLE]…`

-   Edits the person at the specified `INDEX`. The index refers to the index number
    shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
-   At least one of the optional fields must be provided.
-   Existing values will be updated to the input values.
-   When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
-   You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:

-   `edit_contact 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be
    `91234567` and `johndoe@example.com` respectively.
-   `edit_contact 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

#### Locating persons: `find_contact`

Find contacts whose names contain any of the given keywords.

Format: `find_contact KEYWORD [MORE_KEYWORDS]`

-   The search is case-insensitive. e.g `hans` will match `Hans`
-   The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
-   Only the name is searched.
-   Only full words will be matched e.g. `Han` will not match `Hans`
-   Persons matching at least one keyword will be returned (i.e. `OR` search).
    e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:

-   `find_contact John` returns `john chan` and `John Doe`
    ![result for 'find alex david'](images/findAlexDavidResult.png)

#### Deleting a person: `delete_contact`

Deletes the specified person from the address book.

Format: `delete_contact INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delete_contact 2` deletes the 2nd contact in the address book.
* `find_contact Betsy` followed by `delete_contact 1` deletes the 1st contact in the results of the `find` command.

#### Clearing all persons: `clear_contact`

Clears all persons from the address book.

Format: `clear_contact`

   
### Meeting Management

#### Adding a meeting: `add_meeting`

Adds a meeting into the meeting schedule.

Format: `add_meeting d/DATETIME dur/DURATION title/TITLE l/LOCATION`

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

-   The format for `DATETIME` is `d/M/yy HHmm`. <br>
    e.g. `d/1/11/20 1430`.
    e.g. `d/1/1/20 1430`.
    e.g. `d/12/11/20 1430`.

-   The format for `DURATION` is `H mm`. <br>
    e.g. `dur/1 30`.
    
-   The number of minutes in `DURATION` cannot exceed `59`.

</div>

<div markdown="span" class="alert alert-primary">:bulb:

**Tip:**
After adding a meeting, add new participants to it with the `add_part` command.
Only people in your contacts can be added as participants.

</div>

Examples:

-   `add_meeting title/abc company meeting d/31/12/20 1400 dur/00 60 l/John street, block 123, #01-01`

#### Listing all meetings: `list_meeting`

Shows a list of all meetings in the address book.

Format: `list_meeting`

#### Editing a meeting: `edit_meeting`

Edits an existing meeting in the meeting schedule.

Format: `edit_meeting INDEX [d/DATETIME] [t/TITLE] [l/LOCATION] [del_part P_INDEX]...`

Delete participants in a meeting with this format:  
E.g.  
Recretary: `Here is the current list of participants.`  
<code> &nbsp; 1. John doe, abc company </code>  
<code> &nbsp; 2. John doe, def company </code>  
`Enter the next participant’s index to delete`  
User: `edit_meeting INDEX del_part/ 1`

<div markdown="span" class="alert alert-primary">:bulb:

**Tip:**
You can add new participants to a meeting with the separate `add_part` command.

</div>

-   Edits the meeting at the specified `INDEX`. The index refers to the index number shown in the displayed meeting list.
    The index **must be a positive integer** 1, 2, 3, …​
-   At least one of the optional fields must be provided.
-   Existing values will be updated to the input values.

Examples:

* `edit_meeting 1 d/10/11/20 1400 l/clementi` Edits the datetime and location of the 1st meeting to be 
`10/11/2020 1400` and `clementi` respectively.
    
#### Locating meetings: `find_meeting`

Find meetings whose titles contain any of the given keywords.

Format: `find_meeting KEYWORD [MORE_KEYWORDS]`

-   The search is case-insensitive. e.g `discuss` will match `Discuss`
-   The order of the keywords does not matter. e.g. `Shareholder Meeting` will match `Meeting Shareholder`
-   Only the title is searched.
-   Only full words will be matched e.g. `Team` will not match `Teams`
-   Meetings matching at least one keyword will be returned (i.e. `OR` search).
    e.g. `Shareholder Meeting` will return `Shareholder Chat`, `Team Meeting`

Examples:

-   `find_meeting abc def` returns `abc meeting`, `def meeting`<br>

#### Deleting a meeting: `delete_meeting`

Deletes the specified item from the address book.

Format: `delete_meeting INDEX`

* Deletes the meeting at the specified `INDEX`.
* The index refers to the index number shown in the displayed list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delete_meeting 2` deletes the 2nd meeting in the meeting schedule.
* `find_meeting Shareholder` followed by `delete_meeting 1` deletes the 1st contact in the results of the `find` command.

#### Adding a participant into a meeting: `add_part`

Adds a participant with the specified `CONTACT_INDEX` in the currently viewable contact list into the meeting with the specified `MEETING_INDEX`.

Format: `add_part ci/CONTACT_INDEX mi/MEETING_INDEX`

<div markdown="span" class="alert alert-primary">:bulb:

**Tip:**
Run a `find_contact` command before running an `add_part` to narrow the contact list so that you can easily add a contact instead of scrolling through the whole list!

Run a `find_meeting` command before running an `add_part` to narrow the meeting list so that you can easily add a meeting instead of scrolling through the whole list!

</div>

Examples:

-   `add_part ci/1 mi/3` adds the first contact in the whole list to the 3rd meeting.
-   `find_contact alice` followed by `add_part ci/1 mi/2` adds the first contact of the `find_contact` command's result into the 2nd meeting.

#### Clearing all meetings : `clear_meeting`

Clears all meetings from the meeting schedule.

Format: `clear_meeting`


### General

#### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

#### Exiting the program : `exit`

Exits the program.

Format: `exit`

#### Saving the data

Recretary data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Recretary home folder.

---

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Exit** | `exit`
**Contacts**
**Add** | `add_contact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/COMPANY [r/COMPANY_ROLE] [t/TAG]…` <br> e.g., `add_contact n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd a/XYZ Company r/manager t/friend` 
**Delete** | `delete_contact INDEX`<br> e.g., `delete_contact 3`
**Edit** | `edit_contact INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [c/COMPANY] [r/COMPANY_ROLE] [t/TAG]…`<br> e.g.,`edit_contact 2 n/James Lee e/jameslee@example.com`
**Find** | `find_contact KEYWORD [MORE_KEYWORDS]`<br> e.g., `find_contact James Jake`
**List** | `list_contact`
**Clear** | `clear_contact`
**Meetings**
**Add** |`add_meeting d/DATETIME dur/DURATION title/TITLE l/LOCATION` <br> e.g., `add_meeting d/31/12/20 1400 dur/01 00 title/xyz meeting l/John street, block 1, #01-01`
**Add Participant** |`add_part ci/[INDEX] mi/[INDEX]`<br> e.g., `add_part ci/1 mi/3`
**Delete** | `delete_meeting INDEX`<br> e.g., `delete_meeting 5`
**Edit** | `edit_meeting INDEX [d/DATETIME] [dur/DURATION] [t/TITLE] [l/LOCATION]`<br> e.g.,`edit_meeting 1 dur/01 30 l/COM2 LT17`
**Find** | `find_meeting KEYWORD [MORE_KEYWORDS]`<br> e.g., `find_meeting recretary stakeholders`
**List** | `list_meeting`
**Clear** | `clear_meeting`


