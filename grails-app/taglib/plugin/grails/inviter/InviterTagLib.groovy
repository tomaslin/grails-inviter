package plugin.grails.inviter

class InviterTagLib {

	static namespace="iv"

	def invitationLink = { attrs, body ->

		if (!attrs.provider) {
            throwTagError("Tag [invitationLink] is missing required attribute [provider]")
        }

		def linkAttrs = [ controller : 'inviter', action: 'invite', params: [provider: attrs.provider ] ]

		out << link( linkAttrs, body )

	}

	def messageForm = { attrs ->

		out << render( template: '/inviter/invitationForm', model: attrs )

	}

	def contacts = { attrs ->

		out << render( template: '/inviter/contacts', model: [contacts: attrs.contacts ])

	}

}
