<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Emisión de Cheques</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "offBasic" class= "datapro.eibs.beans.EOF011501Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


<SCRIPT Language="Javascript">

<%	if(userPO.getPurpose().equals("APPROVAL") ||
		userPO.getPurpose().equals("APPROVAL_INQ")) { %>	
		builtNewMenu(of_a_opt);
<%	} else { %>
		builtNewMenu(of_np_opt);
<%	} %>

	initMenu();

builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   var concepto=document.getElementById("E01DEBOPC").value;
   if (concepto == "02")
     Client=document.getElementById("E01OFMCUN").value;
   else
     Client=""; 
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
 
 function loadBeneficiary(){
   
   var beneficiary = document.getElementById("Aux_E01OFMBNF").value;
   var BN1 = document.getElementById("E01OFMBNF");
   var BN2 = document.getElementById("E01OFMBN1");
   var BN3 = document.getElementById("E01OFMBN2");
   
   BN1.value = ' ';
   BN2.value=' ';
   BN3.value=' ' ;

   if (beneficiary.length > 0 && beneficiary.length <= 30)
     BN1.value=beneficiary.substring(0,beneficiary.length);

   
   if (beneficiary.length > 30 && beneficiary.length <= 60){

     BN1.value=beneficiary.substring(0,30);
     /*
     if (BN1.value.charAt(29) == ' ')
       BN1.value = BN1.value.substring(0,29) + '*';
     */  
     BN2.value=beneficiary.substring(30,beneficiary.length);
     /*
      if (BN2.value.charAt(0) == ' ')
       BN2.value = '*' + BN2.value.substring(1,BN2.value.length);
       */
   }
     
   if (beneficiary.length > 60 ){
     BN1.value=beneficiary.substring(0,30);
     BN2.value=beneficiary.substring(30,60);
     BN3.value=beneficiary.substring(60,beneficiary.length);
     /*
     if (BN1.value.charAt(29) == ' ')
       BN1.value = BN1.value.substring(0,29) + '*';
       
     if (BN2.value.charAt(0) == ' ')
       BN2.value = '*' + BN2.value.substring(1,BN2.value.length);
     if (BN2.value.charAt(29) == ' ')
       BN2.value = BN2.value.substring(0,BN2.value.length - 1) + '*'; 
       
      if (BN3.value.charAt(0) == ' ')
       BN3.value = '*' + BN3.value.substring(1,BN3.value.length);   
       */
   } 
    //alert(BN1.value + BN2.value+BN3.value);
    return true;

 }
 
 function unLoadBeneficiary(){
   //var BN1 = document.getElementById("E01OFMBNF").value;
   //var BN2 = document.getElementById("E01OFMBN1").value;
   //var BN3 = document.getElementById("E01OFMBN2").value;
   <%
     String concatValue = offBasic.getE01OFMBNF()+offBasic.getE01OFMBN1()+offBasic.getE01OFMBN2();
     concatValue=concatValue.replace('*',' ');
   %>
   document.getElementById("Aux_E01OFMBNF").value='<%=concatValue%>';
 } 
 

function delEnterKey(evt){
    evt = (evt)? evt: ((window.event) ? window.event : "");
    var key = 0;
    key = (evt.keyCode)? evt.keyCode: evt.charCode ;
    // Verifica que el usuarion no teclee enter.
    if (key >= 9 && key <=13){
     //alert ("key :"+key+ ", lenght :"+this.value.length);
     this.value= this.value.substring(0,this.value.length - 1);
    }
    
    if ( key == 32  && this.value.charAt(this.value.length - 2) == ' ')
      this.value= this.value.substring(0,this.value.length - 1);
    
   if (key == 188){
     this.value= this.value + ' ';
   }
   
   checkMaxLength(evt);
   
}
 
 function checkMaxLength(evt)
{
    //alert ("in");
    evt = (evt)? evt: ((window.event) ? window.event : "");
 
	var maxLength = this.getAttribute('maxlength');
	var currentLength =  this.value.length;
	if (currentLength > maxLength - 1 )
		this.value = this.value.substring(0,maxLength-1);
}
 
   
</SCRIPT>

</head>


<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
 	  error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
if(userPO.getPurpose().equals("MAINTENANCE")) {
  out.println("<SCRIPT> initMenu(); </SCRIPT>");}
%> 
 
<H3 align="center">Emisi&oacute;n de Cheques de Gerencia<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="of_chk_basic.jsp, EOF0115"> 
</H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <!--input type="hidden" id="E01OFMBNF" name="E01OFMBNF" value="<%=offBasic.getE01OFMBNF()%>" >
  <input type="hidden" id="E01OFMBN1" name="E01OFMBN1" value="<%=offBasic.getE01OFMBN1()%>" >
  <input type="hidden" id="E01OFMBN2" name="E01OFMBN2" value="<%=offBasic.getE01OFMBN2()%>" -->
  
  
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
                <input type="text" size="9" maxlength="9" name="E01OFMFTY" value="<%= offBasic.getE01OFMFTY().trim()%>" readonly>
              </div>
            </td>
            <td nowrap colspan="3" width="77%" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" size="45" maxlength="35" name="E01OFMDSC" value="<%= offBasic.getE01OFMDSC().trim()%>" readonly>
                </font></font></font></div>
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
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="26%"> 
              <div align="right">Cheque No. :</div>
            </td>
            <td nowrap width="27%">
 
              <input type="text" name="E01OFMCKN" size="9" maxlength="9" value="<%= offBasic.getE01OFMCKN().trim()%>" readonly>

            </td>
            <td nowrap width="24%"> 
              <div align="right">Fecha :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01OFMEM1" size="2" maxlength="2" value="<%= offBasic.getE01OFMEM1().trim()%>" readonly>
              <input type="text" name="E01OFMEM2" size="2" maxlength="2" value="<%= offBasic.getE01OFMEM2().trim()%>" readonly>
              <input type="text" name="E01OFMEM3" size="2" maxlength="2" value="<%= offBasic.getE01OFMEM3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%"> 
              <div align="right">Banco / Sucursal : </div>
            </td>
            <td nowrap>
              <input type="text" name="E01OFMBNK" size="3" maxlength="2" value="<%= offBasic.getE01OFMBNK().trim()%>" readonly>
              / 
              <input type="text" name="E01OFMBRN" size="3" maxlength="3" value="<%= offBasic.getE01OFMBRN().trim()%>" readonly>
            </td>
            <td nowrap>
              <div align="right">Moneda : </div>
            </td>
            <td nowrap>
              <input type="text" name="E01OFMCCY" size="3" maxlength="3" value="<%= offBasic.getE01OFMCCY().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="26%" height="19"> 
              <div align="right">Clase de Cheque :</div>
            </td>
            <td nowrap height="19"> 
              <input type="text" name="E01OFMCLS" size="2" maxlength="1" value="<%= offBasic.getE01OFMCLS().trim()%>" readonly>
            </td>
            <td nowrap width="26%" height="19"> 
            </td>
            <td nowrap width="28%" height="19"> 

             </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%"> 
              <div align="right">Pagar a la Orden de :</div>
            </td>
			<td nowrap colspan="3">
            	<input type="text" name="E01OFMBNF" size="36" maxlength="35" value="<%= offBasic.getE01OFMBNF().trim()%>" readonly><br>
            	<input type="text" name="E01OFMBN1" size="36" maxlength="35" value="<%= offBasic.getE01OFMBN1().trim()%>" readonly><br>
            	<input type="text" name="E01OFMBN2" size="36" maxlength="35" value="<%= offBasic.getE01OFMBN2().trim()%>" readonly>
			</td>
			</tr>
          <tr id="trdark"> 
            <td nowrap width="26%" height="23"> 
              <div align="right">Identificación :</div>
            </td>
            <td nowrap height="23" colspan="3">
            <INPUT type="text" name="E01OFMBID" size="15" maxlength="15" value="<%= offBasic.getE01OFMBID().trim()%>" readonly></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" height="19"> 
              <div align="right">La suma de :</div>
            </td>
            <td nowrap height="19" colspan="3"> 
              <input type="text" name="E01OFMAMT" size="22" maxlength="20" value="<%= offBasic.getE01OFMAMT().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

  <h4>Datos de la Transacci&oacute;n</h4>
<table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark">  
            <td nowrap width="19%"> 
              <div align="right">Identificaci&oacute;n del Aplicante :</div>
            </td>
            <td nowrap width="22%">
              <input type="text" name="E01OFMAID" size="15" maxlength="15" value="<%= offBasic.getE01OFMAID().trim()%>" readonly>
            </td>
            <td nowrap width="18%"> 
              <div align="right">Cliente :</div>
            </td>
            <td nowrap width="41%"> 
              <input type="text" name="E01OFMCUN" id="E01OFMCUN" size="9" maxlength="9" value="<%= offBasic.getE01OFMCUN().trim()%>" readonly>
             </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="19%"> 
              <div align="right">Nombre del Aplicante :</div>
            </td>
            <td nowrap colspan="3">
              <input type="text" name="E01OFMAPL" size="35" maxlength="30" value="<%= offBasic.getE01OFMAPL().trim()%>" readonly>
            </td>
          </tr>
		  <tr id="trdark"> 
            <td nowrap width="19%"> 
              <div align="right"> </div>
            </td>
            <td nowrap colspan="3"> 
              <input type="text" name="E01OFMAP1" size="35" maxlength="30" value="<%= offBasic.getE01OFMAP1().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trcleark"> 
            <td nowrap width="19%"> 
              <div align="right">Descripcion :</div>
            </td>
            <td nowrap colspan="3"> 
              <input type="text" name="E01OFMCO1" size="70" maxlength="70" value="<%= offBasic.getE01OFMCO1().trim()%>" readonly>
            </td>
          </tr>
          
          <tr id="trdark"> 
            <td nowrap width="19%"> 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3"> 
              <input type="text" name="E01OFMCO2" size="70" maxlength="70" value="<%= offBasic.getE01OFMCO2().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="19%"> 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3"> 
              <input type="text" name="E01OFMCO3" size="70" maxlength="70" value="<%= offBasic.getE01OFMCO3().trim()%>" readonly>
            </td>
          </tr>
          
          <tr id="trdark"> 
            <td nowrap width="19%"> 
              <div align="right">Direcci&oacute;n :</div>
            </td>
            <td nowrap colspan="3">
              <input type="text" name="E01OFMAD1" size="70" maxlength="70" value="<%= offBasic.getE01OFMAD1().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="19%"> 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3">
              <input type="text" name="E01OFMAD2" size="70" maxlength="70" value="<%= offBasic.getE01OFMAD2().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="19%"> 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3">
              <input type="text" name="E01OFMAD3" size="70" maxlength="70" value="<%= offBasic.getE01OFMAD3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            	<td nowrap> 
              		<div align="right">Cuenta de Pago :</div>
            	</td>	  
          	  <td nowrap colspan="3"> 
          	  <table class="tableinfo" style="filter:''">
          		<tr id="trdark"> 
            		<td width="31%"> 
              		<div align="center">Concepto</div>
            		</td>
            		<td width="5%"> 
              		<div align="center">Banco</div>
            		</td>
            		<td width="13%" > 
              		<div align="center">Sucursal</div>
            		</td>
            		<td width="12%" > 
              		<div align="center">Moneda</div>
            		</td>
            		<td width="21%" > 
              		<div align="center">Referencia</div>
            		</td>
          		</tr>
          		<tr id="trclear"> 
            		<td width="31%" > 
              		<div align="center" nowrap> 
                		<input type=text name="E01DEBOPC" id="E01DEBOPC" value="<%= offBasic.getE01DEBOPC().trim()%>" size="3" maxlength="3" readonly>
                		<input type=HIDDEN name="E01DEBGLN" value="<%= offBasic.getE01DEBGLN().trim()%>" readonly>
                		<input type="text" name="E01DEBCON" size="25" maxlength="25" readonly value="<%= offBasic.getE01DEBCON().trim()%>" readonly>
              		</div>
            		</td>
            		<td width="5%" > 
              		<div align="center"> 
                		<input type="text" name="E01DEBBNK" size="2" maxlength="2" value="<%= offBasic.getE01DEBBNK().trim()%>" readonly>
              		</div>
            		</td>
            		<td width="13%"> 
              		<div align="center"> 
                		<input type="text" name="E01DEBBRN" size="3" maxlength="3" value="<%= offBasic.getE01DEBBRN().trim()%>" readonly>
              		</div>
            		</td>
            		<td width="12%" > 
              		<div align="center">
                		<input type="text" name="E01DEBCCY" size="3" maxlength="3" value="<%= offBasic.getE01DEBCCY().trim()%>" readonly> 
              		</div>
            		</td>
            		<td width="21%" > 
              		<div align="center"> 
                		<input type="text" name="E01DEBACC" size="14" maxlength="12" value="<%= offBasic.getE01DEBACC().trim()%>" readonly>
              		 </div>
            		</td>
          		</tr>
        	</table>
        	</td>
           </tr>
        </table>
        
      </td>
    </tr>
  </table>

</form>

</body>
</html>
