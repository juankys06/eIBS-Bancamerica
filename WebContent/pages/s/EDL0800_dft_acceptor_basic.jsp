<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Draft Acceptor Maintenance</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


<jsp:useBean id="dftBasic" class="datapro.eibs.beans.EDL080001Message"  scope="session" />

<jsp:useBean id="dftAcceptor" class="datapro.eibs.beans.EDL080003Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>
<body nowrap>
<% 
 String strBlank = "";
 String strGrp = "";
 try {
       strGrp = request.getParameter("ACTION");
       if (strGrp == null) strGrp = "";
 } catch (Exception e) {
       strGrp = "";
 }

 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
  
  String DEAIPD, DEAPPD;
  String E01FLGDED, E01FLGREF,E01FLGPAY,E01FLGCOL,E01DEACLF;
  boolean isDEAIPDNum = true;
  boolean isDEAPPDNum = true;
  
  
%> 
<h3 align="center">Mantenimiento de Aceptantes<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="dft_acceptor_basic.jsp,EDL0800"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="502">
  <INPUT TYPE=HIDDEN NAME="GRP" VALUE="1">
<%
if (strGrp.equals("G")) {
%>
  <INPUT TYPE=HIDDEN NAME="GENDOC" VALUE="1">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left">
                <INPUT type="text" name="E01DEACUN" size="9" maxlength="9" value="<%= userPO.getHeader2()%>" readonly>
                </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <INPUT type="text" name="E01CUSNA1" size="45" maxlength="45" value="<%= userPO.getHeader3() %>" readonly>
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <INPUT type="text" name="E01DEAACC" size="12" maxlength="12" value="<%= userPO.getIdentifier() %>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <INPUT type="text" name="E01DEACCY" size="4" maxlength="3" value="<%= userPO.getCurrency() %>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <INPUT type="text" name="E01DEAPRO" size="4" maxlength="4" value="<%= userPO.getHeader1() %>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
<%
} else { %>

<INPUT TYPE=HIDDEN NAME="ACTION" VALUE="A">

<% }
%>
  <h4>Información Básica</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Identificación :</div>
            </td>
            <td nowrap width="23%"> 
              <INPUT type="text" name="E03NUMIDE" size="17" maxlength="15" value="<%= dftAcceptor.getE03NUMIDE() %>" readonly>
               </td>
            <td nowrap width="25%"></td>
            <td nowrap width="27%">
              </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Nombre :</div>
            </td>
            <TD nowrap width="23%" colspan="3"> 
              <INPUT type="text" name="E03CUMMA1" size="45" maxlength="45" value="<%= dftAcceptor.getE03CUMMA1() %>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Dirección 1 :</div>
            </td>
            <TD nowrap width="23%" colspan="3"> 
              <INPUT type="text" name="E03CUMMA2" size="35" maxlength="35" value="<%= dftAcceptor.getE03CUMMA2() %>"> </TD>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Dirección 2 :</div>
            </td>
            <TD nowrap width="23%" colspan="3"> 
              <INPUT type="text" name="E03CUMMA3" size="35" maxlength="35" value="<%= dftAcceptor.getE03CUMMA3() %>"> </TD>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Teléfono :</div>
            </td>
            <td nowrap width="23%" > 
              <INPUT type="text" name="E03CUMHPN" size="17" maxlength="17" value="<%= dftAcceptor.getE03CUMHPN() %>"> </td>
            <td nowrap width="25%" ></td>
            <td nowrap width="27%" ></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Ciudad :</div>
            </td>
            <td nowrap width="23%" > 
              <INPUT type="text" name="E03CUMCTY" size="30" maxlength="30" value="<%= dftAcceptor.getE03CUMCTY() %>">
            </td>
            <td nowrap width="25%" > 
              <div align="right">Estado :</div>
            </td>
            <td nowrap width="27%" > 
              <INPUT type="text" name="E03CUMSTE" size="4" maxlength="4" value="<%= dftAcceptor.getE03CUMSTE() %>"><a href="javascript:GetCodeCNOFC('E03CUMSTE','27')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absbottom" border="0"></a></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Código Postal:</div>
            </td>
            <td nowrap width="23%" > 
              <INPUT type="text" name="E03CUMZPC" size="15" maxlength="15" value="<%= dftAcceptor.getE03CUMZPC() %>">
            </td>
            <td nowrap width="25%" > 
              <div align="right">Casilla Postal:</div>
            </td>
            <td nowrap width="27%" > 
              <INPUT type="text" name="E03CUMPOB" size="10" maxlength="10" value="<%= dftAcceptor.getE03CUMPOB() %>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Plaza de Pago:</div>
            </td>
            <td nowrap width="23%" > 
              <INPUT type="text" name="E03DLDPWY" size="4" maxlength="4" value="<%= dftAcceptor.getE03DLDPWY()  %>">
              <a href="javascript:getCodeAcpt('E03DLDPWY','02')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a></td>
            <td nowrap width="25%" ></td>
            <td nowrap width="27%" ></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Agente Cobrador:</div>
            </td>
            <td nowrap width="23%" > 
              <INPUT type="text" name="E03DLDREM" size="4" maxlength="4" value="<%= dftAcceptor.getE03DLDREM() %>">
              <a href="javascript:getCodeAcpt('E03DLDREM','01')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a></td>
            <td nowrap width="25%" ></td>
            <td nowrap width="27%" ></td>
          </tr>
          
        </table>
      </td>
    </tr>
  </table>
<p align="center">&nbsp; </p>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"></div>
      </td>
      <td width="34%"> 
        <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
      </td>
      <td width="33%"> 
        <div align="center"></div>
      </td>
    </tr>
  </table>
  <p align="center">&nbsp; </p>
  </form>
</body>
</html>
