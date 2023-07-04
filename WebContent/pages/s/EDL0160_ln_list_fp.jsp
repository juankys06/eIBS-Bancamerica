<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Estado de Cuentas
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "cifList" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id="stBalances" class="datapro.eibs.beans.EDL030002Message"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<BODY>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
<%
if (userPO.getPurpose().equals("INQUIRY")){
%>

<%
if ( userPO.getHeader23().equals("G") ||  userPO.getHeader23().equals("V")){
%>
	builtNewMenu(ln_i_1_opt);
<%   
}
else if (userPO.getHeader23().equals("DFT")) {
%>
	builtNewMenu(dft_i_opt);
<%   
}
else  {
%>
	builtNewMenu(ln_i_2_opt);
<%   
}
%>
<%
}
%>

function PrintPreview() {

  <% 
  int iniPos = cifList.getFirstRec() - 1;
  out.println("var pos = " + iniPos + ";");
  %>
	var pg = '<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSEDL0300L?SCREEN=4&Pos=' + pos;
	CenterWindow(pg,720,500,2);

}

</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
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
  <h3 align="center">Estado de Cuentas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ln_list_fp.jsp,EDL0160"> 
  </h3>
  <hr size="4">
  <BR>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="14%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="9%" > 
              <div align="left"> 
                <input type="text" name="E02CUN2" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E02NA12" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap ><b> 
              <input type="text" name="E02PRO2" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
              </b></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="14%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="9%"> 
              <div align="left"> 
                <input type="text" name="E02ACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="12%"> 
              <div align="right">Oficial :</div>
            </td>
            <td nowrap width="33%"> 
              <div align="left"><b> 
                <input type="text" name="E02NA122" size="30" maxlength="30" readonly value="<%= userPO.getOfficer().trim()%>">
                </b> </div>
            </td>
            <td nowrap width="11%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="21%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Datos B&aacute;sicos de la Cuenta</h4>
  <table class="tableinfo">
    <tr > 
      <td > 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td  width="32%" height="15" nowrap> 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td  width="28%" height="15"> 
              <div align="left"></div>
              <%= stBalances.getE02CUMMA1().trim()%></td>
            <td  width="25%" height="15"> 
              <div align="right"><b>Saldo</b> <%= stBalances.getE02DEACCY().trim()%> 
                <b>Principal :</b></div>
            </td>
            <td  width="15%" height="15" nowrap> 
              <div align="right"><%= Util.fcolorCCY(stBalances.getE02DEAMEP().trim())%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td  width="32%"> 
              <div align="right"><b>Direcci&oacute;n :</b></div>
            </td>
            <td  width="28%" nowrap> 
              <div align="left"></div>
              <%= stBalances.getE02CUMMA2().trim()%></td>
            <td  width="25%" nowrap> 
              <div align="right"><b>Saldo Inter&eacute;s :</b></div>
            </td>
            <td  width="15%"> 
              <div align="right"><%= Util.fcolorCCY(stBalances.getE02DEAMEI().trim())%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td  width="32%" nowrap>&nbsp;</td>
            <td  width="28%"><%= stBalances.getE02CUMMA3().trim()%></td>
            <td  width="25%" nowrap> 
              <div align="right"><b> Cargo por Mora :</b></div>
            </td>
            <td  width="15%">
              <div align="right"><%= Util.fcolorCCY(stBalances.getE02DEAMEM().trim())%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td  width="32%" nowrap>&nbsp;</td>
            <td  width="28%"><%= stBalances.getE02CUMMA4().trim()%></td>
            <td  width="25%" nowrap> 
              <div align="right"><b>Otros Cargos :</b></div>
            </td>
            <td  width="15%">
              <div align="right"><%= Util.fcolorCCY(stBalances.getE02DEAOTH().trim())%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td  width="32%" nowrap> 
              <div align="right"><b>Fecha de Apertura :</b></div>
            </td>
            <td  width="28%"> 
              <div align="left"><%= Util.formatDate(stBalances.getE02OPEND1(),stBalances.getE02OPEND2(),stBalances.getE02OPEND3())%></div>
            </td>
            <td  width="25%" nowrap> 
              <div align="right"><b>Saldo Total :</b></div>
            </td>
            <td  width="15%"> 
              <div align="right"><%= Util.fcolorCCY(stBalances.getE02TOTAMN().trim())%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td  width="32%" nowrap> 
              <div align="right"><b>Fecha de Vencimiento :</b></div>
            </td>
            <td  width="28%" nowrap> 
              <div align="left"><%= Util.formatDate(stBalances.getE02MATUR1(),stBalances.getE02MATUR2(),stBalances.getE02MATUR3())%></div>
            </td>
            <td  width="25%" nowrap> 
              <div align="right"><b>Tasa de Inter&eacute;s :</b></div>
            </td>
            <td  width="15%"> 
              <div align="right"><%= stBalances.getE02DEARTE().trim()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td  width="32%"> 
              <div align="right"><b>Ultima Renovaci&oacute;n :</b></div>
            </td>
            <td  width="28%" nowrap> 
              <div align="left"><%= Util.formatDate(stBalances.getE02LSTRD1(),stBalances.getE02LSTRD2(),stBalances.getE02LSTRD3())%></div>
            </td>
            <td  width="25%" nowrap> 
              <div align="right"><b>N&uacute;mero Renovaciones :</b></div>
            </td>
            <td  width="15%" nowrap> 
              <div align="right"><%= stBalances.getE02DEARON().trim()%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td  width="25%" nowrap height="20"> 
              <div align="right"><b>Correo Electr&oacute;nico :</b></div>
            </td>
            <td  width="15%" nowrap height="20"> 
              <div align="left"><a href="mailto:<%= stBalances.getE02CUSIAD().trim()%>" target="body"><%= stBalances.getE02CUSIAD().trim()%></a></div>
            </td>
                        <td  width="32%" height="20"> 
              <div align="right"><b>Ultimo C&aacute;lculo Interes :</b></div>
            </td>
            <td  width="28%" nowrap height="20"> 
              <div align="right"></div>
              <%= Util.formatDate(stBalances.getE02LSTCL1(),stBalances.getE02LSTCL2(),stBalances.getE02LSTCL3())%></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4 align="left">Transacciones</h4>
  <TABLE id=cfTable class="tableinfo">
    <TR id=trdark> 
      <TH ALIGN=CENTER nowrap width="8%" >Fecha Proceso</TH>
      <TH ALIGN=CENTER nowrap width="1%" >Fecha de <br> Registro</TH>
      <TH ALIGN=CENTER nowrap width="1%" >TC</TH>
      <TH ALIGN=CENTER nowrap width="6%" >Descripci&oacute;n </TH>
      <TH ALIGN=CENTER nowrap width="4%" >Principal</TH>
      <TH ALIGN=CENTER nowrap width="0%" >&nbsp;</TH>
      <TH ALIGN=CENTER nowrap width="5%" >Intereses </TH>
      <TH ALIGN=CENTER nowrap width="0%" >&nbsp;</TH>
      <TH ALIGN=CENTER nowrap width="2%" >Mora</TH>
      <TH ALIGN=CENTER nowrap width="0%" >&nbsp;</TH>
      <TH ALIGN=CENTER nowrap width="7%" >Otros Cargos</TH>
      <TH ALIGN=CENTER nowrap width="0%" >&nbsp;</TH>
      <TH ALIGN=CENTER nowrap width="2%" >Lote</TH>
      <TH ALIGN=CENTER nowrap width="6%" >Fecha Valor</TH>
      <TH ALIGN=CENTER nowrap width="2%" >Hora </TH>
      <TH ALIGN=CENTER nowrap width="14%" >Usuario</TH>
      <TH ALIGN=CENTER nowrap width="9%" >Banco Org.</TH>
      <TH ALIGN=CENTER nowrap width="34%" >Sucursal Org.</TH>
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
  <BR>
  
  <TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
 <%
        if ( cifList.getShowPrev() ) {
      			int pos = cifList.getFirstRec() - 51;
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.client.JSEDL0300L?SCREEN=1&Pos=" + pos +"\"><img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
%> 
	</TD>
   <TD WIDTH="50%" ALIGN=RIGHT> 
 <%      
        if ( cifList.getShowNext() ) {
      			int pos = cifList.getLastRec();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.client.JSEDL0300L?SCREEN=1&Pos=" + pos +"\"><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");
        }
%>
   </TD>
  </TR>
  </TABLE>

  <p align=center>&nbsp; </p>

  <%
  }
%> 
  
  <div align="center"> 
    <input id="EIBSBTN" type=button name="Submit" OnClick="PrintPreview()" value="Imprimir">
  </div>

  <SCRIPT Language="Javascript">
   var max=cfTable.rows.length;
     for(i= 0; i < max; i++){
      cfTable.rows[i].cells[1].style.display="none";
     }
  </SCRIPT>
</FORM>

</BODY>
</HTML>
