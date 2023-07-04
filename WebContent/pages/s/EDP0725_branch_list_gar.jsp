<%@ page import ="datapro.eibs.master.Util" %>
<html>
<head>
<title>Lista Codigos de Formatos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "EDP072501Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<script language="JavaScript">
function goAction(op) {
	if (op == "1") {
	       document.forms[0].opt.value = op;
	       document.forms[0].SCREEN.value = 210;
	       document.forms[0].submit();
	} else {
		if (op == "2") {
	       document.forms[0].opt.value = op;
	       document.forms[0].SCREEN.value = 220;
	       document.forms[0].submit();
	}
	} 
}

function getParams(currrow,SEQ) {

	document.forms[0].CURRENTROW.value = currrow;
	document.forms[0].SEQ.value = SEQ;

}

function goCancel() {
document.forms[0].SCREEN.value="100";
document.forms[0].submit(); 
	  		  
}



</SCRIPT>  
</head>
<BODY>
<h3 align="center">Garantias<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="branch_list_gar.jsp, EDP0725"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0725" >
  <p> 
    <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0"> 
    <input type=HIDDEN name="SCREEN" value="100">
    <input type=HIDDEN name="totalRow" value="0">
    <input type=HIDDEN name="SEQ" value="">
    <input type=HIDDEN name="opt">
	<INPUT TYPE=HIDDEN NAME="ActAccTb" VALUE="0"> 
	<INPUT TYPE=HIDDEN NAME="ActOpt" VALUE="0"> 
  </p>
   <TABLE id="headTable" width="100%" cellpading=0 cellspacing=0>
    <tr id="trdark"> 
      <td align="right"  >
         <b>Cliente :</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01DPCCUN" size="12" readonly value="<%= userPO.getHeader5()%>">
      </td>
      <td align="right"  >
         <b>Nombre :</b>
      </td>
      <td nowrap colspan=3> 
         <input type="text" name="E01CUSNA1" size="45" readonly value="<%=userPO.getHeader6()%>">
      </td>         
    </tr> 
    <tr id="trdark"> 
      <td align="right"  >
         <b>Nro. Propuesta :</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01DPCNPR" size="15" readonly value="<%=userPO.getHeader4()%>">
      </td>
      <td nowrap > 
      </td>
      <td nowrap colspan=3> 
      </td>         
    </tr>  
  </table> 
  <p> 
    <%
	if ( EDP072501Help.getNoResult() ) {
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
                <div align="center"><a href="javascript:goAction(1)"><b>Nueva</b></a></div>
              </td>
              <td class=TDBKG width="30%"> 
                <div align="center"><a href="javascript:goCancel()"><b>Salir</b></a></div>
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
      <td class=TDBKG width="20%"><div align="center"><a href="javascript:goAction(1)"><b>Nueva</b></a></div></td>
	  <td class=TDBKG width="20%"><div align="center"><a href="javascript:goAction(2)"><b>Modificar</b></a></div></td>
	  <td class=TDBKG width="20%"><div align="center"><a href="javascript:goAction(3)"><b>Borrar</b></a></div></td>
      <td class=TDBKG width="20%"><div align="center"><a href="javascript:goAction(4)"><b>Garantes</b></a></div></td>
      <td class=TDBKG width="20%"><div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div></td>
    </tr>
  </table>
  <br>
  <table  id="cfTable" class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable0" width="100%">
          <%
                EDP072501Help.initRow();
                chk = "checked";
                boolean firstTime=true;
                while (EDP072501Help.getNextRow()) {
                  datapro.eibs.beans.EDP072501Message msgList = (datapro.eibs.beans.EDP072501Message) EDP072501Help.getRecord();
		 %>

          <%
				 if (firstTime==true){
		 %>
          <tr id="trdark"> 
            <th align=CENTER nowrap width="5%">&nbsp;</th>
            <th align=CENTER nowrap width="5%"><div align="center">Secuencia</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Tipo</div></th>
            <th align=CENTER nowrap width="15%"><div align="center">Identificación</div></th>
            <th align=CENTER nowrap width="35%"><div align="center">Descripción</div></th>
            <th align=CENTER nowrap width="25%"><div align="center">Avaluo</div></th>
           </tr>
          <%
				firstTime=false;		
				}
                 if (msgList.getH01FLGWK2().equals("")) {
				
		 %>
          <tr> 
            <td NOWRAP  align=CENTER width="5%"> 
            <INPUT type="radio" name="CURRCODE"
					value="<%= EDP072501Help.getCurrentRow() %>" <%=chk%>
					onclick="getParams(this.value,'<%= msgList.getE01DPCSEQ() %>');"></td>
			<td NOWRAP  align=CENTER width=\"5%\"><%= msgList.getE01DPCSEQ() %></td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01DPCTYP() %></td>
			<td NOWRAP  align=CENTER width=\"15%\"><%= msgList.getE01DPCIDN() %></td>
			<td NOWRAP  align=CENTER width=\"35%\"><%= msgList.getE01DPCDES() %></td>
			<td NOWRAP  align=CENTER width=\"25%\"><%= msgList.getE01DPCFAA() %></td>
         </tr>
          <%
              				chk = "";     
				}
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