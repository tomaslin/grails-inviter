
class DemoInviterService {

	static def authService

	static useEmail = false

	def getAuthDetails(callbackUrl) {
		[ authUrl : callbackUrl, requestToken : [] ]
	}

	def getAccessToken( params, requestToken ){
	}

	def getContacts(accessToken) {
		def contacts = []
		( 1..20 ).each{
			def contact = [ : ]
			contact.name = "Demo user ${ it }"
			contact.address = "demo${it}@example.com"
            //contact.photo = 
			contacts << contact
		}
		contacts
	}

	def sendMessage(attrs) {
		// attrs.contact
        // attrs.message
        // attrs.link
        // attrs.picture
        // attrs.name
        // attrs.caption
        // attrs.description
        // attrs.source
	    return [ view: '/inviter/custom', model:[success:true]]

	}
}