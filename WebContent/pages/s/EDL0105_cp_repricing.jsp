<html>
<head>
<title>Exchange Rate</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "cdRep" class= "datapro.eibs.beans.EDL010512Message" scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(cp_m_opt);


</SCRIPT>


</head>

<body nowrap>



<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 if ( !userPO.getPurpose().equals("NEW") ) {
    out.println("<SCRIPT> initMenu();  </SCRIPT>");
 }
%>

<h3 align="center">Mantenimiento de Precio de Mercado<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cp_repricing.jsp, EDL0105"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="10">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>No. Cliente:</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E12DEACUN" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E12CUSNA1" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E12DEAACC" size="12" maxlength="9" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E12DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E12DEAPRO" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Opciones de Call/Put</h4>
  <table class="tableinfo">
    <tr > 
      <td> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td width="39%" > 
              <div align="right">Fecha de Call :</div>
            </td>
            <td width="61%">
              <input type="text" name="E12CALDT1" size="2" maxlength="2" value="<%= cdRep.getE12CALDT1().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E12CALDT2" size="2" maxlength="2" value="<%= cdRep.getE12CALDT2().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E12CALDT3" size="2" maxlength="2" value="<%= cdRep.getE12CALDT3().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E12CALDT1,document.forms[0].E12CALDT2,document.forms[0].E12CALDT3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="ayuda" align="absmiddle" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="39%" > 
              <div align="right">Fecha de Put :</div>
            </td>
            <td width="61%" > 
              <input type="text" name="E12PUTDT1" size="2" maxlength="2" value="<%= cdRep.getE12PUTDT1().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E12PUTDT2" size="2" maxlength="2" value="<%= cdRep.getE12PUTDT2().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E12PUTDT3" size="2" maxlength="2" value="<%= cdRep.getE12PUTDT3().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E12PUTDT1,document.forms[0].E12PUTDT2,document.forms[0].E12PUTDT3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="ayuda" align="absmiddle" border="0"></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="39%"> 
              <div align="right">Retasación :</div>
            </td>
            <td width="61%"> 
              <input type="text" name="E12DEALSR" size="15" maxlength="13" value="<%= cdRep.getE12DEALSR().trim()%>" onkeypress="enterSignDecimal()">
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0" >
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
   <br>
  <div align="center"> 
	   <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</form>
</body>
</html>
