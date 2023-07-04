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

<h3 align="center">Consulta Protesto Manual<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ptt_stop_pay_consulta.jsp , EDD0190"></h3>
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
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"><b><%= userPO.getHeader1().trim()%></b></div>
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
              <div align="left"><b><%= userPO.getCurrency().trim()%></b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b><%= userPO.getHeader6().trim()%></b> </div>
            </td>
          </tr>
		  <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Ejecutivo :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"><b><%= userPO.getHeader18().trim()%></b> </div>
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
                      <div align="left"><% if ( userPO.getCurrency().trim().equals("USD")) {out.print("US$ ");} else {out.print("$ ");} %><%= Util.fcolorCCY( userPO.getHeader15().trim())%></div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="right"><b>Sdo. Disponible : </b></div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="left"><b><% if ( userPO.getCurrency().trim().equals("USD")) {out.print("US$ ");} else {out.print("$ ");} %><%= Util.fcolorCCY( userPO.getHeader16().trim())%></b> </div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="right"><b>Sdo. Retención: </b></div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="left"><b><% if ( userPO.getCurrency().trim().equals("USD")) {out.print("US$ ");} else {out.print("$ ");} %><%= Util.fcolorCCY( userPO.getHeader17().trim())%></b> </div>
                    </td>
                  </tr>
        </table>
      </td>
    </tr>
  </table>
  <p><b> </b></p>
  
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
		
           <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Fecha del Movimiento :</b></div>
            </td>
            <td nowrap colspan="1"><%= stop.getRecord(29) %>/<%= stop.getRecord(28) %>/<%= stop.getRecord(30) %></td>
			<td nowrap > 
              <div align="right"><b>Moneda :</b></div>
			</td>
            <td nowrap colspan="1"><%= stop.getRecord(22) %></td>
          </tr>
		  
		  <tr id="trclear"> 
            <td nowrap> 
              <div align="right"><b>Banco :</b></div>
            </td>
            <td nowrap colspan="1"><%= stop.getRecord(20) %></td>
            <td nowrap colspan="2"><%= stop.getRecord(80) %></td>
		  </tr>
		  
		  <tr id="trdark"> 
            <td nowrap colspan="1"> 
              <div align="right"><b>Sucursal :</b></div>
            </td>
            <td nowrap colspan="1"> 
              <div align="left"><%= stop.getRecord(21) %></div>
            </td>
			<td nowrap colspan="2"> 
              <div align="left"><%= stop.getRecord(81) %></div>
            </td>
          </tr>
		  
		  <tr id="trclear"> 
            <td nowrap > 
              <div align="right"><b>N&uacute;mero Documento:</b></div>
            </td>
            <td nowrap colspan="1"> 
              <div align="left"><%= stop.getRecord(24) %></div>
            </td>
            <td nowrap > 
              <div align="right"><b>Monto :</b></div>
            </td>
            <td nowrap  colspan="1"> 
              <div align="left"><% if ( userPO.getCurrency().trim().equals("USD")) {out.print("US$ ");} else {out.print("$ ");} %><%= Util.fcolorCCY(stop.getRecord(7)) %></div>
            </td>
          </tr>
		  
		  <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Banco Tenedor :</b></div>
            </td>
            <td nowrap  colspan="1"><%= stop.getRecord(31) %></td>
			<td nowrap  colspan="2"><%= stop.getRecord(82) %></td>
		  </tr>
		  
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right"><b>Oficial :</b></div>
            </td>
            <td nowrap  colspan="1"><%= stop.getRecord(34) %></td>
			<td nowrap  colspan="2"><%= stop.getRecord(83) %></td>
		  </tr>
		  
		  <tr id="trdark"> 
            <td nowrap > 
              <div align="right"><b>Origen Protesto :</b></div>
            </td>
            <td nowrap  colspan="1"><%= stop.getRecord(43) %></td>
			<td nowrap  colspan="2"><%= stop.getRecord(84) %></td>
          </tr>
		   
		  <tr id="trclear"> 
            <td nowrap> 
              <div align="right"><b>Motivo :</b></div>
            </td>
            <td nowrap colspan="1"><%= stop.getRecord(33) %></td>
			<td nowrap colspan="2"><%= stop.getRecord(85) %></td>
		   </tr>
		   
		   <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Estado :</b></div>
            </td>
            <td nowrap colspan="1"><%= stop.getRecord(37) %></td>
			<td nowrap colspan="2"><%= stop.getRecord(86) %></td>
		   </tr>
		   
		   <tr id="trclear"> 
            <td nowrap> 
              <div align="right"><b>Usuario que Protesto :</b></div>
            </td>
            <td nowrap colspan="1"><%= stop.getRecord(38) %></td>
	        <td nowrap colspan="2">&nbsp;</td>
          </tr>
		  
		   <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Hora Protesto :</b></div>
            </td>
            <td nowrap colspan="1"><%= Util.formatTime(stop.getRecord(6)) %></td>
			<td nowrap> 
              <div align="right"><b>Fecha Protesto :</b></div>
            </td>
            <td nowrap colspan="1" ><%= Util.formatDate(stop.getRecord(3),stop.getRecord(4),stop.getRecord(5)) %></td>
		   </tr>
		   
		  <tr id="trclear"> 
            <td nowrap> 
              <div align="right"><b>Cuenta del Deposito :</b></div>
            </td>
            <td nowrap colspan="1"><%= stop.getRecord(45) %></td>
		   <td nowrap> 
              <div align="right"><b>Posting :</b></div>
            </td>
            <td nowrap  colspan="1"><%= stop.getRecord(44) %></td>
           </tr>
		   
		  <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Estado Original :</b></div>
            </td>
            <td nowrap colspan="1"><%= stop.getRecord(46) %></td>
			<td nowrap colspan="2">&nbsp;</td>
		   </tr>
		   
		  <tr id="trclear"> 
            <td nowrap> 
              <div align="right"><b>Banco Cliente:</b></div>
            </td>
            <td nowrap colspan="1"><%= stop.getRecord(47) %></td>
		    <td nowrap colspan="2"><%= stop.getRecord(87) %></td>
          </tr>
		  
		   <tr id="trdark"> 
		   <td nowrap> 
              <div align="right"><b>Sucursal Cliente :</b></div>
            </td>
            <td nowrap colspan="1"><%= stop.getRecord(48) %></td>
			<td nowrap colspan="2"><%= stop.getRecord(88) %></td>
          </tr>
		  
		  <tr id="trclear"> 
		   <td nowrap> 
              <div align="right"><b>Tipo Documento :</b></div>
            </td>
            <td nowrap colspan="1"><%= stop.getRecord(49) %></td>
			<td nowrap colspan="2"><%= stop.getRecord(89) %></td>
		   </tr>
		  
		   <tr id="trdark">  
		    <td nowrap> 
              <div align="right"><b>Motivo Rech. Original :</b></div>
            </td>
            <td nowrap colspan="1"><%= stop.getRecord(50) %></td>
			<td nowrap colspan="2"><%= stop.getRecord(90) %></td>
		   </tr>
		   
		 <tr id="trclear">  
		   <td nowrap> 
              <div align="right"><b>Fecha Envio Boletin :</b></div>
            </td>
            <td nowrap colspan="1"	><%= stop.getRecord(61) %>/<%= stop.getRecord(60) %>/<%= stop.getRecord(62) %></td>
			<td nowrap> 
              <div align="right"><b>Hora Envio :</b></div>
            </td>
            <td nowrap  colspan="1"><%= stop.getRecord(63) %></td>
          </tr>
		  
		  <tr id="trdark"> 
		    <td nowrap> 
              <div align="right"><b>Estado :</b></div>
            </td>
            <td nowrap colspan="1"><%= stop.getRecord(64) %></td>
			<td nowrap colspan="2"><%= stop.getRecord(91) %></td>
		   </tr>
		   
		  <tr id="trclear"> 
		   <td nowrap> 
              <div align="right"><b>Usuario Aclaración :</b></div>
            </td>
            <td nowrap colspan="1"><%= stop.getRecord(65) %></td>
			<td nowrap colspan="2">&nbsp;</td>
		   </tr>
		   
		  <tr id="trdark">    
		    <td nowrap> 
              <div align="right"><b>Fecha Aclaración :</b></div>
            </td>
            <td nowrap colspan="1"><%= stop.getRecord(67) %>/<%= stop.getRecord(66) %>/<%= stop.getRecord(68) %></td>
			<td nowrap> 
              <div align="right"><b>Hora Aclaración :</b></div>
            </td>
            <td nowrap  colspan="1"><%= stop.getRecord(69) %></td>
          </tr>
		  
         </table>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
