package grails.plugin.inviter.module

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH
import org.scribe.builder.api.FacebookApi
import grails.converters.deep.JSON
import org.scribe.model.Verifier
import org.scribe.builder.ServiceBuilder
import org.scribe.model.OAuthRequest
import org.scribe.model.Verb

class FacebookInviterService{

	static transactional = true
	static def authService
	static usesOAuth = true
	static clientSideSend = true

	def getAuthDetails( callbackUrl ){
		if(!authService){
			authService = new ServiceBuilder().provider( FacebookApi.class)
							.apiKey( CH.config.grails.plugin.inviter.facebook.key as String )
							.apiSecret( CH.config.grails.plugin.inviter.facebook.secret as String )
							.callback( callbackUrl as String ).build()
		}
		[ authUrl : authService.getAuthorizationUrl( null  ), requestToken : null ]
	}

	def getAccessToken( params, requestToken ){
		Verifier verifier = new Verifier( params.code as String );
		authService.getAccessToken( null, verifier );
	}

	def getContacts( accessToken ){

		OAuthRequest request = new OAuthRequest( Verb.GET, 'https://graph.facebook.com/me/friends/' );
		authService.signRequest( accessToken, request )
		def response = request.send();

		def contacts = []

		JSON.parse( response.body ).data.each{

			def contact = [ : ]
			contact.name = it.name
			contact.address = it.id
			contact.photo = "http://graph.facebook.com/${ it.id }/picture"
			contacts << contact

		}

		contacts.sort { it.name.toLowerCase() }

	}

}
