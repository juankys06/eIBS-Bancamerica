<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Cambio de PIN
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<body >
<% 
 if ( !error.getERRNUM().equals("0")  ) {
 
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 
%> 
<h3 align="center">Cambio de PIN<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ECC010001_change_pin.jsp"></h3>
<hr size="4">
<%--
Este componente se carga en el frame que tiene la información del cliente para poder
ejecutar funciones en diferentes requests sin perder la comunicacion con el PINPAD
<DIV style="visibility:hidden" >
	<OBJECT ID="PINPAD"
	  CLASSID="CLSID:2E8A70D6-2E7E-4A2B-BEAF-437BBF1126B6"
	  CODEBASE="intellipinactivex.dll">
	</OBJECT>
</DIV>
--%>
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC010001" id="form1" name="form1" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="30">
<INPUT TYPE=HIDDEN NAME="OPT" VALUE="1">
<INPUT TYPE=HIDDEN NAME="E01CCRPIO" VALUE="">
<INPUT TYPE=HIDDEN NAME="E01CCRPIN" VALUE="">

   		<TABLE class="tbenter" width=100% >
   		<TR>
      <TD> 
        <div align="center"> 
   	      <table class="tableinfo">
            <tr > 
              <td nowrap> 
                <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
                  <tr id="trdark"> 
                    <td nowrap width="16%" > 
                      <div align="right"><b>Cliente :</b></div>
                    </td>
                    <td nowrap width="20%" > 
                      <div align="left"><b> 
                        <input type="text" readonly name="E01CARCUN" size="9" maxlength="9" value="<%= userPO.getCusNum().trim()%>" >
                        </b> </div>
                    </td>
                    <td nowrap width="16%" >
                      <div align="right"><b>Nombre :</b> </div>
                    </td>
                    <td nowrap > 
                      <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                        <input type="text" name="E01CARNA1" size="45" maxlength="45" readonly value="<%= userPO.getCusName().trim()%>">
                        </font></font></font></div>
                    </td>
                  </tr>
                  <%-- 
                  <tr id="trdark"> 
                    <td nowrap width="16%" > 
                      <div align="right">Estado</div>
                    </td>
                    <td nowrap width="20%" > 
			           <input type="text" readonly name="E01CCRSTA" size="12" maxlength="10" 
			           	value="<% 
			           	if 		(userPO.getHeader2().equals("I")) out.print("Inactiva");
						else if	(userPO.getHeader2().equals("A")) out.print("Activa");
			           	else out.print("");	%>" >	
                    </td>
                    <td nowrap width="16%" > 
                      <div align="right">Causal de Estado :</div>
                    </td>
                    <td nowrap > 
                      <div align="left">
                      	<input type="text" readonly name="E01CCRDSC" size="17" maxlength="15" value="<%= userPO.getHeader3()%>" >
                      </div>
                    </td>
                  </tr>         
                  --%>
                  <tr id="trdark"> 
                    <td nowrap width="16%" > 
                      <div align="right"></div>
                    </td>
                    <td nowrap > 
                      <div align="left">
                      </div>
                    </td>
                    <td nowrap width="16%" > 
                      <div align="right">Numero de tarjeta :</div>
                    </td>
                    <td nowrap > 
                      <div align="left">
                      	<input type="text" readonly name="E01CCRNUM" size="30" maxlength="30" value="<%= userPO.getHeader1().trim()%>" >
                      </div>
                    </td>
                  </tr>                             
                </table>
              </td>
            </tr>
          </table>      

          <p>&nbsp;</p>
          <p></p><p>&nbsp; </p>
        </div>
      </TD></TR></TABLE><p></p>
  <center></center>
      
    <table align="center" width="40%" >     
	  <tr align="center" > 
	    <%--
	    <TD nowrap width="25%"  > 
	      <INPUT type="button" id="EIBSBTN" name="activepin" value="Activar" size="30" onclick="actPinpad()" >
	    </TD>
	    --%>
	    <TD nowrap width="25%"> 
	      <input id="EIBSBTN" type="button" name="leerPINB" value="Cambiar PIN" onClick="leerPIN();" >
	    </TD>
	    
	    <%--
	    <TD nowrap width="23%"> 
			   <input id="EIBSBTN" type="button" name="Submit" value="aceptar" onClick="enviar()" disabled="disabled" >
	    </TD>
	    --%>
	    
      </tr>                                                                                           
    </table>
      
</FORM>

<script language="JavaScript">

function enviar(){
	if(document.forms[0].E01CCRPIO.value == ""){
		alert("Debe leer el PIN correspondiente a la tarjeta.") ;
	}else {
		document.forms[0].submit() ;
	}
}


function openPINPAD(){


	var open = top.client.document.PINPAD.Iniciar("15") ;
	if( open != 0 ){
		var text = "" ;
		switch(open){
			case 100 :
				text = "Error. Puerto de comunicacion con el dispositivo." ;
				break ;

		}
		alert( text ) ;
		return false ;
	}else {
		return true ;
	}
	
}

function setPIN( cardnum ){

			var pinblock = top.client.document.PINPAD.AsignarPin( cardnum , "0" ) ;
			var text = "" ;
			if( pinblock.length < 16 ){
				switch( pinblock ){
					case "150" :
						text = "Operación Cancelada" ;
						break ;
					
					case "900" :
						text = "Tarjeta no corresponde" ;
						break ;
					
					case "200" :
						text = "Los PIN Introducidos son distintos" ;
						break ;
		
					default :
						text = "Error en el dispositivo. Cod:" + pinblock ;
						break ;
				}
				alert( text ) ;
			
			}
			return pinblock ;

}


function setNewPIN(){

			var pb = setPIN( document.forms[0].E01CCRNUM.value ) ;
			if( pb.length != 16 ){
				//top.client.document.PINPAD.Salir();
				
			}else{
				//ASIGNAR EL PINBLOCK EN EL CAMPO EN EL QUE DEBE VIAJAR AL HOST
				document.forms[0].E01CCRPIO.value = pb ;
			}

}

function activeDevice(){

	switch( top.client.document.PINPAD.ActivarLectura() ){
		case 0 :
			return true ;
			break ;
		default :
			alert("No se pudo activar el dispositivo.") ;
			return false ;
			break ;
	}

}

function actPinpad(){

	if( openPINPAD() && activeDevice() ){
		alert("Deslice la Tarjeta y luego presione Aceptar.") ;
	}else{
		top.client.document.PINPAD.Salir() ;
	}

}

function readPIN(){
	
	var pinblock = top.client.document.PINPAD.LeerPin( document.forms[0].E01CCRNUM.value ) ;
	var text = "" ;

	if( pinblock.length < 16 ){
		switch( pinblock ){
			case "150" :
				text = "Operación Cancelada" ;
				break ;
			
			case "900" :
				text = "Tarjeta no corresponde" ;
				break ;
			
			case "200" :
				text = "Los PIN Introducidos son distintos" ;
				break ;

			default :
				text = "Error en el dispositivo. Cod:" + pinblock ;
				break ;
		}
		alert( text ) ;
	
	}else{
		document.forms[0].E01CCRPIN.value = pinblock ;
	}
	
	//top.client.document.PINPAD.Salir() ;
	
}

function leerPIN(){

	document.forms[0].E01CCRPIO.value != ""
	document.forms[0].E01CCRPIN.value != ""

	if( openPINPAD() && activeDevice() ){
		alert("Deslice la Tarjeta y luego presione Aceptar.") ;

		setNewPIN() ;
		
		if( document.forms[0].E01CCRPIO.value != "" ){
			readPIN() ;
		
			if( document.forms[0].E01CCRPIN.value != "" ){
				//document.forms[0].Submit.disabled = false ;
				alert("PIN Leido correctamente.") ;
				document.forms[0].submit() ;
			}
		
		}


	}

	top.client.document.PINPAD.Salir() ;
	
<%--
/*
	document.forms[0].E01CCRPIO.value = "" ;
	document.forms[0].E01CCRPIN.value = "" ;

	setNewPIN() ;
	
	if( document.forms[0].E01CCRPIO.value != "" ){
		readPIN() ;
	
		if( document.forms[0].E01CCRPIN.value != "" ){
			document.forms[0].Submit.disabled = false ;
			
		}
	
	}
	
	top.client.document.PINPAD.Salir() ;
	
*/	

/*

	if( openPINPAD() && activeDevice() ){
		alert("Deslice la Tarjeta.") ;

		setNewPIN() ;
		
		if( document.forms[0].E01CCRPIO.value != "" ){
			readPIN() ;
		
			if( document.forms[0].E01CCRPIN.value != "" ){
				document.forms[0].Submit.disabled = false ;
				
			}
		
		}


	}

	top.client.document.PINPAD.Salir() ;

//**********************************************

	if(document.forms[0].OPT.value == '1'){
		setNewPIN() ;	
	}else{
		readPIN() ;
	}
*/

--%>


}


</script>

<%-- 
 if ( error.getERRNUM().equals("0")  ) {
      out.println("<SCRIPT Language=\"Javascript\">");
      out.println(" document.forms[0].OPT.value = '1' ;   ");
      out.println(" document.forms[0].leerPINB.value = 'Leer PIN' ; ");
      out.println(" document.forms[0].Submit.value = 'Validar PIN' ; ");
      out.println(" document.forms[0].activepin.disabled = false ; ");
      out.println(" top.client.document.PINPAD.Salir() ; ");
      out.println("</SCRIPT>");
 
 }else{
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println(" document.forms[0].OPT.value = '2' ;   ");
     out.println(" document.forms[0].leerPINB.value = 'Leer PIN' ; ");
     out.println(" document.forms[0].Submit.value = 'Asignar PIN' ; ");
     out.println(" document.forms[0].activepin.disabled = true ; ");
     out.println("</SCRIPT>");

 }

--%> 

</BODY>
</HTML>

