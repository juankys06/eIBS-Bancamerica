<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
 
<%@ page import ="datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Lista de Balances - Pago y Cobro de Servicios
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
    	page = "<%=request.getContextPath()%>/pages/s/SRV0030_services_balance_maint.jsp?ROW=" + row;
  		CenterNamedWindow(page,'Information',550,450,2);
  	} else {
  	    page = "<%=request.getContextPath()%>/pages/s/SRV0030_services_balance_new.jsp";
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


<h3 align="center">Lista de Balances - Pago y Cobro de Servicios
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="services_balance_list.jsp,SRV0030">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.services.JSSRV0030" >

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
            <b>No existen registros de balances. Haga click en la opcion "Nuevo" para crear un registro </b>
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
				<DIV align="right">Compañ&iacute;a :</DIV>
			</TD>
			<TD nowrap width="60%">
				<INPUT type="text" name="E01BALCIA" size="5" maxlength="4" value="<%= userPO.getHeader16() %>" readonly>
				<INPUT type="text" name="E01BALCIN" size="40" maxlength="35" value="<%= userPO.getHeader17() %>" readonly> 
			</TD>
		</TR>
		<TR>
			<TD nowrap width="40%">
				<DIV align="right">Servicio :</DIV>
			</TD>
			<TD nowrap width="60%">
				<INPUT type="text" name="E01BALSRV" size="5" maxlength="4" value="<%= userPO.getHeader18() %>" readonly>
				<INPUT type="text" name="E01BALSRN" size="40" maxlength="35" value="<%= userPO.getHeader19() %>" readonly>  
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
        <div align="center"><a href="javascript:goAction('M')">Consulta</a> 
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
  			<TH ALIGN=CENTER >Secuencia</TH>
    		<TH ALIGN=CENTER >Codigo de<BR>Cuenta</TH>
    		<TH ALIGN=CENTER >Valor Saldo</TH>
    		<TH ALIGN=CENTER >Valor Aplicado</TH>
    		<TH ALIGN=CENTER >Fecha Saldo</TH>
    		<TH ALIGN=CENTER >Status</TH>
  			<TH ALIGN=CENTER >Fecha<BR>Ultima<BR>Actualizacion</TH>
    		<TH ALIGN=CENTER >Usuario<BR>Ultima<BR>Actualizacion</TH> 
  		</TR>
     <%
                mtList.initRow(); 
                chk = "checked";               
                while (mtList.getNextRow()) {
                   datapro.eibs.beans.SRV003001Message msgMT = (datapro.eibs.beans.SRV003001Message) mtList.getRecord();
     %>               
        <TR>
			<TD NOWRAP width="2%"><input type="radio" name="ROW" value="<%= mtList.getCurrentRow()%>" <%=chk%> onClick="document.forms[0].CURRENTROW.value = this.value;"></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE01BALSEQ())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE01BALCTA())+'-'+Util.formatCell(msgMT.getE01BALCON())%></TD>
			<TD NOWRAP ALIGN="RIGHT"><%=Util.formatCell(msgMT.getE01BALBAL())%></TD>
			<TD NOWRAP ALIGN="RIGHT"><%=Util.formatCell(msgMT.getE01BALAPP())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatDate(msgMT.getE01BALBD1(),msgMT.getE01BALBD2(),msgMT.getE01BALBD3()) %></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE01BALSTN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatDate(msgMT.getE01BALUD1(),msgMT.getE01BALUD2(),msgMT.getE01BALUD3())  + " - " + Util.formatTime(msgMT.getE01BALUPT())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE01BALUPU())%></TD>
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
