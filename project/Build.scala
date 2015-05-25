import sbt._
import Keys._
import play.Project._
import com.typesafe.sbteclipse.core.EclipsePlugin.EclipseKeys
import com.github.play2war.plugin._

object ApplicationBuild extends Build {

    val appName         = "tutorial"
    val appVersion      = "0.1-SNAPSHOT"
    val groupId 	= "pt.ubiquity"
    
    val publishURL 		= Some("Ubiquity Nexus Repository Manager" at "url here")   
    
    var pomInfo = (  
          <developers>
	 	    <developer>
	              <id>nelsonf</id>
	              <name>Nelson Ferreira</name>
	              <email>nelson.ferreira@ubiquity.pt</email>      
	              <roles>
	                <role>developer</role>
	                <role>architect</role>
	              </roles>
	              <timezone>0</timezone>
	            </developer>
         </developers>
    )
    
    override def settings = super.settings ++ Seq(
		EclipseKeys.skipParents in ThisBuild := false
	)
	
    val repositories = Seq(
	  	"Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository",
	 	"maven repo1" at "http://repo1.maven.org/maven2/",
		"sonatype-snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
	 	"sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases",
	  	"java-net" at "http://download.java.net/maven/2",
	  	"Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
	  	"wordnik Repository" at "http://repo1.maven.org/maven2/com/wordnik/",
	  	"mygrid Repository" at "http://build.mygrid.org.uk/maven/repository",
	  	"Spring Maven Repository" at "http://repo.springsource.org/libs-release",
	  	"Spring Maven MILESTONE Repository" at "http://repo.springsource.org/libs-milestone",
	  	"Spring Maven SNAPSHOT Repository" at "http://repo.springsource.org/libs-snapshot",
	  	"Neo4j" at "http://m2.neo4j.org/content/repositories/releases/", 
	  	"codehaus" at "https://nexus.codehaus.org/content/groups/public/",
	  	"OSGEO GeoTools repo" at "http://download.osgeo.org/webdav/geotools",
	  	"Hibernate Spatial repo" at "http://www.hibernatespatial.org/repository",
	  	"Third party repo" at "https://repository.jboss.org/nexus/content/repositories/thirdparty-releases"
	)
  
	val appDependencies = Seq(
		javaCore,
		javaEbean.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
		"org.postgresql" % "postgresql" % "9.3-1101-jdbc4",
		"org.hibernate" % "hibernate-core" % "4.3.1.Final" ,
		"org.hibernate" % "hibernate-entitymanager" % "4.3.1.Final" ,
		"commons-dbcp" % "commons-dbcp" % "1.4",
		"commons-io" % "commons-io" % "2.4",		
		"org.springframework" % "spring-context" % "4.0.1.RELEASE",
		"org.springframework" % "spring-orm" % "4.0.1.RELEASE",
		"org.springframework" % "spring-jdbc" % "4.0.1.RELEASE",
		"org.springframework" % "spring-tx" % "4.0.1.RELEASE",
		"org.springframework" % "spring-aspects" % "4.0.1.RELEASE",
		"org.springframework" % "spring-test" % "4.0.1.RELEASE" % "test",
		"cglib" % "cglib" % "3.0",
		"asm" % "asm" % "3.3.1"
	)
	
	val main = play.Project(appName, appVersion, appDependencies)
	.settings(Play2WarPlugin.play2WarSettings: _*)
	.settings(
		Play2WarKeys.servletVersion := "3.0",
		Play2WarKeys.targetName := Option(appName+"##"+appVersion),
		Play2WarKeys.explodedJar := true,
		ebeanEnabled := false,
		credentials += Credentials(Path.userHome / ".m2" / "credentials.properties"),
		resolvers ++= repositories,
		organization := groupId,
		publishTo := publishURL,
		pomExtra := pomInfo
	)
	
}
