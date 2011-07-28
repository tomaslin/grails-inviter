package grails.plugin.inviter

class InviterController {

    def grailsApplication

    def index = { }

    def invite = {

		def service = resolveService( params.provider )

		if( service.usesOAuth ){
		 	redirect( url: service.getLoginUrl( createLink( action:'contacts', controller:'inviter', absolute:'true', params:[ provider: params.provider ] ) ) )
		}

    }

	def sendInvites = {

	}

	def contacts = {

		def service = resolveService( params.provider )
		def authToken = service.getAccessToken( params )
		def contacts = service.getContacts( authToken )
		render( view: '/inviter/contacts', model: [contacts:contacts], plugin:'inviter' )

	}

	private def resolveService( provider ){

		def serviceName = "${ provider as String }InviterService"
        serviceName = serviceName[0].toString().toLowerCase() + serviceName[1..serviceName.length() -1 ]
        grailsApplication.mainContext.getBean( serviceName )

	}

}