<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Distribución de Dividendos (FMH)</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="div" class="datapro.eibs.beans.EDD400301Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

</head>
<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%>
<h3 align="center">Distribución de Dividendos (FMH)<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="div_parameters_prc.jsp, EDD4003"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDD4003" >
  <input type=HIDDEN name="SCREEN" value="700">

  <h4>Informaci&oacute;n B&aacute;sica</h4>
  <table class="tableinfo" width="100%" >
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trclear"> 
            <td nowrap width="15%" >
              <div align="right">Banco :</div>
            </td>
            <td nowrap colspan="3" width="85%" > 
              <div align="left"> 
                <input type="text" name="E01DIVBNK" size="3" maxlength="2" readonly value="<%= div.getE01DIVBNK()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Contabiliza en Otra Agencia :</div>
            </td>
            <td nowrap width="22%" height="19"><font face="Arial" size="2"> 
              <input type="text" name="E01DIVCDO" size="2" value="<% if(div.getE01DIVCDO().equals("Y")) out.print("Si");
              				else if(div.getE01DIVCDO().equals("N")) out.print("No");
							else out.print("");%>" readonly>
              </font> </td>
		  </tr>

          <tr id="trclear"> 
            <td nowrap width="28%" > 
              <div align="right">Agencia a Contabilizar :</div>
            </td>
            <td nowrap width="44%" >
              <input type="text" readonly name="E01DIVCBR" size="4" maxlength="3" onKeyPress="enterInteger()" value="<%= div.getE01DIVCBR()%>">
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Contabiliza en Línea :</div>
            </td>
            <td nowrap width="22%" height="19"><font face="Arial" size="2"> 
              <input type="text" name="E01DIVGIN" size="2" value="<% if(div.getE01DIVGIN().equals("Y")) out.print("Si");
              				else if(div.getE01DIVGIN().equals("N")) out.print("No");
							else out.print("");%>" readonly>
              </font> </td>
		  </tr>

          <tr id="trclear"> 
            <td nowrap width="28%" > 
              <div align="right">Porcentaje de Calculo Dividendos :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" readonly name="E01DIVRTI" size="16" maxlength="11" value="<%= div.getE01DIVRTI()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%" > 
              <div align="right">Valor a Distribuír :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" readonly name="E01DIVRTA" size="16" maxlength="15" value="<%= div.getE01DIVRTA()%>"  onKeypress="enterSignDecimal()">
              </div>
            </td>
          </tr>

          <tr id="trclear"> 
            <td nowrap width="28%" > 
              <div align="right">Fecha de Distribución :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" readonly name="E01DIVDC1" size="3" maxlength="2" value="<%= div.getE01DIVDC1()%>" >
                <input type="text" readonly name="E01DIVDC2" size="3" maxlength="2" value="<%= div.getE01DIVDC2()%>" >
                <input type="text" readonly name="E01DIVDC3" size="3" maxlength="2" value="<%= div.getE01DIVDC3()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="28%" > 
              <div align="right">Usuario Ultimo Proceso :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" readonly name="E01DIVUSR" size="13" maxlength="11" value="<%= div.getE01DIVUSR()%>" >
              </div>
            </td>
          </tr>

          <tr id="trclear"> 
            <td nowrap width="28%" > 
              <div align="right">Fecha de Distribución :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" readonly name="E01DIVLC1" size="3" maxlength="2" value="<%= div.getE01DIVLC1()%>" >
                <input type="text" readonly name="E01DIVLC2" size="3" maxlength="2" value="<%= div.getE01DIVLC2()%>" >
                <input type="text" readonly name="E01DIVLC3" size="3" maxlength="2" value="<%= div.getE01DIVLC3()%>" >
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>


  <p>&nbsp;</p><table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%">
 <div align="center"> 
	      <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>      </td>
    </tr>
  </table>
  </form>
</body>
</html>
