<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title>Foreign Exchange Fees</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "EFE0110List" class= "datapro.eibs.beans.JBObjList"  scope="session" />
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
function protectAll(rows){
  for(var i=0;i<rows;i++){
	protect(i);
	}
  }  
function protect(row) {
	if(document.getElementsByName("E01FEFFT1_" + row)[0].value == "F") {
    	document.getElementsByName("E01FEFBR1_" + row)[0].value="0";
        document.getElementsByName("E01FEFBR1_" + row)[0].disabled=true;
      	document.getElementsByName("E01FEFFA1_" + row)[0].disabled=false;
    }
	if(document.getElementsByName("E01FEFFT1_" + row)[0].value == "P") {
    	document.getElementsByName("E01FEFFA1_" + row)[0].value="0";
        document.getElementsByName("E01FEFFA1_" + row)[0].disabled=true;
      	document.getElementsByName("E01FEFBR1_" + row)[0].disabled=false;
    }
	if(document.getElementsByName("E01FEFFT1_" + row)[0].value == "N") {
    	document.getElementsByName("E01FEFBR1_" + row)[0].value="0";
    	document.getElementsByName("E01FEFFA1_" + row)[0].value="0";
        document.getElementsByName("E01FEFBR1_" + row)[0].disabled=true;
      	document.getElementsByName("E01FEFFA1_" + row)[0].disabled=true;
    }
	if(document.getElementsByName("E01FEFFT2_" + row)[0].value == "F") {
    	document.getElementsByName("E01FEFBR2_" + row)[0].value="0";
        document.getElementsByName("E01FEFBR2_" + row)[0].disabled=true;
      	document.getElementsByName("E01FEFFA2_" + row)[0].disabled=false;
    }
	if(document.getElementsByName("E01FEFFT2_" + row)[0].value == "P") {
    	document.getElementsByName("E01FEFFA2_" + row)[0].value="0";
        document.getElementsByName("E01FEFFA2_" + row)[0].disabled=true;
      	document.getElementsByName("E01FEFBR2_" + row)[0].disabled=false;
    }
	if(document.getElementsByName("E01FEFFT2_" + row)[0].value == "N") {
    	document.getElementsByName("E01FEFBR2_" + row)[0].value="0";
    	document.getElementsByName("E01FEFFA2_" + row)[0].value="0";
        document.getElementsByName("E01FEFBR2_" + row)[0].disabled=true;
      	document.getElementsByName("E01FEFFA2_" + row)[0].disabled=true;
    }
      
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

<h3 align="center">Tabla De Tipos De Cambios<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" 
	name="EIBS_GIF" ALT="fx_fees_list.jsp, EFE0110"></h3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0110">

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="5">
  <INPUT TYPE=HIDDEN NAME="actRow" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="TOTROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="NEXTROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="CURRROWS" VALUE="0">

<% 
 if (userPO.getPurpose().equals("NEW") || userPO.getPurpose().equals("MAINTENANCE")){
%>
 
<TABLE class="tbenter"> 
	<TR>
		<TD align="CENTER" class="TDBKG" width="33%">
			<a href="javascript:goNew()"><b>Nuevo</b></a>
      	</TD>
		<TD align="CENTER" class="TDBKG" width="33%">
			<a href="javascript:goProcess()"><b>Aceptar</b></a>
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
	if ( EFE0110List.getNoResult() ) {
%>
 	<TABLE class="tbenter" width=100% height=30%">
 		<TR>
      <TD>         
      <div align="center"> <h4 style="text-align:center"> No hay parametros de comisiones.</h4> 
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
		<td NOWRAP align="center" width="5%"><b>C&oacute;digo Tabla</b></td>
		<td NOWRAP align="center" width="10%"><b>Descripci&oacute;n</b></td>
		<td NOWRAP align="center" width="5%"><b>Tipo Cambio</b></td>
		<td NOWRAP align="center" width="35%" colspan="3"><B>Comisiones</B></td>
		<td NOWRAP align="center" width="35%" colspan="4"><b>Cuenta Contable<B></B></b></td>
	</TR>
    <TR id=trdark>
		<td NOWRAP align="center" width="5%"></td>
		<td NOWRAP align="center" width="5%">
			<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Mandatory" align="bottom">
		</td>
		<td NOWRAP align="center" width="5%">
			<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Mandatory" align="bottom">
		</td>
		<td NOWRAP align="center" width="10%"><B>
			<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Mandatory" align="bottom"></B>
		</td>
		<td NOWRAP align="center" width="5%"><B>
			<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Mandatory" align="bottom"></B>
		</td>
		<td NOWRAP align="center" width="10%"><B>Tipo</B><BR><B>
			<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Mandatory" align="bottom"></B>
		</td>
		<TD nowrap align="center" width="15%"><B>Valor<BR>
			<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Mandatory" align="bottom"></B>
		</TD>
		<TD nowrap align="center" width="10%"><B>Minimo /<BR>
		Maximo</B></TD>
		<TD nowrap align="center" width="10%"><B>Moneda</B></TD><TD nowrap align="center" width="15%"><B>Numero<BR>
			<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Mandatory" align="bottom"></B>
		</TD>
	</TR>
 
        <%
    	  int i = 0;
          EFE0110List.initRow();    
          while (EFE0110List.getNextRow()) {
               EFE011001Message msgList = (EFE011001Message) EFE0110List.getRecord();		 	 
         %>              
        <TR id=trclear>

		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="checkbox" name="E01ACT_<%= EFE0110List.getCurrentRow() %>" <%= disabled %>>
		</td>

		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01FEFBNK_<%= EFE0110List.getCurrentRow() %>" size="3" maxlength="2"
					onkeypress="enterInteger()" value='<%= msgList.getE01FEFBNK()%>' <%= read %>>
		</td>
		
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01FEFCOD_<%= EFE0110List.getCurrentRow() %>" 
				onkeypress="enterInteger()" value="<%= msgList.getE01FEFCOD() %>" size="3" maxlength="2" <%= read %>>
		</td>
		<td NOWRAP align="center" width="10%" valign="top">
			<INPUT type="text" name="E01FEFTNM_<%= EFE0110List.getCurrentRow() %>"
				value="<%= msgList.getE01FEFTNM() %>" size="26" maxlength="25" <%= read %>>
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01FEFCCY_<%= EFE0110List.getCurrentRow() %>"
				value="<%= msgList.getE01FEFCCY() %>" size="4" maxlength="3" <%= read %>>
			<a href="javascript:GetCurrency('E01FEFCCY_<%= EFE0110List.getCurrentRow() %>','')">
			<img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a>
		</td>
		<TD nowrap align="left" width="10%" valign="top">
			<SELECT name="E01FEFFT1_<%= EFE0110List.getCurrentRow() %>" <%= disabled %> onChange="protect('<%= EFE0110List.getCurrentRow()%>')">
            	<OPTION <%= msgList.getE01FEFFT1().trim().equals("F")?"Selected":"" %>  value="F">Flat</OPTION>
                <OPTION <%= msgList.getE01FEFFT1().trim().equals("P")?"Selected":"" %>  value="P">Percent</OPTION>
                <OPTION <%= msgList.getE01FEFFT1().trim().equals("N")?"Selected":"" %>  value="N">None</OPTION>
            </SELECT>
		</TD>
		<TD nowrap align="center" width="5%" valign="top">
			<INPUT type="text" name="E01FEFFA1_<%= EFE0110List.getCurrentRow() %>" 
				value="<%= msgList.getE01FEFFA1() %>" size="13" maxlength="12" onkeypress="enterDecimal()" <%= read %>>
			<BR>
			<INPUT type="text" name="E01FEFBR1_<%= EFE0110List.getCurrentRow() %>" 
				value="<%= msgList.getE01FEFBR1() %>" size="13" maxlength="12" onkeypress="enterRate()" <%= read %>>
		</TD>
		<TD nowrap align="center" width="10%" valign="top">
			<INPUT type="text" name="E01FEFMI1_<%= EFE0110List.getCurrentRow() %>" 
				value="<%= msgList.getE01FEFMI1() %>" size="13" maxlength="12" onkeypress="enterDecimal()" <%= read %>><BR>
			<INPUT type="text" name="E01FEFMA1_<%= EFE0110List.getCurrentRow() %>" 
				value="<%= msgList.getE01FEFMA1() %>" size="13" maxlength="12" onkeypress="enterDecimal()" <%= read %>>
		</TD> 
		<TD nowrap align="center" width="5%" valign="top">
			<INPUT type="text" name="E01FEFCY1_<%= EFE0110List.getCurrentRow() %>" 
				value="<%= msgList.getE01FEFCY1() %>" size="4" maxlength="3" <%= read %>>
			<a href="javascript:GetCurrency('E01FEFCY1_<%= EFE0110List.getCurrentRow() %>','')">
			<img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a>

		</TD>
		<TD nowrap align="center" width="10%" valign="top">
			<INPUT type="text" name="E01FEFGL1_<%= EFE0110List.getCurrentRow() %>" 
				value="<%= msgList.getE01FEFGL1() %>" size="17" maxlength="16" onkeypress="enterInteger()" <%= read %>>
				<A href="javascript:GetLedger('E01FEFGL1_<%= EFE0110List.getCurrentRow() %>', document.forms[0].E01FEFBNK_<%=EFE0110List.getCurrentRow()%>.value, document.forms[0].E01FEFCY1_<%=EFE0110List.getCurrentRow()%>.value, '')">
				<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0" align="top"></A>
		</TD>
	</TR>
    <TR id=trclear>
		<td NOWRAP align="center" width="5%" valign="top"></td>
		<td NOWRAP align="center" width="5%" valign="top"></td>
		<td NOWRAP align="center" width="5%" valign="top"></td>
		<td NOWRAP align="center" width="10%" valign="top"></td>
		<td NOWRAP align="center" width="5%" valign="top"></td>
		<TD nowrap align="left" width="10%" valign="top">
			<SELECT name="E01FEFFT2_<%= EFE0110List.getCurrentRow() %>" <%= disabled %> onChange="protect('<%= EFE0110List.getCurrentRow() %>')">
            	<OPTION <%= msgList.getE01FEFFT2().trim().equals("F")?"Selected":"" %>  value="F">Flat</OPTION>
                <OPTION <%= msgList.getE01FEFFT2().trim().equals("P")?"Selected":"" %>  value="P">Percent</OPTION>
                <OPTION <%= msgList.getE01FEFFT2().trim().equals("N")?"Selected":"" %>  value="N">None</OPTION>
            </SELECT>		</TD>
		<TD nowrap align="center" width="5%" valign="top">
			<INPUT type="text" name="E01FEFFA2_<%= EFE0110List.getCurrentRow() %>" 
				value="<%= msgList.getE01FEFFA2() %>" size="13" maxlength="12" onkeypress="enterDecimal()" <%= read %>>
			<BR>
			<INPUT type="text" name="E01FEFBR2_<%= EFE0110List.getCurrentRow() %>" 
				value="<%= msgList.getE01FEFBR2() %>" size="13" maxlength="12" onkeypress="enterRate()" <%= read %>>
		</TD>
		<TD nowrap align="center" width="10%" valign="top">
			<INPUT type="text" name="E01FEFMI2_<%= EFE0110List.getCurrentRow() %>" 
				value="<%= msgList.getE01FEFMI2() %>" size="13" maxlength="12" onkeypress="enterDecimal()" <%= read %>><BR>
			<INPUT type="text" name="E01FEFMA2_<%= EFE0110List.getCurrentRow() %>" 
				value="<%= msgList.getE01FEFMA2() %>" size="13" maxlength="12" onkeypress="enterDecimal()" <%= read %>>
		</TD> 
		<TD nowrap align="center" width="5%" valign="top">
			<INPUT type="text" name="E01FEFCY2_<%= EFE0110List.getCurrentRow() %>" 
				value="<%= msgList.getE01FEFCY2() %>" size="4" maxlength="3" <%= read %>>
			<a href="javascript:GetCurrency('E01FEFCY2_<%= EFE0110List.getCurrentRow() %>','')">
			<img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a>
		</TD>
		<TD nowrap align="center" width="10%" valign="top">
			<INPUT type="text" name="E01FEFGL2_<%= EFE0110List.getCurrentRow() %>" 
				value="<%= msgList.getE01FEFGL2() %>" size="17" maxlength="16" onkeypress="enterInteger()" <%= read %>>
				<A href="javascript:GetLedger('E01FEFGL2_<%= EFE0110List.getCurrentRow() %>',document.forms[0].E01FEFBNK_<%=EFE0110List.getCurrentRow()%>.value, document.forms[0].E01FEFCY2_<%=EFE0110List.getCurrentRow()%>.value, '')">
				<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0" align="top"></A>
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
    	if ( EFE0110List.getShowPrev() ) {
  			int pos = EFE0110List.getFirstRec() - 21;
  			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.forex.JSEFE0110?SCREEN=1&FromRecord=" + pos + "\" > <img src=\""+request.getContextPath()+"/images/e/previous_records.gif\" border=0></A>");
    	} %>
      </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> 
 	  	<%       
    	if (EFE0110List.getShowNext()) {
  			int pos = EFE0110List.getLastRec();
  			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.forex.JSEFE0110?SCREEN=1&FromRecord=" + pos +  "\" ><img src=\""+request.getContextPath()+"/images/e/next_records.gif\" border=0></A>");
	    } %>
  </TD>
 </TR>
 </TABLE>
  
<BR>
<SCRIPT Language="javascript">
	document.forms[0].TOTROWS.value = <%= i%>;
	document.forms[0].NEXTROWS.value = <%= EFE0110List.getLastRec()%>;
	document.forms[0].CURRROWS.value = <%= EFE0110List.getFirstRec()%>;
    protectAll('<%= i%>');
</SCRIPT>
<%      
  }
%> 
</form>
</body>
</html>
