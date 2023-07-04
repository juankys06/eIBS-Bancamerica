<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import ="datapro.eibs.master.Util,datapro.eibs.beans.*" %>
<HTML>
<HEAD>
<TITLE>
Lista de Solicitudes Pendientes
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "chkbList" class= "datapro.eibs.beans.JBObjList"  scope="session" /> 
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" /> 
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<script language="JavaScript">

 function goAction(op) {
    document.forms[0].opt.value = op; 
    var page = "";  
    if (op == 1) {
     	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH0306?SCREEN=200&opt=" + op;
	} else {
	 	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH0306?SCREEN=200&opt=" + op + "&actRow=" + document.forms[0].actRow.value;	
	}
	CenterWindow(page,450,400,2);		 
 }

 function setInfo(idxRow){
   var i= parseInt(document.forms[0].actRow.value);
   dataTable.rows[i].className="trnormal";   
   dataTable.rows[idxRow].className="trhighlight";
   document.forms[0].actRow.value = idxRow;   
  }
   
</script>
</head>

<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

%> 
<h3 align="center">Listado de Solicitudes de Chequeras sin Personalizaci&oacute;n
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="chb_list.jsp,ECH0306">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH0306">

<INPUT TYPE=HIDDEN NAME="opt" VALUE="1">


<%
	if ( chkbList.getNoResult() ) {
%> 

   <TABLE class="tbenter" width=100% height=60%>
   	<TR>
      <TD> 
        <div align="center">           
          <p>&nbsp;</p>
          <p><b>No existen solicitudes de chequeras pendientes de aprobacion,
             por favor elija una de las siguientes opciones</b></p>
          <table class="tbenter" width="100%">
            <tr> 
              <td class=TDBKG> <a href="javascript:goAction(1)">Nuevo</a></td>
              <td class=TDBKG> <a href="javascript:checkClose()">Salir</a></td>
            </tr>
          </table>
        </div>
      </TD>
     </TR>
   </TABLE>
  <%   		
	}
	else {
	 int row;
	 try{row = Integer.parseInt(request.getParameter("ROW"));}catch(Exception e){row = 0;}
         
%> 

  <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="actRow" VALUE="<%= row %>">
  
  <table class="tbenter" width="100%">
    <tr>     
      <td class=TDBKG><a href="javascript:goAction(1)">Nuevo</a></td>
      <td class=TDBKG><a href="javascript:goAction(2)">Mantenimiento</a></td>
      <td class=TDBKG><a href="javascript:checkClose()">Salir</a></td>    
    </tr>
  </table>
 <TABLE  id="mainTable" class="tableinfo">
 <TR> 
   <TD NOWRAP valign="top">
        <TABLE id="headTable">
          <TR id="trdark"> 
            <TH ALIGN=CENTER NOWRAP></TH>
            <TH ALIGN=CENTER NOWRAP>No. Solicitud</TH>
            <TH ALIGN=CENTER NOWRAP>Suc.<br>Destino</TH>            
            <TH ALIGN=CENTER NOWRAP>Suc.<br>Origen</TH>
            <TH ALIGN=CENTER NOWRAP>Moneda</TH>
            <TH ALIGN=CENTER NOWRAP>Tipo</TH>            
            <TH ALIGN=CENTER NOWRAP>Cantidad</TH>
          </TR>
        </TABLE>
        
    <div id="dataDiv1" class="scbarcolor" style="padding:0" NOWRAP>
        <table id="dataTable"  NOWRAP>
    <%
         chkbList.initRow();
         int k=1;
         while (chkbList.getNextRow()) {
            ECH030601Message msgCHKB = (ECH030601Message) chkbList.getRecord();
     %> 
            <TR> 
              <TD ALIGN=CENTER NOWRAP> 
                <INPUT TYPE="radio" NAME="ROW" VALUE="<%= chkbList.getCurrentRow() %>" <% if (chkbList.getCurrentRow() == row) out.print("checked");%> onClick="setInfo(<%= chkbList.getCurrentRow() %>)">
              </TD>
              <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(msgCHKB.getE01NUMSOL()) %></TD>
              <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(msgCHKB.getE01SUCDST()) %></TD>
              <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(msgCHKB.getE01SUCORG()) %></TD>
              <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(msgCHKB.getE01CODCCY()) %></TD>
              <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(msgCHKB.getE01TIPCHK()) %></TD>
              <TD ALIGN=RIGHT NOWRAP><%= Util.formatCell(msgCHKB.getE01CANCHK()) %></TD>
            </TR>
      <%
              k++;
         }        
    %> 
          </table>
   </div>
   </TD>
  </TR>	
</TABLE>
      
<SCRIPT language="JavaScript">
  document.forms[0].totalRow.value="<%= k %>";
  function resizeDoc() {
       divResize();
       adjustEquTables(headTable, dataTable, dataDiv1,1,false);
  }
  showChecked("ROW");
  resizeDoc();
  window.onresize=resizeDoc;
     
</SCRIPT>


  <%
  }
%> 
</FORM>

</BODY>
</HTML>
