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

<jsp:useBean id= "applayList" class= "datapro.eibs.beans.JBObjList"  scope="session" />

<jsp:useBean id= "chkList" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="javascript">
  function goAction(op){
    document.forms[0].E03ACTION.value = op;    
    var ok = confirm("Esta seguro de la operación a realizar ?");
    if (ok) document.forms[0].submit();
  }    
</script>

</head>

<body>
<h3 align="center">Listado de C&aacute;mara Entrante
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cleaning_applay_list, EDD0930"></h3> 
<hr size="4">
  
<%
	if ( applayList.getNoResult() ) {
%>
 		<TABLE class="tbenter" width=100% height=60%>
 		<TR>
      <TD> 
        
      <div align="center"> <font size="3"><b> No hay datos que correspondan con su criterio de busqueda</b></font> 
      </div>
      </TD></TR>
   		</TABLE>
<%
	} else {
%>
<FORM method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.cleaning.JSEDD0930">
 
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="2">
  <INPUT TYPE=HIDDEN NAME="E03CNTFE1" value="<%=userPO.getHeader10()%>">
  <INPUT TYPE=HIDDEN NAME="E03CNTFE2" value="<%=userPO.getHeader11()%>">
  <INPUT TYPE=HIDDEN NAME="E03CNTFE3" value="<%=userPO.getHeader12()%>">
  <INPUT TYPE=HIDDEN NAME="E03ACTION" value="A">
  <TABLE class="tbenter">
    <TR>
      <TD class="TDBKG" width="33%">
  		<a href="javascript:goAction('A')">Aplicar<BR>Seleccion</a>
      </TD>
      <TD class="TDBKG" width="33%">
  		<a href="javascript:goAction('D')">Borrar<BR>Seleccion</a>
      </TD>      
      <TD class="TDBKG" width="33%">
  		<a href="javascript:checkClose()">Salir</a>
      </TD>
    </TR>
  </TABLE>
  <TABLE class="tbenter" width="100%">
    <TR>
      <TD ALIGN=CENTER >
		  <input type="radio" name="ACTION" checked onClick="SetSelection(0)">Limpiar Todos
	  </TD>
	  <TD ALIGN=CENTER >
		  <input type="radio" name="ACTION" onClick="SetSelection(1)">Seleccionar Todos
	  </TD>  
    </TR>
  </TABLE>
  <TABLE class="tableinfo">
    <TR id="trdark">
      <TH ALIGN=CENTER  nowrap width="1%"></TH> 
      <TH ALIGN=CENTER  nowrap width="9%">Bco.</TH>
      <TH ALIGN=CENTER  nowrap width="60%">Sucursal</TH>     
      <TH ALIGN=CENTER  nowrap width="10%">Mda.</TH>
      <TH ALIGN=CENTER  nowrap width="20%">Monto</TH>
    </TR>
    <%
                
          applayList.initRow();
          chkList.initRow();               
          while (applayList.getNextRow()) {
               EDD093002Message msgList = (EDD093002Message) applayList.getRecord();			 
               chkList.setCurrentRow(applayList.getCurrentRow());     
       %>             
                    
          <TR>
          <td NOWRAP align=center>
          	<input name="SEL<%=applayList.getCurrentRow()%>" type=checkbox value="1" <%=chkList.getRecord()%>>
		  </td>
          <td NOWRAP align=center>
          	<%=Util.formatCell(msgList.getE02CODBNK())%>
		  </td>
		  <td NOWRAP >
			<%=Util.formatCell(msgList.getE02CODSUC())%> - <%=Util.formatCell(msgList.getE02NOMSUC())%>
		  </td>		  
		  <td NOWRAP >
          	<%=Util.formatCell(msgList.getE02CODCCY())%>
		  </td>
		  <td NOWRAP align=right>
          	<%=Util.fcolorCCY(msgList.getE02AMOUNT())%>
		  </td>
		</TR>
        <%        }
              %> 
  </TABLE>
  
  <script language="javascript">
	function SetSelection(value){
	    var val = (value == 0) ? false : true;  		  		
  		for(var n=0;n<=<%=chkList.getLastRec()%>;n++) {
   			document.forms[0]["SEL"+n].checked = val;
  		}
	}
 </script>
 </FORM>
<%      
  }
%> 


</BODY>
</HTML>