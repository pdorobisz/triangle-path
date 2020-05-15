package com.github.pdorobisz.service

import com.github.pdorobisz.model.{RegularNode, RootNode}
import org.scalatest.{FunSpec, Matchers}

class DefaultPathFinderTest extends FunSpec with Matchers {

  describe("Default path finder") {
    describe("when finding min path") {
      it("should correctly handle empty list") {
        DefaultPathFinder.minPath(Nil) shouldBe empty
      }

      it("should return correct result for a triangle with single node") {
        val input = RootNode(123)
        val result = DefaultPathFinder.minPath(List(input))
        result shouldBe Seq(input.value)
      }

      it("should return correct result for a triangle with 2 level") {
        val root = RootNode(1)
        val leaf1 = RegularNode(3, None, Some(root))
        val leaf2 = RegularNode(2, Some(root), None)

        val result = DefaultPathFinder.minPath(List(leaf1, leaf2))
        result shouldBe Seq(root.value, leaf2.value)
      }

      it("should return correct result for a triangle with 3 levels") {
        val root = RootNode(7)
        val node1 = RegularNode(6, None, Some(root))
        val node2 = RegularNode(3, Some(root), None)

        val leaf1 = RegularNode(3, None, Some(node1))
        val leaf2 = RegularNode(8, Some(node1), Some(node2))
        val leaf3 = RegularNode(5, Some(node2), None)

        val result = DefaultPathFinder.minPath(List(leaf1, leaf2, leaf3))
        result shouldBe Seq(root.value, node2.value, leaf3.value)
      }
    }
  }
}
