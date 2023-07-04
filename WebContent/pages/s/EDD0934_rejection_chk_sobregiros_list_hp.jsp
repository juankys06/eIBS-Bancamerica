<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Resolución Protestos
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "chkList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "dataCR" class= "datapro.eibs.beans.DataCheckReject"  scope="session" />

<SCRIPT language="Javascript1.1" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT language="Javascript1.1" SRC="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="javascript">

function closeDivs() {
	setVisibility(document.getElementById("hiddenDivSearch"), "hidden");
}

function showHiddenDivSearch(evt) {	 
	evt = (evt) ? evt : ((window.event) ? window.event : "");

	var hiddenDivSearch = document.getElementById("hiddenDivSearch");
	
	var y=evt.clientY + document.body.scrollTop - hiddenDivSearch.clientHeight;
    var x=evt.clientX + document.body.scrollLeft - 1;
    
	cancelBub();
	
	setVisibility(document.getElementById("hiddenDivComments"), "hidden");
	
	moveElement(hiddenDivSearch, y, x);
	setVisibility(hiddenDivSearch, "visible");
	
	document.forms[0].ACCNUM.focus();
}


function goAction(op) {
     var form = document.forms[0];
     var formLength= document.forms[0].elements.length;
     var ok = false;
     var num = "";
     var pg = "";
     for(var n=0;n<formLength;n++) {
      	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "RECNUM") {
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

function showACC(){
 document.forms[0].FlagMov.value="0";
 document.forms[0].submit();
}


function checkCause(){
 var valc=document.forms[0].CAUSE.value;
 if (!(valc == "000" || valc == "001" || valc == "002" || valc == "013")) { showACC();}
 else { 
  document.forms[0].CAUSE.value="";
  document.forms[0].CAUSE.focus();} 
}
</script>

<script language="javascript">

function showAddInfo(idxRow){
  var i= parseInt(document.forms[0].ActRow.value);
  document.getElementById("dataTable").rows[i].className="trnormal";
  document.getElementById("dataTable").rows[idxRow].className="trhighlight";
  document.forms[0].ActRow.value=""+idxRow
}   
 
function submitThis(option) {
  document.forms[0].submit();
//  windows.close();
 }
 
function GetInfo(currentrow){
  var form = document.forms[0];
  if (form["OPTION_"+currentrow].value == "0004") {
	var acc = form["NEWACC_"+currentrow].value;
	var chk = form["NEWCHK_"+currentrow].value;
	var rs1 = form["NEWRS1_"+currentrow].value;
	var rs2 = form["NEWRS2_"+currentrow].value;
	var rs3 = form["NEWRS3_"+currentrow].value;
	var rs4 = form["NEWRS4_"+currentrow].value;
	var gl = form["NEWGL_"+currentrow].value;
	var type = form["TYPE_"+currentrow].value;
	var Params = "?CurrRow="+ currentrow + "&Acc=" + acc + "&Chk=" + chk + "&Rs1=" + rs1 + "&Rs2=" + rs2 + "&Rs3=" + rs3 + "&Rs4=" + rs4 + "&GL=" + gl + "&TYPE=" + type;
	page= prefix + language + "EDD0924_rejection_chk_change.jsp" + Params; 
	CenterWindow(page, 470, 350, 3);
	
	}else {
		if (form["OPTION_"+currentrow].value == "0009") {
		
	  		if (form["TYPE_"+currentrow].value == ""){ 
		  		var acc = form["NEWACC_"+currentrow].value;
		  		var chk = form["NEWCHK_"+currentrow].value;
		  		var rs1 = form["NEWRS1_"+currentrow].value;
		  		var rs2 = form["NEWRS2_"+currentrow].value;
			  	var rs3 = form["NEWRS3_"+currentrow].value;
			  	var rs4 = form["NEWRS4_"+currentrow].value;
			  	var gl = form["NEWGL_"+currentrow].value;
			  	var type = form["TYPE_"+currentrow].value;
			  	var Params = "?CurrRow="+ currentrow + "&Acc=" + acc + "&Chk=" + chk + "&Rs1=" + rs1 + "&Rs2=" + rs2 + "&Rs3=" + rs3 + "&Rs4=" + rs4 + "&GL=" + gl + "&TYPE=" + type;
			  	page= prefix + language + "EDD0924_rejection_chk_change_print.jsp" + Params; 
			  	CenterWindow(page, 600, 450, 3);
				form["OPTION_"+currentrow].value = " "; 
			}else { 
			  	alert("Solo los Cheques se Pueden Listar (Tipo:CHK)");
				form["OPTION_"+currentrow].value = " ";
			}                           
		}
	}
}

function SetSelection(value){
  var form = document.forms[0];
  var maxrow = document.all["dataTable"].rows.length;
  for(var n=0;n<maxrow;n++) {
   var optChk = form["OPTION_"+n];
   var maxopt = optChk.length;    
   for(var i=0;i<maxopt;i++) {
    if (optChk[i].value == value) {
     optChk.selectedIndex = i;}
   }
  }
}

function ShowPrev(){
  document.forms[0].FlagMov.value="-";
  document.forms[0].submit();
}

function ShowNext(){
  document.forms[0].FlagMov.value="+";
  document.forms[0].submit();
}

</script>

</HEAD>

<BODY>

<SCRIPT Language="Javascript">
builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }	

</SCRIPT>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %> 
<SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
<%
 }
%>
<h3 align="center">Sobregiros Monto Mayor, Oficial : 
  <% if ( dataCR.getOfficer().trim().equals("")){out.print("Todos");} else {out.print(dataCR.getOfficer());} %>
  <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rejection_chk_sobregiros_list.jsp,EDD0934"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEDD0934">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="ActRow" VALUE="0">
<INPUT TYPE=HIDDEN NAME="FlagMov" VALUE="0">
<%
	if ( chkList.getNoResult() ) {
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
%>
<div id="hiddenDivSearch" class="hiddenDiv">
 <TABLE id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
	<TR>
	  <TD ALIGN=right nowrap>
          Buscar Cuenta : 
          <input type="text" maxlength=9 size=11 name="ACCNUM" onKeyPress="enterInteger()"
			oncontextmenu="showPopUp(accountHelp,this.name,<%= dataCR.getBank() %>,'','','','RT'); return false;" >
	  </TD>
      <TD ALIGN=Left nowrap>
	     <a href="javascript:showACC()"><img name="Img1" border="0" src="<%=request.getContextPath()%>/images/search1.gif"></a> 
	  </TD>
   </TR>
 </TABLE>
</div>

  <TABLE class="tbenter" width="100%">
    <TR>
	  <td class="TDBKG"><a href="javascript:submitThis(1)">Registro<br>Anterior</a></td>
      <TD class="TDBKG"><a href="javascript:goAction(1)">Sumario</a></TD>
      <TD class="TDBKG"><a href="javascript:goAction(2)">Consulta</a></TD>
      <TD class="TDBKG"><a href="javascript:goAction(3)">Estado<br>de Cuentas</a></TD>
      <TD class="TDBKG"><a href="javascript:goAction(4)">Promedio</a></TD>
      <TD class="TDBKG"><a href="<%=request.getContextPath()%>/pages/background.htm">Salir</a></TD>
    </TR>
   
  </TABLE>
  
  
  <TABLE  id="mainTable" class="tableinfo" ALIGN=CENTER NOWRAP>
 <TR> 
    <TD NOWRAP>
   <TABLE id="headTable" NOWRAP>
    	<TR id="trdark"> 
				<TH ALIGN=CENTER NOWRAP></TH>
      			<TH ALIGN=CENTER NOWRAP>RUT</TH>
				<TH ALIGN=CENTER NOWRAP>Nombre Cliente</TH>
      			<TH id="eibsSearch" ALIGN=CENTER NOWRAP style="cursor:pointer">Cuenta</TH>
      			<TH ALIGN=CENTER NOWRAP>Monto Sobregiro</TH>
				<TH ALIGN=CENTER NOWRAP>Saldo Contable</TH>
      		    <TH ALIGN=CENTER NOWRAP>Ultimo Sobregiro</TH>
				<TH ALIGN=CENTER NOWRAP>Dias Sobregiro</TH>
				<TH ALIGN=CENTER NOWRAP>Suc</TH>
				<TH ALIGN=CENTER NOWRAP>Ofic</TH>
      		</TR>
    </TABLE>
  
  
   <div id="dataDiv1" class="scbarcolor" NOWRAP>
    <table id="dataTable" NOWRAP> 
     <%
          String chk = "";
		  int recnum = 0;
		  try { recnum = Integer.parseInt(request.getParameter("ROW")); } catch (Exception e) {};
		  chkList.initRow();
                while (chkList.getNextRow()) {
                    if (chkList.getFlag().equals("")) {
				if (chkList.getCurrentRow() == recnum) {
					chk = "checked";
				}
				else {
					chk = "";
				}	
  %> 
    <TR> 
				<TD ALIGN=Left NOWRAP> 
				<DIV NOWRAP> 
                  <input type="Radio" name="RECNUM" value="<%= chkList.getCurrentRow() %>" <%= chk %> onClick="showAddInfo('<%= chkList.getCurrentRow() %>')">
                  <input type="HIDDEN" name="CURACC_<%= chkList.getCurrentRow() %>" value="<%= chkList.getRecord(0) %>">
			    </DIV>
			    </TD>
             	<TD ALIGN=LEFT NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(2) %>-<%= chkList.getRecord(3) %></a></TD>
				<TD ALIGN=LEFT NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(1) %></a></TD>
			 	<TD ALIGN=LEFT NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(0) %></a></TD>
				<TD ALIGN=RIGHT NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= datapro.eibs.master.Util.formatCCY(chkList.getRecord(4)) %></a></TD>
      			<TD ALIGN=RIGHT NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= datapro.eibs.master.Util.formatCCY(chkList.getRecord(5)) %></a></TD>
       			<TD ALIGN=CENTER NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(6) %>/<%= chkList.getRecord(7) %>/<%= chkList.getRecord(8) %></a></TD>
				<TD ALIGN=CENTER NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(17) %></a></TD>
				<TD ALIGN=CENTER NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(9) %></a></TD>
				<TD ALIGN=LEFT NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(10) %></a></TD>
     		</TR>
      <% 
                    }
                }
  %>
  </table>
  </div>
   
  </TD>
  </TR>	
  
   
</table>

 <SCRIPT language="JavaScript">
  function resizeDoc() {
       divResize();
       adjustDifTables(
    		document.getElementById("headTable"),
    		document.getElementById("dataTable"),
    		document.getElementById("dataDiv1"),1,1);
  }
  showChecked("ROW");
  resizeDoc();
  window.onresize=resizeDoc;
  
 	document.getElementById("hiddenDivSearch").onclick=cancelBub;
	document.getElementById("eibsSearch").onclick=showHiddenDivSearch;  
     
</SCRIPT>
<BR>
<TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
<%
        if ( chkList.getShowPrev() ) {
      			out.println("<A HREF=\"javascript:ShowPrev()\"><IMG border=\"0\" src=\""+ request.getContextPath()+"/images/s/previous_records.gif\" ></A>");
        }
  %>  
  </TD>
  <TD WIDTH="50%" ALIGN=RIGHT>     
 <%      
        if ( chkList.getShowNext() ) {
      			out.println("<A HREF=\"javascript:ShowNext()\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/next_records.gif\" ></A>");
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
