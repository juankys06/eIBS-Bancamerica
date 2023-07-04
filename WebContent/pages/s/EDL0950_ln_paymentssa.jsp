<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Pago de Prest mos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="/eIBS_R04M07/pages/style.css" rel="stylesheet">

<SCRIPT SRC="/eIBS_R04M07/pages/s/javascripts/eIBS.js"> </SCRIPT>

<jsp:useBean id= "lnCancel" class= "datapro.eibs.beans.EDL095001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT Language="Javascript"> 
 	function checkOPT(){
 		var val = "";
 		var ln = document.forms[0].E01PAGFLG.length;
 		var chk = false;
 		if (ln > 0) {
 			for (i=0; i<ln;i++) {
 		 	 if (document.forms[0].E01PAGFLG[i].checked) {
 		    	chk = true;
 		    	if (document.forms[0].E01PAGFLG[i].value == "PC") {
 		      		val = document.forms[0].E01PAGFLG1.value;
 		      		if (val.length < 1) document.forms[0].E01PAGFLG1.value = "01";
 		    	}
 		    	break;
 		 	 }
 			}
 	    } else {
 	    	if (document.forms[0].E01PAGFLG.checked) chk = true;
 	    }
 		if (chk == false) alert("Selecione una Forma de Pago");
 		return chk;
 	}
 	
 	function checkVal(val){ 		
 		selParcial.style.display = (val=="TP") ?  "" : "none";
 	}
</SCRIPT>
</head>
<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 
<% String title="";
   if (userPO.getOption().equals("LC")) title = title="Pago L&iacute;nea de Cr&eacute;dito";
   else if (userPO.getOption().equals("SP")) title = title="Pago Sobregiro Pactado";
   else title="Pago de Cr&eacute;ditos";
%>
<H3 align="center"><%= title %><img src="/eIBS_R04M07/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_payment.jsp, EDL0950" width="35" height="35"> 
</H3>
<hr size="4">
<form method="post" action="/eIBS_R04M07/servlet/datapro.eibs.products.JSEDL0950" onsubmit="return(checkOPT())">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="10">
  
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left">
                <input type="text" size="9" maxlength="9" name="E01DEACUN" value="<%= lnCancel.getE01DEACUN().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" size="45" maxlength="45" name="E01CUSNA1" value="<%= lnCancel.getE01CUSNA1().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" size="12" maxlength="12" name="E01DEAACC" value="<%= lnCancel.getE01DEAACC().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= lnCancel.getE01DEACCY().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" size="4" maxlength="4" name="E01DEAPRO" value="<%= lnCancel.getE01DEAPRO().trim()%>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Saldos</h4>
 <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="40%"> 
              <div align="right"><b>Concepto</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="right"><b>Vencido</b></div>
            </td>
            <td nowrap width="23%"> 
              <div align="right"><b>Pr&oacute;ximo a Vencer</b></div>
            </td>
            <td nowrap width="17%"> 
              <div align="right"><b>Saldo Total</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="40%"> 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap width="20%"> 
              <div align="right"><%= Util.formatDate(lnCancel.getE01VENDT1(),lnCancel.getE01VENDT2(),lnCancel.getE01VENDT3())%></div>
            </td>
            <td nowrap width="23%"> 
              <div align="right"><%= Util.formatDate(lnCancel.getE01PRXDT1(),lnCancel.getE01PRXDT2(),lnCancel.getE01PRXDT3())%></div>
            </td>
            <td nowrap width="17%"> 
              <div align="right"><%= Util.formatDate(lnCancel.getE01SALDT1(),lnCancel.getE01SALDT2(),lnCancel.getE01SALDT3())%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%"> 
              <div align="right">Principal :</div>
            </td>
            <td nowrap width="20%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01VENPRI().trim())%></div>
            </td>
            <td nowrap width="23%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01PRXPRI().trim())%></div>
            </td>
            <td nowrap width="17%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01SALPRI().trim())%></div>
            </td>
          </tr>
          <%if(lnCancel.getH01FLGWK3().equals("R")){%>
          <tr id="trclear"> 
            <td nowrap width="40%"> 
              <div align="right">Reajuste :</div>
            </td>
            <td nowrap width="20%" height="19"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01VENREA().trim())%></div>
            </td>
            <td nowrap width="23%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01PRXREA().trim())%></div>
            </td>
            <td nowrap width="17%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01SALREA().trim())%></div>
            </td>
          </tr>
          <%}%>
		 <%if(lnCancel.getH01FLGWK3().equals("R")){%>
          <tr id="trdark"> 
         <%}
		    else 
		 {%>
		 <tr id="trclear">
         <%}%>	  
            <td nowrap width="40%"> 
              <div align="right">Intereses :</div>
            </td>
            <td nowrap width="20%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01VENINT().trim())%></div>
            </td>
            <td nowrap width="23%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01PRXINT().trim())%></div>
            </td>
            <td nowrap width="17%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01SALINT().trim())%></div>
            </td>
          </tr>
		 <%if(lnCancel.getH01FLGWK3().equals("R")){%>
          <tr id="trclear"> 
         <%}
		    else 
		 {%>
		 <tr id="trdark">
         <%}%>
            <td nowrap width="40%"> 
              <div align="right">Interes por Mora :</div>
            </td>
            <td nowrap width="20%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01VENMOR().trim())%></div>
            </td>
            <td nowrap width="23%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01PRXMOR().trim())%></div>
            </td>
            <td nowrap width="17%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01SALMOR().trim())%></div>
            </td>
          </tr>
         <%if(lnCancel.getH01FLGWK3().equals("R")){%>
          <tr id="trdark"> 
            <td nowrap width="40%"> 
              <div align="right">Reajuste por Mora :</div>
            </td>
            <td nowrap width="20%" height="19"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01VENREX().trim())%></div>
            </td>
            <td nowrap width="23%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01PRXREX().trim())%></div>
            </td>
            <td nowrap width="17%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01SALREX().trim())%></div>
            </td>
          </tr>
          <%}%> 
		 <%if(lnCancel.getH01FLGWK3().equals("R")){%>
          <tr id="trclear"> 
         <%}
		  else 
		  {
		  %>
		 <tr id="trdark">
         <%}%>
            <td nowrap width="40%"> 
              <div align="right">Impuesto :</div>
            </td>
            <td nowrap width="20%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01VENIMP().trim())%></div>
            </td>
            <td nowrap width="23%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01PRXIMP().trim())%></div>
            </td>
            <td nowrap width="17%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01SALIMP().trim())%></div>
            </td>
          </tr>
		 <%if(lnCancel.getH01FLGWK3().equals("R")){%>
          <tr id="trdark"> 
         <%}
		  else {
         %>
		 <tr id="trclear">
         <%}%>
            <td nowrap width="40%"> 
              <div align="right">Comisiones :</div>
            </td>
            <td nowrap width="20%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01VENCOM().trim())%></div>
            </td>
            <td nowrap width="23%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01PRXCOM().trim())%></div>
            </td>
            <td nowrap width="17%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01SALCOM().trim())%></div>
            </td>
          </tr>
		 <%if(lnCancel.getH01FLGWK3().equals("R")){%>
          <tr id="trclear"> 
         <%}
		   else {
		  %>
		 <tr id="trdark">
         <%}%>
            <td nowrap width="40%"> 
              <div align="right">Deducciones :</div>
            </td>
            <td nowrap width="20%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01VENDED().trim())%></div>
            </td>
            <td nowrap width="23%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01PRXDED().trim())%></div>
            </td>
            <td nowrap width="17%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01SALDED().trim())%></div>
            </td>
          </tr>
		 <%if(lnCancel.getH01FLGWK3().equals("R")){%>
          <tr id="trdark"> 
         <%}
		   else {
		%>
		 <tr id="trclear">
         <%}%>
            <td nowrap width="40%"> 
              <div align="right">I.V.A. :</div>
            </td>
            <td nowrap width="20%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01VENIVA().trim())%></div>
            </td>
            <td nowrap width="23%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01PRXIVA().trim())%></div>
            </td>
            <td nowrap width="17%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01SALIVA().trim())%></div>
            </td>
          </tr>
		 <%if(lnCancel.getH01FLGWK3().equals("R")){%>
          <tr id="trclear"> 
         <%} 
		   else {
         %>
		 <tr id="trdark">
         <%}%>
            <td nowrap width="40%"> 
              <div align="right">Total :</div>
            </td>
            <td nowrap width="20%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01VENTOT().trim())%></div>
            </td>
            <td nowrap width="23%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01PRXTOT().trim())%></div>
            </td>
            <td nowrap width="17%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01SALTOT().trim())%></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Forma de Pago</h4>
<table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
        
        <%if(userPO.getType().equals("1")) {%>
          <tr id="trdark"> 
            <td nowrap width="20%"> 
              <div align="right">                
                <input type="radio" name="E01PAGFLG" value="MA"                
			  <%if(!(lnCancel.getE01PAGFLG().equals("TO") || lnCancel.getE01PAGFLG().equals("TP") || lnCancel.getE01PAGFLG().equals("CV")
			  || lnCancel.getE01PAGFLG().equals("PC") || lnCancel.getE01PAGFLG().equals("DS"))) out.print("checked");%> onclick="checkVal(this.value)">
              </div>
            </td>
            <td nowrap colspan=2>Mantenimiento</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">                
                <input type="radio" name="E01PAGFLG" value="TO"
			  <%if(lnCancel.getE01PAGFLG().equals("TO")) out.print("checked");%> onclick="checkVal(this.value)">
              </div>
            </td>
            <td nowrap colspan=2>Pago Total</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">
                <input type="radio" name="E01PAGFLG" value="TP"
			  <%if(lnCancel.getE01PAGFLG().equals("TP")) out.print("checked");%> onclick="checkVal(this.value)">
              </div>
            </td>
            <td nowrap>Pago Parcial 
             <input type="text" name="E01PAGABO" size="15" maxlength="15" value="<%= lnCancel.getE01PAGDIS().trim()%>" onKeypress="enterDecimal()">
            </td>
            <TD nowrap>
              <div id="selParcial" <%if(!lnCancel.getE01PAGFLG().equals("TP")) out.print("style=\"display:none\"");%>>
            	<INPUT type="radio" name="E01DEASEL" value="1" <% if (!lnCancel.getE01DEASEL().equals("2")) out.print("checked");%>>Adelgazar Valor Cuota
				<INPUT type="radio" name="E01DEASEL" value="2" <% if (lnCancel.getE01DEASEL().equals("2")) out.print("checked");%>>Reducir Plazo
			  </div>
			</TD>
						
          </tr>

          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">
                <input type="radio" name="E01PAGFLG" value="CV"
			  <%if(lnCancel.getE01PAGFLG().equals("CV")) out.print("checked");%> onclick="checkVal(this.value)">
              </div>
            </td>
            <td nowrap colspan=2>Las Cuotas Vencidas</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">
                <input type="radio" name="E01PAGFLG" value="PC" 
			  <%if(lnCancel.getE01PAGFLG().equals("PC")) out.print("checked");%> onclick="checkVal(this.value)">
              </div>
            </td>
            <td nowrap colspan=2> Por Cuotas (Cantidad de Cuotas 
              <input type="text" name="E01PAGFLG1" size="3" maxlength="2" onKeypress="enterInteger()">
              ) </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">
                <input type="radio" name="E01PAGFLG" value="DS" 
			  <%if(lnCancel.getE01PAGFLG().equals("DS")) out.print("checked");%> onclick="checkVal(this.value)">
              </div>
            </td>
            <td nowrap colspan=2> Monto a Distribuir (Valor a Pagar 
              <input type="text" name="E01PAGTOT" size="15" maxlength="15" value="<%= lnCancel.getE01PAGDIS().trim()%>" onKeypress="enterDecimal()">
              ) </td>
          </tr>
       
        <%} else {
            if (userPO.getType().equals("2")) {
        %>
        	<tr id="trdark"> 
            <td nowrap > 
              <div align="right">
                <input type="radio" name="E01PAGFLG" value="TP" checked>
              </div>
            </td>
            <td nowrap>Pago Parcial 
             <input type="text" name="E01PAGABO" size="15" maxlength="15" value="<%= lnCancel.getE01PAGDIS().trim()%>" onKeypress="enterDecimal()">
            </td>
            <TD nowrap>
              <div id="selParcial">
            	<INPUT type="radio" name="E01DEASEL" value="1" <% if (!lnCancel.getE01DEASEL().equals("2")) out.print("checked");%>>Adelgazar Valor Cuota
				<INPUT type="radio" name="E01DEASEL" value="2" <% if (lnCancel.getE01DEASEL().equals("2")) out.print("checked");%>>Reducir Plazo
			  </div>
			</TD>						
          </tr>
         <% } else {%> 
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">                
                <input type="radio" name="E01PAGFLG" value="TO"
			  <%if(lnCancel.getE01PAGFLG().equals("TO")) out.print("checked");%>>
              </div>
            </td>
            <td nowrap colspan=2>Pago Total</td>
          </tr>
          <% if (userPO.getOption().equals("LC") || userPO.getOption().equals("SP")){%>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">
                <input type="radio" name="E01PAGFLG" value="DS" 
			  <%if(lnCancel.getE01PAGFLG().equals("DS")) out.print("checked");%>>
              </div>
            </td>
            <td nowrap colspan=2> Monto a Distribuir (Valor a Pagar 
              <input type="text" name="E01PAGTOT" size="15" maxlength="15" value="<%= lnCancel.getE01PAGDIS().trim()%>" onKeypress="enterDecimal()">
              ) </td>
          </tr>
          <% } else {%>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">
                <input type="radio" name="E01PAGFLG" value="PC" 
			  <%if(lnCancel.getE01PAGFLG().equals("PC")) out.print("checked");%>>
              </div>
            </td>
            <td nowrap colspan=2> Por Cuotas (Cantidad de Cuotas 
              <input type="text" name="E01PAGFLG1" size="3" maxlength="2" onKeypress="enterInteger()">
              ) </td>
          </tr>
        <%	}
          }
        } %>
         </table>
      </td>
    </tr>
  </table>

  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>

</form>
</body>
</html>
