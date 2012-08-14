grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        //grailsPlugins()
        //grailsHome()
        grailsCentral()

        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        mavenLocal()
        mavenCentral()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

		build "org.scribe:scribe:1.2.1"
		runtime "org.scribe:scribe:1.2.1"



        // runtime 'mysql:mysql-connector-java:5.1.13'
    }
    
    plugins {
        build(":tomcat:$grailsVersion",
              ":release:2.0.3",
              ":rest-client-builder:1.0.2") {
            export = false
        }
        
/*

plugins.hibernate=1.3.7
plugins.jquery=1.6.1.1
plugins.mail=1.0-SNAPSHOT
plugins.release=1.0.0.RC3
plugins.rest=0.6.1
plugins.tomcat=1.3.7
*/

        compile ":mail:1.0"
        compile ":rest:0.6.1"
        runtime ":hibernate:$grailsVersion"
        runtime ":jquery:1.6.1"
        runtime ":resources:1.1.6"
    }
}
