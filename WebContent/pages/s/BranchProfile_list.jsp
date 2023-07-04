<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Busqueda de Sucursales</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "java.io.*,java.net.*,datapro.eibs.beans.*,com.datapro.eibs.security.bean.*,java.math.*" %>
<jsp:useBean id= "bankBranchList" class= "datapro.eibs.beans.JBObjList" scope="session"/>

<script language="javascript">
//<!-- Hide from old browsers
function enter(code,desc) {
var form = parent.query.document.forms[0];
 form["BRANCH"].value = code;
 form["BNAME"].value = desc;
 //top.close();
 }
//-->
</script>
</head>
<body>
<%
if ( bankBranchList.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
      <div align="center"> <font size="3"><b> No hay resultados para su criterio 
        de busqueda  </b></font></div>
      </TD></TR>
   		</TABLE>
<%   		
		}
	else {
%>	
    <h4>Seleccione una sucursal</h4>
	<TABLE class="tableinfo">
			 <tr id="trdark">
			  <th  ALIGN=CENTER nowrap width=10%>Numero</th>
			  <th  ALIGN=CENTER nowrap width=45%>Nombre</th>
			  <th  ALIGN=CENTER nowrap width=45%>Ciudad</th>
			 </tr>
		
<%		
	 		bankBranchList.initRow();
            while (bankBranchList.getNextRow()) {
             CNTRLBRN brnBean = (CNTRLBRN) bankBranchList.getRecord(); %>
             <tr id="trclear">
			  <td ALIGN=CENTER nowrap >
			    <a href="javascript:enter('<%=brnBean.getBRNNUM()%>','<%=brnBean.getBRNNME()%>')"><%=brnBean.getBRNNUM()%></a>
			  </td>
			  <td nowrap >
			    <a href="javascript:enter('<%=brnBean.getBRNNUM()%>','<%=brnBean.getBRNNME()%>')"><%=brnBean.getBRNNME()%></a>
			  </td>
			  <td nowrap >
			    <a href="javascript:enter('<%=brnBean.getBRNNUM()%>','<%=brnBean.getBRNNME()%>')"><%=brnBean.getBRNCIT()%></a>
			  </td>
			 </tr> 
                    
   <%             }
    %> 

  </TABLE>

<%
   }
%>
</body>
</html>			
		