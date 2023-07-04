<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Certificates Maintenance</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="cdMant" class="datapro.eibs.beans.EDL013001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">


<%
if ( userPO.getHeader16().equals("N") ) {
%>
	builtNewMenu(cdm_m_act_opt);
<%   
} 
else {
%>  
   builtNewMenu(cdm_m_opt);
<%}%>

	builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }

</SCRIPT>

</head>

<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
 	 error.setERRNUM("0");
    out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
  out.println("<SCRIPT> initMenu(); </SCRIPT>");
%>
<h3 align="center"> Informaci&oacute;n B&aacute;sica <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_maint.jsp,EDL0130B"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
  <INPUT TYPE=HIDDEN NAME="E01DEABNK"  value="<%= cdMant.getE01DEABNK().trim()%>">
  <input type=HIDDEN name="E01DEAACD"  value="<%= cdMant.getE01DEAACD().trim()%>">
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
                <input type="text" name="E01DEACUN" size="9" maxlength="9" value="<%= cdMant.getE01DEACUN().trim()%>">
                <a href="javascript:GetCustomerDescId('E01DEACUN','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a></div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01CUSNA1" size="45" maxlength="45" value="<%= cdMant.getE01CUSNA1().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01DEAACC" size="12" maxlength="9" value="<%= cdMant.getE01DEAACC().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY2" size="3" maxlength="3" value="<%= cdMant.getE01DEACCY().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEAPRO" size="4" maxlength="4" value="<%= cdMant.getE01DEAPRO().trim()%>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n Original </h4>
      <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Fecha de Apertura :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAOD1" size="2" maxlength="2" value="<%= cdMant.getE01DEAOD1().trim()%>" readonly>
              <input type="text" name="E01DEAOD2" size="2" maxlength="2" value="<%= cdMant.getE01DEAOD2().trim()%>" readonly>
              <input type="text" name="E01DEAOD3" size="2" maxlength="2" value="<%= cdMant.getE01DEAOD3().trim()%>" readonly>
            </td>
            <td nowrap > 
              <div align="right">Monto Original :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAOAM" size="15" maxlength="13" value="<%= cdMant.getE01DEAOAM().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAMD1" size="2" maxlength="2" value="<%= cdMant.getE01DEAMD1().trim()%>" readonly>
              <input type="text" name="E01DEAMD2" size="2" maxlength="2" value="<%= cdMant.getE01DEAMD2().trim()%>" readonly>
              <input type="text" name="E01DEAMD3" size="2" maxlength="2" value="<%= cdMant.getE01DEAMD3().trim()%>" readonly>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="Mandatory Field" align="absbottom" border="0" > 
            </td>
            <td nowrap > 
              <div align="right">Moneda :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= cdMant.getE01DEACCY().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap >
              <div align="right">Base de Periodificaci&oacute;n :</div>
            </td>
            <td nowrap >
              <input type="text" name="E01DEABAS" size="3" maxlength="3" value="<%= cdMant.getE01DEABAS().trim()%>" readonly>
            </td>
            <td nowrap >
              <div align="right">Tasa Actual :</div>
            </td>
            <td nowrap >
              <input type="text" name="E01RATE" size="10" maxlength="9" value="<%= cdMant.getE01RATE().trim()%>" readonly>
            </td>
          </tr>
 
        </table>
      </td>
    </tr>
  </table>
  <h4> Informaci&oacute;n B&aacute;sica</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Nueva Fecha de Vencimiento :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01NEWMD1" size="2" maxlength="2" value="<%= cdMant.getE01NEWMD1().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01NEWMD2" size="2" maxlength="2" value="<%= cdMant.getE01NEWMD2().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01NEWMD3" size="2" maxlength="2" value="<%= cdMant.getE01NEWMD3().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E01NEWMD1,document.forms[0].E01NEWMD2,document.forms[0].E01NEWMD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
            </td>
            <td nowrap > 
              <div align="right">T&eacute;rminos :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEATRM" size="4" maxlength="4" value="<%= cdMant.getE01DEATRM().trim()%>" onkeypress="enterInteger()">
              <select name="E01DEATRC">
                <option value=" " <% if (!(cdMant.getE01DEATRC().equals("D") ||cdMant.getE01DEATRC().equals("M")||cdMant.getE01DEATRC().equals("Y"))) out.print("selected"); %>></option>
                <option value="D" <% if(cdMant.getE01DEATRC().equals("D")) out.print("selected");%>>Día(s)</option>
                <option value="M" <% if(cdMant.getE01DEATRC().equals("M")) out.print("selected");%>>Mes(s)</option>
                <option value="Y" <% if(cdMant.getE01DEATRC().equals("Y")) out.print("selected");%>>Año(s)</option>
              </select>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="Mandatory Field" align="absbottom" border="0" > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Tipo de Tasa Flotante :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAFTB" size="2" maxlength="2" value="<%= cdMant.getE01DEAFTB().trim()%>">
              <a href="javascript:GetFloating('E01DEAFTB')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Floating Rates Table" align="absmiddle" border="0" ></a> 
              <select name="E01DEAFTY">
                <option value=" " <% if (!(cdMant.getE01DEAFTY().equals("FP") ||cdMant.getE01DEAFTY().equals("FS"))) out.print("selected"); %>></option>
                <option value="FP" <% if (cdMant.getE01DEAFTY().equals("FP")) out.print("selected"); %>>Primaria</option>
                <option value="FS" <% if (cdMant.getE01DEAFTY().equals("FS")) out.print("selected"); %>>Secundaria</option>
              </select>
            </td>
            <td nowrap > 
              <div align="right">Tasa Flotante :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01FLTRTE" size="10" maxlength="9" value="<%= cdMant.getE01FLTRTE().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Per&iacute;odo Revisi&oacute;n Tasa / Fecha:</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEARRP" size="3" maxlength="3" value="<%= cdMant.getE01DEARRP().trim()%>" onkeypress="enterInteger()">
              / 
              <input type="text" name="E01DEARD1" size="2" maxlength="2" value="<%= cdMant.getE01DEARD1().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01DEARD2" size="2" maxlength="2" value="<%= cdMant.getE01DEARD2().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01DEARD3" size="2" maxlength="2" value="<%= cdMant.getE01DEARD3().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E01DEARD1,document.forms[0].E01DEARD2,document.forms[0].E01DEARD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
            </td>
            <td nowrap > 
              <div align="right">Tasa de Inter&eacute;s /Spread :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEARTE" size="10" maxlength="9" value="<%= cdMant.getE01DEARTE().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Retenciones /Impuestos :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAWHF" size="2" maxlength="1" value="<%= cdMant.getE01DEAWHF().trim()%>">
              <a href="javascript:GetCode('E01DEAWHF','STATIC_cd_taxes.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" align="absbottom" border="0" alt="help" ></a> 
            </td>
            <td nowrap > 
              <div align="right"> N&uacute;mero de Referencia :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAREF" size="12" maxlength="12" value="<%= cdMant.getE01DEAREF().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">M&eacute;todo C&aacute;lculo de Inter&eacute;s 
                :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAICT" size="2" maxlength="1" value="<%= cdMant.getE01DEAICT().trim()%>">
              <a href="javascript:GetCode('E01DEAICT','STATIC_cd_formula.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" align="absbottom" border="0" alt="help" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="Mandatory Field" align="absbottom" border="0"> 
            </td>
            <td nowrap > 
              <div align="right"> Tasa Moneda Extranjera :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAEXR" size="11" maxlength="11" value="<%= cdMant.getE01DEAEXR().trim()%>" onkeypress="enterDecimal()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Direcci&oacute;n :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAMLA" size="2" maxlength="1" value="<%= cdMant.getE01DEAMLA().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:GetMailing('E01DEAMLA',document.forms[0].E01DEACUN.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"></a> 
            </td>
            <td nowrap > 
              <div align="right">C&aacute;lculo de Inter&eacute;s :</div>
            </td>
            <td nowrap > 
              <input type="radio" name="E01DEAIFL" value="1" <% if (cdMant.getE01DEAIFL().equals("1")) out.print("checked"); %>>
              Si 
              <input type="radio" name="E01DEAIFL" value="N" <% if (cdMant.getE01DEAIFL().equals("N")) out.print("checked"); %>>
              No </td>
          </tr>
          <tr id="trdark">
            <td nowrap >
              <div align="right">Centro de Costo :</div>
            </td>
            <td nowrap >
              <input type="text" name="E01DEACCN" size="8" maxlength="6" value="<%= cdMant.getE01DEACCN().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:GetCostCenter('E01DEACCN',document.forms[0].E01DEABNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a> 
            </td>
            <td nowrap >
              <div align="right">Tablas de Tasas : </div>
            </td>
            <td nowrap >
              <input type="text" name="E01DEARTB" size="1" maxlength="1" value="<%= cdMant.getE01DEARTB().trim()%>">
              <a href="javascript:GetRateTable('E01DEARTB')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Estado : </div>
            </td>
            <td nowrap > 
              <select name="E01DEADLC">
                <option value=" " <% if (!(cdMant.getE01DEADLC().equals("1") ||cdMant.getE01DEADLC().equals("2")||cdMant.getE01DEADLC().equals("3"))) out.print("selected"); %>></option>
                <option value="1" <% if (cdMant.getE01DEADLC().equals("1")) out.print("selected"); %>>Activado</option>
                <option value="2" <% if (cdMant.getE01DEADLC().equals("2")) out.print("selected"); %>>Vencido</option>
                <option value="3" <% if (cdMant.getE01DEADLC().equals("3")) out.print("selected"); %>>Retenido</option>
              </select>
            </td>
            <td nowrap > 
              <div align="right">Forma de Pago :</div>
            </td>
            <td nowrap >
              <input type="text" name="E01DEAPVI" size="2" maxlength="1" value="<%= cdMant.getE01DEAPVI().trim()%>">
              <a href="javascript:GetCode('E01DEAPVI','STATIC_cd_pmnt_via.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" align="absbottom" border="0" alt="help" ></a> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Cuenta Contrapartida</h4>
   <TABLE class="tableinfo">
  <TR><TD>
  
   <table id="headTable">
    <tr id="trdark"> 
            <td nowrap align="center" >Concepto</td>
            <td nowrap align="center" >Banco</td>
            <td nowrap align="center" >Sucursal</td>
            <td nowrap align="center" >Moneda</td>
            <td nowrap align="center" >Referencia</td>
            <td nowrap align="center" >Monto</td>
    </tr>
    </table> 
      
    <div id="dataDiv" class="scbarcolor" style="height:60; overflow-y :scroll; z-index:0" >
     <table id="dataTable">
    <%
  				   int amount = 9;
 				   String name;
  					for ( int i=1; i<=amount; i++ ) {
   					  name = i + "";
   			%> 
    <tr id="trclear"> 
      <td nowrap > 
                <div align="center"> 
                  <input type="text" name="E01OFFOP<%= name %>" value="<%= cdMant.getField("E01OFFOP"+name).getString().trim()%>" size="2" maxlength="2">
                  <input type="hidden" name="E01OFFGL<%= name %>" value="<%= cdMant.getField("E01OFFGL"+name).getString().trim()%>">
          <input type="text" name="E01OFFCO<%= name %>" size="25" maxlength="25" readonly value="<%= cdMant.getField("E01OFFCO"+name).getString().trim()%>" 
                  oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E01DEABNK.value,'','E01OFFGL<%= name %>','E01OFFOP<%= name %>',document.forms[0].E01DEAACD.value)">
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01OFFBK<%= name %>" size="2" maxlength="2" value="<%= cdMant.getField("E01OFFBK"+name).getString().trim()%>" onkeypress="enterInteger()">
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01OFFBR<%= name %>" size="3" maxlength="3" value="<%= cdMant.getField("E01OFFBR"+name).getString().trim()%>"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01DEABNK.value,'','','','')" onkeypress="enterInteger()">
        </div>
      </td>
      <td nowrap  > 
        <div align="center"> 
          <input type="text" name="E01OFFCY<%= name %>" size="3" maxlength="3" value="<%= cdMant.getField("E01OFFCY"+name).getString().trim()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01DEABNK.value,'','','','')">
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
                  <input type="text" name="E01OFFAC<%= name %>" size="13" maxlength="12"  value="<%= cdMant.getField("E01OFFAC"+name).getString().trim()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01DEABNK.value,'','','','RC')"  onkeypress="enterInteger()">
        </div>
      </td>
      <td nowrap > 
                <div align="center"> 
                  <input type="text" name="E01OFFAM<%= name %>" size="15" maxlength="13"  value="<%= cdMant.getField("E01OFFAM"+name).getString().trim()%>" onKeyPress="enterDecimal()">
                </div>
      </td>
    </tr>
    <%
    		}
    		%> 
     </table>
        </div>
 <table id="footTable"> 		
    <tr id="trdark"> 
            <td nowrap align="right"><b>Equivalente en Moneda del Certificado 
              :</b> </td>
      <td nowrap align="center"><b><i><strong> 
          <input type="text" name="E01OFFEQV" size="15" maxlength="13" readonly value="<%= cdMant.getE01OFFEQV().trim()%>">
          </strong></i></b>
      </td>
    </tr>
  </table>
  </TD>  
</TR>	
</TABLE>    
 <SCRIPT language="javascript">
    function tableresize() {
     adjustEquTables(headTable,dataTable,dataDiv,0,true);
   }
  tableresize();
  window.onresize=tableresize;
  </SCRIPT>
	<% 
		if (error.getERWRNG().equals("Y")) { 
			error.setERWRNG(" ");
	%>
			<h4 style="text-align:center"><input type="checkbox" name="H01FLGWK2" value="A">
    Aceptar con Errores</h4>
	<% 
		} 
	%>       
 <br>
  <div align="center"> 
	   
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
  </form>
</body>
</html>
