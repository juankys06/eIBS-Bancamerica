<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Tipo Protección Sobregiros</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

var typ;
typ = 'L';
// onclick="enter()"
function enter() {
	parent.results.window.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp?URL='<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0430?SCREEN=900@E01TYP=" + typ + "'"; //"@FromRecord=" + FromRecord + "@shrBank=" + Bank + "@shrAppCode=" +  AppCode + "@shrSelect=" + Type + "@shrClient=" + Client + "@shrAcc=" + Account + "'";
}
</SCRIPT>


</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body bgcolor="#FFFFFF">

<H3 align="center">Tipo Protección Sobregiros<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_enter_type, EDD0430"></H3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0430" target="results">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="900">
    <INPUT TYPE=HIDDEN NAME="ETYP" VALUE="900">
  </p>

  <table class="tableinfo" cellspacing="0" cellpadding="2" width="100%"border="0" >
   <tr> 
      <td > 
        <table id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">      
          <tr> 
            <td width="20%" nowrap>&nbsp;</td>
            <td width="20%" nowrap> 
              <input type="radio" name="E01TYP" value="L" checked onClick="typ='L'"> Crear Préstamo Protección Sobregiro (ODP) 
            </td>
            <td width="60%" nowrap> 
            </td>
          </tr>  
          <tr> 
            <td width="20%" nowrap>&nbsp;</td>
            <td width="20%" nowrap> 
              <input type="radio" name="E01TYP" value="H" onClick="typ='H'"> Establecer Relación con Existente Home Equity Line
            </td>
            <td width="60%" nowrap> 
            </td>
          </tr>                                     
        </table>      
      </td>
    </tr>  
  </table>  
 <p align="center"> 
    <input id="EIBSBTN" type="submit" name="Search" value="Buscar" >
  </p>  
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
</form>
</body>
</html>
