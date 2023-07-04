<html>
<head>
<title>Cajero</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="brnDetails" class="datapro.eibs.beans.EDD031001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<jsp:useBean id= "EDD031001Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />


<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }

    int row;
    String sTitle = "";
    int sValue = 600;
    int sOption = 0;
    String sLabel = "";
     
   if (request.getParameter("SCREEN") != null){
      sValue = Integer.parseInt(request.getParameter("SCREEN"));
      if (sValue == 600)
        sTitle = "Mantenimiento Cajero";                
      else
       	sTitle = "Nuevo Cajero";                
   } 
%>

<H3 align="center"><%=sTitle%> <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="teller_maintenance.jsp, EDD0310"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.teller.JSEDD0310" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="<%=sValue%>">
  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Identificación :</b></div>
            </td>
            <td nowrap width="14%"> 
              <div align="left"> 
                <input type="text" name="E01TLMTID" size="3"
					   maxlength="3" value="<%= brnDetails.getE01TLMTID().trim()%>"
					   readonly>
              </div>
            </td>
            <td nowrap width="20%"> 
              <div align="right"><b>Moneda :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E01TLMCCY" size="3"
					   maxlength="3" value="<%= brnDetails.getE01TLMCCY().trim()%>" 
					   readonly> 
                </font></font></font></div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Tipo :</b></div>
            </td>
            <td nowrap width="40%"> 
              <div align="left"> 
				<%String sType = "";
				  if (brnDetails.getE01TLMTYP().equals("T")) {sType = "Regular"; sOption = 1;
				  } else if (brnDetails.getE01TLMTYP().equals("H")) {sType = "Principal"; sOption = 2; 
				  } else if (brnDetails.getE01TLMTYP().equals("O")) {sType = "Oficial S/G"; sOption = 3;
				  } else if (brnDetails.getE01TLMTYP().equals("S")) {sType = "Supervisor Operaciones"; sOption = 4;
				  } else if (brnDetails.getE01TLMTYP().equals("I")) {sType = "Cajero Interface"; sOption = 5;
				  }%>  
                <input type="text" name="TMPTLMTYP" size="25" maxlength="22" value = "<%=sType%>"  readonly>
                <input type=HIDDEN name="E01TLMTYP" size="1" maxlength="3" value="<%= brnDetails.getE01TLMTYP().trim()%>" readonly>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  <div id="maintenace" style="visibility:visible">
  <h4>Información Cajero</h4>  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">Banco Cajero :</div>
            </td>
            <td nowrap height="23" colspan="3"> 
              <input type="text" name="E01TLMBNK" size="2"	
                     maxlength="2" value="<%= brnDetails.getE01TLMBNK().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">Agencia Cajero :</div>
            </td>
            <td nowrap height="23" colspan="3"> 
              <input type="text" name="E01TLMBRN" size="3"	
                     maxlength="3" value="<%= brnDetails.getE01TLMBRN().trim()%>">
              <a href="javascript:GetBranch('E01TLMBRN',document.forms[0].E01TLMBNK.value)">
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="bottom" border="0"  ></a>
              
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="23">
              <% switch (sOption){
                   case 5:
                   case 1: sLabel = "Efectivo Máximo Permitido :";
                           break;
                   default: sLabel = "Monto Sobregiro :";
                            break;  
              } 
              %> 
              <div align="right"><%=sLabel%></div>
            </td>
            <td nowrap height="23" colspan="3"> 
              <input type="text" name="E01TLMMXC" size="13"	
                     maxlength="13" value="<%= brnDetails.getE01TLMMXC().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <% switch (sOption){
                   case 5:
                   case 1: sLabel = "Límite de Retiros en Efectivo :";
                           break;
                   default: sLabel = "Monto Retiros :";
                            break;  
              } 
              %> 
              <div align="right"><%=sLabel%></div>
            </td>
            <td nowrap height="23" colspan="3"> 
              <input type="text" name="E01TLMFLW" size="13"	
                     maxlength="13" value="<%= brnDetails.getE01TLMFLW().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="23"> 
              <% switch (sOption){
                   case 5:
                   case 1: sLabel = "Límite de Depósitos Efectivo :";
                           break;
                   default: sLabel = "Monto Depósitos :";
                            break;  
              } 
              %> 
              <div align="right"><%=sLabel%></div>
            </td>
            <td nowrap height="23" colspan="3"> 
              <input type="text" name="E01TLMFLD" size="13"	
                     maxlength="13" value="<%= brnDetails.getE01TLMFLD().trim()%>" >
            </td>
          </tr>
          <% if (sOption == 1 || sOption == 5){%>
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">Cuenta Contable :</div>
            </td>
            <td nowrap height="23" colspan="3"> 
              <input type="text" name="E01TLMGLN" size="13"
					 maxlength="12" value="<%= brnDetails.getE01TLMGLN().trim()%>">
              <a href="javascript:GetLedger('E01TLMGLN',document.forms[0].E01TLMBNK.value,document.forms[0].E01TLMCCY.value,'')">
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="16%" height="23"> 
              <div align="right">Cheque ON-US G/L No. (No Retenidos) :</div>
            </td>
            <td nowrap height="23" colspan="3"> 
              <input type="text" name="E01TLMONG" size="13"	
                     maxlength="12" value="<%= brnDetails.getE01TLMONG().trim()%>">
              <a href="javascript:GetLedger('E01TLMONG',document.forms[0].E01TLMBNK.value,document.forms[0].E01TLMCCY.value,'')">
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">Cheque ON-US G/L No. (Con Retenciones) :</div>
            </td>
            <td nowrap height="23" colspan="3"> 
              <input type="text" name="E01TLMOHG" size="13"	
                     maxlength="12" value="<%= brnDetails.getE01TLMOHG().trim()%>">
              <a href="javascript:GetLedger('E01TLMOHG',document.forms[0].E01TLMBNK.value,document.forms[0].E01TLMCCY.value,'')">
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="16%" height="23"> 
              <div align="right">Cuenta Contable Cheque :</div>
            </td>
            <td nowrap height="23" colspan="3"> 
              <input type="text" name="E01TLMLOG" size="13"	
                     maxlength="12" value="<%= brnDetails.getE01TLMLOG().trim()%>">
              <a href="javascript:GetLedger('E01TLMLOG',document.forms[0].E01TLMBNK.value,document.forms[0].E01TLMCCY.value,'')">
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">Cuenta Contable de Cheque No Local :</div>
            </td>
            <td nowrap height="23" colspan="3"> 
              <input type="text" name="E01TLMNLG" size="13"	
                     maxlength="12" value="<%= brnDetails.getE01TLMNLG().trim()%>">
              <a href="javascript:GetLedger('E01TLMNLG',document.forms[0].E01TLMBNK.value,document.forms[0].E01TLMCCY.value,'')">
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="16%" height="23"> 
              <div align="right">No. Lote Cajero :</div>
            </td>
            <td nowrap height="23" colspan="3"> 
              <input type="text" name="E01TLMBTH" size="4"	
                     maxlength="4" value="<%= brnDetails.getE01TLMBTH().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">No. Centro Costo Cajero :</div>
            </td>
            <td nowrap height="23" colspan="3"> 
              <input type="text" name="E01TLMCST" size="6"	
                     maxlength="6" value="<%= brnDetails.getE01TLMCST().trim()%>">
              <a href="javascript:GetCostCenter('E01TLMCST',document.forms[0].E01TLMBNK.value)">
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
            </td>
          </tr>
          <%}%>
        </table>
      </td>
    </tr>
  </table>
  </div>  
  
  <br>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
  
  </form>
</body>
</html>
