<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Informacion Basica</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "client" class= "datapro.eibs.beans.ESD008001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />


<%
 if ( !userPO.getPurpose().equals("NEW") ) {
%>

   <SCRIPT Language="Javascript">
       
     builtNewMenu(client_personal_opt);
       
   </SCRIPT>

<%
}
%>

</head>

<body bgcolor="#FFFFFF">

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 if ( !userPO.getPurpose().equals("NEW") ) {
    out.println("<SCRIPT> initMenu(); </SCRIPT>");
 }
%>

<h3 align="center">Central de Riesgo<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF"  ></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <input type="hidden" name="E01LGT" size="15" maxlength="10" value="<%= client.getE01LGT().trim()%>">
  <h4> Titular</h4>
    
  <div align="left"> 
    <table class="tableinfo">
      <tr id="trdark"> 
        <td nowrap width="25%"> 
          <div align="right">Identificaci&oacute;n :</div>
        </td>
        <td nowrap colspan="6"> 
          <input type="text" name="E01IDN2" size="20" maxlength="15" value="<%= client.getE01IDN().trim()%>">
          <input type="text" name="E01TID2" size="8" maxlength="4" value="<%= client.getE01TID().trim()%>">
        </td>
      </tr>
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right"> Nombre :</div>
        </td>
        <td nowrap colspan=5> 
          <input type="text" name="E01FNA2" size="35" maxlength="30" value="<%= client.getE01FNA().trim()%>">
        </td>
      </tr>
    </table>
    <br>
    <table class="tableinfo">
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right"> Mes :</div>
        </td>
        <td nowrap colspan=5> 
          <div align="center">1</div>
        </td>
        <td nowrap> 
          <div align="center">2</div>
        </td>
        <td nowrap> 
          <div align="center">3</div>
        </td>
        <td nowrap> 
          <div align="center">4</div>
        </td>
        <td nowrap> 
          <div align="center">5</div>
        </td>
        <td nowrap>
          <div align="center">6</div>
        </td>
      </tr>
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right">Cartera x Vencimiento :</div>
        </td>
        <td nowrap colspan=5> 
          <input type="text" name="E01TID22" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap> 
          <input type="text" name="E01TID2219" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap> 
          <input type="text" name="E01TID2220" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap> 
          <input type="text" name="E01TID2221" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap> 
          <input type="text" name="E01TID2222" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2223" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
      </tr>
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right">Vencimiento 1 - 3 Meses :</div>
        </td>
        <td nowrap colspan=5> 
          <input type="text" name="E01TID222" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2225" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2224" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2226" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2227" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2228" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
      </tr>
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right">Vencimiento 3 - 9 Meses :</div>
        </td>
        <td nowrap colspan=5> 
          <input type="text" name="E01TID223" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2229" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2245" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2261" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2277" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID22108" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
      </tr>
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right">Vencimiento 9 - 24 Meses :</div>
        </td>
        <td nowrap colspan=5> 
          <input type="text" name="E01TID224" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2230" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2246" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2262" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2278" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID22107" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
      </tr>
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right">Vencimiento m&aacute;s de 24 Meses :</div>
        </td>
        <td nowrap colspan=5> 
          <input type="text" name="E01TID225" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2231" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2247" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2263" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2279" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID22106" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
      </tr>
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right">Demanda Judicial :</div>
        </td>
        <td nowrap colspan=5> 
          <input type="text" name="E01TID226" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2232" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2248" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2264" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2280" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID22105" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
      </tr>
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right">Cartera Castigada :</div>
        </td>
        <td nowrap colspan=5> 
          <input type="text" name="E01TID227" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2233" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2249" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2265" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2281" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID22104" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
      </tr>
      <tr id="trclear"> 
        <td nowrap colspan="11"> 
          <div align="right"></div>
        </td>
      </tr>
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right">Monto Garant&iacute;a :</div>
        </td>
        <td nowrap colspan=5> 
          <input type="text" name="E01TID228" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2234" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2250" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2266" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2282" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID22103" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
      </tr>
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right">Monto Demanda :</div>
        </td>
        <td nowrap colspan=5> 
          <input type="text" name="E01TID229" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2235" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2251" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2267" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2283" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID22102" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
      </tr>
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right">Monto Castigada :</div>
        </td>
        <td nowrap colspan=5> 
          <input type="text" name="E01TID2210" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2236" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2252" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2268" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2284" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID22101" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
      </tr>
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right">Calificaci&oacute;n A :</div>
        </td>
        <td nowrap colspan=5> 
          <input type="text" name="E01TID2211" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2237" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2253" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2269" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2285" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID22100" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
      </tr>
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right">Calificaci&oacute;n B :</div>
        </td>
        <td nowrap colspan=5> 
          <input type="text" name="E01TID2212" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2238" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2254" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2270" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2286" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2299" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
      </tr>
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right">Calificaci&oacute;n C :</div>
        </td>
        <td nowrap colspan=5> 
          <input type="text" name="E01TID2213" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2239" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2255" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2271" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2287" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2298" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
      </tr>
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right">Calificaci&oacute;n D :</div>
        </td>
        <td nowrap colspan=5> 
          <input type="text" name="E01TID2214" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2240" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2256" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2272" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2288" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2297" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
      </tr>
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right">Calificaci&oacute;n E :</div>
        </td>
        <td nowrap colspan=5> 
          <input type="text" name="E01TID2215" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2241" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2257" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2273" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2289" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2296" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
      </tr>
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right">Saldo BCM :</div>
        </td>
        <td nowrap colspan=5> 
          <input type="text" name="E01TID2216" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2242" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2258" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2274" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2290" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2295" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
      </tr>
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right">Garante BCM :</div>
        </td>
        <td nowrap colspan=5> 
          <input type="text" name="E01TID2217" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2243" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2259" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2275" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2291" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
        <td nowrap>
          <input type="text" name="E01TID2294" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
      </tr>
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right"><b>Total de Cr&eacute;ditos :</b></div>
        </td>
        <td nowrap colspan=5><b> 
          <input type="text" name="E01TID2218" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
          </b></td>
        <td nowrap><b>
          <input type="text" name="E01TID2244" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
          </b></td>
        <td nowrap><b>
          <input type="text" name="E01TID2260" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
          </b></td>
        <td nowrap><b>
          <input type="text" name="E01TID2276" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
          </b></td>
        <td nowrap><b>
          <input type="text" name="E01TID2292" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
          </b></td>
        <td nowrap>
          <input type="text" name="E01TID2293" size="15" maxlength="15" value="<%= client.getE01TID().trim()%>">
        </td>
      </tr>
    </table>
  </div>
  </form>
</body>
</html>

