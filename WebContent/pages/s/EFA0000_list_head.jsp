<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>I.B.S.</title>
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id="facusdata" class="datapro.eibs.beans.EFA000002Message"  scope="session" />
<jsp:useBean id="facuslist" class="com.datapro.generics.BeanList"  scope="session" />

<script language="JavaScript">

function goAction(op) {
  <% if(facusdata.getH02FLGWK3().equals("R")){ %>
       document.forms[0].SCREEN.value='500';
       document.forms[0].submit();          
  <% }else{ %>
       document.forms[0].SCREEN.value='100';
       document.forms[0].submit();        
  <% } %>
}
</script>  
</head>

<body>

 <h3 align="center"><%= facusdata.getH02FLGWK3().equals("R")?"Reimpresion":"Impresion" %> de Comprobante Fiscal<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="enter_customer,EFA0000"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSEFA0000" target="main">
  <p>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="<%= facusdata.getH02FLGWK3().equals("R")?"100":"100" %>">
    <INPUT TYPE=HIDDEN NAME="E01OPESEL" VALUE="<%= facusdata.getH02FLGWK3() %>">         
  </p>
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap align="right" valign="middle" >Cliente :</td>
            <td nowrap align="left" valign="middle"  >
                <INPUT type="text" name="E02CUSCUN" value="<%= facusdata.getE02CUSCUN() %>" maxlength="10" size="10" readonly> 
                <INPUT type="text" name="E02CUSNA1" value="<%= facusdata.getE02CUSNA1() %>"  size="50" readonly> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>

    <table class="tbenter" width="100%">
    <tr> 
      <TD class=TDBKG width="50%"> 
        <div align="center"><a href="javascript:goAction(2)"><b>Continuar</b></a></div>
      </TD>
    </tr>
  </table>

</form>
</body>
</html>
