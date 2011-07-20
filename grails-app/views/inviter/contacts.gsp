<html>
<head><title>Invite Friends</title></head>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>

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

ul, li {
    list-style: none outside none;
    margin:0px;
    padding:0px;
}

</style>

<script>

    function filter(  ){

    }

    function toggleAddress( email ){

    }

</script>

<body>

<noscript>
    Sorry, this page requires JavaScript to work.
</noscript>

<h2>Invite Friends</h2>

<div id="filter">Filter: <g:textField name="filter"/> <input type="button" value="Filter Now" onclick="filter()"></input> </div>
<div class="friends">
		<ul>
			<g:each in="${ contacts }" var="contact">
				<li><g:checkBox onclick="toggleAddress( '${contact.address}' )" name=""></g:checkBox> ${contact.address} ${contact.name ? '(' + contact.name + ')' : ''}</li>
			</g:each>
		</ul>
	</div>

<g:form action="sendInvite">
	<g:hiddenField name="provider" value="${provider}"/>
    <g:hiddenField name="addresses"></g:hiddenField>
    <label for="message">Message</label><br/>
	<g:textArea name="message" rows="10" cols="120"/><br/>
	<g:actionSubmit value="Invite Friends"></g:actionSubmit>
</g:form>
</body>
</html>