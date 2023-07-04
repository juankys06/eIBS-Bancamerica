<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page  import = "datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Resumen de Cuentas por Cliente
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "listRep" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "totalsRep" class= "datapro.eibs.beans.JBListRec"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"   scope="session" />


</HEAD>


<BODY>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>



<FORM>
  <%
	if ( listRep.getNoResult() ) {
%> 
  <table class="tbenter" width=100% height=100%>
    <tr> 
      <td> 
        
          <h3> No hay resultados para su criterio de b&uacute;squeda </h3>
       
      </td>
    </tr>
  </table>
  <%
	}
	else {
%>
  <h3 align="center">Resumen de Cuentas por N&uacute;mero de Cliente<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="rp_sum_by_cust.jsp, ECIF0100P"></h3>
  <hr size="4">
  <p>&nbsp;</p>
  <table class="tableinfo" width="286">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="24%" > 
              <div align="right"><b>Reporte preparado para : </b></div>
            </td>
            <td nowrap width="76%" > 
              <div align="left"> <%= userPO.getHeader1().trim()%> - <%= userPO.getHeader2().trim()%></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <table  class="tableinfo" width="100%">
    <tr>
      <td>
        <table width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="3%" height="60">Prest.</th>
            <th align=CENTER nowrap width="4%" height="60">Cuenta</th>
            <th align=CENTER nowrap width="8%" height="60"> 
              <div align="center">T&eacute;rmino</div>
            </th>
            <th align=CENTER nowrap width="5%" height="60"> 
              <div align="center">Nombre</div>
            </th>
            <th align=CENTER nowrap width="7%" height="60"> 
              <div align="center">N&uacute;mero <BR>Cliente</div>
            </th>
            <th align=CENTER nowrap width="9%" height="60">Saldo Actual<br>
              ( Moneda <BR>Nacional)</th>
            <th align=CENTER nowrap width="7%" height="60">Tasa <BR>
              Conversion</th>
            <th align=CENTER nowrap width="8%" height="60">Saldo Actual<br>
              ( Moneda <BR>Base)</th>
            <th align=CENTER nowrap width="4%" height="60">Tasa <BR>
              Interes</th>
            <th align=CENTER nowrap width="8%" height="60">Vencimiento</th>
            <th align=CENTER nowrap width="8%" height="60"> Fecha <BR>
              Creaci&oacute;n<BR> 
              Cuenta</th>
            <th align=CENTER nowrap width="7%" height="60">Fecha <BR>
              Est. del<BR> Cliente</th>
            <th align=CENTER nowrap width="22%" height="60">Producto</th>
          </tr>
          <%
                listRep.initRow();
		  String prevFlag = "0";
		  int i = 0;
                while (listRep.getNextRow()) {
                    if (listRep.getFlag().equals(prevFlag)) {
                    		out.println(listRep.getRecord());
                    }
			else {
				prevFlag = listRep.getFlag();
				totalsRep.setCurrentRow(i);
				i++;
              %> 
          <tr id="trdark"> 
            <td colspan="3"><b>Total para Prestamos: <%= totalsRep.getRecord(3)%></b></td>
            <td colspan="3" > 
              <div align="right"><b>Cantidad : <%= totalsRep.getRecord(2) %></b></div>
            </td>
            <td colspan="2"> 
              <div align="right"><b>$ <%= Util.fcolorCCY(totalsRep.getRecord(1)) %></b></div>
            </td>
            <td colspan="5">&nbsp; </td>
          </tr>
          <%
                    		out.println(listRep.getRecord());
			}
                }
		  totalsRep.setCurrentRow(i);
                if (totalsRep.getRecord(0).equals("*")) {
              %> 
          <tr id="trdark"> 
            <td colspan="3"><b>Total para Prestamos: <%= totalsRep.getRecord(3) %></b></td>
            <td colspan="3"> 
              <div align="right"><b>Cantidad : <%= totalsRep.getRecord(2) %></b></div>
            </td>
            <td colspan="2"> 
              <div align="right"><b>$ <%= Util.fcolorCCY(totalsRep.getRecord(1)) %></b></div>
            </td>
            <td colspan="5">&nbsp;</td>
          </tr>
          <%
		  }
	}
              %> 
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <BR>
  <div align="center"> </div>

</FORM>

</BODY>
</HTML>
