package sparkRDD

import org.apache.spark.Partitioner

class Pa(numP: Int) extends Partitioner {
    override def numPartitions: Int = numP

    override def getPartition(key: Any): Int = {
        val i: Int = key.hashCode() % numP
        i match {
            case 0 => 0
            case 2 => 2
            case 1 => 1
            case _ => 3
        }
    }
}
