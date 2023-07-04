<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<%@ page import = "java.lang.Object" %>
<HTML>
<HEAD>
<TITLE>
Documentos Varios Detalles
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id="dvDocDet" class="datapro.eibs.beans.ETL051002Message"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


</HEAD>

<BODY>

<form>

  <h3 align="center">Consulta de Documentos Varios - Detalles<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="dv_inq_doc_det.jsp,ETL0510"> 
  </h3>
  <hr size="4">
  <h4>Información Básica</h4>
    <table class="tableinfo">
      <tr > 
        <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
          <tr id="trdark"> 
            <td nowrap width="28%" height="23" > 
              <div align="right">N&uacute;mero de Documento : </div>
            </td>
            <td nowrap width="20%" height="23" > 
              <%= dvDocDet.getE02OFMNCH().trim()%></td>
            <td nowrap width="27%" height="23" > 
              <div align="right">Tipo Documento :</div>
            </td>
            <td nowrap height="23" width="25%" > 
              <%= dvDocDet.getE02DSCDTY().trim()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" > 
              <div align="right">Referencia / Cuenta : </div>
            </td>
            <td nowrap width="20%" > 
              <%= dvDocDet.getE02OFMACC().trim()%></td>
            <td nowrap width="27%" > 
              <div align="right">Estado Documento :</div>
            </td>
            <td nowrap width="25%" > 
              <%= dvDocDet.getE02OFMSTS().trim()%></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%" height="10" > 
              <div align="right">Nombre / Cliente : </div>
            </td>
            <td nowrap width="20%" height="10" > 
              <%= dvDocDet.getE02CUSNA1().trim()%></td>
            <td nowrap width="27%" height="10" > 
              <div align="right">Origen Documento :</div>
            </td>
            <td nowrap width="25%" height="10" > 
              <%= dvDocDet.getE02DSCORI().trim()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" height="18" > 
              <div align="right">Banco / Sucursal : </div>
            </td>
            <td nowrap width="20%" height="18" > 
				<div align="left">
					<%= dvDocDet.getE02OFMBNK().trim()%> / <%= dvDocDet.getE02OFMBRN().trim()%>
				</div></td>
            <td nowrap width="27%" height="18" > 
              <div align="right">Cuenta Contable :</div>
            </td>
            <td nowrap height="18" width="25%" > 
              <%= dvDocDet.getE02OFMGLN().trim()%></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%" height="18" > 
              <div align="right">Moneda : </div>
            </td>
            <td nowrap width="20%" height="18" > 
              <%= dvDocDet.getE02OFMCCY().trim()%></td>
            <td nowrap width="27%" height="18" > 
              <div align="right">Valor :</div>
            </td>
            <td nowrap height="18" width="25%" > 
              <%= Util.fcolorCCY(dvDocDet.getE02OFMMCH().trim())%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" height="23" > 
              <div align="right">Aplicante :</div>
            </td>
            <td nowrap width="20%" height="23" > 
              <%= dvDocDet.getE02OFMAPL().trim()%>
			</td>
            <td nowrap width="26%" height="23" > 
              <div align="right">Beneficiario :</div>
            </td>
            <td nowrap height="23" width="26%" > 
              <%= dvDocDet.getE02OFMBNF().trim()%></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%" > 
              <div align="right">Detalle Pago :</div>
            </td>
            <td nowrap width="20%" > 
              <%= dvDocDet.getE02OFMAP1().trim()%></td>
            <td nowrap width="26%" > 
              <div align="right">Tipo Pago :</div>
            </td>
            <td nowrap width="26%" > 
              <%= dvDocDet.getE02DSCPAY().trim()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" height="10" >&nbsp;</td>
            <td nowrap width="20%" height="10" > 
              <%= dvDocDet.getE02OFMAP2().trim()%></td>
            <td nowrap width="26%" height="10" > 
              <div align="right">Sucursal Pago :</div>
            </td>
            <td nowrap width="26%" height="10" > 
              <%= dvDocDet.getE02PMTBRN().trim()%></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%" height="10" > 
              <div align="right">Emitido por :</div>
            </td>
            <td nowrap width="20%" height="10" > 
              <%= dvDocDet.getE02OFMUSR().trim()%></td>
            <td nowrap width="26%" height="10" > 
              <div align="right">Fecha Emisi&oacute;n :</div>
            </td>
            <td nowrap width="26%" height="10" > 
              <%= Util.formatDate(dvDocDet.getE02OFMED1(),dvDocDet.getE02OFMED2(),dvDocDet.getE02OFMED3())%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" height="18" > 
              <div align="right">Pagado por:</div>
            </td>
            <td nowrap width="20%" height="18" > 
              <%= dvDocDet.getE02OFMUCN().trim()%></td>
            <td nowrap width="26%" height="18" > 
              <div align="right">Fecha Pago :</div>
            </td>
            <td nowrap height="18" width="26%" > 
              <%= Util.formatDate(dvDocDet.getE02OFMMD1(),dvDocDet.getE02OFMMD2(),dvDocDet.getE02OFMMD3())%></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <% if (!dvDocDet.getE02STPUSR().trim().equals("")) {%>
  <h4>Orden de No Pago</h4>
  <table class="tableinfo">
      <tr > 
        <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap> 
              <div align="CENTER">Fecha</div>
            </td>
            <td nowrap> 
              <div align="CENTER">Hora</div>
            </td>
            <td nowrap> 
              <div align="CENTER">Usuario</div>
            </td>
          </tr>
          <tr > 
            <td nowrap id="trdark"> 
              <div align="right">Suspención : </div>
            </td>
            <td nowrap ALIGN=CENTER> 
              <%= Util.formatDate(dvDocDet.getE02STPDT1(),dvDocDet.getE02STPDT2(),dvDocDet.getE02STPDT3())%>
            </td>
            <td nowrap ALIGN=CENTER> 
              <%= Util.formatTime(dvDocDet.getE02STPTIM())%>
            </td>
            <td nowrap ALIGN=CENTER>
              <%= dvDocDet.getE02STPUSR().trim()%> 
            </td>
          </tr>
          <tr > 
            <td nowrap id="trdark"> 
              <div align="right">Aclaración : </div>
            </td>
            <td nowrap ALIGN=CENTER> 
              <%= Util.formatDate(dvDocDet.getE02STPRD1(),dvDocDet.getE02STPRD2(),dvDocDet.getE02STPRD3())%>
            </td>
            <td nowrap ALIGN=CENTER> 
              <%= Util.formatTime(dvDocDet.getE02STPRTM())%>
            </td>
            <td nowrap ALIGN=CENTER>
              <%= dvDocDet.getE02STPRUS().trim()%> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <% } %>
  
  <!--<p>
  <div align="center"> 
    <input id="EIBSBTN" type=button name="Submit" OnClick="top.close()" value="Cerrar">
  </div>
</p>-->

  </FORM>
  
</BODY>
</HTML>
