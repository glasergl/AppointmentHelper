@echo off

echo delete old files
del AppointmentAdapter.jar
del AppointmentEditor.jar
del AppointmentVisualizer.jar
del AppointmentHelper.zip
del appointments.json

echo ################################################################-Base-################################################################
call mvn install -f Base/

echo ################################################################-FileInteraction-################################################################
call mvn install -f FileInteraction/

echo ################################################################-Adapter-################################################################
call mvn package -f Adapter/

echo ################################################################-Editor-################################################################
call mvn package -f Editor/

echo ################################################################-Visualizer-################################################################
call mvn package -f Visualizer/

echo rename jars
rename "AppointmentAdapter-0.0.1-SNAPSHOT-jar-with-dependencies.jar" "AppointmentAdapter.jar"
rename "AppointmentEditor-0.0.1-SNAPSHOT-jar-with-dependencies.jar" "AppointmentEditor.jar"
rename "AppointmentVisualizer-0.0.1-SNAPSHOT-jar-with-dependencies.jar" "AppointmentVisualizer.jar"

echo pack jars in zip:
call 7z a AppointmentHelper.zip AppointmentAdapter.jar AppointmentEditor.jar AppointmentVisualizer.jar

echo\

echo build finished.

pause