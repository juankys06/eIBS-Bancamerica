<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import ="datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Lista de Garantías
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "gaMant" class= "datapro.eibs.beans.ERA001101Message"  scope="session"/>
<jsp:useBean id= "collList" class= "datapro.eibs.beans.JBObjList"  scope="session" /> 
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" /> 
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">
<!--
//-->

function setOptMenu(purpose) {
	if (purpose == "INQUIRY") {
    	builtNewMenu(colla_i_opt);
	} else {
		if (purpose == "APPROVAL_INQ") {
    		builtNewMenu(colla_A_opt);
    	} else {	
    		builtNewMenu(colla_M_opt);
    	}
    }		
    initMenu();
}

function goAction(op) {
    document.forms[0].opt.value = op;
    if (op == 3) {// Ask for confirmation in order to delete
		if (!confirm("Esta seguro que desea eliminar este detalle?")) return;
	}
	if (op==1) { //New
		document.forms[0].actRow.value = -1;
	}
	document.forms[0].submit();
}

//-->
function showAddInfo(idxRow, seq){
   for ( var i=0; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idxRow].className="trhighlight";
   document.forms[0].actRow.value = idxRow;
   document.forms[0].SEQ.value = seq;
} 
</script>

<SCRIPT Language="Javascript">
	setOptMenu("<%= userPO.getPurpose() %>");
</SCRIPT>

</head>

<body onLoad="MM_preloadImages('<%=request.getContextPath()%>/images/s/nueva_over.gif','<%=request.getContextPath()%>/images/s/modificar_over.gif','<%=request.getContextPath()%>/images/s/exit_over.gif','<%=request.getContextPath()%>/images/s/delete_over.gif')">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 

<h3 align="center">Listado de Otras Polizas de Seguro
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ga_poliza_list_detail.jsp,ERA0011">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0011">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="6">
<INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
<INPUT TYPE=HIDDEN NAME="actRow" VALUE="">
<INPUT TYPE=HIDDEN NAME="SEQ" VALUE="">

<%
	if ( collList.getNoResult() ) {
       String action = "";
	   try {
			action = userPO.getHeader20();
			userPO.setHeader20("");
       } catch (Exception e) {
       		action = "";
       }
       String pagename = "";
       try {
       		pagename = userPO.getHeader21();
       		userPO.setHeader21("");
       } catch (Exception e) {
       		pagename = "";
       }
       if ( action.equals("DO_MAINT") || action.equals("DO_NEW")) {
%>
       		<SCRIPT Language="Javascript">
<% 
            if ( !pagename.equals("") ) {
%>
	   			CenterWindow('<%= pagename %>',620,500,2);
<% 
            }
%>
            </SCRIPT>
<% 
		}
%> 
   		<TABLE class="tbenter" width=100% height=90%>
   			<TR>
      			<TD> 
        			<div align="center"> 
          				<table class="tableinfo">
            				<tr> 
              					<td nowrap> 
                					<table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
                  						<tr id="trdark"> 
                    						<td nowrap><div align="right"><b>Cliente :</b></div></td>
                    						<td nowrap><div align="left"><%=userPO.getCusNum().trim()%></div></td>
                    						<td nowrap><div align="right"><b>Nombre :</b></div></td>
                    						<td nowrap><div align="left"><%= userPO.getCusName().trim()%></div></td>
                    						<td nowrap><div align="right"><b>Referencia :</b></div></td>
                    						<td nowrap><%= userPO.getIdentifier().trim()%></td>
                  						</tr>
                					</table>
              					</td>
            				</tr>
          				</table>
          				<p>&nbsp;</p>
          				<p><b>El cliente seleccionado a&uacute;n no posee Otras Polizas 
            				  asignadas, por favor elija una de las siguientes opciones</b></p>
          				<table class="tbenter" width="100%">
            				<tr> 
              					<td align=CENTER> <a href="javascript:goAction(1)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image11','','<%=request.getContextPath()%>/images/s/nueva_over.gif',1)"><img name="Image11" border="0" src="<%=request.getContextPath()%>/images/s/nueva.gif"  ></a></td>
              					<td align=CENTER> <a href="<%=request.getContextPath()%>/pages/background.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image51','','<%=request.getContextPath()%>/images/s/EXIT_OVER.gif',1)"><img name="Image51" border="0" src="<%=request.getContextPath()%>/images/s/EXIT.gif" ></a></td>
            				</tr>
          				</table>
          				<p>&nbsp; </p>
        			</div>
      			</TD>
      		</TR>
   		</TABLE>
<% 	} else {
      	String action = "";
		try {
			action = userPO.getHeader20();
			userPO.setHeader20("");
        } catch (Exception e) {
       		action = "";
        }
        String pagename = "";
        try {
       	 	pagename = userPO.getHeader21();
       	 	userPO.setHeader21("");
        } catch (Exception e) {
       		pagename = "";
       	}               
       if ( action.equals("DO_MAINT") || action.equals("DO_NEW")) {
%>
       		<SCRIPT Language="Javascript">
<% 
            	if ( !pagename.equals("") ) {
%>
	   			CenterWindow('<%= pagename %>',620,500,2);
<% 
              	}
%>
             </SCRIPT>
<% 
		}
%> 

		<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
		<table class="tableinfo">
    		<tr> 
      			<td nowrap> 
        			<table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          				<tr id="trdark"> 
            				<td nowrap width="50%"><div align="right"><b>Referencia :</b></div></td>
            				<td nowrap width="50%"><%= userPO.getIdentifier().trim()%></td>
          				</tr>
        			</table>
      			</td>
    		</tr>
		</table>

		<h4>Inscripción Principal</h4>

		<TABLE class="tableinfo">
			<TR id="trdark"> 
				<TH ALIGN=CENTER NOWRAP>Codigo Cliente</TH>
				<TH ALIGN=CENTER NOWRAP>ID</TH>
				<TH ALIGN=CENTER NOWRAP>Oficina<BR>Propietaria</TH>
				<TH ALIGN=CENTER NOWRAP>Grado</TH>
				<TH ALIGN=CENTER NOWRAP>Valor Limite</TH>
				<TH ALIGN=CENTER NOWRAP>Ltda<BR>Oper</TH>
				<TH ALIGN=CENTER NOWRAP>Balance<BR>V. Limite</TH>
				<TH ALIGN=CENTER NOWRAP>Fecha<BR>Vencimiento</TH>
			</TR>
			<TR>
				<td nowrap align="center"><%= gaMant.getE01ROCCUN() %></td>
				<td nowrap align="center"><%= gaMant.getE01RUTO01() %></td>
				<td nowrap align="center"><%= gaMant.getE01ROCOF2() %></td>
				<td nowrap align="center"><%= gaMant.getE01ROCUC1() %></td>
				<td nowrap align="center"><%= gaMant.getE01ROCAPA() %></td>
				<td nowrap align="center"><%= (gaMant.getE01ROCUC2().equals("S") ? "SI" : "NO") %></td>
				<td nowrap align="center"><%= gaMant.getE01ROCAM2() %></td>
				<td nowrap align="center"><%= gaMant.getE01ROCMT1() %>/<%= gaMant.getE01ROCMT2() %>/<%= gaMant.getE01ROCMT3() %></td>
			</TR>
		</table>

		<br>

  		<table class="tbenter" width="100%">
    		<tr> 
      			<td align=CENTER> <a href="javascript:goAction(1)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1','','<%=request.getContextPath()%>/images/s/new_over.gif',1)"><img name="Image1" border="0" src="<%=request.getContextPath()%>/images/s/new.gif" ></a></td>
      			<td align=CENTER> <a href="javascript:goAction(2)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image2','','<%=request.getContextPath()%>/images/s/maintenance_over.gif',1)"><img name="Image2" border="0" src="<%=request.getContextPath()%>/images/s/maintenance.gif" ></a></td>
      			<td align=CENTER> <a href="javascript:goAction(3)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image3','','<%=request.getContextPath()%>/images/s/delete_over.gif',1)"><img name="Image3" border="0" src="<%=request.getContextPath()%>/images/s/delete.gif" ></a></td>
      			<td align=CENTER> <a href="<%=request.getContextPath()%>/pages/background.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image5','','<%=request.getContextPath()%>/images/s/EXIT_OVER.gif',1)"><img name="Image5" border="0" src="<%=request.getContextPath()%>/images/s/EXIT.gif" ></a></td>
    		</tr>
  		</table>
  
		<h4>Otras inscripciones</h4>

 		<TABLE  id="mainTable" class="tableinfo">
 			<TR> 
   				<TD NOWRAP valign="top">
        			<TABLE id="headTable">
          				<TR id="trdark"> 
				            <TH ALIGN=CENTER NOWRAP></TH>
				            <TH ALIGN=CENTER NOWRAP>Compa&ntilde;&iacute;a</TH>
				            <TH ALIGN=CENTER NOWRAP>Nombre</TH>
				            <TH ALIGN=CENTER NOWRAP>N&uacute;mero<BR>P&oacute;liza</TH>
				            <TH ALIGN=CENTER NOWRAP>Moneda</TH>
				            <TH ALIGN=CENTER NOWRAP>Monto</TH>
          				</TR>
        			</TABLE>
        
    				<div id="dataDiv1" class="scbarcolor" style="padding:0">
        				<table id="dataTable">
    					<%
					         int row;
							 try{row = Integer.parseInt(request.getParameter("ROW"));}catch(Exception e){row = 0;}
					         collList.initRow();
					         int k=1;
					         while (collList.getNextRow()) {
					         	datapro.eibs.beans.ERA001103Message collDet = (datapro.eibs.beans.ERA001103Message) collList.getRecord();                   
					     %> 
	            				<TR> 
	              					<TD ALIGN=CENTER NOWRAP><INPUT TYPE="radio" NAME="ROW" VALUE="<%= collList.getCurrentRow() %>" <% if (collList.getCurrentRow() == row) out.print("checked"); %> onClick="showAddInfo(<%= collList.getCurrentRow() %>, '<%= collDet.getE03ROCCIN() %>')"></TD>
					              	<TD ALIGN=CENTER NOWRAP><%= Util.formatCell(collDet.getE03ROCICN()) %></TD>
					              	<TD ALIGN=CENTER NOWRAP><%= Util.formatCell(collDet.getE03ROCICM()) %></TD>
					              	<TD ALIGN=CENTER NOWRAP><%= Util.formatCell(collDet.getE03ROCCIN()) %></TD>
					              	<TD ALIGN=CENTER NOWRAP><%= Util.formatCell(collDet.getE03ROCICY()) %></TD>
					              	<TD ALIGN=CENTER NOWRAP><%= Util.fcolorCCY(collDet.getE03ROCIPA()) %></TD>
	            				</TR>
      					<%
              					k++;
         					}        
    					%> 
          				</table>
   					</div>
   				</TD>
  			</TR>	
		</TABLE>
      
		<SCRIPT language="JavaScript">
		  document.forms[0].totalRow.value="<%= k %>";
		  function resizeDoc() {
		       divResize();
		       adjustEquTables(headTable, dataTable, dataDiv1,1,false);
		  }
		  showChecked("ROW");
		  resizeDoc();
		  window.onresize=resizeDoc;
		     
		</SCRIPT>


<%
	}
%> 
</FORM>

</BODY>
</HTML>
