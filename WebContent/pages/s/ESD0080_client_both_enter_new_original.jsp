<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<html>
<head>
<title>Nuevo Cliente</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
 

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<%if(currUser.getE01INT().equals("03")){%>

<script language="JavaScript">

 function enterInteger1 (idType)
 {

 if (idType.value != 'P')
   enterInteger();
 
 }

 function joinID(idField, idType, idNum){

	  var strID = "";
	    while ((idNum.value.length < 9) && (idType.value!='P'))
	      idNum.value='0'+idNum.value;
	  strID = trim(idType.value) + trim(idNum.value);
      idField.value=strID.toUpperCase();
}

 function getIdTypeHelp(){
   // Get the client type selected
   var clientTypeSelected="CORPORATIVE";
   for (counter = 0; counter < document.forma.TYPE.length; counter++)
   {
      if (document.forma.TYPE[counter].checked)
        clientTypeSelected = document.forma.TYPE[counter].value; 
   }
   // Display the id screen help 
   if (clientTypeSelected == "CORPORATIVE") 
     GetCode('IDN0','STATIC_client_help_id_type.jsp?clientType=CORPORATIVE');
   else
     GetCode('IDN0','STATIC_client_help_id_type.jsp?clientType=PERSONAL');
 }
</script>
<% } %>

</head>

<body bgcolor="#FFFFFF">

<h3 align="center">Nuevo Cliente<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="client_both_enter_new, ESD0080"></h3>
<hr size="4">
<form name="forma" method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080">
  <p>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
  </p>
  <h4 align="center">Ingrese el N&uacute;mero de Cliente o de Identificaci&oacute;n 
    y seleccione una opci&oacute;n. </h4>
  <p>&nbsp; </p>
  <table class="tableinfo">
<%if(currUser.getE01INT().equals("03")){%>
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap align="right" valign="middle" width="49%" > 
              Tipo de Cliente
            </td>
            <td colspan="2">
    		  <INPUT TYPE=HIDDEN NAME="IDN" value="">
              <input type="radio" name="TYPE" value="CORPORATIVE">Corporativo
              <input type="radio" name="TYPE" value="PERSONAL" checked>Personal
            </td>
          </tr>
         <tr id="trdark"> 
            <td nowrap align="right" valign="middle" width="49%">N&uacute;mero de Cliente :</td>
            <td nowrap align="left" valign="middle" colspan="2"> 
              <input type="text" name="CUN" value="0" maxlength="10" size="11" onKeypress="enterInteger()" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap align="right" valign="middle" width="49%">Identificaci&oacute;n :
           </td>
            <td nowrap align="left" valign="middle" colspan="2"> 
              
              <input type="text" name="IDN0" value="" maxlength="1" size="1" readonly
              			onchange="joinID(document.forms[0].IDN, document.forms[0].IDN0, document.forms[0].IDN1);">
				<a href="javascript:getIdTypeHelp()">
				<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>

              <input type="text" name="IDN1" value="" maxlength="14" size="15" 
              onKeypress="enterInteger1(document.forms[0].IDN0)"
    			onchange="joinID(document.forms[0].IDN, document.forms[0].IDN0, document.forms[0].IDN1);">
              
            </td>
          </tr>
         
        </table>
      </td>
    </tr>
<% } else { %>
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap align="right" valign="middle" width="49%">N&uacute;mero de Cliente :</td>
            <td nowrap align="left" valign="middle" colspan="2"> 
              <input type="text" name="CUN" value="0" maxlength="10" size="11" onKeypress="enterInteger()" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap align="right" valign="middle" width="49%">Identificaci&oacute;n :
           </td>
            <td nowrap align="left" valign="middle" colspan="2"> 
              <input type="text" name="IDN" value="" maxlength="15" size="16">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap align="right" valign="middle" colspan="3" > 
              <div align="center">Tipo de Cliente</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap align="right" valign="middle" colspan="2"> Corporativo 
              :</td>
            <td nowrap width="46%" align="left" valign="middle"> 
              <input type="radio" name="TYPE" value="CORPORATIVE">
            </td>
          </tr>
          <tr  id="trclear"> 
            <td nowrap colspan="2" align="right" valign="middle"> 
              Personal :</td>
            <td nowrap width="46%" align="left" valign="middle"> 
              <input type="radio" name="TYPE" value="PERSONAL" checked>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2" align="right" valign="middle"> Otros :</td>
            <td nowrap width="46%" align="left" valign="middle"> 
              <input type="radio" name="TYPE" value="OTHER">
            </td>
          </tr>
        </table>
      </td>
    </tr>
<% } %>
  </table>
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
</form>
<script language="JavaScript">
<%if(currUser.getE01INT().equals("03")){%>
  document.forms[0].IDN1.focus();
  document.forms[0].IDN1.select();
<% } else { %>
  document.forms[0].IDN.focus();
  document.forms[0].IDN.select();
<% } %>
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
</body>
</html>
