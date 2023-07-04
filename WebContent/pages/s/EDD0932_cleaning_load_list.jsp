<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title></title>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">

<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<SCRIPT language="Javascript1.1" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "loadList" class= "datapro.eibs.beans.JBObjList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="javascript">
function confirmAct() {
    var ok = true;
    ok = confirm("Esta seguro de la operación a realizar ?");
    if (ok) document.forms[0].submit();
  }
</script>
</head>

<body>
<h3 align="center">Listado de C&aacute;mara Saliente
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cleaning_load_list, EDD0932"></h3> 
<hr size="4">
 <form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.cleaning.JSEDD0932">
  
<%
	if ( loadList.getNoResult() ) {
%>
 		<TABLE class="tbenter" width=100% height=60%>
 		<TR>
      <TD> 
        
      <div align="center"> <font size="3"><b> No hay datos que correspondan con su criterio de busqueda</b></font> 
      </div>
      </TD></TR>
   		</TABLE>
<%
	}
	else {
%>
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="2">
  <INPUT TYPE=HIDDEN NAME="E03CNTFE1" value="<%=userPO.getHeader10()%>">
  <INPUT TYPE=HIDDEN NAME="E03CNTFE2" value="<%=userPO.getHeader11()%>">
  <INPUT TYPE=HIDDEN NAME="E03CNTFE3" value="<%=userPO.getHeader12()%>">
  <INPUT TYPE=HIDDEN NAME="E03ACTION" value="A">
  <TABLE class="tbenter">
    <TR>
      <TD class="TDBKG" width="50%">
  		<a href="javascript:confirmAct()">Cargar Lista</a>
      </TD>      
      <TD class="TDBKG" width="50%">
  		<a href="javascript:checkClose()">Salir</a>
      </TD>
    </TR>
  </TABLE>
  <TABLE class="tableinfo">
    <TR id="trdark"> 
      <TH ALIGN=CENTER  nowrap width="5%">Bco.</TH>
      <TH ALIGN=CENTER  nowrap width="40%">Sucursal</TH>     
      <TH ALIGN=CENTER  nowrap width="5%">Mda.</TH>
      <TH ALIGN=CENTER  nowrap width="40%">Bco. Dep.</TH>
      <TH ALIGN=CENTER  nowrap width="10%">Monto</TH>
    </TR>
    <%
                
          loadList.initRow();               
          while (loadList.getNextRow()) {
               EDD093202Message msgList = (EDD093202Message) loadList.getRecord();			 
                    
       %>             
                    
          <TR>
          <td NOWRAP align=center>
          	<%=Util.formatCell(msgList.getE02CODBNK())%>
		  </td>
		  <td NOWRAP >
			<%=Util.formatCell(msgList.getE02CODSUC())%> - <%=Util.formatCell(msgList.getE02NOMSUC())%>
		  </td>		  
		  <td NOWRAP >
          	<%=Util.formatCell(msgList.getE02CODCCY())%>
		  </td>
		  <td NOWRAP >
          	<%=Util.formatCell(msgList.getE02CODBAN())%> - <%=Util.formatCell(msgList.getE02NOMBAN())%>
		  </td>
		  <td NOWRAP align=right>
          	<%=Util.fcolorCCY(msgList.getE02AMOUNT())%>
		  </td>
		</TR>
        <%        }
              %> 
  </TABLE>
  
<%      
  }
%> 
</FORM>

</BODY>
</HTML>