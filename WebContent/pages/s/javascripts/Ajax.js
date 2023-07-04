//author: fhernandez

function EventElement(evt) {
    evt = (evt) ? evt : ((window.event) ? window.event : "");
	var elem;
    if (evt) {
        if (evt.target) {
			elem = evt.target;
			while(elem.nodeType != elem.ELEMENT_NODE){
				elem = elem.parentNode;
			}
        } else {
            elem = evt.srcElement;
        }
    }
    return elem;
}

function CallbackObject() {
	this.ajax;
	this.callback;
}

function CreateXMLHttpRequest() {
	try { return new XMLHttpRequest(); } catch(e) {}
	try { return new ActiveXObject("Msxml2.XMLHTTP"); } catch (e) {}
	try { return new ActiveXObject("Microsoft.XMLHTTP"); } catch (e) {}
	return null;
}
//Parameter Asynchronus takes values: true or false
function GetXMLResponse(URL, callbackObject, asynchronus){
	ajax = CreateXMLHttpRequest();
	CallbackObject.ajax = ajax;
	ajax.onreadystatechange = callbackObject;
	ajax.open("GET", URL, asynchronus);
	ajax.send(null);
}

function XMLResponse(ajax){
	// Comprobamos si la peticion se ha completado (estado 4) 
	if( ajax.readyState == 4 ) {
		// Comprobamos si la respuesta ha sido correcta (resultado HTTP 200)
		if( ajax.status == 200 ) {
	    	// Escribimos el resultado en la pagina HTML mediante DHTML			
			return ajax.responseXML;
		}
	}
}

function TextResponse(ajax){
	// Comprobamos si la peticion se ha completado (estado 4) 
	if( ajax.readyState == 4 ) {
		// Comprobamos si la respuesta ha sido correcta (resultado HTTP 200)
		if( ajax.status == 200 ) {
	    	// Escribimos el resultado en la pagina HTML mediante DHTML			
			return ajax.responseText;
		}
	}
}

function selectCallback(){
	xmlResponse = XMLResponse(CallbackObject.ajax);	
	if(xmlResponse){
		var items = xmlResponse.getElementsByTagName('select');
		for (var i = 0 ; i < items.length ; i++){
			var item = items[i];
			var controlName = item.getAttribute("name");
			var control = document.getElementsByName(controlName)[0];
			control.outerHTML=item.xml;
		}
	}
}

function tableCallback(){
	xmlResponse = XMLResponse(CallbackObject.ajax);
	if(xmlResponse){
		var items = xmlResponse.getElementsByTagName('table');
		for (var i = 0 ; i < items.length ; i++){
			var item = items[i];
			var controlName = item.getAttribute("id");
			var control = document.getElementsByName(controlName)[0];
			control.outerHTML=item.xml;
		}
	}
}

function divCallback(){
	response = XMLResponse(CallbackObject.ajax);
	if(response){
		var items = response.getElementsByTagName('div');
		for (var i = 0 ; i < items.length ; i++){
			var item = items[i];
			var controlId = item.getAttribute("id");
			if(controlId){
				var control = document.getElementById(controlId);
				control.outerHTML=item.xml;
			}
		}
	}
}

