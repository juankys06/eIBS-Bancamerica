<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Orden de No pago</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="stpchk" class= "datapro.eibs.beans.EOF010001Message"  scope="session"/>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

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


<H3 align="center">Confirmacion</H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0100" >
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Beneficiario :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"><b> <%= stpchk.getE01STPBNF()%> </b> </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Aplicante :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <%= stpchk.getE01STPAPL()%> </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cheque :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> <%= stpchk.getE01STPCKN()%> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> <%= stpchk.getE01STPCCY()%> </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Monto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> <%= Util.fcolorCCY(stpchk.getE01STPAMT())%> </b> </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Banco / Agencia :</b></div>
            </td>
            <td nowrap width="20%"> <%= stpchk.getE01STPBNK()%> / <%= stpchk.getE01STPBNK()%> 
            </td>
            <td nowrap width="16%"><b>Cuenta Contable :</b></td>
            <td nowrap colspan="3"> <%= stpchk.getE01STPGLN()%> </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="44%" height="19"> 
              <div align="right">Secuencia :</div>
            </td>
            <td nowrap height="19" colspan="3"> <%= stpchk.getE01STPSEQ()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="44%" height="19"> 
              <div align="right">Fecha del Cheque :</div>
            </td>
            <td nowrap height="19" colspan="3"> 
              <div align="left"> <%= Util.formatDate(stpchk.getE01STPCK1(),stpchk.getE01STPCK2(),stpchk.getE01STPCK3())%> 
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="44%" height="19"> 
              <div align="right">Observaciones :</div>
            </td>
            <td nowrap height="19" colspan="3"> <%= stpchk.getE01STPRMK()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="44%" height="19"> 
              <div align="right">Causa :</div>
            </td>
            <td nowrap height="19" colspan="3"> <%= stpchk.getE01STPF02()%> </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="4" height="19">Esta operacion ha concluido</td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
</form>
</body>
</html>
