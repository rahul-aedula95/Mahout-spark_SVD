 
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.Matrix
import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.mllib.linalg.distributed.RowMatrix
import org.apache.spark.mllib.linalg.SingularValueDecomposition
 
 
 
  // Load and parse the data file.
  val rows = sc.textFile("file:///home/cloudera/testnah.txt").map { line =>
    val values = line.split(' ').map(_.toDouble)
    Vectors.dense(values)
} 
  val mat = new RowMatrix(rows)
 
  // Compute SVD
  val svd = mat.computeSVD(mat.numCols().toInt, computeU = true)
  val U: RowMatrix = svd.U
  val s: Vector = svd.s
  val V: Matrix = svd.V
 
  println("Left Singular vectors :")
  U.rows.foreach(println)
 
  println("Singular values are :")
  println(s)
 
  println("Right Singular vectors :")
  println(V)
 
