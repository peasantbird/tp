---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# RTPM User Guide

RealtorTrackerPlusMax (RTPM) is a desktop app for realtors who want to manage contacts, optimized for use via a 
Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI), so you can get your 
contact management tasks done faster than traditional GUI apps!

--------------------------------------------------------------------------------------------------------------------

## Contents

* [Quick Start](#quick-start)
* [Features](#features)<br>
    * Adding a client<br>
       * [Adding a buyer: `buyer`](#adding-a-buyer-buyer)<br>
       * [Adding a seller: `seller`](#adding-a-seller-seller)<br>
    * Editing a client<br>
      * [Editing a buyer: `bedit`](#editing-a-buyer-bedit)<br>
      * [Editing a seller: `sedit`](#editing-a-seller-sedit)<br>
    * Deleting a client<br>
      * [Deleting a buyer: `bdelete`](#deleting-a-buyer-bdelete)<br>
      * [Deleting a seller: `sdelete`](#deleting-a-seller-sdelete)<br>
      * [Clearing all entries: `clear`](#clearing-all-entries-clear)<br>
    * Setting a client's priority<br>
      * [Setting a buyer's priority: `bprio`](#setting-a-buyer-s-priority-bprio)<br>
      * [Setting a seller's priority: `sprio`](#setting-a-seller-s-priority-sprio)<br>
    * Viewing the clients<br>
      * [Listing all buyers and sellers: `list`](#listing-all-buyers-and-sellers-list)<br>
      * [Filtering buyers and sellers: `filter`](#filtering-buyers-and-sellers-filter)<br>
      * [Displaying a buyer from buyer list: `blist`](#displaying-a-buyer-from-buyer-list-blist)<br>
      * [Displaying a seller from seller list: `slist`](#displaying-a-seller-from-seller-list-slist)<br>
      * [Sorting buyers: `bsort`](#sorting-buyers-bsort)<br>
      * [Sorting sellers: `ssort`](#sorting-sellers-ssort)<br>
    * Miscellaneous commands<br>
      * [Undoing previous action: `undo`](#undoing-previous-action-undo)<br>
      * [Redoing previous action: `redo`](#redoing-previous-action-redo)<br>
      * [Viewing help: `help`](#viewing-help-help)<br>
      * [Exiting the program: `exit`](#exiting-the-program-exit)<br>
* [FAQ](#faq)
* [Known Issues](#known-issues)<br>
* [Command Summary](#command-summary)<br>
* Appendices
    * [Appendix A: Warnings](#appendix-a-warnings)<br>
    * [Appendix B: Fields](#appendix-b-fields)<br>
    * [Appendix C: Similar names](#appendix-c-similar-names)<br>


<div style="page-break-after: always;"></div>

## Quick Start

1. Ensure you have Java `11` or above installed in your computer.

2. Download the latest `rtpm.jar` [here](https://github.com/AY2324S1-CS2103T-F11-3/tp/releases/tag/v1.3(trial)).

3. Copy the file to the folder you want to use as the _home folder_ for RTPM.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar rtpm.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. If you are starting the app for the first time,
 there will be some sample data loaded.<br>
<div style="text-align:center;">
   <img src="images/Ui.png" alt="Ui" style="width:500px"/>
</div>


5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `buyer n/John Doe` : Adds a buyer named John Doe to the RTPM.

    * `list` : Lists all buyers and sellers.

    * `bdelete 2` : Deletes the 2nd buyer shown in the current list.

    * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

<div style="page-break-after: always;"></div>


<br>

## Features

Here are some things regarding RTPM's features to take note of before using them.

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `buyer n/NAME`, `NAME` is a parameter which can be used as `buyer n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* All commands and prefixes ignore case. Fields however, are case-sensitive. <br>
  e.g. if the command specifies `buyer n/NAME`, `BUYER N/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>


<div style="page-break-after: always;"></div>

<box type="info" seamless>

**General notes about features:**<br>

* RTPM does not require all fields to be filled in when creating buyers or sellers. Fields that have been omitted will be set to their default values.

* RTPM accepts unconventional entries for data values to an extent. However, the warning system informs the user of any valid but possibly unintended inputs. For more information, refer to [Appendix A: Warnings](#appendix-a-warnings)

* The priority system allows for the designation of levels of importance to each buyer and seller, which will be displayed as a tag in RTPM. When this priority is set to `nil`, there will be no tag.

* RTPM data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

* RTPM data are saved automatically as a JSON file `[JAR file location]/data/rtpm.json`.

* Note that RTPM checks for duplicate and similar entries. Refer to [Appendix C: Similar Names](#appendix-c-similar-names) for more info.

* **If you manually make changes to the saved data file such that the JSON format is invalid, RTPM will discard all data and start with an empty data file at the next run.
  Hence, manually modifying the saved data file is not recommended.**
</box>

--------------------------------------------------------------------------------------------------------------------


<div style="page-break-after: always;"></div>

<br>

### Adding a buyer: `buyer`

Adds a buyer with their information to the list. 

<box type="info" seamless>

Note that this command throws warnings. For more info on fields, head [here.](#appendix-b-fields) For more info on warnings, head [here.](#appendix-a-warnings)

</box>

Format: `buyer n/NAME [p/PHONE_NUMBER] [e/EMAIL] [ah/HOME_ADDRESS] [i/HOUSE_INFO] [prio/PRIORITY] [t/TAG]…`


Example:
`buyer n/Jane Doe p/91234567 e/janedoe@gmail.com ah/1 College Ave East i/Central Area 5 Room Condominium prio/high`

Precise outputs when the command succeeds:

>Got it. I've added a buyer contact:<br>
Jane Doe; Phone: 91234567; Email: janedoe@gmail.com; Address: 1 College Ave East; House Info: Central Area 5 Room Condominium; Priority: high; Tags:


Precise outputs when the command fails due to missing name parameter:

>Invalid command format!<br>
buyer: Adds a buyer to the address book. Parameters: n/NAME [p/PHONE] [e/EMAIL] [ah/ADDRESS] [i/INFO] [prio/PRIORITY] [t/TAG]...<br> 
Example: buyer n/John Doe p/98765432 e/johnd@example.com ah/311, Clementi Ave 2, #02-25 i/Central Area 5 Room Condominium prio/medium t/friends t/owesMoney


Precise outputs when the command fails due to invalid parameters:

>Emails must contain at least one '@'.

<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

<div style="page-break-after: always;"></div>


<br>

### Adding a seller : `seller`

Adds a seller with their information to the list. 

<box type="info" seamless>

Note that this command throws warnings. For more info on fields, head [here.](#appendix-b-fields) For more info on warnings, head [here.](#appendix-a-warnings)

</box>

Format: `seller n/NAME [p/PHONE_NUMBER] [e/EMAIL] [ah/HOME_ADDRESS] [as/SELLING_ADDRESS] [i/HOUSE_INFO] [prio/PRIORITY] [t/TAG]…`


Example: `seller n/Ryan p/91234567 e/ryan@gmail.com ah/My Secret Home as/47D Lor Sarhad, Singapore 119164 i/4 Room Flat in Sarhad Ville prio/high`

Precise outputs when the command succeeds:

>Got it. I've added a seller contact:<br>
Ryan; Phone: 91234567; Email: ryan@gmail.com; Address: My Secret Home; Selling Address: 47D Lor Sarhad, Singapore 119164; House Info: 4 Room Flat in Sarhad Ville; Priority: high; Tags:


Precise outputs when the command fails due to missing parameters:

>Invalid command format!<br>
seller: Adds a seller to the address book. Parameters: n/NAME p/PHONE e/EMAIL ah/ADDRESS as/SELLING_ADDRESS i/HOUSE_INFO [t/TAG]...<br>
Example: seller n/Ryan p/91234567 e/ryan@gmail.com ah/My Secret Home as/47D Lor Sarhad, Singapore 119164 i/4 Room Flat in Sarhad Ville prio/medium t/friends t/owesMoney


Precise outputs when the command fails due to invalid parameters:

>Emails must contain at least one '@'.


<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

<div style="page-break-after: always;"></div>


<br>

### Editing a buyer: `bedit`

Edits the information of a buyer based on their index number in the buyers' list.

<box type="info" seamless>

Note that this command throws warnings. For more info on fields, head [here.](#appendix-b-fields) For more info on warnings, head [here.](#appendix-a-warnings)

</box>

Format: `bedit INDEX PREFIX/VALUE [MORE_PREFIX/VALUE]…`
* `INDEX`: A positive integer (1, 2, 3, …) which must not exceed the last index in the buyers' list
* `PREFIX/VALUE`: Refer to the appendix linked above.

Example: `bedit 3 e/example@email.com ah/Residential Street`

Precise outputs when the command succeeds:

>Got it. I've edited a buyer contact:<br>
Jane Doe; Phone: 91234567; Email: something@else.com; Address: 1 College Ave East; House Info: Central Area 5 Room Condominium; Priority: nil; Tags:

Precise outputs when the command fails:

>Invalid command format!<br> 
bedit: Edits the details of the buyer identified by the index number used in the displayed buyer list. Existing values will be overwritten by the input values.<br>
Parameters: INDEX (must be a positive integer) [n/NAME] [p/PHONE] [e/EMAIL] [ah/ADDRESS] [i/HOUSE_INFO] [t/TAG]...<br>
[prio/PRIORITY] Example: bedit 1 p/91234567 e/johndoe@example.com

>At least one field to edit must be provided!

>The buyer index provided is higher than the last number in the list!

<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

<div style="page-break-after: always;"></div>


<br>

### Editing a seller: `sedit`

Edits the information of a seller based on their index number in the sellers' list.

<box type="info" seamless>

Note that this command throws warnings. For more info on fields, head [here.](#appendix-b-fields) For more info on warnings, head [here.](#appendix-a-warnings)

</box>

Format: `sedit INDEX PREFIX/VALUE [MORE_PREFIX/VALUE]…`
* `INDEX`: A positive integer (1, 2, 3, …) which must not exceed the last index in the sellers' list
* `PREFIX/VALUE`: Refer to the appendix linked above.

Example: `sedit 3 e/example@email.com ah/Residential Street`

Precise outputs when the command succeeds:

>Got it. I've edited a seller contact:<br>
Ryan; Phone: 91234567; Email: ryan@gmail.com; Address: Another Place; Selling Address: 47D Lor Sarhad, Singapore 119164; House Info: 4 Room Flat in Sarhad Ville; Priority: nil; Tags:

Precise outputs when the command fails:

>Invalid command format!<br>
sedit: Edits the details of the seller identified by the index number used in the displayed seller list. Existing values will be overwritten by the input values.<br>
Parameters: INDEX (must be a positive integer) [n/NAME] [p/PHONE] [e/EMAIL] [ah/ADDRESS] [as/SELLING_ADDRESS] [i/HOUSE_INFO] [t/TAG]...<br>
[prio/PRIORITY] Example: sedit 1 p/91234567 e/johndoe@example.com

>At least one field to edit must be provided!

>The seller index provided is higher than the last number in the list!


<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

<div style="page-break-after: always;"></div>



<br>

### Deleting a buyer: `bdelete`

Deletes a buyer based on their index number in the buyers’ list.

Format: `bdelete INDEX`
* `INDEX`: A positive integer (1, 2, 3, …) which must not exceed the last index in the buyers' list

Example: `bdelete 3`

Precise outputs when the command succeeds:

>Got it. I’ve deleted a buyer contact:<br>
Jane Doe; Phone: 91234567; Email: janedoe@gmail.com; Address: 1 College Ave East; House Info: Central Area 5 Room Condominium; Priority: nil; Tags:

Precise outputs when the command fails:

>Invalid command format!<br>
bdelete: Deletes the buyer identified by the index number used in the displayed buyer list.<br>
Parameters: INDEX (must be a positive integer)<br>
Example: bdelete 1

>The buyer index provided is higher than the last number in the list!


<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

<div style="page-break-after: always;"></div>


<br>

### Deleting a seller: `sdelete`

Deletes a seller based on their index number in the sellers’ list.

Format: `sdelete INDEX`
* `INDEX`: A positive integer (1, 2, 3, …), which must not exceed last index in the sellers’ list

Example: `sdelete 3`

Precise outputs when the command succeeds:

>Got it. I’ve deleted a seller contact:<br>
Ryan; Phone: 91234567; Email: ryan@gmail.com; Address: My Secret Home; Selling Address: 47D Lor Sarhad, Singapore 119164; House Info: 4 Room Flat in Sarhad Ville; Priority: nil; Tags:

Precise outputs when the command fails:

>Invalid command format! <br>
sdelete: Deletes the seller identified by the index number used in the displayed seller list.<br>
Parameters: INDEX (must be a positive integer)<br>
Example: sdelete 1


>The seller index provided is higher than the last number in the list!

--------------------------------------------------------------------------------------------------------------------

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

Precise outputs when the command succeeds:

>Address book has been cleared!

<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

<div style="page-break-after: always;"></div>



<br>

### Setting a buyer's priority: `bprio`

Sets the priority level of a buyer based on their index number in the buyer's list.

<box type="info" seamless>

Note that this command throws warnings. For more info on warnings, head [here.](#appendix-a-warnings)

</box>

Format: `bprio INDEX PRIORITY`
* `INDEX`: A positive integer (1, 2, 3, …) which must not exceed the last index in the buyer's list
* `PRIORITY`: See [here.](#appendix-b-fields)

Example: `bprio 3 high`

<box type="info">

**Note:**
* `PRIORITY` only considers the first letter of the input (`h` for high, `m` for medium, `l` for low, or `n` for nil). For example, `bprio 3 hlow` sets the priority of buyer 3 to high.
* Any extra inputs given after `PRIORITY` will be ignored. For example, `bprio 3 high low` is equivalent to `bprio 3 high`.

</box>

Precise outputs when the command succeeds:

>The buyer's priority level has been set:<br>
Jane Doe; Phone: 91234567; Email: something@else.com; Address: 1 College Ave East; House Info: Central Area 5 Room Condominium; Priority: high; Tags:

Precise outputs when the command fails:

>Invalid command format!<br>
bprio: Sets a priority level for the buyer, identified by index in the displayed buyer list. INDEX must be a positive integer, while PRIORITY can be either 'high', 'medium', 'low', or 'nil'.<br>
Parameters: INDEX PRIORITY


>The buyer index provided is higher than the last number in the list!


<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

<div style="page-break-after: always;"></div>



<br>

### Setting a seller's priority: `sprio`

<box type="info" seamless>

Note that this command throws warnings. For more info on warnings, head [here.](#appendix-a-warnings)

</box>

Sets the priority level of a seller based on their index number in the seller's list.

Format: `sprio INDEX PRIORITY`
* `INDEX`: A positive integer (1, 2, 3, …) which must not exceed the last index in the buyer's list
* `PRIORITY`: See [here.](#appendix-b-fields)

Example: `sprio 3 high`

<box type="info">

**Note:**
* `PRIORITY` only considers the first letter of the input (`h` for high, `m` for medium, `l` for low, or `n` for nil). For example, `bprio 3 hlow` sets the priority of buyer 3 to high.
* Any extra inputs given after `PRIORITY` will be ignored. For example, `bprio 3 high low` is equivalent to `bprio 3 high`.

</box>

Precise outputs when the command succeeds:

>The seller's priority level has been set:<br>
Ryan; Phone: 91234567; Email: ryan@gmail.com; Address: Another Place; Selling Address: 47D Lor Sarhad, Singapore 119164; House Info: 4 Room Flat in Sarhad Ville; Priority: high; Tags:

Precise outputs when the command fails:

>Invalid command format!<br>
sprio: Sets a priority level for the seller, identified by index in the displayed seller list. INDEX must be a positive integer, while PRIORITY can be either 'high', 'medium', 'low', or 'nil'.<br>
Parameters: INDEX PRIORITY


>The seller index provided is higher than the last number in the list!

<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

<div style="page-break-after: always;"></div>



<br>

### Listing all buyers and sellers: `list`

Lists all buyers and sellers that the user has added. Clears filters and reloads the data file to check for changes.

Format: `list`

Precise outputs when the command succeeds:

>Listed all buyers and sellers!

--------------------------------------------------------------------------------------------------------------------

### Filtering buyers and sellers: `filter`

Filters both lists so that they only display buyers and sellers whose names match any of the given keywords fully.

Format: `filter KEYWORD [MORE_KEYWORDS]…`

- `KEYWORD`: No restrictions; case-insensitive

Example: `filter John Doe`

**Tip: `filter John Doe` will filter for**
- [x] John
- [x] John Do
- [x] Jane Doe
- [x] John Doe
- [x] john doe
- [x] JOHN DOE
- [ ] Johnny
- [ ] Jo


Precise outputs when the command succeeds:

>1 buyer(s) and 0 seller(s) listed!

<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

<div style="page-break-after: always;"></div>


<br>

### Displaying a buyer from buyer list: `blist`

Displays the information of a buyer based on their index number in the buyers' list.

Format: `blist INDEX`
* `INDEX`: A positive integer (1, 2, 3, …) which must not exceed the last index in the buyers' list

Example: `blist 3`

Precise outputs when the command succeeds:

>Got it. Here's the information of this buyer:<br>
Jane Doe; Phone: 91234567; Email: janedoe@gmail.com; Address: 1 College Ave East; House Info: Central Area 5 Room Condominium; Priority: nil; Tags:

<br>

--------------------------------------------------------------------------------------------------------------------

### Displaying a seller from seller list: `slist`

Displays the information of a seller based on their index number in the sellers' list.

Format: `slist INDEX`
* `INDEX`: A positive integer (1, 2, 3, …) which must not exceed the last index in the sellers' list

Example: `slist 3`

Precise outputs when the command succeeds:

>Got it. Here's the information of this seller:<br>
Ryan; Phone: 91234567; Email: ryan@gmail.com; Address: My Secret Home; Selling Address: 47D Lor Sarhad, Singapore 119164; House Info: 4 Room Flat in Sarhad Ville; Priority: nil; Tags:

<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

<div style="page-break-after: always;"></div>



### Sorting buyers: `bsort`

Sorts the buyers' list either by a provided prefix and direction, or by the list's default order when no prefix and
direction is provided.

Format: `bsort [PREFIX/DIRECTION]`
* `PREFIX`: Choose <u>one</u> of:
  * `n` - sort by **name**
  * `ah` - sort by **home address**
  * `i` - sort by **house info**
  * `prio` - sort by **priority**
* `DIRECTION`: "a" OR "d" for ascending or descending respectively

Examples: 
* `bsort` to sort by default
* `bsort n/d` to sort by name descending

Precise outputs when the command succeeds:

>Got it. I've sorted the buyer list!


Precise outputs when the command fails:
1. For general errors:
>Invalid command format!<br>
bsort: Sorts the buyers in RTPM. Parameters: Choose zero or one of [n/] [ah/] [i/] [prio/] a/d (for ASC/DESC)<br>
Example: bsort prio/d
2. When the same prefix is used more than once:
>Multiple values specified for the following single-valued field(s): ...

<box type="info">

**Note:**

* The sort command will ignore any extraneous inputs and invalid prefixes after the `bsort` keyword and before the next 
valid prefix (`n`, `ah`, `i` or `prio`).
  * For example, `bsort qwerty z/asdf prio/d` will execute `bsort prio/d`.
  * Any extraneous inputs or invalid prefixes after a valid prefix will cause an error.
* When two or more valid prefixes are provided, `bsort` will sort by only one of the provided prefixes, which is chosen
  based on this order:<br> **1. Name**, **2. Home address**, **3. House info**, **4. Priority**.
  * For example, `bsort prio/d n/d` will execute `bsort n/d`.

</box>

<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

<div style="page-break-after: always;"></div>



### Sorting sellers: `ssort`

Sorts the sellers' list either by a provided prefix and direction, or by the list's default order when no prefix and 
direction is provided.

Format: `ssort [PREFIX/DIRECTION]`
* `PREFIX`: Choose <u>one</u> of:
    * `n` - sort by **name**
    * `ah` - sort by **home address**
    * `i` - sort by **house info**
    * `prio` - sort by **priority**
* `DIRECTION`: "a" OR "d" for ascending or descending respectively

Examples:
* `ssort` to sort by default
* `ssort n/d` to sort by name descending

Precise outputs when the command succeeds:

>Got it. I've sorted the seller list!


Precise outputs when the command fails:
1. For general errors:
>Invalid command format!<br>
ssort: Sorts the sellers in RTPM. Parameters: Choose zero or one of [n/] [ah/] [i/] [prio/] a/d (for ASC/DESC)<br>
Example: ssort prio/d
2. When the same prefix is used more than once:
>Multiple values specified for the following single-valued field(s): ...

<box type="info">

**Note:**

* Similar to `bsort`, the `ssort` command will also ignore any extraneous inputs and invalid prefixes after the `ssort` keyword and before the next
  valid prefix (`n`, `ah`, `i` or `prio`).
* Similar to `bsort`, when two or more valid prefixes are provided, `ssort` will sort by only one of the provided prefixes, which is chosen
  based on this order: <br> **1. Name**, **2. Home address**, **3. House info**, **4. Priority**.

</box>


<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

<div style="page-break-after: always;"></div>


<br>

### Undoing previous action: `undo`

Undoes the previous action. Only undoes actions that changes the contacts in RTPM, and does not undo actions that 
changes the list view (i.e. `list`, `filter`, `sort`).

Format: `undo`

<box type="tip">

**Tip:** 

Using `undo` multiple times will undo multiple actions in order.


</box>

Precise outputs when the command succeeds:

>Last command was undone.

Precise outputs when the command fails:

>No commands to undo!

--------------------------------------------------------------------------------------------------------------------

### Redoing previous action: `redo`

Restores the previously undone action. Only redoes actions that changes the contacts in RTPM, and does not redo actions
that changes the list view (i.e. `list`, `filter`, `sort`).

Format : `redo`

<box type="tip">

**Tip:**

Using `redo` multiple times will redo multiple actions in order.


</box>

Precise outputs when the command succeeds:

>The next command was redone.

Precise outputs when the command fails:

>No commands to redo!


<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

<div style="page-break-after: always;"></div>


<br>

### Viewing help: `help`

Shows a message explaining how to access the help page.

Format: `help`

--------------------------------------------------------------------------------------------------------------------

### Exiting the program: `exit`

Exits the program.

Format: `exit`

<box type="info">

Upon exit, the latest data is saved to your computer at data/addressbook.json.

</box>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous RTPM home folder.

**Q**: My entries are missing! Did they get deleted somehow?<br>
**A**: One possible fix is to try calling the `list` command. If you manually edit the data file, or you filtered the list,
the entries may not appear in the application until you refresh the list.

**Q**: I made a mistake! How do I fix it?<br>
**A**: You can make use of our handy [undo](#undoing-previous-action-undo) and [redo](#redoing-previous-action-redo)
commands to fix any errors made.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. Adding multiple contacts with excessively long names (>5000 characters) may cause RTPM to lag significantly. It is recommended to use nicknames or initials if necessary.
3. Long fields can cause the details of a client to not be displayed fully. A workaround is to use the 
```slist```/```blist``` commands to display the details of the client in the result box.


<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

<div style="page-break-after: always;"></div>



## Command summary

| Action                  | Format, Examples                                                                                                                                                                                                                                                                   |
|-------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add Buyer**           | `buyer n/NAME [p/PHONE_NUMBER] [e/EMAIL] [ah/HOME_ADDRESS] [i/BUY_HOUSE_INFO] [prio/PRIORITY] [t/TAG]`<br> e.g. buyer n/Jane Doe p/91234567 e/janedoe@gmail.com ah/1 College Ave East i/Central Area 5 Room Condominium prio/high                                                  |
| **Add Seller**          | `seller n/NAME [p/PHONE_NUMBER] [e/EMAIL] [ah/HOME_ADDRESS] [as/SELLING_ADDRESS] [i/SELLING_HOUSE_INFO] [prio/PRIORITY] [t/TAG]​`<br> e.g. seller n/Ryan p/91234567 e/ryan@gmail.com ah/My Secret Home as/47D Lor Sarhad, Singapore 119164 i/4 Room Flat in Sarhad Ville prio/high |
| **Edit Buyer**          | `bedit INDEX PREFIX/VALUE [MORE_PREFIX/VALUE]`<br> e.g. bedit 3 e/example@email.com ah/Residential Street                                                                                                                                                                          |
| **Edit Seller**         | `sedit INDEX PREFIX/VALUE [MORE_PREFIX/VALUE]`<br> e.g. sedit 3 e/example@email.com as/Selling Street                                                                                                                                                                              |
| **Delete Buyer**        | `bdelete INDEX`<br> e.g. bdelete 2                                                                                                                                                                                                                                                 |
| **Delete Seller**       | `sdelete INDEX`<br> e.g. sdelete 2                                                                                                                                                                                                                                                 |
| **Clear**               | `clear`                                                                                                                                                                                                                                                                            |
| **Set Buyer Priority**  | `bprio INDEX PRIORITY`<br> e.g. bprio 3 high                                                                                                                                                                                                                                       |
| **Set Seller Priority** | `sprio INDEX PRIORITY`<br> e.g. sprio 3 high                                                                                                                                                                                                                                       |
| **List All**            | `list`                                                                                                                                                                                                                                                                             |
| **Filter**              | `filter KEYWORD [MORE_KEYWORDS]`<br> e.g. filter John Doe                                                                                                                                                                                                                          |
| **List Buyer**          | `blist INDEX` <br> e.g. blist 1                                                                                                                                                                                                                                                    |
| **List Seller**         | `slist INDEX`<br> e.g. slist 1                                                                                                                                                                                                                                                     |
| **Sort Buyers**         | `bsort [PREFIX/DIRECTION]`<br> e.g. bsort n/d                                                                                                                                                                                                                                      |
| **Sort Sellers**        | `ssort [PREFIX/DIRECTION]`<br> e.g. ssort prio/a                                                                                                                                                                                                                                   |
| **Undo**                | `undo`                                                                                                                                                                                                                                                                             |
| **Redo**                | `redo`                                                                                                                                                                                                                                                                             |
| **Exit**                | `exit`                                                                                                                                                                                                                                                                             |
| **Help**                | `help`                                                                                                                                                                                                                                                                             |

<br>


<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

<div style="page-break-after: always;"></div>



## Appendix A: Warnings

RTPM allows you to flexibly input most fields of data, for example, you can insert chinese characters as names, or
use slashes to abbreviate "son of" as "s/o".
However, we still have some things in mind for each field, to reduce faulty data.

Hence, the warning system allows our app to inform you of any valid but possibly unintended inputs.
The warning system is able to alert the user of multiple errors at once. For a non-exhaustive list, see below.

The warning system is also used to inform the user if, when you are adding new buyers/sellers, 
whether there are two similar buyers or two similar sellers, 
or a buyer that shares the same name as a seller. See [Appendix C](#appendix-c-similar-names) for more details.

```
Warning!; [Phone numbers should only contain numbers, and it should be at 
least 3 digits long. Area codes are allowed, signified by a '+' and up to 
3 numbers, followed by a space separating this from the main number.]
Please ignore if this is expected.
```
```
Warning!; [Emails should be of the format local-part@domain and adhere to 
the following constraints:
1. The local-part should only contain alphanumeric characters and these 
special characters, excluding the parentheses, (+_.-). The local-part may 
not start or end with any special characters.
2. This is followed by a '@' and then a domain name. The domain name is 
made up of domain labels separated by periods.
The domain name must:
    - end with a domain label at least 2 characters long
    - have each domain label start and end with alphanumeric characters
    - have each domain label consist of alphanumeric characters, separated 
      only by hyphens, if any.]
Please ignore if this is expected.
```


<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

<div style="page-break-after: always;"></div>


## Appendix B: Fields
Here, we provide the exact checks that RTPM does for each field, and the warning given if the field is inappropriate.

| Field            | Valid                                    | Appropriate                                                                                       | Exact warning given                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
|------------------|------------------------------------------|---------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Name**         | Must not be blank                        | Alphanumeric characters only                                                                      |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
| **Phone Number** | At least one numerical character         | Only numerical characters, and at least 3 digits long. Area codes allowed as provided in warning. | `Phone numbers should only contain numbers, and it should be at least 3 digits long. Area codes are allowed, signified by a '+' and up to 3 numbers, followed by a space separating this from the main number.`                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
| **Email**        | At least one `@` character               | See warning message.                                                                              | `Emails should be of the format local-part@domain and adhere to the following constraints: 1. The local-part should only contain alphanumeric characters and these special characters, excluding the parentheses, (+_.-). The local-part may not start or end with any special characters. 2. This is followed by a '@' and then a domain name. The domain name is made up of domain labels separated by periods. The domain name must: - end with a domain label at least 2 characters long - have each domain label start and end with alphanumeric characters - have each domain label consist of alphanumeric characters, separated only by hyphens, if any.` |
| **Address**      | Must not be blank                        | Nil                                                                                               | Nil                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| **Info**         | Must not be blank                        | Nil                                                                                               | Nil                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |

<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>
<div style="page-break-after: always;"></div>

| Field            | Valid                                    | Appropriate                                                                                       | Exact warning given                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
|------------------|------------------------------------------|---------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Priority**     | Starts with one of the letters `h,l,m,n` | Matches the first part of one of the words `high, low, medium, nil`.                              | `Inputs should be 'high', 'medium', 'low' or 'nil'. However, if at least the first letter is valid, we will read correctly.`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| **Tags**         | Must not be blank                        | Alphanumeric characters only                                                                      | `Tags names should be alphanumeric`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |


--------------------------------------------------------------------------------------------------------------------

## Appendix C: Similar names
RTPM does checks to ensure users do not accidentally enter the same person twice, preventing cases where you have duplicate entries.
This is also true if you try to edit two people to have the same name.

There may be some ambiguity when names are similar but not exactly the same; in this case,
the app will warn the user in case this was unintentional,
but will not prevent the command from executing. 
This appendix aims to explain how exactly similar names are determined.

RTPM's definition of 'similar' is as follows: Either one of the names is contained 
in the other, or the names require 2 or fewer edits (deletions, insertions, transpositions)
to make them the same. (for more details search for the definition of Levenshtein distance).

An example when the same name is detected across buyers and sellers: 
```This seller potentially also exists in the buyer list: If so, please verify that their contact information is the same```

An example when similar names are detected:
```The seller is similar to one of the sellers in the list!```

An example when the same name is detected:
```This seller already exists in the address book```
(This is not allowed, hence the command will not execute.)

<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

