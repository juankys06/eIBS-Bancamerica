<html>
<head>
<title>Prepayment Interests</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
 
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


</head>
<jsp:useBean id="cdIntPrep" class="datapro.eibs.beans.EDL013009Message"  scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos"  scope="session" />

<body>


<% 
 if ( !error.getERRNUM().equals("0")  ) {
	 error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 
%>
<SCRIPT Language="Javascript">

	builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }

</SCRIPT>
<h3 align="center">Intereses de Prepagos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_prep_int.jsp, EDL0130B"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="40"> 
  <INPUT TYPE=HIDDEN NAME="E09DEABNK" VALUE="<%= cdIntPrep.getE09DEABNK().trim()%>">
  <input type=HIDDEN name="E09DEAACD" value="<%= cdIntPrep.getE09DEAACD().trim()%>">
 <table class="tableinfo">
    <tr > 
      <td>
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td width="23%" > 
              <div align="right"><b>Cliente : </b></div>
            </td>
            <td width="32%" > 
              <input type="text" size="9" maxlength="9" name="E09DEACUN" value="<%= cdIntPrep.getE09DEACUN().trim()%>" readonly>
            </td>
            <td width="30%" > 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td width="15%" > 
              <input type="text" size="12" maxlength="9" name="E09DEAACC" value="<%= cdIntPrep.getE09DEAACC().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="23%"> 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td width="32%"> 
              <input type="text" size="45" maxlength="45" name="E09CUSNA1" value="<%= cdIntPrep.getE09CUSNA1().trim()%>" readonly>
            </td>
            <td width="30%"> 
              <div align="right"> <b>Producto :</b></div>
            </td>
            <td width="15%"> 
              <input type="text" size="4" maxlength="4" name="E09DEAPRO" value="<%= cdIntPrep.getE09DEAPRO().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <h4>Informaci&oacute;n Financiera</h4>
  <table class="tableinfo">
    <tr > 
      <td> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td width="39%"> 
              <div align="right">Intereses Pagados :</div>
            </td>
            <td width="61%">
              <input type="text" name="E09TRNINT" size="15" maxlength="13" value="<%= cdIntPrep.getE09TRNINT().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="39%" > 
              <div align="right">Retenci&oacute;n de Impuesto :</div>
            </td>
            <td width="61%" > 
              <input type="text" name="E09TRNWHL" size="15" maxlength="13" value="<%= cdIntPrep.getE09TRNWHL().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="39%"> 
              <div align="right">Impuestos de Venta :</div>
            </td>
            <td width="61%"> 
              <input type="text" name="E09TRNTAX" size="15" maxlength="13" value="<%= cdIntPrep.getE09TRNTAX().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="39%"> 
              <div align="right">Total :</div>
            </td>
            <td width="61%"> 
              <input type="text" name="E09TRNTOT" size="15" maxlength="13" value="<%= cdIntPrep.getE09TRNTOT().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4><b>Cuenta</b> de Prepago</h4>
  <table class="tableinfo">
    <tr > 
      <td> 
        <table cellpadding=0 cellspacing=2 width="100%" >
          <tr id="trdark"> 
            <td width="31%" > 
              <div align="center">Concepto</div>
            </td>
            <td width="5%" > 
              <div align="center">Banco</div>
            </td>
            <td width="13%" > 
              <div align="center">Sucursal</div>
            </td>
            <td width="12%"  > 
              <div align="center">Moneda</div>
            </td>
            <td width="21%"  > 
              <div align="center">Referencia</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="31%" > 
              <div align="center"> 
                <input type="text" size="3" maxlength="3" name="E09TRNOPC" value="<%= cdIntPrep.getE09TRNOPC().trim()%>">
                <input type=HIDDEN name="E09TRNGLN" value="<%= cdIntPrep.getE09TRNGLN().trim()%>">
                <input type="text" size="25" maxlength="25" readonly name="E09TRNCON" value="<%= cdIntPrep.getE09TRNCON().trim()%>"
                  oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E09TRNBNK.value,'','E09TRNGLN','E09TRNOPC',document.forms[0].E09DEAACD.value)">
              </div>
            </td>
            <td width="5%" > 
              <div align="center"> 
                <input type="text" size="2" maxlength="2" value="<%= cdIntPrep.getE09TRNBNK().trim()%>" name="E09TRNBNK" onkeypress="enterInteger()">
              </div>
            </td>
            <td width="13%" > 
              <div align="center"> 
                <input type="text" size="3" maxlength="3" value="<%= cdIntPrep.getE09TRNBRN().trim()%>" name="E09TRNBRN" onkeypress="enterInteger()" 
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E09TRNBNK.value,'','','','')" >
              </div>
            </td>
            <td width="12%"  > 
              <div align="center"> 
                <input type="text" size="3" maxlength="3" name="E09TRNCCY" value="<%= cdIntPrep.getE09TRNCCY().trim()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E09TRNBNK.value,'','','','')">
              </div>
            </td>
            <td width="21%"  > 
              <div align="center"> 
                <input type="text" size="16" maxlength="12" value="<%= cdIntPrep.getE09TRNACC().trim()%>" name="E09TRNACC" onkeypress="enterInteger()"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E09TRNBNK.value,'','','','RT')">
					</div>
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
