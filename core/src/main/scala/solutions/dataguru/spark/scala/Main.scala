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
      .getOrCreate()
    val sc = spark.sparkContext
    spark.stop()
  }
}

