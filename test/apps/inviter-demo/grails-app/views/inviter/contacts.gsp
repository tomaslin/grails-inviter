<html>
<head><title>Invite Friends</title></head>

<r:require module="inviter"/>
<r:layoutResources/>

<body>

	<noscript>
		Sorry, this page requires JavaScript to work.
	</noscript>

	<h2>Pick Friends</h2>

	<iv:contacts contacts="${contacts}"/>

	<h2>iv:messageForm Send Invites Adding A Personal Message</h2>

	<iv:messageForm provider="${ provider }"
					link="http://inviter.cloudfoundry.com"
					subject="join grails inviter"
					description="grails inviter"
					caption="picture caption"
					picture="http://www.grails.org/static/cXmUZIAv28XIiNgkRiz4RRl21TsGZ5HoGpZw1UITNyV.png"
					redirectUrl="http://inviter.cloudfoundry.com/success"
					message="join grails inviter"
					canEditMessage="true"
	/>

<r:layoutResources/>
</body>
</html>
