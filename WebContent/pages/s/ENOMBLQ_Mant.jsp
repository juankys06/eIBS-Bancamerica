<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Mantenimiento Retencion de Cuotas
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "blqList" class= "datapro.eibs.beans.JBList"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="javascript">
function goAction(op) {

    document.forms[0].SCREEN.value = op;
    var formLength= document.forms[0].elements.length;
    var ok = false;
    if(op==2){
    	
    	ok = true;
   		
    }
	    for(n=0;n<formLength;n++)
	    {
	     	var elementName= document.forms[0].elements[n].name;
	     	if(elementName == "ACC") 
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
function Search(op) {

   
    document.forms[0].SCREEN.value = op;
    var ok = true;
    
	
		  if ( ok ) {
	         document.forms[0].submit();
	    }
	    
	 

 }


</script>

</HEAD>

<BODY>

<h3 align="center">Mantenimiento Retencion de Cuotas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="Mant.jsp,JENOMBLQ"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSENOMBLQ" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">


  <TABLE class="tbenter">
    <TR>
      <TD ALIGN=CENTER width="33%">
  			<a href="javascript:goAction(2)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1','','<%=request.getContextPath()%>/images/s/nueva_over.gif',1)"><img name="Image1" border="0" src="<%=request.getContextPath()%>/images/s/new.gif" ></a>
      </TD>
      <TD ALIGN=CENTER width="33%">
  			<a href="javascript:goAction(3)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image2','','<%=request.getContextPath()%>/images/s/maintenance_over.gif',1)"><img name="Image2" border="0" src="<%=request.getContextPath()%>/images/s/maintenance.gif" ></a>
      </TD>
      <TD ALIGN=CENTER width="33%">
  			<a href="javascript:goAction(4)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image3','','<%=request.getContextPath()%>/images/s/delete_over.gif',1)"><img name="Image3" border="0" src="<%=request.getContextPath()%>/images/s/delete.gif" ></a>
      </TD>
   
    </TR>
    
<%
	if (! blqList.getNoResult() ) {
 %>
    <TR>
    <td nowrap align=right> 
	              <div align="right">Buscar Credito : </div>
	            </td>
      <TD ALIGN=CENTER width="25%" align=left>
      	<INPUT type="text" name="ACCAUX"  size="15" maxlength="13" onkeypress="enterInteger()" value="" > 
  			<a href="javascript:Search(1)">
        		<img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" onclick="document.forms[0].search[0].click()"></a> 
	    </TD>
      <TD ALIGN=CENTER width="25%">
  			</TD>
      <TD ALIGN=CENTER width="25%">
  			</TD>
  		<TD ALIGN=CENTER width="25%">
  			</TD>	
   <%   		
	}
	
%>
    </TR>
  </TABLE>
  

<%
	if ( blqList.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=90%>
   		<TR>
      <TD> 
        
      <div align="center"> <font size="3"><b> No existen retenciones de cuenta registradas en el sistema. 
        </b></font> </div>
      </TD></TR>
   		</TABLE>
<%   		
	}
	else {
%>
  
 
  
<TABLE  id="mainTable" class="tableinfo" ALIGN=CENTER >
 <TR> 
    <TD NOWRAP valign="top" width="100%" >
     <TABLE id="headTable" width="100%" >
     <TR id="trdark"> 
     <TH ALIGN=CENTER NOWRAP></TH>
      <TH ALIGN=CENTER NOWRAP>Cuenta</TH>
      <TH ALIGN=CENTER NOWRAP>Credito</TH>
      <TH ALIGN=CENTER NOWRAP>Moneda</TH>
      <TH ALIGN=CENTER NOWRAP>Monto</TH>
      <TH ALIGN=CENTER NOWRAP>Fecha</TH>
  </TR>
    </TABLE>
  
   <div id="dataDiv1" class="scbarcolor" NOWRAP>
    <table id="dataTable" width="100%" >
  <%
                blqList.initRow();
                while (blqList.getNextRow()) {
                    if (blqList.getFlag().equals("")) {
                    		out.println(blqList.getRecord());
                    }
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
      tbAddInfoH.rows[0].cells[0].height = headTable.rows[0].cells[0].clientHeight;
      }
     
     resizeDoc();
     window.onresize=resizeDoc;
     
</SCRIPT>
<BR>
<TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
<%
        if ( blqList.getShowPrev() ) {
      			int pos = blqList.getFirstRec() - 21;
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSENOMBLQ?SCREEN=1&NameSearch=" + blqList.getSearchText() +"&Type=" + blqList.getSearchType() + "&Pos=" + pos + "\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/previous_records.gif\" ></A>");
        }
  %>  
  </TD>
  <TD WIDTH="50%" ALIGN=RIGHT>     
 <%      
        if ( blqList.getShowNext() ) {
      			int pos = blqList.getLastRec();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSENOMBLQ?SCREEN=1&NameSearch=" + blqList.getSearchText() +"&Type=" + blqList.getSearchType() + "&Pos=" + pos + "\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/next_records.gif\" ></A>");
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