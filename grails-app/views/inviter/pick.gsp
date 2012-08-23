<html>
<head><title>Pick Friends</title></head>

<r:require module="inviter"/>
<r:layoutResources/>

<body>

	<noscript>
		Sorry, this page requires JavaScript to work.
	</noscript>

	<h2>Pick Friends</h2>

	<iv:contacts contacts="${contacts}"/>

    <h2>iv:pickForm Send Picked Contacts To A Custom Action</h2>

	<iv:pickForm controller="" action=""
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
