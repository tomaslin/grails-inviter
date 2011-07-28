// configuration for plugin testing - will not be included in the plugin zip

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
grails.plugin.inviter.yahoo.key='dj0yJmk9NEl6aU1zYnZhMHdLJmQ9WVdrOVdHRjBZVVI2Tm1zbWNHbzlNVGMzT0RBME16UTJNZy0tJnM9Y29uc3VtZXJzZWNyZXQmeD05Zg--'
grails.plugin.inviter.yahoo.secret= 'c7b26de0dd6877812056a8255fd3a31d1fe1ec66'
grails.plugin.inviter.google.key='inviter.cloudfoundry.com'
grails.plugin.inviter.google.secret='mIE86xbAHcH5Cr2J9BYoz0k9'
grails.plugin.inviter.providers=[ 'facebook', 'gmail', 'yahoo' ]


grails.plugin.cloudfoundry.username = "t.omaslin@gmail.com"
grails.plugin.cloudfoundry.password = "hokXENNO"
grails.serverURL = 'http://inviter.cloudfoundry.com'