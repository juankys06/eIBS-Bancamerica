<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<%@ page import = "java.lang.Object" %>
<HTML>
<HEAD>
<TITLE>
Official Checks
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id="dvDocDet" class= "datapro.eibs.beans.ETL051002Message"  scope="session"/>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


</HEAD>

<BODY>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0100" >
  <input type=HIDDEN name="SCREEN" value="8">


  <h3 align="center">Cheques de Gerencia - Orden de No Pago<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="of_inq_det.jsp, EOF0100"></h3>
  <hr size="4">
  <BR>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="28%" > 
              <div align="right">Numero Cheque:</div>
            </td>
            <td nowrap width="20%" > 
              <div > 
                <input type="hidden" name="E01STPCKN"  value="<%= dvDocDet.getE02OFMNCH()%>" readonly>
                <%= dvDocDet.getE02OFMNCH().trim()%></div>
            </td>
            <td nowrap width="28%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="24%" > 
              <div align="right"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%"> 
              <div align="right">Banco / Sucur. :</div>
            </td>
            <td nowrap width="20%"> 
              <div >
                <input type="hidden" name="E01STPBNK"  value="<%= dvDocDet.getE02OFMBNK()%>" readonly>
                <%= dvDocDet.getE02OFMBNK().trim()%> /
                <input type="hidden" name="E01STPBRN"  value="<%= dvDocDet.getE02OFMBRN()%>" readonly>
                <%= dvDocDet.getE02OFMBRN().trim()%></div>
            </td>
            <td nowrap width="28%"> 
              <div align="right">Cuenta Contable :</div>
            </td>
            <td nowrap width="24%" > 
              <div >
                <input type="hidden" name="E01STPGLN"  value="<%= dvDocDet.getE02OFMGLN()%>" readonly>
                <%= dvDocDet.getE02OFMGLN().trim()%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%"> 
              <div align="right">Moneda :</div>
            </td>
            <td nowrap width="20%"> 
              <div >
                <input type="hidden" name="E01STPCCY"  value="<%= dvDocDet.getE02OFMCCY()%>" readonly>
                <%= dvDocDet.getE02OFMCCY().trim()%></div>
            </td>
            <td nowrap width="28%"> 
              <div align="right">Monto :</div>
            </td>
            <td nowrap width="24%" > 
              <div align="right">
                <input type="hidden" name="E01STPAMT"  value="<%= dvDocDet.getE02OFMMCH()%>" readonly>
                <%= Util.fcolorCCY(dvDocDet.getE02OFMMCH().trim())%></div>
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
                <input type="hidden" name="E01STPAPL"  value="<%= dvDocDet.getE02OFMAPL()%>" readonly>
                <%= dvDocDet.getE02OFMAPL().trim()%></div>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Beneficiario :</div>
            </td>
            <td nowrap width="26%" > 
              <div >
                <input type="hidden" name="E01STPBNF"  value="<%= dvDocDet.getE02OFMAPL()%>" readonly>
                <%= dvDocDet.getE02OFMBNF().trim()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" > 
              <div align="right">Detalle Pago :</div>
            </td>
            <td nowrap width="20%" > 
              <div ><%= dvDocDet.getE02OFMAP1().trim()%></div>
            </td>
            <td nowrap width="26%" > 
              <div align="right">Sucur. Pago :</div>
            </td>
            <td nowrap width="26%" > 
              <div ><%= dvDocDet.getE02PMTBRN().trim()%></div>
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
          <tr id="trdark"> 
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
              <div > <%= Util.formatDate(dvDocDet.getE02OFMED1(),dvDocDet.getE02OFMED2(),dvDocDet.getE02OFMED3())%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%"> 
              <div align="right">Pagado Por :</div>
            </td>
            <td nowrap width="20%"> 
              <div><%= dvDocDet.getE02OFMUCN().trim()%></div>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Fecha Pago :</div>
            </td>
            <td nowrap width="26%" > 
              <div ><%= Util.formatDate(dvDocDet.getE02OFMMD1(),dvDocDet.getE02OFMMD2(),dvDocDet.getE02OFMMD3())%></div>
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
