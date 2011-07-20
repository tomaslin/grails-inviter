<html>
<head><title>Invite Friends</title></head>


<style type="text/css">

.friends {
	border: 1px solid grey;
	height: 315px;
	margin-right: 5px;
	overflow-x: hidden;
	overflow-y: scroll;
	padding: 2px;
	width: 855px;
}

</style>

<body>

<h2>Invite Friends</h2>

<div id="filter">Filter: <g:textField name="filter"/> <input type="button" value="Filter Now"></input></div>

<g:form action="sendInvite">
	<g:hiddenField name="provider" value="${provider}"/>
	<div class="friends">
		<ul>
			<g:each in="${ contacts }" var="contact">
				<li><g:checkBox name="address.${contact.address}"></g:checkBox> ${contact.address} ${contact.name ? '(' + contact.name + ')' : ''}</li>
			</g:each>
		</ul>
	</div>
	<label for="message">Message</label><br/>
	<g:textArea name="message" rows="10" cols="120"/><br/>
	<g:actionSubmit value="Invite Friends"></g:actionSubmit>

</g:form>
</body>
</html>