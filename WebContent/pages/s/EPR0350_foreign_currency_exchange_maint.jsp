<html>  
<head>
<title>Compraventa Moneda Extranjera</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "mtList" 	class= "datapro.eibs.beans.JBObjList"  		scope="session" />
<jsp:useBean id= "error" 	class= "datapro.eibs.beans.ELEERRMessage"  	scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>
<jsp:useBean id= "msgLiquida" class= "datapro.eibs.beans.EPR035002Message"  	scope="session" />

<SCRIPT Language="Javascript">
  
  function showTab(index, name){  
  	for(var i=0;i<5;i++){
   		document.all["Tab"+i].className="tabnormal";
   		document.all["dataTab"+i].style.display="none";
   	}
  
    document.all["Tab"+index].className="tabhighlight";  
  	document.all["dataTab"+index].style.display="";
  	document.all[name].focus();
  }
 
  
</SCRIPT>

<% 

int row = 0;
try { row = Integer.parseInt(request.getParameter("ROW"));} catch (Exception e) {}
mtList.setCurrentRow(row);

if (error.getERRNUM().equals("0") || error.getERRNUM().equals(null)) {

datapro.eibs.beans.EPR035001Message msgInst = (datapro.eibs.beans.EPR035001Message) mtList.getRecord();
int MNT = 0;
try { MNT = Integer.parseInt(request.getParameter("MNT"));} catch (Exception e) {}
if (MNT == 1 )  {
msgLiquida.destroy();
msgLiquida.setE02REQCS1("");
msgLiquida.setE02REQCS2("");
msgLiquida.setE02REQCS3("");
msgLiquida.setE02REQCS4("");
msgLiquida.setE02REQCS5("");
msgLiquida.setE02REQCN1("");
msgLiquida.setE02REQCN2("");
msgLiquida.setE02REQCN3("");
msgLiquida.setE02REQCN4("");
msgLiquida.setE02REQCN5("");
msgLiquida.setE02REQCL1("");
msgLiquida.setE02REQCL2("");
msgLiquida.setE02REQCL3("");
msgLiquida.setE02REQCL4("");
msgLiquida.setE02REQCL5("");
msgLiquida.setE02REQCF1("0");
msgLiquida.setE02REQCF2("0");
msgLiquida.setE02REQCF3("0");
msgLiquida.setE02REQCF4("0");
msgLiquida.setE02REQCF5("0");
msgLiquida.setE02REQCB1("0");
msgLiquida.setE02REQCB2("0");
msgLiquida.setE02REQCB3("0");
msgLiquida.setE02REQCB4("0");
msgLiquida.setE02REQCB5("0");
msgLiquida.setE02REQCT1("0");
msgLiquida.setE02REQCT2("0");
msgLiquida.setE02REQCT3("0");
msgLiquida.setE02REQCT4("0");
msgLiquida.setE02REQCT5("0");
msgLiquida.setE02REQCI1("0");
msgLiquida.setE02REQCI2("0");
msgLiquida.setE02REQCI3("0");
msgLiquida.setE02REQCI4("0");
msgLiquida.setE02REQCI5("0");
msgLiquida.setE02REQCQ1("0");
msgLiquida.setE02REQCQ2("0");
msgLiquida.setE02REQCQ3("0");
msgLiquida.setE02REQCQ4("0");
msgLiquida.setE02REQCQ5("0");
msgLiquida.setE02REQBE1("");
msgLiquida.setE02REQBE2("");
msgLiquida.setE02REQBE3("");
msgLiquida.setE02REQBE4("");
msgLiquida.setE02REQBE5("");
msgLiquida.setE02REQBEN("");
msgLiquida.setE02REQCHK("");
msgLiquida.setE02REQBEI("");
msgLiquida.setE02REQBET("");
msgLiquida.setE02REQBEY("");
msgLiquida.setE02REQBEA("");
msgLiquida.setE02REQINT("");
msgLiquida.setE02REQITN("");
msgLiquida.setE02REQIN1("");
msgLiquida.setE02REQIN2("");
msgLiquida.setE02REQIN3("");
msgLiquida.setE02REQIN4("");
msgLiquida.setE02REQIN5("");
msgLiquida.setE02REQPD1("");
msgLiquida.setE02REQPD2("");
msgLiquida.setE02REQPD3("");
msgLiquida.setE02REQPD4("");
msgLiquida.setE02REQPD5("");
msgLiquida.setE02REQBB1("");
msgLiquida.setE02REQBB2("");
msgLiquida.setE02REQBB3("");
msgLiquida.setE02REQBB4("");
msgLiquida.setE02REQBB5("");
msgLiquida.setE02REQBI1("");
msgLiquida.setE02REQBI2("");
msgLiquida.setE02REQBI3("");
msgLiquida.setE02REQBI4("");
msgLiquida.setE02REQBI5("");
msgLiquida.setE02REQEDE("");
msgLiquida.setE02REQDI4("");
msgLiquida.setE02REQRF1("");
msgLiquida.setE02REQRF2("");
msgLiquida.setE02REQCX1("");
msgLiquida.setE02REQCX2("");
msgLiquida.setE02REQFL1("");
msgLiquida.setE02REQFL2("");

}
msgLiquida.setH02FLGWK2(msgInst.getH01FLGWK2());
msgLiquida.setH02FLGWK3(msgInst.getH01FLGWK3());
msgLiquida.setH02USERID(msgInst.getH01USERID());
msgLiquida.setE02REQREF(msgInst.getE01REQREF());
msgLiquida.setE02REQOPC(msgInst.getE01REQOPC());
msgLiquida.setE02REQOPN(msgInst.getE01REQOPN());
msgLiquida.setE02REQINS(msgInst.getE01REQINS());
msgLiquida.setE02REQINN(msgInst.getE01REQINN());
msgLiquida.setE02REQROR(msgInst.getE01REQROR());
msgLiquida.setE02REQBNK(msgInst.getE01REQBNK());
msgLiquida.setE02REQBRN(msgInst.getE01REQBRN());
msgLiquida.setE02REQBRM(msgInst.getE01REQBRM());
msgLiquida.setE02REQCCY(msgInst.getE01REQCCY());
msgLiquida.setE02REQCCN(msgInst.getE01REQCCN());
msgLiquida.setE02REQCUS(msgInst.getE01REQCUS());
msgLiquida.setE02REQSTN(msgInst.getE01REQSTN());
msgLiquida.setE02REQCUN(msgInst.getE01REQCUN());
msgLiquida.setE02REQOPR(msgInst.getE01REQOPR());
msgLiquida.setE02REQORN(msgInst.getE01REQORN());
msgLiquida.setE02REQCOR(msgInst.getE01REQCOR());
msgLiquida.setE02REQCON(msgInst.getE01REQCON());
msgLiquida.setE02REQVD1(msgInst.getE01REQVD1());
msgLiquida.setE02REQVD2(msgInst.getE01REQVD2());
msgLiquida.setE02REQVD3(msgInst.getE01REQVD3());
msgLiquida.setE02REQFEA(msgInst.getE01REQFEA());
msgLiquida.setE02REQEXR(msgInst.getE01REQEXR());
msgLiquida.setE02REQLCA(msgInst.getE01REQLCA());
msgLiquida.setE02REQCOA(msgInst.getE01REQCOA());
msgLiquida.setE02REQTOA(msgInst.getE01REQTOA());
msgLiquida.setE02REQAAD(msgInst.getE01REQAAD());
msgLiquida.setE02REQALD(msgInst.getE01REQALD());
msgLiquida.setE02REQRF1(msgInst.getE01REQRF1());
msgLiquida.setE02REQRF2(msgInst.getE01REQRF2());
msgLiquida.setE02REQCX1(msgInst.getE01REQCX1());
msgLiquida.setE02REQCX2(msgInst.getE01REQCX2());
msgLiquida.setE02REQFL1(msgInst.getE01REQFL1());
msgLiquida.setE02REQFL2(msgInst.getE01REQFL2());

msgLiquida.setE02OFFOP1(msgInst.getE01OFFOP1());
msgLiquida.setE02OFFOP2(msgInst.getE01OFFOP2());
msgLiquida.setE02OFFOP3(msgInst.getE01OFFOP3());
msgLiquida.setE02OFFOP4(msgInst.getE01OFFOP4());
msgLiquida.setE02OFFOP5(msgInst.getE01OFFOP5());
msgLiquida.setE02OFFCO1(msgInst.getE01OFFCO1());
msgLiquida.setE02OFFCO2(msgInst.getE01OFFCO2());
msgLiquida.setE02OFFCO3(msgInst.getE01OFFCO3());
msgLiquida.setE02OFFCO4(msgInst.getE01OFFCO4());
msgLiquida.setE02OFFCO5(msgInst.getE01OFFCO5());
msgLiquida.setE02OFFBK1(msgInst.getE01OFFBK1());
msgLiquida.setE02OFFBK2(msgInst.getE01OFFBK2());
msgLiquida.setE02OFFBK3(msgInst.getE01OFFBK3());
msgLiquida.setE02OFFBK4(msgInst.getE01OFFBK4());
msgLiquida.setE02OFFBK5(msgInst.getE01OFFBK5());
msgLiquida.setE02OFFBR1(msgInst.getE01OFFBR1());
msgLiquida.setE02OFFBR2(msgInst.getE01OFFBR2());
msgLiquida.setE02OFFBR3(msgInst.getE01OFFBR3());
msgLiquida.setE02OFFBR4(msgInst.getE01OFFBR4());
msgLiquida.setE02OFFBR5(msgInst.getE01OFFBR5());
msgLiquida.setE02OFFCY1(msgInst.getE01OFFCY1());
msgLiquida.setE02OFFCY2(msgInst.getE01OFFCY2());
msgLiquida.setE02OFFCY3(msgInst.getE01OFFCY3());
msgLiquida.setE02OFFCY4(msgInst.getE01OFFCY4());
msgLiquida.setE02OFFCY5(msgInst.getE01OFFCY5());
msgLiquida.setE02OFFAC1(msgInst.getE01OFFAC1());
msgLiquida.setE02OFFAC2(msgInst.getE01OFFAC2());
msgLiquida.setE02OFFAC3(msgInst.getE01OFFAC3());
msgLiquida.setE02OFFAC4(msgInst.getE01OFFAC4());
msgLiquida.setE02OFFAC5(msgInst.getE01OFFAC5());
msgLiquida.setE02OFFVA1(msgInst.getE01OFFVA1());
msgLiquida.setE02OFFVA2(msgInst.getE01OFFVA2());
msgLiquida.setE02OFFVA3(msgInst.getE01OFFVA3());
msgLiquida.setE02OFFVA4(msgInst.getE01OFFVA4());
msgLiquida.setE02OFFVA5(msgInst.getE01OFFVA5());
//if (userPO.getHeader10().equals("A")) {
msgLiquida.setE02REQCS1(msgInst.getE01REQCS1());
msgLiquida.setE02REQCS2(msgInst.getE01REQCS2());
msgLiquida.setE02REQCS3(msgInst.getE01REQCS3());
msgLiquida.setE02REQCS4(msgInst.getE01REQCS4());
msgLiquida.setE02REQCS5(msgInst.getE01REQCS5());
msgLiquida.setE02REQCN1(msgInst.getE01REQCN1());
msgLiquida.setE02REQCN2(msgInst.getE01REQCN2());
msgLiquida.setE02REQCN3(msgInst.getE01REQCN3());
msgLiquida.setE02REQCN4(msgInst.getE01REQCN4());
msgLiquida.setE02REQCN5(msgInst.getE01REQCN5());
msgLiquida.setE02REQCL1(msgInst.getE01REQCL1());
msgLiquida.setE02REQCL2(msgInst.getE01REQCL2());
msgLiquida.setE02REQCL3(msgInst.getE01REQCL3());
msgLiquida.setE02REQCL4(msgInst.getE01REQCL4());
msgLiquida.setE02REQCL5(msgInst.getE01REQCL5());
msgLiquida.setE02REQCF1(msgInst.getE01REQCF1());
msgLiquida.setE02REQCF2(msgInst.getE01REQCF2());
msgLiquida.setE02REQCF3(msgInst.getE01REQCF3());
msgLiquida.setE02REQCF4(msgInst.getE01REQCF4());
msgLiquida.setE02REQCF5(msgInst.getE01REQCF5());
msgLiquida.setE02REQCB1(msgInst.getE01REQCB1());
msgLiquida.setE02REQCB2(msgInst.getE01REQCB2());
msgLiquida.setE02REQCB3(msgInst.getE01REQCB3());
msgLiquida.setE02REQCB4(msgInst.getE01REQCB4());
msgLiquida.setE02REQCB5(msgInst.getE01REQCB5());
msgLiquida.setE02REQCT1(msgInst.getE01REQCT1());
msgLiquida.setE02REQCT2(msgInst.getE01REQCT2());
msgLiquida.setE02REQCT3(msgInst.getE01REQCT3());
msgLiquida.setE02REQCT4(msgInst.getE01REQCT4());
msgLiquida.setE02REQCT5(msgInst.getE01REQCT5());
msgLiquida.setE02REQCI1(msgInst.getE01REQCI1());
msgLiquida.setE02REQCI2(msgInst.getE01REQCI2());
msgLiquida.setE02REQCI3(msgInst.getE01REQCI3());
msgLiquida.setE02REQCI4(msgInst.getE01REQCI4());
msgLiquida.setE02REQCI5(msgInst.getE01REQCI5());
msgLiquida.setE02REQCQ1(msgInst.getE01REQCQ1());
msgLiquida.setE02REQCQ2(msgInst.getE01REQCQ2());
msgLiquida.setE02REQCQ3(msgInst.getE01REQCQ3());
msgLiquida.setE02REQCQ4(msgInst.getE01REQCQ4());
msgLiquida.setE02REQCQ5(msgInst.getE01REQCQ5());
msgLiquida.setE02REQBE1(msgInst.getE01REQBE1());
msgLiquida.setE02REQBE2(msgInst.getE01REQBE2());
msgLiquida.setE02REQBE3(msgInst.getE01REQBE3());
msgLiquida.setE02REQBE4(msgInst.getE01REQBE4());
msgLiquida.setE02REQBE5(msgInst.getE01REQBE5());
msgLiquida.setE02REQBEN(msgInst.getE01REQBEN());
msgLiquida.setE02REQCHK(msgInst.getE01REQCHK());
msgLiquida.setE02REQBEI(msgInst.getE01REQBEI());
msgLiquida.setE02REQBET(msgInst.getE01REQBET());
msgLiquida.setE02REQBEY(msgInst.getE01REQBEY());
msgLiquida.setE02REQBEA(msgInst.getE01REQBEA());
msgLiquida.setE02REQINT(msgInst.getE01REQINT());
msgLiquida.setE02REQITN(msgInst.getE01REQITN());
msgLiquida.setE02REQIN1(msgInst.getE01REQIN1());
msgLiquida.setE02REQIN2(msgInst.getE01REQIN2());
msgLiquida.setE02REQIN3(msgInst.getE01REQIN3());
msgLiquida.setE02REQIN4(msgInst.getE01REQIN4());
msgLiquida.setE02REQIN5(msgInst.getE01REQIN5());
msgLiquida.setE02REQPD1(msgInst.getE01REQPD1());
msgLiquida.setE02REQPD2(msgInst.getE01REQPD2());
msgLiquida.setE02REQPD3(msgInst.getE01REQPD3());
msgLiquida.setE02REQPD4(msgInst.getE01REQPD4());
msgLiquida.setE02REQPD5(msgInst.getE01REQPD5());
msgLiquida.setE02REQBB1(msgInst.getE01REQBB1());
msgLiquida.setE02REQBB2(msgInst.getE01REQBB2());
msgLiquida.setE02REQBB3(msgInst.getE01REQBB3());
msgLiquida.setE02REQBB4(msgInst.getE01REQBB4());
msgLiquida.setE02REQBB5(msgInst.getE01REQBB5());
msgLiquida.setE02REQBI1(msgInst.getE01REQBI1());
msgLiquida.setE02REQBI2(msgInst.getE01REQBI2());
msgLiquida.setE02REQBI3(msgInst.getE01REQBI3());
msgLiquida.setE02REQBI4(msgInst.getE01REQBI4());
msgLiquida.setE02REQBI5(msgInst.getE01REQBI5());
msgLiquida.setE02REQEDE(msgInst.getE01REQEDE());
msgLiquida.setE02REQDI4(msgInst.getE01REQDI4());
//}
}

if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
%>
<SCRIPT Language="Javascript">
        showErrors();
</SCRIPT>
<%}%>

</head>
<body>

<%
String lectura =" ";

if (userPO.getHeader10().equals("A")) {
	lectura="readonly style=\"background-color: #F0F0F0;\""; 
}else{
    lectura=" ";
}
%>


<%if (userPO.getHeader10().equals("A")) {%>
<h3 align="center">Compraventa Moneda Extranjera<BR>Aprobacion de Liquidacion de Operaciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="foreign_currency_exchange_maint, EPR0350"></H3>
<%} else {%>
<h3 align="center">Compraventa Moneda Extranjera<BR>Liquidacion de Operaciones<%if (userPO.getHeader10().equals("D")) {%> Diarias <%} else {%> SPOT <%}%><img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="foreign_currency_exchange_maint, EPR0350"></H3>
<%}%>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEPR0350">
 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= row %>">
  
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	<tr id=trdark> 
	      <td nowrap width="40%"> 
	        <div align="right">Referencia : </div>
	      </td>
	      <td nowrap width="60%"> 
	        <input type="text" name="E02REQREF" readonly size="15" maxlength="15" value="<%= msgLiquida.getE02REQREF() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right">Operacion : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E02REQOPN" readonly size="18" maxlength="15" value="<%= msgLiquida.getE02REQOPN() %>">
		  </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap width="40%"> 
	        <div align="right">Banco : </div>
	      </td>
	      <td nowrap width="60%"> 
	        <input type="text" name="E02REQBNK" readonly size="3" maxlength="2" value="<%= msgLiquida.getE02REQBNK() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right">Oficina : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E02REQBRN" readonly size="5" maxlength="3" value="<%= msgLiquida.getE02REQBRN() %>">
	      	<input type="text" name="E02REQBRM" readonly size="35" maxlength="35" readonly value="<%= msgLiquida.getE02REQBRM() %>">
		  </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap> 
	        <div align="right">Instrumento : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E02REQINS" readonly size="5" maxlength="4" value="<%= msgLiquida.getE02REQINS() %>">
	      	<input type="text" name="E02REQINN" readonly size="35" maxlength="35" readonly value="<%= msgLiquida.getE02REQINN() %>">
		  </td>
     	</tr>
     	<tr id=trclear> 
		  <td> 
		     <div align="right">Moneda : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E02REQCCY" readonly size="5" maxlength="3" value="<%= msgLiquida.getE02REQCCY() %>">
	      	<input type="text" name="E02REQCCN" readonly size="35" maxlength="35" readonly value="<%= msgLiquida.getE02REQCCN() %>">
      	  </td>     
      	</tr>
      	<tr id=trdark>
          <td nowrap>
              <div align="right">Cliente : </div>
          </td>
          <td nowrap>
              <input type="text" name="E02REQCUS" readonly size="10" maxlength="9" value="<%= msgLiquida.getE02REQCUS() %>">
              <input type="text" name="E02REQCUN" readonly size="35" maxlength="35" readonly value="<%= msgLiquida.getE02REQCUN() %>"> 
          </td>
        </tr>
      	<tr id=trclear>
          <td nowrap>
              <div align="right">Estado : </div>
          </td>
          <td nowrap>
              <input type="text" name="E02REQSTN" readonly size="10" maxlength="10" value="<%= msgLiquida.getE02REQSTN() %>">
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
			<td nowrap width="40%">
				<div align="right">Valor en Moneda Extranjera:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E02REQFEA" size="20" maxlength="15" value="<%= msgLiquida.getE02REQFEA() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor Tasa de Cambio:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E02REQEXR" size="20" maxlength="15" value="<%= msgLiquida.getE02REQEXR() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor en Moneda Local:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E02REQLCA" size="20" maxlength="15" value="<%= msgLiquida.getE02REQLCA() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor de Comision:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E02REQCOA" size="20" maxlength="15" value="<%= msgLiquida.getE02REQCOA() %>" readonly>
				<%if (!msgLiquida.getE02REQCOA().equals("0.00")) {%>
				<a href="javascript:GetComission(document.forms[0].E02REQREF.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
				<%}%> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor Total:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E02REQTOA" size="20" maxlength="15" value="<%= msgLiquida.getE02REQTOA() %>" readonly> 
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
			<td nowrap width="40%">
				<div align="right">Motivo de Operacion:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E02REQORN" size="40" maxlength="40" value="<%= msgLiquida.getE02REQORN() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Corresponsal:</div>
			</td>
			<%if (!userPO.getHeader10().equals("A")) {%>
			<td nowrap width="60%">
				<input type="text" name="E02REQCOR" size="10" maxlength="9" value="<%= msgLiquida.getE02REQCOR() %>"> 
	      		<input type="text" name="E02REQCON" size="35" maxlength="35" value="<%= msgLiquida.getE02REQCON() %>" readonly>
	      		<a href="javascript:GetCorrespondentDescId('E02REQCOR','E02REQCON','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
			</td>
			<%} else {%>
			<td nowrap width="60%">
				<input type="text" name="E02REQCOR" size="10" maxlength="9" value="<%= msgLiquida.getE02REQCOR() %>" readonly> 
	      		<input type="text" name="E02REQCON" size="35" maxlength="35" value="<%= msgLiquida.getE02REQCON() %>" readonly>
			</td>
			<%}%>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">AAD:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E02REQAAD" size="12" maxlength="10" value="<%= msgLiquida.getE02REQAAD() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">ALD:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E02REQALD" size="12" maxlength="10" value="<%= msgLiquida.getE02REQALD() %>" readonly> 
			</td>
		</tr>
		<%if (userPO.getHeader10().equals("S")) {%>
		<tr>
			<td nowrap width="40%">
				<div align="right">Fecha de Liquidacion SPOT:</div>
			</td>
			<td nowrap width="60%">
                <input type="text" name="E02REQVD1" size="2" maxlength="2" value="<%= msgLiquida.getE02REQVD1() %>" readonly>
                <input type="text" name="E02REQVD2" size="2" maxlength="2" value="<%= msgLiquida.getE02REQVD2() %>" readonly>
                <input type="text" name="E02REQVD3" size="2" maxlength="2" value="<%= msgLiquida.getE02REQVD3() %>" readonly>
			</td>
		</tr>
		<%}%>
     </table>
    </td>
   </tr>
  </table>
  <br>
  <TABLE  id="mainTable" class="tableinfo">
  <TR><TD>
   <table id="headTable" width="100%">
    <tr id="trdark" align="center"> 
      <td nowrap align="center" >Concepto</td>
      <td nowrap align="center" >Banco</td>
      <td nowrap align="center" >Sucursal</td>
      <td nowrap align="center" >Moneda</td>
      <td nowrap align="center" >Cuenta Cliente</td>
      <td nowrap align="center" >Valor</td>
    </tr>
    <%if (!msgLiquida.getE02OFFVA1().equals("0.00")) {%>
    <tr id="trclear" align="center"> 
      <td nowrap > 
          <div align="center"> 
          <input type="text" name="E02OFFOP1" size="3" maxlength="2" value="<%= msgLiquida.getE02OFFOP1() %>" readonly>
          <input type="text" name="E02OFFCO1" size="25" maxlength="25" readonly value="<%= msgLiquida.getE02OFFCO1() %>">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFBK1" size="2" maxlength="2" value="<%= msgLiquida.getE02OFFBK1() %>" readonly>
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFBR1" size="3" maxlength="3" value="<%= msgLiquida.getE02OFFBR1() %>" readonly>
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFCY1" size="3" maxlength="3" value="<%= msgLiquida.getE02OFFCY1() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFAC1" size="12" maxlength="12"  value="<%= msgLiquida.getE02OFFAC1() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFVA1" size="18" maxlength="15"  value="<%= msgLiquida.getE02OFFVA1() %>" readonly>
          </div>
       </td>
      </tr>
      <%}%>
      <%if (!msgLiquida.getE02OFFVA2().equals("0.00")) {%>
      <tr id="trdark" align="center"> 
      <td nowrap > 
          <div align="center"> 
          <input type="text" name="E02OFFOP2" size="3" maxlength="2" value="<%= msgLiquida.getE02OFFOP2() %>" readonly>
          <input type="text" name="E02OFFCO2" size="25" maxlength="25" readonly value="<%= msgLiquida.getE02OFFCO2() %>">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFBK2" size="2" maxlength="2" value="<%= msgLiquida.getE02OFFBK2() %>" readonly>
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFBR2" size="3" maxlength="3" value="<%= msgLiquida.getE02OFFBR2() %>" readonly>
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFCY2" size="3" maxlength="3" value="<%= msgLiquida.getE02OFFCY2() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFAC2" size="12" maxlength="12"  value="<%= msgLiquida.getE02OFFAC2() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFVA2" size="18" maxlength="15"  value="<%= msgLiquida.getE02OFFVA2() %>" readonly>
          </div>
       </td>
      </tr>
      <%}%>
      <%if (!msgLiquida.getE02OFFVA3().equals("0.00")) {%>
      <tr id="trclear" align="center"> 
      <td nowrap > 
          <div align="center"> 
          <input type="text" name="E02OFFOP3" size="3" maxlength="2" value="<%= msgLiquida.getE02OFFOP3() %>" readonly>
          <input type="text" name="E02OFFCO3" size="25" maxlength="25" readonly value="<%= msgLiquida.getE02OFFCO3() %>">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFBK3" size="2" maxlength="2" value="<%= msgLiquida.getE02OFFBK3() %>" readonly>
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFBR3" size="3" maxlength="3" value="<%= msgLiquida.getE02OFFBR3() %>" readonly>
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFCY3" size="3" maxlength="3" value="<%= msgLiquida.getE02OFFCY3() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFAC3" size="12" maxlength="12"  value="<%= msgLiquida.getE02OFFAC3() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFVA3" size="18" maxlength="15"  value="<%= msgLiquida.getE02OFFVA3() %>" readonly>
          </div>
       </td>
      </tr>
      <%}%>
      <%if (!msgLiquida.getE02OFFVA4().equals("0.00")) {%>
      <tr id="trdark" align="center"> 
      <td nowrap > 
          <div align="center"> 
          <input type="text" name="E02OFFOP4" size="3" maxlength="2" value="<%= msgLiquida.getE02OFFOP4() %>" readonly>
          <input type="text" name="E02OFFCO4" size="25" maxlength="25" readonly value="<%= msgLiquida.getE02OFFCO4() %>">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFBK4" size="2" maxlength="2" value="<%= msgLiquida.getE02OFFBK4() %>" readonly>
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFBR4" size="3" maxlength="3" value="<%= msgLiquida.getE02OFFBR4() %>" readonly>
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFCY4" size="3" maxlength="3" value="<%= msgLiquida.getE02OFFCY4() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFAC4" size="12" maxlength="12"  value="<%= msgLiquida.getE02OFFAC4() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFVA4" size="18" maxlength="15"  value="<%= msgLiquida.getE02OFFVA4() %>" readonly>
          </div>
       </td>
      </tr>
      <%}%>
      <%if (!msgLiquida.getE02OFFVA5().equals("0.00")) {%>
      <tr id="trclear" align="center"> 
      <td nowrap > 
          <div align="center"> 
          <input type="text" name="E02OFFOP5" size="3" maxlength="2" value="<%= msgLiquida.getE02OFFOP5() %>" readonly>
          <input type="text" name="E02OFFCO5" size="25" maxlength="25" readonly value="<%= msgLiquida.getE02OFFCO5() %>">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFBK5" size="2" maxlength="2" value="<%= msgLiquida.getE02OFFBK5() %>" readonly>
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFBR5" size="3" maxlength="3" value="<%= msgLiquida.getE02OFFBR5() %>" readonly>
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFCY5" size="3" maxlength="3" value="<%= msgLiquida.getE02OFFCY5() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFAC5" size="12" maxlength="12"  value="<%= msgLiquida.getE02OFFAC5() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFVA5" size="18" maxlength="15"  value="<%= msgLiquida.getE02OFFVA5() %>" readonly>
          </div>
       </td>
      </tr>
      <%}%>
     </table>       
   </TD>  
</TR>	
</TABLE>
   <br>
  <%if (msgLiquida.getH02FLGWK3().equals("5")) {%>
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">
    	<%if (!msgLiquida.getE02REQOPC().equals("P")) {%>    
		<tr>
			<td nowrap width="20%">
				<div align="center">Denominacion de Cheque</div>
			</td>
			<td nowrap width="15%">
				<div align="center">Cantidad Disponible</div>
			</td>
			<td nowrap width="10%">
				<div align="center">Desde No.</div>
			</td>
			<td nowrap width="10%">
				<div align="center">Hasta No.</div>
			</td>
			<td nowrap width="20%">
				<div align="center">Nombre del Beneficiario</div>
			</td>
			<td nowrap width="15%">
				<div align="center">Cantidad a Vender</div>
			</td>
			<td nowrap width="10%">
				<div align="center">Desde No.</div>
			</td>
		</tr>
		<tr>
			<td nowrap width="20%" align="center">
                <input type="hidden" name="E02REQCS1" size="9" readonly maxlength="8" value="<%= msgLiquida.getE02REQCS1() %>">
                <input type="text" name="E02REQCN1" size="35" readonly maxlength="35" value="<%= msgLiquida.getE02REQCN1() %>">
                <%if (!userPO.getHeader10().equals("A")) {%>
				<a href="javascript:GetCheckForeignExc('E02REQCS1','E02REQCN1','E02REQCL1','E02REQCF1','E02REQCT1','E02REQCB1','E02REQCI1',document.forms[0].E02REQBNK.value,document.forms[0].E02REQBRN.value,document.forms[0].E02REQINS.value,'<%= msgLiquida.getH02USERID() %>')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></a>
				<%}%>
                <input type="hidden" name="E02REQCL1" value="<%= msgLiquida.getE02REQCL1() %>">
			</td>
			<td nowrap width="15%" align="center">
                <input type="text" name="E02REQCB1" size="9" readonly maxlength="7" value="<%= msgLiquida.getE02REQCB1() %>">
			</td>
			<td nowrap width="10%" align="center">
                <input type="text" name="E02REQCF1" size="11" readonly maxlength="9" value="<%= msgLiquida.getE02REQCF1() %>">
			</td>
			<td nowrap width="10%" align="center">
                <input type="text" name="E02REQCT1" size="11" readonly maxlength="9" value="<%= msgLiquida.getE02REQCT1() %>">
			</td>
			<%if (!userPO.getHeader10().equals("A")) {%>
			<td nowrap width="20%">
                <input type="text" name="E02REQBE1" size="35" maxlength="35" value="<%= msgLiquida.getE02REQBE1() %>">
			</td>
			<td nowrap width="15%" align="center">
                <input type="text" name="E02REQCQ1" size="9" maxlength="7" value="<%= msgLiquida.getE02REQCQ1() %>">
			</td>
			<td nowrap width="10%" align="center">
                <input type="text" name="E02REQCI1" size="11" maxlength="9" value="<%= msgLiquida.getE02REQCI1() %>">
			</td>
			<%} else {%>
			<td nowrap width="20%">
                <input type="text" name="E02REQBE1" size="35" readonly maxlength="35" value="<%= msgLiquida.getE02REQBE1() %>">
			</td>
			<td nowrap width="15%" align="center">
                <input type="text" name="E02REQCQ1" size="9" readonly maxlength="7" value="<%= msgLiquida.getE02REQCQ1() %>">
			</td>
			<td nowrap width="10%" align="center">
                <input type="text" name="E02REQCI1" size="11" readonly maxlength="9" value="<%= msgLiquida.getE02REQCI1() %>">
			</td>
			<%}%>
		</tr>
		<tr>
			<td nowrap width="20%" align="center">
                <input type="hidden" name="E02REQCS2" size="9" readonly maxlength="8" value="<%= msgLiquida.getE02REQCS2() %>">
                <input type="text" name="E02REQCN2" size="35" readonly maxlength="35" value="<%= msgLiquida.getE02REQCN2() %>">
                <%if (!userPO.getHeader10().equals("A")) {%>
				<a href="javascript:GetCheckForeignExc('E02REQCS2','E02REQCN2','E02REQCL2','E02REQCF2','E02REQCT2','E02REQCB2','E02REQCI2',document.forms[0].E02REQBNK.value,document.forms[0].E02REQBRN.value,document.forms[0].E02REQINS.value,'<%= msgLiquida.getH02USERID() %>')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></a>
                <%}%>
                <input type="hidden" name="E02REQCL2" value="<%= msgLiquida.getE02REQCL2() %>">
			</td>
			<td nowrap width="15%" align="center">
                <input type="text" name="E02REQCB2" size="9" readonly maxlength="7" value="<%= msgLiquida.getE02REQCB2() %>">
			</td>
			<td nowrap width="10%" align="center">
                <input type="text" name="E02REQCF2" size="11" readonly maxlength="9" value="<%= msgLiquida.getE02REQCF2() %>">
			</td>
			<td nowrap width="10%" align="center">
                <input type="text" name="E02REQCT2" size="11" readonly maxlength="9" value="<%= msgLiquida.getE02REQCT2() %>">
			</td>
			<%if (!userPO.getHeader10().equals("A")) {%>
			<td nowrap width="20%">
                <input type="text" name="E02REQBE2" size="35" maxlength="35" value="<%= msgLiquida.getE02REQBE2() %>">
			</td>
			<td nowrap width="15%" align="center">
                <input type="text" name="E02REQCQ2" size="9" maxlength="7" value="<%= msgLiquida.getE02REQCQ2() %>">
			</td>
			<td nowrap width="10%" align="center">
                <input type="text" name="E02REQCI2" size="11" maxlength="9" value="<%= msgLiquida.getE02REQCI2() %>">
			</td>
			<%} else {%>
			<td nowrap width="20%">
                <input type="text" name="E02REQBE2" size="35" readonly maxlength="35" value="<%= msgLiquida.getE02REQBE2() %>">
			</td>
			<td nowrap width="15%" align="center">
                <input type="text" name="E02REQCQ2" size="9" readonly maxlength="7" value="<%= msgLiquida.getE02REQCQ2() %>">
			</td>
			<td nowrap width="10%" align="center">
                <input type="text" name="E02REQCI2" size="11" readonly maxlength="9" value="<%= msgLiquida.getE02REQCI2() %>">
			</td>
			<%}%>
		</tr>
		<tr>
			<td nowrap width="20%" align="center">
                <input type="hidden" name="E02REQCS3" size="9" readonly maxlength="8" value="<%= msgLiquida.getE02REQCS3() %>">
                <input type="text" name="E02REQCN3" size="35" readonly maxlength="35" value="<%= msgLiquida.getE02REQCN3() %>">
                <%if (!userPO.getHeader10().equals("A")) {%>
				<a href="javascript:GetCheckForeignExc('E02REQCS3','E02REQCN3','E02REQCL3','E02REQCF3','E02REQCT3','E02REQCB3','E02REQCI3',document.forms[0].E02REQBNK.value,document.forms[0].E02REQBRN.value,document.forms[0].E02REQINS.value,'<%= msgLiquida.getH02USERID() %>')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></a>
                <%}%>
                <input type="hidden" name="E02REQCL3" value="<%= msgLiquida.getE02REQCL3() %>">
			</td>
			<td nowrap width="15%" align="center">
                <input type="text" name="E02REQCB3" size="9" readonly maxlength="7" value="<%= msgLiquida.getE02REQCB3() %>">
			</td>
			<td nowrap width="10%" align="center">
                <input type="text" name="E02REQCF3" size="11" readonly maxlength="9" value="<%= msgLiquida.getE02REQCF3() %>">
			</td>
			<td nowrap width="10%" align="center">
                <input type="text" name="E02REQCT3" size="11" readonly maxlength="9" value="<%= msgLiquida.getE02REQCT3() %>">
			</td>
			<%if (!userPO.getHeader10().equals("A")) {%>
			<td nowrap width="20%">
                <input type="text" name="E02REQBE3" size="35" maxlength="35" value="<%= msgLiquida.getE02REQBE3() %>">
			</td>
			<td nowrap width="15%" align="center">
                <input type="text" name="E02REQCQ3" size="9" maxlength="7" value="<%= msgLiquida.getE02REQCQ3() %>">
			</td>
			<td nowrap width="10%" align="center">
                <input type="text" name="E02REQCI3" size="11" maxlength="9" value="<%= msgLiquida.getE02REQCI3() %>">
			</td>
			<%} else {%>
			<td nowrap width="20%">
                <input type="text" name="E02REQBE3" size="35" readonly maxlength="35" value="<%= msgLiquida.getE02REQBE3() %>">
			</td>
			<td nowrap width="15%" align="center">
                <input type="text" name="E02REQCQ3" size="9" readonly maxlength="7" value="<%= msgLiquida.getE02REQCQ3() %>">
			</td>
			<td nowrap width="10%" align="center">
                <input type="text" name="E02REQCI3" size="11" readonly maxlength="9" value="<%= msgLiquida.getE02REQCI3() %>">
			</td>
			<%}%>
		</tr>
		<tr>
			<td nowrap width="20%" align="center">
                <input type="hidden" name="E02REQCS4" size="9" readonly maxlength="8" value="<%= msgLiquida.getE02REQCS4() %>">
                <input type="text" name="E02REQCN4" size="35" readonly maxlength="35" value="<%= msgLiquida.getE02REQCN4() %>">
				<%if (!userPO.getHeader10().equals("A")) {%>
				<a href="javascript:GetCheckForeignExc('E02REQCS4','E02REQCN4','E02REQCL4','E02REQCF4','E02REQCT4','E02REQCB4','E02REQCI4',document.forms[0].E02REQBNK.value,document.forms[0].E02REQBRN.value,document.forms[0].E02REQINS.value,'<%= msgLiquida.getH02USERID() %>')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></a>
                <%}%>
                <input type="hidden" name="E02REQCL4" value="<%= msgLiquida.getE02REQCL4() %>">
			</td>
			<td nowrap width="15%" align="center">
                <input type="text" name="E02REQCB4" size="9" readonly maxlength="7" value="<%= msgLiquida.getE02REQCB4() %>">
			</td>
			<td nowrap width="10%" align="center">
                <input type="text" name="E02REQCF4" size="11" readonly maxlength="9" value="<%= msgLiquida.getE02REQCF4() %>">
			</td>
			<td nowrap width="10%" align="center">
                <input type="text" name="E02REQCT4" size="11" readonly maxlength="9" value="<%= msgLiquida.getE02REQCT4() %>">
			</td>
			<%if (!userPO.getHeader10().equals("A")) {%>
			<td nowrap width="20%">
                <input type="text" name="E02REQBE4" size="35" maxlength="35" value="<%= msgLiquida.getE02REQBE4() %>">
			</td>
			<td nowrap width="15%" align="center">
                <input type="text" name="E02REQCQ4" size="9" maxlength="7" value="<%= msgLiquida.getE02REQCQ4() %>">
			</td>
			<td nowrap width="10%" align="center">
                <input type="text" name="E02REQCI4" size="11" maxlength="9" value="<%= msgLiquida.getE02REQCI4() %>">
			</td>
			<%} else {%>
			<td nowrap width="20%">
                <input type="text" name="E02REQBE4" size="35" readonly maxlength="35" value="<%= msgLiquida.getE02REQBE4() %>">
			</td>
			<td nowrap width="15%" align="center">
                <input type="text" name="E02REQCQ4" size="9" readonly maxlength="7" value="<%= msgLiquida.getE02REQCQ4() %>">
			</td>
			<td nowrap width="10%" align="center">
                <input type="text" name="E02REQCI4" size="11" readonly maxlength="9" value="<%= msgLiquida.getE02REQCI4() %>">
			</td>
			<%}%>
		</tr>
		<tr>
			<td nowrap width="20%" align="center">
                <input type="hidden" name="E02REQCS5" size="9" readonly maxlength="8" value="<%= msgLiquida.getE02REQCS5() %>">
                <input type="text" name="E02REQCN5" size="35" readonly maxlength="35" value="<%= msgLiquida.getE02REQCN5() %>">
				<%if (!userPO.getHeader10().equals("A")) {%>
				<a href="javascript:GetCheckForeignExc('E02REQCS5','E02REQCN5','E02REQCL5','E02REQCF5','E02REQCT5','E02REQCB5','E02REQCI5',document.forms[0].E02REQBNK.value,document.forms[0].E02REQBRN.value,document.forms[0].E02REQINS.value,'<%= msgLiquida.getH02USERID() %>')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></a>
                <%}%>
                <input type="hidden" name="E02REQCL5" value="<%= msgLiquida.getE02REQCL5() %>">
			</td>
			<td nowrap width="15%" align="center">
                <input type="text" name="E02REQCB5" size="9" readonly maxlength="7" value="<%= msgLiquida.getE02REQCB5() %>">
			</td>
			<td nowrap width="10%" align="center">
                <input type="text" name="E02REQCF5" size="11" readonly maxlength="9" value="<%= msgLiquida.getE02REQCF5() %>">
			</td>
			<td nowrap width="10%" align="center">
                <input type="text" name="E02REQCT5" size="11" readonly maxlength="9" value="<%= msgLiquida.getE02REQCT5() %>">
			</td>
			<%if (!userPO.getHeader10().equals("A")) {%>
			<td nowrap width="20%">
                <input type="text" name="E02REQBE5" size="35" maxlength="35" value="<%= msgLiquida.getE02REQBE5() %>">
			</td>
			<td nowrap width="15%" align="center">
                <input type="text" name="E02REQCQ5" size="9" maxlength="7" value="<%= msgLiquida.getE02REQCQ5() %>">
			</td>
			<td nowrap width="10%" align="center">
                <input type="text" name="E02REQCI5" size="11" maxlength="9" value="<%= msgLiquida.getE02REQCI5() %>">
			</td>
			<%} else {%>
			<td nowrap width="20%">
                <input type="text" name="E02REQBE5" size="35" readonly maxlength="35" value="<%= msgLiquida.getE02REQBE5() %>">
			</td>
			<td nowrap width="15%" align="center">
                <input type="text" name="E02REQCQ5" size="9" readonly maxlength="7" value="<%= msgLiquida.getE02REQCQ5() %>">
			</td>
			<td nowrap width="10%" align="center">
                <input type="text" name="E02REQCI5" size="11" readonly maxlength="9" value="<%= msgLiquida.getE02REQCI5() %>">
			</td>
			<%}%>
		</tr>
		<%}%>
		<%if (msgLiquida.getE02REQOPC().equals("P")) {%> 
		<tr>
			<td nowrap width="20%">
				<div align="right">Nombre del Beneficiario</div>
			</td>
			<td nowrap width="30%">
				<%if (!userPO.getHeader10().equals("A")) {%>
                <input type="text" name="E02REQBEN" size="35" maxlength="35" value="<%= msgLiquida.getE02REQBEN() %>">
                <%} else {%>
                <input type="text" name="E02REQBEN" size="35" readonly maxlength="35" value="<%= msgLiquida.getE02REQBEN() %>">
                <%}%>
			</td>
			<td nowrap width="20%">
				<div align="right">Numero de Cheque</div>
			</td>
			<td nowrap width="30%">
				<%if (!userPO.getHeader10().equals("A")) {%>
                <input type="text" name="E02REQCHK" size="11" maxlength="9" value="<%= msgLiquida.getE02REQCHK() %>">
                <%} else {%>
                <input type="text" name="E02REQCHK" size="11" readonly maxlength="9" value="<%= msgLiquida.getE02REQCHK() %>">
                <%}%>
            </td>
		</tr>
		<%}%>
     </table>
    </td>
   </tr>
  </table>
  <br>
  <%}%>
  <%if (msgLiquida.getH02FLGWK3().equals("2")) {%>
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">
    	<%if (!msgLiquida.getE02REQOPC().equals("P")) {%>    
		<tr>
			<td nowrap width="20%">
				<div align="center">Denominacion de Cheque</div>
			</td>
			<td nowrap width="15%">
				<div align="center">Cantidad Disponible</div>
			</td>
			<td nowrap width="10%">
				<div align="center">Desde No.</div>
			</td>
			<td nowrap width="10%">
				<div align="center">Hasta No.</div>
			</td>
			<td nowrap width="20%">
				<div align="center">Nombre del Beneficiario</div>
			</td>
			<td nowrap width="10%">
				<div align="center">Numero de Cheque</div>
			</td>
		</tr>
		<tr>
			<td nowrap width="20%" align="center">
                <input type="hidden" name="E02REQCS1" size="9" readonly maxlength="8" value="<%= msgLiquida.getE02REQCS1() %>">
                <input type="text" name="E02REQCN1" size="35" readonly maxlength="35" value="<%= msgLiquida.getE02REQCN1() %>">
                <%if (!userPO.getHeader10().equals("A")) {%>
				<a href="javascript:GetCheckForeignExc('E02REQCS1','E02REQCN1','E02REQCL1','E02REQCF1','E02REQCT1','E02REQCB1','E02REQCI1',document.forms[0].E02REQBNK.value,document.forms[0].E02REQBRN.value,document.forms[0].E02REQINS.value,'<%= msgLiquida.getH02USERID() %>')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></a>
                <%}%>
                <input type="hidden" name="E02REQCL1" value="<%= msgLiquida.getE02REQCL1() %>">
			</td>
			<td nowrap width="15%" align="center">
                <input type="text" name="E02REQCB1" size="9" readonly maxlength="7" value="<%= msgLiquida.getE02REQCB1() %>">
			</td>
			<td nowrap width="10%" align="center">
                <input type="text" name="E02REQCF1" size="11" readonly maxlength="9" value="<%= msgLiquida.getE02REQCF1() %>">
			</td>
			<td nowrap width="10%" align="center">
                <input type="text" name="E02REQCT1" size="11" readonly maxlength="9" value="<%= msgLiquida.getE02REQCT1() %>">
			</td>
			<%if (!userPO.getHeader10().equals("A")) {%>
			<td nowrap width="20%">
                <input type="text" name="E02REQBE1" size="35" maxlength="35" value="<%= msgLiquida.getE02REQBE1() %>">
			</td>
			<td nowrap width="10%" align="center">
                <input type="text" name="E02REQCI1" size="11" maxlength="9" value="<%= msgLiquida.getE02REQCI1() %>">
			</td>
			<%} else {%>
			<td nowrap width="20%">
                <input type="text" name="E02REQBE1" size="35" readonly maxlength="35" value="<%= msgLiquida.getE02REQBE1() %>">
			</td>
			<td nowrap width="10%" align="center">
                <input type="text" name="E02REQCI1" size="11" readonly maxlength="9" value="<%= msgLiquida.getE02REQCI1() %>">
			</td>
			<%}%>
		</tr>
		<%}%>
		<%if (msgLiquida.getE02REQOPC().equals("P")) {%> 
		<tr>
			<td nowrap width="20%">
				<div align="right">Nombre del Beneficiario</div>
			</td>
			<td nowrap width="30%">
				<%if (!userPO.getHeader10().equals("A")) {%>
                <input type="text" name="E02REQBEN" size="35" maxlength="35" value="<%= msgLiquida.getE02REQBEN() %>">
                <%} else {%>
                <input type="text" name="E02REQBEN" size="35" readonly maxlength="35" value="<%= msgLiquida.getE02REQBEN() %>">
                <%}%>
			</td>
			<td nowrap width="20%">
				<div align="right">Numero de Cheque</div>
			</td>
			<td nowrap width="30%">
				<%if (!userPO.getHeader10().equals("A")) {%>
                <input type="text" name="E02REQCHK" size="11" maxlength="9" value="<%= msgLiquida.getE02REQCHK() %>">
                <%} else {%>
                <input type="text" name="E02REQCHK" size="11" readonly maxlength="9" value="<%= msgLiquida.getE02REQCHK() %>">
                <%}%>
            </td>
		</tr>
		<%}%>
     </table>
    </td>
   </tr>
  </table>
  <br>
  <%}%>
  <%if (msgLiquida.getH02FLGWK3().equals("3")) {%>
   <br>
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
		<tr>
			<td nowrap width="20%">
				<div align="right">Identificacion del Beneficiario</div>
			</td>
			<td nowrap width="30%">
				<%if (!userPO.getHeader10().equals("A")) {%>
                <input type="text" name="E02REQBEI" size="15" maxlength="15" value="<%= msgLiquida.getE02REQBEI() %>">
                <%} else {%>
                <input type="text" name="E02REQBEI" size="15" readonly maxlength="15" value="<%= msgLiquida.getE02REQBEI() %>">
                <%}%>
			</td>
			<td nowrap width="20%">
				<div align="right">Cuenta del Beneficiario</div>
			</td>
			<td nowrap width="30%">
				<%if (!userPO.getHeader10().equals("A")) {%>
                <input type="text" name="E02REQBEA" size="40" maxlength="35" value="<%= msgLiquida.getE02REQBEA() %>">
                <%} else {%>
                <input type="text" name="E02REQBEA" size="40" readonly maxlength="35" value="<%= msgLiquida.getE02REQBEA() %>">
                <%}%>
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
			</td>
		</tr>
		<tr>
			<td nowrap width="20%">
				<div align="right">Nuestra Referencia</div>
			</td>
			<td nowrap width="30%">
				<%if (!userPO.getHeader10().equals("A")) {%>
                <input type="text" name="E02REQRF1" size="18" maxlength="16" value="<%= msgLiquida.getE02REQRF1() %>">
                <%} else {%>
                <input type="text" name="E02REQRF1" size="18" readonly maxlength="16" value="<%= msgLiquida.getE02REQRF1() %>">
                <%}%>
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
			</td>
			<td nowrap width="20%"> 
                <div align="right">V&iacute;a de Pago</div>
            </td>
            <td nowrap width="30%">
            	<%if (!userPO.getHeader10().equals("A")) {%> 
                <select name="E02REQEDE">
                  <option value="1" <% if (msgLiquida.getE02REQEDE().equals("1")) out.print("selected"); %>>Swift MT-103</option>
                  <option value="2" <% if (msgLiquida.getE02REQEDE().equals("2")) out.print("selected"); %>>Swift MT-202</option>
                </select>
                <%} else {%>
                <input type="text" readonly name="E02REQEDE" size="15" maxlength="15"
                  value="<% if (msgLiquida.getE02REQEDE().equals("1")) { out.print("Swift MT-103"); }
							else if (msgLiquida.getE02REQEDE().equals("2")) { out.print("Swift MT-202"); }
							else { out.print(""); } %>" >
				<%}%>
            </td>
		</tr>
				<tr>
			<td nowrap width="20%">
				<div align="right">Detalle de Cargos</div>
			</td>
			<td nowrap width="30%">
            	<%if (!userPO.getHeader10().equals("A")) {
            	if (!(msgLiquida.getE02REQDI4().equals("1") || msgLiquida.getE02REQDI4().equals("2") || msgLiquida.getE02REQDI4().equals("3")))
            	{
					msgLiquida.setE02REQDI4("3");            	
            	}
            	
            	%> 
                <select name="E02REQDI4">
                  <option value="1" <% if (msgLiquida.getE02REQDI4().equals("1")) out.print("selected"); %>>Beneficiario</option>
                  <option value="2" <% if (msgLiquida.getE02REQDI4().equals("2")) out.print("selected"); %>>Banco</option>
                  <option value="3" <% if (msgLiquida.getE02REQDI4().equals("3")) out.print("selected"); %>>Ambos</option>
                </select>
                <%} else {%>
                <input type="text" readonly name="E02REQDI4" size="15" maxlength="15"
                  value="<% if (msgLiquida.getE02REQDI4().equals("1")) { out.print("Beneficiario"); }
							else if (msgLiquida.getE02REQDI4().equals("2")) { out.print("Banco"); }
							else if (msgLiquida.getE02REQDI4().equals("3")) { out.print("Ambos"); }
							else { out.print(""); } %>" >
				<%}%>

			</td>
			<td nowrap width="20%"> 
                <div align="right"></div>
            </td>
            <td nowrap width="30%">
            </td>
		</tr>
		
     </table>
    </td>
   </tr>
  </table>
  <br>
  <table class="tbenter" width="100%" border="0" cellspacing=0 cellpadding=0>
    <tr> 
       <td nowrap> 
           <table id="TableTab" cellspacing=0 cellpadding=2 border="0">
          		<tr>
                 <td nowrap id="Tab0" class="tabnormal" onClick="showTab(0,'E02REQBI1')"> 
               		<div nowrap >Banco Intermediario</div>
                 </td> 
                  <td nowrap id="Tab1" class="tabnormal" onClick="showTab(1,'E02REQBB1')"> 
                    <div nowrap >Banco Pagador</div>
                  </td>
                  <td nowrap id="Tab2" class="tabnormal" onClick="showTab(2,'E02REQBE1')"> 
                   	<div >Nombre del Beneficiario</div>
                  </td> 
          		  <td nowrap id="Tab3" class="tabnormal" onClick="showTab(3,'E02REQPD1')">  
                    <div nowrap >Detalles de Pago </div>
                  </td>            
                  <td nowrap id="Tab4" class="tabnormal" onClick="showTab(4,'E02REQIN1')"> 
               		<div nowrap >Infor. Banco a Banco</div>
                  </td> 
                  <td class="tabempty" width="100%">&nbsp;                       
                  </td> 
                </tr>
        	</table>
       </td>
    </tr>
  </table>
  <table class="tabdata" width="100%">
    <tr>
     	<td nowrap>
            <div id="dataTab0" align=center> 
               <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr id="trdark" > 
                     <td nowrap align=center> 
		                 <div><a href="javascript:GetSwiftIdDesc('E02REQBI1','E02REQBI2','E02REQBI3','E02REQBI4')">
		                  	<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0"></a>
		                 </div>
                         <input <%=lectura%> type="text" name="E02REQBI1" size="45" maxlength="35" value="<%= msgLiquida.getE02REQBI1()%>">
                         <br>
                         <input <%=lectura%> type="text" name="E02REQBI2" size="45" maxlength="35" value="<%= msgLiquida.getE02REQBI2()%>">
                         <br>
                         <input <%=lectura%> type="text" name="E02REQBI3" size="45" maxlength="35" value="<%= msgLiquida.getE02REQBI3()%>">
                         <br>
                         <input <%=lectura%> type="text" name="E02REQBI4" size="45" maxlength="35" value="<%= msgLiquida.getE02REQBI4()%>">
                         <br>
                         <input <%=lectura%> type="text" name="E02REQBI5" size="45" maxlength="35" value="<%= msgLiquida.getE02REQBI5()%>">
                      </td>
    				</tr>
 				</table>
            </div>         
	        <div id="dataTab1" style="display: none" align=center> 
    	    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
            		<tr id="trdark" > 
                		<td nowrap align=center> 
		            		<div><a href="javascript:GetSwiftIdDesc('E02REQBB1','E02REQBB2','E02REQBB3','E02REQBB4')">
		            			<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0"></a>
		            		</div>
                    		<input <%=lectura%> type="text" name="E02REQBB1" size="45" maxlength="35" value="<%= msgLiquida.getE02REQBB1()%>">
                    		<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
                    		<br>
                    		<input <%=lectura%> type="text" name="E02REQBB2" size="45" maxlength="35" value="<%= msgLiquida.getE02REQBB2()%>">
                    		<br>
                    		<input <%=lectura%> type="text" name="E02REQBB3" size="45" maxlength="35" value="<%= msgLiquida.getE02REQBB3()%>">
                    		<br>
                    		<input <%=lectura%> type="text" name="E02REQBB4" size="45" maxlength="35" value="<%= msgLiquida.getE02REQBB4()%>">
                    		<br>
                    		<input <%=lectura%> type="text" name="E02REQBB5" size="45" maxlength="35" value="<%= msgLiquida.getE02REQBB5()%>">
                		</td>
    				</tr>
 				</table>
 		    </div>
	        <div id="dataTab2" style="display: none" align=center> 
        		<table width="100%" border="0" cellspacing="0" cellpadding="0">
           			<tr id="trdark" > 
                		<td nowrap align=center> 
		            		<div><a href="javascript:GetSwiftIdDesc('E02REQBE1','E02REQBE2','E02REQBE3','E02REQBE4')">
		            			<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0"></a>
		            		</div>                        
                    		<input <%=lectura%> type="text" name="E02REQBE1" size="45" maxlength="35" value="<%= msgLiquida.getE02REQBE1()%>">
                    		<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
                    		<br>
                    		<input <%=lectura%> type="text" name="E02REQBE2" size="45" maxlength="35" value="<%= msgLiquida.getE02REQBE2()%>">
                    		<br>
                    		<input <%=lectura%> type="text" name="E02REQBE3" size="45" maxlength="35" value="<%= msgLiquida.getE02REQBE3()%>">
                    		<br>
                    		<input <%=lectura%> type="text" name="E02REQBE4" size="45" maxlength="35" value="<%= msgLiquida.getE02REQBE4()%>">
                    		<br>
                    		<input <%=lectura%> type="text" name="E02REQBE5" size="45" maxlength="35" value="<%= msgLiquida.getE02REQBE5()%>">
                		</td>
    				</tr>
 				</table>
        	</div>
			<div id="dataTab3" style="display: none" align=center>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
           			<tr id="trdark" > 
               			<td nowrap align=center> 
		           			<div><a href="javascript:GetSwiftIdDesc('E02REQPD1','E02REQPD2','E02REQPD3','E02REQPD4')">
		                		<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0"></a>
		           			</div>
                   			<input <%=lectura%> type="text" name="E02REQPD1" size="45" maxlength="35" value="<%= msgLiquida.getE02REQPD1()%>">
                   			<br>
                  			<input <%=lectura%> type="text" name="E02REQPD2" size="45" maxlength="35" value="<%= msgLiquida.getE02REQPD2()%>">
                   			<br>
                   			<input <%=lectura%> type="text" name="E02REQPD3" size="45" maxlength="35" value="<%= msgLiquida.getE02REQPD3()%>">
                   			<br>
                   			<input <%=lectura%> type="text" name="E02REQPD4" size="45" maxlength="35" value="<%= msgLiquida.getE02REQPD4()%>">
                   			<br>
                   			<input <%=lectura%> type="text" name="E02REQPD5" size="45" maxlength="35" value="<%= msgLiquida.getE02REQPD5()%>">
                		</td>
    				</tr>
 				</table>
      		</div>
            <div id="dataTab4" style="display: none" align=center>
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                	<tr id="trdark" > 
                    	<td nowrap align=center>  
							<div><a href="javascript:GetSwiftIdDesc('E02REQIN1','E02REQIN2','E02REQIN3','E02REQIN4')">
		                   		<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0"></a>
		                   	</div>
                         	<input <%=lectura%> type="text" name="E02REQIN1" size="45" maxlength="35" value="<%= msgLiquida.getE02REQIN1()%>">
                         	<br>
                         	<input <%=lectura%> type="text" name="E02REQIN2" size="45" maxlength="35" value="<%= msgLiquida.getE02REQIN2()%>">
                         	<br>
                         	<input <%=lectura%> type="text" name="E02REQIN3" size="45" maxlength="35" value="<%= msgLiquida.getE02REQIN3()%>">
                         	<br>
                         	<input <%=lectura%> type="text" name="E02REQIN4" size="45" maxlength="35" value="<%= msgLiquida.getE02REQIN4()%>">
                         	<br>
                         	<input <%=lectura%> type="text" name="E02REQIN5" size="45" maxlength="35" value="<%= msgLiquida.getE02REQIN5()%>">
                      	 </td>
    				</tr>
 				</table>
            </div>
     	</td>           
    </tr>
  </table>                
  <BR>                   
   
  <%}%>
  <p align="center"> 
    <input id="EIBSBTN" type="submit" name="Submit" value="Enviar">
  </p>

</form>
</body>
</html>
