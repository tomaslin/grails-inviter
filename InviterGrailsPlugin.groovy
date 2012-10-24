class InviterGrailsPlugin {
    // the plugin version
    def version = "0.3"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.3.7 > *"
    // the other plugins this plugin depends on
    def dependsOn = [mail:'0.9 > *', rest:'0.6.1' ]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [ ]

    def author = "Tomas Lin"
    def authorEmail = "tomaslin@gmail.com"
    def title = "Inviter - a simple way to invite people"
    def description = '''\\
This is a grails port of the functionality found in the OpenInviter PHP project. The plugin allows you to get address book information and send messages to various email clients and social networks.

Under the hood, it uses the excellent Scribe Library for java.

Full documentation at https://github.com/tomaslin/grails-inviter
'''

    // URL to the plugin's documentation
    def documentation = "https://github.com/tomaslin/grails-inviter"

    def doWithWebDescriptor = { xml ->
    }

    def doWithSpring = {
    }

    def doWithDynamicMethods = { ctx ->
    }

    def doWithApplicationContext = { applicationContext ->
    }

    def onChange = { event ->
    }

    def onConfigChange = { event ->
    }
}
