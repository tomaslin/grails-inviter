package grails.plugin.inviter

class InviterController {

    def grailsApplication

    def index = { }

    def invite = {

        def providers = [ 'Facebook', 'Gmail', 'Yahoo', 'Windows Live' ]

        [ providers: providers ]

    }

    def contacts = {

        def serviceName = "${ params.provider as String }InviterService"
        serviceName = serviceName[0].toString().toLowerCase() + serviceName[1..serviceName.length() -1 ]
        def service = grailsApplication.mainContext.getBean( serviceName )
        def contacts

        def ( authenticated, authToken ) = service.login( params.username as String, params.password as String )

        if( !authenticated ){

        } else
        {
            contacts = service.getContacts( authToken )
        }

        [ contacts: contacts ]

    }

	def sendInvites = {

		def addresses = params.addresses.split( ';' )

		addresses.each{

		}

	}

}