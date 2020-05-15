package com.github.pdorobisz.service

import com.github.pdorobisz.model.{RegularNode, RootNode, Triangle}

trait PathFinder {

  /**
   * Finds minimal path from root to leaf node
   *
   * @param leafNodes list of triangle's leaf nodes
   * @return list of node values (from root to leaf) in the best path
   */
  def minPath(leafNodes: List[Triangle]): List[Int]
}

object DefaultPathFinder extends PathFinder {

  override def minPath(leafNodes: List[Triangle]): List[Int] = {

    def bestMinPath(row: List[Triangle], bestChildPaths: List[(Int, List[Int])]): List[(Int, List[Int])] =
      if (bestChildPaths.isEmpty)
        row.collect { case n: RegularNode => (n.value, List(n.value)) }
      else
        bestChildPaths
          .sliding(2)
          .zip(row)
          .toList
          .collect { case (children, n: RegularNode) =>
            val (bestChildValue, bestChildPath) = children.minBy(_._1)
            (n.value + bestChildValue, n.value :: bestChildPath)
          }

    @scala.annotation.tailrec
    def findMinPath(currentRow: List[Triangle], bestChildPaths: List[(Int, List[Int])]): List[Int] = currentRow match {
      case RootNode(rootValue) :: Nil =>
        val bestChildPath = bestChildPaths
          .minByOption(_._1)
          .toList
          .flatMap(_._2)
        rootValue :: bestChildPath
      case _ =>
        val bestPaths = bestMinPath(currentRow, bestChildPaths)
        val parentRow = currentRow.collect { case n: RegularNode => n.rightParent.toList }.flatten
        findMinPath(parentRow, bestPaths)
    }

    findMinPath(leafNodes, Nil)
  }

}
