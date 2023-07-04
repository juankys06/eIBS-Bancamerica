<html>
<head>
<title>Capital</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
 
 


<SCRIPT Language="Javascript">

  builtNewMenu(client_inq_corp_opt);

</SCRIPT>

</head>


<jsp:useBean id= "capital" class= "datapro.eibs.beans.ESD008008Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

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
<h3 align="center">Balance del Cliente <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_corp_capital, ESD0080"></h3>
<hr size="4">
<p>&nbsp;</p>

 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="22">
   

<table class="tableinfo">
  <tr > 
    <td> 
      <table cellspacing="0" cellpadding="2" width="100%" class="tbhead"   align="center">
        <tr>
             <td nowrap width="10%" align="right"> Cliente: 
               </td>
          <td nowrap width="12%" align="left">
      			<%= userPO.getHeader1()%>
          </td>
            <td nowrap width="6%" align="right">ID:  
            </td>
          <td nowrap width="14%" align="left">
      			<%= userPO.getHeader2()%>
          </td>
            <td nowrap width="8%" align="right"> Nombre: 
               </td>
          <td nowrap width="50%"align="left">
      			<%= userPO.getHeader3()%>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>

 <p>&nbsp;</p>

 <table class="tableinfo">
      <tr id="trdark"> 
        <td width="44%" align="center">&nbsp;ACTIVOS&nbsp;</td>
        <td width="56%" align="center">&nbsp;PASIVOS&nbsp;</td>
      </tr>
 </table>
 <table class="tableinfo">
    <tr > 
      <td> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="center">
          <tr id="trdark"> 
            <td width="20%">&nbsp;</td>
            <td width="24%">
              <input type="text" readonly name="E08A01" size="16" maxlength="15" value="<%= capital.getE08A01().trim()%>">
            </td>
            <td width="22%">&nbsp;</td>
            <td width="34%"> 
              <input type="text" readonly name="E08L01" value="<%= capital.getE08L01().trim()%>" size="16" maxlength="15">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="20%" > </td>
            <td width="24%" > 
              <input type="text" readonly name="E08A02" value="<%= capital.getE08A02().trim()%>" size="16" maxlength="15">
            </td>
            <td width="22%" >&nbsp; </td>
            <td width="34%" >
              <input type="text" readonly name="E08L02" value="<%= capital.getE08L02().trim()%>" size="16" maxlength="15">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="20%" >&nbsp;</td>
            <td width="24%" >
              <input type="text" readonly name="E08A03" value="<%= capital.getE08A03().trim()%>" size="16" maxlength="15">
            </td>
            <td width="22%">&nbsp;</td>
            <td width="34%" >
              <input type="text" readonly name="E08L03" value="<%= capital.getE08L03().trim()%>" size="16" maxlength="15">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="20%" >&nbsp;</td>
            <td width="24%" >
              <input type="text" readonly name="E08A04" value="<%= capital.getE08A04().trim()%>" size="16" maxlength="15">
            </td>
            <td width="22%" >&nbsp;</td>
            <td width="34%" >
              <input type="text" readonly name="E08L04" value="<%= capital.getE08L04().trim()%>" size="16" maxlength="15">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="20%" >&nbsp;</td>
            <td width="24%" >
              <input type="text" readonly name="E08A05" value="<%= capital.getE08A05().trim()%>" size="16" maxlength="15">
            </td>
            <td width="22%" >&nbsp;</td>
            <td width="34%" >
              <input type="text" readonly name="E08L05" value="<%= capital.getE08L05().trim()%>" size="16" maxlength="15">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="20%" >&nbsp;</td>
            <td width="24%" > 
              <input type="text" readonly name="E08A06" value="<%= capital.getE08A06().trim()%>" size="16" maxlength="15">
            </td>
            <td width="22%" >&nbsp;</td>
            <td width="34%" >
              <input type="text" readonly name="E08L06" value="<%= capital.getE08L06().trim()%>" size="16" maxlength="15">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="20%" >&nbsp;</td>
            <td width="24%" > 
              <input type="text" readonly name="E08A07" value="<%= capital.getE08A07().trim()%>" size="16" maxlength="15">
            </td>
            <td width="22%" >&nbsp;</td>
            <td width="34%" >
              <input type="text" readonly name="E08L07" value="<%= capital.getE08L07().trim()%>" size="16" maxlength="15">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="20%" >&nbsp;</td>
            <td width="24%" >
              <input type="text" readonly name="E08A08" value="<%= capital.getE08A08().trim()%>" size="16" maxlength="15">
            </td>
            <td width="22%" >&nbsp;</td>
            <td width="34%" >
              <input type="text" readonly name="E08L08" value="<%= capital.getE08L08().trim()%>" size="16" maxlength="15">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="20%" >&nbsp;</td>
            <td width="24%" >
              <input type="text" readonly name="E08A09" value="<%= capital.getE08A09().trim()%>" size="16" maxlength="15">
            </td>
            <td width="22%" >&nbsp;</td>
            <td width="34%" >
              <input type="text" readonly name="E08L09" value="<%= capital.getE08L09().trim()%>" size="16" maxlength="15">
            </td>
          </tr>
          <tr id="trclear">
            <td width="20%" >&nbsp;</td>
            <td width="24%" >
              <input type="text" readonly name="E08A10" value="<%= capital.getE08A10().trim()%>" size="16" maxlength="15">
            </td>
            <td width="22%" >&nbsp;</td>
            <td width="34%" >
              <input type="text" readonly name="E08L10" value="<%= capital.getE08L10().trim()%>" size="16" maxlength="15">
            </td>
          </tr>
          <tr id="trdark">
            <td width="20%" >
              <div align="right"><b>Total :</b></div>
            </td>
            <td width="24%" >
              <input type="text" readonly name="E08A11" value="<%= capital.getE08A11().trim()%>" size="16" maxlength="15">
            </td>
            <td width="22%" >
              <div align="right"><b>Total :</b></div>
            </td>
            <td width="34%" >
              <input type="text" readonly name="E08L11" value="<%= capital.getE08L11().trim()%>" size="16" maxlength="15">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <table class="tableinfo">
      <tr id="trdark"> 
        <td width="50%" >
          <div align="right"><b>Capital Líquido :</b></div>
        </td>
        <td width="50%" >
          <input type="text" readonly name="CAPITAL" value="<%= userPO.getHeader21() %>" size="16" maxlength="15">
        </td>
      </tr>
 </table>
 
</form>
</body>
</html>
