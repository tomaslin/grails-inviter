<html>
<head><title>Invite Friends</title></head>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>

<style type="text/css">

body {
    font: 13px arial,sans-serif;
}

#filter {
	border: 1px solid #CCCCCC;
    border-top-left-radius: 6px;
    border-top-right-radius: 6px;
    height: 32px;
    margin: 0;
    padding: 6px 2px;
    width: 721px;
}

.friends {
	border-left: 1px solid #CCCCCC;
	border-right: 1px solid #CCCCCC;
	height: 315px;
	margin-right: 5px;
	overflow-x: hidden;
	overflow-y: scroll;
	padding: 2px;
	width: 721px;
}

.friend{
    background: -moz-linear-gradient(center top , #FFFFFF, #F4F4F4) repeat scroll 0 0 transparent;
    border: 1px solid #DDDDDD;
    border-radius: 2px 2px 2px 2px;
    box-shadow: 0 1px 0 #AAAAAA;
    margin: 9px;
    padding: 4px;
    position: relative;
    vertical-align: top;
	display: inline-block;
	height: 48px;
    margin-right: 21px;
    width: 128px;
}

.friend.selected{
	background: -moz-linear-gradient(center top , #63ABF7, #5E99CD) repeat scroll 0 0 transparent;
    border: 1px solid #777799;
    box-shadow: 0 0 3px #497FB8;
    color: #FFFFFF;
    -moz-transition: opacity 0.5s ease 0s;
}

textarea{
	height: 80px;
	width: 721px;
	padding: 2px
}

</style>

<script>

	var addresses = [];

	// adds or removes elements from the list of addresses
    function toggleAddress( address ){

		if( jQuery.inArray( address, addresses ) >= 0 ){
			addresses = jQuery.grep(  addresses , function(val) { return val != address });
			getByEmail( address ).each(function() { $(this).removeClass( 'selected') });
		} else {
			addresses.push( address );
			getByEmail( address ).each(function() { $(this).addClass( 'selected') });
		}

		$( '#addresses' ).val( addresses.join( ',' ) )

    }

	function getByEmail( address ){
		return $('.friends div').filter(function() { return $(this).attr('data-email') == address })
	}

</script>

<body>

<noscript>
    Sorry, this page requires JavaScript to work.
</noscript>

<h2>Invite Friends</h2>

<div id="filter">Find Friends: <g:textField name="filterField" id="filterField" value="start typing a name"></g:textField>

</div>
<div class="friends">
			<g:each in="${ contacts }" var="contact">

				<div class="friend" data-email="${contact.address}" onclick="toggleAddress( '${contact.address}' )">
					${contact.name ? contact.name : contact.address.replaceAll( '@', ' @')}
				</div>

			</g:each>
</div>

<g:form action="sendInvite">
	<g:hiddenField name="provider" value="${provider}"/>
	<g:hiddenField name="addresses" value=""/>
    <label for="message">Add a personal message</label><br/>
	<g:textArea name="message"/><br/>
	<g:actionSubmit value="Invite Friends"></g:actionSubmit>
</g:form>

</body>
</html>