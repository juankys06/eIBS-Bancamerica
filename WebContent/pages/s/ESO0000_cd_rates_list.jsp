<html>
<head>
<title>Negociación de Tasas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id= "cdList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "msgCD" class= "datapro.eibs.beans.ESO000002Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
 
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
 

<script language="JavaScript">

function goAction(op) {
	document.forms[0].opt.value = op;
	if (op == 4){
		if(confirm("¿Está seguro que desea eliminar esta tasa?")){
			document.forms[0].submit();
		} 
	} else {
		document.forms[0].submit();
	}
}	

</SCRIPT>  

</head>

<BODY>
<h3 align="center">Negociación de Tasas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cd_rates_list.jsp, ESO0000"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESO0000" >
  <input type=HIDDEN name="SCREEN" value="400">
  <input type=HIDDEN name="opt" value="1">
  <input type=HIDDEN name="totalRow" value="0">
  
  <%
	if ( cdList.getNoResult() ) {
 %>
  <p>&nbsp;</p><TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>No hay resultados para su b&uacute;squeda</b></p>
          <table class="tbenter" width=100% align=center>
           <tr>
            <td class=TDBKG>
        		<div align="center"><a href="javascript:goAction(1)"><b>Nueva</b></a></div>
              </td>
              <td class=TDBKG>
                <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
              </td>
           </tr>
         </table>
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
      <td class=TDBKG> 
        <div align="center"><a href="javascript:goAction(1)"><b>Nueva</b></a></div>
      </td>
      <td class=TDBKG> 
        <div align="center"><a href="javascript:goAction(2)"><b>Mantenimiento</b></a></div>
      </td>
      <td class=TDBKG> 
        <div align="center"><a href="javascript:goAction(4)"><b>Eliminar</b></a></div>
      </td>
      <td class=TDBKG> 
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
            <th align=CENTER nowrap width="2%">&nbsp;</th>
            <th align=CENTER nowrap width="18%"><div align="center">Cliente</div></th>
            <th align=CENTER nowrap width="20%"><div align="center">Nombre</div></th>
            <th align=CENTER nowrap width="20%"><div align="center">Tasa</div></th>
            <th align=CENTER nowrap width="20%"><div align="center">Monto</div></th>
            <th align=CENTER nowrap width="20%"><div align="center">Plazo</div></th>
          </tr>
     	<%
        cdList.initRow(); 
		boolean firstTime = true;
		String chk = "";
		String trc = "";
        while (cdList.getNextRow()) {
			if (firstTime) {
				firstTime = false;
				chk = "checked";
			} else {
				chk = "";
			}
           datapro.eibs.beans.ESO000002Message msgPart = (datapro.eibs.beans.ESO000002Message) cdList.getRecord();
           
           if (msgPart.getE02SOLTRC().equals("D"))
           		trc = "Día(s)";
           if (msgPart.getE02SOLTRC().equals("M"))
           		trc = "Mes(es)";
           if (msgPart.getE02SOLTRC().equals("Y"))
           		trc = "Año(s)";
           
     	%>               
        <TR>
			<TD NOWRAP width="2%"><input type="radio" name="ROW" value="<%= cdList.getCurrentRow()%>" <%=chk%> 
				onClick="document.forms[0].ROW.value = this.value;"></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE02SOLCUN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE02CUSNA1())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE02SOLRTE())%></TD>
		  	<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE02SOLPRI()) %></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE02SOLTRM() + " " + trc)%></TD>
		</TR>    		
    	<%}%>    
        </table>
    </table>
     
  <%}%>

<SCRIPT language="JavaScript">  
	showChecked("ROW"); 
</SCRIPT>
</form>

</body>
</html>
