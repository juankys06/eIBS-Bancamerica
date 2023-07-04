<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Productos Vigentes</title>
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
	 var pg = "";
	 var acc = ""
	 for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "ACCNUM") 
      	{
				if (document.forms[0].elements[n].checked == true) {
					acc = document.forms[0].elements[n].value;
        			ok = true;
        			break;
				}
      	}
      }
	  if ( ok ) {
		  
		  pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF010?SCREEN=10&ACCNUM=" + acc +"&opt=" + op; 
		  CenterWindow(pg,600,500,2);

			
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

</SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
builtNewMenu(cr_i_opt);
initMenu();
</SCRIPT>
</head>

<BODY onload="MM_preloadImages('<%=request.getContextPath()%>/images/s/exit_over.gif','<%=request.getContextPath()%>/images/s/inquiry_over.gif','<%=request.getContextPath()%>/images/s/average_over.gif','<%=request.getContextPath()%>/images/s/statement_account_over.gif')">

<%@ page import="datapro.eibs.master.Util" %>
<h3 align="center">Productos Vigentes<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cif_accounts.jsp,ECIF010"></h3>
<hr size="4">
  <form method="post" >
    <INPUT TYPE=HIDDEN NAME="SCREEN" value="10">
	 <INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
	 <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
  <table class="tableinfo" width="102%">
    <tr > 
      <td nowrap > 
        <table cellspacing="0" cellpadding="2" width="100%" class="tbhead"   align="center">
          <tr> 
            <td nowrap width="9%" align="right"> Cliente : </td>
            <td nowrap width="15%" align="left"> <%= userPO.getCusNum()%> 
              <input type="hidden" name="E01CUN" value="<%= userPO.getCusNum()%>">
            </td>
            <td nowrap width="11%" align="right"> 
              <div align="right">RUT : </div>
            </td>
            <td nowrap width="10%" align="left"> <%= userPO.getID()%> </td>
            <td nowrap width="14%" align="right"> 
              <div align="right">Nombre: </div>
            </td>
            <td nowrap width="41%"align="left"> <%= userPO.getCusName()%> </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
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
      <TD ALIGN=CENTER width="20%">
  			<a href="javascript:goAction(1)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1','','<%=request.getContextPath()%>/images/s/inquiry_over.gif',1)"><img name="Image1" border="0" src="<%=request.getContextPath()%>/images/s/INQUIRY.gif" ></a>
      </TD>
      <TD ALIGN=CENTER width="20%">
  			<a href="javascript:goAction(3)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image3','','<%=request.getContextPath()%>/images/s/average_over.gif',1)"><img name="Image3" border="0" src="<%=request.getContextPath()%>/images/s/AVERAGE.gif" ></a>
      </TD>
      <TD ALIGN=CENTER width="20%">
  			<a href="<%=request.getContextPath()%>/pages/background.jsp"  onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image5','','<%=request.getContextPath()%>/images/s/exit_over.gif',1)"><img name="Image5" border="0" src="<%=request.getContextPath()%>/images/s/EXIT.gif" ></a>
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
