'''
Created on 24 de ago de 2017

@author: ulysses

 Cluster 0 <-- Iris-versicolor
 Cluster 1 <-- Iris-setosa
 Cluster 2 <-- Iris-virginica
 
'''
#!/usr/bin/python
import sys

result = {}
counter = [0,0,0]
lastExpected = ''
for line in sys.stdin:
    
    line = line.strip()
    expected, classified = line.split('\t')
    
    #--- changed class
    if expected != lastExpected:
        counter = [0,0,0]
    
    if classified == "Iris-versicolor":
        counter[0]+=1
    elif classified == "Iris-setosa":
        counter[1]+=1
    elif classified == "Iris-virginica":
        counter[2]+=1
        
    result[expected] = counter
    lastExpected = expected
    
for cluster in result.keys():
    print '%s\t%s'% ( cluster, result[cluster] )

    
