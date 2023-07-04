<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Confirmacion</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<!--meta http-equiv="Refresh" CONTENT="3;url='javascript:reCall()'"-->
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "msgCHKB" class= "datapro.eibs.beans.ECH030601Message"  scope="session" />


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="javascript">
//<!-- Hide from old browsers
function reCall() {
  	top.opener.location.href = "<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH0306?SCREEN=100";
	top.close();
}

 setTimeout("reCall();", 7000);
//-->
</script>
</head>

<body>

<H3 align="center">Solicitud de Chequeras sin Personalizacion<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="chb_req_confirm,ECH0306"></H3> 
<hr size="4">
 <FORM >
  <table class="tableinfo">
    <tr > 
      <td > 
        <p align="center">&nbsp; </p>
        <table width="100%">
          <tr> 
            
            <td width="34%" height="20"> 
              <div align="right"></div>
            </td>
            <td width="64%" height="20"> 
              <div align="center"></div>
            </td>
          </tr>
          <tr> 
            
            <td width="34%"> 
              <div align="right"></div>
            </td>
            <td width="64%">&nbsp;</td>
          </tr>
          <tr> 
            <td width="34%"> 
              <div align="right">Numero de Solicitud : </div>
            </td>
            <td width="64%"><%= msgCHKB.getE01NUMSOL()%> </td>
          </tr>
          <tr> 
            <td width="34%"> 
              <div align="right"></div>
            </td>
            <td width="64%">&nbsp;</td>
          </tr>
          <tr>
            <td width="34%">&nbsp;</td>
            <td width="64%">&nbsp;</td>
          </tr>
          <tr> 
            
            <td colspan=2><p align=center> La solicitud ha sido procesada satisfactoriamente. Por 
              favor espere...</p></td>
          </tr>
        </table>
        
      </td>
    </tr>
  </table>
  
</FORM>
</body>
</html>
