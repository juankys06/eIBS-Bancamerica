<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import ="datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Lista de Cuentas
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "authoList" class= "datapro.eibs.beans.JBObjList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT language="javascript">
  var reason = '';
  
  function goAction(op) {
     	
     document.forms[0].action.value = op;
     document.forms[0].reason.value = reason;
     
     var formLength= document.forms[0].elements.length;
     var ok = false;
     for(var n=0;n<formLength;n++)
      {
      	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "ROW") 
      	{
        		ok = true;
        		break;
      	}
      }
      if ( ok ) {          
        document.forms[0].submit();
      }
      else {
 		alert("Seleccione una cuenta antes de realizar esta operacion");	   
      }

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
   adjustDifTables(headTable, dataTable, dataDiv1,2,1);
   document.forms[0]["E01DEANRTE"+idxRow].focus();
  }   

	
</SCRIPT>

</HEAD>

<BODY>

<% 
 boolean firstTime = true;
 String chk = "";
 int row;
 try {
	  	row = Integer.parseInt(request.getParameter("ROW"));
	  	firstTime = false;
	} 
 catch (Exception e) {
		row = 0;
		firstTime = true;		
	}
	
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     //firstTime = false;
 } 
%>

<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0141" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="action" VALUE="A">
<INPUT TYPE=HIDDEN NAME="reason" VALUE="">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
<h3 align="center">Autorización de Sobre-Tasas	
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="authoritation_list.jsp,EDL0141">
</h3>
<hr size="4">
  
<%
	if ( authoList.getNoResult() ) {
%> 

  <TABLE class="tbenter" width=100% height=40%>
   	<TR>
      <TD> 
        <div align="center"> 
          <p>
            <b>No hay resultados que correspondan a su criterio de búsqueda</b>
          </p>          
        </div>
      </TD>
     </TR>
   </TABLE>
  <%   		
	}
	else { 
  %>   
  <TABLE class="tbenter" width=100%>
    <TR> 
      <TD class=TDBKG> <a href="javascript:goAction('A')" id="linkApproval">Autorizar</a> 
      </TD>
      <TD class=TDBKG> <a href="javascript:enterReason('R')" id="linkReject">Rechazar</a> 
      </TD>
      <TD class=TDBKG> <a href="javascript:goExit()">Salir</a> 
      </TD>
    </TR>
  </TABLE>
  
 <TABLE  id="mainTable" class="tableinfo" >
 <TR> 
    <TD NOWRAP valign="top" width="100%">
  <TABLE id="headTable" >
  <TR id="trdark"> 
    <TH ALIGN=CENTER rowspan="2" nowrap></TH>
    <TH ALIGN=CENTER rowspan="2" nowrap>Cuenta</TH>
    <TH ALIGN=CENTER colspan="2" nowrap>Cliente</TH>
    <TH ALIGN=CENTER rowspan="2" nowrap>Código de Producto</TH>
  </TR>
  <TR id="trdark"> 
    <TH ALIGN=CENTER nowrap>Número</TH>
    <TH ALIGN=CENTER nowrap>Nombre</TH>
  </TR>
  </TABLE>
  
   <div id="dataDiv1" class="scbarcolor" style="padding:0">
    <table id="dataTable"  >
    <%
                authoList.initRow();
                int k=1;
                
                while (authoList.getNextRow()) {
                  if (firstTime) {
						firstTime = false;
						chk = "checked";
				  }
				  else {
						if (authoList.getCurrentRow() == row )
							chk = "checked";
						else 
							chk = "";
				  }
                  datapro.eibs.beans.EDL014101Message msgAuthoritation = (datapro.eibs.beans.EDL014101Message) authoList.getRecord();
     %>               
                <TR>
					<TD NOWRAP><input type="radio" name="ROW" value="<%= authoList.getCurrentRow()%>" <%=chk%>  onclick="showAddInfo('<%= authoList.getCurrentRow()%>')"></TD>
					<TD NOWRAP ALIGN="LEFT"><%= Util.formatCell(msgAuthoritation.getE01DEAACC())%></TD>
					<TD NOWRAP ALIGN="CENTER"><%= Util.formatCell(msgAuthoritation.getE01DEACUN()) %></TD>
					<TD NOWRAP ALIGN="LEFT"><%= Util.formatCell(msgAuthoritation.getE01CUSNA1())%></TD>
					<TD NOWRAP ALIGN="CENTER"><%= Util.formatCell(msgAuthoritation.getE01DEAPRO())%>
					<INPUT TYPE=HIDDEN NAME="TXTDATA<%= authoList.getCurrentRow()%>" 
					 VALUE="<%=Util.fcolorCCY(msgAuthoritation.getE01DEAPRI())%><% out.print(" <br>");%>
					 <%=Util.formatCell(msgAuthoritation.getE01DEAUSR())%><% out.print(" <br>");%>
					 <%=Util.formatCell(msgAuthoritation.getE01DEABAS())%><% out.print(" <br>");%>
					 <%=Util.formatCell(msgAuthoritation.getE01DEAORTE())%><% out.print(" <br>");%>
					 <% out.print(" <INPUT TYPE=TEXT SIZE=9 NAME='E01DEANRTE" + authoList.getCurrentRow() + "' MAXLENGTH=10 VALUE='" + Util.formatCell(msgAuthoritation.getE01DEANRTE()) + "'>");%>">
					</TD>
				</TR>    		
    <%             k++;        
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
      <tr id="trclear">
         <TD ALIGN="RIGHT" valign="center" nowrap><b>Principal : <br>Operador : <br>Base : <br>Tasa :<br>Nueva Tasa : </b></TD>
         <TD ALIGN="LEFT" valign="center" nowrap class="tdaddinfo"></TD>
      </tr>
     </Table>
  </TD>
  </TR>	
</TABLE>

<SCRIPT language="JavaScript">
  document.forms[0].totalRow.value="<%= k %>";
   function resizeDoc() {
       divResize(true);
       adjustDifTables(headTable, dataTable, dataDiv1,2,1);
  }
  showChecked("ROW");
  resizeDoc();
  tbAddInfoH.rows[0].cells[0].height = headTable.rows[0].cells[0].clientHeight;
  window.onresize=resizeDoc;
     
</SCRIPT>

<%   		
	} 
 %>
</FORM>

</BODY>
</HTML>
