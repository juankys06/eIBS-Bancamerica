<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Amortización/Acumulado </title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="acc" class="datapro.eibs.beans.EGL081502Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body> 

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

	builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
 //  Process according with user selection
 function goAction(op) {

   	switch (op){
	// Validate & Update
  	case 1:  {
    	document.forms[0].OPTION.value = 'U';
		document.forms[0].submit();
       	break;
        }
	// Stop Amortization
	case 2: {
		if (!confirm("Desea Suspender esta Amortización?")) {
			return
		}
		document.forms[0].OPTION.value = 'S';
		document.forms[0].submit();
       	break;
		}
	case 3:  {
	// Delete Amortization
		if (!confirm("Desea Borrar esta Amortización?")) {
			return
		}
		document.forms[0].OPTION.value = 'D';
		document.forms[0].submit();
       	break;
		}
  	default:  {
		document.forms[0].OPTION.value = 'U';
		document.forms[0].submit();
       	break;
		}

    }  
 }
// end hiding from old browsers -->

</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
  

%> 
<H3 align="center">Mantenimiento de Amortización<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="amort_accrual_basic, EGL0815"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEGL0815" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="3">
  <INPUT TYPE=HIDDEN NAME="OPTION" value="U">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap  > 
              <div align="right"><b>Banco :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E02AMOBNK" size="4" maxlength="3" value="<%= acc.getE02AMOBNK().trim()%>" readonly>
               </div>
            </td>
            <td nowrap  > 
              <div align="right"><b>Cuenta Contable :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E02AMOGLN" size="19" maxlength="17" readonly value="<%= acc.getE02AMOGLN().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap  > 
              <div align="right"><b>Sucursal :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E02AMOBRN" size="5" maxlength="4" value="<%= acc.getE02AMOBRN().trim()%>" readonly>
               </div>
            </td>
            <td nowrap  > 
              <div align="right"><b>Referencia :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E02AMOACC" size="19" maxlength="17" readonly value="<%= acc.getE02AMOACC().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap  > 
              <div align="right"><b>Moneda :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E02AMOCCY" size="4" maxlength="3" value="<%= acc.getE02AMOCCY().trim()%>" readonly>
               </div>
            </td>
            <td nowrap  > 
              <div align="right"><b>Centro Costo :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E02AMOCCN" size="10" maxlength="9" value="<%= acc.getE02AMOCCN().trim()%>" >
              		<A href="javascript:GetCostCenter('E02AMOCCN',document.forms[0].E02AMOBNK.value)">
					<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0"></A></div>
            </td>
          </tr>

        </table>
      </td>
    </tr>
  </table>
<H4>Información Básica</H4>
<table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="30%" > 
              <div align="right">Descripción :</div>
            </td>
            <td nowrap width="70%" > 
      			<input type="text" name="E02AMONAR" size="35" maxlength="30" value="<%= acc.getE02AMONAR().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%" > 
              	<div align="right">Monto :</div>
            </td>
            <td nowrap width="70%" > 
           		<input type="text" name="E02AMOOAM" size="17" maxlength="15" value="<%= acc.getE02AMOOAM().trim()%>" onkeypress="enterDecimal()">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%" > 
              	<div align="right">Fecha Valor :</div>
            </td>
            <td nowrap width="70%" > 
           		<input type="text" name="E02AMOVL1" size="3" maxlength="2" value="<%= acc.getE02AMOVL1().trim()%>" onkeypress="enterInteger()">
           		<input type="text" name="E02AMOVL2" size="3" maxlength="2" value="<%= acc.getE02AMOVL2().trim()%>" onkeypress="enterInteger()">
           		<input type="text" name="E02AMOVL3" size="3" maxlength="2" value="<%= acc.getE02AMOVL3().trim()%>" onkeypress="enterInteger()">
           		<a href="javascript:DatePicker(document.forms[0].E02AMOVL1,document.forms[0].E02AMOVL2,document.forms[0].E02AMOVL3)">
           		<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%" > 
              	<div align="right">Fecha Vencimiento :</div>
            </td>
            <td nowrap width="70%" > 
           		<input type="text" name="E02AMOMD1" size="3" maxlength="2" value="<%= acc.getE02AMOMD1().trim()%>" onkeypress="enterInteger()">
           		<input type="text" name="E02AMOMD2" size="3" maxlength="2" value="<%= acc.getE02AMOMD2().trim()%>" onkeypress="enterInteger()">
           		<input type="text" name="E02AMOMD3" size="3" maxlength="2" value="<%= acc.getE02AMOMD3().trim()%>" onkeypress="enterInteger()">
           		<a href="javascript:DatePicker(document.forms[0].E02AMOMD1,document.forms[0].E02AMOMD2,document.forms[0].E02AMOMD3)">
           		<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%" > 
              	<div align="right">Método Amortización :</div>
            </td>
            <td nowrap width="70%" > 
              <select name="E02AMOMTD">
				<option value=" " <% if (!(acc.getE02AMOMTD().equals("1") ||acc.getE02AMOMTD().equals("2")||acc.getE02AMOMTD().equals("3")||acc.getE02AMOMTD().equals("4"))) out.print("selected"); %>></option>
                <option value="1" <%if (acc.getE02AMOMTD().equals("1")) out.print("selected"); %>>Amortización 365/365</option>
                <option value="2" <%if (acc.getE02AMOMTD().equals("2")) out.print("selected"); %>>Acumulado 365/365</option>
                <option value="3" <%if (acc.getE02AMOMTD().equals("3")) out.print("selected"); %>>Amortización 30/360</option>
                <option value="4" <%if (acc.getE02AMOMTD().equals("4")) out.print("selected"); %>>Acumulado 30/360</option>
              </select>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%" > 
              	<div align="right">Débito/Crédito :</div>
            </td>
            <td nowrap width="70%" > 
              <select name="E02AMODBC">
				<option value=" " <% if (!(acc.getE02AMODBC().equals("D") ||acc.getE02AMODBC().equals("C"))) out.print("selected"); %>></option>
                <option value="D" <%if (acc.getE02AMODBC().equals("D")) out.print("selected"); %>>Debito</option>
                <option value="C" <%if (acc.getE02AMODBC().equals("C")) out.print("selected"); %>>Credito</option>
              </select>
            </td>
          </tr>        
          <tr id="trdark"> 
            <td nowrap width="30%" > 
              	<div align="right">Código Transacción :</div>
            </td>
            <td nowrap width="70%" > 
           		<input type="text" name="E02AMOTCD" size="5" maxlength="4" value="<%= acc.getE02AMOTCD().trim()%>" >
           		<a href="javascript:GetCodeCNOFC('E02AMOTCD','20')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
    <h4>Cuenta de Contrapartida</h4>
  <TABLE class="tableinfo" align="center">
  <TR><TD>
  
   <table id="headTable1">
    <tr id="trdark"> 
      <td nowrap align="center" >Banco </td>
      <td nowrap align="center" >Sucursal</td>
      <td nowrap align="center" >Moneda</td>
      <td nowrap align="center" >Cuenta Contable</td>
      <td nowrap align="center" >Referencia</td>
    </tr>
    </table> 
      
    <div id="dataDiv1" class="scbarcolor" style="height:60; overflow-y :scroll; z-index:0" >
     <table id="dataTable1" >
     <tr id="trclear"> 
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E02OFFBNK" size="3" maxlength="2" value="<%= acc.getE02OFFBNK().trim()%>" onkeypress="enterInteger()">
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E02OFFBRN" size="4" maxlength="3" value="<%= acc.getE02OFFBRN().trim()%>"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E02OFFBNK.value,'','','','')" onkeypress="enterInteger()">
        </div>
      </td>
      <td nowrap  > 
        <div align="center"> 
          <input type="text" name="E02OFFCCY" size="4" maxlength="3" value="<%= acc.getE02OFFCCY().trim()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E02OFFBNK.value,'','','','')">
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E02OFFGLN" size="19" maxlength="17"  value="<%= acc.getE02OFFGLN().trim()%>"
                oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E02OFFBNK.value,'','','','')"  onkeypress="enterInteger()">
        </div>
      </td>      
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E02OFFACC" size="15" maxlength="13"  value="<%= acc.getE02OFFACC().trim()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E02OFFBNK.value,'','','','RT')"  onkeypress="enterInteger()">
        </div>
      </td>
    </tr>
     </table>
        </div>
  </TD>  
</TR>	
</TABLE>    
 <SCRIPT language="javascript">
    function tableresize() {
     adjustEquTables(headTable1,dataTable1,dataDiv1,0,false);
   }
  tableresize();
  window.onresize=tableresize;  
  </SCRIPT>

<table width="100%">		
  	<tr>
  		<td width="33%">
  		  <div align="center"> 
     		<input id="EIBSBTN" type="button" name="Update" value="Actualizar" onClick="javascript:goAction(1);">
     	  </div>	
  		</td>
		<td width="34%">
	  		<div align="center">
		 		<input id="EIBSBTN" type="button" name="Submit" value="Suspender" onClick="javascript:goAction(2);">
   	  	 	</div>	
  		</td>
  		<td width="33%"> 
  		  <div align="center"> 
     		<input id="EIBSBTN" type="button" name="Delete" value="Borrar" onClick="javascript:goAction(3);">
     	  </div>	
  		</td>
  	</tr>	
</table>

  </form>
</body>
</html>
