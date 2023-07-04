<html> 
<head>
<title>Aprobacion de Remesas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
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
   appList.setCurrentRow(row);
   datapro.eibs.beans.EFE051001Message msgMT = (datapro.eibs.beans.EFE051001Message) appList.getRecord();
   
%>
</head>
<body>

<H3 align="center">Aprobacion de Remesas de Efectivo<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="money_transf_app_detail,EFE0510"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.moneytransfer.JSEFE0510">
  
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
  <INPUT TYPE=HIDDEN NAME="H01FLGWK1" VALUE="A">
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
	        <input type="text" name="E01REMREF" size="10" maxlength="2" readonly value="<%= msgMT.getE01REMREF() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right">Banco/Oficina : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01REMBNK" size="3" maxlength="2" readonly value="<%= msgMT.getE01REMBNK() %>"><b>-</b> 
	        <input type="text" name="E01REMBRN" size="4" maxlength="3" readonly value="<%= msgMT.getE01REMBRN() %>">
	      </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Creada por : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01REMCRU" size="12" maxlength="10" readonly value="<%= msgMT.getE01REMCRU() %>">
      	  </td>     
      	</tr>
      	<tr id=trclear>
          <td nowrap>
              <div align="right">Moneda : </div>
          </td>
          <td nowrap>
              <input type="text" name="E01REMCCY" size="4" maxlength="3" value="<%= msgMT.getE01REMCCY() %>" readonly>
          </td>
        </tr>
        <tr id=trdark>
          <td nowrap>
             <div align="right">Monto Solicitado : </div>
          </td>
          <td nowrap>
             <input type="text" name="E01REMRAM" value ="<%= Util.formatCCY(msgMT.getE01REMRAM()) %>" readonly>             
          </td>
        </tr>
        <tr id=trclear>
          <td nowrap>
             <div align="right">Tipo de Remesa : </div>
          </td>
          <td nowrap>
             <input type="text" readonly name="E01REMTYP" size="10" maxlength="8" 
				value="<% if (msgMT.getE01REMTYP().equals("C")) 
				             { out.print("Canje"); }
                    	  else if (msgMT.getE01REMTYP().equals("A")) 
				             { out.print("ATM"); } 
				          else
                    	     { out.print("Normal"); } %>">             
          </td>
        </tr>
        <tr id=trdark>
          <td nowrap>
             <div align="right">Monto Aprobado : </div>
          </td>
          <td nowrap>
             <input type="text" name="E01REMAAM" value ="<%= msgMT.getE01REMAAM() %>" onkeypress="enterDecimal()">             
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
	 <%if (!msgMT.getE01REMV01().equals("0.00")) {%> 
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E01REMD01" size="5" maxlength="4" value="<%= msgMT.getE01REMD01() %>" readonly> 
	      	<input type="text" name="E01REMN01" size="40" maxlength="35" value="<%= msgMT.getE01REMN01() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ01" value ="<%= msgMT.getE01REMQ01() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV01" value ="<%= msgMT.getE01REMV01() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE01REMV02().equals("0.00")) {%> 
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E01REMD02" size="5" maxlength="4" value="<%= msgMT.getE01REMD02() %>" readonly> 
	      	<input type="text" name="E01REMN02" size="40" maxlength="35" value="<%= msgMT.getE01REMN02() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ02" value ="<%= msgMT.getE01REMQ02() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV02" value ="<%= msgMT.getE01REMV02() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE01REMV03().equals("0.00")) {%> 
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E01REMD03" size="5" maxlength="4" value="<%= msgMT.getE01REMD03() %>" readonly> 
	      	<input type="text" name="E01REMN03" size="40" maxlength="35" value="<%= msgMT.getE01REMN03() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ03" value ="<%= msgMT.getE01REMQ03() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV03" value ="<%= msgMT.getE01REMV03() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE01REMV04().equals("0.00")) {%> 
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E01REMD04" size="5" maxlength="4" value="<%= msgMT.getE01REMD04() %>" readonly> 
	      	<input type="text" name="E01REMN04" size="40" maxlength="35" value="<%= msgMT.getE01REMN04() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ04" value ="<%= msgMT.getE01REMQ04() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV04" value ="<%= msgMT.getE01REMV04() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE01REMV05().equals("0.00")) {%> 
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E01REMD05" size="5" maxlength="4" value="<%= msgMT.getE01REMD05() %>" readonly> 
	      	<input type="text" name="E01REMN05" size="40" maxlength="35" value="<%= msgMT.getE01REMN05() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ05" value ="<%= msgMT.getE01REMQ05() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV05" value ="<%= msgMT.getE01REMV05() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE01REMV06().equals("0.00")) {%> 
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E01REMD06" size="5" maxlength="4" value="<%= msgMT.getE01REMD06() %>" readonly> 
	      	<input type="text" name="E01REMN06" size="40" maxlength="35" value="<%= msgMT.getE01REMN06() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ06" value ="<%= msgMT.getE01REMQ06() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV06" value ="<%= msgMT.getE01REMV06() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE01REMV07().equals("0.00")) {%> 
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E01REMD07" size="5" maxlength="4" value="<%= msgMT.getE01REMD07() %>" readonly> 
	      	<input type="text" name="E01REMN07" size="40" maxlength="35" value="<%= msgMT.getE01REMN07() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ07" value ="<%= msgMT.getE01REMQ07() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV07" value ="<%= msgMT.getE01REMV07() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE01REMV08().equals("0.00")) {%> 
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E01REMD08" size="5" maxlength="4" value="<%= msgMT.getE01REMD08() %>" readonly> 
	      	<input type="text" name="E01REMN08" size="40" maxlength="35" value="<%= msgMT.getE01REMN08() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ08" value ="<%= msgMT.getE01REMQ08() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV08" value ="<%= msgMT.getE01REMV08() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE01REMV09().equals("0.00")) {%> 
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E01REMD09" size="5" maxlength="4" value="<%= msgMT.getE01REMD09() %>" readonly> 
	      	<input type="text" name="E01REMN09" size="40" maxlength="35" value="<%= msgMT.getE01REMN09() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ09" value ="<%= msgMT.getE01REMQ09() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV09" value ="<%= msgMT.getE01REMV09() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE01REMV10().equals("0.00")) {%> 
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E01REMD10" size="5" maxlength="4" value="<%= msgMT.getE01REMD10() %>" readonly> 
	      	<input type="text" name="E01REMN10" size="40" maxlength="35" value="<%= msgMT.getE01REMN10() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ10" value ="<%= msgMT.getE01REMQ10() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV10" value ="<%= msgMT.getE01REMV10() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE01REMV11().equals("0.00")) {%> 
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E01REMD11" size="5" maxlength="4" value="<%= msgMT.getE01REMD11() %>" readonly> 
	      	<input type="text" name="E01REMN11" size="40" maxlength="35" value="<%= msgMT.getE01REMN11() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ11" value ="<%= msgMT.getE01REMQ11() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV11" value ="<%= msgMT.getE01REMV11() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE01REMV12().equals("0.00")) {%> 
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E01REMD12" size="5" maxlength="4" value="<%= msgMT.getE01REMD12() %>" readonly> 
	      	<input type="text" name="E01REMN12" size="40" maxlength="35" value="<%= msgMT.getE01REMN12() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ12" value ="<%= msgMT.getE01REMQ12() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV12" value ="<%= msgMT.getE01REMV12() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE01REMV13().equals("0.00")) {%> 
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E01REMD13" size="5" maxlength="4" value="<%= msgMT.getE01REMD13() %>" readonly> 
	      	<input type="text" name="E01REMN13" size="40" maxlength="35" value="<%= msgMT.getE01REMN13() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ13" value ="<%= msgMT.getE01REMQ13() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV13" value ="<%= msgMT.getE01REMV13() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE01REMV14().equals("0.00")) {%> 
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E01REMD14" size="5" maxlength="4" value="<%= msgMT.getE01REMD14() %>" readonly> 
	      	<input type="text" name="E01REMN14" size="40" maxlength="35" value="<%= msgMT.getE01REMN14() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ14" value ="<%= msgMT.getE01REMQ14() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV14" value ="<%= msgMT.getE01REMV14() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE01REMV15().equals("0.00")) {%> 
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E01REMD15" size="5" maxlength="4" value="<%= msgMT.getE01REMD15() %>" readonly> 
	      	<input type="text" name="E01REMN15" size="40" maxlength="35" value="<%= msgMT.getE01REMN15() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ15" value ="<%= msgMT.getE01REMQ15() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV15" value ="<%= msgMT.getE01REMV15() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE01REMV16().equals("0.00")) {%> 
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E01REMD16" size="5" maxlength="4" value="<%= msgMT.getE01REMD16() %>" readonly> 
	      	<input type="text" name="E01REMN16" size="40" maxlength="35" value="<%= msgMT.getE01REMN16() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ16" value ="<%= msgMT.getE01REMQ16() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV16" value ="<%= msgMT.getE01REMV16() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE01REMV17().equals("0.00")) {%> 
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E01REMD17" size="5" maxlength="4" value="<%= msgMT.getE01REMD17() %>" readonly> 
	      	<input type="text" name="E01REMN17" size="40" maxlength="35" value="<%= msgMT.getE01REMN17() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ17" value ="<%= msgMT.getE01REMQ17() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV17" value ="<%= msgMT.getE01REMV17() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE01REMV18().equals("0.00")) {%> 
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E01REMD18" size="5" maxlength="4" value="<%= msgMT.getE01REMD18() %>" readonly> 
	      	<input type="text" name="E01REMN18" size="40" maxlength="35" value="<%= msgMT.getE01REMN18() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ18" value ="<%= msgMT.getE01REMQ18() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV18" value ="<%= msgMT.getE01REMV18() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE01REMV19().equals("0.00")) {%> 
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E01REMD19" size="5" maxlength="4" value="<%= msgMT.getE01REMD19() %>" readonly> 
	      	<input type="text" name="E01REMN19" size="40" maxlength="35" value="<%= msgMT.getE01REMN19() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ19" value ="<%= msgMT.getE01REMQ19() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV19" value ="<%= msgMT.getE01REMV19() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
     <%if (!msgMT.getE01REMV20().equals("0.00")) {%> 
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E01REMD20" size="5" maxlength="4" value="<%= msgMT.getE01REMD20() %>" readonly> 
	      	<input type="text" name="E01REMN20" size="40" maxlength="35" value="<%= msgMT.getE01REMN20() %>" readonly >
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ20" value ="<%= msgMT.getE01REMQ20() %>" readonly>             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV20" value ="<%= msgMT.getE01REMV20() %>" readonly>             
      	</td>   
     </tr>
     <%}%>
  	</table>
    </td>
   </tr>
  </table>
  <p align="center"> 
    <input id="EIBSBTN" type="submit" name="Submit" value="Aprobar">
  </p>
  
</form>
</body>
</html>
