<html>
<head>
<title>Cajero</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EDD031001Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
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

</SCRIPT> 



</head>

<BODY>

<h3 align="center">Mantenimiento Cajero<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="teller_list.jsp, EDD0310"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.teller.JSEDD0310" >
  <p> 
    <input type=HIDDEN name="SCREEN" value="800">
    <input type=HIDDEN name="totalRow" value="0">
    <input type=HIDDEN name="idxrow">
    <input type=HIDDEN name="opt">
  </p>
   <p> 
    <%
	if ( EDD031001Help.getNoResult() ) {
 %>
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
        <div align="center"><a href="javascript:goAction(3)"><b>Eliminar</b></a></div>
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
            <th align=CENTER nowrap width="10%">Identificación</th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Moneda</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Tipo</div>
            </th>
          </tr>
          <%
          EDD031001Help.initRow();
          boolean firstTime = true;
          String chk = "";
          while (EDD031001Help.getNextRow()) {
				if (firstTime) {
					firstTime = false;
					chk = "checked";
				} else {
					chk = "";
				}
                 
                datapro.eibs.beans.EDD031001Message msgList = (datapro.eibs.beans.EDD031001Message) EDD031001Help.getRecord();
                String sType = "";
                if (msgList.getE01TLMTYP().equals("T")) {
                	sType = "Cajero Regular";
                } else if (msgList.getE01TLMTYP().equals("H")) {
                	sType = "Cajero Principal";
                } else if (msgList.getE01TLMTYP().equals("O")) {
                    sType = "Oficial S/G";
                } else if (msgList.getE01TLMTYP().equals("S")) {
                    sType = "Supervisor Operaciones";
                } else if (msgList.getE01TLMTYP().equals("I")) {
                    sType = "Cajero Interface";
                }
		 %>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
              <input type="radio" name="CURRCODE" value="<%= EDD031001Help.getCurrentRow() %>" <%=chk%> onclick="document.forms[0].idxrow.value=<%= EDD031001Help.getCurrentRow() %>">
            </td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01TLMTID() %></td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01TLMCCY() %></td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= sType %></td>
          </tr>
          <%
                }
              %>
        </table>
  </table>
     
<SCRIPT language="JavaScript">
	showChecked("CURRCODE");
</SCRIPT>
<%}%>
</form>
</body>
</html>
