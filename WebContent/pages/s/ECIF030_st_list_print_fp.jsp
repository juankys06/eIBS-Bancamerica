<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Lista de Clientes
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage" scope="session" />

<jsp:useBean id= "cifList" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id="stBalances" class="datapro.eibs.beans.ECIF03002Message"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id="interestTable" class="java.util.Hashtable"  scope="session" />

<BODY>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

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
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 

<FORM >
<%
	if ( cifList.getNoResult() ) {
   		out.print("<center><h4>No hay resultados que correspondan a su criterio de búsqueda</h4></center>");
	}
	else {
%>
  <h3 align="center">Estado de Cuentas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="st_list_print_fp.jsp,ECIF030"> 
  </h3>
  <hr size="4">
  <p>&nbsp;</p>
  <table class="tableinfo">
    <tr > 
      <td height="155" > 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td  width="16%" height="25" nowrap > 
              <div align="right"><b>Cuenta : </b></div>
            </td>
            <td  width="38%" height="25" nowrap> <%= userPO.getIdentifier().trim()%></td>
            <td  width="1%" height="25" nowrap>&nbsp; </td>
            <td  width="32%" height="25" nowrap> 
              <div align="right"><b>Saldo</b> <%= stBalances.getE02ACMCCY().trim()%> 
                <b>en Libros : </b></div>
            </td>
            <td  width="13%" height="25" nowrap> 
              <div align="right"><%= Util.fcolorCCY(stBalances.getE02ACMMGB().trim())%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td  width="16%" height="31" nowrap> 
              <div align="right"><b>Nombre : </b></div>
            </td>
            <td  width="38%" height="31" nowrap><%= stBalances.getE02CUSNA1().trim()%> 
            </td>
            <td  width="1%" height="31">&nbsp;</td>
            <td  width="32%" height="31" nowrap> 
              <div align="right"><b>Monto de Diferido : </b></div>
            </td>
            <td  width="13%" height="31" nowrap> 
              <div align="right"><%= Util.fcolorCCY(stBalances.getE02UNCBAL().trim())%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td  width="16%"> 
              <div align="right"><b>Direcci&oacute;n : </b></div>
            </td>
            <td  width="38%" nowrap><%= stBalances.getE02CUMMA2().trim()%></td>
            <td  width="1%">&nbsp;</td>
            <td  width="32%" nowrap> 
              <div align="right"><b>Retenciones : </b></div>
            </td>
            <td  width="13%"> 
              <div align="right"><%= Util.fcolorCCY(stBalances.getE02ACMHAM().trim())%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td  width="16%" nowrap> 
              <div align="right"></div>
            </td>
            <td  width="38%" nowrap><%= stBalances.getE02CUMMA3().trim()%></td>
            <td  width="1%">&nbsp;</td>
            <td  width="32%" nowrap> 
              <div align="right"><b>Saldo Disponible : </b></div>
            </td>
            <td  width="13%"> 
              <div align="right"><%= Util.fcolorCCY(stBalances.getE02AVALBL().trim())%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td  width="16%">&nbsp;</td>
            <td  width="38%" nowrap><%= stBalances.getE02CUMMA4().trim()%></td>
            <td  width="1%">&nbsp;</td>
            <td  width="32%" nowrap> 
              <div align="right"><b>Promedio en Libros : </b></div>
            </td>
            <td  width="13%" nowrap> 
              <div align="right"><%= Util.fcolorCCY(stBalances.getE02ACMGAV().trim())%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td  width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td  width="38%" nowrap><%= stBalances.getE02ACMPRO().trim()%></td>
            <td  width="1%" nowrap>&nbsp;</td>
            <td  width="32%" nowrap> 
              <div align="right"><b> Saldo al </b> <%= Util.formatDate(stBalances.getE02LSSTM1(),stBalances.getE02LSSTM2(),stBalances.getE02LSSTM3())%> 
                <b>: </b></div>
            </td>
            <td  width="13%"> 
              <div align="right"><%= Util.fcolorCCY(userPO.getHeader5().trim())%></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  
  <%if(interestTable.size()>0){ %>
  <table class="tableinfo">
  		<tr id="trdark">
 			<th>Monto</th>
 			<th>Tasa</th> 
  		</tr>
  	<%
 
  	for(int i= 0; i< ((java.util.ArrayList)interestTable.keys().nextElement()).size();i++){ %>
  		<tr>
  			<td style="text-align:center;"><%out.print(((java.util.ArrayList)interestTable.elements().nextElement()).get(i)); %></td>
  			<td style="text-align:center;"><%out.print(((java.util.ArrayList)interestTable.keys().nextElement()).get(i)); %></td>
  		</tr>
  	<%} %>
  </table>
  <%} %>
  <p/>
  <table class="tableinfo" width="100%" >
    <tr id="trdark"> 
      <th align=CENTER >Fecha Proceso</th>
      <th align=CENTER > Referencia</th>
      <th align=CENTER >Descripci&oacute;n</th>
      <th align=CENTER >D&eacute;bito</th>
      <th align=CENTER >Cr&eacute;dito</th>
      <th align=CENTER >Saldo</th>
    </tr>
    <%
                cifList.initRow();
                while (cifList.getNextRow()) {
                    if (cifList.getFlag().equals("")) {
                    		out.println(cifList.getRecord());
                    }
                }
              %> 
  </table>
  <p>&nbsp;</p>
  <table width="100%">
    <tr > 
      <td colspan="2" height="34"> 
        <div align="left"><b>Total </b><b>D&eacute;bitos : </b> (<%= userPO.getHeader22().trim()%>) 
          : <%= userPO.getHeader19().trim()%></div>
        </td>
      <td width="1%" height="34" >&nbsp;</td>
      <td width="37%" height="34" ><b>Total Cr&eacute;ditos :</b> (<%= userPO.getHeader23().trim()%>) 
        : <%= userPO.getHeader20().trim()%></td>
      <td width="1%" height="34" >&nbsp;</td>
      <td width="27%" height="34" ><b>Saldo Final :</b> <%= Util.fcolorCCY(userPO.getHeader21().trim())%></td>
    </tr>
  </table>
  <p align=left>&nbsp; </p>

  <%
  }
%> 
  
</FORM>

</BODY>
</HTML>
