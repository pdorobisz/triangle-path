package com.github.pdorobisz.reader

import com.github.pdorobisz.model.{RegularNode, RootNode}
import org.scalatest.{FunSpec, Matchers}

class LineParserTest extends FunSpec with Matchers {

  describe("Line parser") {

    it("should return an error when empty line") {
      LineParser.parse("", Nil) shouldBe Left("An empty line has occurred")
    }

    it("should return an error when line contains white spaces only") {
      LineParser.parse("   ", Nil) shouldBe Left("An empty line has occurred")
    }

    it("should return an error when unable to parse first row") {
      val input = "42xyz"
      LineParser.parse(input, Nil) shouldBe Left(s"Unable to parse $input, line: $input")
    }

    it("should return an error when first line contains multiple values") {
      val input = "4 2"
      val result = LineParser.parse(input, Nil)
      result shouldBe Left(s"Number of nodes in the current row doesn't match nodes in the previous row, line: $input")
    }

    it("should correctly parse first row") {
      val result = LineParser.parse("42", Nil)
      result shouldBe Right(Seq(RootNode(42)))
    }

    it("should correctly parse row when previous row was already parsed") {
      val root = RootNode(7)
      val node1 = RegularNode(6, None, Some(root))
      val node2 = RegularNode(3, Some(root), None)
      val previousRow = Seq(node1, node2)

      val expected1 = RegularNode(3, None, Some(node1))
      val expected2 = RegularNode(8, Some(node1), Some(node2))
      val expected3 = RegularNode(5, Some(node2), None)

      val result = LineParser.parse("3 8 5", previousRow)
      result shouldBe Right(Seq(expected1, expected2, expected3))
    }

    it("should return an error when unable to parse non-first row") {
      val invalidValue = "abc"
      val input = s"6 $invalidValue"
      val result = LineParser.parse(input, Seq(RootNode(7)))
      result shouldBe Left(s"Unable to parse $invalidValue, line: $input")
    }

    it("should return an error when number of values doesn't match previous row") {
      val input = "1 2 3"
      val result = LineParser.parse(input, Seq(RootNode(7)))
      result shouldBe Left(s"Number of nodes in the current row doesn't match nodes in the previous row, line: $input")
    }
  }
}
