<html>
<head>
<title>Transacciones de Prestamos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<%@ page import = "datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<jsp:useBean id= "lnTransac" class= "datapro.eibs.beans.EDL015203Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
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
    if (document.forms[0][sfield].value=="0") afval=bfval+trval;
    else if (document.forms[0][sfield].value=="5") afval=bfval-trval;
    else afval=bfval;
    document.forms[0][afield].value = formatCCY(""+afval);
  } 

 function Recalculate(){
    UpdateField('E03DEAPRI','E03TRNPRI','AFTERPRI','E03TRNPRF',false);
    <% if(lnTransac.getH03FLGWK3().equals("R")){%>
    UpdateField('E03DEAREA','E03TRNREA','AFTERREA','E03TRNREF',false);
    <% } %>
    UpdateField('E03DEAINT','E03TRNINT','AFTERINT','E03TRNINF',false);
    UpdateField('E03DEAINT','E03TRNAIN','AFTERINT','E03TRNAIF',true);
    UpdateField('E03DEAMOR','E03TRNMOR','AFTERMOR','E03TRNMOF',false);
    UpdateField('E03DEAMOR','E03TRNAMO','AFTERMOR','E03TRNAMF',true);
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
    try{
     total=total+parseFloat(formatFloat(document.forms[0].AFTERINT.value));}
    catch(e){
    }
    try{
     total=total+parseFloat(formatFloat(document.forms[0].AFTERMOR.value));}
    catch(e){
    }
   document.forms[0].AFTERTOT.value=formatCCY(""+total);
   
  }
</SCRIPT>
<SCRIPT Language="Javascript">

   builtNewMenu(ln_t_m_opt);
   initMenu();


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

<SCRIPT LANGUAGE="JavaScript">
builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
</SCRIPT>

<h3 align="center">Transacciones de Pr&eacute;stamos (F.E.C.I)<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_transac.jsp, EDL0150"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0152">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="16">
   <INPUT TYPE=HIDDEN NAME="E03DEABNK" VALUE="<%= lnTransac.getE03DEABNK().trim()%>">
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
                <input type="text" name="E03DEACUN" size="9" maxlength="9" value="<%= lnTransac.getE03DEACUN().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E03CUSNA1" size="45" maxlength="45" value="<%= lnTransac.getE03CUSNA1().trim()%>" readonly>
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E03DEAACC" size="12" maxlength="12" value="<%= lnTransac.getE03DEAACC().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E03DEACCY" size="3" maxlength="3" value="<%= lnTransac.getE03DEACCY().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E03DEAPRO" size="4" maxlength="4" value="<%= lnTransac.getE03DEAPRO().trim()%>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n del Préstamo</h4>
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
              <DIV align="center"><B>Antes</B></DIV>
            </TD>
            <TD nowrap width="40%" id="trdark"> 
              <DIV align="center"><B>Después</B></DIV>
            </TD>
          </TR>
          <TR> 
            <TD nowrap id="trdark"> 
              <DIV align="right">F.E.C.I :</DIV>
            </TD>
            <TD nowrap align="center"> 
              <INPUT id="txtright" type="text" readonly name="E03DEAPRI" size="15" maxlength="13" value="<%= Util.formatCCY(lnTransac.getE03DEAPRI())%>" onkeypress="enterDecimal()">
            </TD>
            <TD nowrap align="center"> 
              <INPUT id="txtright" type="text" readonly name="AFTERPRI" size="15" maxlength="13" value="" onkeypress="enterDecimal()">
            </TD>
          </TR>
          <TR> 
            <TD nowrap id="trdark"> 
              <DIV align="right">F.E.C.I Vencido :</DIV>
            </TD>
            <TD nowrap align="center"> 
              <INPUT id="txtright" type="text" readonly name="E03DEAINT" size="15" maxlength="13" value="<%= Util.formatCCY(lnTransac.getE03DEAINT())%>" onkeypress="enterDecimal()">
            </TD>
            <TD nowrap align="center"> 
              <INPUT id="txtright" type="text" readonly name="AFTERINT" size="15" maxlength="13" value="" onkeypress="enterDecimal()">
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
    <tr > 
      <td> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td width="40%"> 
              <div align="right">F.E.C.I :</div>
            </td>
            <td width="60%"> 
              <input type="text" name="E03TRNPRI" size="15" maxlength="15" value="<%= lnTransac.getE03TRNPRI().trim()%>" onKeypress="enterDecimal()"
              onChange="changeField('CE03TRNPFL')" onblur="verifyNum(this)">
              <input type="hidden" name="E03TRNPRF" value="<%= lnTransac.getE03TRNPRF()%>">
              <input type="radio" name="CE03TRNPFL" value="0" 
              onClick="document.forms[0].E03TRNPRF.value='0';Recalculate();" 
			  <%if(lnTransac.getE03TRNPRF().equals("0")) out.print("checked");%>>
              Aumento 
              <input type="radio" name="CE03TRNPFL" value="5" 
              onClick="document.forms[0].E03TRNPRF.value='5';Recalculate();" 
			  <%if(lnTransac.getE03TRNPRF().equals("5")) out.print("checked");%>>
              Disminuci&oacute;n</td>
          </tr>
          <tr id="<% if(lnTransac.getH03FLGWK3().equals("R")) out.print("trdark"); else out.print("trclear"); %>"> 
            <td width="40%" height="27"> 
              <div align="right">F.E.C.I Vencido :</div>
            </td>
            <td width="60%" height="27"> 
              <input type="text" name="E03TRNINT" size="15" maxlength="15" value="<%= lnTransac.getE03TRNINT().trim()%>" onKeypress="enterDecimal()"
              onChange="changeField('CE03TRNINF')" onblur="verifyNum(this)">
              <input type="hidden" name="E03TRNINF" value="<%= lnTransac.getE03TRNINF()%>">
              <input type="radio" name="CE03TRNINF" value="0"
			   onClick="document.forms[0].E03TRNINF.value='0';Recalculate();"
			   <%if(lnTransac.getE03TRNINF().equals("0")) out.print("checked");%>>
			   Aumento
			   <input type="radio" name="CE03TRNINF" value="5"
			   onClick="document.forms[0].E03TRNINF.value='5';Recalculate();"
			   <%if(lnTransac.getE03TRNINF().equals("5")) out.print("checked");%>>
               Disminuci&oacute;n</td>
          </tr>
        </table>
        
      </td>
    </tr>
  </table>
  <SCRIPT language="JavaScript">
      Recalculate();
  </SCRIPT>
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</p>
</form>
</body>
</html>
