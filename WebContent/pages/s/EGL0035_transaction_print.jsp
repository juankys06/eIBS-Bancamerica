<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Transacciones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "trans" class= "datapro.eibs.beans.JBListRec"  scope="session" /> 
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" /> 
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "transData" class= "datapro.eibs.beans.DataTransaction"  scope="session" /> 

<SCRIPT Language="Javascript">

function doPrint(){
	if(!window.print){
       var msg ="Debe actualizar su navegador para imprimir";
	    alert(msg);
	    return;}
	window.onbeforeprint=hideOpt;
	window.onafterprint=showOpt;
	window.focus();
	window.print();

	return;
}

function GetInfo(currentrow){

if ( currentrow !== "" ) { setCurrentRow(currentrow);}

var winH = (currentrow == "") ? 400 : 380;

	pg= prefix +language + "EGL0035_transaction_info.jsp?CurrRow="+ currentrow;           
	
	CenterNamedWindow(pg,'Information',400,winH,1);  
}

function setCurrentRow(row) {
var form = document.forms[0];
   form["CURRENTROW"].value = row;
   if ( (form["E01WRKBNK_"+ row].value =="") && (form["E01WRKBRN_"+ row].value =="") && (form["E01WRKCCY_"+ row].value =="")
        && (form["E01WRKGLN_"+ row].value =="") && (form["E01WRKCCN_"+ row].value =="") && (form["E01WRKACC_"+ row].value =="")
        && (form["E01WRKCDE_"+ row].value =="") && (form["E01WRKAMT_"+ row].value =="") && (form["E01WRKDCC_"+ row].value =="")) {
    form["E01WRKTDS_"+ row].value = form["E01WRKTDS"].value;
    form["E01WRKVD1_"+ row].value = form["E01WRKVD1"].value;
    form["E01WRKVD2_"+ row].value = form["E01WRKVD2"].value;
    form["E01WRKVD3_"+ row].value = form["E01WRKVD3"].value;
  }
}



function hideOpt() {
   RemoveFilter();
   OPTDIV.style.display="none";
   dataDiv1.style.height="100%";
   dataDiv1.style.overflowY="visible"
 
}

function showOpt() {
   RestoreFilter();
   OPTDIV.style.display="";
   divResize(); 
}

</SCRIPT>
</head>
<body >

<%
	 trans.initRow();
    int total_row;
    try {
      total_row = trans.getLastRow() + 1;
    }
    catch (Exception e) {
      total_row  = 0;
    }
%> 

<h3 align="center">Transacciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="transaction_print,EGL0035"></h3>
<hr size="4">
  
<form >
  
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
    <INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
    <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="">
    <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="<%= total_row %>">
 
  
  <table class="tableinfo" id="trdark">
    <tr id="trdark"> 
      <td align="right"  nowrap width="21%"  >
       <% if (userPO.getOption().equals("LN")) { out.println("<b>Cuenta : </b>");}
        		 else if (userPO.getOption().equals("CD")) { out.println("<b>Certificado : </b>");}
        		 else if (userPO.getOption().equals("OCK")) { out.println("<b>Cheque No. : </b>");}
        		 else if (userPO.getOption().equals("TRANSACTION")) { out.println("<b>Lote : </b>");}%>
      </td>
      <td align="right"  nowrap width="21%" > 
        <div align="left"> 
          <% if (!userPO.getOption().equals("TRANSACTION")) {  
             out.print(transData.getAccNum());
             } else {
             out.print(transData.getBthnum()); } 
           %>
         </div>
      </td>
      <td align="left" nowrap width="20%" > 
        <div align="right">
           <% if (userPO.getOption().equals("TRANSACTION")) { %> 
             <b>Originado : </b>
           <% } %>  
        </div>
      </td>
      <td align="right" nowrap width="33%" > 
        <div align="left"> 
          <% if (userPO.getOption().equals("TRANSACTION")) { out.print( transData.getBank() + " - "+  transData.getBranch() );} %>
        </div>
      </td>
      <td align="right" nowrap width="33%" ><b> Total Debitos  : </b></td>
      <td align="left" nowrap colspan="3" width="26%"> 
        <%= Util.formatCCY(transData.getDebitAmt())%>
      </td>
    </tr>
    <tr id="trdark"> 
      <td align="right" nowrap width="21%" ><b>Total Transacciones  :</b></td>
      <td align="right" nowrap width="21%" > 
        <div align="left"> 
          <%= total_row %>
        </div>
      </td>
      <td align="left" nowrap width="20%" >&nbsp; </td>
      <td align="right" nowrap width="33%"  > 
        <div align="left"></div>
      </td>
      <td align="right" nowrap width="33%"  ><b> Total Creditos  : </b></td>
      <td align="left" nowrap colspan="3"  width="26%"> 
        <%= Util.formatCCY(transData.getCreditAmt()) %>
      </td>
    </tr>
  </table>
  
  <BR>
  
  <table class="tableinfo">
    <tr  id="clear">
      <td align="right" width="17%" valign="top"><b> Descripcion : </b></td>
      <td align="left" width="40%" valign="top"> 
        <div align="left" style="width:350"><%= transData.getDescription() %></div>
      </td>
      <td align="right" nowrap width="14%" valign="top"><b> Fecha Valor : </b></td>
      <td align="left" width="29%" nowrap valign="top"> 
       <%= Util.formatDate(transData.getDate1(),transData.getDate2(),transData.getDate3())  %>
      </td>
    </tr>
  </table>
  <BR>
  
  <TABLE  id="mainTable" class="tableinfo">
 <TR> 
    <TD NOWRAP>
   <TABLE id="headTable">
    <tr id="trdark"> 
      <th align="center" nowrap > Bnk </th>
      <th align="center" nowrap > Suc </th>
      <th align="center" nowrap > Mda </th>
      <th align="center" nowrap > Cuenta<br>Contable </th>
      <th align="center" nowrap > Centro<br> Costo </th>
      <th align="center" nowrap > Número<br>Cuenta </th>
      <th align="center" nowrap > Descripcion </th>  
      <th align="center" nowrap > TR/CD </th>
      <th align="center" nowrap > Debito </th>
      <th align="center" nowrap > Credito </th>
    </tr>
    </TABLE>
    <div id="dataDiv1" class="scbarcolor">
    <table id="dataTable">  
        <%
	 				 trans.initRow();
                while (trans.getNextRow()) {
	      %> 
    <tr id="trclear"> 
      <td align="center" nowrap > <%= trans.getRecord(0) %></td>
      <td align="center" nowrap > <%= trans.getRecord(1) %></td>
      <td align="center" nowrap > <%= trans.getRecord(2) %></td>
      <td align="center" nowrap > <%= trans.getRecord(3) %></td>
      <td align="center" nowrap > <%= trans.getRecord(4) %></td>
      <td align="center" nowrap > <%= trans.getRecord(5) %></td>
      <% if (!userPO.getPurpose().equals("MAINTENANCE")) { %>
       <td align="center" nowrap  onclick="GetInfo('<%= trans.getCurrentRow()%>')" style="cursor: hand" > <%= trans.getRecord(9) %>
        <input type= Hidden name="E01WRKTDS_<%= trans.getCurrentRow()%>" size="37" maxlength="300" value="<%= trans.getRecord(9)+trans.getRecord(15)+trans.getRecord(16)+
                                                                                                              trans.getRecord(17)+trans.getRecord(18)+trans.getRecord(19)+
																											  trans.getRecord(20)+trans.getRecord(21)+trans.getRecord(22)+
																											  trans.getRecord(23)%>">
        <input type= Hidden name="E01WRKVD1_<%= trans.getCurrentRow()%>" size="2" maxlength="2" value = "<%= trans.getRecord(10) %>">
        <input type= Hidden name="E01WRKVD2_<%= trans.getCurrentRow()%>" size="2" maxlength="2" value = "<%= trans.getRecord(11) %>">
        <input type= Hidden name="E01WRKVD3_<%= trans.getCurrentRow()%>" size="2" maxlength="2" value = "<%= trans.getRecord(12) %>">
        
        
          <input type=Hidden name="E01WRKBNK_<%= trans.getCurrentRow() %>" size="4" maxlength="4"  value="<%= trans.getRecord(0) %>" onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" <% if ( trans.getRecord(24).equals("*")) out.print("id=\"txtchanged\""); %>> 
      
      
          <input type=Hidden name="E01WRKBRN_<%= trans.getCurrentRow() %>" size="4" maxlength="4"  onKeypress="enterInteger()" value="<%= trans.getRecord(1) %>" oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01WRKBNK_<%= trans.getCurrentRow() %>.value,'','','','')" onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" <% if ( trans.getRecord(24).equals("*")) out.print("id=\"txtchanged\""); %>>
      
      
          <input type=Hidden name="E01WRKCCY_<%= trans.getCurrentRow() %>" size="3" maxlength="4" value="<%= trans.getRecord(2) %>" oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01WRKBNK_<%= trans.getCurrentRow() %>.value,'','','','')"  onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" <% if ( trans.getRecord(24).equals("*")) out.print("id=\"txtrchanged\""); else out.print("id=\"txtright\""); %>>
      
      
          <input type=Hidden name="E01WRKGLN_<%= trans.getCurrentRow() %>" size="17" maxlength="17" onKeypress="enterInteger()" value="<%= trans.getRecord(3) %>" oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01WRKBNK_<%= trans.getCurrentRow() %>.value,document.forms[0].E01WRKCCY_<%= trans.getCurrentRow() %>.value,'','','')" onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" <% if ( trans.getRecord(24).equals("*")) out.print("id=\"txtrchanged\""); else out.print("id=\"txtright\""); %>>
      
      
          <input type=Hidden name="E01WRKCCN_<%= trans.getCurrentRow() %>" size="8" maxlength="8" value="<%= trans.getRecord(4) %>" oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E01WRKBNK_<%= trans.getCurrentRow() %>.value,'','','','')" onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" <% if ( trans.getRecord(24).equals("*")) out.print("id=\"txtrchanged\""); else out.print("id=\"txtright\""); %>>
      
      
          <input type=Hidden name="E01WRKACC_<%= trans.getCurrentRow() %>" size="13" maxlength="13" onKeypress="enterInteger()" value="<%= trans.getRecord(5) %>" oncontextmenu="showPopUp(accountHelp,this.name,'','','','','')" onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" <% if ( trans.getRecord(24).equals("*")) out.print("id=\"txtrchanged\""); else out.print("id=\"txtright\""); %>>
      
      
          <input type=Hidden name="E01WRKCDE_<%= trans.getCurrentRow() %>" size="4" maxlength="4" value="<%= trans.getRecord(6) %>" oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')"  onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" <% if ( trans.getRecord(24).equals("*")) out.print("id=\"txtrchanged\""); else out.print("id=\"txtright\""); %>>
      
      
          <input type=Hidden name="E01WRKAMT_<%= trans.getCurrentRow() %>" size="17" maxlength="17" value="<%= trans.getRecord(7) %>" onKeypress="enterDecimal()" onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" <% if ( trans.getRecord(24).equals("*")) out.print("id=\"txtrchanged\""); else out.print("id=\"txtright\""); %>>
      
      
          <input type=Hidden name="E01WRKDCC_<%= trans.getCurrentRow() %>" size="4" maxlength="4" value="<%= trans.getRecord(8) %>" onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" <% if ( trans.getRecord(24).equals("*")) out.print("id=\"txtrchanged\""); else out.print("id=\"txtright\""); %>>
      
       <input type= Hidden name="E01WRKMD1_<%= trans.getCurrentRow()%>" size="4" maxlength="4" value = "<%= trans.getRecord(37) %>">
        <input type= Hidden name="E01WRKMD2_<%= trans.getCurrentRow()%>" size="4" maxlength="4" value = "<%= trans.getRecord(38) %>">
        <input type= Hidden name="E01WRKMD3_<%= trans.getCurrentRow()%>" size="4" maxlength="4" value = "<%= trans.getRecord(39) %>">
        <input type= Hidden name="E01WRKCKN_<%= trans.getCurrentRow()%>" size="10" maxlength="9" value = "<%= trans.getRecord(27) %>">        
        <input type= Hidden name="E01WRKTYP_<%= trans.getCurrentRow()%>" size="4" maxlength="4" value = "<%= trans.getRecord(25) %>">
        <input type= Hidden name="E01WRKEXR_<%= trans.getCurrentRow()%>" size="12" maxlength="11" value = "<%= trans.getRecord(13) %>">
        <input type= Hidden name="E01WRKCUN_<%= trans.getCurrentRow()%>" size="10" maxlength="9" value = "<%= trans.getRecord(26) %>"> 
   
      
      
      </td> 
      <% }else{ %>
        <td align="center" nowrap > <%= trans.getRecord(9) %></td>
      <% } %>
      <td align="center" nowrap  > <%= trans.getRecord(6) %></td>
      <td align="right" nowrap > <% if ( trans.getRecord(8).equals("D")) {out.print( Util.formatCCY(trans.getRecord(7)) );} else {out.print("");} %></td>
      <td align="right" nowrap > <% if ( trans.getRecord(8).equals("C")) {out.print( Util.formatCCY(trans.getRecord(7)) );} else {out.print("");}%></td>
    </tr>

   	<tr id="trdark"> 
		<td nowrap colspan="4">
      	</td>
		<td nowrap colspan="7"> 
        	<%= trans.getRecord(40).trim()%> 
      	</td>
    </tr>

    <%-- 
    This modification was done to put an additional description in a new line
    this additional description must be used only for printing purpose.
    --%>
    <% if (userPO.getPurpose().equals("MAINTENANCE")) { %>
    <tr>
    	<td></td>
    	<td></td>
    	<td></td>
    	<td></td>
    	<td></td>
    	<td></td>
    	<td> <%= trans.getRecord(46) %> </td>
    	<td></td>
    	<td></td>
    	<td></td>
    </tr>
    <% } %>
    
    <%
                }
    %> 
  </table>
  </div>
   
  </TD>
  </TR>	
</TABLE>

    <table width="80%" class="tbenter" id="OPTDIV">
      <tr align="center"> 
        
        <% if (userPO.getPurpose().equals("APPROVAL_INQ") && userPO.getOption().equals("TRANSACTION")) { %>
         <td class="TDBKG">
           	<a href="javascript:goOpenerAction('A')">Aprobar</a>
         </td>
       <% }%>
      
        <td class="TDBKG"> 
          <a href="javascript:doPrint()">Imprimir</a>
        </td>
        
        <% if (userPO.getPurpose().equals("APPROVAL_INQ") && userPO.getOption().equals("TRANSACTION")) { %>
       	<td class="TDBKG">
       		<a href="javascript:goOpenerAction('R')">Rechazar</a>
        </td>
        <% }%>
        
      </tr>
    </table>
 
  <SCRIPT Language="Javascript">
     function resizeDoc() {
       divResize();
       adjustEquTables(headTable, dataTable, dataDiv1,0,false);
      }
     resizeDoc();
     window.onresize=resizeDoc; 
  </SCRIPT>  
</form>
</body>
</html>
