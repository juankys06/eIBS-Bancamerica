<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Estado de Cuentas de Cuentas Contables
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "glList" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id="stGLBal" class="datapro.eibs.beans.EGL042002Message"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<BODY>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

function PrintPreview() {

  <% 
  int iniPos = glList.getFirstRec() - 1;
  out.println("var pos = " + iniPos + ";");
  %>
	var pg = '<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSEGL0420?SCREEN=3&Pos=' + pos;
	CenterWindow(pg,720,500,2);

}

</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 

<FORM>
<%
	if ( glList.getNoResult() ) {
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

  <h3 align="center">Consulta de Transacciones de Cuentas Contables<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="st_list_fv.jsp,EGL0420"> 
  </h3>
  <hr size="4">

  <p>&nbsp;</p>
<table class="tableinfo">
    <tr > 
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark">
            <td nowrap width="30%" > 
              <div align="right"><b>Cuenta Contable : </b></div>
            </td>
            <td nowrap width="70%" > 
              <input type="text" name="E02GLMBNK" size="3" maxlength="2" value="<%= userPO.getBank().trim()%>" readonly>
              <input type="text" name="E02GLMCCY" size="4" readonly maxlength="3" value="<%= userPO.getCurrency().trim()%>" >
              <input type="text" name="E02GLMBRN" size="4" readonly maxlength="3" value="<%= userPO.getBranch().trim()%>" >
              <input type="text" name="E02GLMGLN" size="20" readonly maxlength="16" value="<%= userPO.getAccNum().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%" > 
              <div align="right">Descripci&oacute;n : </div>
            </td>
            <td nowrap width="70%" > 
              <div align="left"> 
                <input type="text" name="E02GLMDSC" size="40" readonly maxlength="35" value="<%= stGLBal.getE02GLMDSC().trim()%>" >
              </div>
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
            <td  width="31%" height="15" nowrap> 
              <div align="right"><b>Tipo de Cuenta : </b></div>
            </td>
            <td colspan="3" height="15"> 
              <div align="left">(<%= stGLBal.getE02GLMATY().trim()%>) <%= stGLBal.getE02DSCATY().trim()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td  width="31%"> 
              <div align="right"><b>Clase de Cuenta : </b></div>
            </td>
            <td  width="19%" nowrap> 
              <div align="left">(<%= stGLBal.getE02GLMCLS().trim()%>) <%= stGLBal.getE02DSCCLS().trim()%></div>
            </td>
            <td  width="28%" nowrap> 
              <div align="right"><b>Cuenta Reconciliable : </b></div>
            </td>
            <td  width="22%"> 
              <div align="left"><% if (stGLBal.getE02GLMRCL().equals("Y")) out.print("Si"); else out.print("No"); %></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td  width="31%" nowrap> 
              <div align="right"><b>Fecha de Apertura</b><b> : </b></div>
            </td>
            <td  width="19%"> 
              <div align="left"><%= Util.formatDate(stGLBal.getE02GLBOP1(), stGLBal.getE02GLBOP2(), stGLBal.getE02GLBOP3())%></div>
            </td>
            <td  width="28%" nowrap> 
              <div align="right"><b>Requiere Centro de Costo : </b></div>
            </td>
            <td  width="22%"> 
              <div align="left"><% if (stGLBal.getE02GLMCCN().equals("Y")) out.print("Si"); else out.print("No"); %></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td  width="31%" nowrap> 
              <div align="right"><b>Fecha de Ultima Transacci&oacute;n : </b></div>
            </td>
            <td  width="19%" nowrap> 
              <div align="left"><%= Util.formatDate(stGLBal.getE02GLBLU1(), stGLBal.getE02GLBLU2(), stGLBal.getE02GLBLU3())%> 
              </div>
            </td>
            <td  width="28%" nowrap> 
              <div align="right"><b>Promedio del Mes : </b></div>
            </td>
            <td  width="22%"> 
              <div align="right" nowrap><%= Util.fcolorCCY(stGLBal.getE02GLBAVG())%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td  width="31%"> 
              <div align="right"><b>Balance Actual : </b></div>
            </td>
            <td  width="19%" nowrap> 
              <div align="right"><%= Util.fcolorCCY(stGLBal.getE02GLBBAL())%></div>
            </td>
            <td  width="28%" nowrap> 
              <div align="right"><b> Saldo Inicial </b> <b>: </b></div>
            </td>
            <td  width="22%" nowrap> 
              <div align="right"><%= Util.fcolorCCY(userPO.getHeader5())%></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4 align="left">Transacciones</h4>
  <table id=cfTable class="tableinfo">
    <tr id="trdark"> 
      <th align=CENTER width="53" nowrap>Fecha Valor</th>
	  <TH ALIGN=CENTER width="53" nowrap>Fecha Registro</TH>
      <th align=CENTER width="57" nowrap>Referencia</th>
      <th align=CENTER width="25" nowrap>TC</th>
      <th align=CENTER width="230" nowrap>Descripci&oacute;n </th>
      <th align=CENTER width="36" nowrap> 
        <div align="right">D&eacute;bito</div>
      </th>
      <th align=CENTER width="46" nowrap> 
        <div align="center">Cr&eacute;dito</div>
      </th>
      <th align=CENTER width="57" nowrap> 
        <div align="center">Saldo</div>
      </th>
      <th align=CENTER width="49" nowrap>Lote</th>
      <th align=CENTER width="43" nowrap>Fecha Proceso </th>
      <th align=CENTER width="39" nowrap>Hora</th>
      <th align=CENTER width="50" nowrap>Usuario </th>
      <th align=CENTER width="47" nowrap> 
        <p>Ban Originador</p>
      </th>
      <th align=CENTER width="43" nowrap>Sucursal Originadora</th>
    </tr>
    <%
                glList.initRow();
                while (glList.getNextRow()) {
                    if (glList.getFlag().equals("")) {
                    		out.println(glList.getRecord());
                    }
                }
              %> 
  </table>
  <BR>
  
  <TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
       <%
        if ( glList.getShowPrev() ) {
      			int pos = glList.getFirstRec() - 51;
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.client.JSEGL0420?SCREEN=1&Pos=" + pos +"\"><img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
        %> 
            </TD>
 				<TD WIDTH="50%" ALIGN=RIGHT>
        <%
        if ( glList.getShowNext() ) {
      			int pos = glList.getLastRec();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.client.JSEGL0420?SCREEN=1&Pos=" + pos +"\"><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");
        }
        %> 
 </TD>
 </TR>
 </TABLE>      
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=button name="Submit" OnClick="PrintPreview()" value="Imprimir">
  </div>
</p>

  <%
  }
%>
<SCRIPT Language="Javascript">
   var max=cfTable.rows.length;
     for(i= 0; i < max; i++){
      cfTable.rows[i].cells[1].style.display="none";
     }
  </SCRIPT> 
</FORM>

</BODY>
</HTML>
