<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Back Office</title>
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

	builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }

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
<h3 align="center">Papeles Comerciales - Back Office<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_cp.jsp,EDL0108B"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0108B" >
<input type="hidden" name="SCREEN"  value="4" >
<INPUT TYPE=HIDDEN NAME="SCRDEABNK" VALUE="<%= deal.getE02DEABNK().trim()%>">
<INPUT TYPE=HIDDEN NAME="SCRDEAACD" VALUE="<%= deal.getE02DEAACD().trim()%>">
   <table class="tableinfo" width="100%">
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

   <h4>  Información Básica</h4> 
  <table class="tableinfo" width="100%">
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
              <div align="right">Saldo Total  :</div>
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

	<TABLE class="tableinfo" width="100%">
   	  	<tr id="trdark"> 
   			<td nowrap align="center" width="15%" >Concepto</td>
   			<td nowrap align="center" width="15%" >Banco </td>
   			<td nowrap align="center" width="15%" >Agencia</td>
   			<td nowrap align="center" width="15%" >Moneda</td>
   			<td nowrap align="center" width="40%" >No. de Referencia</td>
		</tr>
   		<tr id="trclear"> 
      		<td nowrap align="center" width="15%" > 
          		<input type="text" name="E02REPOPE" value="<%= deal.getE02REPOPE().trim()%>" size="2" maxlength="2">
				<input type="hidden" name="E02DLIOGL" value="<%= deal.getE02DLIOGL().trim()%>"> 
          		<input type="text" name="E02REPCON" size="25" maxlength="25" readonly value="<%= deal.getE02REPCON().trim()%>" 
                  oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].SCRDEABNK.value,'','E02DLIOGL','E02REPOPE',document.forms[0].SCRDEAACD.value); return false;">
        	</td>
      		<td nowrap align="center" width="15%" > 
          		<input type="text" name="E02DLIOBN" size="2" maxlength="2" value="<%= deal.getE02DLIOBN().trim()%>" onkeypress="enterInteger()">
        	</td>
      		<td nowrap align="center"> 
          		<input type="text" name="E02DLIOBR" size="3" maxlength="3" value="<%= deal.getE02DLIOBR().trim()%>"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].SCRDEABNK.value,'','','','')" onkeypress="enterInteger()">
        	</td>
      		<td nowrap align="center" width="15%" > 
          		<input type="text" name="E02DLIOCY" size="3" maxlength="3" value="<%= deal.getE02DLIOCY().trim()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].SCRDEABNK.value,'','','','')">
        	</td>
      		<td nowrap align="center" width="40%" > 
          		<input type="text" name="E02DLIOAC" size="13" maxlength="12"  value="<%= deal.getE02DLIOAC().trim()%>"
                oncontextmenu="showPopUp(openingWireHelp,this.name,document.forms[0].SCRDEABNK.value,'','document.forms[0].E01DEACUN.value','','RT')"  onkeypress="enterInteger()">
      		</td>
    	</tr>
  	</table>    

  <h4>Límites</h4>
  <table  class="tableinfo" width="100%">
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
              <div align="center"><b>Monto Límite Disponible </b></div>
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
