package grails.plugin.inviter.module

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH
import static groovyx.net.http.ContentType.URLENC
import org.scribe.model.Verb
import org.scribe.model.OAuthRequest
import org.apache.commons.io.IOUtils

// uses the new inviter methods but doesn't work because you can't invite on Messenger Connect 5.
class WindowsliveInviterService {

    static transactional = true

	static usesOAuth = true

	static def authService

	def getAuthDetails(callbackUrl) {

		if( !authService ){
			authService = [:]
			authService.authUrl = """https://consent.live.com/Connect.aspx?wrap_client_id=${ CH.config.grails.plugin.inviter.windowslive.key as String }&wrap_callback=${ ( callbackUrl as String ).encodeAsURL() }&wrap_scope=WL_Contacts.View"""
			authService.callbackUrl = callbackUrl as String
			authService.key = CH.config.grails.plugin.inviter.windowslive.key as String
			authService.secret = CH.config.grails.plugin.inviter.windowslive.secret as String
		}


		[ authUrl : authService.authUrl , requestToken : null ]

	}

	def getAccessToken( params, requestToken ){

		def accessDetails = [:]

		OAuthRequest request = new OAuthRequest( Verb.POST, "https://consent.live.com" )
		withHttp(uri: "https://consent.live.com/") {

			post(
					path: 'AccessToken.aspx',
					body: [
							wrap_client_id: authService.key,
							wrap_client_secret: authService.secret,
							wrap_callback: authService.callbackUrl,
							wrap_verification_code: params.wrap_verification_code,
							idtype: 'CID'
					],
					headers: [
					  	'User-Agent' : 'Mozilla/5.0 Ubuntu/8.10 Firefox/3.0.4'
					],
					requestContentType: URLENC
			) { resp ->
				def reply = IOUtils.toString( resp.entity.content )
				reply.split( '&' ).each{
					def ( key, value ) = it.split( '=' )
					if( key == 'wrap_access_token' ){
						accessDetails.token = value
					}
					if( key == 'uid' ){
						accessDetails.uid = value
					}
				}
			}
		}

		return accessDetails
	}

	def getContacts(accessToken) {

		return []

	}

}