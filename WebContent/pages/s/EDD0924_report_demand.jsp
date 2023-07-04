<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title>Procesar Por Demanda</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "dvList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function setInfo(idx){  
  for ( var i=1; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idx].className="trhighlight";
   document.forms[0].actRow.value = idx;       
}

function goProcess() {

    var row = parseInt(document.forms[0].actRow.value) - 1;   
	document.forms[0].action = "<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSERPTSTD?SCREEN=26&ROW=" + row;	
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

%>

<h3 align="center">Generar Reportes o Ejecutar Procesos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="report_demand.jsp, EDD0924"></h3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSERPTSTD">

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="26">
  <INPUT TYPE=HIDDEN NAME="actRow" VALUE="0">
        
<% 
	if ( dvList.getNoResult() ) {
%>
 	<TABLE class="tbenter" width=100% height=30%">
 		<TR>
      <TD>         
      <div align="center"> <h4 style="text-align:center"> No existen Procesos a ejecutar.</h4> 
      </div>
      </TD></TR>
   	</TABLE>
<%
	}
	else {
%>
  
  <TABLE class="tbenter">
    <TR>
      <TD ALIGN=CENTER class="TDBKG" width="50%">
  		<a href="javascript:goProcess()">Procesar</a>
      </TD>
      <TD ALIGN=CENTER class="TDBKG" width="50%">
  		<a href="<%=request.getContextPath()%>/pages/background.jsp">Salir</a>
      </TD>
    </TR>
  </TABLE>
  <TABLE class="tableinfo" id="dataTable">
    <TR id="trdark">
      <TH ALIGN=CENTER  nowrap width="1%"></TH> 
      <TH ALIGN=CENTER  nowrap >Nombre</TH>
      <TH ALIGN=CENTER  nowrap >Descripción</TH>
    </TR>
    
    <%
                
          dvList.initRow();               
          while (dvList.getNextRow()) {
               EREPORTSTDMessage msgList = (EREPORTSTDMessage) dvList.getRecord();			 
                    
       %>             
                    
          <TR>
          <td align="center" nowrap > 
        	<input type="radio" name="ROW" value="<%= dvList.getCurrentRow() %>" onClick="setInfo(<%= dvList.getCurrentRow() + 1 %>)" <% if (dvList.getCurrentRow() == 0) out.print("checked");%>>
      	  </td>
          <td NOWRAP align=center>
          	<%=Util.formatCell(msgList.getE01REPNME())%>
		  </td>		  
		  <td NOWRAP >
			<%=Util.formatCell(msgList.getE01REPDSC())%>
		  </td>		  
		</TR>
        <% } %> 
  </TABLE>
  <SCRIPT language="JavaScript">     
	 showChecked("ROW");          
  </SCRIPT> 
<BR>

<%      
  }
%> 
</form>
</body>
</html>
