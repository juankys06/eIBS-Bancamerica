<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Proteccion de Cheques</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="chkDet" class= "datapro.eibs.beans.ECH033501Message"  scope="session"/>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

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


<H3 align="center">Proteccion de Cheques (Nuevo)<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ga_new_detail.jsp , ERA0010"></H3> 
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH0335" >
  <input type=HIDDEN name="SCREEN" value="3">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
  <INPUT TYPE=HIDDEN NAME="E01CKCACC" VALUE="<%= userPO.getIdentifier().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E01CUSCUN" VALUE="<%= userPO.getCusNum().trim() %>">
  <INPUT TYPE=HIDDEN NAME="E01CUSNA1" VALUE="<%= userPO.getCusName().trim()%>">
  
    <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark">
            <td nowrap > 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap> 
              <%= userPO.getIdentifier().trim()%>
            </td> 
            <td nowrap > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <%= userPO.getCusNum().trim() %>
              </div>
            </td>
            <td nowrap> 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <%= userPO.getCusName().trim()%>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  <h4>Informaci&oacute;n B&aacute;sica</h4>
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">No. de Cheque :</div>
            </td>
            <td nowrap>
              <INPUT type="text" name="E01CKCCKN" size="11" maxlength="9" onKeypress="enterInteger()" value="<%= chkDet.getE01CKCCKN().trim()%>">
            </td>         
          </tr>          
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Monto :</div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01CKCAMT" size="15" maxlength="15" onKeypress="enterDecimal()" value="<%= chkDet.getE01CKCAMT().trim() %>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Beneficiario :</div>
            </td>
            <td nowrap >
              <INPUT type="text" name="E01CKCBNF" size="36" maxlength="35" value="<%= chkDet.getE01CKCBNF().trim()%>">
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
  </p>                      

  </form>
</body>
</html>
