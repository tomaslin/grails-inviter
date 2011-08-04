<html>
<head><title>Invite Friends</title></head>

<g:javascript library="jquery" plugin="jquery"/>
<g:javascript library="inviter"/>
<link rel='stylesheet' type='text/css' href="${ resource( dir:'css', file:'inviter.css') }" />
<script src="http://connect.facebook.net/en_US/all.js"></script>

<body>

	<noscript>
		Sorry, this page requires JavaScript to work.
	</noscript>

	<h2>Pick Friends</h2>

	<tmpl:/inviter/contacts contacts="${contacts}"/>

	<h2>Add A Personal Message</h2>

	<g:form name="sendInvite" action="sendInvites">
		<g:hiddenField name="provider" value="${provider}"/>
		<g:hiddenField name="addresses" value=""/>
		<g:textArea name="message"/><br/>
		<g:actionSubmit value="Invite Friends"></g:actionSubmit>
	</g:form>

</body>
</html>