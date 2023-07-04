<html>
<head> 
<title>Solicitud de Plastico</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">

function goAction(op) {

	document.forms[0].opt.value = op;
	document.forms[0].submit();
  
}

function PrintPreview() {

	var pg = "<%=request.getContextPath()%>/pages/s/ECD0002_plastic_dist_recep_inq_print.jsp";
	CenterWindow(pg,720,500,2);

}

</SCRIPT>  

</head>

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
 	 error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>
<BODY>
<h3 align="center">Distribución y Recepción de Plástico
<BR>Consulta
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="plastic_dist_recep_inq_list.jsp, ECD0002"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECD0002" >
  <input type=HIDDEN name="SCREEN" value="2">
  <input type=HIDDEN name="opt" value="101">
  <input type=HIDDEN name="totalRow" value="0">
  <input type=HIDDEN name="CURRENTROW" value="">
  
    <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"># Solicitud : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E02CDRNUM" size="15" maxlength="13" readonly value="<%= userPO.getHeader1() %>">
			  </div>
            </td>
            <td nowrap width="16%" > 
            </td>
            <td nowrap width="20%" > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">Oficina Origen : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E02CDRBRN" size="5" maxlength="4" readonly value="<%= userPO.getHeader2() %>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right">Oficina Destino : </div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"> 
                <input type="text" name="E02CDRBRD" size="5" maxlength="4" readonly value="<%= userPO.getHeader3() %>">
                 </div>
            </td>
          </tr>  
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Fecha Enviada : </div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E02CDRDAY" size="2" maxlength="2" readonly value="<%= userPO.getHeader4() %>">
                <input type="text" name="E02CDRMON" size="2" maxlength="2" readonly value="<%= userPO.getHeader5() %>">
                <input type="text" name="E02CDRYEA" size="4" maxlength="4" readonly value="<%= userPO.getHeader6() %>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Fecha Rec. : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E02CDRDAR" size="2" maxlength="2" readonly value="<%= userPO.getHeader7() %>">
                <input type="text" name="E02CDRMOR" size="2" maxlength="2" readonly value="<%= userPO.getHeader8() %>">
                <input type="text" name="E02CDRYER" size="4" maxlength="4" readonly value="<%= userPO.getHeader9() %>">
			  </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
 <%
	if ( appList.getNoResult() ) {
 %>
  <p>&nbsp;</p><TABLE class="tbenter" width="100%" >
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
%> 
  <p> 
  <table class="tbenter" width=100% align=center>
    <tr> 
      <td class=TDBKG> 
 		<div align="center"><a href="javascript:goAction(101)"><b>Cambio de <BR>Estado</b></a></div>
      </td>
      <td class=TDBKG> 
		<div align="center" style="cursor:hand" onClick="PrintPreview()"><a><b>Imprimir</b></a></div>
      </td>
      <td class=TDBKG> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
    <p> 
  <br>
  
  <TABLE  id=cfTable class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" width="100%">
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="10%">&nbsp;</th>
            <th align=CENTER nowrap width="20%"><div align="center">Tarjeta</div></th>
            <th align=CENTER nowrap width="20%"><div align="center">Usuario Envia</div></th>
            <th align=CENTER nowrap width="20%"><div align="center">Usuario Recibe</div></th>
            <th align=CENTER nowrap width="10%">Sts.</th>
			<th align=CENTER nowrap width="60%">Observación</th>
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
           datapro.eibs.beans.ECD0002DSMessage msgPart = (datapro.eibs.beans.ECD0002DSMessage) appList.getRecord();
     	%>               
        <TR>
			<TD NOWRAP width="2%"><input type="radio" name="ROW" value="<%= appList.getCurrentRow()%>" <%=chk%> 
				onClick="document.forms[0].CURRENTROW.value = this.value;"></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE02CDRINI())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE02CDRUSR())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE02CDRRUS())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE02CDRSTD())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE02CDROBS())%></TD>
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
