package com.github.pdorobisz.service

import com.github.pdorobisz.model.{RegularNode, RootNode, Triangle}

trait PathFinder {
  def minPath(t: Triangle): Seq[Int]
}

object DefaultPathFinder extends PathFinder {
  override def minPath(t: Triangle): Seq[Int] = t match {
    case RootNode(value) => Seq(value)
    case RegularNode(value, leftParent, rightParent) => Seq(value)
  }

}
