---
layout: page
title: Adithya Narayan Rangarajan Sreenivasan's Project Portfolio Page
---

## Project: AddressBook Level 3

Recretary is a tool that helps Executive Personal Secretaries manage their day-to-day tasks and take care of their executive's busy meeting schedule. The aim is to reduce the burden and the workload of the Executive Personal Secretaries. While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface). It is written in Java, and has about 14 kLoC.

Given below are my contributions to the project.

* **Major Enhancement**: Responsible for the UI overhaul and updated the entire frontend
  * Highlights: Making it work cross-platform full screen was a hassle that I had sucessfully solved. Also, good knowledge of JavaFX and ListView was required for the timeline bar in the meeting section and the green bar of the current time.

* **New Feature**: Added the ability to undo previous commands.
  * What it does: allows the user to undo all previous commands one at a time or also mulitple commands at once!
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. After careful consideration, I decided to use the Memento pattern to rectify the undo rather than the command pattern as it can undo multiple commands at once.

* **New Feature**: Added a command session history feature that allows the user to navigate to previous commands using up/down keys.
  * Justification: I thought it would be good if the app could stay true to its CLI roots and hence this feature was introduced for ease of use as users can change text in previous commands to form a new command. 

* **New Feature**: Added a `findmeeting` feature.
  * Justification: Easily find meetings that match with name, date etc. Natural Date matching felt like a must have and hence was implemented.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=w16&sort=groupTitle&sortWithin=title&since=2020-08-14&until=2020-11-09&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=AdithyaNarayan&tabRepo=AY2021S1-CS2103T-W16-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub

* **Documentation**:
  * User Guide:
    * Added section on using the user guide and overview of the user interface: [\#185](https://github.com/AY2021S1-CS2103T-W16-1/tp/pull/185) and [\#191](https://github.com/AY2021S1-CS2103T-W16-1/tp/pull/191)
    * Added documentation for the features `findmeeting`, `undo` and Command Session History: [\#110](https://github.com/AY2021S1-CS2103T-W16-1/tp/pull/110)
  * Developer Guide:
    * Added implementation details of the `findcontact`, `findmeeting`, `undo` features: [\#191](https://github.com/AY2021S1-CS2103T-W16-1/tp/pull/191)
    * Updated UI architecture to match the updated UI: [\#191](https://github.com/AY2021S1-CS2103T-W16-1/tp/pull/191)

* **Community**:
  * Helped fellow teammates with some implementation issues like
    * updating ui using UUID
    * deleting recurring meetings
  * Helped other teams with bug finding ([Link](https://github.com/adithyaNarayan/ped))
  * Helped another team in class tutorial with PlantUML
