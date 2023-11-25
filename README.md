# AppointmentHelper
A set of tools to organize events which occur on the same day every year, e.g., birthdays, wedding days etc.

Tool     | What it does
-------- | --------------------------------------------------------------------------------------
Editor   | Add, change and delete appointments
Calendar | Overview of all stored appointments
Reminder | Shows appointments which occur today or tomorrow (at the time the program is executed)

## Build-
1. Install https://github.com/glasergl/StandardGlaserGl (via Maven, i.e., run *mvn install* with a console in the folder of the cloned repository).
1. Clone this repository.
1. Run *mvn package*.
1. The target of the Editor, Calendar and Reminder contain an .exe file which should be moved to a common folder where they should be stored at.

## Usage
1. Put a shortcut to the **Reminder** in the autostart folder of Windows.
1. Enter your appointments with the **Editor**.
1. A window will show if an appointment occurs today or will occur tomorrow.
1. You can always look at an overview of all appointments with the **Calendar**.
  
## Notes
- The file *AppointmentHelper_Configuration.json* needs to be in the same folder as the three .exe-files.
- Do not modify the file *AppointmentHelper_Configuration.json* on your own.
