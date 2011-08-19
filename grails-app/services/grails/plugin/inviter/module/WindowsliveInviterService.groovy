package grails.plugin.inviter.module

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

import grails.converters.deep.XML
import org.apache.commons.io.IOUtils
import static groovyx.net.http.ContentType.URLENC

class WindowsliveInviterService {

	static transactional = true

	static def authService

	static def tokenStore = [:]

	def getAuthDetails(callbackUrl) {

		if (!authService)
		{
			authService = [:]
			authService.authUrl = """https://consent.live.com/Connect.aspx?wrap_client_id=${ CH.config.grails.plugin.inviter.windowslive.key as String }&wrap_callback=${ (callbackUrl as String).encodeAsURL() }&wrap_scope=WL_Contacts.View"""
			authService.callbackUrl = callbackUrl as String
			authService.key = CH.config.grails.plugin.inviter.windowslive.key as String
			authService.secret = CH.config.grails.plugin.inviter.windowslive.secret as String
		}


		[authUrl: authService.authUrl, requestToken: null]

	}

	def getAccessToken(params, requestToken) {

		def accessDetails = [:]

		withHttp(uri: "https://consent.live.com/") {

			post(
					path: 'AccessToken.aspx',
					body: [
							wrap_client_id: authService.key,
							wrap_client_secret: authService.secret,
							wrap_callback: authService.callbackUrl,
							wrap_verification_code: params.wrap_verification_code as String,
							idtype: 'CID'
					],
					requestContentType: URLENC
			) { resp ->
				def reply = IOUtils.toString(resp.entity.content)
				reply.split('&').each {
					def (key, value) = it.split('=')
					if (key == 'wrap_access_token')
					{
						accessDetails.token = value
					}
					if (key == 'uid')
					{
						accessDetails.uid = value
					}
				}
				tokenStore[accessDetails.token] = accessDetails.uid
			}
		}

		return accessDetails.token
	}

	def getContacts(accessToken) {

		def contacts = []

		def uid = tokenStore[accessToken]

		withHttp(uri: 'https://apis.live.net/V4.1/') {

			get(path: "cid-${uid}/Contacts/AllContacts",
					headers: [Authorization: "WRAP access_token=${ accessToken }"]
			) { resp, reader ->

				XML.parse(reader.text).entry.each {

					def contact = [:]
					contact.name = it.title as String
					contact.address = ( it.id as String ).replace('urn:uuid:', '')
					def profilePic = it.link.find { it.@title == 'ThumbnailImage' }.@href
					if (profilePic)
					{
						contact.photo = profilePic as String
					}
					contacts << contact

				}

			}

		}

		contacts.sort { it.name.toLowerCase() }

	}

	// http://msdn.microsoft.com/en-us/library/gg430655.aspx
	def sendMessage = { attrs ->

		def uid = tokenStore[attrs.accessToken]

		withHttp(uri: 'https://apis.live.net/V4.1/') {

			post(path: "cid-${uid}/Contacts/Invitations",
				headers: [Authorization: "WRAP access_token=${ accessToken }",
				body: 	"""{
							"Invitations": [
								{
									"To": ["${ attrs.contact }"],
									"From": "",
									"Subject": "${ attrs.subject }",
									"Body": "${ attrs.message }"
								}
							]
						}"""
				]
			) { resp, reader ->
				return resp
			}

		}

	}

}