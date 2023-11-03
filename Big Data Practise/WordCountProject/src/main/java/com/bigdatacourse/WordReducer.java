package com.bigdatacourse;

import javax.naming.Context;

public class WordReducer extends Reducer<Text, IntWritable, Text, IntWritable>
{
   @Override
   public void reduce(Text key, Iterable<IntWritable> values, Context context){
    int wordCount = 0;
    for(IntWritable value : values){
        wordCount += value.get();
    }
    context.write(key,IntWritable(wordCount));
   }
}