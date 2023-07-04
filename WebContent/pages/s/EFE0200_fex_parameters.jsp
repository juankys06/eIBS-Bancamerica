<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Parametros</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="fix" class="datapro.eibs.beans.EFE020001Message"  scope="session" />

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
<h3 align="center">Parametros de Control de Moneda Extranjera<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fex_parameters, EFE0200"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEFE0200" >
  <input type=HIDDEN name="SCREEN" value="400">
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
                <input type="text" name="E01FEPBNK" size="3" maxlength="2" readonly value="<%= fix.getE01FEPBNK()%>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n B&aacute;sica</h4>
  <table class="tableinfo" width="100%" >
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark"> 
            <td nowrap width="46%" > 
              <div align="right">N&uacute;mero de D&iacute;as Spot :</div>
            </td>
            <td nowrap width="54%" > 
              <div align="left"> 
                <input type="text" name="E01FEPNDS" size="2" maxlength="1" value="<%= fix.getE01FEPNDS()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="46%" > 
              <div align="right">N&uacute;mero de D&iacute;as para Transferir 
                hacia Spot :</div>
            </td>
            <td nowrap width="54%" > 
              <input type="text" name="E01FEPNTS" size="2" maxlength="1" value="<%= fix.getE01FEPNTS()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="46%" > 
              <div align="right">Reevaluacion de Spot :</div>
            </td>
            <td nowrap width="54%" > 
              <div align="left"> 
                <input type="radio" name="E01FEPRSP" value="Y"  <%if (fix.getE01FEPRSP().equals("Y")) out.print("checked"); %>>
                S&iacute; 
                <input type="radio" name="E01FEPRSP" value="N"  <%if (fix.getE01FEPRSP().equals("N")) out.print("checked"); %>>
                No</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="46%" > 
              <div align="right">Cargos Automaticos:</div>
            </td>
            <td nowrap width="54%" > 
              <div align="left"> 
                <input type="radio" name="E01FEPCHG" value="Y"  <%if (fix.getE01FEPCHG().equals("Y")) out.print("checked"); %>>
                S&iacute; 
                <input type="radio" name="E01FEPCHG" value="N"  <%if (fix.getE01FEPCHG().equals("N")) out.print("checked"); %>>
                No</div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="46%" > 
              <div align="right">Generaci&oacute;n Automatica de N&uacute;mero 
                :</div>
            </td>
            <td nowrap width="54%" > 
              <div align="left"> 
                <input type="radio" name="E01FEPANG" value="Y"  <%if (fix.getE01FEPANG().equals("Y")) out.print("checked"); %>>
                S&iacute; 
                <input type="radio" name="E01FEPANG" value="N"  <%if (fix.getE01FEPANG().equals("N")) out.print("checked"); %>>
                No </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="46%" > 
              <div align="right">Actualizar Posici&oacute;n en la Fecha de la 
                Transacci&oacute;n :</div>
            </td>
            <td nowrap width="54%" > 
              <div align="left"> 
                <input type="radio" name="E01FEPRVT" value="1"  <%if (fix.getE01FEPRVT().equals("1")) out.print("checked"); %>>
                Deal Date 
                <input type="radio" name="E01FEPRVT" value="2"  <%if (fix.getE01FEPRVT().equals("2")) out.print("checked"); %>>
                Settlement Date</div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="46%" > 
              <div align="right">Posici&oacute;n de Contravalor Puente :</div>
            </td>
            <td nowrap width="54%" > 
              <div align="left"> 
                <input type="text" name="E01FEPEGL" size="17" maxlength="16" value="<%= fix.getE01FEPEGL()%>" >
                <a href="javascript:GetLedger('E01FEPEGL',document.forms[0].E01FEPBNK.value,'','')"> 
                <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>                
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="46%" > 
              <div align="right">L&iacute;mites Diarios :</div>
            </td>
            <td nowrap width="54%" > 
              <div align="left"> 
                <input type="text" name="E01FEPDLM" size="17" maxlength="15" value="<%= fix.getE01FEPDLM()%>" >
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
