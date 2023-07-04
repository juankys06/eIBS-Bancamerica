<%@ page contentType="application/x-javascript" %>

var applyFilters = false;

function checkCombo(x,y,w,h){
 var coll = document.getElementsByTagName("select");
 var x1 = 0;
 var y1 = 0;
 var w1 = 0;
 var h1 = 0;
 var t1;
 var t2;
 var t3;
 var t4;
 var obj;
 if (coll!=null)
 {
    for (var i=0; i<coll.length; i++){
      obj = coll[i].offsetParent;
      x1 = obj.offsetLeft;
      y1 = obj.offsetTop; 
      while (obj !== document.body){
	obj = obj.offsetParent;
	x1 += obj.offsetLeft;
	y1 += obj.offsetTop;
	}
      x1 += coll[i].offsetLeft;
      y1 += coll[i].offsetTop;
	
      w1 = coll[i].offsetWidth;
      h1 = coll[i].offsetHeight; 
      t1 = x1 + w1;
      t2 = x + w;
      t3 = y1 + h1;
      t4 = y + h;
      //alert(coll[i].name + ' x1 = '+ x1 + ', y1 = '+ y1 + ', w1 = '+ w1 + ', h1 = '+ h1);
      if (((t1 > x) && (x1 < t2)) && ((t3 > y) && (y1 < t4))) {
        document.forms[0][coll[i].name].style.visibility="hidden";
        //eval('document.forms[0].'+coll[i].name+'.style.visibility="hidden"');
      } else {  
        document.forms[0][coll[i].name].style.visibility="visible";
        //eval('document.forms[0].'+coll[i].name+'.style.visibility="visible"');
	  }
    }
 }

}

function getElementByNameAndId(name, id) {
	var elem;
	var list = document.getElementsByName(name);
	if (!list) return;
	
	for (var i = 0; i < list.length; ++i)
		if (list[i].id == id) elem = list[i];
    return elem;
}

function getEventElement(evt) {
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

function moveElement(elemRef, y, x){
	elemRef.style.top= y + "px";
    elemRef.style.left= x + "px";
}

function setVisibility(elemRef, visib){
	if (applyFilters && elemRef.filters){
   	  elemRef.filters[0].apply();
      elemRef.style.visibility=visib;
      elemRef.filters[0].Play();	
	} else {
      elemRef.style.visibility=visib;
	}
}

function applyTrans(elemRef){
  
	var MSIEVER=getMSIEVer();
	if (applyFilters && MSIEVER < 5.5) { elemRef.style.filter="blendTrans(duration=1)"; }
  
}

function cancelBub(evt){
	if (window.event) {
		window.event.cancelBubble = true;
	} else {
		if (evt) evt.stopPropagation();
	}
}

