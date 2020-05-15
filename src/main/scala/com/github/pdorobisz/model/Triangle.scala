package com.github.pdorobisz.model

sealed trait Triangle

case class TriangleNode(value: Int, left: Triangle, right: Triangle) extends Triangle

case class TriangleLeaf(value: Int) extends Triangle
