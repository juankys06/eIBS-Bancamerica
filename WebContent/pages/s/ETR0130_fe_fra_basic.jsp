<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Forward Rate Agreements</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="fra" class="datapro.eibs.beans.ETR0130DSMessage"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


</head>
<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 
<h3 align="center"> Forward Rates Agreements <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_fra_basic.jsp,ETR0130"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSETR0130" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
  <table class="tableinfo" width="315">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"><b>Contraparte :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="hidden" name="D01WFRCP1"  value="<%= fra.getD01WFRCP1()%>" readonly>
                <%= fra.getD01WFRCP1()%> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"><b>Localizaci�n :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="hidden" name="D01WFRCP2"  value="<%= fra.getD01WFRCP2()%>" readonly>
                <%= fra.getD01WFRCP2()%> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap >&nbsp;</td>
            <td nowrap colspan="3" > 
              <input type="hidden" name="D01WFRCP3"  value="<%= fra.getD01WFRCP3()%>" readonly>
              <%= fra.getD01WFRCP3()%> </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci�n B�sica</h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Tipo Contrato :</div>
            </td>
            <td nowrap ><%= fra.getE01WFRITP().trim()%></td>
            <td nowrap > 
              <div align="right">Acci�n Tomada :</div>
            </td>
            <td nowrap colspan="2">
              <% if(fra.getE01WFRSBT().equals("PU")) out.print("Compra");
						else out.print("Venta");%>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Moneda :</div>
            </td>
            <td nowrap ><%= fra.getE01WFRCCY().trim()%></td>
            <td nowrap > 
              <div align="right">L�nea de Cr�dito :</div>
            </td>
            <td nowrap colspan="2">
			  <%= fra.getE01WFRLCU().trim()%>
			  &nbsp;/&nbsp;
              <%= fra.getE01WFRCMM().trim()%>			
			</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Monto Te�rico :</div>
            </td>
            <td nowrap ><%= Util.fcolorCCY(fra.getE01WFROAM())%> </td>
            <td nowrap > 
              <div align="right">Tasa Contrato : </div>
            </td>
            <td nowrap colspan="2"><%= fra.getE01WFRRTE().trim()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Fecha Contrato :</div>
            </td>
            <td nowrap ><%= Util.formatDate(fra.getE01WFRDD1(),fra.getE01WFRDD2(),fra.getE01WFRDD3())%> 
            </td>
            <td nowrap align="right">Fecha Valor :</td>
            <td nowrap colspan="2"><%= Util.formatDate(fra.getE01WFRVD1(),fra.getE01WFRVD2(),fra.getE01WFRVD3())%> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">No. Referencia :</div>
            </td>
            <td nowrap ><%= fra.getE01WFRREF().trim()%> </td>
            <td nowrap > 
              <div align="right">Fecha Vencimiento :</div>
            </td>
            <td nowrap colspan="2"> <%= Util.formatDate(fra.getE01WFRMA1(),fra.getE01WFRMA2(),fra.getE01WFRMA3())%> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Notas :</div>
            </td>
            <td nowrap colspan="4" ><%= fra.getE01WFROT1().trim()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="4" ><%= fra.getE01WFROT2().trim()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Dealer :</div>
            </td>
            <td nowrap colspan="4" ><%= fra.getH01USERID().trim()%> - <%= fra.getD01USRDSC()%></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci�n Adicional</h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Producto :</div>
            </td>
            <td nowrap colspan="2" > 
              <input type="text" name="E01WFRPRO" size="5" maxlength="4" value="<%= fra.getE01WFRPRO()%>" 
			  >
              <a href="javascript:GetProductT('E01WFRPRO','D01APCDSC','','FRA')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a>
<input type="text" name="D01APCDSC" size="50" maxlength="70" value="<%= fra.getD01APCDSC()%>" 
			  >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">V�a de Pago :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01WFRPVI" size="3" maxlength="2" value="<%= fra.getE01WFRPVI()%>" 
			  >
              <a href="javascript:GetCode('E01WFRPVI','STATIC_fe_bo_pay.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a> 
            </td>
            <td nowrap > 
              <div align="left">Tipo Confirmaci�n : 
                <input type="text" name="E01WFRCFT" size="3" maxlength="2" value="<%= fra.getE01WFRCFT().trim()%>" 
			  >
                <a href="javascript:GetCode('E01WFRCFT','STATIC_fe_bo_not.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4 align="left">L�mites</h4>
  <table  class="tableinfo" >
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap  colspan="2"> 
              <h4 align="center">&nbsp;</h4>
            </td>
            <td nowrap width="21%"> 
              <h4 align="center">Monto L�mite</h4>
            </td>
            <td nowrap width="23%"> 
              <h4 align="center"><b>L�mite Disponible</b> </h4>
            </td>
            <td nowrap width="34%"> 
              <h4 align="center">Monto L�mite Final</h4>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap  colspan="2"> 
              <div align="right">L�nea de Cr�dito :</div>
            </td>
            <td nowrap width="21%" > 
              <div align="center"><%= fra.getD01LIMAMT()%></div>
            </td>
            <td nowrap width="23%" > 
              <div align="center"><%= fra.getD01LIMAVL()%></div>
            </td>
            <td nowrap width="34%" > 
              <div align="center"><%= fra.getD01LIMEND()%></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br><table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
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
