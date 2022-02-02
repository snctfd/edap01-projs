object ConnectFour:
  @main
  def main() =
    testWins()

  def testWins(): Unit =
    val empty = Board.empty()
    val p1 = Cell.PlayerOne
    val p2 = Cell.PlayerTwo

    val noWinner1 = empty.move(0, p1).move(0, p2).move(0, p1).move(0, p1)
    assert(noWinner1.getWinner == None)

    val p1WinHor = empty.move(0, p1).move(1, p1).move(2, p1).move(3, p1)
    val p2WinHor = empty.move(0, p2).move(1, p2).move(2, p2).move(3, p2)
    assert(p1WinHor.getWinner == Some(p1))
    assert(p2WinHor.getWinner == Some(p2))

    val p1WinVert = empty.move(0, p1).move(0, p1).move(0, p1).move(0, p1)
    val p2WinVert = empty.move(0, p2).move(0, p2).move(0, p2).move(0, p2)
    assert(p1WinVert.getWinner == Some(p1))
    assert(p2WinVert.getWinner == Some(p2))

    val p1WinDiag1 = empty
      .move(0, p2).move(0, p1).move(0, p2).move(0, p1)
      .move(1, p1).move(1, p2).move(1, p1)
      .move(2, p2).move(2, p1)
      .move(3, p1)

    val p1WinDiag2 = empty
      .move(0, p1).move(1, p2).move(2, p1).move(3, p2)
      .move(1, p1).move(2, p2).move(3, p1)
      .move(2, p1).move(3, p2)
      .move(3, p1)

    val p1WinDiag3 = empty
      .move(3, p2).move(3, p1).move(3, p2).move(3, p1)
      .move(4, p1).move(4, p2).move(4, p1)
      .move(5, p2).move(5, p1)
      .move(6, p1)

    val p1WinDiag4 = empty
      .move(3, p1).move(4, p2).move(5, p1).move(6, p2)
      .move(4, p1).move(5, p2).move(6, p1)
      .move(5, p1).move(6, p2)
      .move(6, p1)

    val p1WinDiag5 = empty
      .move(3, p2).move(4, p1).move(5, p2).move(6, p1)
      .move(3, p1).move(4, p2).move(5, p1).move(6, p2)
      .move(3, p2).move(3, p1).move(3, p2).move(3, p1)
      .move(4, p2).move(4, p2).move(4, p1)
      .move(5, p1).move(5, p1)
      .move(6, p1)

    println(s"$p1WinDiag1\n")
    println(s"$p1WinDiag2\n")
    println(s"$p1WinDiag3\n")
    println(s"$p1WinDiag4\n")
    println(s"$p1WinDiag5\n")
    assert(p1WinDiag1.getWinner == Some(p1))
    assert(p1WinDiag2.getWinner == Some(p1))
    assert(p1WinDiag3.getWinner == Some(p1))
    assert(p1WinDiag4.getWinner == Some(p1))
    assert(p1WinDiag5.getWinner == Some(p1))

    println("testWins successful")
