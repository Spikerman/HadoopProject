//Created by Nina Lei
//Modified by Dayou DU on Nov 30th, 2017
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class data {

	public static void main(String[] args) throws Exception {
		if(args.length != 2) {
			System.err.println("Usage: 311data <input path> <output path>");
			System.exit(-1);
		}
		
		Job job = new Job();
		job.setJarByClass(data.class);
		job.setJobName("311data");

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.setMapperClass(dataMapper.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}



