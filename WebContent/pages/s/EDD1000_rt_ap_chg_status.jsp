<html>
<head>
<title>Cambio de Status de Cuenta</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="rtStatus" class="datapro.eibs.beans.EDD000002Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

<%
	if(userPO.getOption().equals("RT")){
%>
	builtNewMenu(rt_a_opt);
<%
	}
	else if(userPO.getOption().equals("SV")){
%>
	builtNewMenu(sv_a_opt);
<%
	} 
%>

</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
    out.println("<SCRIPT> initMenu(); </SCRIPT>");
%>


<H3 align="center">Cambio de Status de Cuenta <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_ap_chg_status, EDD1000"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="16">
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E02ACMCUN" readonly size="9" maxlength="9" value="<%= rtStatus.getE02ACMCUN().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E02CUSNA1" size="45" readonly maxlength="45" value="<%= rtStatus.getE02CUSNA1().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E02ACMACC" size="12" readonly maxlength="12" value="<%= rtStatus.getE02ACMACC().trim()%>">
                </font></font></font> </div>
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
                <input type="text" name="E02ACMPRO" size="4" readonly maxlength="4" value="<%= rtStatus.getE02ACMPRO().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="41%"> 
              <div align="right">Status de la Cuenta:</div>
            </td>
            <td nowrap width="59%"> 
              <input type="text" readonly  name="E02ACMAST" size="35" maxlength="30" 
				  value="<% if (rtStatus.getE02ACMAST().equals("A")) out.print("Cuenta Activa");
							else if (rtStatus.getE02ACMAST().equals("C")) out.print("Cuenta Cancelada");
						    else if (rtStatus.getE02ACMAST().equals("I")) out.print("Cuenta Inactiva 1");
						    else if (rtStatus.getE02ACMAST().equals("D")) out.print("Cuenta Inactiva 2");
						    else if (rtStatus.getE02ACMAST().equals("O")) out.print("Cuenta Controlada");
						    else if (rtStatus.getE02ACMAST().equals("E")) out.print("Cuenta Embargada");
						    else if (rtStatus.getE02ACMAST().equals("T")) out.print("Acepta Sólo Depósitos");
							else out.print("");%>" 
				>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="41%"> 
              <div align="right">Cambiado por (Funcionario):</div>
            </td>
            <td nowrap width="59%"> 
              <input type="text" readonly name="E02ACMUIN" size="5" maxlength="4" value="<%= rtStatus.getE02ACMUIN().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="41%" height="23"> 
              <div align="right">Fecha de Cambio:</div>
            </td>
            <td nowrap width="59%" height="23"> 
              <input type="text" name="E02ACMCH1" size="2" maxlength="2" value="<%= rtStatus.getE02ACMCH1().trim()%>" readonly>
              <input type="text" name="E02ACMCH2" size="2" maxlength="2" value="<%= rtStatus.getE02ACMCH2().trim()%>" readonly>
              <input type="text" name="E02ACMCH3" size="2" maxlength="2" value="<%= rtStatus.getE02ACMCH3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="41%" height="19"> 
              <div align="right">Cambiar Fecha/ Ultima Actualizaci&oacute;n</div>
            </td>
            <td nowrap width="59%" height="19"> 
              <input type="text" readonly  name="E02ACMACT" size="3" maxlength="2" 
				  value="<% if (rtStatus.getE02ACMACT().equals("Y")) out.print("Si");
							else if (rtStatus.getE02ACMACT().equals("N")) out.print("No");
							else out.print("");%>" 
				>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="41%" height="19"> 
              <div align="right">Descripci&oacute;n:</div>
            </td>
            <td nowrap width="59%" height="19"> 
              <input type="text" name="E02ADECRI" size="35" maxlength="35" value="<%= rtStatus.getE02ADECRI().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>
