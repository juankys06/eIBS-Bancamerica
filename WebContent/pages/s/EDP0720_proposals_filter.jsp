<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css"> 
<TITLE>Propuesta de Crédito</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"></SCRIPT>

<script language="JavaScript">

function goCancel() {
	document.forms[0].SCREEN.value="200";
	document.forms[0].submit(); 
}
</script>
<jsp:useBean id= "optList746"  class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<jsp:useBean id= "error"     class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

</HEAD>


<body bgcolor="#FFFFFF">

<h3 align="center">Propuesta de Crédito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="proposals_filter,EDP0720"></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0720" >
  <CENTER>
    <p><INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  <input type=HIDDEN name="E01PLPBNK" value="<%= currUser.getE01UBK().trim()%>"> 
  <input type=HIDDEN name="E01YYYFIL" value=""> 
  <input type=HIDDEN name="E01XXXFIL" value=""> 
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
            <input type=TEXT name="D01FILCUN" size=20 maxlength=20 readonly>
            <a href="javascript:GetCustomerDescId('E01FILCUN','D01FILCUN','')">
            <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" ></a> 
        </td>
      </tr>
      
     <tr valign="middle"> 
        <td nowrap width=20% align="right" >
          C&oacute;digo Ejecutivo : 
        </td>
        <td nowrap width=60% align="left"> 
            <input type=TEXT name="E01FILEJE" size=6 maxlength=4 onKeypress="enterInteger()">
            <input type=TEXT name="D01FILEJE" size=20 maxlength=20 readonly>
            <A href="javascript:GetCodeDescCNOFC('E01FILEJE','D01FILEJE','15')">
            <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
        </td>                  
      </tr>
      
      <tr valign="middle"> 
        <td nowrap width=20% align="right" >
          Estado Propuesta : 
        </td>
        <td nowrap width=60% align="left"> 
            <input type=TEXT name="E01FILEST" size=3 maxlength=2 onKeypress="enterInteger()">
            <input type=TEXT name="D01FILEST" size=20 maxlength=20 readonly>
            <A href="javascript:GetCodeDescCNOFC('E01FILEST','D01FILEST','37')">
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
  <% if(userPO.getOption().equals("5")){ %>

      <tr valign="middle"> 
        <td nowrap width=20% align="right" >
          Usuario: 
        </td>
        <td nowrap width=60% align="left"> 
      
            <select name="E01FILUSR">
             	<%
                optList746.initRow();
                while (optList746.getNextRow()) {
                    if (optList746.getFlag().equals("")) {
                    		out.println(optList746.getRecord());
                    }        
                }                 
    			%> 
            </select>
        </td>
      </tr>
      <tr valign="middle"> 
        <td nowrap width=20% align="right" >
          Ruta: 
        </td>
        <td nowrap width=60% align="left"> 
			           <input type="text" name="E01FILRUT" size="5" maxlength="4" readonly>
			              <a href="javascript:GetCodeCNOFC('E01FILRUT','48')">
			              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
        </td>
      </tr>
  <% }; %>      
      
      <tr valign="middle"> 
        <td nowrap width=20% align="right" > 
          Ver Históricas : 
        </td>
        <td nowrap width=60% align="left">  
   		<INPUT type="checkbox" name="E01YYYFIL99" onClick="UpdateFlag(this.checked)">
		</td>
      </tr>

<%--
	<tr valign="middle"> 
        <td nowrap width=20% align="right" >
		<div id=Renova style="display: none" align="right">
		Renovacion:
		</div>
		</td>
        <td nowrap width=60% align="left">
		<div id=Renova1 style="display: none" align="left">
			<INPUT type="checkbox" name="E01XXXFIL99" onclick="UpdateFlag1(this.checked)">
		</div>
		</td>
	</tr>
--%>
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
	
//	box2 = eval("document.forms[0].E01XXXFIL99");
//	if (box2.checked == true) {
//	    document.forms[0].E01XXXFIL.value="1";
//	} else {
//		document.forms[0].E01XXXFIL.value="0";
//	}
	  
	document.forms[0].E01PLPBRN.value=document.forms[0].E01FILBRN.value; 
	document.forms[0].submit();	  		  
}

 function UpdateFlag(fieldbox) {
  box = eval("document.forms[0].E01YYYFIL99");
  //alert(box.checked);
  if (box.checked == true) {
      document.forms[0].E01YYYFIL.value="1";
//	Renova.style.display="";
//	Renova1.style.display="";
  } else {
      document.forms[0].E01YYYFIL.value="0";
//    Renova.style.display="none";
//    Renova1.style.display="none";
  }         
 }

 function UpdateFlag1(fieldbox) {
  box = eval("document.forms[0].E01XXXFIL99");
  //alert(box.checked);
  if (box.checked == true) {
      document.forms[0].E01XXXFIL.value="1";
  } else {
      document.forms[0].E01XXXFIL.value="0";
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
 