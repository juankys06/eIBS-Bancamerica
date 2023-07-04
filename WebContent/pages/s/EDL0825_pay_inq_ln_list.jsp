<html>
<head>
<title>Información Básica</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<%@ page import = "datapro.eibs.master.Util" %>
<jsp:useBean id="docHeader" class="datapro.eibs.beans.EDL016002Message" scope="session"/>
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

<h3 align="center">Consulta 
  <% if (docHeader.getE02DEACLF().equals("A")) out.print("Arrendamiento Financiero");
							else if (docHeader.getE02DEACLF().equals("C")) out.print("Préstamos de Consumo");
							else if (docHeader.getE02DEACLF().equals("D")) out.print("Préstamos sobre Saldo Disoluto");
							else if (docHeader.getE02DEACLF().equals("L")) out.print("Préstamo Regular");
							else if (docHeader.getE02DEACLF().equals("H")) out.print("Hipotecarios");
							else if (docHeader.getE02DEACLF().equals("P")) out.print("Politica Habitacional");
							else if (docHeader.getE02DEACLF().equals("G")) out.print("Descuento Documentos(Factoring)");
							else if (docHeader.getE02DEACLF().equals("V")) out.print("Valores al Cobro");
							else if (docHeader.getE02DEACLF().equals("O")) out.print("Para Control de Sobregiros");
							else if (docHeader.getE02DEACLF().equals("R")) out.print("Préstamo para Refinanciar Otro");
							else if (docHeader.getE02DEACLF().equals("I")) out.print("Préstamo Credilinea");
							else out.print("Proyectos de Constructor");%>
  <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="pay_inq_ln_list.jsp,EDL0825"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0825I"><INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<table class="tableinfo">
    <tr>
        <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
            
            <TR id="trdark">
                <TD nowrap width="14%">
                <DIV align="right"><B>Cuenta :</B></DIV>
                </TD>
                <TD nowrap>
                <DIV align="left"><%= docHeader.getE02DEABNK() %>-<%= docHeader.getE02DEABRN() %>-<%=docHeader.getE02DEACCY() %>-<%=docHeader.getE02DEAGLN() %>-<%=docHeader.getE02DEAACC() %></DIV>
                </TD>
                <TD nowrap width="14%">
                <DIV align="right"><B>Cliente :</B></DIV>
                </TD>
                <TD nowrap>
                <DIV align="left"><%= docHeader.getE02DEACUN() %>-<%= docHeader.getE02CUSNA1() %></DIV>
                </TD>
            </TR>
            <TR>
              <td nowrap colspan=4 >
        		<table class="tableinfo" style="filter:">
            <TR >
                <TD nowrap>
                <DIV align="right"><b>Monto Original :</b></DIV>
                </TD>
                <TD nowrap><%= Util.formatCCY(docHeader.getE02DEAOAM().trim())%></TD>
                <TD nowrap>
                <DIV align="right"><b>Fecha de Apertura :</b></DIV>
                </TD>
                <TD nowrap><%= Util.formatDate(docHeader.getE02DEAOD1().trim(),docHeader.getE02DEAOD2().trim(),docHeader.getE02DEAOD3().trim())%></TD>
            </TR>
            </tr>
            <tr>
                <td nowrap>
                <div align="right"><b>Tasa de Inter&eacute;s/Spread :</b></div>
                </td>
                <td nowrap><%= docHeader.getE02DEARTE().trim()%></td>
                <td nowrap>
                <div align="right"><b>Fecha de Vencimiento :</b></div>
                </td>
                <td nowrap><%= Util.formatDate(docHeader.getE02DEAMD1().trim(),docHeader.getE02DEAMD2().trim(),docHeader.getE02DEAMD3().trim())%></td>
            </tr>
            <tr >
                <td nowrap>
                <div align="right"><b>Per&iacute;odo Base :</b></div>
                </td>
                <td nowrap><%= docHeader.getE02DEABAS().trim()%></td>
                <td nowrap>
                <div align="right"><b>T&eacute;rmino :</b></div>
                </td>
                <td nowrap><%= docHeader.getE02DEATRM().trim()%>-<% if (docHeader.getE02DEATRC().equals("D")) out.print("D&iacute;a(s)");
							else if (docHeader.getE02DEATRC().equals("M")) out.print("Mes(es)");
							else if (docHeader.getE02DEATRC().equals("Y")) out.print("A&ntilde;o(s)");
							else out.print("");%></td>
            </tr>
            <tr>
                <td nowrap>
                <div align="right"><b>Tipo de Inter&eacute;s :</b></div>
                </td>
                <td nowrap><% if (docHeader.getE02DEAICT().equals("S")) out.print("Al Vencimiento Calendario");
							else if (docHeader.getE02DEAICT().equals("M")) out.print("Al Vencimiento Comercial");
							else if (docHeader.getE02DEAICT().equals("P")) out.print("Prepagados Calendario");
							else if (docHeader.getE02DEAICT().equals("A")) out.print("Prepagados Comerciales");
							else if (docHeader.getE02DEAICT().equals("D")) out.print("Descontados Calendario");
							else if (docHeader.getE02DEAICT().equals("T")) out.print("Descontados Comerciales");
							else if (docHeader.getE02DEAICT().equals("R")) out.print("Capitalizados(CD's)");
							else if (docHeader.getE02DEAICT().equals("1")) out.print("Al Vencimiento Calendario");
							else if (docHeader.getE02DEAICT().equals("2")) out.print("Al Vencimiento Comercial");
							else if (docHeader.getE02DEAICT().equals("3")) out.print("Prepagados Calendario");
							else if (docHeader.getE02DEAICT().equals("4")) out.print("Prepagados Comerciales");
							else if (docHeader.getE02DEAICT().equals("5")) out.print("Descontados Calendario");
							else if (docHeader.getE02DEAICT().equals("6")) out.print("Descontados Comerciales");
							else if (docHeader.getE02DEAICT().equals("7")) out.print("Capitalizados (CD's)");
							else if (docHeader.getE02DEAICT().equals("8")) out.print("Regla 78");
							else out.print("");%></td>
                <td nowrap>
                <div align="right"><b>Tabla de Tasa Flotante :</b></div>
                </td>
                <td nowrap><%= docHeader.getE02DEAFTB().trim()+ " "%> <% if (docHeader.getE02DEAFTY().equals("FP")) out.print("Primaria Flotante");
							else if (docHeader.getE02DEAFTY().equals("FS")) out.print("Secundaria Flotante");
							else out.print("");%></td>
            </tr>
            <tr >
                <td nowrap>
                <div align="right"><b>C&aacute;lculo Inter&eacute;s Normal :</b></div>
                </td>
                <td nowrap><% if (docHeader.getE02DEAIFL().equals("1")) out.print("Capital Vigente");
							else if (docHeader.getE02DEAIFL().equals("2")) out.print("Capital Original");
							else if (docHeader.getE02DEAIFL().equals("3")) out.print("Capital Vigente menos Capital Vencido");
							else if (docHeader.getE02DEAIFL().equals("4")) out.print("No Calcula Intereses");
							else out.print("");%></td>
                <td nowrap>
                <div align="right"><b>Inter&eacute;s de Mora :</b></div>
                </td>
                <td nowrap><%= docHeader.getE02DEAPIF().trim()%> <%= docHeader.getE02DEAPEI().trim()%></td>
            </tr>
            <tr>
                <td nowrap>
                <div align="right"><b>C&aacute;lculo Inter&eacute;s Mora :</b></div>
                </td>
                <td nowrap><% if (docHeader.getE02DEAPCL().equals("1")) out.print("Sobre Capital Vencido");
							else if (docHeader.getE02DEAPCL().equals("2")) out.print("Sobre Capital Original");
							else if (docHeader.getE02DEAPCL().equals("3")) out.print("Sobre Capital Vigente");
							else if (docHeader.getE02DEAPCL().equals("4")) out.print("Sobre Capital Vencido mas Intereses Vencido");
							else out.print("No Calcula Intereses");%></td>
                <td nowrap>
                <div align="right"><b>L&iacute;nea de Cr&eacute;dito :</b></div>
                </td>
                <td nowrap><%= docHeader.getE02DEACMC().trim()%>-<%= docHeader.getE02DEACMN().trim()%></td>
            </tr>
            <tr >
                <td nowrap>
                <div align="right"><b>Tabla de Cargos :</b></div>
                </td>
                <td nowrap><%= docHeader.getE02DEATLN().trim()%></td>
                <td nowrap>
                <div align="right"><B>Producto :</B></div>
                </td>
                <td nowrap><%= docHeader.getE02DEAPRO().trim()%></td>
            </tr>
            <tr>
                <td nowrap>
                <div align="right"><B>Contrapartida :</B></div>
                </td>
                <td nowrap><%= docHeader.getE02DEAOFB().trim()%>-<%= docHeader.getE02DEAOCR().trim()%>-<%= docHeader.getE02DEAOCY().trim()%>-<%= docHeader.getE02DEAOFA().trim()%></td>
                <td nowrap>
                <div align="right"><B>Cuenta a Debitar :</B></div>
                </td>
                <td nowrap><%= docHeader.getE02DEAREB().trim()%>-<%= docHeader.getE02DEARPR().trim()%>-<%= docHeader.getE02DEARPC().trim()%>-<%= docHeader.getE02DEARAC().trim()%></td>
            </tr>
            

            </Td>
            </tr>
            </table>
          </td>
          </tr>
       </table>
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
                <TH ALIGN=CENTER NOWRAP>Aceptante</TH>
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
                <TD ALIGN=LEFT NOWRAP>
                <div nowrap><a href="javascript:showDetailInquiry(<%= docList.getCurrentRow() %>)"><%= docList.getRecord(2) %></a></DIV>
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
