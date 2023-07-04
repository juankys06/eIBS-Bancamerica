<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Mantenimiento de Depositos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="lnChgCust" class="datapro.eibs.beans.EDL013502Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

<%if (userPO.getPurpose().equals("MAINTENANCE")){%>

   

<%}%>

  
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
%>
 
<h3 align="center">Custodia de Dep&oacute;sitos a Plazo<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cd_chg_cust.jsp,EDL0135"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0135" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
  
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
                <input type="text" name="E02DEACUN" size="9" maxlength="9" value="<%= lnChgCust.getE02DEACUN().trim()%>" readonly>
              </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" name="E02CUSNA1" size="45" maxlength="45" value="<%= lnChgCust.getE02CUSNA1().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E02DEAACC" size="12" maxlength="12" value="<%= lnChgCust.getE02DEAACC().trim() %>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E02DEACCY" size="4" maxlength="3" value="<%= lnChgCust.getE02DEACCY().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left">
                <input type="text" name="E02DEAPRO" size="4" maxlength="4" value="<%= lnChgCust.getE02DEAPRO().trim()%>" readonly>
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
              <%= lnChgCust.getE02DEAOAM().trim()%> 
            </td>            
            <td nowrap> 
              <div align="right">Saldo Principal :</div>
            </td>
            <td nowrap > 
              <%= lnChgCust.getE02BALPRI().trim()%>
            </td>            
          </tr> 
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha de Apertura :</div>
            </td>
            <td nowrap width="25%"> 
              <%= Util.formatDate(lnChgCust.getE02DEAOD1(),lnChgCust.getE02DEAOD2(),lnChgCust.getE02DEAOD3()) %>
            </td>
            <td nowrap> 
              <div align="right">Saldo Inter&eacute;s :</div>
            </td>
            <td nowrap > 
              <%= lnChgCust.getE02BALINT().trim()%>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap> 
              <%= Util.formatDate(lnChgCust.getE02DEAMD1(),lnChgCust.getE02DEAMD2(),lnChgCust.getE02DEAMD3()) %> 
            </td>            
            <td nowrap> 
              <div align="right">Saldo Reajuste :</div>
            </td>
            <td nowrap > 
              <%= lnChgCust.getE02BALREA().trim()%>
            </td>            
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Estado :</div>
            </td>
            <td nowrap>               
               <%= lnChgCust.getE02DEASTS().trim()%>
            </td>          
            <td nowrap> 
              <div align="right">Saldo Total :</div>
            </td>
            <td nowrap > 
              <%= lnChgCust.getE02BALTOT().trim()%>
            </td>           
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Tasa Inter&eacute;s :</div>
            </td>
            <td nowrap>               
               <%= lnChgCust.getE02DEARTE().trim()%>
            </td>          
            <td nowrap> 
              <div align="right"></div>
            </td>
            <td nowrap > 
              
            </td>           
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Per&iacute;odo Base :</div>
            </td>
            <td nowrap>               
               <%= lnChgCust.getE02DEABAS().trim()%>
            </td>          
            <td nowrap> 
              <div align="right"></div>
            </td>
            <td nowrap >              
            </td>           
          </tr>
          <tr id="trdark">
            <td nowrap align="right">Custodia : </td>
            <td nowrap>
               <% if (lnChgCust.getE02DEAFRA().equals("1")) out.print("Custodia Electronica");
                else out.print("No Custodia"); %>
            </td>
            <td nowrap > 
              <div align="right">Forma de Pago :</div>
            </td>
            <td nowrap > 
              <% if(lnChgCust.getE02DEASOF().equals("0")) out.print("Efectivo");
                else if(lnChgCust.getE02DEASOF().equals("1")) out.print("Documento CorpBanca");
                else if(lnChgCust.getE02DEASOF().equals("2")) out.print("Documento Otros Bancos misma Localidad");
              	else if(lnChgCust.getE02DEASOF().equals("4")) out.print("Documento Otros Bancos Otras Localidades");
                else if(lnChgCust.getE02DEASOF().equals("5")) out.print("Vale Vista,Cheque Fiscal");%>               
            </td>
          </tr>     
        </table>
      </td>
    </tr>
  </table>

<h4>Cambio de Custodia</h4>
<table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr  id=trdark>
            <td nowrap align="right">Nueva Custodia : </td>
            <td nowrap>
             <SELECT name="E02DEAFRA">
                <OPTION value="N" <% if (!lnChgCust.getE02DEAFRA().equals("1")) out.print("selected"); %>>No Custodia</OPTION>
                <OPTION value="1" <% if (lnChgCust.getE02DEAFRA().equals("1")) out.print("selected"); %>>Custodia Electronica</OPTION>
             </SELECT>
            </td>
                
          
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
