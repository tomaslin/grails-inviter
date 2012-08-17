var addresses = [];
var FILTER_BLANK = INVITER_FILTER_BLANK || 'start typing a name';

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

	$('#clearSelection').click(clearSelection);
	$('#selectAll').click(selectAll);

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


function clearSelection() {
        addresses = [];

        $('.friends div').each(function() {
                $(this).removeClass('selected');
        });
}

function selectAll() {
        addresses = [];

        $('.friends div').each(function() {
                addresses.push($(this).attr('data-email'));
                $(this).addClass('selected');
        });
}