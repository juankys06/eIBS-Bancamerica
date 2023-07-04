<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Rentabilidad por Clientes</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="average" class="datapro.eibs.beans.EGL075002Message" scope="session" />

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

<h3 align="center">Saldos Promedios G/L  <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="inq_promedios.jsp,EGL0750"></h3>
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
              <input type="text" readonly name="BANCO"  size="3" maxlength="2" value="<%= average.getE02GLBBNK().trim()%>">
              <input type="text" readonly name="SUCURSAL"  size="4" maxlength="3" value="<%= average.getE02GLBBRN().trim()%>">
              <input type="text" readonly name="MONEDA"  size="4" maxlength="3" value="<%= average.getE02GLBCCY().trim()%>">
              <input type="text" readonly name="CUENTA"  size="17" maxlength="16" value="<%= average.getE02GLBGLN().trim()%>">
            </td>
            <td nowrap width="25%" height="31"> 
              <div align="right">Descripcion :</div>
            </td>
            <td nowrap width="25%" height="31"> 
              <input type="text" readonly name="E02GLBDSC"  size="35" maxlength="45" value="<%= average.getE02GLBDSC().trim()%>">
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
              <div align="center"><b>Año Actual</b></div>
            </td>
            <td nowrap height="25" bordercolor="#000000"> 
              <div align="center"><b></b></div>
            </td>
            <td nowrap height="25" height="50" bordercolor="#000000"> 
              <div align="right"><b>Año Anterior</b></div>
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
              <div align="right"><b>Incio de average :</b></div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLAINI" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLAINI())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLYINI" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLYINI())%>" >
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
                <input type="text" id="txtright" readonly name="E02GLAENE" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLAENE())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLYENE" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLYENE())%>" >
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
                <input type="text" id="txtright" readonly name="E02GLAFEB" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLAFEB())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="35" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="35" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLYFEB" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLYFEB())%>" >
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
                <input type="text" id="txtright" readonly name="E02GLBMAR" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLBMAR())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLYMAR" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLYMAR())%>" >
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
                <input type="text" id="txtright" readonly name="E02GLAQT1" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLAQT1())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLYQT1" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLYQT1())%>" >
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
                <input type="text" id="txtright" readonly name="E02GLAABR" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLAABR())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLYABR" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLYABR())%>" >
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
                <input type="text" id="txtright" readonly name="E02GLAMAY" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLAMAY())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLYMAY" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLYMAY())%>" >
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
                <input type="text" id="txtright" readonly name="E02GLAJUN" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLAJUN())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLYJUN" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLYJUN())%>" >
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
                <input type="text" id="txtright" readonly name="E02GLAQT2" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLAQT2())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLYQT2" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLYQT2())%>" >
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
                <input type="text" id="txtright" readonly name="E02GLAJUL" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLAJUL())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLYJUL" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLYJUL())%>" >
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
                <input type="text" id="txtright" readonly name="E02GLAAGO" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLAAGO())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLYAGO" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLYAGO())%>" >
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
                <input type="text" id="txtright" readonly name="E02GLASET" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLASET())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLYSET" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLYSET())%>" >
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
                <input type="text" id="txtright" readonly name="E02GLAQT3" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLAQT3())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLYQT3" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLYQT3())%>" >
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
                <input type="text" id="txtright" readonly name="E02GLAOCT" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLAOCT())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLYOCT" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLYOCT())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="28%" bordercolor="#000000"> 
              <div align="right">Noviembre : </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLANOV" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLANOV())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLYNOV" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLYNOV())%>" >
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
                <input type="text" id="txtright" readonly name="E02GLADIC" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLADIC())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLYDIC" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLYDIC())%>" >
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
                <input type="text" id="txtright" readonly name="E02GLAQT4" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLAQT4())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLYQT4" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLYQT4())%>" >
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
                <input type="text" id="txtright" readonly name="E02GLAFIN" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLAFIN())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLYFIN" size="17" maxlength="15" value="<%= Util.formatCCY(average.getE02GLYFIN())%>" >
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
