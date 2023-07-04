<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>User Privilege</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<%@ page import = "java.io.*,java.net.*,datapro.eibs.beans.*,com.datapro.eibs.security.bean.*,java.math.*" %>

<%
   CNTRLPVLNME priviBean = new CNTRLPVLNME();
   int row = -1;
   String parent = "USR";
   try {
     if (!request.getParameter("ROW").equals(null)) {
     	row = Integer.parseInt(request.getParameter("ROW"));     	
     }     
   }
   catch (Exception e) {
   	 row=-1;
   }
   try {
     if (!request.getParameter("PARENT").equals(null)) {
     	parent = request.getParameter("PARENT");     	
     }     
   }
   catch (Exception e) {
   	 parent="USR";
   }
   if (row != -1) {
        JBObjList pvList = null;
        if (parent.equals("GRP")) {
           pvList = (JBObjList) session.getAttribute("grpPriviList");
        } else {
           pvList = (JBObjList) session.getAttribute("priviList");
        }
		pvList.setCurrentRow(row);
   		priviBean = (CNTRLPVLNME) pvList.getRecord();
   } 
%>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
  
  function enter() {	
	var form = top.opener.document.forms[0];
	var typ = document.forms[0].CNOF04.value;
	var newcode = "";
	var newval = "";	
		if (trim(document.forms[0].PVLCOD.value) == "") {
	   		 alert("Seleccione un privilegio antes de realizar esta operación");
		} else {
			newcode = document.forms[0].PVLCOD.value;
		    if (typ == "B") {
		    	newval = document.forms[0].TYPEB.options[document.forms[0].TYPEB.selectedIndex].value;
		    } else {
		    	newval = document.forms[0]["TYPE"+typ].value;
		    }
		    if (trim(newval) == "") {
		    	alert("Teclee un valor para el privilegio seleccionado");
		    } else {  
			form[top.opener.fieldPVL].value = newcode;
			form[top.opener.fieldVAL].value = newval; 
			<%   if (row != -1) {%>		
				top.opener.goAction('M');
			<% } else {%>
				top.opener.goAction('A');
			<% } %> 
			top.close();
			}
		}		
  }
  
  function setType() {
  	var typ = document.forms[0].CNOF04.value;
  	document.forms[0].TYPEB.style.display = "none";
  	document.forms[0].TYPEN.style.display = "none";
  	document.forms[0].TYPEC.style.display = "none";
  	document.forms[0]["TYPE"+typ].style.display = "";
  	if (typ == 'C' || typ == 'N') {
  	 <%if (row == -1) { %> 
  	 document.forms[0]["TYPE"+typ].value = "";
  	 <% }%>  	 
  	}
  	document.forms[0]["TYPE"+typ].focus();
  }
  
</SCRIPT>

</head>
<body>
<h3>Privilegios</h3>
<form>
  <input type="hidden" name="NEWCOD" value="">
  <input type="hidden" name="NEWVAL" value="">
  <input type="hidden" name="CNOF04" value="<% if (row != -1) out.print(priviBean.getCNOF04()); else out.print('N');%>"> 
        	            
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr> 
            <td id="trdark" nowrap width="20%" align=right> 
              <b>Privilegio :</b>
            </td>
         	<td nowrap >
         	    <input type="hidden" name="PVLCOD" value="<% if (row != -1) out.print(priviBean.getPVLPVL());%>">
            	<input type="text" readonly name="PVLNME" size="40" maxlength="30" value="<% if (row != -1) out.print(priviBean.getCNODSC());%>">
           </td>
     	  </tr>
     	  <tr> 
     	    <td id="trdark" nowrap width="20%" align=right> 
              <b>Valor :</b>
            </td>
         	<td nowrap> 
            	<input type="text" name="TYPEC" size="50" maxlength="50" value="<% if (priviBean.getCNOF04().equals("C")) out.print(priviBean.getPVLVAL());%>" style="display:none">
            	
            	<input type="text" name="TYPEN" size="16" maxlength="15" onkeypress="enterDecimal()" value="<% if (priviBean.getCNOF04().equals("N")) out.print(priviBean.getPVLVAL());%>" style="display:none">
         	   
         	    <select name="TYPEB" onchange="document.forms[0].NEWVAL.value = this.options[this.selectedIndex].value;" style="display:none">         	                 
                    <option value="SI" <% if (priviBean.getCNOF04().equals("B") && priviBean.getPVLVAL().equals("SI")) out.print("selected");%>>SI</option>
                    <option value="NO" <% if (priviBean.getCNOF04().equals("B") && priviBean.getPVLVAL().equals("NO")) out.print("selected");%>>NO</option>
                 </select>        	
         	</td>
     	  </tr>
  		</table>
  	  </td>
    </tr>
  </table>
  
  <p align="center"> 
    <input id="EIBSBTN" type=button name="Submit" value="Enviar" onclick="enter()">
  </p>

	<SCRIPT Language="Javascript"> 
  		setType();
	</SCRIPT>

</form>
</body>
</html>
