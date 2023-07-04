<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Documentación</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "lnCancelDet" class= "datapro.eibs.beans.EDL095002Message"  scope="session" />
 
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

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
<h3 align="center">Pre- Liquidaci&oacute;n de la Cuota</h3>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_payment_print.jsp,EDL0950"> 
<hr size="4">
 <FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130" >
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="38">
  </p>
  <table width="100%">
    <tr> 
      <td colspan="2"> 
        <div align="center">No v&aacute;lido como comprobante de Pago</div>
      </td>
    </tr>
  </table>
  <br>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="23%" > 
              <div align="right">Monto Original :</div>
            </td>
            <td nowrap width="27%" > 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02DEAOAM().trim())%></div>
            </td>
            <td nowrap width="25%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="25%" > 
              <div align="right"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="right">Fecha Apertura :</div>
            </td>
            <td nowrap width="27%" > 
              <div align="right"><%= Util.formatDate(lnCancelDet.getE02DEAOD1(),lnCancelDet.getE02DEAOD2(),lnCancelDet.getE02DEAOD3())%></div>
            </td>
            <td nowrap width="25%" > 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap width="25%" > 
              <div align="right"><%= Util.formatDate(lnCancelDet.getE02DEAMD1(),lnCancelDet.getE02DEAMD2(),lnCancelDet.getE02DEAMD3())%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="23%" > 
              <div align="right">Tasa de Inter&eacute;s :</div>
            </td>
            <td nowrap width="27%" > 
              <div align="right"><%= lnCancelDet.getE02DEARTE().trim()%></div>
            </td>
            <td nowrap width="25%" > 
              <div align="right">T&eacute;rmino :</div>
            </td>
            <td nowrap width="25%" > 
              <div align="right"><%= lnCancelDet.getE02DEATRM().trim()%> 
                - <% if(lnCancelDet.getE02DEATRC().equals("Y")) out.print("Años");
              				else if(lnCancelDet.getE02DEATRC().equals("M")) out.print("Meses");
							else out.print("Dias");%></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
    <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="24%" > 
              <div align="right">Nuevo Valor de Cuota :</div>
            </td>
            <td nowrap width="76%" > 
              <div align="left"></div>
              <%= lnCancelDet.getE02NEWCUO().trim()%> 
          </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="24%" > 
             <div align="right">Nuevo Vencimiento :</div>
            </td>
            <td nowrap width="76%" > 
              <div align="left"></div>
              <%= Util.formatDate(lnCancelDet.getE02NEWMDD(),lnCancelDet.getE02NEWMDM(),lnCancelDet.getE02NEWMDY())%>
          </tr>
        </table>
      </td>
    </tr>
  </table>
    <p>&nbsp;</p>
  <table cellspacing=0 cellpadding=2 width="100%" border="0">
    <tr id="trdark"> 
      <td nowrap width="36%"> 
        <div align="right"><b>Concepto</b></div>
      </td>
      <td nowrap width="21%"> 
        <div align="right"><b>Saldo Anterior</b></div>
      </td>
      <td nowrap width="23%"> 
        <div align="right"><b>Transacci&oacute;n</b></div>
      </td>
      <td nowrap width="20%"> 
        <div align="right"><b>Nuevo Saldo</b></div>
      </td>
    </tr>
    <tr id="trclear"> 
      <td nowrap width="36%" height="23"> 
        <div align="right">Principal :</div>
      </td>
      <td nowrap width="21%" height="23"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02BEFPRI().trim())%></div>
      </td>
      <td nowrap width="23%" height="23"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02TRNPRI().trim())%></div>
      </td>
      <td nowrap height="23" width="20%"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02AFTPRI().trim())%></div>
      </td>
    </tr>
    <%if(lnCancelDet.getH02FLGWK3().equals("R")){%> 
    <tr id="trdark"> 
      <td nowrap width="36%" height="19"> 
        <div align="right">Reajuste :</div>
      </td>
      <td nowrap width="21%" height="19"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02BEFREA().trim())%></div>
      </td>
      <td nowrap width="23%" height="19"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02TRNREA().trim())%></div>
      </td>
      <td nowrap height="19" width="20%"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02AFTREA().trim())%></div>
      </td>
    </tr>
    <%}%> 
    <tr id="<% if(lnCancelDet.getH02FLGWK3().equals("R")) out.print("trclear"); else out.print("trdark"); %>"> 
      <td nowrap width="36%"> 
        <div align="right">Intereses :</div>
      </td>
      <td nowrap width="21%"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02BEFINT().trim())%></div>
      </td>
      <td nowrap width="23%"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02TRNINT().trim())%></div>
      </td>
      <td nowrap width="20%"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02AFTINT().trim())%></div>
      </td>
    </tr>
    <tr id="<% if(lnCancelDet.getH02FLGWK3().equals("R")) out.print("trdark"); else out.print("trclear"); %>"> 
      <td nowrap width="36%"> 
        <div align="right">Mora :</div>
      </td>
      <td nowrap width="21%"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02BEFMOR().trim())%></div>
      </td>
      <td nowrap width="23%"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02TRNMOR().trim())%></div>
      </td>
      <td nowrap width="20%"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02AFTMOR().trim())%></div>
      </td>
    </tr>
    <tr id="<% if(lnCancelDet.getH02FLGWK3().equals("R")) out.print("trclear"); else out.print("trdark"); %>"> 
      <td nowrap width="36%"> 
        <div align="right">Impuesto :</div>
      </td>
      <td nowrap width="21%"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02BEFIMP().trim())%></div>
      </td>
      <td nowrap width="23%"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02TRNIMP().trim())%></div>
      </td>
      <td nowrap width="20%"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02AFTIMP().trim())%></div>
      </td>
    </tr>
    <tr id="<% if(lnCancelDet.getH02FLGWK3().equals("R")) out.print("trdark"); else out.print("trclear"); %>"> 
      <td nowrap width="36%"> 
        <div align="right">Comisiones :</div>
      </td>
      <td nowrap width="21%"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02BEFCOM().trim())%></div>
      </td>
      <td nowrap width="23%"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02TRNCOM().trim())%></div>
      </td>
      <td nowrap width="20%"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02AFTCOM().trim())%></div>
      </td>
    </tr>
    <tr id="<% if(lnCancelDet.getH02FLGWK3().equals("R")) out.print("trclear"); else out.print("trdark"); %>"> 
      <td nowrap width="36%"> 
        <div align="right">Deducciones :</div>
      </td>
      <td nowrap width="21%"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02BEFDED().trim())%></div>
      </td>
      <td nowrap width="23%"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02TRNDED().trim())%></div>
      </td>
      <td nowrap width="20%"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02AFTDED().trim())%></div>
      </td>
    </tr>
    <tr id="<% if(lnCancelDet.getH02FLGWK3().equals("R")) out.print("trdark"); else out.print("trclear"); %>"> 
      <td nowrap width="36%"> 
        <div align="right">I.V.A. :</div>
      </td>
      <td nowrap width="21%"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02BEFIVA().trim())%></div>
      </td>
      <td nowrap width="23%"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02TRNIVA().trim())%></div>
      </td>
      <td nowrap width="20%"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02AFTIVA().trim())%></div>
      </td>
    </tr>
    <tr id="<% if(lnCancelDet.getH02FLGWK3().equals("R")) out.print("trclear"); else out.print("trdark"); %>"> 
      <td nowrap width="36%"> 
        <div align="right">Total :</div>
      </td>
      <td nowrap width="21%"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02BEFTOT().trim())%></div>
      </td>
      <td nowrap width="23%"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02TRNTOT().trim())%></div>
      </td>
      <td nowrap width="20%"> 
        <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02AFTTOT().trim())%></div>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="24%" > 
              <div align="right">Cuenta D&eacute;bito :</div>
            </td>
            <td nowrap width="76%" > 
              <div align="left"></div>
              <%= lnCancelDet.getE02PAGOBK().trim()%> <%= lnCancelDet.getE02PAGOBR().trim()%> 
              <%= lnCancelDet.getE02PAGOCY().trim()%> <%= lnCancelDet.getE02PAGOGL().trim()%> 
              <%= lnCancelDet.getE02PAGOAC().trim()%> </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="24%" >&nbsp;</td>
            <td nowrap width="76%" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="24%" > 
              <div align="right">Descripci&oacute;n :</div>
            </td>
            <td nowrap width="76%" > 
              <div align="left"></div>
              <%= lnCancelDet.getE02DEANR1().trim()%></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="24%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="76%" > 
              <div align="left"><%= lnCancelDet.getE02DEANR2().trim()%></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <div align="center"> </div>
</form>
</body>
</html>
