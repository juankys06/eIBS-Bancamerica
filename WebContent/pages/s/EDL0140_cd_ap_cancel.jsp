<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Cancelación de Certificados</title>

<%@ page import = "datapro.eibs.master.Util" %>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<jsp:useBean id= "cdCancel" class= "datapro.eibs.beans.EDL013007Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT LANGUAGE="JavaScript">
   
function showCancelData(obj){
   if (obj.checked) CANCELDATA.style.display=""; else CANCELDATA.style.display="none";
}


 function UpdateField(bfield,trfield,afield,cancelfield){
   var trval;
   var bfval;
   var afval=0.00;
   var clval;
    try{
     trval= parseFloat(formatFloat(document.forms[0][trfield].value));
     if (isNaN(trval)) trval=0.00;}
    catch(e){
     trval=0.00;
    }
    try{
     bfval=parseFloat(formatFloat(document.forms[0][bfield].value));}
    catch(e){
     bfval=0.00;
    }
    if (cancelfield=="") { clval=0.00;}
    else {
    	try{
     		clval=parseFloat(formatFloat(document.forms[0][cancelfield].value));}
    	catch(e){
     		clval=0.00;
    	}
    }
    afval=bfval-trval - clval;
    document.forms[0][afield].value = formatCCY(""+afval);
  } 

 function Recalculate(){
    UpdateField('E07DEAMEP','E07TRNPRI','AFTERPRI','');
    <% if(cdCancel.getH07FLGWK3().equals("R")){%>
      UpdateField('E07DEAREA','E07TRNREA','AFTERREA','E07PENREA');
    <% } %>
    UpdateField('E07DEAMEI','E07TRNINT','AFTERINT','E07PENINT');
    UpdateField('E07DEAWHL','E07TRNWHL','AFTERWHL','');  
    UpdateTotal();
 }
  

  
  function UpdateTotal(){
   
   var total;
   var totalCancel;
     
    try{
     total= parseFloat(formatFloat(document.forms[0].AFTERPRI.value));}
    catch(e){
     total=0.00;
    }
    try{
     totalCancel= parseFloat(formatFloat(document.forms[0].E07PENINT.value));}
    catch(e){
     totalCancel=0.00;
    }
    <% if(cdCancel.getH07FLGWK3().equals("R")){%>
    try{
     total=total+parseFloat(formatFloat(document.forms[0].AFTERREA.value));}
    catch(e){
    }
    try{
     totalCancel=totalCancel+parseFloat(formatFloat(document.forms[0].E07PENREA.value));}
    catch(e){
    }
    <% } %>
    try{
     total=total+parseFloat(formatFloat(document.forms[0].AFTERINT.value));}
    catch(e){
    }
    try{
     total=total+parseFloat(formatFloat(document.forms[0].AFTERWHL.value));}
    catch(e){
    }
    
   document.forms[0].AFTERTOT.value=formatCCY(""+total);
   document.forms[0].E07PENTOT.value=formatCCY(""+totalCancel);
  }   
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
%> 
<H3 align="center">Pago/Cancelaci&oacute;n de Certificados de Dep&oacute;sito <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_cancel.jsp, EDL0130" width="35" height="35"> </H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="12">
  <INPUT TYPE=HIDDEN NAME="E07DEABNK" VALUE="<%= cdCancel.getE07DEABNK().trim()%>">
  <input type=HIDDEN name="E07DEAACD" value="<%= cdCancel.getE07DEAACD().trim()%>">
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
                <input type="text" readonly size="9" maxlength="9" name="E07DEACUN" value="<%= cdCancel.getE07DEACUN().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text" readonly size="45" maxlength="45" name="E07CUSNA1" value="<%= cdCancel.getE07CUSNA1().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Certificado :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" readonly size="12" maxlength="12" name="E07DEAACC" value="<%= cdCancel.getE07DEAACC().trim()%>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E07DEACCY" size="3" maxlength="3" value="<%= cdCancel.getE07DEACCY().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" readonly size="4" maxlength="4" name="E07DEAPRO" value="<%= cdCancel.getE07DEAPRO().trim()%>">
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
             <INPUT type="text" name="E07DEAMEP" id="txtright" size="15" maxlength="13" value="<%= Util.formatCCY(cdCancel.getE07DEAMEP())%>" readonly></TD>
            <TD nowrap align="center"> 
              <INPUT type="text" id="txtright" readonly name="AFTERPRI" size="15" maxlength="13" value="" onkeypress="enterDecimal()">
            </TD>
          </TR>
          <%if(cdCancel.getH07FLGWK3().equals("R")){%>
          <tr > 
            <td id="trdark"> 
              <div align="right">Saldo de Reajuste :</div>
            </td>
            <td nowrap align="center"> 
              <input type="text" id="txtright" name="E07DEAREA" size="15" maxlength="15" value="<%= cdCancel.getE07DEAREA().trim()%>" readonly>
            </td>
            <TD nowrap align="center"> 
              <INPUT type="text" id="txtright" readonly name="AFTERREA" size="15" maxlength="13" value="" onkeypress="enterDecimal()">
            </TD>
          </tr> 
           <%}%>
          <TR> 
            <TD nowrap id="trdark"> 
              <DIV align="right">Saldo de Interés :</DIV>
            </TD>
            <TD nowrap align="center">
            <INPUT type="text" name="E07DEAMEI" id="txtright" size="15" maxlength="13" value="<%= Util.formatCCY(cdCancel.getE07DEAMEI())%>" readonly></TD>
            <TD nowrap align="center"> 
              <INPUT id="txtright" type="text" readonly name="AFTERINT" size="15" maxlength="13" value="" onkeypress="enterDecimal()">
            </TD>
          </TR>
          <TR> 
            <TD nowrap id="trdark"> 
              <DIV align="right">Impuestos :</DIV>
            </TD>
            <TD nowrap align="center">
              <INPUT type="text" name="E07DEAWHL" id="txtright" size="15" maxlength="13" value="<%= Util.formatCCY(cdCancel.getE07DEAWHL())%>" readonly></TD>
            <TD nowrap align="center">
             <INPUT id="txtright" type="text" readonly name="AFTERWHL" size="15" maxlength="13" value="" onkeypress="enterDecimal()">
            </TD>
          </TR>
          <TR> 
            <TD nowrap id="trdark"> 
              <DIV align="right">Total :</DIV>
            </TD>
            <TD nowrap align="center"><INPUT type="text" name="E07DEATOT" id="txtright" size="15" maxlength="13" value="<%= Util.formatCCY(cdCancel.getE07DEATOT())%>" readonly></TD>
            <TD nowrap align="center"> 
              <INPUT id="txtright" type="text" readonly name="AFTERTOT" size="15" maxlength="13" value="" onkeypress="enterDecimal()">
            </TD>
          </TR>
          
        </TBODY>
        </TABLE>
        <TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
           <TR id=trdark >
             <TD nowrap> 
              <DIV align="right">Fecha de Ultimo C&aacute;lculo :</DIV>
             </TD>
             <TD nowrap>  
              <INPUT type="text" name="E07DEALC1" size="2" maxlength="2" value="<%= cdCancel.getE07DEALC1().trim()%>" readonly>
              <INPUT type="text" name="E07DEALC2" size="2" maxlength="2" value="<%= cdCancel.getE07DEALC2().trim()%>" readonly>
              <INPUT type="text" name="E07DEALC3" size="2" maxlength="2" value="<%= cdCancel.getE07DEALC3().trim()%>" readonly> 
             </TD>
             <TD nowrap> 
              <DIV align="right">Tasa Interés :</DIV>
             </TD>
             <TD nowrap>  
              <INPUT type="text" name="E07DEARTE" size="11" maxlength="11" value="<%= cdCancel.getE07DEARTE().trim()%>" readonly>
             </TD>
             <TD nowrap> 
              <DIV align="right">Días Interés :</DIV>
             </TD>
             <TD nowrap>  
              <INPUT type="text" name="E07INTDYS" size="3" maxlength="3" value="<%= cdCancel.getE07INTDYS().trim()%>" readonly>
             </TD>
           </TR>
        </TABLE>
      </TD>
    </TR>
  </TBODY>
</TABLE>
<p>
<table class="tbenter">
<tr>
  <TD>   
   <b>Datos de la Transacci&oacute;n</b>
  </TD>
  <TD>
   <b>PreCancelar </b><input type="checkbox" name="PRECANCEL" value="" onclick="showCancelData(this)" disable>    
  </TD>
  </tr>
</table>
</p> 
<table class="tbenter" cellpadding=3>
<tr>
 <TD valign=top>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="40%"> 
              <div align="right">Principal :</div>
            </td>
            <td nowrap width="60%"> 
              <input type="text" name="E07TRNPRI" size="15" maxlength="15" value="<%= cdCancel.getE07TRNPRI().trim()%>" readonly>
            </td>
          </tr>
          <%if(cdCancel.getH07FLGWK3().equals("R")){%>
          <tr id="trclear">
            <td nowrap width="40%">
              <div align="right">Reajuste :</div>
            </td>
            <td nowrap width="60%">
              <input type="text" name="E07TRNREA" size="15" maxlength="15" value="<%= cdCancel.getE07TRNREA().trim()%>" readonly>
            </td>
          </tr>
		  <%}%>
          <tr id="<% if(cdCancel.getH07FLGWK3().equals("R")) out.print("trdark"); else out.print("trclear"); %>"> 
            <td nowrap width="40%"> 
              <div align="right">Intereses :</div>
            </td>
            <td nowrap width="60%"> 
              <input type="text" name="E07TRNINT" size="15" maxlength="15" value="<%= cdCancel.getE07TRNINT().trim()%>" readonly>
            </td>
          </tr>
          <tr id="<% if(cdCancel.getH07FLGWK3().equals("R")) out.print("trclear"); else out.print("trdark"); %>"> 
            <td nowrap width="40%"> 
              <div align="right">Impuestos :</div>
            </td>
            <td nowrap width="60%"> 
              <input type="text" name="E07TRNWHL" size="15" maxlength="15" value="<%= cdCancel.getE07TRNWHL().trim()%>" readonly>
            </td>
          </tr>
          <tr id="<% if(cdCancel.getH07FLGWK3().equals("R")) out.print("trdark"); else out.print("trclear"); %>"> 
            <td nowrap width="40%"> 
              <div align="right">Monto a Pagar :</div>
            </td>
            <td nowrap width="60%"> 
              <input type="text" name="E07TRNTOT" size="15" maxlength="15" value="<%= cdCancel.getE07TRNTOT().trim()%>" readonly>
            </td>
          </tr>
        </table>
       </td>
      </tr>
    </table>
  </TD>
  <TD valign=top>
    <div id="CANCELDATA" style="display:none">
    <table class="tableinfo">
     <tr > 
      <td nowrap>    
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="39%"> 
              <div align="right">Tasa de Penalidad :</div>
            </td>
            <td nowrap width="61%">
              <input type="text" name="E07PENRTE" size="11" maxlength="11" value="<%= cdCancel.getE07PENRTE().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="39%">
              <div align="right">N&uacute;mero de D&iacute;as :</div>
            </td>
            <td nowrap width="61%">
              <input type="text" name="E07PENDYS" size="3" maxlength="3" value="<%= cdCancel.getE07PENDYS().trim()%>" readonly>
            </td>
          </tr>
          <%if(cdCancel.getH07FLGWK3().equals("R")){%>
          <tr id="trdark"> 
            <td nowrap width="39%"> 
              <div align="right">Reajuste :</div>
            </td>
            <td nowrap width="61%">
              <input type="text" name="E07PENREA" size="15" maxlength="15" value="<%= cdCancel.getE07PENREA().trim()%>" readonly>
            </td>
          </tr>
          <% } %>
          <tr id="<% if(cdCancel.getH07FLGWK3().equals("R")) out.print("trclear"); else out.print("trdark"); %>"> 
            <td nowrap width="39%"> 
              <div align="right">Interes :</div>
            </td>
            <td nowrap width="61%">
              <input type="text" name="E07PENINT" size="15" maxlength="15" value="<%= cdCancel.getE07PENINT().trim()%>" readonly>
            </td>
          </tr>
          <tr id="<% if(cdCancel.getH07FLGWK3().equals("R")) out.print("trdark"); else out.print("trclear"); %>"> 
            <td nowrap width="39%"> 
              <div align="right">Total :</div>
            </td>
            <td nowrap width="61%">
              <input type="text" readonly name="E07PENTOT" size="15" maxlength="15" value="<%= cdCancel.getE07PENTOT().trim()%>">
            </td>
          </tr>
        </table>
                
      	</td>
      </tr>
  	</table>
    </div>
  </TD>
 </tr>
</table>
  
<h4>Cuenta Pago</h4>
<table class="tableinfo">
    <tr> 
      <td nowrap >
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap  > 
              <div align="right">Descripción :</div>
            </td>
            <td nowrap colspan=3> 
              <input type="text" size="31" readonly maxlength="30" readonly name="E07DEANR1" value="<%= cdCancel.getE07DEANR1().trim()%>">                
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap  > 
              <div align="right">Fecha Valor :</div>
            </td>
            <td nowrap  > 
              <INPUT type="text" name="E07TRNVD1" size="2" maxlength="2" value="<%= cdCancel.getE07TRNVD1().trim()%>" readonly>
              <INPUT type="text" name="E07TRNVD2" size="2" maxlength="2" value="<%= cdCancel.getE07TRNVD2().trim()%>" readonly>
              <INPUT type="text" name="E07TRNVD3" size="2" maxlength="2" value="<%= cdCancel.getE07TRNVD3().trim()%>" readonly>
            <td nowrap  > 
              <div align="right">Tasa Cambio :</div>
            </td>
            <td nowrap  > 
              <INPUT type="text" name="E07DEAEXR" size="11" maxlength="11" value="<%= cdCancel.getE07DEAEXR().trim()%>" readonly>              
            </td>
          </tr>
        </table>
        <table cellspacing=2 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap  > 
              <div align="center">Concepto</div>
            </td>
            <td nowrap  > 
              <div align="center">Banco</div>
            </td>
            <td nowrap  > 
              <div align="center">Sucursal</div>
            </td>
            <td nowrap > 
              <div align="center">Moneda</div>
            </td>
            <td nowrap > 
              <div align="center">Referencia</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap  > 
              <div align="center" nowrap> 
                <input type=text name="E07TRNOPC" value="<%= cdCancel.getE07TRNOPC().trim()%>" size="3" maxlength="3">
                <input type=HIDDEN name="E07TRNGLN" value="<%= cdCancel.getE07TRNGLN().trim()%>">
                <input type="text" size="26" maxlength="25" readonly name="E07TRNCON" value="<%= cdCancel.getE07TRNCON().trim()%>" readonly>
              </div>
            </td>
            <td nowrap  > 
              <div align="center"> 
                <input type="text" size="2" maxlength="2" value="<%= cdCancel.getE07TRNBNK().trim()%>" name="E07TRNBNK" readonly>
              </div>
            </td>
            <td nowrap  "> 
              <div align="center"> 
                <input type="text" size="3" maxlength="3" value="<%= cdCancel.getE07TRNBRN().trim()%>" name="E07TRNBRN" readonly>
              </div>
            </td>
            <td nowrap   > 
              <div align="center"> 
                <input type="text" size="3" maxlength="3" name="E07TRNCCY" value="<%= cdCancel.getE07TRNCCY().trim()%>" readonly>
              </div>
            </td>
            <td nowrap   > 
              <div align="center"> 
                <input type="text" size="16" maxlength="13" value="<%= cdCancel.getE07TRNACC().trim()%>" name="E07TRNACC" readonly>
               </div>
            </td>
          </tr>
        </table>
        
      </td>
    </tr>
  </table>
  <SCRIPT language="JavaScript">
      Recalculate();
      var cancelTot=<%= cdCancel.getE07PENTOT().trim()%>;
      if (cancelTot > 0) document.forms[0].PRECANCEL.click();
  </SCRIPT>
 </form>
</body>
</html>
