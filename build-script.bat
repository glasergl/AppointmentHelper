@echo off

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

rename "AppointmentAdapter-0.0.1-SNAPSHOT-jar-with-dependencies.jar" "AppointmentAdapter.jar"
rename "AppointmentEditor-0.0.1-SNAPSHOT-jar-with-dependencies.jar" "AppointmentEditor.jar"
rename "AppointmentVisualizer-0.0.1-SNAPSHOT-jar-with-dependencies.jar" "AppointmentVisualizer.jar"

pause