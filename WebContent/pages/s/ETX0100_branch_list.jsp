<%@ page import ="datapro.eibs.master.Util" %>
<html> 
<head>
<title>Maestro de Agencias</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ETX010001Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">



function goAction(op) {

		
	if (op == "3") {
      ok = confirm("Esta seguro que desea eliminar el registro seleccionado?");
	  if (ok) 
	       {
	       document.forms[0].opt.value = op;
	       document.forms[0].submit();
	       }  
	  
	} else 
	
			{
	       document.forms[0].opt.value = op;
	       document.forms[0].submit();
	       }
	
	  
}


function getParams(currrow,bank,branch,code) {

	document.forms[0].CURRENTROW.value = currrow;
    document.forms[0].BANK.value = bank;
    document.forms[0].BRANCH.value = branch;
    document.forms[0].CODE.value = code;
}


</SCRIPT>  

</head>

<BODY>
<h3 align="center">Control de Timbres Fiscales <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="branch_list.jsp, ETX0100"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSETX0100" >
  <p> 
    <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0"> 
    <input type=HIDDEN name="SCREEN" value="800">
    <input type=HIDDEN name="totalRow" value="0">
    <input type=HIDDEN name="BANK" value="  " size=3 maxlength=2>
    <input type=HIDDEN name="BRANCH" value="   " size=4 maxlength=3>
    <input type=HIDDEN name="CODE" value="    " size=5 maxlength=4>
    
    
      
    <input type=HIDDEN name="opt">
  </p>
  <p> 
    <%
	if ( ETX010001Help.getNoResult() ) {
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
        <div align="center"><a href="javascript:goAction(1)"><b>Nueva</b></a></div>
      </td>
		<td class=TDBKG width="30%"> 
        <div align="center"><a href="javascript:goAction(2)"><b>Mantenimiento</b></a></div>
      </td>
	  <td class=TDBKG width="30%"> 
        <div align="center"><a href="javascript:goAction(3)"><b>Borrado</b></a></div>
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
              <div align="center">Oficina</div>
            </th>
            <th align=CENTER nowrap width="25%"> 
              <div align="center">Descripcion de Oficina</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Recaudador</div>
            </th>
            <th align=CENTER nowrap width="25%"> 
              <div align="center">Descripcion Recaudador</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Porcentaje</div>
            </th>
            
            
           </tr>
          <%
                ETX010001Help.initRow();
                chk = "checked";
                while (ETX010001Help.getNextRow()) {
                 
                  datapro.eibs.beans.ETX010001Message msgList = (datapro.eibs.beans.ETX010001Message) ETX010001Help.getRecord();
		 %>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
              <input type="radio" name="CURRCODE" value="<%= ETX010001Help.getCurrentRow() %>" <%=chk%> onClick="getParams(this.value,'<%= msgList.getE01TXBBNK() %>','<%= msgList.getE01TXBBRN() %>','<%= msgList.getE01TXBCDE() %>');">
            </td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01TXBBNK() %></td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01TXBBRN() %></td>
            <td NOWRAP  align=CENTER width=\"25%\"><%= msgList.getE01BRNNME() %></td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01TXBCDE() %></td>
            <td NOWRAP  align=CENTER width=\"25%\"><%= msgList.getE01CNODSC() %></td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01TXBPCT() %></td>
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
