<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Protesto Manual
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "stop" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">
function goPrint(){
location= prefix + language + "EDD0190_ptt_stop_pay_print_all.jsp";
}
<!--
//-->
  function goAction(op) {

    document.forms[0].opt.value = op;
    if ((op == 1) || (op == 6) || (op == 8)){
		document.forms[0].submit();
	}
	 else {
      	 var formLength= document.forms[0].elements.length;
           var ok = false;
		   var en = "";
           for(n=0;n<formLength;n++)
           {
            	var elementName= document.forms[0].elements[n].name;
            	if(elementName == "ROW") 
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
						if (!confirm("Esta seguro que desea eliminar este Protesto?")) return;
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

<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

%> 
<h3 align="center">Protesto Manual<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ptt_stop_pay.jsp , EDD0190"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0190">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="opt" VALUE="1">

<%
	if ( stop.getNoResult() ) {

      	String action = "";
	try {
		action = userPO.getHeader20();
		//userPO.setHeader20("");
       }
       catch (Exception e) {
       	action = "";
       }
       String pagename = "";
       try {
       	pagename = userPO.getHeader21();
       	//userPO.setHeader21("");
       }
       catch (Exception e) {
       	pagename = "";
       }
               
       if ( !action.equals("")) {
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
            <tr > 
              <td nowrap> 
                <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
                  <tr id="trdark"> 
                    <td nowrap width="16%" > 
                      <div align="right"><b>Cliente :</b></div>
                    </td>
                    <td nowrap width="20%" > 
                      <div align="left"><b><%= userPO.getHeader1().trim()%></b> </div>
                    </td>
                    <td nowrap width="16%" > 
                      <div align="right"><b>Nombre :</b> </div>
                    </td>
                    <td nowrap colspan="3" ><%= userPO.getHeader5().trim()%>
                    </td>
                  </tr>
                  <tr id="trclear"> 
                    <td nowrap width="16%"> 
                      <div align="right"><b>Cuenta :</b></div>
                    </td>
                    <td nowrap width="20%"> 
                      <div align="left"><%= userPO.getIdentifier().trim()%></div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="right"><b>Moneda : </b></div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="left"><b><%= userPO.getCurrency().trim()%></b> </div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="right"><b>Producto: </b></div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="left"><b><%= userPO.getHeader6().trim()%></b> </div>
                    </td>
                  </tr>
				  <tr id="trdark"> 
                    <td nowrap width="16%" > 
                      <div align="right"><b>Ejecutivo :</b></div>
                    </td>
                    <td nowrap width="20%" > 
                      <div align="left"><b><%= userPO.getHeader18().trim()%></b> </div>
                    </td>
                    <td nowrap width="16%" > 
                      <div align="right"><b>Nombre :</b> </div>
                    </td>
                    <td nowrap colspan="3" > 
                      <div align="left"><%= userPO.getHeader19().trim()%></div>
                    </td>
                  </tr>
				  <tr id="trclear"> 
                    <td nowrap width="16%"> 
                      <div align="right"><b>Sdo. Contable :</b></div>
                    </td>
                    <td nowrap width="20%"> 
                      <div align="left"><% if ( userPO.getCurrency().trim().equals("USD")) {out.print("US$ ");} else {out.print("$ ");} %><%= Util.fcolorCCY(userPO.getHeader15().trim())%></div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="right"><b>Sdo. Disponible : </b></div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="left"><% if ( userPO.getCurrency().trim().equals("USD")) {out.print("US$ ");} else {out.print("$ ");} %><%= Util.fcolorCCY(userPO.getHeader16().trim())%></div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="right"><b>Sdo. Retención: </b></div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="left"><% if ( userPO.getCurrency().trim().equals("USD")) {out.print("US$ ");} else {out.print("$ ");} %><%= Util.fcolorCCY(userPO.getHeader17().trim())%></div>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>
          <p>&nbsp;</p>
          <h3>La cuenta elegida no posee Ordenes de Protesto 
            emitidas, por favor escoja una de las siguientes opciones:</h3>
          <table class="tbenter" width="100%">
            <tr> 
              <td align=CENTER class="TDBKG"> <a href="javascript:goAction(1)">Nueva</a></td>
          	  <TD ALIGN=CENTER class="TDBKG"> <a href="<%=request.getContextPath()%>/pages/background.htm">Salir</a></TD>
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
		//userPO.setHeader20("");
       }
       catch (Exception e) {
       	action = "";
       }
       String pagename = "";
       try {
       	pagename = userPO.getHeader21();
       	//userPO.setHeader21("");
       }
       catch (Exception e) {
       	pagename = "";
       }
               
       if ( !action.equals("")) {
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
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"><b><%= userPO.getHeader1().trim()%></b> </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><%= userPO.getHeader5().trim()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"><%= userPO.getIdentifier().trim()%></div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b><%= userPO.getCurrency().trim()%></b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto: </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b><%= userPO.getHeader6().trim()%></b> </div>
            </td>
          </tr>
		  <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Ejecutivo :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"><b><%= userPO.getHeader18().trim()%></b> </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><%= userPO.getHeader19().trim()%></div>
            </td>
          </tr>
		  <tr id="trclear"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Sdo. Contable :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">$ <%= userPO.getHeader15().trim()%></div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Sdo. Disponible : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>$ <%= userPO.getHeader16().trim()%></b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Sdo. Retención: </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>$ <%= userPO.getHeader17().trim()%></b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <table class="tbenter" width="100%">
    <tr> 
      <td class="TDBKG"> <a href="javascript:goAction(1)">Nueva</a></td>
	  <td class="TDBKG"> <a href="javascript:goAction(5)">Aclarar</a></td>
	  <td class="TDBKG"> <a href="javascript:goAction(6)">Imprimir<br>Selección</a></td>
	  <td class="TDBKG"> <a href="javascript:goAction(9)">Entregar</a></td> 
      <td class="TDBKG"> <a href="javascript:goAction(8)">Consulta</a></td>
	  <td class="TDBKG"> <a href="javascript:goPrint()">Imprimir<br>Todo</a></td>
      <TD class="TDBKG"> <a href="<%=request.getContextPath()%>/pages/background.htm">Salir</a></TD>
    </tr>
 <!--
	  <td class="TDBKG"> <a href="javascript:goAction(2)">Mantenimiento</a></td> 
      <td class="TDBKG"> <a href="javascript:goAction(3)">Borrar</a></td>
    //-->    
  </table>
  <TABLE  id="mainTable" class="tableinfo"  NOWRAP>
 <TR> 
   <TD NOWRAP valign="top" width="100%" >
        <TABLE id="headTable"  NOWRAP width="561">
          <TR id="trdark"> 
            <TH ALIGN=CENTER rowspan="2" NOWRAP width="10" ></TH>
            <TH ALIGN=CENTER colspan="4" NOWRAP>Cheques</TH>
            <TH ALIGN=CENTER rowspan="2" NOWRAP width="123">Monto</TH>
            <TH ALIGN=CENTER rowspan="2" NOWRAP width="107">Estado</TH>
     </TR>
     <TR id="trdark">  
            <TH ALIGN=CENTER NOWRAP width="69">Sucursal Origen</TH>
            <TH ALIGN=CENTER NOWRAP width="45">N&uacute;mero</TH>
            <TH ALIGN=CENTER NOWRAP width="35">Oficial</TH>
            <TH ALIGN=CENTER NOWRAP width="140"> 
              <DIV ALIGN=CENTER NOWRAP>Fecha / Hora del Protesto</DIV>
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
        <INPUT TYPE="radio" NAME="ROW" VALUE="<%= stop.getCurrentRow() %>" <% if (stop.getCurrentRow() == row) out.print("checked"); %> onClick="showAddInfo(<%= stop.getCurrentRow() %>)">
		<INPUT TYPE="hidden" NAME="SEQ_<%= stop.getCurrentRow() %>" VALUE="<%= stop.getRecord(0) %>">
      </TD>
      <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(stop.getRecord(0)) %></TD>
      <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(stop.getRecord(1)) %></TD>
      <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(stop.getRecord(2)) %></TD>
      <TD ALIGN=CENTER NOWRAP><%= Util.formatDate(stop.getRecord(3),stop.getRecord(4),stop.getRecord(5)) %> - <%= Util.formatTime(stop.getRecord(6)) %></TD>
      <TD ALIGN=RIGHT NOWRAP><%= Util.fcolorCCY(stop.getRecord(7)) %></TD>
      <TD ALIGN=CENTER NOWRAP><div nowrap>
      <% if (stop.getRecord(8).equals("A")) {  // Estado
			 out.print("Aclarado");              
		 } else if (stop.getRecord(8).equals("P")) {
			 out.print("Protestado"); 			
		 } else if (stop.getRecord(8).equals("R")) {
			 out.print("Rechazado");
		 } else if (stop.getRecord(8).equals("E")) {
			 out.print("Entregado"); 			
		 } else if (stop.getRecord(8).equals("D")) {
			 out.print("Devuelto");  			
		 } else out.print("Pagado");%>
		 </div></TD>
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
  showChecked("ROW");
  resizeDoc();
  window.onresize=resizeDoc;
     
</SCRIPT>
<BR>
<TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
<%
        if ( stop.getShowPrev() ) {
      			int pos = stop.getFirstRec() - 51;
%>  
      			<A HREF="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0190?SCREEN=7&FLAG=<%= userPO.getHeader13() %>&Pos=<%= pos %>"><IMG border="0" src="<%=request.getContextPath()%>/images/s/previous_records.gif" ></A>
<%      
        }
%>  
  </TD>
  <TD WIDTH="50%" ALIGN=RIGHT>     
<%      
        if ( stop.getShowNext() ) {
      			int pos = stop.getLastRec();
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
