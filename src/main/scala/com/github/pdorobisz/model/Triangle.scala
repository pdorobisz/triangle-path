package com.github.pdorobisz.model

sealed trait Triangle

case class RootNode(value: Int) extends Triangle

case class RegularNode(value: Int, leftParent: Option[Triangle], rightParent: Option[Triangle]) extends Triangle
