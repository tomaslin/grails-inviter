package grails.plugin.inviter.module

import org.scribe.builder.ServiceBuilder
import org.scribe.model.Verifier
import org.scribe.model.OAuthRequest
import org.scribe.model.Verb
import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH
import org.scribe.model.Token
import org.scribe.builder.api.LiveApi
import grails.converters.deep.JSON

class WindowsliveInviterService {

    static transactional = true
	static def authService
	static usesOAuth = true

	def getAuthDetails(callbackUrl) {
		if (!authService) {

			authService = new ServiceBuilder()
							   .provider(LiveApi.class)
							   .apiKey( CH.config.grails.plugin.inviter.windowslive.key as String )
							   .apiSecret( CH.config.grails.plugin.inviter.windowslive.secret as String )
							   .callback( callbackUrl as String )
							   .scope('wl.basic,wl.emails')
							   .build();
			}

		[ authUrl : authService.getAuthorizationUrl( null  ), requestToken : null ]

	}

	def getAccessToken( params, requestToken ){
		Verifier verifier = new Verifier( params.code as String );
		authService.getAccessToken( null, verifier );
	}

	def getContacts(accessToken) {

		[]

		// contacts.sort { it.name.toLowerCase() }
	}


	private def sendRequest( accessToken, method, url ){
		OAuthRequest request = new OAuthRequest( method, url )
		authService.signRequest( accessToken, request )
		def response = request.send();
		return JSON.parse( response.body )
	}

}