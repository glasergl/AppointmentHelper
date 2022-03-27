# AppointmentHelper
A set of tools to organize events which occur on the same day every year (e.g. birthdays, wedding days etc.).

Tool     | What it does
-------- | -------------------------------------------------------------------------------------
Editor   | Add, change and delete appointments
Calendar | Overview of all stored appointments
Reminder | Shows appointments which occur today or tomorrow (at the time the program is executed)

Currently, there is an Adapter, too. It is used to migrate from an old version.

## How to use
1. Clone the repository.
2. Run *build-script.bat*.
3. The output-Folder contains the three .exe-files for the **Editor**, **Calendar** and **Reminder**.
4. Put a shortcut to the **Reminder** in the autostart folder of Windows.
5. Enter your appointments with the **Editor**.
6. A window will show if an appointment occurs today or will occur tomorrow.
  
## Important
- The file *appointments.json* needs to be in the same folder as the three .exe-files.
- Don't modify the file *appointments.json*.