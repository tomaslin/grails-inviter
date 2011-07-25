package grails.plugin.inviter.module

import static groovyx.net.http.ContentType.URLENC
import grails.converters.XML

class YahooInviterService extends BaseService {

	static transactional = true
	static Url = "http://mail.yahoo.com"

	def login(email, password) {

		def hiddenFields = getHiddenFields( 'https://login.yahoo.com/config/mail?.intl=us&rl=1' )

	}

}
