<html>
<head>
<title></title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</head>

<jsp:useBean id= "clBalance" class= "datapro.eibs.beans.ELN006001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(cl_i_opt);

</SCRIPT>

<body>

 <%@ page import = "datapro.eibs.master.Util" %>

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<SCRIPT> initMenu(); </SCRIPT>

<h3 align="center">Saldo de Línea de Crédito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cl_inq_balance.jsp, ELN0060"></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="32">
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
                <input type="text" name="CUSCUN" size="9" maxlength="9" value="<%= userPO.getCusNum().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="CUSNA1" size="45" readonly maxlength="45" value="<%= userPO.getCusName().trim()%>" >
              </div>
            </td>
            <td nowrap > 
              <div align="right"><b>Tipo de Linea : </b></div>
            </td>
            <td nowrap ><b> 
              <input type="text" name="PROCOD" size="4" readonly maxlength="4" value="<%= userPO.getCreditLineType().trim()%>">
              </b></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="14%"> 
              <div align="right"><b>N&uacute;mero de Linea :</b></div>
            </td>
            <td nowrap width="9%"> 
              <div align="left"> 
                <input type="text" name="ACCNUM" size="12" readonly maxlength="12" value="<%= userPO.getCreditLineNum().trim()%>" >
              </div>
            </td>
            <td nowrap width="12%"> 
              <div align="right">Oficial :</div>
            </td>
            <td nowrap width="33%"> 
              <div align="left"><b> 
                <input type="text" name="E02NA122" size="30" maxlength="30" readonly value="<%= userPO.getOfficer().trim()%>">
                </b> </div>
            </td>
            <td nowrap width="11%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="21%"> 
              <div align="left"><b> 
                <input type="text" name="PROCCY" size="4" readonly maxlength="4" value="<%= userPO.getCurrency().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Sumario</h4>
    <table class="tableinfo">
      <tr > 
        <td >
          <table cellspacing="0" cellpadding="2" width="100%" border="0" >
            <tr id="trdark"> 
              <td width=25%> 
                <div align="right">Monto Aprobado : </div>
              </td>
              
            <td width=22%> 
              <div align="left">
                  <input type="text" id="txtright" name="E01LNEAMN" readonly  size="17" maxlength="15" value="<%= Util.formatCCY(clBalance.getE01LNEAMN())%>">
                </div>
              </td>
              
            <td width=24%> 
              <div align="right">Fecha de Apertura : </div>
              </td>
              
            <td width=29% nowrap> 
              <div align="left">
                  
                <input type="text" name="E01LNEOP1" readonly  size="3" maxlength="2" value="<%= clBalance.getE01LNEOP1().trim()%>">
                <input type="text" name="E01LNEOP2" readonly  size="3" maxlength="2" value="<%= clBalance.getE01LNEOP2().trim()%>">
                <input type="text" name="E01LNEOP3" readonly  size="3" maxlength="2" value="<%= clBalance.getE01LNEOP3().trim()%>">
                </div>
              </td>
            </tr>
            <tr id="trclear"> 
              <td width=25%> 
                <div align="right">Monto Reasignado : </div>
              </td>
              
            <td width=22%> 
              <div align="left">
                  <input type="text" id="txtright" name="E01LNEREA" readonly  size="17" maxlength="15" value="<%= Util.formatCCY(clBalance.getE01LNEREA())%>">
                </div>
              </td>
              
            <td width=24%> 
              <div align="right">Fecha de Vencimiento : </div>
              </td>
              
            <td width=29% nowrap> 
              <div align="left">
                  
                <input type="text" name="E01LNEMT1" readonly  size="3" maxlength="2" value="<%= clBalance.getE01LNEMT1().trim()%>">
                <input type="text" name="E01LNEMT2" readonly  size="3" maxlength="2" value="<%= clBalance.getE01LNEMT2().trim()%>">
                <input type="text" name="E01LNEMT3" readonly  size="3" maxlength="2" value="<%= clBalance.getE01LNEMT3().trim()%>">
                </div>
              </td>
            </tr>
            <tr id="trdark"> 
              <td width=25%> 
                <div align="right">Monto Utilizado : </div>
              </td>
              
            <td width=22%> 
              <div align="left">
                  <input type="text" id="txtright" name="E01LNEAMU" readonly  size="17" maxlength="15" value="<%= Util.formatCCY(clBalance.getE01LNEAMU())%>">
                </div>
              </td>
              
            <td width=24%> 
              <div align="right">Fecha de Revisi&oacute;n : </div>
              </td>
              
            <td width=29% nowrap> 
              <div align="left">
                  
                <input type="text" name="E01LNERV1" readonly  size="3" maxlength="2" value="<%= clBalance.getE01LNERV1().trim()%>">
                <input type="text" name="E01LNERV2" readonly  size="3" maxlength="2" value="<%= clBalance.getE01LNERV2().trim()%>">
                <input type="text" name="E01LNERV3" readonly  size="3" maxlength="2" value="<%= clBalance.getE01LNERV3().trim()%>">
                </div>
              </td>
            </tr>
            <tr id="trclear"> 
              <td width=25%> 
                <div align="right">Solicitudes del D&iacute;a : </div>
              </td>
              
            <td width=22%> 
              <div align="left">
                  <input type="text" id="txtright" name="E01LNEOFA" readonly  size="17" maxlength="15" value="<%= Util.formatCCY(clBalance.getE01LNEOFA())%>">
                </div>
              </td>
              
            <td width=24%> 
              <div align="right">Fecha de Reasignaci&oacute;n : </div>
              </td>
              
            <td width=29% nowrap> 
              <div align="left">
                  
                <input type="text" name="E01LNERE1" readonly  size="3" maxlength="2" value="<%= clBalance.getE01LNERE1().trim()%>">
                  
                <input type="text" name="E01LNERE2" readonly  size="3" maxlength="2" value="<%= clBalance.getE01LNERE2().trim()%>">
                  
                <input type="text" name="E01LNERE3" readonly  size="3" maxlength="2" value="<%= clBalance.getE01LNERE3().trim()%>">
                </div>
              </td>
            </tr>
            <tr id="trdark"> 
              <td width=25%> 
                <div align="right">Saldo de la Linea : </div>
              </td>
              
            <td width=22%> 
              <div align="left">
                  <input type="text" id="txtright" name="E01LNEBAL" readonly  size="17" maxlength="15" value="<%= Util.formatCCY(clBalance.getE01LNEBAL())%>">
                </div>
              </td>
              
            <td width=24%> 
              <div align="right">Monto de Garant&iacute;a : </div>
              </td>
              
            <td width=29%> 
              <div align="left">
                  <input type="text" id="txtright" name="E01LNEOCM" readonly  size="17" maxlength="15" value="<%= Util.formatCCY(clBalance.getE01LNEOCM())%>">
                </div>
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>

<h4>Información Adicional</h4>
    <table class="tableinfo">
      <tr > 
        <td >
          
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark"> 
            <td width=30%> 
              <div align="right">Promedio no Utilizado : </div>
            </td>
            <td width=20%> 
              <div align="left"> 
                <input type="text" id="txtright" name="E01LNEAVN" readonly  size="17" maxlength="15" value="<%= Util.formatCCY(clBalance.getE01LNEAVN())%>">
              </div>
            </td>
            <td width=25%> 
              <div align="right">Total de Comisi&oacute;n : </div>
            </td>
            <td width=25%> 
              <div align="left"> 
                <input type="text" id="txtright" name="E01LNECLT" readonly  size="17" maxlength="15" value="<%= Util.formatCCY(clBalance.getE01LNECLT())%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width=30%> 
              <div align="right">Promedio Monto Utilizado : </div>
            </td>
            <td width=20%> 
              <div align="left"> 
                <input type="text" id="txtright" name="E01LNEAVU" readonly  size="17" maxlength="15" value="<%= Util.formatCCY(clBalance.getE01LNEAVU())%>">
              </div>
            </td>
            <td width=25%> 
              <div align="right">Comisiones del A&ntilde;o : </div>
            </td>
            <td width=25%> 
              <div align="left"> 
                <input type="text" id="txtright" name="E01LNECYT" readonly  size="17" maxlength="15" value="<%= Util.formatCCY(clBalance.getE01LNECYT())%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td> 
              <div align="right"> Prop&oacute;sito de la Linea : </div>
            </td>
            <td colspan="3"> 
              <div align="right"></div>
              <div align="left"> 
                <input type="text" name="E01LNEPUR" readonly  size="45" maxlength="35" value="<%= clBalance.getE01LNEPUR().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td> 
              <div align="right">Autorizado por : </div>
            </td>
            <td colspan="3"> 
              <div align="left"> 
                <input type="text" name="E01LNEABY" readonly  size="45" maxlength="25" value="<%= clBalance.getE01LNEABY().trim()%>">
              </div>
            </td>
          </tr>
        </table>
        </td>
      </tr>
    </table>
</form>
</body>
</html>

