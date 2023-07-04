<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Analisis de Intereses
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>

<jsp:useBean id="girHeader" class="datapro.eibs.beans.EDL081601Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "cifList" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<BODY>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

<%
if ( userPO.getHeader23().equals("G") ||  userPO.getHeader23().equals("V")){
%>
	builtNewMenu(ln_i_1_opt);
<%   
}
else  {
%>
	builtNewMenu(ln_i_2_opt);
<%   
}
%>

</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
   if (userPO.getPurpose().equals("INQUIRY")){ 
   out.println("<SCRIPT> initMenu(); </SCRIPT>");}
%> 

<FORM>
<%
	if ( cifList.getNoResult() ) {
   		out.print("<center><h4>No hay resultados que correspondan a su criterio de búsqueda</h4></center>");
	}
	else {
%>
  <h3 align="center">Lista de Documentos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ln_inq_gir.jsp,EDL0816"> 
  </h3>
  <hr size="4">
  <BR>
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
                <input type="text" name="E02ACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
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
  <H4>Informaci&oacute;n del Cr&eacute;dito</H4>
  <table class="tableinfo">
    <tr > 
      <td nowrap height="69">
        <table width="100%" border="0" cellpadding="2" cellspacing="0" >
          <tr id="trdark"> 
            <td width="24%"> 
              <div align="right">Saldo :</div>
            </td>
            <td width="26%"> 
              <input type="text" name="E01DEAPRI" size="15" maxlength="15" readonly value="<%= girHeader.getE01DEAPRI().trim()%>">
            </td>
            <td width="25%"> 
              <div align="right">Tasa Inter&eacute;s :</div>
            </td>
            <td width="25%"> 
              <input type="text" name="E01DEARTE" size="9" maxlength="9" readonly value="<%= girHeader.getE01DEARTE().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="24%"> 
              <div align="right">Fecha Apertura :</div>
            </td>
            <td width="26%"> 
              <input type="text" name="E01OPEND1" size="2" maxlength="2" readonly value="<%= girHeader.getE01OPEND1().trim()%>">
              <input type="text" name="E01OPEND2" size="2" maxlength="2" readonly value="<%= girHeader.getE01OPEND2().trim()%>">
              <input type="text" name="E01OPEND3" size="2" maxlength="2" readonly value="<%= girHeader.getE01OPEND3().trim()%>">
            </td>
            <td width="25%"> 
              <div align="right">Fecha Vencimiento :</div>
            </td>
            <td width="25%"> 
              <input type="text" name="E01MATUR1" size="2" maxlength="2" readonly value="<%= girHeader.getE01MATUR1().trim()%>">
              <input type="text" name="E01MATUR2" size="2" maxlength="2" readonly value="<%= girHeader.getE01MATUR2().trim()%>">
              <input type="text" name="E01MATUR3" size="2" maxlength="2" readonly value="<%= girHeader.getE01MATUR3().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="24%"> 
              <div align="right">Per&iacute;odo Base :</div>
            </td>
            <td width="26%"> 
              <input type="text" name="E01DEABAS" size="3" maxlength="3" readonly value="<%= girHeader.getE01DEABAS().trim()%>">
            </td>
            <td width="25%"> 
              <div align="right">Tasa Mora :</div>
            </td>
            <td width="25%"> 
              <input type="text" name="E01MORRTE" size="9" maxlength="9" readonly value="<%= girHeader.getE01MORRTE().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n de Documentos</h4>
  <TABLE class="tableinfo">
    <TR id=trdark> 
      <TH ALIGN=CENTER  nowrap >Documento</TH>
      <TH ALIGN=CENTER  nowrap >Vencimiento</TH>
      <TH ALIGN=CENTER  nowrap >Girado</TH>
      <TH ALIGN=CENTER  nowrap > 
        <div align="center">Valor</div>
      </TH>
      <TH ALIGN=CENTER  nowrap > 
        <div align="center">Cargo Mora</div>
      </TH>
      <TH ALIGN=CENTER  nowrap > 
        <div align="center">Estado</div>
      </TH>
      <TH ALIGN=CENTER  nowrap >Identificaci&oacute;n</TH>
      <TH ALIGN=CENTER  nowrap >Fecha Pago</TH>
      <TH ALIGN=CENTER  nowrap >Inter&eacute;s</TH>
      <TH ALIGN=CENTER  nowrap >Tasa</TH>
      <TH ALIGN=CENTER  nowrap >Cuenta Repago</TH>
    </TR>
    <%
                cifList.initRow();
                while (cifList.getNextRow()) {
                    if (cifList.getFlag().equals("")) {
                    		out.println(cifList.getRecord());
                    }
                }
              %> 
  </TABLE>

  <%
  }
%> 
  <div align="center"> </div>
</FORM>

</BODY>
</HTML>
