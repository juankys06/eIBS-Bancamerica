<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>Sobregiros Monto Mayor</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "chkList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT language="Javascript1.1" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">
function goPrint(){
location= prefix + language + "EDD0934_rejection_chk_sobregiros_print.jsp";
}

function goAction(op) {
     var form = document.forms[0];
     var formLength= document.forms[0].elements.length;
     var ok = false;
     var num = "";
     var pg = "";
	 for(var n=0;n<formLength;n++) {
      	var elementName= document.forms[0].elements[n].name;
		var numreg= document.forms[0].elements[n].value;
	 	if(elementName == "ROW") {
		if (document.forms[0].elements[n].checked == true) {
			num = document.forms[0].elements[n].value;
	  		ok = true;
       		break;
		}
      	}
      }
      if ( ok ) {
	switch (op) {
		
		case 1 :  // Account Sumary Inquiry
			pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEDD0924?SCREEN=6&ACCNUM=" + form["CURACC_"+num].value;
			break;
		case 2 :  // Account Inquiry
		  	pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF010?SCREEN=400&opt=1&ACCNUM=" + form["CURACC_"+num].value;
			break;
		case 3 :  // Account Statement
		  	pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF010?SCREEN=400&opt=2&ACCNUM=" + form["CURACC_"+num].value;
			break;
		case 4 :  // Account Average
		  	pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF010?SCREEN=400&opt=3&ACCNUM=" + form["CURACC_"+num].value;
			break;
		case 5 :  // Signature Image Inquiry
		case 6 :  // Check Image Inquiry
			pg = "<%=request.getContextPath()%>/pages/s/EDD0924_rejection_chk_view_images.jsp?ROW="+form.ActRow.value;
			break;
	}
	CenterWindow(pg,620,500,2);
  }
   else {
		alert("Seleccione un número de Cuenta para continuar.");	   
     }

}


function showAddInfo(idxRow){
   var i= parseInt(document.forms[0].ActRow.value);
   dataTable.rows[i].className="trnormal";   
   dataTable.rows[idxRow].className="trhighlight";
   document.forms[0].ActRow.value = "" + idxRow;
  } 
  
function submitThis(option) {
  document.forms[0].submit();
//  windows.close();
 }
 
 
</script>
</head>

<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

%> 
<h3 align="center">Sobregiros  por Rangos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="rejection_chk_sobregiros_list.jsp, EDD0934"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEDD0934">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="ActRow" VALUE="0">
<INPUT TYPE=HIDDEN NAME="FlagMov" VALUE="0">
<INPUT TYPE=HIDDEN NAME="opt" VALUE="1">

<%
	if ( chkList.getNoResult() ) {

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
      <div align="center"> <font size="3"><b> No hay datos que correspondan con su criterio de busqueda 
        </b></font> </div>
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
	   		CenterWindow('<%= pagename %>',620,500,2);
<% 
              }
%>
              </SCRIPT>
<% 
	}
%> 

  <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0"> 
 
  <table class="tbenter" width="100%">
    <tr> 
      <TD class="TDBKG"> <a href="javascript:goAction(1)">Sumario</a></TD>
      <TD class="TDBKG"> <a href="javascript:goAction(2)">Consulta</a></TD>
      <TD class="TDBKG"> <a href="javascript:goAction(3)">Estado<br>de Cuentas</a></TD>
      <TD class="TDBKG"> <a href="javascript:goAction(4)">Promedio</a></TD>
      <TD class="TDBKG"> <a href="javascript:goPrint()">Imprimir</a></TD>
      <TD class="TDBKG"> <a href="javascript:checkClose()">Salir</a></TD>
    </tr>
 <!--
     <td class="TDBKG"> <a href="javascript:goAction(2)">Mantenimiento</a></td> 
     <td class="TDBKG"> <a href="javascript:goAction(3)">Borrar</a></td>
    //-->    
  </table>
<TABLE  id="mainTable" class="tableinfo"  NOWRAP>
 <TR>
            <TD NOWRAP valign="top" height="100" width="625">
            <TABLE id="headTable"  NOWRAP width="100%">
         <TR id="trdark"> 
				<TH ALIGN=CENTER NOWRAP width="25" > </TH>
               <TH ALIGN=CENTER width="60" NOWRAP>ID</TH>
                        <TH ALIGN=CENTER width="120" NOWRAP>Nombre Cliente</TH>
                        <TH ALIGN=CENTER width="60" NOWRAP style="cursor:hand" onClick="ShowSearchAcc()">Cuenta</TH>
                        <TH ALIGN=CENTER width="100" NOWRAP>Monto Sobregiro</TH>
                        <TH ALIGN=CENTER width="100" NOWRAP>Saldo Contable</TH>
                        <TH ALIGN=CENTER width="60" NOWRAP>Ultimo Sobregiro</TH>
                        <TH ALIGN=CENTER width="40" NOWRAP>Dias SGiro</TH>
				<TH ALIGN=CENTER width="30" NOWRAP>Suc</TH>
				<TH ALIGN=CENTER width="30" NOWRAP>Ofic</TH>
                  </TR>
	</TABLE>
            <div id="dataDiv1" class="scbarcolor" style="padding:0" NOWRAP>
            <table id="dataTable"  NOWRAP width="100%">
    <%
               int row;
		  			try{row = Integer.parseInt(request.getParameter("ROW"));}catch(Exception e){row = 0;}
               chkList.initRow();
                int k=1;
               while (chkList.getNextRow()) {
     %> 
    <TR>
                        <TD ALIGN=CENTER width="25" NOWRAP> 
        <INPUT TYPE="radio" NAME="ROW" VALUE="<%= chkList.getCurrentRow() %>" <% if (chkList.getCurrentRow() == row) out.print("checked"); %> onClick="showAddInfo(<%= chkList.getCurrentRow() %>)">
	    <input type="HIDDEN" name="CURACC_<%= chkList.getCurrentRow() %>" value="<%= chkList.getRecord(0) %>">
		      </TD>
                        <TD ALIGN=right  width="60"  NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(2) %>-<%= chkList.getRecord(3) %></a></TD>
                        <TD ALIGN=LEFT   width="120" NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(1) %></a></TD>
                        <TD ALIGN=right  width="60"  NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(0) %></a></TD>
                        <TD ALIGN=RIGHT  width="100"  NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= datapro.eibs.master.Util.formatCCY(chkList.getRecord(4)) %></a></TD>
                        <TD ALIGN=RIGHT  width="100"  NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= datapro.eibs.master.Util.formatCCY(chkList.getRecord(5)) %></a></TD>
                        <TD ALIGN=CENTER width="60"  NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(6) %>/<%= chkList.getRecord(7) %>/<%= chkList.getRecord(8) %></a></TD>
                        <TD ALIGN=CENTER width="40"  NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(17) %></a></TD>
				<TD ALIGN=CENTER width="30"  NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(9) %></a></TD>
                        <TD ALIGN=CENTER width="30"  NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(10) %></a></TD>
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
  showChecked("RECNUM");
  //document.forms[0].ActRow.value = document.forms[0].RECNUM.selectedIndex;
  resizeDoc();
  window.onresize=resizeDoc;
     
</SCRIPT>
<BR>
<TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
<%
        if ( chkList.getShowPrev() ) {
      			int pos = chkList.getFirstRec() - 51;
%>  
      			<A HREF="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0190?SCREEN=7&FLAG=<%= userPO.getHeader13() %>&Pos=<%= pos %>"><IMG border="0" src="<%=request.getContextPath()%>/images/s/previous_records.gif" ></A>
<%      
        }
%>  
  </TD>
  <TD WIDTH="50%" ALIGN=RIGHT>     
<%      
        if ( chkList.getShowNext() ) {
      			int pos = chkList.getLastRec();
%>  
      			<A HREF="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0190?SCREEN=7&FLAG=<%= userPO.getHeader13() %>&Pos=<%= pos %>"><IMG border="0" src="<%=request.getContextPath()%>/images/s/next_records.gif" ></A>
<%      
        }
%> 
   </TD>
 	</TR>
 	</TABLE>

  <%
  }
%> 
</FORM>

</BODY>
</HTML>
