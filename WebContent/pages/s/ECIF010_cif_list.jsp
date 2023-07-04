<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Lista de Cliente
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "cifList" class= "datapro.eibs.beans.JBList"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="javascript">
  function goAction(op) {

     document.forms[0].opt.value = op;
     var formLength= document.forms[0].elements.length;
     var ok = false;
	 var cun = "";
	 var pg = "";
     for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "CUSTOMER") 
      	{
				if (document.forms[0].elements[n].checked == true) {
					cun = document.forms[0].elements[n].value;
        			ok = true;
        			break;
				}
      	}
      }
      if ( ok ) {
          if (op == 7) {
		pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=600&E01CUN=" + cun;
//		 window.location.href = pg;
	   }
	   else {
	  	pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF010?SCREEN=4&opt=" + op + "&CUSTOMER=" + cun;
	   }
	   CenterWindow(pg,600,500,2);

     }
     else {
			alert("Please select an account number to continue.");	   
     }

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
   adjustEquTables(headTable, dataTable, dataDiv1,1,false);
}    
</script>

</HEAD>

<BODY>

<h3 align="center">Clientes<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cif_list.jsp,ECIF010"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF010" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
<INPUT TYPE=HIDDEN NAME="opt" VALUE="1">

<%
	if ( cifList.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=90%>
   		<TR>
      <TD> 
        
      <div align="center"> <font size="3"><b> No hay resultado que corresponda a su criterio de búsqueda. 
        </b></font> </div>
      </TD></TR>
   		</TABLE>
<%   		
	}
	else {
%>
  
  <TABLE class="tbenter" width="100%">
    <TR> 
      <TD ALIGN=CENTER class=TDBKG> <a href="javascript:goAction(7)" ><b>Consulta</b></a> 
      </TD>
      <TD ALIGN=CENTER class=TDBKG> <a href="javascript:goAction(1)" ><b> Totales</b></a> 
      </TD>
      <TD ALIGN=CENTER class=TDBKG> <a href="javascript:goAction(2)" ><b>Posicion</b></a> 
      </TD>
      <TD ALIGN=CENTER class=TDBKG> <a href="javascript:goAction(3)" ><b>Consulta 
        <br>
        Gerencial</b></a> </TD>
      <TD ALIGN=CENTER class=TDBKG> <a href="javascript:goAction(4)" ><b>Lista 
        de <br>
        Cuentas</b></a> </TD>
      <TD ALIGN=CENTER class=TDBKG> <a href="javascript:goAction(5)" ><b>Lineas 
        de <br>
        Credito</b></a> </TD>
      <TD ALIGN=CENTER class=TDBKG> <a href="javascript:goAction(6)" ><b>Garantias</b></a> 
      </TD>
      <TD ALIGN=CENTER class=TDBKG><a href="javascript:goAction(9)"><b>Rentabilidad</b></a></TD>
      <TD ALIGN=CENTER class=TDBKG> <a href="<%=request.getContextPath()%>/pages/background.jsp" ><b>Salir</b></a> 
      </TD>
    </TR>
  </TABLE>
        
  
<TABLE  id="mainTable" class="tableinfo" ALIGN=CENTER >
 <TR> 
    <TD NOWRAP valign="top" width="100%" >
     <TABLE id="headTable" >
     <TR id="trdark"> 
      <TH ALIGN=CENTER NOWRAP></TH>
      <TH ALIGN=CENTER NOWRAP>Número</TH>
      <TH ALIGN=CENTER NOWRAP>Nombre Legal</TH>
      <TH ALIGN=CENTER NOWRAP>Nombre Corto</TH>
  </TR>
    </TABLE>
  
   <div id="dataDiv1" class="scbarcolor" NOWRAP>
    <table id="dataTable" >
  <%
                cifList.initRow();
                while (cifList.getNextRow()) {
                    if (cifList.getFlag().equals("")) {
                    		out.println(cifList.getRecord());
                    }
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

     <Table id="tbAddInfo" width="100%">
      <tr id="trclear" >
         <TD  ALIGN="RIGHT"  valign="center" nowrap ><b>Número Ident. : <br>Tipo Ident. : <br>País : <br>Status : <br>Tipo : </b></TD>
         <TD ALIGN="LEFT" valign="center" nowrap class="tdaddinfo"></TD>
      </tr>
     </Table>
  </TD>
  </TR>	
</TABLE>

<SCRIPT language="JavaScript">
     function resizeDoc() {
      adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      tbAddInfoH.rows[0].cells[0].height = headTable.rows[0].cells[0].clientHeight;
      }
      showAddInfo(0);
     resizeDoc();
     window.onresize=resizeDoc;
     
</SCRIPT>
<BR>
<TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
<%
        if ( cifList.getShowPrev() ) {
      			int pos = cifList.getFirstRec() - 13;
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.client.JSECIF010?SCREEN=3&NameSearch=" + cifList.getSearchText() +"&Type=" + cifList.getSearchType() + "&Pos=" + pos + "\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/previous_records.gif\" ></A>");
        }
  %>  
  </TD>
  <TD WIDTH="50%" ALIGN=RIGHT>     
 <%      
        if ( cifList.getShowNext() ) {
      			int pos = cifList.getLastRec();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.client.JSECIF010?SCREEN=3&NameSearch=" + cifList.getSearchText() +"&Type=" + cifList.getSearchType() + "&Pos=" + pos + "\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/next_records.gif\" ></A>");
        }
   %> 
   </TD>
 	</TR>
 	</TABLE>
<%           
  }
%>

</FORM>

</BODY>
</HTML>
