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
		document.forms[0].SCREEN.value = 300;
	}
	document.forms[0].submit();
 } 

</SCRIPT>  

</head>

<BODY>
<h3 align="center">Ajuste Transacciones Reconciliaci&oacute;n Bancaria<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="adjust_list_ent.jsp, ERC0060"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSERC0060" >
  <p> 
    <input type=hidden name="SCREEN">
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
  <TABLE class="tbenter" width="100%" >
    <TR>
    
      
        <div align="center"> 
          <p><b>No hay resultados para su criterio de busqueda</b></p>
        </div>
	 
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
        <div align="center"><a href="javascript:goAction(1)"><b>Ver Detalle</b></a></div>
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
              <div align="center">Cuenta Contable</div>
            </th>
            <th align=CENTER nowrap  > 
              <div align="center">N&uacute;mero Cuenta</div>
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
                 
                datapro.eibs.beans.ERC006001Message msgList = (datapro.eibs.beans.ERC006001Message) glList.getRecord();
		 %>
          <tr> 
                     
             <td NOWRAP  align=CENTER width="2%"> 
              <input type="radio" name="CURRCODE" value="<%= glList.getCurrentRow() %>" <%=chk%>>
             </td>

			 
            <td NOWRAP align="CENTER"><%= Util.formatCell(msgList.getE01RCTBNK()) %></td>
            <td NOWRAP align="CENTER"><%= Util.formatCell(msgList.getE01RCTCCY()) %></td>
            <td NOWRAP align="CENTER"><%= Util.formatCell(msgList.getE01RCTGLN()) %></td>
            <td NOWRAP align="CENTER"><%= Util.formatCell(msgList.getE01RCTACC()) %></td>

 
            
          </tr>
          <input type="hidden" value="<%=msgList.getE01RCTBNK()%>" name="E01RCTBNK<%=count%>">
          <input type="hidden" value="<%=msgList.getE01RCTCCY()%>" name="E01RCTCCY<%=count%>">
          <input type="hidden" value="<%=msgList.getE01RCTGLN()%>" name="E01RCTGLN<%=count%>">
          <input type="hidden" value="<%=msgList.getE01RCTACC()%>" name="E01RCTACC<%=count%>">
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
