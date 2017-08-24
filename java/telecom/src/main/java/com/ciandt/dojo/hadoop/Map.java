package com.ciandt.dojo.hadoop;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Input expected
 * 
 * FromPhoneNumber|ToPhoneNumber|CallStartTime|CallEndTime|STDFlag
 */
public class Map extends Mapper<LongWritable, Text, Text, IntWritable> {

	private static final String STDFlag = "1";
	private static final String[] datePatterns = { "yyyy-MM-dd HH:mm:ss" };

	private Text phoneNumber = new Text();
	private IntWritable result = new IntWritable();

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		String line = value.toString();
		String[] parts = line.split("\\|");

		if (parts.length < 5) {
			// discart
		} else {

			if (STDFlag.equals(parts[4])) {

				try {
					// CallStartTime
					Date startDate = DateUtils.parseDate(parts[2], datePatterns);

					// CallEndTime
					Date endDate = DateUtils.parseDate(parts[3], datePatterns);

					int seconds = (int) ((endDate.getTime() - startDate.getTime()) / 1000);

					phoneNumber.set(parts[0]);
					result.set(seconds);

					// FromPhoneNumber TimeInSeconds
					context.write(phoneNumber, result);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

	}
}
