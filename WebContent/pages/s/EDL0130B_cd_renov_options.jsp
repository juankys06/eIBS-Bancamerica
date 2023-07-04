<html>
<head>
<title>Certificates of Deposit</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="cdRenov" class="datapro.eibs.beans.EDL013008Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

<%
if ( userPO.getHeader3().equals("CDS") && !(userPO.getHeader16().equals("N"))) {
%>
	builtNewMenu(cd_bo_opt);
<%   
}
else if ( userPO.getHeader3().equals("TDS") && !(userPO.getHeader16().equals("N"))) {
%>
	builtNewMenu(td_bo_opt);
<%   
}
else if ( userPO.getHeader3().equals("FFS") && !(userPO.getHeader16().equals("N")) ) {
%>
	builtNewMenu(ff_bo_opt);
<%   
}
else if ( userPO.getHeader3().equals("TPS") && !(userPO.getHeader16().equals("N"))) {
%>
	builtNewMenu(tp_bo_opt);
<%   
}
else if ( userPO.getHeader3().equals("ACS")&& !(userPO.getHeader16().equals("N")) ) {
%>
	builtNewMenu(ac_bo_act_opt);
<%   
}

else if ( userPO.getHeader3().equals("CDS") && (userPO.getHeader16().equals("N"))) {
%>
	builtNewMenu(cd_bo_act_opt);
<%   
}
else if ( userPO.getHeader3().equals("TDS") && (userPO.getHeader16().equals("N"))) {
%>
	builtNewMenu(td_bo_act_opt);
<%   
}
else if ( userPO.getHeader3().equals("FFS") && (userPO.getHeader16().equals("N")) ) {
%>
	builtNewMenu(ff_bo_act_opt);
<%   
}
else if ( userPO.getHeader3().equals("TPS") && !(userPO.getHeader16().equals("N"))) {
%>
	builtNewMenu(tp_bo_opt);
<%   
}
else if ( userPO.getHeader3().equals("ACS")&& (userPO.getHeader16().equals("N")) ) {
%>
	builtNewMenu(ac_bo_act_opt);
<%   
}
%>   

</SCRIPT>


<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
	 error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
	 
	 
     out.println("<SCRIPT> initMenu();  </SCRIPT>");
%> 
<h3 align="center">Renovaci&oacute;n<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_renov_options,EDL0130B"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="64">
  <INPUT TYPE=HIDDEN NAME="E08DEABNK" VALUE="<%= cdRenov.getE08DEABNK().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E08DEACCY" VALUE="<%= cdRenov.getE08DEACCY().trim()%>">
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
                <input type="text" onkeypress="enterInteger()" name="E08DEACUN" size="9" maxlength="9" readonly value="<%= userPO.getCusNum().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" onkeypress="enterInteger()" name="E08CUSNA1" size="45" maxlength="45" readonly value="<%= userPO.getCusName().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" onkeypress="enterInteger()" name="E08DEAACC" size="12" maxlength="9" value="<%= userPO.getIdentifier().trim()%>" readonly>
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
                <input type="text" name="E08DEAPRO" size="4" maxlength="4" readonly value="<%= userPO.getProdCode().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>&nbsp;</h4>
  <table class="tableinfo" width="714">
    <tr > 
      <td>
        <table cellpadding=2 cellspacing=0 align=center width=100% bordercolor="#000000">
          <tr id="trdark"> 
            <td width=46 height="46"><b><b> </b><b><b><b> 
              <input type="radio" name="E08DEAROC" value="D" 
			  <%if (cdRenov.getE08DEAROC().equals("D")) out.print("checked");%>>
              </b></b></b>D</b></td>
            <td height="46" colspan="2" width="650" > Cuando el Contrato vence 
              cancela el dep&oacute;sito m&aacute;s los Intereses pagaderos a 
              la Cuenta Contable N&uacute;mero
<input type="text" onkeypress="enterInteger()" name="E08DDDGLN" size="17" maxlength="16" value="<%= cdRenov.getE08DDDGLN().trim()%>">
              <a href="javascript:GetLedger('E08DDDGLN',document.forms[0].E08DEABNK.value,document.forms[0].E08DEACCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="bottom" border="0" ></a> 
              , generando un 
              <input type="hidden" name="E08DDDFLV" value="">
              <input type="radio" name="E08DDDFLV" value="C" onClick="document.forms[0].E08DDDFLV.value='C'"
			  <%if (cdRenov.getE08DDDFLV().equals("C")) out.print("checked");%>>
              Cheque Oficial, o una 
              <input type="radio" name="E08DDDFLV" value="" onClick="document.forms[0].E08DDDFLV.value='V'"
			  <%if (cdRenov.getE08DDDFLV().equals("V")) out.print("checked");%>>
              Transferencia de Fondos V&iacute;a 
              <select name="E08DDDPVI">
                <option value="" <% if (!(cdRenov.getE08DDDPVI().equals("F") || cdRenov.getE08DDDPVI().equals("Q")||
				cdRenov.getE08DDDPVI().equals("T") ||cdRenov.getE08DDDPVI().equals("1")||cdRenov.getE08DDDPVI().equals("2") 
				||cdRenov.getE08DDDPVI().equals("3")))out.print("selected"); %>> 
                </option>
                <option value="F" <% if (cdRenov.getE08DDDPVI().equals("F")){out.print("selected");} %>>Emisión FED</option>
                <option value="Q" <% if (cdRenov.getE08DDDPVI().equals("Q")){out.print("selected");} %>>Emisión de Cupones</option>
                <option value="T" <% if (cdRenov.getE08DDDPVI().equals("T")){out.print("selected");} %>>Telex</option>
                <option value="1" <% if (cdRenov.getE08DDDPVI().equals("1")){out.print("selected");} %>>Swift Formato MT-100</option>
                <option value="2" <% if (cdRenov.getE08DDDPVI().equals("2")){out.print("selected");} %>>Swift Formato MT-200</option>
                <option value="3" <% if (cdRenov.getE08DDDPVI().equals("3")){out.print("selected");} %>>Swift Formato MT-202</option>
              </select>
              . </td>
          </tr>
          <tr id="trclear"> 
            <td width=46></td>
            <td colspan="2" width="650" >&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td width=46 height="36"><b><b> </b><b> 
              <input type="radio" name="E08DEAROC" value="E" 
			  <%if (cdRenov.getE08DEAROC().equals("E")) out.print("checked");%>>
              </b>E</b></td>
            <td height="36" colspan="2" width="650" >Cuando el Contrato vence 
              cancela el dep&oacute;sito y acredita el monto a la Cuenta N&uacute;mero 
              <input type="text" onkeypress="enterInteger()" name="E08EEEACC" size="13" maxlength="12" value="<%= cdRenov.getE08EEEACC().trim()%>">
              <a href="javascript:GetAccount('E08EEEACC',document.forms[0].E08DEABNK.value,'RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="bottom" border="0" ></a>(Corriente, 
              Ahorro, etc.) . </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <div align="center"> 
	   
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>

  </form>
</body>
</html>
