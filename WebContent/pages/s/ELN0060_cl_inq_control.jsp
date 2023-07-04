<html>
<head>
<title></title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</head>

<jsp:useBean id= "clControl" class= "datapro.eibs.beans.ELN006003Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(cl_i_opt);

</SCRIPT>

<body>

 
 
 <% 
 if ( !error.getERRNUM().equals("0")  ) {
 	error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<SCRIPT> initMenu(); </SCRIPT>

<h3 align="center">Linea de Control<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cl_inq_control.jsp, ELN0060"></h3>
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
<table class="tableinfo" >
      <tr > 
        <td >
          
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark"> 
            <td width=26%> 
              <div align="right">Monto Aprobado : </div>
            </td>
            <td width=20%> 
              <div align="left"> 
                <input type="text" name="E03LNEAMN" readonly  size="17" maxlength="15" value="<%= clControl.getE03LNEAMN().trim()%>">
              </div>
            </td>
            <td width=26%> 
              <div align="right">Fecha Apertura : </div>
            </td>
            <td width=28% nowrap> 
              <div align="left"> 
                <input type="text" name="E03LNEOP1" readonly  size="2" maxlength="2" value="<%= clControl.getE03LNEOP1().trim()%>">
                <input type="text" name="E03LNEOP2" readonly  size="2" maxlength="2" value="<%= clControl.getE03LNEOP2().trim()%>">
                <input type="text" name="E03LNEOP3" readonly  size="2" maxlength="2" value="<%= clControl.getE03LNEOP3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width=26%> 
              <div align="right">Monto Utilizado : </div>
            </td>
            <td width=20%> 
              <div align="left"> 
                <input type="text" name="E03LNEAMU" readonly  size="17" maxlength="15" value="<%= clControl.getE03LNEAMU().trim()%>">
              </div>
            </td>
            <td width=26%> 
              <div align="right">Fecha Vencimiento : </div>
            </td>
            <td width=28% nowrap> 
              <div align="left"> 
                <input type="text" name="E03LNEMT1" readonly  size="2" maxlength="2" value="<%= clControl.getE03LNEMT1().trim()%>">
                <input type="text" name="E03LNEMT2" readonly  size="2" maxlength="2" value="<%= clControl.getE03LNEMT2().trim()%>">
                <input type="text" name="E03LNEMT3" readonly  size="2" maxlength="2" value="<%= clControl.getE03LNEMT3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width=26%> 
              <div align="right"> Saldo Disponible : </div>
            </td>
            <td width=20%> 
              <div align="left"> 
                <input type="text" name="E03LNEBAL" readonly  size="17" maxlength="15" value="<%= clControl.getE03LNEBAL().trim()%>">
              </div>
            </td>
            <td width=26%> 
              <div align="right">Fecha Autorizaci&oacute;n : </div>
            </td>
            <td width=28% nowrap> 
              <div align="left"> 
                <input type="text" name="E03LNEAU1" readonly  size="2" maxlength="2" value="<%= clControl.getE03LNEAU1().trim()%>">
                <input type="text" name="E03LNEAU2" readonly  size="2" maxlength="2" value="<%= clControl.getE03LNEAU2().trim()%>">
                <input type="text" name="E03LNEAU3" readonly  size="2" maxlength="2" value="<%= clControl.getE03LNEAU3().trim()%>">
              </div>
            </td>
          </tr>
        </table>
        </td>
      </tr>
    </table></form>
</body>
</html>

