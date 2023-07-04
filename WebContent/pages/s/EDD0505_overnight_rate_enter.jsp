<html>
<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>

<script language="JavaScript">
function enter(){
	  document.forms[0].submit();
	 }
</script>

</head>
<body>

 
 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
    }
%>
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDD0505" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="100">
  <h3 align="center">Tabla de Tasa de Colocaciones a 24 Horas (Overnight)<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="overnight_rate_enter.jsp,EDD0505"> 
  </h3>
  <hr size="4">
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <table id="tbenter" align="center" style="width:85%" border="1">
    <tr> 
      <td> 
        <table id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr> 
            <td width="12%" nowrap>&nbsp;</td>
            <td width="10%" nowrap> 
              <div align="right">Banco :</div>
            </td>
            <td nowrap colspan="4"> 
              <input type="text" name="E01ONRBNK" size="3" maxlength="2"  >
            </td>
          </tr>
          <tr> 
            <td width="12%" nowrap>&nbsp;</td>
            <td width="10%" nowrap> 
              <div align="right">Moneda :</div>
            </td>
            <td nowrap colspan="4"> 
              <input type="text" name="E01ONRCCY" size="4" maxlength="3"  >
              <a href="javascript:GetCurrency('E01ONRCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a> 
            </td>
          </tr>
          <tr> 
            <td width="12%" nowrap rowspan="2">&nbsp;</td>
            <td width="10%" nowrap> 
              <div align="right"></div>
            </td>
            <td width="2%" nowrap> 
              <div align="center"></div>
            </td>
            <td nowrap width="2%"> 
              <div align="center"></div>
            </td>
            <td nowrap width="2%"> 
              <div align="center"></div>
            </td>
            <td nowrap width="72%"> 
              <div align="center"></div>
            </td>
          </tr> 
          <tr>
            <td width="10%" nowrap> 
              <div align="right">Fecha Efectiva :</div>
            </td>
            <td width="2%" nowrap> 
              <div align="center"> 
                <input type="text" name="E01ONREMM" size="3" maxlength="2"  >
              </div>
            </td>
            <td width="2%" nowrap> 
              <div align="center"> 
                <input type="text" name="E01ONREDD" size="3" maxlength="2"  >
              </div>
            </td>
            <td width="2%" nowrap> 
              <div align="center"> 
                <input type="text" name="E01ONREYY" size="3" maxlength="2"  >
              </div>
            </td>
            <td width="72%" nowrap>&nbsp;</td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
        <p>&nbsp;</p>
  <p><BR>
  </p>
  <p align="center">&nbsp; </p>
      
</form>
</body>
</html>
