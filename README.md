## Hadoop Group Project:

Created by Shiyao Lei, Dayou Du, Hao Chen at New York University

### Introduction

Neighborhood quality has always been difficult to quantify. Tranditionally, 
neighborhood quality are measured by using the data from housing survey and 
urban development cusyomer satisfication survey. In this paper, we analyze 
the neighborhood quality and livibility based on three data source which are 
311 Service Requests, NYPD Complaint Data Historic and geolocation based twitter 
sentiment analysis. Investifations of neighborhood quality often relate to 
various indicators. Combining these three data with geological information 
and foucusing mainly in three indicators which are safety, environment, and 
residents' happiness level.

### Usage

The project contains four parts of codes:

#### DataCollectParse: ($PROJECTROOT)/DataCollectParse

##### TwitterCollector:
TwitterCollector is a tool used to collect tweets through Twitter Streamline API. 
Specifically, only tweets posted at NYC with geolocation info will be collected. 

**How to run?**
The jar file is included in the TwitterCollector/artifact. 
To run the collector, simply type `java -jar filename.jar`


##### 311Parser:

Describe the usage here(@Shiyao Lei)

##### NYCCrimeParser:

NYCCrimeParser parse and wash the raw NYCCrime Data. Select the fields that we are interested in.

**How to run?**
Compile and run `NYCCrimeParser.java`, `NYCCrimeMapper.java` using Hadoop.

#### SingleSourceAnalytic: ($PROJECTROOT)/SingleSourceAnalytic

##### TwitterSentiment:

TwitterSentiment contains two parts:
1. Build java version of the python package VADER, and create a UDF in Hive.

   `cd ($PROJECTROOT)/SingleSourceAnalytic/TwitterSentiment`

   `./compileAndPack.sh`

   Then the .jar package which contains sentiment module will be on HDFS 

2. A Hive SQL command file that perform sentiment analytic on tweets, and calcualte the
  normalized average happyness polarity on differenct workdays an different day hours.
  It also create a table which contains geolocation and sentiment score for further use.

   Run `($PROJECTROOT)/SingleSourceAnalytic/TwitterSentiment/sentiment_hive_command.sql` using Hive.

   Note: to use the hive commands, please first run 

##### NYCCrimeAnalyzer

Analyze the relationship between the amount of NYC Crime complaints and 
the specific day of one week or time in one day. 

**How to run?**
Run `($PROJECTROOT)/SingleSourceAnalytic/NYCCrimeAnalyzer/nyccrime_hive_command.sql` using Hive.

##### 311Analyzer(@Shiyao Lei)

Analyze the relationship between the amount of 311Complaints and the specific day of one week, the time in one day and the location.

#### CombineAnalytic: ($PROJECTROOT)/CombineAnalytic

##### TwitterClustering:

TwitterKmeans.scala includes codes that train model for tweets clusting based on geolocation info.

**How to run?**
Copy the code to the spark-shell.

##### ScoreGenerator
This file include the code used to generated the final score for each clustering area.

**How to run?**
Compile and run `.java` file. (Need import *Apache Commons Mathematics Library*)



#### dateUDFs : ($PROJECTROOT)/DateUDFs

Contains the common date related hive UDF functions.

**How to run?**
`cd ($PROJECTROOT)/DateUDFs`
`./compileAndPack.sh`

### CREDITS

The java version of VADER is based on Nuno A. C. Henriques's project [nunoachenriques.net]

And also based on Hutto's original Python project VADER
@see <a href="http://comp.social.gatech.edu/papers/icwsm14.vader.hutto.pdf">VADER:
A Parsimonious Rule-based Model for Sentiment Analysis of Social Media Text</a>

