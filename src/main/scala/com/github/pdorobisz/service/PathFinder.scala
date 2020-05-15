package com.github.pdorobisz.service

import com.github.pdorobisz.model.Triangle

trait PathFinder {
  def minPath(t: Triangle): Seq[Triangle]
}

object DefaultPathFinder extends PathFinder {
  override def minPath(t: Triangle): Seq[Triangle] = Seq(t)
}
