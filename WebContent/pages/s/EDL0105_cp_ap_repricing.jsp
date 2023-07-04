<html>
<head>
<title>Exchange Rate</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "cdRep" class= "datapro.eibs.beans.EDL010512Message" scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(cp_a_opt);


</SCRIPT>


</head>

<body nowrap>



<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 if ( !userPO.getPurpose().equals("NEW") ) {
    out.println("<SCRIPT> initMenu();  </SCRIPT>");
 }
%>

<h3 align="center">Mantenimiento de Precio de Mercado<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cp_ap_repricing.jsp, EDL0105"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="10">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>No. Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E12DEACUN" size="9" maxlength="9" readonly value="<%= userPO.getCusNum().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E12CUSNA1" size="45" maxlength="45" readonly value="<%= userPO.getCusName().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E12DEAACC" size="12" maxlength="9" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E12DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E12DEAPRO" size="4" maxlength="4" readonly value="<%= userPO.getProdCode().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Opci�n de Call/Put</h4>
  <table class="tableinfo">
    <tr > 
      <td> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td width="39%" > 
              <div align="right">Fecha de Call :</div>
            </td>
            <td width="61%">
              <input type="text" name="E12CALDT1" <% if (cdRep.getF12CALDT1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= cdRep.getE12CALDT1().trim()%>" readonly>
              <input type="text" name="E12CALDT2" <% if (cdRep.getF12CALDT2().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= cdRep.getE12CALDT2().trim()%>" readonly>
              <input type="text" name="E12CALDT3" <% if (cdRep.getF12CALDT3().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= cdRep.getE12CALDT3().trim()%>" readonly>
             </td>
          </tr>
          <tr id="trclear"> 
            <td width="39%" > 
              <div align="right">Fecha de Put :</div>
            </td>
            <td width="61%" > 
              <input type="text" name="E12PUTDT1" <% if (cdRep.getF12PUTDT1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= cdRep.getE12PUTDT1().trim()%>" readonly>
              <input type="text" name="E12PUTDT2" <% if (cdRep.getF12PUTDT2().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= cdRep.getE12PUTDT2().trim()%>" readonly>
              <input type="text" name="E12PUTDT3" <% if (cdRep.getF12PUTDT3().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= cdRep.getE12PUTDT3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="39%"> 
              <div align="right">Retasaci�n :</div>
            </td>
            <td width="61%"> 
              <input type="text" name="E12DEALSR" size="15" maxlength="13" <% if (cdRep.getF12DEALSR().equals("Y")) out.print("id=\"txtchanged\""); %> value="<%= cdRep.getE12DEALSR().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
   <br>
</form>
</body>
</html>