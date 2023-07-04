<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Consulta de Prestamos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="lnChgSts" class="datapro.eibs.beans.EDL013501Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

</head>
<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
%>
 
<h3 align="center">Condiciones del Pr&eacute;stamo<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ln_inq_chg_status.jsp,EDL0135"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0135" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  
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
                <input type="text" name="E01DEACUN" size="9" maxlength="9" value="<%= lnChgSts.getE01DEACUN().trim()%>" readonly>
              </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" name="E01CUSNA1" size="45" maxlength="45" value="<%= lnChgSts.getE01CUSNA1().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01DEAACC" size="12" maxlength="12" value="<%= lnChgSts.getE01DEAACC().trim() %>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="4" maxlength="3" value="<%= lnChgSts.getE01DEACCY().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left">
                <input type="text" name="E01DEAPRO" size="4" maxlength="4" value="<%= lnChgSts.getE01DEAPRO().trim()%>" readonly>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  <h4>Datos B&aacute;sicos de la Operaci&oacute;n</h4> 

  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Monto Original :</div>
            </td>
            <td nowrap> 
              <%= lnChgSts.getE01DEAOAM().trim()%> 
            </td>            
            <td nowrap> 
              <div align="right">Saldo Principal :</div>
            </td>
            <td nowrap > 
              <%= lnChgSts.getE01BALPRI().trim()%>
            </td>            
          </tr> 
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha de Apertura :</div>
            </td>
            <td nowrap width="25%"> 
              <%= Util.formatDate(lnChgSts.getE01DEAOD1(),lnChgSts.getE01DEAOD2(),lnChgSts.getE01DEAOD3()) %>
            </td>
            <td nowrap> 
              <div align="right">Saldo Inter&eacute;s :</div>
            </td>
            <td nowrap > 
              <%= lnChgSts.getE01BALINT().trim()%>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap> 
              <%= Util.formatDate(lnChgSts.getE01DEAMD1(),lnChgSts.getE01DEAMD2(),lnChgSts.getE01DEAMD3()) %> 
            </td>            
            <td nowrap> 
              <div align="right">Saldo Reajuste :</div>
            </td>
            <td nowrap > 
              <%= lnChgSts.getE01BALREA().trim()%>
            </td>            
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Estado :</div>
            </td>
            <td nowrap>               
               <% if (lnChgSts.getE01DEADLC().equals("1")) out.print("Vigente");
                else if(lnChgSts.getE01DEADLC().equals("2")) out.print("Vencido");
                else if(lnChgSts.getE01DEADLC().equals("3")) out.print("Castigado");
                else if(lnChgSts.getE01DEADLC().equals("4")) out.print("Castigado No Informado");%>
            </td>          
            <td nowrap> 
              <div align="right">Saldo Mora :</div>
            </td>
            <td nowrap > 
              <%= lnChgSts.getE01BALMOR().trim()%>
            </td>           
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Tasa Inter&eacute;s :</div>
            </td>
            <td nowrap>               
               <%= lnChgSts.getE01DEARTE().trim()%>
            </td>          
            <td nowrap> 
              <div align="right">Otros Cargos :</div>
            </td>
            <td nowrap > 
              <%= lnChgSts.getE01BALOTH().trim()%>
            </td>           
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Per&iacute;odo Base :</div>
            </td>
            <td nowrap>               
               <%= lnChgSts.getE01DEABAS().trim()%>
            </td>          
            <td nowrap> 
              <div align="right">Saldo Total :</div>
            </td>
            <td nowrap > 
              <%= lnChgSts.getE01BALTOT().trim()%>
            </td>           
          </tr> 
        </table>
      </td>
    </tr>
  </table>
<% if (lnChgSts.getE01FLGOPE().equals("1")) {%>
<h4>Cambio de Estado</h4>
<table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr  id=trdark>
            <td nowrap align="right"> 
               Nuevo Estado :
            </td>          
 			<td nowrap >                
				<% if (lnChgSts.getE01NEWDLC().equals("2")) out.print("Vencido"); 
				else if (lnChgSts.getE01NEWDLC().equals("3")) out.print("Castigado"); 
				else if (lnChgSts.getE01NEWDLC().equals("4")) out.print("Castigado No Informado"); %>		 			   
 			</td>
 		  </tr>                     
        </table>
      </td>
    </tr>
  </table>
<% } %>
<% if (lnChgSts.getE01FLGOPE().equals("2")) {%>
  <h4>Gesti&oacute;n Cobranza</h4>
<table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id=trdark>
            <td nowrap align="right"> 
               Fecha :
            </td>
 			<td nowrap >                
			  <input type="text" name="E01DATEP1" size="2" maxlength="2" value="<%= lnChgSts.getE01DATEP1().trim()%>" readonly>
              <input type="text" name="E01DATEP2" size="2" maxlength="2" value="<%= lnChgSts.getE01DATEP2().trim()%>" readonly>
              <input type="text" name="E01DATEP3" size="2" maxlength="2" value="<%= lnChgSts.getE01DATEP3().trim()%>" readonly>		  			  
 			</td>
 		  </tr> 
 		  <tr id=trclear>
 			<td nowrap align="right">                 
				Centro Responsabilidad :		  			   
 			</td>          
 			<td nowrap >                 
 				<input type="text" name="E01RESPON" size="5" maxlength="4" value="<%= lnChgSts.getE01RESPON().trim()%>" readonly>
                <input type="text" name="D01RESPON" size="36" maxlength="35" value="<%= lnChgSts.getD01RESPON().trim()%>"readonly>
            </td>
 		  </tr>                     
        </table>
      </td>
    </tr>
  </table>
<% } %> 
<% if (lnChgSts.getE01FLGOPE().equals("3")) {%>
  <h4>Acci&oacute;n : Suspenci&oacute;n de Devengos</h4>
<% } %>
<% if (lnChgSts.getE01FLGOPE().equals("4")) {%>
  <h4>Acci&oacute;n : Aceleraci&oacute;n</h4>
<% } %>    
  </form>
</body>
</html>
