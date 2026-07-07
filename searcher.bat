@echo off
javac -d build Main.java && java -cp build Main %*
pause