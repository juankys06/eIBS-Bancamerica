<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Información Financiera</TITLE>

<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR"
	content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css"
	rel="stylesheet">

<jsp:useBean id="mL0730" class="datapro.eibs.beans.EDP073001Message" scope="session" />
<jsp:useBean id="optListN" class="datapro.eibs.beans.JBList" scope="session" />
<jsp:useBean id="optListJ" class="datapro.eibs.beans.JBList" scope="session" />
<jsp:useBean id="optListF"  class="datapro.eibs.beans.JBList" scope="session" />
<jsp:useBean id="optList"  class="datapro.eibs.beans.JBListRec" scope="session" />
<jsp:useBean id="grpList" class="datapro.eibs.beans.JBListRec" scope="session" />
<jsp:useBean id="accList" class="datapro.eibs.beans.JBListRec" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />
<jsp:useBean id= "cnofcP4" class= "datapro.eibs.beans.JBList"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>
<script language="javascript">

function init() {
var audw = "<%= mL0730.getE01IFMCFA().trim()%>"
	if (document.forms[0].E01IFMCFA != null) {
		var boxLength = document.forms[0].E01IFMCFA.length;
		i = 0;
		document.forms[0].E01IFMCFA.selectedIndex=0;
		if (boxLength != 0) {
			for (i = 0; i < boxLength; i++) {
				thisvalue = document.forms[0].E01IFMCFA.options[i].value;
				if (thisvalue == audw) {
					document.forms[0].E01IFMCFA.selectedIndex=i;
					break;
	   			}
			}
		}
	}
	if ("<%= mL0730.getH01OPECOD().trim() %>" != "") {
		if (document.forms[0].curpos != null) {
			document.forms[0].curpos.focus()
		}
	}
}


function showOption(obj){
 var i= obj[obj.selectedIndex].value; 
   document.all["dataGrpTable"+ document.forms[0].ActOpt.value].style.display="none"
   document.all["dataGrpTable"+ i].style.display=""
   document.forms[0].ActOpt.value = i;
   adjustEquTables(headTable1, document.all["dataGrpTable"+ i], dataDiv1,1,false);
   showChecked("GRPNUM"+i);
}

function showGrpAcc(flg,idxRow){
  var i= document.forms[0].ActAccTb.value;
  var j= document.forms[0].ActOpt.value;
  var k= document.all["dataGrpTable"+j].rows.length;
  for(var l=0;l<k;l++) {
    document.all["dataGrpTable"+j].rows[l].className="trnormal";
    }
  document.all["dataGrpTable"+j].rows[idxRow].className="trhighlight";
  document.all["dataAccTable"+i].style.display="none";
  document.forms[0].ActAccTb.value=flg;
//  document.forms[0].E01CNORCD.value=trim(dataTable.rows[idxRow].cells[5].innerText);
  try {
  	document.all["dataAccTable"+flg].style.display="";
  	if (document.all["dataAccTable"+flg].rows.length > 0) {
  	ACCINFO.style.display="";
  	adjustEquTables(headTable2, document.all["dataAccTable"+flg], dataDiv2,1,false);
  	showChecked("ACCNUM"+flg);
  	} else ACCINFO.style.display="none";
  } catch(e){}
  finally {}  
  
}    

function setAccInfo(valNA1,idxRow,idxAbsRow,MEMO){

// alert(document.forms[0].E01DPXLID.text);

  var dataTable=document.all["dataAccTable"+document.forms[0].ActAccTb.value];
  for ( var i=0; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idxRow].className="trhighlight";
   document.forms[0].E01IFMNA1.value=valNA1;
   document.forms[0].E01IFMGLN.value=trim(dataTable.rows[idxRow].cells[1].innerText);
   document.forms[0].E01IFMDSC.value=trim(dataTable.rows[idxRow].cells[2].innerText);
   document.forms[0].E01IFMAMT.value=trim(replaceAll(dataTable.rows[idxRow].cells[3].innerText,","));
   document.forms[0].E01CNORCD.value=trim(dataTable.rows[idxRow].cells[5].innerText);
   document.forms[0].E01DPMS01.value="<%=mL0730.getE01DPMS01().trim()%>";
   document.forms[0].E01DPXLID.value=trim(dataTable.rows[idxRow].cells[7].innerText);
   document.forms[0].MEMO.value=MEMO;
   document.forms[0].ROW.value=idxAbsRow;

//alert(document.forms[0].MEMO.value);

}

 function goUpdVal(){
document.forms[0].SCREEN.value=8;
document.forms[0].submit();	 

}

 function updval(valNA1,idxRow,idxAbsRow){


  var dataTable=document.all["dataAccTable"+document.forms[0].ActAccTb.value];
  for ( var i=0; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idxRow].className="trhighlight";

   document.forms[0].E01IFMAMT.value=valNA1;
   document.forms[0].E01IFMNA1.value=valNA1;
   document.forms[0].E01IFMGLN.value=trim(dataTable.rows[idxRow].cells[1].innerText);
   document.forms[0].E01IFMDSC.value=trim(dataTable.rows[idxRow].cells[2].innerText);
//   document.forms[0].E01IFMAMT.value=trim(replaceAll(dataTable.rows[idxRow].cells[3].innerText,","));
   document.forms[0].E01CNORCD.value=trim(dataTable.rows[idxRow].cells[5].innerText);
   document.forms[0].E01DPMS01.value="<%=mL0730.getE01DPMS01().trim()%>";
   document.forms[0].E01DPXLID.value=trim(dataTable.rows[idxRow].cells[7].innerText);
   document.forms[0].ROW.value=idxAbsRow;
	document.forms[0].submit();	 

 }

 function cancelBub(){
  event.cancelBubble = true;
 }

 function ShowAMT() {	 
	 var y= opTable.offsetTop + 10;
     var x= opTable.offsetLeft + (AMT.clientWidth /2);
	 //cancelBub();
	 eval('AMT.style.pixelTop=' + y);
     eval('AMT.style.pixelLeft=' + x);
	 AMT.filters[0].apply();
     AMT.style.visibility="visible";
     AMT.filters[0].Play();
	 //document.forms[0].RUTNUM.focus();
 }

 function hideAMT(){
   	  //formatData.filters[0].apply();
//      AMT.style.visibility="hidden";
      //formatData.filters[0].Play();
 }

// document.onclick= hideAMT;
 
 function goAction(op) {
   //document.forms[0].opt.value = op;
// LA SIGUIENTE PANTALLA LA MUESTRA EN LA MISMA	CenterNamedWindow(page,'Information',800,600,2);
// ABRE OTRA PANTALLA SOBRE LA MISMA QUE LA LLAMA				CenterWindow(page,600,600,2);
   switch (op) {
    case 1 :{
    
		  	if (document.forms[0].E01DPXLID.value != "Titulo") {
			  	if (document.forms[0].E01DPXLID.value != "Calculo") {
//		            ShowAMT();
//    				document.forms[0].E01IFMAMT.focus();
	            }
            }
            }
            break;
    case 2 :{
			if ("<%= mL0730.getE01CUSLGT().trim() %>" == "1") {
	            page= prefix +language + "EDP0730_client_financial_plan_comp.jsp"
 				CenterWindow(page,800,600,2);
			} else {;
        	    page= prefix +language + "EDP0730_client_financial_plan.jsp"
 				CenterWindow(page,600,600,2);
			};
            break;
            }
    case 3 :{
			if ("<%= mL0730.getE01CUSLGT().trim() %>" == "1") {
            page = webapp + "/servlet/datapro.eibs.creditproposal.JSEDP0730?SCREEN=600";
			CenterWindow(page,800,600,2);
			} else {;
            page = webapp + "/servlet/datapro.eibs.creditproposal.JSEDP0730?SCREEN=300&E01DPBFED="
							+ document.forms[0].E01DPBFED.value;
			CenterWindow(page,700,600,2);
			};

    		break;
            }
    case 4 :{
    
			if (document.forms[0].MEMO.value!="0") {
	    		document.forms[0].SCREEN.value="4";
		     	document.forms[0].submit();	  	
			};
    		break;
            }
    case 5 :{

//		var selectedItem = document.forms[0].E01IFMCFOW.selectedIndex;
//		var selectedText = document.forms[0].E01IFMCFOW.options[selectedItem].text;
//		var selectedValue = document.forms[0].E01IFMCFOW.options[selectedItem].value;

//			document.forms[0].TITULO.value = selectedText;
			
//			document.forms[0].opt.value = op;
			document.forms[0].action.value = op;

//			document.forms[0].E01IFMCFO.value = selectedValue;
			document.forms[0].SCREEN.value="400";
			document.forms[0].submit(); 

    		break;
            }
    case 6 :{

//			document.forms[0].opt.value = op;
			document.forms[0].action.value = op;
			document.forms[0].SCREEN.value="800";
//			document.forms[0].submit(); 
            page = webapp + "/servlet/datapro.eibs.creditproposal.JSEDP0730?SCREEN=800&E01IFMFEM="
							+ document.forms[0].E01DPBFEM.value +
							"&E01IFMFEY="+ document.forms[0].E01DPBFEY.value +
							"&E01IFMFED="+ document.forms[0].E01DPBFED.value +
							"&E01IFMCFO="+ document.forms[0].E01IFMCFO.value ;
			CenterWindow(page,800,700,2);

    		break;
            }
    case 7 :{
			if ("<%= mL0730.getE01CUSLGT().trim() %>" == "1") {
            page = webapp + "/servlet/datapro.eibs.creditproposal.JSEDP0739?SCREEN=400&E01DPBFEM="
							+ document.forms[0].E01DPBFEM.value +
							"&E01DPBFEY="+ document.forms[0].E01DPBFEY.value +
							"&E01DPBFED="+ document.forms[0].E01DPBFED.value +
							"&E01IFMCFO="+ document.forms[0].E01IFMCFO.value ;
			CenterWindow(page,800,700,2);
			} else {;
            page = webapp + "/servlet/datapro.eibs.creditproposal.JSEDP0739?SCREEN=400&E01DPBFEY="
							+ document.forms[0].E01DPBFEY.value +
							"&E01DPBFEM="+ document.forms[0].E01DPBFEM.value +
							"&E01DPBFED="+ document.forms[0].E01DPBFED.value +
							"&E01IFMCFO="+ document.forms[0].E01IFMCFO.value ;
			CenterWindow(page,800,700,2);
			};
    		break;
            }
    case 8 :{
			if ("<%= mL0730.getE01CUSLGT().trim() %>" == "1") {
            page = webapp + "/servlet/datapro.eibs.creditproposal.JSEDP0755?SCREEN=400&E01DPBFEM="
							+ document.forms[0].E01DPBFEM.value +
							"&E01DPBFEY="+ document.forms[0].E01DPBFEY.value +
							"&E01DPBFED="+ document.forms[0].E01DPBFED.value +
							"&E01IFMCFO="+ document.forms[0].E01IFMCFO.value ;
			CenterWindow(page,900,650,2);
			} else {;
            page = webapp + "/servlet/datapro.eibs.creditproposal.JSEDP0755?SCREEN=400&E01DPBFEY="
							+ document.forms[0].E01DPBFEY.value +
							"&E01DPBFEM="+ document.forms[0].E01DPBFEM.value +
							"&E01DPBFED="+ document.forms[0].E01DPBFED.value +
							"&E01IFMCFO="+ document.forms[0].E01IFMCFO.value ;
			CenterWindow(page,900,650,2);
			};
    		break;
            }
    case 10 :{
			  var dataTable=document.all["dataAccTable"+document.forms[0].ActAccTb.value];
			// numero de filas de la tabla
//			alert(dataTable.rows.length);

//			for ( var i=0; i<dataTable.rows.length; i++ ) {
//				alert(trim(dataTable.rows[i].cells[1].innerText));
//   			alert(trim(dataTable.rows[i].cells[2].innerText));
//				var evalw = "document.forms[0].VLRCTA00"+i
//				if (eval(evalw) != null) {
//					var evalw = "document.forms[0].VLRCTA00"+i+".value"
//					alert(eval(evalw));
//				}
//		    }
    		break;
            }
   }  
 }

function goMemo(valNA1,idxRow,idxAbsRow,MEMO){

// alert(document.forms[0].E01DPXLID.text);

  var dataTable=document.all["dataAccTable"+document.forms[0].ActAccTb.value];
  for ( var i=0; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idxRow].className="trhighlight";
   document.forms[0].E01IFMNA1.value=valNA1;
   document.forms[0].E01IFMGLN.value=trim(dataTable.rows[idxRow].cells[1].innerText);
   document.forms[0].E01IFMDSC.value=trim(dataTable.rows[idxRow].cells[2].innerText);
   document.forms[0].E01IFMAMT.value=trim(replaceAll(dataTable.rows[idxRow].cells[3].innerText,","));
   document.forms[0].E01CNORCD.value=trim(dataTable.rows[idxRow].cells[5].innerText);
   document.forms[0].E01DPMS01.value="<%=mL0730.getE01DPMS01().trim()%>";
   document.forms[0].E01DPXLID.value=trim(dataTable.rows[idxRow].cells[7].innerText);
   document.forms[0].MEMO.value=MEMO;
   document.forms[0].ROW.value=idxAbsRow;
   document.forms[0].SCREEN.value="4";
   document.forms[0].submit();	  	
}

 function goExit(){

	if (document.forms[0].RETORNO.value != "null") {
	window.location.href=webapp +"<%=userPO.getHeader11()%>";
	} else {
	   	window.location.href="<%=request.getContextPath()%>/pages/background.jsp";
	}
	
  }

function goConfirm() {

//	document.forms[0].E01IFMCFO.value=E01IFMCFOW;

	document.forms[0].submit();	  		  
}

function goVal() {

	document.forms[0].SCREEN.value="6";

	document.forms[0].submit();	  		  
}

function recycl(){
//   window.location.href="<%=request.getContextPath()%>/pages/background.jsp";
}

function selFmt() {
	var selectedItem = document.forms[0].E01IFMCFOW.selectedIndex;
	var selectedText = document.forms[0].E01IFMCFOW.options[selectedItem].text;
	var selectedValue = document.forms[0].E01IFMCFOW.options[selectedItem].value;
	document.forms[0].TITULO.value = selectedText;
	document.forms[0].E01IFMCFO.value = selectedValue;
}


</script>

</HEAD>

<body onload=javascript:init()>

<h3 align="center">Información Financiera Propuesta de Crédito<img
	src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" ALT="client_financial.jsp,EDP0730"></h3>
<hr size="2">
<P align="center">
<INPUT type="text" name="TITULOH" size="45" readonly	value="<%=userPO.getHeader20()%>"></P>
<FORM Method="post" ACTION="<%= request.getContextPath() %>/pages/s/body_wait.jsp?ServletName=<%= request.getContextPath() %>/servlet/datapro.eibs.creditproposal.JSEDP0730">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2"> 
<INPUT TYPE=HIDDEN NAME="ActRow" VALUE="0"> 
<INPUT TYPE=HIDDEN NAME="ActRowD" VALUE="0"> 
<INPUT TYPE=HIDDEN NAME="ActAccTb" VALUE="0"> 
<INPUT TYPE=HIDDEN NAME="ActGrpTb" VALUE="0"> 
<INPUT TYPE=HIDDEN NAME="ActOpt" VALUE="0"> 
<INPUT type=HIDDEN NAME="E01IFMDSC" value="">
<INPUT type=HIDDEN NAME="E01IFMTYP" value="D"> 
<INPUT type=HIDDEN NAME="E01IFMNA1" value=""> 
<INPUT type=HIDDEN NAME="E01DPMS01" value="">
<INPUT type=HIDDEN NAME="E01DPXLID" value=""> 
<INPUT type=HIDDEN NAME="E01IFMAMT" value=""> 
<INPUT type=HIDDEN NAME="E01IFMGLN" value=""> 
<INPUT type=HIDDEN NAME="E01CNORCD" value=""> 

<INPUT type=HIDDEN NAME="ROW" value=""> 

<INPUT type=HIDDEN NAME="MEMO" value=""> 
<INPUT type=HIDDEN NAME="TITULO" value=""> 
<INPUT type=HIDDEN NAME="opt" value=""> 
<INPUT type=HIDDEN NAME="action" value=""> 
<INPUT type=HIDDEN NAME="RETORNO" value="<%=userPO.getHeader11()%>" >
<input type=HIDDEN name="AUD" value="<%= mL0730.getE01IFMCFA().trim()%>">

<table class="tableinfo">
	<TR>
		<TD NOWRAP>
		<TABLE id="headTable" width="100%" cellpading=0 cellspacing=0>
			<tr id="trdark">
				<td align="right"><b>Cliente :</b></td>
				<td nowrap>
				<input type="text" name="E01IFMCUN" size="12" value="<%=userPO.getHeader1()%>" onKeypress="enterInteger()"
				<% if(!userPO.getOption().equals("1")){out.print("readonly");}%> 
				>
				<% if(userPO.getOption().equals("1")) { %> 
	            <a href="javascript:GetCustomerDescId('E01IFMCUN','NAMECUM','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" >
				<% };%>
	            </a> 
				</td>
				<td align="right"><b>Nombre :</b></td>
				<td nowrap colspan=3>
				<input type="text" name="NAMECUM" size="45" readonly value="<%=userPO.getHeader2()%>">
				<INPUT type="text" name="E01IFMCIN" size="36" readonly value="<%=userPO.getHeader18()%>"></td>
			</tr>
			<tr id="trdark">
				<td align="right">Dia/Mes/Año Fin<b></b></td>
				<td nowrap>
				<INPUT type="text" name="E01IFMFED" size="3" maxlength="2" value="<%= mL0730.getE01IFMFED().trim()%>"
				<% if(!userPO.getOption().equals("1")){out.print("readonly");}%> 
				>/
				<INPUT type="text" name="E01IFMFEM" size="3" maxlength="2" value="<%= mL0730.getE01IFMFEM().trim()%>"
				<% if(!userPO.getOption().equals("1")){out.print("readonly");}%> 
				>/
				<INPUT type="text" name="E01IFMFEY" size="5" maxlength="4" value="<%= mL0730.getE01IFMFEY().trim()%>"
				<% if(!userPO.getOption().equals("1")){out.print("readonly");}%> >
				<% if(userPO.getOption().equals("1")) { %> 
				<A 	href="javascript:DOBPicker(document.forms[0].E01IFMFEM,document.forms[0].E01IFMFED,document.forms[0].E01IFMFEY)"><IMG
					src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0">
				</A>
				<% };%>
				</td>

				<td align="right"></td>

				<td nowrap>

				<% if(mL0730.getE01CUSLGT().equals("")){%> 
				<SELECT name="E01IFMCFOW" onchange="selFmt()">
					<%optListF.initRow();
					while (optListF.getNextRow()) {
						if (optListF.getFlag().equals("")) { 
							out.println(optListF.getRecord());
						}
					}%>
				</SELECT>
				<%}%>
				<% if(mL0730.getE01CUSLGT().equals("1")){%> 
				<SELECT name="E01IFMCFOW" onchange="selFmt()">
					<%optListN.initRow();
					while (optListN.getNextRow()) {
						if (optListN.getFlag().equals("")) { 
							out.println(optListN.getRecord());
						}
					}%>
				</SELECT>
				<%}%>
				<% if(mL0730.getE01CUSLGT().equals("2")){%> 
				<SELECT name="E01IFMCFOW" onchange="selFmt()">
					<%optListJ.initRow();
					while (optListJ.getNextRow()) {
						if (optListJ.getFlag().equals("")) { 
							out.println(optListJ.getRecord());
						}
					}%>
				</SELECT>
				<%};%>

				<INPUT type="text" name="E01IFMCFO" size="2" readonly value="<%=userPO.getHeader3()%>">
				<INPUT type="text" name="E01DXXCR1" size="25" readonly value="<%=mL0730.getE01DXXCR1()%>">
				<div align="right"> 
					<INPUT id="EIBSBTN03" type="button" name="Submit0" value="Enviar" onclick="goAction(5)"
					<% if(userPO.getOption().equals("4")){out.print("disabled");}%> >
				</div>

				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<%if (accList.getNoResult()) {%>
<TABLE class="tbenter" width=100% height=30%>
	<TR>
		<TD>
		<% if(!userPO.getOption().equals("1")) { %> 
		
		<div align="center"><font size="3">
		<b> No hay datos que correspondan con su criterio de busqueda </b>
		</font>
		<INPUT type="text" name="TITULO1" size="45" readonly value="<%=userPO.getHeader20()%>"><BR>
		</div>
		<%} else {%>
		<div align="center"><font size="3">
		<b> Ingreso de nuevo estado financiero </b>
		</font>
<%--
		<INPUT type="text" name="TITULO1" size="45" readonly value="<%=userPO.getHeader20()%>"><BR>
--%>
		</div>
<%--
<div align="center"> 
		<BR>
		<INPUT id="EIBSBTN0" type="submit" name="Submit0" value="Enviar" onclick="goAction(5)"
		<% if(userPO.getOption().equals("4")){out.print("disabled");}%> >
</div>
--%>
		<%} %>
		</TD>
	</TR>
</TABLE>
<%} else { %>
<TABLE class="tbenter">
	<TR>
		<TD ALIGN=LEFT width="4%" nowrap><SELECT name="OPTION" DISABLED
			onChange="showOption(this)">
			<%optList.initRow();
            int k=0;
            while (optList.getNextRow()) {
			if (optList.getFlag().equals("")) {%>
				<option value="<%=optList.getCurrentRow()%>"><%=optList.getRecord(0)%></option>
			     <% k++;
                }
            }
    		%>
		</SELECT></TD>
<%--
			<%optList.initRow();
            int k=0;
            while (optList.getNextRow()) {
			if (optList.getFlag().equals("")) {%>
				<INPUT type=HIDDEN value="<%=optList.getCurrentRow()%>">
			     <% k++;
                }
            }
    		%>
--%>

		<TD ALIGN=CENTER width="15%" class=tdbkg><a
			href="javascript:goAction(2)"><b>Estado Financiero</b></a></TD>
		<TD ALIGN=CENTER width="15%" class=tdbkg><a
			href="javascript:goAction(7)"><b>Análisis Variaciones</b></a></TD>
		<TD ALIGN=CENTER width="15%" class=tdbkg><a
			href="javascript:goAction(3)"><b>Indices Financieros</b></a></TD>
		<TD ALIGN=CENTER width="15%" class=tdbkg><a
			href="javascript:goAction(6)"><b>Análisis Aspectos Cuantitativos</b></a></TD>
		<TD ALIGN=CENTER width="15%" class=tdbkg><a
			href="javascript:goAction(8)"><b>Proyección</b></a></TD>
		<TD ALIGN=CENTER width="15%" class=tdbkg><a 
			href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></TD>
	</TR>
</TABLE>
<table class="tableinfo" width="100%">
	<TR>
		<TD NOWRAP width="100%">
		<TABLE id="headTable" width="100%" cellpading=0 cellspacing=0>
			<tr id="trdark">
				<td align="right"><b>Revisado:</b></td>
				<td nowrap>
				<SELECT name="E01DPBREV" 
				<% if(userPO.getOption().equals("4")){out.print("disabled");}%> 
				>
					<OPTION value=""
						<%if (mL0730.getE01DPBREV().equals("")) { out.print("selected"); }%>></OPTION>
					<OPTION value="1"
						<%if (mL0730.getE01DPBREV().equals("1")) { out.print("selected"); }%>>SI</OPTION>
					<OPTION value="0"
						<%if (!mL0730.getE01DPBREV().equals("1")) { out.print("selected"); }%>>NO</OPTION>
				</SELECT>
				</td>
				<td align="right"><b></b></td>
				<td nowrap colspan=3>
				</td>
				<td align="right" width="344"><b>Periodo: Desde:</b>
				<input type="text" name="E01DPBIED" size="2" maxlength="2" value="<%= mL0730.getE01DPBIED().trim()%>" readonly>
				<input type="text" name="E01DPBIEM" size="2" maxlength="2" value="<%= mL0730.getE01DPBIEM().trim()%>" readonly>
				<input type="text" name="E01DPBIEA" size="4" maxlength="4" value="<%= mL0730.getE01DPBIEA().trim()%>" readonly>
				<% if(!userPO.getOption().equals("4")){ %>
				<a href="javascript:DOBPicker(document.forms[0].E01DPBIEM,document.forms[0].E01DPBIED,document.forms[0].E01DPBIEA)">
				<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0">
				</a>
				<%}%> 
				</td>
				<td align="right" width="282"><b>Hasta:</b>
				<input type="text" name="E01DPBFED" size="2" maxlength="2" value="<%=mL0730.getE01IFMFED().trim()%>" readonly>
				<input type="text" name="E01DPBFEM" size="2" maxlength="2" value="<%=mL0730.getE01IFMFEM().trim()%>" readonly>
				<input type="text" name="E01DPBFEY" size="4" maxlength="4" value="<%=mL0730.getE01IFMFEY().trim()%>" readonly>
				<%--
				<% if(!userPO.getOption().equals("4")){ %>
				<a href="javascript:DOBPicker(document.forms[0].E01DPBFEM,document.forms[0].E01DPBFED,document.forms[0].E01DPBFEY)">
				<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0">
				</a>
				<%}%> 
 				--%>

				</td>
			</tr>
			
			
			<tr id="trclear">
				
				<td align="right">
					<b>Clase:</b>
				</td>
						
				<td nowrap>
	            	<SELECT name="E01DPBCRE"
					<% if(userPO.getOption().equals("4")){out.print("disabled");}%> 
	            	>
					<OPTION value=""
						<%if (mL0730.getE01DPBCRE().equals("")) { out.print("selected"); }%>></OPTION>
					<OPTION value="1"
						<%if (mL0730.getE01DPBCRE().equals("1")) { out.print("selected"); }%>>Auditado</OPTION>
					<OPTION value="2"
						<%if (mL0730.getE01DPBCRE().equals("2")) { out.print("selected"); }%>>No Auditado</OPTION>
					<OPTION value="3"
						<%if (mL0730.getE01DPBCRE().equals("3")) { out.print("selected"); }%>>Precierre</OPTION>	
				</SELECT>	 
				</td>
				
				<td align="right">
				</td>
				
				<td nowrap colspan=3>
				<B>Auditado:</B></td>


				<td align="left" width="344"><b></b> <INPUT type="text" name="E01DPBAU1"
					size="35" maxlength="35" value="<%=mL0730.getE01DPBAU1()%>"
					<% if(userPO.getOption().equals("4")){out.print("readonly");}%>>
				<%--	
					<br>
					<select name="E01IFMCFA"
					<% if(userPO.getOption().equals("4")){out.print("disabled");}%> 
					>
             		<%
                	cnofcP4.initRow();
                	while (cnofcP4.getNextRow()) {
                    	if (cnofcP4.getFlag().equals("")) {
                    		out.println(cnofcP4.getRecord());
                    	}        
                	}                 
    				%>
				--%>
				</td>


				<td align="left" width="282">
				<div align="left">

				<b>Fecha Vencim.: </b><%=mL0730.getE01DPBFVD()%>/<%=mL0730.getE01DPBFVM()%>/<%=mL0730.getE01DPBFVA()%>
<%--
				<INPUT type="text" name="E01DPBFVD" size="2" maxlength="2" value="<%=mL0730.getE01DPBFVD()%>" readonly>
				<INPUT type="text" name="E01DPBFVM" size="2" maxlength="2" value="<%=mL0730.getE01DPBFVM()%>" readonly>
				<INPUT type="text" name="E01DPBFVA" size="4" maxlength="4" value="<%=mL0730.getE01DPBFVA()%>" readonly>
--%>
				</div>
				<div align="right">
				<INPUT id="EIBSBTN01" type="button" name="Submit0" value="Enviar" onclick="goVal()"
					<% if(userPO.getOption().equals("4")){out.print("disabled");}%> 
					>
				</div>
				</td>
				
			</tr>
		</table>
		</td>
	</tr>
</table>

<% if(!mL0730.getH01FLGWK3().equals("5")) { %> 

<TABLE id="mainTable" class="tableinfo" ALIGN=CENTER width="100%">
	<TR>
		<TD NOWRAP valign="top" width="100%">
		<TABLE id="headTable1">
			<TR id="trdark">
				<TH ALIGN=CENTER NOWRAP></TH>
				<TH ALIGN=CENTER NOWRAP>Descripción</TH>
				<TH ALIGN=CENTER NOWRAP>Monto</TH>
			</TR>
		</TABLE>

		<div id="dataDiv1" class="scbarcolor"><%boolean firstTime = true;
String grpChk = "";
String flg = "";
int accRow = 0;
int grpRow = 0;
int selOpt = 0;
int actAccTb = 0;
int actGrpTb = 0;

try {
	accRow = Integer.parseInt(request.getParameter("ROW"));
} catch (Exception e) {
};
accList.setCurrentRow(accRow);
flg = accList.getFlag();
actAccTb = Integer.parseInt(flg); //
try {
	grpRow = Integer.parseInt(flg.substring(1, flg.length()));
	selOpt = Integer.parseInt(flg.substring(0, 1));
} catch (Exception e) {
};

for (int i = 0; i < k; i++) {%>
		<table id="dataGrpTable<%=i%>" style="display: none">
			<%int j = 0;
firstTime = true;
grpList.initRow();
while (grpList.getNextRow()) {
	if (grpList.getFlag().equals("" + i)) {
		if (i == selOpt) {
			grpChk = (grpRow == j) ? "checked" : "";
		} else {
			grpChk = (firstTime) ? "checked" : "";
		}
		firstTime = false;%>
			<TR>
				<TD ALIGN=Center NOWRAP>
				<input type="radio" name="GRPNUM<%=i%>"	value="<%=grpList.getFlag() + "" + j%>" onClick="showGrpAcc(this.value,<%=j%>)" <%=grpChk%>></TD>
				<TD ALIGN=LEFT NOWRAP><a
					href="javascript:radioClick('GRPNUM<%=i%>',<%=j%>)">
				<DIV NOWRAP><%=grpList.getRecord(0)%></DIV>
				</a></TD>
				<TD ALIGN=RIGHT NOWRAP>
				<DIV NOWRAP>
				<a href="javascript:radioClick('GRPNUM<%=i%>',<%=j%>)"><%=datapro.eibs.master.Util.fcolorCCY(grpList.getRecord(1))%></a>
				</DIV>
				</TD>
			</TR>
			<%j++;
}
}%>
		</table>
		<%}%></div>
		</TD>
	</TR>
</TABLE>
<DIV id="ACCINFO">
<TABLE class="tbenter" id="opTable">
	<TR>
		<TD ALIGN=CENTER class=tdbkg><a href="javascript:goAction(4)"><b>Memo</b>
		</TD>
	</TR>
</TABLE>
<A NAME="accounts"> </A>

<TABLE id="mainTable2" class="tableinfo" ALIGN=CENTER width="100%">
	<TR>
		<TD NOWRAP valign="top" width="100%">
		<TABLE id="headTable2">
			<TR id="trdark">
				<TH ALIGN=CENTER NOWRAP></TH>
				<TH ALIGN=CENTER NOWRAP>Cuenta</TH>
				<TH ALIGN=CENTER NOWRAP>Descripción</TH>
				<TH ALIGN=CENTER NOWRAP>Monto</TH>
				<TH ALIGN=CENTER NOWRAP>Audit</TH>
				<TH ALIGN=CENTER NOWRAP>Grupo</TH>
				<TH ALIGN=CENTER NOWRAP>Sec.</TH>
				<TH ALIGN=CENTER NOWRAP>Tipo</TH>
				<TH ALIGN=CENTER NOWRAP>Memo</TH>
			</TR>
		</TABLE>

		<div id="dataDiv2" class="scbarcolor"><%grpList.initRow();
int g = 0;
int h = 0;
int chgGrp = 0;
int accRowD = 0;
while (grpList.getNextRow()) {
	if (Integer.parseInt(grpList.getFlag()) != chgGrp) {
		chgGrp = Integer.parseInt(grpList.getFlag());
		g = 0;
	}%>
		<table id="dataAccTable<%=grpList.getFlag() + "" + g%>"
			style="display: none">
			<%String accChk = "";
accList.initRow();
int j = 0;
firstTime = true;
while (accList.getNextRow()) {
	if (accList.getFlag().equals(grpList.getFlag() + "" + g)) {
		if (accList.getFlag().equals(flg)) {
			accChk = (accRowD == accList.getCurrentRow()) ? "checked" : "";
		} else {
			accChk = (firstTime) ? "checked" : "";
		}
		firstTime = false;%>
			<TR>
            <input type="HIDDEN" name="DPLGLN<%=h%>" value="<%=accList.getRecord(0)%>">
            <input type="HIDDEN" name="CNORCD<%=h%>" value="<%=accList.getRecord(5)%>">

				<TD ALIGN=Center NOWRAP>
				<input type="radio" name="ACCNUM<%=accList.getFlag()%>"
					value="<%=accList.getRecord(3)%>" <%=accChk%>
					onClick="setAccInfo(this.value,'<%=j%>','<%=accList.getCurrentRow()%>',<%=accList.getRecord(10)%>)">
				<TD ALIGN=LEFT NOWRAP>
				<div nowrap><a
					href="javascript:radioClick('ACCNUM<%=accList.getFlag()%>',<%=j%>)"><%=accList.getRecord(0)%></a></DIV>
				</TD>
				<TD ALIGN=LEFT NOWRAP>
				<div nowrap><a
					href="javascript:radioClick('ACCNUM<%=accList.getFlag()%>',<%=j%>)"><%=accList.getRecord(1)%></a></DIV>
				</TD>
				<TD ALIGN=LEFT NOWRAP>
				<div nowrap>
				<% if (!accList.getRecord(7).equals("Titulo")) { %>		
					<% if (!accList.getRecord(7).equals("Calculo")) { %>				
					<INPUT type="text" name="VLRCTA<%=h%>" size="21" maxlength="16" value="<%=accList.getRecord(2)%>"  
					<% if(userPO.getOption().equals("4")){out.print("readonly");}%> 
					href="javascript:radioClick('ACCNUM<%=accList.getFlag()%>',<%=j%>)"
					onKeypress="setAccInfo(this.value,'<%=j%>','<%=accList.getCurrentRow()%>',<%=accList.getRecord(10)%>)"					
					>
      			<% } else { %>
					<a href="javascript:radioClick('ACCNUM<%=accList.getFlag()%>',<%=j%>)"><%=datapro.eibs.master.Util.fcolorCCY(accList.getRecord(2))%></a>
      			<% } %>
      			<% } %>
				</DIV>
				</TD>
				<TD ALIGN=LEFT NOWRAP>
				<div nowrap><a
					href="javascript:radioClick('ACCNUM<%=accList.getFlag()%>',<%=j%>)"><%=accList.getRecord(4)%> </a></DIV>
				</TD>
				<TD ALIGN=LEFT NOWRAP>
				<div nowrap><a
					href="javascript:radioClick('ACCNUM<%=accList.getFlag()%>',<%=j%>)"><%=accList.getRecord(5)%></a></DIV>
				</TD>
				<TD ALIGN=LEFT NOWRAP>
				<div nowrap>
					<a
					href="javascript:radioClick('ACCNUM<%=accList.getFlag()%>',<%=j%>)"><%=accList.getRecord(6)%>
					</a>
				</DIV>
				</TD>
				<TD ALIGN=LEFT NOWRAP>
				<div nowrap>
					<a
					href="javascript:radioClick('ACCNUM<%=accList.getFlag()%>',<%=j%>)"><%=accList.getRecord(7)%>
					</a>
				</DIV>
				</TD>
				<TD ALIGN=LEFT NOWRAP>
				<div nowrap>
					<a
					<% if (!accList.getRecord(10).equals("0")) { %>	
					href="javascript:radioClick('ACCNUM<%=accList.getFlag()%>',<%=j%>)">
	                <img src="<%=request.getContextPath()%>/images/page_properties.gif" alt="Memo" align="absbottom" border="0" width="16" height="20" onclick="goMemo(this.value,'<%=j%>','<%=accList.getCurrentRow()%>',<%=accList.getRecord(10)%>)">

					<%}%>
					</a>
					<a
					<% if (accList.getRecord(11).equals("1")) { %>	
					href="javascript:radioClick('ACCNUM<%=accList.getFlag()%>',<%=j%>)">
	                <img src="<%=request.getContextPath()%>/images/close_up.gif" alt="Memo" align="absbottom" border="0" width="16" height="20" onclick="goMemo(this.value,'<%=j%>','<%=accList.getCurrentRow()%>',<%=accList.getRecord(10)%>)">
					<%}%>
					
					</a>
				</DIV>
				</TD>
			</TR>

			<%j++;h++;} %>
<%}%>

		</table>
		<%g++;
}%>
    <input type="HIDDEN" name="RECNUM" value="<%=h%>">
</div>
	</TR>

</TABLE>
<div align="center"> 
		<INPUT id="EIBSBTN02" type="button" name="Submit0" value="Enviar" onclick="goUpdVal()"
	<% if(userPO.getOption().equals("4")){out.print("disabled");}%> 
	>
</div>


</DIV>
<SCRIPT language="JavaScript">
   document.forms[0].ActRow.value=<%=accRow%>;
   document.forms[0].ActRowD.value=<%=accRowD%>;
   document.forms[0].ActAccTb.value="<%=flg%>";
   document.forms[0].ActGrpTb.value="<%=selOpt%>";
   document.forms[0].ActOpt.value=<%=selOpt%>;

   document.forms[0].OPTION.value=<%=selOpt%>;
   
   showOption(document.forms[0].OPTION);  
   function resizeDoc() {
      var actvTbGrp = document.forms[0].ActGrpTb.value;
      var actvTbAcc = document.forms[0].ActAccTb.value;
      var dataTGrp = document.all["dataGrpTable"+actvTbGrp];
      var dataTAcc = document.all["dataAccTable"+actvTbAcc];
       adjustEquTables(headTable1, dataTGrp, dataDiv1,1,false);
       adjustEquTables(headTable2, dataTAcc, dataDiv2,1,false);
   }
    
   resizeDoc();
   window.onresize=resizeDoc;
</SCRIPT>
<% } %> 
<% } %>
<INPUT type="text" maxlength="1" size="1" name="curpos" value="" readonly>

</FORM>

</BODY>
</HTML>
