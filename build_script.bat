call mvn install -f Base/
call mvn install -f FileInteraction/
call mvn package -f Adapter/
call mvn package -f Editor/
call mvn package -f Visualizer/

pause