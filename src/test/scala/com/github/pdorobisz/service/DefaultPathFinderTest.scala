package com.github.pdorobisz.service

import com.github.pdorobisz.model.TriangleLeaf
import org.scalatest.{FunSpec, Matchers}

class DefaultPathFinderTest extends FunSpec with Matchers {

  describe("Default path finder") {
    describe("when finding min path") {
      it("should return correct result for a triangle with one node only") {
        val input = TriangleLeaf(123)
        val result = DefaultPathFinder.minPath(input)
        result shouldBe Seq(input)
      }
    }
  }
}
