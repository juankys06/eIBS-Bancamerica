<html>
<head>
<title>Templates</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ESWF0300Help" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">
 function goAction(op) {

	document.forms[0].opt.value = op;
	document.forms[0].submit();
  
}



  function getParams(format,template,swiftid) {

    document.forms[0].FORMAT.value = format;
	document.forms[0].TEMPLATE.value = template; 
	document.forms[0].SWIFTID.value = swiftid;   
  }

</SCRIPT>
</head>

<BODY onLoad="">
<h3 align="center">Listado de Plantillas - Consulta<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="sel.jsp,ESWF300I"></h3>
<hr size="4">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 
<%
	if ( ESWF0300Help.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
      <div align="center"> 
        <p><b> No hay resultados que correspondan con su criterio de b&uacute;squeda</b></p>
        <p>&nbsp;</p>
      </div>
      </TD></TR>
   		</TABLE>
<%  
		}
	else {
%>	
  <form>

    
  <p>
    <INPUT TYPE=HIDDEN NAME="SCREEN" value="12">
    <INPUT TYPE=HIDDEN NAME="opt">
    <input type=HIDDEN name="FORMAT">
    <input type=HIDDEN name="TEMPLATE">
     <input type=HIDDEN name="SWIFTID">
    <input type=HIDDEN name="totalRow" value="0">
  </p>
  <table class="tbenter" align=center>
    <tr> 
      <td class=TDBKG > 
        <div align="center"><a href="javascript:goAction(3)"><b>Consulta</b></a></div>
      </td>
      <td class=TDBKG > 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
  <br> 
  <TABLE  id="mainTable" class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" width="100%" >
        <table id="headTable" width="100%" >
          <tr id="trdark"> 
			<th align=CENTER nowrap width="2%" >&nbsp;</th>
            <th align=CENTER nowrap width="20%" >Formato</th>
            <th align=CENTER nowrap width="27%" > 
              <div align="center">Plantilla</div>
            </th>
            <th align=CENTER nowrap width="24%" >Identificaci&oacute;n Swift </th>
            <th align=CENTER nowrap width="27%" > 
              <div align="center">Comentarios</div>
            </th>
          </tr>
        </table>
     <div id="dataDiv1" class="scbarcolor" >
    <table id="dataTable" > 
		<%
                ESWF0300Help.initRow();
				int k=1;
                while (ESWF0300Help.getNextRow()) {
                 
                  out.println(ESWF0300Help.getRecord());
                  k++;   
                }
              %> 
    </table>
   </div>
 </Table>
<SCRIPT language="JavaScript">
     document.forms[0].totalRow.value="<%= k %>";
     function resizeDoc() {
       divResize(false);
     adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      }

     resizeDoc();
    
     window.onresize=resizeDoc;
     
</SCRIPT>
  </form>
<%}%>
</body>
</html>
