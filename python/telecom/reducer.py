#!/usr/bin/env python
'''
Created on 23 de ago de 2017

@author: ulysses
'''

import sys

lastPhone = ""
totalSeconds = 0

#--- get all lines from stdin ---
for line in sys.stdin:
    #--- remove leading and trailing whitespace---
    line = line.strip()
    phone, seconds = line.split('\t')

    seconds = int(seconds)

    if lastPhone == phone:
        totalSeconds = totalSeconds + seconds

    else:
        if totalSeconds > 3600 and lastPhone != "":
            print "%s\t%s" % (lastPhone, totalSeconds)

        totalSeconds = seconds

    lastPhone = phone

if totalSeconds > 3600:
    print "%s\t%s" % (lastPhone, totalSeconds)
