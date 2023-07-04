<html>
<head>
<title>Tramos y Tasas Preferenciales</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id= "list" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "prefere" class= "datapro.eibs.beans.EDL114201Message"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<%@ page import="datapro.eibs.master.Util"%>

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
   
function goAction(op) {

	if(op == 1){
		document.forms[0].SCREEN.value = 200;
	}
	if(op == 2){
		document.forms[0].SCREEN.value = 300;
		document.forms[0].E01TASTBL.value = document.forms[0].ROW.value;
	}
	document.forms[0].submit();
}

function closeHiddenDivNew(){
	setVisibility(document.getElementById("hiddenDivNew"), "hidden");
    document.forms[0].E01TASTBL.value="";
}

function showHiddenDivNew(evt) {
	evt = (evt) ? evt : ((window.event) ? window.event : "");
 	
	var hiddenDivNew = document.getElementById("hiddenDivNew");

	var y=evt.clientY + document.body.scrollTop;
	var x=evt.clientX + document.body.scrollLeft;
     
	cancelBub(evt);

	moveElement(hiddenDivNew, y, x);
	setVisibility(hiddenDivNew, "visible");
	 
	document.forms[0].E01TASTBL.focus();
}

document.onclick= closeHiddenDivNew;

</SCRIPT>

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>


<H3 align="center">Tablas de Tramos y Tasas Preferenciales<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="limits_parameters_tables.jsp, EDL1142"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDL1142" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
	<div id="hiddenDivNew" class="hiddenDiv">
	 <TABLE id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
		border-color: #0b23b5;
		border-style : solid solid solid solid;
		filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
		<tr id="trdark"> 
	      <td align=CENTER width="25%"> 
	        <div align="right">Tabla :</div>
	      </td>
	      <td align=CENTER width="75%"> 
	        <div align="left"> 
	          <input type="text" name="E01TASTBL" size="3" maxlength="2">
	        </div>
	      </td>
	    </tr>
		<tr id="trdark"> 
	      <td align=CENTER width="25%"> 
	        <div align="right">Descripción :</div>
	      </td>
	      <td align=CENTER width="75%"> 
	        <div align="left"> 
	          <input type="text" name="E01TASDSC" size="36" maxlength="35">
	        </div>
	      </td>
	    </tr>
		<tr id="trdark"> 
	      <td align=CENTER width="25%"> 
	        <div align="right">Fecha :</div>
	      </td>
	      <td align=CENTER width="75%"> 
	        <div align="left"> 
	          <input type="text" name="E01TASDTE" size="36" maxlength="35">
	        </div>
	      </td>
	    </tr>
	   <tr id="trclear">
	   
	   <TD ALIGN=center nowrap colspan="2">
		     <input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="goAction(1)"> 
	   </TD>
	   
	   </tr>
	 </TABLE>
	 </div>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right">Banco :</div>
            </td>
            <td nowrap width="20%" > 
              <div align="left">
                <input type="text" name="E01TASBNK" size="3" maxlength="2" value="<%= prefere.getE01TASBNK().trim()%>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
 
  <br>
  
    <%
	if ( list.getNoResult() ) {
 	%>
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
                <div id="eibsNew" align="center" style="cursor:pointer"><a><b>Nueva</b></a></div>              
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
	
  <%  }	else { %>
  
  <table class="tbenter" width=100% align=center>
    <tr> 
      <td class=TDBKG width="30%"> 
		<div id="eibsNew" align="center" style="cursor:pointer"><a><b>Nueva</b></a></div>
      </td>
		<td class=TDBKG width="30%"> 
        <div align="center"><a href="javascript:goAction(2)"><b>Mantenimiento</b></a></div>
      </td>
      <td class=TDBKG width="30%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
	
    <table  id="mainTable" class="tableinfo">
    <tr >
      <td NOWRAP valign="top" width="100%" >
      <TABLE id="headTable"  width="100%">
    <TR id="trdark"> 
      <TH ALIGN=CENTER nowrap>&nbsp;</TH>
      <TH ALIGN=CENTER nowrap>Tabla</TH>
      <TH ALIGN=CENTER nowrap>Descripción</TH>
      <TH ALIGN=CENTER nowrap>Fecha</TH>            
    </TR>
	<%
		 int row = 0;
		 boolean firsTime = true;
         list.initRow();        
         while (list.getNextRow()) {
            datapro.eibs.beans.EDL114201Message msg = (datapro.eibs.beans.EDL114201Message) list.getRecord();
	%>
            <TR> 
              <TD ALIGN=CENTER NOWRAP> 
                <INPUT TYPE="radio" NAME="ROW" VALUE="<%= row %>" <% 
                	if (firsTime) out.print("checked"); %> >
              </TD>
              <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(msg.getE01TASTBL()) %></TD>              
              <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(msg.getE01TASDSC()) %></TD>              
              <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(msg.getE01TASDTE()) %></TD>
             </TR>
	<%              
         	firsTime = false;
         	row++;
         }        
    %>
    </table>
      </td>
    </tr>
  </table>
  	<%              
         }        
    %>
  
  <SCRIPT language="JavaScript">
 	document.getElementById("hiddenDivNew").onclick=cancelBub;
	document.getElementById("eibsNew").onclick=showHiddenDivNew;
</SCRIPT>
  </form>
</body>
</html>
