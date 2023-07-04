<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Treasury Module</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="deal" class="datapro.eibs.beans.EDL0120DSMessage"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT> 
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

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
<h3 align="center"> Orden de Confirmaci&oacute;n - Pr&eacute;stamos</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0120" >
<%
String ogen = "";
if (deal.getE01DLSSTS().equals("T")) {
	ogen = "Tesorería";
} else if (deal.getE01DLSSTS().equals("F")) {
	ogen = "Fideicomiso";
}  else if (deal.getE01DLSSTS().equals("E")) {
	ogen = "FEM";
}  else if (deal.getE01DLSSTS().equals("R")) {
	ogen = "Terceros";
}
%>
  <input type=HIDDEN name="SCREEN" value="2">
  <table class="tableinfo" width="100%" >
    <tr id="trclear"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>Contraparte :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" > 
              <div align="left"> 
                <input type="hidden" name="D01DLSCP1"  value="<%= deal.getD01DLSCP1()%>" readonly>
                <%= deal.getD01DLSCP1()%> </div>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="15%" >
              <div align="right"><b>Localizaci&oacute;n :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" >
              <input type="hidden" name="D01DLSCP2"  value="<%= deal.getD01DLSCP2()%>" readonly>
              <%= deal.getD01DLSCP2()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" width="85%" >
              <input type="hidden" name="D01DLSCP3"  value="<%= deal.getD01DLSCP3()%>" readonly>
              <%= deal.getD01DLSCP3()%> </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="15%" >
              <div align="right"><b>Orden Generada :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" >
              <input type="hidden" name="E01DLSSTS"  value="<%= deal.getE01DLSSTS()%>" readonly>
              <%= ogen%></td>
          </tr>
          
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp; </p>
  <table  class="tableinfo" width="597">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="35%" > 
              <div align="right">N&uacute;mero de Referencia :</div>
            </td>
            <td nowrap width="25%" ><%= deal.getE01DLSREF()%></td>
            <td nowrap width="32%" > 
              <div align="right">Fecha Pacto :</div>
            </td>
            <td nowrap colspan="2" width="8%" ><%= Util.formatDate(deal.getE01DLSDD1(),deal.getE01DLSDD2(),deal.getE01DLSDD3())%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="35%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="25%" >  
            </td>
            <td nowrap width="32%" > 
              <div align="right"> Moneda :</div>
            </td>
            <td nowrap colspan="2" width="8%" > <%= deal.getE01DLSCCY().trim()%> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="35%" > 
              <div align="right">Monto :</div>
            </td>
            <td nowrap width="25%" ><%= Util.fcolorCCY(deal.getE01DLSAMN())%> 
            </td>
            <td nowrap width="32%" > 
              <div align="right">Tasa :</div>
            </td>
            <td nowrap colspan="2" width="8%"> <%= deal.getE01DLSRA1()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="35%" > 
              <div align="right">Fecha Valor :</div>
            </td>
            <td nowrap width="25%" > <%= Util.formatDate(deal.getE01DLSVD1(),deal.getE01DLSVD2(),deal.getE01DLSVD3())%> 
            </td>
            <td nowrap align="right" width="32%">Fecha de Vencimiento :</td>
            <td nowrap colspan="2" width="8%"> <%= Util.formatDate(deal.getE01DLSMA1(),deal.getE01DLSMA2(),deal.getE01DLSMA3())%> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="35%" > 
              <div align="right">Plazo :</div>
            </td>
            <td nowrap width="25%" > 
              <div align="left"><%= deal.getE01DLSROY()%>- 
                <% if(deal.getE01DLSODA().equals("D")) out.print("Dias");
					   else if(deal.getE01DLSODA().equals("M")) out.print("Mes");
					    else if(deal.getE01DLSODA().equals("Y")) out.print("Años");
						else out.print("");%>
              </div>
            </td>
            <td nowrap align="right" width="32%"> 
              <div align="right">Tipo de Calculo Interes :</div>
            </td>
            <td nowrap colspan="2" width="8%"> 
              <div align="left">
                <% if(deal.getE01DEARRP().equals("1")) out.print("Actual/actual (en periodo)");
					   else if(deal.getE01DEARRP().equals("2")) out.print("Actual/365");
					    else if(deal.getE01DEARRP().equals("3")) out.print("Actual/365 (366 en bisiesto)");
                         else if(deal.getE01DEARRP().equals("4")) out.print("Actual/360");
						 else if(deal.getE01DEARRP().equals("5")) out.print("30/360");
						 else if(deal.getE01DEARRP().equals("6")) out.print("30E/360");
						else out.print("");%>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="35%" > 
              <div align="right">Interes :</div>
            </td>
            <td nowrap width="25%" > 
              <div align="left"><%= Util.fcolorCCY(deal.getE01DLSAM5())%></div>
            </td>
            <td nowrap align="right" width="32%"> 
              <div align="right">Capital + Interes :</div>
            </td>
            <td nowrap colspan="2" width="8%"> 
              <div align="left"><%= Util.fcolorCCY(deal.getE01DLSAM1())%></div>
            </td>
          </tr>
          <tr id="trclear"> 
          <% if(deal.getH01FLGWK3().equals("Y")){%>
          	<td nowrap width="21%" > 
              <div align="right">Producto : </div>
            </td>
            <td nowrap><%= deal.getE01DLSPRO()%> - <%= deal.getD01PRDDSC()%></td>
           <%} else {%>
            <td nowrap width="21%" > 
              <div align="right"></div>
            </td>
            <td nowrap >&nbsp; </td>
           <%}%> 
            <td nowrap > 
              <div align="right"> </div>
            </td>
            <td nowrap > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="35%" > 
              <div align="right">Notas :</div>
            </td>
            <td nowrap colspan="4" ><%= deal.getE01DLSOT1().trim()%></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="35%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="4" ><%= deal.getE01DLSOT2().trim()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="35%" > 
              <div align="right">Tesorero :</div>
            </td>
            <td nowrap colspan="4" ><%= deal.getE01DLSDID().trim()%> - <%= deal.getD01USRDSC().trim()%></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <table  class="tableinfo" >
    <tr > 
      <td nowrap> 
        <table width="100%">
          <tr id="trdark"> 
            <td nowrap>&nbsp;</td>
            <td nowrap  colspan="2"> 
              <div align="center"><b>Monto L&iacute;mite</b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>Monto L&iacute;mite <br>Disponible</b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>Monto L&iacute;mite <br>Final</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap>L&iacute;neas de Cr&eacute;dito</td>
            <td nowrap  colspan="2"> 
              <div align="right"><%= Util.fcolorCCY(deal.getD01LIMAMT())%>:</div>
            </td>
            <td nowrap > 
              <div align="right"><%= Util.fcolorCCY(deal.getD01LIMAVL())%></div>
            </td>
            <td nowrap > 
              <div align="right"><b><%= Util.fcolorCCY(deal.getD01LIMEND())%></b></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <script language="javascript">
  eIBSPrint();
</script>
</form>
</body>
</html>
