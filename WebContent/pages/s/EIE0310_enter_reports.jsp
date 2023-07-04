<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>


<META name="GENERATOR" content="IBM WebSphere Studio">
</head>
<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }


%>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIE0310" >
	
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
 	
  <INPUT TYPE=HIDDEN NAME="REPTYP" VALUE="3">
  
  <h3 align="center">Reportes Banca Privada</h3>
  <table class="tbenter" height="55%" width="0%" border="0">
    <tr> 
      <td nowrap align="center"> 
        <table class="tableinfo" align="center" width="100%" >
          <tr> 
            <td> 
              <table width="100%">
                <tr id="trdark"> 
                  <td width="20%" nowrap >&nbsp;</td>
                  <td width="70%" nowrap ><b>Reportes por :</b></td>
                </tr>
                <tr id="trclear"> 
                  <td width="20%" nowrap > 
                    <div align="right"></div>
                  </td>
                  <td width="70%" nowrap > 
                    <div align="left"> <a href="javascript:invReports('R04DATAPRO','f:\\iis\\dummy\\eibsreports\\reports\\','ibs_inv_instruments.rpt')">Instrumentos 
                      Inversi&oacute;n</a> </div>
                  </td>
                </tr>
                <tr id="trdark"> 
                  <td width="20%" nowrap >&nbsp;</td>
                  <td width="70%" nowrap > 
                    <div align="left"> <a href="javascript:invReports('R04DATAPRO','f:\\iis\\dummy\\eibsreports\\reports\\','ibs_out_by_custodian.rpt')"> 
                      Posici&oacute;n Vigente por Custodio</a></div>
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td width="20%" nowrap >&nbsp;</td>
                  <td width="70%" nowrap > 
                    <div align="left"> <a href="javascript:invReports('R04DATAPRO','f:\\iis\\dummy\\eibsreports\\reports\\','ibs_out_by_customer.rpt')"> 
                      </a><a href="javascript:invReports('R04DATAPRO','f:\\iis\\dummy\\eibsreports\\reports\\','ibs_out_by_custodian.rpt')">Posici&oacute;n 
                      Vigente por Cliente</a> </div>
                  </td>
                </tr>
                <tr id="trdark"> 
                  <td width="20%" nowrap >&nbsp;</td>
                  <td width="70%" nowrap > <a href="javascript:invReports('R04DATAPRO','f:\\iis\\dummy\\eibsreports\\reports\\','ibsstmt_en.rpt')"> 
                    Estado de Cuenta (Ingl&eacute;s) </a> </td>
                </tr>
                <tr id="trclear"> 
                  <td width="20%" nowrap >&nbsp;</td>
                  <td width="70%" nowrap > <a href="javascript:invReports('R04DATAPRO','f:\\iis\\dummy\\eibsreports\\reports\\','ibsstmt_es.rpt')"> 
                    </a><a href="javascript:invReports('R04DATAPRO','f:\\iis\\dummy\\eibsreports\\reports\\','ibsstmt_en.rpt')">Estado 
                    de Cuenta</a><a href="javascript:invReports('R04DATAPRO','f:\\iis\\dummy\\eibsreports\\reports\\','ibsstmt_es.rpt')">(Espa&ntilde;ol) 
                    </a> </td>
                </tr>
              </table>
            </td>
          </tr>
        </table>

        
      </td>
    </tr>
  </table>

      
</form>
</body>
</html>
