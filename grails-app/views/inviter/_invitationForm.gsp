<g:form name="inviteForm" action="sendInvites" controller="inviter">
	<g:hiddenField name="provider" value="${provider}"/>
	<g:hiddenField name="addresses" value=""/>
	<g:textArea name="message"/><br/>
	<g:submitButton name="invite" value="Invite Friends"></g:submitButton>
</g:form>