<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Commercial Paper - Back Office</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="deal" class="datapro.eibs.beans.EDL010802Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
  
<SCRIPT Language="Javascript">

</SCRIPT>

</head>
<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 
<h3 align="center">Papeles Comerciales  - Back Office<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_cp_mod.jsp,EDL0108B"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0108B" >
<input type="hidden" name="SCREEN"  value="4" >
<INPUT TYPE=HIDDEN NAME="SCRACMBNK" VALUE="<%= deal.getE02DEABNK().trim()%>">

   <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="20%" > 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="80%" > 
              <div align="left"> 
                <%= deal.getE02DEAACC().trim()%>
               </div>
            </td>
		  </tr>
          <tr id="trdark">            
            <td nowrap width="20%" > 
              <div align="right"><b>Cliente :</b> </div>
            </td>
            <td nowrap width="80%"> 
              <div align="left"> 
                <%= deal.getE02DLICUS().trim()%>&nbsp;&nbsp;
                <%= deal.getE02DLINAM().trim()%>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%"> 
              <div align="right"><b>No. Cliente :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <%= deal.getE02DEACUN().trim()%>&nbsp;&nbsp;
                <%= deal.getE02CUSNA1().trim()%>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

   <h4>Informacion Básica</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha de Proceso/Orden :</div>
            </td>
            <td nowrap width="23%"> 
              <%= Util.formatDate(deal.getE02DLITD1().trim(),deal.getE02DLITD2().trim(),deal.getE02DLITD3().trim())%>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Fecha Valor :</div>
            </td>
            <td nowrap width="27%"> 
            	<%= Util.formatDate(deal.getE02DLISD1().trim(),deal.getE02DLISD2().trim(),deal.getE02DLISD3().trim())%>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Monto Nominal :</div>
            </td>
            <td nowrap width="23%"> 
				<%= deal.getE02DLIFCV().trim()%> 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Precio de Venta/Compra :</div>
            </td>
            <td nowrap width="27%"> 
 				<%= deal.getE02DLIPRC().trim()%>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" height="37"> 
              <div align="right">Interés Acumulado :</div>
            </td>
            <td nowrap width="23%" height="37"> 
				<%= deal.getE02DLIINT().trim()%>
            </td>
            <td nowrap width="25%" height="37"> 
              <div align="right">Saldo Total :</div>
            </td>
            <td nowrap width="27%" height="37">
              <%= deal.getE02DLINET().trim()%>
                        
            </td>
          </tr>                  
        </table>
      </td>
    </tr>
  </table>  
  <h4> Cuenta de Pago</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Banco :</div>
            </td>
            <td nowrap width="23%"> 
				<%= deal.getE02DLIOBN().trim()%>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Agencia :</div>
            </td>
            <td nowrap width="27%"> 
				<%= deal.getE02DLIOBR().trim()%>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Moneda :</div>
            </td>
            <td nowrap width="23%"> 
				<%= deal.getE02DLIOCY().trim()%>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Cuenta :</div>
            </td>
            <td nowrap width="27%"> 
				<%= deal.getE02DLIOAC().trim()%>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>    

  <h4>Límites</h4>
  <table  class="tableinfo" >
    <tr > 
      <td nowrap> 
        <table width="100%">
          <tr id="trdark"> 
            <td nowrap>&nbsp;</td>
            <td nowrap  colspan="2"> 
              <div align="center"><b>Monto Límite</b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>Monto Límite Utilizado</b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>Monto Límite Disponible</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap>Líneas de Crédito</td>
            <td nowrap  colspan="2"> 
              <div align="right"><%= Util.fcolorCCY(deal.getE02DLSAMT1())%></div>
            </td>
            <td nowrap > 
              <div align="right"><%= Util.fcolorCCY(deal.getE02DLSAMT3())%></div>
            </td>
            <td nowrap > 
              <div align="right"><b><%= Util.fcolorCCY(deal.getE02DLSAMT2())%></b></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  <br>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
<div align="center"> 
	   <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>      </td>
    </tr>
    <tr bgcolor="#FFFFFF"> 
      <td> 
        <div align="center"> </div>
      </td>
    </tr>
  </table>
  <BR>
  </form>
</body>
</html>
