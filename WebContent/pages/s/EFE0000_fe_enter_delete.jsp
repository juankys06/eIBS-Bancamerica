<html>
<head>
<title>Front Office Input</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT language="JavaScript">

 function Send(){
  if (document.forms[0].E01FESREF.value == "") {
  	alert("Por favor, ingrese el número de referencia.");
  	return false;
  }
  else {
  
	  if((document.forms[0].E01FESTYPO.value == '31') ||
	     (document.forms[0].E01FESTYPO.value == '32') ||
	     (document.forms[0].E01FESTYPO.value == '33') ||
		 (document.forms[0].E01FESTYPO.value == '34'))
		{
	    document.forms[0].SCREEN.value = 400;     
	  }
	  else if(document.forms[0].E01FESTYPO.value == '35'){
	   document.forms[0].SCREEN.value = 2000;
	  }
	  else{
	   document.forms[0].SCREEN.value = 1800;
	  }
	  //document.forms[0].submit();
	  return true;
  }
 }

</SCRIPT>


</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "custList" class= "datapro.eibs.beans.JBList"   scope="session"/>

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />


<body bgcolor="#FFFFFF">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>
<H3 align="center"> Tesorer&iacute;a - Borrado de Tiquetes sin Back-Office <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="fe_enter_delete,EFE0000"></H3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0000" onSubmit="return Send();">
  <p>
    <input type=HIDDEN name="SCREEN">
    <input type=HIDDEN name="E01FESTYP">
  </p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <center>
    <table class="tableinfo" >
      <tr id="trintrot"> 
        <td colspan="2"><%= currUser.getH01USR()%></td>
        <td width="25%"> 
          <div align="right"><%= datapro.eibs.master.Util.formatDate(currUser.getE01RDM(),currUser.getE01RDD(),currUser.getE01RDY())%></div>
        </td>
      </tr>
      <tr id="trintro"> 
        <td colspan="3">
          <div align="center">N&uacute;mero de Referencia: 
            <input type="hidden" name="E01FESDID">
            <input type="hidden" name="E01FESTYPO">
            <input type="hidden" name="REFTYP" >
            <input type="text" name="E01FESREF" size="12" maxlength="12">
             <a href="javascript:GetFeRef('E01FESDID','E01FESTYPO','REFTYP','E01FESREF')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a></div>
        </td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
      <tr bgcolor="#FFFFFF">
        <td width="33%">&nbsp;</td>
      </tr>
      <tr bgcolor="#FFFFFF"> 
        <td width="33%"> 

  <div align="center"> 
	        <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
        </td>
      </tr>
      <tr bgcolor="#FFFFFF"> 
        <td> </td>
      </tr>
    </table>

  </center>
  <center>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
  </center>
      
  
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%>  <b>
  <input type=HIDDEN name="totalRow" value="1">
  </b>


</form>
</body>
</html>
