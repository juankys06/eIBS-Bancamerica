<HTML>
<HEAD>
<TITLE>
Lista de Clientes
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>

<jsp:useBean id= "msgList" class= "datapro.eibs.beans.JBList" scope="session"/>

<BODY>

   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
             <TD> 
               <div align="center">
               		<b>
               				<%
               					 int row = 0;
               					 try { row = Integer.parseInt(request.getParameter("row")); } catch (Exception e) {}
               					 msgList.setCurrentRow(row);
               					 out.print(msgList.getFlag() );
               				%>
               		</b>
               	</div>
             </TD>
         </TR>
   		</TABLE>

</BODY>
</HTML>
