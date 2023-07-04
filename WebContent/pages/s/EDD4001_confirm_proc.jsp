<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Mantenimiento de Pagos Automáticos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EDD400101Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">



function goAction(op) {

	document.forms[0].opt.value = op;
	document.forms[0].submit();
  
}

function goProcess() {

	if(confirm("Esta seguro que procesar el archivo de pagos/aportes?")){
		document.forms[0].opt.value = 3;
		document.forms[0].submit();
	}
	
	
  
}

</SCRIPT>  

</head>

<BODY>
<h3 align="center">Mantenimiento de Pagos Automáticos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="comp_accounts_list.jsp, EDD4001"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDD4001" >
  <p> 
    <input type=HIDDEN name="SCREEN" value="100">
    <input type=HIDDEN name="totalRow" value="0">
    <input type=HIDDEN name="opt">
    <input type=HIDDEN name="REFCODE">
	<input type=HIDDEN name="TBLCODE">
  </p>
  
  <p> 
    <%
	if ( EDD400101Help.getNoResult() ) {
 %>
  </p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>No hay resultados para su b&uacute;squeda</b></p>
          <table class="tbenter" width=100% align=center>
            <tr> 
              <td class=TDBKG width="30%"> 
                <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
              </td>
            </tr>
          </table>
          <p>&nbsp;</p>
          
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

          



  <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="2%">&nbsp;</th>
            <th align=CENTER nowrap width="4%">Compañia</th>
            <th align=CENTER nowrap width="20%"> 
              <div align="center">Descripción</div>
            </th>
            <th align=CENTER nowrap width="8%">Proceso</th>
            <th align=CENTER nowrap width="10%">Registros</th>            
            <th align=CENTER nowrap width="8%">Monto</th>            
            <th align=CENTER nowrap width="4%"> </th>            
          </tr>
          <%
                EDD400101Help.initRow();
				boolean firstTime = true;
				String chk = "";
        		while (EDD400101Help.getNextRow()) {
					if (firstTime) {
						firstTime = false;
						chk = "checked";
					} else {
						chk = "";
					}
                  	datapro.eibs.beans.EDD400101Message msgList = (datapro.eibs.beans.EDD400101Message) EDD400101Help.getRecord();
		 %>
          <tr> 

			<% if (msgList.getE01PAYIDN().equals("")) { %>
            <td NOWRAP  align=CENTER width="2%">&nbsp;</td>
            <td NOWRAP  align=CENTER width="4%"><%= msgList.getE01PAYCIA() %></td>
            <td NOWRAP  align=LEFT width="20%"><%= msgList.getD01PAYCIA() %></td>
            <td NOWRAP  align=CENTER width="8%"><%= msgList.getE01PAYCDE() %></td>
            <td NOWRAP  align=LEFT width="11%"><%= msgList.getE01PAYACC() %></td>
            <td NOWRAP  align=RIGHT width="8%"><%= msgList.getE01PAYAMT() %></td>
			<% } %>			
          </tr>
          <%
                }
              %>
        </table>

  </table>


  <TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p> </p>
          <table class="tbenter" width=100% align=center>
          <p>&nbsp;</p>
          </table>
          <p>&nbsp;</p>
          
        </div>

        <div align="center"> 
          <p><b>Proceso Finalizo Normalmente. Pagos/Aportes Seran Aplicados Durante el Cierre</b></p>
          <table class="tbenter" width=100% align=center>
            <tr> 
              <td class=TDBKG width="30%"> 
                <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
              </td>
            </tr>
          </table>
          <p>&nbsp;</p>
          
        </div>

	  </TD>
	</TR>
    </TABLE>

     
  <SCRIPT language="JavaScript">
 showChecked("CURRCODE");
     
</SCRIPT>

<%}%>


  </form>

</body>
</html>
