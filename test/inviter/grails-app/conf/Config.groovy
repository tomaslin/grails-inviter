// configuration for plugin testing - will not be included in the plugin zip

grails.config.locations = [ "file:${userHome}/Dropbox/inviter-config.groovy" ]

log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'

    warn   'org.mortbay.log'
}

grails.plugin.inviter.facebook.key='197755966948551'
grails.plugin.inviter.facebook.secret='5dc5ace6405e5882511e6dd177aa5f77'
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

grails.serverURL = 'http://inviter.cloudfoundry.com'
grails.views.javascript.library = "jquery"
grails.plugin.inviter.debug = false