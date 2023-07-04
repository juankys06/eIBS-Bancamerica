<html>
<head>
<title>Esquema de Pagos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "pmnt" class= "datapro.eibs.beans.JBListRec"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "userInfo" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

</head>
<body nowrap>
<%
	 pmnt.initRow();
    int blank_row = 5;
    int max_row = 361;
    int row;
    int total_row;
    try {
      row = Integer.parseInt(request.getParameter("ROW"));
    }
    catch (Exception e) {
      row = 0;
    }
    if ( (row == 0) || (row < pmnt.getLastRow()) ) {
      total_row = pmnt.getLastRow() + 1 + blank_row;
    }
    else {
		total_row = row;       
    }
%> 

<SCRIPT>

function checkRowValue() {
  var r = <%= total_row %> + parseInt(document.forms[0].TEMP_ROW.value);
  if (r > <%= max_row %>) {
    alert("Valor fuera de limites, el numero maximo es 361.");
  }
  else {
    document.forms[0].ROW.value = r + "";
  }
}

function submitThis(option) {
  document.forms[0].opt.value = option;
  document.forms[0].submit();
}

function isYear(field) {
var year = parseInt(field.value)
if ( field.value.length > 0 ) {

  if ( isNaN(year) ) {
   alert(" Solamente admite valores numericos.")
   field.focus()
   field.value = ""
  }
else {
   if ( (""+year).length < 4 || year == 0 ) {
   alert("Please, enter a valid year ( 4 digits )")
   field.focus()
	}
  }
 }
}

</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
%> 

<h3 align="center"> Plan de Pagos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_pay_pln_det,EDL0130"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="10">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= total_row %>">
  <h4>Generaci&oacute;n Automatica</h4>
        
  <table class="tableinfo">
    <tr id="trclear"> 
      <td nowrap width="14%"  bordercolor="#FFFFFF"> 
        <div align="right">N&uacute;mero de Pagos : </div>
      </td>
      <td nowrap width="42%"  bordercolor="#FFFFFF"> 
        <div align="left"> 
          <input type="text" name="NUMBER" size="4" maxlength="3">
        </div>
      </td>
      <td nowrap width="10%"  bordercolor="#FFFFFF"> 
        <div align="right">Monto : </div>
      </td>
      <td nowrap width="9%"  bordercolor="#FFFFFF"> 
        <input type="text" name="AMOUNT" size="14" maxlength="13">
      </td>
      <td nowrap rowspan="2"  bordercolor="#FFFFFF" bgcolor="#FFFFFF" width="6%"><a href="javascript:submitThis(2)"><img src="<%=request.getContextPath()%>/images/s/generar_on.gif" alt="generate" align="absmiddle" border="0" width="70" height="55" ></a></td>
    </tr>
    <tr id="trdark"> 
      <td nowrap width="14%"  bordercolor="#FFFFFF"> 
        <div align="right">Primera Fecha de Pago:</div>
      </td>
      <td nowrap width="42%"  bordercolor="#FFFFFF"> <%
          if ( userInfo.getE01DTF().equals("YMD") ) {
        %> 
              <input type="text" name="DATE3" size="3" maxlength="2">
              <input type="text" name="DATE1" size="3" maxlength="2">
              <input type="text" name="DATE2" size="3" maxlength="2" >
              <font face="Arial" size="2">(yy/mm/dd)</font>

        <%
          }
          else if ( userInfo.getE01DTF().equals("MDY") ) {
        %>
              <input type="text" name="DATE1" size="3" maxlength="2">
              <input type="text" name="DATE2" size="3" maxlength="2">
              <input type="text" name="DATE3" size="3" maxlength="2" >
              <font face="Arial" size="2">(mm/dd/yy)</font>

        <%
          }
          else {
        %>
              <input type="text" name="DATE2" size="3" maxlength="2">
              <input type="text" name="DATE1" size="3" maxlength="2">
              <input type="text" name="DATE3" size="3" maxlength="2" >
              <font face="Arial" size="2">(dd/mm/yy)</font>


        <%
          }
        %>
		</td>
      <td nowrap width="10%"  bordercolor="#FFFFFF"> 
        <div align="right">Frecuencia : </div>
      </td>
      <td nowrap width="9%"  bordercolor="#FFFFFF"> 
        <input type="text" name="FREQUENCY" size="5" maxlength="4">
        <select name="CODE">
          <option value="D" selected>Día(s)</option>
          <option value="M">Mes(es)</option>
          <option value="Y">Año(s)</option>
        </select>
      </td>
    </tr>
  </table>
  <h4>&nbsp;</h4>
  <table class="tableinfo">
    <tr id="trdark"> 
      <td nowrap bordercolor="#FFFFFF"> 
        <div align="center">Pago N&uacute;mero</div>
      </td>
      <td nowrap bordercolor="#FFFFFF"> 
        <div align="center">Fecha</div>
      </td>
      <td nowrap bordercolor="#FFFFFF"> 
        <div align="center">Principal</div>
      </td>
      <td nowrap bordercolor="#FFFFFF">
        <div align="center">Inter&eacute;s</div>
      </td>
    </tr>
    <%
	 				 pmnt.initRow();
                while (pmnt.getNextRow()) {
	      %> 
    <tr id="trclear"> 
      <td nowrap bordercolor="#FFFFFF"> 
        <div align="center"> 
          <input type="text" name="DLPPNU_<%= pmnt.getCurrentRow() %>" size="4" maxlength="3"  value="<%= pmnt.getRecord(0) %>">
        </div>
      </td>
      <td nowrap bordercolor="#FFFFFF"> 
        <div align="center"> 
          <input type="text" name="DLPPD1_<%= pmnt.getCurrentRow() %>" size="2" maxlength="2"  value="<%= pmnt.getRecord(1) %>">
          <input type="text" name="DLPPD2_<%= pmnt.getCurrentRow() %>" size="2" maxlength="2"  value="<%= pmnt.getRecord(2) %>">
          <input type="text" name="DLPPD3_<%= pmnt.getCurrentRow() %>" size="2" maxlength="2"  value="<%= pmnt.getRecord(3) %>">
        </div>
      </td>
      <td nowrap bordercolor="#FFFFFF" > 
        <div align="center"> 
          <input type="text" name="DLPPRI_<%= pmnt.getCurrentRow() %>" size="17" maxlength="13" value="<%= pmnt.getRecord(4) %>" id="txtright" onKeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap bordercolor="#FFFFFF" >
        <div align="center">
          <input type="text" name="DLPINT_<%= pmnt.getCurrentRow() %>" size="17" maxlength="13" value="<%= pmnt.getRecord(5) %>" id="txtright" onKeypress="enterDecimal()">
        </div>
      </td>
    </tr>
    <%
                }
			for (int i=pmnt.getRow(); i < total_row; i++) {
    %> 
    <tr id="trclear"> 
      <td nowrap bordercolor="#FFFFFF"> 
        <div align="center"> 
          <input type="text" name="DLPPNU_<%= i %>" size="4" maxlength="3">
        </div>
      </td>
      <td nowrap bordercolor="#FFFFFF"> 
        <div align="center"> 
          <input type="text" name="DLPPD1_<%= i %>" size="2" maxlength="2">
          <input type="text" name="DLPPD2_<%= i %>" size="2" maxlength="2">
          <input type="text" name="DLPPD3_<%= i %>" size="2" maxlength="2">
        </div>
      </td>
      <td nowrap bordercolor="#FFFFFF" > 
        <div align="center"> 
          <input type="text" name="DLPPRI_<%= i %>" size="17" maxlength="15" value="" id="txtright">
        </div>
      </td>
      <td nowrap bordercolor="#FFFFFF" >
        <div align="center">
          <input type="text" name="DLPINT_<%= i %>" size="17" maxlength="15" value="" id="txtright">
        </div>
      </td>
    </tr>
    <%
                }
    %> 
  </table>
 
  <table  class="tbenter" >
    <tr> 
      <td > 
        <div align="right">Cantidad de Registros a A&ntilde;adir : 
          <input type="text" name="TEMP_ROW" size="4" maxlength="3" value="0" onBlur="checkRowValue()">
        </div>
      </td>
      <td bordercolor="#FFFFFF" bgcolor="#FFFFFF" width="8%"><a href="javascript:submitThis(3)"><img src="<%=request.getContextPath()%>/images/s/applicar_on.gif" alt="apply" align="absmiddle" border="0" ></a></td>
    </tr>
  </table>
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
  </form>
</body>
</html>
