---
layout: page
title: Zhao Huan's Project Portfolio Page
---

## Project: Recretary

Recretary is a desktop app for managing contacts and meetings, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). For fast typists, Recretary is the faster option for finishing contact management tasks than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added some basic commands for meeting management.
  * What it does: This allows the user to delete, view and clear existing meetings.
  * Justification: These commands are among basic requirements of the app. 

* **New Feature**: Added the support for recurring meetings.
  * What it does: This allows the user to add, edit and delete recurring meetings.
  * Justification: This feature improves the product effectively because recurring meetings are common in daily use, and enabling recurrences drastically reduces the time to repetitively input similar meeting information.  

* **New Feature**: Implemented a system-wide scheduler to enable automated tasks.
  * What it does: A system timer provides the ability to automatically update the application without user interference. Together with variants of scheduled tasks implemented, features like auto-Ui updates and pop-up reminders are now possible. 
  * Justification: Though this functionality may not be easily observed by the users, the extendability it provides is significant. Always-up-to-date Ui and auto-pop-up reminders are two examples. Later on, any proposed automated tasks can be easily written. 
  * Highlights: This enhancement requires an in-depth knowledge of the structure of the base code. It is also not straightforward in terms of implementation, as it involves multi-thread programming and synchronization.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=zhaohuanqdcn)

* **Enhancements to existing features**:
  * Updated the Model and AddressBook to accommodate classes related to Meeting
  * Added tests for various features implemented by the team

* **Documentation**:
  * User Guide:
    * Added documentation for the basic commands implemented
    * Added documentation for automatic pop up reminders
    * Added short intro, command and prefix summary
  * Developer Guide:
    * Updated user stories and use cases for some features.
    * Added brief explanation of `list` and `clear` commands for both entities.
    * Added implementation details of the `deletemeeting`, `deletecontact` and `system timer` features.
