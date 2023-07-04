<html>
<head>
<title>Informacion para Sobregiros</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="rtOverdraft" class="datapro.eibs.beans.EDD000003Message"  scope="session" />

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


<H3 align="center">Informaci&oacute;n para Sobregiros<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_ap_overdraft.jsp, EDD1000"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="6">
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
                <input type="text" name="E03ACMCUN" size="9" maxlength="9"  value="<%= rtOverdraft.getE03ACMCUN().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E03CUSNA1" size="45"  maxlength="45" value="<%= rtOverdraft.getE03CUSNA1().trim()%>" readonly>
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta : </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E03ACMACC"  size="12" maxlength="12" value="<%= rtOverdraft.getE03ACMACC().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda :</b></div>
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
                <input type="text" name="E03ACMPRO" size="4"  maxlength="4" value="<%= rtOverdraft.getE03ACMPRO().trim()%>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>L&iacute;mites para Sobregiros</h4>  
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="17%"> 
              <div align="right">Cargos por Sobregiros :</div>
            </td>
            <td nowrap width="30%"> 
              <input type="text" readonly  name="E03ACMODF" size="20" maxlength="25" 
				  value="<% if (rtOverdraft.getE03ACMODF().equals("N")) out.print("No Cargos");
							else if (rtOverdraft.getE03ACMODF().equals("1")) out.print("Si Cargos");
							else if (rtOverdraft.getE03ACMODF().equals("2")) out.print("Diferir Cargos");
							else if (rtOverdraft.getE03ACMODF().equals("3")) out.print("Cargo Diario");
							else if (rtOverdraft.getE03ACMODF().equals("4")) out.print("A Pr&Eacute;stamo");
						    else out.print("");%>" 
				>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%"> 
              <div align="right">Saldo Usado para Sobregiros :</div>
            </td>
            <td nowrap width="30%"> 
              <input type="text" readonly  name="E03ACMONG" size="20" maxlength="25" 
				  value="<% if (rtOverdraft.getE03ACMONG().equals("G")) out.print("Saldo en Libros");
							else if (rtOverdraft.getE03ACMONG().equals("N")) out.print("Saldo Neto");
							else if (rtOverdraft.getE03ACMONG().equals("C")) out.print("Seg&uacute;n Control");
						    else out.print("");%>" 
				>
            </td>
          </tr>
          
        
          <tr id="trdark"> 
            <td nowrap width="17%"> 
              <div align="right">Valor L&iacute;mite de Sobregiro 1:</div>
            </td>
            <td nowrap width="30%"> 
              <input type="text" name="E03ACMOL1" maxlength="11" size="11" value="<%= rtOverdraft.getE03ACMOL1().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" height="23"> 
              <div align="right">Sobretasa por Sobregiro 1:</div>
            </td>
            <td nowrap width="30%" height="23">
              <input type="text" name="E03ACMOI1" size="5" maxlength="5" value="<%= rtOverdraft.getE03ACMOI1().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" height="19"> 
              <div align="right">Valor L&iacute;mite de Sobregiro 2:</div>
            </td>
            <td nowrap width="30%" height="19"> 
              <input type="text" name="E03ACMOL2" maxlength="11" size="11" value="<%= rtOverdraft.getE03ACMOL2().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" height="19"> 
              <div align="right">Sobretasa por Sobregiro2 :</div>
            </td>
            <td nowrap width="30%" height="19"> 
              <input type="text" name="E03ACMOI2" size="5" maxlength="5" value="<%= rtOverdraft.getE03ACMOI2().trim()%>" readonly>
            </td>
          </tr>
          
       
          
          <tr id="trdark"> 
            <td nowrap width="17%" height="19"> 
              <div align="right">Cuenta Contable Relacionada</div>
            </td>
            <td nowrap width="30%" height="19"> 
              <input type="text" name="E03ACMRBK" size="2" maxlength="2" value="<%= rtOverdraft.getE03ACMRBK().trim()%>" readonly>
              <input type="text" name="E03ACMRBR" size="3" maxlength="3" value="<%= rtOverdraft.getE03ACMRBR().trim()%>" readonly>
              <input type="text" name="E03ACMRCY" size="3" maxlength="3" value="<%= rtOverdraft.getE03ACMRCY().trim()%>" readonly>
              <input type="text" name="E03ACMRGL" size="16" maxlength="16" value="<%= rtOverdraft.getE03ACMRGL().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" height="19"> 
              <div align="right">Cuenta Detalle Relacionada:</div>
            </td>
            <td nowrap width="30%" height="19"> 
              <input type="text" name="E03ACMRAC" size="12" maxlength="12" value="<%= rtOverdraft.getE03ACMRAC().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" height="19"> 
              <div align="right">Producto Pr&eacute;stamo Relacionado:</div>
            </td>
            <td nowrap width="30%" height="19"> 
              <input type="text" name="E03LNSPRD" size="2" maxlength="1" value="<%= rtOverdraft.getE03LNSPRD().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>
