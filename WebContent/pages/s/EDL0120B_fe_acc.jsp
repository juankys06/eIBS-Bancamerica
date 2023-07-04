<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Foreign Exchange Module</title>
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
<h3 align="center">Aceptaciones Bancarias - Back Office<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_acc.jsp,EDL0120B"> 
</h3>
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
                <input type="hidden" name="D01DLSCP1"  value="<%= deal.getD01DLSCP1()%>">
                <%= deal.getD01DLSCP1()%> </div>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="15%" >
              <div align="right"><b>Localizaci&oacute;n :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" >
              <input type="hidden" name="D01DLSCP2"  value="<%= deal.getD01DLSCP2()%>" >
              <%= deal.getD01DLSCP2()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" width="85%" >
              <input type="hidden" name="D01DLSCP3"  value="<%= deal.getD01DLSCP3()%>" >
              <%= deal.getD01DLSCP3()%></td>
          </tr>
          <tr id="trclear">
            <td nowrap width="15%" >
              <div align="right"><b>Orden Generada :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" >
              <input type="hidden" name="E01DLSSTS"  value="<%= deal.getE01DLSSTS()%>" >
              <%= ogen%> </td>
          </tr>
          
        </table>
      </td>
    </tr>
  </table>
  <h4> 
    <input type=HIDDEN name="SCREEN" value="8">
    Informaci&oacute;n B&aacute;sica </h4>
  <table  class="tableinfo" width="736">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">N&uacute;mero de Referencia :</div>
            </td>
            <td nowrap ><%= deal.getE01DLSREF().trim()%></td>
            <td nowrap > 
              <div align="right">Fecha Pacto :</div>
            </td>
            <td nowrap ><%= Util.formatDate(deal.getE01DLSDD1(),deal.getE01DLSDD2(),deal.getE01DLSDD3())%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="23%" >  
            </td>
            <td nowrap width="18%" > 
              <div align="right">Moneda :</div>
            </td>
            <td nowrap width="20%" ><%= deal.getE01DLSCCY().trim()%> </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="21%" > 
              <div align="right">Valor Nominal : </div>
            </td>
            <td nowrap width="23%" > <%= Util.fcolorCCY(deal.getE01DLSAMN())%></td>
            <td nowrap width="18%" > 
              <div align="right">Tasa de Descuento :</div>
            </td>
            <td nowrap width="20%"> <%= deal.getE01DLSRA1().trim()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Monto Neto : </div>
            </td>
            <td nowrap width="23%" ><%= Util.fcolorCCY(deal.getE01DLSAM1())%> 
            </td>
            <td nowrap width="18%" > 
              <div align="right">Tasa Efectiva : </div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"><%= deal.getE01DLSRA2().trim()%> </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="21%" > 
              <div align="right">Fecha del Contrato :</div>
            </td>
            <td nowrap width="23%" ><%= Util.formatDate(deal.getE01DLSDD1(),deal.getE01DLSDD2(),deal.getE01DLSDD3())%> 
            </td>
            <td nowrap align="right" width="18%">Fecha Valor :</td>
            <td nowrap width="20%"><%= Util.formatDate(deal.getE01DLSVD1(),deal.getE01DLSVD2(),deal.getE01DLSVD3())%> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" >&nbsp;</td>
            <td nowrap >&nbsp;</td>
            <td nowrap > 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap ><%= Util.formatDate(deal.getE01DLSMA1(),deal.getE01DLSMA2(),deal.getE01DLSMA3())%></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="21%" > 
              <div align="right">Notas :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><%= deal.getE01DLSOT1().trim()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" >&nbsp;</td>
            <td nowrap colspan="3" ><%= deal.getE01DLSOT2().trim()%></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="21%" > 
              <div align="right">Tesorero :</div>
            </td>
            <td nowrap colspan="3" ><%= deal.getE01DLSDID().trim()%> - <%= deal.getD01PRDDSC().trim()%></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n Adicional </h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="10%" > 
              <div align="right">Producto :</div>
            </td>
            <td nowrap width="10%" > 
              <input type="text" name="E01DLSPRO" size="5" maxlength="4" value="<%= deal.getE01DLSPRO()%>">
              <a href="javascript:GetProduct('E01DLSPRO','14','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
              <input type="text" name="D01PRDDSC" size="30" value="<%= deal.getD01PRDDSC()%>" readonly>
            </td>
            <td nowrap width="36%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="10%" > 
              <div align="right">Tipo de Confirmaci&oacute;n :</div>
            </td>
            <td nowrap width="10%" > 
              <input type="text" name="E01DLSDLI" size="3" maxlength="1" value="<%= deal.getE01DLSDLI()%>" 
			  >
              <a href="javascript:GetCode('E01DLSDLI','STATIC_fe_bo_not.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a></td>
            <td nowrap width="36%" > 
              <div align="right">Forma de Pago :</div>
            </td>
            <td nowrap width="18%" > 
              <input type="text" name="E01DLSPVI" size="3" maxlength="1" value="<%= deal.getE01DLSPVI()%>" 
			  >
              <a href="javascript:GetCode('E01DLSPVI','STATIC_fe_bo_pay.jsp')"> 
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>L&iacute;mites</h4>
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
  <br>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td> 
        <div align="center"> </div>
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
  
  </form>
</body>
</html>
