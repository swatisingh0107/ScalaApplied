class  Rational private(val n: Int, val d: Int) {
  //Auxillary constructor
  // def this(i: Int) = this(i, 1)

  override def toString: String = s"R($n/$d)"

  def min(other:Rational):Rational=
    if((n.toDouble/d)<(other.n.toDouble/other.d))
      this else other

  def +(other: Rational): Rational =
    new Rational(
      this.n * other.d + this.d * other.n,
      this.d * other.d
    )
}


object Rational {
  def apply(n: Int, d: Int): Rational =
    new Rational(n, d)
  def apply(i: Int): Rational =
    new Rational(i, 1)
}
val fifth = Rational(1, 5) // can drop the new
val five = Rational(5)
val sum = five + fifth