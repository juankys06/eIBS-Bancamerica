<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Proteccion de Sobregiros</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="rtBasic" class="datapro.eibs.beans.EDD043801Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>



<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
   

%> 
<H3 align="center">Protecci&oacute;n de Sobregiros<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="EDD0000_rt_basic.jsp, EDD0000"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0438" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="2">
  
  
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Banco :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01OVCBNK" size="3" maxlength="2" value="<%= rtBasic.getE01OVCBNK()%>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>Datos B&aacute;sicos de la Operaci&oacute;n</H4>
<table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right">Monto M&aacute;ximo :</div>
            </td>
            <td nowrap width="19%"> 
              <input type="text" name="E01OVCMAX" size="15" maxlength="15" value="<%= rtBasic.getE01OVCMAX()%>" onKeyPress="enterDecimal()">
            </td>
            <td nowrap width="26%"> 
              <div align="right">Cuenta Contable :</div>
            </td>
            <td nowrap width="26%"> 
              <input type="text" name="E01OVCCGL" size="19" maxlength="16" value="<%= rtBasic.getE01OVCCGL()%>" onKeyPress="enterInteger()">
              <a href="javascript:GetLedger('E01OVCCGL',document.forms[0].E01OVCBNK.value,'','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Monto M&iacute;nimo :</div>
            </td>
            <td nowrap width="19%"> 
              <input type="text" name="E01OVCMIN" size="15" maxlength="15" value="<%= rtBasic.getE01OVCMIN()%>" onKeyPress="enterDecimal()">
            </td>
            <td nowrap width="26%"> 
              <div align="right">Comisi&oacute;n :</div>
            </td>
            <td nowrap width="26%"> 
              <input type="text" name="E01OVCCOM" size="15" maxlength="15" value="<%= rtBasic.getE01OVCCOM()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="23"> 
              <div align="right">% Inversiones :</div>
            </td>
            <td nowrap width="19%" height="23"> 
              <input type="text" name="E01OVCINV" size="15" maxlength="15" value="<%= rtBasic.getE01OVCINV()%>" >
            </td>
            <td nowrap width="26%" height="23"> 
              <div align="right">% Saldo Promedio :</div>
            </td>
            <td nowrap width="26%" height="23"> 
              <input type="text" name="E01OVCAVG" size="15" maxlength="15" value="<%= rtBasic.getE01OVCAVG()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="23"> 
              <div align="right">Plazo M&iacute;nimo para Inversiones :</div>
            </td>
            <td nowrap width="19%" height="23"> 
              <input type="text" name="E01OVCTRM" size="4" maxlength="3" value="<%= rtBasic.getE01OVCTRM()%>" >
            </td>
            <td nowrap width="26%" height="23"> 
              <div align="right">Duraci&oacute;n de L&iacute;nea de Cr&eacute;dito 
                :</div>
            </td>
            <td nowrap width="26%" height="23"> 
              <input type="text" name="E01OVCLIN" size="4" maxlength="3" value="<%= rtBasic.getE01OVCLIN()%>" >
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="29%" height="23">
              <div align="right">D&iacute;as para tomar Inversiones :</div>
            </td>
            <td nowrap width="19%" height="23">
              <input type="text" name="E01OVCDAY" size="4" maxlength="3" value="<%= rtBasic.getE01OVCDAY()%>" >
            </td>
            <td nowrap width="26%" height="23">
              <div align="right">N&uacute;mero de Veces de Utilizaci&oacute;n 
                :</div>
            </td>
            <td nowrap width="26%" height="23">
              <input type="text" name="E01OVCNUM" size="4" maxlength="3" value="<%= rtBasic.getE01OVCNUM()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="23"> 
              <div align="right">Narrativa : </div>
            </td>
            <td nowrap colspan="3" height="23"> 
              <input type="text" name="E01OVCNAR" size="35" maxlength="35" value="<%= rtBasic.getE01OVCNAR()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>
    <SCRIPT language="javascript">
    function tableresize() {
     adjustEquTables(headTable,dataTable,dataDiv,0,true);
   }
  tableresize();
  window.onresize=tableresize;
  </SCRIPT>
    <br>
  </H4>
  <div align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </div>
  </form>
</body>
</html>
