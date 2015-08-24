#!/bin/bash
echo "Start checking current date..."
weekdaytest=$(date -d "$d" +"%u")
echo "Today is " $(date -d "$d")

if [ "$weekdaytest" == 6 ] || [ "$weekdaytest" == 7 ];
then
echo "Its weekend"
mv Weekend/*.jpg Target
else
echo "Its business day"
mv BusinessDays/*.jpg Target
fi