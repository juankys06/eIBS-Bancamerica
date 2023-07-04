<html> 
<head>
<title>Embossing de Plastico</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "msgCD" class= "datapro.eibs.beans.ECD0001DSMessage"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">

function goAction(op) {
	document.forms[0].opt.value = op;
	if (op == 4){
		if(confirm("Esta seguro que desea eliminar este registro?")){
			document.forms[0].submit();
		} 
	} else {
		document.forms[0].submit();
	}
}


</SCRIPT>  

</head>

<BODY>
<h3 align="center">Embossing de Plástico</h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECD0001" >
  <input type=HIDDEN name="SCREEN" value="102">
  <input type=HIDDEN name="opt" value="1">
  <input type=HIDDEN name="totalRow" value="0">
  <input type=HIDDEN name="CURRENTROW" value="">
  
  <%
	if ( appList.getNoResult() ) {
 %>
  <p>&nbsp;</p><TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>No hay resultados para su b&uacute;squeda</b></p>
          <table class="tbenter" width=100% align=center>
           <tr>
              <td class=TDBKG>
                <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
              </td>
           </tr>
         </table>
	  </div>

	  </TD>
	</TR>
    </TABLE>
	
  <%  } else {
  %>
<% 
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
        <div align="center"><a href="javascript:goAction(1)"><b>Nuevo</b></a></div>
      </td>
      <td class=TDBKG> 
        <div align="center"><a href="javascript:goAction(2)"><b>Mantenimiento</b></a></div>
      </td>
      <td class=TDBKG> 
        <div align="center"><a href="javascript:goAction(3)"><b>Recepción</b></a></div>
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
            <th align=CENTER nowrap width="10%">&nbsp;</th>
            <th align=CENTER nowrap width="20%"><div align="center"># Solicitud</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Tipo de Plástico</div></th>
            <th align=CENTER nowrap width="30%">Cantidad</th>
            <th align=CENTER nowrap width="30%">Cantidad <br>Recibida</th>
            <th align=CENTER nowrap width="20%">Fecha de <br>Solicitud</th>
            <th align=CENTER nowrap width="10%"><div align="center">Usuario</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Proveedor</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Estado</div></th>
          </tr>
     	<%
        appList.initRow(); 
		boolean firstTime = true;
		String chk = "";
        while (appList.getNextRow()) {
			if (firstTime) {
				firstTime = false;
				chk = "checked";
			} else {
				chk = "";
			}
           datapro.eibs.beans.ECD0001DSMessage msgPart = (datapro.eibs.beans.ECD0001DSMessage) appList.getRecord();
     	%>               
        <TR>
			<TD NOWRAP width="2%"><input type="radio" name="ROW" value="<%= appList.getCurrentRow()%>" <%=chk%> 
				onClick="document.forms[0].CURRENTROW.value = this.value;"></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01CDRNUM())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01CDRNPL())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01CDRQTY())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01CDRREQ())%></TD>
		  	<TD NOWRAP ALIGN="CENTER"><%= Util.formatDate(msgPart.getE01CDRDAY(),msgPart.getE01CDRMON(),msgPart.getE01CDRYEA()) %></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01CDRUSR())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01CDRNAM())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01CDRSTD())%></TD>
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
