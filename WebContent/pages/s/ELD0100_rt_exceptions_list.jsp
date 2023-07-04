<html>
<head>
<title>Excepciones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ELD010003Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">


function goAction(op) {

	document.forms[0].opt.value = op;
	document.forms[0].submit();
  
}


</SCRIPT>  

</head>

<BODY>
<h3 align="center">Lavado de Dinero - Consulta de Excepciones</h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELD0100" >
  <p>
    <input type=HIDDEN name="SCREEN" value="600">
    <input type=HIDDEN name="opt" value="1">
    <input type=HIDDEN name="totalRow" value="0">
    <input type=HIDDEN name="SEQ" value="">
    <input type=HIDDEN name="MONTH" value="">
    <input type=HIDDEN name="ACCOUNT" value="">
    <%
	if ( ELD010003Help.getNoResult() ) {
 %>
  </p>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="14%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="9%"> 
              <div align="left"> 
                <input type="text" name="E03LDMACC" size="12" maxlength="12" value="<%= userPO.getAccNum().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="12%"> 
              <div align="right"><b>Mes :</b></div>
            </td>
            <td nowrap width="33%"> 
              <div align="left"><b> 
                <input type="text" name="E03LDEBDM" size="3" maxlength="2" readonly value="<%= userPO.getHeader1().trim()%>">
                </b> </div>
            </td>
            <td nowrap width="11%"> 
              <div align="right"><b>A&ntilde;o : </b></div>
            </td>
            <td nowrap width="21%"> 
              <div align="left"><b> 
                <input type="text" name="E03LDEBDY" size="6" maxlength="4" value="<%= userPO.getHeader2().trim()%>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp; </p>
  <TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>No hay resultados para su b&uacute;squeda</b></p>
          
        </div>

	  </TD>
	</TR>
    </TABLE>
	
  <%  
		}
	else {
%> <% 
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
      <td class=TDBKG width="33%"> 
        <div align="center"><a href="javascript:goAction(2)"><b>Consulta</b></a></div>
      </td>
      <td class=TDBKG width="33%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
   
  <p>&nbsp;</p>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="14%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="9%" > 
              <div align="left"> 
                <input type="text" name="E03LDMCUN" size="9" maxlength="9" readonly value="<%= userPO.getCusNum().trim()%>">
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E03CUSNA1" size="45" maxlength="45" readonly value="<%= userPO.getCusName().trim()%>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p><br>
  </p>
  <TABLE  id=cfTable class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" width="100%">
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="10%">&nbsp;</th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Sequencia</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Tipo de Excepci&oacute;n</div>
            </th>
          </tr>
          <%
          		boolean firstTime = true;
          		String checked = " checked ";
                ELD010003Help.initRow();
                while (ELD010003Help.getNextRow()) {
                  if (!firstTime){
						checked = "";
                  }
                  datapro.eibs.beans.ELD010003Message msgList = (datapro.eibs.beans.ELD010003Message) ELD010003Help.getRecord();
		 %>
          <TR> 
            <TD NOWRAP  ALIGN=CENTER width="10%"> 
              <input type="radio" name="CURRCODE" value="<%= ELD010003Help.getCurrentRow() %>" <%= checked %> >
            </TD>
            <TD NOWRAP  ALIGN=CENTER width=\"10%\"><%= msgList.getE03LDESEQ() %></td>
            <TD NOWRAP  ALIGN=CENTER width=\"10%\"><%= msgList.getE03LDETPY() %></td>
          </TR>
          <%
                }
              %>
        </table>
    </table>
     
  <%}%>

<SCRIPT language="JavaScript">
  
  showChecked("CURRCODE");
  
</SCRIPT>
</form>

</body>
</html>
