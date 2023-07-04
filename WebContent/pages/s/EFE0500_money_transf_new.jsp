<html> 
<head>
<title>Creacion de Remesas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgMT" class= "datapro.eibs.beans.EFE050002Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<% 

	int NEW = 0;
	try { NEW = Integer.parseInt(request.getParameter("NEW"));} catch (Exception e) {}
	if (NEW == 1) {
	msgMT.destroy();
	}

   	if (msgMT == null) msgMT = new datapro.eibs.beans.EFE050002Message();
   	msgMT.setE02REMBNK(userPO.getBank());
	msgMT.setE02REMBRN(userPO.getBranch());   
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

<H3 align="center">Creacion de Remesas de Efectivo<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="money_transfer_new,EFE0500"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.moneytransfer.JSEFE0500">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="300">
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	<tr id=trdark> 	      
	      <td nowrap> 
	        <div align="right">Banco/Oficina : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E02REMBNK" size="3" maxlength="2" value="<%= msgMT.getE02REMBNK() %>"><b>-</b> 
	        <input type="text" name="E02REMBRN" size="4" maxlength="3" value="<%= msgMT.getE02REMBRN() %>">
	        <a href="javascript:GetBranch('E02REMBRN',document.forms[0].E02REMBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a> 
	      </td>
     	</tr>
     	<tr id=trclear> 
		  <td> 
		     <div align="right">Carrier : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E02REMCAR" size="5" maxlength="4" value="<%= msgMT.getE02REMCAR() %>">
      	    <a href="javascript:GetCodeCNOFC('E02REMCAR','YK')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>
      	<tr id=trdark>
          <td nowrap>
              <div align="right">Moneda : </div>
          </td>
          <td nowrap>
              <input type="text" name="E02REMCCY" size="4" maxlength="3" value="<%= msgMT.getE02REMCCY() %>" >
              <a href="javascript:GetCurrency('E02REMCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" align="bottom" border="0"></a> 
          </td>
        </tr>
        <tr id=trclear>          
          <td nowrap>
             <div align="right">Tipo de Remesa : </div>
          </td>
          <td nowrap>
             <select name="E02REMTYP">
          	  	<option value="C" <%if (msgMT.getE02REMTYP().equals("C")) { out.print("selected"); }%>>Canje</option>
                <option value="A" <%if (msgMT.getE02REMTYP().equals("A")) { out.print("selected"); }%>>ATM</option>
                <option value="N" <%if (msgMT.getE02REMTYP().equals("N")) { out.print("selected"); }%>>Normal</option>
           	</select>
          </td>
        </tr>
        <tr id=trdark>          
          <td nowrap>
             <div align="right">Monto Total Solicitado : </div>
          </td>
          <td nowrap>
             <input type="text" name="E02REMRAM" value ="<%= Util.formatCCY(msgMT.getE02REMRAM()) %>" onkeypress="enterDecimal()">             
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
			<input type="text" name="E02REMD01" size="5" maxlength="4" value="<%= msgMT.getE02REMD01() %>"> 
	      	<input type="text" name="E02REMN01" size="40" maxlength="35" value="<%= msgMT.getE02REMN01() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD01','E02REMN01','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E02REMQ01" value ="<%= msgMT.getE02REMQ01() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV01" value ="<%= msgMT.getE02REMV01() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E02REMD02" size="5" maxlength="4" value="<%= msgMT.getE02REMD02() %>"> 
	      	<input type="text" name="E02REMN02" size="40" maxlength="35" value="<%= msgMT.getE02REMN02() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD02','E02REMN02','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E02REMQ02" value ="<%= msgMT.getE02REMQ02() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV02" value ="<%= msgMT.getE02REMV02() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E02REMD03" size="5" maxlength="4" value="<%= msgMT.getE02REMD03() %>"> 
	      	<input type="text" name="E02REMN03" size="40" maxlength="35" value="<%= msgMT.getE02REMN03() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD03','E02REMN03','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E02REMQ03" value ="<%= msgMT.getE02REMQ03() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV03" value ="<%= msgMT.getE02REMV03() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E02REMD04" size="5" maxlength="4" value="<%= msgMT.getE02REMD04() %>"> 
	      	<input type="text" name="E02REMN04" size="40" maxlength="35" value="<%= msgMT.getE02REMN04() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD04','E02REMN04','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E02REMQ04" value ="<%= msgMT.getE02REMQ04() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV04" value ="<%= msgMT.getE02REMV04() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E02REMD05" size="5" maxlength="4" value="<%= msgMT.getE02REMD05() %>"> 
	      	<input type="text" name="E02REMN05" size="40" maxlength="35" value="<%= msgMT.getE02REMN05() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD05','E02REMN05','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E02REMQ05" value ="<%= msgMT.getE02REMQ05() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV05" value ="<%= msgMT.getE02REMV05() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E02REMD06" size="5" maxlength="4" value="<%= msgMT.getE02REMD06() %>"> 
	      	<input type="text" name="E02REMN06" size="40" maxlength="35" value="<%= msgMT.getE02REMN06() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD06','E02REMN06','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E02REMQ06" value ="<%= msgMT.getE02REMQ06() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV06" value ="<%= msgMT.getE02REMV06() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E02REMD07" size="5" maxlength="4" value="<%= msgMT.getE02REMD07() %>"> 
	      	<input type="text" name="E02REMN07" size="40" maxlength="35" value="<%= msgMT.getE02REMN07() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD07','E02REMN07','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E02REMQ07" value ="<%= msgMT.getE02REMQ07() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV07" value ="<%= msgMT.getE02REMV07() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E02REMD08" size="5" maxlength="4" value="<%= msgMT.getE02REMD08() %>"> 
	      	<input type="text" name="E02REMN08" size="40" maxlength="35" value="<%= msgMT.getE02REMN08() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD08','E02REMN08','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E02REMQ08" value ="<%= msgMT.getE02REMQ08() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV08" value ="<%= msgMT.getE02REMV08() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E02REMD09" size="5" maxlength="4" value="<%= msgMT.getE02REMD09() %>"> 
	      	<input type="text" name="E02REMN09" size="40" maxlength="35" value="<%= msgMT.getE02REMN09() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD09','E02REMN09','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E02REMQ09" value ="<%= msgMT.getE02REMQ09() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV09" value ="<%= msgMT.getE02REMV09() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E02REMD10" size="5" maxlength="4" value="<%= msgMT.getE02REMD10() %>"> 
	      	<input type="text" name="E02REMN10" size="40" maxlength="35" value="<%= msgMT.getE02REMN10() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD10','E02REMN10','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E02REMQ10" value ="<%= msgMT.getE02REMQ10() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV10" value ="<%= msgMT.getE02REMV10() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E02REMD11" size="5" maxlength="4" value="<%= msgMT.getE02REMD11() %>"> 
	      	<input type="text" name="E02REMN11" size="40" maxlength="35" value="<%= msgMT.getE02REMN11() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD11','E02REMN11','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E02REMQ11" value ="<%= msgMT.getE02REMQ11() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV11" value ="<%= msgMT.getE02REMV11() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E02REMD12" size="5" maxlength="4" value="<%= msgMT.getE02REMD12() %>"> 
	      	<input type="text" name="E02REMN12" size="40" maxlength="35" value="<%= msgMT.getE02REMN12() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD12','E02REMN12','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E02REMQ12" value ="<%= msgMT.getE02REMQ12() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV12" value ="<%= msgMT.getE02REMV12() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E02REMD13" size="5" maxlength="4" value="<%= msgMT.getE02REMD13() %>"> 
	      	<input type="text" name="E02REMN13" size="40" maxlength="35" value="<%= msgMT.getE02REMN13() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD13','E02REMN13','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E02REMQ13" value ="<%= msgMT.getE02REMQ13() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV13" value ="<%= msgMT.getE02REMV13() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E02REMD14" size="5" maxlength="4" value="<%= msgMT.getE02REMD14() %>"> 
	      	<input type="text" name="E02REMN14" size="40" maxlength="35" value="<%= msgMT.getE02REMN14() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD14','E02REMN14','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E02REMQ14" value ="<%= msgMT.getE02REMQ14() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV14" value ="<%= msgMT.getE02REMV14() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E02REMD15" size="5" maxlength="4" value="<%= msgMT.getE02REMD15() %>"> 
	      	<input type="text" name="E02REMN15" size="40" maxlength="35" value="<%= msgMT.getE02REMN15() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD15','E02REMN15','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E02REMQ15" value ="<%= msgMT.getE02REMQ15() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV15" value ="<%= msgMT.getE02REMV15() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E02REMD16" size="5" maxlength="4" value="<%= msgMT.getE02REMD16() %>"> 
	      	<input type="text" name="E02REMN16" size="40" maxlength="35" value="<%= msgMT.getE02REMN16() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD16','E02REMN16','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E02REMQ16" value ="<%= msgMT.getE02REMQ16() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV16" value ="<%= msgMT.getE02REMV16() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E02REMD17" size="5" maxlength="4" value="<%= msgMT.getE02REMD17() %>"> 
	      	<input type="text" name="E02REMN17" size="40" maxlength="35" value="<%= msgMT.getE02REMN17() %>" readonly >
      	   <a href="javascript:GetCodeDescAuxCNOFC('E02REMD17','E02REMN17','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E02REMQ17" value ="<%= msgMT.getE02REMQ17() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV17" value ="<%= msgMT.getE02REMV17() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E02REMD18" size="5" maxlength="4" value="<%= msgMT.getE02REMD18() %>"> 
	      	<input type="text" name="E02REMN18" size="40" maxlength="35" value="<%= msgMT.getE02REMN18() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD18','E02REMN18','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E02REMQ18" value ="<%= msgMT.getE02REMQ18() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV18" value ="<%= msgMT.getE02REMV18() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trdark> 	      
	    <td>
			<input type="text" name="E02REMD19" size="5" maxlength="4" value="<%= msgMT.getE02REMD19() %>"> 
	      	<input type="text" name="E02REMN19" size="40" maxlength="35" value="<%= msgMT.getE02REMN19() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD19','E02REMN19','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E02REMQ19" value ="<%= msgMT.getE02REMQ19() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV19" value ="<%= msgMT.getE02REMV19() %>" onkeypress="enterDecimal()">             
      	</td>   
     </tr>
     <tr id=trclear> 	      
	    <td>
			<input type="text" name="E02REMD20" size="5" maxlength="4" value="<%= msgMT.getE02REMD20() %>"> 
	      	<input type="text" name="E02REMN20" size="40" maxlength="35" value="<%= msgMT.getE02REMN20() %>" readonly >
      	    <a href="javascript:GetCodeDescAuxCNOFC('E02REMD20','E02REMN20','YN',document.forms[0].E02REMCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
		</td> 
      	<td> 
      	   <input type="text" name="E02REMQ20" value ="<%= msgMT.getE02REMQ20() %>" onkeypress="enterInteger()">             
      	</td>
      	<td> 
      	   <input type="text" name="E02REMV20" value ="<%= msgMT.getE02REMV20() %>" onkeypress="enterDecimal()">             
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
