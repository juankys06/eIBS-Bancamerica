
<%@ page contentType="application/x-javascript" %>

    function concatCiudad(){
      this.NA3.value=this.Aux_CtyCode.value+this.Aux_CTYDsc.value;
    }
    
     
    
    function concatUbicacion(){
      while (this.Aux_CasaEdif.value.length < 15 )this.Aux_CasaEdif.value = this.Aux_CasaEdif.value+ ' ';
      while (this.Aux_PisoNivel.value.length < 5) this.Aux_PisoNivel.value = this.Aux_PisoNivel.value + ' ';
      while (this.Aux_AptoOfi.value.length < 15) this.Aux_AptoOfi.value = this.Aux_AptoOfi.value + ' ';
      this.NA3.value=this.Aux_CasaEdif.value + this.Aux_PisoNivel.value+ this.Aux_AptoOfi.value;
    }       



function Address(pIndex){
  
  // source text fields
  this.index=pIndex;
  this.Aux_Municipio=document.getElementById("Aux_Municipio" + this.index);
  this.Aux_CTYCode=document.getElementById("Aux_CTYCode" + this.index);
  this.Aux_CTYDsc=document.getElementById("Aux_CTYDsc" + this.index);
  this.Aux_CasaEdif=document.getElementById("Aux_CasaEdif" + this.index);
  this.Aux_PisoNivel=document.getElementById("Aux_PisoNivel" + this.index);
  this.Aux_AptoOfi=document.getElementById("Aux_AptoOfi" + this.index);
  
  

  //hidden field CUSLN3   
  this.NA3=document.getElementById("E"+ this.index  + "4MA3");  // incluye casa, piso y apto
  this.CTY=document.getElementById("E"+ this.index+ "4CTR");//incluye codigo de ciudad y descripcion
  
  //object functions  
  this.concatCTY=concatCiudad;
  this.concatNA3=concatUbicacion;
 
}



function calculateAddress(evt){
  evt = (evt) ? evt : ((window.event) ? window.event : "");
  for (  i=1; i<10;i++){
    var address = new Address(i);
    address.concatCTY();
    address.concatNA3();
  }
}





