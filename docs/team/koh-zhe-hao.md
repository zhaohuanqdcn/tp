---
layout: page
title: Koh Zhe Hao's Project Portfolio Page
---

## Project: Recretary

Recretary is a one-stop contact and meeting management application specially made for secretaries, to help them improve productivity in businesses. For fast typists, Recretary is the faster option for finishing contact management tasks than traditional GUI apps because it is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).


Given below are my contributions to the project.

* **New Feature**: Added the ability to find all meeting within certain hours relative to the time of user's local machine.
  * What it does: allows the user to view a list of upcoming meetings, user will specify the time 
  * Justification: This feature improves the product because a user can now proactively check for upcoming meetings (filter out late future meetings)
  * Credits: The design and implementation is somewhat similar to `find` command in the original AB3 base code.

* **New Feature**: Added a `edituserpref` command that allows the user to customize the interval value between meetings, and store the value inside preferences.json
  * What it does: allows the users to edit the interval value inside preferences.json, this will affect the behavior of conflict checking
  * Justification: This feature improves the product because now there is a customized break in between any two consecutive meetings. This break is important as it represents travel time or rest time in between meetings. This prevents users from scheduling tight/unreasonable meetings that might make the boss angry. 
  * Highlights: Although we only allow user to edit interval value in preferences.json for now, I implement it with `ArgumentMultimap` when parsing user input. Therefore, it is quite easy to extend this feature to allow users to edit and even add new field in preferences.json
  
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=KOH-ZHE-HAO&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed releases `v1.3b` on GitHub

* **Enhancements to existing features**:
  * Refactor code base and test cases so that all contacts have compulsory company fields and optional company role fields, minor refactor to differentiate contact and meeting commands also  (Pull requests [\#50](https://github.com/AY2021S1-CS2103T-W16-1/tp/pull/50), [\#54](https://github.com/AY2021S1-CS2103T-W16-1/tp/pull/54))
  * Implement sort function for `UniqueMeetingList`, by calling this function whenever the order of list changes, this will make sure the meeting list is always sorted at all time. (Pull requests [\#91](https://github.com/AY2021S1-CS2103T-W16-1/tp/pull/91))
  * Implement conflict checking function for `UniqueMeetingList` and call this method whenever needed, eg: after `addmeeting`, `editmeeting`, etc (Pull requests [\#104](https://github.com/AY2021S1-CS2103T-W16-1/tp/pull/104), [\#116](https://github.com/AY2021S1-CS2103T-W16-1/tp/pull/116))
  
  
* **Documentation**:
  * User Guide:
    * Added documentation for the features `edituserpref` and `remindmeeting` [\#171](https://github.com/AY2021S1-CS2103T-W16-1/tp/pull/171)
    * Did cosmetic tweaks to existing documentation of features `addmeeting`, `editmeeting`, etc: [\#171](https://github.com/AY2021S1-CS2103T-W16-1/tp/pull/171)
    * Minor improvement to overall user guide, eg: polish introduction, some choice of words, etc: [\#171](https://github.com/AY2021S1-CS2103T-W16-1/tp/pull/171)
  * Developer Guide:
    * Edit the class diagram to support everything related to meetings and `company`, `company role`.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
