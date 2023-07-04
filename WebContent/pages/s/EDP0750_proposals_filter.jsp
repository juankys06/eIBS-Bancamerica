<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css"> 
<TITLE>Información Financiera</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"></SCRIPT>

<script language="JavaScript">

function goCancel() {
	document.forms[0].SCREEN.value="200";
	document.forms[0].submit(); 
}
</script>

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<jsp:useBean id= "error"     class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

</HEAD>


<body bgcolor="#FFFFFF">

<h3 align="center">Seguimiento Propuesta de Crédito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="proposals_filter,EDP0750"></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0750" >
  <CENTER>
    <p><INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  <input type=HIDDEN name="E01PLPBNK" value="<%= currUser.getE01UBK().trim()%>"> 
  <input type=HIDDEN name="E01YYYFIL" value=""> 
    </p>
    <table cellspacing="0" cellpadding="2" class="tbenter"  border=0  width=70% align="center" >
      <tr valign="middle"> 
        <td nowrap colspan="2">&nbsp;</td>
      </tr>
      
      <tr valign="middle"> 
        <td nowrap width=20% align="right" > 
          N&uacute;mero   de Sucursal : 
        </td>
        
        <td nowrap width=60% align="left"> 
  			<input type=HIDDEN name="E01PLPBRN" value="">         
      	    <input type="text" name="E01FILBRN" size="4" maxlength="3" value="" onKeypress="enterInteger()">
	        <a href="javascript:GetBranch('E01FILBRN','<%=currUser.getE01UBK().trim()%>')">
	        <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a> 
	    </td> 
      </tr>
      
      <tr valign="middle"> 
        <td nowrap width=20% align="right" >
          N&uacute;mero de Propuesta : 
        </td>
        <td nowrap width=60% align="left"> 
            <input type=TEXT name="E01FILNPR" size=16 maxlength=12 onKeypress="enterInteger()">
        </td>
      </tr>
       <tr valign="middle"> 
        <td nowrap width=20% align="right" >
          N&uacute;mero de Cliente : 
        </td>
        <td nowrap width=60% align="left"> 
            <input type=TEXT name="E01FILCUN" size=16 maxlength=9 onKeypress="enterInteger()">
            <a href="javascript:GetCustomerDescId('E01FILCUN','','')">
            <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" ></a> 
        </td>
      </tr>
     <tr valign="middle"> 
        <td nowrap width=20% align="right" >
          C&oacute;digo Ejecutivo : 
        </td>
        <td nowrap width=60% align="left"> 
            <input type=TEXT name="E01FILEJE" size=6 maxlength=4 onKeypress="enterInteger()">
            <A href="javascript:GetCodeDescCNOFC('E01FILEJE','E01FILEJE','15')">
            <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
        </td>
                        
      </tr>
      <tr valign="middle"> 
        <td nowrap width=20% align="right" >
          Producto : 
        </td>
        <td nowrap width=60% align="left"> 
            <input type=TEXT name="E01FILTYP" size="6" maxlength="4" >
            <input type=TEXT name="E01FILPRD" size="6" maxlength="4" >
	        <a href="javascript:GetProposalsProducts('E01FILPRD','E01FILTYP','','')">
	        <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a> 
        </td>
      </tr>
      
      <tr valign="middle"> 
        <td nowrap width=20% align="right" >
          Referencia IBS : 
        </td>
        <td nowrap width=60% align="left"> 
            <input type=TEXT name="E01FILREF" size=16 maxlength=16 onKeypress="enterInteger()">
        </td>
      </tr>
      <tr valign="middle"> 
        <td nowrap width=20% align="right" > 
          Ver desincorporadas: 
        </td>
        <td nowrap width=60% align="left">  
      		<INPUT type="checkbox" name="E01YYYFIL99" onClick="UpdateFlag(this.checked)">
        </td>
      </tr>
      <tr> 
        <td nowrap colspan="2" valign="middle" height="200"> 
			<div align="center"> 
			<INPUT id="EIBSBTN" type="button" name="Submit" value="Enviar" onclick="goConfirm()"></div>
        </td>
      </tr>
      <tr> 
        <td nowrap colspan="2" valign="middle">&nbsp;</td>
      </tr>
    </table>
  </CENTER>
</FORM>
<script language="JavaScript">

function goConfirm() {
box1 = eval("document.forms[0].E01YYYFIL99");
if (box1.checked == true) {
      document.forms[0].E01YYYFIL.value="1";
  } else {
      document.forms[0].E01YYYFIL.value="0";
}
  
document.forms[0].E01PLPBRN.value=document.forms[0].E01FILBRN.value;
document.forms[0].submit();	  		  
}

 function UpdateFlag(fieldbox) {
  box = eval("document.forms[0].E01YYYFIL99");
  //alert(box.checked);
  if (box.checked == true) {
      document.forms[0].E01YYYFIL.value="1";
  } else {
      document.forms[0].E01YYYFIL.value="0";
  }         
 }
</script>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">;
            showErrors();
     </SCRIPT>
 <%
 }
%>
</BODY>
</HTML>
 