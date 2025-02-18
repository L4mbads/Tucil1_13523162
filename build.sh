#!/bin/bash
mkdir -p bin
/usr/bin/find ./src/com/fachriza/iqpuzzlersolver -name "*.java" > sources.txt
javac -d bin -sourcepath /src/com/fachriza/iqpuzzlersolver @sources.txt
rm sources.txt
echo "Build complete!"
