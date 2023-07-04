<%@ page import ="datapro.eibs.master.Util" %>
<html>
<head>
<title>Lista Codigos de Formatos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "EDP071501Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<script language="JavaScript">

function goAction(op) {	
	document.forms[0].opt.value = op;
	i = 0;
   var recnum = document.forms[0].RECNUM.value;
   for (i = 0; i < recnum; i++) {

		var CHK = "document.forms[0].DPWFG2"+i+".checked" ;
		if (eval(CHK) == true) {
			eval("document.forms[0].E01DPWFG2"+i+".value = '1'");
		} else {
			eval("document.forms[0].E01DPWFG2"+i+".value = '0'");
		}
		
		var CHK = "document.forms[0].DPWTAC"+i+".checked" ;
		if (eval(CHK) == true) {
			eval("document.forms[0].E01DPWTAC"+i+".value = '1'");
		} else {
			eval("document.forms[0].E01DPWTAC"+i+".value = '0'");
		}
		
		var CHK = "document.forms[0].DPWINQ"+i+".checked" ;
		if (eval(CHK) == true) {
			eval("document.forms[0].E01DPWINQ"+i+".value = '1'");
		} else {
			eval("document.forms[0].E01DPWINQ"+i+".value = '0'");
		}
		
		var CHK = "document.forms[0].DPWFG1"+i+".checked" ;
		if (eval(CHK) == true) {
			eval("document.forms[0].E01DPWFG1"+i+".value = '1'");
		} else {
			eval("document.forms[0].E01DPWFG1"+i+".value = '0'");
		}
		
//		var CHK = "document.forms[0].DPWFG3"+i+".checked" ;
//		if (eval(CHK) == true) {
//			eval("document.forms[0].E01DPWFG3"+i+".value = '1'");
//		} else {
//			eval("document.forms[0].E01DPWFG3"+i+".value = '0'");
//		}

		}
//alert(document.forms[0].DPWFG20.value);
	document.forms[0].submit();		  	
}


function getParams(currrow,cacti,dacti) {

	document.forms[0].CURRENTROW.value = currrow;
    //document.forms[0].cacti.value = cacti;
    //document.forms[0].dacti.value = dacti;
}

function asigDPWFG2() {	
	i = 0;
    var recnum = document.forms[0].RECNUM.value;
	var CHK = "document.forms[0].CHKDPWFG2.checked" ;
    for (i = 0; i < recnum; i++) {

		if (eval(CHK) == true) {
			eval("document.forms[0].DPWFG2"+i+".checked = true");
		} else {
			eval("document.forms[0].DPWFG2"+i+".checked = false");
		}
	}
}
function asigDPWTAC() {	
	i = 0;
    var recnum = document.forms[0].RECNUM.value;
	var CHK = "document.forms[0].CHKDPWTAC.checked" ;
    for (i = 0; i < recnum; i++) {

		if (eval(CHK) == true) {
			eval("document.forms[0].DPWTAC"+i+".checked = true");
		} else {
			eval("document.forms[0].DPWTAC"+i+".checked = false");
		}
	}
}
function asigDPWINQ() {	
	i = 0;
    var recnum = document.forms[0].RECNUM.value;
	var CHK = "document.forms[0].CHKDPWINQ.checked" ;
    for (i = 0; i < recnum; i++) {

		if (eval(CHK) == true) {
			eval("document.forms[0].DPWINQ"+i+".checked = true");
		} else {
			eval("document.forms[0].DPWINQ"+i+".checked = false");
		}
	}
}
function asigDPWFG2() {	
	i = 0;
    var recnum = document.forms[0].RECNUM.value;
	var CHK = "document.forms[0].CHKDPWFG2.checked" ;
    for (i = 0; i < recnum; i++) {

		if (eval(CHK) == true) {
			eval("document.forms[0].DPWFG2"+i+".checked = true");
		} else {
			eval("document.forms[0].DPWFG2"+i+".checked = false");
		}
	}
}
function asigDPWINQ() {	
	i = 0;
    var recnum = document.forms[0].RECNUM.value;
	var CHK = "document.forms[0].CHKDPWINQ.checked" ;
    for (i = 0; i < recnum; i++) {

		if (eval(CHK) == true) {
			eval("document.forms[0].DPWINQ"+i+".checked = true");
		} else {
			eval("document.forms[0].DPWINQ"+i+".checked = false");
		}
	}
}
function asigDPWFG1() {	
	i = 0;
    var recnum = document.forms[0].RECNUM.value;
	var CHK = "document.forms[0].CHKDPWFG1.checked" ;
    for (i = 0; i < recnum; i++) {

		if (eval(CHK) == true) {
			eval("document.forms[0].DPWFG1"+i+".checked = true");
		} else {
			eval("document.forms[0].DPWFG1"+i+".checked = false");
		}
	}
}


</SCRIPT>  
</head>
<BODY>
<h3 align="center">Autorizar Usuario a Ruta-Actividad</h3>
<P align="center">
	<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ListAccess_maint, EDP0715">
	<INPUT type="text" name="USR" size="35" maxlength="35" value="<%= userPO.getHeader10().trim()%>" readonly>
	<INPUT type="text" name="RUT" size="35" maxlength="35" value="<%= userPO.getHeader9().trim()%>" readonly>
	
</P>
<HR size="4" width="100%" align="right">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0715" >
  <p> 
    <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0"> 
    <input type=HIDDEN name="SCREEN" value="200">
    <input type=HIDDEN name="opt">
    <input type=HIDDEN name="opt">
  </p>
  <p> 
    <%
	if ( EDP071501Help.getNoResult() ) {
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
        <div align="center"><a href="javascript:goAction(2)"><b>Enviar</b></a></div> 
     </td> 
      <td class=TDBKG width="20%"> 
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
            <th align=CENTER nowrap width="10%"> <div align="center">Secuencia</div> </th>
            <th align=CENTER nowrap width="20%"> <div align="center">Actividad</div> </th>
            <th align=CENTER nowrap width="10%"> <div align="center">Ingresa</div> </th>
            <th align=CENTER nowrap width="10%"> <div align="center">Procesa</div> </th>
            <th align=CENTER nowrap width="10%"> <div align="center">Consulta</div> </th>
            <th align=CENTER nowrap width="10%"> <div align="center">Modifica</div> </th>
           </tr>
          <tr id="trclear"> 
            <td NOWRAP  align=RIGHT width="10%"></td>
            <td NOWRAP  align=LEFT width="20%"></td>
            <td NOWRAP  align=CENTER width="10%"> 
      		<INPUT type="checkbox" name="CHKDPWFG2"   
   			onClick="asigDPWFG2();" >
   			</td>
            <td NOWRAP  align=CENTER width="10%">  			
      		<INPUT type="checkbox" name="CHKDPWTAC" 
   			onClick="asigDPWTAC();" >
   			</td>
            <td NOWRAP  align=CENTER width="10%">  			
      		<INPUT type="checkbox" name="CHKDPWINQ"  
   			onClick="asigDPWINQ();" >
   			</td>
            <td NOWRAP  align=CENTER width="10%">
      		<INPUT type="checkbox" name="CHKDPWFG1" 
   			onClick="asigDPWFG1();" >
   			</td>
           </tr>
          <%
                EDP071501Help.initRow();
				int recnum = 0;
                while (EDP071501Help.getNextRow()) {
                datapro.eibs.beans.EDP071501Message msgList = (datapro.eibs.beans.EDP071501Message) EDP071501Help.getRecord();
				
		 %>

          <tr> 
            <input type="HIDDEN" name="DPWSEC<%= recnum %>" value="<%= msgList.getE01DPWSEC() %>">
            <input type="HIDDEN" name="DPWACT<%= recnum %>" value="<%= msgList.getE01DPWACT() %>">
            <td NOWRAP  align=RIGHT width=\"20%\"><%= msgList.getE01DPWSEC() %></td>
            <td NOWRAP  align=LEFT width=\"20%\"><%= msgList.getE01DPWACT() %>-<%= msgList.getE01DPWADE() %></td>
            <td NOWRAP  align=CENTER width="10%"> 
      		<INPUT type="checkbox" name="DPWFG2<%= recnum %>"   
   			<%if (msgList.getField("E01DPWFG2").getString().trim().equals("1")) out.print("checked");%> >
   			</td>
            <td NOWRAP  align=CENTER width="10%">  			
      		<INPUT type="checkbox" name="DPWTAC<%= recnum %>" 
   			<%if (msgList.getField("E01DPWTAC").getString().trim().equals("1")) out.print("checked");%> >
   			</td>
            <td NOWRAP  align=CENTER width="10%">  			
      		<INPUT type="checkbox" name="DPWINQ<%= recnum %>"  
   			<%if (msgList.getField("E01DPWINQ").getString().trim().equals("1")) out.print("checked");%> >
   			</td>
            <td NOWRAP  align=CENTER width="10%">
      		<INPUT type="checkbox" name="DPWFG1<%= recnum %>" 
   			<%if (msgList.getField("E01DPWFG1").getString().trim().equals("1")) out.print("checked");%> >
   			</td>

		    <input type=HIDDEN name="E01DPWFG2<%= recnum %>">
		    <input type=HIDDEN name="E01DPWTAC<%= recnum %>">
		    <input type=HIDDEN name="E01DPWINQ<%= recnum %>">
		    <input type=HIDDEN name="E01DPWFG1<%= recnum %>">
		    <input type=HIDDEN name="E01DPWFG3<%= recnum %>">

          </tr>
          <%recnum += 1; }; %>
          <input type="HIDDEN" name="RECNUM" value="<%= recnum %>">
	</table>
  </table>
<%}%>
</form>
</body>
</html>