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
        grailsCentral()
        mavenLocal()
        mavenCentral()
    }
    
    dependencies {
		build "org.scribe:scribe:1.2.1"
		runtime "org.scribe:scribe:1.2.1"
    }
    
    plugins {
        build(":release:2.0.3",
              ":rest-client-builder:1.0.2") {
            export = false
        }

        compile ":mail:1.0"
        compile ":rest:0.6.1"
        runtime ":resources:1.2.RC2"
    }
}
