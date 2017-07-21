#!/usr/bin/env bash

base_dir=$(dirname $0)
spark-submit --class solutions.dataguru.spark.scala.Main --master local[2] $base_dir/../core/target/core-1.0.0-jar-with-dependencies.jar

