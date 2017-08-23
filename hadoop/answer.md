# Dojo - Answer

## Steps

- Dojo

        docker cp /home/CIT/ulysses/itau/git/ACE/dojo/hadoop/puzzle1.dta dojo-hadoop:/root/

        hadoop jar /usr/local/hadoop/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.7.4.jar sudoku puzzle1.dta

        hadoop jar /root/kmeans.classifier-0.0.1.jar com.ciandt.dojo.hadoop.kmeans.classifier.Main /iris.data /output/kmeans

        