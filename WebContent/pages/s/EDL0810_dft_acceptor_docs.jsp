<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Mantenimiento Documentos Cobranza</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<jsp:useBean id="dftBasic" class="datapro.eibs.beans.EDL081001Message"  scope="session" />
<jsp:useBean id="dftAcceptor" class="datapro.eibs.beans.EDL081003Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<SCRIPT>
<%if (userPO.getPurpose().equals("MAINTENANCE")){%>
   builtNewMenu(ln_m_opt);
<%}%>
builtHPopUp();
function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
}
function genDoc() {
     var formLength= document.forms[0].elements.length;
     var ok = false;
     var elementName = "";	
     var element = "";
     for(n=0;n<formLength;n++)
     {
      	elementName = document.forms[0].elements[n].name;
	element = document.forms[0].elements[n];
      	if(elementName == 'AUTGEN' && element.checked) 
      	{
          if (element.value == "Y") {
            var max=tblAcceptant.rows.length;
     		for(i= 2; i < max; i++){
      			tblAcceptant.rows[i].style.display="";
     		}
          
            document.forms[0].DFTINIDOC.disabled = false;
            document.forms[0].DFTFINDOC.disabled = false;
            document.forms[0].DFTFRECUENCY.disabled = false;
            document.forms[0].DFTDTMAT1.disabled = false;
	     document.forms[0].DFTDTMAT2.disabled = false;
            document.forms[0].DFTDTMAT3.disabled = false;
            document.forms[0].DFTAMOUNT.disabled = false;

            document.forms[0].DFTAUTDEBACCT.disabled = false;

	     document.forms[0].DFTINIDOC.focus();
            document.forms[0].DFTINIDOC.select();
            
     
          } else {
            var max=tblAcceptant.rows.length;
     		for(i= 2; i < max; i++){
      			tblAcceptant.rows[i].style.display="none";
     		}
          
	     document.forms[0].DFTINIDOC.value = "";
            document.forms[0].DFTFINDOC.value = "";
            document.forms[0].DFTFRECUENCY.value = "";
            document.forms[0].DFTDTMAT1.value = "";
	     document.forms[0].DFTDTMAT2.value = "";
            document.forms[0].DFTDTMAT3.value = "";
            document.forms[0].DFTAMOUNT.value = "";

            document.forms[0].DFTAUTDEBACCT.value = "";

            document.forms[0].DFTINIDOC.disabled = true;
            document.forms[0].DFTFINDOC.disabled = true;
            document.forms[0].DFTFRECUENCY.disabled = true;
            document.forms[0].DFTDTMAT1.disabled = true;
	     document.forms[0].DFTDTMAT2.disabled = true;
            document.forms[0].DFTDTMAT3.disabled = true;
            document.forms[0].DFTAMOUNT.disabled = true;

            document.forms[0].DFTAUTDEBACCT.disabled = true;

          }
         break;
      	}
     }
}

function valida(opt){
	var docini = document.forms[0].DFTINIDOC.value;
	var docfin = document.forms[0].DFTFINDOC.value;
	var freq = document.forms[0].DFTFRECUENCY.value;
	if (opt == 'DOC') {
	   if (docfin < docini ) {
             alert("Final Document must be greater than initial document!");
	      document.forms[0].DFTFINDOC.focus();
             document.forms[0].DFTFINDOC.select();
	   } 
       }
       if (opt == 'FREC') {
          if (freq <= 0 ) {
              alert("Frecuency must be greater than 0");
	       document.forms[0].DFTFRECUENCY.focus();
              document.forms[0].DFTFRECUENCY.select();
  	   } 
       }
       if (opt == 'DATE') {
          if (document.forms[0].DFTDTMAT1.value == '' || document.forms[0].DFTDTMAT2.value == '' || document.forms[0].DFTDTMAT3.value == '') {
              alert("Please enter first maturity date");
	       document.forms[0].DFTDTMAT1.focus();
              document.forms[0].DFTDTMAT2.select();
   	   }
       }
}

</SCRIPT>
</head>
<body nowrap>
<% 
 String strBlank = "";
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 
<h3 align="center">Generación de Documentos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="dft_acceptor_docs, EDL0810"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0810" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1000">
  <INPUT TYPE=HIDDEN NAME="GRP" VALUE="4">
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
                <INPUT type="text" name="E01DEACUN" size="9" maxlength="9" value="<%= userPO.getHeader2()%>" readonly>
                <a href="javascript:GetCustomerDescId('E01DEACUN','E01CUSNA1','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a></div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <INPUT type="text" name="E01CUSNA1" size="45" maxlength="45" value="<%= userPO.getHeader3() %>" readonly>
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <INPUT type="text" name="E01DEAACC" size="12" maxlength="12" value="<%= userPO.getIdentifier() %>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <INPUT type="text" name="E01DEACCY" size="4" maxlength="3" value="<%= userPO.getCurrency() %>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <INPUT type="text" name="E01DEAPRO" size="4" maxlength="4" value="<%= userPO.getHeader1() %>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Aceptante</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap>
        <TABLE id="tblAcceptant" cellpadding="2" cellspacing="0" width="100%" border="0">
          <tr id="trdark"> 
            <TD nowrap width="19%"> 
              <div align="right">Identificación :</div>
            </TD>
            <TD nowrap colspan="2"> 
              <INPUT type="text" name="E03DLDIDA" size="18" maxlength="2" value="<%= dftAcceptor.getE03DLDIDA() %>" readonly>
            </TD>
            <TD nowrap width="13%"> 
              <div align="right">Nombre :</div>
            </TD>
            <TD nowrap width="56%"> 
              <INPUT type="text" name="E03DLDMA1" size="35" maxlength="35" value="<%= dftAcceptor.getE03DLDMA1() %>" readonly>
            </TD>
          </tr>
          <tr id="trclear"> 
            <TD nowrap width="19%"> 
              <div align="right">Generación Automática:</div>
            </TD>
            <TD nowrap colspan="4"> 
              <INPUT type="radio" name="AUTGEN" value="Y" onclick="genDoc()"> Si <INPUT type="radio" name="AUTGEN" value="N" onclick="genDoc()" checked>
              No</TD>
          </tr>
          <tr id="trdark"> 
            <TD nowrap width="19%"> 
              <div align="right">Documento Inicial:</div>
            </TD>
            <TD nowrap colspan="2"> 
              <INPUT size="6" type="text" name="DFTINIDOC" maxlength="6" >
            </TD>
            <TD nowrap width="13%" align="right">Documento Final:</TD>
            <TD nowrap width="56%"> 
              <INPUT size="6" type="text" name="DFTFINDOC" maxlength="6" onblur="valida()" >
            </TD>
          </tr>
          <tr id="trclear"> 
            <TD nowrap width="19%"> 
              <div align="right">Frecuencia :</div>
            </TD>
            <TD nowrap width="4%"> 
              <INPUT size="6" type="text" name="DFTFRECUENCY" maxlength="6" onblur="valida()" >
            </TD>
            <TD nowrap width="8%"> 
              <input type="radio" name="INDFREC" value="D" checked> Dia(s) </TD>
            <TD width="13%"></TD>
            <TD nowrap width="56%"></TD>
          </tr>
          <tr id="trdark"> 
            <TD nowrap width="19%"></TD>
            <TD nowrap width="4%">&nbsp; </TD>
            <TD nowrap width="8%"> 
              <input type="radio" name="INDFREC" value="M"> Meses(s)</TD>
            <TD nowrap width="13%"></TD>
            <TD nowrap width="56%"></TD>
          </tr>
          <tr id="trclear"> 
            <TD nowrap width="19%"></TD>
            <TD nowrap width="4%">&nbsp; </TD>
            <TD nowrap width="8%"> 
              <input type="radio" name="INDFREC" value="Y"> Año(s)</TD>
            <TD nowrap width="13%"></TD>
            <TD nowrap width="56%"></TD>
          </tr>
          <tr id="trdark"> 
            <TD nowrap width="19%"> 
              <div align="right">Fecha Primer Vcto:</div>
            </TD>
            <TD nowrap colspan="2"> 
              <INPUT size="2" type="text" name="DFTDTMAT1" maxlength="2" >
              <INPUT size="2" type="text" name="DFTDTMAT2" maxlength="2" >
              <INPUT size="2" type="text" name="DFTDTMAT3" maxlength="2" onblur="valida()" >
            </TD>
            <TD nowrap width="13%"></TD>
            <TD nowrap width="56%"></TD>
          </tr>
          <tr id="trclear"> 
            <TD nowrap width="19%"> 
              <div align="right">Monto :</div>
            </TD>
            <TD nowrap colspan="2"> 
              <INPUT size="20" type="text" name="DFTAMOUNT" maxlength="20" onkeypress="enterDecimal()">
            </TD>
            <TD nowrap width="13%" align="right"></TD>
            <TD nowrap width="56%">
            </TD>
          </tr>
          <tr id="trdark"> 
            <TD nowrap width="19%"> 
              <div align="right">Cuenta de Débito Automatica:</div>
            </TD>
            <TD nowrap colspan="2"> 
              <INPUT size="20" type="text" name="DFTAUTDEBACCT">
              <a href="javascript:GetAccount('DFTAUTDEBACCT','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
            </TD>
            <TD nowrap width="13%" align="right"></TD>
            <TD nowrap width="56%">
            </TD>
          </tr>
        </TABLE>
      </td>
    </tr>
  </table>
<p align="center">&nbsp; </p>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"></div>
      </td>
      <td width="34%"> 
        <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</p>
      </td>
      <td width="33%"> 
        <div align="center"></div>
      </td>
    </tr>
  </table>
  <p align="center">&nbsp; </p>
  </form>
<script>
var max=tblAcceptant.rows.length;
for(i= 2; i < max; i++){
   tblAcceptant.rows[i].style.display="none";
}
document.forms[0].DFTINIDOC.disabled = true;
document.forms[0].DFTFINDOC.disabled = true;
document.forms[0].DFTFRECUENCY.disabled = true;
document.forms[0].DFTDTMAT1.disabled = true;
document.forms[0].DFTDTMAT2.disabled = true;
document.forms[0].DFTDTMAT3.disabled = true;
document.forms[0].DFTAMOUNT.disabled = true;

document.forms[0].DFTAUTDEBACCT.disabled = true;

</script>
</body>
</html>
