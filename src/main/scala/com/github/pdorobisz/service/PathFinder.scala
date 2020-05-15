package com.github.pdorobisz.service

import com.github.pdorobisz.model.{Triangle, TriangleLeaf, TriangleNode}

trait PathFinder {
  def minPath(t: Triangle): Seq[Int]
}

object DefaultPathFinder extends PathFinder {
  override def minPath(t: Triangle): Seq[Int] = t match {
    case TriangleNode(value, left, right) => Seq(value)
    case TriangleLeaf(value) => Seq(value)
  }
}
