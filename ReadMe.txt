1. Problem

Find common words between 2 files. we consider that both files are large enough
This approach demonstrates a rather simple approach of using mar reduce API on top of Hadoop. 

2.Description

Map method: Default TextInputFormat is used. In a nutshell, each line (in our case word) is the 
Value of mapper and the mapper's key is a number (offset of the begging of file).
Once the mapper called, it emits the word along with the file name the word belongs to.

Based on how M/R works, this means that all the same words will be processed 
by the same reducer. In general, a hash function executed against the word (type of Text) 
and the resulting id signifies the reducer id that will take care the execution of the word in the
reduce phase. 

Reduce method: every reducer takes as input a key which is the word and as value a list of file names
the word belongs to. If a word belongs to 2 different files then the word is common between the files
and it is written to the output.

TestDriver.class: the configuration of the M/R job; mostly default options were used.

3. Execution
The application can be build using "mvn clean package". The resulted adacom-test-mr.jar file can be executed across multiple
nodes using the command "hadoop jar adacom-test-mr.jar input_path output_path". 

input_path: a path that both input files resides (on HDFS file system)
output_path: the path the common words will be written to (on HDFS file)  

The Map/Reduce version was successfully executed on a single installation of Hadoop version 1.0.3
on pseudo-distributed mode.
