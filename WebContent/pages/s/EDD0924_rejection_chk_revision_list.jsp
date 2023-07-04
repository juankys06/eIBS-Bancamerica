<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Cheques No Procesados
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "chkList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "dataCR" class= "datapro.eibs.beans.DataCheckReject"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="javascript">
function retorna_pagina(){
location= prefix + language + "EDD0924_rejection_chk_search.jsp";
} 

function closeDivs() {
	setVisibility(document.getElementById("hiddenDivSearch"), "hidden");
	setVisibility(document.getElementById("hiddenDivCause"), "hidden");
	setVisibility(document.getElementById("hiddenDivComments"), "hidden");
}

function OkComments(){
	var idxchk =document.forms[0].ActRow.value;
	document.forms[0]["REMDATA"+idxchk].value = trim(document.forms[0].txtComments.value);
	
	if (document.forms[0]["REMDATA"+idxchk].value == ""){
	  getElementByNameAndId("IMGREM", ""+idxchk).style.display="none";
    } else {
      getElementByNameAndId("IMGREM", ""+idxchk).style.display="";
	}
	adjustEquTables(
		document.getElementById("headTable"), 
		document.getElementById("dataTable"), 
		document.getElementById("dataDiv1"),1,false);
	
	setVisibility(document.getElementById("hiddenDivComments"), "hidden");
}

function addComments() {
	var idxchk =document.forms[0].ActRow.value;
	var hiddenDivComments = document.getElementById("hiddenDivComments");
	
	var y= document.getElementById("mainTable").offsetTop + 10;
    var x= document.getElementById("mainTable").offsetLeft  + (hiddenDivComments.clientWidth /2);
	 
	moveElement(hiddenDivComments, y, x);    
	setVisibility(hiddenDivComments, "visible");     
	
    document.forms[0].txtComments.value=document.forms[0]["REMDATA"+idxchk].value;
	document.forms[0].txtComments.focus();
}

function showHiddenDivComments(evt) {
	var elem = getEventElement(evt);
	idxchk = elem.id;
	
	evt = (evt) ? evt : ((window.event) ? window.event : "");

	var hiddenDivComments = document.getElementById("hiddenDivComments");
	
	var y=evt.clientY + document.body.scrollTop - hiddenDivComments.clientHeight;
    var x=evt.clientX + document.body.scrollLeft - 1;
	document.forms[0]["RECNUM"][idxchk].click();

	cancelBub();
	
	setVisibility(document.getElementById("hiddenDivSearch"), "hidden");
	setVisibility(document.getElementById("hiddenDivCause"), "hidden");
	
	moveElement(hiddenDivComments, y, x);
	setVisibility(hiddenDivComments, "visible");
	
    document.forms[0].txtComments.value=document.forms[0]["REMDATA"+idxchk].value;
	document.forms[0].txtComments.focus();
}

function showHiddenDivSearch(evt) {
	evt = (evt) ? evt : ((window.event) ? window.event : "");
 	
	var hiddenDivSearch = document.getElementById("hiddenDivSearch");

	var y=evt.clientY + document.body.scrollTop - hiddenDivSearch.clientHeight;
    var x=evt.clientX + document.body.scrollLeft - 1;
     
	cancelBub(evt);
	
	setVisibility(document.getElementById("hiddenDivCause"), "hidden");
	setVisibility(document.getElementById("hiddenDivComments"), "hidden");

	moveElement(hiddenDivSearch, y, x);
	setVisibility(hiddenDivSearch, "visible");
	 
	document.forms[0].ACCNUM.focus();
}

function showHiddenDivCause(evt) {
	evt = (evt) ? evt : ((window.event) ? window.event : "");
 	
	var hiddenDivCause = document.getElementById("hiddenDivCause");

	var y=evt.clientY + document.body.scrollTop - hiddenDivCause.clientHeight;
    var x=evt.clientX + document.body.scrollLeft - hiddenDivCause.clientWidth;
     
	cancelBub(evt);
	
	setVisibility(document.getElementById("hiddenDivSearch"), "hidden");
	setVisibility(document.getElementById("hiddenDivComments"), "hidden");

	moveElement(hiddenDivCause, y, x);
	setVisibility(hiddenDivCause, "visible");
	
	document.forms[0].CAUSE.focus();
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

function SendInfo(){
  document.forms[0].FlagMov.value="1";
  document.forms[0].submit();
}

function checkCause(){
 var valc=document.forms[0].CAUSE.value;
 if (!(valc == "0000" || valc == "0001" || valc == "0002" || valc == "0013")) { showACC();}
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

<h3 align="center">Revisi&oacute;n Resoluci&oacute;n de Protestos, Oficial : 
  <% if ( dataCR.getOfficer().trim().equals("")){out.print("Todos");} else {out.print(dataCR.getOfficer());} %>
  <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rejection_chk_revision_list.jsp,EDD0924"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEDD0924">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="8">
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
<div id="hiddenDivCause" class="hiddenDiv">
 <TABLE id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
	<TR>
	  <TD ALIGN=right nowrap>
          Buscar Causal : 
          <input type="text" name="CAUSE" maxlength=4 size=5 
			oncontextmenu="showPopUp(causeHelp,this.name,<%= dataCR.getBank() %>,'','','',''); return false;">
      </TD>
      <TD ALIGN=Left nowrap>
	     <a href="javascript:checkCause()"><img name="Img2" border="0" src="<%=request.getContextPath()%>/images/search1.gif"></a> 
	  </TD>
   </TR>
 </TABLE>
</div>
<div id="hiddenDivComments" class="hiddenDiv">
 <TABLE id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
	<TR>
	  <TD ALIGN=Left nowrap>
          Comments :<BR> 
          <input type="text" maxlength=61 size=60 name="txtComments">
      </TD>
    </TR>
    <TR>
      <TD ALIGN=Center nowrap>
	     <a href="javascript:OkComments()">Ok</a> 
	  </TD>
   </TR>
 </TABLE>
</div>
  <TABLE class="tbenter" width="100%">
    <TR>
   	  <TD class="TDBKG"> <a href="javascript:goAction(1)">Sumario</a></TD>
      <TD class="TDBKG"> <a href="javascript:goAction(2)">Consulta</a></TD>
      <TD class="TDBKG"> <a href="javascript:goAction(3)">Estado<br>de Cuentas</a></TD>
      <TD class="TDBKG"> <a href="javascript:goAction(4)">Promedios</a></TD>
      <!--<TD class="TDBKG"> <a href="javascript:goAction(5)">Firma</a></TD>
      <TD class="TDBKG"> <a href="javascript:goAction(6)">Imagen<br>de Cheque</a></TD>-->
      <TD class="TDBKG"> <a href="javascript:addComments()">Comentarios</a></TD>
	</TR>
  </TABLE>
  
  <TABLE  id="mainTable" class="tableinfo" ALIGN=CENTER width="100%" height="86" >
    <TR> 
      <TD NOWRAP valign="top" width="100%"> 
        <TABLE id="headTable" >
     		<TR id="trdark"> 
      			<TH ALIGN=CENTER NOWRAP></TH>
      			<TH id="eibsSearch" ALIGN=CENTER NOWRAP style="cursor:pointer">Cuenta</TH>
      			<TH ALIGN=CENTER NOWRAP>Nombre</TH>
				<TH ALIGN=CENTER NOWRAP>Tipo</TH>     
      			<TH ALIGN=CENTER NOWRAP>No. Cheque</TH>
      			<TH ALIGN=CENTER NOWRAP>Monto</TH>
      			<TH ALIGN=CENTER NOWRAP>Estado</TH>
      			<TH ALIGN=CENTER NOWRAP>Bco.<br>Depos.</TH>
      			<TH ALIGN=CENTER NOWRAP>Suc.<br>Proc.</TH>
      			<TH id="eibsCause" ALIGN=CENTER NOWRAP style="cursor:pointer">Causal</TH>
     		</TR>
    	 </TABLE>
  
   <div id="dataDiv1" class="scbarcolor">
    	<table id="dataTable">
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
                  <input type="radio" name="RECNUM" value="<%= chkList.getCurrentRow() %>" <%= chk %> onClick="showAddInfo('<%= chkList.getCurrentRow() %>')">
                 
                  <img id="<%= chkList.getCurrentRow() %>" name="IMGREM" src="<%=request.getContextPath()%>/images/s/info.gif" alt="More Information" border="0" align="absmiddle"
				 <% if (chkList.getRecord(22) == null || chkList.getRecord(22).trim().equals("")) out.print("style=\"display:none;\""); %>> 
                <input type="HIDDEN" name="CURACC_<%= chkList.getCurrentRow() %>" value="<%= chkList.getRecord(0) %>">
			   	<input type="HIDDEN" name="CURCUN_<%= chkList.getCurrentRow() %>" value="<%= chkList.getRecord(16) %>">
			   <input type="HIDDEN" name="NEWACC_<%= chkList.getCurrentRow() %>" value="<%= chkList.getRecord(10) %>">
			   <input type="HIDDEN" name="NEWCHK_<%= chkList.getCurrentRow() %>" value="<%= chkList.getRecord(11) %>">
			   <input type="HIDDEN" name="NEWRS1_<%= chkList.getCurrentRow() %>" value="<%= chkList.getRecord(12) %>">
			   <input type="HIDDEN" name="NEWRS2_<%= chkList.getCurrentRow() %>" value="<%= chkList.getRecord(13) %>">
			   <input type="HIDDEN" name="NEWRS3_<%= chkList.getCurrentRow() %>" value="<%= chkList.getRecord(14) %>">
			   <input type="HIDDEN" name="NEWRS4_<%= chkList.getCurrentRow() %>" value="<%= chkList.getRecord(15) %>">
			   <input type="HIDDEN" name="NEWGL_<%= chkList.getCurrentRow() %>" value="<%= chkList.getRecord(23) %>">
				<INPUT TYPE=HIDDEN NAME="REMDATA<%= chkList.getCurrentRow() %>" VALUE="<%= chkList.getRecord(22) %>">
                </DIV>
			  </TD>
      			
              <TD ALIGN=Left NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(0) %> - </a> 
				<img name="ImgGL_<%= chkList.getCurrentRow() %>" border="0" alt="GL. Number: <%= chkList.getRecord(26) %> " src="<%=request.getContextPath()%>/images/s/imgGL.gif" > 
                </TD>
      			<TD ALIGN=LEFT NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(1) %></a></TD>
				<TD ALIGN=LEFT NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)">
				<% if (chkList.getRecord(24).equals("1")){out.print("VVS");}
 				else if (chkList.getRecord(24).equals("2")){out.print("CHC");}
				else if (chkList.getRecord(24).equals("3")){out.print("CDP");}
				else if (chkList.getRecord(24).equals("4")){out.print("INP");}
				else if (chkList.getRecord(24).equals("5")){out.print("CHF");}
				else if (chkList.getRecord(24).equals("6")){out.print("VCA");}
				else if (chkList.getRecord(24).equals("C")){out.print("CRE");}
				else if (chkList.getRecord(24).equals("")){out.print("CHK");}
				else {out.print("");}%></a>
				<input type="HIDDEN" name="TYPE_<%= chkList.getCurrentRow() %>" value="<%= chkList.getRecord(24) %>">
				</TD>      
      			<TD ALIGN=LEFT NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(2) %></a></TD>
      			<TD ALIGN=RIGHT NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= datapro.eibs.master.Util.formatCCY(chkList.getRecord(3)) %></a></TD>
      			<TD ALIGN=CENTER NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)">
				<% if (chkList.getRecord(4).equals("R")) out.print("Rechazado"); else if (chkList.getRecord(4).equals("P")) out.print("Protestado"); else out.print("Pagado"); %></a></TD>
				<TD ALIGN=RIGHT NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= datapro.eibs.master.Util.formatCell(chkList.getRecord(45)) %></a></TD>
      			<TD ALIGN=RIGHT NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= datapro.eibs.master.Util.formatCell(chkList.getRecord(43)) %></a></TD>
      			<TD ALIGN=CENTER NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= datapro.eibs.master.Util.concatBR(new String[] {chkList.getRecord(5),chkList.getRecord(6),chkList.getRecord(7),chkList.getRecord(8)}) %></a></TD>
     		</TR>
  <% 
                    }
                }
  %>
 	</table>
   </div>
  </TR>	
</TABLE>

<SCRIPT language="JavaScript">
     function resizeDoc() {
    	adjustEquTables(
    		document.getElementById("headTable"),
    		document.getElementById("dataTable"),
    		document.getElementById("dataDiv1"),1,false);
    }
	applyTrans(document.getElementById("hiddenDivSearch"));
	applyTrans(document.getElementById("hiddenDivCause"));
	applyTrans(document.getElementById("hiddenDivComments"));
	
	 document.onclick= closeDivs;
	 showAddInfo('<%= recnum %>');
     resizeDoc();
     window.onresize=resizeDoc;
    document.getElementById("dataDiv1").onScroll=showChecked("RECNUM");
    
	function setComments(){
	    var list = document.getElementsByName("IMGREM");
	    if (!list)
	        return;
	    for (var i = 0; i < list.length; ++i)
	        if (!list[i].onclick) list[i].onclick = showHiddenDivComments;
	}
	setComments();
	
 	document.getElementById("hiddenDivSearch").onclick=cancelBub;
	document.getElementById("eibsSearch").onclick=showHiddenDivSearch;
	
 	document.getElementById("hiddenDivCause").onclick=cancelBub;
	document.getElementById("eibsCause").onclick=showHiddenDivCause;
     
</SCRIPT>
<BR>
<TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
<%
        if ( chkList.getShowPrev() ) {
      			out.println("<A HREF=\"javascript:ShowPrev()\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/previous_records.gif\" ></A>");
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
