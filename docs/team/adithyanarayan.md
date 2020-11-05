---
layout: page
title: Adithya Narayan Rangarajan Sreenivasan's Project Portfolio Page
---

## Project: AddressBook Level 3

Recretary is a tool that helps Executive Personal Secretaries manage their day-to-day tasks and take care of their executive's busy meeting schedule. The aim is to reduce the burden and the workload of the Executive Personal Secretaries. While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface). It is written in Java, and has about 14 kLoC.

Given below are my contributions to the project.

* **Major Enhancement**: Responsible for the UI overhaul and updated the entire frontend

* **New Feature**: Added the ability to undo previous commands.
  * What it does: allows the user to undo all previous commands one at a time or also mulitple commands at once!
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. After careful consideration, I decided to use the Memento pattern to rectify the undo rather than the command pattern as it can undo multiple commands at once.

* **New Feature**: Added a history command that allows the user to navigate to previous commands using up/down keys.
  * Justification: I thought it would be good if the app could stay true to its CLI roots and hence this feature was introduced for ease of use as users can change text in previous commands to form a new command. 


* **Code contributed**: [RepoSense link]()

* **Project management**:
  * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete` and `find` [\#72]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `delete` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
