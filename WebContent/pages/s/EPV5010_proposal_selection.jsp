<html>
<head>
<title>TABLAS DE SERVICIOS</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EPV5000Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<%@ page import = "datapro.eibs.master.Util" %>
<script language="JavaScript">


function goAction(op) {

	document.forms[0].submit();
  
}


function getParams(propnum,cusnum) {

    document.forms[0].PROPNUM.value = propnum;
	document.forms[0].CUSNUM.value = cusnum;
	
    
}


</SCRIPT>  

</head>

<BODY>
<h3 align="center">Verificaci&oacute;n de Propuestas</h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.credprop.JSEPV5010" >
  <input type=HIDDEN name="SCREEN" value="700">
  <input type=HIDDEN name="opt" value="1">
  <input type=HIDDEN name="totalRow" value="0">
  <input type=HIDDEN name="PROPNUM" value="">
  <input type=HIDDEN name="CUSNUM" value="">
  
  
  <%
	if ( EPV5000Help.getNoResult() ) {
 %>
  <p>&nbsp;</p>
  <TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>No hay resultados para su b&uacute;squeda</b></p>
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
           
  <table class="tbenter" width=100% align=center>
    <tr> 
      <td class=TDBKG width="50%"> 
        <div align="center"><a href="javascript:goAction(2)"><b>Verificar</b></a></div>
      </td>
      <td class=TDBKG width="50%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
   
  <br>
  
  <TABLE  id=cfTable class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" width="100%">
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="10%">&nbsp;</th>
            <th align=CENTER nowrap width="20%"> 
              <div align="center">No. Propuesta</div>
            </th>
            <th align=CENTER nowrap width="40%">Cliente</th>
            <th align=CENTER nowrap width="40%"> 
              <div align="center">Estado</div>
            </th>
            <th align=CENTER nowrap width="40%"> 
              <div align="center">Fecha de <br>
                Propuesta</div>
            </th>
            <th align=CENTER nowrap width="40%"> 
              <div align="center">Vendedor</div>
            </th>
          </tr>
          <%
                EPV5000Help.initRow();
				int k=1;
				boolean chk = true;
                while (EPV5000Help.getNextRow()) {
                 datapro.eibs.beans.EPV500002Message msgList = (datapro.eibs.beans.EPV500002Message) EPV5000Help.getRecord();
                %>                 
                <TR>
                    <TD NOWRAP  ALIGN=CENTER width="10%">
                    	<input type="radio" name="ACCNUM" value="<%= EPV5000Help.getCurrentRow() %>" <% if (chk) { out.print("checked"); chk=false;}%>>
					</TD>
					<TD NOWRAP ALIGN=CENTER width="20%"><%= msgList.getE02PVMNUM()%></td>
					<TD NOWRAP ALIGN=LEFT width="30%"><%= msgList.getE02PVMNME()%></td>
					<TD NOWRAP ALIGN=CENTER width="40%"><%= msgList.getE02PVMSTS()%></td>
					<TD NOWRAP ALIGN=CENTER width="40%"><%= Util.formatDate(msgList.getE02PVMOP1(),msgList.getE02PVMOP1(),msgList.getE02PVMOP1())%></td>
					<TD NOWRAP ALIGN=LEFT width="40%"><%=  msgList.getPVMRTE()%></td>
				</TR>
                  
             <%     
                 k++;   
                }
              %>
        </table>
    </table>
     
  <%}%>

<SCRIPT language="JavaScript">
  
  showChecked("ACCNUM");
  
</SCRIPT>
</form>

</body>
</html>
