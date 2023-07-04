<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Informacion Basica</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
builtNewMenu(cr_i_opt);
initMenu();
</SCRIPT>


<jsp:useBean id= "custinf" class= "datapro.eibs.beans.ECIF20001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

</head>

<body bgcolor="#FFFFFF">

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<h3 align="center">An&aacute;lisis de Riesgo<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="custinf_personal_basic, ESD0080"  ></h3>
<hr size="4">
 <FORM>
  
  <table class="tableinfo" width="102%">
    <tr > 
      <td nowrap > 
        <table cellspacing="0" cellpadding="2" width="100%" class="tbhead"   align="center">
          <tr> 
            <td nowrap width="9%" align="right"> Cliente : </td>
            <td nowrap width="15%" align="left"> <%= userPO.getCusNum()%> </td>
            <td nowrap width="11%" align="right"> 
              <div align="right">RUT : </div>
            </td>
            <td nowrap width="10%" align="left"> <%= userPO.getID()%> </td>
            <td nowrap width="14%" align="right"> 
              <div align="right">Nombre: </div>
            </td>
            <td nowrap width="41%"align="left"> <%= userPO.getCusName()%> </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <input type="hidden" readonly name="CUSTOMER" value="<%= userPO.getCusNum()%>">
  <H4>Informaci&oacute;n B&aacute;sica</H4>
<div align="left">
      
    <table class="tableinfo" >
      <tr > 
          <td nowrap > 
            <div align="center"> 
              
            <table cellspacing="0" cellpadding="2" width="100%" border="0">
              <tr  id="trdark"> 
                <td nowrap width="32%"> 
                  <div align="right">Sucursal :</div>
                </td>
                <td nowrap> 
                  <input type="text" readonly name="E01SUCNME" size="35" maxlength="15" value="<%= custinf.getE01SUCNME().trim()%>">
                </td>
                <td nowrap> 
                  <div align="right">Ejecutivo :</div>
                </td>
                <td nowrap> 
                  <div align="left"> 
                    <input type="text" readonly name="E01OFICOD" size="4" maxlength="4" value="<%= custinf.getE01OFICOD().trim()%>">
                    <input type="text" readonly name="E01OFINME" size="35" maxlength="35" value="<%= custinf.getE01OFINME().trim()%>">
                  </div>
                </td>
              </tr>
              <tr  id="trclear"> 
                <td nowrap width="32%"> 
                  <div align="right">Edad :</div>
                </td>
                <td nowrap> 
                  <div align="left">
                    <input type="text" readonly name="E01EDADNO" size="3" maxlength="3" value="<%= custinf.getE01EDADNO().trim()%>">
                    (A&ntilde;os) </div>
                </td>
                <td nowrap> 
                  <div align="right">Tipo Actividad :</div>
                </td>
                <td nowrap> 
                  <div align="left">
                    <input type="text" readonly name="E01ACTCOD" size="4" maxlength="3" value="<%= custinf.getE01ACTCOD().trim()%>">
                    <input type="text" readonly name="E01ACTNME" size="35" maxlength="35" value="<%= custinf.getE01ACTNME().trim()%>">
                  </div>
                </td>
              </tr>
              <tr  id="trdark"> 
                <td nowrap width="32%"> 
                  <div align="right">Declaraci&oacute;n Jurada :</div>
                </td>
                <td nowrap> 
                  <div align="left">
                    <input type="text" readonly name="E01DLRJUR" size="4" maxlength="3" 
				
			    value="<% if (custinf.getE01DLRJUR().equals("Y")) { out.print("YES"); } 
						else { out.print("NO"); } %>" >
                  </div>
                </td>
                <td nowrap> 
                  <div align="right">Relacionado Banco :</div>
                </td>
                <td nowrap> 
                  <div align="left"> 
                    <input type="text" readonly name="E01RELBNS" size="4" maxlength="3" 
				
			    value="<% if (custinf.getE01RELBNS().equals("Y")) { out.print("YES"); } 
						else { out.print("NO"); } %>" >
                  </div>
                </td>
              </tr>
              <tr  id="trclear"> 
                <td nowrap width="32%"> 
                  <div align="right">Clasificaci&oacute;n Banco :</div>
                </td>
                <td nowrap> 
                  <div align="left"> 
                    <input type="text" readonly name="E01CLSBNS" size="4" maxlength="3" 
				
			    value="<% if (custinf.getE01CLSBNS().equals("Y")) { out.print("YES"); } 
						else { out.print("NO"); } %>" >
                  </div>
                </td>
                <td nowrap> 
                  <div align="right">Bolet&iacute;n Laboral :</div>
                </td>
                <td nowrap> 
                  <div align="left"> 
                    <input type="text" readonly name="E01BOLLAB" size="4" maxlength="3" 
				
			    value="<% if (custinf.getE01BOLLAB().equals("Y")) { out.print("YES"); } 
						else { out.print("NO"); } %>" >
                  </div>
                </td>
              </tr>
              <tr  id="trdark"> 
                <td nowrap width="32%"> 
                  <div align="right">Imp. Cta. Cte. SBIF :</div>
                </td>
                <td nowrap> 
                  <div align="left"> 
                    <input type="text" readonly name="E01CTSSIF" size="4" maxlength="3" 
				
			    value="<% if (custinf.getE01CTSSIF().equals("Y")) { out.print("YES"); } 
						else { out.print("NO"); } %>" >
                  </div>
                </td>
                <td nowrap> 
                  <div align="right">Imp. Cta. Cte. Banco :</div>
                </td>
                <td nowrap> 
                  <div align="left"> 
                    <input type="text" readonly name="E01CTSBNS" size="4" maxlength="3" 
				
			    value="<% if (custinf.getE01CTSBNS().equals("Y")) { out.print("YES"); } 
						else { out.print("NO"); } %>" >
                  </div>
                </td>
              </tr>
            </table>
            
          </div>
          </td>
        </tr>
      </table>
    
    <h4>Estado de Situaci&oacute;n al <%= Util.formatDate(currUser.getE01RDD(),currUser.getE01RDM(),currUser.getE01RDY())%> 
      (en <%= currUser.getE01BCU()%>) </h4>
  </div>
    
  <table class="tableinfo" >
    <tr > 
        <td nowrap >
          
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap colspan="2"><b>Activos</b></td>
            <td nowrap colspan="2"><b>Pasivos</b></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Circulantes :</div>
            </td>
            <td nowrap width="25%"> 
              <div align="left"> 
                <input type="text" readonly name="E01ACTCIR" size="17" maxlength="15" value="<%= custinf.getE01ACTCIR().trim()%>">
              </div>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Circulantes :</div>
            </td>
            <td nowrap width="25%"> 
              <div align="left"> 
                <input type="text" readonly name="E01PASCIR" size="17" maxlength="15" value="<%= custinf.getE01PASCIR().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Fijos :</div>
            </td>
            <td nowrap width="25%"> 
              <div align="left"> 
                <input type="text" readonly name="E01ACTFIJ" size="17" maxlength="15" value="<%= custinf.getE01ACTFIJ().trim()%>">
              </div>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Largo Plazo :</div>
            </td>
            <td nowrap width="25%"> 
              <div align="left"> 
                <input type="text" readonly name="E01PASFIJ" size="17" maxlength="15" value="<%= custinf.getE01PASFIJ().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Otros :</div>
            </td>
            <td nowrap width="25%"> 
              <div align="left"> 
                <input type="text" readonly name="E01ACTOTH" size="17" maxlength="15" value="<%= custinf.getE01ACTOTH().trim()%>">
              </div>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Patrimonio :</div>
            </td>
            <td nowrap width="25%"> 
              <div align="left"> 
                <input type="text" readonly name="E01PASOTH" size="17" maxlength="15" value="<%= custinf.getE01PASOTH().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="4">&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right"><b>Total Activos :</b></div>
            </td>
            <td nowrap width="25%"> 
              <div align="left">
                <input type="text" readonly name="E01ACTTOT" size="17" maxlength="15" value="<%= custinf.getE01ACTTOT().trim()%>">
              </div>
            </td>
            <td nowrap width="25%"> 
              <div align="right"><b>Pasivos + Patrimonio :</b></div>
            </td>
            <td nowrap width="25%"> 
              <div align="left"> 
                <input type="text" readonly name="E01PASTOT" size="17" maxlength="15" value="<%= custinf.getE01PASTOT().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%">&nbsp;</td>
            <td nowrap width="25%">&nbsp;</td>
            <td nowrap width="25%">&nbsp;</td>
            <td nowrap width="25%">&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Renta Mensual :</div>
            </td>
            <td nowrap width="25%"> 
              <div align="left">
                <input type="text" readonly name="E01RENMES" size="17" maxlength="15" value="<%= custinf.getE01RENMES().trim()%>">
              </div>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Cuota Comprom.:</div>
            </td>
            <td nowrap width="25%"> 
              <div align="left">
                <input type="text" readonly name="E01CUOCOM" size="17" maxlength="15" value="<%= custinf.getE01CUOCOM().trim()%>">
              </div>
            </td>
          </tr>
        </table>
        </td>
      </tr>
    </table>
    
  <h4>Garant&iacute;as</h4>
    
  <table class="tableinfo">
    <tr > 
      <td nowrap > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="center">
          <tr id="trdark"> 
            <td nowrap width="35%"> 
              <div align="right">Hipotecaria :</div>
            </td>
            <td nowrap width="65%"> 
              <div align="left"> 
                <input type="text" readonly name="E01GARHIP" size="17" maxlength="15" value="<%= custinf.getE01GARHIP().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="35%"> 
              <div align="right">Prendaria :</div>
            </td>
            <td nowrap width="65%"> 
              <div align="left"> 
                <input type="text" readonly name="E01GARPRE" size="17" maxlength="15" value="<%= custinf.getE01GARPRE().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="35%"> 
              <div align="right">Fianzas :</div>
            </td>
            <td nowrap width="65%"> 
              <div align="left"> 
                <input type="text" readonly name="E01GARFIA" size="17" maxlength="15" value="<%= custinf.getE01GARFIA().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="35%"> 
              <div align="right">Warrants :</div>
            </td>
            <td nowrap width="65%"> 
              <div align="left"> 
                <input type="text" readonly name="E01GARWAR" size="17" maxlength="15" value="<%= custinf.getE01GARWAR().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="35%"> 
              <div align="right">Valores Financieros :</div>
            </td>
            <td nowrap width="65%"> 
              <div align="left"> 
                <input type="text" readonly name="E01GARVFI" size="17" maxlength="15" value="<%= custinf.getE01GARVFI().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="35%"> 
              <div align="right">Acciones :</div>
            </td>
            <td nowrap width="65%"> 
              <div align="left"> 
                <input type="text" readonly name="E01GARACC" size="17" maxlength="15" value="<%= custinf.getE01GARACC().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="35%"> 
              <div align="right">Letras :</div>
            </td>
            <td nowrap width="65%"> 
              <div align="left"> 
                <input type="text" readonly name="E01GARLET" size="17" maxlength="15" value="<%= custinf.getE01GARLET().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap  width="35%"> 
              <div align="right">Otros :</div>
            </td>
            <td nowrap  width="65%"> 
              <div align="left"> 
                <input type="text" readonly name="E01GAROTH" size="17" maxlength="15" value="<%= custinf.getE01GAROTH().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap  width="35%"> 
              <div align="right"></div>
            </td>
            <td nowrap  width="65%"> 
              <div align="left"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap  width="35%"> 
              <div align="right">Total :</div>
            </td>
            <td nowrap  width="65%"> 
              <div align="left"> 
                <input type="text" readonly name="E01GARTOT" size="17" maxlength="15" value="<%= userPO.getHeader1()%>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Deudas</h4>
    
  <table class="tableinfo" >
    <tr > 
        <td nowrap >
          
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap colspan="5"> 
              <div align="left"><a href="javascript:showDetailsDebit(document.forms[0].CUSTOMER.value,'D','')">
				<b>Deuda Directa</b>
				 </a></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%">&nbsp;</td>
            <td nowrap colspan="2" bgcolor="#FFFFFF"> 
              <div align="center"><b>Banco</b></div>
            </td>
            <td nowrap colspan="2"> 
              <div align="center"><b>SBIF</b></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%"> 
              <div align="center"></div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"><i>Moneda <br>
                Nacional</i></div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"><i>Moneda <br>
                Extranjera</i></div>
            </td>
            <td nowrap width="20%"> 
              <div align="center"><i>Moneda <br>
                Nacional</i></div>
            </td>
            <td nowrap width="20%"> 
              <div align="center"><i>Moneda <br>
                Extranjera</i></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%"> 
              <div align="right"><a href="javascript:showDetailsDebit(document.forms[0].CUSTOMER.value,'D','1')">Vigente :</a></div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"> 
                <input type="text" readonly name="E01BDBVIG" size="17" maxlength="15" value="<%= custinf.getE01BDBVIG().trim()%>">
              </div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"> 
                <input type="text" readonly name="E01BDEVIG" size="17" maxlength="15" value="<%= custinf.getE01BDEVIG().trim()%>">
              </div>
            </td>
            <td nowrap width="20%"> 
              <div align="center"> 
                <input type="text" readonly name="E01SDBVIG" size="17" maxlength="15" value="<%= custinf.getE01SDBVIG().trim()%>">
              </div>
            </td>
            <td nowrap width="20%"> 
              <input type="text" readonly name="E01SDEVIG" size="17" maxlength="15" value="<%= custinf.getE01SDEVIG().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%"> 
              <div align="right"><a href="javascript:showDetailsDebit(document.forms[0].CUSTOMER.value,'D','2')">Vencida :</a></div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"> 
                <input type="text" readonly name="E01BDBVEN" size="17" maxlength="15" value="<%= custinf.getE01BDBVEN().trim()%>">
              </div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"> 
                <input type="text" readonly name="E01BDEVEN" size="17" maxlength="15" value="<%= custinf.getE01BDEVEN().trim()%>">
              </div>
            </td>
            <td nowrap width="20%"> 
              <div align="center"> 
                <input type="text" readonly name="E01SDBVEN" size="17" maxlength="15" value="<%= custinf.getE01SDBVEN().trim()%>">
              </div>
            </td>
            <td nowrap width="20%"> 
              <input type="text" readonly name="E01SDEVEN" size="17" maxlength="15" value="<%= custinf.getE01SDEVEN().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%"> 
              <div align="right"><a href="javascript:showDetailsDebit(document.forms[0].CUSTOMER.value,'D','5')">Morosa :</a></div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"> 
                <input type="text" readonly name="E01BDBMOR" size="17" maxlength="15" value="<%= custinf.getE01BDBMOR().trim()%>">
              </div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"> 
                <input type="text" readonly name="E01BDEMOR" size="17" maxlength="15" value="<%= custinf.getE01BDEMOR().trim()%>">
              </div>
            </td>
            <td nowrap width="20%"> 
              <div align="center"> 
                <input type="text" readonly name="E01SDBMOR" size="17" maxlength="15" value="<%= custinf.getE01SDBMOR().trim()%>">
              </div>
            </td>
            <td nowrap width="20%"> 
              <input type="text" readonly name="E01SDEMOR" size="17" maxlength="15" value="<%= custinf.getE01SDEMOR().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%"> 
              <div align="right"><a href="javascript:showDetailsDebit(document.forms[0].CUSTOMER.value,'D','3')">Castigada :</a></div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"> 
                <input type="text" readonly name="E01BDBCAS" size="17" maxlength="15" value="<%= custinf.getE01BDBCAS().trim()%>">
              </div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"> 
                <input type="text" readonly name="E01BDECAS" size="17" maxlength="15" value="<%= custinf.getE01BDECAS().trim()%>">
              </div>
            </td>
            <td nowrap width="20%"> 
              <div align="center"> 
                <input type="text" readonly name="E01SDBCAS" size="17" maxlength="15" value="<%= custinf.getE01SDBCAS().trim()%>">
              </div>
            </td>
            <td nowrap width="20%"> 
              <input type="text" readonly name="E01SDECAS" size="17" maxlength="15" value="<%= custinf.getE01SDECAS().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%"> 
              <div align="right"><a href="javascript:showDetailsDebit(document.forms[0].CUSTOMER.value,'D','4')">Castigado no Informado :</a></div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"> 
                <input type="text" readonly name="E01BDBCAN" size="17" maxlength="15" value="<%= custinf.getE01BDBCAN().trim()%>">
              </div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"> 
                <input type="text" readonly name="E01BDECAN" size="17" maxlength="15" value="<%= custinf.getE01BDECAN().trim()%>">
              </div>
            </td>
            <td nowrap width="20%"> 
              <div align="center"> 
                <input type="text" readonly name="E01SDBCAN" size="17" maxlength="15" value="<%= custinf.getE01SDBCAN().trim()%>">
              </div>
            </td>
            <td nowrap width="20%"> 
              <input type="text" readonly name="E01SDECAN" size="17" maxlength="15" value="<%= custinf.getE01SDECAN().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%"> 
              <div align="right"><i><b>Total Deuda Directa :</b></i></div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"> 
                <input type="text" readonly name="E01BDBTOT" size="17" maxlength="15" value="<%= userPO.getHeader2()%>">
              </div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"> 
                <input type="text" readonly name="E01BDETOT" size="17" maxlength="15" value="<%= userPO.getHeader5()%>">
              </div>
            </td>
            <td nowrap width="20%"> 
              <div align="center"> 
                <input type="text" readonly name="E01SDBTOT" size="17" maxlength="15" value="<%= userPO.getHeader8()%>">
              </div>
            </td>
            <td nowrap width="20%"> 
              <input type="text" readonly name="E01SDETOT" size="17" maxlength="15" value="<%= userPO.getHeader11()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="5"> 
              <div align="left"><a href="javascript:showDetailsDebit(document.forms[0].CUSTOMER.value,'I','')"><b>Deuda Indirecta</b></a></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%"> 
              <div align="right"><a href="javascript:showDetailsDebit(document.forms[0].CUSTOMER.value,'I','1')">Vigente :</a></div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"> 
                <input type="text" readonly name="E01BIBVIG" size="17" maxlength="15" value="<%= custinf.getE01BIBVIG().trim()%>">
              </div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"> 
                <input type="text" readonly name="E01BIEVIG" size="17" maxlength="15" value="<%= custinf.getE01BIEVIG().trim()%>">
              </div>
            </td>
            <td nowrap width="20%"> 
              <div align="center"> 
                <input type="text" readonly name="E01SIBVIG" size="17" maxlength="15" value="<%= custinf.getE01SIBVIG().trim()%>">
              </div>
            </td>
            <td nowrap width="20%"> 
              <input type="text" readonly name="E01SIEVIG" size="17" maxlength="15" value="<%= custinf.getE01SIEVIG().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%"> 
              <div align="right"><a href="javascript:showDetailsDebit(document.forms[0].CUSTOMER.value,'I','2')">Vencida :</a></div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"> 
                <input type="text" readonly name="E01BIBVEN" size="17" maxlength="15" value="<%= custinf.getE01BIBVEN().trim()%>">
              </div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"> 
                <input type="text" readonly name="E01BIEVEN" size="17" maxlength="15" value="<%= custinf.getE01BIEVEN().trim()%>">
              </div>
            </td>
            <td nowrap width="20%"> 
              <div align="center"> 
                <input type="text" readonly name="E01SIBVEN" size="17" maxlength="15" value="<%= custinf.getE01SIBVEN().trim()%>">
              </div>
            </td>
            <td nowrap width="20%"> 
              <input type="text" readonly name="E01SIEVEN" size="17" maxlength="15" value="<%= custinf.getE01SIEVEN().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%"> 
              <div align="right"><a href="javascript:showDetailsDebit(document.forms[0].CUSTOMER.value,'I','5')">Morosa :</a></div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"> 
                <input type="text" readonly name="E01BIBMOR" size="17" maxlength="15" value="<%= custinf.getE01BIBMOR().trim()%>">
              </div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"> 
                <input type="text" readonly name="E01BIEMOR" size="17" maxlength="15" value="<%= custinf.getE01BIEMOR().trim()%>">
              </div>
            </td>
            <td nowrap width="20%"> 
              <div align="center"> 
                <input type="text" readonly name="E01SIBMOR" size="17" maxlength="15" value="<%= custinf.getE01SIBMOR().trim()%>">
              </div>
            </td>
            <td nowrap width="20%"> 
              <input type="text" readonly name="E01SIEMOR" size="17" maxlength="15" value="<%= custinf.getE01SIEMOR().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%"> 
              <div align="right"><a href="javascript:showDetailsDebit(document.forms[0].CUSTOMER.value,'I','3')">Castigada :</a></div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"> 
                <input type="text" readonly name="E01BIBCAS" size="17" maxlength="15" value="<%= custinf.getE01BIBCAS().trim()%>">
              </div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"> 
                <input type="text" readonly name="E01BIECAS" size="17" maxlength="15" value="<%= custinf.getE01BIECAS().trim()%>">
              </div>
            </td>
            <td nowrap width="20%"> 
              <div align="center"> 
                <input type="text" readonly name="E01SIBCAS" size="17" maxlength="15" value="<%= custinf.getE01SIBCAS().trim()%>">
              </div>
            </td>
            <td nowrap width="20%"> 
              <input type="text" readonly name="E01SIECAS" size="17" maxlength="15" value="<%= custinf.getE01SIECAS().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%"> 
              <div align="right"><a href="javascript:showDetailsDebit(document.forms[0].CUSTOMER.value,'I','4')">Castigado no Informado :</a></div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"> 
                <input type="text" readonly name="E01BIBCAN" size="17" maxlength="15" value="<%= custinf.getE01BIBCAN().trim()%>">
              </div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"> 
                <input type="text" readonly name="E01BIECAN" size="17" maxlength="15" value="<%= custinf.getE01BIECAN().trim()%>">
              </div>
            </td>
            <td nowrap width="20%"> 
              <div align="center"> 
                <input type="text" readonly name="E01SIBCAN" size="17" maxlength="15" value="<%= custinf.getE01SIBCAN().trim()%>">
              </div>
            </td>
            <td nowrap width="20%"> 
              <input type="text" readonly name="E01SIECAN" size="17" maxlength="15" value="<%= custinf.getE01SIECAN().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%"> 
              <div align="right"><i><b>Total Deuda Indirecta :</b></i></div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"> 
                <input type="text" readonly name="E01BIBTOT" size="17" maxlength="15" value="<%= userPO.getHeader3()%>">
              </div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"> 
                <input type="text" readonly name="E01BIETOT" size="17" maxlength="15" value="<%= userPO.getHeader6()%>">
              </div>
            </td>
            <td nowrap width="20%"> 
              <div align="center"> 
                <input type="text" readonly name="E01SIBTOT" size="17" maxlength="15" value="<%= userPO.getHeader9()%>">
              </div>
            </td>
            <td nowrap width="20%"> 
              <input type="text" readonly name="E01SIETOT" size="17" maxlength="15" value="<%= userPO.getHeader12()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="5">&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%"> 
              <div align="right"><b>Total Deudas :</b></div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"> 
                <input type="text" readonly name="E01BBTOT" size="17" maxlength="15" value="<%= userPO.getHeader4()%>">
              </div>
            </td>
            <td nowrap width="20%" bgcolor="#FFFFFF"> 
              <div align="center"> 
                <input type="text" readonly name="E01BITOT" size="17" maxlength="15" value="<%= userPO.getHeader7()%>">
              </div>
            </td>
            <td nowrap width="20%"> 
              <div align="center"> 
                <input type="text" readonly name="E01SIITOT" size="17" maxlength="15" value="<%= userPO.getHeader10()%>">
              </div>
            </td>
            <td nowrap width="20%"> 
              <input type="text" readonly name="E01SEETOT" size="17" maxlength="15" value="<%= userPO.getHeader13()%>">
            </td>
          </tr>
        </table>
        </td>
      </tr>
    </table>
</form>
</body>
</html>

