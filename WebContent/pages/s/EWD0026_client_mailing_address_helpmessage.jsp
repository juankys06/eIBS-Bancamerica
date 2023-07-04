<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Direcciones del Cliente</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ewd0026Help" class= "datapro.eibs.beans.JBObjList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>
<script language="javascript">
//<!-- Hide from old browsers
function enter(code) {
var formLength= top.opener.document.forms[0].elements.length;
for(n=0;n<formLength;n++){
var elementName= top.opener.document.forms[0].elements[n].name;
	if(elementName == top.opener.fieldName){
  		top.opener.document.forms[0].elements[n].value = code;
  		break;
	}
 }
top.close();
 }
//-->
</script>
</head>
<body>
<%
	if ( ewd0026Help.getNoResult() ) {
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
	      ewd0026Help.initRow();
         while (ewd0026Help.getNextRow()) {
				datapro.eibs.beans.EWD0026DSMessage beanAddress = (datapro.eibs.beans.EWD0026DSMessage)ewd0026Help.getRecord();
%>
					  <BR>
					  <TABLE class="tableinfo" style="width:95%" ALIGN=CENTER>
						 <TR id = "trdark">
						 <td width=30%>Direccion No.</td>
					   <td width=70%><a href= "javascript:enter('<%=beanAddress.getEWDMAN()%>')"><%=beanAddress.getEWDMAN()%></a></td>
						</TR>
						<TR>
						 <td width=30% >Cliente</td>
						<td width=70%><a href= "javascript:enter('<%=beanAddress.getEWDMAN()%>')"><%=beanAddress.getEWDACC()%></a></td>
						</TR>
						<TR id = "trdark">
						 <td width=30% >Nombre </td>
						<td width=70%><a href= "javascript:enter('<%=beanAddress.getEWDMAN()%>')"><%=beanAddress.getEWDMA1()%></a></td>
						</TR>
						<TR>
						 <td width=30%> Direccion</td>
						 <td width=70%><a href= "javascript:enter('<%=beanAddress.getEWDMAN()%>')">
						   <%=beanAddress.getEWDMA2()%><BR>
						   <%=beanAddress.getEWDMA3()%><BR>
						   <%=beanAddress.getEWDMA4()%></a></td>
						<TR id = "trdark">
						 <td width=30%> Ciudad</td>
						 <td width=70%><a href= "javascript:enter('<%=beanAddress.getEWDMAN()%>')"><%=beanAddress.getEWDCTY()%></a></td>
						</TR>
						<TR>
						 <td width=30%> Estado </td>
						 <td width=70%><a href= "javascript:enter('<%=beanAddress.getEWDMAN()%>')"><%=beanAddress.getEWDSTE()%></a></td>
						</TR>
						<TR id = "trdark">
						 <td width=30%>Pais </td>
						 <td width=70%><a href= "javascript:enter('<%=beanAddress.getEWDMAN()%>')"><%=beanAddress.getEWDCTR()%></a></td>
						</TR>
						<TR>
						 <td width=30%>Codigo Postal</td>
						 <td width=70%><a href= "javascript:enter('<%=beanAddress.getEWDMAN()%>')"><%=beanAddress.getEWDZPC()%></a></td>
						</TR>
						<TR id = "trdark">
						 <td width=30%>Apartado Postal</td>
						 <td width=70%><a href= "javascript:enter('<%=beanAddress.getEWDMAN()%>')"><%=beanAddress.getEWDPOB()%></a></td>
						</TR>
					</TABLE>				
					<BR>
  <%

                }
            }    
  %> 
</body>
</html>