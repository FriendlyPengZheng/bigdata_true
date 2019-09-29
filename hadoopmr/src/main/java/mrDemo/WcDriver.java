package mrDemo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;


public class WcDriver extends Configured implements Tool {
    static {
        System.load("D:\\MyDownloads\\Download\\hadoop-2.7.3\\hadoop.dll");
    }
    public static void main(String[] args) throws Exception {
        //BasicConfigurator.configure();
        int status = ToolRunner.run(new WcDriver(),args);
        System.exit(status);
    }
    public int run(String[] args) throws Exception {

        //创建一个job = map + reduce
        Configuration conf = new Configuration();
        //JobConf job = new JobConf(this.getConf(), this.getClass());
        //创建一个Job
        Job job = Job.getInstance(conf);
        //Job job = new Job(getConf(),"wc");
        //Job job = new Job(conf);
        //指定任务的入口
        //job.setJarByClass(WcDriver.class);
        job.setJarByClass(getClass());
        //指定job的mapper
        job.setMapperClass(WcMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        //指定job的reducer
        job.setReducerClass(WcReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        //指定任务的输入和输出
        //MultipleInputs.addInputPath(job, new Path(args[0]),FileInputFormat.class  );
        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        FileSystem fs = FileSystem.get(conf);
        if(fs.exists(new Path(args[1]))){
            fs.delete(new Path(args[1]),true);
        }
        //提交任务
        return job.waitForCompletion(true) ? 0:1;
    }
}
