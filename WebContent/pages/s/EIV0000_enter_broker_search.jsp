<html>
<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>



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
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEWD0312P" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1">
  <h3 align="center">B&uacute;squeda - Tablas de Comisi&oacute;n <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="enter_broker_search.jsp,EIV0000"> 
  </h3>
  <hr size="4">
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <table  class="tableinfo" align="center" >
    <tr> 
      <td> 
        <table id="tableinfo" width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr id="trdark"> 
            <td>&nbsp;</td>
            <td width="20%" nowrap> 
              <div align="right">Tipo de Producto : </div>
            </td>
            <td width="70%" nowrap> 
              <input type="hidden" name="DOCTYP">
              <input type="radio" name="CDOCTYP" value="BND" onClick="document.forms[0].DOCTYP.value ='BND'">
              Bonos 
              <input type="radio" name="CDOCTYP" value="EQT" onClick="document.forms[0].DOCTYP.value ='EQT'">
              Acciones 
              <input type="radio" name="CDOCTYP" value="MUT" onClick="document.forms[0].DOCTYP.value ='MUT'">
              Fondos Mutuos</td>
          </tr>
          <tr id="trclear"> 
            <td>&nbsp;</td>
            <td width="20%" nowrap> 
              <div align="right">N&uacute;mero de Tabla : </div>
            </td>
            <td width="70%" nowrap> 
              <input type="text" name="TABLE" size="4" maxlength="2"  >
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="10%" align="center">&nbsp;</td>
            <td width="20%" nowrap> 
              <div align="right">Tipo de Tabla :</div>
            </td>
            <td width="70%" nowrap> 
              <input type="hidden" name="TABTYP">
              <input type="radio" name="CTABTYP" value="P" onClick="document.forms[0].TABTYP.value ='P'">
              Producto 
              <input type="radio" name="CTABTYP" value="C" onClick="document.forms[0].TABTYP.value ='C'">
              Cliente 
              <input type="radio" name="CTABTYP" onClick="document.forms[0].TABTYP.value =''">
              Gen&eacute;rico </td>
          </tr>
          <tr id="trclear"> 
            <td width="10%" align="center">&nbsp;</td>
            <td width="20%" nowrap > 
              <div align="right">N&uacute;mero de Cliente : </div>
            </td>
            <td width="70%" nowrap > 
              <input type="text" name="CUSTOMER" size="12" maxlength="9">
              <input type="text" name="CUNDSC" size="25" maxlength="25">
              <a href="javascript:GetCustomerDescId('CUSTOMER','CUNDSC','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
              o</td>
          </tr>
          <tr id="trdark"> 
            <td width="10%" align="center">&nbsp;</td>
            <td width="20%" nowrap > 
              <div align="right">C&oacute;digo de Producto : </div>
            </td>
            <td width="70%" nowrap > 
              <div align="left"> 
                <input type="text" name="PRODUCT"  size=4 maxlength="4">
                <input type="text" name="DESCPROD"  size=40 maxlength="35">
                <a href="javascript:GetProduct('PRODUCT','DESCPROD','IV','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a> 
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <div align="center"> 
	   
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>      
</form>
</body>
</html>
