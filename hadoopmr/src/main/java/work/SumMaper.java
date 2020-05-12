package work;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SumMaper extends Mapper<LongWritable, Text, Text, LongWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] items = value.toString().split(",");
        if (!StringUtils.startsWith(items[0], "t")
                && !StringUtils.isEmpty(items[0])
                && StringUtils.contains(items[0],"-")) {
            context.write(new Text(items[0]), new LongWritable(Integer.parseInt(items[1])));
        }
    }
}
