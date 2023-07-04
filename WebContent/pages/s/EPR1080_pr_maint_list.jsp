<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Transacciones Financieras</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"   scope="session"/>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>
<jsp:useBean id= "lista" class= "datapro.eibs.beans.JBSortList"   scope="session"/>

<%@ page import="datapro.eibs.beans.EPR012001Message"%>
<%@ page import="datapro.eibs.master.Util"%>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/graphical.jsp"> </SCRIPT>

</HEAD>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<BODY onload="MM_preloadImages('<%=request.getContextPath()%>/images/s/maintenance_over.gif')">
<h3 align="center">Transacciones Financieras<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="pr_maint_list.jsp, EPR1080"> 
</h3>
<hr size="4">

<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPR1080">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
<INPUT TYPE=HIDDEN NAME="REFNUM" VALUE="">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
<INPUT TYPE=HIDDEN NAME="FIELD" VALUE=""> 
<INPUT TYPE=HIDDEN NAME="TYP" VALUE="<%= request.getParameter("TYP")%>"> 

	  <TABLE class="tbenter">
	    <TR>
	      <TD ALIGN=CENTER width="50%">
	  			<a href="javascript:goAction('')" id="" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1','','<%=request.getContextPath()%>/images/s/maintenance_over.gif',1)"><img name="Image1" alt="Maintenance" border="0" src="<%=request.getContextPath()%>/images/s/maintenance.gif" ></a>
	      </TD>
	      <TD ALIGN=CENTER width="50%">
	  			<a href="javascript:goExit()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image5','','<%=request.getContextPath()%>/images/s/exit_over.gif',1)"><img name="Image5" border="0" src="<%=request.getContextPath()%>/images/s/EXIT.gif" ></a>
	      </TD>
	    </TR>
	  </TABLE>
	  
	 <TABLE  id="mainTable" class="tableinfo">
	 <TR > 
	    <TD NOWRAP valign="top" width="100%" >
	
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
				<th align=CENTER NOWRAP><a href="javascript:ordena('E01PRIDPT')">Area <BR>Responsable</a></th>                        
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
	    </TR>
	    </TABLE>
	    
	   <div id="dataDiv1" class="scbarcolor">              
	    <table id="dataTable">
	
		<%int indexRow=0;
		  int i=0;
		  String msgImg = "";
		  for (indexRow = lista.getBaseIdx(); indexRow < lista.getSize() && indexRow < lista.getBaseIdx() + lista.getDisplaySize(); indexRow++) { 
			EPR012001Message msgList = (EPR012001Message)lista.getData(indexRow);
			
			String chk = "";
			if (indexRow == 0) chk = "checked";	else chk = "";
			
			if (!msgList.getE01MSGTXT().trim().equals("")) {
				msgImg= "<IMG border=\"0\" src=\"../images/ico1.gif\" ALT=\"TRX con observación\" onClick=\"viewMessage('"+ indexRow + "')\">";
			}else{
				msgImg= "";
			}%>
				<TR>
				<TD NOWRAP>
					<INPUT TYPE="HIDDEN" NAME="TXTMESSAGE<%=indexRow%>" VALUE="<%= msgList.getE01MSGTXT()%>">
					<input type="radio" name="PRINUM" value="<%= msgList.getE01PRINUM()%>" <%=chk%> onclick="showAddInfo('<%= msgList.getE01PRINUM()%>',<%=i%>)">
				</TD>
				<TD NOWRAP ALIGN="CENTER"><A HREF="javascript:goAction('<%=msgList.getE01PRINUM()%>')"><%= Util.formatCell(msgList.getE01PRINUM()) %></A><%= msgImg %></TD>
				<TD NOWRAP ALIGN="CENTER"><A HREF="javascript:goAction('<%=msgList.getE01PRINUM()%>')"><%= Util.formatCell(msgList.getE01PRITHF()) %></A></TD>
				<TD NOWRAP ALIGN="CENTER"><A HREF="javascript:goAction('<%=msgList.getE01PRINUM()%>')"><%= Util.formatCell(msgList.getE01PRICCY()) %></A></TD>
				<TD NOWRAP ALIGN="RIGHT"><A HREF="javascript:goAction('<%=msgList.getE01PRINUM()%>')"> <%=Util.fcolorCCY(msgList.getE01PRIAMT())   %></A></TD>
				<TD NOWRAP ALIGN="CENTER"><A HREF="javascript:goAction('<%=msgList.getE01PRINUM()%>')"><%= Util.formatCell(msgList.getE01PRIFMT()) %></A></TD>
				<TD NOWRAP ALIGN="CENTER"><A HREF="javascript:goAction('<%=msgList.getE01PRINUM()%>')"><%= Util.formatCell(msgList.getE01PRIPTY()) %></A></TD>
				<TD NOWRAP ALIGN="LEFT"><A HREF="javascript:goAction('<%= msgList.getE01PRINUM()%>')"><%= Util.formatCell(msgList.getE01REMARK())  %></A></TD>
				<TD NOWRAP ALIGN="CENTER"><A HREF="javascript:goAction('<%=msgList.getE01PRINUM()%>')"><%= Util.formatCell(msgList.getE01PRIDPT()) %></A>
				<INPUT TYPE=HIDDEN NAME="TXTDATA<%=i%>" VALUE=" <%=Util.formatDate(msgList.getE01PRIVDM(),msgList.getE01PRIVDD(),msgList.getE01PRIVDY())%>
				<br><%=Util.formatCell(msgList.getE01PRIOBN())%>/<%=Util.formatCell(msgList.getE01PRIOBR())%><br><%=Util.formatCell(msgList.getE01BENFIC()) %>
				<br><%=Util.formatCell(msgList.getE01PRIRID())%><br><%=Util.formatCell(msgList.getE01PRICNL())%><br><%=Util.formatCell(msgList.getE01PRIUSR())%>"></TD>
				</TR>
	
	
		<% i++;
		}%>
	   </table>
	
	   </div>
	   </TD>
	   <TD nowrap ALIGN="RIGHT" valign="top">
	      <Table id="tbAddInfoH" width="100%" >
	        <tr id="trdark">
	            <TH ALIGN=CENTER nowrap >Informaci&oacute;n B&aacute;sica</TH>
	        </tr>
	      </Table>
	
	     <Table id="tbAddInfo" >
	          <tr id="trclear" > 
	            <td  align="RIGHT"  valign="middle" nowrap ><b>
	              Fecha de Transferencia : <br>
	              Banco/Agencia : <br>
	              Beneficiario : <br>
	              Receptor : <br>
	              Canal : <br>
	              Usuario : </b>
	             </td>
	            <td align="LEFT" valign="middle" nowrap class="tdaddinfo"></td>
	          </tr>
	     </Table>
	  </TD>
	  </TR>	
	</TABLE>
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

<script language="javascript">

 function sendClick(refnum) {
   var formLength= document.forms[0].elements.length;
   for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	var elementValue= document.forms[0].elements[n].value;
      	if(elementName == "PRINUM" && elementValue== refnum) 
      	{
        		document.forms[0].elements[n].click();
        		break;
      	}
      }
 }

 function goAction(refnum) {
     if ( refnum !== "") {
		  sendClick(refnum);
     }
     page = webapp + "/servlet/datapro.eibs.products.JSEPR0000?SCREEN=3&REFNUM="+document.forms[0].REFNUM.value;
	 CenterWindow(page,600,500,2);
  }

 function goExit(){
    window.location.href="<%=request.getContextPath()%>/pages/background.jsp";
  }
 
 function showAddInfo(refernum,idxRow){

   tbAddInfo.rows[0].cells[1].style.color="white";
   tbAddInfo.rows[0].cells[1].innerHTML=document.forms[0]["TXTDATA"+idxRow].value;
   
   //tbAddInfo.rows[0].cells[1].filters[0].apply();
   tbAddInfo.rows[0].cells[1].style.color="";
   //tbAddInfo.rows[0].cells[1].filters[0].Play();
   
   for(var i=0;i<dataTable.rows.length;i++ ){
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idxRow].className="trhighlight";
   document.forms[0].REFNUM.value = refernum;
   
   adjustEquTables(
   	document.getElementById("headTable"),
   	document.getElementById("dataTable"),
   	document.getElementById("dataDiv1"), 1,false);
   
  }   
  
function viewMessage(idxRow) {
	var text = document.forms[0]["TXTMESSAGE"+idxRow].value;

	page = webapp + "/pages/s/MISC_message_viewer.jsp?MESSAGE=" + text;
	CenterWindow(page,300,200,2);
}

function lookAcc()
{
//	if (trim(document.forms[0].ACCNUM1.value) != "") {
		document.forms[0].SCREEN.value="1";
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
	document.forms[0].SCREEN.value = "506";
	document.forms[0].DAY.value = dd;
	document.forms[0].MONTH.value = mm;
	document.forms[0].YEAR.value = yy;
	document.forms[0].submit();
}

     document.forms[0].totalRow.value="<%= indexRow %>";
     
     function resizeDoc() {
       divResize(true);
	   adjustEquTables(
	   	document.getElementById("headTable"),
	   	document.getElementById("dataTable"),
	   	document.getElementById("dataDiv1"), 1,false);
      }
	 
	 //showChecked("PRINUM");
	 radioClick("PRINUM", 0);
     //resizeDoc();
     tbAddInfoH.rows[0].cells[0].height = headTable.rows[0].cells[0].clientHeight;
     window.onresize=resizeDoc;
     
</SCRIPT>

</FORM>

</BODY>
</HTML>
