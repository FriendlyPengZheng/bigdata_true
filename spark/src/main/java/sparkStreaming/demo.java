package sparkStreaming;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.Optional;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function0;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class demo {
    public static void main(String[] args) {

        SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("my sparkStreaming");
        System.setProperty("HADOOP_USER_NAME","hadoop");

        JavaStreamingContext jsc = new JavaStreamingContext(conf, Durations.seconds(5L));

        jsc.checkpoint("checkpointDir");

        JavaReceiverInputDStream<String> input = jsc.socketTextStream("10.1.1.34",9999);

        //JavaDStream<String> input = jsc.textFileStream(args[0]);

        JavaDStream<String> map = input.flatMap(new FlatMapFunction<String, String>() {
            public Iterator<String> call(String s) throws Exception {
                String[] item = s.split(" ");
                return Arrays.asList(item).iterator();
            }
        });

        JavaPairDStream<String,Integer> pair = map.mapToPair(new PairFunction<String, String, Integer>() {
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<String, Integer>(s,1);
            }
        });

        JavaPairDStream<String,Integer> result = pair.reduceByKey(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });

        JavaPairDStream<String,Integer> re = result.updateStateByKey(new Function2<List<Integer>, Optional<Integer>, Optional<Integer>>() {
            public Optional<Integer> call(List<Integer> v1, Optional<Integer> v2) throws Exception {

                Integer currentCount = 0;
                for(Integer i : v1){
                    currentCount += i;
                }
                Integer previoursCount = v2.orElse(0);
                Optional<Integer> optional = Optional.of(currentCount+previoursCount);
                return optional;
            }
        });

        re.print(100);

        jsc.start();
        try {
            jsc.awaitTermination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}