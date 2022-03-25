@echo off

echo delete old files
del /s /q output\*.*
echo\

echo build modules

echo ################################################################-Base-################################################################
call mvn clean install -f Base/

echo ################################################################-FileInteraction-################################################################
call mvn clean install -f FileInteraction/

echo ################################################################-Adapter-################################################################
call mvn clean package -f Adapter/

echo ################################################################-Calendar-################################################################
call mvn clean package -f Calendar/

echo ################################################################-Editor-################################################################
call mvn clean package -f Editor/

echo ################################################################-Reminder-################################################################
call mvn clean package -f Reminder/

echo\
echo create .exe-Files
call launch4jc Calendar\config.xml
echo\
call launch4jc Editor\config.xml
echo\
call launch4jc Reminder\config.xml

echo\
echo create Geburtstage.txt
echo Test1.21.9>>output\Geburtstage.txt
echo Test2.27.8>>output\Geburtstage.txt
echo Test3.25.9>>output\Geburtstage.txt
echo Test4.5.5>>output\Geburtstage.txt
echo Test5.25.2>>output\Geburtstage.txt

echo\
echo pack .exe-Files in zip
call 7z a output\TerminHelfer.zip .\output\*.exe

echo\
echo build finished.

pause