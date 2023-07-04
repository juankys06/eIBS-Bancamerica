<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>
<jsp:useBean id="accrual" class="datapro.eibs.beans.EGL081501Message"  scope="session" />

<META name="GENERATOR" content="IBM WebSphere Studio">

<SCRIPT Language="javascript">


</SCRIPT>

</head>
<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
    }
%> 
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEGL0815" >
	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">


<h3 align="center">Mantenimiento de Amortizaciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="amort_accrual_enter.jsp, EGL0815"></h3>
<hr size="4">
  <br><br><br>
  <table class="tableinfo"  cellspacing="0" cellpadding="2" width="100%" border="0">
    <tr id="trdark"> 
      <td valign="middle" align="center" width="49%"> 
        <div align="right">Banco : </div>
      </td>
      <td valign="middle" align="center" width="51%"> 
        <div align="left"> 
          <input type="text" name="E01AMOBNK"  size="3" maxlength="2" value="<%= accrual.getE01AMOBNK().trim()%>" onkeypress="enterInteger()" >
        </div>
      </td>
    </tr>
    <tr id="trclear"> 
      <td valign="middle" align="center" width="49%"> 
        <div align="right">Sucursal : </div>
      </td>
      <td valign="middle" align="center" width="51%"> 
        <div align="left"> 
          <input type="text" name="E01AMOBRN"  size="5" maxlength="4" value="<%= accrual.getE01AMOBRN().trim()%>" onkeypress="enterInteger()" >
          <a href="javascript:GetBranch('E01AMOBRN','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"  ></a>
        </div>
      </td>
    </tr>
    <tr id="trdark"> 
      <td valign="middle" align="center" width="49%"> 
        <div align="right">Moneda : </div>
      </td>
      <td valign="middle" align="center" width="51%"> 
        <div align="left"> 
          <input type="text" name="E01AMOCCY"  size="4" maxlength="3" value="<%= accrual.getE01AMOCCY().trim()%>"  >
          <a href="javascript:GetCurrency('E01AMOCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0"></a> 
        </div>
      </td>
    </tr>
    <tr id="trclear"> 
      <td valign="middle" align="center" width="49%"> 
        <div align="right">Cuenta Contable : </div>
      </td>
      <td valign="middle" align="center" width="51%"> 
        <div align="left"> 
          <input type="text" name="E01AMOGLN"  size="19" maxlength="17" value="<%= accrual.getE01AMOGLN().trim()%>"  >
          <a href="javascript:GetLedger('E01AMOGLN',document.forms[0].E01AMOBNK.value,document.forms[0].E01AMOCCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"  ></a>
        </div>
      </td>
    </tr>
    <tr id="trdark"> 
      <td valign="middle" align="center" width="49%"> 
        <div align="right">Referencia : </div>
      </td>
      <td valign="middle" align="center" width="51%"> 
        <div align="left"> 
          <input type="text" name="E01AMOACC"  size="15" maxlength="13" value="<%= accrual.getE01AMOACC().trim()%>"  >
           <a href="javascript:GetAccount('E01AMOACC',document.forms[0].E01AMOBNK.value,'RT','')">
			<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
		   </a>
        </div>
      </td>
    </tr>
    <tr id="trclear"> 
      <td valign="middle" align="center" width="49%"> 
        <div align="right"> Amortización en curso :</div>
      </td>
      <td valign="middle" align="center" width="51%"> 
        <div align="left">
          	<a href="javascript:GetAmortization('E01AMOBNK','E01AMOBRN','E01AMOCCY','E01AMOGLN','E01AMOACC','')">
				<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
			</A>
        </div>
      </td>
    </tr>
    
  </table>
  <br>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
          <tr bgcolor="#FFFFFF"> 
            <td width="33%"> 
              <div align="center"> 
                <input id="EIBSBTN" type=submit name="Submit" value="Enviar" >
              </div>
            </td>
          </tr>
          <tr bgcolor="#FFFFFF"> 
            <td> 
              <div align="center"> </div>
            </td>
          </tr>
        </table>
              
</form>
</body>
</html>
