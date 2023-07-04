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


<H3 align="center"><% if (userPO.getHeader20().equals("DO_CLEAR")) out.print("Aclarar"); else out.print("Entregar");%> Protesto del Documento<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ptt_stop_pay_clear.jsp , EDD0190"> 
</H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0190" >
  <input type=HIDDEN name="SCREEN" value="6">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="2">

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
              <div align="left"><b><%= userPO.getHeader1().trim()%></b> </div>
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
              <div align="left"><b><%= userPO.getCurrency().trim()%></b> </div>
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
                      <div align="left"><b><% if ( userPO.getCurrency().trim().equals("USD")) {out.print("US$ ");} else {out.print("$ ");} %><%= Util.fcolorCCY(userPO.getHeader15().trim())%></b> </div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="right"><b>Sdo. Disponible : </b></div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="left"><b><% if ( userPO.getCurrency().trim().equals("USD")) {out.print("US$ ");} else {out.print("$ ");} %><%= Util.fcolorCCY(userPO.getHeader16().trim())%></b> </div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="right"><b>Sdo. Retención: </b></div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="left"><b><% if ( userPO.getCurrency().trim().equals("USD")) {out.print("US$ ");} else {out.print("$ ");} %><%= Util.fcolorCCY(userPO.getHeader17().trim())%></b> </div>
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
            <td nowrap width="40%"> 
              <div align="right">Fecha Protesto :</div>
            </td>
            <td nowrap> 
              <div align="left"><%= Util.formatDate(stop.getRecord(3),stop.getRecord(4),stop.getRecord(5)) %></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">N&uacute;mero de Cheque :</div>
            </td>
            <td nowrap> 
              <div align="left"><%= rtStop.getDHSCHK().trim()%></div>
            </td>
            
          </tr>
          
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Monto del Cheque :</div>
            </td>
            <td nowrap> 
              <div align="left"><% if ( userPO.getCurrency().trim().equals("USD")) {out.print("US$ ");} else {out.print("$ ");} %><%= Util.fcolorCCY(stop.getRecord(7)) %></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Oficial :</div>
            </td>
            <td nowrap><%= rtStop.getDHSOFC().trim()%></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Origen Protesto :</div>
            </td>
            <td nowrap><%= rtStop.getDHSORG().trim()%></td>
          </tr>
		  <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Sucursal Origen :</div>
            </td>
            <td nowrap><%= rtStop.getDHSOBR().trim()%></td>
			
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Motivo :</div>
            </td>
            <td nowrap><%= rtStop.getDHSRES().trim()%></td>
          </tr>
         
          
        </table>
      </td>
    </tr>
  </table>
 
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="enviar">
  </p>
  </form>
</body>
</html>
