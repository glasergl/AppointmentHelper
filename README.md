# AppointmentHelper
A set of tools to organize events which occur on the same day every year, e.g., birthdays, wedding days etc.

Tool     | What it does
-------- | --------------------------------------------------------------------------------------
Editor   | Add, change and delete appointments
Calendar | Overview of all stored appointments
Reminder | Shows appointments which occur today or tomorrow (at the time the program is executed)

## Build
1. Install https://github.com/glasergl/StandardGlaserGl.
1. Clone this repository.
1. Run `mvn package`.
1. Each target folder of the modules `Editor`, `Calendar` and `Reminder` contains an `.exe` file which wraps the Maven-generated `.jar`. (jar could be used equivalently, but has no custom icon)
1. Move all `.exe` files to a common folder (where u want to store them).

## Usage
1. Put a shortcut to the `Reminder` in the autostart folder of your operating system.
1. Enter your appointments with the `Editor`.
1. A window will become visible if an appointment occurs today or will occur tomorrow (at the time the `Reminder` is executed at the start of your computer).
1. You can always look at an overview of all appointments with the `Calendar`.
  
## Notes
- If there is no `AppointmentHelper_Configuration.json` file, running any of the `.exe` (or `.jar`) will create an empty one.
- The file `AppointmentHelper_Configuration.json` has to be in the same folder as the three `.exe`-files.
- Do not modify the file `AppointmentHelper_Configuration.json` on your own.
