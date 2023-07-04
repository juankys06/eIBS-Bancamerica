<html>
<head>
<title>Suspension Debito Automatico</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "lnAutoDebit" class= "datapro.eibs.beans.EDL015210Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   <%
	if ( userPO.getHeader23().equals("G") ||  userPO.getHeader23().equals("V")){
	%>
		builtNewMenu(ln_i_1_opt);
	<%   
	}
	else  {
	%>
		builtNewMenu(ln_i_2_opt);
	<%   
	}
	%>


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
 if ( !userPO.getPurpose().equals("NEW") ) {
    out.println("<SCRIPT> initMenu(); </SCRIPT>");
 }
%>

<h3 align="center">Suspension Debito Automatico<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_cancel_auto_debit.jsp, EDL0150"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0150" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="34">
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
                <input type="text" name="E10DEACUN" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" name="E10CUSNA1" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
               </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E10DEAACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E10DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E10DEAPRO" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n B&aacute;sica</h4>
  <table class="tableinfo">
    <tr > 
      <td> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td width="39%"> 
              <div align="right">Fecha Inicio :</div>
            </td>
            <td width="61%">
              <input type="text" name="E10DLASDM" size="2" maxlength="2" value="<%= lnAutoDebit.getE10DLASDM().trim()%>" readonly>
              <input type="text" name="E10DLASDD" size="2" maxlength="2" value="<%= lnAutoDebit.getE10DLASDD().trim()%>" readonly>
              <input type="text" name="E10DLASDY" size="2" maxlength="2" value="<%= lnAutoDebit.getE10DLASDY().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="39%" > 
              <div align="right">Fecha Vencimiento :</div>
            </td>
            <td width="61%">
              <input type="text" name="E10DLAMAM" size="2" maxlength="2" value="<%= lnAutoDebit.getE10DLAMAM().trim()%>" readonly>
              <input type="text" name="E10DLAMAD" size="2" maxlength="2" value="<%= lnAutoDebit.getE10DLAMAD().trim()%>" readonly>
              <input type="text" name="E10DLAMAY" size="2" maxlength="2" value="<%= lnAutoDebit.getE10DLAMAY().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="39%" > 
              <div align="right">Motivo :</div>
            </td>
            <td width="61%" > 
              <input type="text" name="E10DLAMO1" size="30" maxlength="30" value="<%= lnAutoDebit.getE10DLAMO1().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="39%"> 
              <div align="right"></div>
            </td>
            <td width="61%"> 
              <input type="text" name="E10DLAMO2" size="30" maxlength="30" value="<%= lnAutoDebit.getE10DLAMO2().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="39%"> 
              <div align="right">Status :</div>
            </td>
            <td width="61%"> 
              <select name="E10DLASTS" disabled>
               <option value=" " <% if (!(lnAutoDebit.getE10DLASTS().equals("A") || lnAutoDebit.getE10DLASTS().equals("I") || lnAutoDebit.getE10DLASTS().equals("M")) ) out.print("SELECTED"); %>></option>
               <option value="A" <% if (lnAutoDebit.getE10DLASTS().equals("A")) out.print("SELECTED"); %>>Activo</option>
               <option value="I" <% if (lnAutoDebit.getE10DLASTS().equals("I")) out.print("SELECTED"); %>>Inactivo</option>
               <option value="M" <% if (lnAutoDebit.getE10DLASTS().equals("M")) out.print("SELECTED"); %>>Vencido</option>
              </select>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Ultima Modificacion</h4>
  <table class="tableinfo">
    <tr > 
      <td> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td width="25%"> 
              <div align="right">Usuario :</div>
            </td>
            <td width="25%">
              <input type="text" name="E10DLAUSR" size="12" maxlength="10" value="<%= lnAutoDebit.getE10DLAUSR().trim()%>" readonly>
            </td>
            <td width="25%"> 
              <div align="right">Fecha :</div>
            </td>
            <td width="25%">
              <input type="text" name="E10DLALDM" size="2" maxlength="2" value="<%= lnAutoDebit.getE10DLALDM().trim()%>" readonly>
              <input type="text" name="E10DLALDD" size="2" maxlength="2" value="<%= lnAutoDebit.getE10DLALDD().trim()%>" readonly>
              <input type="text" name="E10DLALDY" size="2" maxlength="2" value="<%= lnAutoDebit.getE10DLALDY().trim()%>" readonly>
            </td>
          </tr>
         </table>
      </td>
    </tr>
  </table>
  
</form>
</body>
</html>
