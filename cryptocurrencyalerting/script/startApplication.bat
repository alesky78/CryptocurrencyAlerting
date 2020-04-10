

REM This script start the cryptocurrencyallerting application
REM created by Alessandro D'Ottavio


REM Configure the console
@echo off
COLOR 04
MODE 80,20
TITLE automa


REM Start the application
start javaw -classpath cryptocurrencyalerting.jar;lib/*;configuration it.spaghettisource.cryptocurrencyalerting.app.Application swing > null
