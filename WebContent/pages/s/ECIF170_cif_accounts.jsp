<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Detalle de Cuentas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "cifAcc" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
  function goAction(op) {

     document.forms[0].opt.value = op;
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
			alert("Seleccione una cuenta antes de realizar esta operación");	   
     }

  }
  
function showAddInfo(idxRow){
var opening="<b>Banco : <br>Sucursal : <br>Apertura : <br>Principal : <br>Interes : <br>Otros : <br>Total : </b>";
var mature="<b>Banco : <br>Sucursal : <br>Vencimiento : <br>Principal : <br>Interes : <br>Otros : <br>Total : </b>";
var codeACD=document.forms[0]["CODACD"+idxRow].value;

   if (codeACD=="01" || codeACD=="02" || codeACD=="03" || codeACD=="04"){
     tbAddInfo.rows[0].cells[0].innerHTML=opening; 
   } else {
     tbAddInfo.rows[0].cells[0].innerHTML=mature;
   }
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
   adjustDifTables(headTable, dataTable, dataDiv1,1,1);
  } 

 // builtNewMenu(ecif10_i_opt);
</SCRIPT>

<SCRIPT> 
// initMenu(); 
</SCRIPT>

</head>

<BODY onload="MM_preloadImages('<%=request.getContextPath()%>/images/s/exit_over.gif','<%=request.getContextPath()%>/images/s/inquiry_over.gif','<%=request.getContextPath()%>/images/s/average_over.gif','<%=request.getContextPath()%>/images/s/statement_account_over.gif')">

<%@ page import="datapro.eibs.master.Util" %>
<h3 align="center">Lista de Cuentas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cif_accounts.jsp,ECIF170"></h3>
<hr size="4">
  <form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF010" >
    <INPUT TYPE=HIDDEN NAME="SCREEN" value="10">
	 <INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
	 <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
<%
	if ( cifAcc.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
        <div align="center">
        		<font size="3"><b>
        				No hay resultados que correspondan a su criterio de búsqueda 
        		</b></font>
        	</div>
      </TD></TR>
   		</TABLE>
<%   		
	}
	else {
    
%>
  
  <TABLE class="tbenter">
    <TR>
      <TD CLASS="TDBKG" ALIGN=CENTER width="20%">
  			<a href="javascript:goAction(1)" >Consulta</a>
      </TD>
      <TD CLASS="TDBKG" ALIGN=CENTER width="20%">
  			<a href="javascript:goAction(2)" >Estado<br>de Cuentas</a>
      </TD>
      <TD CLASS="TDBKG" ALIGN=CENTER width="20%">
  			<a href="javascript:goAction(3)" >Promedio</a>
      </TD>
      <TD CLASS="TDBKG" ALIGN=CENTER width="20%">
  			<a href="<%=request.getContextPath()%>/pages/background.jsp" onClick="top.close()">Salir</a>
      </TD>
    </TR>
  </TABLE>

<TABLE  id="mainTable" ALIGN=CENTER class=tableinfo >
<TR> 
    <TD NOWRAP valign="top" width="100%" >
<TABLE id="headTable" >
  <TR id="trdark"> 
    <TH nowrap ALIGN=CENTER rowspan="2"></TH>
    <TH nowrap ALIGN=CENTER colspan="2">Cuenta</TH>
    <TH nowrap ALIGN=CENTER rowspan="2">Producto</TH>
	<TH nowrap ALIGN=CENTER rowspan="2">Mda</TH>
    <TH nowrap ALIGN=CENTER rowspan="2">Principal</TH>
    <TH nowrap ALIGN=CENTER rowspan="2">Oficial</TH>
  </TR>
  <TR id="trdark"> 
    <TH nowrap ALIGN=CENTER>Número</TH>
    <TH nowrap ALIGN=CENTER>Status</TH>
  </TR>
  </TABLE>
  
   <div id="dataDiv1" class="scbarcolor" >
    <table id="dataTable" >
   
  <%
                cifAcc.initRow();
                int k=1;
                while (cifAcc.getNextRow()) {
                    if (cifAcc.getFlag().equals("")) {
                    		out.println(cifAcc.getRecord());
                    		k++;
                    }        
                }
    %> 
   </table>
   </div>
   <!--<TABLE id="tbbotton" width="100%">
     <TR> 
      <TD nowrap align=right width="70%"><b>Total del Principal:</b>
      </TD>
      <TD nowrap align=right><%= Util.fcolorCCY((String)request.getAttribute("Total")) %>
      </TD>
	  <TD nowrap align=right width="14%">
      </TD>
     </TR>
   </TABLE>-->
   </TD>
   <TD nowrap ALIGN="RIGHT" valign="top">
      <Table id="tbAddInfoH" width="100%" >
        <tr id="trdark">
         <TH ALIGN=CENTER nowrap >Información Básica </TH>
        </tr>
      </Table>

     <Table id="tbAddInfo" height="100%">
      <tr id="trclear" >
         <TD  ALIGN="RIGHT"  valign="center" nowrap ></TD>
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
      adjustDifTables(headTable, dataTable, dataDiv1,1,1);
      
      }
     showAddInfo(0);
     resizeDoc();
     tbAddInfoH.rows[0].cells[0].height = headTable.rows[0].cells[0].clientHeight;
     window.onresize=resizeDoc;
</SCRIPT>

<%
  }
%>

  </form>
</body>
</html>
