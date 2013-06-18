package com.vassilis.reducer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReducerCommonWordsBtwFiles extends Reducer<Text, Text, Text, Text> {
	
	@Override
	protected void reduce(Text word, Iterable<Text> listOfFileNames,
			Context context) throws IOException, InterruptedException {
		
		Set<Text> distinctFileNames = new HashSet<Text>();
		
		for(Text fileName : listOfFileNames) {
			distinctFileNames.add(fileName);
		}
		
		//word belongs to both input files
		if(distinctFileNames.size()==2) {
			context.write(word, null);
		}
	}
}
