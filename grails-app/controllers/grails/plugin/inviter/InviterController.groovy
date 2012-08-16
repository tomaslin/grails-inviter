package grails.plugin.inviter

import org.scribe.model.Token

class InviterController {

	def grailsApplication

	def index = { }

	def invite() {
        validateRequest(params.provider)
        redirect(url: getAuthUrl(params.provider, 'contacts'))
	}

	def pick() {
        validateRequest(params.provider)
		redirect(url: getAuthUrl(params.provider, 'pickContacts'))
	}
    
    def validateRequest(provider) {
        if (    !provider ||
                !grailsApplication.config.grails.plugin.inviter[params.provider] || 
                !grailsApplication.config.grails.plugin.inviter[params.provider].enabled) {
                throw new RuntimeException("Provider '${provider}' not enabled")
        }
    }
    
    private getAuthUrl(provider, action) {
		def service = resolveService(provider)
		def authInfo = service.getAuthDetails(createLink(action: action, controller: 'inviter', absolute: 'true', params: [provider: provider]))
		if (authInfo.requestToken) {
			session["${provider}_requestToken"] = authInfo.requestToken
		}
        return authInfo.authUrl
    }

	def sendInvites = {
		def service = resolveService(params.provider)
		def accessToken = session["${params.provider}_accessToken"]
		def message = params.message + ' ' + ( grailsApplication.config.grails.plugin.inviter.defaultMessage ?: '' ) as String

		if( grailsApplication.config.grails.plugin.inviter.debug ){
			render """

				<html>
				<body>
				This is a debug screen.<br/>
				In a real life situation, I would have sent ${ message } to ${ params.addresses } on ${ params.provider }
				</body>
				</html>

			"""
			return
		} else {
			if (service.useEmail) {
				params.addresses.split(',').each { address ->
					sendMail {
						to       address
						subject  params.subject
						text     params.message
					}
				}
			} else {
				params.addresses.split(',').each { address ->
					def response = service.sendMessage( accessToken: accessToken, link: params.link, message: params.message, description: params.description, contact:address, subject: params.subject )
                    log.info "response = ${response}"
				}
			}
		}
        render(view: '/inviter/sent', model: [addresses:params.addresses, success:true], plugin: 'inviter')
	}

    /*
     * the action intended to show the 'send invites' form.
     */
	def contacts() {
        validateRequest(params.provider)
		def service = resolveService(params.provider)
		def accessToken = service.getAccessToken(params, session["${params.provider}_requestToken"])
		session["${params.provider}_accessToken"] = accessToken
		def contacts = service.getContacts(accessToken)
		render(view: '/inviter/contacts', model: [contacts: contacts, provider: params.provider], plugin: 'inviter')
	}

    /*
     * the action intended to show the 'pick contacts' form.
     */
	def pickContacts() {
        validateRequest(params.provider)
		def service = resolveService(params.provider)
		def accessToken = service.getAccessToken(params, session["${params.provider}_requestToken"])
		session["${params.provider}_accessToken"] = accessToken
		def contacts = service.getContacts(accessToken)
		render(view: '/inviter/pick', model: [contacts: contacts, provider: params.provider], plugin: 'inviter')
	}

	def photo = {
		def service = resolveService(params.provider)
		def photo = service.getPhoto(session["${params.provider}_accessToken"] as Token, params.photo)

		response.contentType = photo.getHeader('Content-Type')

		BufferedReader reader = new BufferedReader(new InputStreamReader(photo.stream));

		byte[] buffer = new byte[4096];
		int bytesRead;
		while ((bytesRead = reader.read(buffer)) != -1)
		{
			response.outputStream.write(buffer, 0, bytesRead);
		}
		reader.close();
		response.flushBuffer()
	}

	private def resolveService(provider) {
		def serviceName = "${ provider as String }InviterService"
		serviceName = serviceName[0].toString().toLowerCase() + serviceName[1..serviceName.length() - 1]
		grailsApplication.mainContext.getBean(serviceName)

	}
}
