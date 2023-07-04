<html>
<head>
<title>Intereses Prepagados</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="cdIntPrep" class="datapro.eibs.beans.EDL013009Message"  scope="session" />

<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(cd_m_opt);
   builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }

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

<h3 align="center">Intereses Prepagados<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_prep_int.jsp, EDL0130"></h3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="40">
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
                <input type="text" size="9" maxlength="9" name="E09DEACUN" value="<%= cdIntPrep.getE09DEACUN().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text" size="45" maxlength="45" name="E09CUSNA1" value="<%= cdIntPrep.getE09CUSNA1().trim()%>" readonly>
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" size="12" maxlength="12" name="E09DEAACC" value="<%= cdIntPrep.getE09DEAACC().trim()%>" readonly>
              </div>
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
                <input type="text" size="4" maxlength="4" name="E09DEAPRO" value="<%= cdIntPrep.getE09DEAPRO().trim()%>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <h4>Informaci&oacute;n Financiera</h4>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td width="39%"> 
              <div align="right">Intereses Pagados :</div>
            </td>
            <td width="61%">
              <input type="text" name="E09TRNINT" size="15" maxlength="15" value="<%= cdIntPrep.getE09TRNINT().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="39%" > 
              <div align="right">Retenciones :</div>
            </td>
            <td width="61%" > 
              <input type="text" name="E09TRNWHL" size="15" maxlength="15" value="<%= cdIntPrep.getE09TRNWHL().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="39%"> 
              <div align="right">Impuestos :</div>
            </td>
            <td width="61%"> 
              <input type="text" name="E09TRNTAX" size="15" maxlength="15" value="<%= cdIntPrep.getE09TRNTAX().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="39%"> 
              <div align="right">Total :</div>
            </td>
            <td width="61%"> 
              <input type="text" name="E09TRNTOT" size="15" maxlength="15" value="<%= cdIntPrep.getE09TRNTOT().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Forma de Pago</h4>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td> 
        <table cellpadding=0 cellspacing=2 width="100%" >
          <tr id="trdark"> 
            <td width="31%"  > 
              <div align="center">Concepto</div>
            </td>
            <td width="5%" > 
              <div align="center">Banco</div>
            </td>
            <td width="13%"  > 
              <div align="center">Sucursal</div>
            </td>
            <td width="12%" > 
              <div align="center">Moneda</div>
            </td>
            <td width="21%" > 
              <div align="center">Referencia</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="31%" > 
              <div align="center"> 
                <input type="text" name="E09TRNOPC" value="<%= cdIntPrep.getE09TRNOPC().trim()%>" size="3" maxlength="3">
                <input type=HIDDEN name="E09TRNGLN" value="<%= cdIntPrep.getE09TRNGLN().trim()%>">
                <input type="text" size="25" maxlength="25" readonly name="E09TRNCON" value="<%= cdIntPrep.getE09TRNCON().trim()%>"
                   oncontextmenu="showPopUp(conceptHelp,this.name,'<%= cdIntPrep.getE09DEABNK().trim()%>','','E09TRNGLN','E09TRNOPC','<%= cdIntPrep.getE09DEAACD().trim()%>')">
              </div>
            </td>
            <td width="5%" > 
              <div align="center"> 
                <input type="text" size="2" maxlength="2" value="<%= cdIntPrep.getE09TRNBNK().trim()%>" name="E09TRNBNK">
              </div>
            </td>
            <td width="13%" > 
              <div align="center"> 
                <input type="text" size="3" maxlength="3" value="<%= cdIntPrep.getE09TRNBRN().trim()%>" name="E09TRNBRN"
                 oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E09TRNBNK.value,'','','','')">
              </div>
            </td>
            <td width="12%"  > 
              <div align="center"> 
                <input type="text" size="3" maxlength="3" name="E09TRNCCY" value="<%= cdIntPrep.getE09TRNCCY().trim()%>"
					oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E09TRNBNK.value,'','','','')">
			  </div>
            </td>
            <td width="21%" > 
              <div align="center"> 
                <input type="text" size="16" maxlength="16" value="<%= cdIntPrep.getE09TRNACC().trim()%>" name="E09TRNACC"
					oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E09TRNBNK.value,'','','','RT')">
               </div>
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
