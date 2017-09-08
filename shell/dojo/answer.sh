#!/bin/bash

#
# Author: ulysses
#

directory_in=$1
directory_out=$2
filter=$3
summary=SUMMARY-$(date +%F).log

# check args
if [ "$#" -ne 3 ]; then
    echo "Please, inform directory and filter"
    echo "Directory in\nDirectory out\nFilter"
    exit 1
fi

# check if directories exits
if [ -d "$directory_out" ]; then
    rm -rf $directory_out  
fi

mkdir -p $directory_out

# Find and move files
find $directory_in -type f -name '*.log' | xargs cp -t $directory_out

# create summary file
grep $filter $directory_out/*.log > $directory_out/$summary

# compress files
tar -zcf $directory_out/archive.tar  $directory_out/*.log

# Remove files
rm $directory_out/*.log
