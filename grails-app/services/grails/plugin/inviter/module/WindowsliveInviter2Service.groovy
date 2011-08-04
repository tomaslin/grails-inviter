package grails.plugin.inviter.module

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

import grails.converters.deep.JSON
import org.scribe.builder.ServiceBuilder
import org.scribe.builder.api.LiveApi
import org.scribe.model.OAuthRequest
import org.scribe.model.Verb
import org.scribe.model.Verifier

// uses the new inviter methods but doesn't work because you can't invite on Messenger Connect 5.
class WindowsliveInviter2Service {

    static transactional = true
	static def authService

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

		def friends = sendRequest( accessToken, Verb.GET, "http://apis.live.net/V4.1/me/Contacts/AllContacts" )

		return [ [ name : friends, address: accessToken.getToken() ]]

		def contacts = []

		friends.data.each{ friend ->

			def contact = [:]
			contact.name = friend.name.trim()
			contact.address = friend.id.trim()
			contacts << contact

		}

		contacts.sort { it.name.toLowerCase() }

	}


	private def sendRequest( accessToken, method, url ){
		OAuthRequest request = new OAuthRequest( method, url )
		authService.signRequest( accessToken, request )
		def response = request.send();
		return JSON.parse( response.body )
	}

}