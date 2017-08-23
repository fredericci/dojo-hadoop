package com.ciandt.dojo.hadoop.kmeans.classifier;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.hadoop.io.ArrayPrimitiveWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Cluster 0: 6.1,2.9,4.7,1.4 
 * Cluster 1: 6.2,2.9,4.3,1.3 
 * Cluster 2: 6.9,3.1,5.1,2.3
 * 
 * Cluster 0 <-- Iris-versicolor
 * Cluster 1 <-- Iris-setosa
 * Cluster 2 <-- Iris-virginica
 */
public class Map extends Mapper<LongWritable, Text, Text, Text> {

	private static final Log logger = LogFactory.getLog(Map.class);
	
	private Text keyOutput = new Text();
	private Text valueOutput = new Text();
	
	private static final String VERSICOLOR = "Iris-versicolor";
	private static final String SETOSA = "Iris-setosa";
	private static final String VIRGINICA = "Iris-virginica";
	
	private int dimensions = 4;
	private double[][] centroids = {  { 6.1, 2.9, 4.7, 1.4 } 
									, { 6.2, 2.9, 4.3, 1.3 }
									, { 6.9, 3.1, 5.1, 2.3 }};

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

		String line = value.toString();
		String[] coordenates = line.split(",");	
		
		if (coordenates.length < dimensions){
			logger.error("coordenates length: " + coordenates.length); 
			logger.error(line);
		}else{
			
			try{	
				
				double[] point = {0,0,0,0};
				for (int i = 0; i < dimensions; i++) {
					point[i] = Double.parseDouble(coordenates[i]);			
				}
				
				double[] distance = {0,0,0};
				for (int i = 0; i < centroids.length; i++) {
					
					distance[i] =  Math.sqrt( 
										( Math.pow(point[0] - centroids[i][0],2) )
									+ 	( Math.pow(point[1] - centroids[i][1],2) )
									+ 	( Math.pow(point[2] - centroids[i][2],2) )
									+ 	( Math.pow(point[3] - centroids[i][3],2) )
							        );
				}
				
				if (distance[0] < distance[1] && distance[0] < distance[2]){
					logger.info(line);
					logger.info(Arrays.toString(distance));
					valueOutput.set(VERSICOLOR);
				} else if ( distance[1] < distance[0] && distance[1] < distance[2]){
					valueOutput.set(SETOSA);
				}else{
					valueOutput.set(VIRGINICA);
				}
				
				keyOutput.set(coordenates[4]);
				
				context.write(keyOutput, valueOutput);		
				
			}catch (Exception e) {
				logger.error("Failed processing: "+line);				
				logger.error(e.getMessage());
				e.printStackTrace();				
			}
			
		}
		
	}

}