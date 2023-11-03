/*
		            Group 7
------------------------------------------
Raineni Akhil Kumar 		- 700734961
Rakesh Boddu 		      	- 700740597
Suma Mounica Rachamsetty- 700747450
Minhaaz Ul Haq Shaik		- 700741120
Avinash Dakey			      - 700740053
------------------------------------------
*/
import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
public class LetterCount {
  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      System.err.println("Usage: LetterCount <input path> <output path>");
      System.exit(-1);
    }
    Job job = Job.getInstance();
    job.setJarByClass(LetterCount.class);
    job.setMapperClass(LetterCountMapper.class);
    job.setReducerClass(LetterCountReducer.class);
    job.setJobName("Letter Count");
//  job.setNumReduceTasks(2);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
  public static class LetterCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
      String line = value.toString();
      line = line.toUpperCase().replaceAll("[^A-Z]"," ");
      line = line.toUpperCase().replaceAll("\\s","");
      for (int i = 0; i < line.length(); i++) {
        char c = line.charAt(i);
        if (Character.isLetter(c)) {
          context.write(new Text(String.valueOf(c)),new IntWritable(1));
        }
      }
    }
  }
  public static class LetterCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    private IntWritable result = new IntWritable();
    private int total_no_of_chars = 0;
    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
      int count_of_chars = 0;
      for (IntWritable value : values) {
        count_of_chars += value.get();
      }
      result.set(count_of_chars);
      context.write(new Text("("+key.toString() + ", " + result.toString() + "),"), null);
      total_no_of_chars += count_of_chars;
    }
    protected void cleanup(Context context) throws IOException, InterruptedException {
      result.set(total_no_of_chars);
      context.write(new Text("(Total ," + result.toString() + ")"), null);    
    }
  }
}

