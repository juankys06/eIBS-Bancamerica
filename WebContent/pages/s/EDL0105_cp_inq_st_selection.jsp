<html>
<head>
<title>Account Statement</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>
<body nowrap>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
	   builtNewMenu(cp_i_opt);

</SCRIPT>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

   out.println("<SCRIPT> initMenu();  </SCRIPT>");
%> 


<h3 align="center"> Estado de Cuenta<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cp_inq_st_selection.jsp , EDL0105"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105I" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="15">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>No. Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E12DEACUN" size="9" maxlength="9" readonly value="<%= userPO.getCusNum().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E12CUSNA1" size="45" maxlength="45" readonly value="<%= userPO.getCusName().trim()%>">
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
                <input type="text" name="E12DEAPRO" size="4" maxlength="4" readonly value="<%= userPO.getProdCode().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <table class="tableinfo">
    <tr > 
      <td nowrap > 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="27%"> 
              <div align="right"><b>Tipo de Fecha  :</b></div>
            </td>
            <td nowrap width="1%">&nbsp;</td>
            <td nowrap width="15%"> 
              <input type="radio" name="E01VALBTH" value="B" checked>
              <b>Fecha de Proceso</b><b></b></td>
            <td nowrap width="10%">&nbsp;</td>
            <td nowrap width="47%"> 
              <input type="radio" name="E01VALBTH" value="V">
              <b>Fecha Valor</b></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="27%"> 
              <div align="right"> <b>Seleccione Fecha desde :</b></div>
            </td>
            <td nowrap width="1%">&nbsp;</td>
            <td nowrap width="15%"> 
              <div align="left"> 
                <input type="text" name="E01FRDTE1" size="2" maxlength="2" onkeypress="enterInteger()">
                <input type="text" name="E01FRDTE2" size="2" maxlength="2" onkeypress="enterInteger()">
                <input type="text" name="E01FRDTE3" size="2" maxlength="2" onkeypress="enterInteger()">
                <a href="javascript:DatePicker(document.forms[0].E01FRDTE1,document.forms[0].E01FRDTE2,document.forms[0].E01FRDTE3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a></div>
            </td>
            <td nowrap width="10%"> 
              <div align="right"><b>hasta :</b></div>
            </td>
            <td nowrap width="47%"> 
              <div align="left"> 
                <input type="text" name="E01TODTE1" size="2" maxlength="2" onkeypress="enterInteger()">
                <input type="text" name="E01TODTE2" size="2" maxlength="2" onkeypress="enterInteger()">
                <input type="text" name="E01TODTE3" size="2" maxlength="2" onkeypress="enterInteger()">
                <a href="javascript:DatePicker(document.forms[0].E01TODTE1,document.forms[0].E01TODTE2,document.forms[0].E01TODTE3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="27%" > 
              <div align="right"><b>Seleccione Monto desde :</b></div>
            </td>
            <td nowrap width="1%" >&nbsp;</td>
            <td nowrap width="15%" > 
              <div align="left"> 
                <input type="text" name="E01FRAMNT" size="15" maxlength="13" onkeypress="enterDecimal()">
              </div>
            </td>
            <td nowrap width="10%" > 
              <div align="right"><b>hasta :</b></div>
            </td>
            <td nowrap width="47%" > 
              <div align="left"> 
                <input type="text" name="E01TOAMNT" size="15" maxlength="13" onkeypress="enterDecimal()">
              </div>
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
