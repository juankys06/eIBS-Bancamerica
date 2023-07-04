<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<%@ page import = "java.lang.Object" %>

<HTML>   
<HEAD>
<TITLE>
Payment Details
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id="header" class= "datapro.eibs.beans.EDL015501Message"  scope="session"/>
<jsp:useBean id="record" class= "datapro.eibs.beans.EDL015502Message"  scope="session"/>
<jsp:useBean id= "list" class= "datapro.eibs.beans.JBObjList"  scope="session"/>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage" scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<script language="Javascript1.1" >

var fieldAmount;
var fieldBank;
var fieldBranch;
var fieldCurrency;
var fieldLedger;

builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
}

function goAction(opt) {
	  if(opt == '0004') {
	  	var rt = confirm("Esta seguro que desea borrar este Registro?")
	  	if(!rt) return;
	  }
      document.forms[0].OPTION.value = opt;
	  document.forms[0].submit();

}

function GetHelpDeductions(name, account, desc, amount, bank, branch, currency, ledger)
{
	page = webapp + "/servlet/datapro.eibs.products.JSEDL0370?SCREEN=9&ACCNUM="+account;
	fieldName=name;
	fieldDesc=desc;
	fieldAmount=amount;
	fieldBank=bank;
	fieldBranch=branch;
	fieldCurrency=currency;
	fieldLedger=ledger;	
	CenterWindow(page,600,350,3);
}

function AddConcepts() {
	var dataT = document.all["dataTable"];
	for(var n=0;n<dataT.rows.length;n++) {
		dataT.rows[n].style.display="";
	}
	
}

function ShowHelpCode(cboName, divName, dscName) {

	var v = document.forms[0][cboName][document.forms[0][cboName].selectedIndex].value;
	if (v == '3') {
		document.all[divName].style.display = "";
		document.forms[0][dscName].value = '';
		document.forms[0][dscName].readOnly = false;
	} else {
		document.all[divName].style.display = "none";
		if (v == '4') {
			document.forms[0][dscName].value = 'FECI/IVA';
			document.forms[0][dscName].readOnly = true;
		} else if (v == '5') {
			document.forms[0][dscName].value = 'FECI Vencido';
			document.forms[0][dscName].readOnly = true;
		} else {
			document.forms[0][dscName].value = '';
			document.forms[0][dscName].readOnly = false;
		}
	}
}

</SCRIPT>

</HEAD>

<BODY>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0155" >
  <p> 
    <input TYPE=HIDDEN name="SCREEN" value="3">
    <input TYPE=HIDDEN NAME="OPTION" VALUE="0002">
  </p>

  <h3 align="center">Mantenimiento al Plan de Pagos y Deducciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ln_crn_pay_det.jsp , EDL0155"> 
  </h3>
  <hr size="4">

<h4></h4>
    <input type=HIDDEN name="E02TYPE01" value="<%=record.getE02TYPE01().trim()%>">
    <input type=HIDDEN name="E02TYPE02" value="<%=record.getE02TYPE02().trim()%>">
    <input type=HIDDEN name="E02TYPE03" value="<%=record.getE02TYPE03().trim()%>">
    <input type=HIDDEN name="E02TYPE04" value="<%=record.getE02TYPE04().trim()%>">
    <input type=HIDDEN name="E02TYPE05" value="<%=record.getE02TYPE05().trim()%>">
    <input type=HIDDEN name="E02TYPE06" value="<%=record.getE02TYPE06().trim()%>">
  
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
                <input type="text" name="E02DEACUN" size="10" maxlength="9" readonly value="<%= userPO.getCusNum().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E02CUSNA1" size="46" maxlength="45" readonly value="<%= userPO.getCusName().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta : </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E02DLPACC" size="13" maxlength="12" value="<%= userPO.getAccNum().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda :</b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="4" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E02DEASPRO" size="5" maxlength="4" readonly value="<%= userPO.getProdCode().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

 <h4 align="left"> Informacion basica </h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="30%" height="23" > 
              <div align="right">Fecha Apertura  :</div>
            </td>
            <td nowrap width="20%" height="23" > 
              <div align="right"><%= userPO.getHeader10().trim()%></div>
            </td>
            <td nowrap width="30%" height="23" > 
              <div align="right">Tasa Interés :</div>
            </td>
            <td nowrap width="20%" height="23" > 
              <div align="right"><%= userPO.getHeader13().trim()%></div>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="30%" > 
              <div align="right">Fecha Vencimiento :</div>
            </td>
            <td nowrap width="20%" > 
              <div align="right"><%= userPO.getHeader11().trim()%></div>
            </td>
            <td nowrap width="30%" > 
              <div align="right">Periodo Base :</div>
            </td>
            <td nowrap width="20%" > 
              <div align="right"><%= userPO.getHeader14().trim()%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%" height="18" > 
              <div align="right">Monto Original  :</div>
            </td>
            <td nowrap width="20%" height="18" > 
              <div align="right"><%= userPO.getHeader12().trim()%></div>
            </td>
            <td nowrap width="30%" height="18" > 
              <div align="right">Tipo de Interés :</div>
            </td>
            <td nowrap width="20%" height="18" > 
              <div align="right"><%= userPO.getHeader15().trim()%></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
 <h4 align="left"> Informacion del Pago </h4> 
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trclear"> 
            <td nowrap width="20%"> <div align="right"> Número Cuota : </div></td>
            <td nowrap width="30%"> 
	          	<input TYPE="TEXT" size="4" maxlength="3" name="E02DLPPNU" value="<%=record.getE02DLPPNU()%>">
	        </td>
            <td nowrap width="15%"  > 
              <div align="right">Fecha de Pago Anterior : </div>
            </td>
            <td nowrap width="30%"  > 
            	<input TYPE="TEXT" size="3" maxlength="2" name="E02DLPPD1" value="<%=record.getE02DLPPD1()%>" onKeyPress="enterInteger()" readonly>
 				<input TYPE="TEXT" size="3" maxlength="2" name="E02DLPPD2" value="<%=record.getE02DLPPD2()%>" onKeyPress="enterInteger()" readonly>
 				<input TYPE="TEXT" size="3" maxlength="2" name="E02DLPPD3" value="<%=record.getE02DLPPD3()%>" onKeyPress="enterInteger()" readonly>
			</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%"  > 
              <div align="right">Fecha de Pago : </div>
            </td>
            <td nowrap width="30%"  > 
            	<input TYPE="TEXT" size="3" maxlength="2" name="E02NEWPD1" value="<%=record.getE02NEWPD1()%>" onKeyPress="enterInteger()">
 				<input TYPE="TEXT" size="3" maxlength="2" name="E02NEWPD2" value="<%=record.getE02NEWPD2()%>" onKeyPress="enterInteger()">
 				<input TYPE="TEXT" size="3" maxlength="2" name="E02NEWPD3" value="<%=record.getE02NEWPD3()%>" onKeyPress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E02NEWPD2,document.forms[0].E02NEWPD1,document.forms[0].E02NEWPD3)">
              <img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
			</td>
            <td nowrap width="20%"  > 
              <div align="right">Fecha Último Pago :</div>
            </td>
            <td nowrap width="30%"  > 
            	<input TYPE="TEXT" size="3" maxlength="2" name="E02DLPDT1" value="<%=record.getE02DLPDT1()%>" onKeyPress="enterInteger()">
 				<input TYPE="TEXT" size="3" maxlength="2" name="E02DLPDT2" value="<%=record.getE02DLPDT2()%>" onKeyPress="enterInteger()">
 				<input TYPE="TEXT" size="3" maxlength="2" name="E02DLPDT3" value="<%=record.getE02DLPDT3()%>" onKeyPress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E02DLPDT2,document.forms[0].E02DLPDT1,document.forms[0].E02DLPDT3)">
              <img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
			</td>            
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" align="right">Periodo de gracia : </td>
            <td nowrap width="30%"> 
	          	<input TYPE="TEXT" size="3" maxlength="2" name="E02DLPGPD" value="<%=record.getE02DLPGPD()%>" onKeyPress="enterInteger()">
	        </td>
            <td nowrap width="20%" align="right">Monto extraordinario : </td>
            <td nowrap width="30%"> 
	          	<input TYPE="TEXT" size="16" maxlength="15" name="E02DLPAEX" value="<%=record.getE02DLPAEX()%>" onKeyPress="enterDecimal()" id="txtright">
	        </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
		   <tr id="trdark"> 
      		  <th align=center nowrap width="30%">Descripción</th>
      		  <th align=right nowrap width="25%">Monto </th>
      		  <th align=right nowrap width="25%">Pagado</th>
		   </tr>
          <tr id="trclear"> 
            <td nowrap width="30%" align="right" >
               	<input TYPE="TEXT" size="40" maxlength="35" name="E02DESC01" value="<%=record.getE02DESC01()%>" readonly></td>
            <td nowrap width="25%" align="right"> 
	          	<input TYPE="TEXT" size="16" maxlength="15" name="E02AMNT01" value="<%=record.getE02AMNT01()%>" onKeyPress="enterDecimal()" id="txtright"></td>
            <td nowrap width="25%" align="right"> 
	          	<input TYPE="TEXT" size="16" maxlength="15" name="E02PAID01" value="<%=record.getE02PAID01()%>" onKeyPress="enterDecimal()" id="txtright"></td>
		   </tr>
		 
          <tr id="trclear"> 
            <td nowrap width="30%" align="right" >
               	<input TYPE="TEXT" size="40" maxlength="35" name="E02DESC02" value="<%=record.getE02DESC02()%>" readonly></td>
            <td nowrap width="25%" align="right"> 
	          	<input TYPE="TEXT" size="16" maxlength="15" name="E02AMNT02" value="<%=record.getE02AMNT02()%>" onKeyPress="enterDecimal()" id="txtright"></td>
            <td nowrap width="25%" align="right"> 
	          	<input TYPE="TEXT" size="16" maxlength="15" name="E02PAID02" value="<%=record.getE02PAID02()%>" onKeyPress="enterDecimal()" id="txtright"></td>
		   </tr>
		  
          <tr id="trclear"> 
            <td nowrap width="30%" align="right" >
               	<input TYPE="TEXT" size="40" maxlength="35" name="E02DESC03" value="<%=record.getE02DESC03()%>" readonly></td>
            <td nowrap width="25%" align="right"> 
	          	<input TYPE="TEXT" size="16" maxlength="15" name="E02AMNT03" value="<%=record.getE02AMNT03()%>" onKeyPress="enterDecimal()" id="txtright"></td>
            <td nowrap width="25%" align="right"> 
	          	<input TYPE="TEXT" size="16" maxlength="15" name="E02PAID03" value="<%=record.getE02PAID03()%>" onKeyPress="enterDecimal()" id="txtright"></td>
		   </tr>
          <tr id="trclear"> 
            <td nowrap width="30%" align="right" >
               	<input TYPE="TEXT" size="40" maxlength="35" name="E02DESC04" value="<%=record.getE02DESC04()%>" readonly></td>
            <td nowrap width="25%" align="right"> 
	          	<input TYPE="TEXT" size="16" maxlength="15" name="E02AMNT04" value="<%=record.getE02AMNT04()%>" onKeyPress="enterDecimal()" id="txtright"></td>
            <td nowrap width="25%" align="right"> 
	          	<input TYPE="TEXT" size="16" maxlength="15" name="E02PAID04" value="<%=record.getE02PAID04()%>" onKeyPress="enterDecimal()" id="txtright"></td>
		   </tr>
		 
          <tr id="trclear"> 
            <td nowrap width="30%" align="right" >
               	<input TYPE="TEXT" size="40" maxlength="35" name="E02DESC05" value="<%=record.getE02DESC05()%>" readonly></td>
            <td nowrap width="25%" align="right"> 
	          	<input TYPE="TEXT" size="16" maxlength="15" name="E02AMNT05" value="<%=record.getE02AMNT05()%>" onKeyPress="enterDecimal()" id="txtright"></td>
            <td nowrap width="25%" align="right"> 
	          	<input TYPE="TEXT" size="16" maxlength="15" name="E02PAID05" value="<%=record.getE02PAID05()%>" onKeyPress="enterDecimal()" id="txtright"></td>
		   </tr>
		   
		 <%if (userPO.getHeader3().trim().equals("R")) { %>  
          <tr id="trclear"> 
            <td nowrap width="30%" align="right" >
               	<input TYPE="TEXT" size="40" maxlength="35" name="E02DESC06" value="<%=record.getE02DESC06()%>" readonly></td>
            <td nowrap width="25%" align="right"> 
	          	<input TYPE="TEXT" size="16" maxlength="15" name="E02AMNT06" value="<%=record.getE02AMNT06()%>" onKeyPress="enterDecimal()" id="txtright"></td>
            <td nowrap width="25%" align="right"> 
	          	<input TYPE="TEXT" size="16" maxlength="15" name="E02PAID06" value="<%=record.getE02PAID06()%>" onKeyPress="enterDecimal()" id="txtright"></td>
		   </tr>
		 <% } %>
		  
        </table>
      </td>
    </tr>
  </table>
 <h4 align="left"> Otros Cargos </h4> 
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
		   <tr id="trdark"> 
        	  <th align=center nowrap width="8%">Tipo</th>
      		  <th align=center nowrap width="8%">Código</th>
      		  <th align=center nowrap width="4%"></th>
      		  <th align=center nowrap width="30%">Descripción</th>
      		  <th align=right nowrap width="25%">Monto </th>
      		  <th align=right nowrap width="25%">Pagado</th>
      		  <th align=right nowrap >Bnc</th>
      		  <th align=right nowrap >Suc</th> 
       		  <th align=right nowrap >Mda</th>
      		  <th align=right nowrap >Contabilidad</th>
      		  <th align=right nowrap >Referencia</th>
		   </tr>
            <%
	           String ext="";
			   int filas = 21;
  				for ( int i=7; i<=filas; i++ ) {
 		           if (i< 10) ext="0"+i; else ext="" +i;
   			%>			 
          <tr id="trclear"> 
            <td nowrap width="8%">
	          <div>
	          <select name="E02TYPE<%=ext%>" onChange="javascript:ShowHelpCode('E02TYPE<%=ext%>','HLPCODE<%=ext%>','E02DESC<%=ext%>')">
	            <option value=" " <% if(record.getField("E02TYPE"+ext).getString().trim().equals("")) out.print("selected"); %>></option>
	            <option value="1" <% if(record.getField("E02TYPE"+ext).getString().trim().equals("1")) out.print("selected"); %>>Impuesto</option>
	            <option value="2" <% if(record.getField("E02TYPE"+ext).getString().trim().equals("2")) out.print("selected"); %>>Comision</option>
	            <option value="3" <% if(record.getField("E02TYPE"+ext).getString().trim().equals("3")) out.print("selected"); %>>Deduccion</option>
	            <option value="4" <% if(record.getField("E02TYPE"+ext).getString().trim().equals("4")) out.print("selected"); %>>FECI/IVA</option>
	            <option value="5" <% if(record.getField("E02TYPE"+ext).getString().trim().equals("5")) out.print("selected"); %>>FECI Vencido</option>	          </select> 
	          </div>
			</td>
            <td align="left" nowrap width="8%">
   	  			<input type="text" name="E02CODE<%=ext%>" size="5" maxlength="4" value="<%=record.getField("E02CODE"+ext).getString().trim()%>" >
      	  	</td> 
        	<td align="left"  nowrap width="4%" >
      	  		<div align="left" id="HLPCODE<%=ext%>" 
      	  			<% if(!record.getField("E02TYPE"+ext).getString().trim().equals("3")) out.print("style=\"display:none\""); %>>
      	  			<a href="javascript:GetHelpDeductions('E02CODE<%=ext%>', document.forms[0].E02DLPACC.value, 'E02DESC<%=ext%>', 'E02AMNT<%=ext%>', 'E02OBNK<%=ext%>', 'E02OBRN<%=ext%>', 'E02OCCY<%=ext%>', 'E02OGLN<%=ext%>')">
      	  			<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0"></a>
      	  		</div>
      	    </td> 
        	<td nowrap width="30%" align="right" >
               	<input TYPE="TEXT" size="25" maxlength="20" name="E02DESC<%=ext%>" value="<%=record.getField("E02DESC"+ext).getString().trim()%>">
            </td>
            <td nowrap width="25%" align="right"> 
	          	<input TYPE="TEXT" size="16" maxlength="15" name="E02AMNT<%=ext%>" value="<%=record.getField("E02AMNT"+ext).getString().trim()%>" onKeyPress="enterDecimal()" id="txtright">
	        </td>
            <td nowrap width="25%" align="right"> 
	          	<input TYPE="TEXT" size="16" maxlength="15" name="E02PAID<%=ext%>" value="<%=record.getField("E02PAID"+ext).getString().trim()%>" onKeyPress="enterDecimal()" id="txtright">
	        </td>
            <td nowrap align="center"> 
                <input type="text" size="2" maxlength="2" name="E02OBNK<%=ext%>"  value="<%=record.getField("E02OBNK"+ext).getString().trim()%>">
            </td>
            <td nowrap align="center"> 
                <input type="text" size="3" maxlength="3" name="E02OBRN<%=ext%>"  value="<%=record.getField("E02OBRN"+ext).getString().trim()%>" onKeyPress="enterInteger()"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E02OBNK<%=ext%>.value,'','','','')">
            </td>
            <td nowrap align="center"> 
                <input type="text" size="3" maxlength="3" name="E02OCCY<%=ext%>" value="<%=record.getField("E02OCCY"+ext).getString().trim()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E02OBNK<%=ext%>.value,'','','','')">
            </td>
            <td nowrap align="center"> 
                <input type="text" size="16" maxlength="16" name="E02OGLN<%=ext%>" value="<%=record.getField("E02OGLN"+ext).getString().trim()%>" onKeyPress="enterInteger()"
                oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02OBNK<%=ext%>.value,document.forms[0].E02OCCY<%=ext%>.value,'','','')"> 
            </td>
            <td nowrap align="center"> 
                <input type="text" size="12" maxlength="12" name="E02OACC<%=ext%>" value="<%=record.getField("E02OACC"+ext).getString().trim()%>" onKeyPress="enterInteger()"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E02OBNK<%=ext%>.value,'','','','RT')"> 
            </td>
	          	
		   </tr>
          
    		<%
    		}
    		%>

        </table>
      </td>
    </tr>
  </table>
<BR>

  <BR>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="20%" height="18" ><b>Total</b></td>
            <td nowrap width="3%" height="18" >&nbsp;</td>
            <td nowrap width="37%" height="18" >Monto a Pagar : <input type="text" name="E02TOAMNT" size="17" maxlength="16" value="<%=record.getE02TOAMNT()%>" readonly id="txtright"></td>
            <td nowrap width="4%" height="18" >&nbsp;</td>
            <td nowrap width="36%" height="18" > 
              <div align="left">Monto Pagado :<input type="text" name="E02TOPAID" size="17" maxlength="16" value="<%=record.getE02TOPAID()%>" readonly id="txtright"></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <BR>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="50%" align="center"> 
       		<input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="goAction('0002');">
  	  </td>     
      <td width="50%" align="center"> 
       		<input id="EIBSBTN" type=button name="Delete" value="Eliminar" onClick="goAction('0004');">
	  </td> 
    </tr>    
  </table>  
  </FORM>

</BODY>
</HTML>
