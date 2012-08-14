<html>
<head><title>Invite Friends</title></head>

<!-- g:javascript library="jquery" plugin="jquery"/ -->
<!-- g:javascript library="inviter" plugin="inviter"/ -->
<script type="text/javascript" src="/inviter-demo/static/plugins/jquery-1.6.1/js/jquery/jquery-1.6.1.min.js"></script>

<script type="text/javascript" src="${ resource( dir:'js', file:'inviter.js', plugin:'inviter') }"></script>

<link rel='stylesheet' type='text/css' href="${ resource( dir:'css', file:'inviter.css', plugin:'inviter') }" />

<body>

	<noscript>
		Sorry, this page requires JavaScript to work.
	</noscript>

	<h2>Pick Friends</h2>



	<iv:contacts contacts="${contacts}"/>

	<h2>Add A Personal Message</h2>

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

</body>
</html>
