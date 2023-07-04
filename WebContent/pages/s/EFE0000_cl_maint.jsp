<html>
<head>
<title>Credit Lines - Basic Information</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="clMant" class="datapro.eibs.beans.ELN000001Message" scope="session"/>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage" scope="session"/>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos" scope="session"/>

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

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
<h3 align="center">Tesorer&iacute;a - L&iacute;neas de Cr&eacute;dito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cl_maint.jsp,EFE0000"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSELN0000T" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="8%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="17%" > 
              <div align="left"> <%= clMant.getE01WLNCUN().trim()%> </div>
            </td>
            <td nowrap width="8%" > 
              <div align="right"><b>L&iacute;nea : </b></div>
            </td>
            <td nowrap width="7%" > <%= clMant.getE01WLNNUM().trim()%> </td>
            <td nowrap width="12%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="4" width="48%" > 
              <div align="left"> <%= clMant.getE01CUSNA1().trim()%> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n B&aacute;sica </h4>
    <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="29%" > 
              <div align="right">Tipo de L&iacute;nea : </div>
            </td>
            <td nowrap width="26%" > 
              <input type="text" readonly name="E01WLNTYL" size="4" maxlength="3" value="TRS">
            </td>
            <td nowrap width="23%" > 
              <div align="right">Monto Aprobado :</div>
            </td>
            <td nowrap width="22%" > 
              <input type="text" name="E01WLNAMN" size="15" maxlength="15" value="<%= clMant.getE01WLNAMN().trim()%>"  onKeyPress="enterDecimal()">
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom"> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" > 
              <div align="right">Fecha de Apertura :</div>
            </td>
            <td nowrap width="26%" > 
              <input type="text" name="E01WLNOP1" size="2" maxlength="2" value="<%= clMant.getE01WLNOP1().trim()%>"  onKeyPress="enterInteger()">
              <input type="text" name="E01WLNOP2" size="2" maxlength="2" value="<%= clMant.getE01WLNOP2().trim()%>"  onKeyPress="enterInteger()">
              <input type="text" name="E01WLNOP3" size="2" maxlength="2" value="<%= clMant.getE01WLNOP3().trim()%>"  onKeyPress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E01WLNOP1,document.forms[0].E01WLNOP2,document.forms[0].E01WLNOP3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom"> 
            </td>
            <td nowrap width="23%" > 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap width="22%" > 
              <input type="text" name="E01WLNMT1" size="2" maxlength="2" value="<%= clMant.getE01WLNMT1().trim()%>"  onkeypress="enterInteger()">
              <input type="text" name="E01WLNMT2" size="2" maxlength="2" value="<%= clMant.getE01WLNMT2().trim()%>"  onkeypress="enterInteger()">
              <input type="text" name="E01WLNMT3" size="2" maxlength="2" value="<%= clMant.getE01WLNMT3().trim()%>"  onkeypress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E01WLNMT1,document.forms[0].E01WLNMT2,document.forms[0].E01WLNMT3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom"> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" > 
              <div align="right">Autorizado por :</div>
            </td>
            <td nowrap width="26%" > 
              <input type="text" name="E01WLNABY" size="26" maxlength="25" value="<%= clMant.getE01WLNABY().trim()%>" >
            </td>
            <td nowrap width="23%" > 
              <div align="right">Fecha de Autorizaci&oacute;n :</div>
            </td>
            <td nowrap width="22%" > 
              <input type="text" name="E01WLNAU1" size="2" maxlength="2" value="<%= clMant.getE01WLNAU1().trim()%>"  onKeyPress="enterInteger()">
              <input type="text" name="E01WLNAU2" size="2" maxlength="2" value="<%= clMant.getE01WLNAU2().trim()%>"  onKeyPress="enterInteger()">
              <input type="text" name="E01WLNAU3" size="2" maxlength="2" value="<%= clMant.getE01WLNAU3().trim()%>"  onKeyPress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E01WLNAU1,document.forms[0].E01WLNAU2,document.forms[0].E01WLNAU3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" >
              <div align="right">Comentarios :</div>
            </td>
            <td nowrap colspan="3" ><%= clMant.getE01WLNDC1().trim()%></td>
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
