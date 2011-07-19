<html>
  <head><title>Invite Friends</title></head>
  <body>
    <g:form action="contacts" controller="inviter">
        <label for="username">Username:</label><g:textField name="username"/> <label for="password">Password: </label><g:passwordField name="password"/>
        <g:select name="provider" from="${ providers }" />
        <g:submitButton name="Fetch Contacts" value="Invite"/>
    </g:form>
  </body>
</html>