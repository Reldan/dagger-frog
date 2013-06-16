addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.9.0")

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.4.0")

resolvers += Resolver.url("artifactory", url("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)

resolvers += "Sonatype snapshot" at "https://oss.sonatype.org/content/repositories/snapshots"
