<html>
<head>
<title>Calculadora</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="calcLoans" class="datapro.eibs.beans.EDL029001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

<%
if ( userPO.getHeader23().equals("G") ||  userPO.getHeader23().equals("V")){
%>
	builtNewMenu(ln_i_1_opt);
<%   
}
else  {
%>
	builtNewMenu(ln_i_2_opt);
<%   
}
%>

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
    out.println("<SCRIPT> initMenu(); </SCRIPT>");
 
%> 
<div align="center"></div>
<h3 align="center"> Calculadora de Intereses<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_cal.jsp, EDL0290"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0290" >
  <input type=HIDDEN name="SCREEN" value="2">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="14%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="9%" > 
              <div align="left"> 
                <input type="text" name="E02CUN2" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E02NA12" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap ><b> 
              <input type="text" name="E02PRO2" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
              </b></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="14%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="9%"> 
              <div align="left"> 
                <input type="text" name="E02ACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="12%"> 
              <div align="right">Oficial :</div>
            </td>
            <td nowrap width="33%"> 
              <div align="left"><b> 
                <input type="text" name="E02NA122" size="30" maxlength="30" readonly value="<%= userPO.getOfficer().trim()%>">
                </b> </div>
            </td>
            <td nowrap width="11%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="21%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n Opcional</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="19%" > 
              <div align="right">N&uacute;mero del Contrato:</div>
            </td>
            <td nowrap width="28%" > 
              <input type="text" name="E01DEAACC" size="14" maxlength="12" value="<%= calcLoans.getE01DEAACC().trim()%>" >
              <a href="javascript:GetAccount('E01DEAACC','','10','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
            <td nowrap colspan="2" >N&uacute;mero de Cliente : 
              <input type="text" name="E01DEACUN" size="10" maxlength="9" value="<%= calcLoans.getE01DEACUN().trim()%>" >
              <a href="javascript:GetCustomer('E01DEACUN')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="19%" > 
              <div align="right">Nombre :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01CUMMA1" size="45" maxlength="45" value="<%= calcLoans.getE01CUMMA1().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="19%" > 
              <div align="right">Direcci&oacute;n :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01CUMMA2" size="45" maxlength="45" value="<%= calcLoans.getE01CUMMA2().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="19%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01CUMMA3" size="45" maxlength="45" value="<%= calcLoans.getE01CUMMA3().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="19%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01CUMMA4" size="45" maxlength="45" value="<%= calcLoans.getE01CUMMA4().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="19%" > 
              <div align="right">Ciudad :</div>
            </td>
            <td nowrap width="28%" > 
              <input type="text" name="E01CUMCTY" size="30" maxlength="30" value="<%= calcLoans.getE01CUMCTY().trim()%>" >
            </td>
            <td nowrap width="17%" > Estado : 
              <input type="text" name="E01CUMSTE" size="5" maxlength="4" value="<%= calcLoans.getE01CUMSTE().trim()%>" >
            </td>
            <td nowrap width="36%" >Pa&iacute;s : 
              <input type="text" name="E01CUMCTR" size="20" maxlength="20" value="<%= calcLoans.getE01CUMCTR().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="19%" > 
              <div align="right">C&oacute;digo Postal :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01CUMZPC" size="15" maxlength="15" value="<%= calcLoans.getE01CUMZPC().trim()%>" >
              <input type="text" name="E01CUMPOB" size="10" maxlength="10" value="<%= calcLoans.getE01CUMPOB().trim()%>" >
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n B&aacute;sica</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap > 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right"> Principal :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAPRI" size="15" maxlength="15" value="<%= calcLoans.getE01DEAPRI().trim()%>" >
            </td>
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap >&nbsp; </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"> Fecha Inicial :</div>
            </td>
            <td nowrap  bordercolor="#FFFFFF" > 
              <input type="text" name="E01DEAOD1" size="2" maxlength="2" value="<%= calcLoans.getE01DEAOD1().trim()%>" >
              <input type="text" name="E01DEAOD2" size="2" maxlength="2" value="<%= calcLoans.getE01DEAOD2().trim()%>" >
              <input type="text" name="E01DEAOD3" size="2" maxlength="2" value="<%= calcLoans.getE01DEAOD3().trim()%>" >
            </td>
            <td nowrap  bordercolor="#FFFFFF" > 
              <div align="right">Fecha Final :</div>
            </td>
            <td nowrap  bordercolor="#FFFFFF" > 
              <input type="text" name="E01DEAMD1" size="2" maxlength="2" value="<%= calcLoans.getE01DEAMD1().trim()%>" >
              <input type="text" name="E01DEAMD2" size="2" maxlength="2" value="<%= calcLoans.getE01DEAMD2().trim()%>" >
              <input type="text" name="E01DEAMD3" size="2" maxlength="2" value="<%= calcLoans.getE01DEAMD3().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right"> Plazo :</div>
            </td>
            <td nowrap  bordercolor="#FFFFFF" > 
              <input type="text" name="E01DEATRM" size="4" maxlength="4" value="<%= calcLoans.getE01DEATRM().trim()%>">
              <select name="E01DEATRC">
                <option value=" " <% if (!(calcLoans.getE01DEATRC().equals("D") ||calcLoans.getE01DEATRC().equals("M")||calcLoans.getE01DEATRC().equals("Y"))) out.print("selected"); %>></option>
                <option value="D" <% if(calcLoans.getE01DEATRC().equals("D")) out.print("selected");%>>D&iacute;a(s)</option>
                <option value="M" <% if(calcLoans.getE01DEATRC().equals("M")) out.print("selected");%>>Mes(es)</option>
                <option value="Y" <% if(calcLoans.getE01DEATRC().equals("Y")) out.print("selected");%>>A&ntilde;o(s)</option>
              </select>
            </td>
            <td nowrap  bordercolor="#FFFFFF" > 
              <div align="right">Tasa de Inter&eacute;s :</div>
            </td>
            <td nowrap  bordercolor="#FFFFFF" > 
              <input type="text" name="E01DEARTE" size="10" maxlength="9" value="<%= calcLoans.getE01DEARTE().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap  > 
              <div align="right">Per&iacute;odo Base :</div>
            </td>
            <td nowrap  bordercolor="#000000" > 
              <input type="text" name="E01DEABAS" size="3" maxlength="3" value="<%= calcLoans.getE01DEABAS().trim()%>" >
            </td>
            <td nowrap  bordercolor="#000000" > 
              <div align="right">Tipo de C&aacute;lculo :</div>
            </td>
            <td nowrap  bordercolor="#000000" > 
              <input type="text" name="E01DEAICT" size="2" maxlength="1" value="<%= calcLoans.getE01DEAICT().trim()%>" >
              <a href="javascript:GetCode('E01DEAICT','STATIC_cd_formula.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap  bordercolor="#000000" height="32">&nbsp; </td>
            <td nowrap  bordercolor="#000000" height="32"> 
              <div align="right"></div>
            </td>
            <td nowrap  bordercolor="#000000" height="32">&nbsp; </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Tasa Descontado :</div>
            </td>
            <td nowrap  bordercolor="#000000">
              <input type="text" name="E01DSCRTE" size="10" maxlength="9" value="<%= calcLoans.getE01DSCRTE().trim()%>" >
            </td>
            <td nowrap  bordercolor="#000000"> 
              <div align="right">Vencimiento :</div>
            </td>
            <td nowrap  bordercolor="#000000"> 
              <input type="text" name="E01DYSNME" size="10" maxlength="9" value="<%= calcLoans.getE01DYSNME().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Principal :</div>
            </td>
            <td nowrap  bordercolor="#000000">
              <input type="text" name="E01DEAPRI" size="15" maxlength="15" value="<%= calcLoans.getE01DEAPRI().trim()%>" >
            </td>
            <td nowrap  bordercolor="#000000"> 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap  bordercolor="#000000"> 
              <input type="text" name="E01VENCI1" size="2" maxlength="2" value="<%= calcLoans.getE01VENCI1().trim()%>" >
              <input type="text" name="E01VENCI2" size="2" maxlength="2" value="<%= calcLoans.getE01VENCI2().trim()%>" >
              <input type="text" name="E01VENCI3" size="2" maxlength="2" value="<%= calcLoans.getE01VENCI3().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Intereses :</div>
            </td>
            <td nowrap  bordercolor="#000000">
              <input type="text" name="E01DEAINT" size="15" maxlength="15" value="<%= calcLoans.getE01DEAINT().trim()%>" >
            </td>
            <td nowrap  bordercolor="#000000">&nbsp;</td>
            <td nowrap  bordercolor="#000000">&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Total :</div>
            </td>
            <td nowrap  bordercolor="#000000"> 
              <input type="text" name="E01TOTDUE" size="15" maxlength="15" value="<%= calcLoans.getE01TOTDUE().trim()%>" >
            </td>
            <td nowrap  bordercolor="#000000">&nbsp;</td>
            <td nowrap  bordercolor="#000000">&nbsp;</td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</p>
  
  </form>
</body>
</html>
