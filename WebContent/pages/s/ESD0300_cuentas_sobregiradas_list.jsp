<html>
<head>
<title>Consulta Sobregiros</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "glList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

</head>

<BODY >
<h3 align="center">Consulta Sobregiros<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cuentas_sobregiradas_list.jsp,ESD0300"></h3>
<hr size="4">
  
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0300" >      
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="2">
  <input type=HIDDEN name="totalRow">

  <%
	if ( glList.getNoResult() ) {
 %> 
  <TABLE class="tbenter" width=100% height=100%>
   	<TR>
	  <TD>     
      <div align="center"> 
          <p><font size="3"><b>No hay resultados para su criterio de busqueda</b></font></p>
          <table class="tbenter" width="100%">
            <tr> 
               <td align=CENTER class=TDBKG style="cursor:hand"> <a href="<%=request.getContextPath()%>/pages/background.jsp" ><b>Salir</b></a></td>
            </tr>
          </table>
          <p>&nbsp; </p>
        </div>
      </TD></TR>
   </TABLE>
  <%   		
	}
	else {
%> 
  <p>
  <center>
    <table  id="mainTable" class="tableinfo">
      <tr > 
        <td NOWRAP valign="top" width="100%" > 
          <table id="headTable" width="100%" >
            <tr id="trdark"> 
              <th align=CENTER nowrap > 
                <div align="center">Oficina</div>
              </th>
              <th align=CENTER nowrap > 
                <div align="center">Nombre</div>
              </th>
              <th align=CENTER nowrap > 
                <div align="center">Cuenta</div>
              </th>
              <th align=CENTER nowrap > 
                <div align="center">Saldo<br> Libros</div>
              </th>
              <th align=CENTER nowrap > 
                <div align="center">Balance <br>Neto</div>
              </th>
              <th align=CENTER nowrap > 
                <div align="center">Disponible</div>
              </th>
            </tr>
          </table>
          <div id="dataDiv1" class="scbarcolor" > 
            <table id="dataTable" >
              <%
	           glList.initRow();
	          int k=0;
	          while (glList.getNextRow()) {
		
		            datapro.eibs.beans.ESD030001Message msgList = (datapro.eibs.beans.ESD030001Message) glList.getRecord();
	          %>
		          <tr> 
		            <td NOWRAP  align=CENTER  >
		            <a href="javascript:showRetAccountInq(<%= msgList.getE01ACMACC() %>)">          
		            <%= msgList.getE01ACMBRN() %> </a> </td>    
		            <td NOWRAP  align=LEFT  ><a href="javascript:showRetAccountInq(<%= msgList.getE01ACMACC() %>)">          
		            <%= msgList.getE01CUSNA1() %></a></td>
		            <td NOWRAP  align=LEFT    >
		            <a href="javascript:showRetAccountInq(<%= msgList.getE01ACMACC() %>)">          
		            <%= msgList.getE01ACMACC() %></a></td>		          
		            <td NOWRAP  align=RIGHT  >
		            <a href="javascript:showRetAccountInq(<%= msgList.getE01ACMACC() %>)">          
		            <%= msgList.getE01ACMMGB() %></a></td>
		            <td NOWRAP  align=RIGHT >
		            <a href="javascript:showRetAccountInq(<%= msgList.getE01ACMACC() %>)">          
		            <%= msgList.getE01ACMMNB() %></a></td>
		            <td NOWRAP  align=RIGHT>
		            <a href="javascript:showRetAccountInq(<%= msgList.getE01ACMACC() %>)">          
		            <%= msgList.getE01AVALBL() %></a></td>
		          </tr>	          
	          <%
	          		k++;
	                }
              %> 
            </table>
          </div>
    </table>

  </center>
<SCRIPT language="JavaScript">
     document.forms[0].totalRow.value="<%= k %>";
     function resizeDoc() {
       divResize(false);
     adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      }

     resizeDoc();    
     window.onresize=resizeDoc;     
</SCRIPT>
  <%
     }   
  %> 
 
</form>
</body>
</html>

