<html>
<head>
<title>Entrada Transacciones Reconciliacion</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id= "glList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">
function goAction(op) {
	if(op==1){
		document.forms[0].SCREEN.value = 200;
	}
	else if (op==2){
		document.forms[0].SCREEN.value = 500;
	}
	document.forms[0].submit();
    
    /*if (op == "N") {
      <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
       
	} else 
	  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="300">  
		 
    }*/
 } 

</SCRIPT>  

</head>

<BODY>
<h3 align="center">Entrada Transacciones Reconciliacion<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="reconc_list_ent.jsp, ERC0000"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSERC0000" >
  <p> 
    <input type=hidden name="SCREEN" 	value="200">
    <input type=hidden name="totalRow" 	value="0">
    <input type=hidden name="BANK" 		value="<%=userPO.getBank()%>">
    <input type=hidden name="opt">
  </p>
  <p> 
  <%if ( glList.getNoResult() ) {%>
  </p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <TABLE class="tbenter" width="80%" >
  <TR>
  		<td class=TDBKG width="30%"> 
        	<div align="center"><a href="javascript:goAction('N')"><b>Nuevo</b></a></div>
      	</td>
      	<td class=TDBKG width="30%"> 
                <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salida</b></a></div>
         </td>
  </TR>
  </TABLE>
  <TABLE class="tbenter" width="80%" >
    <TR>
        <td align="center"> 
          <p><b>No hay resultados para su criterio de busqueda</b></p>
        </td>
	 
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
      <td class=TDBKG width="33%"> 
        <div align="center"><a href="javascript:goAction(1)"><b>Nuevo</b></a></div>
      </td>
		<td class=TDBKG width="33%"> 
        <div align="center"><a href="javascript:goAction(2)"><b>Mantenimiento</b></a></div>
      </td>
      <td class=TDBKG width="33%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salida</b></a></div>
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
            <th align=CENTER nowrap  >Banco</th>
            <th align=CENTER nowrap "> 
              <div align="center">Moneda</div>
            </th>
            <th align=CENTER nowrap  > 
              <div align="center">Cuenta<br>Contable</div>
            </th>
            <th align=CENTER nowrap  > 
              <div align="center">Cuenta</div>
            </th>
            <th align=CENTER nowrap  > 
              <div align="center">Fecha<br>Extracto</div>
            </th>
            <th align=CENTER nowrap  > 
              <div align="center">Saldo<br>Inicial</div>
            </th>
            <th align=CENTER nowrap  > 
              <div align="center">Saldo<br>Final</div>
            </th>
          </tr>
          <%
          glList.initRow();
          boolean firstTime = true;
          String chk = "";
          int count=0;
          while (glList.getNextRow()) {
				if (firstTime) {
					firstTime = false;
					chk = "checked";
				} else {
					chk = "";
				}
                 
                datapro.eibs.beans.ERC000001Message msgList = (datapro.eibs.beans.ERC000001Message) glList.getRecord();
		 %>
          <tr> 
                     
             <td NOWRAP  align=CENTER width="2%"> 
              <input type="radio" name="CURRCODE" value="<%= glList.getCurrentRow() %>" <%=chk%>>
             </td>

			 
            <td NOWRAP align="CENTER"><%= Util.formatCell(msgList.getE01RCIBNK()) %></td>
            <td NOWRAP align="CENTER"><%= Util.formatCell(msgList.getE01RCICCY()) %></td>
            <td NOWRAP align="CENTER"><%= Util.formatCell(msgList.getE01RCIGLN()) %></td>
            <td NOWRAP align="CENTER"><%= Util.formatCell(msgList.getE01RCIACC()) %></td>
             
            <TD NOWRAP ALIGN="CENTER"><%= Util.formatDate(msgList.getE01RCISD1(),msgList.getE01RCISD2(),msgList.getE01RCISD3()) %></TD>
            <td NOWRAP align="RIGHT" ><%= Util.formatCell(msgList.getE01RCISTI()) %></td>
            <td NOWRAP align="RIGHT" ><%= Util.formatCell(msgList.getE01RCISTF()) %></td>

 
            
          </tr>
          <input type="hidden" value="<%=msgList.getE01RCIBNK()%>" name="E01RCIBNK<%=count%>">
          <input type="hidden" value="<%=msgList.getE01RCICCY()%>" name="E01RCICCY<%=count%>">
          <input type="hidden" value="<%=msgList.getE01RCIGLN()%>" name="E01RCIGLN<%=count%>">
          <input type="hidden" value="<%=msgList.getE01RCIACC()%>" name="E01RCIACC<%=count%>">
          <input type="hidden" value="<%=msgList.getE01RCISD1()%>" name="E01RCISD1<%=count%>">
          <input type="hidden" value="<%=msgList.getE01RCISD2()%>" name="E01RCISD2<%=count%>">
          <input type="hidden" value="<%=msgList.getE01RCISD3()%>" name="E01RCISD3<%=count%>">
          <input type="hidden" value="<%=msgList.getE01RCISTI()%>" name="E01RCISTI<%=count%>">
          <input type="hidden" value="<%=msgList.getE01RCISTF()%>" name="E01RCISTF<%=count%>">
          <%
          count++;
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
