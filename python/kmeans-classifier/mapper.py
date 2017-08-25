'''
Created on 24 de ago de 2017

@author: ulysses

 Cluster 0: 6.1,2.9,4.7,1.4 
 Cluster 1: 6.2,2.9,4.3,1.3 
 Cluster 2: 6.9,3.1,5.1,2.3
  
 Cluster 0 <-- Iris-versicolor
 Cluster 1 <-- Iris-setosa
 Cluster 2 <-- Iris-virginica
 
'''
#!/usr/bin/python
import sys
import math

dimensions = 4
centroids = [  [ 6.1, 2.9, 4.7, 1.4 ] 
             , [ 6.2, 2.9, 4.3, 1.3 ]
             , [ 6.9, 3.1, 5.1, 2.3 ]]

for line in sys.stdin:
    
    line = line.strip()
    point = line.split(",")
    
    if len(point) > dimensions:
    
        distances = [0, 0, 0]
        
        for i in list(range(3)):
            distances[i] = math.sqrt((math.pow(float(point[0]) - centroids[i][0], 2))
                                   + (math.pow(float(point[1]) - centroids[i][1], 2))
                                   + (math.pow(float(point[2]) - centroids[i][2], 2))
                                   + (math.pow(float(point[3]) - centroids[i][3], 2)))
        
        classification = ""
        if (distances[0] < distances[1] and distances[0] < distances[2]):
            classification = "Iris-versicolor"
        elif ( distances[1] < distances[0] and distances[1] < distances[2]):
            classification = "Iris-setosa"
        else:
            classification = "Iris-virginica"
                    
        
        print '%s\t%s' % (point[4], classification)
        
        
