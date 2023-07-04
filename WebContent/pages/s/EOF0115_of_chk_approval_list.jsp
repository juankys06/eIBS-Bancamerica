<HTML>
<HEAD>
<TITLE>
Lista de Cheques a Aprobar
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "dvList" class= "datapro.eibs.beans.JBList"  scope="session" />

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
			alert("Seleccione un cheque antes de realizar esta operación");	   
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

<BODY onload="MM_preloadImages('<%=request.getContextPath()%>/images/s/approve_over.gif','<%=request.getContextPath()%>/images/s/reject_over.gif')">

<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115A">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="action" VALUE="A">
<INPUT TYPE=HIDDEN NAME="reason" VALUE="">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
         
<h3 align="center"> Aprobación de Cheques de Gerencia <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="approval_list.jsp,EOF0115"> 
  </h3>
<hr size="4">

  <TABLE class="tbenter">
    <TR>
      <TD ALIGN=CENTER width="25%">
  			<a href="javascript:goAction('A')" id="linkApproval" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1','','<%=request.getContextPath()%>/images/s/approve_over.gif',1)"><img name="Image1" alt="Aprobar" border="0" src="<%=request.getContextPath()%>/images/s/approve.gif" ></a>
      </TD>
      <!--
      <TD ALIGN=CENTER width="25%">
  			<a href="javascript:enterReason('R')" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image2','','<%=request.getContextPath()%>/images/s/reject_over.gif',1)"><img name="Image2" alt="Rechazar" border="0" src="<%=request.getContextPath()%>/images/s/reject.gif" ></a>
      </TD>
      -->
      <TD ALIGN=CENTER width="25%">
  			<a href="javascript:enterReason('D')" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image3','','<%=request.getContextPath()%>/images/s/delete_over.gif',1)"><img name="Image3" alt="Eliminar" border="0" src="<%=request.getContextPath()%>/images/s/delete.gif" ></a>
      </TD>

      <TD ALIGN=CENTER width="25%">
  			<a href="javascript:goExit()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image5','','<%=request.getContextPath()%>/images/s/EXIT_OVER.gif',1)"><img name="Image5" border="0" src="<%=request.getContextPath()%>/images/s/EXIT.gif" ></a>
      </TD>
    </TR>
  </TABLE>
  
  <TABLE  id="mainTable" class="tableinfo" ALIGN=CENTER NOWRAP>
 <TR> 
    <TD NOWRAP>
  <TABLE id="headTable"  NOWRAP>
  <TR id="trdark"> 
      <th align=CENTER  nowrap >&nbsp;</th>
      <th align=CENTER  nowrap >Referencia</th>
      <th align=CENTER  nowrap >Moneda</th>
      <th align=CENTER  nowrap >Sucursal</th>
      <th align=CENTER  nowrap >Monto</th>
      <th align=CENTER  nowrap > 
        <div align="right">Estado</div>
      </th>
      <th align=CENTER  nowrap > 
        <div align="center">Emitido</div>
      </th>
      <th align=CENTER  nowrap > 
        <div align="center">Beneficiario</div>
      </th>
    </tr>
    </TABLE>
  
   <div id="dataDiv1" class="scbarcolor" style="padding:0" NOWRAP>
    <table id="dataTable"  NOWRAP>
    <%
                dvList.initRow();
                 int k=1;
                while (dvList.getNextRow()) {
                    if (dvList.getFlag().equals("")) {
                    		out.println(dvList.getRecord());
                    		k++;
                    }
                }
                 if ( k > 10 ) {
               }
              %> 
   </table>
   </div>
   
  </TD>
  </TR>	
</TABLE>
  </TABLE>
  <TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
<%
        if ( dvList.getShowPrev() ) {
      			int pos = dvList.getFirstRec() - 51;
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.products.JSEOF0115A?SCREEN=1&Pos=" + pos +"\"><img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
%> 
	</TD>
 	<TD WIDTH="50%" ALIGN=RIGHT>
<%       
        if ( dvList.getShowNext() ) {
      			int pos = dvList.getLastRec();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.products.JSEOF0115A?SCREEN=1&Pos=" + pos +"\"><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");
        }
%> 
 	</TD>
	 </TR>
	 </TABLE>

<SCRIPT language="JavaScript">
   document.forms[0].totalRow.value="<%= k %>";

  function resizeDoc() {
       divResize();
    adjustEquTables(headTable, dataTable, dataDiv1,1,false); 
    }
  resizeDoc();
  window.onresize=resizeDoc;
     
</SCRIPT>
  
</FORM>

</BODY>
</HTML>

