<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css"> 
<TITLE>Manejo de Clientes</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function CheckACC(){
if ( document.forms[0].E01PVMIDN.value.length < 1) {
  alert("Ingrese un Número de Identificación");
  document.forms[0].E01PVMIDN.value='';
  document.forms[0].E01PVMIDN.focus();
}
else {
  document.forms[0].submit();
  }
}

</SCRIPT>


<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />

</HEAD>

<body>

 
<h3 align="center">Manejo de Propuestas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="proposal_verif_enter, EPV5010"></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.credprop.JSEPV5010" >

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  <INPUT TYPE=HIDDEN NAME="CUSNUM" VALUE="">
    
  <table class="tbenter">
    <tr>
      <td align="center"> 
        <table class="tableinfo">
          <tr > 
            <td nowrap> 
              <table cellspacing="0" cellpadding="2" width="100%" border="0">
                <tr id="trdark"> 
                  <td nowrap align="right" valign="middle" colspan="3"> 
                    <div align="left">Seleccione uno de los siguientes criterios 
                      de b&uacute;squeda</div>
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td nowrap align="right" valign="middle" colspan="2">Mostrar 
                    las propuestas asociadas al cliente : </td>
                  <td nowrap width="46%" align="left" valign="middle"> 
                    <input type="radio" name="TYPSEL" value="1" checked>
                    <input type="text" name="E01PVMIDN" size="17" maxlength="15" onKeyPress="enterInteger()">
                    <a href="javascript:GetCustomerDescId('CUSNUM','','E01PVMIDN')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="middle" border="0" ></a> 
                  </td>
                </tr>
                <tr  id="trdark"> 
                  <td nowrap colspan="2" align="right" valign="middle"> Mostrar 
                    todas las propuestas asociadas al usuario :</td>
                  <td nowrap width="46%" align="left" valign="middle">
                    <input type="radio" name="TYPSEL" value="2">
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td nowrap colspan="2" align="right" valign="middle"> Mostrar 
                    todas las propuestas con Status :</td>
                  <td nowrap width="46%" align="left" valign="middle"> 
                    <input type="radio" name="TYPSEL" value="3">
                    <select name="E02PVMSTS">
                        <option value=" " selected>Todas</option>
                        <option value="A">Ingresada</option>
                        <option value="B">Ofertada</option>
                        <option value="C">Evaluada</option>
                        <option value="D">Aceptada Oferta</option>                        
                    </select>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
        <p align="center"> 
						
          <input id="EIBSBTN" type=submit name="Submit" value="Enviar" > 
 				 </p> 
     </td>        
    </tr>
  </table>

<script language="JavaScript">
  document.forms[0].E01PVMIDN.focus();
  document.forms[0].E01PVMIDN.select();
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
 </FORM>
</BODY>
</HTML>
 