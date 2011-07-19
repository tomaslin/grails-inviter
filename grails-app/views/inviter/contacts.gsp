<html>
  <head><title>Invite Friends</title></head>
  <body>
        <ul>
            <g:each in="${ contacts }" var="contact">
                <li>${ contact.address } ${ contact.name ? '(' + contact.name + ')' : '' }</li>
            </g:each>
        </ul>
  </body>
</html>