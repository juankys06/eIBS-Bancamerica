<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Services Charges</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT language="JavaScript">

	function getCorrespDescId(name, desc, id){
		page= prefix + language + "EWD0001_corresp_desc_id_help_container.jsp";
		fieldName=name;
		fieldDesc=desc;
		fieldId=id;
		fieldAux1="";
		CenterWindow(page,530,530,1);
	}

</SCRIPT>
</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body bgcolor="#FFFFFF">

<H3 align="center">Comisiones  y Gastos de Corresponsales<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="sc_corresp_enter_selection.jsp, ELC0600"></H3>

<HR size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0600">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="36">
  </p>
<br>
  <table class="tableinfo" cellspacing="0" cellpadding="2" width="100%"border="0" >
   <tr> 
      <td > 
        <table id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">      
          <tr> 
            <td width="50%" nowrap> 
              <div align="right">Banco :</div>  
            </td>
            <td width="50%" nowrap>
             	<input type="text" name="E01RLCBNK" size="3" maxlength="2" value="" onkeypress="enterInteger()">
            </td>
          </tr>  
          <tr> 
            <td width="49%" nowrap> 
        <div align="right">Numero de Cliente :</div>
      </td>
      <td width="51%" nowrap> 
        <input type="text" name="E01RLCCUN" size="16" maxlength="9"
					value="">
        <a href="javascript:getCorrespDescId('E01RLCCUN','','')"> 
        <img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
      </td> 
          </tr>        
        </table>      
      </td>
    </tr>  
  </table>  
 <p align="center"> 
    <input id="EIBSBTN" type=submit name="Aceptar" value="Aceptar" >
  </p>  
<script language="JavaScript">
  document.forms[0].E01RLCBNK.focus();
  document.forms[0].E01RLCBNK.select();
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
