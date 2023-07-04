<html> 
<head>
<title>Mantenimiento de Remesas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "mtList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<% 
   	int row = 0;
   	try { row = Integer.parseInt(request.getParameter("ROW"));} catch (Exception e) {}
   	mtList.setCurrentRow(row);
   	datapro.eibs.beans.EFE050001Message msgMT = (datapro.eibs.beans.EFE050001Message) mtList.getRecord();
   
%>
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
</head>
<body>

<H3 align="center">Mantenimiento de Remesas de Efectivo<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="money_transfer_maint,EFE0500"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.moneytransfer.JSEFE0500">
 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= row %>">
  
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
	      	<input type="text" name="E01REMBNK" size="3" maxlength="2" value="<%= msgMT.getE01REMBNK() %>" readonly><b>-</b> 
	        <input type="text" name="E01REMBRN" size="4" maxlength="3" value="<%= msgMT.getE01REMBRN() %>" readonly>
	      </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Carrier : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01REMCAR" size="5" maxlength="4" value="<%= msgMT.getE01REMCAR() %>">
      	    <a href="javascript:GetCodeCNOFC('E01REMCAR','YK')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>
      	<tr id=trclear>
          <td nowrap>
              <div align="right">Moneda : </div>
          </td>
          <td nowrap>
              <input type="text" name="E01REMCCY" size="4" maxlength="3" value="<%= msgMT.getE01REMCCY() %>">
              <a href="javascript:GetCurrency('E01REMCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" align="bottom" border="0"></a> 
          </td>
        </tr>
        <tr id=trdark>          
          <td nowrap>
             <div align="right">Tipo de Remesa : </div>
          </td>
          <td nowrap>
             <select name="E01REMTYP">
          	  	<option value="C" <%if (msgMT.getE01REMTYP().equals("C")) { out.print("selected"); }%>>Canje</option>
                <option value="A" <%if (msgMT.getE01REMTYP().equals("A")) { out.print("selected"); }%>>ATM</option>
                <option value="N" <%if (msgMT.getE01REMTYP().equals("N")) { out.print("selected"); }%>>Normal</option>
           	</select>
          </td>
        </tr>
        <tr id=trclear>
          <td nowrap>
             <div align="right">Monto Total Solicitado : </div>
          </td>
          <td nowrap>
             <input type="text" name="E01REMRAM" value ="<%= msgMT.getE01REMRAM() %>" onkeypress="enterDecimal()">             
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
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E01REMD01" size="5" maxlength="4" value="<%= msgMT.getE01REMD01() %>"> 
	      	<input type="text" name="E01REMN01" size="40" maxlength="35" value="<%= msgMT.getE01REMN01() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E01REMD01','E01REMN01','YN',document.forms[0].E01REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ01" value ="<%= msgMT.getE01REMQ01() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV01" value ="<%= msgMT.getE01REMV01() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E01REMD02" size="5" maxlength="4" value="<%= msgMT.getE01REMD02() %>"> 
	      	<input type="text" name="E01REMN02" size="40" maxlength="35" value="<%= msgMT.getE01REMN02() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E01REMD02','E01REMN02','YN',document.forms[0].E01REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ02" value ="<%= msgMT.getE01REMQ02() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV02" value ="<%= msgMT.getE01REMV02() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E01REMD03" size="5" maxlength="4" value="<%= msgMT.getE01REMD03() %>"> 
	      	<input type="text" name="E01REMN03" size="40" maxlength="35" value="<%= msgMT.getE01REMN03() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E01REMD03','E01REMN03','YN',document.forms[0].E01REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ03" value ="<%= msgMT.getE01REMQ03() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV03" value ="<%= msgMT.getE01REMV03() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E01REMD04" size="5" maxlength="4" value="<%= msgMT.getE01REMD04() %>"> 
	      	<input type="text" name="E01REMN04" size="40" maxlength="35" value="<%= msgMT.getE01REMN04() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E01REMD04','E01REMN04','YN',document.forms[0].E01REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ04" value ="<%= msgMT.getE01REMQ04() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV04" value ="<%= msgMT.getE01REMV04() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E01REMD05" size="5" maxlength="4" value="<%= msgMT.getE01REMD05() %>"> 
	      	<input type="text" name="E01REMN05" size="40" maxlength="35" value="<%= msgMT.getE01REMN05() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E01REMD05','E01REMN05','YN',document.forms[0].E01REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ05" value ="<%= msgMT.getE01REMQ05() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV05" value ="<%= msgMT.getE01REMV05() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E01REMD06" size="5" maxlength="4" value="<%= msgMT.getE01REMD06() %>"> 
	      	<input type="text" name="E01REMN06" size="40" maxlength="35" value="<%= msgMT.getE01REMN06() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E01REMD06','E01REMN06','YN',document.forms[0].E01REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ06" value ="<%= msgMT.getE01REMQ06() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV06" value ="<%= msgMT.getE01REMV06() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E01REMD07" size="5" maxlength="4" value="<%= msgMT.getE01REMD07() %>"> 
	      	<input type="text" name="E01REMN07" size="40" maxlength="35" value="<%= msgMT.getE01REMN07() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E01REMD07','E01REMN07','YN',document.forms[0].E01REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ07" value ="<%= msgMT.getE01REMQ07() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV07" value ="<%= msgMT.getE01REMV07() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E01REMD08" size="5" maxlength="4" value="<%= msgMT.getE01REMD08() %>"> 
	      	<input type="text" name="E01REMN08" size="40" maxlength="35" value="<%= msgMT.getE01REMN08() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E01REMD08','E01REMN08','YN',document.forms[0].E01REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ08" value ="<%= msgMT.getE01REMQ08() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV08" value ="<%= msgMT.getE01REMV08() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E01REMD09" size="5" maxlength="4" value="<%= msgMT.getE01REMD09() %>"> 
	      	<input type="text" name="E01REMN09" size="40" maxlength="35" value="<%= msgMT.getE01REMN09() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E01REMD09','E01REMN09','YN',document.forms[0].E01REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ09" value ="<%= msgMT.getE01REMQ09() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV09" value ="<%= msgMT.getE01REMV09() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E01REMD10" size="5" maxlength="4" value="<%= msgMT.getE01REMD10() %>"> 
	      	<input type="text" name="E01REMN10" size="40" maxlength="35" value="<%= msgMT.getE01REMN10() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E01REMD10','E01REMN10','YN',document.forms[0].E01REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ10" value ="<%= msgMT.getE01REMQ10() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV10" value ="<%= msgMT.getE01REMV10() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E01REMD11" size="5" maxlength="4" value="<%= msgMT.getE01REMD11() %>"> 
	      	<input type="text" name="E01REMN11" size="40" maxlength="35" value="<%= msgMT.getE01REMN11() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E01REMD11','E01REMN11','YN',document.forms[0].E01REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ11" value ="<%= msgMT.getE01REMQ11() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV11" value ="<%= msgMT.getE01REMV11() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E01REMD12" size="5" maxlength="4" value="<%= msgMT.getE01REMD12() %>"> 
	      	<input type="text" name="E01REMN12" size="40" maxlength="35" value="<%= msgMT.getE01REMN12() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E01REMD12','E01REMN12','YN',document.forms[0].E01REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ12" value ="<%= msgMT.getE01REMQ12() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV12" value ="<%= msgMT.getE01REMV12() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E01REMD13" size="5" maxlength="4" value="<%= msgMT.getE01REMD13() %>"> 
	      	<input type="text" name="E01REMN13" size="40" maxlength="35" value="<%= msgMT.getE01REMN13() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E01REMD13','E01REMN13','YN',document.forms[0].E01REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ13" value ="<%= msgMT.getE01REMQ13() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV13" value ="<%= msgMT.getE01REMV13() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E01REMD14" size="5" maxlength="4" value="<%= msgMT.getE01REMD14() %>"> 
	      	<input type="text" name="E01REMN14" size="40" maxlength="35" value="<%= msgMT.getE01REMN14() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E01REMD14','E01REMN14','YN',document.forms[0].E01REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ14" value ="<%= msgMT.getE01REMQ14() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV14" value ="<%= msgMT.getE01REMV14() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E01REMD15" size="5" maxlength="4" value="<%= msgMT.getE01REMD15() %>"> 
	      	<input type="text" name="E01REMN15" size="40" maxlength="35" value="<%= msgMT.getE01REMN15() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E01REMD15','E01REMN15','YN',document.forms[0].E01REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ15" value ="<%= msgMT.getE01REMQ15() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV15" value ="<%= msgMT.getE01REMV15() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E01REMD16" size="5" maxlength="4" value="<%= msgMT.getE01REMD16() %>"> 
	      	<input type="text" name="E01REMN16" size="40" maxlength="35" value="<%= msgMT.getE01REMN16() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E01REMD16','E01REMN16','YN',document.forms[0].E01REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ16" value ="<%= msgMT.getE01REMQ16() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV16" value ="<%= msgMT.getE01REMV16() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E01REMD17" size="5" maxlength="4" value="<%= msgMT.getE01REMD17() %>"> 
	      	<input type="text" name="E01REMN17" size="40" maxlength="35" value="<%= msgMT.getE01REMN17() %>" readonly >
      	   <a href="javascript:GetCodeDescAuxCNOFC('E01REMD17','E01REMN17','YN',document.forms[0].E01REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ17" value ="<%= msgMT.getE01REMQ17() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV17" value ="<%= msgMT.getE01REMV17() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E01REMD18" size="5" maxlength="4" value="<%= msgMT.getE01REMD18() %>"> 
	      	<input type="text" name="E01REMN18" size="40" maxlength="35" value="<%= msgMT.getE01REMN18() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E01REMD18','E01REMN18','YN',document.forms[0].E01REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ18" value ="<%= msgMT.getE01REMQ18() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV18" value ="<%= msgMT.getE01REMV18() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E01REMD19" size="5" maxlength="4" value="<%= msgMT.getE01REMD19() %>"> 
	      	<input type="text" name="E01REMN19" size="40" maxlength="35" value="<%= msgMT.getE01REMN19() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E01REMD19','E01REMN19','YN',document.forms[0].E01REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ19" value ="<%= msgMT.getE01REMQ19() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV19" value ="<%= msgMT.getE01REMV19() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E01REMD20" size="5" maxlength="4" value="<%= msgMT.getE01REMD20() %>"> 
	      	<input type="text" name="E01REMN20" size="40" maxlength="35" value="<%= msgMT.getE01REMN20() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E01REMD20','E01REMN20','YN',document.forms[0].E01REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E01REMQ20" value ="<%= msgMT.getE01REMQ20() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E01REMV20" value ="<%= msgMT.getE01REMV20() %>" onkeypress="enterDecimal()">             
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
