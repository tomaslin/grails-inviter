package grails.plugin.inviter

class InviterController {

    def grailsApplication

    def index = { }

    def invite = {

        def providers = [ 'Facebook', 'Gmail', 'Yahoo', 'Windows Live' ]

        [ providers: providers ]

    }

    def contacts = {

        def serviceName = "${ params.provider as String }InviterService"
        serviceName = serviceName[0].toString().toLowerCase() + serviceName[1..serviceName.length() -1 ]
        def service = grailsApplication.mainContext.getBean( serviceName )
        def contacts

        def ( authenticated, authToken ) = service.login( params.username as String, params.password as String )

        if( !authenticated ){

        } else
        {
            // contacts = service.getContacts( authToken )
  			contacts = []

			contacts << [ name: 'tomas lin', address: 'tomaslin@gmail.com' ]
			contacts << [ name: 'tomas lin', address: 'to.maslin@gmail.com' ]
			contacts << [ name: 'tomas lin', address: 'toma.slin@gmail.com' ]
			contacts << [ name: 'tomas lin', address: 'tomasl.in@gmail.com' ]
			contacts << [ name: 'molly lin', address: 'mbushman7@gmail.com' ]
			contacts << [ address: 'tomas@secretescapes.com' ]

        }

        [ contacts: contacts ]

    }

}