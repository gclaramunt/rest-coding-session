name := "service-coding-session"

version:= "0.1"

seq(Revolver.settings: _*)

libraryDependencies ++= Seq(
  "io.spray" %% "spray-io" % "1.3.2",
  "io.spray" %% "spray-http" % "1.3.2",
  "io.spray" %% "spray-httpx" % "1.3.2",
  "io.spray" %% "spray-util" % "1.3.2",
  "io.spray" %% "spray-can" % "1.3.2",
  "io.spray" %% "spray-routing" % "1.3.2",
  "io.spray" %%  "spray-json" % "1.3.2",
  "com.typesafe.akka" %% "akka-actor" % "2.3.12"
)

