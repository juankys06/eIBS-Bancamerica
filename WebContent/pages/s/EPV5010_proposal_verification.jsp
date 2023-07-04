<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css"> 
<TITLE>Manejo de Clientes</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="javascript">

  function setInfo(code,txt) {
    if ( code !== document.forms[0].SELCODE.value ) {
       document.all["div"+document.forms[0].SELCODE.value].style.display="none";
       document.all["div"+code].style.display="";
       document.forms[0].SELCODE.value = code;
       document.forms[0].selTEXT.value = txt;       
    }    
  }

</SCRIPT>

<jsp:useBean id="proposal" class="datapro.eibs.beans.EPV501002Message"  scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />

</HEAD>

<body>

 
<h3 align="center">Verificacion de Propuesta<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="client_both_enter, EPV5000"></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.credprop.JSEPV5010" >

     <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="800">
     <INPUT TYPE=HIDDEN NAME="SELCODE" VALUE="00">
     
   <table class="tableinfo">
      <tr id="trdark"> 
        <td nowrap> 
          <div align="right">Cliente :</div>
        </td>
        <td nowrap> 
          <%= userPO.getCusName()%>
        </td>
        <td nowrap> 
          <div align="right">Propuesta :</div>
        </td>
        <td nowrap> 
          <%= userPO.getIdentifier()%>
        </td>
      </tr>
   </table> 
   <br> 
   <table class="tableinfo">
      <tr id="trdark"> 
        <td nowrap> 
          <div align="center">Datos Verificables</div>
        </td>
        <td nowrap> 
          <div align="center">Comentarios</div>
        </td>
      </tr>
      <tr id="trclear"> 
        
        <td nowrap align="center"> 
          <select name="E02PVVTY" size=16 onchange="setInfo(this.options[this.selectedIndex].value, this.options[this.selectedIndex].text);">
            <option value="00" selected>Propuesta Aceptada</option>
            <option value="99">Propuesta Rechazada</option>
            <option value="10">Direccion</option>
            <option value="11">Empleo Cliente</option>
            <option value="12">Empleo Conyuge</option>
            <option value="A0">Tipo vivienda</option>
            <option value="B0">Estado vivienda</option>
            <option value="C0">Tipo Construccion</option>
            <option value="D0">Tipo Acceso</option>
            <option value="E0">Automovil</option>
            <option value="F0">Television</option>
            <option value="G0">Refrigerador</option>
            <option value="H0">VHS/DVD</option>
            <option value="I0">Cocina gas</option>
            <option value="J0">Cocina electrica</option>
            <option value="K0">Lavadora</option>
          </select>
        </td>
        <td nowrap valign="top"> 
          <INPUT TYPE=text id="TXTLABEL" name="selTEXT" value="Propuesta Aceptada">
          <% 
          String code="00";
          for (int i=0; i<16;i++) {
            switch (i) {
              case 0: code="00";
                      break;
              case 1: code="99";
                      break;
              case 2: code="10";
                      break;
              case 3: code="11";
                      break;
              case 4: code="12";
                      break;
              case 5: code="A0";
                      break;
              case 6: code="B0";
                      break;
              case 7: code="C0";
                      break;
              case 8: code="D0";
                      break;
              case 9: code="E0";
                      break;
              case 10: code="F0";
                      break;
              case 11: code="G0";
                      break;
              case 12: code="H0";
                      break;
              case 13: code="I0";
                      break;
              case 14: code="J0";
                      break;
              case 15: code="K0";
                      break; 
             }       
          %>
          <div id="div<%=code%>" <% if (!code.equals("00")) out.print("style=\"display:none\"");%>>
            
            Verificado : <input type="radio" name="E02PVVTY<%=code%>" value="Y" <% if (proposal.getField("E02PVVTY" + code).getString().equals("Y")) out.print("checked");%>>Si
          	<input type="radio" name="E02PVVTY<%=code%>" value="N" <% if (!proposal.getField("E02PVVTY" + code).getString().equals("Y")) out.print("checked");%>>No<br>
            <input type="text" name="E02PVVC1<%=code%>" size="32" maxlength="30" value="<%= proposal.getField("E02PVVC1" + code).getString().trim()%>"><br>
          	<input type="text" name="E02PVVC2<%=code%>" size="32" maxlength="30" value="<%= proposal.getField("E02PVVC2" + code).getString().trim()%>"><br>
          	<input type="text" name="E02PVVC3<%=code%>" size="32" maxlength="30" value="<%= proposal.getField("E02PVVC3" + code).getString().trim()%>"><br>
          	<input type="text" name="E02PVVC4<%=code%>" size="32" maxlength="30" value="<%= proposal.getField("E02PVVC4" + code).getString().trim()%>"><br>
          	<input type="text" name="E02PVVC5<%=code%>" size="32" maxlength="30" value="<%= proposal.getField("E02PVVC5" + code).getString().trim()%>"><br>
          	<input type="text" name="E02PVVC6<%=code%>" size="32" maxlength="30" value="<%= proposal.getField("E02PVVC6" + code).getString().trim()%>">
          </div>
          <% }%>
          
        </td>
        
      </tr>
    </table>
    <p align="center"> 
		<input id="EIBSBTN" type=submit name="Submit" value="Enviar"> 
 	</p> 
     
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
 </FORM>
</BODY>
</HTML>
 