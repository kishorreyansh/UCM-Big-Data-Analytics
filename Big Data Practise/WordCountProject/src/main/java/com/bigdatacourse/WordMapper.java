package com.bigdatacourse;

public class WordMapper extends Mapper<IntWritable, Text, Text, IntWritable>
{
    @Override
    public void map(IntWritable key, Text value, Context context){
        String line = value.toString();
        for(String word: line.split("\\W+")){
            if(word.length() > 0){
                context.write(new Text(word), new IntWritable(1));
            }
        }
    }
}