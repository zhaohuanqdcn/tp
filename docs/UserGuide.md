---
layout: page
title: User Guide
---

Recretary is a **desktop app for managing contacts and meetings, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Recretary can get your contact management tasks done faster than traditional GUI apps.

**Table of Content**
-   Quick Start
-   Features
    -   Contact Management
        -   Adding a person: `addcontact`
        -   Listing all persons: `listcontact`
        -   Editing a person: `editcontact`
        -   Locating persons: `findcontact`
        -   Deleting a person: `deletecontact`
        -   Clearing all entries: `clearcontact`
    -   Meeting Management
        -   Adding a meeting: `addmeeting`
        -   Listing all meetings: `listmeeting`
        -   Editing a meeting: `editmeeting`
        -   Locating meetings: `findmeeting`
        -   Deleting a meeting: `deletemeeting`
        -   Adding a participant into a meeting: `addpart`
        -   Clearing all entries: `clearmeeting`
        -   Remind meeting: `remindmeeting`
        -  Exporting meetings in .ics format: `exportmeeting`

    -   General
        -   Viewing help : `help`
        -   Undo : `undo`
        -   Exiting the program : `exit`
        -   Update user preference : `edituserpref`
-   FAQ
-   Command summary


## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `recretary.jar` from [here](https://github.com/se-edu/recretary/releases).

1. Copy the file to the folder you want to use as the _home folder_ for Recretary.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data. You can get familiar with our app by following the simple instructions below and observe the changes in your GUI. You can also just type `clearcontact` and `clearmeeting` separately to clear all data.
   ![Ui](images/Ui.png)

1. For you to get familiar with the app and to practice, type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.
   Some example commands you can try:

    - **`listcontact`** : Lists all contacts.

    - **`addcontact`** `add_contact n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 c/ABC Holdings Pte. Ltd` : Adds a contact named `John Doe` to the Address Book.

    - **`deletemeeting`**`1` : Deletes the 1st meeting shown in the current list.

    - **`clearmeeting`** : Deletes all meetings.
    
    - **`clearcontact`** : Deletes all contacts. 

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
   
-   Although it is not recommended to supply duplicate parameters, they are accepted as only the last parameter of the same type will be considered.<br>
    e.g. if the command entered by user is `editcontact 1 n/John n/Bob`, only `n/Bob` will be considered as there are two `n/` parameters and thus only the last is considered. 

</div>

### Contact Management

Contact entries in Recretary contain multiple pieces of information: name, phone number, email, address and company. There are also optional entries company role and tags that may help you organize your contacts better. Contact information will be displayed on the left-hand-side of the window.

#### Adding a person: `addcontact`

Adds a person to the address book.

Format: `addcontact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/COMPANY [r/COMPANY_ROLE]…​ [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb:

**Tip:**
A person can have any number of tags and company roles (including 0)

</div>

Examples:

-   `addcontact n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 c/ABC Holdings Pte. Ltd`
-   `addcontact n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Mansion p/1234567 r/CEO c/DEF Company`

#### Listing all persons: `listcontact`

Shows a list of all persons in the address book.

Format: `listcontact`

#### Editing a person: `editcontact`

Edits an existing person in the address book.

Format: `editcontact INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [c/COMPANY] [t/TAG]… [r/COMPANY_ROLE]…`

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

#### Locating persons: `findcontact`

Find contacts whose names contain any of the given keywords.

Format: `findcontact KEYWORD [MORE_KEYWORDS]`

-   The search is case-insensitive. e.g `hans` will match `Hans`
-   The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
-   Only the name is searched.
-   Only full words will be matched e.g. `Han` will not match `Hans`
-   Persons matching at least one keyword will be returned (i.e. `OR` search).
    e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:

-   `findcontact John` returns `john chan` and `John Doe`
-   `findcontact bernice david` returns:
    ![result for 'find bernice david'](images/findBerniceDavidResult.png)

<div markdown="span" class="alert alert-primary">:framed_picture:

**Visual Walkthrough Guide:**

State of the app *BEFORE* the `findcontact John` command.

   ![findJohnBefore](images/findJohnBefore.png)

State of the app *AFTER* the `findcontact John` command.

   ![findJohnAfter](images/findJohnResult.png)

</div>


#### Deleting a person: `deletecontact`

Deletes the specified person from the address book.

Format: `deletecontact INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `deletecontact 2` deletes the 2nd contact in the address book.

* `findcontact Betsy` followed by `deletecontact 1` deletes the 1st contact in the results of the `find` command.

#### Clearing all persons: `clearcontact`

Clears all persons from the address book.

Format: `clearcontact`

   
### Meeting Management

Meeting entries in Recretary have multiple attributes: date time, duration, title and location. You may declare the recurrence of a meeting to avoid repetitive input, and you may also add contacts as participants of a meeting. All meeting information will be displayed on the right-hand-side of the window, ordered in starting time, with a green bar indicating the next upcoming meeting. When a meeting starts, the green bar will move to the next meeting automatically. 

#### Adding a meeting: `addmeeting`

Adds a meeting into the meeting schedule. The existing list of meetings will be automatically sorted afterwards according to date and time. It also takes meeting interval into consideration and checks for meeting conflict (since your boss is always a participant in these meetings, therefore meetings can't overlap with each other). 


Format: `addmeeting d/DATETIME dur/DURATION title/TITLE l/LOCATION [rec/RECURRENCE]`

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

-   The number of recurrences added is by default 5, and can be edited in `UserPrefs`. //THIS NEEDS TO BE EDITED

</div>

Examples:

-   `addmeeting title/abc company meeting d/31/12/20 1400 dur/00 50 l/John street, block 123, #01-01`

<div markdown="span" class="alert alert-primary">:bulb:

**Note:**
After adding a meeting, add new participants to it with the `addpart` described command below.
Only people in your contacts can be added as participants.

</div>

#### Adding a participant into a meeting: `addpart`

Adds a participant with the specified `CONTACT_INDEX` in the currently viewable contact list into the meeting with the specified `MEETING_INDEX`.

Format: `addpart ci/CONTACT_INDEX mi/MEETING_INDEX`

<div markdown="span" class="alert alert-primary">:bulb:

**Tip:**
Run a `findcontact` command before running an `addpart` to narrow the contact list so that you can easily add a contact instead of scrolling through the whole list!

Run a `findmeeting` command before running an `addpart` to narrow the meeting list so that you can easily add a meeting instead of scrolling through the whole list!

</div>

Examples:

-   `addpart ci/1 mi/3` adds the first contact in the whole list to the 3rd meeting.
-   `findcontact alice` followed by `addpart ci/1 mi/2` adds the first contact of the `findcontact` command's result into the 2nd meeting.

#### Listing all meetings: `listmeeting`

Shows a list of all meetings in the address book.

Format: `listmeeting`

#### Editing a meeting: `editmeeting`

Edits an existing meeting in the meeting schedule. Similar to adding a meeting, the sorting and conflict checking will also take place automatically. The `RECURRENCE` field is not modifiable, and the edition of recurring meeting will only edit the specified instance. If the title of a recurring meeting is edited, it is no longer considered as an instance of recurrence.

Format: `editmeeting INDEX [d/DATETIME] [t/TITLE] [l/LOCATION] ...`

Delete participants in a meeting with this format:  //NEEDS TO BE EDITED
E.g.  
Recretary: `Here is the current list of participants.`  
<code> &nbsp; 1. John doe, abc company </code>  
<code> &nbsp; 2. John doe, def company </code>  
`Enter the next participant’s index to delete`  
User: `editmeeting INDEX deletepart/ 1`

<div markdown="span" class="alert alert-primary">:bulb:

**Tip:**
You can add new participants to a meeting with the separate `addpart` command.

</div>

-   Edits the meeting at the specified `INDEX`. The index refers to the index number shown in the displayed meeting list.
    The index **must be a positive integer** 1, 2, 3, …​
-   At least one of the optional fields must be provided.
-   Existing values will be updated to the input values.

Examples:

* `editmeeting 1 d/10/11/20 1400 l/clementi` Edits the datetime and location of the 1st meeting to be 
`10/11/2020 1400` and `clementi` respectively.
    
#### Locating meetings: `findmeeting`

Find meetings whose titles contain any of the given keywords.

Format: `findmeeting KEYWORD [MORE_KEYWORDS]`

-   The search is case-insensitive. e.g `discuss` will match `Discuss`
-   The order of the keywords does not matter. e.g. `Shareholder Meeting` will match `Meeting Shareholder`
-   All fields (title, participants etc) are searched.
-   Only full words will be matched e.g. `Team` will not match `Teams`
-   Meetings matching at least one keyword will be returned (i.e. `OR` search).
    e.g. `Shareholder Meeting` will return `Shareholder Chat`, `Team Meeting`

Examples:

-   `findmeeting abc def` returns `abc meeting`, `def meeting`<br>

<div markdown="span" class="alert alert-primary">:framed_picture:

**Visual Walkthrough Guide:**

State of the app *BEFORE* the `findmeeting` command.

   ![findMeetingBefore](images/findMeetingBefore.png)

State of the app *AFTER* the `findmeeting v1.3` command.

   ![findMeetingAfter](images/findMeetingAfter.png)

</div>

#### Deleting a meeting: `deletemeeting`

Deletes the specified item (and its recurrernces) from the address book.

Format: `deletemeeting INDEX [rec/RECURRING]`

* Deletes the meeting at the specified `INDEX`.
* The index refers to the index number shown in the displayed list.
* The index **must be a positive integer** 1, 2, 3, …​
* The recurring must be either `true` or `false`.

Examples:
* `deletemeeting 2` deletes the 2nd meeting in the meeting schedule.
* `deletemeeting 2 rec/true` deletes the 2nd meeting and all its recurrences in the address book.
* `findmeeting Shareholder` followed by `deletemeeting 1` deletes the 1st contact in the results of the `find` command.

#### Clearing all meetings : `clearmeeting`

Clears all meetings from the meeting schedule.

Format: `clearmeeting`


#### Remind meetings: `remindmeeting`

Search and display all meetings that will occur within the hours specify by the user.

Format: `remindmeeting HOUR`

-   HOUR must be a positive integer, the range of this value is from 1 - 2147483647 (user most likely won't have so many data to keep track of in a practical usage).
-   The reference point of time is the time on user's local machine when user entered the command.

Examples:

-   `remindmeeting 1440` returns ` abc meeting`, `xyz meeting`<br> 
Remark: 1440 = 2(months) * 30(days) * 24(hours) which is the total hours for two months; user can use this strategy to standardize the unit of time(hours) beforehand

**Visual Walkthrough Guide:**

State of the app *BEFORE* the `remindmeeting` command.

   ![Picture for GUI before remind command](images/remind_meeting_before.png)

State of the app *AFTER* the `remindmeeting 1440` command.

   ![Picture for GUI after remind command](images/remind_meeting_after.png)

#### Exporting meetings in .ics format : `exportmeeting`

Exports all meetings as an iCalendar file that is compatible with other calendar apps such as Google Calendar. By default, the resulting file can be found in the `data` folder. Check the FAQ section to see how to change the save location.

Format: `exportmeeting`



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

#### Update user preference : `edituserpref i/INTERVAL`

-  Edit the user preferred intervals between meetings. 
-   The dafault value is 0 (which means that interval is not considered). 
-   This interval value will be used for conflict checking and meant to simulate the resting or travel time between meetings.
-  `i/INTERVAL` indicates the interval between meetings. Note that there is a range restriction similar to the `remindmeeting` command

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
**Edit user preference** | `edituserpref i/10`
***Contacts*** |
**Add** | `addcontact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/COMPANY [r/COMPANY_ROLE] [t/TAG]…` <br> e.g., `add_contact n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd a/XYZ Company r/manager t/friend` 
**Delete** | `deletecontact INDEX` <br> e.g., `delete_contact 3`
**Edit** | `editcontact INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [c/COMPANY] [r/COMPANY_ROLE] [t/TAG]…`<br> e.g.,`edit_contact 2 n/James Lee e/jameslee@example.com`
**Find** | `findcontact KEYWORD [MORE_KEYWORDS]`<br> e.g., `find_contact James Jake`
**List** | `listcontact`
**Clear** | `clearcontact`
***Meetings*** |
**Add** |`addmeeting d/DATETIME dur/DURATION title/TITLE l/LOCATION [rec/RECURRENCE]` <br> e.g., `add_meeting d/31/12/20 1400 dur/01 00 title/xyz meeting l/John street, block 1, #01-01 rec/weekly`
**Add Participant** |`addpart ci/[INDEX] mi/[INDEX]`<br> e.g., `add_part ci/1 mi/3`
**Delete** | `deletemeeting INDEX [rec/RECURRING]`<br> e.g., `delete_meeting 5 rec/true`
**Edit** | `editmeeting INDEX [d/DATETIME] [dur/DURATION] [t/TITLE] [l/LOCATION]`<br> e.g.,`edit_meeting 1 dur/01 30 l/COM2 LT17`
**Find** | `findmeeting KEYWORD [MORE_KEYWORDS]`<br> e.g., `find_meeting recretary stakeholders`
**List** | `listmeeting`
**Clear** | `clearmeeting`
**Remind** | `remindmeeting`


