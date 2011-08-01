<html>
<head><title>Invite Friends</title></head>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>

<style type="text/css">

body {
	font: 13px arial, sans-serif;
}

#filter {
	border: 1px solid #CCCCCC;
	border-top-left-radius: 6px;
	border-top-right-radius: 6px;
	height: 32px;
	margin: 0;
	padding: 6px 2px;
	width: 821px;
}

.friends {
	border: 1px solid #CCCCCC;
	border-top: 0px;
	height: 315px;
	margin-right: 5px;
	overflow-x: hidden;
	overflow-y: auto;
	padding: 2px;
	width: 821px;
}

.friend {
	background: -moz-linear-gradient(center top, #FFFFFF, #F4F4F4) repeat scroll 0 0 transparent;
	background-color: #F4F4F4;
	border: 1px solid #DDDDDD;
	border-radius: 2px 2px 2px 2px;
	box-shadow: 0 1px 0 #AAAAAA;
	margin: 9px;
	padding: 4px;
	position: relative;
	vertical-align: top;
	display: inline-block;
	height: 48px;
	margin-right: 11px;
	width: 164px;
	font-size: 12px;
	word-wrap: break-word;
	overflow: hidden;
	cursor: pointer
}

.friend img{
	float:left;
	padding-right:4px;
	height:50px;
	width:50px;
}

.friend.selected {
	background: -moz-linear-gradient(center top, #63ABF7, #5E99CD) repeat scroll 0 0 transparent;
	background-color: #63ABF7;
	border: 1px solid #777799;
	box-shadow: 0 0 3px #497FB8;
	color: #FFFFFF;
	-moz-transition: opacity 0.5s ease 0s;
}

textarea {
	height: 80px;
	width: 821px;
	padding: 6px;
}

</style>

<script>

	var addresses = [];
	var FILTER_BLANK = 'start typing a name';

	// adds or removes elements from the list of addresses
	function toggleAddress(address) {

		if ($.inArray(address, addresses) >= 0) {
			addresses = $.grep(addresses, function(val) {
				return val != address
			});
			getByEmail(address).each(function() {
				$(this).removeClass('selected')
			});
		} else {
			addresses.push(address);
			getByEmail(address).each(function() {
				$(this).addClass('selected')
			});
		}

		$('#addresses').val(addresses.join(','))

	}

	function getByEmail(address) {
		return $('.friends div').filter(function() {
			return $(this).attr('data-email') == address
		})
	}

	$(document).ready(function() {

		$('.friends div').click(function() {
			toggleAddress($(this).attr('data-email'));
		});

		$('#filterField').focus(function() {
			if ($('#filterField').val() == FILTER_BLANK) {
				$('#filterField').val('')
			}
		})

		$('#filterField').focusout(function() {
			if ($('#filterField').val() == '') {
				$('#filterField').val(FILTER_BLANK)
			}
		})

		$('#filterField').bindWithDelay( 'keyup', function() {

			var filterVal = $('#filterField').val()

			if (filterVal == '') {

				$('.friends div').show()

			} else if (filterVal.length > 0) {

				var rgx = new RegExp(filterVal, "i");

				$('.friends div').each(function() {
					($(this).text().search(rgx) < 0 && $(this).attr('data-email').search(rgx) < 0) ? $(this).hide() : $(this).show();
				});

			}

		}, 500 );

		$('#filterField').val(FILTER_BLANK)

	});

	/*
	bindWithDelay jQuery plugin
	Author: Brian Grinstead
	MIT license: http://www.opensource.org/licenses/mit-license.php
	http://github.com/bgrins/bindWithDelay
	http://briangrinstead.com/files/bindWithDelay
	*/

	(function($) {
	$.fn.bindWithDelay = function( type, data, fn, timeout, throttle ) {
		var wait = null;
		var that = this;

		if ( $.isFunction( data ) ) {
			throttle = timeout;
			timeout = fn;
			fn = data;
			data = undefined;
		}

		function cb() {
			var e = $.extend(true, { }, arguments[0]);
			var throttler = function() {
				wait = null;
				fn.apply(that, [e]);
			};

			if (!throttle) { clearTimeout(wait); }
			if (!throttle || !wait) { wait = setTimeout(throttler, timeout); }
		}

		return this.bind(type, data, cb);
	}
	})(jQuery);

</script>

<body>

	<noscript>
		Sorry, this page requires JavaScript to work.
	</noscript>

	<h2>Pick Friends</h2>

	<div id="filter">Find Friends: <g:textField name="filterField" id="filterField" value=""></g:textField>

	</div>

	<div class="friends">
		<g:each in="${ contacts }" var="contact">
			<div class="friend" data-email="${contact.address}">
				<g:if test="${contact.photo}">
					<img src="${contact.photo}"/>
				</g:if><g:else>
					<img src="${resource( dir:'images', file:'empty.png' ) }"/>
				</g:else>
				${contact.name ? contact.name : contact.address}
			</div>
		</g:each>
	</div>

	<h2>Add A Personal Message</h2>

	<g:form action="sendInvite">
		<g:hiddenField name="provider" value="${provider}"/>
		<g:hiddenField name="addresses" value=""/>
		<g:textArea name="message"/><br/>
		<g:actionSubmit value="Invite Friends"></g:actionSubmit>
	</g:form>

</body>
</html>