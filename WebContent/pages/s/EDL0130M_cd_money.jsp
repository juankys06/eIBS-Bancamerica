<html>
<head>
<title>Money Launder Control</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="cdMoney" class="datapro.eibs.beans.ELD000001Message" scope="session" />
<jsp:useBean id="error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id="userPO" class= "datapro.eibs.beans.UserPos" scope="session" />

<body>

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

 builtNewMenu(cdm_m_opt);

 
</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
	 error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

   out.println("<SCRIPT> initMenu();  </SCRIPT>");
  
%>


<H3 align="center">Par&aacute;metros de Lavado de Dinero<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_money, EDL0130M"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="53">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente : </b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text"  name="CUSCUN" size="9" maxlength="9" value="<%= cdMoney.getE06LDMCUN().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text"  name="CUSNA1" size="45"  maxlength="45" value="<%= cdMoney.getE06CUSNA1().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta : </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text"  name="ACCNUM" size="12"  maxlength="9" value="<%= cdMoney.getE06LDMACC().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"> 
                <input type="text"  name="PROCCY" size="4"  maxlength="4" value="<%= cdMoney.getE06LDMCCY().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"> 
                <input type="text"  name="PROCOD" size="4"  maxlength="4" value="<%= cdMoney.getE06LDMPRO().trim()%>" readonly>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>Fuente de Fondos</H4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="center"><b>Tipo de Actividad</b></div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"><b>N&uacute;mero</b></div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"><b>Monto</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Efectivo :</div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text"  name="E06LDMSCN" size="5" maxlength="5" value="<%= cdMoney.getE06LDMSCN().trim()%>" onKeyPress="enterInteger()">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text"  name="E06LDMSCA" size="15" maxlength="13" value="<%= cdMoney.getE06LDMSCA().trim()%>" onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right">Transferencia de Fondos :</div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text"  name="E06LDMSWN" size="5" maxlength="5" value="<%= cdMoney.getE06LDMSWN().trim()%>" onKeyPress="enterInteger()">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text"  name="E06LDMSWA" size="15" maxlength="13" value="<%= cdMoney.getE06LDMSWA().trim()%>" onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="23"> 
              <div align="right">Cuenta Corriente :</div>
            </td>
            <td nowrap width="19%" height="23"> 
              <div align="center"> 
                <input type="text"  name="E06LDMSDN" size="5" maxlength="5" value="<%= cdMoney.getE06LDMSDN().trim()%>" onKeyPress="enterInteger()">
              </div>
            </td>
            <td nowrap width="19%" height="23"> 
              <div align="center"> 
                <input type="text"  name="E06LDMSDA" size="15" maxlength="13" value="<%= cdMoney.getE06LDMSDA().trim()%>" onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Cheques :</div>
            </td>
            <td nowrap width="19%" height="19"> 
              <div align="center"> 
                <input type="text"  name="E06LDMSKN" size="5" maxlength="5" value="<%= cdMoney.getE06LDMSKN().trim()%>" onKeyPress="enterInteger()">
              </div>
            </td>
            <td nowrap width="19%" height="19"> 
              <div align="center"> 
                <input type="text"  name="E06LDMSKA" size="15" maxlength="13" value="<%= cdMoney.getE06LDMSKA().trim()%>" onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Desembolso de Fondos</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="center"><b>Tipo de Actividad</b></div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"><b>N&uacute;mero</b></div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"><b>Monto</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Efectivo :</div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text"  name="E06LDMBCN" size="5" maxlength="5" value="<%= cdMoney.getE06LDMBCN().trim()%>" onKeyPress="enterInteger()">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text"  name="E06LDMBCA" size="15" maxlength="13" value="<%= cdMoney.getE06LDMBCA().trim()%>" onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right">Transferencia de Fondos :</div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text"  name="E06LDMBWN" size="5" maxlength="5" value="<%= cdMoney.getE06LDMBWN().trim()%>" onKeyPress="enterInteger()">
              </div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text"  name="E06LDMBWA" size="15" maxlength="13" value="<%= cdMoney.getE06LDMBWA().trim()%>" onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="23"> 
              <div align="right">Cuenta Corriente :</div>
            </td>
            <td nowrap width="19%" height="23"> 
              <div align="center"> 
                <input type="text"  name="E06LDMBDN" size="5" maxlength="5" value="<%= cdMoney.getE06LDMBDN().trim()%>" onKeyPress="enterInteger()">
              </div>
            </td>
            <td nowrap width="19%" height="23"> 
              <div align="center"> 
                <input type="text"  name="E06LDMBDA" size="15" maxlength="13" value="<%= cdMoney.getE06LDMBDA().trim()%>" onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Cheques :</div>
            </td>
            <td nowrap width="19%" height="19"> 
              <div align="center"> 
                <input type="text"  name="E06LDMBKN" size="5" maxlength="5" value="<%= cdMoney.getE06LDMBKN().trim()%>" onKeyPress="enterInteger()">
              </div>
            </td>
            <td nowrap width="19%" height="19"> 
              <div align="center"> 
                <input type="text"  name="E06LDMBKA" size="15" maxlength="13" value="<%= cdMoney.getE06LDMBKA().trim()%>" onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

  <H4>Saldos</H4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="38%"> 
              <div align="right">Promedio Principal :</div>
            </td>
            <td nowrap width="62%"> 
              <input type="text" name="E06LDMGAV" size="15" maxlength="13" value="<%= cdMoney.getE06LDMGAV().trim()%>" onkeypress="enterDecimal()">
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
