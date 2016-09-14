 
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.Matrix
import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.mllib.linalg.distributed.RowMatrix
import org.apache.spark.mllib.linalg.SingularValueDecomposition
import java.util.Calendar 
 
 
  // Load and parse the data file.
  val rows = sc.textFile("file:///home/cloudera/tryme.txt").map { line =>
    val values = line.split(' ').map(_.toDouble)
    Vectors.dense(values)
} 

  val mat = new RowMatrix(rows)
val currentHour = Calendar.getInstance().get(Calendar.HOUR) 
val currentMinute = Calendar.getInstance().get(Calendar.MINUTE) 
{ 
  // Compute SVD
  val svd = mat.computeSVD(mat.numCols().toInt, computeU = true)
 }
