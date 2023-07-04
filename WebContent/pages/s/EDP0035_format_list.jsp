<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Format Code List
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "fmtList" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/graphical.jsp"> </SCRIPT>

<script language="javascript">

 function showHiddenDivNew() {	 
 	var hiddenDivNew = document.getElementById("hiddenDivNew");
 	var opTable = document.getElementById("opTable");
 	
	 var y= opTable.offsetTop + 10;
     var x= opTable.offsetLeft + (hiddenDivNew.clientWidth /2);
     
	 //cancelBub();
	moveElement(hiddenDivNew, y, x);
	setVisibility(hiddenDivNew, "visible");
	
	 //document.forms[0].RUTNUM.focus();
 }

function closeHiddenDivNew(){
	setVisibility(document.getElementById("hiddenDivNew"), "hidden");
}

document.onclick= closeHiddenDivNew;
 
 function goAction(op) {
   document.forms[0].opt.value = op;
   showChecked("FMTCOD");
   switch (op) {
    case 1 :{
            document.forms[0].E01IFFFMT.value="";
   			document.forms[0].E01IFFDSC.value="";
   			document.forms[0].E01IFFFMT.readOnly=false;
    		showHiddenDivNew();
    		document.forms[0].E01IFFFMT.focus();
            break;
            }
    case 2 :{
    		showHiddenDivNew();
    		document.forms[0].E01IFFFMT.readOnly=true;
    		document.forms[0].E01IFFDSC.focus();
            break;
            }
    case 3 :{
    		var okdel = confirm("Esta opcion borrará el Formato : "+ document.forms[0].E01IFFFMT.value);
    		if ( okdel ) document.forms[0].submit();
            break;
            }
    case 4 :{
            window.location.href="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSEDP0001?SCREEN=100&FMTCOD=" + document.forms[0].E01IFFFMT.value + "&FMTDSC=" + document.forms[0].E01IFFDSC.value;
            break;
            }
   }  
   
 }
 
 function goExit(){
  window.location.href="<%=request.getContextPath()%>/pages/background.jsp";

  }
 
 function setFormatInfo(idxRow){   
   for ( var i=0; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idxRow].className="trhighlight";
   document.forms[0].E01IFFFMT.value=dataTable.rows[idxRow].cells[1].innerText;
   document.forms[0].E01IFFDSC.value=dataTable.rows[idxRow].cells[2].innerText;
   document.forms[0].ROW.value=idxRow;
  } 
    
 function showAccountChart(idxRow){
   radioClick("FMTCOD",idxRow);
   goAction(4); 
 }
</script>

</HEAD>
<BODY>

 <h3 align="center">Formatos de Balance <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="format_list.jsp,EPD0035"> 
 </h3>
 <hr size="4">
 <FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSEDP0035" >

 <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
 <INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
 <INPUT TYPE=HIDDEN NAME="ROW" VALUE="">
 
<div id="hiddenDivNew" class="hiddenDiv">
 <TABLE id=tbhelp cellpadding=4 cellspacing=2 style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
	<TR>
	  <TD ALIGN=center colspan=2>
          <b>Formato</b>  
      </TD>
   </TR>
	<TR>
	  <TD ALIGN=right nowrap>
          <b>Código :</b>  
      </TD>
      <TD ALIGN=Left nowrap>
	     <input type="text" maxlength=1 size=2 name="E01IFFFMT" value="">  
	  </TD>
   </TR>
   <TR>
	  <TD ALIGN=right nowrap>
          <b>Descripción:</b>  
      </TD>
      <TD ALIGN=Left nowrap>
	     <input type="text" maxlength=35 size=36 name="E01IFFDSC" value="">  
	 </TD>
   </TR>
   <TR>
	  <TD ALIGN=center colspan=2>
	    <p> </p>
	  </TD>
   </TR>
   <TR>
	  <TD ALIGN=center colspan=2>
	    <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</p>
      </TD>
   </TR>
 </TABLE>
</div>  
  <% if (fmtList.getNoResult()) { %>
   <TABLE id=opTable class="tbenter">
    <TR>
      <TD ALIGN=CENTER width="20%" class=TDBKG>
  		 <a href="javascript:goAction(1)">Nueva</a>
      </TD>
      <TD ALIGN=CENTER width="20%" class=TDBKG>
  		 <a href="javascript:goExit()">Salir</a>
      </TD>
    </TR>
   </TABLE>
   <TABLE class="tbenter" height="60%">
   <TR>
      <TD ALIGN=CENTER VALIGN=MIDDLE>
  		 <H4 style="text-align: center">No existe ningun Formato de Balance creado. <br> Seleccione la opción NUEVA si desea crear un nuevo Formato</h4>
      </TD>
    </TR>
   </TABLE>
  <% } else {%> 
   <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
   <TABLE id=opTable class="tbenter">
    <TR>
      <TD ALIGN=CENTER width="20%" class=TDBKG>
  		 <a href="javascript:goAction(1)">Nueva</a>
      </TD>
      <TD ALIGN=CENTER width="20%" class=TDBKG>
  		 <a href="javascript:goAction(2)">Mantenimiento</a>
      </TD>
      <TD ALIGN=CENTER width="20%" class=TDBKG>
         <a href="javascript:goAction(3)">Eliminar</a>
      </TD>
      <TD ALIGN=CENTER width="20%" class=TDBKG>
         <a href="javascript:goAction(4)">Plan de Cuentas</a>
      </TD>
      <TD ALIGN=CENTER width="20%" class=TDBKG>
  		 <a href="javascript:goExit()">Salir</a>
      </TD>
    </TR>
  </TABLE>
  
 <TABLE  id="mainTable" class="tableinfo"  > 
 
 <TR > 
  <TD NOWRAP valign="top" width="100%" >
   <TABLE id="headTable" >
    <TR id="trdark">  
      <TH ALIGN=CENTER nowrap></TH>
      <TH ALIGN=CENTER nowrap>Código</TH>
      <TH ALIGN=CENTER nowrap>Description</TH>
    </TR>
    
   </TABLE>
  
   <div id="dataDiv1" class="scbarcolor" style="padding:0" NOWRAP>
    <table id="dataTable"  >
    <%
                fmtList.initRow();
                int k=1;
                while (fmtList.getNextRow()) {
                    if (fmtList.getFlag().equals("")) {
                    		out.println(fmtList.getRecord());
                    k++;
                    }        
                }                 
    %> 
   </table>
   </div>
   
  </TD>
   
  </TR>	
</TABLE>

<SCRIPT language="JavaScript">
  document.forms[0].totalRow.value="<%= k %>";
  function resizeDoc() {
       divResize(false);
       adjustEquTables(headTable, dataTable, dataDiv1,1,false);
  }
  showChecked("FMTCOD");
  resizeDoc();
  window.onresize=resizeDoc;
</SCRIPT>
<% } %>

<%  	
    int row=0;
    try {
      row = Integer.parseInt(request.getParameter("ROW"));
    }
    catch (Exception e) {
      row = -1;
    }    
 
   if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     if (row > -1) {
       String fmtCod=request.getParameter("FMTCOD");
       String fmtDsc=request.getParameter("FMTDSC");
%>
  <SCRIPT language="JavaScript">
     radioClick("FMTCOD",<%= row %>); 
     document.forms[0].E01IFFFMT.value="<%= fmtCod %>";
     document.forms[0].E01IFFDSC.value="<%= fmtDsc %>";
     showHiddenDivNew();
  </SCRIPT>
<% 
  }    
 }
%>

  <SCRIPT language="JavaScript">
 	document.getElementById("hiddenDivNew").onclick=cancelBub;
  </SCRIPT>
</FORM>

</BODY>
</HTML>
