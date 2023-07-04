<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
 
<%@ page import ="datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>Consulta de Ejecutivos de Cuenta</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "mtListAgent" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPOLevel" class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<SCRIPT language="javascript" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT language="javascript">

function previousScreen() {

document.forms[0].SCREEN.value = "202";
document.forms[0].E01INQBNK.value = "<%= userPOLevel.getHeader18() %>";
document.forms[0].E01INQRDY.value = <%= userPOLevel.getHeader22() %>;
document.forms[0].E01INQRDM.value = <%= userPOLevel.getHeader23() %>;
document.forms[0].submit();

}

function goAction() {
	document.forms[0].SCREEN.value ="300";
    document.forms[0].submit();	 
}

function goLoans(ofc) {
	document.forms[0].SCREEN.value ="400";
	document.forms[0].E01INQOFC.value = ofc;
	document.forms[0].submit();	 
}



function goInquiry(bnk, rdy, rdm, ofc) {

if (document.forms[0].CURRENTROW.value!=0)
   {
	document.forms[0].SCREEN.value = "201";
	document.forms[0].E01INQBNK.value = bnk;
	document.forms[0].E01INQRDY.value = rdy;
	document.forms[0].E01INQRDM.value = rdm;
	document.forms[0].E01INQOFC.value = ofc;
	document.forms[0].submit();	 
   }
}


function currentOfc(ofc, row) {
	document.forms[0].CURRENTOFC.value = ofc;
	document.forms[0].CURRENTROW.value = row;
	
	
}


</SCRIPT>

</HEAD>
<BODY>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors();");
     out.println("</SCRIPT>");
     //firstTime = false;
 } 
%>

<h3 align="center">Consulta de Ejecutivos de Cuenta
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_agent_list.jsp,ECIF090">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF090" >
<input type=hidden name="SCREEN" value="100">
<input type=hidden name="E01INQBNK" value="  ">
<input type=hidden name="E01INQRDY" value="    ">
<input type=hidden name="E01INQRDM" value="  ">
<input type=hidden name="E01INQOFC" value="    ">
<%if ( mtListAgent.getNoResult() ) {%> 
  <TABLE class="tbenter" width=100% height=40%>
   	<TR>
      <TD> 
        <div align="center"> 
          <p>
            <b>No hay resultados que correspondan a su criterio de búsqueda</b>
          </p>          
        </div>
      </TD>
     </TR>
   </TABLE>
<%} else {%>
   
 <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="CURRENTOFC" VALUE="     ">
 <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
		<tr>
			<td nowrap width="40%">
				<div align="right">Banco:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E02INQBNK" size="3" maxlength="3" value="<%= userPOLevel.getHeader18() %>" readonly> 
				<input type="text" name="E02INQBNN" size="35" maxlength="35" value="<%= userPOLevel.getHeader19() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Año - Mes:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E02INQRDY" size="3" maxlength="3" value="<%= userPOLevel.getHeader22() %>" readonly> 
				<input type="text" name="E02INQRDM" size="3" maxlength="3" value="<%= userPOLevel.getHeader23() %>" readonly> 
			</td>
		</tr>
     </table>
    </td>
   </tr>
  </table> 
  <br>    
 <TABLE class="tbenter" width="60%" align=center>
    <TR> 
      <TD class=TDBKG width="25%"> 
        <div align="center"><a href="javascript:goInquiry('<%= userPOLevel.getHeader18() %>',<%= userPOLevel.getHeader22() %>,<%= userPOLevel.getHeader23() %>,document.forms[0].CURRENTOFC.value)">Consulta<BR>Ejecutivos</a></div>
      </TD>
      <TD class=TDBKG width="25%"> 
        <div align="center"><a href="javascript:goAction()">Totales<BR>Producto</a></div>
      </TD>
      <TD class=TDBKG width="25%"> 
        <div align="center"><a href="javascript:goLoans(document.forms[0].CURRENTOFC.value)">Situaci&oacute;n<BR>Cr&eacute;ditos</a></div>
      </TD>
      <TD class=TDBKG width="25%"> 
        <div align="center"><a href="javascript:checkClose()">Salir</a></div>
      </TD>
    </TR>
 </TABLE>
  
 <TABLE  id="mainTable" class="tableinfo" >
  <TR> 
    <TD NOWRAP valign=top>
  	 <TABLE id="dataTable" width="100%">
  		<TR id="trdark"> 
    		<TH ALIGN=CENTER ></TH>
    		<TH ALIGN=CENTER >Codigo de Ejecutivo</TH>
    		<TH ALIGN=CENTER >Nombre</TH>
    		<TH ALIGN=CENTER >Activos</TH>
    		<TH ALIGN=CENTER >Pasivos</TH>
    		<TH ALIGN=CENTER >Otros</TH>
  		</TR>
     <%
        mtListAgent.initRow(); 
		boolean firstTime = true;
		String chk = "";
        while (mtListAgent.getNextRow()) {
			if (firstTime) {
				firstTime = false;
				chk = "checked";
			} else {
				chk = "";
			}
           datapro.eibs.beans.ECIF09002Message msgMT = (datapro.eibs.beans.ECIF09002Message) mtListAgent.getRecord();
     %>               
        <TR>
            <TD NOWRAP  align=CENTER width="10%"> 
              <input type="radio" name="CURRCODE" value="<%= mtListAgent.getCurrentRow() %>" <%=chk%> onclick=currentOfc('<%=msgMT.getE02INQOFC()%>',<%= mtListAgent.getCurrentRow() %>)>
            </TD>
			<% if (msgMT.getH02FLGWK1().equals("O")) { %>
				<TD NOWRAP ALIGN="CENTER"><B><%=Util.formatCell(msgMT.getE02INQOFC())%></B></TD>
				<TD NOWRAP ALIGN="LEFT"><B><%=Util.formatCell(msgMT.getE02INQOFN())%></B></TD>
				<TD NOWRAP ALIGN="RIGHT"><B><%=Util.formatCell(msgMT.getE02INQACT())%></B></TD>
				<TD NOWRAP ALIGN="RIGHT"><B><%=Util.formatCell(msgMT.getE02INQPAS())%></B></TD>
				<TD NOWRAP ALIGN="RIGHT"><B><%=Util.formatCell(msgMT.getE02INQOTH())%></B></TD>
			<% } else {%>
				<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE02INQOFC())%></TD>
				<TD NOWRAP ALIGN="LEFT"><%=Util.formatCell(msgMT.getE02INQOFN())%></TD>
				<TD NOWRAP ALIGN="RIGHT"><%=Util.formatCell(msgMT.getE02INQACT())%></TD>
				<TD NOWRAP ALIGN="RIGHT"><%=Util.formatCell(msgMT.getE02INQPAS())%></TD>
				<TD NOWRAP ALIGN="RIGHT"><%=Util.formatCell(msgMT.getE02INQOTH())%></TD>
			<% } %>
		</TR>    		
    <%}%>    
     </TABLE>
    </TD>
   </TR>	
</TABLE>

<SCRIPT language="JavaScript">
  showChecked("CURRCODE");       
</SCRIPT>
<%}%>
<p align="center"> 
  <input id="EIBSBTN" type=submit name="Submit" value="Regresar" onclick=previousScreen()>
</p>
</FORM>
</BODY>
</HTML>
