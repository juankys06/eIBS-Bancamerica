<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Proteccionde Sobregiros
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "accList" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">

<%
if ( userPO.getOption().equals("RT") ) {
%>
	builtNewMenu(rt_i_opt);
<%   
}
else if ( userPO.getOption().equals("SV") ) {
%>
	builtNewMenu(sv_i_opt);
<%   
}
%>

</script>
</HEAD>

<BODY>



<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
   
   out.println("<SCRIPT> initMenu(); </SCRIPT>");
%> 

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0439">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  </p>

  <h3 align="center">Protecci&oacute;n de Sobregiros<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="od_inq_protect.jsp, EDD0439"> 
  </h3>
  <hr size="4">
  <br>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark"> 
            <td nowrap width="14%" > 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="9%" > 
              <div align="left"> 
                <input type="text" name="E01CLSACC" size="15" maxlength="12" readonly value="<%= userPO.getHeader21().trim()%>">
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="right"><b>Cliente : </b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01CLSNME" size="45" maxlength="45" readonly value="<%= userPO.getHeader19().trim()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap > 
              <input type="text" name="E01CLSCCY" size="4" maxlength="4" readonly value="<%= userPO.getHeader22().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="3" > 
              <div align="right"><b>Monto de Protecci&oacute;n de Sobregiros Actual 
                : </b></div>
            </td>
            <td nowrap >
              <input type="text" name="E01CLSAMT" size="15" maxlength="12" readonly value="<%= userPO.getHeader15().trim()%>">
            </td>
            <td nowrap >&nbsp;</td>
            <td nowrap >&nbsp;</td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Datos de la Cuenta de Cheques </h4>
  <table class="tableinfo">
    <tr> 
      <td > 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Saldo Disponible : </div>
            </td>
            <td width="25%"> 
              <div align="Left"><%= Util.fcolorCCY(userPO.getHeader16())%></div>
            </td>
            <td width="25%"> 
              <div align="right">Saldo Diferido :</div>
            </td>
            <td nowrap> 
              <div align="left"></div>
              <%= Util.fcolorCCY(userPO.getHeader17())%></td>
          </tr>
          <tr id="trclear"> 
            <td colspan="3"> 
              <div align="right">Promedio de los Ultimos 06 Meses : </div>
            </td>
            <td> 
              <div align="left"><%= Util.fcolorCCY(userPO.getHeader18())%></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>C&aacute;lculo Nuevo</h4>
  <table class="tableinfo" width="265">
    <tr> 
      <td > 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right"> <%= userPO.getHeader10()%> % de Saldo Promedio 
                :</div>
            </td>
            <td width="25%"> 
              <div align="Left"><%= Util.fcolorCCY(userPO.getHeader11())%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right"> <%= userPO.getHeader12()%> % de Inversiones 
                :</div>
            </td>
            <td width="25%"><%= Util.fcolorCCY(userPO.getHeader13())%></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="25%">
              <div align="right">Total :</div>
            </td>
            <td width="25%"><%= Util.fcolorCCY(userPO.getHeader14())%></td>
          </tr>
          <tr id="trclear">
            <td nowrap width="25%">
              <div align="right">Monto de Protecci&oacute;n Calculado :</div>
            </td>
            <td width="25%">
              <input type="text" name="E01CLSPRT" readonly size="16" maxlength="15"  value="<%= userPO.getHeader20().trim()%>" onKeyPress="enterDecimal()">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <%
	if ( userPO.getHeader4().equals("+") ) {
%>
  <br>  
  <table class="tableinfo">
         <tr id="trclear">
           No posee Inversiones Vigentes con Plazo Mayor a 28 días
         </tr>
   </table>
<%
	}
	else {
%>
  <h4 align="left">Inversiones Vigentes con Plazo Mayor a 28 D&iacute;as</h4>
  <TABLE id=cfTable class="tableinfo">
    <TR id="trdark"> 
      <TH   nowrap> 
        <div align="center" >Cuenta </div>
      </TH>
      <TH   nowrap> 
        <div align="center">Monto</div>
      </TH>
      <TH  nowrap> 
        <div align="center" >Plazo</div>
      </TH>
      <TH   nowrap> 
        <div align="center" >Vencimiento</div>
      </TH>
    </TR>
    <%
                accList.initRow();
                while (accList.getNextRow()) {
                    if (accList.getFlag().equals("")) {
                    		out.println(accList.getRecord());
                    }
                }
              %> 
    <tr id="trdark"> 
      <td nowrap > 
        <div align="right">Total :</div>
      </td>
      <td > 
        <div align="right"><b><%= Util.fcolorCCY(userPO.getHeader23())%></b></div>
      </td>
      <td nowrap > </td>
      <td nowrap > </td>
    </tr>
  </TABLE>
<% } %>
 
  <p>
    <div align="center"> 
      
    <input id="EIBSBTN" type=submit name="Submit"  value="Enviar">
    </div>
  </FORM>

</BODY>
</HTML>
