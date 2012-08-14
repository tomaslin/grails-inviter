<html>
<head>
	<meta name="google-site-verification" content="1wCMRez6Z6j0auEsb0SATyf95UUc4eEEO7gN5Ua0ZpY"/>
	<title>Grails inviter plugin demo</title>
</head>

<body>

<h3>Invite Friends Demo</h3>

<p>This is a demo of the grails inviter plugin. To invite your friends, click on any of the icons below.</p>

<p>
<ul>
	<g:each in="${['test', 'facebook', 'google', 'yahoo', 'twitter', 'linkedin', 'windowslive' ]}" var="provider">
		<li>
			<iv:invitationLink provider="${provider}"><img
					src="${resource(dir: 'images', file: provider + '.png')}"/> ${provider}</iv:invitationLink>
		</li>
	</g:each>
</ul>
</p>

<hr/>
<i>Icons: <a href="http://icondock.com">Icon Dock</a>.</i>


</body></html>
