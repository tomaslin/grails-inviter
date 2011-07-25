package grails.plugin.inviter.module

import org.htmlcleaner.HtmlCleaner
import grails.converters.deep.XML
import org.htmlcleaner.SimpleXmlSerializer

class BaseService {

	static transactional = true

	def getHiddenFields(address) {

		def cleaner = new HtmlCleaner()
		def node = cleaner.clean(address.toURL())

		def props = cleaner.getProperties()
		def serializer = new SimpleXmlSerializer(props)
		def xml = XML.parse(serializer.getXmlAsString(node))

		def hiddenValues = [:]

		xml.depthFirst().grep { it.@type == 'hidden' }.each {
			hiddenValues["${ it.@name }"] = "${it.@value}"
		}

		return hiddenValues

	}


}
