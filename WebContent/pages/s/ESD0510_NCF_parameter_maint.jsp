<html>
<head>
<title>N&uacute;mero de Comprobante Fiscal</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="intDetails" class="datapro.eibs.beans.ESD051001Message"  scope="session" />
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


<H3 align="center">N&uacute;mero de Comprobante Fiscal<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="NCF_parameter_maint.jsp, ESD0510"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSESD0510" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>C&oacute;digo Banco :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" readonly name="E01NCFBNK" size="3" maxlength="2"  value="<%= intDetails.getE01NCFBNK().trim()%>" >
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
              <input type="text" name="E01NCFSER" maxlength="1" size="4" value="<%= intDetails.getE01NCFSER().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%"> 
              <div align="right">Divisi&oacute;n :</div>
            </td>
            <td nowrap width="40%"> 
              <input type="text" name="E01NCFDIV" maxlength="2" size="4"  value="<%= intDetails.getE01NCFDIV().trim()%>" >
			   <a href="javascript:GetCode('E01NCFDIV','STATIC_ncf_division.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
            </td>
          </tr>	
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Zona/Sucursal :</div>
            </td>
            <td nowrap width="40%"> 
              <input type="text" name="E01NCFBRN" maxlength="3" size="4" value="<%= intDetails.getE01NCFBRN().trim()%>" >
 		  	  <a href="javascript:GetBranch('E01NCFBRN','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0"  ></a>       
            </td>
          </tr>
          
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">Area de Impresi&oacute;n :</div>
            </td>
            <td width="58%" nowrap > 
              <input type="text" name="E01NCFAIC" maxlength="3" size="4" value="<%= intDetails.getE01NCFAIC().trim()%>" >
              <a href="javascript:GetCode('E01NCFAIC','STATIC_ncf_area_impresion.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
              </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Tipo de Comprobante :</div>
            </td>
            <td nowrap width="40%"> 
              <input type="text" name="E01NCFTCF" maxlength="2" size="4" value="<%= intDetails.getE01NCFTCF().trim()%>" >
              <a href="javascript:GetCode('E01NCFTCF','STATIC_ncf_tipo_comprobante.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">Secuencial Inicial :</div>
            </td>
            <td width="58%" nowrap > 
              <input type="text" name="E01NCFSQI" maxlength="8" size="12" value="<%= intDetails.getE01NCFSQI().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">Secuencial Final :</div>
            </td>
            <td width="58%" nowrap > 
              <input type="text" name="E01NCFSQF" maxlength="8" size="12" value="<%= intDetails.getE01NCFSQF().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">Secuencial Utilizado :</div>
            </td>
            <td width="58%" nowrap > 
              <input type="text" name="E01NCFLSQ" maxlength="8" size="12" value="<%= intDetails.getE01NCFLSQ().trim()%>" >
            </td>
          </tr>

          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Status :</div>
            </td>
            <td width="58%" nowrap > 
              <select name="E01NCFSTS">
                <option value=A <% if (intDetails.getField("E01NCFSTS").getString().equals("A")) out.print("selected"); %>>Actual</option>
                <option value=C <% if (intDetails.getField("E01NCFSTS").getString().equals("C")) out.print("selected"); %>>Agotado</option>
                <option value="" <% if (intDetails.getField("E01NCFSTS").getString().equals("")) out.print("selected"); %>></option>
              </select>
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
