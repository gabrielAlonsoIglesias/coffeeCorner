# coffeeCorner
## Description
###### PoC basic stuff for a little coffee shop

## Execution steps

###### Prerequisites
Make sure you have installed:
* JAVA version 8 or higher
* IDE (for this Poc I used *eclipse-jee-2020-03-R*)
* GIT client to clone repository (Eclipse plugin is enough)

###### Steps
1. Clone repository *https://github.com/gabrielAlonsoIglesias/coffeeCorner* into a folder (ie: */tmp*)
2. Open IDE and import Project (General > Existing Projects into Workspace) from location */tmp*
3. Import launchers located in *launchers* folder
4. Execute *Application.launch* launcher for making orders in *Charlene's* Coffee Corner
5. Execute *ApplicationTest.launch* launcher for testing suite

###### Notes
You can:
* Print only one receipt per execution
* Make several selections of different products choosing the accurate number
* If you choose a selection that is not in the menu, program will discard
* Edit *products.txt* to add/remove new/existing products in a valid JSON format
* Basis stuff is implemented; no complex validations have been performed (right JSON format, at least one available selection, duplicated ids, ...) 