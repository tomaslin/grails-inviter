package grails.plugin.inviter.module

import groovyx.net.http.RESTClient
import groovy.util.slurpersupport.GPathResult
import static groovyx.net.http.ContentType.URLENC
import grails.converters.XML

class GmailInviterService {

    static transactional = true
    static Url = "https://www.google.com"

    def login(email, password) {

        def authenticated = false;
        def authToken = ''

        withHttp(uri: Url) {

            post(
                    path: 'accounts/ClientLogin',
                    body: [
                            accountType: 'HOSTED_OR_GOOGLE',
                            Email: email,
                            Passwd: password,
                            service: 'cp',
                            source: 'Grails-Inviter-0.1'
                    ],
                    requestContentType: URLENC
            ) { resp, reader ->

                if (resp.statusLine.statusCode == 200) {
                    authenticated = true
                    reader.text.eachLine {
                        if (it.startsWith('Auth=')) {
                            authToken = it.split('=')[1]
                        }
                    }
                }

            }
        }

        return [authenticated, authToken]
    }

    def getContacts(authToken) {

        def parsedContacts = false

        withHttp(uri: Url) {
            get(
                    path: 'm8/feeds/contacts/default/full',
                    query: ['max-results': 10000],
                    headers: [Authorization: "GoogleLogin auth=${authToken}"]
            ) { resp, reader ->

                def contacts = XML.parse(reader.text)
                parsedContacts = []

                contacts.entry.each { entry ->
                    entry.email.@address.each{ email ->
                        def contact = [ : ]
                        if( entry.title ){
                            contact.name = entry.title
                        }
                        contact.address = email
                        parsedContacts << contact
                    }
                }

            }
            parsedContacts
        }

    }
}
