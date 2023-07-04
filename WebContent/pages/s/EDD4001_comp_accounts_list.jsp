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
    <input type=HIDDEN name="SCREEN" value="800">
    <input type=HIDDEN name="totalRow" value="0">
    <input type=HIDDEN name="opt">
    <input type=HIDDEN name="REFCODE">
	<input type=HIDDEN name="TBLCODE">
  </p>
  
  <p> 

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
%>
  <table class="tbenter" width=100% align=center>
    <tr> 
      <td class=TDBKG width="25%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
 
<%}  else {
%>

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
                <div align="center"><a href="javascript:goAction(1)"><b>Nuevo</b></a></div>
              </td>
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

          
  <table class="tbenter" width=100% align=center>
    <tr> 
      <td class=TDBKG width="25%"> 
        <div align="center"><a href="javascript:goAction(1)"><b>Nueva</b></a></div>
      </td>
      <td class=TDBKG width="25%"> 
        <div align="center"><a href="javascript:goAction(2)"><b>Mantenimiento</b></a></div>
      </td>
	  <td class=TDBKG width="25%"> 
        <div align="center"><a href="javascript:goProcess(3)"><b>Procesar Pago/Aporte</b></a></div>
      </td>      
      <td class=TDBKG width="25%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>




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
            <th align=CENTER nowrap width="10%">Identificación</th>
            <th align=CENTER nowrap width="18%">Cliente</th>
            <th align=CENTER nowrap width="10%">Cuenta</th>            
            <th align=CENTER nowrap width="8%">Monto</th>            
            <th align=CENTER nowrap width="4%">Fecha Proceso</th>            
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
            <td NOWRAP  align=CENTER width="5%"> 
			<% if (!msgList.getE01PAYIDN().equals("")) { %>
              <input type="radio" name="CURRCODE" value="<%= EDD400101Help.getCurrentRow() %> "  <%=chk%> >
			<% } %>
            </td>
            <td NOWRAP  align=CENTER width="4%"><%= msgList.getE01PAYCIA() %></td>
            <td NOWRAP  align=LEFT width="20%"><%= msgList.getD01PAYCIA() %></td>
            <td NOWRAP  align=CENTER width="8%"><%= msgList.getE01PAYCDE() %></td>
            <td NOWRAP  align=LEFT width="10%"><%= msgList.getE01PAYIDN() %></td>
            <td NOWRAP  align=LEFT width="20%"><%= msgList.getE01PAYNME() %></td>
            <td NOWRAP  align=LEFT width="11%"><%= msgList.getE01PAYACC() %></td>
            <td NOWRAP  align=RIGHT width="8%"><%= msgList.getE01PAYAMT() %></td>
			<% if (!msgList.getE01PAYIDN().equals("")) { %>
            <td NOWRAP  align=CENTER width="3%"><%= msgList.getE01PAYRD1() %>/<%= msgList.getE01PAYRD2() %>/<%= msgList.getE01PAYRD3() %></td>
			<% } %>			
          </tr>
          <%
                }
              %>

          <%
                }
              %>



        </table>
  </table>
     
  <SCRIPT language="JavaScript">
 showChecked("CURRCODE");
     
</SCRIPT>

<%}%>


  </form>

</body>
</html>
