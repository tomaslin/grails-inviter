package grails.plugin.inviter.module

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

import grails.converters.XML
import org.scribe.builder.ServiceBuilder
import org.scribe.builder.api.GoogleApi
import org.scribe.model.OAuthRequest
import org.scribe.model.Token
import org.scribe.model.Verb
import org.scribe.model.Verifier

class GmailInviterService {

	static transactional = true
	static def authService

	static useEmail = true

 	private static final String AUTHORIZE_URL = "https://www.google.com/accounts/OAuthAuthorizeToken?oauth_token=";

	def getAuthDetails(callbackUrl) {
		if (!authService) {
			authService = new ServiceBuilder().provider(GoogleApi.class)
											  .apiKey(CH.config.grails.plugin.inviter.google.key as String)
											  .apiSecret(CH.config.grails.plugin.inviter.google.secret as String)
											  .callback(callbackUrl as String)
											  .scope('https://www.google.com/m8/feeds')
											  .build()
		}

		Token requestToken = authService.getRequestToken();

		[ authUrl : AUTHORIZE_URL + requestToken.getToken(), requestToken : requestToken ]

	}

	def getAccessToken( params, requestToken ){
		requestToken = requestToken as Token
		Verifier verifier = new Verifier( params.oauth_verifier )
		authService.getAccessToken(requestToken, verifier);
	}

	def getContacts(accessToken) {

		OAuthRequest request = new OAuthRequest(Verb.GET, 'https://www.google.com/m8/feeds/contacts/default/full?max-results=10000');
		authService.signRequest(accessToken, request)
		def response = request.send();

		def contactList = []
		def contactListWithoutNames = []
		def addedContacts = []

		XML.parse(response.body).entry.each { entry ->

			entry.email.each { email ->

				def address = email.@address as String

				if (email.@primary == 'true' && !addedContacts.contains(address)) {
					def contact = [:]
					if (!entry.title?.toString().isEmpty()) {
						contact.name = entry.title.toString()
					}
					contact.address = address

					/*
					def photoLink = entry.link.find { it.@rel.toString().endsWith('#photo') }
					if (photoLink.@etag && photoLink.@href && photoLink.@href != '') {
						contact.photo = photoLink.@href
						contact.signPhoto = true
					}

					*/

					if (contact.name) {
						contactList << contact
					} else {
						contactListWithoutNames << contact
					}

					addedContacts.add(address)
				}
			}
		}

		contactList.sort { it.name.toLowerCase() } + contactListWithoutNames.sort { it.address }

	}

	def getPhoto( accessToken, photoUrl ){

		def photo

		OAuthRequest request = new OAuthRequest(Verb.GET, photoUrl);
		authService.signRequest(accessToken, request)
		def response = request.send();

		return response


	}

}
