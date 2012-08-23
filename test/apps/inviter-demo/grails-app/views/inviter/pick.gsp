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

    <h2>iv:pickForm Send Picked Contacts To A Custom Action</h2>
    <br>You can use this tag to import contacts</br>

	<iv:pickForm controller="demo" action="save"
                    provider="${ provider }"
					link="http://inviter.cloudfoundry.com"
					subject="join grails inviter"
					description="grails inviter"
					caption="picture caption"
					picture="http://www.grails.org/static/cXmUZIAv28XIiNgkRiz4RRl21TsGZ5HoGpZw1UITNyV.png"
					redirectUrl="http://inviter.cloudfoundry.com/success"
	/>
<r:layoutResources/>
</body>
</html>
