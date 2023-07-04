<%@ page import ="datapro.eibs.master.Util" %>
<html>
<head>
<title>Lista Codigos de Formatos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "EDP074901Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
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

function getParams(currrow) {

	document.forms[0].CURRENTROW.value = currrow;
}


</SCRIPT>  

</head>

<BODY>
<h3 align="center">Propuesta de Cr&eacute;dito <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="trace_list1.jsp, EDP0749"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0749" >
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
	if ( EDP074901Help.getNoResult() ) {
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
        <div align="center"><a href="javascript:goAction(1)"><b>Trabajar Propuesta</b></a></div>

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
              <div align="center">Propuesta</div>
            </th>

            <th align=CENTER nowrap width="05%"> 
              <div align="center">Cliente</div>
            </th>
            <th align=CENTER nowrap width="65%"> 
              <div align="center">Nombre</div>
            </th>
            <th align=CENTER nowrap width="05%"> 
              <div align="center">Fecha</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Sec-Actividad</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">OFC EJE</div>
            </th>
           </tr>
          <%
                EDP074901Help.initRow();
                chk = "checked";
                while (EDP074901Help.getNextRow()) {
                 
                  datapro.eibs.beans.EDP074901Message msgList = (datapro.eibs.beans.EDP074901Message) EDP074901Help.getRecord();
		 %>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
              <input type="radio" name="CURRCODE" value="<%= EDP074901Help.getCurrentRow() %>" <%=chk%> onClick="getParams(this.value,'<%= msgList.getE01PLPNPR() %>');">
            </td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01PLPNPR() %></td>
			<td NOWRAP  align=CENTER width=\"05%\"><%= msgList.getE01PLPCUN() %></td>
			<td NOWRAP  align=CENTER width=\"65%\"><%= msgList.getE01CUSNA1() %>
												   <%= msgList.getE01CUSNA2() %></td>	
			<td NOWRAP  align=CENTER width=\"05%\"><%= msgList.getE01PLPIPD() %>/<%= msgList.getE01PLPIPM() %>/<%= msgList.getE01PLPPLP() %></td>
			  <td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01PLPRUT() %>-<%= msgList.getE01PLPSRU() %>-<%= msgList.getE01PLPACT() %>-<%= msgList.getE01DESAC2() %> </td>
			<td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01PLPEJE() %></td>			
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
