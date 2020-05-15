package com.github.pdorobisz.service

import com.github.pdorobisz.model.{RegularNode, RootNode, Triangle}

trait PathFinder {
  def minPath(leafLayer: List[Triangle]): List[Int]
}

object DefaultPathFinder extends PathFinder {
  override def minPath(leafLayer: List[Triangle]): List[Int] = {
    @scala.annotation.tailrec
    def findMinPath(currentLayer: List[Triangle], bestSubtriangles: List[(Int, List[Int])]): List[Int] =
      currentLayer match {
        case (r: RootNode) :: Nil =>
          if (bestSubtriangles.isEmpty) List(r.value)
          else {
            val bestChild = bestSubtriangles.minBy(_._1)
            r.value :: bestChild._2
          }
        case childrenLayer: List[RegularNode] =>
          val bestPath: List[(Int, List[Int])] = bestSubtriangles.sliding(2).zip(childrenLayer).map { case (children, n) =>
            val bestChild = children.minBy(_._1)
            (n.value + bestChild._1, n.value :: bestChild._2)
          }.toList
          val parentLayer = childrenLayer.flatMap(_.rightParent)
          findMinPath(parentLayer, bestPath)
      }

    leafLayer match {
      case (r: RootNode) :: Nil => List(r.value)
      case list: List[RegularNode] =>
        val values = list.map(n => (n.value, List(n.value)))
        val parentLayer = list.flatMap(_.rightParent)

        findMinPath(parentLayer, values)
    }
  }

}
