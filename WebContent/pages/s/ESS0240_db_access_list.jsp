<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Lista de Acceso a Base de Datos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "dbList" 	class= "datapro.eibs.beans.JBObjList"  			scope="session" />
<jsp:useBean id= "error" 	class= "datapro.eibs.beans.ELEERRMessage"  		scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  			scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  	scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">
function goAction(acc, apc) {

	document.forms[0].ACCNUMBER.value = acc;
	document.forms[0].APCCDE.value = apc;
	document.forms[0].submit();
}
</SCRIPT>  

</head>

<BODY>
<h3 align="center">Acceso a Base de Datos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="db_access_list.jsp, ESS0240"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.security.JSESS0240" >
  <p> 
    <input type=hidden name="SCREEN" value="300">
    <input type=hidden name="ACCNUMBER" value="">
    <input type=hidden name="APCCDE" value="">
  </p>
  <p> 
  <%if ( dbList.getNoResult() ) {%>
  </p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>No hay resultados para su criterio de búsqueda</b></p>
          <table class="tbenter" width=100% align=center>
            <tr> 
              <td class=TDBKG width="30%"> 
                <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
              </td>
            </tr>
          </table>
          <p>&nbsp;</p>
        </div>
	  </TD>
	</TR>
    </TABLE>
	<%} else {
		if ( !error.getERRNUM().equals("0")  ) {
     		error.setERRNUM("0");
     		out.println("<SCRIPT Language=\"Javascript\">");
	     	out.println("       showErrors()");
     		out.println("</SCRIPT>");
     	}
	%> 
  <p> 
  <table class="tbenter" width=100% align=center>
    <tr> 
      <td class=TDBKG width="100%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
   
  <br>
  <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="15%">No. Referencia</th>
            <th align=CENTER nowrap width="30%"> 
              <div align="center">Descripción de Campo</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Antes</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Después</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Usuario</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Fecha</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Hora</div>
            </th>
          </tr>
          <%
          dbList.initRow();
          boolean firstTime = true;
          String chk = "";
          while (dbList.getNextRow()) {
				if (firstTime) {
					firstTime = false;
					chk = "checked";
				} else {
					chk = "";
				}
                 
                datapro.eibs.beans.ESS024002Message msgList = (datapro.eibs.beans.ESS024002Message) dbList.getRecord();
		 %>
          <tr> 
            <td NOWRAP  align=CENTER width=\"15%\"><a href="javascript:goAction('<%= msgList.getE02NUMREF() %>','<%= msgList.getE02APLCDE() %>')"><%= msgList.getE02NUMREF() %></a></td>
            <td NOWRAP  align=CENTER width=\"15%\"><a href="javascript:goAction('<%= msgList.getE02NUMREF() %>','<%= msgList.getE02APLCDE() %>')"><%= msgList.getE02FLDDSC() %></a></td>
            <td NOWRAP  align=LEFT   width=\"30%\"><a href="javascript:goAction('<%= msgList.getE02NUMREF() %>','<%= msgList.getE02APLCDE() %>')"><%= msgList.getE02VALBEF() %></a></td>
            <td NOWRAP  align=CENTER width=\"10%\"><a href="javascript:goAction('<%= msgList.getE02NUMREF() %>','<%= msgList.getE02APLCDE() %>')"><%= msgList.getE02VALAFT() %></a></td>
            <td NOWRAP  align=RIGHT  width=\"10%\"><a href="javascript:goAction('<%= msgList.getE02NUMREF() %>','<%= msgList.getE02APLCDE() %>')"><%= msgList.getE02USERID() %></a></td>
            <td NOWRAP  align=CENTER width=\"10%\"><a href="javascript:goAction('<%= msgList.getE02NUMREF() %>','<%= msgList.getE02APLCDE() %>')"><%= Util.formatDate(msgList.getE02DATER1(), msgList.getE02DATER2(), msgList.getE02DATER3()) %></a></td>
            <td NOWRAP  align=RIGHT  width=\"10%\"><a href="javascript:goAction('<%= msgList.getE02NUMREF() %>','<%= msgList.getE02APLCDE() %>')"><%= Util.formatTime(msgList.getE02VALTIM()) %></a></td>
          </tr>
          <%}%>
        </table>
  </table>
     
<SCRIPT language="JavaScript">
	showChecked("CURRCODE");
</SCRIPT>
<%}%>
</form>
</body>
</html>
