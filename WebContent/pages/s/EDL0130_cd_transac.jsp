<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Transacciones de Certificados</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<%@ page import = "datapro.eibs.master.Util" %>
<jsp:useBean id= "cdTransac" class= "datapro.eibs.beans.EDL013003Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<SCRIPT Language="Javascript">

 function UpdateField(bfield,trfield,afield,sfield,rep){
   var trval;
   var bfval;
   var afval=0.00;
    try{
     trval= parseFloat(formatFloat(document.forms[0][trfield].value));
     if (isNaN(trval)) trval=0.00;}
    catch(e){
     trval=0.00;
    }
    try{
     if (rep) bfval=parseFloat(formatFloat(document.forms[0][afield].value));
     else bfval=parseFloat(formatFloat(document.forms[0][bfield].value));}
    catch(e){
     bfval=0.00;
    }
    if (document.forms[0][sfield][0].checked) afval=bfval+trval;
    else if (document.forms[0][sfield][1].checked) afval=bfval-trval;
    else afval=bfval;
    document.forms[0][afield].value = formatCCY(""+afval);
  } 

 function Recalculate(){
    UpdateField('E03DEAMEP','E03TRNPRI','AFTERPRI','E03TRNPRF',false);
    <% if(cdTransac.getH03FLGWK3().equals("R")){%>
    UpdateField('E03DEAREA','E03TRNREA','AFTERREA','E03TRNREF',false);
    <% } %>
    UpdateField('E03DEAMEI','E03TRNINT','AFTERINT','E03TRNINF',false);
    UpdateField('E03DEAMEI','E03TRNADJ','AFTERINT','E03TRNADF',true);
    UpdateTotal();
 }
 
 function changeField(radio){
   var numval;
   var total;
   var newval; 
    if (!document.forms[0][radio][0].checked && !document.forms[0][radio][1].checked) {
      return;
    }
    Recalculate();
  }
  
  function verifyNum(elem){
   if (trim(elem.value)=="") elem.value="0.00";
  }
  
  function UpdateTotal(){
   
   var total;  
    try{
     total= parseFloat(formatFloat(document.forms[0].AFTERPRI.value));}
    catch(e){
     total=0.00;
    }
    <% if(cdTransac.getH03FLGWK3().equals("R")){%>
    try{
     total=total+parseFloat(formatFloat(document.forms[0].AFTERREA.value));}
    catch(e){
    }
    <% } %>
    try{
     total=total+parseFloat(formatFloat(document.forms[0].AFTERINT.value));}
    catch(e){
    }
    
   document.forms[0].AFTERTOT.value=formatCCY(""+total);
   
  }
</SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(cd_t_m_opt);
   initMenu();


</SCRIPT>


</head>

<body nowrap>
<SCRIPT LANGUAGE="JavaScript">
builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
</SCRIPT>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
    error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<h3 align="center">Transacciones de Certificados de Dep&oacute;sito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_transac.jsp, EDL0130"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="6">
   <INPUT TYPE=HIDDEN NAME="E03DEABNK" VALUE="<%= cdTransac.getE03DEABNK().trim()%>">
  <input type=HIDDEN name="E03DEAACD" value="<%= cdTransac.getE03DEAACD().trim()%>">
  <input type=HIDDEN name="E03DEABRN" value="<%= cdTransac.getE03DEABRN().trim()%>">
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left">
                <input type="text" name="E03DEACUN" size="9" maxlength="9" value="<%= cdTransac.getE03DEACUN().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text" name="E03CUSNA1" size="45" maxlength="45" value="<%= cdTransac.getE03CUSNA1().trim()%>" readonly>
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Certificado :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E03DEAACC" size="12" maxlength="12" value="<%= cdTransac.getE03DEAACC().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E03DEACCY" size="3" maxlength="3" value="<%= cdTransac.getE03DEACCY().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E03DEAPRO" size="4" maxlength="4" value="<%= cdTransac.getE03DEAPRO().trim()%>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n Financiera</h4>
  <TABLE class="tableinfo">
     <TBODY>
        <TR> 
      <TD nowrap> 
        <TABLE cellspacing="2" cellpadding="2" width="100%" border="0">
          <TBODY>
                    <TR> 
            <TD nowrap width="20%" id="trdark"> 
              <DIV align="center"></DIV>
            </TD>
            <TD nowrap width="40%" id="trdark"> 
              <DIV align="center"><B>Antes </B></DIV>
            </TD>
            <TD nowrap width="40%" id="trdark"> 
              <DIV align="center"><B>Despues </B></DIV>
            </TD>
          </TR>
          <TR> 
            <TD nowrap id="trdark"> 
              <DIV align="right">Saldo de Principal :</DIV>
            </TD>
            <TD nowrap align="center">
             <INPUT type="text" name="E03DEAMEP" id="txtright" size="23" maxlength="13" value="<%= Util.formatCCY(cdTransac.getE03DEAMEP())%>" readonly></TD>
            <TD nowrap align="center"> 
              <INPUT type="text" id="txtright" readonly name="AFTERPRI" size="23" maxlength="13" value="" onkeypress="enterDecimal()">
            </TD>
          </TR>
          <%if(cdTransac.getH03FLGWK3().equals("R")){%>
          <tr > 
            <td id="trdark"> 
              <div align="right">Saldo de Reajuste :</div>
            </td>
            <td nowrap align="center"> 
              <input type="text" id="txtright" name="E03DEAREA" size="23" maxlength="15" value="<%= cdTransac.getE03DEAREA().trim()%>" readonly>
            </td>
            <TD nowrap align="center"> 
              <INPUT type="text" id="txtright" readonly name="AFTERREA" size="23" maxlength="13" value="" onkeypress="enterDecimal()">
            </TD>
          </tr> 
           <%}%>
          <TR> 
            <TD nowrap id="trdark"> 
              <DIV align="right">Saldo de Interés :</DIV>
            </TD>
            <TD nowrap align="center">
            <INPUT type="text" name="E03DEAMEI" id="txtright" size="23" maxlength="13" value="<%= Util.formatCCY(cdTransac.getE03DEAMEI())%>" readonly></TD>
            <TD nowrap align="center"> 
              <INPUT id="txtright" type="text" readonly name="AFTERINT" size="23" maxlength="13" value="" onkeypress="enterDecimal()">
            </TD>
          </TR>
          <TR> 
            <TD nowrap id="trdark"> 
              <DIV align="right">Retenciones :</DIV>
            </TD>
            <TD nowrap align="center"><INPUT type="text" name="E03DEAWHL" id="txtright" size="23" maxlength="13" value="<%= Util.formatCCY(cdTransac.getE03DEAWHL())%>" readonly></TD>
            <TD nowrap align="center"></TD>
          </TR>
          <TR> 
            <TD nowrap id="trdark"> 
              <DIV align="right">Impuestos :</DIV>
            </TD>
            <TD nowrap align="center"><INPUT type="text" name="E03DEATAX" id="txtright" size="23" maxlength="13" value="<%= Util.formatCCY(cdTransac.getE03DEATAX())%>" readonly></TD>
            <TD nowrap align="center"></TD>
          </TR>
          <TR> 
            <TD nowrap id="trdark"> 
              <DIV align="right">Total :</DIV>
            </TD>
            <TD nowrap align="center"><INPUT type="text" name="E03DEATOT" id="txtright" size="23" maxlength="13" value="<%= Util.formatCCY(cdTransac.getE03DEATOT())%>" readonly></TD>
            <TD nowrap align="center"> 
              <INPUT id="txtright" type="text" readonly name="AFTERTOT" size="23" maxlength="13" value="" onkeypress="enterDecimal()">
            </TD>
          </TR>
          <TR>
          	<TD nowrap colspan="3"> 
              <DIV align="center">Fecha de Ultimo C&aacute;lculo : 
              <INPUT type="text" name="E03DEALC1" size="2" maxlength="2" value="<%= cdTransac.getE03DEALC1().trim()%>" readonly>
              <INPUT type="text" name="E03DEALC2" size="2" maxlength="2" value="<%= cdTransac.getE03DEALC2().trim()%>" readonly>
              <INPUT type="text" name="E03DEALC3" size="2" maxlength="2" value="<%= cdTransac.getE03DEALC3().trim()%>" readonly> </DIV>
            </TD>
           </TR>
        	</TBODY>
            </TABLE>
      	</TD>
    </TR>
  </TBODY>
</TABLE>
  
  <h4>Datos de la Transacci&oacute;n</h4>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td width="40%"> 
              <div align="right">Principal :</div>
            </td>
            <td width="60%"> 
              <input type="text" name="E03TRNPRI" size="15" maxlength="15" value="<%= cdTransac.getE03TRNPRI().trim()%>" onKeypress="enterDecimal()"
              onChange="changeField('E03TRNPRF')" onblur="verifyNum(this)">
              <input type="radio" name="E03TRNPRF" value="0" onClick="Recalculate();"
              <%if(cdTransac.getE03TRNPRF().equals("0")) out.print("checked");%>>
              Aumento 
              <input type="radio" name="E03TRNPRF" value="5" onClick="Recalculate();"
              <%if(cdTransac.getE03TRNPRF().equals("5")) out.print("checked");%>> Disminuci&oacute;n</td>
          </tr>
			<%if(cdTransac.getH03FLGWK3().equals("R")){%>
          <tr id="trclear">
            <td width="40%">
              <div align="right">Reajuste :</div>
            </td>
            <td width="60%">
              <input type="text" name="E03TRNREA" size="15" maxlength="15" value="<%= cdTransac.getE03TRNREA().trim()%>" onKeyPress="enterDecimal()"
              onChange="changeField('E03TRNREF')" onblur="verifyNum(this)">
              <input type="radio" name="E03TRNREF" value="0" onClick="Recalculate();"
		<%if(cdTransac.getE03TRNREF().equals("0")) out.print("checked");%>>
              Aumento 
              <input type="radio" name="E03TRNREF" value="5" onClick="Recalculate();"
	       <%if(cdTransac.getE03TRNREF().equals("5")) out.print("checked");%>>
              Disminuci&oacute;n </td>
          </tr> 
		  <%}%>	
          <tr id="<% if(cdTransac.getH03FLGWK3().equals("R")) out.print("trdark"); else out.print("trclear"); %>">             
            <td width="40%"> 
              <div align="right">Intereses :</div>
            </td>
            <td width="60%"> 
              <input type="text" name="E03TRNINT" size="15" maxlength="15" value="<%= cdTransac.getE03TRNINT().trim()%>" onKeypress="enterDecimal()"
              onChange="changeField('E03TRNINF')" onblur="verifyNum(this)">
              <input type="radio" name="E03TRNINF" value="0" onClick="Recalculate();"
  	       <%if(cdTransac.getE03TRNINF().equals("0")) out.print("checked");%>> Reversar <input type="radio" name="E03TRNINF" value="5" onClick="Recalculate();"
	       <%if(cdTransac.getE03TRNINF().equals("5")) out.print("checked");%>> Pagar </td>
          </tr> 
          <tr id="<% if(cdTransac.getH03FLGWK3().equals("R")) out.print("trclear"); else out.print("trdark"); %>">              
            <td width="40%" > 
              <div align="right">Ajustes de Intereses :</div>
            </td>
            <td width="60%" height="27"> 
              <input type="text" name="E03TRNADJ" size="15" maxlength="15" value="<%= cdTransac.getE03TRNADJ().trim()%>" onKeypress="enterDecimal()"
              onChange="changeField('E03TRNADF')" onblur="verifyNum(this)">
              <input type="radio" name="E03TRNADF" value="0" onClick="Recalculate();"
		<%if(cdTransac.getE03TRNADF().equals("0")) out.print("checked");%>>
              Aumento 
              <input type="radio" name="E03TRNADF" value="5" onClick="Recalculate();"
	       <%if(cdTransac.getE03TRNADF().equals("5")) out.print("checked");%>>
              Disminuci&oacute;n </td>
          </tr> 
          <tr id="<% if(cdTransac.getH03FLGWK3().equals("R")) out.print("trdark"); else out.print("trclear"); %>">             
            <td width="40%"> 
              <div align="right">Retenciones :</div>
            </td>
            <td width="60%"> 
              <input type="text" name="E03TRNWHL" size="15" maxlength="15" value="<%= cdTransac.getE03TRNWHL().trim()%>" onKeypress="enterDecimal()">
            </td>
          </tr> 
          <tr id="<% if(cdTransac.getH03FLGWK3().equals("R")) out.print("trclear"); else out.print("trdark"); %>">             
            <td width="40%"> 
              <div align="right">Impuestos :</div>
            </td>
            <td width="60%"> 
              <input type="text" name="E03TRNTAX" size="15" maxlength="15" value="<%= cdTransac.getE03TRNTAX().trim()%>" onKeypress="enterDecimal()">
            </td>
          </tr> 
          <tr id="<% if(cdTransac.getH03FLGWK3().equals("R")) out.print("trdark"); else out.print("trclear"); %>">             
            <td width="40%"> 
              <div align="right">Fecha Valor :</div>
            </td>
            <td width="60%"> 
              <input type="text" name="E03TRNVD1" size="2" maxlength="2" value="<%= cdTransac.getE03TRNVD1().trim()%>">
              <input type="text" name="E03TRNVD2" size="2" maxlength="2" value="<%= cdTransac.getE03TRNVD2().trim()%>">
              <input type="text" name="E03TRNVD3" size="2" maxlength="2" value="<%= cdTransac.getE03TRNVD3().trim()%>">
            </td>
          </tr> 
          <tr id="<% if(cdTransac.getH03FLGWK3().equals("R")) out.print("trclear"); else out.print("trdark"); %>">             
            <td width="40%"> 
              <div align="right">Tasa de Cambio :</div>
            </td>
            <td width="60%"> 
              <input type="text" name="E03DEAEXR" size="11" maxlength="11" value="<%= cdTransac.getE03DEAEXR().trim()%>" onKeypress="enterDecimal()">
            </td>
          </tr> 
          <tr id="<% if(cdTransac.getH03FLGWK3().equals("R")) out.print("trdark"); else out.print("trclear"); %>">             
            <td width="40%"> 
              <div align="right">Descripci&oacute;n :</div>
            </td>
            <td width="60%"> 
              <input type="text" name="E03DEANR1" size="30" maxlength="30" value="<%= cdTransac.getE03DEANR1().trim()%>">
            </td>
          </tr> 
          <tr id="<% if(cdTransac.getH03FLGWK3().equals("R")) out.print("trclear"); else out.print("trdark"); %>">             
            <td width="40%"> 
              <div align="right"></div>
            </td>
            <td width="60%"> 
              <input type="text" name="E03DEANR2" size="30" maxlength="30" value="<%= cdTransac.getE03DEANR2().trim()%>">
            </td>
          </tr>
        </table>
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr>
            <td align="center">&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td align="center"> <b>Forma de Pago</b> </td>
          </tr>
        </table>
        <table class="tableinfo" style="filter:''" >
          <tr id="trdark"> 
            <td  nowrap> 
              <div align="center">Concepto</div>
            </td>
            <td nowrap> 
              <div align="center">Banco</div>
            </td>
            <td nowrap> 
              <div align="center">Sucursal</div>
            </td>
            <td nowrap> 
              <div align="center">Moneda</div>
            </td>
            <td nowrap> 
              <div align="center">Referencia</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td  nowrap> 
              <div align="center" nowrap>
                <input type="text" name="E03TRNOPC" value="<%= cdTransac.getE03TRNOPC().trim()%>" size="3" maxlength="3">
                <input type=HIDDEN name="E03TRNGLN" value="<%= cdTransac.getE03TRNGLN().trim()%>">
                <input type="text" name="E03TRNCON" size="26" maxlength="25" readonly value="<%= cdTransac.getE03TRNCON().trim()%>"
                   oncontextmenu="showPopUp(conceptHelp,this.name,'','','E03TRNGLN','E03TRNOPC','<%= cdTransac.getE03DEAACD().trim()%>')">
              </div>
            </td>
            <td  nowrap> 
              <div align="center"> 
                <input type="text" name="E03TRNBNK" size="2" maxlength="2" value="<%= cdTransac.getE03TRNBNK().trim()%>">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E03TRNBRN" size="3" maxlength="3" value="<%= cdTransac.getE03TRNBRN().trim()%>"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E03DEABNK.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center">
                <input type="text" name="E03TRNCCY" size="3" maxlength="3" value="<%= cdTransac.getE03TRNCCY().trim()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E03DEABNK.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E03TRNACC" size="16" maxlength="13" value="<%= cdTransac.getE03TRNACC().trim()%>"
                  oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E03DEABNK.value,'','','','RT')">
               </div>
            </td>
          </tr>
        </table>
        
      </td>
    </tr>
  </table>
  <SCRIPT language="JavaScript">
      Recalculate();
  </SCRIPT>
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
</form>
</body>
</html>
