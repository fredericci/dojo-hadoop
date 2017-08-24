package com.ciandt.dojo.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Reduce extends Reducer<Text, IntWritable, Text, IntWritable>{

	private int totalSeconds;
	
	private IntWritable result = new IntWritable();
	
	@Override
	protected void reduce(Text phone, Iterable<IntWritable> callTime, Context context) throws IOException, InterruptedException {
		
		totalSeconds = 0;
		
		for (IntWritable seconds : callTime) {
			totalSeconds += seconds.get();
		}
		
		// > 60min
		if (totalSeconds > 3600) {
			result.set(totalSeconds);
			context.write(phone, result);
		}
		
	}
	
	
}
