@echo off
setlocal enabledelayedexpansion

set SELENIUM_VERSION=4.15.0
set TARGET_DIR=%~dp0..\target
set JAR_FILE=selenium-server-%SELENIUM_VERSION%.jar
set JAR_PATH=%TARGET_DIR%\%JAR_FILE%
set DOWNLOAD_URL=https://github.com/SeleniumHQ/selenium/releases/download/selenium-%SELENIUM_VERSION%/%JAR_FILE%

if not exist "%JAR_PATH%" (
    echo Selenium Server JAR not found. Downloading version %SELENIUM_VERSION%...
    if not exist "%TARGET_DIR%" mkdir "%TARGET_DIR%"
    powershell -Command "Invoke-WebRequest '%DOWNLOAD_URL%' -OutFile '%JAR_PATH%'"
    if %errorlevel% neq 0 (
        echo Failed to download Selenium Server from %DOWNLOAD_URL%
        exit /b 1
    )
    echo Download complete: %JAR_PATH%
) else (
    echo Found existing Selenium Server: %JAR_PATH%
)

echo Starting Selenium Grid Standalone...
java -jar "%JAR_PATH%" standalone
