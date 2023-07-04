<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Lista de Clientes
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "cifList" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id="stBalances" class="datapro.eibs.beans.EPR203002Message"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<BODY>

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 


<h3 align="center">Consulta Transacciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="st_list_fv.jsp,EPR2030"> 
  </h3>
  <hr size="4">
 <FORM> 
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
            <td nowrap ><b><input type="text" name="E02PRO2" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
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
              <div align="right"><b>Oficial :</b></div>
            </td>
            <td nowrap width="33%"> 
              <div align="left"><input type="text" name="E02NA122" size="30" maxlength="30" readonly value="<%= userPO.getOfficer().trim()%>">
              </div>
            </td>
            <td nowrap width="11%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="21%"> 
              <div align="left"><b><input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
<%
	if ( cifList.getNoResult() ) {
%>
 		<TABLE class="tbenter" width=100% height=100%>
 		<TR>
      <TD> 
        
      <div align="center"> <font size="3"><b> No hay datos que correspondan con su criterio de busqueda</b></font> 
      </div>
      </TD></TR>
   		</TABLE>
<%
	}
	else {
%>
  
  
  <h4>Datos B&aacute;sicos de la Cuenta</h4>
  <table class="tableinfo">
    <tr> 
      <td > 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td  width="21%" height="15" nowrap> 
              <div align="right"><b>Promedio en Libros :  </b></div>
            </td>
            <td  width="30%" height="15"> 
              <div align="right"><%= Util.fcolorCCY(stBalances.getE02ACMNAV().trim())%></div>
            </td>
            <td  width="1%" height="15">&nbsp;</td>
            <td  width="32%" height="15"> 
              <div align="right"> <b>Saldo</b> <%= stBalances.getE02ACMCCY().trim()%> 
                <b>en Libros :</b></div>
            </td>
            <td  width="16%" height="15" nowrap> 
              <div align="right"><%= Util.fcolorCCY(stBalances.getE02ACMMGB().trim())%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td  width="21%"> 
              <div align="right"><b>Promedio Neto : </b></div>
            </td>
            <td  width="30%" nowrap> 
             <div align="right"><%= Util.fcolorCCY(stBalances.getE02ACMGAV().trim())%></div>
            </td>
            <td  width="1%">&nbsp;</td>
            <td  width="32%" nowrap> 
              <div align="right"><b>Monto Diferido : </b></div>
            </td>
            <td  width="16%"> 
              <div align="right"><%= Util.fcolorCCY(stBalances.getE02UNCBAL().trim())%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td  width="21%" nowrap> 
              <div align="right"><b>Fax : </b></div>
            </td>
            <td  width="30%"> 
              <div align="right"><%= stBalances.getE02CUSFAX().trim()%></div>
            </td>
            <td  width="1%">&nbsp;</td>
            <td  width="32%" nowrap> 
              <div align="right"><b>Monto Retenido : </b></div>
            </td>
            <td  width="16%"> 
              <div align="right"><%= Util.fcolorCCY(stBalances.getE02ACMHAM().trim())%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td  width="21%" nowrap> 
              <div align="right"><b>Correo Electr&oacute;nico : </b></div>
            </td>
            <td  width="30%" nowrap> 
             <div align="right"><a href="mailto:<%= stBalances.getE02CUSIAD().trim()%>" target="body"><%= stBalances.getE02CUSIAD().trim()%></a></div>             
            </td>
            <td  width="1%">&nbsp;</td>
            <td  width="32%" nowrap> 
              <div align="right"><b>Saldo Disponible : </b></div>
            </td>
            <td  width="16%"> 
              <div align="right"><%= Util.fcolorCCY(stBalances.getE02AVALBL().trim())%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td  width="21%"> 
              <div align="right"><b></b></div>
            </td>
            <td  width="30%" nowrap> 
            </td>
            <td  width="1%">&nbsp;</td>
            <td  width="32%" nowrap> 
              <div align="right"><b> Saldo al </b><%= Util.formatDate(stBalances.getE02LSSTM1().trim(),stBalances.getE02LSSTM2().trim(), stBalances.getE02LSSTM3().trim())%> 
                <b>: </b></div>
            </td>
            <td  width="16%" nowrap> 
              <div align="right"><%= Util.fcolorCCY(userPO.getHeader5().trim())%></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4 align="left">Transacciones</h4>
  <table id=cfTable class="tableinfo">
    <tr id=trdark> 
      <th align=CENTER width="5%" nowrap>Fecha Valor</th>
      <th align=CENTER style="display:none" nowrap>Fecha de <BR> Registro</th>
      <th align=CENTER width="10%" nowrap>Referencia</th>
      <th align=CENTER width="2%" nowrap>TC</th>
      <th align=CENTER width="50%" nowrap>Descripci&oacute;n </th>
      <th align=CENTER width="20%" nowrap>Cr&eacute;dito</th>
      <th align=CENTER width="8%" nowrap>Lote</th>
      <th align=CENTER width="5%" nowrap>Fecha Proceso </th>
      
    </tr>
    <%
                cifList.initRow();
                while (cifList.getNextRow()) {
                    if (cifList.getFlag().equals("")) {
                    		out.println(cifList.getRecord());
                    }
                }
              %> 
  </table>
  <BR>
  
       <TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
       <TR>
       <TD WIDTH="50%" ALIGN=LEFT>
        <%
        if ( cifList.getShowPrev() ) {
      			int pos = cifList.getFirstRec() - 51;
      			out.println("<A HREF=\"" + request.getContextPath()+ "/servlet/datapro.eibs.client.JSECIF030?SCREEN=1&Pos=" + pos +"\"><img src=\"" + request.getContextPath()+ "/images/s/previous_records.gif\" border=0></A>");
        }
        %>
        </TD>
 		  <TD WIDTH="50%" ALIGN=RIGHT>
 		   <%
         if ( cifList.getShowNext() ) {
      			int pos = cifList.getLastRec();
      			out.println("<A HREF=\"" + request.getContextPath()+ "/servlet/datapro.eibs.client.JSECIF030?SCREEN=1&Pos=" + pos +"\"><img src=\"" + request.getContextPath()+ "/images/s/next_records.gif\" border=0></A>");
        }
         %>
         </TD>
 			</TR>
			</TABLE> 
  <p align="center"> 
      <input id="EIBSBTN" type=button name="Submit" OnClick="checkClose()" value="Cerrar">
  </p>
  <SCRIPT Language="Javascript">
   var max=cfTable.rows.length;
     for(i= 1; i < max; i++){
      cfTable.rows[i].cells[1].style.display="none";
     }
  </SCRIPT>
   <%
  }
%> 
</FORM>

</BODY>
</HTML>
