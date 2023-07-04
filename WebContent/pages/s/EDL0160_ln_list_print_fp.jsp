<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Impresión de Estado de Cuentas 
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "cifList" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id="stBalances" class="datapro.eibs.beans.EDL030002Message"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<BODY>


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

function doPrint(){
	if(!window.print){
       var msg ="Debe actualizar su navegador para imprimir";
	   alert(msg);
	   return;}

function checkSubmit(){
 document.forms[0].submit();
}
	
    window.focus();
	window.print();

	return;
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

<FORM>
<%
	if ( cifList.getNoResult() ) {
   		out.print("<center><h4>No hay resultados que correspondan a su criterio de búsqueda</h4></center>");
	}
	else {
%>
  <h3 align="center">Estado de Cuentas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ln_list_print_fp.jsp,EDL0160"> 
  </h3>
  <hr size="4">
  <br>
  <table class="tableinfo">
    <tr > 
      <td > 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td  width="32%" height="15" nowrap> 
              <div align="right"><b>Nombre y Direcci&oacute;n :</b></div>
            </td>
            <td  width="28%" height="15"> 
              <div align="left"><%= stBalances.getE02CUMMA1().trim()%></div>
            </td>
            <td  width="25%" height="15"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td  width="15%" height="15" nowrap> 
              <div align="right"><%= stBalances.getE02DEAACC().trim()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td  width="32%"> 
              <div align="right"></div>
            </td>
            <td  width="28%" nowrap> 
              <div align="left"><%= stBalances.getE02CUMMA2().trim()%></div>
            </td>
            <td  width="25%" nowrap> 
              <div align="right"><b>Saldo</b> <%= stBalances.getE02DEACCY().trim()%> 
                <b>Principal :</b></div>
            </td>
            <td  width="15%"> 
              <div align="right"><%= Util.fcolorCCY(stBalances.getE02DEAMEP().trim())%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td  width="32%" nowrap>&nbsp;</td>
            <td  width="28%"><%= stBalances.getE02CUMMA3().trim()%></td>
            <td  width="25%" nowrap> 
              <div align="right"><b>Saldo Inter&eacute;s : </b></div>
            </td>
            <td  width="15%"> 
              <div align="right"><%= Util.fcolorCCY(stBalances.getE02DEAMEI().trim())%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td  width="32%" nowrap>&nbsp;</td>
            <td  width="28%">&nbsp;</td>
            <td  width="25%" nowrap> 
              <div align="right"><b>Mora :</b></div>
            </td>
            <td  width="15%"> 
              <div align="right"><%= Util.fcolorCCY(stBalances.getE02DEAMEM().trim())%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td  width="32%" nowrap> 
              <div align="right"></div>
            </td>
            <td  width="28%"> 
              <div align="left"><%= stBalances.getE02CUMMA4().trim()%></div>
            </td>
            <td  width="25%" nowrap> 
              <div align="right"><b>Otros Cargos :</b></div>
            </td>
            <td  width="15%"> 
              <div align="right"><%= Util.fcolorCCY(stBalances.getE02DEAOTH().trim())%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td  width="32%" nowrap> 
              <div align="right"><b>Fecha de Apertura :</b></div>
            </td>
            <td  width="28%" nowrap> 
              <div align="left"><%= Util.formatDate(stBalances.getE02OPEND1(),stBalances.getE02OPEND2(),stBalances.getE02OPEND3())%></div>
            </td>
            <td  width="25%" nowrap> 
              <div align="right"><b>Saldo Total :</b></div>
            </td>
            <td  width="15%"> 
              <div align="right"><%= Util.fcolorCCY(stBalances.getE02TOTAMN().trim())%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td  width="32%"> 
              <div align="right"><b>Fecha de Vencimiento :</b></div>
            </td>
            <td  width="28%" nowrap> 
              <div align="left"><%= Util.formatDate(stBalances.getE02MATUR1(),stBalances.getE02MATUR2(),stBalances.getE02MATUR3())%></div>
            </td>
            <td  width="25%" nowrap> 
              <div align="right"><b>Tasa de Inter&eacute;s :</b></div>
            </td>
            <td  width="15%" nowrap> 
              <div align="right"><%= stBalances.getE02DEARTE().trim()%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td  width="32%" height="20"> 
              <div align="right"><b>Ultima Fecha de Renovaci&oacute;n :</b></div>
            </td>
            <td  width="28%" nowrap height="20"> 
              <div align="left"><%= Util.formatDate(stBalances.getE02LSTRD1(),stBalances.getE02LSTRD2(),stBalances.getE02LSTRD3())%></div>
            </td>
            <td  width="25%" nowrap height="20"> 
              <div align="right"><b>N&uacute;mero de Renovaciones :</b></div>
            </td>
            <td  width="15%" nowrap height="20"> 
              <div align="right"><%= stBalances.getE02DEARON().trim()%></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <TABLE class="tableinfo" >
    <TR> 
      <TH ALIGN=CENTER  >Fecha Proceso</TH>
      <TH ALIGN=CENTER  > Fecha Valor</TH>
      <TH ALIGN=CENTER  >TC</TH>
      <TH ALIGN=CENTER  >Descripci&oacute;n</TH>
      <TH ALIGN=CENTER  >Principal</TH>
      <TH ALIGN=CENTER  >&nbsp;</TH>
      <TH ALIGN=CENTER  >Intereses</TH>
      <TH ALIGN=CENTER  >&nbsp;</TH>
      <TH ALIGN=CENTER  >Mora</TH>
      <TH ALIGN=CENTER  >&nbsp;</TH>
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
      <td width="45%"><b>Principal</b> : <%= userPO.getHeader19().trim()%></td>
      <td width="35%"><b>Inter&eacute;s </b> : <%= userPO.getHeader20().trim()%></td>
    </tr>
  </table>
  <h4>&nbsp;</h4>
  <p>&nbsp;</p>
  <p align="left">&nbsp;</p>
  <p>&nbsp;</p>
  <p align=left>&nbsp; </p>

  <%
  }
%> 
  
</FORM>

</BODY>
</HTML>
