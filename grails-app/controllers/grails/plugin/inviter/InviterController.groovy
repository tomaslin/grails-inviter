package grails.plugin.inviter

class InviterController {

    def grailsApplication

    def index = { }

    def invite = {

    }

    def contacts = {

        def service = grailsApplication.mainContext.getBean( "${params.provider as String}InviterService" )

        def ( authenticated, authToken ) = login( params.username as String, params.password as String )

        if( !authenticated ){

        } else {
            def contacts = service.getContacts( authToken )
        }

        [ contacts: contacts ]

    }

}