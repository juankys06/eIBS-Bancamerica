<html>
<head>
<title>Special Codes</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</head>

<jsp:useBean id= "lnCodes" class= "datapro.eibs.beans.ESD000002Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<SCRIPT Language="Javascript">

  	builtNewMenu(cc_a_opt);

</SCRIPT>

<body nowrap bgcolor="#FFFFFF">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
    out.println("<SCRIPT> initMenu();  </SCRIPT>");

%> 
<h3 align="center">Codigos especiales<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cc_codes.jsp,ECC0010" ></h3> 
<hr size="4">
 <FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0010" >
  <p>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="8">
  </p>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Clientes :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left">
                <input type="text" name="E02CUN2" size="9" maxlength="9" readonly value="<%= userPO.getCusNum().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text" name="E02NA12" size="45" maxlength="45" readonly value="<%= userPO.getCusName().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E02ACC" size="12" maxlength="12" value="<%= userPO.getAccNum().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E02PRO2" size="4" maxlength="4" readonly value="<%= userPO.getHeader9().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Codigos especiales</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap > 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Oficial de cuenta :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02OFC" readonly size="5" maxlength="3" value="<%= lnCodes.getE02OFC().trim()%>">
              <input type="text" name="D02OFC" size="40" maxlength="35" value="<%= lnCodes.getD02OFC().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%" > 
              <div align="right">Oficial substituto :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" readonly name="E02OF2" size="5" maxlength="3" value="<%= lnCodes.getE02OF2().trim()%>">
              <input type="text" readonly name="D02OF2" size="40" maxlength="35" value="<%= lnCodes.getD02OF2().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="3%"> 
              <div align="right">Pais de residencia :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" readonly name="E02GEC" size="5" maxlength="3" value="<%= lnCodes.getE02GEC().trim()%>">
              <input type="text" readonly name="D02GEC" size="40" maxlength="35" value="<%= lnCodes.getD02GEC().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="3%"> 
              <div align="right">Pais de riesgo :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" readonly name="E02GRC" size="5" maxlength="3" value="<%= lnCodes.getE02GRC().trim()%>" readonly>
              <input type="text" readonly name="D02GRC" size="40" maxlength="35" value="<%= lnCodes.getD02GRC().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Codigo de industria :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" readonly name="E02INC" size="5" maxlength="3" value="<%= lnCodes.getE02INC().trim()%>">
              <input type="text" readonly name="D02INC" size="40" maxlength="35" value="<%= lnCodes.getD02INC().trim()%>" readonly>
             </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Linea de negocios :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" readonly name="E02BUC" size="5" maxlength="3" value="<%= lnCodes.getE02BUC().trim()%>">
              <input type="text" readonly name="D02BUC" size="40" maxlength="35" value="<%= lnCodes.getD02BUC().trim()%>" readonly>
             </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Codigo de usuario 3 :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" readonly name="E02UC3" size="5" maxlength="3" value="<%= lnCodes.getE02UC3().trim()%>">
              <input type="text" readonly name="D02UC3" size="40" maxlength="35" value="<%= lnCodes.getD02UC3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Grado del cliente :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" readonly name="E02UC5" size="5" maxlength="3" value="<%= lnCodes.getE02UC5().trim()%>">
              <input type="text" readonly name="D02UC5" size="40" maxlength="35" value="<%= lnCodes.getD02UC5().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Grado de facilidad :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" readonly name="E02UC6" size="5" maxlength="3" value="<%= lnCodes.getE02UC6().trim()%>">
              <input type="text" readonly name="D02UC6" size="40" maxlength="35" value="<%= lnCodes.getD02UC6().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Codigo de usuario 7 :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" readonly name="E02UC7" size="5" maxlength="3" value="<%= lnCodes.getE02UC7().trim()%>">
              <input type="text" readonly name="D02UC7" size="40" maxlength="35" value="<%= lnCodes.getD02UC7().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Codigo de usuario 8  :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" readonly name="E02UC4" size="7" maxlength="6" value="<%= lnCodes.getE02UC4().trim()%>">
              <input type="text" readonly name="D02UC4" size="40" maxlength="35" value="<%= lnCodes.getD02UC4().trim()%>" readonly>
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


