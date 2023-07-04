<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Pago de Prestámos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="Javascript"> 
 	function checkNuPay(){
 		var val = "";
 		if (document.forms[0].CE01PAGFLG[3].checked) {
 		    val = trim(document.forms[0].E01PAGFLG1.value);
 			if (val.length == 1) val="0" + val;
 			document.forms[0].E01PAGFLG1.value = val;
 		}
 		return true;
 	}
  	function checkVal(val){ 		
 		selParcial.style.display = (val=="TP") ?  "" : "none";
 		document.forms[0].E01PAGFLG.value=val;
 	}
 	
</SCRIPT>
</head>

<jsp:useBean id= "lnCancel" class= "datapro.eibs.beans.EDL095001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 
<H3 align="center">Pago de Pr&eacute;stamos <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_payment.jsp, EDL0950"> 
</H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0950" onsubmit="return(checkNuPay())">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="10">
  <input type="hidden" name="E01PAGFLG" value="<%= lnCancel.getE01PAGFLG()%>">
  <input type="hidden" name="H01FLGWK3">
  
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
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text" size="45" maxlength="45" name="E01CUSNA1" value="<%= lnCancel.getE01CUSNA1().trim()%>" readonly>
                </font></font></font></div>
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
            <td nowrap width="40%" height="23"> 
              <div align="right">Principal :</div>
            </td>
            <td nowrap width="20%" height="23"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01VENPRI().trim())%></div>
            </td>
            <td nowrap width="23%" height="23"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01PRXPRI().trim())%></div>
            </td>
            <td nowrap height="23" width="17%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01SALPRI().trim())%></div>
            </td>
          </tr>
          <%if(lnCancel.getH01FLGWK3().equals("R")){%>
          <tr id="trclear"> 
            <td nowrap width="40%" height="19"> 
              <div align="right">Reajuste :</div>
            </td>
            <td nowrap width="20%" height="19"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01VENREA().trim())%></div>
            </td>
            <td nowrap width="23%" height="19"> 
              <div align="right"><%= Util.fcolorCCY(lnCancel.getE01PRXREA().trim())%></div>
            </td>
            <td nowrap height="19" width="17%"> 
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
              <div align="right">Mora :</div>
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
         <%}
		  else 
		  {
		  %>
		 <tr id="trclear">
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
          <tr id="trclear"> 
         <%}
		  else {
         %>
		 <tr id="trdark">
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
          <tr id="trdark"> 
         <%}
		   else {
		  %>
		 <tr id="trclear">
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
          <tr id="trclear"> 
         <%}
		   else {
		%>
		 <tr id="trdark">
         <%}%>
            <td nowrap width="40%"> 
              <div align="right">I.V.A/F.E.C.I :</div>
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
          <tr id="trdark"> 
         <%} 
		   else {
         %>
		 <tr id="trclear">
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
          <tr id="trclear"> 
            <td nowrap width="40%"> 
              <div align="right">                
                <input type="radio" name="CE01PAGFLG" value="MA"
			  <%if(!(lnCancel.getE01PAGFLG().equals("TO") || lnCancel.getE01PAGFLG().equals("TP")
			  || lnCancel.getE01PAGFLG().equals("CV")
			  || lnCancel.getE01PAGFLG().equals("PC")
			  || lnCancel.getE01PAGFLG().equals("DS"))) out.print("checked");%> onclick="checkVal(this.value)">
              </div>
            </td>
            <td nowrap width="60%">Distribución Manual</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%"> 
              <div align="right">                
                <input type="radio" name="CE01PAGFLG" value="TO" 
			  <%if(lnCancel.getE01PAGFLG().equals("TO")) out.print("checked");%> onclick="checkVal(this.value)">
              </div>
            </td>
            <td nowrap width="60%">Pago Total</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="40%"> 
              <div align="right">
                <input type="radio" name="CE01PAGFLG" value="TP" 
			  <%if(lnCancel.getE01PAGFLG().equals("TP")) out.print("checked");%> onclick="checkVal(this.value)">
              </div>
            </td>
            <td nowrap width="60%">Pago Parcial 
             <input type="text" name="E01PAGABO" size="15" maxlength="15" value="<%= lnCancel.getE01PAGDIS().trim()%>" onKeypress="enterDecimal()">

              <span id="selParcial" <%if(!lnCancel.getE01PAGFLG().equals("TP")) out.print("style=\"display:none\"");%>>
            	<INPUT type="radio" name="E01DEASEL" value="1" <% if (!lnCancel.getE01DEASEL().equals("2")) out.print("checked");%>>Adelgazar Valor Cuota
				<INPUT type="radio" name="E01DEASEL" value="2" <% if (lnCancel.getE01DEASEL().equals("2")) out.print("checked");%>>Reducir Plazo
			  </span>
             
			</td>

          </tr>

          <tr id="trdark"> 
            <td nowrap width="40%"> 
              <div align="right">
                <input type="radio" name="CE01PAGFLG" value="CV" 
			  <%if(lnCancel.getE01PAGFLG().equals("CV")) out.print("checked");%> onclick="checkVal(this.value)">
              </div>
            </td>
            <td nowrap width="60%">Las Cuotas Vencidas</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="40%"> 
              <div align="right">
              	&nbsp;
              </div>
            </td>
            <td nowrap width="60%"> 
                
                <TABLE>
                	<TBODY>
                		<TR>
                			<TD>Por Cuotas :</TD>
                			<TD>
				            	<input type="radio" name="CE01PAGFLG" value="PC" 
							    <%if(lnCancel.getE01PAGFLG().equals("PC")) out.print("checked");%> onclick="checkVal(this.value)">
				            	Número de Cuotas
                			</TD>
                			<TD>
			            		<input type="text" name="E01PAGFLG1" size="3" maxlength="2" onKeypress="enterInteger()">
                			</TD>
                		</TR>

                		<TR>
                			<TD>&nbsp;</TD>
                			<TD>
				                <input type="radio" name="CE01PAGFLG" value="PM" 
							    <%if(lnCancel.getE01PAGFLG().equals("PM")) out.print("checked");%> onclick="checkVal(this.value)">
				            	Monto a Aplicar
                			</TD>
                			<TD>
				                <input type="text" name="E01PAGQAM" size="17" maxlength="15" onKeypress="enterDecimal()" > 
                			</TD>
                		</TR>

                	</TBODY>
                </TABLE>
                
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%"> 
              <div align="right">
                <input type="radio" name="CE01PAGFLG" value="DS" 
			  <%if(lnCancel.getE01PAGFLG().equals("DS")) out.print("checked");%> onclick="checkVal(this.value)">
              </div>
            </td>
            <td nowrap width="60%"> Monto a Distribuir (Valor a Pagar 
              <input type="text" name="E01PAGTOT" size="15" maxlength="15" value="<%= lnCancel.getE01PAGDIS().trim()%>" onKeypress="enterDecimal()">
              ) </td>
          </tr>
        </table>
        
      </td>
    </tr>
  </table>
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</p>
</form>
</body>
</html>
