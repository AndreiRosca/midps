#! /bin/bash

cd ./IDE/HelloWorldPrograms

#compile c program
echo "Compiling and running the C program..."
gcc -Wall -std=c99 -g -o ./c/hello.exe ./c/hello.c && ./c/hello.exe

#compile c++ program
echo "Compiling and running the C++ programm..."
g++ -Wall -std=c++11 -g -o ./cpp/hello.exe ./cpp/hello.cpp && ./cpp/hello.exe

#compile java program
echo "Compiling and runnig the Java programm..."
javac ./java/hello.java && java -cp ./java HelloWorld

#run the python program
echo "Running the python interpreter..."

/C/Users/debian/AppData/Local/Programs/Python/Python35-32/python $PWD/python/hello.py

#running the Runy program
echo "Running the Ruby interpreter..."

/C/Ruby22/bin/ruby $PWD/ruby/hello.rb

