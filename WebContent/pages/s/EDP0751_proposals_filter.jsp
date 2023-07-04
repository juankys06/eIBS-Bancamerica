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
//document.forms[0].E01DPXCFO.value=fmt;
// document.forms[0].E01DPXCLI.value=cli;
// document.forms[0].E01DPXGLN.value=gln;
document.forms[0].submit(); 
}
</script>


<jsp:useBean id= "optList1"  class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optList2"  class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optList3"  class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optLP4"  class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "clientFnl" class= "datapro.eibs.beans.EDP072001Message"  scope="session" />
<jsp:useBean id= "error"     class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

</HEAD>


<body bgcolor="#FFFFFF">

<h3 align="center">Propuesta de Crédito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="proposals_filter,EDP0720"></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0720" >
  <CENTER>
    <p><INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">

  <input type=HIDDEN name="CUN" value=""> 

  <input type=HIDDEN name="PRD" value=""> 
  <input type=HIDDEN name="TYP" value=""> 
  <input type=HIDDEN name="BNK" value=""> 
  <input type=HIDDEN name="BRN" value=""> 
  <input type=HIDDEN name="RUT" value=""> 
  <input type=HIDDEN name="CUS" value=""> 
  <input type=HIDDEN name="E01YYYFIL" value=""> 
    </p>
    <table cellspacing="0" cellpadding="2" class="tbenter"  border=0  width=70% align="center" >
      <tr valign="middle"> 
        <td nowrap colspan="2">&nbsp;</td>
      </tr>
      
      <tr valign="middle"> 
        <td nowrap width=20% align="right" > 
          Banco : 
        </td>
        <td nowrap width=60% align="left">  
            <select DISABLED name="E01PLPBNK">
             	<%
                optList2.initRow();
                while (optList2.getNextRow()) {
                    if (optList2.getFlag().equals("")) {
                    		out.println(optList2.getRecord());
                    }        
                }                 
    			%> 
            </select>
        </td>
      </tr>
            
      <tr valign="middle"> 
        <td nowrap width=20% align="right" > 
          N&uacute;mero   de Sucursal : 
        </td>
        <td nowrap width=60% align="left">  
            <select name="E01PLPBRN">
             	<%
                optList1.initRow();
                int k=1;
                while (optList1.getNextRow()) {
                    if (optList1.getFlag().equals("")) {
                    		out.println(optList1.getRecord());
                    k++;
                    }        
                }                 
    			%> 
            </select>
        </td>
      </tr>
      <tr valign="middle"> 
        <td nowrap width=20% align="right" >
          N&uacute;mero de Propuesta : 
        </td>
        <td nowrap width=60% align="left"> 
            <input type=TEXT name="E01FILNPR" size=9 maxlength=9 onKeypress="enterInteger()">
        </td>
      </tr>
       <tr valign="middle"> 
        <td nowrap width=20% align="right" >
          N&uacute;mero de Cliente : 
        </td>
        <td nowrap width=60% align="left"> 
            <input type=TEXT name="E01FILCUN" size=15 maxlength=9 onKeypress="enterInteger()">
            <a href="javascript:GetCustomerDescId('E01FILCUN','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" ></a> 
        </td>
      </tr>
     <tr valign="middle"> 
        <td nowrap width=20% align="right" >
          C&oacute;digo Ejecutivo : 
        </td>
        <td nowrap width=60% align="left"> 
            <input type=TEXT name="E01FILEJE" size=15 maxlength=9 onKeypress="enterInteger()">
            <A href="javascript:GetCodeDescCNOFC('E01FILEJE','E01FILEJE','15')">
            <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
        </td>
                        
      </tr>
      <tr valign="middle"> 
        <td nowrap width=20% align="right" > 
          Producto : 
        </td>
        <td nowrap width=60% align="left">  
            <select name="E01FILPRD">
             	<%
                optList3.initRow();
                int t=1;
                while (optList3.getNextRow()) {
                    if (optList3.getFlag().equals("")) {
                    		out.println(optList3.getRecord());
                    t++;
                    }        
                }                 
    			%> 
            </select>
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
//  document.forms[0].E01IFMCUN.focus();
//  document.forms[0].E01IFMCUN.select();

function goConfirm() {
//document.forms[0].E01YYYFIL.value="0";
box1 = eval("document.forms[0].E01YYYFIL99");
//alert(box1.checked);
if (box1.checked == true) {
      document.forms[0].E01YYYFIL.value="1";
  } else {
      document.forms[0].E01YYYFIL.value="0";
}
  
document.forms[0].PRD.value=document.forms[0].E01FILPRD.value; 
document.forms[0].BNK.value=document.forms[0].E01PLPBNK.value; 
document.forms[0].BRN.value=document.forms[0].E01PLPBRN.value; 
document.forms[0].CUS.value=document.forms[0].E01FILCUN.value; 
var selectedItem = document.forms[0].E01FILPRD.selectedIndex;
var selectedText = document.forms[0].E01FILPRD.options[selectedItem].text;
document.forms[0].TYP.value = selectedText.substring(4,9)
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
 