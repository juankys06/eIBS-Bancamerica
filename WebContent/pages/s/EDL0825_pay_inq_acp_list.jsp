<html>
<head>
<title>Información Básica</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<%@ page import = "datapro.eibs.master.Util" %>
<jsp:useBean id="docHeader" class="datapro.eibs.beans.EDL080003Message" scope="session"/>
<jsp:useBean id="docList" class="datapro.eibs.beans.JBListRec" scope="session"/>
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT language="JavaScript">

function showDetailInquiry(idxRow) {
	var dataT = document.all["dataTable"];
    for ( var i=0; i<dataT.rows.length; i++ ) {
       dataT.rows[i].className="trnormal";
    }
    dataT.rows[idxRow].className="trhighlight";
    
	var winH = 400;
	if (document.forms[0]["TYPEDOC"+idxRow].value == "4") winH=480;
	page= prefix +language + "EDL0825_pay_inq_det.jsp?CurrRow="+ idxRow;
	
	CenterWindow(page,600,winH,1);
 
}
</SCRIPT>
</head>

<body>


<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<h3 align="center">Consulta de Descuento de Documentos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_inq_basic.jsp,EDL0160"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0825I"><INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">

<table class="tableinfo" id="trdark">
    <tr id="trdark">
      <th align="center" nowrap colspan="2">
      Aceptante
      </th>
    </tr>
    <tr id="trclear">
      <td align="center" nowrap width="50%">
       <b>Identificación : </b><%= docHeader.getE03NUMIDE() %>
      </td>
      
      <td align="center" nowrap width="50%">
       <b>Nombre : </b><%= docHeader.getE03CUMMA1() %>
      </td>
    </tr>   
  </table>

<h4>Documentos</h4>
<TABLE id="mainTable" class="tableinfo">
    <TR>
        <TD NOWRAP valign="top" width="100%">

        <TABLE id="headTable">
            <TR id="trdark">
                <TH ALIGN=CENTER NOWRAP>No. Doc</TH>
                <TH ALIGN=CENTER NOWRAP>Cuenta</TH>
                <TH ALIGN=CENTER NOWRAP>Cliente</TH>
                <TH ALIGN=CENTER NOWRAP>Valor Doc.</TH>
                <TH ALIGN=CENTER NOWRAP>Vence</TH>
                <TH ALIGN=CENTER NOWRAP>Cargo x Mora</TH>
                <TH ALIGN=CENTER NOWRAP>Status</TH>
            </TR>
        </TABLE>

        <div id="dataDiv" class="scbarcolor">

        <table id="dataTable">
            <%
  
                docList.initRow();
    			while (docList.getNextRow()) {
                    
                     %>
            <TR>
                <TD ALIGN=CENTER NOWRAP>
                <div nowrap><a href="javascript:showDetailInquiry(<%= docList.getCurrentRow() %>)"><%= docList.getRecord(0) %></a></DIV>
                <INPUT TYPE=HIDDEN NAME="TYPEDOC<%= docList.getCurrentRow() %>" VALUE="<%= docList.getRecord(6) %>">                  
                </TD>
                <TD ALIGN=CENTER NOWRAP>
                <div nowrap><a href="javascript:showDetailInquiry(<%= docList.getCurrentRow() %>)"><%= docList.getRecord(7) %></a></DIV>
                </TD>
                <TD ALIGN=LEFT NOWRAP>
                <div nowrap><a href="javascript:showDetailInquiry(<%= docList.getCurrentRow() %>)"><%= docList.getRecord(9) %></a></DIV>
                </TD>
                <TD ALIGN=RIGHT NOWRAP>
                <div nowrap><a href="javascript:showDetailInquiry(<%= docList.getCurrentRow() %>)"><%= docList.getRecord(3) %></a></DIV>
                </TD>
                <TD ALIGN=CENTER NOWRAP>
                <div nowrap><a href="javascript:showDetailInquiry(<%= docList.getCurrentRow() %>)"><%= docList.getRecord(4) %></a></DIV>
                </TD>
                <TD ALIGN=RIGHT NOWRAP>
                <div nowrap><a href="javascript:showDetailInquiry(<%= docList.getCurrentRow() %>)"><%= docList.getRecord(5) %></a></DIV>
                </TD>
                <TD ALIGN=CENTER NOWRAP>
                <div nowrap><a href="javascript:showDetailInquiry(<%= docList.getCurrentRow() %>)"> <% if (docList.getRecord(8).equals("A")) out.print("Activo");
      			   else if (docList.getRecord(8).equals("P")) out.print("Pagado");
      			   else if (docList.getRecord(8).equals("Q")) out.print("Anulado");
      			   else if (docList.getRecord(8).equals("D")) out.print("Diferido");
      			   else if (docList.getRecord(8).equals("N")) out.print("Renovado");
      			   else if (docList.getRecord(8).equals("R")) out.print("Reversión");
      			   else out.print("");													
      			 %></a></DIV>
                </TD>
            </TR>
            <% 
                           
                }
    	%>
        </table>

    </div>
    </TD>

    </TR>
</TABLE>

<SCRIPT language="JavaScript">
     //if (dataTable.rows.length >=5){
	 //  dataDiv1.style.height="120"; 
   	 //  dataDiv1.style.overflowY="scroll";
   	 //}
     function resizeDoc() {
      
       adjustEquTables(headTable, dataTable, dataDiv,1,false);
      }
	 
     resizeDoc();
     window.onresize=resizeDoc;
     
</SCRIPT></form>
</body>
</html>
