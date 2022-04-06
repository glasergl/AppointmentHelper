# AppointmentHelper
A set of tools to organize events which occur on the same day every year (e.g. birthdays, wedding days etc.).

Tool     | What it does
-------- | -------------------------------------------------------------------------------------
Editor   | Add, change and delete appointments
Calendar | Overview of all stored appointments
Reminder | Shows appointments which occur today or tomorrow (at the time the program is executed)

## How to use?

### Requirements:
- Operating System: *Windows*
- *JDK-Version* atleast 1.8.9_301
- *Apache Maven* installed

### Build-process:
1. Clone https://github.com/glasergl/StandardSwing
2. Call *mvn install* from the console
3. Clone this repository
4. Run *build-script.bat*
5. The output-Folder contains the three .exe-files for the **Editor**, **Calendar** and **Reminder**

### Workflow:
1. Put a shortcut to the **Reminder** in the autostart folder of Windows
2. Enter your appointments with the **Editor**
3. A window will show if an appointment occurs today or will occur tomorrow
4. You can always look at an overview of all appointments with the **Calendar**
  
## Important!
- The file *appointments.json* needs to be in the same folder as the three .exe-files
- Don't modify the file *appointments.json*
