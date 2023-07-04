<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Account Summary Inquiry</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="rtRejAccInq" class="datapro.eibs.beans.EDD009001Message"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
</head>

<body>
<div align="center"></div>
<h3 align="center">Resumen de Cuenta<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rejection_chk_inq_account, EDD00924"></h3>
<hr size="4">
<form>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" readonly name="E01ACMCUN" size="9" maxlength="9"  value="<%= rtRejAccInq.getE01ACMCUN().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" readonly name="E01CUSNA1" size="45" maxlength="45"  value="<%= rtRejAccInq.getE01CUSNA1().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" readonly name="E01ACMACC" size="12" maxlength="12" value="<%= rtRejAccInq.getE01ACMACC().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" readonly name="E01ACMCCY" size="4" maxlength="3" value="<%= rtRejAccInq.getE01ACMCCY().trim()%>" >
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" readonly name="E01ACMPRO" size="4" maxlength="4"  value="<%= rtRejAccInq.getE01ACMPRO().trim()%>">
                </b> </div>
            </td>
          </tr>

        </table>
      </td>
    </tr>
  </table>
<BLOCKQUOTE>
<BLOCKQUOTE>
<BLOCKQUOTE>
<BLOCKQUOTE>
<BLOCKQUOTE>
<BLOCKQUOTE>
<BLOCKQUOTE>
<BLOCKQUOTE>
<BLOCKQUOTE>
<P>&nbsp;</P>
</BLOCKQUOTE>
</BLOCKQUOTE>
</BLOCKQUOTE>
</BLOCKQUOTE>
</BLOCKQUOTE>
</BLOCKQUOTE>
</BLOCKQUOTE>
</BLOCKQUOTE>
</BLOCKQUOTE>
<table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Saldo Contable :</div>
            </td>
            <td nowrap width="23%"> 
              <div align="left"> 
                <input type="text" readonly name="E01ACMMGB" size="20" maxlength="20" value="<%= rtRejAccInq.getE01ACMMGB().trim()%>" >
              </div>
            </td>
            <td nowrap width="26%">
                        <div align="right">Saldo Promedio Contable:</div>
            </td>
            <td nowrap width="26%"> 
              <div align="left">
                <input type="text" readonly name="E01ACMGAV" size="20" maxlength="20" value="<%= rtRejAccInq.getE01ACMGAV().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Saldo Disponible :</div>
            </td>
            <td nowrap width="23%"> 
              <div align="left"> 
                <input type="text" readonly name="E01ACMMNB" size="20" maxlength="20" value="<%= rtRejAccInq.getE01ACMMNB().trim()%>" >
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Saldo Promedio Disponible :</div>
            </td>
            <td nowrap width="26%"> 
              <div align="left">
                <input type="text" readonly name="E01ACMNAV" size="20" maxlength="20" value="<%= rtRejAccInq.getE01ACMNAV().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Monto Retenido :</div>
            </td>
            <td nowrap width="23%"> 
              <div align="left"> 
                <input type="text" readonly name="E01ACMHAM" size="20" maxlength="20" value="<%= rtRejAccInq.getE01ACMHAM().trim()%>" >
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Saldo Promedio Anual Contable :</div>
            </td>
            <td nowrap width="26%"> 
              <div align="left">
                <input type="text" readonly name="E01ACMYAG" size="20" maxlength="20" value="<%= rtRejAccInq.getE01ACMYAG().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Interes a Pagar :</div>
            </td>
            <td nowrap width="23%"> 
              <div align="left"> 
                <input type="text" readonly name="E01ACMIAC" size="20" maxlength="20" value="<%= rtRejAccInq.getE01ACMIAC().trim()%>" >
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Saldo Promedio Anual Disponible :</div>
            </td>
            <td nowrap width="26%"> 
              <div align="left">
                <input type="text" readonly name="E01ACMYAN" size="20" maxlength="20" value="<%= rtRejAccInq.getE01ACMYAN().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Interes Sobregiro :</div>
            </td>
            <td nowrap width="23%"> 
              <div align="left"> 
                <input type="text" readonly name="E01ACMOIA" size="20" maxlength="20" value="<%= rtRejAccInq.getE01ACMOIA().trim()%>" >
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Monto Total Diferidos :</div>
            </td>
            <td nowrap width="26%"> 
              <div align="left"> 
                <input type="text" readonly name="E01UNCBAL" size="20" maxlength="20" value="<%= rtRejAccInq.getE01UNCBAL().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Diferido 1 Día :</div>
            </td>
            <td nowrap width="23%"> 
              <div align="left">
                <input type="text" readonly name="E01ACMUL1" size="20" maxlength="20" value="<%= rtRejAccInq.getE01ACMUL1().trim()%>" >
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Fecha Ultimo Sobregiro :</div>
            </td>
            <td nowrap width="26%"> 
              <div align="left"> 
                <input type="text" readonly name="E01LSTOD1" size="2" maxlength="2" value="<%= rtRejAccInq.getE01LSTOD1().trim()%>" >
                <input type="text" readonly name="E01LSTOD2" size="2" maxlength="2" value="<%= rtRejAccInq.getE01LSTOD2().trim()%>" >
                <input type="text" readonly name="E01LSTOD3" size="2" maxlength="2" value="<%= rtRejAccInq.getE01LSTOD3().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Diferido 2 Días :</div>
            </td>
            <td nowrap width="23%"> 
              <div align="left">
                <input type="text" readonly name="E01ACMUL2" size="20" maxlength="20" value="<%= rtRejAccInq.getE01ACMUL2().trim()%>" >
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="right">N&uacute;mero Anual Sobregiros :</div>
            </td>
            <td nowrap width="26%"> 
              <div align="left"> 
                <input type="text" readonly name="E01ACMTOY" size="5" maxlength="5" value="<%= rtRejAccInq.getE01ACMTOY().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Diferido 3 Días :</div>
            </td>
            <td nowrap width="23%"> 
              <div align="left">
                <input type="text" readonly name="E01ACMUL3" size="20" maxlength="20" value="<%= rtRejAccInq.getE01ACMUL3().trim()%>" >
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Dias consecutivos Sobregiro ciclo:</div>
            </td>
            <td nowrap width="26%"> 
              <div align="left"> 
                <input type="text" readonly name="E01ACMCDO" size="5" maxlength="5" value="<%= rtRejAccInq.getE01ACMCDO().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Diferido por más 3 Días :</div>
            </td>
            <td nowrap width="23%"> 
              <div align="left">
                <input type="text" readonly name="E01ACMFL1" size="20" maxlength="20" value="<%= rtRejAccInq.getE01ACMFL1().trim()%>" >
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="right">N&uacute;mero Total Sobregiros:</div>
            </td>
            <td nowrap width="26%"> 
              <div align="left"> 
                <input type="text" readonly name="E01ACMTOD" size="5" maxlength="5" value="<%= rtRejAccInq.getE01ACMTOD().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha Apertura :</div>
            </td>
            <td nowrap width="23%"> 
              <div align="left">
                <input type="text" readonly name="E01OPNDT1" size="2" maxlength="2" value="<%= rtRejAccInq.getE01OPNDT1().trim()%>" >
                <input type="text" readonly name="E01OPNDT2" size="2" maxlength="2" value="<%= rtRejAccInq.getE01OPNDT2().trim()%>" >
                <input type="text" readonly name="E01OPNDT3" size="2" maxlength="2" value="<%= rtRejAccInq.getE01OPNDT3().trim()%>" >
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Dias Anual de Sobregiro :</div>
            </td>
            <td nowrap width="26%"> 
              <div align="left"> 
                <input type="text" readonly name="E01ACMDOA" size="5" maxlength="5" value="<%= rtRejAccInq.getE01ACMDOA().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Cliente de la Linea de Crédito :</div>
            </td>
            <td nowrap width="23%"> 
              <div align="left">
                <input type="text" readonly name="E01ACMCMC" size="9" maxlength="9" value="<%= rtRejAccInq.getE01ACMCMC().trim()%>" >
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Numero de Linea de Credito :</div>
            </td>
            <td nowrap width="26%"> 
              <div align="left">
                <input type="text" readonly name="E01ACMCMN" size="15" maxlength="15" value="<%= rtRejAccInq.getE01ACMCMN().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Veces de uso de la Linea :</div>
            </td>
            <td nowrap width="23%"> 
              <div align="left">
                <input type="text" readonly name="E01ACMCLY" size="15" maxlength="15" value="<%= rtRejAccInq.getE01ACMCLY().trim()%>" >
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="right"> Disponible Linea de Credito :</div>
            </td>
            <td nowrap width="26%"> 
              <div align="left">
                <input type="text" readonly name="E01LNESAL" size="20" maxlength="20" value="<%= rtRejAccInq.getE01LNESAL().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha de Vencimiento Linea Credito :</div>
            </td>
            <td nowrap width="23%"> 
              <div align="left">
                <input type="text" readonly name="E01LNXDT1" size="2" maxlength="2" value="<%= rtRejAccInq.getE01LNXDT1().trim()%>" >
                <input type="text" readonly name="E01LNXDT2" size="2" maxlength="2" value="<%= rtRejAccInq.getE01LNXDT2().trim()%>" >
                <input type="text" readonly name="E01LNXDT3" size="2" maxlength="2" value="<%= rtRejAccInq.getE01LNXDT3().trim()%>" >
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="right">No. Devoluciones por Forma Ciclo :</div>
            </td>
            <td nowrap width="26%"> 
              <div align="left">
                <input type="text" readonly name="E01ACMNRY" size="5" maxlength="5" value="<%= rtRejAccInq.getE01ACMNRY().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha Ultimo Deposito :</div>
            </td>
            <td nowrap width="23%"> 
              <div align="left">
                <input type="text" readonly name="E01LSTDD1" size="2" maxlength="2" value="<%= rtRejAccInq.getE01LSTDD1().trim()%>" >
                <input type="text" readonly name="E01LSTDD2" size="2" maxlength="2" value="<%= rtRejAccInq.getE01LSTDD2().trim()%>" >
                <input type="text" readonly name="E01LSTDD3" size="2" maxlength="2" value="<%= rtRejAccInq.getE01LSTDD3().trim()%>" >
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="right">No. Devoluciones por Fondos Ciclo :</div>
            </td>
            <td nowrap width="26%"> 
              <div align="left">
                <input type="text" readonly name="E01ACMNRL" size="5" maxlength="5" value="<%= rtRejAccInq.getE01ACMNRL().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Estado de la Cuenta :</div>
            </td>
            <td nowrap width="23%"> 
              <div align="left">
                <input type="text" readonly name="E01DSCAST" size="21" maxlength="20" value="<%= rtRejAccInq.getE01DSCAST().trim()%>" >
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Monto del Ultimo Deposito :</div>
            </td>
            <td nowrap width="26%"> 
              <div align="left">
                <input type="text" readonly name="E01ACMLDA" size="20" maxlength="20" value="<%= rtRejAccInq.getE01ACMLDA().trim()%>" >
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>&nbsp;</h4>
</form>
</body>
</html>
