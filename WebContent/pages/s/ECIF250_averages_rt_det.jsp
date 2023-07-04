<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Promedios de Cuentas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="aveBean" class="datapro.eibs.beans.JBAverage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

function showGraph()
{
	var pg= "<%=request.getContextPath()%>/pages/s/ECIF040_averages_rt_graph.jsp";
	CenterNamedWindow(pg,'graph',700,560,2);
}


</SCRIPT>

</head>

<body>
<%@ page import = "datapro.eibs.master.Util" %>



<form method="post" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  
  <h4>Promedios</h4>
  <table width="100%" border="0" cellspacing="2" cellpadding="0" id="tbhelp">
    <tr> 
      <td width="12%" nowrap>Ver Gr&aacute;fica</td>
      <td width="88%"><a href="javascript:showGraph()"><img src="<%=request.getContextPath()%>/images/graphic1.gif" border="0" width="32" height="32"></a></td>
    </tr>
  </table>
  <table class="tableinfo">
    <tr > 
      <td nowrap>
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark">
            <td width="17%"> 
              <div align="center"><b>Meses</b></div>
            </td>
            <td width="43%"> 
              <div align="center"><b>Año : <%= aveBean.getCurrYear() %></b></div>
            </td>
            <td width="40%"> 
              <div align="center"><b>Año : <%= aveBean.getPrevYear() %></b></div>
            </td>
          </tr>
        </table>
		
        <table cellspacing=0 cellpadding=2 width="100%" border="1">
          <tr id="trclear"> 
            <td nowrap width="16%"> 
              <div align="center"></div>
            </td>
            <td nowrap width="22%"> 
              <div align="center">Promedio Mensual</div>
            </td>
            <td nowrap width="20%"> 
              <div align="center">Promedio Trimestral</div>
            </td>
            <td nowrap width="20%"> 
              <div align="center">Promedio Mensual</div>
            </td>
            <td nowrap width="22%"> 
              <div align="center">Promedio Trimestral</div>
            </td>
          </tr>
          <tr>
            <td id="trdark" nowrap width="18%"> 
              <div align="right">Enero : </div>
            </td>
            <td id="trclear" nowrap width="22%"> 
              <div align="right"><%= Util.fcolorCCY(aveBean.getCurrYearMAve(0)) %> </div>
            </td>
            <td id="trdark" nowrap rowspan="3" width="20%"> 
              <div align="right"><%= Util.fcolorCCY(aveBean.getCurrYear3MAve(0)) %></div>
            </td>
            <td id="trclear" nowrap width="20%"> 
              <div align="right"><%= Util.fcolorCCY(aveBean.getPrevYearMAve(0)) %></div>
            </td>
            <td id="trdark" nowrap rowspan="3" width="20%"> 
              <div align="right"><%= Util.fcolorCCY(aveBean.getPrevYear3MAve(0)) %></div>
            </td>
          </tr>
          <tr> 
            <td id="trdark" nowrap width="18%" height="23"> 
              <div align="right">Febrero : </div>
            </td>
            <td id="trclear" nowrap width="22%" height="23"> 
              <div align="right"><%= Util.fcolorCCY(aveBean.getCurrYearMAve(1)) %> </div>
            </td>
            <td id="trclear" nowrap width="20%" height="23">
              <div align="right"><%= Util.fcolorCCY(aveBean.getPrevYearMAve(1)) %> </div>
            </td>
          </tr>
          <tr> 
            <td id="trdark" nowrap width="18%" height="19"> 
              <div align="right">Marzo : </div>
            </td>
            <td id="trclear" nowrap width="22%" height="19"> 
              <div align="right"><%= Util.fcolorCCY(aveBean.getCurrYearMAve(2)) %> </div>
            </td>
            <td id="trclear" nowrap width="20%" height="19">
              <div align="right"><%= Util.fcolorCCY(aveBean.getPrevYearMAve(2)) %> </div>
            </td>
          </tr>
          <tr> 
            <td id="trdark" nowrap width="18%" height="19"> 
              <div align="right">Abril : </div>
            </td>
            <td id="trclear" nowrap width="22%" height="19"> 
              <div align="right"><%= Util.fcolorCCY(aveBean.getCurrYearMAve(3)) %> </div>
            </td>
            <td id="trdark" nowrap rowspan="3" height="57" width="20%"> 
              <div align="right"><%= Util.fcolorCCY(aveBean.getCurrYear3MAve(1)) %> </div>
            </td>
            <td id="trclear" nowrap width="20%" height="19">
              <div align="right"><%= Util.fcolorCCY(aveBean.getPrevYearMAve(3)) %> </div>
            </td>
            <td id="trdark" nowrap rowspan="3" height="57" width="20%"> 
              <div align="right"><%= Util.fcolorCCY(aveBean.getPrevYear3MAve(1)) %> </div>
            </td>
          </tr>
          <tr> 
            <td id="trdark" nowrap width="18%" height="19"> 
              <div align="right">Mayo : </div>
            </td>
            <td id="trclear" nowrap width="22%" height="19"> 
              <div align="right"><%= Util.fcolorCCY(aveBean.getCurrYearMAve(4)) %> </div>
            </td>
            <td id="trclear" nowrap width="20%" height="19">
              <div align="right"><%= Util.fcolorCCY(aveBean.getPrevYearMAve(4)) %> </div>
            </td>
          </tr>
          <tr> 
            <td id="trdark" nowrap width="18%" height="19"> 
              <div align="right">Junio : </div>
            </td>
            <td id="trclear" nowrap width="22%" height="19"> 
              <div align="right"><%= Util.fcolorCCY(aveBean.getCurrYearMAve(5)) %> </div>
            </td>
            <td id="trclear" nowrap width="20%" height="19">
              <div align="right"><%= Util.fcolorCCY(aveBean.getPrevYearMAve(5)) %> </div>
            </td>
          </tr>
          <tr> 
            <td id="trdark" nowrap width="18%" height="26"> 
              <div align="right">Julio : </div>
            </td>
            <td id="trclear" nowrap width="22%" height="26"> 
              <div align="right"><%= Util.fcolorCCY(aveBean.getCurrYearMAve(6)) %> </div>
            </td>
            <td id="trdark" nowrap rowspan="3" height="57" width="20%"> 
              <div align="right"><%= Util.fcolorCCY(aveBean.getCurrYear3MAve(2)) %> </div>
            </td>
            <td id="trclear" nowrap width="20%" height="26"> 
              <div align="right"><%= Util.fcolorCCY(aveBean.getPrevYearMAve(6)) %> </div>
            </td>
            <td id="trdark" nowrap rowspan="3" height="57" width="20%"> 
              <div align="right"><%= Util.fcolorCCY(aveBean.getPrevYear3MAve(2)) %> </div>
            </td>
          </tr>
          <tr> 
            <td id="trdark" nowrap width="18%" height="19"> 
              <div align="right">Agosto : </div>
            </td>
            <td id="trclear" nowrap width="22%" height="19"> 
              <div align="right"><%= Util.fcolorCCY(aveBean.getCurrYearMAve(7)) %> </div>
            </td>
            <td id="trclear" nowrap width="20%" height="19">
              <div align="right"><%= Util.fcolorCCY(aveBean.getPrevYearMAve(7)) %> </div>
            </td>
          </tr>
          <tr> 
            <td id="trdark" nowrap width="18%" height="19"> 
              <div align="right">Septiembre : </div>
            </td>
            <td id="trclear" nowrap width="22%" height="19"> 
              <div align="right"><%= Util.fcolorCCY(aveBean.getCurrYearMAve(8)) %> </div>
            </td>
            <td id="trclear" nowrap width="20%" height="19">
              <div align="right"><%= Util.fcolorCCY(aveBean.getPrevYearMAve(8)) %> </div>
            </td>
          </tr>
          <tr> 
            <td id="trdark" nowrap width="18%" height="19"> 
              <div align="right">Octubre : </div>
            </td>
            <td id="trclear" nowrap width="22%" height="19"> 
              <div align="right"><%= Util.fcolorCCY(aveBean.getCurrYearMAve(9)) %> </div>
            </td>
            <td id="trdark" nowrap rowspan="3" height="57" width="20%"> 
              <div align="right"><%= Util.fcolorCCY(aveBean.getCurrYear3MAve(3)) %> </div>
            </td>
            <td id="trclear" nowrap width="20%" height="19">
              <div align="right"><%= Util.fcolorCCY(aveBean.getPrevYearMAve(9)) %> </div>
            </td>
            <td id="trdark" nowrap rowspan="3" height="57" width="20%"> 
              <div align="right"><%= Util.fcolorCCY(aveBean.getPrevYear3MAve(3)) %> </div>
            </td>
          </tr>
          <tr> 
            <td id="trdark" nowrap width="18%" height="19"> 
              <div align="right">Noviembre : </div>
            </td>
            <td id="trclear" nowrap width="22%" height="19"> 
              <div align="right"><%= Util.fcolorCCY(aveBean.getCurrYearMAve(10)) %> </div>
            </td>
            <td id="trclear" nowrap width="20%" height="19">
              <div align="right"><%= Util.fcolorCCY(aveBean.getPrevYearMAve(10)) %> </div>
            </td>
          </tr>
          <tr> 
            <td id="trdark" nowrap width="18%" height="19"> 
              <div align="right">Diciembre : </div>
            </td>
            <td id="trclear" nowrap width="22%" height="19"> 
              <div align="right"><%= Util.fcolorCCY(aveBean.getCurrYearMAve(11)) %> </div>
            </td>
            <td id="trclear" nowrap width="20%" height="19">
              <div align="right"><%= Util.fcolorCCY(aveBean.getPrevYearMAve(11)) %> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>
