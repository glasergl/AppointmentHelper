@echo off

echo delete old files
echo\

del AppointmentAdapter.jar
del AppointmentEditor.jar
del AppointmentVisualizer.jar
del AppointmentHelper.zip
del appointments.json

echo\
echo\

echo ################################################################-Base-################################################################
echo\
call mvn install -f Base/
echo\
echo\
echo\

echo ################################################################-FileInteraction-################################################################
echo\
call mvn install -f FileInteraction/
echo\
echo\
echo\

echo ################################################################-Adapter-################################################################
echo\
call mvn package -f Adapter/
echo\
echo\
echo\

echo ################################################################-Editor-################################################################
echo\
call mvn package -f Editor/
echo\
echo\
echo\

echo ################################################################-Visualizer-################################################################
echo\
call mvn package -f Visualizer/

echo ################################################################-rename jars-################################################################
echo\
rename "AppointmentAdapter-0.0.1-SNAPSHOT-jar-with-dependencies.jar" "AppointmentAdapter.jar"
rename "AppointmentEditor-0.0.1-SNAPSHOT-jar-with-dependencies.jar" "AppointmentEditor.jar"
rename "AppointmentVisualizer-0.0.1-SNAPSHOT-jar-with-dependencies.jar" "AppointmentVisualizer.jar"
echo\

echo ################################################################-pack jars in zip-################################################################
echo\
call 7z a AppointmentHelper.zip AppointmentAdapter.jar AppointmentEditor.jar AppointmentVisualizer.jar
echo\
echo\
echo\

echo build finished.

pause