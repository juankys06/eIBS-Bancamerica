 <%@ page import = "datapro.eibs.master.Util" %>
 <%@ page import = "datapro.eibs.beans.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Banesco - Abonos Masivos, opcion manual - Aprobaci�n</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">

<jsp:useBean id= "extList" class= "datapro.eibs.beans.JBObjList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="javascript">

function goSubmit(value){
	if (value == 1) {
		document.forms[0].SCREEN.value = 4;
		document.forms[0].submit();
	}
	else {
		if (confirm("�Est� seguro que desea eliminar esta planilla?")) {
			document.forms[0].SCREEN.value = 6;
			document.forms[0].submit();
		}
	}
}



function changeChecked(field){

	for (i = 0; i < field.length; i++)
		field[i].checked = document.forms[0].hcheck.checked ;
}


function goAction(op) {
 var delok= false;
 var page="";
 if (op == 1) {
     page = prefix +language + 'EDL1112_ln_new_sch.jsp';
 } else if (op == 5) {
     page = prefix +language + 'EDL1112_ln_inq_sch.jsp?ROW=' + document.forms[0].ROW.value;
 }
   CenterWindow(page,600,470,2);
 }      

function goExit(){
  window.location.href="<%=request.getContextPath()%>/pages/background.jsp";

}

function verificahip(){
   for ( var i=0; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idxRow].className="trhighlight";
   document.forms[0].ROW.value = "" + idxRow;
//   document.forms[0].E01DEAACC.value = d1;
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


<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL1112">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
<INPUT TYPE=HIDDEN NAME="OPT" VALUE="3">
<INPUT TYPE=HIDDEN NAME="ROW" VALUE="0">

<h3 align="center">Abonos Masivos, Proceso Manual - Aprobaci�n<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_list_sch_app.jsp,EDL1112"> 
</h3>
<hr size="4">
  
<table class="tableinfo">
	<tr > 
    	<td nowrap > 
        	<table cellpadding=2 cellspacing=0 width="100%" border="0">
          
          		<tr id="trdark"> 
            		<td nowrap width="40%"><div align="right"><b>Compa�ia de Pago :</b></div></td>
            		<td nowrap width="60%" align="left"> 
                		<input type="text" readonly name="E01CNOMID" size="5" maxlength="4" value="<%= userPO.getHeader7().trim()%>">
                		<input type="text" readonly name="E01CNODSC" size="31" maxlength="30" value="<%= userPO.getHeader20().trim()%>">
            		</td>
          		</tr>
				<tr id="trclear"> 
           			<td nowrap width="40%"><div align="right"><b>Frecuencia :</b></div></td>
            		<td nowrap width="60%" align="left">
            			<input type="text" readonly name="E01FRECUE" size="20" maxlength="20" value=<%= userPO.getHeader5().equals("M") ? "MENSUAL" : userPO.getHeader5().equals("Q") ? "QUINCENAL" : "" %>>
            		</td>
          		</tr>
          		<tr id="trdark"> 
            		<td nowrap width="40%"><div align="right"><b>Hipotecas :</b></div></td>
            		<td nowrap width="60%" align="left"><input type="checkbox" readonly name="E01PRETYP" value="Y"  <%if (userPO.getHeader6().equals("Y")) out.print("checked"); %>></td>
          		</tr>
          		<tr id="trclear"> 
            		<td nowrap width="40%" align="right"><b>Descripci�n :</b></td>
            		<td nowrap width="60%"><input type="text" readonly name="E01NARRAT" size="31" maxlength="30" value="<%= userPO.getHeader9().trim()%>"></td>
          		</tr>
          		<tr id="trdark"> 
           			<td nowrap width="40%" align="right"><b>Total :</b></td>
            		<td nowrap width="60%"><input type="text" readonly name="E01TOTAMT" size="16" maxlength="15" value="<%= userPO.getHeader22().trim()%>"></td>
          		</tr>
          	</table>
    	</td>
	</tr>
	<tr > 
    	<td nowrap > 
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
			              <div align="center" > 
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
    	</td>
	</tr>
</table>

  
<TABLE class="tbenter">
  <TR> 
 <%
	if ( !(extList.getNoResult() )) {
 %>
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
          <p><b>No hay resultados que correspondan a su criterio de b�squeda.</b></p>
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
		      <TH ALIGN=CENTER nowrap>N�mero de Pr�stamo</TH>
		      <TH ALIGN=CENTER nowrap>Nombre Cliente</TH>
			  <TH ALIGN=CENTER nowrap>Identificaci�n</TH>
			  <TH ALIGN=CENTER nowrap>Sucursal</TH>
		      <TH ALIGN=CENTER nowrap>N�mero de Empleado</TH>
		      <TH ALIGN=CENTER nowrap>Valor Recibido</TH>
		      <TH ALIGN=CENTER nowrap>Valor Cuota</TH>
		    </TR>
		
		</TABLE>
  
    <div id="dataDiv1" class="scbarcolor" style="padding:0">
    <table id="dataTable"  >
    <%
         int row;
		 try{row = Integer.parseInt(request.getParameter("ROW"));} catch(Exception e){row = 0;}
         extList.initRow();        
         while (extList.getNextRow()) {
            EDL111201Message msgLN = (EDL111201Message) extList.getRecord();
     %> 
            <TR> 
              <TD ALIGN=CENTER NOWRAP> 
                <INPUT TYPE="radio" NAME="EXTCHG" VALUE="<%= extList.getCurrentRow() %>" <% if (extList.getCurrentRow() == row) out.print("checked"); %> onClick="showInfo(<%= extList.getCurrentRow() %>,'<%=msgLN.getE01DEAACC()%>')">
              </TD>
          <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(msgLN.getE01DEAACC()) %></TD>              
          <TD ALIGN=LEFT NOWRAP><%= Util.formatCell(msgLN.getE01CUSNA1()) %></TD>              
          <TD ALIGN=LEFT NOWRAP><%= Util.formatCell(msgLN.getE01CUSLN3()) %></TD>
          <TD ALIGN=RIGHT NOWRAP><%= Util.formatCell(msgLN.getE01CUSBRN()) %></TD>
          <TD ALIGN=LEFT NOWRAP><%= Util.formatCell(msgLN.getE01CUFTOM()) %></TD>
          <TD ALIGN=RIGHT NOWRAP><%= Util.formatCell(msgLN.getE01DLCVA1()) %></TD>
          <TD ALIGN=RIGHT NOWRAP><%= Util.formatCell(msgLN.getE01DLCVA2()) %></TD>              

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

  <div align="center"> 
 	<%	if ( !(extList.getNoResult() )) { %>
    <input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="javascript:goSubmit(1);">    
    <input id="EIBSBTN" type=button name="Submit" value="Eliminar" onClick="javascript:goSubmit(2);">
	<% } %>
  </div>
 


</FORM>

</BODY>
</HTML>
