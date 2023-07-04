<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Stop Payments
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "stop" class= "datapro.eibs.beans.JBListRec"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">
<!--
//-->
  function goAction(op) {

    document.forms[0].opt.value = op;
    if (op == 1) {
          document.forms[0].submit();
	 }
	 else {
      	 var formLength= document.forms[0].elements.length;
           var ok = false;
		   var en = "";
           for(n=0;n<formLength;n++)
           {
            	var elementName= document.forms[0].elements[n].name;
            	if(elementName == "CKROW") 
            	{
					if (document.forms[0].elements[n].checked == true) {
               			ok = true;
						en = 'SEQ_' + document.forms[0].elements[n].value;
              			break;
					}
            	}
            }
      	 if ( ok ) {
                if (op == 4) {
					for(n=0;n<formLength;n++) {
      					var l = document.forms[0].elements[n].name;
						if (l == en) {
							window.location.href="<%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM000?SCREEN=1&INTERFACE=B&OPE_CODE=SP&SEQ=" + document.forms[0].elements[n].value;
							break;
						}
					}
				}
				else {	
					if (op == 3) {
//						 Ask for confirmation in order to delete
						if (!confirm("Esta seguro que desea eliminar esta orden de no pago?")) return;
					}
					document.forms[0].submit();
				}
           }
           else {
      			alert("A valid account must be selected before this operation");	   
           }
	 }
	 
  }

//-->
function showAddInfo(idxRow){
   for ( var i=0; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idxRow].className="trhighlight";
  } 
</script>
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
<h3 align="center">Suspensiones de Pagos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="of_stop_pay.jsp , EDD0180"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0180" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="8">
<INPUT TYPE=HIDDEN NAME="opt" VALUE="1">

<%
	if ( stop.getNoResult() ) {

      	String action = "";
	try {
		action = userPO.getHeader20();
		userPO.setHeader20("");
       }
       catch (Exception e) {
       	action = "";
       }
       String pagename = "";
       try {
       	pagename = userPO.getHeader21();
       	userPO.setHeader21("");
       }
       catch (Exception e) {
       	pagename = "";
       }
               
       if ( action.equals("DO_MAINT") || action.equals("DO_NEW") || action.equals("DO_CLEAR")) {
%>
       	<SCRIPT Language="Javascript">
<% 
              if ( !pagename.equals("") ) {
%>
	   		CenterWindow('<%= pagename %>',580,410,2);
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
    		<tr > 
      		<td nowrap> 
        	  <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          		<tr id="trdark"> 
            	<td nowrap width="16%" > 
              		<div align="right"><b>Formato :</b></div>
            	</td>
            	<td nowrap width="7%" > 
              		  <div align="left"> 
                        <input type="text" size="9" maxlength="9" name="FTY" value="<%= userPO.getHeader11().trim()%>" readonly>
                      </div>
            	</td>
            	<td nowrap colspan="3" width="77%" > 
              	<div align="left"> 
                	<input type="text" size="45" maxlength="35" name="DSC" value="<%= userPO.getHeader12().trim()%>" readonly>
              	</div>
            	</td>
          		</tr>
        	  </table>
      		</td>
    		</tr>
  		</table>
		<p>&nbsp;</p>
          <p><font size="3"><b>El formato elegido no posee Ordenes de No Pago emitidas, 
            por favor escoja una de las </b></font><font size="3"><b>siguientes 
            opciones:</b></font></p>
          <table class="tbenter" width="100%">
            <tr> 
              <td align=CENTER> <a href="javascript:goAction(1)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image11','','<%=request.getContextPath()%>/images/s/nueva_over.gif',1)"><img name="Image11" border="0" src="<%=request.getContextPath()%>/images/s/nueva.gif"  ></a></td>
              <td align=CENTER> <a href="<%=request.getContextPath()%>/pages/background.jsp" onClick="top.close()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image51','','<%=request.getContextPath()%>/images/s/exit_over.gif',1)"><img name="Image51" border="0" src="<%=request.getContextPath()%>/images/s/EXIT.gif" ></a></td>
            </tr>
          </table>
          <p>&nbsp; </p>
        </div>
      </TD></TR>
   		</TABLE>
  <%   		
	}
	else {
%> <% 
      	String action = "";
	try {
		action = userPO.getHeader20();
		userPO.setHeader20("");
       }
       catch (Exception e) {
       	action = "";
       }
       String pagename = "";
       try {
       	pagename = userPO.getHeader21();
       	userPO.setHeader21("");
       }
       catch (Exception e) {
       	pagename = "";
       }
               
       if ( action.equals("DO_MAINT") || action.equals("DO_NEW") || action.equals("DO_CLEAR")) {
%>
       	<SCRIPT Language="Javascript">
<% 
              if ( !pagename.equals("") ) {
%>
	   		CenterWindow('<%= pagename %>',580,410,2);
<% 
              }
%>
              </SCRIPT>
<% 
	}
%> 

  <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0"> 
		<table class="tableinfo">
    		<tr > 
      		<td nowrap> 
        	  <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          		<tr id="trdark"> 
            	<td nowrap width="16%" > 
              		<div align="right"><b>Formato :</b></div>
            	</td>
            	<td nowrap width="7%" > 
              		<div align="left"> 
                	<input type="text" size="9" maxlength="9" name="FTY" value="<%= userPO.getHeader11().trim()%>" readonly>
              		</div>
            	</td>
            	<td nowrap colspan="3" width="77%" > 
              	<div align="left"> 
                	<input type="text" size="45" maxlength="35" name="DSC" value="<%= userPO.getHeader12().trim()%>" readonly>
              	</div>
            	</td>
          		</tr>
        	  </table>
      		</td>
    		</tr>
  		</table>
  <table class="tbenter" width="100%">
    <tr> 
      <td align=CENTER> <a href="javascript:goAction(1)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1','','<%=request.getContextPath()%>/images/s/new_over.gif',1)"><img name="Image1" border="0" src="<%=request.getContextPath()%>/images/s/new.gif" ></a></td>
      <td align=CENTER> <a href="javascript:goAction(2)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image2','','<%=request.getContextPath()%>/images/s/maintenance_over.gif',1)"><img name="Image2" border="0" src="<%=request.getContextPath()%>/images/s/maintenance.gif" ></a></td>
      <td align=CENTER> <a href="javascript:goAction(3)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image3','','<%=request.getContextPath()%>/images/s/delete_over.gif',1)"><img name="Image3" border="0" src="<%=request.getContextPath()%>/images/s/delete.gif" ></a></td>
      <%--
      <td align=CENTER> <a href="javascript:goAction(5)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image3','','<%=request.getContextPath()%>/images/s/clear_over.gif',1)"><img name="Image3" border="0" src="<%=request.getContextPath()%>/images/s/clear.gif" ></a></td>
      <td align=CENTER><a href="javascript:goAction(4)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image4','','<%=request.getContextPath()%>/images/s/imprimir_over.gif',1)"><img name="Image4" border="0" src="<%=request.getContextPath()%>/images/s/imprimir.gif"  ></a></td>
       --%>
      <td align=CENTER> <a href="<%=request.getContextPath()%>/pages/background.jsp" onClick="top.close()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image5','','<%=request.getContextPath()%>/images/s/exit_over.gif',1)"><img name="Image5" border="0" src="<%=request.getContextPath()%>/images/s/EXIT.gif" ></a></td>
    </tr>
  </table>
  <TABLE  id="mainTable" class="tableinfo"  NOWRAP>
 <TR> 
   <TD NOWRAP valign="top" width="100%" >
        <TABLE id="headTable"  NOWRAP>
          <TR id="trdark"> 
            <TH ALIGN=CENTER rowspan="2" NOWRAP ></TH>
            <TH ALIGN=CENTER colspan="3" NOWRAP>Cheques</TH>
            <TH ALIGN=CENTER rowspan="2" NOWRAP >Monto del Cheque</TH>
            <TH ALIGN=CENTER rowspan="2" NOWRAP >Comentarios</TH>
     </TR>
     <TR id="trdark">  
            <TH ALIGN=CENTER NOWRAP >Secuencia</TH>
            <TH ALIGN=CENTER NOWRAP >N&uacute;mero</TH>
            <TH ALIGN=CENTER NOWRAP > 
              <DIV ALIGN=CENTER NOWRAP>Fecha /Hora de Suspensi&oacute;n</DIV>
            </TH>
     </TR>
    </TABLE>
    <div id="dataDiv1" class="scbarcolor" style="padding:0" NOWRAP>
    <table id="dataTable"  NOWRAP>
    <%
               int row;
		  			try{row = Integer.parseInt(request.getParameter("ROW"));}catch(Exception e){row = 0;}
               stop.initRow();
                int k=1;
               while (stop.getNextRow()) {
     %> 
    <TR> 
      <TD ALIGN=CENTER NOWRAP> 
        <INPUT TYPE="radio" NAME="CKROW" VALUE="<%= stop.getCurrentRow() %>" <% if (stop.getCurrentRow() == row) out.print("checked"); %> onClick="showAddInfo(<%= stop.getCurrentRow() %>)">
		<INPUT TYPE="hidden" NAME="SEQ_<%= stop.getCurrentRow() %>" VALUE="<%= stop.getRecord(0) %>">
      </TD>
      <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(stop.getRecord(0)) %></TD>
      <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(stop.getRecord(1)) %></TD>
      <TD ALIGN=CENTER NOWRAP><%= Util.formatDate(stop.getRecord(3),stop.getRecord(4),stop.getRecord(5)) %> - <%=Util.formatTime(stop.getRecord(6))%></TD>
      <TD ALIGN=RIGHT NOWRAP><%= Util.fcolorCCY(stop.getRecord(7)) %></TD>
      <TD ALIGN=CENTER NOWRAP><div nowrap><%= Util.formatCell(stop.getRecord(8)) %></div></TD>
    </TR>
    <%
                k++;
                }
        if ( k > 10 ) {
      %>
                    		 <SCRIPT Language="Javascript">
                    		    document.forms[0].totalRow.value="<%= k %>";
   							</SCRIPT>	 
  			<%	 
               }        
    %> 
  </table>
   </div>
   </TD>
  </TR>	
</TABLE>
      
<SCRIPT language="JavaScript">
  function resizeDoc() {
       divResize();
       adjustDifTables(headTable, dataTable, dataDiv1,1,1);
  }
  showChecked("CKROW");
  resizeDoc();
  window.onresize=resizeDoc;
     
</SCRIPT>

  <%
  }
%> 
</FORM>

</BODY>
</HTML>
