<html>
<head>
<title>Excepciones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "reqExcep" class= "datapro.eibs.beans.JBList"   scope="session"/>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


</head>

<body>
<h3 align="center">Excepciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="exceptions,EPV0140"></h3>
<hr size="4">
<%
if ( reqExcep.getNoResult() ) {
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
  <br>
		<table class="tableinfo">
    	<tr > 
      	<td nowrap> 
        	<table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          	<tr id="trdark"> 
            	<td nowrap width="50%"> 
              	<div align="right"><b>N&uacute;mero de Propuesta:</b></div>
            	</td>
            	<td nowrap width="50%"> 
              	<div align="left"> 
                	<input type="text" readonly name="E12ACC" size="9" maxlength="9"  value="<%= request.getParameter("ACCNUM") %>">
              	</div>
            	</td>
          	</tr>
        	</table>
      	</td>
    	</tr>
  		</table>
		<br>
			<table class="tableinfo" style="width:100%" ALIGN=CENTER>
			 <tr id="trdark">
			  
    <th  ALIGN=CENTER nowrap width="21%" >Código</th>	
			  
    <th  ALIGN=CENTER nowrap width="57%" >Descripción</th>
			  
    <th  ALIGN=CENTER nowrap width="22%" >Nivel</th>
			 </tr>
		
<%		
	 reqExcep.initRow();
                while (reqExcep.getNextRow()) {
                    if (reqExcep.getFlag().equals("")) {
                    		out.println(reqExcep.getRecord());
                    }
                }
    %> 

  </TABLE>

<%
   }
%>
</body>
</html>
