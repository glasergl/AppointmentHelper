@echo off

echo delete old files
del AppointmentAdapter.jar
del AppointmentCalendar.jar
del AppointmentEditor.jar
del AppointmentReminder.jar

del AppointmentHelper.zip

del appointments.json


echo ################################################################-Base-################################################################
call mvn install -f Base/

echo ################################################################-FileInteraction-################################################################
call mvn install -f FileInteraction/

echo ################################################################-Adapter-################################################################
call mvn package -f Adapter/

echo ################################################################-Calendar-################################################################
call mvn package -f Calendar/

echo ################################################################-Editor-################################################################
call mvn package -f Editor/

echo ################################################################-Reminder-################################################################
call mvn package -f Reminder/

echo rename jars
rename "AppointmentAdapter-0.0.1-SNAPSHOT-jar-with-dependencies.jar" "AppointmentAdapter.jar"
rename "AppointmentCalendar-0.0.1-SNAPSHOT-jar-with-dependencies.jar" "AppointmentCalendar.jar"
rename "AppointmentEditor-0.0.1-SNAPSHOT-jar-with-dependencies.jar" "AppointmentEditor.jar"
rename "AppointmentReminder-0.0.1-SNAPSHOT-jar-with-dependencies.jar" "AppointmentReminder.jar"

echo pack jars in zip:
call 7z a AppointmentHelper.zip AppointmentAdapter.jar AppointmentCalendar.jar AppointmentEditor.jar AppointmentReminder.jar

echo\

echo build finished.

pause