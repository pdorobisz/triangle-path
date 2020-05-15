package com.github.pdorobisz.reader

import com.github.pdorobisz.model.Triangle

import scala.io.StdIn

trait InputReader {

  /**
   * Reads from standard input and parses data.
   *
   * @return parsed triangle (list of leaf nodes) or error
   */

  def parseInput(): Either[String, Seq[Triangle]]
}

object DefaultInputReader extends InputReader {

  override def parseInput(): Either[String, Seq[Triangle]] = {
    Iterator
      .continually(StdIn.readLine())
      .takeWhile(!Option(_).forall(_.trim.isEmpty))
      .foreach(println)

    Right(Nil)
  }
}