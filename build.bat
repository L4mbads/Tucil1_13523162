@echo off
setlocal

:: Create output directory if it doesn't exist
if not exist bin mkdir bin

:: Find all Java files and compile
dir /s /b src\com\fachriza\iqpuzzlersolver\*.java > sources.txt
javac -d bin -sourcepath src\com\fachriza\iqpuzzlersolver @sources.txt
del sources.txt

if %ERRORLEVEL% neq 0 (
    echo Compilation failed.
    exit /b %ERRORLEVEL%
)

echo Build complete!

endlocal

