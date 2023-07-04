<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
 
<%@ page import ="datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Lista de Cuentas Jur&iacute;dicas Relacionadas Sistema de Bastanteo
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "mtListAcc" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<SCRIPT language="javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT language="javascript">
  
  function goAction(opt) {
    
    if (opt == "D") {
      ok = confirm("Esta seguro que desea eliminar el registro seleccionado?");
	  if (ok) document.forms[0].submit();
	} else if (opt == "M") { 
		document.forms[0].SCREEN.value = 31;   
		document.forms[0].submit();
  	} else if (opt == "R") {   
    	page = "<%=request.getContextPath()%>/pages/s/ECU0000_bastanteo_customers_maint.jsp?ROW=" + <%= userPO.getHeader4()%>;
  		CenterNamedWindow(page,'Information',650,500,2);
  	} else {
  	    page = "<%=request.getContextPath()%>/pages/s/ECU0000_bastanteo_accounts_new.jsp";
  		CenterNamedWindow(page,'Information',650,500,2);
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


<h3 align="center">Sistema de Bastanteo - Lista de Cuentas Jur&iacute;dicas Relacionadas
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bastanteo_accounts_list.jsp,ECU0000">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000" >

<%
	if ( mtListAcc.getNoResult() ) {
%> 

  <TABLE class="tbenter" width="60%" align=center>
    <TR> 
      <TD class=TDBKG width="33%"> 
        <div align="center"><a href="javascript:goAction('N')">Nuevo</a> 
        </div>
      </TD>
      <TD class=TDBKG width="33%"> 
        <div align="center"><a href="javascript:goAction('R')">Regresar</a> 
        </div>
      </TD>
      <TD class=TDBKG width="33%"> 
        <div align="center"><a href="javascript:checkClose()">Salir</a> </div>
      </TD>
    </TR>
  </TABLE>
  <TABLE class="tbenter" width=100% height=40%>
   	<TR>
      <TD> 
        <div align="center"> 
          <p>
            <b>No existen registros de cuentas jurídicas. Haga click en la opcion "Nuevo" para crear un registro. </b>
          </p>          
        </div>
      </TD>
     </TR>
   </TABLE>
  <%   		
	}
	else { 
	
  %>
     
 <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0"> 
 <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="32">
 
 <TABLE cellspacing=0 cellpadding=2 width="100%" border="0">    
		<TR>
			<TD nowrap width="40%">
				<DIV align="right">C&oacute;digo de Cliente :</DIV>
			</TD>
			<TD nowrap width="60%">
				<INPUT type="text" name="E03CRLCUN" size="10" maxlength="9" value="<%= userPO.getHeader16() %>" readonly> 
			</TD>
		</TR>
		<TR>
			<TD nowrap width="40%">
				<DIV align="right">Nombre o Denominaci&oacute;n Social :</DIV>
			</TD>
			<TD nowrap width="60%">
				<INPUT type="text" name="E03CUSNA1" size="40" maxlength="35" value="<%= userPO.getHeader17() %>" readonly>  
			</TD>
		</TR>
 </TABLE>
    
 <TABLE class="tbenter" width="100%" align=center>
    <TR> 
      <TD class=TDBKG width="20%"> 
        <div align="center"><a href="javascript:goAction('N')">Nuevo</a> 
        </div>
      </TD>
      <TD class=TDBKG width="20%"> 
        <div align="center"><a href="javascript:goAction('M')">Mantenimiento</a> 
        </div>
      </TD>
      <TD class=TDBKG width="20%"> 
        <div align="center"><a href="javascript:goAction('D')">Borrar</a> 
        </div>
      </TD>
      <TD class=TDBKG width="20%"> 
        <div align="center"><a href="javascript:goAction('R')">Regresar</a> 
        </div>
      </TD>
      <TD class=TDBKG width="20%"> 
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
    		<TH ALIGN=CENTER >Tipo de Cuenta</TH>
    		<TH ALIGN=CENTER >Numero de Cuenta</TH>
    		<TH ALIGN=CENTER >Fecha Ultima<BR>Actualizacion</TH>
    		<TH ALIGN=CENTER >Usuario Ultima<BR>Actualizacion</TH> 
  		</TR>
     <%
                mtListAcc.initRow(); 
                chk = "checked";               
                while (mtListAcc.getNextRow()) {
                   datapro.eibs.beans.ECU000003Message msgMTAcc = (datapro.eibs.beans.ECU000003Message) mtListAcc.getRecord();
     %>               
        <TR>
			<TD NOWRAP width="2%"><input type="radio" name="ROW" value="<%= mtListAcc.getCurrentRow()%>" <%=chk%> onClick="document.forms[0].CURRENTROW.value = this.value;"></TD>
			<TD NOWRAP ALIGN="LEFT"><%=Util.formatCell(msgMTAcc.getE03CRLTYN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMTAcc.getE03CRLACC())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatDate(msgMTAcc.getE03CRLAC1(),msgMTAcc.getE03CRLAC2(),msgMTAcc.getE03CRLAC3())  + " - " + Util.formatTime(msgMTAcc.getE03CRLACT())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMTAcc.getE03CRLACU())%></TD>
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

<%   		
	} 
 %>
</FORM>

</BODY>
</HTML>
