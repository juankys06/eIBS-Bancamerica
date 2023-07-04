<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Confirmacion</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<!--meta http-equiv="Refresh" CONTENT="7;url='javascript:document.forms[0].submit();"-->
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "rtFinish" class= "datapro.eibs.beans.EFT000015Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="javascript">
 function finish(){
	if (document.getElementById("H15FLGWK2").value == 'A') {
		self.window.location.href = "<%=request.getContextPath()%>/pages/background.jsp";
	} else {
		document.forms[0].submit();
	}
  }

 setTimeout("finish();", 7000);
 
</SCRIPT>

</head>


<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 
<h3 align="center"><%	if(userPO.getOption().equals("RT")){%>
	Cuentas de Detalle
<%
	}
	else if(userPO.getOption().equals("SV")){%>
	Cuentas de Ahorro
	<%}%>
</h3>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_confirm, EDD0000"> 
<hr size="4">
 <FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="44">
  <INPUT TYPE=HIDDEN NAME="H15FLGWK2" VALUE="<%= rtFinish.getH15FLGWK2().trim()%>">
  <table class="tableinfo">
    <tr > 
      <td height="409"> 
        <p align="center">&nbsp; </p>
        <table width="100%">
          <tr> 
            <td width="2%" height="20">&nbsp;</td>
            <td width="34%" height="20"> 
              <div align="right"></div>
            </td>
            <td width="64%" height="20"> 
              <div align="center"></div>
            </td>
          </tr>
          <tr> 
            <td width="2%">&nbsp;</td>
            <td width="34%"> 
              <div align="right"></div>
            </td>
            <td width="64%">&nbsp;</td>
          </tr>
          <tr> 
            <td width="2%">&nbsp;</td>
            <td width="34%"> 
              <div align="right">Numero de Cuenta : </div>
            </td>
            <td width="64%"><%= datapro.eibs.master.Util.justifyRight(rtFinish.getE15ACCNUM().trim(),12)%> </td>
          </tr>
          <tr> 
            <td width="2%">&nbsp;</td>
            <td width="34%"> 
              <div align="right"></div>
            </td>
            <td width="64%">&nbsp;</td>
          </tr>
          <tr> 
            <td width="2%" height="15">&nbsp;</td>
            <td width="34%" height="15"> 
              <div align="right">A favor de :</div>
            </td>
            <td width="64%" height="15"> 
              <div align="left"> <%= rtFinish.getE15CUSNA1().trim()%> </div>
            </td>
          </tr>
          <tr> 
            <td width="2%">&nbsp;</td>
            <td width="34%"> 
              <div align="right">N&uacute;mero de Cliente:</div>
            </td>
            <td width="64%"><%= datapro.eibs.master.Util.justifyRight(rtFinish.getE15CUSCUN().trim(),9)%></td>
          </tr>
          <tr> 
            <td width="2%">&nbsp;</td>
            <td width="34%"> 
              <div align="right">Moneda :</div>
            </td>
            <td width="64%"> <%= rtFinish.getE15CCYCDE().trim()%> - <%= rtFinish.getE15DSCCCY().trim()%></td>
          </tr>
          <tr> 
            <td width="2%">&nbsp;</td>
            <td width="34%"> 
              <div align="right"></div>
            </td>
            <td width="64%">&nbsp;</td>
          </tr>
          <tr> 
            <td width="2%" height="19">&nbsp;</td>
            <td width="34%" height="19"> 
              <div align="right">Banco :</div>
            </td>
            <td width="64%" height="19"> <%= rtFinish.getE15BANKNM().trim()%> 
            </td>
          </tr>
          <tr> 
            <td width="2%" height="21">&nbsp;</td>
            <td width="34%" height="21"> 
              <div align="right">Agencia :</div>
            </td>
            <td width="64%" height="21"><%= rtFinish.getE15BRNNUM().trim()%> - 
              <%= rtFinish.getE15BRNNME().trim()%></td>
          </tr>
          <tr> 
            <td width="2%">&nbsp;</td>
            <td width="34%"> 
              <div align="right">Producto :</div>
            </td>
            <td width="64%"><%= rtFinish.getE15PROCDE().trim()%> - <%= rtFinish.getE15DSCPRO().trim()%></td>
          </tr>
          <tr> 
            <td width="2%">&nbsp;</td>
            <td width="34%"> 
              <div align="right"></div>
            </td>
            <td width="64%">&nbsp;</td>
          </tr>
          <tr> 
            <td width="2%">&nbsp;</td>
            <td width="34%"> 
              <div align="right">Fecha de Apertura :</div>
            </td>
            <td width="64%"> <%= Util.formatDate(rtFinish.getE15OPNDT1(),rtFinish.getE15OPNDT2(),rtFinish.getE15OPNDT3())%></td>
          </tr>
          <tr> 
            <td width="2%">&nbsp;</td>
            <td width="34%"> 
              <div align="right">Dep&oacute;sito Inicial :</div>
            </td>
            <td width="64%"><%= Util.fcolorCCY(rtFinish.getE15OPNAMT().trim())%></td>
          </tr>
          <tr> 
            <td width="2%">&nbsp;</td>
            <td width="34%">&nbsp;</td>
            <td width="64%"><%= rtFinish.getE15LETOAM().trim()%></td>
          </tr>
          <tr>
            <td width="2%">&nbsp;</td>
            <td width="34%">&nbsp;</td>
            <td width="64%">&nbsp;</td>
          </tr>
          <tr> 
            <td width="2%">&nbsp;</td>
            <td width="34%"> 
              <div align="right"></div>
            </td>
            <td width="64%"> La Cuenta ha sido procesada satisfactoriamente. Por 
              favor espere...</td>
          </tr>
        </table>
        
      </td>
    </tr>
  </table>
  <div align="center"> 
    <p>&nbsp;</p>
    </div>
</form>
</body>
</html>
