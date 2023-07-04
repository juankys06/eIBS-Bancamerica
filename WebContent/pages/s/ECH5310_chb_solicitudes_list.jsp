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

<jsp:useBean id= "chkbList" class= "datapro.eibs.beans.JBObjList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
 

<SCRIPT language="javascript">
 
  function goAction(op) {
     	
     document.forms[0].action.value = op;
     
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
   
   //for ( var i=1; i<dataTable.rows.length; i++ ) {
   //    dataTable.rows[i].className="trnormal";
   // }
   var i = parseInt(document.forms[0]["ACTROW"].value);
   var j = idxRow + 1;
   dataTable.rows[i].className="trnormal";
   dataTable.rows[j].className="trhighlight";
   document.forms[0]["ACTROW"].value = "" + j;
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

<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH5310" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
<INPUT TYPE=HIDDEN NAME="action" VALUE="R">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
<INPUT TYPE=HIDDEN NAME="ACTROW" VALUE="1">
<h3 align="center">Solicitudes Pendientes en la Dispensadora	
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="chb_solicitudes_list.jsp, ECH5310">
</h3>
<hr size="4">
  
<%
	if ( chkbList.getNoResult() ) {
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
      <TD class=TDBKG> <a href="javascript:goAction('R')">Re-enviar</a> 
      </TD>
      <TD class=TDBKG> <a href="javascript:goExit()">Salir</a> 
      </TD>
    </TR>
  </TABLE>
  
 <TABLE  id="mainTable" class="tableinfo" >
 <TR> 
    <TD NOWRAP >
  <TABLE id="dataTable" width="100%">
  <TR id="trdark"> 
    <TH ALIGN=CENTER ></TH>
    <TH ALIGN=CENTER >Fecha<br>Solicitud</TH>
    <TH ALIGN=CENTER >Cuenta</TH>
    <TH ALIGN=CENTER >Cliente</TH>
    <TH ALIGN=CENTER >Succ</TH>
    <TH ALIGN=CENTER >Número<br>de Chequera</TH>
    <TH ALIGN=CENTER >Cheque<br>Inicial</TH>
    <TH ALIGN=CENTER >Cheque<br>Final</TH>
    <TH ALIGN=CENTER >Estado</TH>
  </TR>
     <%
                chkbList.initRow();
                while (chkbList.getNextRow()) {
                  if (firstTime) {
						firstTime = false;
						chk = "checked";
				  }
				  else {
						if (chkbList.getCurrentRow() == row )
							chk = "checked";
						else 
							chk = "";
				  }
                  datapro.eibs.beans.ECH531001Message msgChkB = (datapro.eibs.beans.ECH531001Message) chkbList.getRecord();
     %>               
        <TR>
			<TD NOWRAP width="2%"><input type="radio" name="ROW" value="<%= chkbList.getCurrentRow()%>" <%=chk%>  onclick="showAddInfo(<%= chkbList.getCurrentRow()%>)"></TD>
			<TD NOWRAP ALIGN="CENTER"><%= Util.formatDate(msgChkB.getE01CHDRQ1(),msgChkB.getE01CHDRQ2(),msgChkB.getE01CHDRQ3()) %></TD>
			<TD NOWRAP ALIGN="CENTER"><%= Util.formatCell(msgChkB.getE01CHDACC())%></TD>
			<TD NOWRAP ALIGN="LEFT"><%= Util.formatCell(msgChkB.getE01CHDNA1())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%= Util.formatCell(msgChkB.getE01CHDBRN()) %></TD>
			<TD NOWRAP ALIGN="CENTER"><%= Util.formatCell(msgChkB.getE01CHDNCB())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgChkB.getE01CHDICK())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgChkB.getE01CHDFCK())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgChkB.getE01CHDSDS())%></TD>
		</TR>
    <%                  
                }
    %> 
   
     </Table>
  </TD>
  </TR>	
</TABLE>

<SCRIPT language="JavaScript">
  showChecked("ROW");       
</SCRIPT>

<%   		
	} 
 %>
</FORM>

</BODY>
</HTML>
