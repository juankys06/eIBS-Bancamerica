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
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSESD0005" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  <h3 align="center">Instrucciones de Pago del Banco<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="bank_instructions_enter.jsp,ESD0005"> 
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
            <td>&nbsp;</td>
            <td width="20%" nowrap> 
              <div align="right">Banco :</div>
            </td>
            <td width="70%" nowrap> 
              <input type="text" name="BANK" size="3" maxlength="2"  > Sucursal : 
			  <INPUT type="text" name="BRANCH" size="4" maxlength="3">
				 <A href="javascript:GetBranch('BRANCH','')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda"
					align="bottom" border="0">
				</A>
			</td>
          </tr>
          <tr> 
            <td>&nbsp;</td>
            <td width="20%" nowrap> 
              <div align="right">Código de Moneda : </div>
            </td>
            <td width="70%" nowrap> 
              <input type="text" name="CCY"  size=4 maxlength="3">
              <a href="javascript:GetCurrency('CCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0" ></a> 
            </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td width="20%" nowrap>
              <div align="right">Tipo :</div>
            </td>
            <td width="70%" nowrap>
              <input type="hidden" name="E06TYP" value="S">
              <input type="radio" name="CE06TYP" value="S" onClick="document.forms[0].E06TYP.value='S'"
			   checked>
              Standard 
              <input type="radio" name="CE06TYP" value="C" onClick="document.forms[0].E06TYP.value='F'"
			  >
              Fed</td>
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
