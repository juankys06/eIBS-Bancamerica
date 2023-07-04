<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Consulta de Certificados de Deposito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="cdInquiry" class="datapro.eibs.beans.EDL016001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "pmnt" class= "datapro.eibs.beans.JBListRec"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(cd_i_opt);
   initMenu(); 
    
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
    out.println("<SCRIPT> initMenu(); </SCRIPT>");
 
%> 
<div align="center"></div>
<h3 align="center"> Consulta de Saldos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_inquiry, EDL0160"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130" >
  <input type=HIDDEN name="SCREEN" value="14">
  <table border="0" cellspacing="0" cellpadding="0" width="100%">
  	<tr>
  		<td align="right" valign="top" width="85%" style="color:red;font-size:12;"><b><%=cdInquiry.getE01PENDAP()%></b></td>
  		<td width="5%"><h4>&nbsp;</h4></td>
  	</tr>
  </table>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E02CUN2" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap> 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E02NA12" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="right"><b>Producto :</b></div>
            </td>
            <td nowrap ><b> 
              <input type="text" name="E02PRO2" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
              </b></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Contrato :</b></div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E02ACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="right"><b>Oficial :</b></div>
            </td>
            <td nowrap> 
              <div align="left"><b> 
                <input type="text" name="E02NA122" size="30" maxlength="30" readonly value="<%= userPO.getOfficer().trim()%>">
                </b> </div>
            </td>
            <td nowrap> 
              <div align="right"><b>Moneda :</b></div>
            </td>
            <td nowrap> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  <table class=tbenter>
   <tr > 
      <td nowrap> 
   		<h4>Sumario</h4>
      </td>
      <td nowrap align=left> 
   		<b>DOCUMENTO :<font color="#ff6600"> <% if (cdInquiry.getE01DEASOF().trim().equals("N") || cdInquiry.getE01DEASOF().trim().equals("")) { out.print("EN NO CUSTODIA");}
   		      else if (cdInquiry.getE01DEASOF().trim().equals("1")) {out.print(" EN CUSTODIA ELECTRONICA");}
   		      else if (cdInquiry.getE01DEASOF().trim().equals("2")) {out.print("IMPRESO");}%></font></b>
      </td>
      <td nowrap align=left> 
   		<b>GARANTIA :<font color="#ff6600"> <% 	if (cdInquiry.getE01COLATR().trim().equals("") )
   													out.print("NO GARANTIZADO");
   												else
   													out.print(cdInquiry.getE01COLATR());
   											%></font></b>
      </td>
      <td nowrap align=right> 
   		<b>ESTADO :</b>
      </td>
      <td nowrap> 
   		<b><font color="#ff6600"><%= cdInquiry.getE01STATUS().trim()%></font></b>
      </td>
    </tr>
  </table>
  
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Monto Original :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01DEAOAM" size="23" maxlength="15" value="<%= cdInquiry.getE01DEAOAM().trim()%>" readonly>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Fecha de Apertura :</div>
            </td>
            <td nowrap width="26%"> 
              <input type="text" name="E01DEAOD1" size="2" maxlength="2" value="<%= cdInquiry.getE01DEAOD1().trim()%>" readonly>
              <input type="text" name="E01DEAOD2" size="2" maxlength="2" value="<%= cdInquiry.getE01DEAOD2().trim()%>" readonly>
              <input type="text" name="E01DEAOD3" size="2" maxlength="2" value="<%= cdInquiry.getE01DEAOD3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Saldo Principal :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01DEAMEP" size="23" maxlength="15" value="<%= cdInquiry.getE01DEAMEP().trim()%>" readonly>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap width="26%"> 
              <input type="text" name="E01DEAMD1" size="2" maxlength="2" value="<%= cdInquiry.getE01DEAMD1().trim()%>" readonly>
              <input type="text" name="E01DEAMD2" size="2" maxlength="2" value="<%= cdInquiry.getE01DEAMD2().trim()%>" readonly>
              <input type="text" name="E01DEAMD3" size="2" maxlength="2" value="<%= cdInquiry.getE01DEAMD3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Monto Reajuste :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01DEAREA" size="23" maxlength="15" value="<%= cdInquiry.getE01DEAREA().trim()%>" readonly>
            </td>
            <td nowrap width="26%"> 
              <div align="right">T&eacute;rmino :</div>
            </td>
            <td nowrap width="26%"> 
              <input type="text" name="E01DEATRM" size="4" maxlength="4" value="<%= cdInquiry.getE01DEATRM().trim()%>" readonly>
              <input type="text" name="E01DEATRC" size="10" 
				  value="<% if (cdInquiry.getE01DEATRC().equals("D")) out.print("D&iacute;a(s)");
							else if (cdInquiry.getE01DEATRC().equals("M")) out.print("Mes(es)");
							else if (cdInquiry.getE01DEATRC().equals("Y")) out.print("A&ntilde;o(s)");
							else out.print("");%>" 
				readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Saldo Intereses :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01DEAMEI" size="23" maxlength="15" value="<%= cdInquiry.getE01DEAMEI().trim()%>" readonly>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Tasa Interes :</div>
            </td>
            <td nowrap width="26%"> 
              <input type="text" name="E01NOWRTE2" size="9" maxlength="9" value="<%= cdInquiry.getE01NOWRTE().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Monto Retenci&oacute;n :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01DEAHAM" size="23" maxlength="15" value="<%= cdInquiry.getE01DEAHAM().trim()%>" readonly>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Per&iacute;odo Base :</div> 
            </td>
            <td nowrap width="26%"> 
              <input type="text" name="E01DEABAS" size="4" maxlength="3" value="<%= cdInquiry.getE01DEABAS().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Saldo Actual :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01MEMBAL" size="23" maxlength="15" value="<%= cdInquiry.getE01MEMBAL().trim()%>" readonly>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Inter&eacute;s al Vencimiento :</div>
            </td>
            <td nowrap width="26%"> 
              <input type="text" name="E01MATINT" size="15" maxlength="15" value="<%= cdInquiry.getE01MATINT().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Firma Digital :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01SHAOUT" size="50" maxlength="50" value="<%= cdInquiry.getE01SHAOUT().trim()%>" readonly>
            </td>
            <td nowrap width="26%"> 
            </td>
            <td nowrap width="26%"> 
            </td>
          </tr>


        </table>
      </td>
    </tr>
  </table>
  <%
String flag = cdInquiry.getH01FLGWK3();
%>
  <%@ include file="ESD0840_reevaluation_inquiry.jsp" %>
  <h4>Renovaciones</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap height="33"> 
        <table width="100%">
          <tr id="trdark">
            <td>

<%
 if (cdInquiry.getE01DEAROC().equals("A")) out.print("Principal m&aacute;s Intereses ser&aacute;n renovados  por el mismo per&iacute;odo de tiempo. ");
 else if (cdInquiry.getE01DEAROC().equals("B")) {
 					out.print("Inter&eacute;s ser&aacute; acreditado a " + cdInquiry.getE01TEXTO2().trim() + " que tendr&aacute;n como n&uacute;mero" 
 				  + cdInquiry.getE01DEARAC().trim() + ". El Principal ser&aacute; renovado por el mismo per&iacute;odo de tiempo ");
 				}
 else if (cdInquiry.getE01DEAROC().equals("C")) {
 					out.print("Inter&eacute;s ser&aacute; acreditado  a la cuenta contable n&uacute;mero " + cdInquiry.getE01DEARAC().trim() + ", emitiendo " 
 				  + cdInquiry.getE01TEXTO2().trim() + ". El Principal ser&aacute; renovado por el mismo per&iacute;odo de tiempo ");
 				}
else if (cdInquiry.getE01DEAROC().equals("D")) {
 					out.print("Al vencimiento cancelar el Dep&oacute;sito mas los Intereses acreditando a la cuenta contable n&uacute;mero  " + cdInquiry.getE01DEARAC().trim()
 					+ ", emitiendo " +  cdInquiry.getE01TEXTO2().trim()) ;
 				}
else if (cdInquiry.getE01DEAROC().equals("E")) {
 					out.print("Al vencimiento cancelar el Dep&oacute;sito mas los Intereses acreditando a la cuenta  (Corriente, Ahorro, etc.) n&uacute;mero  " +  cdInquiry.getE01DEARAC().trim() ) ;  
 				}
else if (cdInquiry.getE01DEAROC().equals("F")) {
 					out.print("Al vencimiento renovar el Dep&oacute;sito y los Intereses,  " + cdInquiry.getE01TEXTO2().trim() + "  en  "   +  cdInquiry.getE01DEAROA().trim()  	+  " contra la cuenta (Corriente, Ahorro, etc.) n&uacute;mero "  +  cdInquiry.getE01DEARAC().trim()) ;
 				} 
else if (cdInquiry.getE01DEAROC().equals("G")) {
 					out.print("Al vencimiento renovar el Dep&oacute;sito y los Intereses por otro per&iacute;odo similar, disminuyendo los Intereses en " +  cdInquiry.getE01DEAROA().trim()  +  " y acreditando los mismos en la Cuenta (Corriente, Ahorro, etc.) n&uacute;mero "  
 				  + cdInquiry.getE01DEARAC().trim()  ); 
 				}	
else if (cdInquiry.getE01DEAROC().equals("H")) {
 					out.print("Inter&eacute;s ser&aacute; pagado cada  " + cdInquiry.getE01DEAROY().trim() + "  " + cdInquiry.getE01TEXTO1().trim() + "  a " + cdInquiry.getE01TEXTO2().trim()
					+ " que tendr&aacute;  como n&uacute;mero " + cdInquiry.getE01DEARAC().trim() + ". El Principal ser&aacute; renovado por el mismo per&iacute;odo de tiempo ");
 				}		
 else if (cdInquiry.getE01DEAROC().equals("I")) {
 					out.print("Inter&eacute;s ser&aacute; pagado cada " +  cdInquiry.getE01DEAROY().trim() +  "  " + cdInquiry.getE01TEXTO1().trim() + "  a la cuenta contable n&uacute;mero"  +
 				    cdInquiry.getE01DEARAC().trim() + ", emitiendo un " +  cdInquiry.getE01TEXTO2().trim() + ". El Principal ser&aacute; renovado por el mismo per&iacute;odo de tiempo ");
 				}	
 else if (cdInquiry.getE01DEAROC().equals("J")) {
 					out.print("Inter&eacute;s y Principal ser&aacute;n pagados al Certificado de Dep&oacute;sito n&uacute;mero  " +  cdInquiry.getE01DEARAC().trim()
 					+ " .No hay renovaci&oacute;n en esta opci&oacute;n");  
 				}
 else if (cdInquiry.getE01DEAROC().equals("K")) {
 					out.print("Inter&eacute;s ser&aacute; pagado cada " +  cdInquiry.getE01DEAROY().trim() + "  " +  cdInquiry.getE01TEXTO1().trim()  +  "  a "  +
 				    cdInquiry.getE01TEXTO2().trim() + ",  que tendr&aacute; como n&uacute;mero " +  cdInquiry.getE01DEARAC().trim());  
 				}	
else if (cdInquiry.getE01DEAROC().equals("P")) out.print("El D&eacute;posito no tiene Instrucciones de Renovaci&oacute;n,  ser&aacute; renovado aut&oacute;maticamente despu&eacute;s del Per&iacute;odo  de Espera "); 		
else if (cdInquiry.getE01DEAROC().equals("S")) out.print("Inter&eacute;s y Principal ser&aacute;n pagados basados  en un Plan de Pagos definido previamente "); 		
					
%>			
</td>
          </tr>
        </table>
        
<%
if ( !pmnt.getNoResult() ) {
%>
        
        <table class="tableinfo" style="filter:''">
          <tr > 
            <td nowrap> 
              <table cellpadding=2 cellspacing=0 width="100%" border="0">
                <tr id="trdark"> 
                  <td nowrap> 
                    <div align="center">Cuota No. </div>
                  </td>
                  <td nowrap> 
                    <div align="center">Fecha</div>
                  </td>
                  <td nowrap> 
                    <div align="center">Principal</div>
                  </td>
                  <td nowrap> 
                    <div align="center">Intereses</div>
                  </td>
                </tr>
                <%
                pmnt.initRow();
                while (pmnt.getNextRow()) {
                    if (pmnt.getFlag().equals("")) {
                    		//out.println(coll.getRecord());
	      %> 
                <tr id="trclear"> 
                  <td nowrap> 
                    <div align="center"><%= pmnt.getRecord(0) %></div>
                  </td>
                  <td nowrap> 
                    <div align="center"><%= pmnt.getRecord(1) %></div>
                  </td>
                  <td nowrap> 
                    <div align="center"><%= pmnt.getRecord(2) %></div>
                  </td>
                  <td nowrap> 
                    <div align="center"><%= pmnt.getRecord(3) %></div>
                  </td>
                </tr>
                <%
                    }
                }
    %> 
              </table>
            </td>
          </tr>
        </table>
<%
}
%>
        
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="22%" height="29"> 
              <div align="right">Fecha Ultima Renovaci&oacute;n :</div>
            </td>
            <td nowrap width="11%" height="29"> 
              <input type="text" name="E01DEARD1" size="2" maxlength="2" value="<%= cdInquiry.getE01DEARD1().trim()%>" readonly>
              <input type="text" name="E01DEARD2" size="2" maxlength="2" value="<%= cdInquiry.getE01DEARD2().trim()%>" readonly>
              <input type="text" name="E01DEARD3" size="2" maxlength="2" value="<%= cdInquiry.getE01DEARD3().trim()%>" readonly>
            </td>
            <td nowrap width="18%" height="29"> 
              <div align="right">N&uacute;mero de Renovaciones :</div>
            </td>
            <td nowrap width="13%" height="29"> 
              <input type="text" name="E01DEARON" size="9" maxlength="9" value="<%= cdInquiry.getE01DEARON().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">Tipo de Renovaci&oacute;n :</div>
            </td>
            <td nowrap width="11%">
              <input type="text" name="E01DEAROC" size="2" maxlength="1" value="<%= cdInquiry.getE01DEAROC().trim()%>" readonly>
            </td>
            <td nowrap width="18%"> 
              <div align="right">Tasa de Renovaci&oacute;n :</div>
            </td>
            <td nowrap width="13%">
              <input type="text" name="E01DEAROR" size="11" maxlength="11" value="<%= cdInquiry.getE01DEAROR().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Intereses</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap > 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"> Interes Acumulado :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01DEAIAL" size="15" maxlength="15" value="<%= cdInquiry.getE01DEAIAL().trim()%>" readonly>
            </td>
            <td nowrap> 
              <div align="right">Inter&eacute;s Pagado A&ntilde;o :</div>
            </td>
            <td nowrap><INPUT type="text" name="E01DEAIPY" size="15" maxlength="15" value="<%= cdInquiry.getE01DEAIPY().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right"> Inter&eacute;s Pagado :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01DEAIPL" size="15" maxlength="15" value="<%= cdInquiry.getE01DEAIPL().trim()%>" readonly>
            </td>
            <td nowrap> 
              <div align="right">Inter&eacute;s Diario :</div>
            </td>
            <td nowrap>
              <INPUT type="text" name="E01DLYINT" size="15" maxlength="15" value="<%= cdInquiry.getE01DLYINT().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"> Inter&eacute;s Ajustado :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01DEAIJL" size="15" maxlength="15" value="<%= cdInquiry.getE01DEAIJL().trim()%>" readonly>
            </td>
            <td nowrap> 
              <div align="right">Inter&eacute;s de Ayer :</div>
            </td>
            <td nowrap>
              <INPUT type="text" name="E01YESINT" size="15" maxlength="15" value="<%= cdInquiry.getE01YESINT().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Tasas </h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap > 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark">
            <td nowrap> 
              <div align="right">Tasa Base / Spread Actual :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01NOWRTE" size="9" maxlength="9" value="<%= cdInquiry.getE01NOWRTE().trim()%>" readonly>
            </td> 
            <td nowrap> 
              <div align="right">Tasa Flotante Actual :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01DEAFRT" size="9" maxlength="9" value="<%= cdInquiry.getE01DEAFRT().trim()%>" readonly>
            </td>            
          </tr>
          <tr id="trclear">             
            <td nowrap> 
              <div align="right">Tasa Base / Spread Anterior :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01DEAPBR" size="9" maxlength="9" value="<%= cdInquiry.getE01DEAPBR().trim()%>" readonly>
            </td>
            <td nowrap> 
              <div align="right">Tasa Flotante Anterior :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01DEAPFR" size="9" maxlength="9" value="<%= cdInquiry.getE01DEAPFR().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap>
              <div align="right">Tasa Efectiva :</div>
            </td>
            <td nowrap>
              <INPUT type="text" name="E01DEASPR" size="9" maxlength="9" value="<%= cdInquiry.getE01DEASPR().trim()%>" readonly>
            </td>
            <td nowrap>
              <div align="right">Pr&oacute;xima Tasa Flotante :</div>
            </td>
            <td nowrap>
              <input type="text" name="E01DEANER" size="9" maxlength="9" value="<%= cdInquiry.getE01DEANER().trim()%>" readonly>
            </td>
          </tr>
         </table>
      </td>
    </tr>
  </table>
  <h4>Retenciones</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap height="33"> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="24%"> 
              <div align="right">Trimestral :</div>
            </td>
            <td nowrap width="26%"> 
              <input type="text" name="E01DEAWQT" size="15" maxlength="15" value="<%= cdInquiry.getE01DEAWQT().trim()%>" readonly>
            </td>
            <td nowrap width="18%"> 
              <div align="right">Anual :</div>
            </td>
            <td nowrap width="32%"> 
              <input type="text" name="E01DEAWYT" size="15" maxlength="15" value="<%= cdInquiry.getE01DEAWYT().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Fechas </h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="31%"> 
              <div align="right">Fecha Valor :</div>
            </td>
            <td nowrap width="13%"> 
              <input type="text" name="E01DEASD1" size="2" maxlength="2" value="<%= cdInquiry.getE01DEASD1().trim()%>" readonly>
              <input type="text" name="E01DEASD2" size="2" maxlength="2" value="<%= cdInquiry.getE01DEASD2().trim()%>" readonly>
              <input type="text" name="E01DEASD3" size="2" maxlength="2" value="<%= cdInquiry.getE01DEASD3().trim()%>" readonly>
            </td>
            <td nowrap width="31%"> 
              <div align="right">Fecha Ultimo C&aacute;lculo :</div>
            </td>
            <td nowrap width="25%"> 
              <input type="text" name="E01DEALC1" size="2" maxlength="2" value="<%= cdInquiry.getE01DEALC1().trim()%>" readonly>
              <input type="text" name="E01DEALC2" size="2" maxlength="2" value="<%= cdInquiry.getE01DEALC2().trim()%>" readonly>
              <input type="text" name="E01DEALC3" size="2" maxlength="2" value="<%= cdInquiry.getE01DEALC3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="31%"> 
              <div align="right">Fecha Ultimo Cambio de Tasa :</div>
            </td>
            <td nowrap width="13%">
              <input type="text" name="E01DEARC1" size="2" maxlength="2" value="<%= cdInquiry.getE01DEARC1().trim()%>" readonly>
              <input type="text" name="E01DEARC2" size="2" maxlength="2" value="<%= cdInquiry.getE01DEARC2().trim()%>" readonly>
              <input type="text" name="E01DEARC3" size="2" maxlength="2" value="<%= cdInquiry.getE01DEARC3().trim()%>" readonly>
            </td>
            <td nowrap width="31%">
              <div align="right"> Fecha Ultimo Pago Capital :</div>
            </td>
            <td nowrap width="25%">
              <input type="text" name="E01DEALP1" size="2" maxlength="2" value="<%= cdInquiry.getE01DEALP1().trim()%>" readonly>
              <input type="text" name="E01DEALP2" size="2" maxlength="2" value="<%= cdInquiry.getE01DEALP2().trim()%>" readonly>
              <input type="text" name="E01DEALP3" size="2" maxlength="2" value="<%= cdInquiry.getE01DEALP3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="31%"> 
              <div align="right">Fecha Pr&oacute;ximo Cambio de Tasa :</div>
            </td>
            <td nowrap width="13%">
              <input type="text" name="E01DEANR1" size="2" maxlength="2" value="<%= cdInquiry.getE01DEANR1().trim()%>" readonly>
              <input type="text" name="E01DEANR2" size="2" maxlength="2" value="<%= cdInquiry.getE01DEANR2().trim()%>" readonly>
              <input type="text" name="E01DEANR3" size="2" maxlength="2" value="<%= cdInquiry.getE01DEANR3().trim()%>" readonly>
            </td>
            <td nowrap width="31%">
              <div align="right">Fecha Ultimo Pago Intereses :</div>
            </td>
            <td nowrap width="25%">
              <input type="text" name="E01DEALI1" size="2" maxlength="2" value="<%= cdInquiry.getE01DEALI1().trim()%>" readonly>
              <input type="text" name="E01DEALI2" size="2" maxlength="2" value="<%= cdInquiry.getE01DEALI2().trim()%>" readonly>
              <input type="text" name="E01DEALI3" size="2" maxlength="2" value="<%= cdInquiry.getE01DEALI3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="31%"> 
              <div align="right">Fecha Ultima Modificaci&oacute;n :</div>
            </td>
            <td nowrap width="13%">
              <input type="text" name="E01DEALM1" size="2" maxlength="2" value="<%= cdInquiry.getE01DEALM1().trim()%>" readonly>
              <input type="text" name="E01DEALM2" size="2" maxlength="2" value="<%= cdInquiry.getE01DEALM2().trim()%>" readonly>
              <input type="text" name="E01DEALM3" size="2" maxlength="2" value="<%= cdInquiry.getE01DEALM3().trim()%>" readonly>
            </td>
            <td nowrap width="31%"> 
              <div align="right">Usuario Ultima Modificaci&oacute;n :</div>
            </td>
            <td nowrap width="25%">
              <input type="text" name="E01DEAUSR" size="15" maxlength="10" value="<%= cdInquiry.getE01DEAUSR().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>
