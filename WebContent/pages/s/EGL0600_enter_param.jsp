<html>
<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id="msgList" class="datapro.eibs.beans.EGL060001Message"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>

<script language="JavaScript">

builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
	init(opth,field,bank,ccy,field1,field2,opcod);
	showPopupHelp();
}

function checkValues() {
	return true;
}

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
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEGL0600" >
	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
 	<INPUT TYPE=HIDDEN NAME="SEARCHC" VALUE="">
<h3 align="center">Consulta Partidas a Reconciliar<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="enter_param.jsp, EGL0600"></h3>
<hr size="4">
  <br><br><br><br><br><br>
  <table class="tableinfo">
    <tr> 
      <td valign="middle" align="center" height=33 width="49%"> 
        <div align="right">Banco : </div>
      </td>
      <td valign="middle" align="center" height=33 width="51%"> 
        <div align="left"> 
          <input type="text" name="E01TLDBNK"  size=3 maxlength="2" value="<%= userPO.getBank().trim()%>">
        </div>
      </td>
    </tr>
    <tr> 
      <td valign="middle" align="center" height=33 width="49%"> 
        <div align="right">Agencia : </div>
      </td>
      <td valign="middle" align="center" height=33 width="51%"> 
        <div align="left"> 
              <input type="text" name="E01TLDBRN" size="4" maxlength="3" value="<%= userPO.getBranch().trim()%>"
              oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01TLDBNK.value,'','','','')">
        </div>
      </td>
    </tr>
    <tr> 
      <td valign="middle" align="center" height=33 width="49%"> 
        <div align="right">Moneda : </div>
      </td>
      <td valign="middle" align="center" height=33 width="51%"> 
        <div align="left"> 
	          <input type="text" name="E01TDRTMC" size="4" maxlength="3" value="<%= userPO.getCurrency().trim()%>"
	           oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01TLDBNK.value,document.forms[0].E01TLDBRN.value,'','','')">
        </div>
      </td>
    </tr>
    <tr> 
      <td valign="middle" align="center" height=33 width="49%"> 
        <div align="right">Cuenta : </div>
      </td>
      <td valign="middle" align="center" height=33 width="51%"> 
        <div align="left"> 
	          <input type="text" name="E01TDRTGL" size="20" maxlength="20" value="<%= userPO.getAccNum().trim()%>" onkeypress="enterInteger()"
	                 oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01TDRTGL.value,'','','','')" >
        </div>
      </td>
    </tr>
    <tr> 
      <td valign="middle" align="center" height=33 width="49%"> 
        <div align="right">Importe Débito : </div>
      </td>
      <td valign="middle" align="center" height=33 width="51%"> 
        <div align="left"> 
		   	<input type="text" name="E01INPLMI" value="<%= msgList.getE01INPLMI() %>" size="15" maxlength="13" onkeypress="enterDecimal()">              
        </div>
      </td>
    </tr>
    <tr> 
      <td valign="middle" align="center" height=33 width="49%"> 
        <div align="right">Importe Crédito : </div>
      </td>
      <td valign="middle" align="center" height=33 width="51%"> 
        <div align="left"> 
		   	<input type="text" name="E01INPLMX" value="<%= msgList.getE01INPLMX() %>" size="15" maxlength="13" onkeypress="enterDecimal()">              
        </div>
      </td>
    </tr>
    
  </table>
  <br>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
          <tr bgcolor="#FFFFFF"> 
            <td width="33%"> 
              <div align="center"> 
                <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
              </div>
            </td>
          </tr>
          <tr bgcolor="#FFFFFF"> 
            <td> 
              <div align="center"> </div>
            </td>
          </tr>
        </table>
</form>
</body>
</html>