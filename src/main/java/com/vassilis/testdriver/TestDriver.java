package com.vassilis.testdriver;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import com.vassilis.mapper.MapperCommonWordsBtwFiles;
import com.vassilis.reducer.ReducerCommonWordsBtwFiles;

public class TestDriver {

	public static void main(String[] args) {
		if(args.length != 2) {
			System.out.println("program works with 3 parameters...");
			System.out.println("input path having the input files");
			System.out.println("output path; the path the file with the common words will be written to");
			System.exit(-1);
		}
		
		try {
			Job job = new Job();
			
			job.setJarByClass(TestDriver.class);
			job.setJobName("Common words between files");
			
			job.setInputFormatClass(TextInputFormat.class);
			
			//input path for the 2 files
			FileInputFormat.addInputPath(job, new Path(args[0]));
			//setting the output path
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			
			job.setMapperClass(MapperCommonWordsBtwFiles.class);
			job.setReducerClass(ReducerCommonWordsBtwFiles.class);
			
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
			
			job.setOutputFormatClass(TextOutputFormat.class);
			
			System.exit(job.waitForCompletion(true) ? 0 : 1);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
