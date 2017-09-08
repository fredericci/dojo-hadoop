#!/bin/bash

#
# Author: ulysses
#

# variables

variable="My variable"
arg1=$1
arg2=$2

echo -e "Variable value: $variable"
echo -e "$# arguments passed\nThey are $@"
echo -e "Argument 1 $args1"
echo -e "Script name: $0"
echo -e "Directory $(pwd)"

# if / loop statements

if [ "$#" -eq 0 ] ; then
    echo "No arguments"
elif [ "$#" -eq 1 ] ; then
    echo "One argument"
else
    echo "Many arguments"
fi

numbers="1 2 3"

echo "Counting "
for number in $numbers
do 
   echo $number
done