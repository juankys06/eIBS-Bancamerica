<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Emision de Protestos Manuales</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "stop" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "rtStop" class= "datapro.eibs.beans.EDD019001Message"  scope="session" />

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="JavaScript" >
function validar_rut(rut,dv){
   var resultado = 0 , resto = 0 , dijito  = 0;
   var ceros_izq = 9 - rut.length;      
   var numeros = new Array(4,3,2,7,6,5,4,3,2);

   for (var i = 1;i <= ceros_izq;i++ ){rut = "0" + rut}
   for(var i = 8; i >= 0; i--) {resultado = resultado + (rut.substring(i + 1,i) * numeros[i])}   
   
   resto = resultado % 11;
   dijito = 11 - resto;
   
   if ((dv == "K" || dv == "k") && resto == 1){return true}
   if (dv == 0 && resto == 0){return true}
   if (dv == dijito){return true}
   return false   
}



function enterInfo() {
 	
 var tipfir = trim(document.forms[0].TIPFIR.value);
 if ((tipfir == "1") || (tipfir == "3")) {
 	document.forms[0].submit();		
 }else {
    if ((document.forms[0].RUTGIR11.value <= 0 ) || (document.forms[0].NOMGIR1.value == "")){
        alert("Ingrese N° de Rut 1 y/o Nombre Girador 1");
		
	}else{	
        rutok = validar_rut(document.forms[0].RUTGIR11.value,document.forms[0].RUTGIR12.value)	
        if (rutok){
			 if ((document.forms[0].RUTGIR21.value <= 0 ) && (document.forms[0].NOMGIR2.value == "")){
			    rutgir2 = "";
			    document.forms[0].submit();
			  }else { 
			     if ((document.forms[0].RUTGIR21.value <= 0 ) || (document.forms[0].NOMGIR2.value == "")){
        			alert("Ingrese N° de Rut 2 y/o Nombre Girador 2");
				 }else{
			    	rutok = validar_rut(document.forms[0].RUTGIR21.value,document.forms[0].RUTGIR22.value)	
        			if (rutok){
						if (document.forms[0].NOMGIR2.value == " "){
							alert("Ingrese Nomgre Girador 2");
						}else {
							document.forms[0].submit();
						}
					}else {
		    			alert("N° de Rut 2, Erroneo");	
					 }
				}	 
			 }
		 }else {
		    alert("N° de Rut 1, Erroneo");	
		 }
	}	 	
 }
 
}

</SCRIPT>

</head>

<body>


<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

%>

<h3 align="center">Imprimir Protesto Manual<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ptt_stop_pay_print_data.jsp , EDD0190"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0190" >
  <input type=HIDDEN name="SCREEN" value="2">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="7">

  <input type=HIDDEN name="E01STPBNK" value="<%= userPO.getHeader10().trim()%>">
  <input type=HIDDEN name="E01STPBRN" value="<%= userPO.getHeader11().trim()%>">
  <input type=HIDDEN name="E01STPGLN" value="<%= userPO.getHeader12().trim()%>">

    <%
        int row;
		  try{row = Integer.parseInt(request.getParameter("ROW"));}catch(Exception e){row = 0;}
		  stop.setCurrentRow(row);
    %>
 <input type=HIDDEN name="ROW" value="<%= row %>">
 <input type=HIDDEN name="ROW1" value="<%= row %>">
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"><%= userPO.getHeader1().trim()%></div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><%= userPO.getHeader5().trim()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"><%= userPO.getIdentifier().trim()%></div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><%= userPO.getCurrency().trim()%></div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><%= userPO.getHeader6().trim()%></div>
            </td>
          </tr>
		  <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Ejecutivo :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"><%= userPO.getHeader18().trim()%></div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><%= userPO.getHeader19().trim()%></div>
            </td>
          </tr>
		  <tr id="trclear"> 
                    <td nowrap width="16%"> 
                      <div align="right"><b>Sdo. Contable :</b></div>
                    </td>
                    <td nowrap width="20%"> 
                      <div align="left"><% if ( userPO.getCurrency().trim().equals("USD")) {out.print("US$ ");} else {out.print("$ ");} %><%= Util.fcolorCCY(userPO.getHeader15().trim())%></div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="right"><b>Sdo. Disponible : </b></div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="left"><% if ( userPO.getCurrency().trim().equals("USD")) {out.print("US$ ");} else {out.print("$ ");} %><%= Util.fcolorCCY(userPO.getHeader16().trim())%></div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="right"><b>Sdo. Retención: </b></div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="left"><% if ( userPO.getCurrency().trim().equals("USD")) {out.print("US$ ");} else {out.print("$ ");} %><%= Util.fcolorCCY( userPO.getHeader17().trim())%></div>
                    </td>
                  </tr>
        </table>
      </td>
    </tr>
  </table>
  <BR>
  <table class="tableinfo" >
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="70%" align="center" border="0">
           <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Fecha del Cheque : </b></div>
            </td>
            <td nowrap> 
              <div align="left"><%= Util.formatDate(stop.getRecord(3),stop.getRecord(4),stop.getRecord(5)) %></div>
            </td>
			<td nowrap > 
              <div align="right"><b>Hora : </b></div>
            </td>
            <td nowrap > 
              <div align="left"><%= Util.formatTime(stop.getRecord(6)) %></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right"><b>N&uacute;mero de Cheque : </b></div>
            </td>
            <td nowrap > 
              <div align="left"> <%= Util.formatCell(stop.getRecord(1)) %></div>
            </td>
            <td nowrap > 
              <div align="right"><b>Monto : </b></div>
            </td>
            <td nowrap > 
              <div align="left"><% if ( userPO.getCurrency().trim().equals("USD")) {out.print("US$ ");} else {out.print("$ ");} %><%= Util.fcolorCCY(stop.getRecord(7)) %></div>
            </td>
          </tr>
		  
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Oficial : </b></div>
            </td>
            <td nowrap > <%= Util.formatCell(stop.getRecord(2)) %> 
            </td>
			<td nowrap > 
              <div align="right"><b>Origen Protesto : </b></div>
            </td>
            <td nowrap > <%= Util.formatCell(stop.getRecord(43)) %> 
            </td>
		  </tr>
		  		   
		  <tr id="trclear"> 
            <td nowrap > 
              <div align="right"><b>Sucursal Origen : </b></div>
            </td>
            <td nowrap ><%= Util.formatCell(stop.getRecord(0)) %> 
            </td>
			<td nowrap > 
              <div align="right"><b>Motivo : </b></div>
            </td>
            <td nowrap name="DHSRES" ><%= Util.formatCell(stop.getRecord(33)) %> 
            </td>
		  </tr>
         
         <tr id="trdark"> 
            <td nowrap width="44%" > 
              <div align="right"><b>Tipo de Firma : </b></div>
            </td>
            <td nowrap  colspan="3"> 
              <SELECT name="TIPFIR" readonly>
				<OPTION value="1" >Cliente</OPTION>
				<OPTION value="2" >Representante</OPTION>
				<OPTION value="3" >Sin Firmas</OPTION>
		     </SELECT>     </td>
          </tr> 
		  
         <tr id="trclear"> 
            <td nowrap> 
              <div align="right"><b>Nombre Girador 1 : </b></div>
            </td>
            <td nowrap  colspan="3"> 
              <input type="text" name="NOMGIR1" size="36" maxlength="35" value="" >
     		</td>
          </tr>
		  <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>N° de Rut 1 : </b></div>
            </td>
            <td colspan="3">
        		<input type="text" name="RUTGIR11" maxlength="9" size="9" onKeyPress=enterInteger()>
       			 - 
        		<input type="text" name="RUTGIR12" maxlength="1" size="2" >
      		</td>
          </tr>
		  <tr id="trclear"> 
            <td nowrap> 
              <div align="right"><b>Nombre Girador 2 : </b></div>
            </td>
            <td nowrap  colspan="3"> 
              <input type="text" name="NOMGIR2" size="36" maxlength="35" value="">
     		</td>
          </tr>
		  <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>N° de Rut 2 : </b></div>
            </td>
           <td colspan="3">
        		<input type="text" name="RUTGIR21" maxlength="9" size="9" onKeyPress=enterInteger()>
       			 - 
        		<input type="text" name="RUTGIR22" maxlength="1" size="2" >
      		</td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
<BR> 
  <div align="center"> 
    <img class="imgfilter" name="Submit" src="<%=request.getContextPath()%>/images/s/bt_submit.gif" 
     onMouseDown="this.className='imgfilterpress'" onMouseUp="this.className='imgfilter'" onClick="enterInfo()">
  </div>
  </form>
</body>
</html>
