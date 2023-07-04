<html> 
<head>
<title>Envio de Remesas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "sendList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }

   int row = 0;
   try { row = Integer.parseInt(request.getParameter("ROW"));} catch (Exception e) {}
   sendList.setCurrentRow(row);
   datapro.eibs.beans.EFE051002Message msgMT = (datapro.eibs.beans.EFE051002Message) sendList.getRecord();
   
%>
</head>
<body>

<H3 align="center">Envio de Remesas de Efectivo<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="money_transf_snd_detail,EFE0510"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.moneytransfer.JSEFE0510">
  
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= row%>">
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	<tr id=trdark> 
	      <td nowrap> 
	        <div align="right">Referencia : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E02REMREF" size="10" maxlength="2" readonly value="<%= msgMT.getE02REMREF() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right">Banco/Oficina : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E02REMBNK" size="3" maxlength="2" readonly value="<%= msgMT.getE02REMBNK() %>"><b>-</b> 
	        <input type="text" name="E02REMBRN" size="4" maxlength="3" readonly value="<%= msgMT.getE02REMBRN() %>">
	      </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Aprobada por : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E02REMAPU" size="12" maxlength="10" readonly value="<%= msgMT.getE02REMAPU() %>">
      	  </td>     
      	</tr>
      	<tr id=trclear>
          <td nowrap>
              <div align="right">Moneda : </div>
          </td>
          <td nowrap>
              <input type="text" name="E02REMCCY" size="4" maxlength="3" value="<%= msgMT.getE02REMCCY() %>" readonly>
          </td>
        </tr>
        <tr id=trdark>
          <td nowrap>
             <div align="right">Monto Solicitado : </div>
          </td>
          <td nowrap>
             <input type="text" name="E02REMRAM" value ="<%= Util.formatCCY(msgMT.getE02REMRAM()) %>" readonly>             
          </td>
        </tr>
        <tr id=trclear>
          <td nowrap>
             <div align="right">Monto Aprobado : </div>
          </td>
          <td nowrap>
             <input type="text" name="E02REMAAM" value ="<%= Util.formatCCY(msgMT.getE02REMAAM()) %>" readonly>             
          </td>
        </tr>
        <tr id=trclear>
          <td nowrap>
             <div align="right">Tipo de Remesa : </div>
          </td>
          <td nowrap>
             <input type="text" readonly name="E02REMTYP" size="10" maxlength="8" 
				value="<% if (msgMT.getE02REMTYP().equals("C")) 
				             { out.print("Canje"); }
                    	  else if (msgMT.getE02REMTYP().equals("A")) 
				             { out.print("ATM"); } 
				          else
                    	     { out.print("Normal"); } %>">             
          </td>
        </tr>
        <tr id=trdark>
          <td nowrap>
             <div align="right">Nro. de Cartaporte : </div>
          </td>
          <td nowrap>
             <input type="text" name="E02REMPOR" value="<%= msgMT.getE02REMPOR() %>" onkeypress="enterInteger()">             
          </td>
        </tr>
        <tr id=trdark>
          <td nowrap>
             <div align="right">Nro. de Plomo : </div>
          </td>
          <td nowrap>
             <input type="text" name="E02REMPLO" value="<%= msgMT.getE02REMPLO() %>" onkeypress="enterInteger()">             
          </td>
        </tr>
     </table>
    </td>
   </tr>
  </table>
  <br>
  <table class="tableinfo">
  <tr>
  <td>
  <table cellspacing=0 cellpadding=2 width="100%" border="0">
  	 <tr>
		<td align="center">Denominacion</td>
		<td align="center">Cantidad Solicitada</td>
		<td align="center">Monto Solicitado</td>
		<td align="center">Cantidad Enviada</td>
		<td align="center">Monto Enviado</td>
	 </tr>
     <tr id=trdark> 	      
	    <td>
	    	<%if (!msgMT.getE02REMV01().equals("0.00")) {%> 
			<input type="text" name="E02REMD01" size="5" maxlength="4" value="<%= msgMT.getE02REMD01() %>" readonly> 
	      	<input type="text" name="E02REMN01" size="40" maxlength="35" value="<%= msgMT.getE02REMN01() %>" readonly >
      	    <%} else {%>
      	    <input type="text" name="E02REMD01" size="5" maxlength="4" value="<%= msgMT.getE02REMD01() %>"> 
	      	<input type="text" name="E02REMN01" size="40" maxlength="35" value="<%= msgMT.getE02REMN01() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD01','E02REMN01','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <%}%>
		</td>
		<td> 
      	   <input type="text" name="E02REMQ01" value ="<%= msgMT.getE02REMQ01() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV01" value ="<%= msgMT.getE02REMV01() %>" readonly>             
      	</td>    
      	<td> 
      	   <input type="text" name="E02RESQ01" value ="<%= msgMT.getE02RESQ01() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02RESV01" value ="<%= msgMT.getE02RESV01() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
	    	<%if (!msgMT.getE02REMV02().equals("0.00")) {%> 
			<input type="text" name="E02REMD02" size="5" maxlength="4" value="<%= msgMT.getE02REMD02() %>" readonly> 
	      	<input type="text" name="E02REMN02" size="40" maxlength="35" value="<%= msgMT.getE02REMN02() %>" readonly >
      	    <%} else {%>
      	    <input type="text" name="E02REMD02" size="5" maxlength="4" value="<%= msgMT.getE02REMD02() %>"> 
	      	<input type="text" name="E02REMN02" size="40" maxlength="35" value="<%= msgMT.getE02REMN02() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD02','E02REMN02','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <%}%>
		</td>
		<td> 
      	   <input type="text" name="E02REMQ02" value ="<%= msgMT.getE02REMQ02() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV02" value ="<%= msgMT.getE02REMV02() %>" readonly>             
      	</td>     
      	<td> 
      	   <input type="text" name="E02RESQ02" value ="<%= msgMT.getE02RESQ02() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02RESV02" value ="<%= msgMT.getE02RESV02() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
	    	<%if (!msgMT.getE02REMV03().equals("0.00")) {%> 
			<input type="text" name="E02REMD03" size="5" maxlength="4" value="<%= msgMT.getE02REMD03() %>" readonly> 
	      	<input type="text" name="E02REMN03" size="40" maxlength="35" value="<%= msgMT.getE02REMN03() %>" readonly >
      	    <%} else {%>
      	    <input type="text" name="E02REMD03" size="5" maxlength="4" value="<%= msgMT.getE02REMD03() %>"> 
	      	<input type="text" name="E02REMN03" size="40" maxlength="35" value="<%= msgMT.getE02REMN03() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD03','E02REMN03','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <%}%>
		</td>
		<td> 
      	   <input type="text" name="E02REMQ03" value ="<%= msgMT.getE02REMQ03() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV03" value ="<%= msgMT.getE02REMV03() %>" readonly>             
      	</td>     
      	<td> 
      	   <input type="text" name="E02RESQ03" value ="<%= msgMT.getE02RESQ03() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02RESV03" value ="<%= msgMT.getE02RESV03() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
	    	<%if (!msgMT.getE02REMV04().equals("0.00")) {%> 
			<input type="text" name="E02REMD04" size="5" maxlength="4" value="<%= msgMT.getE02REMD04() %>" readonly> 
	      	<input type="text" name="E02REMN04" size="40" maxlength="35" value="<%= msgMT.getE02REMN04() %>" readonly >
      	    <%} else {%>
      	    <input type="text" name="E02REMD04" size="5" maxlength="4" value="<%= msgMT.getE02REMD04() %>"> 
	      	<input type="text" name="E02REMN04" size="40" maxlength="35" value="<%= msgMT.getE02REMN04() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD04','E02REMN04','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <%}%>
		</td>
		<td> 
      	   <input type="text" name="E02REMQ04" value ="<%= msgMT.getE02REMQ04() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV04" value ="<%= msgMT.getE02REMV04() %>" readonly>             
      	</td>     
      	<td> 
      	   <input type="text" name="E02RESQ04" value ="<%= msgMT.getE02RESQ04() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02RESV04" value ="<%= msgMT.getE02RESV04() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
	    	<%if (!msgMT.getE02REMV05().equals("0.00")) {%> 
			<input type="text" name="E02REMD05" size="5" maxlength="4" value="<%= msgMT.getE02REMD05() %>" readonly> 
	      	<input type="text" name="E02REMN05" size="40" maxlength="35" value="<%= msgMT.getE02REMN05() %>" readonly >
      	    <%} else {%>
      	    <input type="text" name="E02REMD05" size="5" maxlength="4" value="<%= msgMT.getE02REMD05() %>"> 
	      	<input type="text" name="E02REMN05" size="40" maxlength="35" value="<%= msgMT.getE02REMN05() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD05','E02REMN05','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <%}%>
		</td>
		<td> 
      	   <input type="text" name="E02REMQ05" value ="<%= msgMT.getE02REMQ05() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV05" value ="<%= msgMT.getE02REMV05() %>" readonly>             
      	</td>     
      	<td> 
      	   <input type="text" name="E02RESQ05" value ="<%= msgMT.getE02RESQ05() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02RESV05" value ="<%= msgMT.getE02RESV05() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
	    	<%if (!msgMT.getE02REMV06().equals("0.00")) {%> 
			<input type="text" name="E02REMD06" size="5" maxlength="4" value="<%= msgMT.getE02REMD06() %>" readonly> 
	      	<input type="text" name="E02REMN06" size="40" maxlength="35" value="<%= msgMT.getE02REMN06() %>" readonly >
      	    <%} else {%>
      	    <input type="text" name="E02REMD06" size="5" maxlength="4" value="<%= msgMT.getE02REMD06() %>"> 
	      	<input type="text" name="E02REMN06" size="40" maxlength="35" value="<%= msgMT.getE02REMN06() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD06','E02REMN06','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <%}%>
		</td>
		<td> 
      	   <input type="text" name="E02REMQ06" value ="<%= msgMT.getE02REMQ06() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV06" value ="<%= msgMT.getE02REMV06() %>" readonly>             
      	</td>     
      	<td> 
      	   <input type="text" name="E02RESQ06" value ="<%= msgMT.getE02RESQ06() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02RESV06" value ="<%= msgMT.getE02RESV06() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
	    	<%if (!msgMT.getE02REMV07().equals("0.00")) {%> 
			<input type="text" name="E02REMD07" size="5" maxlength="4" value="<%= msgMT.getE02REMD07() %>" readonly> 
	      	<input type="text" name="E02REMN07" size="40" maxlength="35" value="<%= msgMT.getE02REMN07() %>" readonly >
      	    <%} else {%>
      	    <input type="text" name="E02REMD07" size="5" maxlength="4" value="<%= msgMT.getE02REMD07() %>"> 
	      	<input type="text" name="E02REMN07" size="40" maxlength="35" value="<%= msgMT.getE02REMN07() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD07','E02REMN07','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <%}%>
		</td>
		<td> 
      	   <input type="text" name="E02REMQ07" value ="<%= msgMT.getE02REMQ07() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV07" value ="<%= msgMT.getE02REMV07() %>" readonly>             
      	</td>     
      	<td> 
      	   <input type="text" name="E02RESQ07" value ="<%= msgMT.getE02RESQ07() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02RESV07" value ="<%= msgMT.getE02RESV07() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
	    	<%if (!msgMT.getE02REMV08().equals("0.00")) {%> 
			<input type="text" name="E02REMD08" size="5" maxlength="4" value="<%= msgMT.getE02REMD08() %>" readonly> 
	      	<input type="text" name="E02REMN08" size="40" maxlength="35" value="<%= msgMT.getE02REMN08() %>" readonly >
      	    <%} else {%>
      	    <input type="text" name="E02REMD08" size="5" maxlength="4" value="<%= msgMT.getE02REMD08() %>"> 
	      	<input type="text" name="E02REMN08" size="40" maxlength="35" value="<%= msgMT.getE02REMN08() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD08','E02REMN08','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <%}%>
		</td>
		<td> 
      	   <input type="text" name="E02REMQ08" value ="<%= msgMT.getE02REMQ08() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV08" value ="<%= msgMT.getE02REMV08() %>" readonly>             
      	</td>     
      	<td> 
      	   <input type="text" name="E02RESQ08" value ="<%= msgMT.getE02RESQ08() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02RESV08" value ="<%= msgMT.getE02RESV08() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
	    	<%if (!msgMT.getE02REMV09().equals("0.00")) {%> 
			<input type="text" name="E02REMD09" size="5" maxlength="4" value="<%= msgMT.getE02REMD09() %>" readonly> 
	      	<input type="text" name="E02REMN09" size="40" maxlength="35" value="<%= msgMT.getE02REMN09() %>" readonly >
      	    <%} else {%>
      	    <input type="text" name="E02REMD09" size="5" maxlength="4" value="<%= msgMT.getE02REMD09() %>"> 
	      	<input type="text" name="E02REMN09" size="40" maxlength="35" value="<%= msgMT.getE02REMN09() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD09','E02REMN09','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <%}%>
		</td>
		<td> 
      	   <input type="text" name="E02REMQ09" value ="<%= msgMT.getE02REMQ09() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV09" value ="<%= msgMT.getE02REMV09() %>" readonly>             
      	</td>     
      	<td> 
      	   <input type="text" name="E02RESQ09" value ="<%= msgMT.getE02RESQ09() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02RESV09" value ="<%= msgMT.getE02RESV09() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
	    	<%if (!msgMT.getE02REMV10().equals("0.00")) {%> 
			<input type="text" name="E02REMD10" size="5" maxlength="4" value="<%= msgMT.getE02REMD10() %>" readonly> 
	      	<input type="text" name="E02REMN10" size="40" maxlength="35" value="<%= msgMT.getE02REMN10() %>" readonly >
     		<%} else {%>
      	    <input type="text" name="E02REMD10" size="5" maxlength="4" value="<%= msgMT.getE02REMD10() %>"> 
	      	<input type="text" name="E02REMN10" size="40" maxlength="35" value="<%= msgMT.getE02REMN10() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD10','E02REMN10','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <%}%>
		</td>
		<td> 
      	   <input type="text" name="E02REMQ10" value ="<%= msgMT.getE02REMQ10() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV10" value ="<%= msgMT.getE02REMV10() %>" readonly>             
      	</td>     
      	<td> 
      	   <input type="text" name="E02RESQ10" value ="<%= msgMT.getE02RESQ10() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02RESV10" value ="<%= msgMT.getE02RESV10() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
	    	<%if (!msgMT.getE02REMV11().equals("0.00")) {%>
	    	<input type="text" name="E02REMD11" size="5" maxlength="4" value="<%= msgMT.getE02REMD11() %>" readonly> 
	      	<input type="text" name="E02REMN11" size="40" maxlength="35" value="<%= msgMT.getE02REMN11() %>" readonly >
	      	<%} else {%> 
			<input type="text" name="E02REMD11" size="5" maxlength="4" value="<%= msgMT.getE02REMD11() %>"> 
	      	<input type="text" name="E02REMN11" size="40" maxlength="35" value="<%= msgMT.getE02REMN11() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD11','E02REMN11','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <%}%>
		</td>
		<td> 
      	   <input type="text" name="E02REMQ11" value ="<%= msgMT.getE02REMQ11() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV11" value ="<%= msgMT.getE02REMV11() %>" readonly>             
      	</td>    
      	<td> 
      	   <input type="text" name="E02RESQ11" value ="<%= msgMT.getE02RESQ11() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02RESV11" value ="<%= msgMT.getE02RESV11() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
	    	<%if (!msgMT.getE02REMV12().equals("0.00")) {%>
	    	<input type="text" name="E02REMD12" size="5" maxlength="4" value="<%= msgMT.getE02REMD12() %>" readonly> 
	      	<input type="text" name="E02REMN12" size="40" maxlength="35" value="<%= msgMT.getE02REMN12() %>" readonly >
	      	<%} else {%> 
			<input type="text" name="E02REMD12" size="5" maxlength="4" value="<%= msgMT.getE02REMD12() %>"> 
	      	<input type="text" name="E02REMN12" size="40" maxlength="35" value="<%= msgMT.getE02REMN12() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD12','E02REMN12','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <%}%> 
		</td>
		<td> 
      	   <input type="text" name="E02REMQ12" value ="<%= msgMT.getE02REMQ12() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV12" value ="<%= msgMT.getE02REMV12() %>" readonly>             
      	</td>    
      	<td> 
      	   <input type="text" name="E02RESQ12" value ="<%= msgMT.getE02RESQ12() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02RESV12" value ="<%= msgMT.getE02RESV12() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
	    	<%if (!msgMT.getE02REMV13().equals("0.00")) {%>
	    	<input type="text" name="E02REMD13" size="5" maxlength="4" value="<%= msgMT.getE02REMD13() %>" readonly> 
	      	<input type="text" name="E02REMN13" size="40" maxlength="35" value="<%= msgMT.getE02REMN13() %>" readonly >
	      	<%} else {%> 
			<input type="text" name="E02REMD13" size="5" maxlength="4" value="<%= msgMT.getE02REMD13() %>"> 
	      	<input type="text" name="E02REMN13" size="40" maxlength="35" value="<%= msgMT.getE02REMN13() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD13','E02REMN13','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <%}%> 
		</td>
		<td> 
      	   <input type="text" name="E02REMQ13" value ="<%= msgMT.getE02REMQ13() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV13" value ="<%= msgMT.getE02REMV13() %>" readonly>             
      	</td>    
      	<td> 
      	   <input type="text" name="E02RESQ13" value ="<%= msgMT.getE02RESQ13() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02RESV13" value ="<%= msgMT.getE02RESV13() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
	    	<%if (!msgMT.getE02REMV14().equals("0.00")) {%>
	    	<input type="text" name="E02REMD14" size="5" maxlength="4" value="<%= msgMT.getE02REMD14() %>" readonly> 
	      	<input type="text" name="E02REMN14" size="40" maxlength="35" value="<%= msgMT.getE02REMN14() %>" readonly >
	      	<%} else {%> 
			<input type="text" name="E02REMD14" size="5" maxlength="4" value="<%= msgMT.getE02REMD14() %>"> 
	      	<input type="text" name="E02REMN14" size="40" maxlength="35" value="<%= msgMT.getE02REMN14() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD14','E02REMN14','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <%}%> 
		</td>
		<td> 
      	   <input type="text" name="E02REMQ14" value ="<%= msgMT.getE02REMQ14() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV14" value ="<%= msgMT.getE02REMV14() %>" readonly>             
      	</td>    
      	<td> 
      	   <input type="text" name="E02RESQ14" value ="<%= msgMT.getE02RESQ14() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02RESV14" value ="<%= msgMT.getE02RESV14() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
	    	<%if (!msgMT.getE02REMV15().equals("0.00")) {%>
	    	<input type="text" name="E02REMD15" size="5" maxlength="4" value="<%= msgMT.getE02REMD15() %>" readonly> 
	      	<input type="text" name="E02REMN15" size="40" maxlength="35" value="<%= msgMT.getE02REMN15() %>" readonly >
	      	<%} else {%> 
			<input type="text" name="E02REMD15" size="5" maxlength="4" value="<%= msgMT.getE02REMD15() %>"> 
	      	<input type="text" name="E02REMN15" size="40" maxlength="35" value="<%= msgMT.getE02REMN15() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD15','E02REMN15','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <%}%> 
		</td>
		<td> 
      	   <input type="text" name="E02REMQ15" value ="<%= msgMT.getE02REMQ15() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV15" value ="<%= msgMT.getE02REMV15() %>" readonly>             
      	</td>    
      	<td> 
      	   <input type="text" name="E02RESQ15" value ="<%= msgMT.getE02RESQ15() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02RESV15" value ="<%= msgMT.getE02RESV15() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
	    	<%if (!msgMT.getE02REMV16().equals("0.00")) {%>
	    	<input type="text" name="E02REMD16" size="5" maxlength="4" value="<%= msgMT.getE02REMD16() %>" readonly> 
	      	<input type="text" name="E02REMN16" size="40" maxlength="35" value="<%= msgMT.getE02REMN16() %>" readonly >
	      	<%} else {%> 
			<input type="text" name="E02REMD16" size="5" maxlength="4" value="<%= msgMT.getE02REMD16() %>"> 
	      	<input type="text" name="E02REMN16" size="40" maxlength="35" value="<%= msgMT.getE02REMN16() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD16','E02REMN16','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <%}%> 
		</td>
		<td> 
      	   <input type="text" name="E02REMQ16" value ="<%= msgMT.getE02REMQ16() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV16" value ="<%= msgMT.getE02REMV16() %>" readonly>             
      	</td>    
      	<td> 
      	   <input type="text" name="E02RESQ16" value ="<%= msgMT.getE02RESQ16() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02RESV16" value ="<%= msgMT.getE02RESV16() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
	    	<%if (!msgMT.getE02REMV17().equals("0.00")) {%>
	    	<input type="text" name="E02REMD17" size="5" maxlength="4" value="<%= msgMT.getE02REMD17() %>" readonly> 
	      	<input type="text" name="E02REMN17" size="40" maxlength="35" value="<%= msgMT.getE02REMN17() %>" readonly >
	      	<%} else {%> 
			<input type="text" name="E02REMD17" size="5" maxlength="4" value="<%= msgMT.getE02REMD17() %>"> 
	      	<input type="text" name="E02REMN17" size="40" maxlength="35" value="<%= msgMT.getE02REMN17() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD17','E02REMN17','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <%}%> 
		</td>
		<td> 
      	   <input type="text" name="E02REMQ17" value ="<%= msgMT.getE02REMQ17() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV17" value ="<%= msgMT.getE02REMV17() %>" readonly>             
      	</td>    
      	<td> 
      	   <input type="text" name="E02RESQ17" value ="<%= msgMT.getE02RESQ17() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02RESV17" value ="<%= msgMT.getE02RESV17() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
	    	<%if (!msgMT.getE02REMV18().equals("0.00")) {%>
	    	<input type="text" name="E02REMD18" size="5" maxlength="4" value="<%= msgMT.getE02REMD18() %>" readonly> 
	      	<input type="text" name="E02REMN18" size="40" maxlength="35" value="<%= msgMT.getE02REMN18() %>" readonly >
	      	<%} else {%> 
			<input type="text" name="E02REMD18" size="5" maxlength="4" value="<%= msgMT.getE02REMD18() %>"> 
	      	<input type="text" name="E02REMN18" size="40" maxlength="35" value="<%= msgMT.getE02REMN18() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD18','E02REMN18','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <%}%> 
		</td>
		<td> 
      	   <input type="text" name="E02REMQ18" value ="<%= msgMT.getE02REMQ18() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV18" value ="<%= msgMT.getE02REMV18() %>" readonly>             
      	</td>    
      	<td> 
      	   <input type="text" name="E02RESQ18" value ="<%= msgMT.getE02RESQ18() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02RESV18" value ="<%= msgMT.getE02RESV18() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
	    	<%if (!msgMT.getE02REMV19().equals("0.00")) {%>
	    	<input type="text" name="E02REMD19" size="5" maxlength="4" value="<%= msgMT.getE02REMD19() %>" readonly> 
	      	<input type="text" name="E02REMN19" size="40" maxlength="35" value="<%= msgMT.getE02REMN19() %>" readonly >
	      	<%} else {%> 
			<input type="text" name="E02REMD19" size="5" maxlength="4" value="<%= msgMT.getE02REMD19() %>"> 
	      	<input type="text" name="E02REMN19" size="40" maxlength="35" value="<%= msgMT.getE02REMN19() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD19','E02REMN19','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <%}%> 
		</td>
		<td> 
      	   <input type="text" name="E02REMQ19" value ="<%= msgMT.getE02REMQ19() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV19" value ="<%= msgMT.getE02REMV19() %>" readonly>             
      	</td>    
      	<td> 
      	   <input type="text" name="E02RESQ19" value ="<%= msgMT.getE02RESQ19() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02RESV19" value ="<%= msgMT.getE02RESV19() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
	    	<%if (!msgMT.getE02REMV20().equals("0.00")) {%>
	    	<input type="text" name="E02REMD20" size="5" maxlength="4" value="<%= msgMT.getE02REMD20() %>" readonly> 
	      	<input type="text" name="E02REMN20" size="40" maxlength="35" value="<%= msgMT.getE02REMN20() %>" readonly >
	      	<%} else {%> 
			<input type="text" name="E02REMD20" size="5" maxlength="4" value="<%= msgMT.getE02REMD20() %>"> 
	      	<input type="text" name="E02REMN20" size="40" maxlength="35" value="<%= msgMT.getE02REMN20() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD20','E02REMN20','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <%}%> 
		</td>
		<td> 
      	   <input type="text" name="E02REMQ20" value ="<%= msgMT.getE02REMQ20() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV20" value ="<%= msgMT.getE02REMV20() %>" readonly>             
      	</td>    
      	<td> 
      	   <input type="text" name="E02RESQ20" value ="<%= msgMT.getE02RESQ20() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02RESV20" value ="<%= msgMT.getE02RESV20() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
  </table>
    </td>
   </tr>
  </table>
  
  <p align="center"> 
    <input id="EIBSBTN" type="submit" name="Submit" value="Enviar">
  </p>
  
</form>
</body>
</html>
