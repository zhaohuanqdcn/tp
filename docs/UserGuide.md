---
layout: page
title: User Guide
---


Recretary is a **desktop app for managing contacts and meetings, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Recretary can get your contact management tasks done faster than traditional GUI apps.

* First Run
* Features
    * Contact & Meeting Management
        * Adding a person/ meeting : `add_contact | add_meeting`
        * Listing all persons/ meetings: `list_contact | list_meeting` 
        * Editing a person/ meeting: `edit_contact | edit_meeting` 
        * Locating persons/ meetings: `find_contact | find_meeting` 
        * Deleting a person/ meeting: `delete_contact | delete_meeting`
    * General
        * Clearing all entries : `clear_contact | clear_meeting`
        * Viewing help : `help`
        * Exiting the program : `exit`
* FAQ
* Command summary
  
    

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

. Download the latest `recretary.jar` from [here](https://github.com/se-edu/recretary/releases).

1. Copy the file to the folder you want to use as the _home folder_ for Recretary.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</div>


### Contact & Meeting management
#### Adding a person / meeting: `add`

Adds a person or meeting to the address book.

Format: `add_contact | add_meeting`

Adds a person to the address book.
 
Format: `add_contact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/COMPANY [r/COMPANY_ROLE] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: 

**Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add_contact n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 c/ABC Holdings Pte. Ltd`
* `add_contact n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Mansion p/1234567 r/CEO c/DEF Company`

Adds a meeting into the meeting schedule 
 
Format: `add_meeting d/DATETIME dur/DURATION title/TITLE l/LOCATION`
where duration is of format `HH mm` and datetime is of format `d/M/y HHmm`

Add participants into the meeting with this format:  
E.g.  
Recretary: `Find the next participant’s name.`  
User: `find_contact john`  
Recretary: `Here is a list of your contacts that match ‘john doe’`  
<code> &nbsp; 1. John doe, abc company </code>  
<code> &nbsp; 2. John lee, def company </code>  
User: `add_part n/john i/2`  

<div markdown="span" class="alert alert-primary">:bulb: 

**Tip:**
Only people in your contacts can be added as participants.
</div>

Examples:
* `add_meeting d/31/12/20 1400 dur/00 60 title/abc company meeting l/John street, block 123, #01-01`

#### Listing all persons / meetings: `list`

Format: `list_contact | list_meeting`

Shows a list of all persons in the address book.

Format: `list_contact`
 
Shows a list of all meetings in the address book.

Format: `list_meeting`


#### Editing a person / meeting: `edit`

Edits an existing person in the address book.

Format: `edit_contact | edit_meeting`

Edits an existing person in the address book.
 
Format: `edit_contact INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [c/COMPANY] [t/TAG] [r/COMPANY_ROLE]…`

* Edits the person at the specified `INDEX`. The index refers to the index number 
shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit_contact 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be 
`91234567` and `johndoe@example.com` respectively.
*  `edit_contact 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

Edits an existing meeting in the meeting schedule.
 
Format: `edit_meeting INDEX [d/DATETIME] [title/TITLE] [l/LOCATION] [add_part/ (name) INDEX] [del_part P_INDEX]...`

Add new participant in this format:
Recretary: `Find the next participant’s name.`  
User: `find_contact john`  
Recretary: `Here is a list of your contacts that match ‘john doe’`  
<code> &nbsp; 1. John doe, abc company </code>  
<code> &nbsp; 2. John lee, def company </code>  
User: `edit_meeting INDEX add_part/ john 1`
  
Delete participants in a meeting with this format:  
E.g.   
Recretary: `Here is the current list of participants.`  
<code> &nbsp; 1. John doe, abc company </code>  
<code> &nbsp; 2. John doe, def company </code>  
`Enter the next participant’s index to delete`  
User: `edit_meeting INDEX del_part/ 1`   

* Edits the meeting at the specified `INDEX`. The index refers to the index number shown in the displayed meeting list. 
The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
 
* `edit_meeting 1 d/10/11/20 1400 l/clementi` Edits the datetime and location of the 1st meeting to be 
`10/11/2020 1400` and `clementi` respectively.

#### Locating persons / meetings: `find`

Finds persons whose names contain any of the given keywords.

Format: `find_contact | find_meeting`

Find contacts/ meetings whose names contain any of the given keywords.
 
Format: `find_meeting KEYWORD [MORE_KEYWORDS]` or
`find_contact KEYWORD [MORE_KEYWORDS]`


* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find_contact John` returns `john chan` and `John Doe`
* `find_meeting abc def` returns `abc meeting`, `def meeting`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

#### Deleting a person / meeting: `delete`

Deletes the specified item from the address book.

Format: `delete_contact | delete_meeting INDEX`

Format: `delete_contact INDEX`

* Deletes the item at the specified `INDEX`.
* The index refers to the index number shown in the displayed list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delete_contact 2` deletes the 2nd contact in the address book.
* `find_contact Betsy` followed by `delete_contact 1` deletes the 1st contact in the results of the `find` command.
* Use `list_meeting` to check the index of the meeting to be deleted, followed by `delete_meeting 2` to delete the 2nd meeting in the address book.

### General 

#### Clearing all entries : `clear_contact | clear_meeting`

Clears all entries from the address book.

Format: `clear_contact | clear_meeting`

#### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

#### Exiting the program : `exit`

Exits the program.

Format: `exit`

#### Saving the data

Recretary data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Recretary home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add_contact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/COMPANY [r/COMPANY_ROLE] [t/TAG]…` <br> e.g., `add_contact n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd a/XYZ Company r/manager t/friend` <br> `add_meeting d/DATETIME dur/DURATION title/TITLE l/LOCATION` <br> e.g., `add_meeting d/31/12/20 1400 dur/01 00 title/xyz meeting l/John street, block 1, #01-01`
**Delete** | `delete_contact INDEX`<br> e.g., `delete_contact 3` <br> `delete_meeting INDEX`<br> e.g., `delete_meeting 5`
**Edit** | `edit_contact INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [c/COMPANY] [r/COMPANY_ROLE] [t/TAG]…`<br> e.g.,`edit_contact 2 n/James Lee e/jameslee@example.com` <br> `edit_meeting INDEX [d/DATETIME] [dur/DURATION] [t/TITLE] [l/LOCATION]`<br> e.g.,`edit_contact 1 dur/01 30 l/COM2 LT17`
**Find** | `find_contact KEYWORD [MORE_KEYWORDS]`<br> e.g., `find_contact James Jake` <br> `find_meeting KEYWORD [MORE_KEYWORDS]`<br> e.g., `find_meeting recretary stakeholders`
**List** | `list_contact` <br> `list_meeting`
**Clear** | `clear_contact` <br> `clear_meeting`
**Help** | `help`
**Exit** | `exit`
