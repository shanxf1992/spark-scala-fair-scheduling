package solutions.dataguru.spark.scala

import org.scalatest._

class FindUnitSpec extends FlatSpec with Matchers {

  it should "list access should return the value" in {
    val arr = List(1,2,3)
    assert(arr.head == 1)
  }
}