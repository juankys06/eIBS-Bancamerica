<html>
<head>
<title>Tablas de Presvisiones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ESD200001Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">
function goAction(op) {
	document.forms[0].opt.value = op;
	document.forms[0].submit();
}

function getParams(bank,branch) {
    document.forms[0].BANK.value = account;
    document.forms[0].BRANCH.value = branch;
}
</SCRIPT>  

</head>

<BODY>
<h3 align="center">Mantenimiento de Parámetros - Banca por Internet<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="previsions_list.jsp, EDL0522"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESD2000" >
  <p> 
    <input type=HIDDEN name="SCREEN" value="800">
    <input type=HIDDEN name="totalRow" value="0">
    <input type=HIDDEN name="BANK">
    <input type=HIDDEN name="BRANCH">
   
    <input type=HIDDEN name="opt">
  </p>
  <p> 
  <%if ( ESD200001Help.getNoResult() ) {%>
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
          <p><b>No hay Resultados para su Criterio de Búsqueda</b></p>
          <table class="tbenter" width=100% align=center>
            <tr> 
              <td class=TDBKG width="30%"> 
                <div align="center"><a href="javascript:goAction(1)"><b>Nuevo</b></a></div>
              </td>
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
      <td class=TDBKG width="30%"> 
        <div align="center"><a href="javascript:goAction(1)"><b>Nuevo</b></a></div>
      </td>
      <td class=TDBKG width="30%"> 
        <div align="center"><a href="javascript:goAction(2)"><b>Mantenimiento</b></a></div>
      </td>
      <td class=TDBKG width="30%"> 
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
            <th align=CENTER nowrap width="10%">&nbsp;</th>
            <th align=CENTER nowrap width="20%">Banco</th>
            <th align=CENTER nowrap width="70%"><div align="center">Descripción</div></th>
          </tr>
          <%
          ESD200001Help.initRow();
          boolean firstTime = true;
          String chk = "";
          while (ESD200001Help.getNextRow()) {
				if (firstTime) {
					firstTime = false;
					chk = "checked";
				} else {
					chk = "";
				}
                 
	            datapro.eibs.beans.ESD200001Message msgList = (datapro.eibs.beans.ESD200001Message) ESD200001Help.getRecord();
		  %>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
              <input type="radio" name="CURRCODE" value="<%= ESD200001Help.getCurrentRow() %>" <%=chk%>>
            </td>
            <td NOWRAP  align=CENTER width=\"20%\"><%= msgList.getE01INTBNK() %></td>
            <td NOWRAP  align=CENTER width=\"70%\"><%= msgList.getE01INTDSC() %></td>
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
