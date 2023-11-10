---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# RTPM User Guide

RealtorTrackerPlusMax (RTPM) is a desktop app for realtors who want to manage contacts, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, RTPM can get your contact management tasks done faster than traditional GUI apps.

* [Quick Start](#quick-start)
* [Features](#features)<br>
    * Adding a person<br>
       * [Adding a buyer: `buyer`](#adding-a-buyer-buyer)<br>
       * [Adding a seller: `seller`](#adding-a-seller--seller)<br>
    * Editing a person<br>
      * [Editing a buyer: `bedit`](#editing-a-buyer-bedit)<br>
      * [Editing a seller: `sedit`](#editing-a-seller-sedit)<br>
    * Deleting a person<br>
      * [Deleting a buyer: `bdelete`](#deleting-a-buyer-bdelete)<br>
      * [Deleting a seller: `sdelete`](#deleting-a-seller-sdelete)<br>
      * [Clearing all entries: `clear`](#clearing-all-entries--clear)<br>
    * Setting a person's priority<br>
      * [Setting a buyer's priority: `bprio`](#setting-a-buyers-priority)<br>
      * [Setting a seller's priority: `sprio`](#setting-a-sellers-priority)<br>
    * Viewing the lists<br>
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
* [Known Issues](#known-issues)
* [Command Summary](#command-summary)
* Appendices
    * [Appendix A: Warnings](#appendix-a-warnings)
--------------------------------------------------------------------------------------------------------------------

## Quick Start

1. Ensure you have Java `11` or above installed in your computer.

1. Download the latest `rtpm.jar` [here](https://github.com/AY2324S1-CS2103T-F11-3/tp/releases/tag/v1.3(trial)).

1. Copy the file to the folder you want to use as the _home folder_ for RTPM.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar rtpm.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `buyer n/John Doe p/91234567 e/johndoe@gmail.com ah/1 College Ave East i/Central Area 5 Room Condominium` : Adds a buyer named John Doe to the RTPM.

    * `list` : Lists all buyers and sellers.

    * `bdelete 2` : Deletes the 2nd buyer shown in the current list.

    * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

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

* Commands and prefixes ignore case.<br>
  e.g. if the command specifies `buyer n/NAME`, `BUYER N/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.


**General notes about features:**<br>

* RTPM does not require all fields to be filled in when creating buyers or sellers. Fields that have been omitted will be set to their default values.
* RTPM accepts unconventional entries for data values to an extent. However, the warning system informs the user of any valid but possibly unintended inputs. For more information, refer to [Appendix A: Warnings](#appendix-a-warnings)
* The priority system allows for the designation of levels of importance to each buyer and seller, which will be displayed as a tag in RTPM. When this priority is set to `nil`, there will be no tag.
* RTPM data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
* RTPM data are saved automatically as a JSON file `[JAR file location]/data/rtpm.json`. Advanced users are welcome to update data directly by editing that data file.

**If your changes to the data file are in an invalid format, RTPM will discard all data and start with an empty data file at the next run.
Hence, it is recommended to take a backup of the file before editing it.**
</box>


--------------------------------------------------------------------------------------------------------------------

### Adding a buyer: `buyer`

Adds a buyer with their information to the list. 

Format: `buyer n/NAME [p/PHONE_NUMBER] [e/EMAIL] [ah/HOME_ADDRESS] [i/BUY_HOUSE_INFO] [prio/PRIORITY] [t/TAG]…`

- `n/NAME`: Contains at least one letter, number or symbol.
- `[p/PHONE_NUMBER]`: Contains at least one number
- `[e/EMAIL]`: Contains at least one '@'
- `[ah/HOME_ADDRESS]`: No restrictions
- `[i/BUY_HOUSE_INFO]`: No restrictions
- `[prio/PRIORITY]`: Either `high`, `medium`, `low`, or `nil` priority level
- `[t/TAG]...`: No restrictions

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

--------------------------------------------------------------------------------------------------------------------

### Adding a seller : `seller`

Adds a seller with their information to the list. 

Format: `seller n/NAME [p/PHONE_NUMBER] [e/EMAIL] [ah/HOME_ADDRESS] [as/SELLING_ADDRESS] [i/SELLING_HOUSE_INFO] [prio/PRIORITY] [t/TAG]…`
- `n/NAME`: Contains at least one letter, number, or symbol.
- `[p/PHONE_NUMBER]`: Contains at least one number
- `[e/EMAIL]`: Contains at least one '@'
- `[ah/HOME_ADDRESS]`: No restrictions
- `[as/SELLING_ADDRESS]`: No restrictions
- `[i/SELLING_HOUSE_INFO]`: No restrictions
- `[prio/PRIORITY]`: Either `high`, `medium`, `low`, or `nil` priority level
- `[t/TAG]`: No restrictions

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

--------------------------------------------------------------------------------------------------------------------

### Editing a buyer: `bedit`

Edits the information of a buyer based on their index number in the buyers' list. 

Format: `bedit INDEX PREFIX/VALUE [MORE_PREFIX/VALUE]…`
* `INDEX`: A positive integer (1, 2, 3, …) which must not exceed the last index in the buyers' list
* `PREFIX/VALUE`: Refer to the add buyer command, `buyer`

Example: `bedit 3 e/example@email.com ah/Residential Street`

Precise outputs when the command succeeds:

>Got it. I've edited a buyer contact:<br>
Jane Doe; Phone: 91234567; Email: something@else.com; Address: 1 College Ave East; House Info: Central Area 5 Room Condominium; Priority: nil; Tags:

Precise outputs when the command fails:

>Invalid command format!<br> 
bedit: Edits the details of the buyer identified by the index number used in the displayed buyer list. Existing values will be overwritten by the input values.<br>
Parameters: INDEX (must be a positive integer) [n/NAME] [p/PHONE] [e/EMAIL] [ah/ADDRESS] [i/BUY_HOUSE_INFO] [t/TAG]...<br>
[prio/PRIORITY] Example: bedit 1 p/91234567 e/johndoe@example.com

>At least one field to edit must be provided!

>The buyer index provided is higher than the last number in the list!

<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

--------------------------------------------------------------------------------------------------------------------

### Editing a seller: `sedit`

Edits the information of a seller based on their index number in the sellers' list. 

Format: `sedit INDEX PREFIX/VALUE [MORE_PREFIX/VALUE]…`
* `INDEX`: A positive integer (1, 2, 3, …) which must not exceed the last index in the sellers' list
* `PREFIX/VALUE`: Refer to the add seller command, `seller`

Example: `sedit 3 e/example@email.com ah/Residential Street`

Precise outputs when the command succeeds:

>Got it. I've edited a seller contact:<br>
Ryan; Phone: 91234567; Email: ryan@gmail.com; Address: Another Place; Selling Address: 47D Lor Sarhad, Singapore 119164; House Info: 4 Room Flat in Sarhad Ville; Priority: nil; Tags:

Precise outputs when the command fails:

>Invalid command format!<br>
sedit: Edits the details of the seller identified by the index number used in the displayed seller list. Existing values will be overwritten by the input values.<br>
Parameters: INDEX (must be a positive integer) [n/NAME] [p/PHONE] [e/EMAIL] [ah/ADDRESS] [as/SELLING_ADDRESS] [i/SELL_HOUSE_INFO] [t/TAG]...<br>
[prio/PRIORITY] Example: sedit 1 p/91234567 e/johndoe@example.com

>At least one field to edit must be provided!

>The seller index provided is higher than the last number in the list!

<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

--------------------------------------------------------------------------------------------------------------------

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

--------------------------------------------------------------------------------------------------------------------

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

<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

--------------------------------------------------------------------------------------------------------------------

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

Precise outputs when the command succeeds:

>Address book has been cleared!

<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

--------------------------------------------------------------------------------------------------------------------

### Setting a buyer's priority: `bprio`

Sets the priority level of a buyer based on their index number in the buyer's list. 

Format: `bprio INDEX PRIORITY`
* `INDEX`: A positive integer (1, 2, 3, …) which must not exceed the last index in the buyer's list
* `PRIORITY`: Either `high`, `medium`, `low`, or `nil` priority level

Example: `bprio 3 high`

**Note: `PRIORITY` will only consider the first letter of the input being `h`, `m`, `l`, or `n` to determine the corresponding priority level. `bprio 3 hlow` sets the priority of buyer 3 to high.**

Precise outputs when the command succeeds:

>The buyer's priority level has been set:<br>
Jane Doe; Phone: 91234567; Email: something@else.com; Address: 1 College Ave East; House Info: Central Area 5 Room Condominium; Priority: high; Tags:

Precise outputs when the command fails:

>Invalid command format!<br>
bprio: Sets a priority level for the buyer, identified by index in the displayed buyer list. INDEX must be a positive integer, while PRIORITY can be either 'high', 'medium', or 'low'.<br>
Parameters: INDEX PRIORITY


>The buyer index provided is higher than the last number in the list!

<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

--------------------------------------------------------------------------------------------------------------------

### Setting a seller's priority: `sprio`

Sets the priority level of a seller based on their index number in the seller's list.

Format: `sprio INDEX PRIORITY`
* `INDEX`: A positive integer (1, 2, 3, …) which must not exceed the last index in the buyer's list
* `PRIORITY`: Either `high`, `medium`, `low`, or `nil` priority level

Example: `sprio 3 high`

**Note: `PRIORITY` will only consider the first letter of the input being `h`, `m`, `l`, or `n` to determine the corresponding priority level. `sprio 3 hlow` sets the priority of seller 3 to high.**

Precise outputs when the command succeeds:

>The seller's priority level has been set:<br>
Ryan; Phone: 91234567; Email: ryan@gmail.com; Address: Another Place; Selling Address: 47D Lor Sarhad, Singapore 119164; House Info: 4 Room Flat in Sarhad Ville; Priority: high; Tags:

Precise outputs when the command fails:

>Invalid command format!<br>
sprio: Sets a priority level for the seller, identified by index in the displayed seller list. INDEX must be a positive integer, while PRIORITY can be either 'high', 'medium', or 'low'.<br>
Parameters: INDEX PRIORITY


>The seller index provided is higher than the last number in the list!

<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

--------------------------------------------------------------------------------------------------------------------

### Listing all buyers and sellers: `list`

Lists all buyers and sellers that the user has added. 

Format: `list`

Precise outputs when the command succeeds:

>Listed all buyers and sellers!

<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

--------------------------------------------------------------------------------------------------------------------

### Filtering buyers and sellers: `filter`

Filters both lists so that they only display buyers and sellers any **complete word** of whose names match any of the given keywords fully.

Format: `filter KEYWORD [MORE_KEYWORDS]…`

- `KEYWORD`: No restrictions; case-insensitive

Example: `filter John Doe`

**Tip: `filter John Doe` will filter for**
- [x] John
- [x] John Do
- [x] Jane Doe
- [x] John Doe
- [ ] Johnny
- [ ] Jo


Precise outputs when the command succeeds:

>1 buyer(s) and 0 seller(s) listed!

<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

--------------------------------------------------------------------------------------------------------------------

### Displaying a buyer from buyer list: `blist`

Displays the information of a buyer based on their index number in the buyers' list.

Format: `blist INDEX`
* `INDEX`: A positive integer (1, 2, 3, …) which must not exceed the last index in the buyers' list

Example: `blist 3`

Precise outputs when the command succeeds:

>Got it. Here's the information of this buyer:<br>
Jane Doe; Phone: 91234567; Email: janedoe@gmail.com; Address: 1 College Ave East; House Info: Central Area 5 Room Condominium; Priority: nil; Tags:

<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

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

--------------------------------------------------------------------------------------------------------------------


### Sorting buyers: `bsort`

Sorts the buyers' list by the provided attribute and in the given direction.

Format: `bsort ATTRIBUTE_PREFIX DIRECTION`
* `ATTRIBUTE_PREFIX`: Refer to the add buyer command, `buyer`
* `DIRECTION`: "a" OR "d" for ascending or descending respectively

Example: `bsort n/d`

Precise outputs when the command succeeds:

>Got it. I've sorted the buyer list!


Precise outputs when the command fails:

>Multiple values specified for the following single-valued field(s): n/

<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

--------------------------------------------------------------------------------------------------------------------

### Sorting sellers: `ssort`

Sorts the sellers' list by the provided attribute and in the given direction.

Format: `ssort ATTRIBUTE_PREFIX DIRECTION`
* `ATTRIBUTE_PREFIX`: Refer to the add seller command, `seller`
* `DIRECTION`: "a" OR "d" for ascending or descending respectively

Example: `ssort n/d`

Precise outputs when the command succeeds:

>Got it. I've sorted the seller list!


Precise outputs when the command fails:

>Multiple values specified for the following single-valued field(s): n/

<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

--------------------------------------------------------------------------------------------------------------------

### Undoing previous action: `undo`

Undoes the previous action.

Format: `undo`

**Tip: Using `undo` multiple times will undo multiple actions in order.**

Precise outputs when the command succeeds:

>Last command was undone.

Precise outputs when the command fails:

>No commands to undo!

<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

--------------------------------------------------------------------------------------------------------------------

### Redoing previous action: `redo`

Restores the previously undone action.

Format : `redo`

**Tip: Using `redo` multiple times will redo multiple actions in order.**

Precise outputs when the command succeeds:

>The next command was redone.

Precise outputs when the command fails:

>No commands to redo!
<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

--------------------------------------------------------------------------------------------------------------------
### Viewing help: `help`

Shows a message explaining how to access the help page.

Format: `help`
--------------------------------------------------------------------------------------------------------------------

### Exiting the program: `exit`

Exits the program.

Format: `exit`
<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous RTPM home folder.
<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------
## On the
--------------------------------------------------------------------------------------------------------------------
## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add Buyer**    | `buyer n/NAME [p/PHONE_NUMBER] [e/EMAIL] [ah/HOME_ADDRESS] [i/BUY_HOUSE_INFO] [prio/PRIORITY] [t/TAG]`
**Add Seller**    | `seller n/NAME [p/PHONE_NUMBER] [e/EMAIL] [ah/HOME_ADDRESS] [as/SELLING_ADDRESS] [i/SELLING_HOUSE_INFO] [prio/PRIORITY] [t/TAG]​`
**Edit Buyer** | `bedit INDEX PREFIX/VALUE [MORE_PREFIX/VALUE]`
**Edit Seller** | `sedit INDEX PREFIX/VALUE [MORE_PREFIX/VALUE]`
**Delete Buyer** | `bdelete INDEX`
**Delete Seller** | `sdelete INDEX`
**Clear**  | `clear`
**Set Buyer Priority** | `bprio INDEX PRIORITY`
**Set Seller Priority** | `sprio INDEX PRIORITY`
**List All** | `list`
**Filter**   | `filter KEYWORD [MORE_KEYWORDS]`
**List Seller**   | `slist INDEX`
**List Buyer**   | `blist INDEX` 
**Sort Buyers** | `bsort ATTRIBUTE_PREFIX DIRECTION`
**Sort Sellers** | `ssort ATTRIBUTE_PREFIX DIRECTION`
**Undo**   | `undo`
**Redo**  | `redo`
**Exit**   | `exit`
**Help**   | `help`
<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>

--------------------------------------------------------------------------------------------------------------------

##Appendix A: Warnings

RTPM allows you to flexibly input most fields of data. However, we still have some things in mind for each field. Hence, the warning system informs the user of any valid but possibly unintended inputs. The warning system is able to alert the user of multiple errors at once. For a non-exhaustive list, see below.

The warning system is also used to check if, when you are adding new buyers/sellers, whether there are two similar buyers or two similar sellers, or a buyer that shares the same name as a seller.

RTPM's definition of 'similar' is as follows: Either one of the names is contained in the other, or the names require 2 or less edits (deletions, insertions, transpositions) to make them the same. (for more details search for a definition of Levenshtein distance).

```
Warning!; [Phone numbers should only contain numbers, and it should be at least 3 digits long. Area codes are allowed, signified by a '+' and up to 3 numbers, followed by a space separating this from the main number.]
Please ignore if this is expected.
```
```
Warning!; [Emails should be of the format local-part@domain and adhere to the following constraints:
1. The local-part should only contain alphanumeric characters and these special characters, excluding the parentheses, (+_.-). The local-part may not start or end with any special characters.
2. This is followed by a '@' and then a domain name. The domain name is made up of domain labels separated by periods.
The domain name must:
    - end with a domain label at least 2 characters long
    - have each domain label start and end with alphanumeric characters
    - have each domain label consist of alphanumeric characters, separated only by hyphens, if any.]
Please ignore if this is expected.
```

<div style='text-align: right;'>

[Back to top](#rtpm-user-guide)

</div>
