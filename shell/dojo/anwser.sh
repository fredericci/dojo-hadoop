#!/bin/bash

#
# Author: ulysses
#

directory_in=$1
directory_out=$2
filter=$3
output_file="SUMMARY - "$(date +%F)

# check args
if [ "$#" -ne 3]; then
    echo "Please, inform directory and filter"
    echo "Directory in\nDirectory out\nFilter"
    exit 1
fi

# check if directories exits
if [ -d "$directory_out" ]; then
    rm -rf $directory_out
else
    mkdir -p $directory_out
fi

# Find and move files
find $directory_in -type f -name '*.log' | xargs cp -t $directory_out

# create summary file
grep $filter > output_file".log"

# compress files
tar -zcf output_file".tar" *.log

# Remove files
rm *.log
