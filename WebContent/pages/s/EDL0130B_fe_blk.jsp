<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Certificates</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="deal" class="datapro.eibs.beans.EDL013111Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

	builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
function GetProductB(name,desc,app,bank)
{
	page= prefix +language + "EWD0008B_client_help_container.jsp"
	fieldName=name;
	fieldAux1 = desc; 
	AppCode = app;
	ProductBank = bank;				
	CenterWindow(page,600,400,1);
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

<h3 align="center"> Financial Blocks - Back Office<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="fe_blk.jsp, EDL0130B"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B" >
<%
String ogen = "";
if (deal.getE11DLSSTS().equals("T")) {
	ogen = "Tesorería";
} else if (deal.getE11DLSSTS().equals("F")) {
	ogen = "Fideicomiso";
}  else if (deal.getE11DLSSTS().equals("E")) {
	ogen = "FEM";
}  else if (deal.getE11DLSSTS().equals("R")) {
	ogen = "Terceros";
}
%>
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="22">
  <INPUT TYPE=HIDDEN NAME="E11DEABNK"  value="<%= deal.getE11DEABNK().trim()%>">
  <input type=HIDDEN name="E11DEAACD"  value="<%= deal.getE11DEAACD().trim()%>">
  <input type=HIDDEN name="E11DEACCY"  value="<%= deal.getE11DEACCY().trim()%>">
  <input type=HIDDEN name="E11DEATYP"  value="<%= deal.getE11DEATYP().trim()%>">
  <table class="tableinfo" width="100%" >
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>Contraparte :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" > 
              <div align="left"> 
                <input type="hidden" name="E11CUSNA1"  value="<%= deal.getE11CUSNA1()%>" >
                <input type="hidden" name="E11DEACUN"  value="<%= deal.getE11DEACUN()%>" >
                <%= deal.getE11DEACUN()%> - <%= deal.getE11CUSNA1()%> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>Localizaci&oacute;n :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" > 
              <input type="hidden" name="E11CUSNA2"  value="<%= deal.getE11CUSNA2()%>" >
              <%= deal.getE11CUSNA2()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" width="85%" > 
              <input type="hidden" name="E11CUSNA3"  value="<%= deal.getE11CUSNA3()%>">
              <%= deal.getE11CUSNA3()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>Orden Generada :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" > 
              <input type="hidden" name="E11DLSSTS"  value="<%= deal.getE11DLSSTS()%>" >
              <%= ogen%></td>
          </tr>
          
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n B&aacute;sica</h4>
  <table  class="tableinfo" width="597">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="34%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="25%" >  
            </td>
            <td nowrap width="20%" > 
              <div align="right"> </div>
            </td>
            <td nowrap width="21%" >&nbsp; </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="34%" > 
              <div align="right">Valor Nominal :</div>
            </td>
            <td nowrap width="25%" ><%= Util.fcolorCCY(deal.getE11DEAOAM())%> 
            </td>
            <td nowrap width="20%" > 
              <div align="right">Moneda :</div>
            </td>
            <td nowrap width="21%"><%= deal.getE11DEACCY().trim()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="34%" > 
              <div align="right">Fecha Valor :</div>
            </td>
            <td nowrap width="25%" > <%= Util.formatDate(deal.getE11DEASD1(),deal.getE11DEASD2(),deal.getE11DEASD3())%> 
            </td>
            <td nowrap align="right" width="20%">Fecha de Vencimiento :</td>
            <td nowrap width="21%"> <%= Util.formatDate(deal.getE11DEAMD1(),deal.getE11DEAMD2(),deal.getE11DEAMD3())%> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="34%" > 
              <div align="right">N&uacute;mero de Referencia :</div>
            </td>
            <td nowrap width="25%" ><%= deal.getE11DEAREF()%></td>
            <td nowrap align="right" width="20%">&nbsp;</td>
            <td nowrap width="21%">&nbsp; </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="34%" > 
              <div align="right">Notas :</div>
            </td>
            <td nowrap colspan="3" ><%= deal.getE11DLSOT1().trim()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="34%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" ><%= deal.getE11DLSOT2().trim()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="34%" > 
              <div align="right">Tesorero :</div>
            </td>
            <td nowrap colspan="3" ><%= deal.getE11DLSUSR().trim()%> - <%= deal.getE11DLSDSC().trim()%></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4> Informaci&oacute;n General </h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="20%" colspan="2" > 
              <div align="right">Producto :</div>
            </td>
            <td nowrap colspan="2" > 
              <input type="text" name="E11DEAPRO" size="5" maxlength="4" value="<%= deal.getE11DEAPRO()%>" 
			  >
              <a href="javascript:GetProductB('E11DEAPRO','E11DLSPDS','<%= deal.getE11DEAACD().trim()%>','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
              <input type="text" name="E11DLSPDS" size="50" maxlength="70" value="<%= deal.getE11DLSPDS()%>" 
			  >
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Cuenta Contrapartida</h4>
  <table class="tableinfo">
    <tr id="trdark"> 
      <td nowrap width="32%"> 
        <div align="center">Concepto</div>
      </td>
      <td nowrap width="14%"> 
        <div align="center">Banco</div>
      </td>
      <td nowrap width="17%"> 
        <div align="center">Sucursal</div>
      </td>
      <td nowrap width="22%"> 
        <div align="center">Moneda</div>
      </td>
      <td nowrap width="15%"> 
        <div align="center">Referencia</div>
      </td>
    </tr>
    <tr id="trclear"> 
      <td nowrap width="32%" > 
        <div align="center"> 
          <input type="text" name="E11OFFOP1" value="<%= deal.getE11OFFOP1().trim()%>" size="2" maxlength="2">
          <input type="hidden" name="E11OFFGL1" value="<%= deal.getE11OFFGL1().trim()%>">
          <input type="text" name="E11OFFCO1" size="25" maxlength="25"  value="<%= deal.getE11OFFCO1().trim()%>"
                  oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E11DEABNK.value,'','E11OFFGL1','E11OFFOP1','95')">
        </div>
      </td>
      <td nowrap width="14%" > 
        <div align="center"> 
          <input type="text" name="E11OFFBK1" size="2" maxlength="2" value="<%= deal.getE11OFFBK1().trim()%>" onKeyPress="enterInteger()">
        </div>
      </td>
      <td nowrap width="17%" > 
        <div align="center"> 
          <input type="text" name="E11OFFBR1" size="3" maxlength="3" value="<%= deal.getE11OFFBR1().trim()%>" onKeyPress="enterInteger()"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E11DEABNK.value,'','','','')">
        </div>
      </td>
      <td nowrap width="22%" > 
        <div align="center"> 
          <input type="text" name="E11OFFCY1" size="3" maxlength="3" value="<%= deal.getE11OFFCY1().trim()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E11DEABNK.value,'','','','')">
        </div>
      </td>
      <td nowrap width="15%" > 
        <div align="center"> 
          <input type="text" name="E11OFFAC1" size="13" maxlength="12"  value="<%= deal.getE11OFFAC1().trim()%>" onKeyPress="enterInteger()"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E11DEABNK.value,'','','','RT')" >
        </div>
      </td>
    </tr>
  </table>
  <h4>L&iacute;mites</h4>
  <table  class="tableinfo" width="445" >
    <tr > 
      <td nowrap> 
        <table width="100%">
          <tr id="trdark"> 
            <td nowrap>&nbsp;</td>
            <td nowrap  colspan="2"> 
              <div align="center"><b>Monto L&iacute;mite</b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>Monto L&iacute;mite <br>Utilizado </b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>Monto L&iacute;mite <br>Disponible</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap>L&iacute;neas de Cr&eacute;dito</td>
            <td nowrap  colspan="2"> 
              <div align="right"><%= Util.fcolorCCY(deal.getE11DLSAMT1())%></div>
            </td>
            <td nowrap > 
              <div align="right"><%= Util.fcolorCCY(deal.getE11DLSAMT3())%></div>
            </td>
            <td nowrap > 
              <div align="right"><%= Util.fcolorCCY(deal.getE11DLSAMT2())%></div>
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
