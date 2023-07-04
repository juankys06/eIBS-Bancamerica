<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Customer List
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "VendorList" class= "com.datapro.generics.BeanList" scope="session"/>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</HEAD>
<BODY>
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS2105">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="0500">
<INPUT TYPE=HIDDEN NAME="ACTION" VALUE="X">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
  <h3 align="center">Proveedores Registrados<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="vendor_list_register.jsp,ESS2105"> 
  por Internet</h3>
<%
java.util.Calendar now = java.util.Calendar.getInstance();
int mm = now.get(java.util.Calendar.MONTH) +1;
int dd = now.get(java.util.Calendar.DAY_OF_MONTH);
int yy = now.get(java.util.Calendar.YEAR);
%>  
<hr size="4">  
 <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <TR id="trdark">
				<TD colspan="4" width="484">EMPRESA</TD>
				<TD width="484"></TD>
			</TR>
		  <TR id="trclear">
		     <TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
				<TD colspan="3"><SELECT name="VENDOR">
				    <OPTION VALUE="0">Todos los Proveedores</OPTION>
					<%
   datapro.eibs.beans.ESS210501Message msg = new datapro.eibs.beans.ESS210501Message();
   VendorList.initRow();
   while(VendorList.getNextRow()){
    	msg = (datapro.eibs.beans.ESS210501Message) VendorList.getRecord();
%>
					<OPTION VALUE="<%= msg.getE01VENNUM() %>"><%= msg.getE01VENNUM() + " - " + msg.getE01VENNME()  %></OPTION>
					<%    	
   }
%></TD>
				<TD></TD>
			</TR>
          <TR id="trdark">
				<TD colspan="4" width="484">FECHAS</TD>
				<TD width="484"></TD>
			</TR>
		  <TR id="trclear">
		     <TD align="right">&nbsp;&nbsp;&nbsp;</TD>
			 <TD>Desde : 
			    <INPUT NAME="SDD" TYPE="TEXT" value="<%= dd %>" maxlength="2" size="3" readonly="readonly">
			    <INPUT NAME="SMM" TYPE="TEXT" value="<%= mm %>" maxlength="2" size="3" readonly="readonly">
			    <INPUT NAME="SYY" TYPE="TEXT" value="<%= yy %>" maxlength="2" size="5" readonly="readonly">	
			    <a href="javascript:DOBPicker(document.forms[0].SMM,document.forms[0].SDD,document.forms[0].SYY)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="ayuda" align="middle" border="0"></a>
			    (DD-MM-AAAA) 		    
			 </TD><TD>
		     <TD align="right">&nbsp;&nbsp;&nbsp;</TD>
				<TD align="left">
				Hasta :
				 <INPUT NAME="EDD" TYPE="TEXT" value="<%= dd %>" maxlength="2" size="3" readonly="readonly">
			    <INPUT NAME="EMM" TYPE="TEXT" value="<%= mm %>" maxlength="2" size="3" readonly="readonly">
			    <INPUT NAME="EYY" TYPE="TEXT" value="<%= yy %>" maxlength="2" size="5" readonly="readonly">	
			    <a href="javascript:DOBPicker(document.forms[0].EMM,document.forms[0].EDD,document.forms[0].EYY)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="ayuda" align="middle" border="0"></a>
			    (DD-MM-AAAA) </TD>

		  </TR>	 
         
            
 		</table>
  </table>
  	<table class="tbenter" width=100% align=center>
		<tr> 
	        <td><div align="center"><input id="EIBSBTN" type=submit name="Aceptar" value="Aceptar"></div></td>
	    </tr>
	</table>
  
</FORM>
</BODY>
</HTML>
