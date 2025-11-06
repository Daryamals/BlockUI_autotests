@echo off

set FAILED_XML_PATH=%~dp0..\target\surefire-reports\testng-failed.xml

echo Looking for testng-failed.xml in %FAILED_XML_PATH%...

if not exist "%FAILED_XML_PATH%" (
    echo.
    echo =======================================================
    echo  testng-failed.xml not found. No failed tests to rerun.
    echo =======================================================
    echo.
    exit /b 0
)

echo.
echo =================================
echo  Found testng-failed.xml.
echo  Changing to project root directory...
echo  Rerunning failed tests...
echo =================================
echo.

cd %~dp0..
mvn test -Dsurefire.suiteXmlFiles="%FAILED_XML_PATH%"

echo.
echo =================================
echo  Rerun of failed tests complete.
echo =================================
echo.