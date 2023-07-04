<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Control de Actividades</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="rtLaunder" class="datapro.eibs.beans.ELD010002Message"  scope="session" />

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
<div align="center"></div>
<h3 align="center"> Consulta de Control de Actividades<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_inquiry, EDL0160"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130" >
  <input type=HIDDEN name="SCREEN" value="14">
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
                <input type="text" name="E02LDMCUN" size="9" maxlength="9" readonly value="<%= rtLaunder.getE02LDMCUN().trim()%>">
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E02CUSNA1" size="45" maxlength="45" readonly value="<%= rtLaunder.getE02CUSNA1().trim()%>">
              </div>
            </td>
            <td nowrap >
              <div align="right"><b>Producto :</b></div>
            </td>
            <td nowrap >
              <div align="left"><b>
                <input type="text" name="E02LDMPRO" size="4" maxlength="4" readonly value="<%= rtLaunder.getE02LDMPRO().trim()%>">
                </b></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="14%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="9%"> 
              <div align="left"> 
                <input type="text" name="E02LDMACC" size="12" maxlength="12" value="<%= rtLaunder.getE02LDMACC().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="12%"> 
              <div align="right"><b>Oficial :</b></div>
            </td>
            <td nowrap width="33%"> 
              <div align="left"><b> 
                <input type="text" name="E02LDWOFC" size="5" maxlength="4" readonly value="<%= rtLaunder.getE02LDWOFC().trim()%>">
                <input type="text" name="E02LDWNOF" size="35" maxlength="35" readonly value="<%= rtLaunder.getE02LDWNOF().trim()%>">
                </b> </div>
            </td>
            <td nowrap width="11%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="21%"> 
              <div align="left"><b> 
                <input type="text" name="E02LDMCCY" size="4" maxlength="3" value="<%= rtLaunder.getE02LDMCCY().trim()%>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br><table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha de Proceso :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E02LDEBDM" size="3" maxlength="2" value="<%= rtLaunder.getE02LDEBDM().trim()%>" readonly>
              <input type="text" name="E02LDEBDY" size="6" maxlength="4" value="<%= rtLaunder.getE02LDEBDY().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
 
    <p><b>Informaci&oacute;n B&aacute;sica</b> </p>
  
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Saldo Disponible :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E02AVALBL" size="15" maxlength="15" value="<%= rtLaunder.getE02AVALBL().trim()%>" readonly>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Saldo en Libros :</div>
            </td>
            <td nowrap width="26%"> 
              <input type="text" name="E02ACMMGB" size="15" maxlength="15" value="<%= rtLaunder.getE02ACMMGB().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha Ultima Transacci&oacute;n :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E02LSTRD1" size="2" maxlength="2" value="<%= rtLaunder.getE02LSTRD1().trim()%>" readonly>
              <input type="text" name="E02LSTRD2" size="2" maxlength="2" value="<%= rtLaunder.getE02LSTRD2().trim()%>" readonly>
              <input type="text" name="E02LSTRD3" size="2" maxlength="2" value="<%= rtLaunder.getE02LSTRD3().trim()%>" readonly>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Fecha Ultimo Sobregiro :</div>
            </td>
            <td nowrap width="26%"> 
              <input type="text" name="E02LSTDD1" size="2" maxlength="2" value="<%= rtLaunder.getE02LSTDD1().trim()%>" readonly>
              <input type="text" name="E02LSTDD2" size="2" maxlength="2" value="<%= rtLaunder.getE02LSTDD2().trim()%>" readonly>
              <input type="text" name="E02LSTDD3" size="2" maxlength="2" value="<%= rtLaunder.getE02LSTDD3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha Ultimo Deposito :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E02LSTDD1" size="2" maxlength="2" value="<%= rtLaunder.getE02LSTDD1().trim()%>" readonly>
              <input type="text" name="E02LSTDD2" size="2" maxlength="2" value="<%= rtLaunder.getE02LSTDD2().trim()%>" readonly>
              <input type="text" name="E02LSTDD3" size="2" maxlength="2" value="<%= rtLaunder.getE02LSTDD3().trim()%>" readonly>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Monto Ultimo Deposito :</div>
            </td>
            <td nowrap width="26%"> 
              <input type="text" name="E02ACMLDA" size="15" maxlength="15" value="<%= rtLaunder.getE02ACMLDA().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Variaci&oacute;n Trans :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E02LDPVNT" size="5" maxlength="4" value="<%= rtLaunder.getE02LDPVNT().trim()%>" readonly>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Variaci&oacute;n Monto :</div>
            </td>
            <td nowrap width="26%"> 
              <input type="text" name="E02LDPVAM" size="6" maxlength="5" value="<%= rtLaunder.getE02LDPVAM().trim()%>" readonly>
              (%) </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n Adicional</h4>
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="center"><b>Tipo de Actividad</b></div>
            </td>
            <td nowrap width="23%"> 
              <div align="center"><b>Estimado </b></div>
            </td>
            <td nowrap width="26%"> 
              <div align="center"><b>Real</b></div>
            </td>
            <td nowrap width="26%"> 
              <div align="center"><b>Variaci&oacute;n (%)</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Dep&oacute;sitos Efectivo :</div>
            </td>
            <td nowrap width="23%"> 
              <div align="center"> 
                <input type="text" name="E02LDMCDP" size="6" maxlength="5" value="<%= rtLaunder.getE02LDMCDP().trim()%>" readonly>
                <input type="text" name="E02LDMCDA" size="15" maxlength="15" value="<%= rtLaunder.getE02LDMCDA().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="center"> 
                <input type="text" name="E02LDHCDP" size="6" maxlength="5" value="<%= rtLaunder.getE02LDHCDP().trim()%>" readonly>
                <input type="text" name="E02LDHCDA" size="15" maxlength="15" value="<%= rtLaunder.getE02LDHCDA().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="center">
                <input type="text" name="E02LDCVA1" size="9" maxlength="9" value="<%= rtLaunder.getE02LDCVA1().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Dep&oacute;sitos Cheque :</div>
            </td>
            <td nowrap width="23%"> 
              <div align="center"> 
                <input type="text" name="E02LDMKDP" size="6" maxlength="5" value="<%= rtLaunder.getE02LDMKDP().trim()%>" readonly>
                <input type="text" name="E02LDMKDA" size="15" maxlength="15" value="<%= rtLaunder.getE02LDMKDA().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="center"> 
                <input type="text" name="E02LDHKDP" size="6" maxlength="5" value="<%= rtLaunder.getE02LDHKDP().trim()%>" readonly>
                <input type="text" name="E02LDHKDA" size="15" maxlength="15" value="<%= rtLaunder.getE02LDHKDA().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="center">
                <input type="text" name="E02LDCVA2" size="9" maxlength="5" value="<%= rtLaunder.getE02LDCVA2().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Retiros Efectivo:</div>
            </td>
            <td nowrap width="23%"> 
              <div align="center"> 
                <input type="text" name="E02LDMCWD" size="6" maxlength="5" value="<%= rtLaunder.getE02LDMCWD().trim()%>" readonly>
                <input type="text" name="E02LDMCWA" size="15" maxlength="15" value="<%= rtLaunder.getE02LDMCWA().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="center"> 
                <input type="text" name="E02LDHCWD" size="6" maxlength="5" value="<%= rtLaunder.getE02LDHCWD().trim()%>" readonly>
                <input type="text" name="E02LDHCWA" size="15" maxlength="15" value="<%= rtLaunder.getE02LDHCWA().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="center">
                <input type="text" name="E02LDCVA3" size="9" maxlength="5" value="<%= rtLaunder.getE02LDCVA3().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Cheques Pagados :</div>
            </td>
            <td nowrap width="23%"> 
              <div align="center"> 
                <input type="text" name="E02LDMCPY" size="6" maxlength="5" value="<%= rtLaunder.getE02LDMCPY().trim()%>" readonly>
                <input type="text" name="E02LDMCPA" size="15" maxlength="15" value="<%= rtLaunder.getE02LDMCPA().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="center"> 
                <input type="text" name="E02LDHCPY" size="6" maxlength="5" value="<%= rtLaunder.getE02LDHCPY().trim()%>" readonly>
                <input type="text" name="E02LDHCPA" size="15" maxlength="15" value="<%= rtLaunder.getE02LDHCPA().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="center">
                <input type="text" name="E02LDCVA4" size="9" maxlength="5" value="<%= rtLaunder.getE02LDCVA4().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Transferencias Emitidas :</div>
            </td>
            <td nowrap width="23%"> 
              <div align="center"> 
                <input type="text" name="E02LDMTIN" size="6" maxlength="5" value="<%= rtLaunder.getE02LDMTIN().trim()%>" readonly>
                <input type="text" name="E02LDMTIA" size="15" maxlength="15" value="<%= rtLaunder.getE02LDMTIA().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="center"> 
                <input type="text" name="E02LDHTIN" size="6" maxlength="5" value="<%= rtLaunder.getE02LDHTIN().trim()%>" readonly>
                <input type="text" name="E02LDHTIA" size="15" maxlength="15" value="<%= rtLaunder.getE02LDHTIA().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="center">
                <input type="text" name="E02LDCVA5" size="9" maxlength="5" value="<%= rtLaunder.getE02LDCVA5().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Transferencias Recibidas :</div>
            </td>
            <td nowrap width="23%"> 
              <div align="center"> 
                <input type="text" name="E02LDMTRV" size="6" maxlength="5" value="<%= rtLaunder.getE02LDMTRV().trim()%>" readonly>
                <input type="text" size="15" maxlength="15" value="<%= rtLaunder.getE02LDMTRA().trim()%>" readonly name="E02LDMTRA">
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="center"> 
                <input type="text" name="E02LDHTRV" size="6" maxlength="5" value="<%= rtLaunder.getE02LDHTRV().trim()%>" readonly>
                <input type="text" name="E02LDHTRA" size="15" maxlength="15" value="<%= rtLaunder.getE02LDHTRA().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="center">
                <input type="text" name="E02LDCVA6" size="9" maxlength="5" value="<%= rtLaunder.getE02LDCVA6().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="center"><b>Saldos</b></div>
            </td>
            <td nowrap colspan="3">&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Promedio en Libros :</div>
            </td>
            <td nowrap width="23%"> 
              <div align="right"> 
                <input type="text" name="E02LDMGAV" size="15" maxlength="15" value="<%= rtLaunder.getE02LDMGAV().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="right"> 
                <input type="text" name="E02LDHGAV" size="15" maxlength="15" value="<%= rtLaunder.getE02LDHGAV().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="center">
                <input type="text" name="E02LDCVA7" size="9" maxlength="5" value="<%= rtLaunder.getE02LDCVA7().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Promedio Disponible :</div>
            </td>
            <td nowrap width="23%"> 
              <div align="right"> 
                <input type="text" name="E02LDMNAV" size="15" maxlength="15" value="<%= rtLaunder.getE02LDMNAV().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="right"> 
                <input type="text" name="E02LDHNAV" size="15" maxlength="15" value="<%= rtLaunder.getE02LDHNAV().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="center">
                <input type="text" name="E02LDCVA8" size="9" maxlength="5" value="<%= rtLaunder.getE02LDCVA8().trim()%>" readonly>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>&nbsp;</h4>
  <p>&nbsp;</p>
</form>
</body>
</html>
