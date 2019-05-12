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
