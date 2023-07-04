<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Información Básica de Certificados de Depósito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="deduct" class="datapro.eibs.beans.EDL037001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
 builtHPopUp();

 function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
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
<% 
    String row = "0";
    try {
      row = request.getParameter("ROW");
      if (row==null) row ="0";
    }
    catch (Exception e) {
      row = "0";
    } 
 %>
<h3 align="center">Deducciones
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="inq_ded_basic.jsp, EDL0370"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0370" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
  <INPUT TYPE=HIDDEN NAME="E01DEABNK"  value="<%= deduct.getE01DEABNK().trim()%>">
  <input type=HIDDEN NAME="E01DEAACD"  value="<%= deduct.getE01DEAACD().trim()%>">
  <INPUT TYPE=HIDDEN NAME="OPT" VALUE="N"> 	  
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= row %>">
  <input type=HIDDEN NAME="E01DLITYP"  value="<%= deduct.getE01DLITYP().trim()%>"> 
  <table class="tableinfo" id="headtable">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="0" width="100%" >          
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>No. Prestamo :</b></div>
            </td>
            <td nowrap> 
              <div align="left">
                <input type="text" name="E01DEAACC" size="12" maxlength="12" value="<%= deduct.getE01DEAACC().trim()%>" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="right"><b>Compañia : </b></div>
            </td>
            <td nowrap> 
              <div align="left">
                <input type="text" name="E01DLICDE" size="4" value="<%= deduct.getE01DLICDE().trim()%>" readonly>
                <input type="text" name="E01DLINME" size="35" value="<%= deduct.getE01DLINME().trim()%>" readonly>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Prestatario</h4>
   
    <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">No Poliza :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DLIREF" size="20" maxlength="20" value="<%= deduct.getE01DLIREF().trim()%>" readonly> </td>
            <td nowrap > 
              <div align="right">Valor Compra :</div>
            </td>
            <td nowrap colspan=2> 
              <input type="text" name="E01DLIPAM" size="17" maxlength="17" value="<%= deduct.getE01DLIPAM().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Descripción :</div>
            </td>
            <td nowrap colspan="4"> 
              <input type="text" name="E01DLIRMK" size="31" maxlength="30" value="<%= deduct.getE01DLIRMK().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap > 
              
            </td>
            <td nowrap > 
              <div align="right">Factor :</div>
            </td>
            <td nowrap colspan=2> 
              <select name="E01DLIFAC" disabled>
                <option value="F" <% if (!(deduct.getE01DLIFAC().equals("1") ||deduct.getE01DLIFAC().equals("2") ||deduct.getE01DLIFAC().equals("3"))) out.print("selected"); %>>FACTOR FIJO</option>
                <option value="1" <% if (deduct.getE01DLIFAC().equals("1")) out.print("selected"); %>>% sobre Monto Original</option>
                <option value="2" <% if (deduct.getE01DLIFAC().equals("2")) out.print("selected"); %>>% sobre Monto Principal</option>
                <option value="3" <% if (deduct.getE01DLIFAC().equals("3")) out.print("selected"); %>>% sobre valor Cuota</option>
              </select>
            </td>
          </tr>
  <% if ( deduct.getH01FLGWK3().equals("3")) {%>        
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Año Fabricación :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DLIYOP" size="5" maxlength="4" value="<%= deduct.getE01DLIYOP().trim()%>" readonly>
            </td>
            <td nowrap > 
              <div align="right">Tabla Seguro :</div>
            </td>
            <td nowrap colspan=2> 
              <input type="text" name="E01DLITBL" size="2" maxlength="2" value="<%= deduct.getE01DLITBL().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Tipo Cobertura :</div>
            </td>
            <td nowrap >
             <INPUT type="checkbox" name="E01DLICMP" value="1" <% if (deduct.getE01DLICMP().equals("1")) out.print("checked"); %> disabled>Comprensivo
            </td>
            <td nowrap >
             <INPUT type="checkbox" name="E01DLICOL" value="1" <% if (deduct.getE01DLICOL().equals("1")) out.print("checked"); %> disabled>Colisión
            </td>
            <td nowrap >
             <INPUT type="checkbox" name="E01DLIINC" value="1" <% if (deduct.getE01DLIINC().equals("1")) out.print("checked"); %> disabled>Incendio
            </td>
            <td nowrap >
             <INPUT type="checkbox" name="E01DLIROB" value="1" <% if (deduct.getE01DLIROB().equals("1")) out.print("checked"); %> disabled>Robo
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Meses de Seguro :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DLIINM" size="3" maxlength="3" value="<%= deduct.getE01DLIINM().trim()%>" readonly>
            </td>
            <td nowrap > 
              <div align="right">Meses Incli.Princ.:</div>
            </td>
            <td nowrap colspan=2> 
              <input type="text" name="E01DLIDDY" size="3" maxlength="2" value="<%= deduct.getE01DLIDDY().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Forma de Pago :</div>
            </td>
            <td nowrap > 
              <select name="E01DLIPMF" disabled>
                <option value=" " <% if (!(deduct.getE01DLIPMF().equals("1") || deduct.getE01DLIPMF().equals("2") || deduct.getE01DLIPMF().equals("3"))) out.print("selected"); %>></option>
                <option value="1" <% if (deduct.getE01DLIPMF().equals("1")) out.print("selected"); %>>Apertura Pre Pagado</option>
                <option value="2" <% if (deduct.getE01DLIPMF().equals("2")) out.print("selected"); %>>Apertura capitalizado</option>
                <option value="3" <% if (deduct.getE01DLIPMF().equals("3")) out.print("selected"); %>>Ciclo de Pago</option>
                </select>
            </td>
            <td nowrap > 
              <div align="right">Cobro de I.V.A/F.E.C.I :</div>
            </td>
            <td nowrap colspan=2> 
              <input type="radio" name="E01DLIIVA" value="Y" <%if(deduct.getE01DLIIVA().equals("Y")) out.print("checked");%> disabled>
                Sí 
              <input type="radio" name="E01DLIIVA" value="N" <%if(!deduct.getE01DLIIVA().equals("Y")) out.print("checked");%> disabled>
                No
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Pago Mensual :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DLIDPM" size="17" maxlength="17" value="<%= deduct.getE01DLIDPM().trim()%>" readonly>
            </td>
            <td nowrap > 
              <div align="right">Pago 1er Año :</div>
            </td>
            <td nowrap colspan=2> 
              <input type="text" name="E01DLIBEG" size="17" maxlength="17" value="<%= deduct.getE01DLIBEG().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

<h4>Contrapartida</h4>
  
  <TABLE id="mainTable" class="tableinfo">
  <TR><TD>
  
   		<table cellspacing=0 cellpadding=2 width="100%" border="0">
    		
           <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Bnc/Suc/Mnd/GL :</div>
            </td>
            <td nowrap colspan=5>                
                <input type="text" name="E01DLIBNK" size="2" maxlength="2" value="<%= deduct.getE01DLIBNK().trim()%>" readonly>             
                <input type="text" name="E01DLIBRN" size="3" maxlength="3" value="<%= deduct.getE01DLIBRN().trim()%>" readonly>             
                <input type="text" name="E01DLICCY" size="3" maxlength="3" value="<%= deduct.getE01DLICCY().trim()%>" readonly>              
                <input type="text" name="E01DLIGLN" size="17" maxlength="17" value="<%= deduct.getE01DLIGLN().trim()%>" readonly>        
            </td>
          </tr>
        </table>
     </TD>  
</TR>	
</TABLE>    

  <% } else {%> 
     <table>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Cuenta Acreditar :</div>
            </td>
            <td nowrap colspan=4>                
                <input type="text" name="E01DLIBNK" size="2" maxlength="2" value="<%= deduct.getE01DLIBNK().trim()%>" readonly>              
                <input type="text" name="E01DLIBRN" size="3" maxlength="3" value="<%= deduct.getE01DLIBRN().trim()%>" readonly>              
                <input type="text" name="E01DLICCY" size="3" maxlength="3" value="<%= deduct.getE01DLICCY().trim()%>" readonly>              
                <input type="text" name="E01DLIGLN" size="17" maxlength="17"  value="<%= deduct.getE01DLIGLN().trim()%>" readonly>        
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Forma de Pago :</div>
            </td>
            <td nowrap > 
              <select name="E01DLIPMF" disabled>
              	<option value=" " <% if (!(deduct.getE01DLIPMF().equals("1") || deduct.getE01DLIPMF().equals("2") || deduct.getE01DLIPMF().equals("3"))) out.print("selected"); %>></option>
                <option value="1" <% if (deduct.getE01DLIPMF().equals("1")) out.print("selected"); %>>Apertura Pre Pagado</option>
                <option value="2" <% if (deduct.getE01DLIPMF().equals("2")) out.print("selected"); %>>Apertura capitalizado</option>
                <option value="3" <% if (deduct.getE01DLIPMF().equals("3")) out.print("selected"); %>>Ciclo de Pago</option>
              </select>
            </td>
            <td nowrap > 
              <div align="right">Cobro de I.V.A/F.E.C.I :</div>
            </td>
            <td nowrap colspan=2> 
              <input type="radio" name="E01DLIIVA" value="Y" <%if(deduct.getE01DLIIVA().equals("Y")) out.print("checked");%> disabled>
                Sí 
              <input type="radio" name="E01DLIIVA" value="N" <%if(!deduct.getE01DLIIVA().equals("Y")) out.print("checked");%> disabled>
                No
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Deducción :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DLIDPM" size="17" maxlength="17" value="<%= deduct.getE01DLIDPM().trim()%>" readonly>
            </td>
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap colspan=2> 
            </td>
          </tr>
          
  		</table>

<H4>Compañia</H4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Fecha Proximo Pago :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DLINPD" size="2" maxlength="2" value="<%= deduct.getE01DLINPD().trim()%>" readonly> 
              <input type="text" name="E01DLINPM" size="2" maxlength="2" value="<%= deduct.getE01DLINPM().trim()%>" readonly> 
              <input type="text" name="E01DLINPY" size="2" maxlength="2" value="<%= deduct.getE01DLINPY().trim()%>" readonly> 
            </td>             
          </tr>
        </table>  
     </TD>  
</TR>	
</TABLE>   
  <% } %> 
</form>
</body>
</html>
