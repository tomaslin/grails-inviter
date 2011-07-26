package grails.plugin.inviter.module

import org.scribe.builder.*;
import org.scribe.builder.api.*;
import org.scribe.model.*;
import org.scribe.oauth.*;

// this is a wrapper around scribe and uses oAuth for the authentication steps
class OauthService {

	static transactional = true

	static OAuthService authService

	def getLoginUrl( apiKey, apiSecret, apiClass ){

		if( !authService ){
			authService = new ServiceBuilder().provider( apiClass ).apiKey( apiKey ).apiSecret( apiSecret ).build();
		}

		Token requestToken = authService.getRequestToken();

		return authService.getAuthorizationUrl(requestToken)

	}

	def getAccessToken( verificationCode, session ){

		Verifier verifier = new Verifier( verificationCode );
		Token accessToken = authService.getAccessToken( requestToken, verifier );

		return accessToken
	}

	def getProtectedUrl( accessToken, method, url  ){
		OAuthRequest request = new OAuthRequest( method, url );
		authService.signRequest( accessToken, request )
		Response response = request.send();
	  	return response
	}

}
