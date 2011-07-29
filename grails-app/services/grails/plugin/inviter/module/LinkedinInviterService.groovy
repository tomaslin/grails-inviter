package grails.plugin.inviter.module

import grails.converters.XML
import org.scribe.builder.api.GoogleApi
import org.scribe.builder.ServiceBuilder
import org.scribe.model.Verifier
import org.scribe.model.OAuthRequest
import org.scribe.model.Verb
import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH
import org.scribe.model.Token
import org.scribe.builder.api.TwitterApi

class LinkedinInviterService {


	static transactional = true
	static def authService
	static usesOAuth = true
 	private static final String AUTHORIZE_URL = "https://www.google.com/accounts/OAuthAuthorizeToken?oauth_token=";

	def getAuthDetails(callbackUrl) {
		if (!authService) {

			authService = new ServiceBuilder()
						   .provider(TwitterApi.class)
						   .apiKey("your_api_key")
						   .apiSecret("your_api_secret")
						   .build();

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



	}


}
