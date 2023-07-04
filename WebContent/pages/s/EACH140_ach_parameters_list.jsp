<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title>ACH Parameters</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "EACH140List" class= "datapro.eibs.beans.JBObjList"  scope="session" />
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

<h3 align="center">Parametros de ACH<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" 
	name="EIBS_GIF" ALT="ach_parameters_list.jsp, EACH140"></h3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.ach.JSEACH140">

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="5">
  <INPUT TYPE=HIDDEN NAME="actRow" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="TOTROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="NEXTROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="CURRROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="TYPE" VALUE="O">
  <INPUT TYPE=HIDDEN NAME="OPEDSC" VALUE="">

<% 
 if (userPO.getPurpose().equals("NEW") || userPO.getPurpose().equals("MAINTENANCE")){
%>
 
<TABLE class="tbenter"> 
	<TR>
		<TD align="CENTER" class="TDBKG" width="33%">
			<a href="javascript:goNew()"><b>Nuevo<br>Banco</b></a>
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
	if ( EACH140List.getNoResult() ) {
%>
 	<TABLE class="tbenter" width=100% height=30%">
 		<TR>
      <TD>         
      <div align="center"> <h4 style="text-align:center"> No hay parametros definidos.</h4> 
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
		<td NOWRAP align="center" width="5%"><b>Banco<BR> </b></td>
		<td NOWRAP align="center" width="10%"><b>Operador /<BR> Originador</b></td>
		<td NOWRAP align="center" width="35%" colspan="3"><B> Recibiendo</B></td>
		<td NOWRAP align="center" width="45%" colspan="4"><b>Enviando<br>
		<B></B></b></td>
	</TR>
    <TR id=trdark>
		<td NOWRAP align="center" width="5%"></td>
		<td NOWRAP align="center" width="5%"><IMG src="<%=request.getContextPath()%>/images/Check.gif"
			alt="Campo Obligatorio" align="bottom">
		</td>
		<td NOWRAP align="center" width="10%"><IMG src="<%=request.getContextPath()%>/images/Check.gif"
			alt="Campo Obligatorio" align="bottom">
		</td>
		<td NOWRAP align="center" width="5%"><B>Lote Contable</B><BR>
			<B><IMG src="<%=request.getContextPath()%>/images/Check.gif"
				alt="Campo Obligatorio" align="bottom"></B>
		</td>
		<TD nowrap align="center" width="15%"><B>Cuenta ACH/<BR>
		Cuenta Medio de Pago<BR>Cuenta de Admon de Créditos
		<BR>
		<IMG src="<%=request.getContextPath()%>/images/Check.gif"
				alt="Campo Obligatorio" align="bottom">
		</B></TD>
		<TD nowrap align="center" width="15%"><B>Aplicar</B></TD>
		<td NOWRAP align="center" width="5%"><B>Lote Contable</B><BR>
			<b><IMG src="<%=request.getContextPath()%>/images/Check.gif"
				alt="Campo Obligatorio" align="bottom"><B></B></b></td>
		<TD nowrap align="center" width="15%"><B>Cuenta ACH</B><BR>
		<B><IMG src="<%=request.getContextPath()%>/images/Check.gif"
			alt="Campo Obligatorio" align="bottom"></B></TD>
		<TD nowrap align="center" width="15%"><B>Cuenta Puente<BR>
		<IMG src="<%=request.getContextPath()%>/images/Check.gif"
			alt="Campo Obligatorio" align="bottom">
		</B></TD>
		<TD nowrap align="center" width="10%"><B>ACH Batch<BR>
		Consecutive</B>
		</TD>
	</TR>
 
        <%
    	  int i = 0;
          EACH140List.initRow();    
          while (EACH140List.getNextRow()) {
               EACH14001Message msgList = (EACH14001Message) EACH140List.getRecord();		 	 
         %>              
          <TR id=trclear>

		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="checkbox" name="E01ACT_<%= EACH140List.getCurrentRow() %>" <%= disabled %>>
		</td>

		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01ACHBNK_<%= EACH140List.getCurrentRow() %>" size="3" maxlength="2"
					onkeypress="enterDecimal()" value='<%= msgList.getE01ACHBNK()%>' <%= read %>>
		</td>
		
		<td NOWRAP align="center" width="10%" valign="top">
			<INPUT type="text" name="E01ACHOCD_<%= EACH140List.getCurrentRow() %>" 
				value="<%= msgList.getE01ACHOCD() %>" size="11" maxlength="10" <%= read %>>
			<A href="javascript:GetAchOperator('E01ACHOCD_<%= EACH140List.getCurrentRow() %>','TYPE','OPEDSC','O')">
				<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" align="top"><BR>
			<INPUT type="text" name="E01ACHODF_<%= EACH140List.getCurrentRow() %>"
				value="<%= msgList.getE01ACHODF() %>" size="11" maxlength="10" <%= read %>>
			<A href="javascript:GetAchOperator('E01ACHODF_<%= EACH140List.getCurrentRow() %>','TYPE','OPEDSC','C')">
				<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" align="top">
			</A>
		</td>

		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01ACHBAI_<%= EACH140List.getCurrentRow() %>"
				onkeypress="enterInteger()"			
				value="<%= msgList.getE01ACHBAI() %>" size="5" maxlength="4" <%= read %>>
		</td>
		<TD nowrap align="center" width="15%" valign="top">
			<INPUT type="text" name="E01ACHIGL_<%= EACH140List.getCurrentRow() %>" 
				value="<%= msgList.getE01ACHIGL() %>" size="17" maxlength="16"  <%= read %>>
			<A href="javascript:GetLedger('E01ACHIGL_<%= EACH140List.getCurrentRow() %>',document.forms[0].E01ACHBNK_<%=EACH140List.getCurrentRow()%>.value,'','')">
				<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" align="top"><BR>
			</A>	
			<INPUT type="text" name="E01ACHGLP_<%= EACH140List.getCurrentRow() %>"
				value="<%= msgList.getE01ACHGLP() %>" size="17" maxlength="16" <%= read %>>
			<A href="javascript:GetLedger('E01ACHGLP_<%= EACH140List.getCurrentRow() %>',document.forms[0].E01ACHBNK_<%=EACH140List.getCurrentRow()%>.value,'','')"> 
			<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" align="top"><BR>
			</A>
		<INPUT type="text" name="E01ACHGLL_<%= EACH140List.getCurrentRow() %>"
			value="<%= msgList.getE01ACHGLL() %>" size="17" maxlength="16" <%= read %>>
			<A href="javascript:GetLedger('E01ACHGLL_<%= EACH140List.getCurrentRow() %>',document.forms[0].E01ACHBNK_<%=EACH140List.getCurrentRow()%>.value,'','')">  
			<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" align="top">
			</A>
		</TD>

		<TD nowrap align="left" width="15%" valign="top">
			<INPUT type="CHECKBOX" name="E01ACHNSF_<%= EACH140List.getCurrentRow() %>" <%= disabled %> 
			 	value="Y" <% if (msgList.getE01ACHNSF().equals("Y")) out.print(" checked"); %>>Cargos NSF<BR>
		<INPUT type="CHECKBOX" name="E01ACHREP_<%= EACH140List.getCurrentRow() %>"
				value="A" <% if (msgList.getE01ACHREP().equals("A")) out.print(" checked"); %>>Retornos<BR>
		<INPUT type="CHECKBOX" name="E01ACHBAA_<%= EACH140List.getCurrentRow() %>"
				value="Y" <% if (msgList.getE01ACHBAA().equals("Y")) out.print(" checked"); %>>Aprobación
		</TD> 

		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01ACHBAT_<%= EACH140List.getCurrentRow() %>" value="<%= msgList.getE01ACHBAT() %>" size="5" maxlength="4"
 				onkeypress="enterInteger()"	<%= read %>>
		</td>
		<TD nowrap align="center" width="15%" valign="top">
			<INPUT type="text" name="E01ACHOGL_<%= EACH140List.getCurrentRow() %>" value="<%= msgList.getE01ACHOGL() %>" size="17" maxlength="16" <%= read %>>
			<A
			href="javascript:GetLedger('E01ACHOGL_<%= EACH140List.getCurrentRow() %>',document.forms[0].E01ACHBNK_<%=EACH140List.getCurrentRow()%>.value,'','')">
			<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
			align="top" border="0">
			</A>
		</TD>
		<TD nowrap align="center" width="15%" valign="top">
			<INPUT type="text" name="E01ACHGLN_<%= EACH140List.getCurrentRow() %>" value="<%= msgList.getE01ACHGLN() %>" size="17" maxlength="16" <%= read %>>
			<A
			href="javascript:GetLedger('E01ACHGLN_<%= EACH140List.getCurrentRow() %>',document.forms[0].E01ACHBNK_<%=EACH140List.getCurrentRow()%>.value,'','')">
			<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
			align="top" border="0">
			</A>
		</TD>
		<TD nowrap align="center" width="10%" valign="top">
			<INPUT type="text" name="E01ACHNUM_<%= EACH140List.getCurrentRow() %>"
			value="<%= msgList.getE01ACHNUM() %>" size="10" maxlength="9" onkeypress="enterDecimal()" <%= read %>>
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
    	if ( EACH140List.getShowPrev() ) {
  			int pos = EACH140List.getFirstRec() - 21;
  			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.ach.JSEACH140?SCREEN=1&FromRecord=" + pos + "\" > <img src=\""+request.getContextPath()+"/images/e/previous_records.gif\" border=0></A>");
    	} %>
      </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> 
 	  	<%       
    	if (EACH140List.getShowNext()) {
  			int pos = EACH140List.getLastRec();
  			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.ach.JSEACH140?SCREEN=1&FromRecord=" + pos +  "\" ><img src=\""+request.getContextPath()+"/images/e/next_records.gif\" border=0></A>");
	    } %>
  </TD>
 </TR>
 </TABLE>
  
<BR>
<SCRIPT Language="javascript">
	document.forms[0].TOTROWS.value = <%= i%>;
	document.forms[0].NEXTROWS.value = <%= EACH140List.getLastRec()%>;
	document.forms[0].CURRROWS.value = <%= EACH140List.getFirstRec()%>;
</SCRIPT>
<%      
  }
%> 
</form>
</body>
</html>
