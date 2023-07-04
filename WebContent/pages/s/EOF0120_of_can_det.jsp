<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Cancellation</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="canchk" class= "datapro.eibs.beans.EOF012001Message"  scope="session"/>

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


<H3 align="center">Cheques de Gerencia - Cancelacion</H3>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="of_can_det.jsp , EOF0120"> 
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0120" >
  <input type=HIDDEN name="SCREEN" value="16">
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
                <input type="text" name="E01OFMCKN" size="9" maxlength="9" value="<%= canchk.getE01OFMCKN()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01OFMCCY" size="4" maxlength="3" value="<%= canchk.getE01OFMCCY()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Banco :</b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01OFMBNK" size="4" maxlength="3" value="<%= canchk.getE01OFMBNK()%>" readonly>
                </b> </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Sucursal :</b></div>
            </td>
            <td nowrap width="20%"><b> 
              <input type="text" name="E01OFMBRN" size="4" maxlength="3" value="<%= canchk.getE01OFMBRN()%>" readonly>
              </b> </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Monto :</b></div>
            </td>
            <td nowrap width="16%"> 
              <input type="text" name="E01OFMAMT" size="15" maxlength="15" value="<%= canchk.getE01OFMAMT()%>" readonly>
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
            <td nowrap > 
              <div align="right"> Cuenta  Credito:</div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01CREBNK" size="2" maxlength="2" value="<%= canchk.getE01CREBNK()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01CREBRN" size="3" maxlength="3"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01CREBNK.value,'','','','')" value="<%= canchk.getE01CREBRN()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01CRECCY" size="3" maxlength="3" value="<%= canchk.getE01CRECCY()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01CREBNK.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01CREGLN" size="17" maxlength="16" value="<%= canchk.getE01CREGLN()%>" 
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01CREBNK.value,document.forms[0].E01CRECCY.value,'','','')" >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01CREACC" size="12" maxlength="9" value="<%= canchk.getE01CREACC()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01CREBNK.value,'','','','RT')" >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01CRECCN" size="12" maxlength="9" value="<%= canchk.getE01CRECCN()%>"
                oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E01CREBNK.value,'','','','')" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap >Cuenta  Debito:</td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01DEBBNK" size="2" maxlength="2" value="<%= canchk.getE01DEBBNK()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01DEBBRN" size="3" maxlength="3"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01DEBBRN.value,'','','','')" value="<%= canchk.getE01DEBBRN()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01DEBCCY" size="3" maxlength="3" value="<%= canchk.getE01DEBCCY()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01DEBBNK.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01DEBGLN" size="17" maxlength="16" value="<%= canchk.getE01DEBGLN()%>" 
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01DEBBNK.value,document.forms[0].E01DEBCCY.value,'','','')" >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01DEBACC" size="12" maxlength="9" value="<%= canchk.getE01DEBACC()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01DEBBNK.value,'','','','RT')" >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> </div>
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
              <input type="text" name="E01DESCRIP" size="45" maxlength="45" value="<%= canchk.getE01DESCRIP()%>">
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
