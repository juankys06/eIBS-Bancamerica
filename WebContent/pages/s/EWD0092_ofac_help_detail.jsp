<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="javascript">
<!--
//<!-- Hide from old browsers
function enter(code) {
var form= top.opener.document.forms[0];
form[top.opener.fieldName].value = code;
top.close();
 }
//-->

function MM_swapImgRestore() {// v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() {// v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) {// v3.0
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document); return x;
}

function MM_swapImage() {// v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
var reason = "";

function goAction(op) {
      top.opener.goAction(op);
      top.close();
 }
 
 function goExit(){
  top.close();
  }

</script>
<TITLE>OFAC Match List</TITLE>
</head>
<jsp:useBean id= "EWD0092Help" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "EWD0092HelpDetail" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<BODY onload="MM_preloadImages('<%=request.getContextPath()%>/images/s/approve_over.gif','<%=request.getContextPath()%>/images/s/reject_over.gif')">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script>
function showOfacDetail(shrAcc, shrCat) {
parent.results.window.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp?URL='<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSEWD0092?shrAcc=" + shrAcc + "@shrCategory=" + shrCat + "@FromRecord=0" + "@shrAction=INQ"+"'";
}
</script>
<FORM>
<%
int k=0;
if ( EWD0092Help.getNoResult() ) {
%>
     <TABLE class="tbenter" width="100%" height="100%" >
     <TR>
     <TD> 
     <div align="center"><b> No hay resultados que correspondan 
        a su criterio de búsqueda</b> 
     </div>
     </TD></TR>
     </TABLE>
<%
} else {
    if (request.getParameter("shrCategory").equals("ALL")){  %>
  <h3 align="center">Listado OFAC </h3>
  <% } %>

<TABLE  id="mainTable" class="tableinfo" align=CENTER>
 <TR > 
    <TD NOWRAP valign="top" width="100%" >

<TABLE id="headTable">
    <TR id="trdark"> 
       <%
          if (request.getParameter("shrCategory").equals("ALL")) { 
       %>
          <TH ALIGN=CENTER  nowrap width="40%">Categoria</TH>
       <% 
          } else { 
       %>
	  <TH ALIGN=CENTER  nowrap width="20%">Nombre Ofac </TH>
          <TH ALIGN=CENTER  nowrap width="20%">Fecha</TH>
          <TH ALIGN=CENTER  nowrap width="20%">Nombre de Busqueda</TH>
       <%
       }
       %>
     </TR>
</TABLE>
<div id="dataDiv1" class="scbarcolor" >
    <table id="dataTable" >

    <%
		String shrAcc = request.getParameter("shrAcc");
		String shrCategory = request.getParameter("shrCategory");
		String shrAction = request.getParameter("shrAction");
		if (shrCategory.equals("ALL")) {
                	EWD0092Help.initRow();
                	while (EWD0092Help.getNextRow()) {
                    	if (EWD0092Help.getFlag().equals("")) {
                		   out.println(EWD0092Help.getRecord());
                  	    }
                	}
		} else {
			EWD0092HelpDetail.initRow();
            while (EWD0092HelpDetail.getNextRow()) {
               	if (EWD0092HelpDetail.getFlag().equals("")) {
                	out.println(EWD0092HelpDetail.getRecord());
                     k++;
                }
            }
		}
     %> 
  </TABLE>
  </div>
  </TD>
  </TABLE>

  <TABLE  class="tbenter" WIDTH="98%" ALIGN=CENTER>
    <TR>
      <TD WIDTH="50%" ALIGN=LEFT height="25"> <%
	if (!shrCategory.equals("ALL")) {
        if ( EWD0092HelpDetail.getShowPrev() ) {
      		int pos =EWD0092HelpDetail.getFirstRec() - 21;
      		out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0092?shrAcc=" + shrAcc + "&shrCategory="+shrCategory+"&FromRecord="+pos+"&shrAction="+shrAction+"\" > <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");        
        }
    %> 
      </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> <%       
        if ( EWD0092HelpDetail.getShowNext() ) {
      		int pos = EWD0092HelpDetail.getLastRec();
		    out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0092?shrAcc=" + shrAcc + "&shrCategory="+shrCategory+"&FromRecord="+pos+"&shrAction="+shrAction+"\" ><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");
        }
	} 
%> 
  </TD>
  </TR>
  </TABLE>
  <TABLE class="tbenter" width="97" height="40">
    <TR>

       <% if (!request.getParameter("shrAction").equals("INQ")){ %>

            <TD ALIGN=CENTER width="33%" height="43"> <img class="imgfilter" name="Submit" src="<%=request.getContextPath()%>/images/s/approve.gif" 
onmousedown="this.className='imgfilterpress'" onmouseup="this.className='imgfilter'" onClick="goAction('Z')">
</TD>
	<% } %>
      <TD ALIGN=CENTER width="34%" height="43"><img class="imgfilter" name="Submit" src="<%=request.getContextPath()%>/images/s/EXIT.gif" 
onmousedown="this.className='imgfilterpress'" onmouseup="this.className='imgfilter'" onClick="goExit()"> </TD>
    </TR>
  </TABLE>

<%      
  }
%> 
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
</FORM>
<script>
document.forms[0].totalRow.value="<%= k %>";
function resizeDoc() {
     divResizeOfac(false);
     adjustEquTables(headTable, dataTable, dataDiv1,1,false);
}
resizeDoc();
window.onresize=resizeDoc;

function divResizeOfac(addInfo) {
  var minValue= mainTable.offsetTop + dataDiv1.offsetTop + 120;
  var h = document.body.clientHeight - minValue ;
  var totalrow= parseInt(document.forms[0].totalRow.value);
  var maxHeight= totalrow * 20; //dataDiv1.childNode.offsetHeight; 
  

  if (addInfo) {
    minValue= mainTable.offsetTop + dataDiv1.offsetTop + tbAddInfo.offsetHeight + 4;
  }
  h = (h <= 0) ? maxHeight: h; 
  if ( totalrow > 10 && document.body.clientHeight > minValue ) {
     if ( h  < maxHeight ) {
        dataDiv1.style.height= h + ""; 
   	dataDiv1.style.overflowY="scroll";
       }
    else {
           dataDiv1.style.height=maxHeight + ""; 
   	     dataDiv1.style.overflowY="";
        }
    }
  else if ( totalrow > 10 && document.body.clientHeight <= minValue ) {
        dataDiv1.style.height= (addInfo) ? "" + tbAddInfo.offsetHeight:"200"; 
        dataDiv1.style.overflowY="scroll";
   }
}

</script>
</BODY>
</HTML>