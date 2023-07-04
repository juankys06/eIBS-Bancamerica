<%@ page import ="datapro.eibs.master.Util" %>
<html>
<head>
<title>Tabla de Porcentajes a Otorgar</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id="Edp088801" class="datapro.eibs.beans.EDP088801Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
</head>
<BODY>
<script language="JavaScript">

function getParams(currrow,cacti,dacti) {
	document.forms[0].CURRENTROW.value = currrow;
}

function submitThis(option) {
  var okdel = false;
  document.forms[0].opt.value = option;
  document.forms[0].SCREEN.value="100";
  document.forms[0].H01OPECOD.value="0005";
  document.forms[0].submit();
 }

</SCRIPT>  

<h3 align="center">Tabla de Porcentajes a Otorgar

<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="percentages,EDP0888"></h3>


<hr size="4">

<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0888" >
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
    <input type=HIDDEN name="opt">
    <INPUT TYPE=HIDDEN NAME="E01MODLGT" VALUE="<%= userPO.getHeader2().trim() %>">
    <INPUT TYPE=HIDDEN NAME="H01OPECOD" VALUE="">
  </p>
  <p> 
<% 
String chk = "";

 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 
  <p> 
  
  <table class="tbenter" width=100% align=center>
    <tr> 
    <td class=TDBKG width="20%">
        <div align="center">
        <a href="javascript:submitThis(7)"><b>Enviar</b></a>
        </div> 
    </td> 
      <td class=TDBKG width="20%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
  
  
  <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th NOWRAP  align=RIGHT width="40%"> 
            Producto :
            </th>
			<td nowrap align="CENTER" width="20%">
				<INPUT type="text" name="E01PCSPRO" size="5" maxlength="4" value="<%= Edp088801.getE01PCSPRO().trim()%>" readonly>
			</td>
			<td nowrap align="CENTER" width="20%">
				<INPUT type="text" name="E01PCSDSC" size="35" maxlength="35" value="<%= Edp088801.getE01PCSDSC().trim()%>" readonly>
			</td>
          </tr>
        </table>
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="40%">Porcentajes a Otorgar Con Garantía Real</th>
            <th align=CENTER nowrap width="20%"> 
              <div align="center">Default</div>
            </th>
            <th align=CENTER nowrap width="20%"> 
              <div align="center">Políticas</div>
            </th>
            
           </tr>
          <tr id="trclear"> 
            <td NOWRAP  align=RIGHT width="40%"> 
            Máximo a Otorgar 
            </td>
			<td nowrap align="CENTER" width="20%">
 		    <INPUT type="TEXT" name="E01PCSMO1" size="12" maxlength="10" value="<%= Edp088801.getE01PCSMO1().trim()%>" onkeypress=enterSignDecimal(2)>%
			</td>
			<td nowrap align="CENTER" width="20%">
 		    <INPUT type="TEXT" name="E01PCSMO2" size="12" maxlength="10" value="<%= Edp088801.getE01PCSMO2().trim()%>" onkeypress=enterSignDecimal(2)>%
			</td>
         </tr>
          <tr id="trdark"> 
            <td NOWRAP  align=RIGHT width="40%"> 
            Mínimo a Otorgar 
            </td>
			<td nowrap align="CENTER" width="20%">
 		    <INPUT type="TEXT" name="E01PCSMO3" size="12" maxlength="10" value="<%= Edp088801.getE01PCSMO3().trim()%>" onkeypress=enterSignDecimal(2)>%
			</td>
			<td nowrap align="CENTER" width="20%">
 		    <INPUT type="TEXT" name="E01PCSMO4" size="12" maxlength="10" value="<%= Edp088801.getE01PCSMO4().trim()%>" onkeypress=enterSignDecimal(2)>%
			</td>
         </tr>
        </table>
  </table>
<br>
<br>

  <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="40%">Porcentajes a Otorgar Sin Garantía Real</th>
            <th align=CENTER nowrap width="20%"> 
              <div align="center">Default</div>
            </th>
            <th align=CENTER nowrap width="20%"> 
              <div align="center">Políticas</div>
            </th>
            
           </tr>
          <tr id="trclear"> 
            <td NOWRAP  align=RIGHT width="40%"> 
            Máximo a Otorgar 
            </td>
			<td nowrap align="CENTER" width="20%">
 		    <INPUT type="TEXT" name="E01PCSMO5" size="12" maxlength="10" value="<%= Edp088801.getE01PCSMO5().trim()%>" onkeypress=enterSignDecimal(2)>%
			</td>
			<td nowrap align="CENTER" width="20%">
 		    <INPUT type="TEXT" name="E01PCSMO6" size="12" maxlength="10" value="<%= Edp088801.getE01PCSMO6().trim()%>" onkeypress=enterSignDecimal(2)>%
			</td>
         </tr>
          <tr id="trdark"> 
            <td NOWRAP  align=RIGHT width="40%"> 
            Mínimo a Otorgar 
            </td>
			<td nowrap align="CENTER" width="20%">
 		    <INPUT type="TEXT" name="E01PCSMO7" size="12" maxlength="10" value="<%= Edp088801.getE01PCSMO7().trim()%>" onkeypress=enterSignDecimal(2)>%
			</td>
			<td nowrap align="CENTER" width="20%">
 		    <INPUT type="TEXT" name="E01PCSMO8" size="12" maxlength="10" value="<%= Edp088801.getE01PCSMO8().trim()%>" onkeypress=enterSignDecimal(2)>%
			</td>
         </tr>
        </table>
  </table>
<br>
<br>

 <SCRIPT language="JavaScript">

</SCRIPT>
</form>
</body>
</html>