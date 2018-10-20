name := "BlockChainApp"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= {
  val akkaV       = "2.4.6"
  val scalaTestV  = "2.2.5"
  Seq(
    "com.typesafe.akka"           %% "akka-actor"                           % akkaV,
    "com.typesafe.akka"           %% "akka-http-experimental"               % akkaV,
    "com.paulgoldbaum"            %% "scala-influxdb-client"                % "0.4.5",
    "ch.qos.logback"              % "logback-classic"                       % "1.1.3",
    "com.typesafe.scala-logging"  %% "scala-logging"                        % "3.1.0"
  )
}