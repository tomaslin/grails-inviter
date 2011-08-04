<div id="filter">Find Friends: <g:textField name="filterField" id="filterField" value=""></g:textField>
</div>

<div class="friends">
	<g:each in="${ contacts }" var="contact">
		<div class="friend" data-email="${contact.address}">
			<g:if test="${contact.photo}">
				<img src="${contact.photo}"/>
			</g:if><g:else>
			<img src="${resource(dir: 'images', file: 'empty.png')}"/>
		</g:else>
			${contact.name ? contact.name : contact.address}
		</div>
	</g:each>
</div>