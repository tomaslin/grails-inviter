package grails.plugin.inviter.module

import org.scribe.builder.*;
import org.scribe.builder.api.*;
import org.scribe.model.*;
import org.scribe.oauth.*;

// this is a wrapper around scribe and uses oAuth for the authentication steps
class BaseOAuthService {

	static transactional = true

	static usesOAuth = true

	def getLoginUrl( apiKey, apiSecret, apiClass, useRequestToken, callbackUrl ){

		Token requestToken

		if( !authService ){

			authService = new ServiceBuilder().provider( apiClass ).apiKey( apiKey as String ).apiSecret( apiSecret as String )

			if( callbackUrl )
				authService = authService.callback( callbackUrl as String )

			authService = authService.build()

		}

		return authService.getAuthorizationUrl( useRequestToken ? authService.getRequestToken() : null )

	}

	def getProtectedUrl( accessToken, method, url  ){

		OAuthRequest request = new OAuthRequest( method, url );
		authService.signRequest( accessToken, request )
		Response response = request.send();
	  	return response
	}

}
