<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Tarjeta Adicional</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "msgCard" class= "datapro.eibs.beans.ECC006001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>
<body onload="top.client.location.reload();" >
<h3 align="center">Tarjeta de <% if (userPO.getHeader2().equals("D")) out.print("Débito"); else out.print("Crédito"); %>
<BR>Asignar
PIN<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ECC0060_cc_aditional_card_assign_pin.jsp"> 
</h3>
<hr size="4">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 
%> 
<%--
Este componente se ubica en el frame que tiene los datos del cliente para poder 
hacer llamados a las funciones en diferentes request.
<DIV style="visibility:hidden" >
	<OBJECT ID="PINPAD"
	  CLASSID="CLSID:2E8A70D6-2E7E-4A2B-BEAF-437BBF1126B6"
	  CODEBASE="intellipinactivex.dll">
	</OBJECT>
</DIV>
--%>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0060" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="7">
  <INPUT TYPE=HIDDEN NAME="E01CCRPIO" VALUE="">

  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><B>Tipo de Tarjeta :</B> </div>
            </td>
            <td nowrap width="16%"> 
              	<input type="text" name="E01TARTY2" size="25" maxlength="14" value="<% if (userPO.getHeader2().equals("D")) out.print("Tarjeta de Debito"); else out.print("Tarjeta de Credito"); %>" readonly>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><!-- <b>Tipo de Tarjeta :</b>  --></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
              	<!-- 
                <input type="text" name="E01CCRPRIM" size="21" maxlength="20" value="Secundaria" readonly>
                 -->
              </div>
            </td>
          </tr>    
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente Primario: </b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01PRICUN" size="9" maxlength="9" readonly value="<%= userPO.getCusNum().trim()%>">
			  </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre Primario: </b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01PRINA1" size="45" maxlength="45" readonly value="<%= userPO.getCusName().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta : </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" readonly name="E01CCRCRA" size="12" maxlength="9" value="<%= userPO.getAccNum().trim() %>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Numero de Tarjeta: </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" readonly name="E01CCRNUM" size="21" maxlength="20" value="<%= msgCard.getE01CCRNUM().trim()%>">
                </b> </div>
            </td>
          </tr>            
        </table>
      </td>
    </tr>
  </table>
  <h4> Informacion Basica</h4> 
  <table class="tableinfo" align="center" >
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0" align="center" >     
          <tr id="trdark"> 
            <TD nowrap width="25%"> 
              <DIV align="right">Fecha de Expiracion:</DIV>
            </TD><TD nowrap width="23%"> 
              <!-- <INPUT type="text" name="E01CCREXD" readonly size="3" maxlength="2" value="<%= msgCard.getE01CCREXD().trim()%>" onkeypress="enterInteger()">  -->
              <INPUT type="text" name="E01CCREXM" readonly size="3" maxlength="2" value="<%= msgCard.getE01CCREXM().trim()%>" onkeypress="enterInteger()">
              <INPUT type="text" name="E01CCREXY" readonly size="6" maxlength="4" value="<%= msgCard.getE01CCREXY().trim()%>" onkeypress="enterInteger()">
               
            </TD>
            
            <td nowrap width="25%"> 
              <div align="right">Fecha de Emision :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01CCRISD" readonly size="3" maxlength="2" value="<%= msgCard.getE01CCRISD().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01CCRISM" readonly size="3" maxlength="2" value="<%= msgCard.getE01CCRISM().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01CCRISY" readonly size="6" maxlength="4" value="<%= msgCard.getE01CCRISY().trim()%>" onkeypress="enterInteger()">
               
  			</td>
          </tr>                                                                                           
        </table>
      </td>
    </tr>
  </table>  

  <br>

	<table align="center" width="40%" >     
	  <tr align="center" > 
	   <%--
	    <TD nowrap width="25%"  > 
	      <INPUT type="button" id="EIBSBTN" name="activepin" value="Activar" size="30" onclick="actPinpad()" >
	    </TD>
	    --%>
	    
	    <TD nowrap width="25%"> 
	      <INPUT id="EIBSBTN" type="button" name="leerPIN" value="Asignar PIN" onClick="setNewPIN();" >
	    </TD>
	    
	    <%--
	    <TD nowrap width="23%"> 
			<INPUT id="EIBSBTN" type="button" name="Submit" value="Aceptar" onClick="enviar()" >
	    </TD>
	    --%>
	    
      </tr>                                                                                           
    </table>

  </form>

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

			var pinblock = top.client.document.PINPAD.AsignarPin( cardnum , "1" ) ;
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
				return "" ;
			
			}else {
				return pinblock ;
			}
			

}


function setNewPIN(){


	document.forms[0].E01CCRPIO.value = "" ;

	if( openPINPAD() && activeDevice() ){
		alert("Deslice la Tarjeta y luego Presione Aceptar.") ;
		
		var pb = setPIN( document.forms[0].E01CCRNUM.value ) ;
		if( pb.length == 16 ){

			//ASIGNAR EL PINBLOCK EN EL CAMPO EN EL QUE DEBE VIAJAR AL HOST
			document.forms[0].E01CCRPIO.value = pb ;
			alert("PIN Leido Correctamente.") ;
			document.forms[0].submit() ;
		
		}
		
		
	}
	
	top.client.document.PINPAD.Salir() ;

<%--
/*
			var pb = setPIN( document.forms[0].E01CCRNUM.value ) ;
			if( pb.length != 16 ){
				top.client.document.PINPAD.Salir();
				
			}else{
				top.client.document.PINPAD.Salir();			
				//ASIGNAR EL PINBLOCK EN EL CAMPO EN EL QUE DEBE VIAJAR AL HOST
				document.forms[0].E01CCRPIO.value = pb ;
			}
*/
--%>

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
		alert("Deslice la Tarjeta.") ;
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
		document.forms[0].E01CCRPIO.value = pb ;
	}
	
	top.client.document.PINPAD.Salir() ;
	
}



</script>

</body>
</html>
