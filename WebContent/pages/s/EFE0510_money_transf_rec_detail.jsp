<html> 
<head>
<title>Recibo de Remesas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "receiveList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
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
   receiveList.setCurrentRow(row);
   datapro.eibs.beans.EFE051003Message msgMT = (datapro.eibs.beans.EFE051003Message) receiveList.getRecord();
   
%>
</head>
<body>

<H3 align="center">Recibo de Remesas de Efectivo<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="money_transf_rec_detail,EFE0510"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.moneytransfer.JSEFE0510">
  
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="500">
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
	        <input type="text" name="E03REMREF" size="10" maxlength="2" readonly value="<%= msgMT.getE03REMREF() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right">Banco/Oficina : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E03REMBNK" size="3" maxlength="2" readonly value="<%= msgMT.getE03REMBNK() %>"><b>-</b> 
	        <input type="text" name="E03REMBRN" size="4" maxlength="3" readonly value="<%= msgMT.getE03REMBRN() %>">
	      </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Enviado por : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E03REMSHU" size="12" maxlength="10" readonly value="<%= msgMT.getE03REMSHU() %>">
      	  </td>     
      	</tr>
      	<tr id=trclear>
          <td nowrap>
              <div align="right">Moneda : </div>
          </td>
          <td nowrap>
              <input type="text" name="E03REMCCY" size="4" maxlength="3" value="<%= msgMT.getE03REMCCY() %>" readonly>
          </td>
        </tr>
        <tr id=trdark>
          <td nowrap>
             <div align="right">Monto Solicitado : </div>
          </td>
          <td nowrap>
             <input type="text" name="E03REMRAM" value ="<%= Util.formatCCY(msgMT.getE03REMRAM()) %>" readonly>             
          </td>
        </tr>
        <tr id=trclear>
          <td nowrap>
             <div align="right">Monto Aprobado : </div>
          </td>
          <td nowrap>
             <input type="text" name="E03REMAAM" value ="<%= Util.formatCCY(msgMT.getE03REMAAM()) %>" readonly>             
          </td>
        </tr>
        <tr id=trclear>
          <td nowrap>
             <div align="right">Tipo de Remesa : </div>
          </td>
          <td nowrap>
             <input type="text" readonly name="E03REMTYP" size="10" maxlength="8" 
				value="<% if (msgMT.getE03REMTYP().equals("C")) 
				             { out.print("Canje"); }
                    	  else if (msgMT.getE03REMTYP().equals("A")) 
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
             <input type="text" name="E03REMPOR" value="<%= msgMT.getE03REMPOR() %>" onkeypress="enterInteger()">             
          </td>
        </tr>
        <tr id=trdark>
          <td nowrap>
             <div align="right">Nro. de Plomo : </div>
          </td>
          <td nowrap>
             <input type="text" name="E03REMPLO" value="<%= msgMT.getE03REMPLO() %>" onkeypress="enterInteger()">             
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
	 <%if ((!msgMT.getE03REMV01().equals("0.00")) || (!msgMT.getE03RESV01().equals("0.00")))  {%> 
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E03REMD01" size="5" maxlength="4" value="<%= msgMT.getE03REMD01() %>" readonly> 
	      	<input type="text" name="E03REMN01" size="40" maxlength="35" value="<%= msgMT.getE03REMN01() %>" readonly >
		</td>
		<td> 
      	   <input type="text" name="E03REMQ01" value ="<%= msgMT.getE03REMQ01() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03REMV01" value ="<%= msgMT.getE03REMV01() %>" readonly>             
      	</td>    
      	<td> 
      	   <input type="text" name="E03RESQ01" value ="<%= msgMT.getE03RESQ01() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03RESV01" value ="<%= msgMT.getE03RESV01() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if ((!msgMT.getE03REMV02().equals("0.00")) || (!msgMT.getE03RESV02().equals("0.00")))  {%> 
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E03REMD02" size="5" maxlength="4" value="<%= msgMT.getE03REMD02() %>" readonly> 
	      	<input type="text" name="E03REMN02" size="40" maxlength="35" value="<%= msgMT.getE03REMN02() %>" readonly >
		</td>
		<td> 
      	   <input type="text" name="E03REMQ02" value ="<%= msgMT.getE03REMQ02() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03REMV02" value ="<%= msgMT.getE03REMV02() %>" readonly>             
      	</td>     
      	<td> 
      	   <input type="text" name="E03RESQ02" value ="<%= msgMT.getE03RESQ02() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03RESV02" value ="<%= msgMT.getE03RESV02() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if ((!msgMT.getE03REMV03().equals("0.00")) || (!msgMT.getE03RESV03().equals("0.00")))  {%> 
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E03REMD03" size="5" maxlength="4" value="<%= msgMT.getE03REMD03() %>" readonly> 
	      	<input type="text" name="E03REMN03" size="40" maxlength="35" value="<%= msgMT.getE03REMN03() %>" readonly >
		</td>
		<td> 
      	   <input type="text" name="E03REMQ03" value ="<%= msgMT.getE03REMQ03() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03REMV03" value ="<%= msgMT.getE03REMV03() %>" readonly>             
      	</td>     
      	<td> 
      	   <input type="text" name="E03RESQ03" value ="<%= msgMT.getE03RESQ03() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03RESV03" value ="<%= msgMT.getE03RESV03() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if ((!msgMT.getE03REMV04().equals("0.00")) || (!msgMT.getE03RESV04().equals("0.00")))  {%> 
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E03REMD04" size="5" maxlength="4" value="<%= msgMT.getE03REMD04() %>" readonly> 
	      	<input type="text" name="E03REMN04" size="40" maxlength="35" value="<%= msgMT.getE03REMN04() %>" readonly >
		</td>
		<td> 
      	   <input type="text" name="E03REMQ04" value ="<%= msgMT.getE03REMQ04() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03REMV04" value ="<%= msgMT.getE03REMV04() %>" readonly>             
      	</td>     
      	<td> 
      	   <input type="text" name="E03RESQ04" value ="<%= msgMT.getE03RESQ04() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03RESV04" value ="<%= msgMT.getE03RESV04() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if ((!msgMT.getE03REMV05().equals("0.00")) || (!msgMT.getE03RESV05().equals("0.00")))  {%> 
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E03REMD05" size="5" maxlength="4" value="<%= msgMT.getE03REMD05() %>" readonly> 
	      	<input type="text" name="E03REMN05" size="40" maxlength="35" value="<%= msgMT.getE03REMN05() %>" readonly >
		</td>
		<td> 
      	   <input type="text" name="E03REMQ05" value ="<%= msgMT.getE03REMQ05() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03REMV05" value ="<%= msgMT.getE03REMV05() %>" readonly>             
      	</td>     
      	<td> 
      	   <input type="text" name="E03RESQ05" value ="<%= msgMT.getE03RESQ05() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03RESV05" value ="<%= msgMT.getE03RESV05() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if ((!msgMT.getE03REMV06().equals("0.00")) || (!msgMT.getE03RESV06().equals("0.00")))  {%> 
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E03REMD06" size="5" maxlength="4" value="<%= msgMT.getE03REMD06() %>" readonly> 
	      	<input type="text" name="E03REMN06" size="40" maxlength="35" value="<%= msgMT.getE03REMN06() %>" readonly >
		</td>
		<td> 
      	   <input type="text" name="E03REMQ06" value ="<%= msgMT.getE03REMQ06() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03REMV06" value ="<%= msgMT.getE03REMV06() %>" readonly>             
      	</td>     
      	<td> 
      	   <input type="text" name="E03RESQ06" value ="<%= msgMT.getE03RESQ06() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03RESV06" value ="<%= msgMT.getE03RESV06() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if ((!msgMT.getE03REMV07().equals("0.00")) || (!msgMT.getE03RESV07().equals("0.00")))  {%> 
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E03REMD07" size="5" maxlength="4" value="<%= msgMT.getE03REMD07() %>" readonly> 
	      	<input type="text" name="E03REMN07" size="40" maxlength="35" value="<%= msgMT.getE03REMN07() %>" readonly >
		</td>
		<td> 
      	   <input type="text" name="E03REMQ07" value ="<%= msgMT.getE03REMQ07() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03REMV07" value ="<%= msgMT.getE03REMV07() %>" readonly>             
      	</td>     
      	<td> 
      	   <input type="text" name="E03RESQ07" value ="<%= msgMT.getE03RESQ07() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03RESV07" value ="<%= msgMT.getE03RESV07() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if ((!msgMT.getE03REMV08().equals("0.00")) || (!msgMT.getE03RESV08().equals("0.00")))  {%> 
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E03REMD08" size="5" maxlength="4" value="<%= msgMT.getE03REMD08() %>" readonly> 
	      	<input type="text" name="E03REMN08" size="40" maxlength="35" value="<%= msgMT.getE03REMN08() %>" readonly >
		</td>
		<td> 
      	   <input type="text" name="E03REMQ08" value ="<%= msgMT.getE03REMQ08() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03REMV08" value ="<%= msgMT.getE03REMV08() %>" readonly>             
      	</td>     
      	<td> 
      	   <input type="text" name="E03RESQ08" value ="<%= msgMT.getE03RESQ08() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03RESV08" value ="<%= msgMT.getE03RESV08() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if ((!msgMT.getE03REMV09().equals("0.00")) || (!msgMT.getE03RESV09().equals("0.00")))  {%> 
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E03REMD09" size="5" maxlength="4" value="<%= msgMT.getE03REMD09() %>" readonly> 
	      	<input type="text" name="E03REMN09" size="40" maxlength="35" value="<%= msgMT.getE03REMN09() %>" readonly >
		</td>
		<td> 
      	   <input type="text" name="E03REMQ09" value ="<%= msgMT.getE03REMQ09() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03REMV09" value ="<%= msgMT.getE03REMV09() %>" readonly>             
      	</td>     
      	<td> 
      	   <input type="text" name="E03RESQ09" value ="<%= msgMT.getE03RESQ09() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03RESV09" value ="<%= msgMT.getE03RESV09() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if ((!msgMT.getE03REMV10().equals("0.00")) || (!msgMT.getE03RESV10().equals("0.00")))  {%> 
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E03REMD10" size="5" maxlength="4" value="<%= msgMT.getE03REMD10() %>" readonly> 
	      	<input type="text" name="E03REMN10" size="40" maxlength="35" value="<%= msgMT.getE03REMN10() %>" readonly >
		</td>
		<td> 
      	   <input type="text" name="E03REMQ10" value ="<%= msgMT.getE03REMQ10() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03REMV10" value ="<%= msgMT.getE03REMV10() %>" readonly>             
      	</td>     
      	<td> 
      	   <input type="text" name="E03RESQ10" value ="<%= msgMT.getE03RESQ10() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03RESV10" value ="<%= msgMT.getE03RESV10() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if ((!msgMT.getE03REMV11().equals("0.00")) || (!msgMT.getE03RESV11().equals("0.00")))  {%> 
     <tr id=trdark> 	      
	    <td>
	    	<input type="text" name="E03REMD11" size="5" maxlength="4" value="<%= msgMT.getE03REMD11() %>" readonly> 
	      	<input type="text" name="E03REMN11" size="40" maxlength="35" value="<%= msgMT.getE03REMN11() %>" readonly >
		</td>
		<td> 
      	   <input type="text" name="E03REMQ11" value ="<%= msgMT.getE03REMQ11() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03REMV11" value ="<%= msgMT.getE03REMV11() %>" readonly>             
      	</td>    
      	<td> 
      	   <input type="text" name="E03RESQ11" value ="<%= msgMT.getE03RESQ11() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03RESV11" value ="<%= msgMT.getE03RESV11() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if ((!msgMT.getE03REMV12().equals("0.00")) || (!msgMT.getE03RESV12().equals("0.00")))  {%> 
     <tr id=trclear> 	      
	    <td>
	    	<input type="text" name="E03REMD12" size="5" maxlength="4" value="<%= msgMT.getE03REMD12() %>" readonly> 
	      	<input type="text" name="E03REMN12" size="40" maxlength="35" value="<%= msgMT.getE03REMN12() %>" readonly >
		</td>
		<td> 
      	   <input type="text" name="E03REMQ12" value ="<%= msgMT.getE03REMQ12() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03REMV12" value ="<%= msgMT.getE03REMV12() %>" readonly>             
      	</td>    
      	<td> 
      	   <input type="text" name="E03RESQ12" value ="<%= msgMT.getE03RESQ12() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03RESV12" value ="<%= msgMT.getE03RESV12() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if ((!msgMT.getE03REMV13().equals("0.00")) || (!msgMT.getE03RESV13().equals("0.00")))  {%> 
     <tr id=trdark> 	      
	    <td>
	    	<input type="text" name="E03REMD13" size="5" maxlength="4" value="<%= msgMT.getE03REMD13() %>" readonly> 
	      	<input type="text" name="E03REMN13" size="40" maxlength="35" value="<%= msgMT.getE03REMN13() %>" readonly >
		</td>
		<td> 
      	   <input type="text" name="E03REMQ13" value ="<%= msgMT.getE03REMQ13() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03REMV13" value ="<%= msgMT.getE03REMV13() %>" readonly>             
      	</td>    
      	<td> 
      	   <input type="text" name="E03RESQ13" value ="<%= msgMT.getE03RESQ13() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03RESV13" value ="<%= msgMT.getE03RESV13() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if ((!msgMT.getE03REMV14().equals("0.00")) || (!msgMT.getE03RESV14().equals("0.00")))  {%> 
     <tr id=trclear> 	      
	    <td>
	    	<input type="text" name="E03REMD14" size="5" maxlength="4" value="<%= msgMT.getE03REMD14() %>" readonly> 
	      	<input type="text" name="E03REMN14" size="40" maxlength="35" value="<%= msgMT.getE03REMN14() %>" readonly >
		</td>
		<td> 
      	   <input type="text" name="E03REMQ14" value ="<%= msgMT.getE03REMQ14() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03REMV14" value ="<%= msgMT.getE03REMV14() %>" readonly>             
      	</td>    
      	<td> 
      	   <input type="text" name="E03RESQ14" value ="<%= msgMT.getE03RESQ14() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03RESV14" value ="<%= msgMT.getE03RESV14() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if ((!msgMT.getE03REMV15().equals("0.00")) || (!msgMT.getE03RESV15().equals("0.00")))  {%> 
     <tr id=trdark> 	      
	    <td>
	    	<input type="text" name="E03REMD15" size="5" maxlength="4" value="<%= msgMT.getE03REMD15() %>" readonly> 
	      	<input type="text" name="E03REMN15" size="40" maxlength="35" value="<%= msgMT.getE03REMN15() %>" readonly >
		</td>
		<td> 
      	   <input type="text" name="E03REMQ15" value ="<%= msgMT.getE03REMQ15() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03REMV15" value ="<%= msgMT.getE03REMV15() %>" readonly>             
      	</td>    
      	<td> 
      	   <input type="text" name="E03RESQ15" value ="<%= msgMT.getE03RESQ15() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03RESV15" value ="<%= msgMT.getE03RESV15() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if ((!msgMT.getE03REMV16().equals("0.00")) || (!msgMT.getE03RESV16().equals("0.00")))  {%> 
     <tr id=trclear> 	      
	    <td>
	    	<input type="text" name="E03REMD16" size="5" maxlength="4" value="<%= msgMT.getE03REMD16() %>" readonly> 
	      	<input type="text" name="E03REMN16" size="40" maxlength="35" value="<%= msgMT.getE03REMN16() %>" readonly >
		</td>
		<td> 
      	   <input type="text" name="E03REMQ16" value ="<%= msgMT.getE03REMQ16() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03REMV16" value ="<%= msgMT.getE03REMV16() %>" readonly>             
      	</td>    
      	<td> 
      	   <input type="text" name="E03RESQ16" value ="<%= msgMT.getE03RESQ16() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03RESV16" value ="<%= msgMT.getE03RESV16() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if ((!msgMT.getE03REMV17().equals("0.00")) || (!msgMT.getE03RESV17().equals("0.00")))  {%> 
     <tr id=trdark> 	      
	    <td>
	    	<input type="text" name="E03REMD17" size="5" maxlength="4" value="<%= msgMT.getE03REMD17() %>" readonly> 
	      	<input type="text" name="E03REMN17" size="40" maxlength="35" value="<%= msgMT.getE03REMN17() %>" readonly >
		</td>
		<td> 
      	   <input type="text" name="E03REMQ17" value ="<%= msgMT.getE03REMQ17() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03REMV17" value ="<%= msgMT.getE03REMV17() %>" readonly>             
      	</td>    
      	<td> 
      	   <input type="text" name="E03RESQ17" value ="<%= msgMT.getE03RESQ17() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03RESV17" value ="<%= msgMT.getE03RESV17() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if ((!msgMT.getE03REMV18().equals("0.00")) || (!msgMT.getE03RESV18().equals("0.00")))  {%> 
     <tr id=trclear> 	      
	    <td>
	    	<input type="text" name="E03REMD18" size="5" maxlength="4" value="<%= msgMT.getE03REMD18() %>" readonly> 
	      	<input type="text" name="E03REMN18" size="40" maxlength="35" value="<%= msgMT.getE03REMN18() %>" readonly >
		</td>
		<td> 
      	   <input type="text" name="E03REMQ18" value ="<%= msgMT.getE03REMQ18() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03REMV18" value ="<%= msgMT.getE03REMV18() %>" readonly>             
      	</td>    
      	<td> 
      	   <input type="text" name="E03RESQ18" value ="<%= msgMT.getE03RESQ18() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03RESV18" value ="<%= msgMT.getE03RESV18() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if ((!msgMT.getE03REMV19().equals("0.00")) || (!msgMT.getE03RESV19().equals("0.00")))  {%> 
     <tr id=trdark> 	      
	    <td>
	    	<input type="text" name="E03REMD19" size="5" maxlength="4" value="<%= msgMT.getE03REMD19() %>" readonly> 
	      	<input type="text" name="E03REMN19" size="40" maxlength="35" value="<%= msgMT.getE03REMN19() %>" readonly >
		</td>
		<td> 
      	   <input type="text" name="E03REMQ19" value ="<%= msgMT.getE03REMQ19() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03REMV19" value ="<%= msgMT.getE03REMV19() %>" readonly>             
      	</td>    
      	<td> 
      	   <input type="text" name="E03RESQ19" value ="<%= msgMT.getE03RESQ19() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03RESV19" value ="<%= msgMT.getE03RESV19() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if ((!msgMT.getE03REMV20().equals("0.00")) || (!msgMT.getE03RESV20().equals("0.00")))  {%> 
     <tr id=trclear> 	      
	    <td>
	    	<input type="text" name="E03REMD20" size="5" maxlength="4" value="<%= msgMT.getE03REMD20() %>" readonly> 
	      	<input type="text" name="E03REMN20" size="40" maxlength="35" value="<%= msgMT.getE03REMN20() %>" readonly >
		</td>
		<td> 
      	   <input type="text" name="E03REMQ20" value ="<%= msgMT.getE03REMQ20() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03REMV20" value ="<%= msgMT.getE03REMV20() %>" readonly>             
      	</td>    
      	<td> 
      	   <input type="text" name="E03RESQ20" value ="<%= msgMT.getE03RESQ20() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E03RESV20" value ="<%= msgMT.getE03RESV20() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
  </table>
    </td>
   </tr>
  </table>
  
  
  <p align="center"> 
    <input id="EIBSBTN" type="submit" name="Submit" value="Recibir">
  </p>
  
</form>
</body>
</html>
