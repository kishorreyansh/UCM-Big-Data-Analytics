
// group name
// name_700 num

package org.example;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapred.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;


public class LetterCount {

    public static void main(String[] args) throws Exception{

        Job job = Job.getInstance();
        job.setJarByClass(LetterCount.class);
        job.setJobName("Letter Count");

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(LCMapper.class);
        job.setReducerClass(LCReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);


        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }

    public static class  LCMapper extends  Mapper<LongWritable,Text,Text,IntWritable>{
        public void map(LongWritable key, Text value,Context context) throws IOException, InterruptedException {
            String line = value.toString();
            line = line.toLowerCase().replaceAll("[^a-z]", " ");
            line = line.toLowerCase().replaceAll("\\s", "");

            String  tokenizer[] = line.split("");
            for(String SingleChar : tokenizer)
            {
                Text charKey = new Text(SingleChar);
                IntWritable val = new IntWritable(1);
                context.write(charKey, val);
            }
            //total
            context.write(new Text("Total"), new IntWritable(line.length()));

        }

    }


    public static class LCReducer  extends Reducer<Text,IntWritable,Text,IntWritable> {
        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int count=0;
            for (IntWritable value : values) {
                count += value.get();
            }
            context.write(key, new IntWritable(count));
        }
    }
}



