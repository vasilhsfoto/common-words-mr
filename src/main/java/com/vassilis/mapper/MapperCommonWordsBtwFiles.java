package com.vassilis.mapper;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class MapperCommonWordsBtwFiles extends Mapper<LongWritable, Text, Text, Text> {
	@Override
	protected void map(LongWritable key, Text word, Context context)
			throws IOException, InterruptedException {
		
		FileSplit fileSplit = (FileSplit) context.getInputSplit();
		
		String inputFileName = fileSplit.getPath().getName();
		
		context.write(word, new Text(inputFileName));
	}
}
