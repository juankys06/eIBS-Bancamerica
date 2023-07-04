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

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "msgCard" class= "datapro.eibs.beans.ECC006001Message"  scope="session" />
	
<script language="JavaScript">
function setShowStatus(){
	if(document.forms[0].E01CCRIST.value == "A") {
		document.forms[0].E01CCRSTS.value = "";
		causalinput.style.visibility = "hidden";
		causallabel.style.visibility = "hidden";
	} else {
		causalinput.style.visibility = "visible";
		causallabel.style.visibility = "visible";
	}
}
</script>
	
</head>
<body onload="processCard();"  >
<%--
ESTE OBJETO ACTIVEX SE DECLARA EN EL JSP QUE TIENE LA INFORMACION DEL 
CLIENTE.
ESTO PARA PODER EJECUTAR LLAMADOS A LAS DIFERENTES FUNCIONES
ENTRE REQUESTS SIN QUE SE DESCARGUE EL COMPONENTE DE LA MEMORIA.

<DIV style="visibility:hidden" >
	<OBJECT ID="DATACARD"
	  CLASSID="CLSID:219D546F-3583-4549-9A3E-1C8C79D43E8F"
	  CODEBASE="IIXBt.dll" declare="declare" >
	</OBJECT>
</DIV>
--%>

<h3 align="center">Tarjeta de <% if (userPO.getHeader2().equals("D")) out.print("Débito"); else out.print("Crédito"); %>
<BR>Nueva
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cc_aditional_card_new.jsp,ECC0060"> 
</h3>
<hr size="4">

<%
 if ( !error.getERRNUM().equals("0")  ) {
     //error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 
%> 
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0060" id="form1" name="form1" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
  <INPUT TYPE=HIDDEN NAME="E01CCRTCL" VALUE="S">
  <INPUT TYPE=HIDDEN NAME="E01TARTYP" VALUE="<%= userPO.getHeader2().trim()%>">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><B>Tipo de Tarjeta :</B> </div>
            </td>
            <td nowrap width="16%"> 
              	<input type="text" name="E01TARTY2" size="15" maxlength="14" value="<% if (userPO.getHeader2().equals("D")) out.print("Debito"); else out.print("Credito"); %>" readonly>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b></b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 

              </div>
            </td>
          </tr>           
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente Primario : </b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01PRICUN" size="9" maxlength="9" readonly value="<%= userPO.getCusNum().trim()%>">
			  </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre Primario : </b></div>
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
              <div align="right"><b>Numero Tarjeta : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left">
                <input type="text" name="E01CCRNUM" size="21" maxlength="20" value="<%= userPO.getHeader23().trim() %>" onKeypress="enterInteger()">
                <A href="javascript:readStripe();">
                <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Leer Tarjeta" align="absbottom" border="0"></A> 
				<%--
                <A href="javascript:GetPlastic4('E01CCRNUM', 'E01CCREXM', 'E01CCREXY', 'E01CCRISD', 'E01CCRISM', 'E01CCRISY', '', '102', 'BVLDATAPRO','2')">
                <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Lista Tarjetas" align="absbottom" border="0"></A> 
                --%>
               </div>
            </td>
          </tr> 
          <%-- 
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente Secundario : </b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01SECCUN" size="9" maxlength="9" value="<%= msgCard.getE01SECCUN().trim()%>">
                <a href="javascript:GetCustomerDescId('E01SECCUN','E01SECNA1','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a>
			  </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre Secundario : </b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01SECNA1" size="45" maxlength="45" readonly value="<%= msgCard.getE01SECNA1().trim()%>">
              </div>
            </td>
          </tr>   
           --%>              
        </table>
      </td>
    </tr>
  </table>
  
<%--  
  <h4> Informacion Basica</h4> 
  <table class="tableinfo" align="center">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0" align="center" >
           <tr id="trdark"> 
            <TD nowrap width="25%"> 
              <DIV align="right">Fecha de Expiracion :</DIV>
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
--%>  
  
<SCRIPT language="JavaScript">     

	//setShowStatus();     

</SCRIPT>
  
  <br>
  <div align="center"> 
	   <input id="EIBSBTN" type=submit name="Submit" value="Aceptar"  >
  </div>
  
  <SCRIPT language="JavaScript" >

function readStripe(){

	var resp = "" ;
	resp = top.client.document.DATACARD.DoReadStripe();
	
	if( resp.length < 19 ){
		switch( resp ){
			case "13" :
				resp = "Impresora no encontrada. Cod:" + resp ;
				break;
			case "23" :
				resp = "Error en la alimentación de tarjetas. Cod:" + resp ;
				break;
			default :
				resp = "Error al leer la tarjeta. Cod:" + resp ;
				break;
		}
		alert( resp );
		//return true ;
	}else{
		document.form1.E01CCRNUM.value = resp ;
		//return false ;
	}
	
}

function printCard( cardNumber , companyName , custName ){
	
	var msg ;
	var resp ;
	var isEerror = true ;
	
	custName = custName.toUpperCase() ;
	custName = custName.replace( /Ñ/g , "N"  ) ;
	custName = custName.replace( /Á/g , "A"  ) ;
	custName = custName.replace( /É/g , "E"  ) ;
	custName = custName.replace( /Í/g , "I"  ) ;
	custName = custName.replace( /Ó/g , "O"  ) ;
	custName = custName.replace( /Ú/g , "U"  ) ;
	custName = custName.substr( 0 , 20  ) ;
	
	var i = 0 ;
	var fmtCardNum = "" ;
	do{
		if( (i+4) > cardNumber.length ){
			fmtCardNum = fmtCardNum + cardNumber.slice(i) ;

		}else{
			fmtCardNum = fmtCardNum + cardNumber.slice(i,i+4) + " " ;
		}
		i = i + 4 ;
		
	}while( i < cardNumber.length )
	
	//cardNumber = fmtCardNum ;
	// El Banco solicitó que no se imprima el número de la tarjeta, solo el nombre.
	cardNumber = "" ;
	
	resp = top.client.document.DATACARD.DoPrintCard( cardNumber , companyName , custName ) ;
	
	switch(resp){
		case 14 :
			msg = "Error al expulsar la tarjeta. Cod:" + resp ;
			break ;
		case 20 :
			msg = "Error al escribir la banda. Cod:" + resp ;
			break ;
		case 100 :
			isError = false ;
			break ;
		default :
			msg = "Error al imprimir la tarjeta. Cod:" + resp ;
			break ;
	}

	if( isError ){
		alert( msg ) ;
		return false ;
	}else{
		return true ;
	}
	
}

function abort(){

	var resp ;
	resp = top.client.document.DATACARD.AbortCard() ;
	if( resp != 100 && resp != 0 ){
		alert( "Error expulsando la tarjeta. Cod:" + resp ) ;
		//return true ;
	}else {
		//return false ;
	}
	
}

function processCard(){

	if( document.form1.E01CCRNUM.value != ""  && 
		"<%=error.getERRNUM()%>" == "0"   ){

		var resp = printCard( document.form1.E01CCRNUM.value , " " , document.form1.E01PRINA1.value ) ;

		if( resp ){
		
			form1.Submit.value = "salir" ;
			form1.action = "<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0060?SCREEN=600&" 
				+ document.form1.E01CCRCRA.value  ;
		
		}else{
			abort();
		}
			
	}else {
		if( document.form1.E01CCRNUM.value != ""){
			abort() ;
		
		}
		
	}

}


</SCRIPT>
  
  
  
  </form>
</body>
</html>

