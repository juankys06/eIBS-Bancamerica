<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Account Statement
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "cifList" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id="stBalances" class="datapro.eibs.beans.EDL030002Message"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<BODY>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

	   builtNewMenu(cdt_i_opt);
	   initMenuT();

function PrintPreview() {

  <% 
  int iniPos = cifList.getFirstRec() - 1;
  out.println("var pos = " + iniPos + ";");
  %>
    CenterWindow('<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSEDL0300?SCREEN=4&Pos=' + pos,600,500,4);
}

</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
   
   out.println("<SCRIPT> initMenu();  </SCRIPT>");
%> 

<FORM>
<h3 align="center">Estado de Cuenta<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="st_list_fp.jsp,EDL0160T"> 
  </h3>
  <hr size="4">
<BR>
<%
	if ( cifList.getNoResult() ) {
%>
   <table class="tbenter" width="100%" height="75%">
          <tr > 
            <td  align="center"> 
              <b>No hay resultados para su criterio de busqueda </b>
            </td>
          </tr>
   </table>           
  <%	
	}
	else {
%> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="14%" > 
              <div align="right"><b>No. Cliente :</b></div>
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
              <div align="right"><b>Contrato :</b></div>
            </td>
            <td nowrap width="9%"> 
              <div align="left"> 
                <input type="text" name="E02ACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="12%"> 
              <div align="right"><b>Oficial :</b></div>
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
  <h4>Información Básica de la Cuenta</h4>
  <table class="tableinfo">
    <tr > 
      <td > 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td  width="32%"  nowrap> 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td  width="28%" > 
              <div align="left"></div>
              <%= stBalances.getE02CUSNA1().trim()%></td>
            <td  width="25%" > 
              <div align="right"><b>Saldo Bruto :</b><b></b></div>
            </td>
            <td  width="15%"  nowrap> 
              <div align="right"><%= Util.fcolorCCY(stBalances.getE02DEAMEP().trim())%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td  width="32%"> 
              <div align="right"><b>Dirección :</b></div>
            </td>
            <td  width="28%" nowrap> 
              <div align="left"></div>
              <%= stBalances.getE02CUMMA1().trim()%></td>
            <td  width="25%" nowrap> 
              <div align="right"><b>Saldo de Interés :</b></div>
            </td>
            <td  width="15%"> 
              <div align="right"><%= Util.fcolorCCY(stBalances.getE02DEAMEI().trim())%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td  width="32%" nowrap> 
              <div align="right"><b> </b></div>
            </td>
            <td  width="28%"> 
              <div align="left"></div>
              <%= stBalances.getE02CUMMA2().trim()%></td>
            <td  width="25%" nowrap> 
              <div align="right"><b>Saldo Total :</b></div>
            </td>
            <td  width="15%"> 
              <div align="right"><%= Util.fcolorCCY(stBalances.getE02TOTAMN().trim())%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td  width="32%" nowrap> 
              <div align="right"></div>
            </td>
            <td  width="28%" nowrap> 
              <div align="left">
              <%= stBalances.getE02CUMMA3().trim()%>
		</div>
              <div align="left">
              <%= stBalances.getE02CUMMA4().trim()%>
		</div>
		</td>
            <td  width="25%" nowrap> 
              <div align="right"><b>Tasa de Interés :</b></div>
            </td>
            <td  width="15%"> 
              <div align="right"><%= stBalances.getE02DEARTE().trim()%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td  width="32%"> 
              <div align="right"><b>Fecha de Apertura :</b></div>
            </td>
            <td  width="28%" nowrap> 
              <div align="left">
              <%= Util.formatDate(stBalances.getE02OPEND1(),stBalances.getE02OPEND2(),stBalances.getE02OPEND3())%>
		</div>
	     </td>
            <td  width="25%" nowrap> 
              <div align="right"><b>Fecha de Vencimiento :</b></div>
            </td>
            <td  width="15%" nowrap> 
              <div align="right"> <%= Util.formatDate(stBalances.getE02MATUR1(),stBalances.getE02MATUR2(),stBalances.getE02MATUR3())%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td  width="32%"> 
              <div align="right"><b>Última Fecha de Renovación :</b></div>
            </td>
            <td  width="28%" nowrap> 
              <div align="left">
              <%= Util.formatDate(stBalances.getE02LSTRD1(),stBalances.getE02LSTRD2(),stBalances.getE02LSTRD3())%>
		</div>
            </td>
            <td  width="25%" nowrap> 
              <div align="right"><b>No. de Renovaciones :</b></div>
            </td>
            <td  width="15%" nowrap> 
              <div align="left"><%= stBalances.getE02DEARON().trim()%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td  width="32%"> 
              <div align="right"><b>Acumulación hasta :</b></div>
            </td>
            <td  width="28%" nowrap > 
              <div align="left"></div>
              <%= Util.formatDate(stBalances.getE02LSTCL1(),stBalances.getE02LSTCL2(),stBalances.getE02LSTCL3())%></td>
            <td  width="25%" nowrap > 
              <div align="right"><b>Correo-Electrónico :</b></div>
            </td>
            <td  width="15%" nowrap > 
              <div align="right"><a href="mailto:<%= stBalances.getE02CUSIAD().trim()%>" target="body"><%= stBalances.getE02CUSIAD().trim()%></a></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4 align="left">Transacciones</h4>
  <TABLE class="tableinfo">
    <TR id="trclear"> 
      <TH ALIGN=CENTER nowrap width="10%" >Fecha de Proceso</TH>
      <TH ALIGN=CENTER  nowrap width="11%" >Fecha Valor</TH>
      <TH ALIGN=CENTER  nowrap width="11%" >Fecha de Contabilización</TH>
      <TH ALIGN=CENTER  nowrap width="3%" >TC</TH>
      <TH ALIGN=CENTER  nowrap width="10%" >Descripción</TH>
      <TH ALIGN=CENTER  nowrap colspan="2" > Principal</TH>
      <TH ALIGN=CENTER  nowrap colspan="2" >Interés </TH>
      <TH ALIGN=CENTER nowrap width="5%" >Lote</TH>
      <TH ALIGN=CENTER nowrap width="5%" > Hora</TH>
      <TH ALIGN=CENTER nowrap width="5%" >Usuario</TH>
      <TH ALIGN=CENTER nowrap width="7%" >O/ Banco</TH>
      <TH ALIGN=CENTER nowrap width="9%" >O/ Agencia</TH>
      <TH ALIGN=CENTER nowrap width="8%" >Trans.ID</TH>
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
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.client.JSEDL0300?SCREEN=1&Pos=" + pos +"\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/e/previous_records.gif\" ></A>");
        }
 %>  
  </TD>
  <TD WIDTH="50%" ALIGN=RIGHT>     
 <%
        if ( cifList.getShowNext() ) {
      			int pos = cifList.getLastRec();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.client.JSEDL0300?SCREEN=1&Pos=" + pos +"\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/e/next_records.gif\" ></A>");
        }
%>
</TD>
</TR>
</TABLE>

 <br>
  <div align="center"> 
	   <input id="EIBSBTN" type=button name="Submit" value="Imprimir" OnClick="PrintPreview();">
  </div>

  <%
  }
%> 
  
</FORM>

</BODY>
</HTML>
