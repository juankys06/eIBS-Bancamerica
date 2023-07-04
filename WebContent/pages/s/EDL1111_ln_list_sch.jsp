 <%@ page import = "datapro.eibs.master.Util" %>
 <%@ page import = "datapro.eibs.beans.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Banesco - Abonos Masivos, opcion automática</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">

<jsp:useBean id= "extList" class= "datapro.eibs.beans.JBObjList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="javascript">

function changeChecked(field){

	for (i = 0; i < field.length; i++)
		field[i].checked = document.forms[0].hcheck.checked ;
}


function goAction(op) {
 var delok= false;
 var page="";
 if (op == 1) {
     page = prefix +language + 'EDL1111_ln_new_sch.jsp';
 } else if (op == 2) {
     page = prefix +language + 'EDL1111_ln_maint_sch.jsp?ROW=' + document.forms[0].ROW.value;
 } else if (op == 4) {     
     page = prefix +language + 'EDL1111_ln_delete_sch.jsp?ROW=' + document.forms[0].ROW.value; 
 } else if (op == 5) {     
     page = prefix +language + 'EDL1111_ln_inquiry_sch.jsp?ROW=' + document.forms[0].ROW.value; 
 }
   CenterWindow(page,800,670,2);
 }      

function goExit(){
  window.location.href="<%=request.getContextPath()%>/pages/background.jsp";

}

function showInfo(idxRow){
   for ( var i=0; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idxRow].className="trhighlight";
   document.forms[0].ROW.value = "" + idxRow;
//   document.forms[0].E01DEAACC.value = d1;
  } 

</script>
</HEAD>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<BODY>


<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL1111">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="OPT" VALUE="3">
<INPUT TYPE=HIDDEN NAME="ROW" VALUE="0">

<h3 align="center">Abonos Masivos, Proceso Automático<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_list_sch.jsp,EDL1111"> 
</h3>
<hr size="4">
  
  <table class="tableinfo">
    <tr > 
      <td nowrap height="143"> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="32%"> 
              <div align="right"><b>Compañia de Pago :</b></div>
            </td>
            <td nowrap width="3%">&nbsp;</td>
            <td nowrap width="23%"> 
              <div align="left"> 
                <input type="hidden" name="E01CNOMID" size="5" maxlength="4" value="<%= userPO.getHeader7().trim()%>">
                <input type="text" readonly name="E01CNODSC" size="31" maxlength="30" value="<%= userPO.getHeader20().trim()%>">
              </div>
            </td>
          </tr>

          <tr id="trclear"> 
            <td nowrap width="32%"> 
              <div align="right"><b>Descripción :</b></div>
            </td>
            <td nowrap width="3%">&nbsp;</td>
            <td nowrap width="23%"> 
	             <input type="text" readonly name="E01NARRAT" size="31" maxlength="30" value="<%= userPO.getHeader9().trim()%>">
            </td>
          </tr>

          <tr id="trdark"> 
            <td nowrap width="32%"> 
              <div align="right"><b>Clave de Descuento :</b></div>
            </td>
            <td nowrap width="3%">&nbsp;</td>
            <td nowrap width="23%"> 
	             <input type="text" readonly name="E01DESCTO" size="8" maxlength="6" value="<%= userPO.getHeader6().trim()%>">
            </td>
          </tr>


          <tr id="trclear"> 
            <td nowrap width="32%"> 
              <div align="right"><b>Total :</b></div>
            </td>
            <td nowrap width="3%">&nbsp;</td>
            <td nowrap width="23%"> 
	             <input type="text" readonly name="E01TOTAMT" size="16" maxlength="15" value="<%= userPO.getHeader22().trim()%>">
            </td>
          </tr>


			<table class="tableinfo">
			    <tr> 
			      <td nowrap >
			        <table cellspacing=2 cellpadding=2 width="100%" border="0">
			          <tr id="trdark"> 
			            <td nowrap  > 
			              <div align="center">Concepto</div>
			            </td>
			            <td nowrap  > 
			              <div align="center">Banco</div>
			            </td>
			            <td nowrap  > 
			              <div align="center">Sucursal</div>
			            </td>
			            <td nowrap > 
			              <div align="center">Moneda</div>
			            </td>
			            <td nowrap > 
			              <div align="center">Referencia</div>
			            </td>
			          </tr>
			          <tr id="trclear"> 
			            <td nowrap  > 
			              <div align="center" nowrap> 
			                <input type=text readonly name="E01OFFOPC" value="<%= userPO.getHeader10().trim()%>" size="3" maxlength="3">
			                <input type=HIDDEN name="E01OFFGLN" value="<%= userPO.getHeader11().trim()%>">
			                <input type="text" readonly size="26" maxlength="25" readonly name="E01OFFCON" value="<%= userPO.getHeader12().trim()%>">
			                <input type="text" readonly size="31" maxlength="30" readonly name="E01GLMDSC" value="<%= userPO.getHeader21().trim()%>">
			              </div>
			            </td>
			            <td nowrap  > 
			              <div align="center"> 
			                <input type="text" readonly size="2" maxlength="2" name="E01OFFBNK" value="<%= userPO.getHeader13().trim()%>">
			              </div>
			            </td>
			            <td nowrap  "> 
			              <div align="center"> 
			                <input type="text" readonly size="3" maxlength="3" name="E01OFFBRN" value="<%= userPO.getHeader14().trim()%>">
			              </div>
			            </td>
			            <td nowrap   > 
			              <div align="center"> 
			                <input type="text" readonly size="3" maxlength="3" name="E01OFFCCY" value="<%= userPO.getHeader15().trim()%>">
			              </div>
			            </td>
			            <td nowrap   > 
			              <div align="center"> 
			                <input type="text" readonly size="16" maxlength="13" name="E01OFFACC" value="<%= userPO.getHeader16().trim()%>">
			               </div>
			            </td>
			          </tr>
			        </table>
			        
			      </td>
			    </tr>
			  </table>
  </table>
 </td>
</tr>
</table>

  
<TABLE class="tbenter">
  <TR> 
<%--    <TD ALIGN=CENTER CLASS=tdbkg><a href="javascript:goAction('1')">Nuevo</a></TD>  --%>
 <%
	if ( !(extList.getNoResult() )) {
 %>
    <TD ALIGN=CENTER CLASS=tdbkg><a href="javascript:goAction('2')">Modificar</a></TD>
    <TD ALIGN=CENTER CLASS=tdbkg><a href="javascript:goAction('4')">Eliminar</a></TD>
    <TD ALIGN=CENTER CLASS=tdbkg><a href="javascript:goAction('5')">Consultar</a></TD>
 <% } %>
    <TD ALIGN=CENTER CLASS=tdbkg><a href="javascript:goExit()">Salir</a></TD>
  </TR>
</TABLE>




<br>
<%
	if ( extList.getNoResult() ) {
 %>
   	<TABLE class="tbenter" width=100% height=40%>
   	 <TR valign="middle">
      <TD> 
        <div align="center">       		
          <p><b>No hay resultados que correspondan a su criterio de búsqueda.</b></p>
          </div>
      </TD>
     </TR>
   	</TABLE>
<%   		
	}
	else {
%>  
 
 <TABLE  id="mainTable" class="tableinfo" >
 <TR> 
   <TD NOWRAP valign="top" width="100%">
		<TABLE id="headTable" >
		
		    <TR id=trdark> 
		      <TH ALIGN=CENTER nowrap>&nbsp;</TH>
			  <TH ALIGN=CENTER nowrap>Identificación</TH>
		      <TH ALIGN=CENTER nowrap>Nombre Cliente</TH>
		      <TH ALIGN=CENTER nowrap>Número de Empleado</TH>
		      <TH ALIGN=CENTER nowrap>Número de Préstamo</TH>
		      <TH ALIGN=CENTER nowrap>Valor Cuota</TH>
		      <TH ALIGN=CENTER nowrap>Validación</TH>
		    </TR>
		
		</TABLE>
  
    <div id="dataDiv1" class="scbarcolor" style="padding:0">
    <table id="dataTable"  >
    <%
         int row;
		 try{row = Integer.parseInt(request.getParameter("ROW"));}catch(Exception e){row = 0;}
         extList.initRow();        
         while (extList.getNextRow()) {
            EDL111101Message msgLN = (EDL111101Message) extList.getRecord();
     %> 
            <TR> 
              <TD ALIGN=CENTER NOWRAP> 
                <INPUT TYPE="radio" NAME="EXTCHG" VALUE="<%= extList.getCurrentRow() %>" <% if (extList.getCurrentRow() == row) out.print("checked"); %> onClick="showInfo(<%= extList.getCurrentRow() %>,'<%=msgLN.getE01DEAACC()%>')">
              </TD>
          <TD ALIGN=LEFT NOWRAP><%= Util.formatCell(msgLN.getE01CUSLN3()) %></TD>
          <TD ALIGN=LEFT NOWRAP><%= Util.formatCell(msgLN.getE01CUSNA1()) %></TD>              
          <TD ALIGN=LEFT NOWRAP><%= Util.formatCell(msgLN.getE01CUFTOM()) %></TD>
          <TD ALIGN=RIGHT NOWRAP><%= Util.formatCell(msgLN.getE01DEAACC()) %></TD>              
          <TD ALIGN=RIGHT NOWRAP><%= Util.formatCell(msgLN.getE01DLCVA1()) %></TD>              
          <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(msgLN.getH01SCRCOD()) %></TD>              

     </TR>
     <% } %>
    </table>
    </div>
   </TD> 
  </TR>	
 </TABLE>
 
  <SCRIPT language="JavaScript">  	
  	function resizeDoc() {
       adjustEquTables(headTable, dataTable, dataDiv1,1,false);
    }
    showChecked("EXTCHG");
    resizeDoc();
    window.onresize=resizeDoc;     
  </SCRIPT>
  <%
  }
 %>
 


</FORM>

</BODY>
</HTML>
