---
  layout: default.md
  title: "Bertrand's Project Portfolio Page"
---

### Project: RealtorTrackerPlusMax

RealtorTrackerPlusMax is a desktop application made for usage by real estate agents to keep track of their contacts, 
such as buyers and sellers. The user interacts with it using a CLI, and it has a GUI created with JavaFX. 
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the `buyer` command.
  * What it does: Allows users to add a new buyer.
  * Justification: A realtor would need to manage separate lists of buyers and sellers, and refer to each list
    individually depending on his objectives at a certain point in time. Having the ability to add a buyer and maintain
    a list of buyers is crucial for his contact management.
  * Credits: Inspired by the command to add person.
* **New Feature**: Added the ability to undo/redo previous commands.
  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. 
  * Credits: The undo & redo feature and its DG implementation details were reused from
    [Address Book (Level4)](https://github.com/se-edu/addressbook-level4) with minor modifications.
* **New Feature**: Added the ability to sort the buyer and seller lists with `bsort` and `ssort`.
  * What it does: The user can sort the buyer/seller list by specifying a `prefix` and a `direction` to sort the list
    by, or sort by the default input order by leaving the `prefix` and `direction` blank. The supported prefixes are
    `n` for name, `ah` for home address
  * Justification: A realtor needs to
  * Highlights: 

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=peasantbird&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22)

* **Project management**:
  * Managed 1.2b iteration
  * Managed releases `v1.2` and `v1.3` (2 releases) on GitHub, including manual tests on the product to ensure 
    correctness before release

* **Enhancements to existing features**:
  * Updated the GUI for the buyer and seller boxes, making them look more sleek and rounding the edges
    (Pull request [\#167](https://github.com/AY2324S1-CS2103T-F11-3/tp/pull/167))
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
