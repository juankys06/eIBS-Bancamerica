<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
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


</head>
<body >
<%
	 trans.initRow();
    int blank_row = Integer.parseInt(transData.getTrNum());
    int max_row = 9999;
    int row;
    int total_row;
    try {
      row = Integer.parseInt(request.getParameter("ROW"));
    }
    catch (Exception e) {
      row = 0;
    }
    if ( (row == 0) || (row < trans.getLastRow()) ) {
      total_row = trans.getLastRow() + 1 + blank_row;
    }
    else {
		total_row = row;       
    }
    
%> 

<SCRIPT LANGUAGE="JavaScript">
builtHPopUp();
<%
  if ( !userPO.getOption().equals("TRANSACTION") ){
    if (userPO.getOption().equals("CD")){
%>
	 builtNewMenu(cd_m_opt);
<%   
   }
   else if (userPO.getOption().equals("LN")){
     if (session.getAttribute("dftBasic") != null) {
   %>
   		builtNewMenu(dft_m_opt);
   <%	
     } else {
   %>
	 	builtNewMenu(ln_m_opt);
  <%
  	}   
   } 
   else if (userPO.getOption().equals("OCK")){
         if ( userPO.getHeader19().equals("0")) {
   %>		
      builtNewMenu(of_p_opt);
  <%	
   			}
       else {
   %>
	   builtNewMenu(of_np_opt);
  <%   
       }
   } else if (userPO.getOption().equals("PR")){
   %>
	 builtNewMenu(pr_m_opt);
  <%   
   } 
	else if (userPO.getOption().equals("LN_TRANSACTION")){
  %>		
      builtNewMenu(ln_t_m_opt);
  <%	
       } 
     
   else if (userPO.getOption().equals("CD_TRANSACTION")){
  %>		
      builtNewMenu(cd_t_m_opt);
  <%	
   }
  %>
  
   initMenu();
 <%  
}
%>
function showPopUp(optHelp,fieldname,bank,ccy,aux1,aux2,opcode) {
   init(optHelp,fieldname,bank,ccy,aux1,aux2,opcode);
   showPopupHelp();
   }
   
</SCRIPT>



<SCRIPT>

function checkRowValue() {
  var r = <%= total_row %> + parseInt(document.forms[0].TEMP_ROW.value);
  if (r > <%= max_row %>) {
    alert("Valor de registros fuera de rango numero maximo es 9999");
  }
  else {
    document.forms[0].ROW.value = r + "";
  }
}

function submitThis(option) {
  var okdel = false;
  document.forms[0].opt.value = option;
  if ( option==4) { 
    okdel = confirm("Esta opcion borrara todo el lote de transacciones");
    if ( okdel ) { document.forms[0].submit();}
  } 
  else if ( option==1) {
     okdel = confirm("Esta opcion borrara las transacciones seleccionadas");
      if ( okdel ) { document.forms[0].submit();	}
    } else { document.forms[0].submit();}
 }

function doPrint() {
 pg= prefix +language + "EGL0035_transaction_print.jsp";
 CenterWindow(pg,650,450,2);
}

function isYear(field) {
var year = parseInt(field.value)
if ( field.value.length > 0 ) {

  if ( isNaN(year) ) {
   alert(" Solamente debe introducir numeros validos.")
   field.focus()
   field.value = ""
  }
else {
   if ( (""+year).length < 4 || year == 0 ) {
   alert("Favor, ingrese año valido ( 4 digitos )")
   field.focus()
	}
  }
 }
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

function GetInfo(currentrow){
if ( currentrow !== "" ) { setCurrentRow(currentrow);}
var winH = (currentrow == "") ? 400 : 455;

	pg= prefix +language + "EGL0035_transaction_info.jsp?CurrRow="+ currentrow;
	
	CenterNamedWindow(pg,'Information',400,winH,1);           
}

</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }
%>

<% 
 if ( transData.isFlagPrint()  ) {
    transData.setFlagPrint(false);
    %>
     <SCRIPT Language="Javascript">
            doPrint();
     </SCRIPT>
 <%
 }
%>
<h3 align="center">Entrada de Multiples Transacciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="transaction_pay,EGL0035"></h3>
<hr size="4">  
  
    
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEGL0035">

    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
    <INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
    <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="">
    <input type=HIDDEN name="OPTHELP" value="">
    <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="<%= total_row %>">
    
  <table width="100%" class="tbenter">
    <tr > 
      <td align="center" class="TDBKG"> 
        <a href="javascript:submitThis(1)">Borrar<BR>Selección</a>
      </td>
      <td align="center" class="TDBKG"> 
        <a href="javascript:submitThis(2)">Duplicar<BR>Selección</a>
      </td>
      <td align="center" class="TDBKG"> 
        <a href="javascript:submitThis(4)">Borrar<BR>Lote</a>
      </td>
      <td align="center" class="TDBKG"> 
        <a href="javascript:submitThis(5)">Imprimir</a>
      </td>
      <td align="center" class="TDBKG"> 
       <a href="javascript:submitThis(6)">Aceptar</a>
      </td>
      <td align="center" class="TDBKG"> 
        <a href="javascript:submitThis(7)">Aceptar con<BR>Errores</a>
      </td>
    </tr>
  </table>
  <table class="tableinfo" id="trdark">
    <tr id="trdark"> 
      <td align="right"  nowrap width="21%" >
       <% if (userPO.getOption().equals("LN")) { out.println("Cuenta : ");}
        		 else if (userPO.getOption().equals("CD")) { out.println("Certificado : ");}
        		 else if (userPO.getOption().equals("OCK")) { out.println("Cheque No. : ");}
        												    //if ( userPO.getHeader19().equals("1")) {out.println("Cheque No. : ");}
        												    //else {out.println("");}
        												    //}
        		else if (userPO.getOption().equals("TRANSACTION")) { out.println("Lote : ");}%>
      </td>
      <td align="right"  nowrap width="21%" > 
        <div align="left"> 
          <% if (!userPO.getOption().equals("TRANSACTION")) { 
           if (userPO.getOption().equals("OCK")) { %> 
             <input type="text" name="E01NUMACC" size="11" maxlength="10" readonly value = "<%= transData.getChkNum() %>">
          <% } else {%>
            <input type="text" name="E01NUMACC" size="17" maxlength="15" readonly value = "<%= transData.getAccNum() %>">
          <% } 
          }else {%> 
             <input type="text" name="E01BTHNUM" size="6" maxlength="5" readonly value = "<%= transData.getBthnum() %>">
          <% } %>
         </div>
      </td>
      <td align="left" nowrap width="20%" > 
        <div align="right">
           <% if (userPO.getOption().equals("TRANSACTION")) { %> 
             Originado : 
           <% } %>  
        </div>
      </td>
      <td align="right" nowrap width="33%" > 
        <div align="left"> 
          <% if (userPO.getOption().equals("TRANSACTION")) { %> 
            <input type="text" name="E01WRKOBK" size="4" maxlength="2" readonly value = "<%= transData.getBank() %>">
            <input type="text" name="E01WRKOBR" size="5" maxlength="4" readonly value = "<%= transData.getBranch() %>">
          <% } %>
        </div>
      </td>
      <td align="right" nowrap width="33%" > Total Debitos 
        : </td>
      <td align="left" nowrap colspan="3"  > 
        <input type="text" name="DEBIT" size="14" maxlength="13" id="txtright" readonly value = "<%= transData.getDebitAmt() %>">
      </td>
    </tr>
    <tr id="trdark"> 
      <td align="right" nowrap width="21%"  >Total Transacciones 
        :</td>
      <td align="right" nowrap width="21%" > 
        <div align="left"> 
          <input type="text" name="ROW" size="5" maxlength="5" id="txtright" readonly value="<%= total_row %>">
        </div>
      </td>
      <td align="left" nowrap width="20%" >&nbsp; </td>
      <td align="right" nowrap width="33%"  > 
        <div align="left"></div>
      </td>
      <td align="right" nowrap width="33%" > Total Creditos 
        : </td>
      <td align="left" nowrap colspan="3"  width="26%"> 
        <input type="text" name="CREDIT" size="14" maxlength="13" id="txtright" readonly value = "<%= transData.getCreditAmt() %>">
      </td>
    </tr>
   
  </table>
  <BR>
  <table class="tableinfo">
    <tr  id="trclear">
      <td align="right" width="17%"> 
        Descripcion : 
      </td>
      <td align="left" width="40%" nowrap> 
        <input type="text" name="E01WRKTDS" size="30" maxlength="300" readonly value = "<%= transData.getDescription() %>">
        <a href="javascript:GetInfo('')"><img src="<%=request.getContextPath()%>/images/s/info.gif" alt="Mas Información" align="middle" border="0" ></a> 
      </td>
      <td align="right"width="14%"> 
        Fecha Valor : 
      </td>
      <td align="left" width="29%" nowrap> 
        <input type="text" name="E01WRKVD1" size="2" maxlength="2" readonly value = "<%= transData.getDate1() %>">
        <input type="text" name="E01WRKVD2" size="2" maxlength="2" readonly value = "<%= transData.getDate2()  %>">
        <input type="text" name="E01WRKVD3" size="2" maxlength="2" readonly value = "<%= transData.getDate3()  %>">
      </td>
    </tr>
  </table>
  <BR>
<TABLE  id="mainTable" class="tableinfo" ALIGN=CENTER >
 <TR> 
    <TD NOWRAP>
   <TABLE id="headTable" >
    <TR id="trdark"> 
      <th nowrap  >&nbsp;</th>
      <th align="center" nowrap >Bnc</th>
      <th align="center" nowrap >Suc</th>
      <th align="center" nowrap >Mda</th>
      <th align="center" nowrap >Cuenta<br>Contable</th>
      <th align="center" nowrap >Centro<br>Costo</th>
      <th align="center" nowrap >Número<br>Cuenta </th>
      <th align="center" nowrap >TR/CD</th>
      <th align="center" nowrap >Monto</th>
      <th align="center" nowrap > D/C</th>
      <th nowrap >&nbsp;</th>
    </tr>
    </TABLE>
  
   <div id="dataDiv1" class="scbarcolor" >
    <table id="dataTable" > 
    <%
	 				 trans.initRow();
                while (trans.getNextRow()) {
	      %> 
    <tr id="trclear"> 
      <td align="center" nowrap > 
        <input type="checkbox" name="TRANSROW_<%= trans.getCurrentRow() %>" value="checked">
      </td>
      <td align="center" nowrap >  
          <input type="text" name="E01WRKBNK_<%= trans.getCurrentRow() %>" size="4" maxlength="4"  value="<%= trans.getRecord(0) %>" onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" <% if ( trans.getRecord(24).equals("*")) out.print("id=\"txtchanged\""); %>> 
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01WRKBRN_<%= trans.getCurrentRow() %>" size="4" maxlength="4"  onKeypress="enterInteger()" value="<%= trans.getRecord(1) %>" oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01WRKBNK_<%= trans.getCurrentRow() %>.value,'','','','')" onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" <% if ( trans.getRecord(24).equals("*")) out.print("id=\"txtchanged\""); %>>
      </td>												
      <td align="center" nowrap > 
          <input type="text" name="E01WRKCCY_<%= trans.getCurrentRow() %>" size="3" maxlength="4" value="<%= trans.getRecord(2) %>" oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01WRKBNK_<%= trans.getCurrentRow() %>.value,'','','','')"  onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" <% if ( trans.getRecord(24).equals("*")) out.print("id=\"txtrchanged\""); else out.print("id=\"txtright\""); %>>
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01WRKGLN_<%= trans.getCurrentRow() %>" size="17" maxlength="17" onKeypress="enterInteger()" value="<%= trans.getRecord(3) %>" oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01WRKBNK_<%= trans.getCurrentRow() %>.value,document.forms[0].E01WRKCCY_<%= trans.getCurrentRow() %>.value,'','','')" onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" <% if ( trans.getRecord(24).equals("*")) out.print("id=\"txtrchanged\""); else out.print("id=\"txtright\""); %>>
      </td>
      <td align="center" nowrap> 
          <input type="text" name="E01WRKCCN_<%= trans.getCurrentRow() %>" size="8" maxlength="8" value="<%= trans.getRecord(4) %>" oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E01WRKBNK_<%= trans.getCurrentRow() %>.value,'','','','')" onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" <% if ( trans.getRecord(24).equals("*")) out.print("id=\"txtrchanged\""); else out.print("id=\"txtright\""); %>>
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01WRKACC_<%= trans.getCurrentRow() %>" size="13" maxlength="13" onKeypress="enterInteger()" value="<%= trans.getRecord(5) %>" oncontextmenu="showPopUp(accountHelp,this.name,'','','','','')" onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" <% if ( trans.getRecord(24).equals("*")) out.print("id=\"txtrchanged\""); else out.print("id=\"txtright\""); %>>
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01WRKCDE_<%= trans.getCurrentRow() %>" size="4" maxlength="4" value="<%= trans.getRecord(6) %>" oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')"  onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" <% if ( trans.getRecord(24).equals("*")) out.print("id=\"txtrchanged\""); else out.print("id=\"txtright\""); %>>
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01WRKAMT_<%= trans.getCurrentRow() %>" size="17" maxlength="17" value="<%= trans.getRecord(7) %>" onKeypress="enterDecimal()" onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" <% if ( trans.getRecord(24).equals("*")) out.print("id=\"txtrchanged\""); else out.print("id=\"txtright\""); %>>
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01WRKDCC_<%= trans.getCurrentRow() %>" size="4" maxlength="4" value="<%= trans.getRecord(8) %>" onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" <% if ( trans.getRecord(24).equals("*")) out.print("id=\"txtrchanged\""); else out.print("id=\"txtright\""); %>>
      </td>
      <td align="left" nowrap> <a href="javascript:GetInfo('<%= trans.getCurrentRow()%>')" ><img src="<%=request.getContextPath()%>/images/s/info.gif" alt="Mas Información" align="middle" border="0" ></a> 
        <input type= Hidden name="E01WRKTDS_<%= trans.getCurrentRow()%>" size="37" maxlength="300" value="<%= trans.getRecord(9)+trans.getRecord(15)+trans.getRecord(16)+
                                                                                                              trans.getRecord(17)+trans.getRecord(18)+trans.getRecord(19)+
																											  trans.getRecord(20)+trans.getRecord(21)+trans.getRecord(22)+
																											  trans.getRecord(23)%>">
        <input type= Hidden name="E01WRKVD1_<%= trans.getCurrentRow()%>" size="4" maxlength="4" value = "<%= trans.getRecord(10) %>">
        <input type= Hidden name="E01WRKVD2_<%= trans.getCurrentRow()%>" size="4" maxlength="4" value = "<%= trans.getRecord(11) %>">
        <input type= Hidden name="E01WRKVD3_<%= trans.getCurrentRow()%>" size="4" maxlength="4" value = "<%= trans.getRecord(12) %>">
        <input type= Hidden name="E01WRKMD1_<%= trans.getCurrentRow()%>" size="4" maxlength="4" value = "<%= trans.getRecord(37) %>">
        <input type= Hidden name="E01WRKMD2_<%= trans.getCurrentRow()%>" size="4" maxlength="4" value = "<%= trans.getRecord(38) %>">
        <input type= Hidden name="E01WRKMD3_<%= trans.getCurrentRow()%>" size="4" maxlength="4" value = "<%= trans.getRecord(39) %>">
        <input type= Hidden name="E01WRKCKN_<%= trans.getCurrentRow()%>" size="10" maxlength="9" value = "<%= trans.getRecord(27) %>">
        <input type= Hidden name="E01WRKTYP_<%= trans.getCurrentRow()%>" size="4" maxlength="4" value = "<%= trans.getRecord(25) %>">
        <input type= Hidden name="E01WRKEXR_<%= trans.getCurrentRow()%>" size="12" maxlength="11" value = "<%= trans.getRecord(13) %>">
        <input type= Hidden name="E01WRKCUN_<%= trans.getCurrentRow()%>" size="10" maxlength="9" value = "<%= trans.getRecord(26) %>">
        <input type= Hidden name="E01WRKUN1_<%= trans.getCurrentRow()%>" value = "<%= trans.getRecord(48) %>">
        <input type= Hidden name="DUPLIC_<%= trans.getCurrentRow()%>" size="4" maxlength="4" value = "<%= trans.getRecord(24) %>">
        <input type= Hidden name="E01WRKCR2_<%= trans.getCurrentRow()%>" value = "<%= trans.getRecord(41) %>">
        <input type= Hidden name="E01WRKUN2_<%= trans.getCurrentRow()%>" value = "<%= trans.getRecord(42) %>">
        <input type= Hidden name="E01WRKCR3_<%= trans.getCurrentRow()%>" value = "<%= trans.getRecord(43) %>">
        <input type= Hidden name="E01WRKUN3_<%= trans.getCurrentRow()%>" value = "<%= trans.getRecord(44) %>">
      </td>
    </tr>
    <tr id="trdark"> 
		<td nowrap colspan="5"> 
      	</td>
		<td nowrap colspan="6"> 
        	<input readonly type="text" name="E01TITDSC" size="36" maxlength="35" value="<%= trans.getRecord(40).trim()%>"> 
      	</td>
    </tr>

    <%
                }
			for (int i=trans.getRow(); i < total_row; i++) {
    %> 
    <tr id="trclear"> 
      <td align="center" nowrap  > 
        <input type="checkbox" name="TRANSROW_<%= i %>" value="checked">
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01WRKBNK_<%= i %>" size="4" maxlength="4"  onFocus="setCurrentRow('<%= i %>')">
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01WRKBRN_<%= i %>" size="4" maxlength="4" onKeypress="enterInteger()" oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01WRKBNK_<%= i %>.value,'','','','')" onFocus="setCurrentRow('<%= i %>')">
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01WRKCCY_<%= i %>" size="4" maxlength="4" value="" id="txtright" oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01WRKBNK_<%= i %>.value,'','','','')" onFocus="setCurrentRow('<%= i %>')">
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01WRKGLN_<%= i %>" size="17" maxlength="17" value="" onKeypress="enterInteger()" id="txtright" onFocus="setCurrentRow('<%= i %>')" oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01WRKBNK_<%= i %>.value,document.forms[0].E01WRKCCY_<%= i %>.value,'','','')">
      </td>
      <td align="center" nowrap> 
          <input type="text" name="E01WRKCCN_<%= i %>" size="8" maxlength="8" value="" id="txtright" onFocus="setCurrentRow('<%= i %>')" oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E01WRKBNK_<%= i %>.value,'','','','')" >
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01WRKACC_<%= i %>" size="13" maxlength="13" value="" onKeypress="enterInteger()" id="txtright" onFocus="setCurrentRow('<%= i %>')" oncontextmenu="showPopUp(accountHelp,this.name,'','','','','')">
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01WRKCDE_<%= i %>" size="4" maxlength="4" value="" id="txtright" onFocus="setCurrentRow('<%= i %>')" oncontextmenu="showPopUp(cnofHelp,this.name,'','','','','20')">
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01WRKAMT_<%= i %>" size="17" maxlength="17" value="" id="txtright" onFocus="setCurrentRow('<%= i %>')" onKeypress="enterDecimal()">
      </td>
      <td align="center" nowrap> 
          <input type="text" name="E01WRKDCC_<%= i %>" size="4" maxlength="4" value="" id="txtright" onFocus="setCurrentRow('<%= i %>')">
      </td>
      <td align="left" nowrap > <a href="javascript:GetInfo('<%= i %>')"><img src="<%=request.getContextPath()%>/images/s/info.gif" alt="Mas Información" align="middle" border="0" ></a> 
        <input type= Hidden name="E01WRKTDS_<%= i %>" size="37" maxlength="300" value="" >
        <input type= Hidden name="E01WRKVD1_<%= i %>" size="4" maxlength="4" value = "">
        <input type= Hidden name="E01WRKVD2_<%= i %>" size="4" maxlength="4" value = "">
        <input type= Hidden name="E01WRKVD3_<%= i %>" size="4" maxlength="4" value = "">
        <input type= Hidden name="E01WRKMD1_<%= i %>" size="4" maxlength="4" value = "">
        <input type= Hidden name="E01WRKMD2_<%= i %>" size="4" maxlength="4" value = "">
        <input type= Hidden name="E01WRKMD3_<%= i %>" size="4" maxlength="4" value = "">
        <input type= Hidden name="E01WRKCKN_<%= i %>" size="10" maxlength="9" value = "">        
        <input type= Hidden name="E01WRKTYP_<%= i %>" size="4" maxlength="4" value = " ">
        <input type= Hidden name="E01WRKEXR_<%= i %>" size="12" maxlength="11" value = "">
        <input type= Hidden name="E01WRKCUN_<%= i %>" size="10" maxlength="9" value = "">
        <input type= Hidden name="E01WRKUN1_<%= i %>" value = "">
        <input type= Hidden name="DUPLIC_<%= i %>" size="4" maxlength="4" value = "">
        <input type= Hidden name="E01WRKCR2_<%= i %>" value = "">
        <input type= Hidden name="E01WRKUN2_<%= i %>" value = "">
        <input type= Hidden name="E01WRKCR3_<%= i %>" value = "">
        <input type= Hidden name="E01WRKUN3_<%= i %>" value = "">
      </td>
    </tr>
    <tr id="trdark"> 
		<td nowrap colspan="5"> 
      	</td>
		<td nowrap colspan="6"> 
        	<input readonly type="text" name="E01TITDSC_<%= i %>" size="36" maxlength="35" > 
      	</td>
    </tr>

    <%
                }
    %> 
  </table>
  </div>
   
  </TD>
  </TR>	
</TABLE>

  <BR>
  <table class="tbenter" width="100%" >
    <tr> 
      <td   width="91%"> 
        <div align="right">Cantidad de Registros a A&ntilde;adir : 
          <input type="text" name="TEMP_ROW" size="4" maxlength="4" value="0" onKeypress="enterInteger()" onBlur="checkRowValue()">
        </div>
      </td>
      <td bordercolor="#FFFFFF"  class="TDBKG" width="8%"><a href="javascript:submitThis(3)">Adicionar</a></td>
    </tr>
  </table>
    <BR>
  <SCRIPT language="JavaScript">
     function resizeDoc() {
       	divResize();
     	adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      }
     resizeDoc();
     window.onresize=resizeDoc;
     
</SCRIPT>     
</form>
</body>
</html>
