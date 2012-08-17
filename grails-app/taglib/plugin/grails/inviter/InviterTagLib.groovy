package plugin.grails.inviter

import groovy.text.Template

class InviterTagLib {

	static namespace="iv"

	def invitationLink = { attrs, body ->

		if (!attrs.provider) {
            throwTagError("Tag [invitationLink] is missing required attribute [provider]")
        }
        def action = (attrs.boolean('pick') ? 'pick' : 'invite')
		def linkAttrs = [ controller : 'inviter', action: action, params: [provider: attrs.provider ] ]

		out << link( linkAttrs, body )

	}

	def messageForm = { attrs ->

		out << render( template: '/inviter/invitationForm', model: attrs, plugin:'inviter' )

	}

	def contacts = { attrs ->

		out << render( template: '/inviter/contacts', model: [contacts: attrs.contacts ], plugin:'inviter' )

	}
    
	def pickForm = { attrs ->
		if (!attrs.controller || !attrs.action) {
            throwTagError("Tag [pickForm] is missing required attribute [controller] or [action]")
        }
		out << render( template: '/inviter/pickForm', model: attrs, plugin:'inviter' )

	}
}
