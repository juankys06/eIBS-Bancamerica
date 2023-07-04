<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Aprobación de Prestamos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="lnMant" class="datapro.eibs.beans.EDL015001Message"  scope="session" />

<jsp:useBean id= "ded" class= "datapro.eibs.beans.JBListRec"  scope="session" />

<jsp:useBean id= "coll" class= "datapro.eibs.beans.JBListRec"  scope="session" />

<jsp:useBean id= "pmnt" class= "datapro.eibs.beans.JBListRec"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(ln_a_opt);


</SCRIPT>

</head>
<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
 out.println("<SCRIPT> initMenu(); </SCRIPT>");

  String DEAIPD, DEAPPD;
  String E01FLGDED, E01FLGREF,E01FLGPAY,E01FLGCOL,E01DEACLF;
  boolean isDEAIPDNum = true;
  boolean isDEAPPDNum = true;
  DEAIPD = lnMant.getE01DEAIPD().trim();
  DEAPPD = lnMant.getE01DEAPPD().trim();
  E01FLGDED = lnMant.getE01FLGDED().trim();
  E01FLGREF = lnMant.getE01FLGREF().trim();
  E01FLGPAY = lnMant.getE01FLGPAY().trim();
  E01FLGCOL = lnMant.getE01FLGCOL().trim();
  E01DEACLF = lnMant.getE01DEACLF().trim();
  
  try {
    int i = Integer.parseInt(DEAIPD);
  }
  catch (Exception e) {
    isDEAIPDNum = false;
  }
  try {
    int i = Integer.parseInt(DEAPPD);
  }
  catch (Exception e) {
    isDEAPPDNum = false;
  }
%> 
<h3 align="center">Aprobaci&oacute;n de <%= lnMant.getE01DSCPRO().trim()%><img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ln_ap_mant.jsp,EDL0140"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0150" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="502">
  <INPUT TYPE=HIDDEN NAME="ACTION" VALUE="F">
  <INPUT TYPE=HIDDEN NAME="E01DEABNK" value="<%= lnMant.getE01DEABNK().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E01DEARD1" value="<%= lnMant.getE01DEARD1().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E01DEARD2" value="<%= lnMant.getE01DEARD2().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E01DEARD3" value="<%= lnMant.getE01DEARD3().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E01DLCNC1" value="<%= lnMant.getE01DLCNC1().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E01DLCVA1" value="<%= lnMant.getE01DLCVA1().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E01DLCFP1" value="<%= lnMant.getE01DLCFP1().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E01DLCTP1" value="<%= lnMant.getE01DLCTP1().trim()%>">

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
                <input type="text"  readonly name="E01DEACUN" size="9" maxlength="9" value="<%= lnMant.getE01DEACUN().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text"  readonly name="E01CUSNA1" size="45" maxlength="45" value="<%= lnMant.getE01CUSNA1().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E01DEAACC" size="12" maxlength="12" readonly  value="<%= lnMant.getE01DEAACC().trim()%>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="4"  maxlength="3" value="<%= lnMant.getE01DEACCY().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E01DEAPRO" size="4" readonly maxlength="4" value="<%= lnMant.getE01DEAPRO().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Datos B&aacute;sicos de la Operaci&oacute;n</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha de Apertura :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" readonly <% if (lnMant.getF01DEAOD1().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEAOD1" size="2" maxlength="2" value="<%= lnMant.getE01DEAOD1().trim()%>">
              <input type="text" readonly <% if (lnMant.getF01DEAOD2().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEAOD2" size="2" maxlength="2" value="<%= lnMant.getE01DEAOD2().trim()%>">
              <input type="text" readonly <% if (lnMant.getF01DEAOD3().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEAOD3" size="2" maxlength="2" value="<%= lnMant.getE01DEAOD3().trim()%>">
            </td>
            <td nowrap width="25%"> 
              <div align="right">T&eacute;rmino :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" <% if (lnMant.getF01DEATRM().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEATRM" size="4" maxlength="4" value="<%= lnMant.getE01DEATRM().trim()%>">
              <input type="text" readonly <% if (lnMant.getF01DEATRC().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEATRC" size="15" maxlength="15" 
				  value="<% if (lnMant.getE01DEATRC().equals("D")) out.print("D&iacute;a(s)");
							else if (lnMant.getE01DEATRC().equals("M")) out.print("Mes(es)");
							else if (lnMant.getE01DEATRC().equals("Y")) out.print("A&ntilde;o(s)");
							else out.print("");%>" 
				>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" readonly <% if (lnMant.getF01DEAMD1().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEAMD1" size="2" maxlength="2" value="<%= lnMant.getE01DEAMD1().trim()%>">
              <input type="text" readonly <% if (lnMant.getF01DEAMD2().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEAMD2" size="2" maxlength="2" value="<%= lnMant.getE01DEAMD2().trim()%>">
              <input type="text" readonly <% if (lnMant.getF01DEAMD3().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEAMD3" size="2" maxlength="2" value="<%= lnMant.getE01DEAMD3().trim()%>">
            </td>
            <td nowrap width="25%"> 
              <div align="right">Monto :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" readonly <% if (lnMant.getF01DEAOAM().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEAOAM" size="15" maxlength="15" value="<%= lnMant.getE01DEAOAM().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Tipo de Tasa Flotante :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" readonly <% if (lnMant.getF01DEAFTB().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEAFTB" size="2" maxlength="2" value="<%= lnMant.getE01DEAFTB().trim()%>">
              <input type="text" readonly <% if (lnMant.getF01DEAFTY().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEAFTY" size="20" maxlength="15" 
				  value="<% if (lnMant.getE01DEAFTY().equals("FP")) out.print("Primaria");
							else if (lnMant.getE01DEAFTY().equals("FS")) out.print("Secundaria");
							else out.print("");%>" 
				>
              <input type="text"  <% if (lnMant.getF01FLTRTE().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01FLTRTE" size="9" maxlength="9" value="<%= lnMant.getE01FLTRTE().trim()%>" readonly>
            </td>
            <td nowrap width="25%" > 
              <div align="right">Tasa de Inter&eacute;s/Spread :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" <% if (lnMant.getF01DEARTE().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEARTE" size="10" maxlength="9" value="<%= lnMant.getE01DEARTE().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Tasa / Cargo por Mora :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" <% if (lnMant.getF01DEAPEI().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEAPEI" size="8" maxlength="7" value="<%= lnMant.getE01DEAPEI().trim()%>" >
              <input type="text" <% if (lnMant.getF01DEAPIF().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEAPIF" size="2" maxlength="1" value="<%= lnMant.getE01DEAPIF().trim()%>" >
            </td>
            <td nowrap width="25%" > 
              <div align="right">N&uacute;mero de Referencia :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" <% if (lnMant.getF01DEAREF().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEAREF" size="12" maxlength="12" value="<%= lnMant.getE01DEAREF().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">L&iacute;nea de Cr&eacute;dito :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" readonly <% if (lnMant.getF01DEACMC().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEACMC" size="9" maxlength="9" value="<%= lnMant.getE01DEACMC().trim()%>" >
              <input type="text" readonly <% if (lnMant.getF01DEACMN().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEACMN" size="4" maxlength="4" value="<%= lnMant.getE01DEACMN().trim()%>" >
            </td>
            <td nowrap width="25%" > 
              <div align="right">Centro de Costo : </div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" readonly <% if (lnMant.getF01DEACCN().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEACCN" size="8" maxlength="8" value="<%= lnMant.getE01DEACCN().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Ciclo de Revisi&oacute;n :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" readonly <% if (lnMant.getF01DEARRP().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEARRP" size="3" maxlength="3" value="<%= lnMant.getE01DEARRP().trim()%>">
            </td>
            <td nowrap width="25%" > 
              <div align="right">Fecha de Revisi&oacute;n :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" readonly <% if (lnMant.getF01DEARR1().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEARR1" size="2" maxlength="2" value="<%= lnMant.getE01DEARR1().trim()%>">
              <input type="text" readonly <% if (lnMant.getF01DEARR2().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEARR2" size="2" maxlength="2" value="<%= lnMant.getE01DEARR2().trim()%>">
              <input type="text" readonly <% if (lnMant.getF01DEARR3().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEARR3" size="2" maxlength="2" value="<%= lnMant.getE01DEARR3().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <% if(E01FLGDED.equals("Y")) { %>
  <h4>Lista de Deducciones</h4>
 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark">
            <td nowrap width="6%" >Selecci&oacute;n</td>
            <td nowrap width="23%" > 
              <div align="center">C&oacute;digo</div>
            </td>
            <td nowrap width="16%" > 
              <div align="center">Compa&ntilde;&iacute;a</div>
            </td>
            <td nowrap width="22%"> 
              <div align="center">No. de P&oacute;liza</div>
            </td>
            <td nowrap width="17%" > 
              <div align="center">Deducci&oacute;n</div>
            </td>
            <td nowrap width="16%" > 
              <div align="center">Factor</div>
            </td>
          </tr>
          <%
                ded.initRow();
                while (ded.getNextRow()) {
                    if (ded.getFlag().equals("")) {
                    		//out.println(list.getRecord());
	      %> 
          <tr id="trclear">
            <td nowrap width="6%" >
              <div align="center"><%= ded.getRecord(0) %></div>
            </td>
            <td nowrap width="23%" > 
              <div align="center"><%= ded.getRecord(1) %></div>
            </td>
            <td nowrap width="16%" > 
              <div align="center"><%= ded.getRecord(2) %></div>
            </td>
            <td nowrap width="22%" > 
              <div align="center"><%= ded.getRecord(3) %></div>
            </td>
            <td nowrap width="17%" > 
              <div align="center"><%= Util.formatCCY(ded.getRecord(4)) %></div>
            </td>
            <td nowrap width="16%" > 
              <div align="center"><%= ded.getRecord(5) %></div>
            </td>
          </tr>
          <%
                    }
                }
    %> 
        </table>
      </td>
    </tr>
  </table>
  <%}%>
  <h4>Condiciones de Pago</h4>
  <% if(DEAIPD.equals("MAT") && DEAPPD.equals("MAT")) { %>
  <h5>Pagar el Inter&eacute;s y el Capital al Vencimiento</h5>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="33%"> 
              <div align="right">Ciclo Pago de Intereses :</div>
            </td>
            <td nowrap width="15%"> <%= lnMant.getE01DEAIPD().trim()%> </td>
            <td nowrap width="22%"> 
              <div align="right">Ciclo Pago Capital :</div>
            </td>
            <td nowrap colspan="2"><%= lnMant.getE01DEAPPD().trim()%> </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <%}%>
  <% if(isDEAIPDNum && DEAPPD.equals("MAT")){ %>
  <h5>Pagar el Capital al Vencimiento y el Inter&eacute;s seg&uacute;n su Ciclo</h5>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="33%"> 
              <div align="right">Ciclo Pago de Intereses :</div>
            </td>
            <td nowrap width="15%"> <%= lnMant.getE01DEAIPD().trim()%> </td>
            <td nowrap width="22%"> 
              <div align="right">Pr&oacute;xima Fecha de Pago :</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" readonly <% if (lnMant.getF01DEARD1().equals("Y")) out.print("id=\"txtchanged\""); %> name="PRXPAG01" size="2" maxlength="2" value="<%= lnMant.getE01DEARD1().trim()%>" >
              <input type="text" readonly <% if (lnMant.getF01DEARD2().equals("Y")) out.print("id=\"txtchanged\""); %> name="PRXPAG02" size="2" maxlength="2" value="<%= lnMant.getE01DEARD2().trim()%>" >
              <input type="text" readonly <% if (lnMant.getF01DEARD3().equals("Y")) out.print("id=\"txtchanged\""); %> name="PRXPAG03" size="2" maxlength="2" value="<%= lnMant.getE01DEARD3().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%"> 
              <div align="right">Ciclo Pago Capital :</div>
            </td>
            <td nowrap width="15%"> <%= lnMant.getE01DEAPPD().trim()%> </td>
            <td nowrap width="22%"> 
              <div align="right"></div>
            </td>
            <td nowrap colspan="2">&nbsp; </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <%}%>
  <% if(isDEAIPDNum && isDEAPPDNum){ %>
  <h5>Pagar el Inter&eacute;s y el Capital seg&uacute;n sus ciclos</h5>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="33%"> 
              <div align="right">Ciclo Pago de Intereses :</div>
            </td>
            <td nowrap width="15%"> <%= lnMant.getE01DEAIPD().trim()%> </td>
            <td nowrap width="22%"> 
              <div align="right">Pr&oacute;xima Fecha de Pago :</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" readonly <% if (lnMant.getF01DEARD1().equals("Y")) out.print("id=\"txtchanged\""); %> name="PRXPAG11" size="2" maxlength="2" value="<%= lnMant.getE01DEARD1().trim()%>" >
              <input type="text" readonly <% if (lnMant.getF01DEARD2().equals("Y")) out.print("id=\"txtchanged\""); %> name="PRXPAG12" size="2" maxlength="2" value="<%= lnMant.getE01DEARD2().trim()%>" >
              <input type="text" readonly <% if (lnMant.getF01DEARD3().equals("Y")) out.print("id=\"txtchanged\""); %> name="PRXPAG13" size="2" maxlength="2" value="<%= lnMant.getE01DEARD3().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%"> 
              <div align="right">Ciclo Pago Capital :</div>
            </td>
            <td nowrap width="15%"> <%= lnMant.getE01DEAPPD().trim()%> </td>
            <td nowrap width="22%"> 
              <div align="right">Pr&oacute;xima Fecha de Pago :</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" readonly <% if (lnMant.getF01DEAHE1().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEAHE1" size="2" maxlength="2" value="<%= lnMant.getE01DEAHE1().trim()%>">
              <input type="text" readonly <% if (lnMant.getF01DEAHE2().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEAHE2" size="2" maxlength="2" value="<%= lnMant.getE01DEAHE2().trim()%>">
              <input type="text" readonly <% if (lnMant.getF01DEAHE3().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEAHE3" size="2" maxlength="2" value="<%= lnMant.getE01DEAHE3().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="33%" > 
              <div align="right">Valor de la Cuota :</div>
            </td>
            <td nowrap width="15%" > 
              <input type="text" readonly <% if (lnMant.getF01DEAROA().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEAROA" size="15" maxlength="15" value="<%= lnMant.getE01DEAROA().trim()%>">
            </td>
            <td nowrap width="22%" >
              <div align="right">Incluye Intereses Pago :</div>
            </td>
            <td nowrap colspan="2" > 
              <input type="text" readonly  name="E01DEAIIP" size="3" maxlength="2" 
				  value="<% if (lnMant.getE01DEAIIP().equals("Y")) out.print("Si");
							else if (lnMant.getE01DEAIIP().equals("N")) out.print("No");
							else out.print("");%>" 
				>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <%}%>
  <% if(DEAIPD.equals("SC1") && DEAPPD.equals("SC1")){ %>
  <h5>Cuotas Niveladas</h5>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="33%"> 
              <div align="right">Ciclo Pago de Intereses :</div>
            </td>
            <td nowrap width="15%"> <%= lnMant.getE01DEAIPD().trim()%> </td>
            <td nowrap width="22%"> 
              <div align="right">Ciclo Pago Capital :</div>
            </td>
            <td nowrap colspan="2"><%= lnMant.getE01DEAPPD().trim()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%"> 
              <div align="right">Total de Cuotas :</div>
            </td>
            <td nowrap width="15%">
              <input type="text" readonly <% if (lnMant.getF01DLCNC1().equals("Y")) out.print("id=\"txtchanged\""); %> name="VALNC01" size="4" maxlength="3" value="<%= lnMant.getE01DLCNC1().trim()%>" >
            </td>
            <td nowrap width="22%"> 
              <div align="right">Intereses Prepagados hasta :</div>
            </td>
            <td nowrap colspan="2">
              <input type="text" readonly <% if (lnMant.getF01DLCPD1().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DLCPD1" size="2" maxlength="2" value="<%= lnMant.getE01DLCPD1().trim()%>">
              <input type="text" readonly <% if (lnMant.getF01DLCPM1().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DLCPM1" size="2" maxlength="2" value="<%= lnMant.getE01DLCPM1().trim()%>">
              <input type="text" readonly <% if (lnMant.getF01DLCPY1().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DLCPY1" size="2" maxlength="2" value="<%= lnMant.getE01DLCPY1().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="33%" > 
              <div align="right">Valor de la Cuota :</div>
            </td>
            <td nowrap width="15%" >
              <input type="text" readonly <% if (lnMant.getF01DLCVA1().equals("Y")) out.print("id=\"txtchanged\""); %> name="VALVA01" size="14" maxlength="13" value="<%= lnMant.getE01DLCVA1().trim()%>" >
            </td>
            <td nowrap width="22%" > 
              <div align="right">Per&iacute;odo de Gracia :</div>
            </td>
            <td nowrap colspan="2" >
              <input type="text" readonly <% if (lnMant.getF01DLCGD1().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DLCGD1" size="2" maxlength="2" value="<%= lnMant.getE01DLCGD1().trim()%>">
              <input type="text" readonly <% if (lnMant.getF01DLCGM1().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DLCGM1" size="2" maxlength="2" value="<%= lnMant.getE01DLCGM1().trim()%>">
              <input type="text" readonly <% if (lnMant.getF01DLCGM1().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DLCGY1" size="2" maxlength="2" value="<%= lnMant.getE01DLCGY1().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="15%" >&nbsp; </td>
            <td nowrap width="22%" >
              <div align="right">Frecuencia de Pagos :</div>
            </td>
            <td nowrap colspan="2" >
              <input type="text" readonly <% if (lnMant.getF01DLCFP1().equals("Y")) out.print("id=\"txtchanged\""); %> name="FRCFP01" size="4" maxlength="3" value="<%= lnMant.getE01DLCFP1().trim()%>" >
              <input type="text" readonly <% if (lnMant.getF01DLCTP1().equals("Y")) out.print("id=\"txtchanged\""); %> name="FRCTP01" size="15" maxlength="15" 
				  value="<% if (lnMant.getE01DLCTP1().equals("D")) out.print("D&iacute;a(s)");
							else if (lnMant.getE01DLCTP1().equals("M")) out.print("Mes(es)");
							else if (lnMant.getE01DLCTP1().equals("Y")) out.print("A&ntilde;o(s)");
							else out.print("");%>" 
				>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <%}%>
  <% if(DEAIPD.equals("SC2") && DEAPPD.equals("SC2")){ %>
  <h5> Doble Esquema de Pago</h5>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="33%"> 
              <div align="right">Ciclo Pago de Intereses :</div>
            </td>
            <td nowrap width="15%"><%= lnMant.getE01DEAIPD().trim()%></td>
            <td nowrap width="22%"> 
              <div align="right">Ciclo Pago Capital :</div>
            </td>
            <td nowrap colspan="2"><%= lnMant.getE01DEAPPD().trim()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%"><b>Plan 1</b></td>
            <td nowrap width="15%">&nbsp;</td>
            <td nowrap width="22%">&nbsp;</td>
            <td nowrap colspan="2">&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="33%"> 
              <div align="right">Porcentaje Capital :</div>
            </td>
            <td nowrap width="15%"> 
              <input type="text" readonly <% if (lnMant.getF01DLCOR1().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DLCOR1" size="4" maxlength="3" value="<%= lnMant.getE01DLCOR1().trim()%>">
              % </td>
            <td nowrap width="22%"> 
              <div align="right">Valor Capital :</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" readonly <% if (lnMant.getF01DLCPP1().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DLCPP1" size="14" maxlength="13" value="<%= lnMant.getE01DLCPP1().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%" height="31"> 
              <div align="right">Frecuencia de Pagos :</div>
            </td>
            <td nowrap width="15%" height="31"> 
              <input type="text" readonly <% if (lnMant.getF01DLCFP1().equals("Y")) out.print("id=\"txtchanged\""); %> name="FRCFP11" size="4" maxlength="3" value="<%= lnMant.getE01DLCFP1().trim()%>" >
              <input type="text" readonly <% if (lnMant.getF01DLCTP1().equals("Y")) out.print("id=\"txtchanged\""); %> name="FRCTP11" size="15" maxlength="15" 
				  value="<% if (lnMant.getE01DLCTP1().equals("D")) out.print("D&iacute;a(s)");
							else if (lnMant.getE01DLCTP1().equals("M")) out.print("Mes(es)");
							else if (lnMant.getE01DLCTP1().equals("Y")) out.print("A&ntilde;o(s)");
							else out.print("");%>" 
				>
            </td>
            <td nowrap width="22%" height="31"> 
              <div align="right"></div>
            </td>
            <td nowrap colspan="2" height="31">&nbsp; </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="33%" > 
              <div align="right">N&uacute;mero de Cuotas :</div>
            </td>
            <td nowrap width="15%" > 
              <input type="text" readonly <% if (lnMant.getF01DLCNC1().equals("Y")) out.print("id=\"txtchanged\""); %> name="VALNC11" size="4" maxlength="3" value="<%= lnMant.getE01DLCNC1().trim()%>" >
            </td>
            <td nowrap width="22%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="2" >&nbsp; </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%" > 
              <div align="right">Valor de la Cuota :</div>
            </td>
            <td nowrap width="15%" > 
              <input type="text" readonly <% if (lnMant.getF01DLCVA1().equals("Y")) out.print("id=\"txtchanged\""); %> name="VALVA11" size="14" maxlength="13" value="<%= lnMant.getE01DLCVA1().trim()%>" >
            </td>
            <td nowrap width="22%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="2" >&nbsp; </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="33%" ><b>Plan 2</b></td>
            <td nowrap width="15%" >&nbsp;</td>
            <td nowrap width="22%" >&nbsp;</td>
            <td nowrap colspan="2" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%"> 
              <div align="right">Porcentaje Capital :</div>
            </td>
            <td nowrap width="15%"> 
              <input type="text" readonly <% if (lnMant.getF01DLCOR2().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DLCOR2" size="4" maxlength="3" value="<%= lnMant.getE01DLCOR2().trim()%>">
              % </td>
            <td nowrap width="22%"> 
              <div align="right">Valor Capital :</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" readonly <% if (lnMant.getF01DLCPP2().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DLCPP2" size="14" maxlength="13" value="<%= lnMant.getE01DLCPP2().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="33%" height="31"> 
              <div align="right">Frecuencia de Pagos :</div>
            </td>
            <td nowrap width="15%" height="31"> 
              <input type="text" readonly <% if (lnMant.getF01DLCFP2().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DLCFP2" size="4" maxlength="3" value="<%= lnMant.getE01DLCFP2().trim()%>">
              <input type="text" readonly <% if (lnMant.getF01DLCTP2().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DLCTP2" size="15" maxlength="15" 
				  value="<% if (lnMant.getE01DLCTP2().equals("D")) out.print("D&iacute;a(s)");
							else if (lnMant.getE01DLCTP2().equals("M")) out.print("Mes(es)");
							else if (lnMant.getE01DLCTP2().equals("Y")) out.print("A&ntilde;o(s)");
							else out.print("");%>" 
				>
            </td>
            <td nowrap width="22%" >&nbsp;</td>
            <td nowrap colspan="2" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%" > 
              <div align="right">N&uacute;mero de Cuotas :</div>
            </td>
            <td nowrap width="15%" > 
              <input type="text" readonly <% if (lnMant.getF01DLCNC2().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DLCNC2" size="4" maxlength="3" value="<%= lnMant.getE01DLCNC2().trim()%>">
            </td>
            <td nowrap width="22%" >&nbsp;</td>
            <td nowrap colspan="2" >&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="33%" > 
              <div align="right">Valor de la Cuota :</div>
            </td>
            <td nowrap width="15%" > 
              <input type="text" readonly <% if (lnMant.getF01DLCVA2().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DLCVA2" size="14" maxlength="13" value="<%= lnMant.getE01DLCVA2().trim()%>">
            </td>
            <td nowrap width="22%" >&nbsp;</td>
            <td nowrap colspan="2" >&nbsp;</td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <%}%> <% if(DEAIPD.equals("SCH") && DEAPPD.equals("SCH")){ %> 
  <h5>Plan de Pagos Irregular</h5>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="33%"> 
              <div align="right">Ciclo Pago de Intereses :</div>
            </td>
            <td nowrap width="15%"> <%= lnMant.getE01DEAIPD().trim()%> </td>
            <td nowrap width="22%"> 
              <div align="right">Ciclo Pago Capital :</div>
            </td>
            <td nowrap colspan="2"><%= lnMant.getE01DEAPPD().trim()%> </td>
          </tr>
        </table>
        <table class="tableinfo" style="filter:''">
          <tr > 
            <td nowrap> 
              <table cellpadding=2 cellspacing=0 width="100%" border="0">
                <tr id="trdark"> 
                  <td nowrap width="25%"> 
                    <div align="center">Cuota No. </div>
                  </td>
                  <td nowrap width="23%"> 
                    <div align="center">Fecha</div>
                  </td>
                  <td nowrap width="25%"> 
                    <div align="center">Principal</div>
                  </td>
                  <td nowrap width="27%"> 
                    <div align="center">Intereses</div>
                  </td>
                </tr>
                <%
                pmnt.initRow();
                while (pmnt.getNextRow()) {
                    if (pmnt.getFlag().equals("")) {
                    		//out.println(coll.getRecord());
	      %> 
                <tr id="trclear"> 
                  <td nowrap width="25%"> 
                    <div align="center"><%= pmnt.getRecord(0) %></div>
                  </td>
                  <td nowrap width="23%"> 
                    <div align="center"><%= Util.formatDate(pmnt.getRecord(1),pmnt.getRecord(2),pmnt.getRecord(3))%></div>
                  </td>
                  <td nowrap width="25%"> 
                    <div align="center"><%= pmnt.getRecord(4) %></div>
                  </td>
                  <td nowrap width="27%"> 
                    <div align="center"><%= pmnt.getRecord(5) %></div>
                  </td>
                </tr>
          <%
                    }
                }
    %> 
              </table>
            </td>
          </tr>
        </table>

        <p align="center">&nbsp;</p>
        
      </td>
    </tr>
  </table>
  <%}%>
  <% if(isDEAIPDNum && DEAPPD.equals("SCH")){ %>
  <h5>Pagar el Inter&eacute;s seg&uacute;n Ciclo Preestablecido y el Capital seg&uacute;n 
    Plan de Pagos Irregular</h5>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="33%"> 
              <div align="right">Ciclo Pago de Intereses :</div>
            </td>
            <td nowrap width="15%"> <%= lnMant.getE01DEAIPD().trim()%> </td>
            <td nowrap width="22%"> 
              <div align="right">Ciclo Pago Capital :</div>
            </td>
            <td nowrap colspan="2"><%= lnMant.getE01DEAPPD().trim()%> </td>
          </tr>
        </table>
        <p><b>Intereses</b></p>
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="33%"> 
              <div align="right">Pr&oacute;xima Fecha de Pago :</div>
            </td>
            <td nowrap width="67%"> 
              <input type="text" readonly <% if (lnMant.getF01DEARD1().equals("Y")) out.print("id=\"txtchanged\""); %> name="PRXPAG21" size="2" maxlength="2" value="<%= lnMant.getE01DEARD1().trim()%>" >
              <input type="text" readonly <% if (lnMant.getF01DEARD2().equals("Y")) out.print("id=\"txtchanged\""); %> name="PRXPAG22" size="2" maxlength="2" value="<%= lnMant.getE01DEARD2().trim()%>" >
              <input type="text" readonly <% if (lnMant.getF01DEARD3().equals("Y")) out.print("id=\"txtchanged\""); %> name="PRXPAG23" size="2" maxlength="2" value="<%= lnMant.getE01DEARD3().trim()%>" >
            </td>
          </tr>
        </table>
        <p><b>Capital</b></p>
        <table class="tableinfo" style="filter:''">
          <tr > 
            <td nowrap> 
              <table cellpadding=2 cellspacing=0 width="100%" border="0">
                <tr id="trdark"> 
                  <td nowrap width="25%"> 
                    <div align="center">Cuota No. </div>
                  </td>
                  <td nowrap width="23%"> 
                    <div align="center">Fecha</div>
                  </td>
                  <td nowrap width="25%"> 
                    <div align="center">Principal</div>
                  </td>
                </tr>
                <%
                pmnt.initRow();
                while (pmnt.getNextRow()) {
                    if (pmnt.getFlag().equals("")) {
                    		//out.println(coll.getRecord());
	      %> 
                <tr id="trclear"> 
                  <td nowrap width="25%"> 
                    <div align="center"><%= pmnt.getRecord(0) %></div>
                  </td>
                  <td nowrap width="23%"> 
                    <div align="center"><%= Util.formatDate(pmnt.getRecord(1),pmnt.getRecord(2),pmnt.getRecord(3))%></div>
                  </td>
                  <td nowrap width="25%"> 
                    <div align="center"><%= pmnt.getRecord(4) %></div>
                  </td>
                </tr>
                <%
                    }
                }
    %> 
              </table>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <%}%>
  <h4>Cuenta de Pago</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="33%"> 
              <div align="center">Concepto</div>
            </td>
            <td nowrap width="15%"> 
              <div align="center">Banco </div>
            </td>
            <td nowrap width="22%"> 
              <div align="center">Sucursal</div>
            </td>
            <td nowrap> 
              <div align="center">Moneda </div>
            </td>
            <td nowrap> 
              <div align="center">Referencia</div>
            </td>
            <td> 
              <div align="center">Titular</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%" > 
              <div align="center"> 
                <input type="hidden" name="E01PAGOPE" value="<%= lnMant.getE01PAGOPE().trim()%>">
                <input type="hidden" name="E01PAGGLN" value="<%= lnMant.getE01PAGGLN().trim()%>">
                <input type="text" name="E01PAGCON" size="25" maxlength="25" readonly value="<%= lnMant.getE01PAGCON().trim()%>">
              </div>
            </td>
            <td nowrap width="15%" > 
              <div align="center"> 
                <input type="text"  readonly name="E01PAGBNK" size="2" maxlength="2" value="<%= lnMant.getE01PAGBNK().trim()%>">
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="center"> 
                <input type="text" readonly name="E01PAGBRN" size="3" maxlength="3" value="<%= lnMant.getE01PAGBRN().trim()%>">
                
                </div>
            </td>
            <td nowrap width="14%" > 
              <div align="center"> 
                <input type="text" readonly name="E01PAGCCY" size="3" maxlength="3" value="<%= lnMant.getE01PAGCCY().trim()%>">
                
               </div>
            </td>
            <td nowrap width="16%" > 
              <div align="center"> 
                <input type="text" readonly name="E01PAGACC" size="12" maxlength="12"  value="<%= lnMant.getE01PAGACC().trim()%>">
                 
                </div>
            </td>
            
			<td nowrap width="28%" > 
              <div align="center"> 
                <input type="text" readonly name="E01PAGNA1" size="25" maxlength="25" value="<%= lnBasic.getE01PAGNA1().trim()%>">
              </div>
            </td>
			            
          </tr>
          <tr id="trdark">
            <td nowrap width="33%" >&nbsp;</td>
            <td nowrap width="15%" >&nbsp;</td>
            <td nowrap width="22%" >&nbsp;</td>
            <td nowrap width="14%" >&nbsp;</td>
            <td nowrap width="16%" >&nbsp;</td>
            <td nowrap width="28%" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%" > Autoriza Sobregiro : 
              <input type="text" readonly <% if (lnMant.getF01DEAODA().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEAODA" size="2" maxlength="1" value="<%= lnMant.getE01DEAODA().trim()%>">
            </td>
            <td nowrap width="15%" > 
              <div align="center"> </div>
            </td>
            <td nowrap width="22%" > 
              <div align="center"> </div>
            </td>
            <td nowrap width="14%" > 
              <div align="center"> </div>
            </td>
            <td nowrap width="16%" > 
              <div align="center"> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4><% if(E01DEACLF.equals("A")) { %>Activos Fijos</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">N&uacute;mero del Activo :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" readonly <% if (lnMant.getF01FIXASN().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01FIXASN" size="9" maxlength="9" value="<%= lnMant.getE01FIXASN().trim()%>">
            </td>
            <td nowrap width="25%"> 
              <div align="right">Cuenta Contable :</div>
            </td>
            <td nowrap width="27%">
              <input type="text" name="E01FIXGLN" size="16" maxlength="16" value="<%= lnMant.getE01FIXGLN().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Marca del Activo :</div>
            </td>
            <td nowrap width="23%">
              <input type="text" readonly <% if (lnMant.getF01FIXMAR().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01FIXMAR" size="20" maxlength="20" value="<%= lnMant.getE01FIXMAR().trim()%>">
            </td>
            <td nowrap width="25%"> 
              <div align="right">Centro de Costo :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01FIXCCN" size="8" maxlength="8" value="<%= lnMant.getE01FIXCCN().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">M&oacute;dulo del Activo :</div>
            </td>
            <td nowrap width="23%" >
              <input type="text" readonly <% if (lnMant.getF01FIXMOD().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01FIXMOD" size="15" maxlength="15" value="<%= lnMant.getE01FIXMOD().trim()%>" >
            </td>
            <td nowrap width="25%" > 
              <div align="right">Tasa de Cambio :</div>
            </td>
            <td nowrap width="27%" >
              <input type="text" readonly <% if (lnMant.getF01FIXTIC().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01FIXTIC" size="11" maxlength="11" value="<%= lnMant.getE01FIXTIC().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Valor del Activo :</div>
            </td>
            <td nowrap width="23%" >
              <input type="text" readonly <% if (lnMant.getF01FIXPPR().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01FIXPPR" size="15" maxlength="2" value="<%= lnMant.getE01FIXPPR().trim()%>" >
            </td>
            <td nowrap width="25%" > 
              <div align="right">Tipo de Depreciaci&oacute;n :</div>
            </td>
            <td nowrap width="27%" >
              <input type="text" readonly <% if (lnMant.getF01FIXDTY().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01FIXDTY" size="2" maxlength="1" value="<%= lnMant.getE01FIXDTY().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">No. de Serie :</div>
            </td>
            <td nowrap width="23%" >
              <input type="text" readonly <% if (lnMant.getF01FIXSER().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01FIXSER" size="15" maxlength="15" value="<%= lnMant.getE01FIXSER().trim()%>">
            </td>
            <td nowrap width="25%" > 
              <div align="right">Porcentaje de Depreciaci&oacute;n :</div>
            </td>
            <td nowrap width="27%" >
              <input type="text" readonly <% if (lnMant.getF01FIXPRT().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01FIXPRT" size="5" maxlength="4" value="<%= lnMant.getE01FIXPRT().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Ubicaci&oacute;n :</div>
            </td>
            <td nowrap width="23%" >
              <input type="text" readonly <% if (lnMant.getF01FIXLOC().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01FIXLOC" size="4" maxlength="3" value="<%= lnMant.getE01FIXLOC().trim()%>">
            </td>
            <td nowrap width="25%" > 
              <div align="right">Meses :</div>
            </td>
            <td nowrap width="27%" >
              <input type="text" readonly <% if (lnMant.getF01FIXMTH().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01FIXMTH" size="4" maxlength="3" value="<%= lnMant.getE01FIXMTH().trim()%>">
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="25%" >
              <div align="right">Clase :</div>
            </td>
            <td nowrap width="23%" >
              <input type="text" readonly <% if (lnMant.getF01FIXCLS().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01FIXCLS" size="5" maxlength="4" value="<%= lnMant.getE01FIXCLS().trim()%>">
            </td>
            <td nowrap width="25%" >
              <div align="right">Comentarios :</div>
            </td>
            <td nowrap width="27%" >
              <input type="text" readonly <% if (lnMant.getF01FIXRMK().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01FIXRMK" size="30" maxlength="25" value="<%= lnMant.getE01FIXRMK().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Valor Residual :</div>
            </td>
            <td nowrap width="23%" >
              <input type="text" readonly <% if (lnMant.getF01FIXRVA().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01FIXRVA" size="15" maxlength="15" value="<%= lnMant.getE01FIXRVA().trim()%>">
            </td>
            <td nowrap width="25%" > 
              <div align="right">N&uacute;mero del Proveedor :</div>
            </td>
            <td nowrap width="27%" >
              <input type="text" readonly <% if (lnMant.getF01FIXPIN().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01FIXPIN" size="10" maxlength="9" value="<%= lnMant.getE01FIXPIN().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <%}%><% if(E01FLGCOL.equals("Y")) { %> 
<h4>Lista de Garant&iacute;as</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="center">Garant&iacute;a No. </div>
            </td>
            <td nowrap width="23%"> 
              <div align="center">Tipo de Garant&iacute;a</div>
            </td>
            <td nowrap width="25%"> 
              <div align="center">Moneda</div>
            </td>
            <td nowrap width="27%"> 
              <div align="center">Monto</div>
            </td>
          </tr>
          <%
                coll.initRow();
                while (coll.getNextRow()) {
                    if (coll.getFlag().equals("")) {
                    		//out.println(coll.getRecord());
	      %> 
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="center"><%= coll.getRecord(0) %></div>
            </td>
            <td nowrap width="23%"> 
              <div align="center"><%= coll.getRecord(1) %></div>
            </td>
            <td nowrap width="25%"> 
              <div align="center"><%= coll.getRecord(2) %></div>
            </td>
            <td nowrap width="27%"> 
              <div align="center"><%= Util.formatCCY(coll.getRecord(3)) %></div>
            </td>
          </tr>
    <%
                    }
                }
    %> 
        </table>
      </td>
    </tr>
  </table>
  <%}%> <% if(E01FLGREF.equals("1")||E01FLGREF.equals("2") || E01FLGREF.equals("3")) { %> 
  <h4>Refinanciamiento<% if(E01FLGREF.equals("1")) { %> </h4>
  <h5>Refinanciamiento de Intereses</h5>
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="40%"> 
              <div align="right">Ciclo de Refinanciamiento :</div>
            </td>
            <td nowrap width="60%"> <%= lnMant.getE01DEAXRC().trim()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="40%"> 
              <div align="right">Fecha Pr&oacute;ximo Refinanciamiento :</div>
            </td>
            <td nowrap width="60%"> 
              <input type="text" readonly <% if (lnMant.getF01DEAXR1().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEAXR1" size="2" maxlength="2" value="<%= lnMant.getE01DEAXR1().trim()%>">
              <input type="text" readonly <% if (lnMant.getF01DEAXR2().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEAXR2" size="2" maxlength="2" value="<%= lnMant.getE01DEAXR2().trim()%>">
              <input type="text" readonly <% if (lnMant.getF01DEAXR3().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEAXR3" size="2" maxlength="2" value="<%= lnMant.getE01DEAXR3().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" > 
              <div align="right">Refinanciar hasta :</div>
            </td>
            <td nowrap width="60%" > 
              <input type="text" readonly <% if (lnMant.getF01DEAPC1().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEAPC1" size="2" maxlength="1" value="<%= lnMant.getE01DEAPC1().trim()%>">
              <input type="text" readonly <% if (lnMant.getF01DEAPC2().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEAPC2" size="2" maxlength="1" value="<%= lnMant.getE01DEAPC2().trim()%>">
              <input type="text" readonly <% if (lnMant.getF01DEAPC3().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEAPC3" size="2" maxlength="1" value="<%= lnMant.getE01DEAPC3().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <%}%>
  <% if(E01FLGREF.equals("2")) { %> 
  <h5>Refinanciamiento Parcial Cuota con incremento al mismo Cr&eacute;dito</h5>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="40%"> 
              <div align="right">Monto a Pagar :</div>
            </td>
            <td nowrap width="60%"> 
              <input type="text" readonly <% if (lnMant.getF01REFPAG().equals("Y")) out.print("id=\"txtchanged\""); %> name="REFPAG01" size="15" maxlength="15" value="<%= lnMant.getE01REFPAG().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <%}%>
  <% if(E01FLGREF.equals("3")) { %>
  <h5>Refinanciamiento Parcial Cuota con incremento a otro Cr&eacute;dito</h5>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark">
            <td nowrap width="40%"> 
              <div align="right">Monto a Pagar :</div>
            </td>
            <td nowrap width="60%"> 
              <input type="text" readonly <% if (lnMant.getF01REFPAG().equals("Y")) out.print("id=\"txtchanged\""); %> name="REFPAG11" size="15" maxlength="15" value="<%= lnMant.getE01REFPAG().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="40%"> 
              <div align="right">No. de Cr&eacute;dito para Aplicar Refinanciamiento 
                : </div>
            </td>
            <td nowrap width="60%"> 
              <input type="text" readonly <% if (lnMant.getF01REFACC().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01REFACC" size="12" maxlength="12" value="<%= lnMant.getE01REFACC().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <%}%>
  <%}%>
  <% if(E01FLGPAY.equals("Y")) { %>
  <h4>Proyecci&oacute;n de Cuota a Pagar</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Monto Pago Final :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" readonly <% if (lnMant.getF01DEABAP().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEABAP" size="15" maxlength="15" value="<%= lnMant.getE01DEABAP().trim()%>">
            </td>
            <td nowrap width="25%"> 
              <div align="right">Fecha Pago Final :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" readonly <% if (lnMant.getF01DEABA1().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEABA1" size="2" maxlength="2" value="<%= lnMant.getE01DEABA1().trim()%>">
              <input type="text" readonly <% if (lnMant.getF01DEABA2().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEABA2" size="2" maxlength="2" value="<%= lnMant.getE01DEABA2().trim()%>">
              <input type="text" readonly <% if (lnMant.getF01DEABA3().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEABA3" size="2" maxlength="2" value="<%= lnMant.getE01DEABA3().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">% Incremento Pago :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" readonly <% if (lnMant.getF01DEAPAP().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEAPAP" size="10" maxlength="9" value="<%= lnMant.getE01DEAPAP().trim()%>">
            </td>
            <td nowrap width="25%"> 
              <div align="right">Factor :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" readonly <% if (lnMant.getF01DEA2TC().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEA2TC" size="4" maxlength="1" value="<%= lnMant.getE01DEAOAM().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Ciclo Pr&oacute;ximo Incremento Pago : </div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" readonly <% if (lnMant.getF01DEAPCU().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEAPCU" size="4" maxlength="3" value="<%= lnMant.getE01DEAPCU().trim()%>">
            </td>
            <td nowrap width="25%" > 
              <div align="right">Fecha :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" readonly <% if (lnMant.getF01DEALS1().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEALS1" size="2" maxlength="2" value="<%= lnMant.getE01DEALS1().trim()%>">
              <input type="text" readonly <% if (lnMant.getF01DEALS2().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEALS2" size="2" maxlength="2" value="<%= lnMant.getE01DEALS2().trim()%>">
              <input type="text" readonly <% if (lnMant.getF01DEALS3().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DEALS3" size="2" maxlength="2" value="<%= lnMant.getE01DEALS3().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Abono a Capital :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" readonly <% if (lnMant.getF01DLCABA().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DLCABA" size="14" maxlength="13" value="<%= lnMant.getE01DLCABA().trim()%>" >
            </td>
            <td nowrap width="25%" > 
              <div align="right">Factor :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" readonly <% if (lnMant.getF01DLCABF().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DLCABF" size="4" maxlength="1" value="<%= lnMant.getE01DLCABF().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Ciclo Pr&oacute;ximo Abono :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" readonly <% if (lnMant.getF01DLCABC().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DLCABC" size="4" maxlength="3" value="<%= lnMant.getE01DLCABC().trim()%>" >
            </td>
            <td nowrap width="25%" > 
              <div align="right">Fecha :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" readonly <% if (lnMant.getF01DLCAB1().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DLCAB1" size="2" maxlength="2" value="<%= lnMant.getE01DLCAB1().trim()%>">
              <input type="text" readonly <% if (lnMant.getF01DLCAB2().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DLCAB2" size="2" maxlength="2" value="<%= lnMant.getE01DLCAB2().trim()%>">
              <input type="text" readonly <% if (lnMant.getF01DLCAB3().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01DLCAB3" size="2" maxlength="2" value="<%= lnMant.getE01DLCAB3().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <%}%>
</form>
</body>
</html>
