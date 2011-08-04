<g:form name="sendInvite" action="sendInvites" controller="inviter">
	<g:hiddenField name="provider" value="${provider}"/>
	<g:hiddenField name="addresses" value=""/>
	<g:textArea name="message"/><br/>
	<g:actionSubmit value="Invite Friends"></g:actionSubmit>
</g:form>