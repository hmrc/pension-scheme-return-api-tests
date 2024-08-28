import sbt.*

object Dependencies {

  val test: Seq[ModuleID] = Seq(
    "org.scalatest"       %% "scalatest"               % "3.2.19" % Test,
    "com.vladsch.flexmark" % "flexmark-all"            % "0.64.8" % Test,
    "com.typesafe"         % "config"                  % "1.4.3"  % Test,
    "org.playframework"   %% "play-ahc-ws-standalone"  % "3.0.5"  % Test,
    "org.slf4j"            % "slf4j-simple"            % "2.0.13" % Test,
    "org.playframework"   %% "play-ws-standalone-json" % "3.0.5"  % Test
  )
}
