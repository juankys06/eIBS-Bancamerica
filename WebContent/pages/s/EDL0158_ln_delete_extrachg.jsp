<%@ page import = "datapro.eibs.beans.EDL015801Message" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Gastos Extras</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "extList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>

<body nowrap>

<% 
 	if ( !error.getERRNUM().equals("0")  ) {
     	error.setERRNUM("0"); 
     	out.println("<SCRIPT Language=\"Javascript\">");
     	out.println("       showErrors()");
     	out.println("</SCRIPT>");
 	}
 	int row;
	try {
		row = Integer.parseInt(request.getParameter("ROW"));
	} 
	catch (Exception e) {
		row = 0;
	}
	extList.setCurrentRow(row);
	EDL015801Message msgLN = (EDL015801Message) extList.getRecord();
%>

<SCRIPT LANGUAGE="JavaScript">

 builtHPopUp();

 function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
 }
 
 function showTypeChg(typval){
   var state="";
   if (typval == "4") state="";
   else state="none";
   INFOCHG.rows[INFOCHG.rows.length -2].style.display=state;
   INFOCHG.rows[INFOCHG.rows.length -1].style.display=state;
 }    
</SCRIPT>

<h3 align="center">Gasto Extra<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_maint_extrachg.jsp, EDL0158"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0158">
  
  
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="OPT" VALUE="3">
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%=row%>">
              
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="25%" > 
              <div align="left"> 
                <input type="text" name="E01DLSACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="25%" > 
              <div align="right"><b>Secuencia :</b></div>
            </td>
            <td nowrap width="25%" > 
              <div align="left"> 
                <input type="text" name="E01DLSSEQ" size="12" maxlength="12" value="<%= msgLN.getE01DLSSEQ().trim()%>" readonly>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n Básica</h4>
  <table class="tableinfo">
    <tr > 
      <td> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0" id="INFOCHG">
          <tr id="trdark"> 
            <td width="30%"> 
              <div align="right">Tipo Gasto :</div>
            </td>
            <td > 
              <input type="radio" name="E01DLSTYP" value="5" <% if (!(msgLN.getE01DLSTYP().equals("4"))) out.print("checked"); %> onclick="showTypeChg(this.value)" readonly>Cargo   
              <input type="radio" name="E01DLSTYP" value="4" <% if (msgLN.getE01DLSTYP().equals("4")) out.print("checked"); %> onclick="showTypeChg(this.value)" readonly>I.V.A
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="30%"> 
              <div align="right">Fecha Valor :</div>
            </td>
            <td > 
              <input type="text" name="E01DLSPD1" size="2" maxlength="2" value="<%= msgLN.getE01DLSPD1().trim()%>" readonly>
              <input type="text" name="E01DLSPD2" size="2" maxlength="2" value="<%= msgLN.getE01DLSPD2().trim()%>" readonly>
              <input type="text" name="E01DLSPD3" size="2" maxlength="2" value="<%= msgLN.getE01DLSPD3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark">  
            <td width="30%"> 
              <div align="right">Tasa de Cambio :</div>
            </td>
            <td > 
              <input type="text" name="E01DLSAMT" size="17" maxlength="17" value="<%= msgLN.getE01DLSAMT()%>" onkeypress="enterDecimal()" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="30%"> 
              <div align="right">Concepto :</div>
            </td>
            <td> 
              <input type="text" name="E01DLSNAR" size="30" maxlength="30" value="<%= msgLN.getE01DLSNAR().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="30%"> 
              <div align="right">Cuenta Debito :</div>
            </td>
            <td> 
               <table class="tableinfo" style="filter:''">
          			<tr id="trdark"> 
            			<td width="5%"> 
              				<div align="center">Banco</div>
            			</td>
            			<td width="13%" > 
              				<div align="center">Sucursal</div>
            			</td>
            			<td width="12%" > 
              				<div align="center">Moneda</div>
            			</td>
            			<td width="25%" > 
              				
                    <div align="center">Cuenta Contable</div>
            			</td>
            			<td width="25%" > 
              				<div align="center">Referencia</div>
            			</td>
          			</tr>
          			<tr id="trclear"> 
            			<td width="5%" > 
              				<div align="center"> 
                			<input type="text" name="E01DLSOBK" size="2" maxlength="2" value="<%= msgLN.getE01DLSOBK()%>">
              				</div>
            			</td>
            			<td width="13%"> 
              				<div align="center"> 
                			<input type="text" name="E01DLSOBR" size="3" maxlength="3" value="<%= msgLN.getE01DLSOBR()%>"
							oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01DLSOBK.value,'','','','')">
              				</div>
            			</td>
            			<td width="12%" > 
              				<div align="center">
                			<input type="text" name="E01DLSOCY" size="3" maxlength="3" value="<%= msgLN.getE01DLSOCY()%>"
                			oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01DLSOBK.value,'','','','')"> 
              				</div>
            			</td>
            			<td width="25%" > 
              				<div align="center"> 
                			<input type="text" name="E01DLSOGL" size="16" maxlength="16" value="<%= msgLN.getE01DLSOGL() %>"
                 			oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01DLSOBK.value,document.forms[0].E01DLSOCY.value,'','','')">
               				</div>
            			</td>
            			<td width="25%" > 
              				<div align="center"> 
                			<input type="text" name="E01DLSOAC" size="16" maxlength="16" value="<%= msgLN.getE01DLSOAC()%>"
                 			oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01DLSOBK.value,'','','','RT')">
               				</div>
            			</td>
          			</tr>
        		</table>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="30%"> 
              <div align="right">Porcentaje I.V.A :</div>
            </td>
            <td > 
              <input type="text" name="E01DLSIVP" size="8" maxlength="8" value="<%= msgLN.getE01DLSIVP() %>" onKeypress="enterInteger()" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="30%"> 
              <div align="right">Monto Base :</div>
            </td>
            <td > 
              <input type="text" name="E01DLSIVB" size="17" maxlength="17" value="<%= msgLN.getE01DLSIVB()%>" onKeypress="enterInteger()" readonly>
            </td>
          </tr>
        </table>                       
      </td>
    </tr>
  </table>
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</p>
  <SCRIPT LANGUAGE="JavaScript">
    showTypeChg("<%= msgLN.getE01DLSTYP()%>"); 
  </SCRIPT>
</form>
</body>
</html>
