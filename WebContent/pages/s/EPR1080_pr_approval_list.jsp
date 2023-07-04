<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBList"   scope="session"/>
<jsp:useBean id= "custList" class= "datapro.eibs.beans.JBList"   scope="session"/>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"   scope="session"/>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>
<jsp:useBean id= "Approv" class= "java.lang.String"  scope="session" />
<jsp:useBean id= "lista" class= "datapro.eibs.beans.JBSortList"   scope="session"/>


<%@ page import="datapro.eibs.beans.EPR012001Message"%>
<%@ page import="datapro.eibs.master.Util"%>

<TITLE>Aprobación</TITLE>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</HEAD>


<BODY onload="MM_preloadImages('<%=request.getContextPath()%>/images/s/approve_over.gif','<%=request.getContextPath()%>/images/s/reject_over.gif')">
<h3 align="center">Aprobaci&oacute;n <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="pr_approval_list.jsp, EPR1080"> 
</h3>
<hr size="4">
<FORM Method="post" name="form1" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEPR1080A">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="action" VALUE="A">
<INPUT TYPE=HIDDEN NAME="reason" VALUE="">
<INPUT TYPE=HIDDEN NAME="appCode" VALUE="">
<INPUT TYPE=HIDDEN NAME="filcod" VALUE="T">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
<INPUT TYPE=HIDDEN NAME="WRGFL" VALUE="">
<INPUT TYPE=HIDDEN NAME="OFAFL" VALUE="">
<INPUT TYPE=HIDDEN NAME="Pos" VALUE="0">
<input type=HIDDEN name="TRANSREFNUM" value="">
<INPUT TYPE=HIDDEN NAME="H02FLGWK1" VALUE="">
<INPUT TYPE=HIDDEN NAME="FIELD" VALUE=""> 
<INPUT TYPE=HIDDEN NAME="DAY"   VALUE=""> 
<INPUT TYPE=HIDDEN NAME="MONTH" VALUE=""> 
<INPUT TYPE=HIDDEN NAME="YEAR"  VALUE=""> 
<INPUT TYPE=HIDDEN NAME="TYP" VALUE="<%= request.getParameter("TYP")==null?"":request.getParameter("TYP")%>">

<%
	String ERWRNG = (request.getParameter("ERWRNG") == null) ? "" : request.getParameter("ERWRNG").trim();
%>

<INPUT TYPE=HIDDEN   NAME="ERWRNG" VALUE="<%=ERWRNG%>">

  <TABLE class="tbenter">
    <TR>
      <TD ALIGN=CENTER class=TDBKG> 
      	<a href="javascript:goAction('A')" id="linkApproval" ><b>Aprobar</b></a>
      </TD>
      <TD ALIGN=CENTER class=TDBKG> 
      	<a href="javascript:enterReason('R')" id="linkReject" ><b>Rechazar</b></a>
      </TD>
      <TD ALIGN=CENTER class=TDBKG> 
      	<a href="javascript:enterReason('J')" id="linkJ" ><b>Devolver por <br>Ordenante</b></a>
      </TD>     
      <TD ALIGN=CENTER class=TDBKG> 
      	<a href="javascript:enterReason('K')" id="linkK" ><b>Devolver por <br>Beneficiario</b></a>
      </TD>     
      <TD ALIGN=CENTER class=TDBKG> 
      	<a href="javascript:goAction('D')" ><b>Eliminar</b></a>
      </TD>
      <TD ALIGN=CENTER class=TDBKG> 
	    <a href="javascript:goMsgSwift()"><b>Mensaje<BR>Swift</b></a> 	  
      </TD>
      <TD ALIGN=CENTER class=TDBKG> 
      	<a href="javascript:goExit()" ><b>Salir</b></a>
      </TD>     
    </TR>
  </TABLE>
  
  <table  id="mainTable" class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%" > 

	    <TABLE id="headTable" >
		  	<TR id="trdark"> 
		      		<TH ALIGN=CENTER NOWRAP></TH>
		            <TH ALIGN=CENTER NOWRAP><a href="javascript:ordena('E01PRINUM')">Referencia</a>
		             	<table border="0">
				    		<tr id="trdark">
				    			<td nowrap>
							    	<input type="text" size="6" name="ACCNUM1"  >
							    </td>
							    <td>
							    	<a href="javascript:lookAcc()"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></a>
							    </td>
							</tr>
				    	</table>
		            </TH>
		            <TH ALIGN=CENTER NOWRAP><a href="javascript:ordena('E01PRITHF')">Referencia<BR>Original</a></TH>             
		            <TH ALIGN=CENTER NOWRAP><a href="javascript:ordena('E01PRICCY')">Mda</a></TH>
		            <TH ALIGN=CENTER NOWRAP><a href="javascript:ordena('E01PRIAMT')">Monto</a></TH>
		            <th align=CENTER NOWRAP><a href="javascript:ordena('E01PRIFMT')">Tipo de Mensaje</a></th>
		            <th align=CENTER NOWRAP><a href="javascript:ordena('E01PRIPTY')">Pri</a></th>
		            <th align=CENTER NOWRAP><a href="javascript:ordena('E01REMARK')">Estado</a></th>  
		            <TH ALIGN=CENTER NOWRAP><a href="javascript:ordenaFecha('E01PRICDD','E01PRICDM','E01PRICDY')">Fecha<br>Valor</a></TH>  
					<th align=CENTER NOWRAP><a href="javascript:ordena('E01PRIDPT')">Area</a></th>                        
		    </TR>
		    <TR id="trdark"> 
		      		<TH ALIGN=CENTER NOWRAP height="2"></TH>
		            <TH ALIGN=CENTER NOWRAP height="2"></TH>
		            <TH ALIGN=CENTER NOWRAP height="2"></TH>             
		            <TH ALIGN=CENTER NOWRAP height="2"></TH>
		            <TH ALIGN=CENTER NOWRAP height="2"></TH>
		            <th align=CENTER NOWRAP height="2"></th>
		            <th align=CENTER NOWRAP height="2"></th>
		            <th align=CENTER NOWRAP height="2"></th>  
		            <th align=CENTER NOWRAP height="2"></th>  
					<th align=CENTER NOWRAP height="2"></th>  
		    </TR>
	    </TABLE>
    
        <div id="dataDiv1" class="scbarcolor"> 
          <table id="dataTable">

		<%
		 	int indexRow=0;
		  	int i=0;
			String sMes = "";
			String sAno = "";
			String sDia = "";
	
			String marker = "";
			String myFlag = "";
			StringBuffer myRow = null;
			String chk = "";
			String warnImg= "";
			String txtAlt="";
			String warnFlag= "";
			String bklFlag="";
			String ofacImg = "";
			String ofacFlag = "";

		  for (indexRow = lista.getBaseIdx(); indexRow < lista.getSize() && indexRow < lista.getBaseIdx() + lista.getDisplaySize(); indexRow++) { 
			EPR012001Message msgList = (EPR012001Message)lista.getData(indexRow);

			//if (indexRow == 0) chk = "checked";	else chk = "";
			
			if (msgList.getH01FLGWK2().trim().equals("A")) {
				warnImg= "<IMG border=\"0\" src=\"../images/warning01.gif\" ALT=\""+txtAlt+"\" onClick=\"showWarnings('" + msgList.getE01PRINUM() + "','PRINUM')\">";
				warnFlag = msgList.getH01FLGWK2().trim();
			}else{
				warnImg= "";
				warnFlag = "";
			}

	        if (msgList.getH01FLGWK3().trim().equals("3")) {
				ofacImg= "<IMG border=\"0\" src=\"../images/warning_16.jpg\" ALT=\"OFAC Match List\" onClick=\"showOfac('" + msgList.getE01PRINUM() + "')\">";
				ofacFlag = msgList.getH01FLGWK3().trim();
			}else{
				ofacImg= "";
				ofacFlag = "";
			}

			if (!Approv.equals("A") && !Approv.equals("B")){
				if (!msgList.getE01MSGTXT().trim().equals("")) {
					ofacImg= "<IMG border=\"0\" src=\"../images/ico1.gif\" ALT=\"TRX con observación\" onClick=\"viewMessage('" + indexRow + "')\">";
					ofacFlag = "";
				}else{
					ofacImg= "";
					ofacFlag = "";
				}
			}
			
			//En Revision por la Sucursal
			String disabled = (msgList.getH01SCRCOD().trim().equals("99")) ? "disabled" : "";
			//Click en el primero de cada pagina
			chk = (chk.equals("") && disabled.equals("")) ? String.valueOf(i) : chk;


			if (Approv.equals("A") || Approv.equals("B")){
				warnImg= "";
			}	

			sMes = msgList.getE01PRICDM();
			sAno = msgList.getE01PRICDY();
			sDia = msgList.getE01PRICDD();

			if( sMes.length() < 2){
				sMes = "0" + sMes;
			}

			if(sDia.length() < 2){
				sDia= "0" + sDia;
			}
			if(sAno.length() < 2){
				sAno= "0" + sAno;
			}

			switch (sAno.length()) 
			{
				case 1 : sAno = "000" + sAno;break;
				case 2 : 
						if (Integer.parseInt(sAno) < 52)
							sAno = "20" + sAno;
						else
							sAno = "19" + sAno;
				break;
				case 3 : sAno = "0"   + sAno;break;
			}
					
			%>
					<TR>
					<TD NOWRAP>
						<INPUT TYPE=HIDDEN NAME="TXTMESSAGE<%=indexRow%>" VALUE="<%=msgList.getE01MSGTXT()%>">
						<INPUT TYPE=HIDDEN NAME="fecha<%=indexRow%>" VALUE="<%=sAno%><%=sMes%><%=sDia%>">
						<input type="radio" name="REFNUM" value="<%=msgList.getE01PRINUM()%>" <%= disabled %> onclick="showAddInfo('<%=msgList.getE01PRINUM()%>',<%=i%>,'<%=warnFlag%>','<%=ofacFlag%>')">
					</TD>
					<TD NOWRAP ALIGN="LEFT"><A HREF="javascript:showInqApprovalPR('<%= msgList.getE01PRINUM()%>')"><%= Util.formatCell(msgList.getE01PRINUM())%></A><%=warnImg+ofacImg%></TD>
					<TD NOWRAP ALIGN="CENTER"><A HREF="javascript:showInqApprovalPR('<%= msgList.getE01PRINUM()%>')"><%= Util.formatCell(msgList.getE01PRITHF())%></A></TD>
					<TD NOWRAP ALIGN="CENTER"><A HREF="javascript:showInqApprovalPR('<%= msgList.getE01PRINUM()%>')"><%= Util.formatCell(msgList.getE01PRICCY())%></A></TD>
					<TD NOWRAP ALIGN="RIGHT"><A HREF="javascript:showInqApprovalPR('<%= msgList.getE01PRINUM()%>')"><%= Util.fcolorCCY(msgList.getE01PRIAMT())%></A></TD>
					<TD NOWRAP ALIGN="CENTER"><A HREF="javascript:showInqApprovalPR('<%=msgList.getE01PRINUM()%>')"><%= Util.formatCell(msgList.getE01PRIFMT())%></A></TD>
					<TD NOWRAP ALIGN="CENTER"><A HREF="javascript:showInqApprovalPR('<%=msgList.getE01PRINUM()%>')"><%= Util.formatCell(msgList.getE01PRIPTY())%></A></TD>
					<TD NOWRAP ALIGN="LEFT"><A HREF="javascript:showInqApprovalPR('<%=msgList.getE01PRINUM()%>')"><%= Util.formatCell(msgList.getE01REMARK())%></A></td>
					<TD NOWRAP ALIGN="CENTER"><A HREF="javascript:showInqApprovalPR('<%=msgList.getE01PRINUM()%>')"><%= Util.formatDate(msgList.getE01PRICDD(),msgList.getE01PRICDM(),msgList.getE01PRICDY())%></A></TD>
					<TD NOWRAP ALIGN="CENTER"><A HREF="javascript:showInqApprovalPR('<%=msgList.getE01PRINUM()%>')"><%= Util.formatCell(msgList.getE01PRIDPT())%></A>
					<INPUT TYPE=HIDDEN NAME="TXTDATA<%=i%>" VALUE="<%=Util.formatDate(msgList.getE01PRICDD(),msgList.getE01PRICDM(),msgList.getE01PRICDY())%><br>
					<%=Util.formatCell(msgList.getE01PRIOBN())%>/<%=Util.formatCell(msgList.getE01PRIOBR()) %><br><%=Util.formatCell(msgList.getE01BENFIC())%><br><%=Util.formatCell(msgList.getE01PRIRID())%><br>
					<%=Util.formatCell(msgList.getE01PRICNL())%><br><%=Util.formatCell(msgList.getE01PRIUSR())%>"></TD>
					</TR>
			<% 
				i++;
			}
			%>

          </table>
        </div>
      </td>
      <td nowrap align="RIGHT" valign="top"> 
        <table id="tbAddInfoH" width="100%" >
          <tr id="trdark"> 
            <th align=CENTER nowrap >Informaci&oacute;n B&aacute;sica</th>
          </tr>
        </table>
        <table id="tbAddInfo" >
          <tr id="trclear" > 
            <td  align="RIGHT"  valign="middle" nowrap ><b>
              Fecha Valor : <br>
              Banco/Agencia : <br>
              Beneficiario : <br>
              Receptor : <br>
              Canal : <br>              
              Usuario : </b>
             </td>
            <td align="LEFT" valign="middle" nowrap class="tdaddinfo"></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
	<TABLE class="tbenter" WIDTH="100%" >
			<TR>
				<TD ALIGN=LEFT>
					<%if (lista.showPrev()) { %>
						<A  HREF="javascript:navega(0)"><IMG border="0" src="<%=request.getContextPath()%>/images/s/previous_records.gif"></A>
					<%}%>  
				</TD>
				<TD ALIGN=RIGHT>     
					<%if (lista.showNext()){ %>
						<A HREF="javascript:navega(1)"><IMG border="0" src="<%=request.getContextPath()%>/images/s/next_records.gif"></A>
					<%}%>
				</TD>
			</TR>
	</TABLE>


<SCRIPT language="JavaScript">
 
 var reason = "";

 function showInqApprovalPR(refnum) {
   var formLength = document.forms[0].elements.length;
   
   for (n=0;n<formLength;n++) {
      	var element = document.forms[0].elements[n];
      	var elementName= element.name;
      	var elementValue= element.value;
      	if (elementName == "REFNUM" && elementValue == refnum) {
      			if (element.disabled == "") { 
      				document.forms[0].elements[n].click(); 
      			} else { 
      				document.forms[0].TRANSREFNUM.value = ""; 
      			}	
        		break;
      	}
   }
   page = webapp + "/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=100&REFNUM="+refnum;
   CenterWindow(page,600,500,2);
 }

function viewMessage(idxRow) {
	var y=event.clientY + document.body.scrollTop;
	var x=event.clientX + document.body.scrollLeft;

	var text = document.forms[0]["TXTMESSAGE"+idxRow].value;
	page = webapp + "/pages/s/MISC_message_viewer.jsp?MESSAGE=" + text;
	CenterWindow(page,300,200,2);
}


 function showAddInfo(refernum,idxRow,wrg,ofa){

   tbAddInfo.rows[0].cells[1].style.color="white";
   tbAddInfo.rows[0].cells[1].innerHTML=document.forms[0]["TXTDATA"+idxRow].value;
   if (tbAddInfo.rows[0].cells[1].filters[0])
   		tbAddInfo.rows[0].cells[1].filters[0].apply();
   tbAddInfo.rows[0].cells[1].style.color="";
   if (tbAddInfo.rows[0].cells[1].filters[0])
   		tbAddInfo.rows[0].cells[1].filters[0].Play();
   for(var i=0;i<dataTable.rows.length;i++ ){
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idxRow].className="trhighlight";
   document.forms[0].REFNUM.value = refernum;
   adjustEquTables(headTable, dataTable, dataDiv1,1,false);
   document.forms[0].WRGFL.value = wrg;
   document.forms[0].OFAFL.value = ofa;
   document.forms[0].TRANSREFNUM.value = refernum;
   document.forms[0].H02FLGWK1.value = "";	
  } 
  
 function goApproval() {
	if (document.forms[0].ERWRNG.value == "S"){
		document.forms[0].H02FLGWK1.value = "A";	
	}
	document.forms[0].action.value = "A";
	document.forms[0].submit();
 }

 function goAction(op) {
 var op2 = '';

     document.forms[0].action.value = op;
     document.forms[0].reason.value = reason;
     if (document.forms[0].TRANSREFNUM.value !== "") {
		var accnum = document.forms[0].TRANSREFNUM.value;
            if (document.forms[0].OFAFL.value !== "" && op != 'Z' && op != 'D') {
		     page = webapp + "/servlet/datapro.eibs.helps.JSEWD0092?shrAcc=" + accnum + "&shrCategory=ALL" + "&FromRecord=0&shrAction=APV";
		     CenterWindow(page,600,500,2);
            } else { 
		      if (document.forms[0].WRGFL.value !== "" && op == 'A') {
			   page = webapp + "/servlet/datapro.eibs.alerts.JSESD0000?ACCNUM=" + accnum+"&APP=1";
			   CenterWindow(page,420,300,2);
                  }
               else {
                        document.forms[0].submit();
                  }
              }
     }
     else {
		  alert("Esta Cuenta esta Pendiente a Revisión por la Agencia.");	   
     }
  }  
  
 function goExit(){
    window.location.href="<%=request.getContextPath()%>/pages/background.jsp";
  }
  
function showOfac(fieldValue){
       document.forms[0].REFNUM.value=fieldValue;

	var formLength= document.forms[0].elements.length;
   	for(n=0;n<formLength;n++)
     	{
      		var elementName= document.forms[0].elements[n].name;
      		var elementValue= document.forms[0].elements[n].value;
      		if(elementName == "PRINUM" && elementValue== fieldValue) 
      			{
        		document.forms[0].elements[n].click();
        		break;
      		}
      	}
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0092?shrAcc=" + fieldValue + "&shrCategory=ALL" + "&FromRecord=0&shrAction=INQ";
	CenterWindow(page,600,500,2);    
 }
  
function enter(recpos){
 document.forms[0].SCREEN.value = '5';
 document.forms[0].Pos.value = "" + recpos;
 document.forms[0].submit();
}
 function goMsgSwift() {
    
     
     if (form1.TRANSREFNUM.value !== "") {
         
		   var dx = 450;
		   var dy = 350;
		   var y0 = (screen.height - dy) / 2;
		   var x0 = (screen.width - dx) / 2;
		   var attr = 'toolbar=no,location=no,directories=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,left=' + x0 + ',top=' + y0 + ',height=' + dy + ',width=' + dx;

		   page = webapp + "/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=8&TRANSREFNUM="+form1.TRANSREFNUM.value;
		   listin = window.open(page,'',attr);
         
     } else {
		  alert("Esta Cuenta esta Pendiente a Revisión por la Agencia.");	   
     }

 }

function lookAcc()
{
//	if (trim(document.forms[0].ACCNUM1.value) != "") {
		document.forms[0].SCREEN.value="5";
		document.forms[0].submit();
//	}
}
  
var ATRAS = 0;
var ADELANTE = 1;

function navega(direccion)
{
	document.forms[0].SCREEN.value = 500 + direccion;
	document.forms[0].submit();
}

function ordena(fieldname){
	document.forms[0].SCREEN.value = "502";
	document.forms[0].FIELD.value = fieldname;
	document.forms[0].submit();
}

function ordenaFecha(dd,mm,yy){
	document.forms[0].SCREEN.value = "503";
	document.forms[0].DAY.value = dd;
	document.forms[0].MONTH.value = mm;
	document.forms[0].YEAR.value = yy;
	document.forms[0].submit();
}

     document.forms[0].totalRow.value="<%= indexRow %>";
     function resizeDoc() {
       divResize(true);
       adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      }
	 //showChecked("REFNUM");
	 radioClick("REFNUM", <%=chk%>);
     //resizeDoc();
     tbAddInfoH.rows[0].cells[0].height = headTable.rows[0].cells[0].clientHeight;
     window.onresize=resizeDoc;
     
</SCRIPT>  
<BR>

</FORM>

<%if(error.getERWRNG().equals("Y") && error.getERRNUM().equals("1")){
	out.println("<SCRIPT Language=\"Javascript\">");
	out.println("       showWarningsApp(\"" + request.getParameter("ACCNUM") + "\")");
	out.println("</SCRIPT>");
}
else if (!error.getERRNUM().equals("0")){
	error.setERRNUM("0");
	out.println("<SCRIPT Language=\"Javascript\">");
    out.println("       showErrors()");
	out.println("</SCRIPT>");
}
String opt = ""; 
%>

</BODY>
</HTML>
