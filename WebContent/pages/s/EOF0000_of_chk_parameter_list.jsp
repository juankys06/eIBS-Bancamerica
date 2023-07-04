<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Formatos de Cheques de Gerencia</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EOF000001Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
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
<h3 align="center">Parametros de Cheques de Gerencia<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="of_chk_parameters_list.jsp, EOF0000"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEOF0000" >
  <p> 
    <input type=HIDDEN name="SCREEN" value="800">
    <input type=HIDDEN name="totalRow" value="0">
    <input type=HIDDEN name="BANK">
    <input type=HIDDEN name="BRANCH">
   
    <input type=HIDDEN name="opt">
  </p>
  <p> 
    <%
	if ( EOF000001Help.getNoResult() ) {
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
            <th align=CENTER nowrap width="10%">Formato</th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Descripci&oacute;n</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Cuenta Contable <br> Cheques x Imprimir</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Cuenta Contable <br> Cheques Impresos</div>
            </th>

          </tr>
          <%
                EOF000001Help.initRow();
				boolean firstTime = true;
				String chk = "";
        		while (EOF000001Help.getNextRow()) {
					if (firstTime) {
						firstTime = false;
						chk = "checked";
					} else {
						chk = "";
					}
                  	datapro.eibs.beans.EOF000001Message msgList = (datapro.eibs.beans.EOF000001Message) EOF000001Help.getRecord();
		 %>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
              <input type="radio" name="CURRCODE" value="<%= EOF000001Help.getCurrentRow() %>"  <%=chk%> >
            </td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01OFCFTY() %></td>
            <td NOWRAP  align=LEFT width=\"10%\"><%= msgList.getE01OFCDSC() %></td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01OFCXPR() %></td>
			<td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01OFCPRT() %></td>
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
