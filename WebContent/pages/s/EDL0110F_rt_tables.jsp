<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Cargos x Servicios</title>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id= "rates" class= "datapro.eibs.beans.EDL011001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


 
</head>

<body bgcolor="#FFFFFF">

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 
 %> 
 
 
<h3 align="center">Tasas Flotantes<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_tables,EDL0110F" ></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDL0110F">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  <INPUT TYPE=HIDDEN NAME="PAG_SCR" VALUE="NEW">
               
  <h4>Informaci&oacute;n  B&aacute;sica</h4>
 
  <table class="tableinfo">
      <tr > 
        <td nowrap> 
          
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left" >
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">N&uacute;mero de Tabla :</div>
            </td>
            <td nowrap width="70%"> 
              <div align="left"> 
                <input type="text" name="E01PRRTBL" size="3" maxlength="2" value="<%= rates.getE01PRRTBL()%>">
              </div>
            </td>            
          </tr>
          <tr id="trclear">
            <td nowrap width="30%"> 
              <div align="right">Descripci&oacute;n :</div>
            </td>
            <td nowrap width="70%">                                                                         
              <input type="text" name="E01PRRNME" size="36" maxlength="35" value="<%= rates.getE01PRRNME()%>">              
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Tipo  :</div>
            </td>
            <td nowrap width="70%">  
              <select name="E01PRRTYP">
                <option value="1" <% if (rates.getE01PRRTYP().equals("1")) out.print("selected"); %>>Efectiva</option>
                <option value=""  <% if (rates.getE01PRRTYP().equals("")) out.print("selected"); %>>Nominal</option>
              </select>              
              
            </td> 
          </tr> 
          <tr id="trclear">
            <td nowrap width="30%"> 
              <div align="right">Tasa Primaria :</div>
            </td>
            <td nowrap width="70%"> 
              <div align="left"> 
                <input type="text" name="E01PRRPRT" size="6" maxlength="5" value="<%= rates.getE01PRRPRT()%>">
              </div>
            </td>
          </tr> 
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Tasa Secundaria :</div>
            </td>
            <td nowrap width="70%"> 
              <div align="left"> 
                <input type="text" name="E01PRRSRT" size="6" maxlength="5" value="<%= rates.getE01PRRSRT()%>"> 
              </div>
            </td>  
          </tr>           
          <tr id="trclear">
            <td nowrap width="30%"> 
              <div align="right">Fecha    Efectiva:</div>
            </td>
            <td nowrap width="70%" > 
              <div align="left">         
                <input type="text" name="E01PRREFM" size="3" maxlength="2" value="<%= rates.getE01PRREFM()%>">
                <input type="text" name="E01PRREFD" size="3" maxlength="2" value="<%= rates.getE01PRREFD()%>">
                <input type="text" name="E01PRREFY" size="3" maxlength="2" value="<%= rates.getE01PRREFY()%>">
              </div>
            </td>
          </tr>
        </table>
        </td>
    </tr>
  </table>
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</form>
</body>
</html>
