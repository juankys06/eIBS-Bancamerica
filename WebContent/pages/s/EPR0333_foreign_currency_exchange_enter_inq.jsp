<html>  
<head>
<title>Compraventa Moneda Extranjera</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "msgMT" class= "datapro.eibs.beans.EPR033301Message"  scope="session" />

</head>
<body>

<H3 align="center">Compraventa Moneda Extranjera<BR>
Preliquidacion de Solicitudes de Canje<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="foreign_currency_exchange_enter_inq, EPR0333"></H3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEPR0333">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="100">
  </p>
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
      	<tr id=trclear> 
		  <td> 
		     <div align="right">Cliente  : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01REQCUS" size="10" maxlength="9" value="<%= msgMT.getE01REQCUS()%>">
            <a href="javascript:GetCustomerDescId('E01REQCUS','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
            <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
      	  </td>     
      	</tr>
      	<tr id=trclear>
          <td nowrap>
              <div align="right">Fecha Liquidacion - Desde : </div>
          </td>
          <td nowrap> 
              <input type="text" name="E01REQLD1" size="3" maxlength="2" value="<%= msgMT.getE01REQLD1()%>" onkeypress="enterInteger()">
              <input type="text" name="E01REQLD2" size="3" maxlength="2" value="<%= msgMT.getE01REQLD2()%>" onkeypress="enterInteger()">
              <input type="text" name="E01REQLD3" size="3" maxlength="2" value="<%= msgMT.getE01REQLD3()%>" onkeypress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E01REQLD1,document.forms[0].E01REQLD2,document.forms[0].E01REQLD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a>
              &nbsp; Hasta : &nbsp; 
              <input type="text" name="E01REQLT1" size="3" maxlength="2" value="<%= msgMT.getE01REQLT1()%>" onkeypress="enterInteger()">
              <input type="text" name="E01REQLT2" size="3" maxlength="2" value="<%= msgMT.getE01REQLT2()%>" onkeypress="enterInteger()">
              <input type="text" name="E01REQLT3" size="3" maxlength="2" value="<%= msgMT.getE01REQLT3()%>" onkeypress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E01REQLT1,document.forms[0].E01REQLT2,document.forms[0].E01REQLT3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a>               
          </td>
        </tr>
     </table>
    </td>
   </tr>
  </table>
  
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
  
	<script language="JavaScript">
	  document.forms[0].E01REQCUS.focus();
	  document.forms[0].E01REQCUS.select();
	</script>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }
%>
</form>
</body>
</html>
