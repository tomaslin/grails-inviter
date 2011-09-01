package grails.plugin.inviter.module

class TestInviterService {

	static transactional = true
	static def authService

	static useEmail = true

 	private static final String AUTHORIZE_URL = "https://www.google.com/accounts/OAuthAuthorizeToken?oauth_token=";

	def getAuthDetails(callbackUrl) {

		[ authUrl : callbackUrl, requestToken : [] ]

	}

	def getAccessToken( params, requestToken ){
	}

	def getContacts(accessToken) {


		def contacts = []

		( 1..20 ).each{

			def contact = [ : ]
			contact.name = "Test user ${ it }"
			contact.address = "test${it}@example.com"
			contacts << contact

		}

		contacts

	}


}
