package com.github.pdorobisz.reader

import java.io.BufferedReader

import com.github.pdorobisz.model.Triangle

trait InputReader {

  /**
   * Reads from input and parses data.
   *
   * @return parsed triangle (list of leaf nodes) or error
   */

  def parseInput(): Either[String, Seq[Triangle]]
}

class DefaultInputReader(in: BufferedReader) extends InputReader {

  override def parseInput(): Either[String, Seq[Triangle]] =
    Iterator
      .continually(in.readLine())
      .takeWhile(!Option(_).forall(_.trim.isEmpty))
      .foldLeft(Right(List.empty[Triangle]): Either[String, Seq[Triangle]]) {
        case (Right(previousRow), line) => LineParser.parse(line, previousRow)
        case (left@Left(_), _) => left
      }
}
