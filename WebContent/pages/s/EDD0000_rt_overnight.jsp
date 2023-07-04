<html>
<head>
<title>Concentración Nocturna</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="rtOvernight" class="datapro.eibs.beans.EDD000005Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

	builtNewMenu(rt_m_opt);

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
%>
<SCRIPT> initMenu();  </SCRIPT>


<H3 align="center">Concentraci&oacute;n Nocturna<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_overnight, EDD0000"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="46">
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
                <input type="text" name="E05ACMCUN" size="9" maxlength="9" readonly value="<%= userPO.getHeader2()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E05CUSNA1" size="45" readonly maxlength="45" value="<%= userPO.getHeader3()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta : </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E05ACMACC" readonly size="12" maxlength="9" value="<%= userPO.getIdentifier()%>">
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
                <input type="text" name="E05ACMPRO" size="4" readonly maxlength="4" value="<%= userPO.getHeader1()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Concentracion Nocturna</h4>  
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="17%" height="22"> 
              <div align="right">Tipo de Operaci&oacute;n :</div>
            </td>
            <td nowrap width="30%" height="22"> 
              <select name="E05ACMITY">
                <option value=" "></option>
                <option value="B" <% if (rtOvernight.getE05ACMITY().equals("B")) out.print("selected"); %>>Transferencia &amp; Protección de Sobregiros</option>
                <option value="C" <% if (rtOvernight.getE05ACMITY().equals("C")) out.print("selected"); %>>Transferir el exceso de fondos</option>
                <option value="D" <% if (rtOvernight.getE05ACMITY().equals("D")) out.print("selected"); %>>Proteccion contra sobregiros si aplica</option>
                <option value="R" <% if (rtOvernight.getE05ACMITY().equals("R")) out.print("selected"); %>>Inversion Nocturna Regular</option>
              </select>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%"> 
              <div align="right">Balance M&iacute;nimo Requerido :</div>
            </td>
            <td nowrap width="30%">
              <input type="text" name="E05ACMIVL" maxlength="11" size="11" value="<%= rtOvernight.getE05ACMIVL().trim()%>" onkeypress="enterDecimal()">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%"> 
              <div align="right">Inversi&oacute;n M&uacute;ltiple :</div>
            </td>
            <td nowrap width="30%"> 
              <input type="text" name="E05ACMIFM" maxlength="11" size="11" value="<%= rtOvernight.getE05ACMIFM().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" height="23"> 
              <div align="right">Tabla de Tasas de Inversi&oacute;n :</div>
            </td>
            <td nowrap width="30%" height="23"> 
              <input type="text" name="E05ACMRTB" size="5" maxlength="5" value="<%= rtOvernight.getE05ACMRTB().trim()%>" onkeypress="enterDecimal()">
              <a href="javascript:GetOvernightTable('E05ACMRTB','<%= rtOvernight.getE05ACMRBK().trim()%>')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" height="19"> 
              <div align="right">Tasa de Inversiones :</div>
            </td>
            <td nowrap width="30%" height="19"> 
              <input type="text" name="E05ACMOIS" maxlength="11" size="11" value="<%= rtOvernight.getE05ACMOIS().trim()%>" onkeypress="enterDecimal()">
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="17%" height="19">
              <div align="right">Cuenta de Detalle de Inversiones :</div>
            </td>
            <td nowrap width="30%" height="19"> 
              <input type="text" name="E05ACMRAC" size="12" maxlength="12" value="<%= rtOvernight.getE05ACMRAC().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:GetAccount('E05ACMRAC','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0"></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" height="19"> 
              <div align="right">Cuenta Contable de Inversi&oacute;n (Sucursal 
                &amp; G/L) :</div>
            </td>
            <td nowrap width="30%" height="19"> 
              <input type="text" name="E05ACMRBR" size="3" maxlength="3" value="<%= rtOvernight.getE05ACMRBR().trim()%>" onKeyPress="enterInteger()">
              <a href="javascript:GetBranch('E05ACMRBR','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0"></a> 
              <input type="text" name="E05ACMRGL" size="16" maxlength="16" value="<%= rtOvernight.getE05ACMRGL().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:GetLedger('E05ACMRGL','<%= rtOvernight.getE05ACMRBK().trim()%>','<%= rtOvernight.getE05ACMRCY().trim()%>','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
          <div align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </div>>
  </form>
</body>
</html>
