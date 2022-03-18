@echo off

echo delete old files
del TerminAdapter.jar
del TerminKalender.jar
del TerminEditor.jar
del TerminReminder.jar

del TerminHelfer.zip

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
rename "AppointmentAdapter-0.0.1-SNAPSHOT-jar-with-dependencies.jar" "TerminAdapter.jar"
rename "AppointmentCalendar-0.0.1-SNAPSHOT-jar-with-dependencies.jar" "TerminKalender.jar"
rename "AppointmentEditor-0.0.1-SNAPSHOT-jar-with-dependencies.jar" "TerminEditor.jar"
rename "AppointmentReminder-0.0.1-SNAPSHOT-jar-with-dependencies.jar" "TerminReminder.jar"

echo pack jars in zip:
call 7z a TerminHelfer.zip TerminAdapter.jar TerminKalender.jar TerminEditor.jar TerminReminder.jar

echo\

echo build finished.

pause