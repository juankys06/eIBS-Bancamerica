<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import ="datapro.eibs.master.Util" %>

<HTML>
<HEAD>
<TITLE>
Reversion de Prestamos
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "lnRever" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">
  function checkTotals() {
    if (document.forms[0].TOTDEB.value == document.forms[0].TOTCRD.value) return true; 
    else {
    	alert("No se puede reversar por diferencias entre Débitos y Créditos");
    	return false;
    	}
  }
</script>
</head>

<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

%> 
<h3 align="center">Reversi&oacute;n de Pr&eacute;stamos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_reversal.jsp,EDL1300">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL1300" onsubmit="return(checkTotals())">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1">

<%
	if ( lnRever.getNoResult() ) {

%> 

  <TABLE class="tbenter" width=100% height=40%>
   	<TR>
      <TD> 
        <div align="center">           
          <p>&nbsp;</p>
          <p><b>El pr&eacute;stamo seleccionado no posee transacciones asignadas</b></p>
        </div>
      </TD>
     </TR>
   </TABLE>
  <%   		
	}
	else {
 	lnRever.initRow();
    lnRever.setCurrentRow(0);
  	datapro.eibs.beans.EDL130001Message msgLoan = (datapro.eibs.beans.EDL130001Message) lnRever.getRecord();    	
%> 
 <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
 
 <table class=tableinfo>
  <tr>
   <td>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    
    <tr id=trdark>
            <td align=right nowrap>Pr&eacute;stamo :</td>
         <td><input size="17" type="text" name="E01DEAACC" readonly value="<%= msgLoan.getE01DEAACC()%>"></td>
    	 <td align=right nowrap>Cliente :</td>
         <td>
         	<input size="35" type="text" name="E01CUSNA1" readonly value="<%= msgLoan.getE01CUSNA1()%>">
         </td>
    </tr>
    <tr id=trdark>
	     <td align=right nowrap>Lote :</td>
	     <td>
	        <input size="3" type="text" name="E01TRABTH" maxlength="2" value="<%=msgLoan.getE01TRABTH()%>" readonly>
		 </td>
	     <td align=right nowrap>Fecha Proceso :</td>
         <td>
            <input size="3" type="text" name="E01TRABDM" maxlength="2" value="<%=msgLoan.getE01TRABDM()%>" readonly>
            <INPUT size="3" type="text" name="E01TRABDD" maxlength="2" value="<%=msgLoan.getE01TRABDD()%>" readonly>
            <INPUT size="5" type="text" name="E01TRABDY" maxlength="4" value="<%=msgLoan.getE01TRABDY()%>" readonly>
         </td>
    </tr>
  </table>
  </td>
  </tr>
 </table>
    
  <BR>
  
 <TABLE  id="mainTable" class="tableinfo">
 <TR> 
   <TD NOWRAP >
        <TABLE id="headTable" width="100%">
          <TR id="trdark"> 
            <Td ALIGN=CENTER NOWRAP>BCO</Td>
            <Td ALIGN=CENTER NOWRAP>SUC</Td>
            <Td ALIGN=CENTER NOWRAP>MDA</Td>
            <Td ALIGN=CENTER NOWRAP>Contabilidad</Td>
            <Td ALIGN=CENTER NOWRAP>Cuenta</Td>
            <Td ALIGN=CENTER NOWRAP>COD</Td>
            <Td ALIGN=CENTER NOWRAP>Descripcion</Td>
            <Td ALIGN=CENTER NOWRAP>Monto</Td>
            <Td ALIGN=CENTER NOWRAP>D/C</Td>
          </TR>
    <%
         
         lnRever.initRow();
         java.math.BigDecimal totDebit = new java.math.BigDecimal("0.00");
         java.math.BigDecimal totCredit = new java.math.BigDecimal("0.00");
         
         while (lnRever.getNextRow()) { 
            msgLoan = (datapro.eibs.beans.EDL130001Message) lnRever.getRecord();                   
     %>  
            <TR id="trclear"> 
              <td ALIGN=CENTER NOWRAP><%=msgLoan.getE01TRABNK() %></td>
              <td ALIGN=CENTER NOWRAP><%=msgLoan.getE01TRABRN() %></td>            	
         	  <td ALIGN=CENTER NOWRAP><%=msgLoan.getE01TRACCY() %></td>
         	  <td ALIGN=CENTER NOWRAP><%=msgLoan.getE01TRAGLN() %></td>
         	  <td ALIGN=CENTER NOWRAP><%=msgLoan.getE01TRAACC() %></td>
              <td ALIGN=CENTER NOWRAP><%=msgLoan.getE01TRACDE() %></td>            	
         	  <td NOWRAP><%=msgLoan.getE01TRANAR() %></td>
         	  <td ALIGN=RIGHT NOWRAP><%= Util.fcolorCCY(msgLoan.getE01TRAAMT()) %></td>
         	  <td ALIGN=CENTER NOWRAP><%=msgLoan.getE01TRADCC() %></td>
    		</TR>    		 
      <%
            if (msgLoan.getE01TRADCC().equals("D")) totDebit=totDebit.add(msgLoan.getBigDecimalE01TRAAMT());
            else totCredit = totCredit.add(msgLoan.getBigDecimalE01TRAAMT());  
         }        
    %> 
             				
		</TABLE>
	</TD>
  </TR>	
</TABLE>
<h4>Totales</h4>
<TABLE class="tableinfo">
 <TR> 
   <TD NOWRAP >
        <TABLE width="100%">
          <TR id="trdark"> 
            <Td ALIGN=CENTER NOWRAP>Nro. Trans : <%= lnRever.getLastRec() + 1 %></Td>
            <Td ALIGN=CENTER NOWRAP>D&eacute;bitos : <b><%=Util.fcolorCCY(totDebit.toString())%></b><input id="TOTDEB" type=hidden value="<%=totDebit.toString()%>"></Td>
            <Td ALIGN=CENTER NOWRAP>Cr&eacute;ditos : <b><%=Util.fcolorCCY(totCredit.toString())%></b><input id="TOTCRD" type=hidden value="<%=totCredit.toString()%>"></Td>
          </TR>
        </TABLE>
	</TD>
  </TR>	
</TABLE>
	<p align="center"> 
    	<input id="EIBSBTN" type=submit name="Submit" value="Enviar">
    </p>

  <%
  }
%> 
</FORM>
 
</BODY>
</HTML>
