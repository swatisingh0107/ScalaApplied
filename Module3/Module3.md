# Module 3
## A Scala Class Definition
* On the JVM, all methods/fields must go inside classes (unlike the REPL)
```
class DemoWithFieldsAndMethods {
  val x: Int = 10
  val y: Int = x * 2
  def timesY(a: Int): Int = a * y
}
class DemoWithParams(name: String) {
  println(s"Constructing for $name")
  def sayHi(times: Int): Unit = {
    var time = 0
    while (time < times) {
        println(s"Hi, $name")
        time += 1
      }       
    }
}
```

## Constructor

```
class DemoWithParams(name: String) {
  println(s"Constructing for $name")
  // rest of class...
}
```
* Parameters on the class definition become primary constructor parameters
* Code in the class (not in defs) becomes the primary constructor code, runs when a new instance is constructed

```
val demo = new DemoWithParams("Jill")
demo.name
// Error:(33, 83) value name is not a member of DemoWithParams
```
* Constructor parameters are private, also vals
* private and protected are keywords, there is no public keyword, that's the default for vals and defs (but not for constructor parameters)
* Adding a val keyword before the parameter definition makes it a public parametric field:

```
class DemoWithParams(val name: String) {
  println(s"Constructing for $name")
}
val demo = new DemoWithParams("Jill")
demo.name // Jill
```
* Parametric fields are idiomatic Scala
```
// how Scala re-writes the above:
class DemoWithParams(_name: String) { // parameter still private[this]
  val name: String = _name // the public field definition
  println(s"constructing for $name")
}
```
### A Rational class

```
class rational(val n:Int, val d:Int)// no body!

val half-new Rational(1,2)
//half:Rational=Rational@6c643605
```

Every class has a toString method that can be overridden:
```
class Rational(val n: Int, val d: Int){
  override def toString:String = s"R($n/$d)"
}
val half=Rational(1,2)
//half: Rational= R(1/2)
}
```

### Checking preconditions in a Constructor
* If you use require and the predicate fails, you will get an
IllegalArgumentException thrown
* The String field in require is optional but recommended

```
class Rational(val n: Int, val d: Int) {
  require(d != 0, "Zero denominator!") // precondition
  override def toString: String = s"R($n/$d)"
}

val half = new Rational(1, 2)
// half: Rational = R(1/2)

val divByZero = new Rational(1, 0)
// java.lang.IllegalArgumentException: requirement failed: Zero denominator!
```
### Referencing Self
* Could also write require(this.d != 0,"Zero denominator!")
* *this* is a reference to the current instance.  

```
class Rational(val n: Int, val d: Int) {
  require(d != 0, "Zero denominator!") // precondition
  override def toString: String = s"R($n/$d)"
//Method to return the minimum of the two rational numbers
  def min(other: Rational): Rational =
  if ((n.toDouble / d) < (other.n.toDouble / other.d))
  this else other // have to use this to return
}

val half = new Rational(1, 2)

val fifth = new Rational(1, 5)

val smaller = fifth min half
// smaller: Rational = R(1/5)
```

### Infix and Symbolic Methods    

```
class Rational(val n: Int, val d: Int) {
  require(d != 0, "Zero denominator!")
  override def toString: String = s"R($n/$d)"
  // rational addition
  def add(other: Rational): Rational =
  new Rational(
    this.n * other.d + this.d * other.n,
    this.d * other.d
  )
}
val half = new Rational(1, 2)

val fifth = new Rational(1, 5)

val sum = half add fifth
// sum: Rational = R(7/10)
```
* Scala doesn't have operator overloading, per-se
* But it does have symbolic method names, (and operator precedence rules for first character)
[Infix operators](https://www.scala-lang.org/files/archive/spec/2.11/06-expressions.html#infix-operations)

```
class Rational(val n: Int, val d: Int) {
  require(d != 0, "Zero denominator!")
  override def toString: String = s"R($n/$d)"
  // symbolic rational addition
  def +(other: Rational): Rational =
  new Rational(
    this.n * other.d + this.d * other.n,
    this.d * other.d
    )
}

val half = new Rational(1, 2)

val fifth = new Rational(1, 5)

val sum = half + fifth
// sum: Rational = R(7/10)
```

Change add to + and infix does the rest

### Adding an Int to a Rational
* Strategy 1: Construct a rational from an Int by using **Auxillary constructor**

```
class Rational(val n: Int, val d: Int) {
//Auxillary constructor
  def this(i: Int) = this(i, 1)
  override def toString: String = s"R($n/$d)"
  def +(other: Rational): Rational =
    new Rational(
      this.n * other.d + this.d * other.n,
      this.d * other.d
    )
}

val fifth = new Rational(1, 5)
//fifth: Rational = R(1/5)


val five = new Rational(5)
//five: Rational = R(5/1)
val sum = five + fifth
//sum: Rational = R(26/5)
```

**Introducing Companion Objects**  
An object in the same source file with the same name as the class

```
class  Rational (val n: Int, val d: Int) {
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
//Notice the apply method here
val fifth = Rational.apply(1, 5) // can drop the new
val five = Rational.apply(5)
val sum = five + fifth
```
We can make the constructor privaye and use the factory methods (apply) here  
```
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
//No need to mention factory method. It's implicit.
val fifth = Rational(1, 5) // can drop the new
val five = Rational(5)
val sum = five + fifth
```
* Strategy 2
**Overloading**
```
class Rational private (val n: Int, val d: Int) {
  require(d != 0, "Zero denominator!")
  override def toString: String = s"R($n/$d)"
  def +(other: Rational): Rational =
  new Rational(
    this.n * other.d + this.d * other.n,
    this.d * other.d
    )
    def +(i: Int): Rational =
  this + Rational(i) // from companion
}
object Rational {
  def apply(n: Int, d: Int): Rational =
  new Rational(n, d)
  def apply(i: Int): Rational =
  new Rational(i, 1)
}
val half=Rational(1,2)
Rational(1, 2) + 5 // R(11/2)
```
Can do half + 5 and Rational(5) + half but not 5 + half... implicit!

### Implicit Conversion
* For implicit conversion, must import language.implicitConversions to avoid warning
* No longer need the overloaded + method for Int
* Implicits used by Scala to solve type problems
* Implicit conversion has single "in" type and single "out" type
* Companion objects for types involved in type problem are one of the places Scala looks for implicits
* Name does not matter to Scala, only types matter
* Implicits can also be used for val, object and class

```
class Rational private (val n: Int, val d: Int) {
  require(d != 0, "Zero denominator!")

  override def toString: String = s"R($n/$d)"

  def +(other: Rational): Rational =
    new Rational(
      this.n * other.d + this.d * other.n,
      this.d * other.d
    )
}
//defined class Rational
object Rational {
  def apply(n: Int, d: Int): Rational =
    new Rational(n, d)

  implicit def apply(i: Int): Rational =
    new Rational(i, 1)
}
//defined object Rational

val half = Rational(1, 2)
//half: Rational = R(1/2)

half + 5
//res0: Rational = R(11/2)

Rational(5) + half
//res1: Rational = R(11/2)

5 + half
res2: Rational = R(11/2)
```
## Module3 Exercise

![alt text](/Images/Module3/Module3Exercise.JPG)  

**Answer Code**
```

/* Copyright (C) 2010-2018 Escalate Software, LLC. All rights reserved. */

package koans
import org.scalatest.Matchers
import support.BlankValues._
import support.KoanSuite
import org.scalatest.SeveredStackTraces

// Note, in the following exercise, to get the tests to even compile we had to
// comment out the test code. You will need to uncomment it first, then write
// the classes necessary to get it to compile, and then get the tests to pass.

class Module03 extends KoanSuite with Matchers with SeveredStackTraces {

  // Flight 03 exercise - uncomment the tests below, and then define a class
  // called ComplexNum that:
  // 1. Has class parameters real and imaginary
  // 2. an auxilliary constructor that takes just a real double and creates a
  //    complex number with a 0 imaginary value
  // 3. has an overridden toString method that prints out the number in the form
  //    "<real> + <imaginary>i", e.g. 2.0 + 8.1i
  // 4. has a + method that creates a new complex number with a real
  //    part containing the sum of the real parts, and an imaginary part containing
  //    the sum of the imaginary parts
  // 5. has a second overloaded + method that creates a new complex number by taking
  //    a double and adding the double to the real part
  //
  // The tests below should exercise all of these requirements although they
  // are far from exhaustive

  // UNCOMMENT BELOW
  //test ("Create a new complex number with double values and check those values")
  class ComplexNum(r: Double, i: Double){
    val real:Double=r
    val imaginary:Double=i

//test ("Create a complex number from a real number, imaginary part should be 0")
    def this(r:Double)=this(r,0.0)

//test ("Complex number should be printed in the form R.R + I.Ii")
    //override def toString: String = s"$r + $i"+"i"
//Extra credit: test ("Format for negative imaginary part should be R.R - I.Ii")
    override def toString: String = if (i>0.0)s"$r + $i"+"i" else
    s"$r - "+ math.abs(i)+"i"


    def +(other:ComplexNum): ComplexNum =
    new ComplexNum(
      this.real+other.real,
      this.imaginary + other.imaginary
    )
  }
  //test ("Add a complex to a double")
  object ComplexNum {
    def apply(r: Double, i: Double): ComplexNum =
      new ComplexNum(r, i)

    implicit def apply(r: Double): ComplexNum =
      new ComplexNum(r, 0.0)
  }

  test ("Create a new Complex number and check the values for the real/imaginary parts") {
    val complex = new ComplexNum(4, 2)

    complex.real should be (4)
    complex.imaginary should be (2)
  }

 test ("Create a new complex number with double values and check those values") {
    val complex = new ComplexNum(6.2, -1.5)

    complex.real should be (6.2)
    complex.imaginary should be (-1.5)
  }

  test ("Create a complex number from a real number, imaginary part should be 0") {
    val complex = new ComplexNum(-3.2)

    complex.real should be (-3.2)
    complex.imaginary should be (0)
  }

  test ("Complex number should be printed in the form R.R + I.Ii") {
    val complex = new ComplexNum(6, 3)
    val complex2 = new ComplexNum(5.4, 7.8)

    complex.toString should be ("6.0 + 3.0i")
    complex2.toString should be ("5.4 + 7.8i")
  }

  test ("Adding complex numbers") {
    val complex = new ComplexNum(6, 3)
    val complex2 = new ComplexNum(5.4, 7.8)
    val complex3 = complex + complex2

    complex3.real should be (11.4)
    complex3.imaginary should be (10.8)
  }

  test ("Adding complex number to a double") {
    val complex = new ComplexNum(6.5, 3.2) + 5.5

    complex.real should be (12)
    complex.imaginary should be (3.2)
  }

  // Extra credit - numbers with a negative imaginary part should be output
  // as 6.0 - 5.0i instead of 6.0 + -5.0i - if you have time, write a new test
  // for this outcome, and then adapt the toString in the class to work correctly
  // Hint: scala.math.abs will give the absolute value of a double

  // UNCOMMENT BELOW
   test ("Format for negative imaginary part should be R.R - I.Ii") {
    val complex = new ComplexNum(5, -6)
    val complex2 = new ComplexNum(5.5, -6.6)

    complex.toString should be ("5.0 - 6.0i")
    complex2.toString should be ("5.5 - 6.6i")
  }

  // Extra extra credit - adding a double to a complex works, but adding a complex
  // to a double does not. If you add an implicit conversion you can make this work
  // if you have time, write a test, and add an implicit to make it work

  // UNCOMMENT BELOW
   test ("Add a complex to a double") {
    val complex = 5.6 + new ComplexNum(3.4, 4.5)

    complex.real should be (9)
    complex.imaginary should be (4.5)
  }


  // Extra, extra, extra credit, add a companion object and factory methods,
  // move the implicit conversion to the factory method from Double, and
  // re-write the tests above to make them work
}

```
## Quiz

![alt text](/Images/Module3/Quiz3.JPG)
