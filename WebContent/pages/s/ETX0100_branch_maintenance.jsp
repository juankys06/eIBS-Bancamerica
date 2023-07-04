<html> 
<head>
<title>Maestro de Agencias</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="brnDetails" class="datapro.eibs.beans.ETX010001Message"  scope="session" />

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


<H3 align="center">Mantenimiento de Timbres Fiscales <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="branch_maintenance.jsp, ETX0100"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSETX0100" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Banco :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01TXBBNK" size="3" maxlength="2"  value="<%= brnDetails.getE01TXBBNK().trim()%>" onKeypress="enterInteger()" >
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Oficina :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E01TXBBRN" size="4"  maxlength="3" value="<%= brnDetails.getE01TXBBRN().trim()%>" onKeypress="enterInteger()">
                <a href="javascript:GetBranch('E01TXBBRN',document.forms[0].E01TXBBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
                </font></font></font></div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Recaudadora :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E01TXBCDE" size="5"  maxlength="4" value="<%= brnDetails.getE01TXBCDE().trim()%>">
                <A href="javascript:GetCodeDescCNOFC('E01TXBCDE','E01TXBCDE','YV')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></A>
                </font></font></font></div>
            </td>
            
            
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Parametros de Recaudación</h4>  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Porcentaje :</div>
            </td>
            <td nowrap width="40%"> 
              <input type="text" name="E01TXBPCT" maxlength="10" size="10" value="<%= brnDetails.getE01TXBPCT().trim()%>" onKeypress="enterDecimal()" >
            </td>
            <td nowrap width="16%"> 
              <div align="right">Método de Pago:</div>
            </td>
            <td nowrap width="28%"> 
            <select name="E01TXBMTH">
                  <option value=" " <%if (brnDetails.getE01TXBMTH().equals(" ")) out.print("selected"); %>>         </option>
                  <option value="1" <%if (brnDetails.getE01TXBMTH().equals("1")) out.print("selected"); %>>Cuenta Cliente</option>
                  <option value="2" <%if (brnDetails.getE01TXBMTH().equals("2")) out.print("selected"); %>>Cuenta Contable</option>
                  <option value="3" <%if (brnDetails.getE01TXBMTH().equals("3")) out.print("selected"); %>>Cheque Gerencia</option>
                  <option value="4" <%if (brnDetails.getE01TXBMTH().equals("4")) out.print("selected"); %>>Mensaje Swift</option>
            </select>
           
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">Cuenta de Pago:</div>
            </td>
            <td nowrap height="23" colspan="1"> 
            <input type="text" name="E01TXBPBK" size="3" maxlength="2" value="<%= brnDetails.getE01TXBPBK().trim()%>" onKeypress="enterInteger()"> 
            &nbsp;
            <INPUT type="text" name="E01TXBPBR" size="4" maxlength="3" value="<%= brnDetails.getE01TXBPBR().trim()%>" onKeypress="enterInteger()">
            <a href="javascript:GetBranch('E01TXBPBR',document.forms[0].E01TXBPBK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
            &nbsp;
		    <INPUT type="text" name="E01TXBPCY" size="4" maxlength="3" value="<%= brnDetails.getE01TXBPCY().trim()%>">
            <a href="javascript:GetCurrency('E01TXBPCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" align="bottom" border="0"></a> 
		    &nbsp;
		    <INPUT type="text" name="E01TXBPGL" size="17" maxlength="16" value="<%= brnDetails.getE01TXBPGL().trim()%>">
		    <a href="javascript:GetLedger('E01TXBPGL',document.forms[0].E01TXBPBK.value,document.forms[0].E01TXBPCY.value,'')">
            <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
		    &nbsp;
		    <INPUT type="text" name="E01TXBPAC" size="13" maxlength="12" value="<%= brnDetails.getE01TXBPAC().trim()%>" onKeypress="enterInteger()">
		    <a href="javascript:GetAccByClient('E01TXBPAC',document.forms[0].E01TXBBNK.value,'RT','','')">
			<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
		    
		    </td>
            <td nowrap height="23" colspan="1"> 
              
            </td>
            <td nowrap height="23" colspan="1"> 
              
            </td>
            <td nowrap height="23" colspan="1"> 
              
            </td>
            <td nowrap height="23" colspan="1"> 
              
            </td>
            
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Tipo de Forma:</div>
            </td>
            <td nowrap height="19" colspan="3"> 
              <input type="text" name="E01TXBFTY" maxlength="2" size="1" value="<%= brnDetails.getE01TXBFTY().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Aplicantes :</div>
            </td>
            <td nowrap width="40%" height="19"> 
              <input type="text" name="E01TXBAPL" size="31" maxlength="30" value="<%= brnDetails.getE01TXBAPL().trim()%>" >
            </td>
            <td nowrap width="16%" height="19"> 
              <div align="right">Identificaci&oacute;n :</div>
            </td>
            <td nowrap width="28%" height="19"> 
              <input type="text" name="E01TXBAID" size="16" maxlength="15" value="<%= brnDetails.getE01TXBAID().trim()%>" >
            </td>
          </tr>
          
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right"></div>
            </td>
            <td nowrap width="40%" height="19"> 
              <input type="text" name="E01TXBAP1" size="31" maxlength="30" value="<%= brnDetails.getE01TXBAP1().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right"></div>
            </td>
            <td nowrap width="40%" height="19"> 
              <input type="text" name="E01TXBAP2" size="31" maxlength="30" value="<%= brnDetails.getE01TXBAP2().trim()%>" >
            </td>
          </tr>
          
             
          
          
          
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              Beneficiarios:</td>
            <td nowrap width="40%" height="19"> 
              <input type="text" name="E01TXBBNF" size="31" maxlength="30" value="<%= brnDetails.getE01TXBBNF().trim()%>">
			</td>
            <td nowrap width="16%" height="19"> 
              <div align="right">Identificación :</div>
            </td>
            <td nowrap width="28%" height="19"> 
              <input type="text" name="E01TXBBID" size="16" maxlength="15" value="<%= brnDetails.getE01TXBBID().trim()%>">
            </td>
          </tr>
          
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              </td>
            <td nowrap width="40%" height="19"> 
              <input type="text" name="E01TXBBN1" size="31" maxlength="30" value="<%= brnDetails.getE01TXBBN1().trim()%>">
			</td>
			<td nowrap width="16%" height="19"> 
			<div align="right"> </div>
			</td>
		    <td nowrap width="28%" height="19"> 
			<div align="right"> </div>
			</td>
          </tr>
          
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              </td>
            <td nowrap width="40%" height="19"> 
              <input type="text" name="E01TXBBN2" size="31" maxlength="30" value="<%= brnDetails.getE01TXBBN2().trim()%>">
			</td>
			<td nowrap width="16%" height="19"> 
			<div align="right"> </div>
			</td>
		    <td nowrap width="28%" height="19"> 
			<div align="right"> </div>
			</td>
          </tr>
          
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Detalle de Pago:</div>
            </td>
            <td nowrap width="40%" height="19"> 
              <input type="text" name="E01TXBDTP" size="31" maxlength="30" value="<%= brnDetails.getE01TXBDTP().trim()%>">
            </td>
            <td nowrap width="16%" height="19"> 
              <div align="right">Contabiliza :</div>
            </td>
            <td nowrap width="28%" height="19"> 
              <input type="radio" name="E01TXBFLG" value="Y"  <%if ((brnDetails.getE01TXBFLG().equals("Y")) ||(brnDetails.getE01TXBFLG().equals(" ")) ) out.print("checked"); %>>
              Si 
              <input type="radio" name="E01TXBFLG" value="N"  <%if (brnDetails.getE01TXBFLG().equals("N")) out.print("checked"); %>>
              No</td>
          </tr>
         
          <tr id="trclear"> 
 			<td nowrap width="16%" height="19"> 
              <div align="right"></div>
            </td>         
            <td nowrap width="40%" height="19"> 
              <input type="text" name="E01TXBDP1" size="31" maxlength="30" value="<%= brnDetails.getE01TXBDP1().trim()%>">
            </td>
          </tr>
          
		<tr id="trclear"> 
		<td nowrap width="16%" height="19"> 
              <div align="right"></div>
            </td>
            <td nowrap width="40%" height="19"> 
              <input type="text" name="E01TXBDP2" size="31" maxlength="30" value="<%= brnDetails.getE01TXBDP2().trim()%>">
            </td>
        </tr>
 		 <tr id="trdark"> 
            <td nowrap width="16%" height="19"><div align="right">Corresponsal:</div>
            </td>
            <td nowrap width="40%" height="19"> 
              <input type="text" name="E01TXBCOR" size="10" maxlength="9" value="<%= brnDetails.getE01TXBCOR().trim()%>" onKeypress="enterInteger()">
              <input type="text" name="E01REQCON" readonly size="35" maxlength="35" value="<%= brnDetails.getE01REQCON().trim()%>" readonly>
            <a href="javascript:GetCorrespondentDescId('E01TXBCOR','E01REQCON','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>		
			</td>
			<td nowrap width="16%" height="19"> 
			<div align="right"> </div>
			</td>
		    <td nowrap width="28%" height="19"> 
			<div align="right"> </div>
			</td>	
         </tr>
       
          
          </table>
      </td>
    </tr>
  </table>
  
          <div align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </div>
  </form>
</body>
</html>
