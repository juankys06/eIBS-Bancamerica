<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Customer List
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">

<%@ page import ="datapro.eibs.master.Util"%>

<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBObjList"   scope="session"/>
<jsp:useBean id= "custList" class= "datapro.eibs.beans.JBObjList"   scope="session"/>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"   scope="session"/>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>
<jsp:useBean id= "ROWCUST" class= "java.lang.String"  scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="javascript">
 var reason = "";

 function goApproval() {
	document.forms[0].action.value = "A";
	document.forms[0].submit();
 }
 
 function checkSelection(op) {
  var actvTb = document.forms[0].activeTable.value;
  var dataT = document.all["dataTable"+actvTb];
  var nChk = 0; 
  var acc = "";  
  var nWarn = 0;
     if (op == 'D' || op == 'R') {               
		   for ( var i=0; i < dataT.rows.length; i++ ) {
		       if (document.forms[0]["ACCNUM" + actvTb + i].checked) {
		         nChk++;
		         if (nChk == 2) break;
		         acc = document.forms[0]["ACCNUM" + actvTb + i].value;
		       }		       
		   }
		   if (nChk == 0) alert("Seleccione una cuenta antes de realizar esta operacion ");
		   else if ( nChk == 2) alert("Seleccione un sola cuenta para realizar esta operacion ");
		   else {
		   		document.forms[0].ACCNUM.value = acc;
		   		if (op == 'D') goAction(op);
		   		else enterReason('R');
		   }
     } else {
           var clName = "";
     	   for ( var i=0; i < dataT.rows.length; i++ ) {
     	       if (document.forms[0]["ACCNUM" + actvTb + i].checked) {
		         nChk++;
		         if (document.forms[0]["ACCNUM" + actvTb + i].className != "") {
		         	nWarn++;
		         	if (nWarn > 0 && nChk > 1) break;
		         	clName = document.forms[0]["ACCNUM" + actvTb + i].className;
		         	acc = document.forms[0]["ACCNUM" + actvTb + i].value;	
		         }	         
		       }		       
		   }
		   if (nChk == 0) alert("Seleccione una cuenta antes de realizar esta operacion ");
		   else if (nWarn > 0 && nChk > 1) alert("Las cuentas con OFAC o Advertencias debe ser aprobadas una por una");
		   else {
		      var wFlg = "";
		      var oFlg = "";
		      if (clName.length > 0) {
		        if (clName.length == 1) {
		            if (clName == "A") wFlg = clName; else oFlg= clName;
		        } else {
		        	wFlg = "A";
		        	oFlg = "3";
		        }
		        document.forms[0].ACCNUM.value = acc;		      	
		      }
		      document.forms[0].OFAFL.value = oFlg;
		      document.forms[0].WRGFL.value = wFlg;
		      goAction(op);	
		   }	  
     }
 } 
 
 function goAction(op) {
    var op2 = '';
    if (op == 'Z') {op2 = 'A';} else {op2 = op;}
    document.forms[0].action.value = op2;

    document.forms[0].reason.value = reason;
     
	var accnum = document.forms[0].ACCNUM.value;
    if (document.forms[0].OFAFL.value !== "" && op != 'Z' && op != 'D') {
	    page = webapp + "/servlet/datapro.eibs.helps.JSEWD0092?shrAcc=" + accnum + "&shrCategory=ALL" + "&FromRecord=0&shrAction=APV";
	    CenterWindow(page,600,500,2);
    } else { 
	    if (document.forms[0].WRGFL.value !== "" && op != 'D') {
			page = webapp + "/servlet/datapro.eibs.alerts.JSESD0000?ACCNUM=" + accnum+"&APP=1";
			CenterWindow(page,420,300,2);
   		}
        else {
              document.forms[0].submit();
        }
    }
    
 }  
  
 function goExit(){
    window.location.href="<%=request.getContextPath()%>/pages/background.jsp";
 }
 
 function showAccInfo(idxRow){
   var actvTb = document.forms[0].activeTable.value;
   var dataT = document.all["dataTable"+idxRow];
      
   dataTable.rows[actvTb].className="trnormal";   
   if (actvTb != idxRow) {
   		document.forms[0].SELALL.checked = false;
   		setSelection(false);
   }
   dataTable.rows[idxRow].className="trhighlight";
   
   document.all["dataTable"+actvTb].style.display="none";
   document.all["dataTable"+idxRow].style.display="";
   document.forms[0].activeTable.value="" + idxRow;
   if (dataT.rows.length >=5){
     dataDiv2.style.pixelHeight = 100;
   }
   adjustEquTables(headTable2, dataT, dataDiv2,1,false);
   
   
  }   
 
 function setSelection(chk){
   var actvTb = document.forms[0].activeTable.value;
   var dataT = document.all["dataTable"+actvTb];
   for ( var i=0; i < dataT.rows.length; i++ ) {
       document.forms[0]["ACCNUM" + actvTb + i].checked = chk;
   }
 } 

 function showNewInqApproval(app, pos, type) {	
	var appcode = ( trim(app) == "" ) ? "06" : app ;
	var actvTb = document.forms[0].activeTable.value;
	var account = document.forms[0]["ACCNUM" + actvTb + pos].value;
	document.forms[0].SELALL.checked = false; 
	setSelection(false);
	document.forms[0]["ACCNUM" + actvTb + pos].checked = true; 
	page = webapp + "/servlet/datapro.eibs.approval.JSESD0140?SCREEN=3&ACCNUM=" + account + "&appCode=" + appcode + "&typeCode=" + type;
	CenterWindow(page,600,500,2);
 }

 function showOfac(fieldValue){   
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0092?shrAcc=" + fieldValue + "&shrCategory=ALL" + "&FromRecord=0&shrAction=INQ";
	CenterWindow(page,600,500,2);    
 }

</script>
</HEAD>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<BODY>
<h3 align="center">Aprobaci&oacute;n General<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="approval_list.jsp,ESD0140">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSESD0140">

<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="ACCNUM" VALUE="">
<INPUT TYPE=HIDDEN NAME="action" VALUE="A">
<INPUT TYPE=HIDDEN NAME="reason" VALUE="">
<INPUT TYPE=HIDDEN NAME="activeTable" VALUE="0">
<INPUT TYPE=HIDDEN NAME="appCode" VALUE="">
<INPUT TYPE=HIDDEN NAME="filcod" VALUE="">
<INPUT TYPE=HIDDEN NAME="WRGFL" VALUE="">
<INPUT TYPE=HIDDEN NAME="BKLFL" VALUE="">
<INPUT TYPE=HIDDEN NAME="OFAFL" VALUE="">


 <TABLE  id="mainTable1" class="tableinfo">
 <TR > 
    <TD NOWRAP width="100%" >
    <TABLE id="headTable1" >
     <TR id="trdark"> 
      <TH ALIGN=CENTER NOWRAP></TH>
      <TH ALIGN=CENTER NOWRAP>Numero de Cliente</TH>
      <TH ALIGN=CENTER NOWRAP>Nombre</TH>
     </TR>
    </TABLE>
  
   <div id="dataDiv1" class="scbarcolor">
    <table id="dataTable">
    <%
                custList.initRow();
                int pos = Integer.parseInt(ROWCUST);
                String chk = "";                
                while (custList.getNextRow()) { 
                  datapro.eibs.beans.ESD014001Message custbean = (datapro.eibs.beans.ESD014001Message) custList.getRecord();
                  chk = (pos == custList.getCurrentRow())? "checked" : "";
                  %>
                  
                  <TR>
					<TD NOWRAP><input type="radio" name="CUSNUM" value="<%= custbean.getE01CUSNUM()%>" <%=chk %> onclick="showAccInfo(<%= custList.getCurrentRow() %>)"></TD>
					<TD NOWRAP ALIGN="CENTER"><A HREF="javascript:radioClick('CUSNUM',<%= custList.getCurrentRow() %>)"><%= Util.formatCell(custbean.getE01CUSNUM())%></A></TD>
					<TD NOWRAP ALIGN="CENTER"><A HREF="javascript:radioClick('CUSNUM',<%= custList.getCurrentRow() %>)"><%= Util.formatCell(custbean.getE01CUSNA1())%></A></TD>
				  </TR>
    <%                 
                }
    %> 
   </table>
   </div>
   </TD>
 </tr>
</table>

  <TABLE class="tbenter">
    <TR> 
      <TD class="TDBKG" ALIGN=CENTER width="25%"><a href="javascript:checkSelection('A')" id="linkApproval">Aprobar<br>Seleccion</a></TD>
      <TD class="TDBKG" ALIGN=CENTER width="25%"><a href="javascript:checkSelection('R')" id="linkReject">Rechazar</a></TD>
      <TD class="TDBKG" ALIGN=CENTER width="25%"><a href="javascript:checkSelection('D')">Eliminar</a></TD>
      <TD class="TDBKG" ALIGN=CENTER width="25%"><a href="javascript:goExit()">Salir</a></TD>
    </TR>
    <TR> 
      <TD colspan="4" align="center"><b>Cuentas o transferencias asociadas al cliente selecionado :</b></TD>
    </TR>
  </TABLE>
 					
 <TABLE  id="mainTable" class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" width="100%" >
      
  <TABLE id="headTable2" >
  	<TR id="trdark"> 
      <TH ALIGN=CENTER NOWRAP>
      	<input type="checkbox" name="SELALL" value="" onclick="setSelection(this.checked)">
	  </TH>
      <TH ALIGN=CENTER NOWRAP>Cuenta/<br>No.Referencia</TH>
	  <TH ALIGN=CENTER NOWRAP>MDA</TH>
	  <TH ALIGN=CENTER NOWRAP>Monto</TH>
      <TH ALIGN=CENTER NOWRAP>Descripcion</TH>
      <TH ALIGN=CENTER NOWRAP>Codigo<br>Producto</TH>
    </TR>
   </TABLE>
  
   <div id="dataDiv2" class="scbarcolor">
    <%
                appList.initRow();
                int j=0;
                while (appList.getNextRow()) {
                   datapro.eibs.beans.JBObjList accList = (datapro.eibs.beans.JBObjList) appList.getRecord();
    			   j = appList.getCurrentRow();
    %>                
    	<table id="dataTable<%=j%>" style="display:none;">
    <%
	                accList.initRow();
	                int k=0;
	                String warnImg= "";
	                String warnFlag = "";
	                String ofacImg= "";
					String ofacFlag = "";
					
	                while (accList.getNextRow()) {
	                    datapro.eibs.beans.ESD014001Message accbean = (datapro.eibs.beans.ESD014001Message) accList.getRecord();
		                k = accList.getCurrentRow();
		                
		            	if (accbean.getH01FLGWK2().trim().equals("A")) {
							warnImg= "<IMG border=\"0\" src=\"" + request.getContextPath() +"/images/warning01.gif\" ALT=\"...\" onClick=\"showWarnings('" + accbean.getE01ACCNUM() + "','ACCNUM"+ j + "" + k + "')\">";
							warnFlag = accbean.getH01FLGWK2().trim();
						}else{
							warnImg= "";
							warnFlag = "";
						}
						//add Ofac status H01FLGWK3 = '3'
				        if (accbean.getH01FLGWK3().trim().equals("3")) {
							ofacImg = "<IMG border=\"0\" src=\"" + request.getContextPath() + "/images/warning_16.jpg\" ALT=\"OFAC Match List\" onClick=\"showOfac('" + accbean.getE01ACCNUM() + "')\">";
							ofacFlag = accbean.getH01FLGWK3().trim();
						}else{
							ofacImg= "";
							ofacFlag = "";
						}
						
						String linkApp="";
						String accTemp="";
						
						if ( accbean.getE01LNENUM().equals("0")) {
						  accTemp = accbean.getE01ACCNUM();
						} else {
						  accTemp = accbean.getE01CUSNUM()+"_"+accbean.getE01LNENUM();
						  } 
						 linkApp="<A HREF=\"javascript:showNewInqApproval('" + accbean.getE01APLCDE() + "', '" + k + "', '" + accbean.getH01FLGWK1() + "')\">";						  					  
						
						String classN = ofacFlag + warnFlag ;
						
						%>
					
					<TR>
						<TD NOWRAP>
							<input type="checkbox" name="ACCNUM<%=j%><%=k%>" <%if (classN != "" ) out.print("class=\""+classN+"\"");%> value="<%=accTemp%>">
						</TD>
					<% if ( accbean.getE01LNENUM().equals("0")){ %>
						<TD NOWRAP ALIGN="CENTER"><%= linkApp %><%= Util.formatCell(accbean.getE01ACCNUM())%><%="</A>"%><%= warnImg%><%= ofacImg%></TD>
					<% } else { %>
						<TD NOWRAP ALIGN="CENTER"><%= linkApp %><%= Util.formatCell(accTemp)%><%="</A>"%><%= warnImg%><%=ofacImg%></TD>		
					<% } %>
						<TD NOWRAP ALIGN="CENTER"><%=linkApp%><%=Util.formatCell(accbean.getE01CURRCY())%><%="</A>"%></TD>
						<TD NOWRAP ALIGN="RIGHT"><%=linkApp%><%=Util.fcolorCCY(accbean.getE01BALAMN())%><%="</A>"%></TD>
						<TD NOWRAP ALIGN="CENTER"><%=linkApp%><%=Util.formatCell(accbean.getE01REMARK())%><%="</A>"%></TD>
						<TD NOWRAP ALIGN="CENTER"><%=linkApp%><%=Util.formatCell(accbean.getE01PRODUC())%><%="</A>"%></TD>
					</TR>
	                 
   <%                         
                	}
    %> 
   		</table>
   <%  
                     
            }
    %> 
   </div>
   </TD>   
  </TR>	
</TABLE>

<SCRIPT language="JavaScript">
     if (dataTable.rows.length >=5){
	   dataDiv1.style.height="120"; 
   	   dataDiv1.style.overflowY="scroll";
   	 }
     function resizeDoc() {
      var actvTb = document.forms[0].activeTable.value;
      var dataT = document.all["dataTable"+actvTb];
       adjustEquTables(headTable1, dataTable, dataDiv1,1,false);
       adjustEquTables(headTable2, dataT, dataDiv2,1,false);
      }
	 showChecked("CUSNUM");
     resizeDoc();
     window.onresize=resizeDoc;
     
</SCRIPT>

</FORM>

</BODY>
</HTML>
