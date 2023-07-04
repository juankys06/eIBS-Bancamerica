<HTML>
<HEAD>
<TITLE>
Lista de Lineas de Cr�dito a Aprobar
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="javascript">
  var reason = '';
 
  function goAction(op) {
     document.forms[0].action.value = op;
     document.forms[0].reason.value = reason;
     var formLength= document.forms[0].elements.length;
     var ok = false;
     for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "ACCNUM") 
      	{
        		ok = true;
        		break;
      	}
      }
	  if ( ok ) {
          document.forms[0].submit();
      }
     else {
			alert("Seleccione una cuenta antes de realizar esta operaci�n");	   
      }

  } 
  function goExit(){
   window.location.href="<%=request.getContextPath()%>/pages/background.jsp";

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

<BODY onload="MM_preloadImages('<%=request.getContextPath()%>/images/s/approve_over.gif','<%=request.getContextPath()%>/images/s/delete_over.gif','<%=request.getContextPath()%>/images/s/reject_over.gif')">

<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0040">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="action" VALUE="A">
<INPUT TYPE=HIDDEN NAME="reason" VALUE="">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">       
 
 <h3 align="center"> Aprobaci�n de Lineas de Cr�dito <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="approval_list.jsp,ELN0040"> 
  </h3>
<hr size="4">
  
  <TABLE class="tbenter">
    <TR>
      <TD ALIGN=CENTER width="25%">
  			<a href="javascript:goAction('A')" id="linkApproval" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1','','<%=request.getContextPath()%>/images/s/approve_over.gif',1)"><img name="Image1" alt="Aprobar" border="0" src="<%=request.getContextPath()%>/images/s/approve.gif" ></a>
      </TD>
      <TD ALIGN=CENTER width="25%">
  			<a href="javascript:goAction('D')" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image3','','<%=request.getContextPath()%>/images/s/delete_over.gif',1)"><img name="Image3" alt="Borrar" border="0" src="<%=request.getContextPath()%>/images/s/delete.gif" ></a>
      </TD>
      <TD ALIGN=CENTER width="25%">
  			<a href="javascript:enterReason('R')" id="linkReject" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image2','','<%=request.getContextPath()%>/images/s/reject_over.gif',1)"><img name="Image2" alt="Rechazar" border="0" src="<%=request.getContextPath()%>/images/s/reject.gif" ></a>
      </TD>
      <TD ALIGN=CENTER width="25%">
  			<a href="javascript:goExit()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image5','','<%=request.getContextPath()%>/images/s/exit_over.gif',1)"><img name="Image5" alt="Salir" border="0" src="<%=request.getContextPath()%>/images/s/EXIT.gif" ></a>
      </TD>
    </TR>
  </TABLE>
   
  <TABLE  id="mainTable" class="tableinfo" ALIGN=CENTER NOWRAP>
 <TR> 
    <TD NOWRAP>
  <TABLE id="headTable"  NOWRAP>
  <TR id="trdark"> 
      <TH ALIGN=CENTER rowspan="2" nowrap></TH>
      <TH ALIGN=CENTER colspan="2" nowrap>N&uacute;mero de</TH>
      <TH ALIGN=CENTER rowspan="2" nowrap>Nombre Cliente</TH>
      <TH ALIGN=CENTER rowspan="2" nowrap>Comentario</TH>
      <TH ALIGN=CENTER rowspan="2" nowrap>Importe<br>
        Aprobado</TH>
    </TR>
    <TR id="trdark">  
      <TH ALIGN=CENTER nowrap>Cliente</TH>
      <TH ALIGN=CENTER nowrap>L&iacute;nea</TH>
    </TR>
    </TABLE>
  
   <div id="dataDiv1" class="scbarcolor" style="padding:0" NOWRAP>
    <table id="dataTable"  NOWRAP>
    <%
                appList.initRow();
                int k=1;
                while (appList.getNextRow()) {
                    if (appList.getFlag().equals("")) {
                    		out.println(appList.getRecord());
                    k++;
                    }        
                }
                  if ( k > 10 ) {
      %>
                    		 <SCRIPT Language="Javascript">
                    		    document.forms[0].totalRow.value="<%= k %>";
                    		    //initTime();
   							</SCRIPT>	 
  			<%	 
               }
    %> 
   </table>
   </div>
   
  </TD>
  </TR>	
</TABLE>

<SCRIPT language="JavaScript">
  function resizeDoc() {
       divResize();
    adjustDifTables(headTable, dataTable, dataDiv1,1,1); 
    }
  resizeDoc();
  window.onresize=resizeDoc;
     
</SCRIPT>
</FORM>

</BODY>
</HTML>
