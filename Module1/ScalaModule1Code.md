# The Scala REPL
**Basics of Scala REPL**  
Difference between val and var    

| Val        | Var           |  
| ---|---|     
|Val is immutable i.e readonly | Var is mutable i.e. read-write  
|| Var have to be initialized at the time of declaration |

![alt text](/Images/ScalaREPL.JPG)  
![alt text](/Images/ValVar.JPG)   

**Scoping in Val**  
```
scala>:paste  
// Entering paste mode (ctrl-D to finish)

val x=10  
println(x)  

{  
  val x=20  
  println(x)  
}

println(x)

//Ctrl+D  
// Exiting paste mode, now interpreting.

10  
20  
10  
x: Int=10    
```
In a Scala program, an inner variable is said to shadow a like-named outer variable, because the outer variable becomes invisible in the inner scope.  

### Defining functions
```
scala> def add(x: Int, y: Int)=x+y   
add: (x: Int, y: Int)Int  

scala> def add(x: Int, y: Int):Int=x+y  
add: (x: Int, y: Int)Int  
```
Functions cannot infer types for parameters inherently. Hence needs to be specified.  

### If Expressions

```
scala> var defaultname="hello"  
defaultname: String = hello  

scala> val args:Array[String]=Array.empty  
args: Array[String] = Array()  

scala> var fileName="default.txt"  
fileName: String = default.txt  

scala> if (args.length>0) fileName=args(0)  

scala> fileName  
res14: String = default.txt  

scala> val newName=if(args.length>0) args(0) else "default.txt"  
newName: String = default.txt

```
In Scala, if-else expressions can return values. Scala places emphasis on expressions that return values than statements that do not. In nested scala expressions, the innermost expression becomes values of overall expression.

```
scala> val a=1
a: Int = 1

scala> val b=2
b: Int = 2

scala> val maxSquaredDouble = if(a>b){
     | val asquared=a*a
     | asquared*2
     | }else{
     | val bsquared=b*b
     | bsquared*2
     | }
maxSquaredDouble: Int = 8
```
### Try Catch Expressions
```
scala> val divided = try {
     |   10/0
     | } catch {
     |   case ae: ArithmeticException =>0
     | } finally {
     |   println("This always runs, but does not affect the result")
     |   42
     | }
<console>:17: warning: a pure expression does nothing in statement position
         42
         ^
<console>:17: warning: multiline expressions might require enclosing parentheses; a value can be silently discarded when Unit is expected
         42
         ^
This always runs, but does not affect the result
divided: Int = 0
```
The final block will always run but will not return anything  
If an exception is caught, a value may be returned to recover

### Looping
Scala has only one true looping construct : while
1. While has no return types
2. Replaced by *foreach* or *map* functions over collections or by *for* and *for...yield* blocks
3. while **must** have a side effect  
**Examples**

```  
scala> var x=0  
x: Int = 0  

scala> while (x<10) {  
     | println(s"the square of $x is ${x*x}")  
     | x+=1  
     | }  
the square of 0 is 0  
the square of 1 is 1  
the square of 2 is 4  
the square of 3 is 9  
the square of 4 is 16  
the square of 5 is 25  
the square of 6 is 36  
the square of 7 is 49  
the square of 8 is 64  
the square of 9 is 81  
```
do-while executes the body at least once and checks the predicate to see if it should repeat.  

```
scala> x=0
x: Int = 0

scala> do {
     | println(s"the square of $x is ${x*x}")
     | x+=1
     | } while (x<10)
the square of 0 is 0
the square of 1 is 1
the square of 2 is 4
the square of 3 is 9
the square of 4 is 16
the square of 5 is 25
the square of 6 is 36
the square of 7 is 49
the square of 8 is 64
the square of 9 is 81
```
**Note**  
Cmd to open scala code fileName     
```
more fileName.sc
```
Execute scala code in command line
```
scala fileName.sc
#Or
scala
>load fileName.sc
```
## Exercises
1. Create a Scala script file called timestable.sc in your working directory (make a directory if you need to). Write two while loops from 1 to 5, one inside the other. Call one loop variable x and the other y. println a message in the inner loop that says s"$x times $y is ${x * y}.   
Remember to increment both x and y in their respective loops. Either run your script using scala timestable.sc or sbt console then :load timestable.sc to check that it works. You should get 25 lines of output.
```
var x=0
var y=0
while (x<5){
  y=0
  while(y<5){
    println(s"$x times $y is ${x * y}")
    y += 1
  }
  x += 1
}
```
![alt text](/Images/Exercises/Exercise1.JPG)  

2. Extra credit: Alter your previous timestable.sc to only print out lines if the result of the multiplication contains either a '4' or a '6' digit in the number produced.

```
var x=0
var y=0
while (x<5){
  y=0
  while(y<5){
    var times = x*y
    if (times.toString.contains('4') || times.toString.contains('6'))
      println(s"$x times $y is $times")
    y += 1
  }
  x += 1
}

```
![alt text](/Images/Exercises/Module1ExtraCredit.JPG) 
