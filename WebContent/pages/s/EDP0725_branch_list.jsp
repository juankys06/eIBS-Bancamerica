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
	       document.forms[0].submit();
	  
	} else {
	       document.forms[0].opt.value = op;
	       document.forms[0].submit();
	       }
	}


function getParams(currrow,NPR,CUN,NAM, EST) {

	document.forms[0].CURRENTROW.value = currrow;
	document.forms[0].NPR.value = NPR;
	document.forms[0].CUN.value = CUN;
	document.forms[0].NAM.value = NAM;
	document.forms[0].EST.value = EST;

}


</SCRIPT>  
</head>
<BODY>
<h3 align="center">Propuestas de Credito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="branch_list.jsp, EDP0725"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0725" >
  <p> 
    <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0"> 
    <input type=HIDDEN name="SCREEN" value="200">
    <input type=HIDDEN name="totalRow" value="0">
    <input type=HIDDEN name="NPR" value="">
    <input type=HIDDEN name="CUN" value="">
    <input type=HIDDEN name="NAM" value="">
    <input type=HIDDEN name="EST" value="">
    <input type=HIDDEN name="opt">
    <input type=HIDDEN name="pos">
  </p>
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
        <div align="center"><a href="javascript:goAction(1)"><b>Garantia</b></a></div>
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
            <th align=CENTER nowrap width="10%"><div align="center">Num.Propuesta</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Id. Cliente</div></th>
            <th align=CENTER nowrap width="35%"><div align="center">Nombre</div></th>
           </tr>
          <%
                EDP072501Help.initRow();
                chk = "checked";
                while (EDP072501Help.getNextRow()) {
                 
                  datapro.eibs.beans.EDP072501Message msgList = (datapro.eibs.beans.EDP072501Message) EDP072501Help.getRecord();
		 %>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
              <input type="radio" name="CURRCODE" value="<%= EDP072501Help.getCurrentRow() %>" <%=chk%> onClick="getParams(this.value,'<%= msgList.getE01DPCNPR() %>','<%= msgList.getE01DPCCUN() %>','<%= msgList.getE01CUSNA1() %>','<%= msgList.getE01DPCSTG() %>');">
            </td>
			<td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01DPCNPR() %></td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01DPCCUN() %></td>
			<td NOWRAP  align=CENTER width=\"35%\"><%= msgList.getE01CUSNA1() %></td>
         </tr>
          <%
              				chk = "";     
                }
              %>
        </table>
  </table>
  <br>
  <TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
  <%
        if ( EDP072501Help.getShowPrev() ) {
		int pos = EDP072501Help.getFirstRec() - 20;
   			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.creditproposal.JSEDP0725?SCREEN=100&Pos=" + pos +"\"><img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
  %>
  </TD>
  <TD WIDTH="50%" ALIGN=RIGHT>
  <%
        if ( EDP072501Help.getShowNext() ) {
		int pos = EDP072501Help.getLastRec();
   			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.creditproposal.JSEDP0725?SCREEN=100&Pos=" + pos +"\"><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");
      }
  %> 
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