#!/bin/bash

#-------------PURPOSE OF THIS SCRIPT------------------
# DEPLOYMENT SCRIPT FOR OPT-NAUKRI BACKEND APPLICATION
# ----- DEPLOYED BY RAJESH K  -----------
# ----- VERSION 1.0 ---------
# ----- DATE: JUN 1ST 2019 -----
JAR_NAME="TrueherbAPI-1.0.0.jar"
JAR_PATH="/home/ubuntu/trueherbProject/trueherbapi/TrueherbAPI/target/TrueherbAPI-1.0.0.jar"
PROJECT_PATH="/home/ubuntu/trueherbProject/trueherbapi/TrueherbAPI/"
BACKUP_JAR_NAME="TrueherbAPI-backup.jar"

then
sudo kill -9 $(ps -ef | grep $JAR_NAME | grep -v 'grep' | awk '{ printf $2 }')
#REMOVE EXISTING JAR FILE
cd $PROJECT_PATH
sudo mvn clean
sudo mvn install

#nohup java -jar TrueherbAPI-1.0.0.jar  > /dev/null &
sudo nohup java -jar $JAR_PATH  > /dev/null &
#sudo nohup java -jar TrueherbAPI-1.0.0.jar  > /dev/null &
echo "\n*********************************************************************"
echo "APPLICATION DEPLOYED SUCCESSFULLY"
echo "***********************************************************************"
echo "PRESS CTRL + C TO EXIT"

