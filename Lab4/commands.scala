// first command
val files = sc.wholeTextFiles("/tmp/*.txt").collect.toList 

// second command
var ch = "hello a b  c   bye"
ch.split("\\s+")
ch.split(" ")

// third command
var fruit = Array(("apple","2"), ("orange","3"), ("pear","1"))
fruit(2)._2

// fourth command
val a=Array((1,2),(3,4),(3,6))
val b=Array((3,9))
val A=sc.parallelize(a)
val B=sc.parallelize(b)
val J=A.join(B).collect
val L=A.leftOuterJoin(B).collect




