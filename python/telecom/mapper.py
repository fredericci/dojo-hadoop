#!/usr/bin/env python
'''
Created on 23 de ago de 2017

@author: ulysses
'''

import sys
from datetime import datetime
 
#--- get all lines from stdin ---
for line in sys.stdin:
    #--- remove leading and trailing whitespace---
    line = line.strip()

    parts = line.split('|')

    if parts[4] == "1":
        endTime = parts[3]
        endTime = datetime.strptime(endTime, "%Y-%m-%d %H:%M:%S")

        startTime = parts[2]
        startTime = datetime.strptime(startTime, "%Y-%m-%d %H:%M:%S")

        seconds = (endTime - startTime).seconds

        print '%s\t%s' % (parts[0], seconds)
        