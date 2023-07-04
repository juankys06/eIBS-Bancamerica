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
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0200I" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1">
  <h3 align="center">B&uacute;squeda - Consulta Formatos Libres <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="enter_search.jsp,ESWF200I"> 
  </h3>
  <hr size="4">
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <table  class="tableinfo" align="center" >
    <tr id="trdark"> 
      <td> 
        <table id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr id="trdark">
            <td>&nbsp;</td>
            <td width="20%" nowrap>
              <div align="right">Usuario : </div>
            </td>
            <td width="70%" nowrap>
              <input type="text" name="USERSWIFT" size="13" maxlength="12"  >
            </td>
          </tr>
          <tr id="trclear"> 
            <td>&nbsp;</td>
            <td width="20%" nowrap> 
              <div align="right">Identificaci&oacute;n Swift :</div>
            </td>
            <td width="70%" nowrap> 
              <input type="text" name="SWIFTID" size="13" maxlength="12"  >
              <a href="javascript:GetSwiftId('SWIFTID')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absmiddle" border="0"></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td>&nbsp;</td>
            <td width="20%" nowrap> 
              <div align="right"> Formato : </div>
            </td>
            <td width="70%" nowrap> 
              <input type="text" name="FORMAT" size="4" maxlength="3">
              <a href="javascript:GetCode('FORMAT','STATIC_Swif_FF_types.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="10%" align="center">&nbsp;</td>
            <td width="20%" nowrap> 
              <div align="right">Referencia :</div>
            </td>
            <td width="70%" nowrap> 
              <input type="text" name="REFERENCE" size="17" maxlength="16">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="10%" align="center">&nbsp;</td>
            <td width="20%" nowrap > 
              <div align="right">Fecha :</div>
            </td>
            <td width="70%" nowrap > 
              <input type="text" name="DATE1"  size=3 maxlength="2" >
              <input type="text" name="DATE2"  size=3 maxlength="2" >
              <input type="text" name="DATE3"  size=3 maxlength="2" >
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <div align="center"> 
	   
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>      
</form>
</body>
</html>
