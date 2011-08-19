<html>
	<head>
		<meta name="google-site-verification" content="1wCMRez6Z6j0auEsb0SATyf95UUc4eEEO7gN5Ua0ZpY" />
		<title>Grails inviter plugin demo</title>
	</head>

	<body>

		<h3>Invite Friends Demo</h3>

		<p> This is a demo of the grails inviter plugin. To invite your friends, click on any of the icons below.</p>

		<p>
			<ul>
		<li>
			<iv:invitationLink provider="facebook"><img src="${resource( dir:'images', file:'facebook.png' )}"/> Facebook</iv:invitationLink>
		</li>
		<li>
			<iv:invitationLink provider="google"><img src="${resource( dir:'images', file:'google.png' )}"/> Google </iv:invitationLink>
		</li>
		<li>
			<g:link controller="inviter" action="invite" params="${[provider:'Yahoo']}">
				<img src="${resource( dir:'images', file:'yahoo.png' )}"/> Yahoo
			</g:link>
		</li>
		<li>
			<g:link controller="inviter" action="invite" params="${[provider:'Twitter']}">
				<img src="${resource( dir:'images', file:'twitter.png' )}"/> Twitter
			</g:link>
		</li>

		<li>
			<g:link controller="inviter" action="invite" params="${[provider:'Linkedin']}">
				<img src="${resource( dir:'images', file:'linkedin.png' )}"/> LinkedIn
			</g:link>
		</li>

		<li>
			<g:link controller="inviter" action="invite" params="${[provider:'windowslive']}">
				<img src="${resource( dir:'images', file:'msn.png' )}"/> Windows Live
			</g:link>
		</li>


			</ul>
		</p>

		<hr/>
		<i>Icons: <a href="http://icondock.com">Icon Dock</a>.</i>

	</body></html>