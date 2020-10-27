---
layout: page
title: User Guide
---

Recretary is a **desktop app for managing contacts and meetings, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Recretary can get your contact management tasks done faster than traditional GUI apps.

## Table of Content

-   Quick Start
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
        -  Exporting meetings in .ics format: `export_meeting`
    -   General
        -   Viewing help : `help`
        -   Undo : `undo`
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

Contact entries in Recretary contain multiple pieces of information: name, phone number, email, address and company. There is also an optional company role and tags that may help you organize your contacts better. Contact information will be displayed on the left-hand-side of the window.

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

-   `find_contact John` returns `John Chan` and `John Doe`

<div markdown="span" class="alert alert-primary">:framed_picture:

**Visual Walkthrough Guide:**

State of the app *BEFORE* the `find_contact John` command.

   ![findJohnBefore](images/findJohnBefore.png)

State of the app *AFTER* the `find_contact John` command.

   ![findJohnAfter](images/findJohnResult.png)

</div>

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

Meeting entries in Recretary have multiple attributes: date time, duration, title and location. You may declare the recurrence of a meeting to avoid repetitive input, and you may also add contacts as participants of a meeting. All meeting information will be displayed on the right-hand-side of the window, ordered in starting time, with a green bar indicating the next upcoming meeting. When a meeting starts, the green bar will move to the next meeting automatically. 

#### Adding a meeting: `add_meeting`

Adds a meeting into the meeting schedule.

Format: `add_meeting d/DATETIME dur/DURATION title/TITLE l/LOCATION [rec/RECURRENCE]`

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

-   The format for `DATETIME` is `d/M/yy HHmm`. <br>
    e.g. `d/1/11/20 1430`.
    e.g. `d/1/1/20 1430`.
    e.g. `d/12/11/20 1430`.

-   The format for `DURATION` is `H mm`. <br>
    e.g. `dur/1 30`.
    
-   The number of minutes in `DURATION` cannot exceed `59`.

-   The field `RECURRENCE` can be one of `DAILY`, `WEEKLY` or `MONTHLY`.

-   The number of recurrences added is by default 5, and can be edited in `UserPrefs`.

</div>

Examples:

-   `add_meeting title/abc company meeting d/31/12/20 1400 dur/00 60 l/John street, block 123, #01-01`

<div markdown="span" class="alert alert-primary">:bulb:

**Tip:**
After adding a meeting, add new participants to it with the `add_part` command below.
Only people in your contacts can be added as participants.

</div>

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

#### Listing all meetings: `list_meeting`

Shows a list of all meetings in the address book.

Format: `list_meeting`

#### Editing a meeting: `edit_meeting`

Edits an existing meeting in the meeting schedule. The `RECURRENCE` field is not modifiable, and the edition of recurring meeting will only edit the specified instance. If the title of a recurring meeting is edited, it is no longer considered as an instance of recurrence.

Format: `edit_meeting INDEX [d/DATETIME] [t/TITLE] [l/LOCATION] ...`

Delete participants in a meeting with this format:  
E.g.  
Recretary: `Here is the current list of participants.`  
<code> &nbsp; 1. John doe, abc company </code>  
<code> &nbsp; 2. John doe, def company </code>  
`Enter the next participant’s index to delete`  
User: `edit_meeting INDEX delete_part/ 1`

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
-   All fields (title, participants etc) are searched.
-   Only full words will be matched e.g. `Team` will not match `Teams`
-   Meetings matching at least one keyword will be returned (i.e. `OR` search).
    e.g. `Shareholder Meeting` will return `Shareholder Chat`, `Team Meeting`

Examples:

-   `find_meeting abc def` returns `abc meeting`, `def meeting`<br>

<div markdown="span" class="alert alert-primary">:framed_picture:

**Visual Walkthrough Guide:**

State of the app *BEFORE* the `find_meeting` command.

   ![findMeetingBefore](images/findMeetingBefore.png)

State of the app *AFTER* the `find_meeting v1.3` command.

   ![findMeetingAfter](images/findMeetingAfter.png)

</div>

#### Deleting a meeting: `delete_meeting`

Deletes the specified item (and its recurrernces) from the address book.

Format: `delete_meeting INDEX [rec/RECURRING]`

* Deletes the meeting at the specified `INDEX`.
* The index refers to the index number shown in the displayed list.
* The index **must be a positive integer** 1, 2, 3, …​
* The recurring must be either `true` or `false`.

Examples:
* `delete_meeting 2` deletes the 2nd meeting in the meeting schedule.
* `delete_meeting 2 rec/true` deletes the 2nd meeting and all its recurrences in the address book.
* `find_meeting Shareholder` followed by `delete_meeting 1` deletes the 1st contact in the results of the `find` command.

#### Clearing all meetings : `clear_meeting`

Clears all meetings from the meeting schedule.

Format: `clear_meeting`

#### Exporting meetings in .ics format : `export_meeting`

Exports all meetings as an iCalendar file that is compatible with other calendar apps such as Google Calendar. By default, the resulting file can be found in the `data` folder. Check the FAQ section to see how to change the save location.

Format: `export_meeting`


### General

#### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

#### Undo : `undo`

Undoes the previous command or previous `n` commands based on the given index.
<div markdown="span" class="alert alert-primary">:framed_picture:

**Visual Walkthrough Guide:**

1. State of the app *BEFORE* the `delete_contact` command that you entered by mistake and wish to undo.

   ![undo1](images/undo1.png)

2. State of the app *AFTER* the `delete_contact` command that you entered by mistake and wish to undo.

   ![undo2](images/undo2.png)

3. State of the app *AFTER* the `undo` command.

   ![undo3](images/undo3.png)

</div>

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the undo command:**<br>
`undo` command is purposefully left out of the history and is hence not undoable. This is because you can undo previous commands before the undo to prevent being stuck in an undo loop. 
</div>

Format: `undo [INDEX]`

#### Exiting the program : `exit`

Exits the program.

Format: `exit`

#### Saving the data

Recretary data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Recretary home folder.

**Q**: Where is my Recretary data stored?<br>
**A**: By default, a  `data` folder will be created in the same folder as the JAR file. After running the app for the first time, you can change the file path by editing preferences.json in the same folder directly. 


---

## Command summary

Action | Format, Examples
--------|------------------
***Generals*** |
**Help** | `help`
**Exit** | `exit`
**Undo** | `undo`
***Contacts*** |
**Add** | `add_contact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/COMPANY [r/COMPANY_ROLE] [t/TAG]…` <br> e.g., `add_contact n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd a/XYZ Company r/manager t/friend` 
**Delete** | `delete_contact INDEX` <br> e.g., `delete_contact 3`
**Edit** | `edit_contact INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [c/COMPANY] [r/COMPANY_ROLE] [t/TAG]…`<br> e.g.,`edit_contact 2 n/James Lee e/jameslee@example.com`
**Find** | `find_contact KEYWORD [MORE_KEYWORDS]`<br> e.g., `find_contact James Jake`
**List** | `list_contact`
**Clear** | `clear_contact`
***Meetings*** |
**Add** |`add_meeting d/DATETIME dur/DURATION title/TITLE l/LOCATION [rec/RECURRENCE]` <br> e.g., `add_meeting d/31/12/20 1400 dur/01 00 title/xyz meeting l/John street, block 1, #01-01 rec/weekly`
**Add Participant** |`add_part ci/[INDEX] mi/[INDEX]`<br> e.g., `add_part ci/1 mi/3`
**Delete** | `delete_meeting INDEX [rec/RECURRING]`<br> e.g., `delete_meeting 5 rec/true`
**Edit** | `edit_meeting INDEX [d/DATETIME] [dur/DURATION] [t/TITLE] [l/LOCATION]`<br> e.g.,`edit_meeting 1 dur/01 30 l/COM2 LT17`
**Find** | `find_meeting KEYWORD [MORE_KEYWORDS]`<br> e.g., `find_meeting recretary stakeholders`
**List** | `list_meeting`
**Clear** | `clear_meeting`


