<html>
	<head>
		<meta name="google-site-verification" content="1wCMRez6Z6j0auEsb0SATyf95UUc4eEEO7gN5Ua0ZpY" />
		<title>Grails inviter plugin demo</title>
	</head>

	<body>

		<g:link controller="inviter" action="invite" params="${[provider:'Facebook']}">
		 	<img src="${resource( dir:'images', file:'facebook.png' )}"/> Facebook
		</g:link>

		<g:link controller="inviter" action="invite" params="${[provider:'Gmail']}">
			<img src="${resource( dir:'images', file:'google.png' )}"/> Gmail
		</g:link>

		<g:link controller="inviter" action="invite" params="${[provider:'Yahoo']}">
			<img src="${resource( dir:'images', file:'yahoo.png' )}"/> Yahoo
		</g:link>

		<hr/>
		<i>Icons: <a href="http://icondock.com">Icon Dock</a> </i>

	</body></html>