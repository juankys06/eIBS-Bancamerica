<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title>ACH Transactions</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "EACH130List" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function goProcess() {
	document.getElementById("SCREEN").value="5"; 
	document.forms[0].submit();
}

function goNew() {
	document.getElementById("SCREEN").value="2";
	document.forms[0].submit();
}

</SCRIPT>

</head>

<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
	 error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 String read = "";
 String disabled = "";
	if (!(userPO.getPurpose().equals("NEW") || userPO.getPurpose().equals("MAINTENANCE"))) 
		{ read = " readonly ";
		  disabled = " disabled"; }	
%>

<h3 align="center">Transacciones de ACH<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" 
	name="EIBS_GIF" ALT="ach_transactions_list.jsp, EACH130"></h3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.ach.JSEACH130">

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="5">
  <INPUT TYPE=HIDDEN NAME="actRow" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="TOTROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="NEXTROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="CURRROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="E01CDEDSC" VALUE="">

<% 
 if (userPO.getPurpose().equals("NEW") || userPO.getPurpose().equals("MAINTENANCE")){
%>
 
<TABLE class="tbenter"> 
	<TR>
		<TD align="CENTER" class="TDBKG" width="33%">
			<a href="javascript:goNew()"><b>Nueva<br>Transacción</b></a>
      	</TD>
		<TD align="CENTER" class="TDBKG" width="33%">
			<a href="javascript:goProcess()"><b>Someter<br>Actualizaciones</b></a>
      	</TD>
	  	<TD align="CENTER" class="TDBKG" width="34%">
			<a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a>
	  	</TD>
	</TR>
</TABLE> 

<%      
  }
%>     


<% 
	if ( EACH130List.getNoResult() ) {
%>
 	<TABLE class="tbenter" width=100% height=30%">
 		<TR>
      <TD>         
      <div align="center"> <h4 style="text-align:center"> No hay Transacciones definidas.</h4> 
      </div>
      </TD></TR>
   	</TABLE>
<%
	}
	else {
%>    
    
  <TABLE class="tableinfo" id="dataTable">
    
    <TR id=trdark>
		<td NOWRAP align="center" width="5%"><b>Borrar</b></td>
		<td NOWRAP align="center" width="5%"><b>Código<BR> </b></td>
		<td NOWRAP align="center" width="20%"><b>Descripción<BR> </b></td>
		<td NOWRAP align="center" width="10%"><b>Tipo</b></td>
		<td NOWRAP align="center" width="5%"><b>DB<BR>ó<BR>CR</b></td>
		<td NOWRAP align="center" width="5%"><b>Con<BR>Monto</b></td>
		<td NOWRAP align="center" width="10%"><b>Código<BR> Retorno</b></td>
		<td NOWRAP align="center" width="10%"><b>Código IBS<BR>/ Días Aplicar</b></td>
		<td NOWRAP align="center" width="30%"><b>Tipos de Cuentas IBS Permitidas</b></td>
	</TR>
    <TR id=trdark>
		<td NOWRAP align="center" width="5%"></td>
		<td NOWRAP align="center" width="5%"><IMG src="<%=request.getContextPath()%>/images/Check.gif"
			alt="Campo Obligatorio" align="bottom">
		</td>
		<td NOWRAP align="center" width="20%"><IMG src="<%=request.getContextPath()%>/images/Check.gif"
			alt="Campo Obligatorio" align="bottom">
		</td>
		<td NOWRAP align="center" width="10%"><B><IMG src="<%=request.getContextPath()%>/images/Check.gif"
				alt="Campo Obligatorio" align="bottom"></B>
		</td>
		<TD nowrap align="center" width="5%"><B><IMG src="<%=request.getContextPath()%>/images/Check.gif"
				alt="Campo Obligatorio" align="bottom">
		</B></TD>
		<TD nowrap align="center" width="5%"><B><IMG src="<%=request.getContextPath()%>/images/Check.gif"
			alt="Campo Obligatorio" align="bottom"></B></TD>
		<td NOWRAP align="center" width="10%"><b></b></td>
		<TD nowrap align="center" width="10%"><B></B></TD>
		<TD nowrap align="center" width="30%"><B></B><B><IMG src="<%=request.getContextPath()%>/images/Check.gif"
			alt="Campo Obligatorio" align="bottom"></B>
		</TD>
	</TR>
 
        <%
    	  int i = 0;
          EACH130List.initRow();    
          while (EACH130List.getNextRow()) {
               EACH13001Message msgList = (EACH13001Message) EACH130List.getRecord();		 	 
         %>              
          <TR id=trclear>

		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="checkbox" name="E01ACT_<%= EACH130List.getCurrentRow() %>" <%= disabled %>>
		</td>

		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01ACTCDE_<%= EACH130List.getCurrentRow() %>" size="3" maxlength="2"
				value='<%= msgList.getE01ACTCDE()%>' <%= read %>>
		</td>
		
		<td NOWRAP align="center" width="20%" valign="top">
			<INPUT type="text" name="E01ACTDSC_<%= EACH130List.getCurrentRow() %>" 
				value="<%= msgList.getE01ACTDSC() %>" size="46" maxlength="45" <%= read %>>
		</td>

		<TD nowrap align="center" width="10%" valign="top">
			<SELECT name="E01ACTTYP_<%= EACH130List.getCurrentRow() %>" <%= disabled %>>
			  <OPTION <%= msgList.getE01ACTTYP().trim().equals("T")?"Selected":""%> value="T">Transacción</OPTION>
			  <OPTION <%= msgList.getE01ACTTYP().trim().equals("R")?"Selected":""%> value="R">Retorno</OPTION>
			  <OPTION <%= msgList.getE01ACTTYP().trim().equals("P")?"Selected":""%> value="P">Prenotificación</OPTION>
			</SELECT>
		</TD> 
		<TD nowrap align="center" width="5%" valign="top">
			<SELECT name="E01ACTDC_<%= EACH130List.getCurrentRow() %>" <%= disabled %>>
			  <OPTION <%= msgList.getE01ACTDC().trim().equals("D")?"Selected":""%> value="D">DB</OPTION>
			  <OPTION <%= msgList.getE01ACTDC().trim().equals("C")?"Selected":""%> value="C">CR</OPTION>
			</SELECT>
		</TD> 
		<TD nowrap align="center" width="5%" valign="top">
			<INPUT type="CHECKBOX" name="E01ACTAMT_<%= EACH130List.getCurrentRow() %>" <%= disabled %> 
				value="Y" <% if (msgList.getE01ACTAMT().equals("Y")) out.print(" checked"); %>>
		</TD>
		<td NOWRAP align="center" width="10%" valign="top">
			<INPUT type="text" name="E01ACTRET_<%= EACH130List.getCurrentRow() %>" value="<%= msgList.getE01ACTRET() %>" size="3" maxlength="2"	<%= read %>>
			<A href="javascript:GetAchTransaction('E01ACTRET_<%= EACH130List.getCurrentRow()%>','E01CDEDSC')"> 
			   <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
			</A>
		</td>
		<TD nowrap align="center" width="10%" valign="top">
			<INPUT type="text" name="E01ACTITC_<%= EACH130List.getCurrentRow() %>" value="<%= msgList.getE01ACTITC() %>" size="3" maxlength="2" <%= read %>>
			<A href="javascript:GetCodeCNOFC('E01ACTITC_<%= EACH130List.getCurrentRow() %>','20')"> 
			   <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
			</A> / 
			<INPUT type="text" name="E01ACTDYS_<%= EACH130List.getCurrentRow() %>" value="<%= msgList.getE01ACTDYS() %>" size="3" maxlength="2" <%= read %>>
	 	</TD>
		<TD nowrap align="center" width="30%" valign="top">
			<INPUT type="CHECKBOX" name="E01ACTAT1_<%= EACH130List.getCurrentRow() %>" <%= disabled %> 
				value="Y" <% if (msgList.getE01ACTAT1().equals("DDA")) out.print(" checked"); %>>Cheques
			<INPUT type="CHECKBOX" name="E01ACTAT2_<%= EACH130List.getCurrentRow() %>" <%= disabled %> 
				value="Y" <% if (msgList.getE01ACTAT2().equals("SAV")) out.print(" checked"); %>>Ahorros
			<INPUT type="CHECKBOX" name="E01ACTAT3_<%= EACH130List.getCurrentRow() %>" <%= disabled %> 
				value="Y" <% if (msgList.getE01ACTAT3().equals("LNS")) out.print(" checked"); %>>Préstamos<BR>
		<INPUT type="CHECKBOX" name="E01ACTAT4_<%= EACH130List.getCurrentRow() %>" <%= disabled %> 
				value="Y" <% if (msgList.getE01ACTAT4().equals("CCR")) out.print(" checked"); %>>Tarjeta CR
			<INPUT type="CHECKBOX" name="E01ACTAT5_<%= EACH130List.getCurrentRow() %>" <%= disabled %> 
				value="Y" <% if (msgList.getE01ACTAT5().equals("GLN")) out.print(" checked"); %>>Cuenta Contable
		</TD>
	</TR>
       <% 
       	 i++; 
        } 
       %> 
  </TABLE>
  <TABLE  class="tbenter" WIDTH="88%" ALIGN=CENTER>
   	 <TR>
      <TD WIDTH="50%" ALIGN=LEFT height="25">
       <%
    	if ( EACH130List.getShowPrev() ) {
  			int pos = EACH130List.getFirstRec() - 21;
  			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.ach.JSEACH130?SCREEN=1&FromRecord=" + pos + "\" > <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
    	} %>
      </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> 
 	  	<%       
    	if (EACH130List.getShowNext()) {
  			int pos = EACH130List.getLastRec();
  			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.ach.JSEACH130?SCREEN=1&FromRecord=" + pos +  "\" ><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");
	    } %>
  </TD>
 </TR>
 </TABLE>
  
<BR>
<SCRIPT Language="javascript">
	document.forms[0].TOTROWS.value = <%= i%>;
	document.forms[0].NEXTROWS.value = <%= EACH130List.getLastRec()%>;
	document.forms[0].CURRROWS.value = <%= EACH130List.getFirstRec()%>;
</SCRIPT>
<%      
  }
%> 
</form>
</body>
</html>
