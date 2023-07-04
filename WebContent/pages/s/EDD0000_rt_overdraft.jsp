<html>
<head>
<title>Informacion para Al Descubierto</title>
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
if ( userPO.getOption().equals("RT") ) {
%>
	builtNewMenu(rt_m_opt);
<%   
}
else if ( userPO.getOption().equals("SV") ) {
%>
	builtNewMenu(sv_m_opt);
<%   
}
%>

</SCRIPT>

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    out.println("<SCRIPT> initMenu(); </SCRIPT>");
%>


<H3 align="center">Informaci&oacute;n para Al Descubierto<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_overdraft.jsp, EDD0000"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="6">
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E03ACMCUN" size="9" maxlength="9" readonly value="<%= rtOverdraft.getE03ACMCUN().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E03CUSNA1" size="45" readonly maxlength="45" value="<%= rtOverdraft.getE03CUSNA1().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta : </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E03ACMACC" readonly size="12" maxlength="12" value="<%= rtOverdraft.getE03ACMACC().trim()%>">
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
                <input type="text" name="E03ACMPRO" size="4" readonly maxlength="4" value="<%= rtOverdraft.getE03ACMPRO().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>L&iacute;mites para Al Descubierto</h4>  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="17%"> 
              <div align="right">Cargos por Al Descubierto :</div>
            </td>
            <td nowrap width="30%"> 
              <select name="E03ACMODF">
                <option value=" " <% if (!(rtOverdraft.getE03ACMODF().equals("N") ||rtOverdraft.getE03ACMODF().equals("1")
				||rtOverdraft.getE03ACMODF().equals("2")||rtOverdraft.getE03ACMODF().equals("3")
				||rtOverdraft.getE03ACMODF().equals("4")||rtOverdraft.getE03ACMODF().equals("5"))) out.print("selected"); %>></option>
                <option value="N" <% if (rtOverdraft.getE03ACMODF().equals("N")) out.print("selected"); %>>No 
                Cargos</option>
                <option value="1" <% if (rtOverdraft.getE03ACMODF().equals("1")) out.print("selected"); %>>Si 
                Cargos</option>
                <option value="2" <% if (rtOverdraft.getE03ACMODF().equals("2")) out.print("selected"); %>>Diferir 
                Cargo</option>
                <option value="3" <% if (rtOverdraft.getE03ACMODF().equals("3")) out.print("selected"); %>>Cargo 
                Diario</option>
                <option value="4" <% if (rtOverdraft.getE03ACMODF().equals("4")) out.print("selected"); %>>A 
                Pr&Eacute;stamo</option>
                <option value="5" <% if (rtOverdraft.getE03ACMODF().equals("5")) out.print("selected"); %>>Fondos en 
                Transito</option>
              </select>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%"> 
              <div align="right">Saldo Usado para Al Descubierto :</div>
            </td>
            <td nowrap width="30%"> 
              <select name="E03ACMONG">
              	<%-- 
                <option value=" " <% if (!(rtOverdraft.getE03ACMONG().equals("G") ||rtOverdraft.getE03ACMONG().equals("N")
				||rtOverdraft.getE03ACMONG().equals("C"))) out.print("selected"); %>></option>
				--%>
                <option value="G" <% if ( rtOverdraft.getE03ACMONG().equals("G") ||
					(!(rtOverdraft.getE03ACMONG().equals("N") || rtOverdraft.getE03ACMONG().equals("C"))) ) out.print("selected"); %>>
					Saldo en Libros </option>
                <option value="N" <% if (rtOverdraft.getE03ACMONG().equals("N")) out.print("selected"); %>>Saldo Neto</option>
                <option value="C" <% if (rtOverdraft.getE03ACMONG().equals("C")) out.print("selected"); %>>Seg&uacute;n Control</option>
              </select>
            </td>
          </tr>
          
         
          <tr id="trdark"> 
            <td nowrap width="17%"> 
              <div align="right">Valor L&iacute;mite de Al Descubierto 1:</div>
            </td>
            <td nowrap width="30%"> 
              <input type="text" name="E03ACMOL1" maxlength="11" size="11" value="<%= rtOverdraft.getE03ACMOL1().trim()%>" onKeypress="enterDecimal()">
            </td>
          </tr>
          
          
          <tr id="trclear"> 
            <td nowrap width="17%" height="23"> 
              <div align="right">Sobretasa por Al Descubierto 1:</div>
            </td>
            <td nowrap width="30%" height="23">
              <input type="text" name="E03ACMOI1" size="5" maxlength="5" value="<%= rtOverdraft.getE03ACMOI1().trim()%>" onKeypress="enterSignDecimal()">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" height="19"> 
              <div align="right">Valor L&iacute;mite de Al Descubierto 2:</div>
            </td>
            <td nowrap width="30%" height="19"> 
              <input type="text" name="E03ACMOL2" maxlength="11" size="11" value="<%= rtOverdraft.getE03ACMOL2().trim()%>" onKeypress="enterDecimal()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" height="19"> 
              <div align="right">Sobretasa por Al Descubierto 2 :</div>
            </td>
            <td nowrap width="30%" height="19"> 
               <input type="text" name="E03ACMOI2" size="5" maxlength="5" value="<%= rtOverdraft.getE03ACMOI2().trim()%>" onKeypress="enterSignDecimal()">
            </td>
          </tr>
          
          
          
          <tr id="trdark"> 
            <td nowrap width="17%" height="19"> 
              <div align="right">Cuenta Contable Relacionada</div>
            </td>
            <td nowrap width="30%" height="19"> 
              <input type="text" name="E03ACMRBK" size="2" maxlength="2" value="<%= rtOverdraft.getE03ACMRBK().trim()%>">
              <input type="text" name="E03ACMRBR" size="3" maxlength="3" value="<%= rtOverdraft.getE03ACMRBR().trim()%>">
              <a href="javascript:GetBranch('E03ACMRBR','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" ></a> 
              <input type="text" name="E03ACMRCY" size="3" maxlength="3" value="<%= rtOverdraft.getE03ACMRCY().trim()%>">
              <a href="javascript:GetCurrency('E03ACMRCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" ></a> 
              <input type="text" name="E03ACMRGL" size="16" maxlength="16" value="<%= rtOverdraft.getE03ACMRGL().trim()%>">
              <a href="javascript:GetLedger('E03ACMRGL',document.forms[0].E03ACMRBK.value,document.forms[0].E03ACMRCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" height="19"> 
              <div align="right">Cuenta Detalle Relacionada:</div>
            </td>
            <td nowrap width="30%" height="19"> 
              <input type="text" name="E03ACMRAC" size="12" maxlength="12" value="<%= rtOverdraft.getE03ACMRAC().trim()%>">
              <a href="javascript:GetAccount('E03ACMRAC','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0"  ></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" height="19"> 
              <div align="right">Producto Pr&eacute;stamo Relacionado:</div>
            </td>
            <td nowrap width="30%" height="19"> 
              <input type="text" name="E03LNSPRD" size="2" maxlength="1" value="<%= rtOverdraft.getE03LNSPRD().trim()%>">
              <a href="javascript:GetProduct('E03ACMPRD','10','')"><img src="<%=request.getContextPath()%>/images/1b.gif" align="absbottom" border="0"></a> 
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
