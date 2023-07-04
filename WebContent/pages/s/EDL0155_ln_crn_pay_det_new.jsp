<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<%@ page import = "java.lang.Object" %>

<HTML>
<HEAD>
<TITLE>
Payment Details
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id="header" class= "datapro.eibs.beans.EDL015501Message"  scope="session"/>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" >


function GetHelpDeductions(name)
{
	page = webapp + "/servlet/datapro.eibs.products.JSEDL0370?SCREEN=8";
	fieldName=name;
	CenterWindow(page,600,350,3);
}

function AddConcepts() {
	var dataT = document.all["dataTable"];
	for(var n=0;n<dataT.rows.length;n++) {
		dataT.rows[n].style.display="";
	}
	
}

function ShowHelpCode(cboName, divName, dscName) {

	var v = document.forms[0][cboName][document.forms[0][cboName].selectedIndex].value;
	if (v == '3') {
		document.all[divName].style.display = "";
		document.forms[0][dscName].value = '';
		document.forms[0][dscName].readOnly = false;
	} else {
		document.all[divName].style.display = "none";
		if (v == 'P') {
			document.forms[0][dscName].value = 'Principal';
			document.forms[0][dscName].readOnly = true;
		} else if (v == 'I') {
			document.forms[0][dscName].value = 'Interés';
			document.forms[0][dscName].readOnly = true;
		} else if (v == 'L') {
			document.forms[0][dscName].value = 'Interés de Mora';
			document.forms[0][dscName].readOnly = true;
		} else if (v == '1') {
			document.forms[0][dscName].value = 'Impuestos';
			document.forms[0][dscName].readOnly = true;
		} else if (v == '2') {
			document.forms[0][dscName].value = 'Comisiones';
			document.forms[0][dscName].readOnly = true;
		} else if (v == '4') {
			document.forms[0][dscName].value = 'FECI/IVA';
			document.forms[0][dscName].readOnly = true;
		} else if (v == '5') {
			document.forms[0][dscName].value = 'FECI Vencido';
			document.forms[0][dscName].readOnly = true;	
		} else {
			document.forms[0][dscName].readOnly = false;
		}
	}
	
}

</SCRIPT>
</HEAD>

<BODY>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0155" >
  <p> 
    <input TYPE=HIDDEN name="SCREEN" value="3">
    <input TYPE=HIDDEN name="OPTION" value="0002">
    
  </p>

  <h3 align="center">Mantenimiento al Plan de Pagos y Deducciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ln_crn_pay_det_new.jsp , EDL0155"> 
  </h3>
  <hr size="4">

<h4></h4>

  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E02CUN2" size="9" maxlength="9" readonly value="<%= userPO.getCusNum().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E02NA12" size="45" maxlength="45" readonly value="<%= userPO.getCusName().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta : </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E02DLPACC" size="12" maxlength="12" value="<%= userPO.getAccNum().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda :</b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E02PRO2" size="4" maxlength="4" readonly value="<%= userPO.getProdCode().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
<br>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="25%" height="23" > 
              <div align="right">Fecha de Apertura :</div>
            </td>
            <td nowrap width="17%" height="23" > 
              <div align="right"><%= userPO.getHeader10().trim()%></div>
            </td>
            <td nowrap width="23%" height="23" > 
              <div align="right">Tasa Interés :</div>
            </td>
            <td nowrap width="35%" height="23" > 
              <div align="right"><%= userPO.getHeader13().trim()%></div>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="25%" > 
              <div align="right">Fecha de Vencimiento  :</div>
            </td>
            <td nowrap width="17%" > 
              <div align="right"><%= userPO.getHeader11().trim()%></div>
            </td>
            <td nowrap width="23%" > 
              <div align="right">Saldo Base :</div>
            </td>
            <td nowrap width="35%" > 
              <div align="right"><%= userPO.getHeader14().trim()%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" height="18" > 
              <div align="right">Monto Original :</div>
            </td>
            <td nowrap width="17%" height="18" > 
              <div align="right"><%= userPO.getHeader12().trim()%></div>
            </td>
            <td nowrap width="23%" height="18" > 
              <div align="right">Tipo de Interés :</div>
            </td>
            <td nowrap width="35%" height="18" > 
              <div align="right"><%= userPO.getHeader15().trim()%></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <BR>
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trclear"> 
            <td nowrap width="21%"  ></td>
            <td nowrap width="4%"  >&nbsp;</td>
            <td nowrap width="15%"  > 
              <div align="left">Fecha de Pago : </div>
            </td>
            <td nowrap width="30%"  > 
            	<input TYPE="TEXT" size="3" maxlength="2" name="E02DLPPD1" value="" onKeyPress="enterInteger()">
 				<input TYPE="TEXT" size="3" maxlength="2" name="E02DLPPD2" value="" onKeyPress="enterInteger()">
 				<input TYPE="TEXT" size="3" maxlength="2" name="E02DLPPD3" value="" onKeyPress="enterInteger()">
			</td>
            <td nowrap width="30%"  > 
              <div align="left">Fecha Último Pago :</div>
              
            </td>
            <td nowrap width="30%"  > 
            	<input TYPE="TEXT" size="3" maxlength="2" name="E02DLPDT1" value="" onKeyPress="enterInteger()">
 				<input TYPE="TEXT" size="3" maxlength="2" name="E02DLPDT2" value="" onKeyPress="enterInteger()">
 				<input TYPE="TEXT" size="3" maxlength="2" name="E02DLPDT3" value="" onKeyPress="enterInteger()">
			</td>            
          </tr>
        </table>
      </td>
    </tr>
  </table>
<BR>

  <table id="dataTable" class="tableinfo" >
    <tr id="trdark"> 
      <th align=CENTER nowrap   >Tipo</th>
      <th align=CENTER nowrap colspan="2"  >Código</th>
      <th align=CENTER nowrap   >Descripción</th>
      <th align=CENTER nowrap   >Monto a Pagar</th>
      <th align=CENTER nowrap   >Monto Pagado</th>
    </tr>
    
    <tr >      
      <td align=CENTER  nowrap >
          <select name="E02TYPE01" onChange="javascript:ShowHelpCode('E02TYPE01','HLPCODE1','E02DESC01')">
            <option value=" " ></option>
            <option value="P" >Principal</option>
            <option value="I" >Interés</option>
            <option value="L" >Interés de Mora</option>
            <option value="1" >Impuestos</option>
            <option value="2" >Comisiones</option>
            <option value="3" >Deducciones</option>
            <option value="4" >FECI/IVA</option>
            <option value="5" >FECI Vencido</option>
          </select>   
        </td>
        <td align="left"  nowrap >
      	  <input type="text" name="E02CODE01" size="4" maxlength="3" value="" >
      	</td> 
      	 <td><div align="left" id="HLPCODE1" style="display:none">
      	  	<a href="javascript:GetHelpDeductions('E02CODE01')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a>
			</div>
        </td>    
      <td align=CENTER  nowrap >
      	<input type="text" name="E02DESC01" size="21" maxlength="20" value="" >
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02AMNT01" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02PAID01" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
    </tr>
    <tr >      
      <td align=CENTER  nowrap >
          <select name="E02TYPE02" onChange="javascript:ShowHelpCode('E02TYPE02','HLPCODE2','E02DESC02')">
            <option value=" " ></option>
            <option value="P" >Principal</option>
            <option value="I" >Interés</option>
            <option value="L" >Interés de Mora</option>
            <option value="1" >Impuestos</option>
            <option value="2" >Comisiones</option>
            <option value="3" >Deducciones</option>
            <option value="4" >FECI/IVA</option>
            <option value="5" >FECI Vencido</option>
           
          </select>   
        </td>
        <td align="left"  nowrap >
      	  <input type="text" name="E02CODE02" size="4" maxlength="3" value="" >
      	</td> 
      	 <td><div align="left" id="HLPCODE2" style="display:none">
      	  	<a href="javascript:GetHelpDeductions('E02CODE02')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a>
			</div>
        </td>    
      <td align=CENTER  nowrap >
      	<input type="text" name="E02DESC02" size="21" maxlength="20" value="">
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02AMNT02" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02PAID02" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
    </tr>    
     <tr >      
      <td align=CENTER  nowrap >
          <select name="E02TYPE03" onChange="javascript:ShowHelpCode('E02TYPE03','HLPCODE3','E02DESC03')">
            <option value=" " ></option>
            <option value="P" >Principal</option>
            <option value="I" >Interés</option>
            <option value="L" >Interés de Mora</option>
            <option value="1" >Impuestos</option>
            <option value="2" >Comisiones</option>
            <option value="3" >Deducciones</option>
            <option value="4" >FECI/IVA</option>
            <option value="5" >FECI Vencido</option>
            
          </select>   
        </td>
        <td align="left"  nowrap >
      	  <input type="text" name="E02CODE03" size="4" maxlength="3" value="" >
      	</td> 
      	 <td><div align="left" id="HLPCODE3" style="display:none">
      	  	<a href="javascript:GetHelpDeductions('E02CODE03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a>
			</div>
        </td>    
      <td align=CENTER  nowrap >
      	<input type="text" name="E02DESC03" size="21" maxlength="20" value="" >
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02AMNT03" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02PAID03" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
    </tr>   
     <tr >      
      <td align=CENTER  nowrap >
          <select name="E02TYPE04" onChange="javascript:ShowHelpCode('E02TYPE04','HLPCODE4','E02DESC04')">
            <option value=" " ></option>
            <option value="P" >Principal</option>
            <option value="I" >Interés</option>
            <option value="L" >Interés de Mora</option>
            <option value="1" >Impuestos</option>
            <option value="2" >Comisiones</option>
            <option value="3" >Deducciones</option>
            <option value="4" >FECI/IVA</option>
            <option value="5" >FECI Vencido</option>
            
          </select>   
        </td>
        <td align="left"  nowrap >
      	  <input type="text" name="E02CODE04" size="4" maxlength="3" value="" >
      	</td> 
      	 <td><div align="left" id="HLPCODE4" style="display:none">
      	  	<a href="javascript:GetHelpDeductions('E02CODE04')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a>
			</div>
        </td>    
      <td align=CENTER  nowrap >
      	<input type="text" name="E02DESC04" size="21" maxlength="20" value="" >
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02AMNT04" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02PAID04" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
    </tr>   
    <tr >      
      <td align=CENTER  nowrap >
          <select name="E02TYPE05" onChange="javascript:ShowHelpCode('E02TYPE05','HLPCODE5','E02DESC05')">
            <option value=" " ></option>
            <option value="P" >Principal</option>
            <option value="I" >Interés</option>
            <option value="L" >Interés de Mora</option>
            <option value="1" >Impuestos</option>
            <option value="2" >Comisiones</option>
            <option value="3" >Deducciones</option>
            <option value="4" >FECI/IVA</option>
            <option value="5" >FECI Vencido</option>
            
          </select>   
        </td>
        <td align="left"  nowrap >
      	  <input type="text" name="E02CODE05" size="4" maxlength="3" value="" >
      	</td> 
      	 <td><div align="left" id="HLPCODE5" style="display:none">
      	  	<a href="javascript:GetHelpDeductions('E02CODE05')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a>
			</div>
        </td>    
      <td align=CENTER  nowrap >
      	<input type="text" name="E02DESC05" size="21" maxlength="20" value="" >
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02AMNT05" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02PAID05" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
    </tr>    
    <tr >      
      <td align=CENTER  nowrap >
          <select name="E02TYPE06" onChange="javascript:ShowHelpCode('E02TYPE06','HLPCODE6','E02DESC06')">
            <option value=" " ></option>
            <option value="P" >Principal</option>
            <option value="I" >Interés</option>
            <option value="L" >Interés de Mora</option>
            <option value="1" >Impuestos</option>
            <option value="2" >Comisiones</option>
            <option value="3" >Deducciones</option>
            <option value="4" >FECI/IVA</option>
            <option value="5" >FECI Vencido</option>
            
          </select>   
        </td>
        <td align="left"  nowrap >
      	  <input type="text" name="E02CODE06" size="4" maxlength="3" value="" >
      	</td> 
      	 <td><div align="left" id="HLPCODE6" style="display:none">
      	  	<a href="javascript:GetHelpDeductions('E02CODE06')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a>
			</div>
        </td>    
      <td align=CENTER  nowrap >
      	<input type="text" name="E02DESC06" size="21" maxlength="20" value="" >
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02AMNT06" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02PAID06" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
    </tr>    
     <tr >      
      <td align=CENTER  nowrap >
          <select name="E02TYPE07" onChange="javascript:ShowHelpCode('E02TYPE07','HLPCODE7','E02DESC07')">
            <option value=" " ></option>
            <option value="P" >Principal</option>
            <option value="I" >Interés</option>
            <option value="L" >Interés de Mora</option>
            <option value="1" >Impuestos</option>
            <option value="2" >Comisiones</option>
            <option value="3" >Deducciones</option>
            <option value="4" >FECI/IVA</option>
            <option value="5" >FECI Vencido</option>
            
          </select>   
        </td>
        <td align="left"  nowrap >
      	  <input type="text" name="E02CODE07" size="4" maxlength="3" value="" >
      	</td> 
      	 <td><div align="left" id="HLPCODE7" style="display:none">
      	  	<a href="javascript:GetHelpDeductions('E02CODE07')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a>
			</div>
        </td>    
      <td align=CENTER  nowrap >
      	<input type="text" name="E02DESC07" size="21" maxlength="20" value="" >
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02AMNT07" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02PAID07" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
    </tr>   
    <tr >      
      <td align=CENTER  nowrap >
          <select name="E02TYPE08" onChange="javascript:ShowHelpCode('E02TYPE08','HLPCODE8','E02DESC08')">
            <option value=" " ></option>
            <option value="P" >Principal</option>
            <option value="I" >Interés</option>
            <option value="L" >Interés de Mora</option>
            <option value="1" >Impuestos</option>
            <option value="2" >Comisiones</option>
            <option value="3" >Deducciones</option>
            <option value="4" >FECI/IVA</option>
            <option value="5" >FECI Vencido</option>
            
          </select>   
        </td>
        <td align="left"  nowrap >
      	  <input type="text" name="E02CODE08" size="4" maxlength="3" value="" >
      	</td> 
      	 <td><div align="left" id="HLPCODE8" style="display:none">
      	  	<a href="javascript:GetHelpDeductions('E02CODE08')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a>
			</div>
        </td>    
      <td align=CENTER  nowrap >
      	<input type="text" name="E02DESC08" size="21" maxlength="20" value="" >
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02AMNT08" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02PAID08" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
    </tr>    
    <tr >      
      <td align=CENTER  nowrap >
          <select name="E02TYPE09" onChange="javascript:ShowHelpCode('E02TYPE09','HLPCODE9','E02DESC09')">
            <option value=" " ></option>
            <option value="P" >Principal</option>
            <option value="I" >Interés</option>
            <option value="L" >Interés de Mora</option>
            <option value="1" >Impuestos</option>
            <option value="2" >Comisiones</option>
            <option value="3" >Deducciones</option>
            <option value="4" >FECI/IVA</option>
            <option value="5" >FECI Vencido</option>
            
          </select>   
        </td>
        <td align="left"  nowrap >
      	  <input type="text" name="E02CODE09" size="4" maxlength="3" value="" >
      	</td> 
      	 <td><div align="left" id="HLPCODE9" style="display:none">
      	  	<a href="javascript:GetHelpDeductions('E02CODE09')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a>
			</div>
        </td>    
      <td align=CENTER  nowrap >
      	<input type="text" name="E02DESC09" size="21" maxlength="20" value="" >
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02AMNT09" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02PAID09" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
    </tr>    
     <tr >      
      <td align=CENTER  nowrap >
          <select name="E02TYPE10" onChange="javascript:ShowHelpCode('E02TYPE10','HLPCODE10','E02DESC10')">
            <option value=" " ></option>
            <option value="P" >Principal</option>
            <option value="I" >Interés</option>
            <option value="L" >Interés de Mora</option>
            <option value="1" >Impuestos</option>
            <option value="2" >Comisiones</option>
            <option value="3" >Deducciones</option>
            <option value="4" >FECI/IVA</option>
            <option value="5" >FECI Vencido</option>
            
          </select>   
        </td>
        <td align="left"  nowrap >
      	  <input type="text" name="E02CODE10" size="4" maxlength="3" value="" >
      	</td> 
      	 <td><div align="left" id="HLPCODE10" style="display:none">
      	  	<a href="javascript:GetHelpDeductions('E02CODE10')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a>
			</div>
        </td>    
      <td align=CENTER  nowrap >
      	<input type="text" name="E02DESC10" size="21" maxlength="20" value="" >
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02AMNT10" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02PAID10" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
    </tr>   
     <tr >      
      <td align=CENTER  nowrap >
          <select name="E02TYPE11" onChange="javascript:ShowHelpCode('E02TYPE11','HLPCODE11','E02DESC11')">
            <option value=" " ></option>
            <option value="P" >Principal</option>
            <option value="I" >Interés</option>
            <option value="L" >Interés de Mora</option>
            <option value="1" >Impuestos</option>
            <option value="2" >Comisiones</option>
            <option value="3" >Deducciones</option>
            <option value="4" >FECI/IVA</option>
            <option value="5" >FECI Vencido</option>
            
          </select>   
        </td>
        <td align="left"  nowrap >
      	  <input type="text" name="E02CODE11" size="4" maxlength="3" value="" >
      	</td> 
      	 <td><div align="left" id="HLPCODE11" style="display:none">
      	  	<a href="javascript:GetHelpDeductions('E02CODE11')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a>
			</div>
        </td>    
      <td align=CENTER  nowrap >
      	<input type="text" name="E02DESC11" size="21" maxlength="20" value="" >
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02AMNT11" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02PAID11" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
    </tr>   
      <tr >      
      <td align=CENTER  nowrap >
          <select name="E02TYPE12" onChange="javascript:ShowHelpCode('E02TYPE12','HLPCODE12','E02DESC12')">
            <option value=" " ></option>
            <option value="P" >Principal</option>
            <option value="I" >Interés</option>
            <option value="L" >Interés de Mora</option>
            <option value="1" >Impuestos</option>
            <option value="2" >Comisiones</option>
            <option value="3" >Deducciones</option>
            <option value="4" >FECI/IVA</option>
            <option value="5" >FECI Vencido</option>
            
          </select>   
        </td>
        <td align="left"  nowrap >
      	  <input type="text" name="E02CODE12" size="4" maxlength="3" value="" >
      	</td> 
      	 <td><div align="left" id="HLPCODE12" style="display:none">
      	  	<a href="javascript:GetHelpDeductions('E02CODE12')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a>
			</div>
        </td>    
      <td align=CENTER  nowrap >
      	<input type="text" name="E02DESC12" size="21" maxlength="20" value="" >
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02AMNT12" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02PAID12" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
    </tr>   
     <tr >      
      <td align=CENTER  nowrap >
          <select name="E02TYPE13" onChange="javascript:ShowHelpCode('E02TYPE13','HLPCODE13','E02DESC13')">
            <option value=" " ></option>
            <option value="P" >Principal</option>
            <option value="I" >Interés</option>
            <option value="L" >Interés de Mora</option>
            <option value="1" >Impuestos</option>
            <option value="2" >Comisiones</option>
            <option value="3" >Deducciones</option>
            <option value="4" >FECI/IVA</option>
            <option value="5" >FECI Vencido</option>
            
          </select>   
        </td>
        <td align="left"  nowrap >
      	  <input type="text" name="E02CODE13" size="4" maxlength="3" value="" >
      	</td> 
      	 <td><div align="left" id="HLPCODE13" style="display:none">
      	  	<a href="javascript:GetHelpDeductions('E02CODE13')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a>
			</div>
        </td>    
      <td align=CENTER  nowrap >
      	<input type="text" name="E02DESC13" size="21" maxlength="20" value="" >
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02AMNT13" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02PAID13" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
    </tr>   
     <tr >      
      <td align=CENTER  nowrap >
          <select name="E02TYPE14" onChange="javascript:ShowHelpCode('E02TYPE14','HLPCODE14','E02DESC14')">
            <option value=" " ></option>
            <option value="P" >Principal</option>
            <option value="I" >Interés</option>
            <option value="L" >Interés de Mora</option>
            <option value="1" >Impuestos</option>
            <option value="2" >Comisiones</option>
            <option value="3" >Deducciones</option>
            <option value="4" >FECI/IVA</option>
            <option value="5" >FECI Vencido</option>
            
          </select>   
        </td>
        <td align="left"  nowrap >
      	  <input type="text" name="E02CODE14" size="4" maxlength="3" value="" >
      	</td> 
      	 <td><div align="left" id="HLPCODE14" style="display:none">
      	  	<a href="javascript:GetHelpDeductions('E02CODE14')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a>
			</div>
        </td>    
      <td align=CENTER  nowrap >
      	<input type="text" name="E02DESC14" size="21" maxlength="20" value="" >
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02AMNT14" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02PAID14" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
    </tr>   
     <tr >      
      <td align=CENTER  nowrap >
          <select name="E02TYPE15" onChange="javascript:ShowHelpCode('E02TYPE15','HLPCODE15','E02DESC15')">
            <option value=" " ></option>
            <option value="P" >Principal</option>
            <option value="I" >Interés</option>
            <option value="L" >Interés de Mora</option>
            <option value="1" >Impuestos</option>
            <option value="2" >Comisiones</option>
            <option value="3" >Deducciones</option>
            <option value="4" >FECI/IVA</option>
            <option value="5" >FECI Vencido</option>
            
          </select>   
        </td>
        <td align="left"  nowrap >
      	  <input type="text" name="E02CODE15" size="4" maxlength="3" value="" >
      	</td> 
      	 <td><div align="left" id="HLPCODE15" style="display:none">
      	  	<a href="javascript:GetHelpDeductions('E02CODE15')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a>
			</div>
        </td>    
      <td align=CENTER  nowrap >
      	<input type="text" name="E02DESC15" size="21" maxlength="20" value="" >
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02AMNT15" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02PAID15" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
    </tr>   
     <tr >      
      <td align=CENTER  nowrap >
          <select name="E02TYPE16" onChange="javascript:ShowHelpCode('E02TYPE16','HLPCODE16','E02DESC16')">
            <option value=" " ></option>
            <option value="P" >Principal</option>
            <option value="I" >Interés</option>
            <option value="L" >Interés de Mora</option>
            <option value="1" >Impuestos</option>
            <option value="2" >Comisiones</option>
            <option value="3" >Deducciones</option>
            <option value="4" >FECI/IVA</option>
            <option value="5" >FECI Vencido</option>
            
          </select>   
        </td>
        <td align="left"  nowrap >
      	  <input type="text" name="E02CODE16" size="4" maxlength="3" value="" >
      	</td> 
      	 <td><div align="left" id="HLPCODE16" style="display:none">
      	  	<a href="javascript:GetHelpDeductions('E02CODE16')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a>
			</div>
        </td>    
      <td align=CENTER  nowrap >
      	<input type="text" name="E02DESC16" size="21" maxlength="20" value="" >
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02AMNT16" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02PAID16" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
    </tr>   
     <tr >      
      <td align=CENTER  nowrap >
          <select name="E02TYPE17" onChange="javascript:ShowHelpCode('E02TYPE17','HLPCODE17','E02DESC17')">
            <option value=" " ></option>
            <option value="P" >Principal</option>
            <option value="I" >Interés</option>
            <option value="L" >Interés de Mora</option>
            <option value="1" >Impuestos</option>
            <option value="2" >Comisiones</option>
            <option value="3" >Deducciones</option>
            <option value="4" >FECI/IVA</option>
            <option value="5" >FECI Vencido</option>
            
          </select>   
        </td>
        <td align="left"  nowrap >
      	  <input type="text" name="E02CODE17" size="4" maxlength="3" value="" >
      	</td> 
      	 <td><div align="left" id="HLPCODE17" style="display:none">
      	  	<a href="javascript:GetHelpDeductions('E02CODE17')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a>
			</div>
        </td>    
      <td align=CENTER  nowrap >
      	<input type="text" name="E02DESC17" size="21" maxlength="20" value="" >
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02AMNT17" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02PAID17" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
    </tr>   
      <tr >      
      <td align=CENTER  nowrap >
          <select name="E02TYPE18" onChange="javascript:ShowHelpCode('E02TYPE18','HLPCODE18','E02DESC18')">
            <option value=" " ></option>
            <option value="P" >Principal</option>
            <option value="I" >Interés</option>
            <option value="L" >Interés de Mora</option>
            <option value="1" >Impuestos</option>
            <option value="2" >Comisiones</option>
            <option value="3" >Deducciones</option>
            <option value="4" >FECI/IVA</option>
            <option value="5" >FECI Vencido</option>
            
          </select>   
        </td>
        <td align="left"  nowrap >
      	  <input type="text" name="E02CODE18" size="4" maxlength="3" value="" >
      	</td> 
      	 <td><div align="left" id="HLPCODE18" style="display:none">
      	  	<a href="javascript:GetHelpDeductions('E02CODE18')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a>
			</div>
        </td>    
      <td align=CENTER  nowrap >
      	<input type="text" name="E02DESC18" size="21" maxlength="20" value="">
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02AMNT18" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02PAID18" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
    </tr>   
      <tr >      
      <td align=CENTER  nowrap >
          <select name="E02TYPE19" onChange="javascript:ShowHelpCode('E02TYPE19','HLPCODE19','E02DESC19')">
            <option value=" " ></option>
            <option value="P" >Principal</option>
            <option value="I" >Interés</option>
            <option value="L" >Interés de Mora</option>
            <option value="1" >Impuestos</option>
            <option value="2" >Comisiones</option>
            <option value="3" >Deducciones</option>
            <option value="4" >FECI/IVA</option>
            <option value="5" >FECI Vencido</option>
            
          </select>   
        </td>
        <td align="left"  nowrap >
      	  <input type="text" name="E02CODE19" size="4" maxlength="3" value="" >
      	</td> 
      	 <td><div align="left" id="HLPCODE19" style="display:none">
      	  	<a href="javascript:GetHelpDeductions('E02CODE19')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a>
			</div>
        </td>    
      <td align=CENTER  nowrap >
      	<input type="text" name="E02DESC19" size="21" maxlength="20" value="" >
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02AMNT19" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02PAID19" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
    </tr>   
     <tr >      
      <td align=CENTER  nowrap >
          <select name="E02TYPE20" onChange="javascript:ShowHelpCode('E02TYPE20','HLPCODE20','E02DESC20')">
            <option value=" " ></option>
            <option value="P" >Principal</option>
            <option value="I" >Interés</option>
            <option value="L" >Interés de Mora</option>
            <option value="1" >Impuestos</option>
            <option value="2" >Comisiones</option>
            <option value="3" >Deducciones</option>
            <option value="4" >FECI/IVA</option>
            <option value="5" >FECI Vencido</option>
   
          </select>   
        </td>
        <td align="left"  nowrap >
      	  <input type="text" name="E02CODE20" size="4" maxlength="3" value="" >
      	</td> 
      	 <td><div align="left" id="HLPCODE20" style="display:none">
      	  	<a href="javascript:GetHelpDeductions('E02CODE20')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a>
			</div>
        </td>    
      <td align=CENTER  nowrap >
      	<input type="text" name="E02DESC20" size="21" maxlength="20" value="" >
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02AMNT20" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
      <td align=CENTER  nowrap > 
        <input type="text" name="E02PAID20" size="17" maxlength="16" value="0.00" onKeyPress="enterDecimal()">
      </td>
    </tr>   
  </table>

  <BR>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="20%" height="18" ><b>Total</b></td>
            <td nowrap width="3%" height="18" >&nbsp;</td>
            <td nowrap width="37%" height="18" >Monto a Pagar : <input type="text" name="E02TOAMNT" size="17" maxlength="16" value="0.00" ></td>
            <td nowrap width="4%" height="18" >&nbsp;</td>
            <td nowrap width="36%" height="18" > 
              <div align="left">Monto Pagado :<input type="text" name="E02TOPAID" size="17" maxlength="16" value="0.00" ></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <BR>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%" align="center"> 
       		<input id="EIBSBTN" type=submit name="Submit" value="Enviar" >
  	  </td>     
    </tr>    
  </table>  
  </FORM>

</BODY>
</HTML>
