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
String sTitle = " Swap - Moneda Extranjera";
try {
   sTitle = trOption.getHeader2();
} catch (Exception e) {
   sTitle = " Swap - Moneda Extranjera";
}   
if (sTitle == null) sTitle = " Swap - Moneda Extranjera";

%>
<h3 align="center"><%= sTitle%> <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_basic_swap.jsp,EFE0120P"> 
</h3>
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
  <input TYPE=HIDDEN name="SCREEN" value="16">
  <INPUT TYPE=HIDDEN NAME="E01FESTYP" VALUE="SWAP">
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
                <input type="hidden" name="D01FESCP1"  value="<%= fex.getD01FESCP1()%>" readonly>
                <%= fex.getD01FESCP1()%> </div>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="15%" >
              <div align="right"><b>Localizaci&oacute;n :</b></div>
            </td>
            <td nowrap  width="85%" >
              <input type="hidden" name="D01FESCP2"  value="<%= fex.getD01FESCP2()%>" readonly>
              <%= fex.getD01FESCP2()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="85%" ><%= fex.getD01FESCP3()%> 
              </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="15%" >
              <div align="right"><b>Orden Generada :</b></div>
            </td>
            <td nowrap  width="85%" >
              <input type="hidden" name="E01FESTIN"  value="<%= fex.getE01FESTIN()%>" readonly>
              <%= ogen%></td>
          </tr>
          
        </table>
      </td>
    </tr>
  </table>
  <BR>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap colspan="4" > 
              <div align="right">Fecha Pacto :<%= Util.formatDate(fex.getE01FESDD1(),fex.getE01FESDD2(),fex.getE01FESDD3())%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap > 
              <input type="hidden" name="E01FESOCY" value="<%= fex.getE01FESOCY()%>">
            </td>
            <td nowrap > 
				<div align="right">Tipo de Operación :</div>
            </td>
            <td nowrap > 
              <input type="hidden" name="E01FESSBT" value="<%= fex.getE01FESSBT()%>">
              <input type="radio" name="CE01FESSBT" value="PU" onClick="document.forms[0].E01FESSBT.value='PU'"
			  <%if(fex.getE01FESSBT().equals("PU")) out.print("checked");%>>
              Compra / Venta 
              <input type="radio" name="CE01FESSBT" value="SL" onClick="document.forms[0].E01FESSBT.value='SL'"
			  <%if(fex.getE01FESSBT().equals("SL")) out.print("checked");%>>
              Venta / Compra</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Moneda Primaria :</div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01FESCCY" size="4" maxlength="3" value="<%= fex.getE01FESCCY().trim()%>" >
                <a href="javascript:GetCurrency('E01FESCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
                <input type="text" name="E01FESAMN" size="15" maxlength="13" value="<%= fex.getE01FESAMN()%>" 
			  onKeyPress="enterDecimal()">
              </div>
            </td>
            <td nowrap > 
              <div align="right">Tasa Spot : </div>
            </td>
            <td nowrap > 
              <input type="text" name="E01FESEXR" size="11" maxlength="11" value="<%= fex.getE01FESEXR()%>" 
			  >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Moneda Base :</div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01FESDCY" size="4" maxlength="3" value="<%= fex.getE01FESDCY().trim()%>" >
                <a href="javascript:GetCurrency('E01FESDCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
                <input type="text" name="E01FESDAM" size="15" maxlength="13" value="<%= fex.getE01FESDAM()%>" 
			  onKeyPress="enterDecimal()" >
              </div>
            </td>
            <td nowrap align="right" > 
              <div align="right">Fecha Spot :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01FESVD1" size="3" maxlength="2" value="<%= fex.getE01FESVD1().trim()%>" 
			  >
              <input type="text" name="E01FESVD2" size="3" maxlength="2" value="<%= fex.getE01FESVD2().trim()%>" 
			  >
              <input type="text" name="E01FESVD3" size="3" maxlength="2" value="<%= fex.getE01FESVD3().trim()%>" 
			  >
              <a href="javascript:DatePicker(document.forms[0].E01FESVD1,document.forms[0].E01FESVD2,document.forms[0].E01FESVD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="middle" border="0"></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Concepto Spot :</div>
            </td>
            <td nowrap > <%=fex.getE01FESBRC()%></td>
            <td nowrap align="right" > 
              <div align="right">Tasa Forward :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01FESBRF" size="11" maxlength="11" value="<%= fex.getE01FESBRF()%>" 
			  >
              <input type="hidden" name="E01FESCMM"  value="<%= fex.getE01FESCMM()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Concepto Forward:</div>
            </td>
            <td nowrap > <%=fex.getE01FESBRS()%></td>
            <td nowrap > 
              <div align="right">Fecha Forward :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01FESMA1" size="3" maxlength="2" value="<%= fex.getE01FESMA1().trim()%>" 
			  >
              <input type="text" name="E01FESMA2" size="3" maxlength="2" value="<%= fex.getE01FESMA2().trim()%>" 
			  >
              <input type="text" name="E01FESMA3" size="3" maxlength="2" value="<%= fex.getE01FESMA3().trim()%>" 
			  >
              <a href="javascript:DatePicker(document.forms[0].E01FESMA1,document.forms[0].E01FESMA2,document.forms[0].E01FESMA3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="middle" border="0"></a> 
            </td>
          </tr>
          
          <tr id="trdark"> 
          	<% if(fex.getH01FLGWK3().equals("Y")){%> 
            <td nowrap > 
              <div align="right">Clasificaci&oacute;n Spot :</div>
            </td>
            <td nowrap   > 
              <input type="text" name="E01FESCLS" size="5" maxlength="4" value="<%= fex.getE01FESCLS()%>" 
			  >
              <a href="javascript:GetClassFex('E01FESCLS','SPT','<%= fex.getE01FESCCY()%>')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
            </td>
            <td nowrap  > 
              <div align="right">Clasificaci&oacute;n Forward :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01FESFLS" size="5" maxlength="4" value="<%= fex.getE01FESFLS()%>" 
			  >
              <a href="javascript:GetClassFex('E01FESFLS','FWR','<%= fex.getE01FESCCY()%>')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
            </td>
            <td nowrap >&nbsp;</td>
             <%}%>
            <td nowrap  colspan="5" > 
              <div align="right"></div>
            </td>         
          </tr>
          
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Notas :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01FESOT1" size="70" maxlength="60" value="<%= fex.getE01FESOT1()%>" 
			  >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01FESOT2" size="70" maxlength="60" value="<%= fex.getE01FESOT2()%>" 
			  >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Tesorero :</div>
            </td>
            <td nowrap colspan="3" ><%= fex.getE01FESDID()%> - <%= fex.getD01FEGDSC()%></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <BR> 
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
              <div align="center"><b>Actividad Diaria </b></div>
            </td>
            <td nowrap > 
              <div align="right">Posici&oacute;n D&iacute;a Anterior :</div>
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
          <tr id="trdark"> 
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
  <font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2">
  <input type="hidden" name="D01FESCP3"  value="<%= fex.getD01FESCP3()%>" readonly>
  </font></font></font></font></font>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"> 
          <input type="checkbox" name="H01FLGWK1" value="1" <% if(fex.getH01FLGWK1().equals("1")){ out.print("checked");} %>>
          Aceptar con Errores</div>
      </td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"> 
	      <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div> 
      </td>
    </tr>
    <tr bgcolor="#FFFFFF"> 
      <td> 
        <div align="center"> </div>
      </td>
    </tr>
  </table>
  <BR>
  </form>
</body>
</html>
