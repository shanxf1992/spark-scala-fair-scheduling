package solutions.dataguru.spark.scala

import org.apache.log4j.{Level, Logger, PropertyConfigurator}
import org.apache.spark.sql.SparkSession

object Main {

  private val logger = Logger.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {

    PropertyConfigurator.configure("config/log4j.properties")
    Logger.getLogger("org").setLevel(Level.WARN)
    Logger.getLogger("akka").setLevel(Level.WARN)
    Logger.getLogger("com.datastax").setLevel(Level.WARN)
    Logger.getLogger("kafka").setLevel(Level.WARN)

    logger.setLevel(Level.INFO)
    val spark = SparkSession
      .builder
      .appName("Spark and Scala Project")
      .config("spark.scheduler.mode", "FAIR")
      .config("spark.scheduler.allocation.file", "fairscheduler.xml")
      .getOrCreate()
    val sc = spark.sparkContext
    sc.setLocalProperty("spark.scheduler.pool", "pool1")


    val thread1 = new Thread {
      override def run: Unit = {
        sc.setLocalProperty("spark.scheduler.pool", "pool1")
        println("Thread 1 " + sc.parallelize(1 to 10000).count())
      }
    }

    thread1.start()

    val thread2 = new Thread {
      override def run: Unit = {
        sc.setLocalProperty("spark.scheduler.pool", "pool2")
        println("Thread 2 " + sc.parallelize(1 to 10000).count())
      }
    }

    thread2.start()
  }
}

