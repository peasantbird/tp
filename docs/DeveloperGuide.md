---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# RTPM Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

_{ list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well }_

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `DisplayableListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Displayable` objects residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("sdelete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteSellerCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteSellerCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteSellerCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a seller).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` 
(`XYZ` is a placeholder for the specific command name e.g., `AddSellerCommandParser`) which uses the other classes shown above 
to parse the user command and create a `XYZCommand` object (e.g., `AddSellerCommand`) which the `AddressBookParser` 
returns back as a `Command` object.

* `AddressBookParser` also creates a CommandWarnings object, which is used to handle user errors that do not require the system
to fail execution of the command (e.g. if user is trying to add seller with a non-alphanumeric name.) If the command inherits
from the `Parser` interface, then it will use this object to store warnings to output into the Command and eventually into 
the resulting CommandResult. It is also used by LogicManager to log warnings. Classes which do not implement `Parser` do
not use warnings (because they either execute successfully or fail; there is no potential for user misinput.)

* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...)
inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Displayable` objects (which are contained in the appropriate `UniqueDisplayableList<? extends Displayable>` object, in this case only buyers and sellers).
* stores the currently 'selected' `Displayable` objects (e.g., results of a search query) as a separate _filtered_ list which 
is exposed to outsiders as an unmodifiable `ObservableList<Buyer>`/`ObservableList<Seller>` that can be 'observed' e.g. 
the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</box>

The following sequence diagram shows how the undo operation works:

<puml src="diagrams/UndoSequenceDiagram.puml" alt="UndoSequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_

### Edit feature

#### Proposed Implementation

Description

Given below is an example usage scenario and how the edit mechanism behaves at each step.

Step 1. The user types in the `bedit` or `sedit` keyword, followed by the index of the buyer or seller that they want
to edit. Following that, they type one or more of `/PREFIX`, where `PREFIX` is a field that they want to edit. 
Then they type in a new 

<box type="info" seamless>

**Note:** If the index or field is invalid, no command will be executed.

</box>

The following sequence diagram shows how the edit operation works:

<puml src="diagrams/EditBuyerSequenceDiagram.puml" alt="EditBuyerSequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `EditBuyerCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

#### Design considerations:

**Aspect: How edit executes:**

* **Alternative 1 (current choice):** Creates an EditBuyerDescriptor or an EditSellerDescriptor to abstract away 
* all the Optional functionality
    * Pros: Assigns concern of dealing with optional fields to the Descriptor class, making the code within
  the `edit` commands easier to read and debug.
    * Cons: Requires creation of additional Descriptor class, which makes it harder to trace through the code

* **Alternative 2:** `edit` command parsers pass Optionals directly to the Buyer Command
    * Pros: Avoid creation of a new class that might introduce bugs
    * Cons: Optionals are not intended to be used as inputs to methods, as they introduce additional 
  work to be done in the method to handle the different inputs.


### \[Completed\] Priority feature

#### Implementation

The priority feature is associated with the `edit` and `sort` commands, allowing the user to assign and sort clients by 
their priority levels in the address book. The priority field is optional when instantiating buyers and sellers, and is
initially unassigned.

To implement this feature, the `Priority` field is firstly added to `Person`, and its corresponding UI Label is
rendered by modifying the `PersonCard.java` controller and the respective BuyerCard and SellerCard FXML files. 
* Since the priority field is 
optional, the `Buyer`/`Seller` constructor is overloaded, such that the one which does not take in priority as an 
argument will initialise priority to the default `nil` level. 
* The priority FXML Label is conditionally rendered 
in `PersonCard.java` based on the buyer/seller's priority field. For instance, its color is red for `high` priority,
orange for `medium`, green for `low`, and not rendered for `nil`.

To accommodate saving of buyers and sellers with the new priority fields in storage, `JsonAdaptedBuyer` and other
relevant files are modified to include these fields in JSON format, and to be readable and loaded back into `Model` in
subsequent RTPM initialisations.

To make it more convenient for the user to directly assign priorities to clients without having to use the `edit` 
command, the `SetBuyerPriority` and `SetSellerPriority` commands are implemented as part of this feature.

Given below is an example usage scenario for setting priorities for buyers in the address book's buyer list.

Step 1. The user launches the application and executes the `priority-b 2 high` command, which sets the priority level of the
2nd person in the buyer list to `high`. The `priority-b` command calls `LogicManager`, which gets `AddressBookParser`
to parse and obtain a `SetBuyerPriorityCommand`, before executing it. The command execution calls `ModelManager` to
update the address book's buyer list with the newly assigned buyer priority, which is reflected on the UI too. 
Finally, `LogicManager` calls `StorageManager` to update the JSON file.

The following sequence diagram shows how the undo operation works:
<puml src="diagrams/SetBuyerPrioritySequenceDiagram.puml" alt="SetBuyerPrioritySequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `SetBuyerPriorityCommand` should end at the destroy marker (X), but due to a 
limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

Step 2. To unassign the priority level of the 2nd person, the user can execute the `priority-b 2 nil` command, which 
runs a similar flow as illustrated in the sequence diagram above.

The same logic can be used for assigning priorities to sellers instead of buyers, by using `priority-s` instead 
of `priority-b`.

#### Design considerations:

**Aspect: How the optional priority field is implemented**

* **Alternative 1 (initial choice):** Overload the `Buyer`/`Seller` constructors.
    * Pros: Relatively simple to implement and refactor.
    * Cons: Not feasible for implementing various optional fields.

* **Alternative 2 (current choice):** Assign a default value for all non-compulsory fields in `AddBuyer` and `AddSeller`, 
only assigning them for arguments available from the parsed user input (in `ArgumentMultimap`).
    * Pros: Only a single constructor for `Buyer`/`Seller` is needed for multiple optional fields.
    * Cons: Address book only adds correctly formatted fields and may discard the rest without the user knowing, so
more robust exception handling is required in parsing the user input which may be tedious to implement.

### \[Proposed\] Sort feature

#### Proposed Implementation

The proposed sort mechanism is facilitated by the `sort` command.

Using the `sort` command, we can sort the buyers and sellers lists respectively by name, priority, and other criteria.

Given below is an example usage scenario and how the sort mechanism behaves at each step.

Step 1. The user types in the `sort-b` or `sort-s` keyword, followed by `name`, `priority`, or another `criteria`.
to sort by. 

The sort command will sort by changing the ObservableList<T> to a SortedList<T>, with the comparator based on the
certain criteria.

## Relaxed parameter matching
### Background
In previous versions of the app and in the original brownfield project AB3, fields such as ```Name``` or ```Email```
had a validation method on instantiation, which would throw an ```IllegalArgumentException``` when 
the provided string did not fit the regex. Although useful, this would often be overzealous, causing potential 
frustration. Furthermore, this exception, as it halts execution, only informs you of the first field that fails 
to pass, so if you had multiple errors you would have to resolve and re-execute each time.
### Implementation
In 1.3, we implemented a group of static methods for each parameter, generally named isAppropriate(*Field*), which has a
looser regex. The result of this boolean check, if it fails, then passes a warning string to the
```CommandWarnings``` class, which collects and stores them in a set. At the end of the execute() method, if the
command encountered any warnings, then they are output into the returned CommandResult.
This is then passed through LogicManager into MainWindow for display to the user.

<puml src="diagrams/isAppropriateNameSequenceDiagram.puml" alt="isAppropriateNameSequenceDiagram" />
<box type="info" seamless>

**Note:** The lifeline for `SetBuyerPriorityCommand` should end at the destroy marker (X), but due to a
limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

#### Design considerations:

**Aspect: How to implement the warnings**

* **Alternative 1 (current choice):** Use a CommandWarnings class to store strings representing warnings
for inappropriate but valid fields.
    * Pros: Allows us to hold multiple warnings at a time without halting execution.
    * Cons: Using strings means that poor usage of addWarnings by callers may give nonsensical results.

* **Alternative 2 (possible future enhancement):** Have CommandWarnings hold a set of predefined Warning singletons 
instead of Strings, and 
    * Pros: Constrains the contents of warning messages defensively, ensuring that they are useful.
    * Cons: Not as flexible, not worth the effort of implementing in this early stage of development
    , easy to add as future enhancement.

* **Alternative 3 (proposed):** Use an exception such as InappropriateFieldException, which would be thrown by ParserUtil and 
caught by the add buyer/seller command parsers, which would then pass a String warning to the command for it to output
as the CommandResult.
    * Pros: Doesn't require a new class to be created.
    * Cons: To properly account for every field, you would require a Try/Catch block for every single field parsing.
     Furthermore, using exceptions in the backend require switching to kernel mode, slowing down the application (which may be 
     significant for high-usage cases in the future such as company-wide integration.)
--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

This product is for student/junior realtors who have many clients and houses to keep track of. 
They are relatively tech-savvy and prefer the keyboard over the mouse, 
prefer concise commands as opposed to full sentences, 
and would like to customise the software to suit their preferences.


**Value proposition**:

Our free and open-source app helps realtors to keep track of their clients’ preferences and house viewings in one place. Unlike apps like Google Sheets, our app is more optimized for large databases. In addition, we help auto-match appropriate houses to a client with matching budget, needs and location.


### User stories

Priorities: High (must have) - * * *, Medium (nice to have) - * *, Low (unlikely to have) - *

Priority level is based on current iteration

| Priority | As a …​                                            | I want to …​                                                                | So that I can…​                                                                        |
|----------|----------------------------------------------------|-----------------------------------------------------------------------------|----------------------------------------------------------------------------------------|
| `* * *`  | realtor                                            | add home-buyer clients into the app                                         | keep track of them and their requirements                                              |
| `* * *`  | realtor                                            | add home owners and their houses into the app                               | keep track of them and relevant details (such as the price they are looking for, etc.) |
| `* * *`  | realtor                                            | view my contacts                                                            | easily find contacts I want to talk to                                                 |
| `* * *`  | user who has been using the app for a long time    | delete/archive old contacts                                                 | declutter my list from outdated information                                            |
| `* * *`  | realtor                                            | save contact data to my computer                                            | refer to it when I reopen my app                                                       |
| `* * *`  | realtor                                            | add houses into the app together with their price, furnishings, etc.        | quickly list the features to my clients                                                |
| `* * *`  | realtor who wants to pack light on the move        | solely use the keyboard and not need to carry a mouse around to use the app | quickly access and update information without the fuss of using a mouse                |
| `* *`    | realtor with many contacts                         | view personal contacts separately from work contacts                        | I can focus on work when I need to                                                     |
| `* *`    | realtor with many client contacts                  | sort my client contacts based on priority (time, importance, etc.)          | I can focus on the most important clients first                                        |
| `* *`    | realtor                                            | add prospective rental clients into the app                                 | keep track of them and their requirements                                              |
| `* *`    | realtor who spends a lot of time at house viewings | I want the app to start up and respond quickly                              | use the app to note down any of my client’s preferences while talking to them          |
| `* *`    | realtor who is flexible with scheduling            | reschedule or postpone my meetings easily in the app                        | so I can avoid the hassle of constantly deleting and making new meetings               |
| `* *`    | realtor                                            | add time to tasks related to each of my clients                             | remember to do them                                                                    |
| `* *`    | busy realtor with other activities in my life      | enter my schedule                                                           | account for overlaps with any meetings                                                 |
| `*`      | forgetful user                                     | be reminded if I have any upcoming or late meetings                         | follow up on my clients                                                                |
| `*`      | realtor                                            | track tasks related to each of my clients                                   | remember what I need to do to follow up on each of them                                |
| `*`      | realtor                                            | be reminded of upcoming tasks or late tasks                                 | do them before meeting clients                                                         |
| `*`      | power user                                         | modify the syntax of (at least some) commands                               | enter them faster                                                                      |
| `*`      | lazy user                                          | be able to automatically match appropriate houses to prospective buyers     | avoid doing it manually                                                                |

### Use cases

(For all use cases below, the **System** is our app `RTPM (RealtorTrackerPlusMax)` and the **Actor** is the `user`,
unless specified otherwise)

**Use case: UC1 - Add homeowner and house**
System: RTPM
Actor: User

**MSS**

1. User enters command to add homeowner and details of the house they are selling.
2. System adds the entry to the list.
3. System saves file.

   Use case ends.

Extensions:
* 1a. User enters invalid parameters.
  * 1a1. System indicates to user that the parameters are invalid.
    Use case restarts from step 1.

* 3a. Failure to update savefile.
  * 3a1. System indicates failure to update.
    Use case restarts from step 1.


**Use case: UC2 - Add homebuyer and preferences**
System: RTPM
Actor: User
**MSS**
1. User enters command to add homebuyer and preferences.
2. System adds the entry to the list.
3. System saves file.
   Use case ends.

Extensions:
* 1a. User enters invalid parameters.
  * 1a1. System indicates to user that the parameters are invalid.
  Use case restarts from step 1.

* 3a. Failure to update savefile.
  * 3a1. System indicates failure to update.
  Use case restarts from step 1.

**Use case: UC3 - View buyers**
System: RTPM
Actor: User
**MSS**
1. User enters the list-b command.
2. System displays list of buyers.

Use case ends.

Extensions:
* 1a. User makes a typo leading to an invalid command.
  * 1a1. System indicates to user that command is invalid, prompting the user for a new input.
  Use case restarts from step 1.

**Use case: UC4 - View sellers**

**MSS**

1. User enters the list-s command.
2. System displays list of sellers.

    Use case ends.

**Extensions**

* 1a. User makes a typo leading to an invalid command.
  * 1a1. System indicates to user that command is invalid, prompting the user for a new input.<br>
    Use case resumes at step 1.

<br>

**Use case: UC5 - Delete a buyer/seller**

**MSS**
1. User enters command to delete a buyer or a seller.
2. System deletes item.
3. System updates savefile.
4. System returns an indicator of execution success.

    Use case ends.

**Extensions**

* 3a. Failure to update savefile.
  * 3a1. System indicates failure to update.
  * 3a2. System undoes deletion (to prevent desync of storage and application).<br>
    Use case restarts from step 1.

<br>

**Use case: UC6 - Enter an invalid command**

**MSS:**
1. User enters misspelled command.
2. System displays invalid command error and refers user to help page.

*{More to be added}*




### Non-Functional Requirements
*NFRs taken from the given constraints found **[here](https://nus-cs2103-ay2324s1.github.io/website/schedule/week4/project.html)**:*

1. The product should be optimized for keyboard users who can type fast and prefer typing over other means of input.
2. The data should be stored locally in a human editable text file, instead of in a database.
3. The software should primarily follow OOP.
4. The software should work on the Windows, Linux, and OS-X platforms (hence shouldn’t depend on OS-specific libraries).
5. The software should work on a computer that has version 11 of Java i.e., no other Java version installed.
6. The software should work without requiring an installer.
7. The use of third-party frameworks/libraries/services is allowed but only if they are free, open-source (this doesn't apply to services), and have permissive license terms.
8. The GUI should work well (i.e., should not cause any resolution-related inconveniences to the user) for
* standard screen resolutions 1920x1080 and higher, and
* for screen scales 100% and 125%.

In addition, the GUI should be usable (i.e., all functions can be used even if the user experience is not optimal) for
* resolutions 1280x720 and higher, and
* for screen scales 150%.
9. The software should be able to be packaged into a single JAR file.
10. The DG and UG should be PDF-friendly (Don't use expandable panels, embedded videos, animated GIFs etc.).

Additional NFRs
11. The internal implementation should be readable and adhere to the coding quality guidelines found here, for maintainability and for peer evaluation.
12. The deliverable deadlines should be met with a fully functioning product (hence, most important features should be prioritized and tested to eliminate bugs) to allow for usage as promised.
13. The software should be resistant to crashes while running to prevent losing important contact details that realtors need to do business with.
14. The software should work fast even on old / low-end laptop so that realtors on the go with their busy days can use our app quickly and efficiently without getting frustrated with lag.
15. The software should be free and easy to use as an open source product.


### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Planned Enhancements**

Given below are the enhancements that will be implemented in a future version.

1. Currently, the UI text is cut off if the entries are too long. While this should not usually happen since the user 
can decide what to enter (nicknames, abbreviations, acronyms, etc.), we plan to accommodate overly long names, 
phone numbers, addresses, emails and house info entries within the UI.
2. Currently, if the user makes a spelling or spacing mistake, the intended prefix of another field is regarded as 
part of the argument for the previous field. We plan to check for misspelled prefixes and prefixes provided as 
arguments of other fields and warn the user.
3. Currently, the user is not warned if addresses, names, and house info entries contain only numbers and special 
symbols. We plan to expand warnings to include warnings for addresses, names and house info entries containing 
only non-alphabetical characters.
4. Currently, for `bprio` and `sprio`, if the user inputs extra arguments 
at the end, such as `bprio 1 high low`, the app accepts the input and sets the first buyer's priority level to 
`high` instead of warning the user about extra arguments which would be ignored. As such, we plan to warn the user 
if any extra arguments are supplied for the user to double check that their priority input is correct.
5. Currently, attempting to add multiple contacts with long names may cause the app to lag considerably. 
We plan to optimise the similarity checks for names so that doing so results in less delay.

