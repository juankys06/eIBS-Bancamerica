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
		  try{row = Integer.parseInt(request.getParameter("ROW"));
			}
			catch(Exception e){row = 0;}
		  stop.setCurrentRow(row);
    
%>


<H3 align="center">Emisi&oacute;n de Ordenes de No Pago
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="rt_stop_pay_det.jsp , EDD0180"></H3> 
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0180" >
  <input type=HIDDEN name="SCREEN" value="9">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="2">
  <input type=HIDDEN name="ROW" value="<%= row %>">
  <input type=HIDDEN name="E01STPBNK" value="<%= userPO.getBank().trim()%>">
  <input type=HIDDEN name="E01STPCCY" value="<%= userPO.getCurrency().trim()%>">
  <input type=HIDDEN name="E01STPBRN" value="<%= stop.getRecord(17).trim()%>">
  <input type=HIDDEN name="E01STPGLN" value="<%= userPO.getIdentifier().trim()%>">
  <input type=HIDDEN name="E01STPDT1" value="<%=stop.getRecord(3).trim()%>">
  <input type=HIDDEN name="E01STPDT2" value="<%=stop.getRecord(4).trim()%>">
  <input type=HIDDEN name="E01STPDT3" value="<%=stop.getRecord(5).trim()%>">
 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Formato :</b></div>
            </td>
            <td nowrap width="7%" > 
              <div align="left"> 
                <input type="text" size="9" maxlength="9" name="FTY" value="<%= userPO.getHeader11().trim()%>" readonly>
              </div>
            </td>
            <td nowrap colspan="3" width="77%" > 
              <div align="left"> 
                <input type="text" size="45" maxlength="35" name="DSC" value="<%= userPO.getHeader12().trim()%>" readonly>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p><b> </b></p>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="44%"> 
              <div align="right">Secuencia :</div>
            </td>
            <td nowrap ><b> 
              <input type="text" name="E01STPSEQ" size="4" readonly maxlength="3" value="<%=stop.getRecord(0).trim()%>">
              </b></td>
            
          </tr>
          <tr id="trdark"> 
            <td nowrap width="44%" > 
              <div align="right">N&uacute;mero de Cheque :</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E01STPFCK" size="10" maxlength="9" value="<%=stop.getRecord(1).trim()%>" onKeypress="enterInteger()">
              </div>
            </td>
            
          </tr>
          <tr id="trclear"> 
            <td nowrap width="44%" > 
              <div align="right">Fecha del Cheque :</div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01STPCK1" size="3" maxlength="2" value="<%=stop.getRecord(14).trim()%>">
                <input type="text" name="E01STPCK2" size="3" maxlength="2" value="<%=stop.getRecord(15).trim()%>">
                <input type="text" name="E01STPCK3" size="3" maxlength="2" value="<%=stop.getRecord(16).trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="44%" > 
              <div align="right">Importe del Cheque :</div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01STPAMT" size="15" maxlength="15" value="<%=stop.getRecord(7).trim()%>" onKeypress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="44%" > 
              <div align="right">Comentarios :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01STPRMK" size="35" maxlength="30" value="<%=stop.getRecord(8).trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="44%"> 
              <div align="right">Eliminaci&oacute;n en 180 D&iacute;as :</div>
            </td>
            <td nowrap >
              <input type="radio" name="E01STPF04" value="Y" <%if(!stop.getRecord(12).equals("N")) out.print("checked");%>>
              S&iacute; 
              <input type="radio" name="E01STPF04" value="N" <%if(stop.getRecord(12).equals("N")) out.print("checked");%>>
              No </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="44%" > 
              <div align="right">Motivo :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01STPF02" size="5" maxlength="4" value="<%=stop.getRecord(13).trim()%>">
              <a href="javascript:GetCodeDescCNOFC('E01STPF02','D01DSCCAU','51')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a>             
              <input type="text" name="D01DSCCAU" size="35" maxlength="35" value="<%=stop.getRecord(18).trim()%>" readonly>
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
