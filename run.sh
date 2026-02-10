rm -rf build
mkdir build
javac ./src/*.java -d ./build/
java -cp ./build src.Main