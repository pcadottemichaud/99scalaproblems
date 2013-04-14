package ninetynineproblems

import scala.annotation.tailrec

object NinetyNineProblems {

  def last(l: List[Int]) = l.last

  def penultimate(l: List[Int]) = l.takeRight(2) head

  def nth(n: Int, l: List[Int]) = l(n)

  def length(l: List[Int]) = l length

  def reverse(l: List[Int]) = l reverse

  @tailrec
  def isPalindrome(l: List[Int]): Boolean = {
    if (l.length <= 1) true
    else if (l.head != l.last) false
    else isPalindrome(l.tail.take(l.length - 2))
  }

  def flatten(l: List[Any]): List[Int] = {
    def iter(l: List[Any], acc: List[Int]): List[Int] = l match {
      case Nil => acc
      case (i: Int) :: tail => iter(tail, acc :+ i)
      case (xs: List[Any]) :: tail => iter(tail, iter(xs, acc))
      case _ => acc
    }
    iter(l, List[Int]())
  }

  def compress(l: List[Symbol]): List[Symbol] = {
    @tailrec
    def iter(l: List[Symbol], acc: List[Symbol]): List[Symbol] = l match {
      case Nil => acc
      case (s: Symbol) :: tail =>
        iter(tail, if (acc.length > 0 && acc.last == s) acc else acc :+ s)
    }
    iter(l, List[Symbol]())
  }

  def listOf(s: Symbol, l: List[Symbol]): List[Symbol] = {
    @tailrec
    def iter(s: Symbol, l: List[Symbol], acc: List[Symbol]): List[Symbol] = l match {
      case Nil => acc :+ s
      case (head: Symbol) :: tail =>
        if (head != s) acc :+ s
        else iter(s, tail, acc :+ s)
    }
    iter(s, l, List[Symbol]())
  }

  @tailrec
  def next(s: Symbol, l: List[Symbol]): List[Symbol] = l match {
    case Nil => List[Symbol]()
    case (head: Symbol) :: tail =>
      if (head != s) l
      else next(s, tail)
  }

  def pack(l: List[Symbol]): List[Any] = {
    @tailrec
    def accumulate(l: List[Symbol], acc: List[Any]): List[Any] = l match {
      case Nil => acc
      case (s: Symbol) :: tail =>
        accumulate(next(s, tail), acc :+ listOf(s, tail))
    }
    accumulate(l, List[Any]())
  }

  def encode(l: List[Symbol]): List[Tuple2[Int, Symbol]] = {
    @tailrec
    def accumulate(l: List[Symbol], acc: List[Tuple2[Int, Symbol]]): List[Tuple2[Int, Symbol]] =
      l match {
      case Nil => acc
      case (head: Symbol) :: tail => {
        val seq = listOf(head, tail)
        accumulate(next(head, tail), acc :+ (seq length, head))
      }
    }
    accumulate(l, List[Tuple2[Int, Symbol]]())
  }
}
