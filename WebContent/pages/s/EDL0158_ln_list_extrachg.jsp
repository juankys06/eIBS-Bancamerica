 <%@ page import = "datapro.eibs.master.Util" %>
 <%@ page import = "datapro.eibs.beans.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "extList" class= "datapro.eibs.beans.JBObjList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="javascript">

function goAction(op) {
 var delok= false;
 var page="";
 if (op == 3) {
     page = prefix +language + 'EDL0158_ln_delete_extrachg.jsp?ROW=' + document.forms[0].ROW.value;
 } else if (op == 1) {
     page = prefix +language + 'EDL0158_ln_new_extrachg.jsp';
 } else {
     page = prefix +language + 'EDL0158_ln_maint_extrachg.jsp?ROW=' + document.forms[0].ROW.value;
 }
   CenterWindow(page,600,470,2);
 }      

function goExit(){
  window.location.href="<%=request.getContextPath()%>/pages/background.jsp";

}

function showInfo(idxRow,d1,d2,d3){
   for ( var i=0; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idxRow].className="trhighlight";
   document.forms[0].ROW.value = "" + idxRow;
   document.forms[0].E01DLSPD1.value = d1;
   document.forms[0].E01DLSPD2.value = d2;
   document.forms[0].E01DLSPD3.value = d3;
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


<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0158">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="OPT" VALUE="3">
<INPUT TYPE=HIDDEN NAME="ROW" VALUE="0">

<h3 align="center"> Lista de Gastos Extraordinarios<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_list_extrachg.jsp,EDL0158"> 
</h3>
<hr size="4">
  
  
<TABLE class="tbenter">
  <TR> 
    <TD ALIGN=CENTER CLASS=tdbkg><a href="javascript:goAction('1')">Nueva</a></TD>
 <%
	if ( !(extList.getNoResult() )) {
 %>
    <TD ALIGN=CENTER CLASS=tdbkg><a href="javascript:goAction('2')">Modificar</a></TD>
    <TD ALIGN=CENTER CLASS=tdbkg><a href="javascript:goAction('3')">Eliminar</a></TD>
 <% } %>
    <TD ALIGN=CENTER CLASS=tdbkg><a href="javascript:goExit()">Salir</a></TD>
  </TR>
</TABLE>

  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="40%" > 
              <div align="right"><b>Cuenta : </b></div>
            </td>
            <td nowrap width="60%" > 
              <div align="left"> 
                <input type="text" name="E01DLSACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
<br>
<%
	if ( extList.getNoResult() ) {
 %>
   	<TABLE class="tbenter" width=100% height=40%>
   	 <TR valign="middle">
      <TD> 
        <div align="center">       		
          <p><b>No hay resultados que correspondan a su criterio de búsqueda. 
            Haga Click en la opci&oacute;n de Nueva para adicionar un nuevo gasto 
            </b></p>
          </div>
      </TD>
     </TR>
   	</TABLE>
<%   		
	}
	else {
%>  
 <INPUT TYPE=HIDDEN NAME="E01DLSPD1" VALUE="">
 <INPUT TYPE=HIDDEN NAME="E01DLSPD2" VALUE="">
 <INPUT TYPE=HIDDEN NAME="E01DLSPD3" VALUE="">
 
 <TABLE  id="mainTable" class="tableinfo" >
 <TR> 
   <TD NOWRAP valign="top" width="100%">
    <TABLE id="headTable" >
    <TR id="trdark"> 
      <TH ALIGN=CENTER nowrap>&nbsp;</TH>
      <TH ALIGN=CENTER nowrap>Secuencia</TH>
      <TH ALIGN=CENTER nowrap>Fecha Aplicación</TH>
      <TH ALIGN=CENTER nowrap>Concepto</TH>
      <TH ALIGN=CENTER nowrap>Monto</TH>
    </TR>
    </TABLE>
  
    <div id="dataDiv1" class="scbarcolor" style="padding:0">
    <table id="dataTable"  >
    <%
         int row;
		 try{row = Integer.parseInt(request.getParameter("ROW"));}catch(Exception e){row = 0;}
         extList.initRow();        
         while (extList.getNextRow()) {
            EDL015801Message msgLN = (EDL015801Message) extList.getRecord();
     %> 
            <TR> 
              <TD ALIGN=CENTER NOWRAP> 
                <INPUT TYPE="radio" NAME="EXTCHG" VALUE="<%= extList.getCurrentRow() %>" <% if (extList.getCurrentRow() == row) out.print("checked"); %> onClick="showInfo(<%= extList.getCurrentRow() %>,'<%=msgLN.getE01DLSPD1()%>','<%=msgLN.getE01DLSPD2()%>','<%=msgLN.getE01DLSPD3()%>')">
              </TD>
              <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(msgLN.getE01DLSSEQ()) %></TD>              
              <TD ALIGN=CENTER NOWRAP><%= Util.formatDate(msgLN.getE01DLSPD1(),msgLN.getE01DLSPD2(),msgLN.getE01DLSPD3()) %></TD>              
              <TD NOWRAP><%= Util.formatCell(msgLN.getE01DLSNAR()) %></TD>
              <TD ALIGN=RIGHT NOWRAP><%= Util.formatCell(msgLN.getE01DLSAMT()) %></TD>
             </TR>
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
       adjustEquTables(headTable, dataTable, dataDiv1,1,false);
    }
    showChecked("EXTCHG");
    resizeDoc();
    window.onresize=resizeDoc;     
  </SCRIPT>
  <%
  }
 %>
 


</FORM>

</BODY>
</HTML>
