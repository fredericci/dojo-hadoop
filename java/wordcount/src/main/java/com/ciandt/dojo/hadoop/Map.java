package com.ciandt.dojo.hadoop;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map extends Mapper<LongWritable, Text, Text, IntWritable> {

	private static final IntWritable ONE = new IntWritable(1);
	private Text word = new Text();
	
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

		String line = value.toString();
		StringTokenizer stringTokenizer = new StringTokenizer(line, " ");

		while (stringTokenizer.hasMoreElements()) {
			word.set(stringTokenizer.nextToken());
			context.write(word, ONE);
		}
	}

}
