<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Cuentas Corrientes</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</head>

<jsp:useBean id="rtBasic" class="datapro.eibs.beans.ECH037501Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) 
 {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }


%> 

<H3 align="center">Personalización de Chequeras<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_basic.jsp, ECH0000"></H3>

<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH0000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="29">
  
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
                <input type="text" name="E01CHPCUN" size="9" maxlength="9" value="<%= rtBasic.getE01CHPCUN().trim()%>">
			</div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01CHPNA1" size="45" maxlength="45" readonly value="<%= rtBasic.getE01CHPNA1().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01CHPACC" size="12" maxlength="12" value="<%= rtBasic.getE01CHPACC().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01CHPCCY" size="3" maxlength="3" value="<%= rtBasic.getE01CHPCCY().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01CHPPRO" size="4" maxlength="4" readonly value="<%= rtBasic.getE01CHPPRO().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
<BR>
<table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right">Número de Talonario Solicitados</div>
            </td>
            <td nowrap width="19%"><INPUT type="text" name="E01CHPNTC" size="14" maxlength="9" value="<%= rtBasic.getE01CHPNTC().trim()%>"></td>
            <td nowrap width="26%">Tipo de Talonario</td>
            <td nowrap width="26%"><INPUT type="text" name="E01CHPTCB" size="14" maxlength="9" value="<%= rtBasic.getE01CHPTCB().trim()%>"></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Minimo de Cheques</div>
            </td>
            <td nowrap width="19%"><INPUT type="text" name="E01CHPCLB" size="14" maxlength="9" value="<%= rtBasic.getE01CHPMSK().trim()%>"></td>
            <td nowrap width="26%">Stock Actual</td>
            <td nowrap width="26%"><INPUT type="text" name="E01CHPMSK" size="14" maxlength="9" value="<%= rtBasic.getE01CHPMSK().trim()%>"></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="23"> 
              <div align="right">Se cobra o no</div>
            </td>
            <td nowrap width="19%" height="23"><INPUT type="text" name="E01CHPASK" size="14" maxlength="9" value="<%= rtBasic.getE01CHPASK().trim()%>"></td>
            <td nowrap width="26%" height="23"></td>
            <td nowrap width="26%" height="23"></td>
          </tr>
        </table>
      </td>
    </tr>
</table>
<BR>
<table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td> 
              <div align="right">Nombre Personalizado</div>
            </td>
            <td><INPUT type="text" name="E01CHPN01" size="58" maxlength="58" value="<%= rtBasic.getE01CHPN01().trim()%>"></td>
          </tr>
          <tr id="trclear"> 
            <td > 
              <div align="right">Número inicial de cheque</div>
            </td>
            <td><INPUT type="text" name="E01CHPVAL" size="14" maxlength="9" value="<%= rtBasic.getE01CHPVAL().trim()%>"></td>
          </tr>
          <tr id="trdark"> 
            <td><div align="right">Restricción de entrega de chequera</div></td>
            <td>
            <SELECT name="E01CHPCON">
                            <OPTION value="Y" <% if (rtBasic.getE01CHPCON().equals("S")) out.print("selected"); %>="" selected>SI</OPTION>
                            <OPTION value="N" <% if (rtBasic.getE01CHPCON().equals("N")) out.print("selected"); %>="">NO</OPTION>
                        </SELECT></td>
          </tr>
          <tr id="trclear"> 
            <td><div align="right">Cobro de Cargo por chequera</div></td>
            <td>
            	<SELECT name="E01CHPCBC">
                            <OPTION value="Y" <% if (rtBasic.getE01CHPCBC().equals("S")) out.print("selected"); %>="" selected>SI</OPTION>
                            <OPTION value="N" <% if (rtBasic.getE01CHPCBC().equals("N")) out.print("selected"); %>="">NO</OPTION>
                        </SELECT></td>
          </tr>
          <tr id="trdark"> 
            <td><div align="right">Firma Relacionada</div></td>
            <td>
            	<SELECT name="E01CHPRE1">
                            <OPTION value="Y" <% if (rtBasic.getE01CHPRE1().equals("S")) out.print("selected"); %>="" selected>SI</OPTION>
                            <OPTION value="N" <% if (rtBasic.getE01CHPRE1().equals("N")) out.print("selected"); %>="">NO</OPTION>
                        </SELECT></td>
          </tr>
          <tr id="trclear"> 
            <td><div align="right">Oficina de entrega</div></td>
            <td>
			<input size="5" type="text" name="E01CHPDBR" maxlength="4"  value="<%= rtBasic.getE01CHPDBR().trim()%>">
			<INPUT size="25" type="text" name="DESNJMOET" maxlength="20" value="">
			<a href="javascript:GetBranchCodeName('E01CHPDBR','DESNJMOET','01')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"  ></a>
			</td>
          </tr>

        </table>
      </td>
    </tr>
  </table>

<p align="center"> 
     <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
</p>
</form>

</body>
</html>
