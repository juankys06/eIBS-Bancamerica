<html> 
<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" 	scope="session" />
<jsp:useBean id="balance" class="datapro.eibs.beans.EGL075001Message" scope="session" />

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
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.accounting.JSEGL0750" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  
  <h3 align="center">Consulta de Balances<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="inq_enter.jsp,EGL0750"> 
  </h3>
  <hr size="4">
<p></p>
  <table class="tableinfo" align="center" width="85%">
    <tr> 
      <td> 
        <table width="100%" border="0" cellspacing="1" cellpadding="0">
          <TR>
            <td nowrap width="50%" align="right">Banco :</td>
            <TD nowrap width="50%" align="left"> 
              <INPUT type="text" name="E01GLBBNK" size="3" maxlength="2">
            </TD>
				
			</TR>
			<tr>
            <td nowrap width="50%" align="right">Sucursal :</td>
            <td nowrap width="50%" align="left"> 
              <input type="text" name="E01GLBBRN" size="4" maxlength="3"  >
              <a href="javascript:GetBranch('E01GLBBRN','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"  ></a>
            </td>
          </tr>
          <tr>
            <td nowrap width="50%" align="right">Clase de Cuenta :</td>
            <td nowrap width="50%" align="left"> 
              <input type="text" name="E01GLBCLS" size="3" maxlength="2"  >
              <a href="javascript:GetCodeDescCNOFC('E01GLBCLS','','01')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
            </td>
          </tr>
          <tr>
            <td nowrap width="50%" align="right">Descripcion :</td>
            <td nowrap width="50%" align="left"> 
              <input type="text" name="E01GLBDSC" size="30" maxlength="30"  >
            </td>
          </tr>
          <tr>
            <td nowrap width="50%" align="right">Moneda :</td>
            <td nowrap width="50%" align="left"> 
              <input type="text" name="E01GLBCCY" size="4" maxlength="3"  >
              <a href="javascript:GetCurrency('E01GLBCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr>
            <td nowrap width="50%" align="right">Tipo de cuenta :</td>
            <td nowrap width="50%" align="left"> 
              <input type="text" name="E01GLBATY" size="5" maxlength="4"  >
              <a href="javascript:GetCodeDescCNOFC('E01GLBATY','','04')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
            </td>
          </tr>
          <tr>
            <td nowrap width="50%" align="right">Centro de Costo :</td>
            <td nowrap width="50%" align="left"> 
              <input type="text" name="E01GLBCCN" size="7" maxlength="6"  >
              <a href="javascript:GetCostCenter('E01GLBCCN','01')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0" ></a> 
            </td>
          </tr>
          <tr>
            <td nowrap width="50%" align="right">Cuenta Contable :</td>
            <td nowrap width="50%" align="left"> 
              <input type="text" name="E01GLBGLN" size="17" maxlength="16"  >
              <a href="javascript:GetLedger('E01GLBGLN',document.forms[0].E01GLBBNK.value,document.forms[0].E01GLBCCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
         <tr>
         	<td nowrap width="50%" align="right">Nivel  Contable :</td>
         	<td nowrap width="50%" align="left">
         	 <select name="E01GLBNIV">
				<option value=" " <%if (balance.getE01GLBNIV().equals(" ")) out.print("selected"); %>>Ninguno</option>
                <option value="1" <%if (balance.getE01GLBNIV().equals("1")) out.print("selected"); %>>Nivel 1</option>
                <option value="2" <%if (balance.getE01GLBNIV().equals("2")) out.print("selected"); %>>Nivel 2</option>
                <option value="3" <%if (balance.getE01GLBNIV().equals("3")) out.print("selected"); %>>Nivel 3</option>
                <option value="4" <%if (balance.getE01GLBNIV().equals("4")) out.print("selected"); %>>Nivel 4</option>
                <option value="5" <%if (balance.getE01GLBNIV().equals("5")) out.print("selected"); %>>Nivel 5</option>
                <option value="6" <%if (balance.getE01GLBNIV().equals("6")) out.print("selected"); %>>Nivel 6</option>
                <option value="7" <%if (balance.getE01GLBNIV().equals("7")) out.print("selected"); %>>Nivel 7</option>
                <option value="8" <%if (balance.getE01GLBNIV().equals("8")) out.print("selected"); %>>Nivel 8</option>
              </select>
             </tr>
          </table>
      </td>
    </tr>
  </table>
  
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
      
</form>
</body>
</html>
