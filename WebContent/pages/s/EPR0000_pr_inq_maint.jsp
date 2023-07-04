<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Transacciones </title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.*" %>

<jsp:useBean id="prMant" class="datapro.eibs.beans.EPR010001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
  
function getLogs(){
	var refnum;
	refnum = document.forms[0].E01PRINUM.value;
	self.window.location.href = "<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPR1060?SCREEN=7&REFNUM="+refnum;
	//page = webapp + "/servlet/datapro.eibs.products.JSEPR1060?SCREEN=7&REFNUM="+refnum;
 	//CenterWindow(page,420,300,2);
}
  
  function showTab(index, name){  
  	for(var i=0;i<4;i++){
   		document.all["Tab"+i].className="tabnormal";
   		document.all["dataTab"+i].style.display="none";
   	}
  
    document.all["Tab"+index].className="tabhighlight";  
  	document.all["dataTab"+index].style.display="";
  	document.all[name].focus();
  }
     
  function showTabB(index,name){  
  	for(var i=0;i<4;i++){
   		document.all["TabB"+i].className="tabnormal";
   		document.all["dataTabB"+i].style.display="none";
   	}
  
    document.all["TabB"+index].className="tabhighlight";  
  	document.all["dataTabB"+index].style.display="";
  	document.all[name].focus();
  }
 
</SCRIPT>
<SCRIPT Language="Javascript">
    builtNewMenu(pr_inq_opt);
 </SCRIPT>
</head>

<body>

<%String lectura =" ";
  	String lectura2=" "; 
  	boolean mostrar=true;
%>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
 out.println("<SCRIPT> initMenu();  </SCRIPT>");
%> 

<h3 align="center">Transacciones Financieras<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="pr_inq_maint.jsp,EPR0000"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPR0000" >
    <input type=HIDDEN name="REFNUM"  value="<%= prMant.getE01PRINUM().trim()%>">
<% 
  if (userPO.getPurpose().equals("NEW")) { 
%> 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<% } else{ %>
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
<% } %>

  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right">Tipo :</div>
            </td>
            <td nowrap width="20%" >
			  <div align="left"> 
                <input type="hidden" name="E01PRITTP" size="2" maxlength="2" value="<%= prMant.getE01PRITTP().trim()%>">
                  
                <input type="text" readonly  name="TypeDesc" size="35" maxlength="35" value="<% if(prMant.getE01PRITTP().trim().equals("IW")){out.print("Transferencias Externas Recibidas");}
				   else if(prMant.getE01PRITTP().trim().equals("OW")){out.print("Transferencias Externas Enviadas"); }
				   else if(prMant.getE01PRITTP().trim().equals("IT")){out.print("Transferencias Internas"); }
				   else if(prMant.getE01PRITTP().trim().equals("IA")){out.print("Ajuste de Intereses"); }
                   else if(prMant.getE01PRITTP().trim().equals("IP")){out.print("Pago de Intereses"); }
                   else if(prMant.getE01PRITTP().trim().equals("RP")){out.print("Reversion de Penalidad"); }
                   else if(prMant.getE01PRITTP().trim().equals("LP")){out.print("Pago de Prestamos"); }
                   else if(prMant.getE01PRITTP().trim().equals("MR")){out.print("Reversion Miscelanea"); }
                   else if(prMant.getE01PRITTP().trim().equals("MP")){out.print("Ajuste Miscelaneo"); }
				   else {out.print(" ");}%>" >
              </div>
            </td>
            <td nowrap  >
            </td>
            <td nowrap >
            </td>
          </tr>
          
        </table>
      </td>
    </tr>
  </table>
<BR>
<table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <TD nowrap> 
                  <DIV align="right">Código de Transacci&oacute;n :</DIV>
            </TD>
            <TD nowrap align="left">
                  <input type="text" name="E01PRITCD" size="11" maxlength="10" value="<%= prMant.getE01PRITCD().trim()%>" readonly>
            </TD>
                
                <td nowrap align="right"> N&uacute;mero de Referencia : </td>
                <td nowrap align="left">
                  <input type="text" name="E01PRINUM" size="11" maxlength="10" value="<%= prMant.getE01PRINUM().trim()%>" readonly>
                </td>
          </tr>
          
        </table>
      </td>
    </tr>
   </table>
   <BR>
<table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap VALIGN="TOP"> 
              <div align="right">Cliente Ordenante :</div>
            </td>
            <td nowrap VALIGN="TOP"> 
              <div align="left"> 

                              	<SELECT name="E01PRIORO"  
					  				<OPTION value=" " <% if (!(prMant.getE01PRIORO().equals("A") ||prMant.getE01PRIORO().equals("F") || prMant.getE01PRIORO().equals("K"))) out.print("selected"); %>></OPTION>
									<OPTION value="A" <% if(prMant.getE01PRIORO().equals("A")) out.print("selected");%>>A</OPTION>
									<OPTION value="F" <% if(prMant.getE01PRIORO().equals("F")) out.print("selected");%>>F</OPTION>
									<OPTION value="K" <% if(prMant.getE01PRIORO().equals("K")) out.print("selected");%>>K</OPTION>
								</SELECT>
     

                <input type="text" readonly  name="E01PRIOCU" size="10" maxlength="10" value="<%= prMant.getE01PRIOCU().trim()%>" >
              </div>
            </td>
            <td nowrap VALIGN="TOP"> 
              <div align="right">Nombre :<BR>
                Direcci&oacute;n :</div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" readonly  name="E11PRIORC" size="36" maxlength="35" value="<%= prMant.getE11PRIORC().trim()%>">
                <br>
                <input type="text" readonly  name="E21PRIORC" size="36" maxlength="35" value="<%= prMant.getE21PRIORC().trim()%>">
                <br>
                <input type="text" readonly  name="E31PRIORC" size="36" maxlength="35" value="<%= prMant.getE31PRIORC().trim()%>">
                <br>
                <input type="text" readonly  name="E41PRIORC" size="36" maxlength="35" value="<%= prMant.getE41PRIORC().trim()%>">
             <br>
                <input type="text" readonly  name="E51PRIORC" size="36" maxlength="35" value="<%= prMant.getE51PRIORC().trim()%>">
                 </div>
            </td>
          </tr>
         </table>
      </td>
    </tr>
  </table>
<BR>
<table class="tableinfo">
    
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Moneda /Monto a Transferir :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E01PRITCY" size="3" maxlength="3" value="<%= prMant.getE01PRITCY().trim()%>" >
              <input type="text" readonly  name="E01PRIAMT" size="15" maxlength="13" value="<%= prMant.getE01PRIAMT().trim()%>" >
            </td>
            <td nowrap >Tasa de Cambio :</td>
            <td nowrap >
              <input type="text" readonly  name="E01PRICXR" size="13" maxlength="13" value="<%= prMant.getE01PRICXR().trim()%>" onKeyPress="enterDecimal()" >
            </td>
          </tr>
        </table>
      </td>
    </tr>
  
</table>
  <h4>Cuenta de D&eacute;bito</h4>
  <TABLE class="tableinfo" >
    <tr id="trdark"> 
      <td nowrap align="center" >Banco</td>
      <td nowrap align="center" >Agencia</td>
      <td nowrap align="center" >Moneda</td>
      <td nowrap align="center" >Cuenta Contable</td>
      <td nowrap align="center" >Cuenta</td>
      <td nowrap align="center" >Centro de Costo</td>
      <td nowrap align="center" >Monto</td>

    </tr>
    <tr id="trclear"> 
      <td nowrap > 
        <div align="center"> 
          <input type="text" readonly  name="E01PRIDBK" size="2" maxlength="2" value="<%= prMant.getE01PRIDBK().trim()%>" >
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" readonly  name="E01PRIDBR" size="4" maxlength="4"  value="<%= prMant.getE01PRIDBR().trim()%>" >
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" readonly  name="E01PRIDCY" size="3" maxlength="3" value="<%= prMant.getE01PRIDCY().trim()%>" >
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" readonly  name="E01PRIDGL" size="17" maxlength="16" value="<%= prMant.getE01PRIDGL().trim()%>" >
        </div>
      </td>
      <td nowrap  > 
        <div align="center"> 
          <input type="text" readonly  name="E01PRIDAC" size="13" maxlength="12" value="<%= prMant.getE01PRIDAC().trim()%>" >
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" readonly  name="E01PRIDCC" size="9" maxlength="9"  value="<%= prMant.getE01PRIDCC().trim()%>" >
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" readonly  name="E01PRIDAM" size="15" maxlength="13" value="<%= prMant.getE01PRIDAM().trim()%>">
        </div>
      </td>
    </tr>
    <tr id="trdark"> 
		<td nowrap colspan="3"> 
      	</td>



    </tr>

  </table>
  <h4>Cuenta de Cr&eacute;dito</h4>
  <table class="tableinfo" >
    <tr id="trdark"> 
      <td nowrap align="center" >Banco </td>
      <td nowrap align="center" >Agencia</td>
      <td nowrap align="center" >Moneda</td>
      <td nowrap align="center" >Cuenta Contable</td>
      <td nowrap align="center" >Cuenta</td>
      <td nowrap align="center" >Centro de Costo</td>
      <td nowrap align="center" >Monto</td>
    </tr>
    <tr id="trclear"> 
      <td nowrap > 
        <div align="center"> 
          <input type="text" readonly  name="E01PRICBK" size="2" maxlength="2" value="<%= prMant.getE01PRICBK().trim()%>" >
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" readonly  name="E01PRICBR" size="4" maxlength="4"  value="<%= prMant.getE01PRICBR().trim()%>" >
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" readonly  name="E01PRICCY" size="3" maxlength="3" value="<%= prMant.getE01PRICCY().trim()%>" >
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" readonly  name="E01PRICGL" size="17" maxlength="16" value="<%= prMant.getE01PRICGL().trim()%>" >
        </div>
      </td>
      <td nowrap  > 
        <div align="center"> 
          <input type="text" readonly  name="E01PRICAC" size="13" maxlength="12" value="<%= prMant.getE01PRICAC().trim()%>" >
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" readonly  name="E01PRICCC" size="9" maxlength="9"  value="<%= prMant.getE01PRICCC().trim()%>" >
        </div>
      </td>
      <td nowrap >
        <div align="center">
          <input type="text" readonly  name="E01PRICAM" size="15" maxlength="13" value="<%= prMant.getE01PRICAM().trim()%>">
        </div>
      </td>
    </tr>
    <tr id="trdark"> 
		<td nowrap colspan="3">	</td>





		<td nowrap align="right">D&iacute;as de Retenci&oacute;n : </td>
		<td nowrap align="left"> 
            <input type="text" name="E01PRIHDY" size="3" maxlength="3" value="<%= prMant.getE01PRIHDY().trim()%>" readonly">
      	</td>
    </tr>
  </table>

  <h4>Nuestras Comisiones</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="24%" > 
              <div align="right">Tabla / Monto :</div>
            </td>
            <td nowrap width="30%" > 
              <input type="text" readonly  name="E01PRICOT" size="2" maxlength="2" value="<%= prMant.getE01PRICOT().trim()%>" >
              <input type="text" readonly  name="E01PRICOM" size="15" maxlength="13" value="<%= prMant.getE01PRICOM().trim()%>" >
            </td>
            <td nowrap width="17%" > 
              <div align="right">Cargos por :</div>
            </td>
            <td nowrap width="29%" > 
			  <input type="text" readonly  name="E01PRICHG" value="<% if (prMant.getE01PRICHG().equals("A")) {out.print("Aplicante");} 
                else if (prMant.getE01PRICHG().equals("B")) { out.print("Beneficiario");} 
                else if (prMant.getE01PRICHG().equals("N")) {out.print("Ninguno");} 
                else if (prMant.getE01PRICHG().equals("O")) {out.print("Nuestro");}
				else out.print(" ");%>" >
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  <H4>Comisiones Banco Receptor</H4>
    <table class="tableinfo">
      <tr > 
        <td nowrap> 
          <table cellspacing=0 cellpadding=2 width="100%" border="0">
            <tr id="trdark"> 
              <td nowrap width="24%" > 
                <div align="right">Moneda / Monto :</div>
              </td>
              <td nowrap width="30%" > 
                <input type="text" readonly name="E01PRIRCC" size="2" maxlength="2" value="<%= prMant.getE01PRIRCC().trim()%>" >
                <input type="text" readonly name="E01PRIRRA" size="15" maxlength="13" value="<%= prMant.getE01PRIRRA().trim()%>" >
              </td>
              <td nowrap width="17%" > 
                <div align="right">Cargos por :</div>
              </td>
              <td nowrap width="29%" > 
			  <input type="text" readonly  name="E01PRIFL4" value="<% if (prMant.getE01PRIFL4().equals("A")) {out.print("Aplicante");} 
                else if (prMant.getE01PRIFL4().equals("B")) { out.print("Beneficiario");} 
				else out.print(" ");%>" >

              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>

  
  <h4>Informaci&oacute;n Adicional</h4>
<br>
<table class="tableinfo">
 <tr > 
      <td > 
          <table cellspacing=0 cellpadding=2 width="100%" border="0">
            <tr id="trdark"> 
              <td nowrap> 
                <div align="right">V&iacute;a de Pago :</div>
              </td>
            <td nowrap > 
              <input type="text" readonly  name="E01PRIPVI" size="30" maxlength="17" 
					value="<% if (prMant.getE01PRIPVI().equals("R")) {out.print("Cuenta de Detalle");}
               					 else if (prMant.getE01PRIPVI().equals("D")) {out.print("Certificado");}
                				 else if (prMant.getE01PRIPVI().equals("G")) {out.print("Cuenta Contable");}
                				 else if (prMant.getE01PRIPVI().equals("6")) {out.print("Cheques de Gerencia");}
                				 else if (prMant.getE01PRIPVI().equals("1")) {out.print("FED");}
                				 else if (prMant.getE01PRIPVI().equals("2")) {out.print("Swift MT-205");}
                				 else if (prMant.getE01PRIPVI().equals("3")) {out.print("Swift MT-103");}
								 else if (prMant.getE01PRIPVI().equals("4")) {out.print("Swift MT-200");}
								 else if (prMant.getE01PRIPVI().equals("5")) {out.print("Swift MT-202");}
								 else if (prMant.getE01PRIPVI().equals("7")) {out.print("Swift MT-202 COV");}
							 else {out.print(" ");}%>" >
            </td>
              <td nowrap > 
                <div align="right">Receptor :</div>
              </td>
              <td nowrap > 
                <input type="text" name="E01PRIRID" size="15" maxlength="15" value="<%= prMant.getE01PRIRID().trim()%>" readonly>
                <a href="javascript:GetSwiftId('E01PRIRID')"></a></td>
            </tr>
            <tr id="trclear"> 
              <td nowrap  > 
                <div align="right">Formato de Cheque :</div>
              </td>
              <td nowrap > 
                <input type="text" name="E01PRIFL3" size="2" maxlength="1" value="<%= prMant.getE01PRIFL3().trim()%>" readonly>
              <td nowrap > 
                <div align="right">Instrucciones Recibidas Via :</div>
              </td>
              <td nowrap >
                <input type="text" name="E01PRIIRV" size="3" maxlength="1" value="<%= prMant.getE01PRIIRV().trim()%>" readonly>
                <a href="javascript:GetCode('E01PRIIRV','STATIC_pr_via.jsp')"></a> 
            </tr>
            <tr id="trdark"> 
              <td nowrap  > 
                <div align="right">Motivo de Operaci&oacute;n :</div>
              </td>
              <td nowrap > 
                <div align="left"> 
                  <input type="text" name="E01PRIDPT" size="5" maxlength="4" value="<%= prMant.getE01PRIDPT().trim()%>" readonly>
                  <a href="javascript:GetCodeCNOFC('E01PRIDPT','YL')"></a></div>
              </td>
              <TD nowrap width="17%"> 
                <DIV align="right">Detalle de Cargos  :</DIV>
              </TD><TD nowrap width="29%"> 
              	<input type="text" readonly  name="E01PRITTC" 
              	value="<%= prMant.getE01PRITTC().trim() %>" > 
              </TD>

            </tr>
            <tr id="trclear"> 
              <td nowrap  > 
                <div align="right">Nuestra Referencia :</div>
              </td>
              <td nowrap > 
                <div align="left"> 
                  <input type="text" name="E01PRISRF" size="17" maxlength="16" value="<%= prMant.getE01PRISRF().trim()%>" readonly>
                </div>
              </td>
              <td nowrap > 
                <div align="right">Referencia Relacionada :</div>
              </td>
              <td nowrap > 
                <div align="left"> 
                  <input type="text" name="E01PRITHF" size="17" maxlength="16" value="<%= prMant.getE01PRITHF().trim()%>" readonly>
                </div>
              </td>
            </tr>
            
            <tr id="trdark"> 
              <td nowrap  > 
                <div align="right">Cod. Operación Bancaria :</div>
              </td>
              <td nowrap > 
                <div align="left"> 
                  <input type="text" name="E01PRIBKO" size="6" maxlength="4" value="<%= prMant.getE01PRIBKO()%>" readonly >
               </div>
              </td>
              <td nowrap > 
                <div align="right">Código de Orden :</div>
              </td>
              <td nowrap > 
                <div align="left"> 
                  <input type="text" name="E01PRIITC" size="6" maxlength="4" value="<%= prMant.getE01PRIITC()%>" readonly >
                  <input type="text" name="E01PRIITA" size="11" maxlength="10" value="<%= prMant.getE01PRIITA()%>" readonly >
                </div>
              </td>
            </tr>
            
            <tr id="trclear"> 
              <td nowrap  > 
                <div align="right">Ident.Beneficiario :</div>
              </td>
              <td nowrap > 
                <div align="left"> 
                  <input type="text" name="E01PRIBID" size="15" maxlength="15" value="<%= prMant.getE01PRIBID()%>" readonly>
                 </div>
              </td>
              <td nowrap > 
                <div align="right">Cuenta del Beneficiario :</div>
              </td>
              <td nowrap > 
                <div align="left"> 
                  <input type="text" name="E01PRIBAC" size="40" maxlength="35" value="<%= prMant.getE01PRIBAC()%>" readonly>
                </div>
              </td>
            </tr>          
	</table>
	</td>
   </tr>
</table>
<br>
  	<table class="tbenter" width="100%" border="0" cellspacing=0 cellpadding=0>
    	<tr> 
       		<td nowrap> 
           		<table id="TableTab" cellspacing=0 cellpadding=2 border="0">
          			<tr> 
                      	<td nowrap id="Tab0" class="tabnormal" onClick="showTab(0,'E11PRIINB')"> 
                        	<div nowrap >Banco Intermediario</div>
                      	</td> 
                  		<td nowrap id="Tab1" class="tabnormal" onClick="showTab(1,'E11PRIBBK')"> 
                            <div nowrap >Banco Pagador</div>
                  		</td>
                  		<td nowrap id="Tab2" class="tabnormal" onClick="showTab(2,'E11PRIBCU')"> 
                            <div >Beneficiario</div>
                  		</td> 
                      	<td nowrap id="Tab3" class="tabnormal" onClick="showTab(3,'E11PRIBKB')"> 
                        	<div nowrap >Infor. Banco a Banco</div>
                      	</td> 
                  		<td class="tabempty" width="100%">&nbsp;                       
                  		</td> 
                	</tr>
        		</table>
       		</td>
    	</tr>
  	</table>
	<table class="tabdata" width="100%">
    	<tr>
    		<td nowrap>
            	<div id="dataTab0" align=center> 
                	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    	<tr id="trdark" > 
                      		<td nowrap align=center> 
                          		<input readonly type="text" name="E11PRIINB" size="45" maxlength="35" value="<%= prMant.getE11PRIINB().trim()%>">
                          		<br>
                          		<input readonly type="text" name="E21PRIINB" size="45" maxlength="35" value="<%= prMant.getE21PRIINB().trim()%>">
                          		<br>
                          		<input readonly type="text" name="E31PRIINB" size="45" maxlength="35" value="<%= prMant.getE31PRIINB().trim()%>">
                          		<br>
                          		<input readonly type="text" name="E41PRIINB" size="45" maxlength="35" value="<%= prMant.getE41PRIINB().trim()%>">
                        	<br>
                          		<input readonly type="text" name="E51PRIINB" size="45" maxlength="35" value="<%= prMant.getE51PRIINB().trim()%>">
                          		</td>
    					</tr>
 					</table>
             	</div>

                <div id="dataTab1" style="display: none" align=center> 
                	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    	<tr id="trdark" > 
                      		<td nowrap align=center> 
                          		<input readonly type="text" name="E11PRIBBK" size="45" maxlength="35" value="<%= prMant.getE11PRIBBK().trim()%>">
                          		<br>
                          		<input readonly type="text" name="E21PRIBBK" size="45" maxlength="35" value="<%= prMant.getE21PRIBBK().trim()%>">
                          		<br>
                          		<input readonly type="text" name="E31PRIBBK" size="45" maxlength="35" value="<%= prMant.getE31PRIBBK().trim()%>">
                          		<br>
                          		<input readonly type="text" name="E41PRIBBK" size="45" maxlength="35" value="<%= prMant.getE41PRIBBK().trim()%>">
                        	<br>
                          		<input readonly type="text" name="E51PRIBBK" size="45" maxlength="35" value="<%= prMant.getE51PRIBBK().trim()%>">
                          	</td>
    					</tr>
 					</table>
             	</div>

                <div id="dataTab2" style="display: none" align=center> 
                	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    	<tr id="trdark" > 
                      		<td nowrap align=center> 
                          		<input readonly type="text" name="E11PRIBCU" size="45" maxlength="35" value="<%= prMant.getE11PRIBCU().trim()%>">
                          		<br>
                          		<input readonly type="text" name="E21PRIBCU" size="45" maxlength="35" value="<%= prMant.getE21PRIBCU().trim()%>">
                          		<br>
                          		<input readonly type="text" name="E31PRIBCU" size="45" maxlength="35" value="<%= prMant.getE31PRIBCU().trim()%>">
                          		<br>
                          		<input readonly type="text" name="E41PRIBCU" size="45" maxlength="35" value="<%= prMant.getE41PRIBCU().trim()%>">
                        	<br>
                          		<input readonly type="text" name="E51PRIBCU" size="45" maxlength="35" value="<%= prMant.getE51PRIBCU().trim()%>">
                          		</td>
    					</tr>
 					</table>
             	</div>
                        
                <div id="dataTab3" style="display: none" align=center>
                	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    	<tr id="trdark" > 
                      		<td nowrap align=center>  
                          		<input readonly type="text" name="E11PRIBKB" size="45" maxlength="35" value="<%= prMant.getE11PRIBKB().trim()%>">
                          		<br>
                          		<input readonly type="text" name="E21PRIBKB" size="45" maxlength="35" value="<%= prMant.getE21PRIBKB().trim()%>">
                          		<br>
                          		<input readonly type="text" name="E31PRIBKB" size="45" maxlength="35" value="<%= prMant.getE31PRIBKB().trim()%>">
                          		<br>
                          		<input readonly type="text" name="E41PRIBKB" size="45" maxlength="35" value="<%= prMant.getE41PRIBKB().trim()%>">
                          		<br>
                          		<input readonly type="text" name="E51PRIBKB" size="45" maxlength="35" value="<%= prMant.getE51PRIBKB().trim()%>">
                          		<br>
                          		<input readonly type="text" name="E61PRIBKB" size="45" maxlength="35" value="<%= prMant.getE61PRIBKB().trim()%>">
                        	</td>
    					</tr>
 					</table>
				</div>
			</td>        
		</tr>
	</table>
	<BR>
	
  	<table class="tbenter" width="100%" border="0" cellspacing=0 cellpadding=0>
    	<tr> 
       		<td nowrap> 
           		<table id="TableTab" cellspacing=0 cellpadding=2 border="0">
          			<tr> 
	                  <td nowrap id="TabB0" class="tabhighlight" onClick="showTabB(0,'E11PRIDTO')"> 
	                            <div nowrap >Detalles para Ordenante </div>
	                  </td> 
	                
	                  <td nowrap id="TabB1" class="tabnormal" onClick="showTabB(1,'E11PRIDTP')"> 
	                            <div nowrap >Detalles de Pago </div>
	                  </td> 

                      <td nowrap id="TabB2" class="tabnormal" onClick="showTabB(2,'E11PRISCB')"> 
                        	<div nowrap >Bco. Corresponsal Emisor</div>
                      </td> 
                    
                      <td nowrap id="TabB3" class="tabnormal" onClick="showTabB(3,'E11PRIRCB')"> 
                        	<div nowrap >Bco. Corresponsal Receptor</div>
                      </td>
                	</tr>
        		</table>
       		</td>
    	</tr>
  	</table>
	<table class="tabdata" width="100%">
    	<tr>
    		<td nowrap>
                        <div id="dataTabB0" align=center> 
                        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    		<tr id="trdark" > 
                      			<td nowrap align=center> 
		                          <input type="text" name="E11PRIDTO" size="36" maxlength="35" value="<%= prMant.getE11PRIDTO().trim()%>" readonly>
		                          <br>
		                          <input type="text" name="E21PRIDTO" size="36" maxlength="35" value="<%= prMant.getE21PRIDTO().trim()%>" readonly>
		                          <br>
		                          <input type="text" name="E31PRIDTO" size="36" maxlength="35" value="<%= prMant.getE31PRIDTO().trim()%>" readonly>
		                          <br>
		                          <input type="text" name="E41PRIDTO" size="36" maxlength="35" value="<%= prMant.getE41PRIDTO().trim()%>" readonly>
                        		<br>
		                          <input type="text" name="E51PRIDTO" size="36" maxlength="35" value="<%= prMant.getE51PRIDTO().trim()%>" readonly>
		                         </td>
    						</tr>
 						 	</table>
                        </div>
                        <div id="dataTabB1" style="display: none" align=center>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    		<tr id="trdark" > 
                      			<td nowrap align=center> 
                          <input type="text" name="E11PRIDTP" size="36" maxlength="35" value="<%= prMant.getE11PRIDTP().trim()%>" readonly>
                          <br>
                          <input type="text" name="E21PRIDTP" size="36" maxlength="35" value="<%= prMant.getE21PRIDTP().trim()%>" readonly>
                          <br>
                          <input type="text" name="E31PRIDTP" size="36" maxlength="35" value="<%= prMant.getE31PRIDTP().trim()%>" readonly>
                          <br>
                          <input type="text" name="E41PRIDTP" size="36" maxlength="35" value="<%= prMant.getE41PRIDTP().trim()%>" readonly>
                        	<br>
                          <input type="text" name="E51PRIDTP" size="36" maxlength="35" value="<%= prMant.getE51PRIDTP().trim()%>" readonly>
                        </td>
    						</tr>
 						 	</table>
                        </div>
                        <div id="dataTabB2" align=center> 
                        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    		<tr id="trdark" > 
                      			<td nowrap align=center>  
	                          <input type="text" name="E11PRISCB" size="36" maxlength="35" value="<%= prMant.getE11PRISCB().trim()%>" readonly>
	                          <br>
	                          <input type="text" name="E21PRISCB" size="36" maxlength="35" value="<%= prMant.getE21PRISCB().trim()%>" readonly>
	                          <br>
	                          <input type="text" name="E31PRISCB" size="36" maxlength="35" value="<%= prMant.getE31PRISCB().trim()%>" readonly>
	                          <br>
	                          <input type="text" name="E41PRISCB" size="36" maxlength="35" value="<%= prMant.getE41PRISCB().trim()%>" readonly>
	                       		<br>
	                          <input type="text" name="E51PRISCB" size="36" maxlength="35" value="<%= prMant.getE51PRISCB().trim()%>" readonly>
	                         </td>
    						</tr>
 						 	</table>
                        </div>

                        <div id="dataTabB3" style="display: none" > 
                        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    		<tr id="trdark" > 
                      			<td nowrap align=center> 
                          <input type="text" name="E11PRIRCB" size="36" maxlength="35" value="<%= prMant.getE11PRIRCB().trim()%>" readonly>
                          <br>
                          <input type="text" name="E21PRIRCB" size="36" maxlength="35" value="<%= prMant.getE21PRIRCB().trim()%>" readonly>
                          <br>
                          <input type="text" name="E31PRIRCB" size="36" maxlength="35" value="<%= prMant.getE31PRIRCB().trim()%>" readonly>
                          <br>
                          <input type="text" name="E41PRIRCB" size="36" maxlength="35" value="<%= prMant.getE41PRIRCB().trim()%>" readonly>
                        	<br>
                          <input type="text" name="E51PRIRCB" size="36" maxlength="35" value="<%= prMant.getE51PRIRCB().trim()%>" readonly>
                          </td>
    						</tr>
 						 	</table>
                        </div>      
			</td>        
		</tr>
	</table>
                  
    <h4>Claves Confirmación DCV</h4>
    <table class="tableinfo">
      <tr > 
        <td nowrap> 
          <table cellspacing=0 cellpadding=2 width="100%" border="0">
            <tr id="trdark"> 
              <td nowrap width="24%" > 
                <input type="text" name="E01PRUCR1" size="16" maxlength="15" value="<%= prMant.getE01PRUCR1().trim()%>" readonly>
              </td>
              <td nowrap width="30%" > 
                <input type="text" name="E01PRUCR2" size="16" maxlength="15" value="<%= prMant.getE01PRUCR2().trim()%>" readonly>
                </td>
              <td nowrap width="17%" > 
                <input type="text" name="E01PRUCR3" size="16" maxlength="15" value="<%= prMant.getE01PRUCR3().trim()%>" readonly>
              </td>
              <td nowrap width="29%" > 
                <input type="text" name="E01PRUCR4" size="16" maxlength="15" value="<%= prMant.getE01PRUCR4().trim()%>" readonly>
              </td>
              <td nowrap width="29%" > 
                <input type="text" name="E01PRUCR5" size="16" maxlength="15" value="<%= prMant.getE01PRUCR5().trim()%>" readonly>
              </td>
            </tr>

            <tr id="trclear"> 
              <td nowrap width="24%" > 
                <input type="text" name="E01PRUCR6" size="16" maxlength="15" value="<%= prMant.getE01PRUCR6().trim()%>" readonly>
              </td>
              <td nowrap width="30%" > 
                <input type="text" name="E01PRUCR7" size="16" maxlength="15" value="<%= prMant.getE01PRUCR7().trim()%>" readonly>
                </td>
              <td nowrap width="17%" > 
                <input type="text" name="E01PRUCR8" size="16" maxlength="15" value="<%= prMant.getE01PRUCR8().trim()%>" readonly>
              </td>
              <td nowrap width="29%" > 
                <input type="text" name="E01PRUCR9" size="16" maxlength="15" value="<%= prMant.getE01PRUCR9().trim()%>" readonly>
              </td>
              <td nowrap width="29%" > 
                <input type="text" name="E01PRUC10" size="16" maxlength="15" value="<%= prMant.getE01PRUC10().trim()%>" readonly>
              </td>
            </tr>


          </table>
        </td>
      </tr>
    </table>


<BR>
 
   
<B>Campos Adicionales</B>
<BR>

<TABLE class="tableinfo" id="dataTable">
    
    <TR id=trdark>
		<td NOWRAP align="center" width="5%"><b>Seq</b></td>
		<td NOWRAP align="center" width="5%"><b>TAG</b></td>
		<td NOWRAP align="center" width="20%"><b>Descripcion</b></td>
		<td NOWRAP align="center" width="5%"><b>Opcion</b></td>
		<td NOWRAP align="left" width="65%"><b>Campo</b></td>
	</TR> 
	<% 
	  if (!prMant.getE01PRATG0().trim().equals("")) { 
	%>
    <TR id=trclear>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRASQ0" size="2" maxlength="1" value="<%= prMant.getE01PRASQ0().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRATG0" size="5" maxlength="4" value="<%= prMant.getE01PRATG0().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="20%" valign="top">
			<INPUT type="text" name="E01TAGDS0" size="31" maxlength="30" value="<%= prMant.getE01TAGDS0().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRAOP0" size="2" maxlength="1" value="<%= prMant.getE01PRAOP0().trim()%>" >
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
	        <%
	        int row = 1;
	        int col = 42;
	        int ln = Integer.parseInt(prMant.getE01TAGLE0().trim());
	        if (ln <= 35) {
	          col = ln + 7;
	        } else {
	          row = ln / 35;
	        }  	
	        %>
			<TEXTAREA name="E01PRAFL0" cols="<%=col%>" rows="<%=row%>" style="overflow:hidden"
			 onkeypress="textCounter(this,<%=prMant.getE01TAGLE0().trim()%>)"
			 ><%= prMant.getE01PRAFL0().trim()%></TEXTAREA>  
		</td>
	</TR>
	<% } %>
	<% 
	  if (!prMant.getE01PRATG1().trim().equals("")) { 
	%>
    <TR id=trclear>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRASQ1" size="2" maxlength="1" value="<%= prMant.getE01PRASQ1().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRATG1" size="5" maxlength="4" value="<%= prMant.getE01PRATG1().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="20%" valign="top">
			<INPUT type="text" name="E01TAGDS1" size="31" maxlength="30" value="<%= prMant.getE01TAGDS1().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRAOP1" size="2" maxlength="1" value="<%= prMant.getE01PRAOP1().trim()%>" >
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<%
	        int row = 1;
	        int col = 42;
	        int ln = Integer.parseInt(prMant.getE01TAGLE1().trim());
	        if (ln <= 35) {
	          col = ln + 7;
	        } else {
	          row = ln / 35;
	        }  	
	        %>
			<TEXTAREA name="E01PRAFL1" cols="<%=col%>" rows="<%=row%>" style="overflow:hidden"
			 onkeypress="textCounter(this,<%=prMant.getE01TAGLE1().trim()%>)"
			 ><%= prMant.getE01PRAFL1().trim()%></TEXTAREA>  
		</td>
	</TR>
	<% } %>  
	<% 
	  if (!prMant.getE01PRATG2().trim().equals("")) { 
	%>
    <TR id=trclear>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRASQ2" size="2" maxlength="1" value="<%= prMant.getE01PRASQ2().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRATG2" size="5" maxlength="4" value="<%= prMant.getE01PRATG2().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="20%" valign="top">
			<INPUT type="text" name="E01TAGDS2" size="31" maxlength="30" value="<%= prMant.getE01TAGDS2().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRAOP2" size="2" maxlength="1" value="<%= prMant.getE01PRAOP2().trim()%>" >
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<%
	        int row = 1;
	        int col = 42;
	        int ln = Integer.parseInt(prMant.getE01TAGLE2().trim());
	        if (ln <= 35) {
	          col = ln + 7;
	        } else {
	          row = ln / 35;
	        }  	
	        %>
			<TEXTAREA name="E01PRAFL2" cols="<%=col%>" rows="<%=row%>" style="overflow:hidden"
			 onkeypress="textCounter(this,<%=prMant.getE01TAGLE2().trim()%>)"
			 ><%= prMant.getE01PRAFL2().trim()%></TEXTAREA>  
		</td>
	</TR>
	<% } %>  
	<% 
	  if (!prMant.getE01PRATG3().trim().equals("")) { 
	%>
    <TR id=trclear>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRASQ3" size="2" maxlength="1" value="<%= prMant.getE01PRASQ3().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRATG3" size="5" maxlength="4" value="<%= prMant.getE01PRATG3().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="20%" valign="top">
			<INPUT type="text" name="E01TAGDS3" size="31" maxlength="30" value="<%= prMant.getE01TAGDS3().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRAOP3" size="2" maxlength="1" value="<%= prMant.getE01PRAOP3().trim()%>" >
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<%
	        int row = 1;
	        int col = 42;
	        int ln = Integer.parseInt(prMant.getE01TAGLE3().trim());
	        if (ln <= 35) {
	          col = ln + 7;
	        } else {
	          row = ln / 35;
	        }  	
	        %>
			<TEXTAREA name="E01PRAFL3" cols="<%=col%>" rows="<%=row%>" style="overflow:hidden"
			 onkeypress="textCounter(this,<%=prMant.getE01TAGLE3().trim()%>)"
			 ><%= prMant.getE01PRAFL3().trim()%></TEXTAREA>  
		</td>
	</TR>
	<% } %>  
	<% 
	  if (!prMant.getE01PRATG4().trim().equals("")) { 
	%>
    <TR id=trclear>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRASQ4" size="2" maxlength="1" value="<%= prMant.getE01PRASQ4().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRATG4" size="5" maxlength="4" value="<%= prMant.getE01PRATG4().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="20%" valign="top">
			<INPUT type="text" name="E01TAGDS4" size="31" maxlength="30" value="<%= prMant.getE01TAGDS4().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRAOP4" size="2" maxlength="1" value="<%= prMant.getE01PRAOP4().trim()%>" >
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<%
	        int row = 1;
	        int col = 42;
	        int ln = Integer.parseInt(prMant.getE01TAGLE4().trim());
	        if (ln <= 35) {
	          col = ln + 7;
	        } else {
	          row = ln / 35;
	        }  	
	        %>
			<TEXTAREA name="E01PRAFL4" cols="<%=col%>" rows="<%=row%>" style="overflow:hidden" 
			 onkeypress="textCounter(this,<%=prMant.getE01TAGLE4().trim()%>)" 
			 ><%= prMant.getE01PRAFL4().trim()%></TEXTAREA>  
		</td>
	</TR>
	<% } %>  
	<% 
	  if (!prMant.getE01PRATG5().trim().equals("")) { 
	%>
    <TR id=trclear>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRASQ5" size="2" maxlength="1" value="<%= prMant.getE01PRASQ5().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRATG5" size="5" maxlength="4" value="<%= prMant.getE01PRATG5().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="20%" valign="top">
			<INPUT type="text" name="E01TAGDS5" size="31" maxlength="30" value="<%= prMant.getE01TAGDS5().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRAOP5" size="2" maxlength="1" value="<%= prMant.getE01PRAOP5().trim()%>" >
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<%
	        int row = 1;
	        int col = 42;
	        int ln = Integer.parseInt(prMant.getE01TAGLE5().trim());
	        if (ln <= 35) {
	          col = ln + 7;
	        } else {
	          row = ln / 35;
	        }  	
	        %>
			<TEXTAREA name="E01PRAFL5" cols="<%=col%>" rows="<%=row%>" style="overflow:hidden"
			 onkeypress="textCounter(this,<%=prMant.getE01TAGLE5().trim()%>)"
			 ><%= prMant.getE01PRAFL5().trim()%></TEXTAREA>  
		</td>
	</TR>
	<% } %>  
	<% 
	  if (!prMant.getE01PRATG6().trim().equals("")) { 
	%>
    <TR id=trclear>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRASQ6" size="2" maxlength="1" value="<%= prMant.getE01PRASQ6().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRATG6" size="5" maxlength="4" value="<%= prMant.getE01PRATG6().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="20%" valign="top">
			<INPUT type="text" name="E01TAGDS6" size="31" maxlength="30" value="<%= prMant.getE01TAGDS6().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRAOP6" size="2" maxlength="1" value="<%= prMant.getE01PRAOP6().trim()%>" >
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<%
	        int row = 1;
	        int col = 42;
	        int ln = Integer.parseInt(prMant.getE01TAGLE6().trim());
	        if (ln <= 35) {
	          col = ln + 7;
	        } else {
	          row = ln / 35;
	        }  	
	        %>
			<TEXTAREA name="E01PRAFL6" cols="<%=col%>" rows="<%=row%>" style="overflow:hidden"
			 onkeypress="textCounter(this,<%=prMant.getE01TAGLE6().trim()%>)"
			 ><%= prMant.getE01PRAFL6().trim()%></TEXTAREA>  
		</td>
	</TR>
	<% } %>  
	<% 
	  if (!prMant.getE01PRATG7().trim().equals("")) { 
	%>
    <TR id=trclear>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRASQ7" size="2" maxlength="1" value="<%= prMant.getE01PRASQ7().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRATG7" size="5" maxlength="4" value="<%= prMant.getE01PRATG7().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="20%" valign="top">
			<INPUT type="text" name="E01TAGDS7" size="31" maxlength="30" value="<%= prMant.getE01TAGDS7().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRAOP7" size="2" maxlength="1" value="<%= prMant.getE01PRAOP7().trim()%>" >
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<%
	        int row = 1;
	        int col = 42;
	        int ln = Integer.parseInt(prMant.getE01TAGLE7().trim());
	        if (ln <= 35) {
	          col = ln + 7;
	        } else {
	          row = ln / 35;
	        }  	
	        %>
			<TEXTAREA name="E01PRAFL7" cols="<%=col%>" rows="<%=row%>" style="overflow:hidden"
			 onkeypress="textCounter(this,<%=prMant.getE01TAGLE7().trim()%>)"
			 ><%= prMant.getE01PRAFL7().trim()%></TEXTAREA>  
		</td>
	</TR>
	<% } %>  
	<% 
	  if (!prMant.getE01PRATG8().trim().equals("")) { 
	%>
    <TR id=trclear>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRASQ8" size="2" maxlength="1" value="<%= prMant.getE01PRASQ8().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRATG8" size="5" maxlength="4" value="<%= prMant.getE01PRATG8().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="20%" valign="top">
			<INPUT type="text" name="E01TAGDS8" size="31" maxlength="30" value="<%= prMant.getE01TAGDS8().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRAOP8" size="2" maxlength="1" value="<%= prMant.getE01PRAOP8().trim()%>" >
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<%
	        int row = 1;
	        int col = 42;
	        int ln = Integer.parseInt(prMant.getE01TAGLE8().trim());
	        if (ln <= 35) {
	          col = ln + 7;
	        } else {
	          row = ln / 35;
	        }  	
	        %>
			<TEXTAREA name="E01PRAFL8" cols="<%=col%>" rows="<%=row%>" style="overflow:hidden"
			 onkeypress="textCounter(this,<%=prMant.getE01TAGLE8().trim()%>)"
			 ><%= prMant.getE01PRAFL8().trim()%></TEXTAREA>  
		</td>
	</TR>
	<% } %>  
	<% 
	  if (!prMant.getE01PRATG9().trim().equals("")) { 
	%>
    <TR id=trclear>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRASQ9" size="2" maxlength="1" value="<%= prMant.getE01PRASQ9().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRATG9" size="5" maxlength="4" value="<%= prMant.getE01PRATG9().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="20%" valign="top">
			<INPUT type="text" name="E01TAGDS9" size="31" maxlength="30" value="<%= prMant.getE01TAGDS9().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRAOP9" size="2" maxlength="1" value="<%= prMant.getE01PRAOP9().trim()%>" >
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<%
	        int row = 1;
	        int col = 42;
	        int ln = Integer.parseInt(prMant.getE01TAGLE9().trim());
	        if (ln <= 35) {
	          col = ln + 7;
	        } else {
	          row = ln / 35;
	        }  	
	        %>
			<TEXTAREA name="E01PRAFL9" cols="<%=col%>" rows="<%=row%>" style="overflow:hidden"
			 onkeypress="textCounter(this,<%=prMant.getE01TAGLE9().trim()%>)"
			 ><%= prMant.getE01PRAFL9().trim()%></TEXTAREA>  
		</td>
	</TR>
	<% } %>  



  </TABLE>
<br>  
  
  
<p align="center"> 
	
	<input id="EIBSBTN" type=button name="Pdf" value="Pdf" OnClick="showPdf();" >

  </p>



<!--
<br>
  <table class="tableinfo">
    <tr>
      <td>Ingresado por : <%= prMant.getE01PRIUSR().trim()%></td>
      <td>Fecha : <%= Util.formatDate(prMant.getE01PRITDM().trim(),prMant.getE01PRITDD().trim(),prMant.getE01PRITDY().trim())%></td>
      <td>Hora : <%= Util.formatTime(prMant.getE01PRITTM().trim())%></td>
    </tr>
    <tr>
      <td>Liberado por : <% if (prMant.getE01PRIRB2().trim().equals("")) { out.print(prMant.getE01PRIRBY().trim());} else { out.print(prMant.getE01PRIRB2().trim());} %></td>
      <td>Fecha : <% if (prMant.getE01PRIRB2().trim().equals("")) {
					out.print(Util.formatDate(prMant.getE01PRIRDM().trim(),prMant.getE01PRIRDD().trim(),prMant.getE01PRIRDY().trim()));
				} else {
					out.print(Util.formatDate(prMant.getE01PRIR2M().trim(),prMant.getE01PRIR2D().trim(),prMant.getE01PRIR2Y().trim()));
                } %> </td>
      <td>Hora : <% if (prMant.getE01PRIRB2().trim().equals("")) { out.print(Util.formatTime(prMant.getE01PRIRTM().trim()));} else { out.print(Util.formatTime(prMant.getE01PRIR2T().trim()));} %></td>
    </tr>
  </table>
-->
  
</form>

</body>
</html>
