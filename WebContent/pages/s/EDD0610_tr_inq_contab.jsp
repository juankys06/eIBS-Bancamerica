<html>
<head>
<title>Contabilización</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="inqContab" class="datapro.eibs.beans.EDD061005Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

<%
if ( userPO.getHeader21().equals("S") ) {
%>
	builtNewMenu(tranS_i_opt);
<%   
}
else if ( userPO.getHeader21().equals("F") ) {
%>
	builtNewMenu(tranF_i_opt);
<%   
}
else if ( userPO.getHeader21().equals("R") ) {
%>
	builtNewMenu(tranT_i_opt);
<%   
}
%> 
</SCRIPT>

</head>

<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
  
    out.println("<SCRIPT> initMenu(); </SCRIPT>");

%>

<h3 align="center">Contabilización<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="EDD0610_tr_inq_contab.jsp"></h3>
<hr size="4" width="100%">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0160" >
  <p>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  </p>
  <p><b>Contabilizaci&oacute;n </b></p>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark"> 
            <td nowrap width="8%" > 
              <div align="right">Banco:</div>
            </td>
            <td nowrap width="21%" > 
              <div align="left"> 
                <input type="text" readonly name="E05WRTBNK" size="1" maxlength="2" value="<%= inqContab.getE05WRTBNK().trim()%>" >
              </div>
            </td>
            <td nowrap width="10%" > 
              <div align="right"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2">Nuestra 
                Ref :</font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
                </font></font></font><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" readonly name="E05WRTORF" size="35" maxlength="35" value="<%= inqContab.getE05WRTORF().trim()%>" >
                </font></font></font></font></font></font></font></font></font></font></font></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="8%"> 
              <div align="right"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2">Lote:</font></font></font></font></font></font></font></font></font></font></font></font></div>
            </td>
            <td nowrap width="21%"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2">
              <input type="text" readonly name="E05WRTBTH" size="2" maxlength="3" value="<%= inqContab.getE05WRTBTH().trim()%>" >
              </font></font></font></font></font></font></font></font></font> 
            </td>
            <td nowrap width="10%"> 
              <div align="right"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2">Por 
                Orden:</font></font></font></font></font></font></font></font></font></font></font></font></font></div>
            </td>
            <td nowrap colspan="3"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"><font face="Arial"><font face="Arial"><font size="2"> 
              </font></font></font></font></font></font></font></font></font><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
              <input type="text" readonly name="E05WRTBYO" size="35" maxlength="30" value="<%= inqContab.getE05WRTBYO().trim()%>" >
              </font></font></font></font></font><font size="2"> </font></font></font><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"></font></font></font></font><font size="2"> 
              </font></font></font></font></font><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
              </font></font></font></font></font></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="8%"> 
              <div align="right">No.Transf :</div>
            </td>
            <td nowrap width="21%">
              <input type="text" readonly size="20" maxlength="7" name="E05SCHNUM" value="<%= inqContab.getE05SCHNUM().trim()%>">
            </td>
            <td nowrap width="10%"> 
              <div align="right"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2">Fecha 
                Valor:</font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></div>
            </td>
            <td nowrap width="10%"><font face="Arial"><font face="Arial"><font size="2"> 
              </font><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
              </font></font></font></font></font><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"><font face="Arial"><font face="Arial"><font size="2">
              <input type="text" readonly size="1" maxlength="2" name="E05INSDT1" value="<%= inqContab.getE05INSDT1().trim()%>" >
              <input type="text" readonly size="1" maxlength="2" name="E05INSDT2" value="<%= inqContab.getE05INSDT2().trim()%>" >
              <input type="text" readonly size="1" maxlength="2" name="E05INSDT3" value="<%= inqContab.getE05INSDT3().trim()%>" >
              </font></font></font></font></font></font></font></font></font></font></font><font size="2"></font></font></font></font></font><font size="2"> 
              </font></font></font></font></font> </td>
            <td nowrap width="11%">
              <div align="right"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
                </font></font></font> <font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2">Tasa 
                Cr&eacute;dito:</font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></div>
            </td>
            <td nowrap width="40%"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
              </font><font face="Arial"><font face="Arial"><font size="2"> </font><font face="Arial"><font face="Arial"><font size="2"> 
              </font></font></font></font></font><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
              </font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2">
              <input type="text" readonly name="E05WRTCRT" size="11" maxlength="11" value="<%= inqContab.getE05WRTCRT().trim()%>" >
              </font></font></font></font></font></font></font></font></font><font size="2"> 
              </font></font></font></font></font> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="8%"> 
              <div align="right">Referencia:</div>
            </td>
            <td nowrap width="21%">
              <input type="text" readonly name="E05WRTDS1" size="32" maxlength="35" value="<%= inqContab.getE05WRTDS1().trim()%>" >
            </td>
            <td nowrap width="10%"> 
              <div align="right"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2">Neg. 
                Futuro</font><font face="Arial"><font face="Arial"><font size="2">:</font></font></font></font></font></font></font></font></font></font></div>
            </td>
            <td nowrap width="10%"><font face="Arial"><font face="Arial"><font face="Arial"> 
              </font><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"><font face="Arial"><font face="Arial"><font size="2"> 
              </font></font></font></font></font></font></font></font></font></font></font><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2">
              <input type="text" readonly size="3" maxlength="3" name="E05APVFUT" value="<%= inqContab.getE05APVFUT().trim()%>" >
              </font></font></font></font></font></font></font><font size="2"> 
              </font></font></font></font></font></td>
            <td nowrap width="11%"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2">Tasa 
              Relaci&oacute;n:</font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></td>
            <td nowrap width="40%"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
              </font><font size="2"> </font></font></font></font></font><font face="Arial"><font face="Arial"><font size="2"> 
              </font></font></font><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
              </font><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
              </font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
              </font></font></font></font></font><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
              </font></font></font></font></font></font></font><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2">
              <input type="text" readonly name="E05WRTDIR" size="11" maxlength="11" value="<%= inqContab.getE05WRTDIR().trim()%>" >
              </font></font></font></font></font></font></font></font></font><font size="2"> 
              </font></font></font></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="8%">&nbsp;</td>
            <td nowrap width="21%">
              <input type="text" readonly name="E05WRTDS2" size="32" maxlength="35" value="<%= inqContab.getE05WRTDS2().trim()%>" >
            </td>
            <td nowrap width="10%">
              <div align="right"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2">Generar 
                Giro:</font></font></font></font></font></font></font></font></font></font></font></font></div>
            </td>
            <td nowrap width="10%"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
              </font></font></font></font></font><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2">
              <input type="text" readonly size="3" maxlength="3" name="E05GENDRF" value="<%= inqContab.getE05GENDRF().trim()%>" >
              </font></font></font></font></font></font></font><font size="2"> 
              </font></font></font></td>
            <td nowrap width="11%">
              <div align="right"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial">Tasa 
                D&eacute;bito:</font></font></font></font></font></font></font></font></font></font></div>
            </td>
            <td nowrap width="40%"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
              </font></font></font></font></font><font size="2">
              <input type="text" readonly name="E05WRTDRT" size="11" maxlength="11" value="<%= inqContab.getE05WRTDRT().trim()%>" >
              </font></font></font></font></font> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="8%"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"></font></font></font></font></font></font></td>
            <td nowrap width="21%"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2">
              <input type="text" readonly name="E05WRTDS3" size="32" maxlength="35" value="<%= inqContab.getE05WRTDS3().trim()%>" >
              </font></font></font></font></font></td>
            <td nowrap width="10%">
              <div align="right"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"></font></font></font><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2">Pa&iacute;s/Usuario:</font></font></font></font><font size="2"> 
                </font></font></font></font></font></font></font></font></font></font><font size="2"> 
                </font></font></font></font></font></font></font></div>
            </td>
            <td nowrap width="10%"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2">
              <input type="text" readonly name="E05WRTBNC" size="4" maxlength="4" value="<%= inqContab.getE05WRTBNC().trim()%>" >
              </font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font></font><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2">/</font><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
              </font></font></font></font></font></font></font></font></font></font></font></font></font></font></font><font size="2"> 
              <input type="text" readonly name="E05WRTUS1" size="4" maxlength="4" value="<%= inqContab.getE05WRTUS1().trim()%>" >
              </font></font></font></font></font></font></font></font></font></font></font></font></font></font></font><font size="2"></font></font></font><font size="2"></font></font></font></font></font><font size="2"> 
              </font></font></font></font></font></font></font></td>
            <td nowrap width="11%">&nbsp;</td>
            <td nowrap width="40%">&nbsp; </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>D&eacute;bitos/Cr&eacute;ditos</h4>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap width="50%"> 
        <table cellspacing=0 cellpadding=2 border="0" width="100%">
          <tr id="trdark"> 
            <td nowrap colspan="3" > 
              <div align="center">D&eacute;bitos</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"  > 
              <div align="left">Bn./Suc./Mda.</div>
            </td>
            <td nowrap width="29%"  > 
              <div align="left">Cta.Contable</div>
            </td>
            <td nowrap width="41%"  > 
              <div align="left">Ref/Cuenta</div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"  > 
              <div align="left"> 
                <input type="text" readonly name="E05WRTDBK" size="1" maxlength="2" value="<%= inqContab.getE05WRTDBK().trim()%>" >
                / 
                <input type="text" readonly name="E05WRTDBR" size="1" maxlength="3" value="<%= inqContab.getE05WRTDBR().trim()%>" >
                / 
                <input type="text" readonly name="E05WRTDCY" size="1" maxlength="3" value="<%= inqContab.getE05WRTDCY().trim()%>" >
              </div>
            </td>
            <td nowrap width="29%"  > 
              <div align="left"> 
                <input type="text" readonly name="E05WRTDGL2" size="14" maxlength="16" value="<%= inqContab.getE05WRTDGL().trim()%>" >
              </div>
            </td>
            <td nowrap width="41%" > 
              <div align="left"> 
                <input type="text" readonly name="E05WRTDAC" size="8" maxlength="15" value="<%= inqContab.getE05WRTDAC().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"  > 
              <div align="left">S/Cuenta</div>
            </td>
            <td nowrap width="29%" > 
              <div align="left">C/Costos</div>
            </td>
            <td nowrap width="41%"  > 
              <div align="left">Monto</div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"  > 
              <div align="left"> 
                <input type="text" readonly name="E05WRTDSU" size="12" maxlength="15" value="<%= inqContab.getE05WRTDSU().trim()%>" >
              </div>
            </td>
            <td nowrap width="29%"  > 
              <div align="left"> 
                <input type="text" readonly name="E05WRTDCT" size="14" maxlength="16" value="<%= inqContab.getE05WRTDCT().trim()%>" >
              </div>
            </td>
            <td nowrap width="41%" bordercolor="#000000" > 
              <div align="left"> 
                <input type="text" readonly name="E05WRTDAM" size="8" maxlength="12" value="<%= inqContab.getE05WRTDAM().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="3" > 
              <p>Descripci&oacute;n:</p>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="3" > 
              <input type="text" readonly name="E05WRTDDS" size="35" maxlength="35" value="<%= inqContab.getE05WRTDDS().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="3"  > 
              <p>Equivalente:</p>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" readonly name="E05WRTDEQ" size="15" maxlength="15" value="<%= inqContab.getE05WRTDEQ().trim()%>" >
              </div>
            </td>
          </tr>
        </table>
      </td>
      <td nowrap width="50%" > 
        <table cellspacing=0 cellpadding=2 border="0" width="100%">
          <tr id="trdark"> 
            <td nowrap colspan="3"  > 
              <div align="center">Cr&eacute;ditos</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"  > 
              <div align="left">Bn./Suc./Mda.</div>
            </td>
            <td nowrap width="24%"  > 
              <div align="left">Cta.Contable</div>
            </td>
            <td nowrap width="51%"  > 
              <div align="left">Ref/Cuenta</div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="left"> 
                <input type="text" readonly name="E05WRTCBK" size="1" maxlength="2" value="<%= inqContab.getE05WRTCBK().trim()%>" >
                / 
                <input type="text" readonly name="E05WRTCBR" size="1" maxlength="3" value="<%= inqContab.getE05WRTCBR().trim()%>" >
                / 
                <input type="text" readonly name="E05WRTCCY" size="1" maxlength="3" value="<%= inqContab.getE05WRTCCY().trim()%>" >
              </div>
            </td>
            <td nowrap width="24%"  > 
              <div align="left"> 
                <input type="text" readonly name="E05WRTCGL" size="14" maxlength="16" value="<%= inqContab.getE05WRTCGL().trim()%>" >
              </div>
            </td>
            <td nowrap width="51%"  > 
              <div align="left"> 
                <input type="text" readonly name="E05WRTCAC" size="8" maxlength="12" value="<%= inqContab.getE05WRTCAC().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"  > 
              <div align="left">S/Cuenta</div>
            </td>
            <td nowrap width="24%"  > 
              <div align="left">C/Costos</div>
            </td>
            <td nowrap width="51%"  > 
              <div align="left">Monto</div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="left"> 
                <input type="text" readonly name="E05WRTCSU" size="12" maxlength="15" value="<%= inqContab.getE05WRTCSU().trim()%>" >
              </div>
            </td>
            <td nowrap width="24%"  > 
              <div align="left"> 
                <input type="text" readonly name="E05WRTCCT" size="14" maxlength="16" value="<%= inqContab.getE05WRTCCT().trim()%>" >
              </div>
            </td>
            <td nowrap width="51%"  > 
              <div align="left"> 
                <input type="text" readonly name="E05WRTCAM" size="8" maxlength="15" value="<%= inqContab.getE05WRTCAM().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="3"  > 
              Descripci&oacute;n:
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="3"  > 
              <input type="text" readonly name="E05WRTCDS" size="35" maxlength="35" value="<%= inqContab.getE05WRTCDS().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="3"  > 
              <p>Equivalente:</p>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="3"  > 
              <div align="left"> 
                <input type="text" readonly name="E05WRTCEQ" size="15" maxlength="15" value="<%= inqContab.getE05WRTCEQ().trim()%>" >
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Comisi&oacute;n</h4>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap colspan="2" > 
              <div align="right">Descripci&oacute;n: </div>
            </td>
            <td nowrap colspan="6" >&nbsp; </td>
            <td nowrap width="17%"> 
              <div align="left">Valor IVA:</div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="8" > 
              <input type="text" readonly name="E02DEABAP2542" size="45" maxlength="15" >
            </td>
            <td nowrap width="17%" > 
              <div align="left"> 
                <input type="text" readonly name="E02DEABAP25437" size="15" maxlength="15" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap rowspan="8" colspan="2" >&nbsp;</td>
            <td nowrap width="6%" > 
              <div align="center">Banco</div>
            </td>
            <td nowrap width="8%" > 
              <div align="center">Sucursal</div>
            </td>
            <td nowrap width="9%" > 
              <div align="center">Moneda</div>
            </td>
            <td nowrap width="16%" > 
              <div align="center">Cta Contable</div>
            </td>
            <td nowrap width="12%" > 
              <div align="center">Ctro Costo</div>
            </td>
            <td nowrap width="16%" > 
              <div align="center">Monto</div>
            </td>
            <td nowrap rowspan="8" width="17%">&nbsp; </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="6%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC1B" size="1" maxlength="2" value="<%= inqContab.getE05WRTC1B().trim()%>" >
              </div>
            </td>
            <td nowrap width="8%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC1S" size="2" maxlength="3" value="<%= inqContab.getE05WRTC1S().trim()%>" >
              </div>
            </td>
            <td nowrap width="9%" > 
              <div align="center"> 
                <input type="text" readonly size="2" maxlength="3" value="<%= inqContab.getE05WRTC1Y().trim()%>" name="E05WRTC1Y" >
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC1G" size="16" maxlength="16" value="<%= inqContab.getE05WRTC1G().trim()%>" >
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC1T" size="8" maxlength="8" value="<%= inqContab.getE05WRTC1T().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC1A" size="15" maxlength="15" value="<%= inqContab.getE05WRTC1A().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="6%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC2B" size="1" maxlength="2" value="<%= inqContab.getE05WRTC2B().trim()%>" >
              </div>
            </td>
            <td nowrap width="8%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC2S" size="2" maxlength="3" value="<%= inqContab.getE05WRTC2S().trim()%>" >
              </div>
            </td>
            <td nowrap width="9%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC2Y" size="2" maxlength="3" value="<%= inqContab.getE05WRTC2Y().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="center"> 
                <input type="text" readonly size="16" maxlength="16" value="<%= inqContab.getE05WRTC2G().trim()%>" name="E05WRTC2G" >
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC2T" size="8" maxlength="8" value="<%= inqContab.getE05WRTC2T().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC2A" size="15" maxlength="15" value="<%= inqContab.getE05WRTC2A().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="6%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC3B" size="1" maxlength="2" value="<%= inqContab.getE05WRTC3B().trim()%>" >
              </div>
            </td>
            <td nowrap width="8%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC3S" size="2" maxlength="3" value="<%= inqContab.getE05WRTC3S().trim()%>" >
              </div>
            </td>
            <td nowrap width="9%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC3Y" size="2" maxlength="3" value="<%= inqContab.getE05WRTC3Y().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC3G" size="16" maxlength="16" value="<%= inqContab.getE05WRTC3G().trim()%>" >
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC3T" size="8" maxlength="8" value="<%= inqContab.getE05WRTC3T().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC3A" size="15" maxlength="15" value="<%= inqContab.getE05WRTC3A().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="6%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC4B" size="1" maxlength="2" value="<%= inqContab.getE05WRTC4B().trim()%>" >
              </div>
            </td>
            <td nowrap width="8%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC4S" size="2" maxlength="3" value="<%= inqContab.getE05WRTC4S().trim()%>" >
              </div>
            </td>
            <td nowrap width="9%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC4Y" size="2" maxlength="3" value="<%= inqContab.getE05WRTC4Y().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC4G" size="16" maxlength="16" value="<%= inqContab.getE05WRTC4G().trim()%>" >
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC4T" size="8" maxlength="8" value="<%= inqContab.getE05WRTC4T().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC4A" size="15" maxlength="15" value="<%= inqContab.getE05WRTC4A().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="6%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC5B" size="1" maxlength="2" value="<%= inqContab.getE05WRTC5B().trim()%>" >
              </div>
            </td>
            <td nowrap width="8%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC5S" size="2" maxlength="3" value="<%= inqContab.getE05WRTC5S().trim()%>" >
              </div>
            </td>
            <td nowrap width="9%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC5Y" size="2" maxlength="3" value="<%= inqContab.getE05WRTC5Y().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC5G2" size="16" maxlength="16" value="<%= inqContab.getE05WRTC5G().trim()%>" >
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC5T" size="8" maxlength="8" value="<%= inqContab.getE05WRTC5T().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC5A" size="15" maxlength="15" value="<%= inqContab.getE05WRTC5A().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="6%" > 
              <div align="center">
                <input type="text" readonly name="E05WRTC6B25536" size="1" maxlength="2" value="<%= inqContab.getE05WRTC6B().trim()%>" >
              </div>
            </td>
            <td nowrap width="8%" > 
              <div align="center">
                <input type="text" readonly name="E05WRTC6S" size="2" maxlength="3" value="<%= inqContab.getE05WRTC6S().trim()%>" >
              </div>
            </td>
            <td nowrap width="9%" > 
              <div align="center">
                <input type="text" readonly name="E05WRTC6Y" size="2" maxlength="3" value="<%= inqContab.getE05WRTC6Y().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="center">
                <input type="text" readonly name="E05WRTC6G" size="16" maxlength="16" value="<%= inqContab.getE05WRTC5G().trim()%>" >
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="center">
                <input type="text" readonly name="E05WRTC6T" size="8" maxlength="8" value="<%= inqContab.getE05WRTC6T().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="center">
                <input type="text" readonly name="E05WRTC6A" size="15" maxlength="15" value="<%= inqContab.getE05WRTC6A().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="6%" > 
              <div align="center">
                <input type="text" readonly name="E05WRTC7B62" size="1" maxlength="2" value="<%= inqContab.getE05WRTC7B().trim()%>" >
              </div>
            </td>
            <td nowrap width="8%" > 
              <div align="center">
                <input type="text" readonly name="E05WRTC7S" size="2" maxlength="3" value="<%= inqContab.getE05WRTC7S().trim()%>" >
              </div>
            </td>
            <td nowrap width="9%" > 
              <div align="center"> 
                <input type="text" readonly name="E05WRTC7Y" size="2" maxlength="3" value="<%= inqContab.getE05WRTC7Y().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="center">
                <input type="text" readonly name="E05WRTC7G" size="16" maxlength="16" value="<%= inqContab.getE05WRTC7G().trim()%>" >
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="center">
                <input type="text" readonly name="E05WRTC7T" size="8" maxlength="8" value="<%= inqContab.getE05WRTC7T().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="center">
                <input type="text" readonly name="E05WRTC7A" size="15" maxlength="15" value="<%= inqContab.getE05WRTC7A().trim()%>" >
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p align="center">&nbsp; </p>
  </form>
</body>
</html>
