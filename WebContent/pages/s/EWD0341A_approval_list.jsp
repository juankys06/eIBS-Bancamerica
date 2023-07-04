<html>
<head>
<title>Back Office</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0341Help" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">
var reason = '';

   function showSwift(refnum) {

     page = webapp + "/servlet/datapro.eibs.transfer.JSEWD0341A?SCREEN=3&REFNUM="+refnum;
	 CenterWindow(page,600,500,2);
  }


function goAction(op) {
     document.forms[0].action.value = op;
	 document.forms[0].reason.value = reason;
     document.forms[0].submit();
     
 }
 
  

 function goExit(){
  window.location.href="<%=request.getContextPath()%>/pages/background.jsp";
  }

</SCRIPT>  

<script language="JavaScript">
<!--
function getValor(Account) {

    document.forms[0].acc.value = Account;
  }

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v3.0
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->




</SCRIPT>
</head>

<BODY >
<h3 align="center">Aprobacion de Transferencias<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="approval_list.jsp, EWD0341A"></h3>
<hr size="4">
<%
	if ( EWD0341Help.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
      <div align="center"> <b> No existen resultados para su criterio de busqueda </b></div>
      </TD></TR>
   		</TABLE>
<%  
		}
	else {
%>	
  <form Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.transfer.JSEWD0341A">

  <% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 
  <p> 
    <input type=HIDDEN name="SCREEN" value="6">
    <INPUT TYPE=HIDDEN NAME="opt">
    <input type=HIDDEN name="totalRow" value="0">
    <input type=HIDDEN name="action">
    <input type=HIDDEN name="reason" value="">
  </p>
  <table class="tbenter">
    <tr> 
      <td class=TDBKG width="33%"> <a href="javascript:goAction('A')"  id="linkApproval" >Aprobar</a> 
      </td>
      <td class=TDBKG width="33%"> <a href="javascript:enterReason('R')" id="linkReject" >Rechazar</a> 
      </td>
      <td class=TDBKG width="34%"> <a href="javascript:goAction('D')" >Borrar</a> 
      </td>
    </tr>
  </table>
  <br>

  <TABLE  id="mainTable" class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" width="100%" >
        <table id="headTable" width="100%" >
          <tr id="trdark"> 
            <th align=CENTER nowrap >&nbsp;</th>
            <th align=CENTER nowrap > 
              <div align="center">Referencia</div>
            </th>
            <th align=CENTER nowrap >Formato</th>
            <th align=CENTER nowrap >Estado</th>
            <th align=CENTER nowrap >Ordenante</th>
            <th align=CENTER nowrap > 
              <div align="center">Fecha</div>
            </th>
            <th align=CENTER nowrap > 
              <div align="center">Monto</div>
            </th>
            <th align=CENTER nowrap > 
              <div align="center">Beneficiario</div>
            </th>
          </tr>
        </table>
        <div id="dataDiv1" class="scbarcolor" >
    <table id="dataTable" > 
     <%
                EWD0341Help.initRow();
                int k=1;
                while (EWD0341Help.getNextRow()) {
                 
                    		out.println(EWD0341Help.getRecord());
							k ++;
                    
                }
              %> 
  </table>

 </div>
</Table>
  <br>
  <table class="tbenter" width="98%" align=CENTER>
    <tr> 
      <td width="50%" align=LEFT> <%
        if ( EWD0341Help.getShowPrev() ) {
      			int pos = EWD0341Help.getFirstRec() - 51;
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.transfer.JSEWD0341A?SCREEN=1&Pos=" + pos +"\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/previous_records.gif\" ></A>");
        }
   %> </td>
      <td width="50%" align=RIGHT> <% 
        if ( EWD0341Help.getShowNext() ) {
      			int pos = EWD0341Help.getLastRec();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.transfer.JSEWD0341A?SCREEN=1&Pos=" + pos +"\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/next_records.gif\" ></A>");
        }
  %> </td>
    </tr>
  </table>
 
    <SCRIPT language="JavaScript">
     document.forms[0].totalRow.value="<%= k %>";
     function resizeDoc() {
       divResize(false);
     adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      }

     resizeDoc();
    
     window.onresize=resizeDoc;
     
</SCRIPT>
 
</form>
<%
   }  
%>
</body>
</html>
