<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Subir Planilla</TITLE>
</HEAD>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<jsp:useBean id= "msgFile" class= "datapro.eibs.beans.EDL081004Message"  scope="session" />
<jsp:useBean id= "linea" class= "java.lang.String"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<BODY>
<h3 align="center">Ingreso de Planilla Automatico<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="collection_upload, EDL0810"></h3>
<hr size="4">
<FORM method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0810" ENCTYPE="multipart/form-data">
<INPUT TYPE="HIDDEN" NAME="SCREEN" VALUE="1300">
<p>&nbsp;</p>
<TABLE class="tbenter">
        <TR with="100%" align="center">
			<TD align="right">Producto : </TD>
			<TD align="left"><INPUT type="textfield" name="PRDCDE" size="4" value ="<%= currUser.getH01OPE() %>" readonly>
			<!--<a href="javascript:GetProduct('PRDCDE','10','<%=currUser.getE01UBK() %>')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absbottom" border="0"></a>-->
			</TD>
		</TR>
		<TR with="100%" align="center">
			<TD align="right">Archivo : </TD>
			<TD align="left"><INPUT type="file" name="NMEFLD" size="60"></TD>
		</TR>

</TABLE>
<% if ( !error.getERRNUM().equals("0")  ) {%>
<br>
<h4>Error</h4>
<table class="tableinfo">
    <tr > 
      <td nowrap> 
		<center>
			<font style="font-family: Arial, Helvetica, sans-serif;color: red;font-size: 9pt;" ><b><%= error.getField("ERDS01").getString() %></b></font>
		</center>
	  </td>
	</tr>
</table>
<%
	String E04DKTFVE=msgFile.getE04DKTFVE();
	if (msgFile.getE04DKTFVE().length() == 7){
			E04DKTFVE = "0"+msgFile.getE04DKTFVE();
	}
	if ( E04DKTFVE != null && !E04DKTFVE.equals("0") && !E04DKTFVE.equals("") ) {
%>
	<h4>Registro con error</h4>
	  <table class="tableinfo">
	    <tr > 
	      <td nowrap> 
	        <table cellpadding=2 cellspacing=0 width="100%" border="0">
	          <tr id="trdark"> 
	            <td nowrap> 
	              <div align="center">Linea</div>
	            </td>
	            <td nowrap> 
	              <div align="center">Rut</div>
	            </td>
	            <td nowrap> 
	              <div align="center">Valor</div>
	            </td>
	            <td nowrap> 
	              <div align="center">Fecha Vencimiento</div>
	            </td>
	
	          </tr>
	          <tr id="trclear"> 
	            <td nowrap> 
	              <div align="center"><%=linea%></div>
	            </td>
	            <td nowrap> 
	              <div align="center"><%=msgFile.getE04DKTRUA()%></div>
	            </td>
	            <td nowrap> 
	              <div align="center"><%=msgFile.getE04DKTVLR()%></div>
	            </td>
	            <td nowrap width="25%"> 
	              <div align="center"><%=E04DKTFVE.substring(0,2)%>/<%=E04DKTFVE.substring(2,4)%>/<%=E04DKTFVE.substring(4,8)%></div>
	            </td>
	          </tr>
	        </table>
	      </td>
	    </tr>
	  </table>
	<%}%>
<%}%>
<div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</FORM>
</BODY>
</HTML>
