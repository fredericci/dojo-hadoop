package com.ciandt.dojo.hadoop.kmeans.classifier;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Main implements Tool {

	public static void main(String[] args) throws Exception {

		int res = ToolRunner.run(new Configuration(), new Main(), args);
		System.exit(res);

	}

	public int run(String[] args) throws Exception {
		Job job = null;
		try {
			job = Job.getInstance();
			job.setJobName("Kmeans - Classification");
			
			job.setJarByClass(Main.class);
			job.setMapperClass(Map.class);
			job.setReducerClass(Reduce.class);

			FileInputFormat.addInputPath(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));

			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int exitCode = -1;
		try {
			exitCode = (job.waitForCompletion(true) ? 0 : 1);

			if (job.isSuccessful())
				System.out.println("Successful");
			else
				System.err.println("Failed");

		} catch (Exception e) {

			e.printStackTrace();
		}

		return exitCode;

	}

	public void setConf(Configuration conf) {
		// TODO Auto-generated method stub

	}

	public Configuration getConf() {
		// TODO Auto-generated method stub
		return null;
	}

}
