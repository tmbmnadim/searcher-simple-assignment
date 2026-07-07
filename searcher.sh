#!/bin/bash
javac -d build Main.java;java -cp build Main  "$@"
read -p "Press [Enter] to close..."