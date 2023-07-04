<html>
<head>
<title>N&uacute;mero de Comprobante Fiscal</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="intDetails" class="datapro.eibs.beans.ESD051501Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>


<H3 align="center">Asignaci&oacute;n de N&uacute;mero Comprobante Fiscal<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="NCR_parameter_maint.jsp, ESD0515"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSESD0515" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="10%" > 
              <div align="right"><b>C&oacute;digo Banco :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" readonly name="E01NCRBNK" size="4" maxlength="2"  value="<%= intDetails.getE01NCRBNK().trim()%>" >
              </div>
            </td>
            <td nowrap width="10%" > 
              <div align="right"><b>Sucursal:</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" readonly name="E01NCRBRN" size="4" maxlength="3"  value="<%= intDetails.getE01NCRBRN().trim()%>" >
              </div>
            </td>

            <td nowrap width="16%" > 
              <div align="right"><b></b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                </font></font></font></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n B&aacute;sica</h4>  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Serie :</div>
            </td>
            <td nowrap width="40%"> 
              <input type="text" name="E01NCRSER" maxlength="1" size="4" value="<%= intDetails.getE01NCRSER().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%"> 
              <div align="right">Divisi&oacute;n :</div>
            </td>
            <td nowrap width="40%"> 
              <input type="text" name="E01NCRDIV" maxlength="2" size="4" value="<%= intDetails.getE01NCRDIV().trim()%>" >
			   <a href="javascript:GetCode('E01NCRDIV','STATIC_ncf_division.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
            </td>
          </tr>	
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Zona/Sucursal :</div>
            </td>
            <td nowrap width="40%"> 
              <input type="text" name="E01NCRSUC" maxlength="3" size="4" value="<%= intDetails.getE01NCRSUC().trim()%>" >
 		  	  <a href="javascript:GetBranch('E01NCRSUC','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0"  ></a>       
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">Area de Impresi&oacute;n :</div>
            </td>
            <td width="58%" nowrap > 
              <input type="text" name="E01NCRAIC" maxlength="3" size="4" value="<%= intDetails.getE01NCRAIC().trim()%>" >
              <a href="javascript:GetCode('E01NCRAIC','STATIC_ncf_area_impresion.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
              </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Tipo de Comprobante :</div>
            </td>
            <td nowrap width="40%"> 
              <input type="text" name="E01NCRTCF" maxlength="2" size="4" value="<%= intDetails.getE01NCRTCF().trim()%>" >
              <a href="javascript:GetCode('E01NCRTCF','STATIC_ncf_tipo_comprobante.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
            </td>
          </tr>

        </table>
      </td>
    </tr>
  </table>
  <br>
          <div align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </div>
  </form>
</body>
</html>
