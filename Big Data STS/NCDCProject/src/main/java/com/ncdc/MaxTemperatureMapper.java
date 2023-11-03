package com.ncdc;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxTemperatureMapper extends Mapper<Object, Text, Text, IntWritable>{
	private static final int MISSING = 9999;
	@Override
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String year = line.substring(15, 19);
		int air_temp;
		if(line.charAt(87) == '*') {
			air_temp = Integer.parseInt(line.substring(88, 92));
		} else {
			air_temp = Integer.parseInt(line.substring(87, 92));
		}
		String quality = line.substring(92, 93);
		if(air_temp != MISSING && quality.matches("[01459]")) {
			context.write(new Text(year), new IntWritable(air_temp));
		}
	}

}
