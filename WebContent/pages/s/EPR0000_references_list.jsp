<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
 
<%@ page import ="datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Detalle de Transferencias Multiples
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "mtListRef" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<SCRIPT language="javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT language="javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT language="javascript">

	builtNewMenu(pr_m_opt);
	initMenu();
  
  function goAction(opt) {
    
    if (opt == "D") {
      ok = confirm("¿Esta seguro que desea eliminar el registro seleccionado?");
	  if (ok) document.forms[0].submit();
	} else if (opt == "M") { 
		document.forms[0].SCREEN.value = "200";
		document.forms[0].submit();
  	} else if (opt == "N") { 
		document.forms[0].SCREEN.value = "200";  
		document.forms[0].PURPOSE.value = "NEW";   
		document.forms[0].submit();
  	} else if (opt == "V") { 
      	ok = confirm("¿Esta seguro que desea devolver el registro seleccionado?");
		document.forms[0].SCREEN.value = "202";
		document.forms[0].PURPOSE.value = "DEVOLVER";   
	  	if (ok) document.forms[0].submit();
  	} else if (opt == "B") { 
      	ok = confirm("¿Esta seguro que desea devolver el registro seleccionado?");
		document.forms[0].SCREEN.value = "202";
		document.forms[0].PURPOSE.value = "RETURN";   
	  	if (ok) document.forms[0].submit();
  	} 
  }
  
</SCRIPT>

</HEAD>

<BODY>

<% 
 String chk = "";
 	
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors();");
     out.println("</SCRIPT>");
 } 
%>


<h3 align="center">Detalle de Transferencias M&uacute;ltiples
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="references_list.jsp,EPR0000">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPR0000" >
 <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0"> 
 <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="202">
 <input type="hidden" name="PURPOSE" value="">
<%
	if ( mtListRef.getNoResult() ) {
%> 

  <TABLE class="tbenter" width="60%" align=center>
    <TR>
     <TD class=TDBKG width="50%"> 
        <div align="center"><a href="javascript:goAction('N')">Nuevo</a> 
        </div>
      </TD> 
      <TD class=TDBKG width="50%"> 
        <div align="center"><a href="javascript:checkClose()">Salir</a> </div>
      </TD>
    </TR>
  </TABLE>
  <TABLE class="tbenter" width=100% height=40%>
   	<TR>
      <TD> 
        <div align="center"> 
          <p>
            <b>No existen registros. </b>
          </p>          
        </div>
      </TD>
     </TR>
   </TABLE>
  <%   		
	}
	else { 
	
  %>
     
 
 
 <TABLE cellspacing=0 cellpadding=2 width="100%" border="0">    
		<TR>
			<TD nowrap width="40%">
				<DIV align="right">N&uacute;mero de Transferencia :</DIV>
			</TD>
			<TD nowrap width="60%">
				<INPUT type="text" name="E02PRMNUM" size="10" maxlength="9" value="<%= userPO.getHeader16() %>" readonly> 
			</TD>
		</TR>
		<TR>
			<TD nowrap width="40%">
				<DIV align="right">Formato SWIFT :</DIV>
			</TD>
			<TD nowrap width="60%">
				<INPUT type="text" name="E02PRIFMT" size="6" maxlength="5" value="<%= userPO.getHeader17() %>" readonly>  
			</TD>
		</TR>
 </TABLE>
    
 <TABLE class="tbenter" width="100%" align=center>
    <TR> 
      <TD class=TDBKG width="15%"> 
        <div align="center"><a href="javascript:goAction('N')">Nuevo</a> 
        </div>
      </TD>
      <TD class=TDBKG width="15%"> 
        <div align="center"><a href="javascript:goAction('M')">Mantenimiento</a> 
        </div>
      </TD>
      <TD class=TDBKG width="20%"> 
        <div align="center"><a href="javascript:goAction('V')">Devolver por Ordenante</a> 
        </div>
      </TD>
      <TD class=TDBKG width="20%"> 
        <div align="center"><a href="javascript:goAction('B')">Devolver por Beneficiario</a> 
        </div>
      </TD>
      <TD class=TDBKG width="15%"> 
        <div align="center"><a href="javascript:goAction('D')">Borrar</a> 
        </div>
      </TD>
      <TD class=TDBKG width="15%"> 
        <div align="center"><a href="javascript:checkClose()">Salir</a> </div>
      </TD>
    </TR>
 </TABLE>
  
 <TABLE  id="mainTable" class="tableinfo" >
  <TR> 
    <TD NOWRAP valign=top>
  	 <TABLE id="dataTable" width="100%">
  		<TR id="trdark"> 
  			<TH ALIGN=CENTER ></TH>
    		<TH ALIGN=CENTER >Secuencia</TH>
    		<TH ALIGN=CENTER >Referencia Relacionada</TH>
    		<TH ALIGN=CENTER >ERR</TH>
    		<TH ALIGN=CENTER >Moneda</TH>
    		<TH ALIGN=CENTER >Monto</TH>
    		<TH ALIGN=CENTER >Beneficiario</TH>  
  		</TR>
     <%
                mtListRef.initRow(); 
                chk = "checked";               
                while (mtListRef.getNextRow()) {
                   datapro.eibs.beans.EPR010002Message msgMTRef = (datapro.eibs.beans.EPR010002Message) mtListRef.getRecord();
     %>               
        <TR>
			<TD NOWRAP width="2%"><input type="radio" name="ROW" value="<%= mtListRef.getCurrentRow()%>" <%=chk%> onClick="document.forms[0].CURRENTROW.value = this.value;"></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMTRef.getE02PRMSQN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMTRef.getE02PRMRRF())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMTRef.getE02PRMERR())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMTRef.getE02PRMCCY())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMTRef.getE02PRMAMT())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMTRef.getE02PRMBC1())%></TD>
		</TR>    		
    <%             
    				chk = "";     
                }
    %>    
     </TABLE>
    </TD>
   </TR>	
</TABLE>

<SCRIPT language="JavaScript">
  showChecked("ROW");       
</SCRIPT>

<TABLE  class="tbenter" WIDTH="88%" ALIGN=CENTER>
    <TR>
      	<TD WIDTH="50%" ALIGN=LEFT height="25"> <%
        if ( mtListRef.getShowPrev() ) {
      			int pos =mtListRef.getFirstRec() - 21;
      			   out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.products.JSEPR0000?FromRecord=" + pos + "\" > <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
%> 	  	</TD>
 	  	<TD WIDTH="50%" ALIGN=RIGHT height="25"> <%       
        if ( mtListRef.getShowNext() ) {
      			int pos = mtListRef.getLastRec();
      			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.products.JSEPR0000?FromRecord=" + pos + "\" ><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");

        }
%> 		</TD>
	</TR>
</TABLE>

<%   		
	} 
 %>
</FORM>

</BODY>
</HTML>

