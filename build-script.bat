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
echo - Adapter:
call mvn clean package -f source/Adapter/
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
echo create Geburtstage.txt
echo Test1.21.9>>output\Geburtstage.txt
echo Test2.27.8>>output\Geburtstage.txt
echo Test3.25.9>>output\Geburtstage.txt
echo Test4.5.5>>output\Geburtstage.txt
echo Test5ßöäü.25.2>>output\Geburtstage.txt

echo\
echo adapt "Geburtstage.txt" to "appointments.json"
cd output
rename "AppointmentAdapter-0.0.1-SNAPSHOT-jar-with-dependencies.jar" "AppointmentAdapter.jar"
call java -jar AppointmentAdapter.jar

echo\
echo pack .exe-Files in zip
call 7z a TerminHelfer.zip *.exe

echo\
echo build finished.

pause