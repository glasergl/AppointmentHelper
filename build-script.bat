@echo off

echo delete old output
del /s /q output\*.*

echo\
echo build modules
echo - Base:
call mvn clean install -f source/AppointmentHelper-Entities/
echo - FileInteraction:
call mvn clean install -f source/AppointmentHelper-Files/
echo - Errors:
call mvn clean install -f source/AppointmentHelper-Util/
echo - Calendar:
call mvn clean install -f source/AppointmentHelper-Calendar/
echo - Editor:
call mvn clean package -f source/AppointmentHelper-Editor/
echo - Reminder:
call mvn clean package -f source/AppointmentHelper-Reminder/

echo\
echo create .exe-Files
call launch4jc source\AppointmentHelper-Calendar\launch4j_configuration.xml
echo\
call launch4jc source\AppointmentHelper-Editor\launch4j_configuration.xml
echo\
call launch4jc source\AppointmentHelper-Reminder\launch4j_configuration.xml

echo\
cd output
echo pack .exe-Files in zip
call 7z a TerminHelfer.zip *.exe

echo\
echo build finished.

pause