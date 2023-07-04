<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head>
<title>Pagos</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<%@ page import = "datapro.eibs.master.Util" %>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "docData" class= "datapro.eibs.beans.DataPayDoc"  scope="session" />
<jsp:useBean id= "payDoc" class= "datapro.eibs.beans.EDL082501Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
function devIntClick(value){
	var divDevInt = document.getElementById("devInt");
	
	if (value == "Y"){
		divDevInt.style.display="";
	}
	else {
		divDevInt.style.display="none";
	}
}

function UpdateTotal(value){
   var numval;
   var total;
  
    try{
     numval= parseFloat(formatFloat(document.forms[0].TMPAMT.value));}
    catch(e){
     numval=0.00;
    }
    try{
     total=parseFloat(formatFloat(document.forms[0].E01PAGTOT.value));}
    catch(e){
     total=0.00;
    }
   total=total-numval;
   total=total+parseFloat(formatFloat(value));
   document.forms[0].E01PAGTOT.value=""+total;
  }
  
  function UpdateTemp(value){
    document.forms[0].TMPAMT.value=value;
  }

</SCRIPT>
</head>
<body>
<SCRIPT LANGUAGE="JavaScript">
builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
</SCRIPT>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">;
            showErrors();
     </SCRIPT>
 <%
 }
%>
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0825">
  
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
	<INPUT TYPE=HIDDEN NAME="TMPAMT" VALUE="">
	<INPUT TYPE=HIDDEN NAME="E01PAGANT" VALUE="<%= payDoc.getE01PAGANT() %>">
	<INPUT TYPE=HIDDEN NAME="E01DEACUN" VALUE="<%= payDoc.getE01DEACUN() %>">

  <h3 align="center">Pagos</h3>
  <table class="tableinfo" cellpadding="2" cellspacing="0">
    <tr id="trdark">
	  <td align="right" width="20%" nowrap> <b> Cuenta : </b></td>
	  <td align="left" nowrap><%= payDoc.getE01DEAACC() %><b> - </b><%= payDoc.getE01CUSNA1() %> </td>
      <td nowrap align="left"> <b>No. Documento : </b><%= payDoc.getE01SELNDR() %> 
      </td> 
    </tr> 
	<tr id="trdark">
	  <td align="right" width="20%"> <b>Aceptante : </b></td>
	  <td align="left" nowrap colspan=2>
       <%= payDoc.getE01SELDID() %><b> - </b><%= payDoc.getE01ACPNME() %>
      </td>
	</tr>  
  </table>
  <BR>
  <table class="tableinfo" cellpadding="2" cellspacing="2">
    <tr> 
      <td nowrap>  
  <table width="100%">
    <tr> 
      <td id=trdark width="20%" nowrap>    
      </td>
	  <td id=trdark width="40%" nowrap align="center"> 
		A Pagar
      </td>
      <td id=trdark width="40%" nowrap  align="center"> 
        Pagado 
	  </td>
    </tr>
    <tr> 
      <td  width="20%" nowrap>  
        <div align="right"><b>Principal : </b></div>
      </td>
	  <td width="40%" nowrap align="right"> 
		<%= payDoc.getE01TRNVDO() %> 
      </td>
      <td width="40%" nowrap  align="right"> 
        <input type="text" name="E01PAGVDO" size="17" maxlength="17" value="<%if (payDoc.getE01MANPAG().equals("Y")) out.print(payDoc.getE01PAGVDO()); else out.print(payDoc.getE01TRNVDO());%>" 
		onKeypress="enterDecimal()" onChange="UpdateTotal(this.value)" onfocus="UpdateTemp(this.value)" readonly>
      </td>
    </tr>
    <tr> 
      <td width="20%" nowrap>  
        <div align="right"><b>Mora : </b></div>
      </td>
	  <td width="40%" nowrap align="right"> 
		<%= payDoc.getE01TRNMOR()%> 
      </td>
      <td width="40%" nowrap align="right"> 
        <input type="text" name="E01PAGMOR" size="17" maxlength="17" value="<%if (payDoc.getE01MANPAG().equals("Y")) out.print(payDoc.getE01PAGMOR()); else out.print(payDoc.getE01TRNMOR());%>"
		onKeypress="enterDecimal()" onChange="UpdateTotal(this.value)" onFocus="UpdateTemp(this.value)">
      </td>
    </tr>
    <% if (payDoc.getH01FLGWK3().equals("R")) {%> 
     <tr> 
      <td width="20%" nowrap>  
        <div align="right"><b>Reajuste : </b></div>
      </td>
	  <td width="40%" nowrap align="right"> 
		<%= payDoc.getE01TRNREA()%> 
      </td>
      <td width="40%" nowrap align="right"> 
        <input type="text" name="E01PAGREA" size="17" maxlength="17" value="<%if (payDoc.getE01MANPAG().equals("Y")) out.print(payDoc.getE01PAGREA()); else out.print(payDoc.getE01TRNREA());%>"
		onKeypress="enterDecimal()" onChange="UpdateTotal(this.value)" onFocus="UpdateTemp(this.value)">
      </td>
    </tr>
    <%
      }
    %>
    <tr>
      <td width="100%" colspan="3" nowrap>  
		<div id="devInt" align="center"> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr> 
		      <td width="20%" nowrap>  
		        <div align="right"><b>Interés Pendiente : </b></div>
		      </td>
			  <td width="40%" nowrap align="right"> 
				<%= payDoc.getE01TRNINT()%> 
		      </td>
		      <td width="40%" nowrap align="right"> 
		      </td>
          </tr>
         </table>
		</div> 
      </td>
    </tr>
    <tr>
      <td width="20%" nowrap>
        <div align="right"><b>I.V.A. : </b></div>
      </td>
	  <td width="40%" nowrap align="right"> 
		<%= payDoc.getE01TRNIVA() %> 
      </td>
      <td width="40%" nowrap align="right"> 
        <input type="text" name="E01PAGIVA" size="17" maxlength="17" value="<%if (payDoc.getE01MANPAG().equals("Y")) out.print(payDoc.getE01PAGIVA()); else out.print(payDoc.getE01TRNIVA());%>" 
		onKeypress="enterDecimal()" onChange="UpdateTotal(this.value)" onfocus="UpdateTemp(this.value)" readonly>
      </td>
    </tr>
    <tr> 
      <td  width="20%" nowrap>  
        <div align="right"><b>Comisión : </b></div>
      </td>
	  <td width="40%" nowrap align="right"> 
		<%= payDoc.getE01TRNCOM() %> 
      </td>
      <td width="40%" nowrap align="right"> 
        <input type="text" name="E01PAGCOM" size="17" maxlength="17" value="<%if (payDoc.getE01MANPAG().equals("Y")) out.print(payDoc.getE01PAGCOM()); else out.print(payDoc.getE01TRNCOM());%>" 
		onKeypress="enterDecimal()" onChange="UpdateTotal(this.value)" onfocus="UpdateTemp(this.value)" readonly>
      </td>
    </tr>
    <tr> 
      <td  width="20%" nowrap>  
        <div align="right"><b>Impuesto : </b></div>
      </td>
	  <td width="40%" nowrap align="right"> 
		<%= payDoc.getE01TRNIMP() %> 
      </td>
      <td width="40%" nowrap align="right"> 
        <input type="text" name="E01PAGIMP" size="17" maxlength="17" value="<%if (payDoc.getE01MANPAG().equals("Y")) out.print(payDoc.getE01PAGIMP()); else out.print(payDoc.getE01TRNIMP());%>" 
		onKeypress="enterDecimal()" onChange="UpdateTotal(this.value)" onfocus="UpdateTemp(this.value)" readonly>
      </td>
    </tr>
    <tr id=trdark> 
      <td width="20%" nowrap>  
        <div align="right"><b>Total : </b></div>
      </td>
	  <td width="40%" nowrap align="right" > 
		<%= payDoc.getE01TRNTOT()%> 
      </td>
      <td width="40%" nowrap  align="right"> 
        <input type="text" name="E01PAGTOT" size="17" maxlength="17" value="<%if (payDoc.getE01MANPAG().equals("Y")) out.print(payDoc.getE01PAGTOT()); else out.print(payDoc.getE01TRNTOT());%>" readonly>
      </td>
    </tr>
	
  </table> 
  <BR>
  <table width="100%" >
	<tr>
            <td nowrap width="20%"> 
              <div align="right">Fecha Valor :</div>
            </td>  
			<td nowrap >
			  <div align="left"> 
        		<input type="text" name="E01PAGVD1" size="2" maxlength="2" onKeypress="enterInteger()" value="<%if (payDoc.getE01MANPAG().equals("Y")) out.print(payDoc.getE01PAGVD1().trim()); else if (!(docData.getPAGVD1()==null)) out.print(docData.getPAGVD1().trim());%>">
        		<input type="text" name="E01PAGVD2" size="2" maxlength="2" onKeypress="enterInteger()" value="<%if (payDoc.getE01MANPAG().equals("Y")) out.print(payDoc.getE01PAGVD2().trim()); else if (!(docData.getPAGVD2()==null)) out.print(docData.getPAGVD2().trim());%>"> 
        		<input type="text" name="E01PAGVD3" size="2" maxlength="2" onKeypress="enterInteger()" value="<%if (payDoc.getE01MANPAG().equals("Y")) out.print(payDoc.getE01PAGVD3().trim()); else if (!(docData.getPAGVD3()==null)) out.print(docData.getPAGVD3().trim());%>">
      		  </div>
			</td>
			<td nowrap width="20%"> 
              <div align="right">Comentario :</div>
            </td>  
			<td nowrap >
			  <div align="left"> 
        		<input type="text" name="E01DEANR1" size="30" maxlength="30" value="<%if (payDoc.getE01MANPAG().equals("Y")) out.print(payDoc.getE01DEANR1().trim()); else if (!(docData.getDEANR1()==null)) out.print(docData.getDEANR1().trim());%>">
              </div>
			</td>
          </tr>
		<tr>
            <td nowrap width="20%">
               <div align="right">Devolver Intereses :</div>
            </td>  
			<td nowrap> 
               	<INPUT type="radio" name="E01DEVINT" value="Y" onClick="devIntClick('Y')" <% if(payDoc.getE01DEVINT().equals("Y")) out.print("checked");%>>Sí
               	<INPUT type="radio" name="E01DEVINT" value="N" onClick="devIntClick('N')" <% if(payDoc.getE01DEVINT().equals("N")) out.print("checked");%>>No
			</td>
			<td nowrap width="20%"> 
            </td>  
			<td nowrap >
			  <div align="left"> 
                <input type="text" name="E01DEANR2" size="30" maxlength="30" value="<%if (payDoc.getE01MANPAG().equals("Y")) out.print(payDoc.getE01DEANR2().trim()); else if (!(docData.getDEANR2()==null)) out.print(docData.getDEANR2().trim());%>">               
              </div>
			</td>
          </tr>
    </table>
  <BR>
    <table width="100%">
		 <tr id="trdark">
            <td nowrap width="30%"> 
              <div align="center">Concepto</div>
            </td>
            <td nowrap width="10%"> 
              <div align="center">Banco</div>
            </td>
            <td nowrap width="10%"> 
              <div align="center">Sucursal</div>
            </td>
            <td nowrap width="10%"> 
              <div align="center">Moneda</div>
            </td>
            <td nowrap width="20%"> 
              <div align="center">Referencia</div>
            </td>
            
          </tr>
		  <tr id="trclear">
             <td nowrap width="40%"> 
              <div align="center"> 
                <input type=text name="E01PAGOPC" value="<%if (payDoc.getE01MANPAG().equals("Y")) out.print(payDoc.getE01PAGOPC().trim()); else if (!(docData.getPAGOPC()==null)) out.print(docData.getPAGOPC().trim());%>" size="3" maxlength="3">
                <input type=HIDDEN name="E01PAGOGL" value="<%if (payDoc.getE01MANPAG().equals("Y")) out.print(payDoc.getE01PAGOGL().trim()); else if (!(docData.getPAGOGL()==null)) out.print(docData.getPAGOGL().trim());%>">
                <input type="text" name="E01PAGCON" size="25" maxlength="25" readonly value="<%if (payDoc.getE01MANPAG().equals("Y")) out.print(payDoc.getE01PAGCON().trim()); else if (!(docData.getPAGCON()==null)) out.print( docData.getPAGCON().trim());%>"
                 		oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E01PAGOBK.value,'','E01PAGOGL','E01PAGOPC','10')">
			  </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01PAGOBK" size="2" maxlength="2" value="<%if (payDoc.getE01MANPAG().equals("Y")) out.print(payDoc.getE01PAGOBK().trim()); else if (!(docData.getPAGOBK()==null)) out.print(docData.getPAGOBK().trim());%>">
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01PAGOBR" size="3" maxlength="3" value="<%if (payDoc.getE01MANPAG().equals("Y")) out.print(payDoc.getE01PAGOBR().trim()); else if (!(docData.getPAGOBR()==null)) out.print(docData.getPAGOBR().trim());%>"
                    oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01PAGOBK.value,'','','','')">
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E01PAGOCY" size="3" maxlength="3" value="<%if (payDoc.getE01MANPAG().equals("Y")) out.print(payDoc.getE01PAGOCY().trim()); else if (!(docData.getPAGOCY()==null)) out.print(docData.getPAGOCY().trim());%>"
                 oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01PAGOBK.value,'','','','')">
              </div>
            </td>
            <td nowrap width="30%"> 
              <div align="center"> 
                <input type="text" name="E01PAGOAC" size="16" maxlength="16" value="<%if (payDoc.getE01MANPAG().equals("Y")) out.print(payDoc.getE01PAGOAC().trim()); else if (!(docData.getPAGOAC()==null)) out.print(docData.getPAGOAC().trim());%>"
                 oncontextmenu="showPopUp(accbyclientHelp,this.name,document.forms[0].E01PAGOBK.value,'',document.forms[0].E01DEACUN.value,'','RT')">
              </div>
            </td>
			
          </tr>
        </table>
   </td>
   </tr>
  </table>
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type="submit" name="Submit" value="Enviar">
  </div>
</p>
<SCRIPT language="JavaScript">
<%if (payDoc.getE01DEVINT().equals("Y")) {%>
     devIntClick('Y');
<%} else { %>
     devIntClick('N');
<%}%>
</SCRIPT>

</form>
</body>
</html>			
