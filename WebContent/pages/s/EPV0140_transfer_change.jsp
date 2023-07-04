<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Change Attribute</title>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<% 
    String accIdx = "";
	String selIdx = "";
    accIdx = request.getParameter("AccIdx");
	selIdx = request.getParameter("SelIdx");
%>

<SCRIPT LANGUAGE="JavaScript" >

function enterInfo() {
  var tab=<%= accIdx %>
  var row=<%= selIdx %>;
  var form = top.opener.document.forms[0];
  if (parseInt(row) < 0){
    for(i=0; i < Math.abs(parseInt(row));i++){
    	form["TRFUSR" + tab+i].value = trim(document.forms[0].TRFUSR.value);
  		form["MSGTXT" + tab+i].value = trim(document.forms[0].MSGTXT.value);
	}
  } else {
  	form["TRFUSR" + tab+row].value = trim(document.forms[0].TRFUSR.value);
  	form["MSGTXT" + tab+row].value = trim(document.forms[0].MSGTXT.value);
  }
  top.close();
}

</SCRIPT>
 
</head>
<body>
<form>

  <h4 style="text-align:center">Transferir a :</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap > 
        <table cellspacing=2 cellpadding=2 width="100%" border="0">
                
          <tr id="trclear"> 
			<td nowrap id="trdark"> 
              <div align="center"><b>Código de Oficial :</b></div>
            </td>
            
            <td nowrap > 
              <div align="left">
              <input type="text" name="TRFUSR" size="12" maxlength="12" value="<%= request.getParameter("Ofr") %>">
                <a href="javascript:GetOfficer('TRFUSR')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></a> 
              </div>
            </td>
          </tr>
	   
		<tr id="trclear">
			<td nowrap id="trdark" valign="top" > 
              <div align="right"><b>Causa :</b></div>
            </td>
            <td nowrap > 
              <div align="left">
              <textarea name="MSGTXT" rows="15" cols="65" ><%= request.getParameter("Causal") %></textarea>
			</div>
            </td>
            
          </tr>
		
		
          
         
		
	 </table> 
	</td>
    </tr>
  </table>
  
  <BR>
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=button name="Submit" OnClick="enterInfo()" value="Enviar">
  </div>
</p>
<SCRIPT>
   	
		document.forms[0].TRFUSR.focus();
   		document.forms[0].TRFUSR.select();
	
</SCRIPT>  
</form>
</body>
</html>			
