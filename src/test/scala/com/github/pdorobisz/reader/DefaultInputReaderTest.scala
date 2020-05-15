package com.github.pdorobisz.reader

import java.io.{BufferedReader, StringReader}

import com.github.pdorobisz.model.{RegularNode, RootNode}
import org.scalatest.{FunSpec, Matchers}

class DefaultInputReaderTest extends FunSpec with Matchers {

  describe("Default input reader") {
    it("should correctly parse input") {
      val input =
        """7
          |6 3
          |3 8 5""".stripMargin
      val bufferedReader = new BufferedReader(new StringReader(input))
      val reader = new DefaultInputReader(bufferedReader)

      val expectedRoot = RootNode(7)
      val expectedNode1 = RegularNode(6, None, Some(expectedRoot))
      val expectedNode2 = RegularNode(3, Some(expectedRoot), None)
      val expectedLeaf1 = RegularNode(3, None, Some(expectedNode1))
      val expectedLeaf2 = RegularNode(8, Some(expectedNode1), Some(expectedNode2))
      val expectedLeaf3 = RegularNode(5, Some(expectedNode2), None)

      val result = reader.parseInput()
      result shouldBe Right(Seq(expectedLeaf1, expectedLeaf2, expectedLeaf3))
    }

    it("should correctly report an error") {
      val invalidValue = "xyz"
      val invalidLine = s"6 $invalidValue"

      val input =
        s"""7
           |$invalidLine
           |3 8 5""".stripMargin
      val reader = new DefaultInputReader(new BufferedReader(new StringReader(input)))
      val result = reader.parseInput()
      result shouldBe Left(s"Unable to parse $invalidValue, line: $invalidLine")
    }
  }
}
