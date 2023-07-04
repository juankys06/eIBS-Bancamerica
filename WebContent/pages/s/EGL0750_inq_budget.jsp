<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Rentabilidad por Clientes</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="budget" class="datapro.eibs.beans.EGL075002Message" scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


</head>

<body>

<%@ page import = "datapro.eibs.master.Util" %>

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

%>

<h3 align="center">Presupuestos G/L  <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="inq_budget.jsp,EGL0750"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.accounting.JSEGL0750" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="3">
  <input type=HIDDEN name="SELMTH">
  <h4>Informaci&oacute;n de la Cuenta Contable</h4>
 <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap>
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%" height="31"> 
              <div align="right">Cuenta Contable :</div>
            </td>
            <td nowrap width="25%" height="30"> 
              <input type="text" readonly name="BANCO"  size="3" maxlength="2" value="<%= budget.getE02GLBBNK().trim()%>">
              <input type="text" readonly name="SUCURSAL"  size="4" maxlength="3" value="<%= budget.getE02GLBBRN().trim()%>">
              <input type="text" readonly name="MONEDA"  size="4" maxlength="3" value="<%= budget.getE02GLBCCY().trim()%>">
              <input type="text" readonly name="CUENTA"  size="17" maxlength="16" value="<%= budget.getE02GLBGLN().trim()%>">
            </td>
            <td nowrap width="25%" height="31"> 
              <div align="right">Descripcion :</div>
            </td>
            <td nowrap width="25%" height="31"> 
              <input type="text" readonly name="E02GLBDSC"  size="35" maxlength="45" value="<%= budget.getE02GLBDSC().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4></h4>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap height="36"> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
         <tr id="trclear"> 
            <td nowrap height="25" bordercolor="#000000"> 
              <div align="center"><b></b></div>
            </td>
            <td nowrap height="25" height="50" bordercolor="#000000"> 
              <div align="center"><b>Saldo Contable</b></div>
            </td>
            <td nowrap height="25" bordercolor="#000000"> 
              <div align="center"><b></b></div>
            </td>
            <td nowrap height="25" height="50" bordercolor="#000000"> 
              <div align="right"><b>Presupuesto</b></div>
            </td>
                       
          </tr>
          <tr id="trdark"> 
            <td nowrap height="20" bordercolor="#000000"> 
              <div align="center"><b></b></div>
            </td>
            <td nowrap height="20" bordercolor="#000000"> 
              <div align="center"><b>Ciclo</b></div>
            </td>
            <td nowrap height="20" bordercolor="#000000"> 
              <div align="center"><b></b></div>
            </td>
            <td nowrap height="20" bordercolor="#000000"> 
              <div align="center"><b>Ciclo</b></div>
            </td>
            <td nowrap height="20" bordercolor="#000000"> 
              <div align="center"><b></b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" bordercolor="#000000" height="31"> 
              <div align="right"><b>Incio de budget :</b></div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLBINI" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLBINI())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>   
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLPINI" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLPINI())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%" bordercolor="#000000"> 
              <div align="right">Enero :</div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLBENE" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLBENE())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLPENE" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLPENE())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" bordercolor="#000000" height="35"> 
              <div align="right">Febrero : </div>
            </td>
            <td nowrap width="20%" height="35" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLBFEB" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLBFEB())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="35" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="35" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLPFEB" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLPFEB())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="35" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%" bordercolor="#000000"> 
              <div align="right">Marzo : </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLBMAR" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLBMAR())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLPMAR" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLPMAR())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" bordercolor="#000000" height="31"> 
              <div align="right"><b>Primer Trimestre :</b></div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLBQT1" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLBQT1())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLPQT1" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLPQT1())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
          </tr>
          
          <tr id="trdark"> 
            <td nowrap width="20%" bordercolor="#000000"> 
              <div align="right">Abril :</div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLBABR" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLBABR())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLPABR" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLPABR())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" bordercolor="#000000"> 
              <div align="right">Mayo :</div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLBMAY" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLBMAY())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLPMAY" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLPMAY())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%" bordercolor="#000000"> 
              <div align="right">Junio : </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLBJUN" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLBJUN())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLPJUN" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLPJUN())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" bordercolor="#000000" height="31"> 
              <div align="right"><b>Segundo Trimestre :</b></div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLBQT2" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLBQT2())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLPQT2" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLPQT2())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
          </tr>
          
          <tr id="trdark"> 
            <td nowrap width="20%" bordercolor="#000000"> 
              <div align="right">Julio : </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLBJUL" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLBJUL())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLPJUL" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLPJUL())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" bordercolor="#000000"> 
              <div align="right">Agosto : </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLBAGO" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLBAGO())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLPAGO" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLPAGO())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%" bordercolor="#000000"> 
              <div align="right">Setiembre : </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLBSET" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLBSET())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLPSET" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLPSET())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" bordercolor="#000000" height="31"> 
              <div align="right"><b>Tercer Trimestre :</b></div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLBQT3" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLBQT3())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLPQT3" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLPQT3())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
          </tr>
          
          <tr id="trdark"> 
            <td nowrap width="20%" bordercolor="#000000"> 
              <div align="right">Octubre : </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLBOCT" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLBOCT())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLPOCT" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLPOCT())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" bordercolor="#000000"> 
              <div align="right">Noviembre : </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLBNOV" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLBNOV())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLPNOV" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLPNOV())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%" bordercolor="#000000"> 
              <div align="right">Diciembre : </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLBDIC" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLBDIC())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLPDIC" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLPDIC())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" bordercolor="#000000" height="31"> 
              <div align="right"><b>Cuarto Trimestre :</b></div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLBQT4" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLBQT4())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLPQT4" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLPQT4())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
          </tr>
          
          <tr id="trdark"> 
            <td nowrap width="20%" bordercolor="#000000"> 
              <div align="right"><b>Total del Ano : </b></div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLBFIN" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLBFIN())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLPFIN" size="17" maxlength="15" value="<%= Util.formatCCY(budget.getE02GLPFIN())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="5" bordercolor="#000000"> 
              <div align="left"></div>
            </td>
          </tr>
         
          <tr id="trclear"> 
            <td nowrap colspan="5" bordercolor="#000000"> 
              <div align="left"></div>
            </td>   
          </tr>
          
        </table>
        
      </td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"> </div>
      </td>
      <td width="33%">
        <div align="center">
        </div>
      </td>
    </tr>
    <tr bgcolor="#FFFFFF"> 
      <td colspan="2"> 
        <div align="center"> </div>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>
