<g:form name="pickForm" action="${action}" controller="${controller}">
	<g:hiddenField name="provider" value="${provider}"/>
	<g:hiddenField name="addresses" value=""/>
	<g:hiddenField name="link" value="${ link }"/>
	<g:hiddenField name="subject" value="${ subject }"/>
	<g:hiddenField name="description" value="${ description }"/>
	<g:hiddenField name="caption" value="${caption}"/>
	<g:hiddenField name="source" value="${source}"/>
	<g:hiddenField name="picture" value="${picture}"/>
	<g:hiddenField name="redirectUrl" value="${redirectUrl}"/>
	<g:submitButton name="pick" value="Pick contacts"></g:submitButton>
</g:form>