<html>
<head>
<title>Sender to Receiver Information (72)</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="msg" class="datapro.eibs.beans.ELC040003Message"  scope="session" />

<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="javascript">

<%if (!userPO.getPurpose().equals("NEW")){%>

	builtNewMenu(sb_opening);

<%}%>

   builtHPopUp();

  function showPopUp(opth,field,Banco,ccy,field1,field2,opcod) {
   init(opth,field,Banco,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
</SCRIPT>

</head>

<body bgcolor="#FFFFFF">


<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 if ( !userPO.getPurpose().equals("NEW") ) {
    out.println("<SCRIPT> initMenu();  </SCRIPT>");
 }
%> 
<h3 align="center">Información de Banco a Banco (72)<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="lc_72_info.jsp, ELC0500"></h3>
<hr size="4">


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0400" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="23">
   
<table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E03LCDPRO" size="5" maxlength="4" value="<%= msg.getE03LCDPRO().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="16%"> 
            </td>
          </tr>        
          <tr id="trclear"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Banco : </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" readonly name="E03LCDBNK" size="3" maxlength="2" value="<%= msg.getE03LCDBNK().trim()%>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Numero Carta de Credito : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" readonly name="E03LCDACC" size="21" maxlength="20" value="<%= msg.getE03LCDACC().trim()%>">
                </b> </div>
            </td>
          </tr>               
        </table>
      </td>
    </tr>
  </table>
<BR>
<table class="tableinfo">
    <tr > 
      <td nowrap align="center"> 
          <input type="text" name="E03LCDM01" size="45" maxlength="35" value="<%= msg.getE03LCDM01().trim()%>"><br>
          <input type="text" name="E03LCDM02" size="45" maxlength="35" value="<%= msg.getE03LCDM02().trim()%>"><br>
          <input type="text" name="E03LCDM03" size="45" maxlength="35" value="<%= msg.getE03LCDM03().trim()%>"><br>
          <input type="text" name="E03LCDM04" size="45" maxlength="35" value="<%= msg.getE03LCDM04().trim()%>"><br>
          <input type="text" name="E03LCDM05" size="45" maxlength="35" value="<%= msg.getE03LCDM05().trim()%>"><br>
          <input type="text" name="E03LCDM06" size="45" maxlength="35" value="<%= msg.getE03LCDM06().trim()%>"><br>
      </td>
    </tr>
  </table>
  <p align="center"> 
   <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
</form>
</body>
</html>
