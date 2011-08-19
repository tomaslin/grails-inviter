package grails.plugin.inviter

import org.scribe.model.Token

class InviterController {

	def grailsApplication

	def index = { }

	def invite = {

		def service = resolveService(params.provider)

		def authInfo = service.getAuthDetails(createLink(action: 'contacts', controller: 'inviter', absolute: 'true', params: [provider: params.provider]))

		if (authInfo.requestToken)
		{
			session["${params.provider}_requestToken"] = authInfo.requestToken
		}

		redirect(url: authInfo.authUrl)
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

			if (service.useEmail)
			{

				params.addresses.split(',').each { address ->
					sendMail {
						to: address
						message: message
					}
				}

			} else
			{
				params.addresses.split(',').each { address ->
					def response = service.sendMessage( accessToken: accessToken, link: 'http://inviter.cloudfoundry.com', message:'join grails inviter ', description:"grails inviter let's you invite people", contact:address, subject:'join grails inviter' )
				}
			}

		}

		render 'Your messages have been sent'

	}

	def contacts = {

		def service = resolveService(params.provider)
		def accessToken = service.getAccessToken(params, session["${params.provider}_requestToken"])
		session["${params.provider}_accessToken"] = accessToken
		def contacts = service.getContacts(accessToken)
		render(view: '/inviter/contacts', model: [contacts: contacts, provider: params.provider], plugin: 'inviter')

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