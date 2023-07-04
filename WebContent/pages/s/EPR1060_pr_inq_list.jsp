<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Lista de Transferencias
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBList"   scope="session"/>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"   scope="session"/>

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="javascript">
 function sendClick(refnum) {
   var formLength= document.forms[0].elements.length;
   for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	var elementValue= document.forms[0].elements[n].value;
      	if(elementName == "PRINUM" && elementValue== refnum) 
      	{
        		document.forms[0].elements[n].click();
        		break;
      	}
      }
 }

 function goAction(refnum) {
     if ( refnum !== "") {
		  sendClick(refnum);
     }
     page = webapp + "/servlet/datapro.eibs.products.JSEPR1060?SCREEN=3&REFNUM="+document.forms[0].REFNUM.value;
	 CenterWindow(page,600,500,2);
  }

 function goExit(){
    window.location.href="<%=request.getContextPath()%>/pages/background.jsp";
  }
 
 function showAddInfo(refernum,idxRow){

   tbAddInfo.rows[0].cells[1].style.color="white";
   tbAddInfo.rows[0].cells[1].innerHTML=document.forms[0]["TXTDATA"+idxRow].value;
   if (tbAddInfo.rows[0].cells[1].filters[0])
   		tbAddInfo.rows[0].cells[1].filters[0].apply();
   tbAddInfo.rows[0].cells[1].style.color="";
   if (tbAddInfo.rows[0].cells[1].filters[0])
   		tbAddInfo.rows[0].cells[1].filters[0].Play();
   for(var i=0;i<dataTable.rows.length;i++ ){
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idxRow].className="trhighlight";
   document.forms[0].REFNUM.value = refernum;
   adjustEquTables(headTable, dataTable, dataDiv1,1,false);
   
  }
  
 function enter(recpos){ 	
 	document.forms[0].Pos.value = "" + recpos;
 	document.forms[0].submit();
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

<BODY onload="MM_preloadImages('<%=request.getContextPath()%>/images/s/inquiry_over.gif')">
<h3 align="center"> Consulta de Transferencias<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="pr_inq_list.jsp,EPR1060"> 
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPR1060">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="REFNUM" VALUE="">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
<INPUT TYPE=HIDDEN NAME="Pos" VALUE="0">
  <TABLE class="tbenter">
    <TR>
      <TD class=TDBKG width="50%"> 
        <div align="center" style="cursor:hand" onClick="goAction('')"><a><b>Consulta</b></a></div>
      </TD>      
      <TD class=TDBKG width="50%"> 
        <div align="center" style="cursor:hand" onClick="goExit()"><a><b>Salir</b></a></div>
      </TD>       
    </TR>
  </TABLE>
  
  <TABLE  id="mainTable" class="tableinfo" width="50%">
    <TR > 
    <TD NOWRAP valign="top" width="100%" >
        <TABLE id="headTable" width="354" >
          <TR id="trdark"> 
            <TH ALIGN=CENTER NOWRAP></TH>
            <TH ALIGN=CENTER NOWRAP>N&uacute;mero de <BR>
				Referencia</TH>
            <TH ALIGN=CENTER NOWRAP>Referencia <BR>
				Original</TH>            
            <TH ALIGN=CENTER NOWRAP>Mda</TH>				
            <TH ALIGN=CENTER NOWRAP>Monto</TH>
            <TH ALIGN=CENTER NOWRAP>Estado</TH>
            <TH ALIGN=CENTER NOWRAP>Fecha<BR>Valor
				</TH>
            <TH ALIGN=CENTER NOWRAP>Tipo<BR>Transacción
				</TH>
          </TR>
        </TABLE>
  
   <div id="dataDiv1" class="scbarcolor">              
    <table id="dataTable">
    <%
                appList.initRow();
                int k=1;
                while (appList.getNextRow()) {
                    if (appList.getFlag().equals("")) {
                    		out.println(appList.getRecord());
                    k++;
                    }        
                }
    %> 
   </table>
   </div>
   </TD>
   <TD nowrap ALIGN="RIGHT" valign="top">
      <Table id="tbAddInfoH" width="100%" >
        <tr id="trdark">
            <TH ALIGN=CENTER nowrap >Informaci&oacute;n B&aacute;sica</TH>
        </tr>
      </Table>

     <Table id="tbAddInfo" >
      <tr id="trclear" >
            <TD  ALIGN="RIGHT"  valign="center" nowrap ><b>Cuenta de D&eacute;bito: 
              <br>
              Cuenta de Cr&eacute;dito: <br>
              Por Orden : <br>
              Beneficiario : <br>
              Banco Beneficiario :<br>
              Instituci&oacute;n Ordenante : </b></TD>
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
       adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      }
	 showChecked("PRINUM");
     resizeDoc();
     tbAddInfoH.rows[0].cells[0].height = headTable.rows[0].cells[0].clientHeight;
     window.onresize=resizeDoc;
     
</SCRIPT>

<TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
<%
        if ( appList.getShowPrev() ) {
      			int pos = appList.getFirstRec() - 21;
      			out.println("<A HREF=\"javascript:enter(" + pos + ")\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/previous_records.gif\" ></A>");
        }
  %>  
  </TD>
  <TD WIDTH="50%" ALIGN=RIGHT>     
 <%      
        if ( appList.getShowNext() ) {
      			int pos = appList.getLastRec();
      			out.println("<A HREF=\"javascript:enter(" + pos + ")\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/next_records.gif\" ></A>");
        }
   %> 
   </TD>
 </TR>
</TABLE>

</FORM>

</BODY>
</HTML>
