package com.ncdc;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxTemperatureReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		int max_value = Integer.MIN_VALUE;
		for(IntWritable value: values) {
			max_value = Math.max(max_value, value.get());
		}
		context.write(key, new IntWritable(max_value));
	}
}
