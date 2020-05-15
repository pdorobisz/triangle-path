package com.github.pdorobisz.reader

import cats.implicits._
import com.github.pdorobisz.model.{RegularNode, RootNode, Triangle}

import scala.collection.mutable.ListBuffer

private[reader] object LineParser {

  def parse(line: String, previousRow: Seq[Triangle]): Either[String, Seq[Triangle]] = {
    def parseValue(s: String): Either[String, Int] = s.toIntOption match {
      case Some(v) => Right(v)
      case None => Left(s"Unable to parse $s, line: $line")
    }

    val parsed = line
      .split(" ")
      .toList
      .filter(_.nonEmpty)
      .traverse(parseValue)

    parsed.flatMap {
      case Nil =>
        Left("An empty line has occurred")
      case values if values.size != previousRow.size + 1 =>
        Left(s"Number of nodes in the current row doesn't match nodes in the previous row, line: $line")
      case v :: Nil =>
        Right(Seq(RootNode(v)))
      case values =>
        val transformedPreviousRow = ListBuffer(Option.empty[Triangle])
          .appendAll(previousRow.map(Option.apply))
          .append(None)
          .toList
        val parsedRow = transformedPreviousRow
          .sliding(2, 1)
          .zip(values)
          .map { case (left :: right :: Nil, v) => RegularNode(v, left, right) }
          .toList
        Right(parsedRow)
    }
  }
}
