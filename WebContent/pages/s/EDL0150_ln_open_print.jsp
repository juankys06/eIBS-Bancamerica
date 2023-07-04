<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Documentación</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="javascript">
 function finish(){
 self.window.location.href = "<%=request.getContextPath()%>/pages/background.jsp";
 }
 
</SCRIPT>

</head>

<body>


<% 
 if ( !error.getERRNUM().equals("0")  ) {
    error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 
<h3 align="center">Pr&eacute;stamos</h3>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_open_print.jsp,EDL0150"> 
<hr size="4">
 <FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0150" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="22">
  <table width="90%" class="tableinfo">
    <tr bordercolor="#FFFFFF" > 
      <td height="409">
        <table width="100%">
          <tr> 
            <td width="2%" height="20">&nbsp;</td>
            <td width="18%" height="20"> 
              <div align="right"></div>
            </td>
            <td height="20" colspan="2"> 
              <div align="center"><font size="3"><b>Banco :</b><i> <%= userPO.getBank().trim()%></i></font></div>
            </td>
          </tr>
          <tr> 
            <td width="2%">&nbsp;</td>
            <td width="18%"> 
              <div align="right"></div>
            </td>
            <td colspan="2"> 
              <div align="center"><font size="3"><b>Agencia :</b><i><%= userPO.getBranch().trim()%></i></font></div>
            </td>
          </tr>
          <tr> 
            <td width="2%">&nbsp;</td>
            <td width="18%"> 
              <div align="right"></div>
            </td>
            <td colspan="2">&nbsp;</td>
          </tr>
          <tr> 
            <td width="2%">&nbsp;</td>
            <td width="18%"> 
              <div align="right"><font size="3">Pr&eacute;stamo N&uacute;mero 
                :<i> </i></font></div>
            </td>
            <td colspan="2"><font size="3"><i><%= userPO.getIdentifier().trim()%> 
              </i></font></td>
          </tr>
          <tr> 
            <td width="2%">&nbsp;</td>
            <td width="18%"> 
              <div align="right"></div>
            </td>
            <td colspan="2">&nbsp;</td>
          </tr>
          <tr> 
            <td width="2%" height="15">&nbsp;</td>
            <td width="18%" height="15"> 
              <div align="right"><font size="2"><font size="3">A nombre de :</font></font><font size="3"><i></i></font></div>
            </td>
            <td height="15" colspan="2"> 
              <div align="left"><font size="2"> </font><font size="3"><i> <%= userPO.getHeader2().trim()%> 
                - <%= userPO.getHeader3().trim()%> </i></font></div>
            </td>
          </tr>
          <tr> 
            <td width="2%">&nbsp;</td>
            <td width="18%"> 
              <div align="right"><font size="3">Moneda :</font></div>
            </td>
            <td colspan="2"> <font size="3"><i><%= userPO.getCurrency().trim()%></i></font></td>
          </tr>
          <tr> 
            <td width="2%">&nbsp;</td>
            <td width="18%"> 
              <div align="right"><font size="3">Producto :</font></div>
            </td>
            <td colspan="2"><font size="3"><i><%= userPO.getHeader1().trim()%> 
              - <%= userPO.getHeader4().trim()%></i></font></td>
          </tr>
          <tr> 
            <td width="2%">&nbsp;</td>
            <td width="18%">&nbsp;</td>
            <td colspan="2">&nbsp;</td>
          </tr>
          <tr> 
            <td width="2%">&nbsp;</td>
            <td width="18%"> 
              <div align="right"><font size="3">Monto :</font></div>
            </td>
            <td colspan="2"><font size="3"><i><%= userPO.getHeader5().trim()%></i></font></td>
          </tr>
          <tr> 
            <td width="2%">&nbsp;</td>
            <td width="18%"> 
              <div align="right"><font size="3">T&eacute;rmino :</font></div>
            </td>
            <td colspan="2"><%= userPO.getHeader6().trim()%> - <% if(userPO.getHeader7().equals("Y")) out.print("Año(s)");
              				else if(userPO.getHeader7().equals("M")) out.print("Mes(es)");
							else out.print("Día(s)");%></td>
          </tr>
          <tr>
            <td width="2%">&nbsp;</td>
            <td width="18%">&nbsp;</td>
            <td colspan="2">&nbsp;</td>
          </tr>
          <tr> 
            <td width="2%">&nbsp;</td>
            <td width="18%"> 
              <div align="right"></div>
            </td>
            <td colspan="2"><font size="2">El Contrato ya ha sido procesado, si 
              desea ingresar otros datos oprima el bot&oacute;n de Continuar o 
              el de Finalizar para terminar</font></td>
          </tr>
        </table>
        
      </td>
    </tr>
  </table>
    <p>&nbsp;</p>
  <table class="tbenter" width="50%" border="0" align="center">
    <tr> 
        <td  width="50%" align="center"> 
            <input type=image class=imgfilter name="Submit" src="<%=request.getContextPath()%>/images/s/bt_enviar.gif" onMouseDown="this.className='' "onMouseUp="this.className='imgfilter' ">
        </td> 
        <td width="50%" align="center">
		   <img name="Submit" class=imgfilter src="<%=request.getContextPath()%>/images/s/bt_finalizar.gif" onClick="finish();"  onMouseDown="this.className='' "onMouseUp="this.className='imgfilter' "> 
		</td>
     </tr>
    </table>
    <p>&nbsp; </p>
  </form>
</body>
</html>
