<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Listado de Privilegios</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "java.io.*,java.net.*,datapro.eibs.beans.*,com.datapro.eibs.security.bean.*,java.math.*" %>
<jsp:useBean id= "pvlList" class= "datapro.eibs.beans.JBObjList" scope="session"/>

<script language="javascript">
//<!-- Hide from old browsers
function enter(code,desc,type) {
var form = parent.query.document.forms[0];
 form["PVLCOD"].value = code;
 form["PVLNME"].value = desc;
 form["CNOF04"].value = type;
 parent.query.setType();
 }
//-->
</script>
</head>
<body>
<%
if ( pvlList.getNoResult() ) {
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
    <h4>Seleccione un Privilegio</h4>
	<TABLE class="tableinfo">
			 <tr id="trdark">
			  <th  ALIGN=CENTER nowrap width=10%>C&oacute;digo</th>
			  <th  ALIGN=CENTER nowrap >Descripci&oacute;n</th>		  
			 </tr>
		
<%		
	 		pvlList.initRow();
            while (pvlList.getNextRow()) {
             CNOFCPVNME pvlBean = (CNOFCPVNME) pvlList.getRecord(); %>
             <tr id="trclear">
			  <td ALIGN=CENTER nowrap >
			    <a href="javascript:enter('<%=pvlBean.getCNORCD()%>','<%=pvlBean.getCNODSC()%>','<%=pvlBean.getCNOF04()%>')"><%=pvlBean.getCNORCD()%></a>
			  </td>
			  <td nowrap >
			    <a href="javascript:enter('<%=pvlBean.getCNORCD()%>','<%=pvlBean.getCNODSC()%>','<%=pvlBean.getCNOF04()%>')"><%=pvlBean.getCNODSC()%></a>
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
		