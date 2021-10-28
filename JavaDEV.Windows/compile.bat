echo "you will need the jdk-17.0.1 one directory above the repository"
cd ..
..\jdk-17.0.1\bin\javac -cp .\bin\jnativehook-2.2.0.jar -d .\bin .\src\lib\*.java
..\jdk-17.0.1\bin\javac -cp ".\bin\;.\bin\jnativehook-2.2.0.jar" -d .\bin .\src\bin\Main.java
