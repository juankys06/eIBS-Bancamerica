<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Estado de Cuentas 
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>

<jsp:useBean id= "cifList" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id="lcBalances" class="datapro.eibs.beans.ESD081702Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


<BODY>


<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

function doPrint(){
	if(!window.print){
       var msg ="Debe actualizar su navegador para imprimir";
	   alert(msg);
	   return;}

    window.focus();
	window.print();

	return;
}

function checkSubmit(){
 document.forms[0].submit();
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

<h3 align="center">Estado de Cuentas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="lc_list_print_fp.jsp,ESD0817"> 
  </h3>
  <hr size="4">
  <FORM>
<%
	if ( cifList.getNoResult() ) {%>
	<TABLE class="tbenter" width=100% height=60%>
   	<TR>
      <TD> 
         <h4 style="text-align:center">No hay resultados que correspondan a su criterio de búsqueda</h4>
      </TD>
    </TR>
 	</TABLE>   		
<%	}
	else {
%>
  
  <table class="tableinfo">
    <tr > 
      <td > 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td  width="30%" height="15" nowrap> 
              <div align="right"><b>Nombre y Direcci&oacute;n :</b></div>
            </td>
            <td  width="32%" height="15"> 
              <div align="left"></div>
              <%= lcBalances.getE02CUMMA1().trim()%></td>
            <td  width="25%" height="15"> 
              <div align="right"><b>Boleta :</b></div>
            </td>
            <td  width="13%" height="15" nowrap> 
              <div align="right"><%= userPO.getIdentifier().trim()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td  width="30%"> 
              <div align="right"></div>
            </td>
            <td  width="28%" height="15"> 
              <div align="left"><%= lcBalances.getE02CUMMA2().trim()%></div>
            </td>
            <td  width="25%" nowrap> 
              <div align="right"><b>Monto</b> <%= lcBalances.getE02CIFCCY().trim()%> 
                <b>Original :</b></div>
            </td>
            <td  width="13%"> 
              <div align="right"><%= Util.fcolorCCY(lcBalances.getE02AMOUN1().trim())%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td  width="30%" nowrap> 
              <div align="right"><b> </b></div>
            </td>
            <td  width="28%" nowrap> 
              <div align="left"><%= lcBalances.getE02CUMMA3().trim()%></div>
            </td>
            <td  width="25%" nowrap> 
              <div align="right"><b>Saldo :</b></div>
            </td>
            <td  width="15%"> 
              <div align="right"><%= Util.fcolorCCY(lcBalances.getE02AMOUN2().trim())%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td  width="30%" nowrap> 
              <div align="right"></div>
            </td>
            <td  width="28%"> 
              <div align="left"><%= lcBalances.getE02CUMMA4().trim()%></div>
            </td>
            <td  width="25%" nowrap> 
              <div align="right"><b>Saldo Negociado :</b></div>
            </td>
            <td  width="15%"> 
              <div align="right"><%= Util.fcolorCCY(lcBalances.getE02AMOUN3().trim())%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td  width="30%"> 
              <div align="right"><b>Fecha de Emisi&oacute;n :</b></div>
            </td>
            <td  width="32%" nowrap><%= Util.formatDate(lcBalances.getE02DATEA1(),lcBalances.getE02DATEA2(),lcBalances.getE02DATEA3())%></td>
            <td  width="25%" nowrap> 
              <div align="right"><b>Fecha de Expiraci&oacute;n :</b></div>
            </td>
            <td  width="15%"> 
              <div align="right"><%= Util.formatDate(lcBalances.getE02DATEB1(),lcBalances.getE02DATEB2(),lcBalances.getE02DATEB3())%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td  width="30%"> 
              <div align="right"><b>Facsimil :</b></div>
            </td>
            <td  width="32%" nowrap> 
              <div align="left"><%= lcBalances.getE02CUSFAX().trim()%></div>
            </td>
            <td  width="25%" nowrap height="20"> 
              <div align="right"><b>Correo Electr&oacute;nico :</b></div>
            </td>
            <td  width="15%" nowrap height="20"> 
              <div align="right"><a href="mailto:<%= lcBalances.getE02CUSIAD().trim()%>" target="body"><%= lcBalances.getE02CUSIAD().trim()%></a></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <TABLE  class="tableinfo">
    <TR id="trdark"> 
      <TH ALIGN=CENTER  >Fecha Proceso</TH>
      <TH ALIGN=CENTER  > Fecha Valor</TH>
      <TH ALIGN=CENTER  >TC</TH>
      <TH ALIGN=CENTER  >Descripci&oacute;n</TH>
      <TH ALIGN=CENTER  >D&eacute;bitos</TH>
      <TH ALIGN=CENTER  >Cr&eacute;ditos</TH>
      <TH ALIGN=CENTER  >Lote</TH>
    </TR>
    <%
                cifList.initRow();
                while (cifList.getNextRow()) {
                    if (cifList.getFlag().equals("")) {
                    		out.println(cifList.getRecord());
                    }
                }
              %> 
  </TABLE>

  
  <p>&nbsp;</p>
  <table width="100%">
    <tr> 
      <td width="20%"> 
        <div align="center"><b>Totales</b></div>
      </td>
      <td width="45%"><b>D&eacute;bitos</b> : <%= userPO.getHeader19().trim()%></td>
      <td width="35%"><b>Cr&eacute;ditos </b> : <%= userPO.getHeader20().trim()%></td>
    </tr>
  </table>
  

  <%
  }
%> 
  
</FORM>

</BODY>
</HTML>
