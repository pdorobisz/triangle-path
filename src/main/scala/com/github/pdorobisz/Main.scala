package com.github.pdorobisz

import com.github.pdorobisz.reader.DefaultInputReader
import com.github.pdorobisz.service.DefaultPathFinder

object Main extends App {
  val eitherPathOrError = new DefaultInputReader(Console.in).parseInput()
    .map(DefaultPathFinder.minPath)

  eitherPathOrError match {
    case Left(error) =>
      println(error)
      System.exit(1)
    case Right(path) =>
      val pathSum = path.sum
      val pathStr = path.mkString(" + ")
      println(s"Minimal path is: $pathStr = $pathSum")
  }
}
