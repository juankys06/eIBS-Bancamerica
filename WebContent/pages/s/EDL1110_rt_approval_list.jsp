<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Approval List</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ftList" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "ldList" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="javascript">
  var reason = '';
 
  function goAction(op) {

    document.forms[0].action.value = op;
    var formLength= document.forms[0].elements.length;
    var ok = false;
    for(var n=0;n<formLength;n++)
    {
    	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "TBL") 
      	{
			ok = true;
        	break;
      	}
    }
	if ( ok ) {
    	document.forms[0].submit();
	} else {
		alert("Seleccione una tasa para efectuar esta operacion");	   
    }

 }
 
 function getParams(param){
	document.forms[0].TBLNUM.value = param;
 }

 function goExit(){
  window.location.href="<%=request.getContextPath()%>/pages/background.jsp";
  }
  
 function showAddInfo(idxRow){
   tbAddInfo.rows[0].cells[1].style.color="white";
   tbAddInfo.rows[0].cells[1].innerHTML=document.forms[0]["TXTDATA"+idxRow].value;
   if (tbAddInfo.rows[0].cells[1].filters[0])
   tbAddInfo.rows[0].cells[1].filters[0].apply();
   tbAddInfo.rows[0].cells[1].style.color="";
   if (tbAddInfo.rows[0].cells[1].filters[0])
   tbAddInfo.rows[0].cells[1].filters[0].Play();
   for ( var i=0; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idxRow].className="trhighlight";
   adjustEquTables(headTable, dataTable, dataDiv1,1,false);
  }    
</script>
</HEAD>

<% 
int k=1;
if ( !error.getERRNUM().equals("0")  ) {
	error.setERRNUM("0");
	out.println("<SCRIPT Language=\"Javascript\">");
	out.println("       showErrors()");
	out.println("</SCRIPT>");
}
%>

<BODY onload="MM_preloadImages('<%=request.getContextPath()%>/images/s/approve_over.gif','<%=request.getContextPath()%>/images/s/reject_over.gif')">
<h3 align="center">Aprobaci&oacute;n de Tasas Flotantes y Lideres<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_approval_list.jsp, EDL1110"> 
</h3>
<hr size="4">
  
  
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDL1110">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="action" VALUE="A">
<INPUT TYPE=HIDDEN NAME="reason" VALUE="">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
<INPUT TYPE=HIDDEN NAME="TBLNUM" VALUE="<%= userPO.getHeader10()%>">

<%if ( ftList.getNoResult() && ldList.getNoResult() ) {%>
<TABLE class="tbenter" width=100% height=100%>
	<TR>
      <TD> 
		<div align="center"> <font size="3"><b> No hay datos que correspondan con su criterio de b&uacute;squeda</b></font> </div>
      </TD>
    </TR>
</TABLE>
<%}	else {%>

<TABLE class="tbenter">
    <TR>
      <TD ALIGN=CENTER width="25%">
  			<a href="javascript:goAction('A')" id="linkApproval" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1','','<%=request.getContextPath()%>/images/s/approve_over.gif',1)"><img name="Image1" alt="Aprobar" border="0" src="<%=request.getContextPath()%>/images/s/approve.gif" ></a>
      </TD>
      <TD ALIGN=CENTER width="25%">
  			<a href="javascript:enterReason('R')" id="linkReject" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image2','','<%=request.getContextPath()%>/images/s/reject_over.gif',1)"><img name="Image2" alt="Rechazar" border="0" src="<%=request.getContextPath()%>/images/s/reject.gif" ></a>
      </TD>
      <TD ALIGN=CENTER width="25%">
  			<a href="javascript:goAction('D')" id="linkDelete" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image3','','<%=request.getContextPath()%>/images/s/delete_over.gif',1)"><img name="Image3" alt="Eliminar" border="0" src="<%=request.getContextPath()%>/images/s/delete.gif" ></a>
      </TD>
      <TD ALIGN=CENTER width="25%">
  			<a href="javascript:goExit()"  onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image5','','<%=request.getContextPath()%>/images/s/exit_over.gif',1)"><img name="Image5" border="0" src="<%=request.getContextPath()%>/images/s/EXIT.gif" ></a>
      </TD>
    </TR>
</TABLE>

<%if (!ftList.getNoResult()) {%>  
<h4>Tasas Flotantes</h4>
<TABLE  id="mainTable" class="tableinfo">
 <TR> 
   <TD NOWRAP valign="top" width="100%"> 
      <TABLE id="headTable" >
         <TR id="trdark">  
            <th align=CENTER   nowrap>&nbsp;</th>
            <th align=CENTER  nowrap> 
              <div align="center">Tabla</div>
            </th>
            <th align=CENTER   nowrap> 
              <div align="center">Descripci&oacute;n</div>
            </th>
            <th align=CENTER   nowrap>Tipo</th>
            
            <th align=CENTER    nowrap> 
              <div align="center">Tasa<br>Primaria</div>
            </th>
            <th align=CENTER   nowrap> 
              <div align="center">Tasa<br>Secundaria</div>
            </th> 
            <th align=CENTER   nowrap> 
               <div align="center">Fecha</div>
            </th>
          </TR>
 			<%
            ftList.initRow();
            while (ftList.getNextRow()) {
                if (ftList.getFlag().equals("")) {
                	out.println(ftList.getRecord());
                	k++;
                }        
            }
			%> 
      </TABLE>
    </TD>
  </TR>	
</TABLE>
<%}%>
<%if (!ldList.getNoResult()) {%>
<h4>Tasas Lideres</h4>
<TABLE  id="mainTable" class="tableinfo">
 <TR> 
   <TD NOWRAP valign="top" width="100%"> 
    <TABLE id="headTable" >
    <TR id="trdark">  
            <th align=CENTER   nowrap>&nbsp;
            </th>
            <th align=CENTER  nowrap> 
              <div align="center">Tabla</div>
            </th>
            <th align=CENTER   nowrap> 
              <div align="center">Descripci&oacute;n</div>
            </th>
            <th align=CENTER nowrap>Rango<br> Inicial
            </th> 
            <th align=CENTER nowrap>Rango<br> Final
            </th>                         
            <th align=CENTER nowrap>Tipo<br> Selecci&oacute;n
            </th>            
            <th align=CENTER nowrap> 
              <div align="center">Tasa<br> Primaria</div>
            </th>
            <th align=CENTER nowrap> 
              <div align="center">Tasa<br> Secundaria</div>
            </th>
            <th align=CENTER nowrap> 
              <div align="center">Fecha</div>
            </th>
    </TR>
	<%
    ldList.initRow();
    while (ldList.getNextRow()) {
        if (ldList.getFlag().equals("")) {
        	out.println(ldList.getRecord());
        	k++;
        }        
    }
	%> 
   </TABLE>
   </TD>
  </TR>	
</TABLE>
<%}%>
<%}%>

<%if ( !ftList.getNoResult() || !ldList.getNoResult() ) {%>
<SCRIPT language="JavaScript">
 	document.forms[0].totalRow.value="<%= k %>";
    function resizeDoc() {
    	divResize(true);
   		adjustEquTables(headTable, dataTable, dataDiv1,1,false);
    }
    showChecked("TBLNUM");
    //resizeDoc();
    //tbAddInfoH.rows[0].cells[0].height = headTable.rows[0].cells[0].clientHeight;
    //window.onresize=resizeDoc;
</SCRIPT>
<%}%>
</FORM>
</BODY>
</HTML>
