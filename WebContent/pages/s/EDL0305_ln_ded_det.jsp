<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<%@ page import = "java.lang.Object" %>
<HTML>
<HEAD>
<TITLE>
Detalle de Cuota
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id="lnDedDet" class="datapro.eibs.beans.EDL030503Message"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


</HEAD>

<BODY>

<form>

  <h3 align="center">Detalle de la Deducci&oacute;n<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ln_ded_det.jsp,EDL0305"> 
  </h3>
  <hr size="4">

<h4></h4>
   
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E02CUN2" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E02NA12" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E02ACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E02PRO2" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>&nbsp;</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" height="23" > 
              <div align="right">C&oacute;digo :</div>
            </td>
            <td nowrap width="23%" height="23" > 
              <div align="right"><%= lnDedDet.getE03DLICDE().trim()%></div>
            </td>
            <td nowrap width="23%" height="23" > 
              <div align="right">Tipo :</div>
            </td>
            <td nowrap height="23" colspan="2" > 
              <div align="right"><%= lnDedDet.getE03DLITYP().trim()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">Compa&ntilde;&iacute;a :</div>
            </td>
            <td nowrap width="23%" > 
              <div align="right"><%= lnDedDet.getE03DEDNME().trim()%></div>
            </td>
            <td nowrap width="23%" > 
              <div align="right">Referencia :</div>
            </td>
            <td nowrap colspan="2" > 
              <div align="right"><%= lnDedDet.getE03DLIREF().trim()%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="10" > 
              <div align="right">Cobertura :</div>
            </td>
            <td nowrap width="23%" height="10" > 
              <div align="right"><%= Util.fcolorCCY(lnDedDet.getE03DLIPAM().trim())%></div>
            </td>
            <td nowrap width="23%" height="10" > 
              <div align="right">Deducci&oacute;n :</div>
            </td>
            <td nowrap width="18%" height="10" > 
              <div align="right"><%= Util.fcolorCCY(lnDedDet.getE03DLIDPM().trim())%> </div>
            </td>
            <td nowrap width="20%" height="10" > 
              <div align="right">Factor : <%= lnDedDet.getE03DLIFAC().trim()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="18" > 
              <div align="right">Comentario :</div>
            </td>
            <td nowrap width="23%" height="18" > 
              <div align="right"><%= lnDedDet.getE03DLIRMK().trim()%></div>
            </td>
            <td nowrap width="23%" height="18" > 
              <div align="right">Monto Pagado :</div>
            </td>
            <td nowrap height="18" colspan="2" >
              <div align="right"><%= Util.fcolorCCY(lnDedDet.getE03DLILTD().trim())%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="18" > 
              <div align="right">Cuenta de Cr&eacute;dito :</div>
            </td>
            <td nowrap width="23%" height="18" > 
              <div align="right"><%= lnDedDet.getE03DLIBNK().trim()%>-<%= lnDedDet.getE03DLIBRN().trim()%>-<%= lnDedDet.getE03DLICCY().trim()%>-<%= lnDedDet.getE03DLIGLN().trim()%></div>
            </td>
            <td nowrap width="23%" height="18" > 
              <div align="right">C&aacute;lculo IVA ::</div>
            </td>
            <td nowrap height="18" colspan="2" > 
              <div align="right"><%= lnDedDet.getE03DLIIVA().trim()%></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
 <br>
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=button name="Submit" OnClick="top.close()" value="Cerrar">
  </div>
</p>


  </FORM>

</BODY>
</HTML>
