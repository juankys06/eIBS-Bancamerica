<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title>Cost Center List</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "ccList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">


function goAction(op) {
    document.forms[0].opt.value = op;
    if (document.forms[0].opt.value == "D") {
    	document.forms[0].SCREEN.value = 5;
    }
    document.forms[0].submit();
}

function setInfo(ccn, bnk, dsc, grp, sbr) {
    document.forms[0].CCN.value = ccn;
    document.forms[0].BNK.value = bnk;
    document.forms[0].DSC.value = dsc;
    document.forms[0].GRP.value = grp;
    document.forms[0].SBR.value = sbr;
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

<h3 align="center">Mantenimiento de Centros de Costo<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cost_center_list.jsp, ECC0000"></h3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSECC0000">

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="">
  <INPUT TYPE=HIDDEN NAME="BNK" VALUE="">
  <INPUT TYPE=HIDDEN NAME="CCN" VALUE="">
  <INPUT TYPE=HIDDEN NAME="DSC" VALUE="">
  <INPUT TYPE=HIDDEN NAME="GRP" VALUE="">
  <INPUT TYPE=HIDDEN NAME="SBR" VALUE="">
  <INPUT TYPE=HIDDEN NAME="actRow" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
         
<%
	if ( ccList.getNoResult() ) {
%>
 	<TABLE class="tbenter" width=100% height=30%">
 		<TR>
      <TD>         
      <div align="center"> <h4 style="text-align:center">No hay centros de costo para este banco. <br>Haga clic en Nuevo para agregar uno.</h4> 
      </div>
      </TD></TR>
   	</TABLE>
   	<TABLE class="tbenter">
    <TR>
      <TD ALIGN=CENTER class="TDBKG" width="50%">
   		<a href="javascript:goAction('N')">Nuevo</a>
      </TD>      
      <TD ALIGN=CENTER class="TDBKG" width="50%">
  		<a href="<%=request.getContextPath()%>/pages/background.jsp">Salir</a>
      </TD>
    </TR>
  </TABLE>
<%
	}
	else {
%>
  
  <TABLE class="tbenter">
    <TR>
      <TD ALIGN=CENTER class="TDBKG" width="33%">
  		<a href="javascript:goAction('N')">Nuevo</a>
      </TD>
      <TD ALIGN=CENTER class="TDBKG" width="33%">
  		<a href="javascript:goAction('M')">Mantenimiento</a>
      </TD>      
      <TD ALIGN=CENTER class="TDBKG" width="34%">
  		<a href="<%=request.getContextPath()%>/pages/background.jsp">Salir</a>
      </TD>
    </TR>
  </TABLE>
  <TABLE class="tableinfo" id="dataTable">
    <TR id="trdark">
      <TH ALIGN=CENTER  nowrap width="1%"></TH> 
      <TH ALIGN=CENTER  nowrap >Centro de Costo</TH>
      <TH ALIGN=CENTER  nowrap >Descripción</TH>
      <TH ALIGN=CENTER  nowrap >Grupo</TH>
      <TH ALIGN=CENTER  nowrap >Sub-Grupo</TH>      
    </TR>
    
    <%
          boolean firstTime = true;  
          String chk = "";  
          ccList.initRow(); 
          int k=1;              
          while (ccList.getNextRow()) {
               ECC000001Message msgList = (ECC000001Message) ccList.getRecord();		
               if (firstTime) {
	               	chk = "checked";
	               	firstTime = false;
               } else {
               		chk = "";
                }	 
       %>             
        <TR>
          <td align="center" nowrap > 
        	<input type="radio" name="ROW1" <%= chk %> value="<%= ccList.getCurrentRow() %>" onClick="setInfo('<%= msgList.getE01CCDCCN()%>', '<%= msgList.getE01CCDBNK() %>', '<%= msgList.getE01CCDCCD() %>', '<%= msgList.getE01CCDGRP() %>', '<%= msgList.getE01CCDSBR() %>')" >
      	  </td>
          <td NOWRAP align=center>
          	<%=Util.formatCell(msgList.getE01CCDCCN())%>
		  </td>		  
		  <td NOWRAP >
			<%=Util.formatCell(msgList.getE01CCDCCD())%>
		  </td>		  
		  <td NOWRAP >
          	<%=Util.formatCell(msgList.getE01CCDGRP())%>
		  </td>
		  <td NOWRAP >
          	<%=Util.formatCell(msgList.getE01CCDSBR())%>
		  </td>
		</TR>
		
        <% 
        	k++;
         }
         %> 
  </TABLE>
  <SCRIPT language="JavaScript">     
  	document.forms[0].totalRow.value="<%= k %>";
  </SCRIPT> 
<%      
  }
%> 
</form>
</body>
</html>
