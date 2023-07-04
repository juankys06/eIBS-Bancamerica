<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Consulta de Portafolios
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "EWD0302Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"   scope="session"/>

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="javascript">
<!--

  function goAction(op) {

	document.forms[0].opt.value = op;
	document.forms[0].submit();
  
}


function getParams(code, customer, idxRow) {

    document.forms[0].CODE.value = code;
    document.forms[0].CUSTOMER.value = customer;
	showAddInfo(idxRow);
	
}

  
 function goExit(){
    window.location.href="<%=request.getContextPath()%>/pages/background.jsp";
  }

 function extraInfo(textfields,noField) {
 var pos=0
 var s= textfields;
 for ( var i=0; i<noField ; i++ ) {
   pos=textfields.indexOf("<br>",pos+1);
  }
 s=textfields.substring(0,pos);
 return(s);
 }
 
 function showAddInfo(idxRow){
   tbAddInfo.rows[0].cells[1].style.color="white";
   tbAddInfo.rows[0].cells[1].innerHTML=document.forms[0]["TXTDATA"+idxRow].value; // extraInfo(document.forms[0]["TXTDATA"+idxRow].value,7);
   if (tbAddInfo.rows[0].cells[1].filters[0])
   tbAddInfo.rows[0].cells[1].filters[0].apply();
   tbAddInfo.rows[0].cells[1].style.color="";
   if (tbAddInfo.rows[0].cells[1].filters[0])
   tbAddInfo.rows[0].cells[1].filters[0].Play();
   for ( var i=0; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idxRow].className="trhighlight";
   adjustEquTables(headTable, dataTable, dataDiv1,1,false);
  }
//-->
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

<BODY onload="MM_preloadImages('<%=request.getContextPath()%>/images/e/approve_over.gif','<%=request.getContextPath()%>/images/e/reject_over.gif')">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEWD0302" >
  <input type=HIDDEN name="SCREEN" value="2">
  <input type=HIDDEN name="opt" value="1">
  <input type=HIDDEN name="totalRow" value="0">
  <input type=HIDDEN name="CODE">
  <input type=HIDDEN name="CUSTOMER">
  <h3 align="center">Consulta de Portafolios<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="approval_list.jsp,EIE0140"> 
  </h3>
<hr size="4">
  <p><%
	if ( EWD0302Help.getNoResult() ) {
 %></p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <table class="tbenter" width="100%" >
    <tr> 
      <td > 
        <div align="center"> 
          <p><b>No hay resultados para su criterio de búsqueda</b></p>
        </div>
      </td>
    </tr>
  </table>
  <%  
		}
	else {
%> <% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%>
<table class="tbenter" width=100% align=center>
    <tr> 
      <td class=TDBKG width="30%"> 
        <div align="center"><a href="javascript:goAction(4)"><b>Consulta</b></a></div>
      </td>
      <td class=TDBKG width="30%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
<br><TABLE  id="mainTable" class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" width="100%" >
  <TABLE id="headTable" >
  <TR id="trdark"> 
      <TH ALIGN=CENTER NOWRAP> </TH>
            <TH ALIGN=CENTER NOWRAP>Cliente</TH>
            <TH ALIGN=CENTER NOWRAP>Número de <br>
              Portafolio</TH>
            <TH ALIGN=CENTER NOWRAP>Descripción</TH>
            <TH ALIGN=CENTER NOWRAP>Tipo de <br>
              Portafolio</TH>
            <TH ALIGN=CENTER NOWRAP>Fecha de <br>
              Apertura</TH>
            <TH ALIGN=CENTER NOWRAP>Moneda</TH>
    </TR>
    </TABLE>
  
   <div id="dataDiv1" class="scbarcolor" >
    <table id="dataTable" >
    <%
                EWD0302Help.initRow();
                int k=1;
                while (EWD0302Help.getNextRow()) {
                    if (EWD0302Help.getFlag().equals("")) {
                    		out.println(EWD0302Help.getRecord());
                    k++;
                    }        
                }
    %> 
   </table>
   </div>
   </TD>
   <TD nowrap ALIGN="RIGHT" valign="top">
      <Table id="tbAddInfoH"   width="100%" >
        <tr id="trdark">
         <TH ALIGN=CENTER nowrap >Información Básica </TH>
        </tr>
      </Table>

     <Table id="tbAddInfo"  >
      <tr id="trclear" >
            <TD  ALIGN="RIGHT" VALIGN="TOP" nowrap > 
              <p><b>
				Código de Oficial : <br>
				Nombre de Oficial : <br><br>
                Nombre Legal : 
				</b>
			  </p>
            </TD>
         <TD ALIGN="LEFT"  class="tdaddinfo"></TD>
      </tr>
     </Table>
  </TD>
  </TR>	
</TABLE>

  <table class="tbenter" width="98%" align=CENTER>
    <tr> 
      <td width="50%" align=LEFT> <%
        if ( EWD0302Help.getShowPrev() ) {
      			int pos = EWD0302Help.getFirstRec() - 51;
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.invest.JSEWD0302?SCREEN=1&Pos=" + pos +"\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/e/previous_records.gif\" ></A>");
        }
   %> </td>
      <td width="50%" align=RIGHT> <% 
        if ( EWD0302Help.getShowNext() ) {
      			int pos = EWD0302Help.getLastRec();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.invest.JSEWD0302?SCREEN=1&Pos=" + pos +"\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/e/next_records.gif\" ></A>");
        }
  %> </td>
    </tr>
  </table>
  <p>
    <SCRIPT language="JavaScript">
     document.forms[0].totalRow.value="<%= k %>";
     function resizeDoc() {
       divResize(true);
     adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      }
     showChecked("PORTNUM");
     resizeDoc();
     tbAddInfoH.rows[0].cells[0].height = headTable.rows[0].cells[0].clientHeight;
     window.onresize=resizeDoc;
     
</SCRIPT>
    <%}%> </p>
  </FORM>

</BODY>
</HTML>
