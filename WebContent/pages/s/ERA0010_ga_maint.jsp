<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Información  Básica de Garantías</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="gaMant" class= "datapro.eibs.beans.ERA001001Message"  scope="session"/>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

 <%
   if ( !userPO.getPurpose().equals("NEW") ) {
 %>
<SCRIPT Language="Javascript">
   
    builtNewMenu(colla_M_opt);
    
</SCRIPT>
  <%
   }
  %>
<SCRIPT Language="Javascript">

function hidePoliza(value){
 if (value) {
   Poliza.style.display="";}
 else{
   Poliza.style.display="none"; }
}

</SCRIPT>

</head>

<body bgcolor="#FFFFFF">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
   if ( !userPO.getPurpose().equals("NEW") ) {
    out.println("<SCRIPT> initMenu(); </SCRIPT>");
 }
%> 
<h3 align="center">Garant&iacute;as - Informaci&oacute;n B&aacute;sica
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ga_maint.jsp,ERA0010"></h3> 
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0010" >

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="8%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="16%" > 
              <div align="left"> 
                <input type="text" name="E01ROCCUN" size="9" maxlength="9" value="<%= gaMant.getE01ROCCUN().trim()%>">
              </div>
            </td>
            <td nowrap width="13%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" width="41%" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E01CUSNA1" size="45" maxlength="45" value="<%= gaMant.getE01CUSNA1().trim()%>" >
                </font></font></font></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n B&aacute;sica</h4>
      <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="21%" > 
              <div align="right">Banco /Sucursal /Moneda :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01ROCBNK" size="2" maxlength="2" value="<%= gaMant.getE01ROCBNK().trim()%>" >
              / 
              <input type="text" name="E01ROCBRN" size="4" maxlength="3" value="<%= gaMant.getE01ROCBRN().trim()%>" >
              <a href="javascript:GetBranch('E01ROCBRN',document.forms[0].E01ROCBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" width="18" height="22" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" > 
              / 
              <input type="text" name="E01ROCCCY" size="4" maxlength="3" value="<%= gaMant.getE01ROCCCY().trim()%>" >
              <a href="javascript:GetCurrency('E01ROCCCY',document.forms[0].E01ROCBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" height="23" > 
              <div align="right">No Cta Contable:</div>
            </td>
            <td nowrap colspan="3" height="23" > <a href="javascript:GetBranch('E01WLNBRN',document.forms[0].E01WLNBNK.value)"> 
              </a> 
              <input type="text" name="E01ROCGLN" size="16" maxlength="16" value="<%= gaMant.getE01ROCGLN().trim()%>" >
              <a href="javascript:GetLedger('E01ROCGLN',document.forms[0].E01ROCBNK.value,document.forms[0].E01ROCCCY.value,'91')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" > 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="21%" height="23" >
              <div align="right">No de Referencia:</div>
            </td>
            <td nowrap colspan="3" height="23" ><a href="javascript:GetBranch('E01WLNBRN',document.forms[0].E01WLNBNK.value)"> 
              </a>
               <%
   					if ( !userPO.getPurpose().equals("NEW") ) {
 					%>
              <input type="text" name="E01ROCREF" size="12" maxlength="12" value="<%= gaMant.getE01ROCREF().trim()%>"  readonly >
              <%
  						 } else {
  					%>
  					<input type="text" name="E01ROCREF" size="12" maxlength="12" value="<%= gaMant.getE01ROCREF().trim()%>" >
  					 <%
  						 } 
  					%>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" height="40" > 
              <div align="right">Tipo de Garant&iacute;a:</div>
            </td>
            <td nowrap colspan="3" height="40" ><a href="javascript:GetBranch('E01WLNBRN',document.forms[0].E01WLNBNK.value)"> 
              <input type="text" name="E01ROCTYP" size="4" maxlength="4" value="<%= gaMant.getE01ROCTYP().trim()%>" >
              </a><a href="javascript:GetCodeCNOFC('E01ROCTYP','05')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" > 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="21%" height="23" > 
              <div align="right"> Due&ntilde;o Garant&iacute;a :</div>
            </td>
            <td nowrap colspan="3" height="23" >
              <input type="text" name="E01ROCOWN" size="4" maxlength="4" value="<%= gaMant.getE01ROCOWN().trim()%>" >
              / 
              <input type="text" name="E01CUSNAM" size="35" maxlength="35" value="<%= gaMant.getE01CUSNAM().trim()%>" >
              <a href="javascript:GetCustomerDescId('E01ROCOWN','E01CUSNAM','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Descripci&oacute;n / Escritura:</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" name="E01ROCDES" size="35" maxlength="35" value="<%= gaMant.getE01ROCDES().trim()%>" >
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" > 
                / 
                <input type="text" name="E01ROCRF3" size="16" maxlength="16" value="<%= gaMant.getE01ROCRF3().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="21%" > 
              <div align="right">Notario /No Inscripci&oacute;n:</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" name="E01ROCRF4" size="35" maxlength="35" value="<%= gaMant.getE01ROCRF4().trim()%>" >
                / 
                <input type="text" name="E01ROCRF2" size="20" maxlength="20" value="<%= gaMant.getE01ROCRF2().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right"> Ubicaci&oacute;n:</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01ROCLOC" size="35" maxlength="35" value="<%= gaMant.getE01ROCLOC().trim()%>" >
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Sumario</h4>
  <TABLE class="tableinfo">
    <tr >
      <TD nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="23%" > 
              <div align="right">Precio Compra Unidad:</div>
            </td>
            <td nowrap width="19%" > 
              <input type="text" name="E01ROCPPU" size="10" maxlength="9" value="<%= gaMant.getE01ROCPPU().trim()%>" >
            </td>
            <td nowrap width="20%" > 
              <div align="right">Unidades y su Valor :</div>
            </td>
            <td nowrap width="38%" >
              <input type="text" name="E01ROCNUN" size="10" maxlength="9" value="<%= gaMant.getE01ROCNUN().trim()%>" >
              / 
              <input type="text" name="E01ROCMPU" size="10" maxlength="9" value="<%= gaMant.getE01ROCMPU().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" height="34" > 
              <div align="right">Valor Tasado:</div>
            </td>
            <td nowrap width="19%" height="34" > 
              <input type="text" name="E01ROCAPA" size="15" maxlength="15" value="<%= gaMant.getE01ROCAPA().trim()%>" >
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" width="16" height="19" > 
            </td>
            <td nowrap width="20%" height="34" > 
              <div align="right">Tasa de Cambio: </div>
            </td>
            <td nowrap width="38%" height="34" > 
              <input type="text" name="E01ROCOFX" size="11" maxlength="11" value="<%= gaMant.getE01ROCOFX().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="23%" > 
              <div align="right">Valor Inelegible:</div>
            </td>
            <td nowrap width="19%" > 
              <input type="text" name="E01ROCINA" size="15" maxlength="15" value="<%= gaMant.getE01ROCINA().trim()%>" >
            </td>
            <td nowrap width="20%" > 
              <div align="right">Tasado por:</div>
            </td>
            <td nowrap width="38%" >
              <input type="text" name="E01ROCABY" size="15" maxlength="9" value="<%= gaMant.getE01ROCABY().trim()%>" >
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" width="16" height="19" > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="right">Valor Elegible:</div>
            </td>
            <td nowrap width="19%" > 
              <input type="text" name="E01ROCLIV" size="15" maxlength="15" value="<%= gaMant.getE01ROCLIV().trim()%>" >
            </td>
            <td nowrap width="20%" > 
              <div align="right">Ultimo Tasamiento:</div>
            </td>
            <td nowrap width="38%" >
              <input type="text" name="E01ROCAP1" size="2" maxlength="2" value="<%= gaMant.getE01ROCAP1().trim()%>" >
              <input type="text" name="E01ROCAP2" size="2" maxlength="2" value="<%= gaMant.getE01ROCAP2().trim()%>" >
              <input type="text" name="E01ROCAP3" size="2" maxlength="2" value="<%= gaMant.getE01ROCAP3().trim()%>" >
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" width="16" height="19" > 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="23%" > 
              <div align="right">Porcentage Adelantado:</div>
            </td>
            <td nowrap width="19%" > 
              <input type="text" name="E01ROCRAT" size="10" maxlength="9" value="<%= gaMant.getE01ROCRAT().trim()%>" >
            </td>
            <td nowrap width="20%" > 
              <div align="right">% M&aacute;ximo Garant&iacute;a:</div>
            </td>
            <td nowrap width="38%" >
              <input type="text" name="E01ROCCPE" size="16" maxlength="16" value="<%= gaMant.getE01ROCCPE().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="right">Monto Disponible:</div>
            </td>
            <td nowrap width="19%" > 
              <input type="text" name="E01ROCFAA" size="15" maxlength="15" value="<%= gaMant.getE01ROCFAA().trim()%>" >
            </td>
            <td nowrap width="20%" > 
              <div align="right">Fecha Apertura:</div>
            </td>
            <td nowrap width="38%" >
              <input type="text" name="E01ROCOP1" size="2" maxlength="2" value="<%= gaMant.getE01ROCOP1().trim()%>" >
              <input type="text" name="E01ROCOP2" size="2" maxlength="2" value="<%= gaMant.getE01ROCOP2().trim()%>" >
              <input type="text" name="E01ROCOP3" size="2" maxlength="2" value="<%= gaMant.getE01ROCOP3().trim()%>" >
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" width="16" height="19" > 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="23%" > 
              <div align="right">P&oacute;liza Seguro: </div>
            </td>
            <td nowrap width="19%" > 
              <input type="hidden" name="E01ROCINF" value="<%= gaMant.getE01ROCINF()%>">
              <input type="radio" name="CE01ROCINF" value="Y" onClick="document.forms[0].E01ROCINF.value='Y'; hidePoliza(true);"
			  <%if(gaMant.getE01ROCINF().equals("Y")) out.print("checked");%>>
              Yes 
              <input type="radio" name="CE01ROCINF" value="N" onClick="document.forms[0].E01ROCINF.value='N'; hidePoliza(false);"
			  <%if(gaMant.getE01ROCINF().equals("N")) out.print("checked");%>>
              No </td>
            <td nowrap width="20%" > 
              <div align="right">Fecha Vencimiento:</div>
            </td>
            <td nowrap width="38%" >
              <input type="text" name="E01ROCMT1" size="2" maxlength="2" value="<%= gaMant.getE01ROCMT1().trim()%>" >
              <input type="text" name="E01ROCMT2" size="2" maxlength="2" value="<%= gaMant.getE01ROCMT2().trim()%>" >
              <input type="text" name="E01ROCMT3" size="2" maxlength="2" value="<%= gaMant.getE01ROCMT3().trim()%>" >
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" width="16" height="19" > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="right">Endosa Seguro:</div>
            </td>
            <td nowrap width="19%" > 
              <input type="hidden" name="E01ROCEND" value="<%= gaMant.getE01ROCEND()%>">
              <input type="radio" name="CE01ROCEND" value="Y" onClick="document.forms[0].E01ROCEND.value='Y'"
			  <%if(gaMant.getE01ROCEND().equals("Y")) out.print("checked");%>>
              Yes 
              <input type="radio" name="CE01ROCEND" value="N" onClick="document.forms[0].E01ROCEND.value='N'"
			  <%if(gaMant.getE01ROCEND().equals("N")) out.print("checked");%>>
              No </td>
            <td nowrap width="20%" >
              <div align="right">Fecha Revisi&oacute;n:</div>
            </td>
            <td nowrap width="38%" >
              <input type="text" name="E01ROCRE1" size="2" maxlength="2" value="<%= gaMant.getE01ROCRE1().trim()%>" >
              <input type="text" name="E01ROCRE2" size="2" maxlength="2" value="<%= gaMant.getE01ROCRE2().trim()%>" >
              <input type="text" name="E01ROCRE3" size="2" maxlength="2" value="<%= gaMant.getE01ROCRE3().trim()%>" >
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" width="16" height="19" > 
            </td>
          </tr>
        </table>
      </TD>
    </tr>
  </TABLE>
<BR>
<div id="Poliza">
  <h4>P&oacute;liza de la Garant&iacute;a</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right"> Compa&ntilde;&iacute;a:</div>
            </td>
            <td nowrap width="28%" > 
              <input type="text" name="E01ROCICN" size="10" maxlength="9" value="<%= gaMant.getE01ROCICN().trim()%>" >
            </td>
            <td nowrap width="18%" > 
                <div align="right">N&uacute;mero P&oacute;liza:</div>
            </td>
              <td nowrap width="37%" >
                <input type="text" name="E01ROCCIN" size="41" maxlength="40" value="<%= gaMant.getE01ROCCIN().trim()%>" >
              </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Nombre :</div>
            </td>
            <td nowrap width="28%" > 
              <input type="text" name="E01ROCICM" size="35" maxlength="35" value="<%= gaMant.getE01ROCICM().trim()%>" >
            </td>
            <td nowrap width="18%" > 
                <div align="right">Monto:</div>
            </td>
              <td nowrap width="37%" >
                <input type="text" name="E01ROCIPA" size="10" maxlength="9" value="<%= gaMant.getE01ROCIPA().trim()%>" >
              </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Descripci&oacute;n :</div>
            </td>
            <td nowrap width="28%" > 
              <input type="text" name="E01ROCIPD" size="35" maxlength="35" value="<%= gaMant.getE01ROCIPD().trim()%>" >
            </td>
            <td nowrap width="18%" > 
                <div align="right">Moneda:</div>
            </td>
              <td nowrap width="37%" >
                <input type="text" name="E01ROCICY" size="4" maxlength="3" value="<%= gaMant.getE01ROCICY().trim()%>" >
                <a href="javascript:GetCurrency('E01ROCICY',document.forms[0].E01ROCBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0" ></a> 
              </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Fecha Emisi&oacute;n:</div>
            </td>
            <td nowrap width="28%" > 
              <input type="text" name="E01ROCEM1" size="2" maxlength="2" value="<%= gaMant.getE01ROCEM1().trim()%>" >
              <input type="text" name="E01ROCEM2" size="2" maxlength="2" value="<%= gaMant.getE01ROCEM2().trim()%>" >
              <input type="text" name="E01ROCEM3" size="2" maxlength="2" value="<%= gaMant.getE01ROCEM3().trim()%>" >
            </td>
            <td nowrap width="18%" > 
                <div align="right">Emitido:</div>
            </td>
              <td nowrap width="37%" >
                <input type="text" name="E01ROCEMB" size="10" maxlength="9" value="<%= gaMant.getE01ROCEMB().trim()%>" >
              </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Fecha Vencimiento:</div>
            </td>
            <td nowrap width="28%" > 
              <input type="text" name="E01ROCMD1" size="2" maxlength="2" value="<%= gaMant.getE01ROCMD1().trim()%>" >
              <input type="text" name="E01ROCMD2" size="2" maxlength="2" value="<%= gaMant.getE01ROCMD2().trim()%>" >
              <input type="text" name="E01ROCMD3" size="2" maxlength="2" value="<%= gaMant.getE01ROCMD3().trim()%>" >
            </td>
            <td nowrap width="18%" > 
                <div align="right">Excepci&oacute;n:</div>
            </td>
              <td nowrap width="37%" >
                <input type="hidden" name="E01ROCRGK" value="<%= gaMant.getE01ROCRGK()%>">
                <input type="radio" name="CE01ROCRGK" value="Y" onClick="document.forms[0].E01ROCRGK.value='Y'"
			  <%if(gaMant.getE01ROCRGK().equals("Y")) out.print("checked");%>>
                Yes 
                <input type="radio" name="CE01ROCRGK" value="N" onClick="document.forms[0].E01ROCRGK.value='N'"
			  <%if(gaMant.getE01ROCRGK().equals("N")) out.print("checked");%>>
                No </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
                <div align="right">Aviso Vencimiento:</div>
            </td>
              <td nowrap width="28%" >
                <input type="hidden" name="E01ROCCLF" value="<%= gaMant.getE01ROCCLF()%>">
                <input type="radio" name="CE01ROCCLF" value="Y" onClick="document.forms[0].E01ROCCLF.value='Y'"
			  <%if(gaMant.getE01ROCCLF().equals("Y")) out.print("checked");%>>
                Yes 
                <input type="radio" name="CE01ROCCLF" value="N" onClick="document.forms[0].E01ROCCLF.value='N'"
			  <%if(gaMant.getE01ROCCLF().equals("N")) out.print("checked");%>>
                No </td>
            <td nowrap width="18%" > 
                <div align="right">C&oacute;digo Usuario:</div>
            </td>
            <td nowrap width="37%" >
                <input type="text" name="E01ROCUSC" size="10" maxlength="9" value="<%= gaMant.getE01ROCUSC().trim()%>" >
				<input type="hidden" name="E02ROCUSC" size="10" maxlength="9" value="" >
                <a href="javascript:GetCodeDescCNOFC('E01ROCUSC','E02ROCUSC','2A')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" width="18" height="22" ></a> 
              </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</div>
 
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>

<SCRIPT Language="Javascript">
	<% if(gaMant.getE01ROCINF().equals("Y")){ %>
 	 hidePoliza(true);
	<%} else { %>
 	 hidePoliza(false);
	<%}%>
</SCRIPT>

 
  </form>
</body>
</html>
