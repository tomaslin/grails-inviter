// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [
    all:           '*/*',       // */
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']        // */

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
}


// inviter plugin
grails.plugin.inviter.facebook.key='68872945632'
grails.plugin.inviter.facebook.secret='aeb81b252776b8d2553fa4369b6b271a'
grails.plugin.inviter.yahoo.key='dj0yJmk9T21LU3dnazNxUnJ2JmQ9WVdrOWRGQlBhWFZ4TlRZbWNHbzlNVEV6TkRreU5qUTJNZy0tJnM9Y29uc3VtZXJzZWNyZXQmeD04Nw--'
grails.plugin.inviter.yahoo.secret= 'a21f29aa15b65c2394a5cd18a5f266eba15fcc91'
grails.plugin.inviter.google.key='inviter.cloudfoundry.com'
grails.plugin.inviter.google.secret='mIE86xbAHcH5Cr2J9BYoz0k9'
grails.plugin.inviter.linkedin.key='k25tupolcvf1'
grails.plugin.inviter.linkedin.secret='KBtxC418sdmu5eur'
grails.plugin.inviter.twitter.key='G8rCU7AHBsZAbeLUAP0i7Q'
grails.plugin.inviter.twitter.secret='BKbt1ygWu0q4xsLuJ8bhjYvegojVHz2GXn1Z5leoXN8'
grails.plugin.inviter.windowslive.key='0000000040062703'
grails.plugin.inviter.windowslive.secret='uuQUGpig3kbv14SdlGwPdHYS86eVWlRc'

grails.views.javascript.library = "jquery"
grails.plugin.inviter.debug = false

environments {
    test {
        grails.mail.port = com.icegreen.greenmail.util.ServerSetupTest.SMTP.port
    }
    development {
        grails.mail.port = com.icegreen.greenmail.util.ServerSetupTest.SMTP.port
    }
    production {
        // greenmail.disabled=true
        // have we got a real production for this app?
        grails.mail.port = com.icegreen.greenmail.util.ServerSetupTest.SMTP.port
    }
}

