<%@ page import ="datapro.eibs.master.Util" %>
<html>
<head>
<title>Lista Codigos de Formatos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "EDP070301Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "optList1" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optList" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<script language="JavaScript">
function goAction(op) {
	if (op == "3") {
      ok = confirm("Esta seguro que desea eliminar el registro seleccionado?");
	  if (ok) 
	       {
	       document.forms[0].SCREEN.value="700";
	       document.forms[0].opt.value = op;
	       document.forms[0].submit();
	       }  
	  
	} else 
	
			{
	       document.forms[0].opt.value = op;
	       document.forms[0].submit();
	       }
}


function getParams(currrow,cli,gln) {

	document.forms[0].CURRENTROW.value = currrow;
    document.forms[0].CLI.value = cli;
    document.forms[0].GLN.value = gln;
}

function goConfirm() {

	       document.forms[0].SCREEN.value="5";
	       document.forms[0].submit();
	        
}

function inipos(pos) {
    document.forms[0].pos.value = pos;
alert(document.forms[0].pos.value);
}
</SCRIPT>  
</head>
<BODY>
<h3 align="center">Estructuraci&oacute;n Estado Financiero</h3>
<P align="center">
	<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="financial_list.jsp, EDP0703">
	<INPUT type="text" name="DSC" size="35" maxlength="35" value="<%= userPO.getHeader10().trim()%>" readonly>
</P>
<HR size="4" width="100%" align="right">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0703" >
  <p> 
    <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0"> 
    <input type=HIDDEN name="SCREEN" value="800">
    <input type=HIDDEN name="totalRow" value="0">
    <input type=HIDDEN name="opt">
    <input type=HIDDEN name="CLI" value=" " size=2 maxlength=1>
    <input type=HIDDEN name="GLN" value=" " size=2 maxlength=1>
    <input type=HIDDEN name="pos">
  </p>
  <p> 
    <%
	if ( EDP070301Help.getNoResult() ) {
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
                <div align="center"> <a href="javascript:goAction(1)"><b>Nueva</b></a> </div>
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
      <td class=TDBKG width="30%"> 
        <div align="center"><a href="javascript:goAction(1)"><b>Nueva</b></a></div>
     </td>
		<td class=TDBKG width="30%">
        <div align="center"><a href="javascript:goAction(2)"><b>Mantenimiento</b></a></div> 
     </td> 
	  <td class=TDBKG width="30%"> 
        <div align="center"><a href="javascript:goAction(3)"><b>Borrado</b></a></div>
      </td>
      <td class=TDBKG width="30%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
  <br>
  <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="10%">&nbsp;</th>
            <th align=CENTER nowrap width="10%"> <div align="center">Cód.Agrup.</div> </th>
            <th align=CENTER nowrap width="25%"> <div align="center">Descripción Agrup. </div> </th>
            <th align=CENTER nowrap width="10%"> <div align="center">Código</div> </th>
            <th align=CENTER nowrap width="25%"> <div align="center">Descripción </div> </th>
            <th align=CENTER nowrap width="5%"> <div align="center">Sec.</div> </th>
            <th align=CENTER nowrap width="5%"> <div align="center">Tipo</div> </th>
            <th align=CENTER nowrap width="25%"> <div align="left">Variable Fin.</div> </th>
           </tr>
          <%
                EDP070301Help.initRow();
                chk = "checked";
                while (EDP070301Help.getNextRow()) {
                 
                  datapro.eibs.beans.EDP070301Message msgList = (datapro.eibs.beans.EDP070301Message) EDP070301Help.getRecord();
		 %>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
              <input type="radio" name="CURRCODE2" value="<%= EDP070301Help.getCurrentRow() %>" <%=chk%> onClick="getParams(this.value,'<%= msgList.getE01DPXCLI() %>','<%= msgList.getE01DPXGLN() %>');">
            </td>
            <td NOWRAP  align=CENTER width=\"5%\"><%= msgList.getE01DPXCLI() %></td>
            <td NOWRAP  align=CENTER width=\"25%\"><%= msgList.getE01DSCLIN() %></td>
			<td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01DPXGLN() %></td>
            <td NOWRAP  align=CENTER width=\"25%\"><%= msgList.getE01DPXDSG() %></td>
			<td NOWRAP  align=CENTER width=\"5%\"><%= msgList.getE01DPXSEC() %></td>
            <td NOWRAP  align=CENTER width=\"5%\"><% if(msgList.getE01DPXLIN().equals("1")){out.print("TIT");} if(msgList.getE01DPXLIN().equals("2")){out.print("DET");} if(msgList.getE01DPXLIN().equals("3")){out.print("CALC");} %></td>
            <td NOWRAP  align=LEFT width=\"5%\"><% if(!msgList.getE01DPXDF1().equals("")){ %> <%= msgList.getE01DPXDF1() %><BR><% } %>
            									<% if(!msgList.getE01DPXDF2().equals("")){ %><%= msgList.getE01DPXDF2() %><BR><% } %>
            									<% if(!msgList.getE01DPXDF3().equals("")){ %><%= msgList.getE01DPXDF3() %><BR><% } %>
            									<% if(!msgList.getE01DPXDF4().equals("")){ %><%= msgList.getE01DPXDF4() %><BR><% } %>
            									<% if(!msgList.getE01DPXDF5().equals("")){ %><%= msgList.getE01DPXDF5() %><BR><% } %>
            </td>
          </tr>
          <%
    				chk = "";     
                }
              %>
        </table>
  </table>
  <BR>
  <TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
  <%
        if ( EDP070301Help.getShowPrev() ) {
		int pos = EDP070301Help.getFirstRec() - 20;
   			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.creditproposal.JSEDP0703?SCREEN=200&Pos=" + pos +"\"><img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
  %>
  </TD>
  <TD WIDTH="50%" ALIGN=RIGHT>
  <%
        if ( EDP070301Help.getShowNext() ) {
		int pos = EDP070301Help.getLastRec();
   			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.creditproposal.JSEDP0703?SCREEN=200&Pos=" + pos +"\"><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");
        }
  %> 
 </TD>
 </TR>
 </TABLE>
<SCRIPT language="JavaScript">
	showChecked("CURRCODE");
</SCRIPT>
<%}%>
<DIV align="center"></DIV>
<div align="center"> 
</div>
</form>
</body>
</html>