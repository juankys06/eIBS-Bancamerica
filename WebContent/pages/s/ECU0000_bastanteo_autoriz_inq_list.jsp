<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
 
<%@ page import ="datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Consulta de Autorizaciones Especiales
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "mtListAut" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<SCRIPT language="javascript" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<SCRIPT language="javascript">
  
  function goAction(opt,typ) {
    
    if (opt == "I") { 
		var row = document.forms[0].CURRENTROW.value;   
    	page = "<%=request.getContextPath()%>/pages/s/ECU0000_bastanteo_autoriz_inq.jsp?ROW=" + row;
    	CenterNamedWindow(page,'Information',650,500,2);
  	} else if (opt == "R") { 
  		if (typ == "Y") {
  			page = "<%=request.getContextPath()%>/pages/s/ECU0000_bastanteo_attorney_inq.jsp?ROW=" + <%= userPO.getHeader5()%>;
    		CenterNamedWindow(page,'Information',650,500,2);  
    	} else {
    		page = "<%=request.getContextPath()%>/pages/s/ECU0000_bastanteo_members_inq.jsp?ROW=" + <%= userPO.getHeader5()%>;
    		CenterNamedWindow(page,'Information',650,500,2);
    	}
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


<h3 align="center">Sistema de Bastanteo - Consulta de Autorizaciones Especiales
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bastanteo_autoriz_inq_list.jsp,ECU0000">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000" >

<%
	if ( mtListAut.getNoResult() ) {
%> 

  <TABLE class="tbenter" width="60%" align=center>
    <TR>
      <TD class=TDBKG width="50%"> 
        <div align="center"><a href="javascript:goAction('R','<%=userPO.getHeader3()%>')">Regresar</a> 
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
            <b>No existen registros de autorizaciones.</b>
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
 
 <TABLE cellspacing=0 cellpadding=2 width="100%" border="0">    
	<TR>
		<TD nowrap width="40%">
			<DIV align="right">C&oacute;digo de Cliente :</DIV>
		</TD>
		<TD nowrap width="60%">
			<INPUT type="text" name="E06CUACUN" size="10" maxlength="9" value="<%= userPO.getHeader16() %>" readonly> 
		</TD>
	</TR>
	<TR>
		<TD nowrap width="40%">
			<DIV align="right">Nombre o Denominaci&oacute;n Social :</DIV>
		</TD>
		<TD nowrap width="60%">
			<INPUT type="text" name="E06CUSNA1" size="40" maxlength="35" value="<%= userPO.getHeader17() %>" readonly>  
		</TD>
	</TR>
	<% 
 	if (!userPO.getHeader3().equals("Y")) {
	%>
	<TR>
		<TD nowrap width="40%">
			<DIV align="right">Integrante :</DIV>
		</TD>
		<TD nowrap width="60%">
			<INPUT type="text" name="E06CUAMAN" size="3" maxlength="2" value="<%= userPO.getHeader18() %>" readonly>
			<INPUT type="text" name="E06CUMMA1" size="40" maxlength="35" value="<%= userPO.getHeader19() %>" readonly> 
		</TD>
	</TR>
	<% } else { %>
	<TR>
		<TD nowrap width="40%">
			<DIV align="right">Apoderado :</DIV>
		</TD>
		<TD nowrap width="60%">
			<INPUT type="text" name="E06CUMMA1" size="40" maxlength="35" value="<%= userPO.getHeader19() %>" readonly> 
		</TD>
	</TR>
	<% } %>
	<% 
 		if (!userPO.getHeader3().equals("Y")  ) {
	%>
	<TR>
		<TD nowrap width="40%">
			<DIV align="right">Cargo :</DIV>
		</TD>
		<TD nowrap width="60%">
			<INPUT type="text" name="E06CUMUC5" size="5" maxlength="4" value="<%= userPO.getHeader20() %>" readonly>
			<INPUT type="text" name="E06CUMUCN" size="40" maxlength="35" value="<%= userPO.getHeader21() %>" readonly> 
		</TD>
	</TR>
	<% } %>
 </TABLE>
    
 <TABLE class="tbenter" width="100%" align=center>
    <TR> 
      <TD class=TDBKG width="33%"> 
        <div align="center"><a href="javascript:goAction('I')">Consultar</a> 
        </div>
      </TD>
      <TD class=TDBKG width="33%"> 
        <div align="center"><a href="javascript:goAction('R','<%=userPO.getHeader3()%>')">Regresar</a> 
        </div>
      </TD>
      <TD class=TDBKG width="33%"> 
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
    		<TH ALIGN=CENTER >Facultad</TH>
    		<TH ALIGN=CENTER >Fecha de<BR> Aprobacion</TH>
    		<TH ALIGN=CENTER >Fecha de<BR> Vencimiento</TH>
    		<TH ALIGN=CENTER >Fecha Ultima<BR>Actualizacion</TH>
    		<TH ALIGN=CENTER >Usuario Ultima<BR>Actualizacion</TH> 
  		</TR>
     <%
                mtListAut.initRow(); 
                chk = "checked";               
                while (mtListAut.getNextRow()) {
                   datapro.eibs.beans.ECU000006Message msgMT = (datapro.eibs.beans.ECU000006Message) mtListAut.getRecord();
     %>               
        <TR>
			<TD NOWRAP width="2%"><input type="radio" name="ROW" value="<%= mtListAut.getCurrentRow()%>" <%=chk%> onClick="document.forms[0].CURRENTROW.value = this.value;"></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE06CUAFAN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatDate(msgMT.getE06CUAAD1(),msgMT.getE06CUAAD2(),msgMT.getE06CUAAD3()) %></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatDate(msgMT.getE06CUAMA1(),msgMT.getE06CUAMA2(),msgMT.getE06CUAMA3()) %></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatDate(msgMT.getE06CUAAC1(),msgMT.getE06CUAAC2(),msgMT.getE06CUAAC3())  + " - " + Util.formatTime(msgMT.getE06CUAACT())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE06CUAACU())%></TD>
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
