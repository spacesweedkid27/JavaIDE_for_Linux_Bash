echo "jdk 17 must be in ../"
../jdk-17.0.1/bin/javac -d ./bin -cp "./bin/jnativehook-2.2.0.jar" ./src/lib/*.java
../jdk-17.0.1/bin/javac -d ./bin -cp "./bin/:./bin/jnativehook-2.2.0.jar" ./src/bin/*.java
chmod +x "./uninstall.sh"
chmod +x ./bin/LinuxBash\ Java\ IDE.sh
echo "installation complete"
