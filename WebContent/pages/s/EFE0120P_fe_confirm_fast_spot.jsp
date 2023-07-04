<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Foreign Exchange Module</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="fex" class="datapro.eibs.beans.EFE0120DSMessage"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "trOption" class= "datapro.eibs.beans.TrOption"  scope="session" />

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

String D01FESCP1;
D01FESCP1 = fex.getD01FESCP1();


String sTitle = "Fast Spot";
try {
  sTitle = trOption.getHeader2();
} catch (Exception e) {
  sTitle = "Fast Spot";	
}
if (sTitle == null) sTitle = "Fast Spot";

%>
<h3 align="center"> Confirmaci&oacute;n - <%= sTitle%> </h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120P" >
<%
String ogen = "";
if (fex.getE01FESTIN().equals("T")) {
	ogen = "Tesorería";
} else if (fex.getE01FESTIN().equals("F")) {
	ogen = "Fideicomiso";
}  else if (fex.getE01FESTIN().equals("E")) {
	ogen = "FEM";
}  else if (fex.getE01FESTIN().equals("R")) {
	ogen = "Terceros";
}
%>
 <%if(!D01FESCP1.equals("")){%>
  <table class="tableinfo" width="100%" >
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>Contraparte :</b></div>
            </td>
            <td nowrap  width="85%" > 
              <div align="left"> 
                <input type="hidden" name="D01FESCP1"  value="<%= fex.getD01FESCP1()%>" >
                <%= fex.getD01FESCP1()%> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>Localizaci&oacute;n :</b></div>
            </td>
            <td nowrap  width="85%" > 
              <input type="hidden" name="D01FESCP2"  value="<%= fex.getD01FESCP2()%>" >
              <%= fex.getD01FESCP2()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"></div>
            </td>
            <td nowrap  width="85%" > 
              <input type="hidden" name="D01FESCP3"  value="<%= fex.getD01FESCP3()%>" >
              <%= fex.getD01FESCP3()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>Orden Generada :</b></div>
            </td>
            <td nowrap  width="85%" > 
              <input type="hidden" name="E01FESTIN"  value="<%= fex.getE01FESTIN()%>" >
              <%= ogen%></td>
          </tr>
          
        </table>
      </td>
    </tr>
  </table>
 <%}%>
  <br>
  <table  class="tableinfo" width="545">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="39%" > 
              <div align="right">Fecha Pacto :</div>
            </td>
            <td nowrap width="17%" ><%= Util.formatDate(fex.getE01FESDD1(),fex.getE01FESDD2(),fex.getE01FESDD3())%><%= userPO.getHeader1()%></td>
            <td nowrap width="20%" > 
              <div align="right">N&uacute;mero de Referencia :</div>
            </td>
            <td nowrap width="24%" > 
              <div align="left"><%= fex.getE01FESREF()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="39%" > 
              <div align="right">Acci&oacute;n Tomada :</div>
            </td>
            <td nowrap width="17%" ><% if(fex.getE01FESSBT().equals("PU")) out.print("Compra");
						else out.print("Venta");%></td>
			<td nowrap><div align="right">Concepto : </div></td>
            <td nowrap > <%=fex.getE01FESBRC()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="39%" > 
              <div align="right">Moneda Primaria :</div>
            </td>
            <td nowrap width="17%" > <%= fex.getE01FESCCY().trim()%> : <%= Util.fcolorCCY(fex.getE01FESAMN())%> 
            </td>
            <td nowrap width="20%" > 
              <div align="right">Tasa Spot :</div>
            </td>
            <td nowrap width="24%"> <%= fex.getE01FESEXR()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="39%" > 
              <div align="right">Moneda Base :</div>
            </td>
            <td nowrap width="17%" > <%= fex.getE01FESDCY().trim()%> : <%= Util.fcolorCCY(fex.getE01FESDAM())%> 
            </td>
            <td nowrap align="right" width="20%">Fecha Valor :</td>
            <td nowrap width="24%"> <%= Util.formatDate(fex.getE01FESVD1(),fex.getE01FESVD2(),fex.getE01FESVD3())%> 
            </td>
          </tr>
          
          <tr id="trclear"> 
          	<% if(fex.getH01FLGWK3().equals("Y")){%>
            	<td nowrap width="39%" > 
              		<div align="right">Clasificaci&oacute;n :</div>
            	</td>
            	<td nowrap  colspan="3" ><%= fex.getE01FESCLS().trim()%> </td> 
             <%}%>             
          </tr>          
          
          <tr id="trclear"> 
            <td nowrap width="39%" > 
              <div align="right">Notas :</div>
            </td>
            <td nowrap colspan="3" ><%= fex.getE01FESOT1().trim()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="39%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" ><%= fex.getE01FESOT2().trim()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="39%" > 
              <div align="right">Tesorero :</div>
            </td>
            <td nowrap colspan="3" ><%= fex.getE01FESDID()%> - <%= fex.getD01FEGDSC().trim()%></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <%if(!D01FESCP1.equals("")){%>
  <table  class="tableinfo" >
    <tr > 
      <td nowrap> 
        <table width="100%">
          <tr id="trdark"> 
            <td nowrap  colspan="2"> 
              <div align="center"><b>L&iacute;mites</b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>L&iacute;nea de Cr&eacute;dito</b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>Actividad Diaria</b></div>
            </td>
            <td nowrap > 
              <div align="right">Posici&oacute;n D&iacute;a Anterior:</div>
            </td>
            <td nowrap ><%= Util.fcolorCCY(fex.getD01YTDBAL())%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap  colspan="2"> 
              <div align="right">Monto L&iacute;mite :</div>
            </td>
            <td nowrap > 
              <div align="center"><%= Util.fcolorCCY(fex.getD01LIMAMT())%></div>
            </td>
            <td nowrap > 
              <div align="center"><%= Util.fcolorCCY(fex.getD01FEOLIM())%></div>
            </td>
            <td nowrap > 
              <div align="right">(+) Compras :</div>
            </td>
            <td nowrap ><%= Util.fcolorCCY(fex.getD01TOTPUR())%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="right">L&iacute;mite Disponible :</div>
            </td>
            <td nowrap > 
              <div align="center"> <%= Util.fcolorCCY(fex.getD01LIMAVL())%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= Util.fcolorCCY(fex.getD01FEOAVL())%> </div>
            </td>
            <td nowrap > 
              <div align="right">(-) Ventas :</div>
            </td>
            <td nowrap ><%= Util.fcolorCCY(fex.getD01TOTSAL())%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap  colspan="2"> 
              <div align="right">Monto L&iacute;mite Final :</div>
            </td>
            <td nowrap > 
              <div align="center"> <%= Util.fcolorCCY(fex.getD01LIMEND())%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= Util.fcolorCCY(fex.getD01FEOEND())%> </div>
            </td>
            <td nowrap > 
              <div align="right">Disponible :</div>
            </td>
            <td nowrap ><%= Util.fcolorCCY(fex.getD01POSBAL())%> </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <%}%>
  </form>
</body>
</html>
