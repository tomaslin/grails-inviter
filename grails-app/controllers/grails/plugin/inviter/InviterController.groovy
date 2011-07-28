package grails.plugin.inviter

class InviterController {

    def grailsApplication

    def index = { }

    def invite = {

		def service = resolveService( params.provider )

		if( service.usesOAuth ){

			def authInfo = service.getAuthDetails( createLink( action:'contacts', controller:'inviter', absolute:'true', params:[ provider: params.provider ] ) )

			if( authInfo.requestToken ){
				session[ resolveRequestToken( params.provider ) ] = authInfo.requestToken
			}

			println session[ resolveRequestToken( params.provider ) ]

		 	redirect( url: authInfo.authUrl )
		}

    }

	def sendInvites = {

	}

	def contacts = {

		def service = resolveService( params.provider )
		def authToken = service.getAccessToken( params, session[ resolveRequestToken( params.provider ) ] )
		def contacts = service.getContacts( authToken )
		render( view: '/inviter/contacts', model: [contacts:contacts], plugin:'inviter' )

	}

	private def resolveService( provider ){

		def serviceName = "${ provider as String }InviterService"
        serviceName = serviceName[0].toString().toLowerCase() + serviceName[1..serviceName.length() -1 ]
        grailsApplication.mainContext.getBean( serviceName )

	}

	private def resolveRequestToken( provider ){
		"${provider}_requestToken"
	}

}