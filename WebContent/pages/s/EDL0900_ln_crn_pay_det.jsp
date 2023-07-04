<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<%@ page import = "java.lang.Object" %>
<HTML>
<HEAD>
<TITLE>
Detalle de Cuota
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="header" class="datapro.eibs.beans.EDL090001Message"  scope="session" />

<jsp:useBean id= "list" class= "datapro.eibs.beans.JBListRec"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


</HEAD>

<BODY>

<form>
  <p> 
    <input TYPE=HIDDEN name="SCREEN" value="500">
    <INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
  </p>

  <h3 align="center">Detalle de la Cuota<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ln_crn_pay_det.jsp,EDL0900"> 
  </h3>
  <hr size="4">

<h4></h4>
  <%
   int row = Integer.parseInt(request.getParameter("Row"));
   list.setCurrentRow(row);
 %> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E02CUN2" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E02NA12" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E02ACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E02PRO2" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  <h4></h4>
  <BR>
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="25%" height="23" > 
              <div align="right">Fecha de Apertura :</div>
            </td>
            <td nowrap width="17%" height="23" > 
              <div align="right"><%= userPO.getHeader10().trim()%></div>
            </td>
            <td nowrap width="23%" height="23" > 
              <div align="right">Tasa de Inter&eacute;s :</div>
            </td>
            <td nowrap width="35%" height="23" > 
              <div align="right"><%= userPO.getHeader13().trim()%></div>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="25%" > 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap width="17%" > 
              <div align="right"><%= userPO.getHeader11().trim()%></div>
            </td>
            <td nowrap width="23%" > 
              <div align="right">Per&iacute;odo Base :</div>
            </td>
            <td nowrap width="35%" > 
              <div align="right"><%= userPO.getHeader14().trim()%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" height="18" > 
              <div align="right">Monto Original :</div>
            </td>
            <td nowrap width="17%" height="18" > 
              <div align="right"><%= userPO.getHeader12().trim()%></div>
            </td>
            <td nowrap width="23%" height="18" > 
              <div align="right">Tipo de C&aacute;lculo :</div>
            </td>
            <td nowrap width="35%" height="18" > 
              <div align="right"><%= userPO.getHeader15().trim()%></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trclear"> 
            <td nowrap width="14%" height="23" >Cuota N&uacute;mero : <%= list.getRecord(0) %></td>
            <td nowrap width="4%" height="23" >&nbsp;</td>
            <td nowrap width="31%" height="23" > 
              <div align="left">Fecha a Pagar: <%= list.getRecord(1) %></div>
            </td>
            <td nowrap width="4%" height="23" >&nbsp;</td>
            <td nowrap width="47%" height="23" > 
              <div align="left">Fecha de Pago : <%= list.getRecord(9) %></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  
  <table class="tableinfo">
    <tr id=trdark> 
      <th align=CENTER nowrap width="11%" >Descripci&oacute;n</th>
      <th align=CENTER nowrap width="9%" >Monto a Pagar</th>
      <th align=CENTER nowrap width="7%" >Monto Pagado</th>
    </tr>
    <%
    String couteDate = list.getRecord(1); 
    do {
    	if (!couteDate.equals(list.getRecord(1))) { 
	   		break;
	 	}
     	for (int i=31;i<51;i++) {
	    	if (!(list.getRecord(i).equals("0.00") ) ){  %> 
	    <tr> 
	      <td align=CENTER  nowrap width="11%"><%= list.getRecord(i-20) %></td>
	      <td align=CENTER  nowrap width="9%"> 
	        <div align="right"><%= list.getRecord(i) %></div>
	      </td>
	      <td align=CENTER  nowrap width="7%"> 
	        <div align="right"><%= list.getRecord(i+20) %></div>
	      </td>
	    </tr>
	    <%   }
	 	}
	} while (list.getNextRow());
	list.setCurrentRow(row);
	%> 
  </table>
  <br>
  
  <table class="tableinfo">
    <tr id=trdark> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="20%" height="18" ><b>Total</b></td>
            <td nowrap width="3%" height="18" >&nbsp;</td>
             <td nowrap width="37%" height="18" >Monto a Pagar :<%= list.getRecord(71) %></td>
            <td nowrap width="4%" height="18" >&nbsp;</td>
            <td nowrap width="36%" height="18" > 
              <div align="left">Monto Pagado :<%= list.getRecord(72) %></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  </FORM>

</BODY>
</HTML>
