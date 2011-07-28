package grails.plugin.inviter.module

import org.scribe.builder.api.YahooApi
import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

class YahooInviterService extends BaseOAuthService {

	static transactional = true

	def getLoginUrl(){
		return getLoginUrl( CH.config.grails.plugin.inviter.yahoo.key as String, CH.config.grails.plugin.inviter.yahoo.key as String, YahooApi.class, true )
	}

	def getContacts(){

	}

}
