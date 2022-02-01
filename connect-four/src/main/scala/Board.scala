case class Board(board: Vector[Vector[String]]):
  def apply(row: Int, col: Int) = board(row)(col)
