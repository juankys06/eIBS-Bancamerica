<%@ page import ="datapro.eibs.master.Util" %>
<html>
<head>
<title>Parámetros de Penalización Money Market</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ESD020601Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
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
	}
	else {
		document.forms[0].opt.value = op;
	    document.forms[0].submit();
	}
}

</SCRIPT>  

</head>

<BODY>
<h3 align="center">Parámetros de Penalización Money Market<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="penalization_list.jsp, ESD0206"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSESD0206" >
  <p> 
    <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0"> 
    <input type=HIDDEN name="SCREEN" value="800">
    <input type=HIDDEN name="totalRow" value="0">
    <input type=HIDDEN name="opt">
  </p>
  <p> 
    <%
	if ( ESD020601Help.getNoResult() ) {
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
      <td class=TDBKG width="25%"> 
        <div align="center"><a href="javascript:goAction(1)"><b>Nueva</b></a></div>

     </td>
		<td class=TDBKG width="25%">
        <div align="center"><a href="javascript:goAction(2)"><b>Mantenimiento</b></a></div> 
     </td> 

	  <td class=TDBKG width="25%"> 
        <div align="center"><a href="javascript:goAction(3)"><b>Eliminar</b></a></div>
      </td>
      <td class=TDBKG width="25%"> 
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
            <th align=CENTER nowrap width="10%" colspan=3> 
              <div align="center">Rangos de Saldos Promedio Mensual</div>
            </th>
            <th align=CENTER nowrap width="10%" colspan=5> 
              <div align="center">Cheques Girados en el Mes Hasta</div>
            </th>
          </tr>
          <tr id="trdark"> 
            <th align=CENTER nowrap width="3%">&nbsp;</th>
            <th align=CENTER nowrap width="11%"> 
              <div align="center">Desde</div>
            </th>
            <th align=CENTER nowrap width="11%"> 
              <div align="center">Hasta</div>
            </th>
            <th align=CENTER nowrap width="15%"> 
              <div align="center">3</div>
            </th>
            <th align=CENTER nowrap width="15%"> 
              <div align="center">6</div>
            </th>
            <th align=CENTER nowrap width="15%"> 
              <div align="center">9</div>
            </th>
            <th align=CENTER nowrap width="15%"> 
              <div align="center">12</div>
            </th>
            <th align=CENTER nowrap width="15%"> 
              <div align="center">&gt; 12</div>
            </th>
            
           </tr>
          <%
                ESD020601Help.initRow();
                chk = "checked";
                while (ESD020601Help.getNextRow()) {
                 
                  datapro.eibs.beans.ESD020601Message msgList = (datapro.eibs.beans.ESD020601Message) ESD020601Help.getRecord();
		 %>
          <tr> 
            <td NOWRAP  align=CENTER width="3%"> 
              <input type="radio" name="CODE" value="<%= ESD020601Help.getCurrentRow() %>" <%=chk%>>
            </td>
            <td NOWRAP  align=CENTER width="11%"><%= msgList.getE01RTEMIN() %></td>
			<td NOWRAP  align=CENTER width="11%"><%= msgList.getE01RTEMAX() %></td>
			<td NOWRAP  align=CENTER width="15%"><%= msgList.getE01RTEC03() %>%</td>
			<td NOWRAP  align=CENTER width="15%"><%= msgList.getE01RTEC06() %>%</td>
			<td NOWRAP  align=CENTER width="15%"><%= msgList.getE01RTEC09() %>%</td>
			<td NOWRAP  align=CENTER width="15%"><%= msgList.getE01RTEC12() %>%</td>
			<td NOWRAP  align=CENTER width="15%"><%= msgList.getE01RTEC15() %>%</td>

         </tr>
          <%
              				chk = "";     
                }
              %>
        </table>
  </table>
     
  <SCRIPT language="JavaScript">
 showChecked("CODE");
     
</SCRIPT>

<%}%>

  </form>

</body>
</html>
