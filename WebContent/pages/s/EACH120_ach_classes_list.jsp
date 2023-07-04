<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title>ACH Entry Classes</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "EACH120List" class= "datapro.eibs.beans.JBObjList"  scope="session" />
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

<h3 align="center">Clases de Entradas ACH<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" 
	name="EIBS_GIF" ALT="ach_classes_list.jsp, EACH120"></h3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.ach.JSEACH120">

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
			<a href="javascript:goNew()"><b>Nueva<br>Clase</b></a>
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
	if ( EACH120List.getNoResult() ) {
%>
 	<TABLE class="tbenter" width=100% height=30%">
 		<TR>
      <TD>         
      <div align="center"> <h4 style="text-align:center"> No hay Clases definidas.</h4> 
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
		<td NOWRAP align="center" width="5%"><b>Clase<BR> </b></td>
		<td NOWRAP align="center" width="30%"><b>Descripción<BR> </b></td>
		<td NOWRAP align="center" width="10%"><b>Aprobación<BR>Requerida<BR>Para Salida</b></td>
		<td NOWRAP align="center" width="10%"><b> Autorización<BR>Requerida<BR>Para Debitos<BR>de Entrada</b></td>
		<td NOWRAP align="center" width="10%"><b>Adenda<BR> </b></td>
		<td NOWRAP align="center" width="10%"><b>Número de<BR> Adendas</b></td>
		<td NOWRAP align="center" width="10%"><b>Máximo Valor<BR> Por Entrada</b></td>
		<td NOWRAP align="center" width="10%"><b>Compañía<BR> Por Omisión</b></td>
	</TR>
    <TR id=trdark>
		<td NOWRAP align="center" width="5%"></td>
		<td NOWRAP align="center" width="5%"><IMG src="<%=request.getContextPath()%>/images/Check.gif"
			alt="Campo Obligatorio" align="bottom">
		</td>
		<td NOWRAP align="center" width="30%"><IMG src="<%=request.getContextPath()%>/images/Check.gif"
			alt="Campo Obligatorio" align="bottom">
		</td>
		<td NOWRAP align="center" width="10%"><B><IMG src="<%=request.getContextPath()%>/images/Check.gif"
				alt="Campo Obligatorio" align="bottom"></B>
		</td>
		<TD nowrap align="center" width="10%"><B><IMG src="<%=request.getContextPath()%>/images/Check.gif"
				alt="Campo Obligatorio" align="bottom">
		</B></TD>
		<TD nowrap align="center" width="10%"><B><IMG
			src="<%=request.getContextPath()%>/images/Check.gif"
			alt="Campo Obligatorio" align="bottom"></B></TD>
		<td NOWRAP align="center" width="10%"><b><IMG src="<%=request.getContextPath()%>/images/Check.gif"
				alt="Campo Obligatorio" align="bottom"><B></B></b></td>
		<TD nowrap align="center" width="10%"><B></B><B><IMG src="<%=request.getContextPath()%>/images/Check.gif"
			alt="Campo Obligatorio" align="bottom"></B>
		</TD>
		<TD nowrap align="center" width="10%"><B></B>
		</TD>

	</TR>
 
        <%
    	  int i = 0;
          EACH120List.initRow();    
          while (EACH120List.getNextRow()) {
               EACH12001Message msgList = (EACH12001Message) EACH120List.getRecord();		 	 
         %>              
          <TR id=trclear>

		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="checkbox" name="E01ACT_<%= EACH120List.getCurrentRow() %>" <%= disabled %>>
		</td>

		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01ACECDE_<%= EACH120List.getCurrentRow() %>" size="4" maxlength="3"
				value='<%= msgList.getE01ACECDE()%>' <%= read %>>
		</td>
		
		<td NOWRAP align="center" width="10%" valign="top">
			<INPUT type="text" name="E01ACEDSC_<%= EACH120List.getCurrentRow() %>" 
				value="<%= msgList.getE01ACEDSC() %>" size="46" maxlength="45" <%= read %>>
		</td>

		<td NOWRAP align="center" width="10%" valign="top">
			<INPUT type="CHECKBOX" name="E01ACERAP_<%= EACH120List.getCurrentRow() %>" <%= disabled %> 
			 	value="Y" <% if (msgList.getE01ACERAP().equals("Y")) out.print(" checked"); %>>
		</td>
		<TD nowrap align="center" width="10%" valign="top">
			<INPUT type="CHECKBOX" name="E01ACERAT_<%= EACH120List.getCurrentRow() %>" <%= disabled %> 
				value="Y" <% if (msgList.getE01ACERAT().equals("Y")) out.print(" checked"); %>>
		</TD>

		<TD nowrap align="center" width="10%" valign="top">
			<SELECT name="E01ACEADR_<%= EACH120List.getCurrentRow() %>" <%= disabled %>>
			  <OPTION <%= msgList.getE01ACEADR().trim().equals("0")?"Selected":""%> value="O">Opcional</OPTION>
			  <OPTION <%= msgList.getE01ACEADR().trim().equals("M")?"Selected":""%> value="M">Obligatorio</OPTION>
			</SELECT>
		</TD> 

		<td NOWRAP align="center" width="10%" valign="top">
			<INPUT type="text" name="E01ACEADL_<%= EACH120List.getCurrentRow() %>" value="<%= msgList.getE01ACEADL() %>" size="3" maxlength="2"
 				onkeypress="enterInteger()"	<%= read %>>
		</td>
		<TD nowrap align="center" width="10%" valign="top">
			<INPUT type="text" name="E01ACEAMT_<%= EACH120List.getCurrentRow() %>" value="<%= msgList.getE01ACEAMT() %>" size="13" maxlength="12" <%= read %>
			onkeypress="enterDecimal()">
		</TD>
		<TD nowrap align="center" width="10%" valign="top">
			<INPUT type="text" name="E01ACEDFI_<%= EACH120List.getCurrentRow() %>" value="<%= msgList.getE01ACEDFI() %>" size="13" maxlength="12" <%= read %>>
			<A
			href="javascript:GetAchOperator('E01ACEDFI_<%= EACH120List.getCurrentRow() %>','TYPE','OPEDSC','C')">
			<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
			align="top" border="0">
			</A>
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
    	if ( EACH120List.getShowPrev() ) {
  			int pos = EACH120List.getFirstRec() - 21;
  			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.ach.JSEACH120?SCREEN=1&FromRecord=" + pos + "\" > <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
    	} %>
      </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> 
 	  	<%       
    	if (EACH120List.getShowNext()) {
  			int pos = EACH120List.getLastRec();
  			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.ach.JSEACH120?SCREEN=1&FromRecord=" + pos +  "\" ><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");
	    } %>
  </TD>
 </TR>
 </TABLE>
  
<BR>
<SCRIPT Language="javascript">
	document.forms[0].TOTROWS.value = <%= i%>;
	document.forms[0].NEXTROWS.value = <%= EACH120List.getLastRec()%>;
	document.forms[0].CURRROWS.value = <%= EACH120List.getFirstRec()%>;
</SCRIPT>
<%      
  }
%> 
</form>
</body>
</html>
