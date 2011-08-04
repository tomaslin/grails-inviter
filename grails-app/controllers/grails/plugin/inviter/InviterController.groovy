package grails.plugin.inviter

import org.scribe.model.Token

class InviterController {

    def grailsApplication

    def index = { }

    def invite = {

		def service = resolveService( params.provider )

		if( service.usesOAuth ){

			def authInfo = service.getAuthDetails( createLink( action:'contacts', controller:'inviter', absolute:'true', params:[ provider: params.provider ] ) )

			if( authInfo.requestToken ){
				session[ "${params.provider}_requestToken" ] = authInfo.requestToken
			}

		 	redirect( url: authInfo.authUrl )
		}

    }

	def sendInvites = {

		def service = resolveService( params.provider )
		def authToken = session[ "${params.provider}_authToken" ]

		render( template: '/module/facebook', model:[ addresses : params.addresses  ]  )

	}

	def contacts = {

		def service = resolveService( params.provider )
		def authToken = service.getAccessToken( params, session[ "${params.provider}_requestToken" ] )
		session[ "${params.provider}_authToken" ] = authToken
		def contacts = service.getContacts( authToken )

		render( view: '/inviter/contacts', model: [contacts:contacts, provider: params.provider], plugin:'inviter' )

	}

	def photo = {
		def service = resolveService( params.provider )
		def photo = service.getPhoto( session[ "${params.provider}_authToken" ] as Token, params.photo )

		response.contentType = photo.getHeader('Content-Type')

		BufferedReader reader = new BufferedReader(new InputStreamReader(photo.stream));

		byte[] buffer = new byte[ 4096 ];
		int bytesRead;
		while ( (bytesRead = reader.read(buffer)) != -1 ) {
			response.outputStream.write( buffer, 0, bytesRead );
		}
		reader.close();
		response.flushBuffer()
	}

	private def resolveService( provider ){

		def serviceName = "${ provider as String }InviterService"
        serviceName = serviceName[0].toString().toLowerCase() + serviceName[1..serviceName.length() -1 ]
        grailsApplication.mainContext.getBean( serviceName )

	}
}