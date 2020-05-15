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

    @scala.annotation.tailrec
    def findMinPath(currentRow: List[Triangle], bestChildPaths: List[(Int, List[Int])]): List[Int] = currentRow match {
      case RootNode(rootValue) :: Nil =>
        val bestChildPath = bestChildPaths
          .minByOption(_._1)
          .toList
          .flatMap(_._2)
        rootValue :: bestChildPath
      case regularNodesRow: List[RegularNode] if bestChildPaths.isEmpty =>
        val bestPaths = regularNodesRow.map(n => (n.value, List(n.value)))
        val parentRow = regularNodesRow.flatMap(_.rightParent)
        findMinPath(parentRow, bestPaths)
      case regularNodesRow: List[RegularNode] =>
        val bestPaths = bestChildPaths
          .sliding(2)
          .zip(regularNodesRow)
          .toList
          .map { case (children, node) =>
            val (bestChildValue, bestChildPath) = children.minBy(_._1)
            (node.value + bestChildValue, node.value :: bestChildPath)
          }
        val parentRow = regularNodesRow.flatMap(_.rightParent)
        findMinPath(parentRow, bestPaths)
    }

    findMinPath(leafNodes, Nil)
  }

}
