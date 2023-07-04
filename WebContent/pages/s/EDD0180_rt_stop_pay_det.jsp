<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Emision de Ordenes de No Pago</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "stop" class= "datapro.eibs.beans.JBListRec"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<body>


<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 
        int row;
		  try{row = Integer.parseInt(request.getParameter("ROW"));}catch(Exception e){row = 0;}
		  stop.setCurrentRow(row);

%>


<H3 align="center">Emisi&oacute;n de Ordenes de No Pago<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="rt_stop_pay_det.jsp , EDD0180"> 
</H3><hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0180" >
  <input type=HIDDEN name="SCREEN" value="4">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="2">

  <input type=HIDDEN name="E01STPBNK" value="<%= userPO.getHeader10().trim()%>">
  <input type=HIDDEN name="E01STPBRN" value="<%= userPO.getHeader11().trim()%>">
  <input type=HIDDEN name="E01STPGLN" value="<%= userPO.getHeader12().trim()%>">
  <input type=HIDDEN name="E01STPDT1" value="<%=stop.getRecord(3).trim()%>">
  <input type=HIDDEN name="E01STPDT2" value="<%=stop.getRecord(4).trim()%>">
  <input type=HIDDEN name="E01STPDT3" value="<%=stop.getRecord(5).trim()%>">
   
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"><b> 
                <input type="text" readonly name="E01STPCUN" size="9" maxlength="9" value="<%= userPO.getHeader1().trim()%>" >
                </b> </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E02NA1" size="45" maxlength="45" readonly value="<%= userPO.getHeader5().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01STPACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01STPCCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E02PRO" size="4" maxlength="4" readonly value="<%= userPO.getHeader6().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>                
 <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trclear">
            <td nowrap width="34%">
              <div align="right">Secuencia :</div>
            </td>
            <td nowrap colspan="3">
              <input type="text" name="E01STPSEQ" size="4" readonly maxlength="3" value="<%=stop.getRecord(0).trim()%>">              
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="34%"> 
              <div align="right">N&uacute;mero de Cheque :</div>
            </td>
            <td nowrap width="15%"> 
              <div align="left"> 
                <input type="text" name="E01STPFCK" size="10" maxlength="9" value="<%=stop.getRecord(1).trim()%>" onKeypress="enterInteger()">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Hasta Cheque :</div>
            </td>
            <td nowrap width="25%" >
              <input type="text" name="E01STPTCK" size="10" maxlength="9" value="<%=stop.getRecord(2).trim()%>" onKeypress="enterInteger()">
              (Si Rango)</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Fecha del Cheque :</div>
            </td>
            <td nowrap colspan="3"> 
              <div align="left"> 
                <input type="text" name="E01STPCK1" size="3" maxlength="2" value="<%=stop.getRecord(14).trim()%>">
                <input type="text" name="E01STPCK2" size="3" maxlength="2" value="<%=stop.getRecord(15).trim()%>">
                <input type="text" name="E01STPCK3" size="3" maxlength="2" value="<%=stop.getRecord(16).trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Importe del Cheque :</div>
            </td>
            <td nowrap colspan="3"> 
              <div align="left"> 
                <input type="text" name="E01STPAMT" size="15" maxlength="15" value="<%=stop.getRecord(7).trim()%>" onKeypress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Comentarios :</div>
            </td>
            <td nowrap colspan="3"> 
              <input type="text" name="E01STPRMK" size="35" maxlength="30" value="<%=stop.getRecord(8).trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Transacci&oacute;n Tipo ACH :</div>
            </td>
            <td nowrap  colspan="3"> 
              <input type="radio" name="E01STPFLG" value="Y"
			  <%if(stop.getRecord(9).equals("Y")) out.print("checked");%>>
              S&iacute; 
              <input type="radio" name="E01STPFLG" value="N"
			  <%if(!stop.getRecord(9).equals("Y")) out.print("checked");%>>
              No <font size="2"> (*)</font> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Cargos al Cliente :</div>
            </td>
            <td nowrap colspan="3"> 
              <input type="radio" name="E01STPCCF" value="Y"
			  <%if(stop.getRecord(10).equals("Y")) out.print("checked");%>>
              S&iacute; 
              <input type="radio" name="E01STPCCF" value=""
			  <%if(stop.getRecord(10).trim().equals("")) out.print("checked");%>>
              No</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Sub - Cuenta N&uacute;mero :</div>
            </td>
            <td nowrap colspan="3"> 
              <input type="text" name="E01STPPTS" size="7" maxlength="5" value="<%=stop.getRecord(11).trim()%>" onKeypress="enterInteger()">
              <a href="javascript:GetSubAcc('E01STPPTS',document.forms[0].E01STPACC.value )"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Eliminaci&oacute;n en 180 D&iacute;as :</div>
            </td>
            <td nowrap colspan="3"> 
              <input type="radio" name="E01STPF04" value="Y"
			  <%if(stop.getRecord(12).equals("Y")) out.print("checked");%>>
              S&iacute; 
              <input type="radio" name="E01STPF04" value="N"
			  <%if(!stop.getRecord(12).equals("Y")) out.print("checked");%>>
              No </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Motivo :</div>
            </td>
            <td nowrap colspan="3"> 
              <input type="text" name="E01STPF02" size="5" maxlength="4" value="<%=stop.getRecord(13).trim()%>">
              <a href="javascript:GetCodeDescCNOFC('E01STPF02','D01DSCCAU','51')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a>             
              <input type="text" name="D01DSCCAU" size="35" maxlength="35" value="<%=stop.getRecord(17).trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Ratificado por el Cliente :</div>
            </td>
            <td nowrap colspan="3"> 
              <input type="radio" name="E01STPF05" value="Y"
			  <%if(stop.getRecord(18).equals("Y")) out.print("checked");%>>
              S&iacute; 
              <input type="radio" name="E01STPF05" value="N"
			  <%if(!stop.getRecord(18).trim().equals("Y")) out.print("checked");%>>
              No</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="4"><b>(*) = &quot;Si&quot; 
              Transacci&oacute;n Tipo ACH se rechazar&aacute;</b>
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
