//created by Dayou Du on Dec 8th, 2017

import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}
import org.apache.spark.mllib.linalg.Vectors

val data = sc.textFile("/user/dd2645/SparkInput/crime.csv")

val dataArray = data.map(s => s.split(','))

val model = KMeansModel.load(sc, "/user/dd2645/KMeansModel") 

case class Result (DataSourceId:Int, PredictionId:Int, Latitude:Double, Longtitude:Double, Score: Double)

val res = dataArray.map(a => Result(1, model.predict(Vectors.dense(a(3).toDouble,a(4).toDouble)),a(3).toDouble,a(4).toDouble,1)).toDF() 

res.coalesce(1).write.format("com.databricks.spark.csv").option("header","false").save("SparkOutput/outCrime")
