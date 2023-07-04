// CrossBrowserFunctions.js

// newFunction
function EventUtils() {

   throw 'RuntimeException: EventUtils is a static utility class ' +
            ' and may not be instantiated';

}

 EventUtils.addEventListener = function (target,type,callback,captures) {
	    if (target.addEventListener) {
                // EOMB
	        target.addEventListener(type,callback,captures);
	    } else if (target.attachEvent) {
	        // IE
	        target.attachEvent('on'+type,callback,captures);
	    } else {
	    	// IE 5 Mac and some others
	    	target['on'+type] = callback;
	    }
	};

	
