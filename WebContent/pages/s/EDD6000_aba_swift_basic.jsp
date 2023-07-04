<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Mantenimiento de Números ABA y Swift ID</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="aba" class="datapro.eibs.beans.EDD600001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">


</SCRIPT>
<%
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }


%>
<H3 align="center"><% if (!aba.getE01INSAB9().equals("") && !aba.getE01INSAB9().equals("0")) out.print("Mantenimiento de Número ABA");
   else out.print("Mantenimiento de Swift ID");
  %><img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="aba_swift_basic.jsp, EDD6000"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.transfer.JSEDD6000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="3">

<table class="tableinfo">
    <tr >
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark">
            <td nowrap width="30%" >
              <% if (!aba.getE01INSAB9().equals("") && !aba.getE01INSAB9().equals("0")) { %>
              		<div align="right">Número ABA :</div>
              <% } else { %>
              		<div align="right">Identificación :</div>
              <%}%>
            </td>
            <td nowrap width="70%" >
            	<% if (!aba.getE01INSAB9().equals("") && !aba.getE01INSAB9().equals("0")) { %>
           			<input type="text" name="E01INSAB9" size="14" value="<%= aba.getE01INSAB9().trim()%>" readonly>
           		<% } else { %>
           			<input type="text" name="E01INSSWF" size="14" value="<%= aba.getE01INSSWF().trim()%>" readonly>
           		<%}%>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="30%" >
              	<div align="right">Nombre del Banco :</div>
            </td>
            <td nowrap width="70%" >
           		<input type="text" name="E01INSNAM" size="45" maxlength="40" value="<%= aba.getE01INSNAM().trim()%>" >
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="30%" >
              	<div align="right">Dirección :</div>
            </td>
            <td nowrap width="70%" >
           		<input type="text" name="E01INSAD1" size="45" maxlength="35" value="<%= aba.getE01INSAD1().trim()%>" >
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="30%" >
              	<div align="right"></div>
            </td>
            <td nowrap width="70%" >
           		<input type="text" name="E01INSAD2" size="45" maxlength="35" value="<%= aba.getE01INSAD2().trim()%>" >
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="30%" >
              	<div align="right"></div>
            </td>
            <td nowrap width="70%" >
           		<input type="text" name="E01INSAD3" size="45" maxlength="35" value="<%= aba.getE01INSAD3().trim()%>" >
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="30%" >
              	<div align="right"></div>
            </td>
            <td nowrap width="70%" >
           		<input type="text" name="E01INSAD4" size="45" maxlength="35" value="<%= aba.getE01INSAD4().trim()%>" >
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="30%" >
              	<div align="right">Ciudad :</div>
            </td>
            <td nowrap width="70%" >
           		<input type="text" name="E01INSCTY" size="45" maxlength="30" value="<%= aba.getE01INSCTY().trim()%>" >
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="30%" >
              	<div align="right">Estado :</div>
            </td>
            <td nowrap width="70%" >
           		<input type="text" name="E01INSTAT" size="3" maxlength="2" value="<%= aba.getE01INSTAT().trim()%>" >
           		<a href="javascript:GetCodeCNOFC('E01INSTAT', '27')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help"	align="absbottom" border="0"></a>
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="30%" >
              	<div align="right">Teléfono :</div>
            </td>
            <td nowrap width="70%" >
           		<input type="text" name="E01INSPHN" size="12" maxlength="10" value="<%= aba.getE01INSPHN().trim()%>" >
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="30%" >
              	<div align="right">Cuenta Corriente :</div>
            </td>
            <td nowrap width="70%" >
           		<input type="text" name="E01MTAACC" size="14" maxlength="13" value="<%= aba.getE01MTAACC().trim()%>" >
           		<input type="text" name="D01MTANME" size="45" maxlength="35" value="<%= aba.getD01MTANME().trim()%>" >
        		<a href="javascript:GetAccountInfo('E01MTAACC','','RA','','','D01MTANME','','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="30%" >
              	<div align="right">Cuenta Contable :</div>
            </td>
            <td nowrap width="70%" >
           		<input type="text" name="E01MTAGLN" size="18" maxlength="17" value="<%= aba.getE01MTAGLN().trim()%>" >
           		<input type="text" name="D01MTAGLD" size="45" maxlength="35" value="<%= aba.getD01MTAGLD().trim()%>" >
            	<a href="javascript:GetLedger('E01MTAGLN','','','','D01MTAGLD')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
            </td>
          </tr>
		  <% if (!aba.getE01INSAB9().equals("")) { %>
	          <tr id="trclear">
	            <td nowrap width="30%" >
	              	<div align="right">Miembro de FED Wire :</div>
	            </td>
	            <td nowrap width="70%" >
	              <select name="E01INSMBR">
					<option value=" " <% if (!(aba.getE01INSMBR().equals("1") ||aba.getE01INSMBR().equals("2")||aba.getE01INSMBR().equals("3"))) out.print("selected"); %>></option>
	                <option value="1" <%if (aba.getE01INSMBR().equals("1")) out.print("selected"); %>>FED Wire No En Línea</option>
	                <option value="2" <%if (aba.getE01INSMBR().equals("2")) out.print("selected"); %>>FED Wire En Línea</option>
	                <option value="3" <%if (aba.getE01INSMBR().equals("3")) out.print("selected"); %>>Agencia</option>
	              </select>
	            </td>
	          </tr>
		  <% } else { %>
	          <tr id="trclear">
	            <td nowrap width="30%" >
	              	<div align="right">Número de Cuenta :</div>
	            </td>
	            <td nowrap width="70%" >
	           		<input type="text" name="E01INSAD6" size="45" maxlength="35" value="<%= aba.getE01INSAD6().trim()%>" >
	            </td>
	          </tr>
	          <tr id="trdark">
	            <td nowrap width="30%" >
	              	<div align="right">Agencia :</div>
	            </td>
	            <td nowrap width="70%" >
	           		<input type="text" name="E01INSBRN" size="5" maxlength="4" value="<%= aba.getE01INSBRN().trim()%>" >
	            </td>
	          </tr>
	          <tr id="trclear">
	            <td nowrap width="30%" >
	              	<div align="right">Swift ID Banco Corresponsal :</div>
	            </td>
	            <td nowrap width="70%" >
	           		<input type="text" name="E01INSCSI" size="14" maxlength="12" value="<%= aba.getE01INSCSI().trim()%>" >
	           		<a href="javascript:GetSwiftId('E01INSCSI')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absmiddle" border="0"></a>
	            </td>
	          </tr>

		  <%}%>
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
