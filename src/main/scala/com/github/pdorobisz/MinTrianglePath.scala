package com.github.pdorobisz

import com.github.pdorobisz.reader.DefaultInputReader
import com.github.pdorobisz.service.DefaultPathFinder

object MinTrianglePath extends App {

  private val inputReader = new DefaultInputReader(Console.in)
  private val resultOrError = inputReader.parseInput().map(DefaultPathFinder.minPath)

  resultOrError match {
    case Left(error) =>
      println(error)
      System.exit(1)
    case Right(path) =>
      val pathSum = path.sum
      val pathStr = path.mkString(" + ")
      println(s"Minimal path is: $pathStr = $pathSum")
  }
}
