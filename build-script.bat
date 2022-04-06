@echo off

echo delete old output
del /s /q output\*.*

echo\
echo build modules
echo - Base:
call mvn clean install -f source/Base/
echo - FileInteraction:
call mvn clean install -f source/FileInteraction/
echo - Errors:
call mvn clean install -f source/Errors/
echo - Calendar:
call mvn clean install -f source/Calendar/
echo - Editor:
call mvn clean package -f source/Editor/
echo - Reminder:
call mvn clean package -f source/Reminder/

echo\
echo create .exe-Files
call launch4jc source\Calendar\launch4j_configuration.xml
echo\
call launch4jc source\Editor\launch4j_configuration.xml
echo\
call launch4jc source\Reminder\launch4j_configuration.xml

echo\
cd output
echo pack .exe-Files in zip
call 7z a TerminHelfer.zip *.exe

echo\
echo build finished.

pause