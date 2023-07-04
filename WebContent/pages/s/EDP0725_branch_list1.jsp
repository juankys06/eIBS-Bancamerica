<%@ page import ="datapro.eibs.master.Util" %>
<html>
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<%@ page import = "java.io.*,java.net.*,datapro.eibs.sockets.*,datapro.eibs.beans.*,datapro.eibs.master.*,java.math.*" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<TITLE>Mantenimiento de Garantias</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="gaList" class="datapro.eibs.beans.JBListRec" scope="session" />
<jsp:useBean id="gaCodeList" class="datapro.eibs.beans.JBListRec" scope="session" />
<jsp:useBean id="gaCodeListR" class="datapro.eibs.beans.JBListRec" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="beanListM" class="datapro.eibs.beans.EDP072501Message"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id="EDP072501Help"  class="datapro.eibs.beans.JBObjList" scope="session" />
<jsp:useBean id="EDP072501HelpD" class="datapro.eibs.beans.JBObjList" scope="session" />
<jsp:useBean id="EDP072501HelpR" class="datapro.eibs.beans.JBObjList" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="JavaScript">

	builtNewMenu(pc_optionGaran);


function callInfF() {

var customer = document.forms[0].IDAVAL.value;    
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0730?SCREEN=200&E01IFMCUN="+ customer;
    	CenterWindow(page,600,600,2);

}

function showCollItems(idx,flg,SEQ,TYP,DES,OWN){
	var actTab= document.forms[0].CCOD.value;
	document.all["dataTableactTab"].style.display="";
	document.forms[0].CCOD.value=flg;
	adjustEquTables(headTable2, document.all["dataTableactTab"], dataDiv2,1,false);
	showChecked("COLLITEM"+flg);    
	document.forms[0].SEQ.value = SEQ;
	getParams(idx, SEQ,TYP,DES);
}

function showCollItemsIBS(idx,flg,SEQ,TYP,DES,OWN){
	var actTab= document.forms[0].CCOD.value;
	//document.all["dataTableactTab"].style.display="";
	//document.forms[0].CCOD.value=flg;
	//adjustEquTables(headTable2, document.all["dataTableactTab"], dataDiv2,1,false);
	//showChecked("COLLITEM"+flg);    
	document.forms[0].SEQ.value = SEQ;
	getParams(idx, SEQ,TYP,DES);
}

function setItemInfo1(idx,param2,param3){
	var actTab= document.forms[0].CCOD.value;
//	document.forms[0].IDAVAL.value = param2;
	getParamsD1(idx);
}


function setItemInfo(idx,SEQ,IDAVAL){
	var actTab= document.forms[0].CCOD.value;
	document.forms[0].IDAVAL.value = IDAVAL;
	getParamsD(SEQ);
}

function closeEnterACC(){
   	  enterACC.filters[0].apply();
      enterACC.style.visibility="hidden";
      enterACC.filters[0].Play();
}

function cancelBub(){
  event.cancelBubble = true;
}

function ShowEnterAcc() {	 
	 var y=event.clientY + document.body.scrollTop - enterACC.clientHeight;
     var x=event.clientX + document.body.scrollLeft - (enterACC.clientWidth/2);
	 cancelBub();
	 eval('enterACC.style.pixelTop=' + y);
     eval('enterACC.style.pixelLeft=' + x);
	 enterACC.filters[0].apply();
     enterACC.style.visibility="visible";
     enterACC.filters[0].Play();
	 document.forms[0].accnum.focus();
}

function goNew(){
   <%boolean autoNum = true;
try {
	autoNum = JSEIBSProp.getAutoNumCOLL();
} catch (Exception e) {
}
if (!autoNum) {%>
	  ShowEnterAcc();
   <%} else {%>
	  document.forms[0].submit();
   <%}%>
}

function goSubmit(){
  document.forms[0].submit();
}



function goAction(op) {
	switch (op) {
	case  1: 
			document.forms[0].opt.value = op;
	        document.forms[0].SCREEN.value = 210;
			document.forms[0].SEQ.value = "";
	        document.forms[0].submit();
			break; 
	case  2: 
			document.forms[0].opt.value = op;
	        document.forms[0].SCREEN.value = 220;
	        document.forms[0].submit();
			break;   
	case  3: ok = confirm("Esta seguro que desea eliminar el registro seleccionado?");
	  		if (ok) 
	       {
	       		document.forms[0].opt.value = op;
	       		document.forms[0].SCREEN.value = 230;
	       		document.forms[0].submit();
	       }
		   break;   
	case  4: document.forms[0].opt.value = op;
	       	document.forms[0].SCREEN.value = 220;
	       	document.forms[0].submit();
			break;   
	case  5: ok = confirm("Esta seguro que desea agregar el registro seleccionado?");
	         if (ok)
	         {
	            document.forms[0].SCREEN.value = 510;
	            document.forms[0].submit();
	         }   
		     break;			
	case  6: document.forms[0].opt.value = op;
	       document.forms[0].SCREEN.value = 310;
	       document.forms[0].submit();
		break; 
	case  7: document.forms[0].opt.value = op;
	       	document.forms[0].SCREEN.value = 320;
	       	document.forms[0].submit();
			break;   
	case  8: ok = confirm("Esta seguro que desea eliminar el registro seleccionado?");
	  		if (ok) 
	       {
	       	document.forms[0].opt.value = op;
	       	document.forms[0].SCREEN.value = 330;
	       	document.forms[0].submit();
	       }
			break;   
	case  9:
		 document.forms[0].opt.value = op;
	     	document.forms[0].SCREEN.value = 320;
	       document.forms[0].submit();
			break;   
	case  10: 
			document.forms[0].opt.value = op;
	       	document.forms[0].SCREEN.value = 220;
	       	document.forms[0].submit();
			break;   
	}

}

function getParams(currrow,SEQ,TYP,DES,CUS) {
	document.forms[0].CURRENTROW.value = currrow;
	document.forms[0].SEQ.value = SEQ;
	document.forms[0].TYP.value = TYP;
	document.forms[0].DES.value = DES;
	document.forms[0].CUS.value = CUS;
}

function getParamsD1(SEQ,GARibs) {
	document.forms[0].SEQD.value = SEQ;
	document.forms[0].GARibs.value = GARibs;
}

function getParamsD(SEQ) {
	document.forms[0].SEQD.value = SEQ;
}

function goCancel() {

document.forms[0].SCREEN.value="100";
document.forms[0].submit(); 
	  		  
}
// document.onclick= closeEnterACC;
</SCRIPT>
</HEAD>
<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
    out.println("<SCRIPT> initMenu(); </SCRIPT>");
 
%> 

<h3 align="center">Garant&iacute;as por Propuesta de Credito<img
	src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" ALT="branch_list1.jsp,EDP072501"></h3>
<hr>

<FORM name="form1" METHOD="post"
	action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0725">
	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="100"> 
	<INPUT TYPE=HIDDEN NAME="CCOD" VALUE=""> 
	<INPUT TYPE=HIDDEN NAME="ICOD" VALUE=""> 
	<INPUT TYPE=HIDDEN NAME="ROW" VALUE="0"> 
	<input type=HIDDEN name="SEQ" value=""> 
	<input type=HIDDEN name="SEQD" value=""> 
	<input type=HIDDEN name="GARibs" value="">
	<input type=HIDDEN name="TYP" value=""> 
	<input type=HIDDEN name="DES" value=""> 
	<INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0"> 
	<input type=HIDDEN name="opt">
	<input type=HIDDEN name="CUS" value=""> 
	<input type=HIDDEN name="IDAVAL" value=""> 

<div id="enterACC"
	style="position: absolute; visibility: hidden; left: 25px; top: -50px; z-index: 3; filter: BlendTrans(duration=1)"
	onclick="cancelBub()">
<TABLE id=tbhelp
	style="border-top-width: 1px; border-right-width: 1px; border-bottom-width: 1px; border-left-width: 1px; border-color: #0b23b5; border-style: solid solid solid solid; filter: progid : DXImageTransform . Microsoft . dropshadow(OffX=3, OffY=3, Color='gray', Positive='true')">
	<TR>
		<TD ALIGN=right nowrap>Introduzca el Número de Cuenta : <input
			type="text" maxlength=12 size=12 name="accnum"
			onKeyPress="enterInteger()" value=""></TD>
		<TD ALIGN=Left nowrap><a href="javascript:goSubmit()"><img
			name="Image1" border="0"
			src="<%=request.getContextPath()%>/images/s/applicar_on.gif"></a></TD>
	</TR>
</TABLE>
</div>

   <TABLE id="headTable" width="100%" cellpading=0 cellspacing=0>
    <tr id="trdark"> 
      <td align="right"  >
         <b>Cliente :</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01DPCCUN" size="12" readonly value="<%= userPO.getCusNum()%>">
      </td>
      <td align="right"  >
         <b>Nombre :</b>
      </td>
      <td nowrap colspan=3> 
         <input type="text" name="E01CUSNA1" size="45" readonly value="<%=userPO.getCusName()%>">
      </td>         
    </tr> 
    <tr id="trdark"> 
      <td align="right"  >
         <b>Nro. Propuesta :</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01DPCNPR" size="15" readonly value="<%=userPO.getHeader4()%>">
      </td>
      <td nowrap align="right">
      </td>
      <td nowrap colspan=3> 
      </td>
    </tr> 
  </table> 


<TABLE id="mainTable" class="tableinfo">
<TR>
	<TD NOWRAP valign="top" width="100%">
	<h3>Garantias Propuesta</h3>

	<table class="tbenter" width=100% align=center>
		<tr>
			<td class=TDBKG width="20%">
			<div align="center"><a href="javascript:goAction(1)"><b>Nueva</b></a></div>
			</td>
			<td class=TDBKG width="20%">
			<div align="center"><a href="javascript:goAction(4)"><b>Consultar</b></a></div>
			</td>
			<td class=TDBKG width="20%">
			<div align="center"><a href="javascript:goAction(2)"><b>Modificar</b></a></div>
			</td>
			<td class=TDBKG width="20%">
			<div align="center"><a href="javascript:goAction(3)"><b>Borrar</b></a></div>
			</td>
			<td class=TDBKG width="20%">
			<div align="center">
			<a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a>
			</div>
			</td>
		</tr>
	</table>


		<TABLE id="headTable1">
			<tr id="trdark">
				<th align=CENTER nowrap width="5%">&nbsp;</th>
				<th align=CENTER nowrap width="5%">
				<div align="center">Secuencia</div>
				</th>
				<th align=CENTER nowrap width="10%">
				<div align="center">Tipo</div>
				</th>
				<th align=CENTER nowrap width="15%">
				<div align="center">Código</div>
				</th>
				<th align=CENTER nowrap width="35%">
				<div align="center">Descripción</div>
				</th>
				<th align=CENTER nowrap width="25%">
				<div align="center"></div>
				</th>
			</tr>
		</TABLE>

		<div id="dataDiv1" class="scbarcolor">
		<table id="dataTable">
			<%boolean firstTime = true;
			String gaChk = "";
			gaCodeList.initRow();
			int i = 0;

			EDP072501Help.initRow();

			while (gaCodeList.getNextRow()) {
				if (gaCodeList.getFlag().equals("")) {
					gaChk = (firstTime) ? "checked" : "";
					firstTime = false;

					EDP072501Help.getNextRow();
					datapro.eibs.beans.EDP072501Message msgList =
					(datapro.eibs.beans.EDP072501Message) EDP072501Help.getRecord();%>
				<TR>
					<TD ALIGN=LEFT NOWRAP>
					<input type="radio" name="COLLCOD" 
					value="<%=EDP072501Help.getCurrentRow()%>" 
					onClick="showCollItems(this.value,<%=gaCodeList.getRecord(2)%>,
					'<%= msgList.getE01DPCSEQ().trim()%>',
					'<%= msgList.getE01DPCTYP().trim()%>',
					'<%= msgList.getE01DPCDES().trim()%>',
					'<%= msgList.getE01DPCOWN().trim()%>')" <%=gaChk%>>
					</TD>
					<TD ALIGN=CENTER NOWRAP><a href="javascript:radioClick('COLLCOD',<%=i%>)">
					<DIV NOWRAP><%=gaCodeList.getRecord(2)%></DIV>
					</a></TD>
					<TD ALIGN=CENTER NOWRAP><a href="javascript:radioClick('COLLCOD',<%=i%>)">
					<DIV NOWRAP><%=gaCodeList.getRecord(0)%>-<%=gaCodeList.getRecord(5)%></DIV>
					</a></TD>
					<TD ALIGN=LEFT NOWRAP><a href="javascript:radioClick('COLLCOD',<%=i%>)">
					<DIV NOWRAP><%=gaCodeList.getRecord(1)%></DIV>
					</a></TD>
					<TD ALIGN=CENTER NOWRAP><a href="javascript:radioClick('COLLCOD',<%=i%>)">
					<DIV NOWRAP><%=gaCodeList.getRecord(3)%></DIV>
					</a></TD>
					<TD ALIGN=CENTER NOWRAP><a href="javascript:radioClick('COLLCOD',<%=i%>)">
					<DIV NOWRAP></DIV>
					</a></TD>

				</TR>

			<%i++;
			}
			}%>
		</table>
		</div>
	</TD>
</TR>
</TABLE>
<TR>

<TABLE id=tabDESC class="tbenter">
	<TR>
		<TD ALIGN=RIGHT width="15%" nowrap></TD>
		<TD ALIGN=LEFT>
		</TD>
		<TD ALIGN=RIGHT width="45%" nowrap>
			</TD>
		<TD ALIGN=LEFT>
		</TD>
	</TR>
</TABLE>

<%--                                --%> 
<TABLE id="mainTable" class="tableinfo">
	<TR>
		<TD NOWRAP valign="top" width="100%">
		<h3>Avales, Fianzas/Co-Solicitante Propuesta</h3>
		<table class="tbenter" width=100% align=center>
			<tr>
				<td class=TDBKG width="20%">
				<div align="center"><a href="javascript:goAction(6)"><b>Nueva</b></a></div>
				</td>
				<td class=TDBKG width="20%">
				<div align="center"><a href="javascript:goAction(9)"><b>Consultar</b></a></div>
				</td>
				<td class=TDBKG width="20%">
				<div align="center"><a href="javascript:goAction(7)"><b>Modificar</b></a></div>
				</td>
				<td class=TDBKG width="20%">
				<div align="center"><a href="javascript:goAction(8)"><b>Borrar</b></a></div>
				</td>
			</tr>
		</table>

		<TABLE id="headTable2">
			<TR id="trdark">
				<TH ALIGN=CENTER NOWRAP></TH>
				<TH ALIGN=CENTER NOWRAP>Secuencia</TH>
				<TH ALIGN=CENTER NOWRAP>Tipo</TH>
				<TH ALIGN=CENTER NOWRAP>Descripción</TH>
				<TH ALIGN=CENTER NOWRAP>No. Cliente.</TH>
			</TR>
		</TABLE>

		<div id="dataDiv2" class="scbarcolor">

		<%gaCodeList.initRow();
		gaList.initRow();
		%>
		<table id="dataTableactTab">
			<%int j = 0;
String itemChk = "";
firstTime = true;
gaList.initRow();
EDP072501HelpD.initRow();

while (gaList.getNextRow()) {
		itemChk = (firstTime) ? "checked" : "";
		firstTime = false;
		EDP072501HelpD.getNextRow();
		datapro.eibs.beans.EDP072501Message msgList =
			(datapro.eibs.beans.EDP072501Message) EDP072501HelpD.getRecord();%>
			<TR>
				<TD ALIGN=Left NOWRAP>
				<input type="radio"
					name="COLLITEM" value="<%=EDP072501HelpD.getCurrentRow()%>" <%=itemChk%> 
					onClick="setItemInfo(this.value,
					'<%= msgList.getE01DPGSGR().trim()%>',
					'<%= msgList.getE01DPGIDN().trim()%>');">
				</TD>
				<TD ALIGN=CENTER NOWRAP>
				<div nowrap><a
					href="javascript:radioClick('COLLITEM1',<%=j%>)"><%=gaList.getRecord(0)%></a></DIV>
				</TD>
				<TD ALIGN=LEFT NOWRAP>
				<div nowrap><a
					href="javascript:radioClick('COLLITEM1',<%=j%>)"><%=gaList.getRecord(1)%></a></DIV>
				</TD>
				<TD ALIGN=LEFT NOWRAP>
				<div nowrap><a
					href="javascript:radioClick('COLLITEM1',<%=j%>)"><%=gaList.getRecord(2)%></a></DIV>
				</TD>
				<TD ALIGN=LEFT NOWRAP>
				<div nowrap><a
					href="javascript:radioClick('COLLITEM1',<%=j%>)"><%=gaList.getRecord(4)%></a></DIV>
				</TD>
			</TR>
			<%j++;

}%>
		</table>
		</div>
		</TD>

	</TR>
</TABLE>

</TR>
<BR>
<TR>
	<TABLE id="mainTable" class="tableinfo">
		<TBODY>
			<TR>
				<TD nowrap valign="top" width="100%">
				<H3>Garantias eIBS</H3>
				<table class="tbenter" width=100% align=center>
				 	  <tr>
						<td class=TDBKG width="50%">
						<%--
						<% if(!userPO.getType().equals("1")){%>
						--%>
							<div align="center">
								<a href="javascript:goAction(5)"><b>Copiar</b></a>
							</div>
						<%--
						<% } %>
						--%>
						</td>
						<td class=TDBKG width="50%">
							<div align="center"><a href="javascript:goAction(10)"><b>Consultar</b></a></div>
						</td>
				  	  </tr>
					</table>
				
				<TABLE id="headTable3">
					<TBODY>
						<TR id="trdark">
							<TH align="CENTER" nowrap></TH>
							<TH align="CENTER" nowrap>Cuenta</TH>
							<TH align="CENTER" nowrap>Tipo</TH>
							<TH align="CENTER" nowrap>Valor</TH>
							<TH align="CENTER" nowrap>Id.Propietario</TH>
							<TH align="CENTER" nowrap>Nombre</TH>
						</TR>
					</TBODY>
				</TABLE>

				<DIV id="dataDiv3" class="scbarcolor">

				<TABLE id="dataTableRoc">
		<%int k = 0;
		firstTime = true;
		String gaChk_R = "";
		gaCodeListR.initRow();
		EDP072501HelpR.initRow();
		while (gaCodeListR.getNextRow()) {
		gaChk_R = (firstTime) ? "checked" : "";
		firstTime = false;
		EDP072501HelpR.getNextRow();
		datapro.eibs.beans.EDP072501Message msgList =
			(datapro.eibs.beans.EDP072501Message) EDP072501HelpR.getRecord();%>
					<TBODY>
						<TR>
							<TD ALIGN=Left NOWRAP>
									<input type="radio" name="COLLITEMR" 
									value="<%=EDP072501HelpR.getCurrentRow()%>" <%=gaChk_R%> 
									onClick="setItemInfo1(this.value, 
									'<%= msgList.getE01ROCREF().trim()%>',
									'<%= msgList.getE01ROCTYP().trim()%>');">
							</TD>
							<TD align="CENTER" nowrap>
								<DIV nowrap="">			
									<A href="javascript:radioClick('COLLITEMR',<%=k%>)" >
									<%=gaCodeListR.getRecord(0)%></A>	
								</DIV>
							</TD>
							<TD align="LEFT" nowrap>
							<DIV nowrap="">
								<A href="javascript:radioClick('COLLITEMR',<%=k%>)">
								<%=gaCodeListR.getRecord(1)%></A></DIV>
							</TD>
							<TD align="LEFT" nowrap>
							<DIV nowrap=""><A
								href="javascript:radioClick('COLLITEMR',<%=k%>)"><%=gaCodeListR.getRecord(2)%></A></DIV>
							</TD>
							<TD align="LEFT" nowrap>
							<DIV nowrap=""><A
								href="javascript:radioClick('COLLITEMR',<%=k%>)"><%=gaCodeListR.getRecord(3)%></A></DIV>
							</TD>
						</TR>
						<%k++;
					}%>
					</TBODY>
				</TABLE>
				</DIV>
				</TD>

			</TR>
		</TBODY>
	</TABLE>


	<%if (!error.getERRNUM().equals("0")) {
	error.setERRNUM("0");
	out.println("<SCRIPT Language=\"Javascript\">");
	out.println("       showErrors()");
	out.println("</SCRIPT>");
	int row;
	String code = "";
	String flag = "";
	try {
		row = Integer.parseInt(request.getParameter("ROW"));
	} catch (Exception e) {
		row = 0;
	}
	gaList.setCurrentRow(row);
	flag = gaList.getFlag();
	code = gaList.getRecord(1);%>
	<SCRIPT language="JavaScript">
     var collLen = document.forms[0].elements("COLLCOD").length;
     var colItm = document.forms[0].elements("COLLITEM<%=flag%>");
     var itemLen = colItm.length; 
     for(var i=0; i < collLen;i++) {
       if (document.forms[0].COLLCOD[i].value == "<%=flag%>") {
         document.forms[0].COLLCOD[i].checked = true;
         break;
       }
     }
     for(var i=0; i<itemLen;i++) {
       if (colItm[i].value == "<%=code%>") {
         colItm[i].checked = true;
         break;
       }
     }
   </SCRIPT>
	<%}
%>
	<SCRIPT language="JavaScript">

     function resizeDoc() {
      var actvTb = document.forms[0].CCOD.value;
      var dataT = document.all["dataTableactTab"];
      var dataR = document.all["dataTableRoc"];
      var data = document.all["dataTable"];
//       adjustEquTables(headTable1, dataTable, dataDiv1,1,false);
       adjustEquTables(headTable2, dataT, dataDiv2,1,false);
       adjustEquTables(headTable3, dataR, dataDiv3,1,false);
       adjustEquTables(headTable1, data, dataDiv1,1,false);
      }
	 showChecked("COLLCOD");
	 showChecked("COLLITEM");
     resizeDoc();
     window.onresize=resizeDoc;
//     setItemInfo();
	 showChecked("COLLITEMR");
</SCRIPT>

</TR>
</FORM>
</BODY>
</HTML>
