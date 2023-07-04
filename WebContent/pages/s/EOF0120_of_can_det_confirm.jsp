<html>
<head>
<title>Cancellation</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="canchk" class= "datapro.eibs.beans.EOF012001Message"  scope="session"/>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

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


<H3 align="center">Cheque de Gerencia - Cancelacion</H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSETL0510" >
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
              <div align="left"> <%= canchk.getE01OFMCKN()%> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> <%= canchk.getE01OFMCCY()%> </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Banco :</b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> <%= canchk.getE01OFMBNK()%> </b> </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Sucursal :</b></div>
            </td>
            <td nowrap width="20%"><b> <%= canchk.getE01OFMBRN()%> </b> </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Monto :</b></div>
            </td>
            <td nowrap width="16%"><%= canchk.getE01OFMAMT()%></td>
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
            <td nowrap> 
              <h4 align="center"><b>Sucursal</b></h4>
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
              <div align="center"> <%= canchk.getE01CREBNK()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= canchk.getE01CREBRN()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= canchk.getE01CRECCY()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= canchk.getE01CREGLN()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= canchk.getE01CREACC()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= canchk.getE01CRECCN()%> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap >Cuenta  Debito:</td>
            <td nowrap > 
              <div align="center"> <%= canchk.getE01DEBBNK()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= canchk.getE01DEBBRN()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= canchk.getE01DEBCCY()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= canchk.getE01DEBGLN()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= canchk.getE01DEBACC()%> </div>
            </td>
            <td nowrap > 
              <div align="center"><%= canchk.getE01CRECCN()%> </div>
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
            <td nowrap height="19" colspan="3"> <%= canchk.getE01DESCRIP()%> </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="44%" height="19">&nbsp;</td>
            <td nowrap height="19" colspan="3">This operation has been finished</td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
</form>
</body>
</html>
