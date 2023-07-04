<html>
<head>
<title>Titulares</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "lnTit" class= "datapro.eibs.beans.ESD000006Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(dft_m_opt);

	builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }


</SCRIPT>

</head>


<body nowrap bgcolor="#FFFFFF">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors();");
     out.println("</SCRIPT>");
 }
    out.println("<SCRIPT> initMenu(); </SCRIPT>");
%> 
<h3 align="center">Titulares <%= userPO.getHeader4().trim()%><img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="dft_tit.jsp,EDL0800"></h3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="8">
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
                <input type="text" name="E02CUN2" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text" name="E02NA12" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E06ACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
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
                <input type="text" name="E02PRO2" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Titulares</h4>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <td nowrap width="9%"> 
              <div align="center">N&uacute;mero</div>
            </td>
            <td nowrap width="47%"> 
              <div align="center">Nombre y Apellidos</div>
            </td>
            <td nowrap width="26%"> 
              <div align="center"> Identificaci&oacute;n</div>
            </td>
            <td nowrap width="18%"> 
              <div align="center">Relaci&oacute;n</div>
            </td>
          </tr>
          <%
  				   int amount = 9;
 				   String name;
  					for ( int i=1; i<=amount; i++ ) {
   					  name = i + "";
   			%> 
          <tr id="trclear"> 
            <td nowrap width="9%"> 
              <div align="center"> 
                <input type="text" name="E06CU<%= name %>" size="9" maxlength="9" value="<%= lnTit.getField("E06CU"+name).getString().trim()%>" 
				oncontextmenu="showPopUp(custdescidHelp,this.name,'','','E06MA<%= name %>','E06ID<%= name %>','')" onKeyPress="enterInteger()">
              </div>
            </td>
            <td nowrap width="47%"> 
              <div align="center"> 
                <input type="text" name="E06MA<%= name %>" size="40" maxlength="45" value="<%= lnTit.getField("E06MA"+name).getString().trim()%>">
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="center"> 
                <input type="text" name="E06ID<%= name %>" size="15" maxlength="15" value="<%= lnTit.getField("E06ID"+name).getString().trim()%>">
              </div>
            </td>
            <td nowrap width="18%"> 
              <div align="center"> 
                <input type="text" name="E06TY<%= name %>" size="2"  maxlength="1"
				oncontextmenu="showPopUp(accholdersHelp,this.name,'','','','','')"  value="<%= lnTit.getField("E06TY"+name).getString().trim()%>" >
              </div>
            </td>
          </tr>
          <%
    		}
    		%> 
        </table>
      </td>
    </tr>
  </table>
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</p>
</form>
</body>
</html>
