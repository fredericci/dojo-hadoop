package com.ciandt.dojo.hadoop.kmeans.classifier;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * Cluster 0 <-- Iris-versicolor 
 * Cluster 1 <-- Iris-setosa 
 * Cluster 2 <-- Iris-virginica
 */
public class Reduce extends Reducer<Text, Text, Text, Text> {

	private Text output = new Text();
	private int[] counter = { 0, 0, 0 };

	private static final String VERSICOLOR = "Iris-versicolor";
	private static final String SETOSA = "Iris-setosa";
	private static final String VIRGINICA = "Iris-virginica";

	@Override
	protected void reduce(Text expected, Iterable<Text> result, Context context)
			throws IOException, InterruptedException {

		counter[0] = 0;
		counter[1] = 0;
		counter[2] = 0;
		
		for (Text value : result) {

			if (VERSICOLOR.equals(value.toString())) {
				counter[0]++;
			} else if (SETOSA.equals(value.toString())) {
				counter[1]++;
			} else if (VIRGINICA.equals(value.toString())) {
				counter[2]++;
			} else {
				
			}
		}
		
		output.set(Arrays.toString(counter));
		
		context.write(expected, output);

	}

}
