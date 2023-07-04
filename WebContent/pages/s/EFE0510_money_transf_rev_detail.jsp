<html> 
<head>
<title>Recibo de Remesas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "reverList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
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
   reverList.setCurrentRow(row);
   datapro.eibs.beans.EFE051004Message msgMT = (datapro.eibs.beans.EFE051004Message) reverList.getRecord();
   
%>
</head>
<body>

<H3 align="center">Reversion de Remesas de Efectivo<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="money_transf_rev_detail,EFE0510"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.moneytransfer.JSEFE0510">
  
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="650">
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
	        <input type="text" name="E04REMREF" size="10" maxlength="2" readonly value="<%= msgMT.getE04REMREF() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right">Banco/Oficina : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E04REMBNK" size="3" maxlength="2" readonly value="<%= msgMT.getE04REMBNK() %>"><b>-</b> 
	        <input type="text" name="E04REMBRN" size="4" maxlength="3" readonly value="<%= msgMT.getE04REMBRN() %>">
	      </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Aprobado por : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E04REMAPU" size="12" maxlength="10" readonly value="<%= msgMT.getE04REMAPU() %>">
      	  </td>     
      	</tr>
      	<tr id=trclear>
          <td nowrap>
              <div align="right">Moneda : </div>
          </td>
          <td nowrap>
              <input type="text" name="E04REMCCY" size="4" maxlength="3" value="<%= msgMT.getE04REMCCY() %>" readonly>
          </td>
        </tr>
        <tr id=trdark>
          <td nowrap>
             <div align="right">Monto Solicitado : </div>
          </td>
          <td nowrap>
             <input type="text" name="E04REMRAM" value ="<%= Util.formatCCY(msgMT.getE04REMRAM()) %>" readonly>             
          </td>
        </tr>
        <tr id=trclear>
          <td nowrap>
             <div align="right">Monto Aprobado : </div>
          </td>
          <td nowrap>
             <input type="text" name="E04REMAAM" value ="<%= Util.formatCCY(msgMT.getE04REMAAM()) %>" readonly>             
          </td>
        </tr>
        <tr id=trclear>
          <td nowrap>
             <div align="right">Tipo de Remesa : </div>
          </td>
          <td nowrap>
             <input type="text" readonly name="E04REMTYP" size="10" maxlength="8" 
				value="<% if (msgMT.getE04REMTYP().equals("C")) 
				             { out.print("Canje"); }
                    	  else if (msgMT.getE04REMTYP().equals("A")) 
				             { out.print("ATM"); } 
				          else
                    	     { out.print("Normal"); } %>">             
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
		<td align="center" width=50%>Denominacion</td>
		<td align="center" width=25%>Cantidad Solicitada</td>
		<td align="center" width=25%>Monto Solicitado</td>
	 </tr>
	 <%if (!msgMT.getE04REMV01().equals("0.00")) {%> 
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E04REMD01" size="5" maxlength="4" value="<%= msgMT.getE04REMD01() %>" readonly> 
	      	<input type="text" name="E04REMN01" size="40" maxlength="35" value="<%= msgMT.getE04REMN01() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E04REMQ01" value ="<%= msgMT.getE04REMQ01() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E04REMV01" value ="<%= msgMT.getE04REMV01() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE04REMV02().equals("0.00")) {%> 
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E04REMD02" size="5" maxlength="4" value="<%= msgMT.getE04REMD02() %>" readonly> 
	      	<input type="text" name="E04REMN02" size="40" maxlength="35" value="<%= msgMT.getE04REMN02() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E04REMQ02" value ="<%= msgMT.getE04REMQ02() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E04REMV02" value ="<%= msgMT.getE04REMV02() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE04REMV03().equals("0.00")) {%> 
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E04REMD03" size="5" maxlength="4" value="<%= msgMT.getE04REMD03() %>" readonly> 
	      	<input type="text" name="E04REMN03" size="40" maxlength="35" value="<%= msgMT.getE04REMN03() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E04REMQ03" value ="<%= msgMT.getE04REMQ03() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E04REMV03" value ="<%= msgMT.getE04REMV03() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE04REMV04().equals("0.00")) {%> 
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E04REMD04" size="5" maxlength="4" value="<%= msgMT.getE04REMD04() %>" readonly> 
	      	<input type="text" name="E04REMN04" size="40" maxlength="35" value="<%= msgMT.getE04REMN04() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E04REMQ04" value ="<%= msgMT.getE04REMQ04() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E04REMV04" value ="<%= msgMT.getE04REMV04() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE04REMV05().equals("0.00")) {%> 
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E04REMD05" size="5" maxlength="4" value="<%= msgMT.getE04REMD05() %>" readonly> 
	      	<input type="text" name="E04REMN05" size="40" maxlength="35" value="<%= msgMT.getE04REMN05() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E04REMQ05" value ="<%= msgMT.getE04REMQ05() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E04REMV05" value ="<%= msgMT.getE04REMV05() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE04REMV06().equals("0.00")) {%> 
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E04REMD06" size="5" maxlength="4" value="<%= msgMT.getE04REMD06() %>" readonly> 
	      	<input type="text" name="E04REMN06" size="40" maxlength="35" value="<%= msgMT.getE04REMN06() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E04REMQ06" value ="<%= msgMT.getE04REMQ06() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E04REMV06" value ="<%= msgMT.getE04REMV06() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE04REMV07().equals("0.00")) {%> 
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E04REMD07" size="5" maxlength="4" value="<%= msgMT.getE04REMD07() %>" readonly> 
	      	<input type="text" name="E04REMN07" size="40" maxlength="35" value="<%= msgMT.getE04REMN07() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E04REMQ07" value ="<%= msgMT.getE04REMQ07() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E04REMV07" value ="<%= msgMT.getE04REMV07() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE04REMV08().equals("0.00")) {%> 
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E04REMD08" size="5" maxlength="4" value="<%= msgMT.getE04REMD08() %>" readonly> 
	      	<input type="text" name="E04REMN08" size="40" maxlength="35" value="<%= msgMT.getE04REMN08() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E04REMQ08" value ="<%= msgMT.getE04REMQ08() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E04REMV08" value ="<%= msgMT.getE04REMV08() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE04REMV09().equals("0.00")) {%> 
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E04REMD09" size="5" maxlength="4" value="<%= msgMT.getE04REMD09() %>" readonly> 
	      	<input type="text" name="E04REMN09" size="40" maxlength="35" value="<%= msgMT.getE04REMN09() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E04REMQ09" value ="<%= msgMT.getE04REMQ09() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E04REMV09" value ="<%= msgMT.getE04REMV09() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE04REMV10().equals("0.00")) {%> 
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E04REMD10" size="5" maxlength="4" value="<%= msgMT.getE04REMD10() %>" readonly> 
	      	<input type="text" name="E04REMN10" size="40" maxlength="35" value="<%= msgMT.getE04REMN10() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E04REMQ10" value ="<%= msgMT.getE04REMQ10() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E04REMV10" value ="<%= msgMT.getE04REMV10() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE04REMV11().equals("0.00")) {%> 
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E04REMD11" size="5" maxlength="4" value="<%= msgMT.getE04REMD11() %>" readonly> 
	      	<input type="text" name="E04REMN11" size="40" maxlength="35" value="<%= msgMT.getE04REMN11() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E04REMQ11" value ="<%= msgMT.getE04REMQ11() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E04REMV11" value ="<%= msgMT.getE04REMV11() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE04REMV12().equals("0.00")) {%> 
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E04REMD12" size="5" maxlength="4" value="<%= msgMT.getE04REMD12() %>" readonly> 
	      	<input type="text" name="E04REMN12" size="40" maxlength="35" value="<%= msgMT.getE04REMN12() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E04REMQ12" value ="<%= msgMT.getE04REMQ12() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E04REMV12" value ="<%= msgMT.getE04REMV12() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE04REMV13().equals("0.00")) {%> 
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E04REMD13" size="5" maxlength="4" value="<%= msgMT.getE04REMD13() %>" readonly> 
	      	<input type="text" name="E04REMN13" size="40" maxlength="35" value="<%= msgMT.getE04REMN13() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E04REMQ13" value ="<%= msgMT.getE04REMQ13() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E04REMV13" value ="<%= msgMT.getE04REMV13() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE04REMV14().equals("0.00")) {%> 
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E04REMD14" size="5" maxlength="4" value="<%= msgMT.getE04REMD14() %>" readonly> 
	      	<input type="text" name="E04REMN14" size="40" maxlength="35" value="<%= msgMT.getE04REMN14() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E04REMQ14" value ="<%= msgMT.getE04REMQ14() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E04REMV14" value ="<%= msgMT.getE04REMV14() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE04REMV15().equals("0.00")) {%> 
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E04REMD15" size="5" maxlength="4" value="<%= msgMT.getE04REMD15() %>" readonly> 
	      	<input type="text" name="E04REMN15" size="40" maxlength="35" value="<%= msgMT.getE04REMN15() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E04REMQ15" value ="<%= msgMT.getE04REMQ15() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E04REMV15" value ="<%= msgMT.getE04REMV15() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE04REMV16().equals("0.00")) {%> 
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E04REMD16" size="5" maxlength="4" value="<%= msgMT.getE04REMD16() %>" readonly> 
	      	<input type="text" name="E04REMN16" size="40" maxlength="35" value="<%= msgMT.getE04REMN16() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E04REMQ16" value ="<%= msgMT.getE04REMQ16() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E04REMV16" value ="<%= msgMT.getE04REMV16() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE04REMV17().equals("0.00")) {%> 
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E04REMD17" size="5" maxlength="4" value="<%= msgMT.getE04REMD17() %>" readonly> 
	      	<input type="text" name="E04REMN17" size="40" maxlength="35" value="<%= msgMT.getE04REMN17() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E04REMQ17" value ="<%= msgMT.getE04REMQ17() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E04REMV17" value ="<%= msgMT.getE04REMV17() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE04REMV18().equals("0.00")) {%> 
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E04REMD18" size="5" maxlength="4" value="<%= msgMT.getE04REMD18() %>" readonly> 
	      	<input type="text" name="E04REMN18" size="40" maxlength="35" value="<%= msgMT.getE04REMN18() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E04REMQ18" value ="<%= msgMT.getE04REMQ18() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E04REMV18" value ="<%= msgMT.getE04REMV18() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE04REMV19().equals("0.00")) {%> 
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E04REMD19" size="5" maxlength="4" value="<%= msgMT.getE04REMD19() %>" readonly> 
	      	<input type="text" name="E04REMN19" size="40" maxlength="35" value="<%= msgMT.getE04REMN19() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E04REMQ19" value ="<%= msgMT.getE04REMQ19() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E04REMV19" value ="<%= msgMT.getE04REMV19() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE04REMV20().equals("0.00")) {%> 
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E04REMD20" size="5" maxlength="4" value="<%= msgMT.getE04REMD20() %>" readonly> 
	      	<input type="text" name="E04REMN20" size="40" maxlength="35" value="<%= msgMT.getE04REMN20() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E04REMQ20" value ="<%= msgMT.getE04REMQ20() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E04REMV20" value ="<%= msgMT.getE04REMV20() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
  	</table>
    </td>
   </tr>
  </table>
  
  <p align="center"> 
    <input id="EIBSBTN" type="submit" name="Submit" value="Reversar">
  </p>
  
</form>
</body>
</html>
