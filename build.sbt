//~~~> Common info
ThisBuild / scalaVersion     := "2.13.11"
ThisBuild / version          := "1.0.0"
ThisBuild / organization     := "com.uet"
ThisBuild / organizationName := "storm.spirit"

//~~~> Root module
lazy val root = (project in file("."))
  .settings(
    name := "scala-activej",
    libraryDependencies ++= Seq(
      "ch.qos.logback" % "logback-classic" % "1.4.8",
      "io.activej" % "activej-launchers-http" % "5.5",
      "com.typesafe.play" %% "play-json" % "2.9.4",
      "com.typesafe.slick" %% "slick" % "3.4.1",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.4.1",
      "mysql" % "mysql-connector-java" % "8.0.33",
      "org.liquibase" % "liquibase-core" % "4.20.0"
    )
  )
