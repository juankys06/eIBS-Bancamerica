<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
 
<%@ page import ="datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Lista de Domiciliaciones - Pago y Cobro de Servicios
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "mtList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<SCRIPT language="javascript" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<SCRIPT language="javascript">
  
  function goAction(opt) {
    
    if (opt == "D") {
      ok = confirm("Esta seguro que desea eliminar el registro seleccionado?");
	  if (ok) document.forms[0].submit();
	} else if (opt == "M") { 
		var row = document.forms[0].CURRENTROW.value;   
    	page = "<%=request.getContextPath()%>/pages/s/SRV0010_services_domici_maint.jsp?ROW=" + row;
  		CenterNamedWindow(page,'Information',550,450,2);
  	} else {
  	    page = "<%=request.getContextPath()%>/pages/s/SRV0010_services_domici_new.jsp";
  		CenterNamedWindow(page,'Information',550,450,2);
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


<h3 align="center">Lista de Domiciliaciones - Pago y Cobro de Servicios
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="services_domici_list.jsp,SRV0010">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.services.JSSRV0010" >

<%
	if ( mtList.getNoResult() ) {
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
            <b>No existen registros de domiciliaciones. Haga click en la opcion "Nuevo" para crear un registro </b>
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
 <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
 
 <TABLE cellspacing=0 cellpadding=2 width="100%" border="0">    
		<TR>
			<TD nowrap width="40%">
				<DIV align="right">N&uacute;mero de Cuenta :</DIV>
			</TD>
			<TD nowrap width="60%">
				<INPUT type="text" name="E01DOMACC" size="15" maxlength="12" value="<%= userPO.getHeader16() %>" readonly> 
			</TD>
		</TR>
		<TR>
			<TD nowrap width="40%">
				<DIV align="right">Nombre del Cliente :</DIV>
			</TD>
			<TD nowrap width="60%">
				<INPUT type="text" name="E01CUSNA1" size="40" maxlength="35" value="<%= userPO.getHeader17() %>" readonly>  
			</TD>
		</TR>
 </TABLE>
    
 <TABLE class="tbenter" width="100%" align=center>
    <TR> 
      <TD class=TDBKG width="25%"> 
        <div align="center"><a href="javascript:goAction('N')">Nuevo</a> 
        </div>
      </TD>
      <TD class=TDBKG width="25%"> 
        <div align="center"><a href="javascript:goAction('M')">Mantenimiento</a> 
        </div>
      </TD>
      <TD class=TDBKG width="25%"> 
        <div align="center"><a href="javascript:goAction('D')">Borrar</a> 
        </div>
      </TD>
      <TD class=TDBKG width="25%"> 
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
    		<TH ALIGN=CENTER >Codigo de<BR>Compañia</TH>
    		<TH ALIGN=CENTER >Codigo de<BR>Servicio</TH>
    		<TH ALIGN=CENTER >Codigo de<BR>Central</TH>
    		<TH ALIGN=CENTER >Referencia de<BR>Servicio</TH>
    		<TH ALIGN=CENTER >Monto Maximo<BR>Autorizado</TH>
    		<TH ALIGN=CENTER >Fecha Desde</TH>
  			<TH ALIGN=CENTER >Fecha Hasta</TH>
  			<TH ALIGN=CENTER >Fecha<BR>Ultima<BR>Actualizacion</TH>
    		<TH ALIGN=CENTER >Usuario<BR>Ultima<BR>Actualizacion</TH> 
  		</TR>
     <%
                mtList.initRow(); 
                chk = "checked";               
                while (mtList.getNextRow()) {
                   datapro.eibs.beans.SRV001001Message msgMT = (datapro.eibs.beans.SRV001001Message) mtList.getRecord();
     %>               
        <TR>
			<TD NOWRAP width="2%"><input type="radio" name="ROW" value="<%= mtList.getCurrentRow()%>" <%=chk%> onClick="document.forms[0].CURRENTROW.value = this.value;"></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE01DOMCIA())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE01DOMSRV())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE01DOMCEN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE01DOMCTA())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE01DOMMAX())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatDate(msgMT.getE01DOMFD1(),msgMT.getE01DOMFD2(),msgMT.getE01DOMFD3()) %></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatDate(msgMT.getE01DOMTD1(),msgMT.getE01DOMTD2(),msgMT.getE01DOMTD3()) %></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatDate(msgMT.getE01DOMUD1(),msgMT.getE01DOMUD2(),msgMT.getE01DOMUD3())  + " - " + Util.formatTime(msgMT.getE01DOMUPT())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE01DOMUPU())%></TD>
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
