<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<%@ page import = "java.lang.Object" %>
<HTML>
<HEAD>
<TITLE>
Cheques Certificados
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="dvDocDet" class= "datapro.eibs.beans.EOF012002Message"  scope="session"/>
<jsp:useBean id= "error"   class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO"  class= "datapro.eibs.beans.UserPos"  scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


</head>

<BODY>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0120" >
  <input type=HIDDEN name="SCREEN" value="204">


  <h3 align="center">Cancelaci&oacute;n de Cheques Certificados<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="of_chkcer_inq_det_cancel.jsp, EOF0120"></h3>
  <hr size="4">
  <BR>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="28%" > 
              <div align="right">N&uacute;mero de Cuenta :</div>
            </td>
            <td nowrap width="20%" > 
              <div > 
                <input type="hidden" name="E02OFMACC" value="<%= dvDocDet.getE02OFMACC()%>" readonly>
                <%= dvDocDet.getE02OFMACC().trim()%></div>
            </td>
            <td nowrap width="28%" > 
              <div align="right">N&uacute;mero Cheque :</div>
            </td>
            <td nowrap width="24%" > 
              <div>
                <input type="hidden" name="E02OFMCKN" value="<%= dvDocDet.getE02OFMCKN()%>" readonly>
                <%= dvDocDet.getE02OFMCKN().trim()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%"> 
              <div align="right">Banco / Sucur. :</div>
            </td>
            <td nowrap width="20%"> 
              <div >
                <input type="hidden" name="E02OFMBNK"  value="<%= dvDocDet.getE02OFMBNK()%>" readonly>
                <%= dvDocDet.getE02OFMBNK().trim()%> /
                <input type="hidden" name="E02OFMBRN"  value="<%= dvDocDet.getE02OFMBRN()%>" readonly>
                <%= dvDocDet.getE02OFMBRN().trim()%></div>
            </td>
            <td nowrap width="28%"> 
              <div align="right">Cuenta Contable :</div>
            </td>
            <td nowrap width="24%" > 
              <div >
                <input type="hidden" name="E02DEBGLN"  value="<%= dvDocDet.getE02DEBGLN()%>" readonly>
                <%= dvDocDet.getE02DEBGLN().trim()%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%"> 
              <div align="right">Moneda :</div>
            </td>
            <td nowrap width="20%"> 
              <div >
                <input type="hidden" name="E02OFMCCY"  value="<%= dvDocDet.getE02OFMCCY()%>" readonly>
                <%= dvDocDet.getE02OFMCCY().trim()%></div>
            </td>
            <td nowrap width="28%"> 
              <div align="right">Monto :</div>
            </td>
            <td nowrap width="24%" > 
              <div align="right">
                <input type="hidden" name="E02OFMAMT"  value="<%= dvDocDet.getE02OFMAMT()%>" readonly>
                <%= Util.fcolorCCY(dvDocDet.getE02OFMAMT().trim())%></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <BR>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="28%"> 
              <div align="right">Aplicante :</div>
            </td>
            <td nowrap width="20%"> 
              <div>
                <input type="hidden" name="E02OFMAPL"  value="<%= dvDocDet.getE02OFMAPL()%>" readonly>
                <%= dvDocDet.getE02OFMAPL().trim()%></div>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Beneficiario :</div>
            </td>
            <td nowrap width="26%" > 
              <div >
                <input type="hidden" name="E02OFMBNF"  value="<%= dvDocDet.getE02OFMAPL()%>" readonly>
                <%= dvDocDet.getE02OFMBNF().trim()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" > 
              <div align="right">Detalle Pago :</div>
            </td>
            <td nowrap width="20%" > 
              <div ><%= dvDocDet.getE02OFMCO1().trim()%></div>
            </td>
            <td nowrap width="26%" > 
              <div align="right">Sucur. Pago :</div>
            </td>
            <td nowrap width="26%" > 
              <div ><%= dvDocDet.getE02DEBBRN().trim()%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%">&nbsp;</td>
            <td nowrap width="20%">
              <div align="right"><%= dvDocDet.getE02OFMAP2().trim()%></div>
            </td>
            <td nowrap width="26%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="26%">
              <div align="right"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%"> 
              <div align="right">Emitido Por :</div>
            </td>
            <td nowrap width="20%"> 
              <div ><%= dvDocDet.getE02OFMUSR().trim()%></div>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Fecha Emision :</div>
            </td>
            <td nowrap width="26%"> 
              <div > <%= Util.formatDate(dvDocDet.getE02OFMEM1(),dvDocDet.getE02OFMEM2(),dvDocDet.getE02OFMEM3())%></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
  </FORM>

</BODY>
</HTML>
