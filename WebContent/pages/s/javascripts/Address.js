function changeAddressFields(block, country){

	var prefix = block+"_";
	tabDiv = document.getElementsByTagName('div');
	if (tabDiv!=null){
		for (i=0; i<tabDiv.length; i++) {
			var elem = tabDiv[i];
			if(elem.id.match(prefix) == prefix){
				var countryPrefix = prefix + country;
				if((elem.id.match(countryPrefix) == countryPrefix)){
					elem.style.display='block';
				} else {
					elem.style.display='none';
				}
			} 
	    }
  	}
}

function addressCountryChanged(block, field){
	if(changeEntityAddressFields){
		changeEntityAddressFields(block);
	}
} 

function clrfield(idField,idFieldClr){
	if (document.forms[0][""+idField+""].value == "") {
		document.forms[0][""+idFieldClr+""].value = "";
	}
}