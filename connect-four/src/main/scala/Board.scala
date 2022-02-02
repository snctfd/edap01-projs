case class Board(board: Vector[Vector[Cell]]):

  lazy val boardTranspose = board.transpose
  val rows = board.length
  val cols = board(0).length

  def apply(row: Int, col: Int) = board(row)(col)

  def move(col: Int, player: Cell): Board =
    require(player != Cell.Empty)

    val firstEmpty = board.lastIndexWhere(xs => xs(col) == Cell.Empty)
    assert(firstEmpty != -1)
    Board(board.updated(firstEmpty, board(firstEmpty).updated(col, player)))

  lazy val getWinner: Option[Cell] = {
    import math.max
    val p1Win = Vector.fill(4)(Cell.PlayerOne)
    val p2Win = Vector.fill(4)(Cell.PlayerTwo)

    // check for horizontal win
    val horWin1 = board.exists(_.containsSlice(p1Win))
    val horWin2 = board.exists(_.containsSlice(p2Win))

    val verWin1 = boardTranspose.exists(_.containsSlice(p1Win))
    val verWin2 = boardTranspose.exists(_.containsSlice(p2Win))

    var diagWin1 = false;
    var diagWin2 = false;

    for i <- (0 to rows - 4) do
      for j <- (0 to cols - 4) do
        val diag1 = (0 until 4).map(x => board(i + x)(j + x))
        val diag2 = (0 until 4).map(x => board(i + x)(cols - (j + x + 1)))

        diagWin1 = diagWin1 || diag1 == p1Win || diag2 == p1Win
        diagWin2 = diagWin2 || diag1 == p2Win || diag2 == p2Win

    if horWin1 || verWin1 || diagWin1 then Some(Cell.PlayerOne)
    else if horWin2 || verWin2 || diagWin2 then Some(Cell.PlayerTwo)
    else None
  }

  override def toString: String = board.map(_.mkString("|", " ", "|")).mkString("\n")

object Board:
  def empty(rows: Int = 6, cols: Int = 7): Board =
    require(rows >= 4)
    require(cols >= 4)
    Board(Vector.fill(rows, cols)(Cell.Empty))

enum Cell(repr: String):
  override def toString: String = repr

  case Empty extends Cell("x")
  case PlayerOne extends Cell("1")
  case PlayerTwo extends Cell("2")