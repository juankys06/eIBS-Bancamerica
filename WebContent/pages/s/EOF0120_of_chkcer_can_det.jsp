<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Cancellation</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="canchk" class= "datapro.eibs.beans.EOF012002Message"  scope="session"/>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<SCRIPT Language="Javascript">

   

   builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }


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


<H3 align="center">Cancelaci&oacute;n de Cheques Certificados</H3>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="of_chkcer_can_det.jsp , EOF0120"> 
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0120" >
  <input type=HIDDEN name="SCREEN" value="206">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cheque :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E02OFMCKN" size="9" maxlength="9" value="<%= canchk.getE02OFMCKN()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E02OFMCCY" size="4" maxlength="3" value="<%= canchk.getE02OFMCCY()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Banco :</b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E02OFMBNK" size="4" maxlength="3" value="<%= canchk.getE02OFMBNK()%>" readonly>
                </b> </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Sucursal :</b></div>
            </td>
            <td nowrap width="20%"><b> 
              <input type="text" name="E02OFMBRN" size="4" maxlength="3" value="<%= canchk.getE02OFMBRN()%>" readonly>
              </b> </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Monto :</b></div>
            </td>
            <td nowrap width="16%"> 
              <input type="text" name="E02OFMAMT" size="15" maxlength="15" value="<%= canchk.getE02OFMAMT()%>" readonly>
            </td>
            <td nowrap colspan="2">&nbsp; </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br><table  class="tableinfo" >
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="center"><b></b></div>
            </td>
            <td nowrap > 
              <h4 align="center"><b>Banco</b></h4>
            </td>
            <td nowrap><h4 align="center"><b>Sucursal</b></h4>
            </td>
            <td nowrap > 
              <h4 align="center"><b>Moneda</b></h4>
            </td>
            <td nowrap > 
              <h4 align="center"><b>Cuenta Contable</b></h4>
            </td>
            <td nowrap > 
              <h4 align="center"><b>Referencia</b></h4>
            </td>
            <td nowrap > 
              <h4 align="center"><b>Centro de Costo</b></h4>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap height="22" align="center"> 
	             Cuenta  Credito:
            </td>
            <td nowrap height="22"> 
              <div align="center"> 
                <input type="text" name="E02CREBNK" size="2" maxlength="2" value="<%= canchk.getE02CREBNK()%>">
              </div>
            </td>
            <td nowrap height="22"> 
              <div align="center"> 
                <input type="text" name="E02CREBRN" size="3" maxlength="3"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E02CREBNK.value,'','','','')" value="<%= canchk.getE02CREBRN()%>">
              </div>
            </td>
            <td nowrap height="22"> 
              <div align="center"> 
                <input type="text" name="E02CRECCY" size="3" maxlength="3" value="<%= canchk.getE02CRECCY()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E02CREBNK.value,'','','','')">
              </div>
            </td>
            <td nowrap height="22"> 
              <div align="center"> 
                <input type="text" name="E02CREGLN" size="17" maxlength="16" value="<%= canchk.getE02CREGLN()%>" 
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02CREBNK.value,document.forms[0].E02CRECCY.value,'','','')" >
              </div>
            </td>
            <td nowrap height="22"> 
              <div align="center"> 
                <input type="text" name="E02CREACC" size="12" maxlength="9" value="<%= canchk.getE02CREACC()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E02CREBNK.value,'','','','RT')" >
              </div>
            </td>
            <td nowrap height="22"> 
              <div align="center"> 
                <input type="text" name="E02CRECCN" size="12" maxlength="9" value="<%= canchk.getE02CRECCN()%>"
                oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E02CREBNK.value,'','','','')" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap height="24" align="center">Cuenta  Debito:</td>
            <td nowrap height="24"> 
              <div align="center"> 
                <input type="text" name="E02DEBBNK" size="2" maxlength="2" value="<%= canchk.getE02DEBBNK()%>">
              </div>
            </td>
            <td nowrap height="24"> 
              <div align="center"> 
                <input type="text" name="E02DEBBRN" size="3" maxlength="3"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E02DEBBRN.value,'','','','')" value="<%= canchk.getE02DEBBRN()%>">
              </div>
            </td>
            <td nowrap height="24"> 
              <div align="center"> 
                <input type="text" name="E02DEBCCY" size="3" maxlength="3" value="<%= canchk.getE02DEBCCY()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E02DEBBNK.value,'','','','')">
              </div>
            </td>
            <td nowrap height="24"> 
              <div align="center"> 
                <input type="text" name="E02DEBGLN" size="17" maxlength="16" value="<%= canchk.getE02DEBGLN()%>" 
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02DEBBNK.value,document.forms[0].E02DEBCCY.value,'','','')" >
              </div>
            </td>
            <td nowrap height="24"> 
              <div align="center"> 
                <input type="text" name="E02DEBACC" size="12" maxlength="9" value="<%= canchk.getE02DEBACC()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E02DEBBNK.value,'','','','RT')" >
              </div>
            </td>
            <td nowrap height="22"> 
              <div align="center"> 
                <input type="text" name="E02DEBCCN" size="12" maxlength="9" value="<%= canchk.getE02DEBCCN()%>"
                oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E02DEBBNK.value,'','','','')" >
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <table class="tableinfo" width="283">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="44%" height="19"> 
              <div align="right">Observaciones :</div>
            </td>
            <td nowrap height="19" colspan="3"> 
              <input type="text" name="E02DESCRIP" size="45" maxlength="45" value="<%= canchk.getE02DESCRIP()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
 <div align="center"> 
	   <input id="EIBSBTN" type=submit name="Enviar" value="Enviar">
  </div>
  </form>
</body>
</html>
