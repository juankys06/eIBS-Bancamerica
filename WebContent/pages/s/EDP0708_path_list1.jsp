<%@ page import ="datapro.eibs.master.Util" %>
<html>
<head>
<title>Lista Codigos de Formatos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "EDP073501Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
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


function getParams(currrow,RUT,dsc) {

	document.forms[0].CURRENTROW.value = currrow;
    document.forms[0].RUT.value = RUT;
    document.forms[0].DSC.value = dsc;
    
}


</SCRIPT>  
</head>
<BODY>
<h3 align="center">Códigos de Rutas <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="path_list1.jsp, EDP0708"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0708" >
  <p> 
    <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0"> 
    <input type=HIDDEN name="SCREEN" value="400">
    <input type=HIDDEN name="totalRow" value="0">
    <input type=HIDDEN name="RUT" value=" " size=2 maxlength=1>
    <input type=HIDDEN name="DSC" value="                                   " size=36 maxlength=35>
    <input type=HIDDEN name="opt">
  </p>
  <p> 
    <%
	if ( EDP073501Help.getNoResult() ) {
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
String chk = "";
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
        <div align="center"><a href="javascript:goAction(1)"><b>Trabajar Ruta</b></a></div>
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
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Código</div>
            </th>
            <th align=CENTER nowrap width="35%"> 
              <div align="center">Descripción</div>
            </th>
           </tr>
          <%
                EDP073501Help.initRow();
                chk = "checked";
                while (EDP073501Help.getNextRow()) {
                 
                  datapro.eibs.beans.EDP073501Message msgList = (datapro.eibs.beans.EDP073501Message) EDP073501Help.getRecord();
		 %>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
              <input type="radio" name="CURRCODE" value="<%= EDP073501Help.getCurrentRow() %>" <%=chk%> onClick="getParams(this.value,'<%= msgList.getE01CNORCD() %>','<%= msgList.getE01CNODSC() %>');">
            </td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01CNORCD() %></td>
			<td NOWRAP  align=CENTER width=\"35%\"><%= msgList.getE01CNODSC() %></td>
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