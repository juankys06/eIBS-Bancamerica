<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import ="datapro.eibs.master.Util,datapro.eibs.beans.*" %>
<HTML>
<HEAD>
<TITLE>
Lista de Solicitudes Pendientes
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "chkbList" class= "datapro.eibs.beans.JBObjList"  scope="session" /> 
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" /> 
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<SCRIPT Language="JavaScript">

 var reason = '';

 function goApproval() {
	document.forms[0].action.value = "A";
	document.forms[0].submit();
 }

 function goAction(op) {
     
     document.forms[0].action.value = op;
     document.forms[0].reason.value = reason;
     
     var ok = true;
     
     if (op == 'D') {    
        ok = confirm("Esta opcion borrara la solicitud seleccionada");     
     }
     if ( ok ) {         
        document.forms[0].submit();          
     }
      

 }

 function setInfo(idxRow, numSol) {
   var i= parseInt(document.forms[0].actRow.value);
   document.forms[0].NUMSOL.value = numSol;
   tbAddInfo.rows[0].cells[1].style.color="white";
   tbAddInfo.rows[0].cells[1].innerHTML=document.forms[0]["TXTDATA"+idxRow].value;
   if (tbAddInfo.rows[0].cells[1].filters[0])
   	tbAddInfo.rows[0].cells[1].filters[0].apply();
   tbAddInfo.rows[0].cells[1].style.color="";
   if (tbAddInfo.rows[0].cells[1].filters[0])
    tbAddInfo.rows[0].cells[1].filters[0].Play();
   dataTable.rows[i].className="trnormal";   
   dataTable.rows[idxRow].className="trhighlight";
   document.forms[0].actRow.value = idxRow; 
   adjustEquTables(headTable, dataTable, dataDiv1,1,false);  
   
  }
   
</SCRIPT>
</head>

<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

%> 
<h3 align="center">Aprobacion de Solicitudes de Chequeras sin Personalizaci&oacute;n
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="chb_list.jsp,ECH0306">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH0306">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
<INPUT TYPE=HIDDEN NAME="action" VALUE="A">
<INPUT TYPE=HIDDEN NAME="reason" VALUE="">
<INPUT TYPE=HIDDEN NAME="NUMSOL" VALUE="">
<%
	 int row;
	 try{row = Integer.parseInt(request.getParameter("ROW"));}catch(Exception e){row = 0;}
         
%> 

  <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="actRow" VALUE="<%= row %>">
  
  <table class="tbenter" width="100%">
    <tr>     
      <td class=TDBKG><a href="javascript:goAction('A')" id="linkApproval">Aprobar</a></td>
      <td class=TDBKG><a href="javascript:enterReason('R')" id="linkReject">Rechazar</a></td>
      <td class=TDBKG><a href="javascript:goAction('D')" id="linkDelete">Borrar</a></td>
      <td class=TDBKG><a href="javascript:checkClose()">Salir</a></td>    
    </tr>
  </table>
 <TABLE  id="mainTable" class="tableinfo">
 <TR> 
   <TD NOWRAP valign="top">
        <TABLE id="headTable">
          <TR id="trdark"> 
            <TH ALIGN=CENTER NOWRAP></TH>
            <TH ALIGN=CENTER NOWRAP>Solicitud</TH>
            <TH ALIGN=CENTER NOWRAP>Banco</TH>
            <TH ALIGN=CENTER NOWRAP>Suc.<br>Destino</TH>            
            <TH ALIGN=CENTER NOWRAP>Suc.<br>Origen</TH>
            <TH ALIGN=CENTER NOWRAP>Serie <br>Chequera</TH>           
            <TH ALIGN=CENTER NOWRAP>Cantidad</TH>
          </TR>
        </TABLE>
        
    <div id="dataDiv1" class="scbarcolor" style="padding:0" NOWRAP>
        <table id="dataTable"  NOWRAP>
    <%
         chkbList.initRow();
         int k=1;
         while (chkbList.getNextRow()) {
            ECH030602Message msgCHKB = (ECH030602Message) chkbList.getRecord();
     %> 
            <TR> 
              <TD ALIGN=CENTER NOWRAP> 
                <INPUT TYPE="radio" NAME="ROW" VALUE="<%= chkbList.getCurrentRow() %>" <% if (chkbList.getCurrentRow() == row) out.print("checked");%> onClick="setInfo(<%= chkbList.getCurrentRow()%>,<%= msgCHKB.getE02NUMSOL()%>)">
                <INPUT TYPE="HIDDEN" NAME="TXTDATA<%= chkbList.getCurrentRow()%>" VALUE="
                <%=msgCHKB.getE02REMARK()%><BR>
                <%=msgCHKB.getE02CODCCY()%><BR>
                <%=msgCHKB.getE02TIPCHK()%><BR>
                <% if (msgCHKB.getE02TIPPER().equals("1")) out.print("JURIDICO");
                   else if (msgCHKB.getE02TIPPER().equals("2")) out.print("NATURAL");
                   else if (msgCHKB.getE02TIPPER().equals("3")) out.print("NO APLICA");%><BR>
                <%=msgCHKB.getE02USERID()%><BR>
                <%=Util.formatDate(msgCHKB.getE02FECDT1(),msgCHKB.getE02FECDT2(),msgCHKB.getE02FECDT3())%>                
                ">
              </TD>
              <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(msgCHKB.getE02NUMSOL()) %></TD>
              <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(msgCHKB.getE02CODBNK()) %></TD>              
              <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(msgCHKB.getE02SUCDST()) %></TD>
              <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(msgCHKB.getE02SUCORG()) %></TD>
              <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(msgCHKB.getE02SERIEK()) %></TD>
              <TD ALIGN=RIGHT NOWRAP><%= Util.formatCell(msgCHKB.getE02CANCHK()) %></TD>
            </TR>
      <%
              k++;
         }        
    %> 
          </table>
   </div>
   </TD>
   <TD nowrap ALIGN="RIGHT" valign="top">
      <Table id="tbAddInfoH" width="100%" >
        <tr id="trdark">
         <TH ALIGN=CENTER nowrap >Información Básica</TH>
        </tr>
      </Table>

     <Table id="tbAddInfo" >
      <tr id="trclear" >
         <TD  ALIGN="RIGHT"  valign="center" nowrap ><b>Comentario : <br>Moneda : <br>Tipo Chequera : <br>Tipo Persona : <br>Operador : <br>Fecha : </b></TD>
         <TD ALIGN="LEFT" valign="center" nowrap class="tdaddinfo"></TD>
      </tr>
     </Table>

  </TD>
  </TR>	
</TABLE>
      
<SCRIPT language="JavaScript">
  document.forms[0].totalRow.value="<%= k %>";
  function resizeDoc() {
       divResize();
       adjustEquTables(headTable, dataTable, dataDiv1,1,false);
  }
  showChecked("ROW");
  resizeDoc();
  tbAddInfoH.rows[0].cells[0].height = headTable.rows[0].cells[0].clientHeight;
  window.onresize=resizeDoc;
     
</SCRIPT>

</FORM>

</BODY>
</HTML>
