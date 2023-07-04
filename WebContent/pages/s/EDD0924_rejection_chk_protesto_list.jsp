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

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="javascript">

function retorna_pagina(){
location= prefix + language + "EDD0924_rejection_chk_search.jsp";
} 

function hideDiv(divobj){
   	  divobj.filters[0].apply();
      divobj.style.visibility="hidden";
      divobj.filters[0].Play();
}

function closeDivs() {
  hideDiv(schACC);
  hideDiv(schCause);
  hideDiv(Comments);
}

function cancelBub(){
  event.cancelBubble = true;
}

function OkComments(){
	var idxchk =document.forms[0].ActRow.value;
	document.forms[0]["REMDATA"+idxchk].value = trim(document.forms[0].txtComments.value);
	if (document.forms[0]["REMDATA"+idxchk].value == ""){
	  document.images["IMGREM"+idxchk].style.display="none";
    } else {
      document.images["IMGREM"+idxchk].style.display="";
	}
	adjustEquTables(headTable, dataTable, dataDiv1,1,false);
	Comments.filters[0].apply();
    Comments.style.visibility="hidden";
    Comments.filters[0].Play();
}

function addComments() {
	 var idxchk =document.forms[0].ActRow.value;	 
	 var y= mainTable.offsetTop + 10;
     var x= mainTable.offsetLeft  + (Comments.clientWidth /2);
	 
	 eval('Comments.style.pixelTop=' + y );
     eval('Comments.style.pixelLeft=' + x);
	 Comments.filters[0].apply();
     Comments.style.visibility="visible";
     Comments.filters[0].Play();
     document.forms[0].txtComments.value=document.forms[0]["REMDATA"+idxchk].value;
	 document.forms[0].txtComments.focus();
}

function ShowComments(idxchk) {	 
	 var y=event.clientY + document.body.scrollTop - Comments.clientHeight;
     var x=event.clientX + document.body.scrollLeft - 1;
	 document.forms[0]["RECNUM"][idxchk].click();
	 cancelBub();
	 hideDiv(schCause);
  	 hideDiv(schACC);
	 eval('Comments.style.pixelTop=' + y);
     eval('Comments.style.pixelLeft=' + x);
	 Comments.filters[0].apply();
     Comments.style.visibility="visible";
     Comments.filters[0].Play();
     document.forms[0].txtComments.value=document.forms[0]["REMDATA"+idxchk].value;
	 document.forms[0].txtComments.focus();
}

function ShowSearchAcc() {	 
	 var y=event.clientY + document.body.scrollTop - schACC.clientHeight;
     var x=event.clientX + document.body.scrollLeft - 1;
	 cancelBub();
	 hideDiv(schCause);
  	 hideDiv(Comments);
	 eval('schACC.style.pixelTop=' + y);
     eval('schACC.style.pixelLeft=' + x);
	 schACC.filters[0].apply();
     schACC.style.visibility="visible";
     schACC.filters[0].Play();
	 document.forms[0].ACCNUM.focus();
}

function ShowSearchCause() {	 
	 var y=event.clientY + document.body.scrollTop - schCause.clientHeight;
     var x=event.clientX + document.body.scrollLeft - schCause.clientWidth;
	 cancelBub();
	 hideDiv(schACC);
  	 hideDiv(Comments);
	 eval('schCause.style.pixelTop=' + y);
     eval('schCause.style.pixelLeft=' + x);
	 schCause.filters[0].apply();
     schCause.style.visibility="visible";
     schCause.filters[0].Play();
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
	}
		if (op < 5) 
		{
			CenterWindow(pg,620,500,2);
		}
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
  dataTable.rows[i].className="trnormal";
  dataTable.rows[idxRow].className="trhighlight";
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
	
	}else {
		if (form["OPTION_"+currentrow].value == "0009") {
		
	  		if (form["TYPE_"+currentrow].value == ""){ 
				if (form["ESTADO_"+currentrow].value == "X"){ 
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
					form["OPTION_"+currentrow].value = " ";
				  	alert("El Cheque debe estar Protestado");
				}    
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

<h3 align="center">Resoluci&oacute;n Protesto Central de Canje<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rejection_chk_protesto_list.jsp,EDD0924"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEDD0924">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="5">
<INPUT TYPE=HIDDEN NAME="ActRow" VALUE="0">
<INPUT TYPE=HIDDEN NAME="FlagMov" VALUE="0">
<%
	int recnum = 0;
	if ( chkList.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=90% >
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
  <div id="schACC" style="position:absolute; visibility:hidden; left:25px; top:-50px; z-index:3; filter:progid:DXImageTransform.Microsoft.Fade(duration=1.0,overlap=1.0)" onclick="cancelBub()">
 <TABLE id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
	<TR>
	  <TD ALIGN=right nowrap>
          Buscar Cuenta : 
          <input type="text" maxlength=13 size=12 name="ACCNUM" onKeyPress="enterInteger()"
			oncontextmenu="showPopUp(accountHelp,this.name,<%= dataCR.getBank() %>,'','','','RT'); return false;" >
	  </TD>
      <TD ALIGN=Left nowrap>
	     <a href="javascript:showACC()"><img name="Img1" border="0" src="<%=request.getContextPath()%>/images/search1.gif"></a> 
	  </TD>
   </TR>
 </TABLE>
</div>
<div id="schCause" style="position:absolute; visibility:hidden; left:25px; top:-100px; z-index:3; filter:progid:DXImageTransform.Microsoft.Fade(duration=1.0,overlap=1.0)" onclick="cancelBub()">
 <TABLE id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
	<TR>
	  <TD ALIGN=right nowrap>
          Buscar Causal : 
          <input type="text" name="CAUSE" maxlength=4 size=4 
			oncontextmenu="showPopUp(causeHelp,this.name,<%= dataCR.getBank() %>,'','','',''); return false;">
      </TD>
      <TD ALIGN=Left nowrap>
	     <a href="javascript:checkCause()"><img name="Img2" border="0" src="<%=request.getContextPath()%>/images/search1.gif"></a> 
	  </TD>
   </TR>
 </TABLE>
</div>
<div id="Comments" style="position:absolute; visibility:hidden; left:25px; top:-100px; z-index:3; filter:progid:DXImageTransform.Microsoft.Fade(duration=1.0,overlap=1.0)" onclick="cancelBub()">
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
      <TD class="TDBKG"><a href="javascript:goAction(1)">Sumario</a></TD>
      <TD class="TDBKG"><a href="javascript:goAction(2)">Consulta</a></TD>
      <TD class="TDBKG"><a href="javascript:goAction(3)">Estado<br>de Cuentas</a></TD>
      <TD class="TDBKG"><a href="javascript:goAction(4)">Promedios</a></TD>
      <!--<TD class="TDBKG"><a href="javascript:goAction(5)">Firma</a></TD>
      <TD class="TDBKG"><a href="javascript:goAction(6)">Imagen de<br>Cheque</a></TD>-->
      <TD class="TDBKG"><a href="javascript:addComments()">Comentarios</a></TD>
    </TR>
   
  </TABLE>
  <TABLE class="tbenter" width="100%">
    <TR>
      <TD ALIGN=CENTER ><input type="radio" name="ACTION value="" checked onClick="SetSelection('')">Limpiar Todos</TD>
	  <TD ALIGN=CENTER ><input type="radio" name="ACTION value="0002" onClick="SetSelection('0002')">Devolver Todos</TD>
	  <TD ALIGN=CENTER ><input type="radio" name="ACTION value="0003" onClick="SetSelection('0003')">Pagar Todos</TD>
	  <TD ALIGN=CENTER ><input type="radio" name="ACTION value="0005" onClick="SetSelection('0005')">Protestar Todos</TD>
	</TR>
  </TABLE>      
  
  <TABLE  id="mainTable" class="tableinfo" ALIGN=CENTER width="100%" height="86" >
    <TR> 
      <TD NOWRAP valign="top" width="100%"> 
        <TABLE id="headTable" >
     		<TR id="trdark">  
      			<TH ALIGN=CENTER NOWRAP>Acción</TH>
      			<TH ALIGN=CENTER NOWRAP style="cursor:hand" onClick="ShowSearchAcc()">Cuenta</TH>
      			<TH ALIGN=CENTER NOWRAP>Nombre</TH>
				<TH ALIGN=CENTER NOWRAP>Tipo</TH>     
      			<TH ALIGN=CENTER NOWRAP>No. Cheque</TH>
      			<TH ALIGN=CENTER NOWRAP>Monto</TH>
      			<TH ALIGN=CENTER NOWRAP>Estado</TH>
      			<TH ALIGN=CENTER NOWRAP style="cursor:hand" onClick="ShowSearchCause()">Causal</TH>
				<TH ALIGN=CENTER NOWRAP>Suc.</TH>
				<TH ALIGN=CENTER NOWRAP>Ejec</TH>
     		</TR>
    	 </TABLE>
  
   <div id="dataDiv1" class="scbarcolor">
    	<table id="dataTable">
  <%
          String chk = "";
		  try { 
		  		recnum = Integer.parseInt(request.getParameter("ROW")); } catch (Exception e) {};
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
                  <select name="OPTION_<%= chkList.getCurrentRow() %>" onChange="GetInfo('<%= chkList.getCurrentRow() %>')">
                    <option value="" <% if (chkList.getRecord(17).equals("")) out.print("selected"); %>></option>
                    <option value="0002" <% if (chkList.getRecord(17).equals("0002")) out.print("selected"); %>>Devolver</option>
                    <option value="0003" <% if (chkList.getRecord(17).equals("0003")) out.print("selected"); %>>Pagar</option>
                    <option value="0004" <% if (chkList.getRecord(17).equals("0004")) out.print("selected"); %>>Cambiar</option>
					<option value="0005" <% if (chkList.getRecord(17).equals("0005")) out.print("selected"); %>>Protestar</option>
					<option value="0009" <% if (chkList.getRecord(17).equals("0009")) out.print("selected"); %>>Listar</option>
                </select>
                  <img name="IMGREM<%= chkList.getCurrentRow() %>" src="<%=request.getContextPath()%>/images/s/info.gif" alt="More Information" border="0" align="absmiddle" onClick="ShowComments(<%= chkList.getCurrentRow() %>)"
				 <% if (chkList.getRecord(22) == null || chkList.getRecord(22).trim().equals("")) out.print("style=\"display:none;\""); %>> 
                <input type="HIDDEN" name="CURACC_<%= chkList.getCurrentRow() %>" value="<%= chkList.getRecord(0) %>">
                <input type="HIDDEN" name="CURCHK_<%= chkList.getCurrentRow() %>" value="<%= chkList.getRecord(2) %>">
                <input type="HIDDEN" name="FULACC_<%= chkList.getCurrentRow() %>" value="<%= chkList.getRecord(31) %>">
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
      			
              <TD ALIGN=Left NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(0) %> </a> 
<%--				<img name="ImgGL_<%= chkList.getCurrentRow() %>" border="0" alt="GL. Number: <%= chkList.getRecord(26) %> " src="<%=request.getContextPath()%>/images/s/imgGL.gif" > --%>
                </TD>
      			<TD ALIGN=LEFT NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(1) %></a></TD>
				<TD ALIGN=LEFT NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)">
				<% if (chkList.getRecord(24).equals(" ")){out.print("NRM");}
 				else if (chkList.getRecord(24).equals("1")){out.print("GER");} 
				else if (chkList.getRecord(24).equals("2")){out.print("CER");}
				else if (chkList.getRecord(24).equals("3")){out.print("CDP");}
				else if (chkList.getRecord(24).equals("4")){out.print("OPG");}
				else if (chkList.getRecord(24).equals("5")){out.print("FIS");}
				else if (chkList.getRecord(24).equals("6")){out.print("CNF");}
				else {out.print("NRM");}%></a>

				<input type="HIDDEN" name="TYPE_<%= chkList.getCurrentRow() %>" value="<%= chkList.getRecord(24) %>">
				</TD>      
      			<TD ALIGN=LEFT NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(2) %></a></TD>
      			<TD ALIGN=RIGHT NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= datapro.eibs.master.Util.formatCCY(chkList.getRecord(3)) %></a></TD>
      			<TD ALIGN=CENTER NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)">
				<% if (chkList.getRecord(4).equals("R")) out.print("Devuelto"); else if (chkList.getRecord(4).equals("P")) out.print("Protestado"); else out.print("Pagado"); %></a></TD>
				<input type="HIDDEN" name="ESTADO_<%= chkList.getCurrentRow() %>" value="<%= chkList.getRecord(4) %>">
      			<TD ALIGN=LEFT NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(5) %></a></TD>
				<TD ALIGN=CENTER NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(28) %></a></TD>
				<TD ALIGN=CENTER NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(26) %></a></TD>
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
      adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      }
     if ( getMSIEVer() < 5.5 ) {
		schACC.style.filter="blendTrans(duration=1)"; 
		Comments.style.filter="blendTrans(duration=1)";
		schCause.style.filter="blendTrans(duration=1)"; 
	 }
	 document.onclick= closeDivs;
	 showAddInfo('<%= recnum %>');
     resizeDoc();
     window.onresize=resizeDoc;
     dataDiv1.onScroll=showChecked("RECNUM");
     
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

       <p align="center"> 
    	  <input id="EIBSBTN" type=Submit name="Submit" value="Enviar" onClick="SendInfo()">
    	</p>
  
<%           
  }
%>
</FORM>


</BODY>
</HTML>
