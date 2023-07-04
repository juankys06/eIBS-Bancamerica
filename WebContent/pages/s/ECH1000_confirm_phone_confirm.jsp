<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Split</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="phConfir" class="datapro.eibs.beans.ECH100001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


</head>
<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }


%>
<div align="center"> 
  <h3>Sistema de Reserva de Cheques<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="enter_phone_confirm.jsp,ECH1000"> 
  </h3>
</div>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH1000" >
  <h4> 
    <input type="hidden" name="SCREEN"  value="2" >
    La Reserva de Cheques ha sido procesada satisfactoriamente. </h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width=50%> 
              <div align="right">N&uacute;mero de Cuenta : </div>
            </td>
            <td nowrap> 
              <div><%= phConfir.getE01CHPACC()%></div>
            </td>
         </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">N&uacute;mero de Cheque : </div>
            </td>
            <td nowrap> 
              <div><%= phConfir.getE01CHMCKN()%></div>
            </td>
           </tr>
          <tr id="trdark">
            <td nowrap>
              <div align="right">Valor del Cheque : </div>
            </td>
            <td nowrap>
              <div><%= phConfir.getE01CHMAMT()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Nombre Negocio : </div>
            </td>
            <td nowrap> 
              <div><%= phConfir.getE01CONNAME()%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Clave de Cheque : </b></div>
            </td>
            <td nowrap> 
              <div><%= phConfir.getE01CONCLAV()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">N&uacute;mero Cliente : </div>
            </td>
            <td nowrap> 
              <div><%= phConfir.getE01CHPCUN()%></div>
            </td>
          </tr> 
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Nombre Cliente : </div>
            </td>
            <td nowrap> 
              <div><%= phConfir.getE01CHPNA1()%></div>
            </td>
          </tr>    
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Moneda : </div>
            </td>
            <td nowrap> 
              <div><%= phConfir.getE01CHPCCY()%></div>
            </td>
          <tr id="trdarkr"> 
            <td nowrap > 
              <div align="right">Producto : </div>
            </td>
            <td nowrap > 
              <div><%= phConfir.getE01CHPPRO()%></div>
            </td>
          </tr>   
        </table>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>
