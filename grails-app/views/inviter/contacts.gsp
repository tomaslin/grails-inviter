<html>
  <head><title>Invite Friends</title></head>
  <body>
        <ul>
            <g:each in="${ contacts }" var="contact">
                <li>${ contact.email } ${ it.name ? '(' + it.name + ')' : '' } ) </li>
            </g:each>
        </ul>
  </body>
</html>