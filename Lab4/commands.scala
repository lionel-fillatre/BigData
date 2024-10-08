// import the library to run linux shell commands inside the spark shell
import scala.sys.process._
//
// for example, you can run the following linux shell commands: 
"ls -l /opt/spark/work-dir/lab4".!
// 
// Print the content of this file
print("\nContent of commands.scala\n-----------------------\n")
"cat /opt/spark/work-dir/lab4/commands.scala".!
print("End of commands.scala\n-----------------------\n\n")
//
// Print the text files
print("\nContent of the three text files\n-----------------------\n")
"cat /opt/spark/work-dir/lab4/color.txt".!
"cat /opt/spark/work-dir/lab4/fruit.txt".!
"cat /opt/spark/work-dir/lab4/vegetable.txt".!
//
// Run some commands
print("\nRun some relevant commands\n-----------------------\n")
// first command
val files = sc.wholeTextFiles("/opt/spark/work-dir/lab4/*.txt").collect.toList 
//
// second command
var ch = "hello a b  c   bye"
ch.split("\\s+")
ch.split(" ")
// third command
var fruit = Array(("apple","2"), ("orange","3"), ("pear","1"))
fruit(2)._2
//
// fourth command
val a=Array((1,2),(3,4),(3,6))
val b=Array((3,9))
val A=sc.parallelize(a)
val B=sc.parallelize(b)
val J=A.join(B).collect
val L=A.leftOuterJoin(B).collect


