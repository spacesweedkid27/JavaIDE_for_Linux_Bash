wget https://download.oracle.com/java/17/latest/jdk-17_linux-aarch64_bin.tar.gz
mkdir java
tar -C ./java -xzvf ./jdk-17_linux-aarch64_bin.tar.gz
rm jdk-17_linux-aarch64_bin.tar.gz
echo "jdk 17 installed in ./java"
./java/jdk-17.0.1/bin/javac -d ./bin ./src/lib/*.java
./java/jdk-17.0.1/bin/javac -d ./bin -cp ./bin ./src/bin/*.java
echo "installation complete"