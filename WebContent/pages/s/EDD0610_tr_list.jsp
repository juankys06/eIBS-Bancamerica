<%


%>
<HTML>
<HEAD>
<TITLE>
Lista de Clientes
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>

<jsp:useBean id= "transList" class= "datapro.eibs.beans.JBList"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="javascript">
  function goAction(op) {

     document.forms[0].opt.value = op;
     var formLength= document.forms[0].elements.length;
     var ok = false;
     for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "CUSTOMER") 
      	{
        		ok = true;
        		break;
      	}
      }
	  if ( ok ) {
          document.forms[0].submit();
     }
     else {
			alert("Seleccione un cliente antes de realizar esta operación");	   
     }

  }
</script>

<BODY>
<h3 align="center">Transferencias<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="EDD0610_tr_list.jsp"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF010" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
<INPUT TYPE=HIDDEN NAME="opt" VALUE="1">

<%
	if ( transList.getNoResult() ) {
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
  
  <TABLE class="tableinfo">
    <TR id="trdark"> 
      <TH ALIGN=CENTER width="15%" >No Transf.</TH>
      <TH ALIGN=CENTER width="20%" >Situacion Transf.</TH>
      <TH ALIGN=CENTER width="32%" >Beneficiario</TH>
      <TH ALIGN=CENTER width="16%" >Fecha Valor</TH>
      <TH ALIGN=CENTER width="17%" >Monto</TH>
    </TR>
    <%
                transList.initRow();
                while (transList.getNextRow()) {
                    if (transList.getFlag().equals("")) {
                    		out.println(transList.getRecord());
                    }
                }
    %> 
  </TABLE>
  
  <BR>
  
  <TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
  <%
  	      if ( transList.getShowPrev() ) {                                                                                                                        
      			int pos = transList.getFirstRec() - 21;
      		   out.println(" <A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.transfer.JSEDD0610?SCREEN=3&Type=" + transList.getSearchType() + "&Pos=" + pos + "\"> <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
  %> 
 	</TD>
 	<TD WIDTH="50%" ALIGN=RIGHT>
  <%
        if ( transList.getShowNext() ) {
      			int pos = transList.getLastRec();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.transfer.JSEDD0610?SCREEN=3&Type=" + transList.getSearchType() + "&Pos=" + pos + "\"><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0> </A>");
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
