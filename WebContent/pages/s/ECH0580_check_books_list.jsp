<HTML>
<HEAD>
<TITLE></TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "checkBooks" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id="rtBasic" class="datapro.eibs.beans.ECH056503Message"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/e/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
<!--- hide script from old browsers
var facc
function FValidateControlF(control) {
  var cl
  var checked = false
  cl = control.length

  for(i=0;i<cl;i++){
    if(control[i].checked){
      posif = i
      facc = control[i].value
      checked = true
      break
    }
  }
  
  if(cl == undefined){
    checked = true
  }
  
  return checked
}
function FSubmitValidation() {
 if (!FValidateControlF(document.forms[0].CHKBCORR)){
      alert("You must select a ChecBook for the action")
      return false
 }
 return true
 }
</script> 
</head>

<body>

<h3 align="center">Cambio de Estatus de Cheques y Chequeras<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="check_books_list.jsp, ECH0580"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH0580"  onsubmit="return FSubmitValidation()">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="300">
<INPUT TYPE=HIDDEN NAME="ACTION" VALUE="">

<%
	if ( checkBooks.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
        <div align="center"> <font size="3"><b> No hay resultados para esta cuenta </b></font></div>
      </TD></TR>
   		</TABLE>
  <%   		
	}
	else {
%> 
 <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E03ACMCUN" size="9" maxlength="9" value="<%= rtBasic.getE03ACMCUN().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" name="E03CUSNA1" size="45"  maxlength="45" value="<%= rtBasic.getE03CUSNA1().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E03ACMACC" size="12" readonly maxlength="12" value="<%= rtBasic.getE03ACMACC().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"> 
                <input type="text" name="E03ACMCCY" size="4" readonly maxlength="4" value="<%= rtBasic.getE03ACMCCY().trim()%>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"> 
                <input type="text" name="E03ACMPRO" size="4" readonly maxlength="4" value="<%= rtBasic.getE03ACMPRO().trim()%>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
<BR> 
<h4></h4> 
  
  <TABLE class="tableinfo">
    <TR id=trdark> 
      <TH ALIGN=CENTER colspan="2">Fecha</TH>
      <TH ALIGN=CENTER rowspan="2" width="13%">Numero Chequera</TH>
      <TH ALIGN=CENTER rowspan="2" width="12%">Estatus Chequera</TH>
      <TH ALIGN=CENTER rowspan="2" width="12%">Numero de Cheques</TH>
      <TH ALIGN=CENTER rowspan="2" width="8%">Cheque Inicial</TH>
      <TH ALIGN=CENTER rowspan="2" width="7%">Cheque Final</TH>
      <TH ALIGN=CENTER rowspan="2" width="4%">Env Pro</TH>
      <TH ALIGN=CENTER rowspan="2" width="4%">Rec Pro</TH>
      <TH ALIGN=CENTER rowspan="2" width="4%">Env Age</TH>
      <TH ALIGN=CENTER rowspan="2" width="4%">Rec Age</TH>
      <TH ALIGN=CENTER rowspan="2" width="4%">Env Clte</TH>
      <TH ALIGN=CENTER rowspan="2" width="4%">Seleccionar</TH>
    </TR>
    <TR id=trdark> 
      <TH ALIGN=CENTER width="5%">Recibida</TH>
      <TH ALIGN=CENTER width="5%">Enviada</TH>
    </TR>
    <%
                checkBooks.initRow();
                while (checkBooks.getNextRow()) {
                    // if (checkBooks.getFlag().equals("")) {
                    		out.println(checkBooks.getRecord());
                    // }
                }
    %> 
  </TABLE>

<%
  }
%>
<br>
<table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
 <tr bgcolor="#FFFFFF"> 
   <td align="center"><input id="EIBSBTN" type=submit name="AnullB" value="Cambiar Chequera" onclick="document.forms[0].ACTION.value='01'"> </td>
   <td align="center"><input id="EIBSBTN" type=submit name="AnullC" value="Cambiar Cheques" onclick="document.forms[0].ACTION.value='02'"> </td>
 </tr>
</table>  
</FORM>

</BODY>
</HTML>
