# AppointmentHelper
A set of tools to handle events which occur on the same day every year (e.g. birthdays, wedding days, ...).

The whole tool contains three tools:
Editor | Add, change and delete appointments
Calendar | Overview of all stored appointments
Reminder | Shows appointments which occur today or tomorrow (at the time the program is executed)

Currently, there is an Adapter, too. It is used to migrate from an old version.

## How to use
	1. Clone the repository
	2. Run *build-script.bat*
	3. The output-Folder contains the three .exe-files for the **Editor**, **Calendar** and **Reminder** (or a zip-Archive with all three)
	4. Put a shortcut of the **Reminder** in the autostart folder of Windows
	5. Enter your appointments with the **Editor**
	6. If you start your computer and an appointment occurs today or tomorrow, a window will pop up saying which appointment.
  
## Important
	- The .exe-Files are fully portable but the file *appointments.json* needs to be in the same folder as the three .exe-files.
(Create shortcuts of the .exe to move them to any location)
	- Don't modify the file *appointments.json* or else the **Editor** won't be able to read and change it anymore.
