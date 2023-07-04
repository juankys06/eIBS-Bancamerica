<html>
<head>
<title>Tasas de Colocaciones 24 Horas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EDD050501Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
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
<%
String chk = "";
%>
<h3 align="center">Tablas de Colocaciones a 24 Horas (Overnight)<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="overnight_rates_list.jsp, EDD0505"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDD0505" >
  <p> 
    <input type=HIDDEN name="SCREEN" value="800">
    <input type=HIDDEN name="totalRow" value="0">

   
    <input type=HIDDEN name="opt">
  </p>
  <p> 
    <%
	if ( EDD050501Help.getNoResult() ) {
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
          <p><b>No hay resultados para su b&uacute;squeda</b></p>
          <table class="tbenter" width=100% align=center>
            <tr> 
              <td class=TDBKG width="30%"> 
                <div align="center"><a href="javascript:goAction(1)"><b>Nueva</b></a></div>
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
	
  <%  
		}
	else {
%> <% 
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
        <div align="center"><a href="javascript:goAction(1)"><b>Nueva</b></a></div>
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
            <th align=CENTER nowrap width="10%">Banco</th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Moneda</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Tabla</div>
            </th>
            <th align=CENTER nowrap width="10%">Tasa</th>
          </tr>
          <%
                EDD050501Help.initRow();
                chk = "checked";               
                while (EDD050501Help.getNextRow()) {
                 
                  datapro.eibs.beans.EDD050501Message msgList = (datapro.eibs.beans.EDD050501Message) EDD050501Help.getRecord();
		 %>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
              <input type="radio" name="CURRCODE" value="<%= EDD050501Help.getCurrentRow() %>" <%=chk%>>
            </td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01ONRBNK() %></td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01ONRCCY() %></td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01ONRTBL() %></td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01ONRTE1() %></td>
          </tr>
          <%
          	chk = ""; 
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
