::My First batch File
cd\
cd PROJECT
del *.class

:: Compile the Java programs
javac -cp ".;c:\*" Home.java 

:: Run the Java programs
java -cp ".;c:\*" Home