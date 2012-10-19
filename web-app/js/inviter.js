var addresses = [];
var INVITER_FILTER_BLANK = INVITER_FILTER_BLANK || 'start typing a name';

// Array indexOf compatibility
// https://developer.mozilla.org/en-US/docs/JavaScript/Reference/Global_Objects/Array/indexOf
if (!Array.prototype.indexOf) {
    Array.prototype.indexOf = function (searchElement /*, fromIndex */ ) {
        "use strict";
        if (this == null) {
            throw new TypeError();
        }
        var t = Object(this);
        var len = t.length >>> 0;
        if (len === 0) {
            return -1;
        }
        var n = 0;
        if (arguments.length > 1) {
            n = Number(arguments[1]);
            if (n != n) { // shortcut for verifying if it's NaN
                n = 0;
            } else if (n != 0 && n != Infinity && n != -Infinity) {
                n = (n > 0 || -1) * Math.floor(Math.abs(n));
            }
        }
        if (n >= len) {
            return -1;
        }
        var k = n >= 0 ? n : Math.max(len - Math.abs(n), 0);
        for (; k < len; k++) {
            if (k in t && t[k] === searchElement) {
                return k;
            }
        }
        return -1;
    }
}

// Array filter compatibility
// https://developer.mozilla.org/en-US/docs/JavaScript/Reference/Global_Objects/Array/filter
if (!Array.prototype.filter)
{
  Array.prototype.filter = function(fun /*, thisp */)
  {
    "use strict";
 
    if (this == null)
      throw new TypeError();
 
    var t = Object(this);
    var len = t.length >>> 0;
    if (typeof fun != "function")
      throw new TypeError();
 
    var res = [];
    var thisp = arguments[1];
    for (var i = 0; i < len; i++)
    {
      if (i in t)
      {
        var val = t[i]; // in case fun mutates this
        if (fun.call(thisp, val, i, t))
          res.push(val);
      }
    }
 
    return res;
  };
}

var inviter = inviter || function() {};
inviter.prototype.onDomReady = function(callback){ // callback should be a function
    if (document.addEventListener) {
        // If the browser supports the DOMContentLoaded event,
        // assign the callback function to execute when that event fires
        document.addEventListener("DOMContentLoaded", callback, false);
    } else {
        if(document.body && document.body.lastChild){
            // If the DOM is available for access, execute the callback function
            callback();
        } else {
            // Reexecute the current function, denoted by arguments.callee,
            // after waiting a brief nanosecond so as not to lock up the browser
            return setTimeout(arguments.callee, 0);
        }
    }
};

inviter.prototype.isNullSafe = function(variable) {
    return (typeof(variable) != 'undefined' && variable != null);
};


inviter.prototype.getAllFriends = function() {
    var friends = [];
    var friendsNode = document.getElementById('inviter-friends');
    var els = friendsNode.getElementsByTagName('div');
    var elsLen = els.length;
    for(var i=0; i<elsLen; i++) {
        //console.log('el '+i);
        friends.push(els[i]);
    }
    return friends;
}

// adds or removes elements from the list of addresses
function toggleAddress(address) {
    console.log('toggle address '+address);
	//if ($.inArray(address, addresses) >= 0) {
    var addressIdx = addresses.indexOf(address);
    if (addressIdx >= 0) {
        console.log('toggle addresses 0 '+addresses);
        addresses.splice(addressIdx, 1);
        /*
		addresses = $.grep(addresses, function(val) {
            console.log('toggle address / grep val '+val);
			return val != address
		});
        */
        console.log('toggle addresses 1 '+addresses);
        
        var els = getByEmail(address);
        var elsLen = els.length;
        for(var i=0; i<elsLen; i++) {
            els[i].className = 'friend';
        }
        /*
		getByEmail(address).each(function() {
			$(this).removeClass('selected')
		});
        */
	} else {
		addresses.push(address);
        var els = getByEmail(address);
        var elsLen = els.length;
        for(var i=0; i<elsLen; i++) {
            els[i].className = 'friend selected';
        }
        /*
		getByEmail(address).each(function() {
			$(this).addClass('selected')
		});
        */
	}
	//$('#addresses').val(addresses.join(','))
    var addressesNode = document.getElementById('addresses');
    if (inviter.isNullSafe(addressesNode)) {
        addressesNode.value = addresses.join(',');
    }

}

function getByEmail(address) {
    var friends = inviter.getAllFriends();
    //console.log('friends = '+friends);
    // $('.friends div')
    //if (Array.isArray(friends) {
    if (inviter.isNullSafe(friends)) {
        return friends.filter(function(node) {
            //console.log('friends filter '+node.getAttribute('data-email') + '/'+address);
            //return $(this).attr('data-email') == address
            return node.getAttribute('data-email') == address;
        });
    }
    return [];
}

var typewatch = function(){
    var timer = 0;
    return function(callback, ms){
        clearTimeout (timer);
        timer = setTimeout(callback, ms);
    }  
}();


inviter.prototype.bindEvent = function(element, eventType, handler) {
    console.log('binding '+element+' - '+eventType);
    if (inviter.isNullSafe(element)) {
        if (typeof element.addEventListener != "undefined") {
            element.addEventListener(eventType, handler, false);
        //if addEventListener is not present, see if this is an IE browser
        } else if (typeof element.attachEvent != "undefined") {
            //element.attachEvent('on'+eventType, handler);
            element.attachEvent('on' + eventType, function(){
                /* use call to simulate addEventListener
                 * This will make sure the callback gets the element for "this"
                 * and will ensure the function's first argument is the event object
                 */
                 handler.call(event.srcElement,event);
            });
        }
    }
}

inviter = new inviter();

//$(document).ready(function() {
inviter.onDomReady(function() {
    /*
	$('.friends div').click(function() {
		toggleAddress($(this).attr('data-email'));
	});
    */
    var friends = inviter.getAllFriends();
    if (inviter.isNullSafe(friends)) {
        var elsLen = friends.length;
        for(var i=0; i<elsLen; i++) {
            var friend = friends[i];
            inviter.bindEvent(friend, 'click', function(e) {
                toggleAddress(this.getAttribute('data-email'));
            });
        }
    }

    
    
    
    
    
    var filterField = document.getElementById('clearSelection');
    inviter.bindEvent(filterField, 'focus', function() {
        //$('#filterField').focus(function() {
		if ($('#filterField').val() == INVITER_FILTER_BLANK) {
			$('#filterField').val('')
		}
	});
    inviter.bindEvent(filterField, 'focusout', function() {
	//$('#filterField').focusout(function() {
		if ($('#filterField').val() == '') {
			$('#filterField').val(INVITER_FILTER_BLANK)
		}
	});
    
	//$('#filterField').bindWithDelay( 'keyup', function() {
    inviter.bindEvent(document.getElementById('filterField'), 'keyup', function () {
        typewatch(function() {
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
    });
    
	$('#filterField').val(INVITER_FILTER_BLANK)
	//$('#clearSelection').click(clearSelection);
    inviter.bindEvent(document.getElementById('clearSelection'), 'click', clearSelection);
    inviter.bindEvent(document.getElementById('selectAll'), 'click', selectAll);
	//$('#selectAll').click(selectAll);
});

/*
bindWithDelay jQuery plugin
Author: Brian Grinstead
MIT license: http://www.opensource.org/licenses/mit-license.php
http://github.com/bgrins/bindWithDelay
http://briangrinstead.com/files/bindWithDelay
*/

/*
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
*/

function clearSelection() {
    addresses = [];
    $('.friends div').each(function() {
        $(this).removeClass('selected');
    });
    $('#addresses').val('')
}

function selectAll() {
    addresses = [];
    $('.friends div').each(function() {
        addresses.push($(this).attr('data-email'));
        $(this).addClass('selected');
    });
    console.log('select all addresses='+addresses);
    $('#addresses').val(addresses.join(','))
}