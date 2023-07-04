<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Transferencias</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

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
function getSwift(){
	var refnum;
	refnum = document.forms[0].E01PRINUM.value;
	//self.window.location.href = "<%=request.getContextPath()%>/servlet/datapro.eibs.transfer.JSEWD0341F?SCREEN=3&REFNUM="+refnum;
	page = webapp + "/servlet/datapro.eibs.transfer.JSEWD0341F?SCREEN=3&REFNUM="+refnum;
 	CenterWindow(page,420,300,2);
}

  builtHPopUp();
  <% 
  if (userPO.getPurpose().equals("REVERSAL")) { 
  %> 
   builtNewMenu(pr_r_opt);
  <% } else {%>
   builtNewMenu(pr_a_opt);
  <%}%>
  initMenu();
  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
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
  
	function getIF01Forms(url) {
		var newurl = url+"&ACCOUNT="+document.forms[0].E01PRICAC.value;
		CenterNamedWindow(newurl, 'pdf', 800, 600, 2);
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

<h3 align="center">Transferencias<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="pr_ap_maint.jsp,EPR0000"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPR0000" >
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
            <td nowrap > 
                  <div align="right">Código de Transacción :</div>
            </td>
                <td nowrap align="left">
                  <input type="text" name="E01PRITCD" size="11" maxlength="10" value="<%= prMant.getE01PRITCD().trim()%>" readonly>
                </td>
                <td nowrap align="right"> N&uacute;mero de Referencia : </td>
                <td nowrap align="left">
                  <input type="text" name="E01PRINUM" size="11" maxlength="10" value="<%= prMant.getE01PRINUM().trim()%>" readonly>
                </td>
          </tr>
          <TR id="trclear">
            	<td nowrap > 
                  <div align="right">Canal :</div>
            	</td>
                <td nowrap align="left">
                  <input type="text" name="E01PRICNL" size="11" maxlength="10" value="<%= prMant.getE01PRICNL().trim()%>" readonly>
              <a href="javascript:GetCodeCNOFC('E01PRICNL','CP')"></a>                   
                </td>
            	<td nowrap > 
                  <div align="right"></div>
            	</td>          
            	<td nowrap > 
                  <div align="left"></div>
            	</td>            	      
          	</TR>          
          
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
     

                <input type="text" name="E01PRIOCU" size="10" maxlength="10" value="<%= prMant.getE01PRIOCU().trim()%>" readonly>
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
              <div align="right">Moneda / Monto a Transferir :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E01PRITCY" size="3" maxlength="3" value="<%= prMant.getE01PRITCY().trim()%>" >
              <input type="text" readonly  name="E01PRIAMT" size="15" maxlength="13" value="<%= prMant.getE01PRIAMT().trim()%>" >
            </td>
            <td nowrap >Tasa de Cambio :</td>
            <td nowrap > 
              <input type="text" readonly  name="E01PRICXR" size="13" maxlength="13" value="<%= prMant.getE01PRICXR().trim()%>" onKeyPress="enterDecimal()" >
            </td>
            <td nowrap > 
              <div align="right">Fecha Pago :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E01PRICDD" size="2" maxlength="2" value="<%= prMant.getE01PRICDD().trim()%>" >
              <input type="text" readonly  name="E01PRICDM" size="2" maxlength="2" value="<%= prMant.getE01PRICDM().trim()%>" >
              <input type="text" readonly  name="E01PRICDY" size="2" maxlength="2" value="<%= prMant.getE01PRICDY().trim()%>" >
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
      <td nowrap align="center" >Producto </td>
    </tr>
    <tr id="trclear"> 
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01PRIDBK" size="2" maxlength="2" value="<%= prMant.getE01PRIDBK().trim()%>" readonly>
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01PRIDBR" size="4" maxlength="4"  value="<%= prMant.getE01PRIDBR().trim()%>" readonly>
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01PRIDCY" size="3" maxlength="3" value="<%= prMant.getE01PRIDCY().trim()%>" readonly>
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01PRIDGL" size="17" maxlength="16" value="<%= prMant.getE01PRIDGL().trim()%>" readonly>
        </div>
      </td>
      <td nowrap  > 
        <div align="center"> 
          <input type="text" name="E01PRIDAC" size="13" maxlength="12" value="<%= prMant.getE01PRIDAC().trim()%>" readonly>
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01PRIDCC" size="9" maxlength="9"  value="<%= prMant.getE01PRIDCC().trim()%>" readonly>
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01PRIDAM" size="15" maxlength="13" value="<%= prMant.getE01PRIDAM().trim()%>">
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01PRIDPD" size="15" maxlength="15"  value="<%= prMant.getE01PRIDPD().trim()%>" readonly>
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
      <td nowrap align="center" > Producto</td>
    </tr>
    <tr id="trclear"> 
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01PRICBK" size="2" maxlength="2" value="<%= prMant.getE01PRICBK().trim()%>" readonly>
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01PRICBR" size="4" maxlength="4"  value="<%= prMant.getE01PRICBR().trim()%>" readonly>
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01PRICCY" size="3" maxlength="3" value="<%= prMant.getE01PRICCY().trim()%>" readonly>
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01PRICGL" size="17" maxlength="16" value="<%= prMant.getE01PRICGL().trim()%>" readonly>
        </div>
      </td>
      <td nowrap  > 
        <div align="center"> 
          <input type="text" name="E01PRICAC" size="13" maxlength="12" value="<%= prMant.getE01PRICAC().trim()%>" readonly>
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01PRICCC" size="9" maxlength="9"  value="<%= prMant.getE01PRICCC().trim()%>" readonly>
        </div>
      </td>
      <td nowrap > 
        <div align="center">
          <input type="text" name="E01PRICAM" size="15" maxlength="13" value="<%= prMant.getE01PRICAM().trim()%>">
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01PRICPD" size="15" maxlength="15"  value="<%= prMant.getE01PRICPD().trim()%>" readonly>
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
  <h4>Comisiones</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="24%" > 
              <div align="right">Tabla / Monto :</div>
            </td>
            <td nowrap width="30%" > 
              <input type="text" name="E01PRICOT" size="2" maxlength="2" value="<%= prMant.getE01PRICOT().trim()%>" readonly>
              <input type="text" name="E01PRICOM" size="15" maxlength="13" value="<%= prMant.getE01PRICOM().trim()%>" readonly>
            </td>
            <td nowrap width="17%" > 
              <div align="right">Cargos por :</div>
            </td>
            <td nowrap width="29%" > 
              <input type="text" name="E01PRICHG" value="<% if (prMant.getE01PRICHG().equals("A")) {out.print("Aplicante");} 
                else if (prMant.getE01PRICHG().equals("B")) { out.print("Beneficiario");} 
                else if (prMant.getE01PRICHG().equals("N")) {out.print("Ninguno");} 
                else if (prMant.getE01PRICHG().equals("O")) {out.print("Nuestro");}
				else out.print(" ");%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n Adicional</h4>
<table class="tableinfo">
 <tr > 
      <td > 
          <TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
            <TBODY><TR id="trdark"> 
              <TD nowrap> 
                <DIV align="right">V&iacute;a de Pago :</DIV>
              </TD>
            <TD nowrap> 
              <INPUT type="text" readonly name="E01PRIPVI" size="30" maxlength="17" value='<% if (prMant.getE01PRIPVI().equals("R")) {out.print("Cuenta de Detalle");}
               					 else if (prMant.getE01PRIPVI().equals("D")) {out.print("Certificado");}
                				 else if (prMant.getE01PRIPVI().equals("G")) {out.print("Cuenta Contable");}
                				 else if (prMant.getE01PRIPVI().equals("6")) {out.print("Cheques de Gerencia");}
                				 else if (prMant.getE01PRIPVI().equals("1")) {out.print("FED");}
                				 else if (prMant.getE01PRIPVI().equals("2")) {out.print("Swift MT-205");}
                				 else if (prMant.getE01PRIPVI().equals("3")) {out.print("Swift MT-103");}
								 else if (prMant.getE01PRIPVI().equals("4")) {out.print("Swift MT-200");}
								 else if (prMant.getE01PRIPVI().equals("5")) {out.print("Swift MT-202");}
								 else if (prMant.getE01PRIPVI().equals("7")) {out.print("Swift MT-202 COV");}
								 else {out.print(" ");}%>'>
            </TD>
              <TD nowrap> 
                <DIV align="right">Receptor :</DIV>
              </TD>
              <TD nowrap> 
                <INPUT type="text" name="E01PRIRID" size="15" maxlength="15" value="<%= prMant.getE01PRIRID().trim()%>" readonly>
                <A href="javascript:GetSwiftId('E01PRIRID')"></A></TD>
            </TR>
            <TR id="trclear"> 
              <TD nowrap> 
                <DIV align="right">Formato de Cheque :</DIV>
              </TD>
              <TD nowrap> 
                <INPUT type="text" name="E01PRIFL3" size="2" maxlength="1" value="<%= prMant.getE01PRIFL3().trim()%>" readonly>
              </TD><TD nowrap> 
                <DIV align="right">Instrucciones Recibidas Via :</DIV>
              </TD>
              <TD nowrap>
                <INPUT type="text" name="E01PRIIRV" size="3" maxlength="1" value="<%= prMant.getE01PRIIRV().trim()%>" readonly>
                <A href="javascript:GetCode('E01PRIIRV','STATIC_pr_via.jsp')"></A> 
            </TD></TR>
            <TR id="trdark"> 
              <TD nowrap> 
                <DIV align="right">FED Tipo / Subtipo :</DIV>
              </TD>
              <TD nowrap>
                <INPUT type="text" name="E01FEDTYP" size="3" maxlength="2" value="<%= prMant.getE01FEDTYP().trim()%>" readonly>
                <A href="javascript:GetCode('E01FEDTYP','STATIC_pr_types.jsp')"></A>
<INPUT type="text" name="E01FEDSTY" size="3" maxlength="2" value="<%= prMant.getE01FEDSTY().trim()%>" readonly>
                <A href="javascript:GetCode('E01FEDSTY','STATIC_pr_subtypes.jsp')"></A> 
              </TD>
              <TD nowrap> 
                <DIV align="right">C&oacute;digo de Producto de FED :</DIV>
              </TD>
              <TD nowrap>
                <INPUT type="text" name="E01PRIPRC" size="3" maxlength="3" value="<%= prMant.getE01PRIIRV().trim()%>" readonly>
                <A href="javascript:GetCode('E01PRIPRC','STATIC_pr_fed.jsp')"></A> 
              </TD>
            </TR>            
            <TR id="trclear"> 
              <TD nowrap> 
                <DIV align="right">Informe Regulatorio :</DIV>
              </TD>
              <TD nowrap colspan="3"> 
                <INPUT type="text" name="E11PRIRTR" size="36" maxlength="35" value="<%= prMant.getE11PRIRTR().trim()%>" readonly>
              </TD>
            </TR>
            <TR id="trdark"> 
              <TD nowrap>&nbsp;</TD>
              <TD nowrap colspan="3"> 
                <INPUT type="text" name="E21PRIRTR" size="36" maxlength="35" value="<%= prMant.getE21PRIRTR().trim()%>" readonly>
              </TD>
            </TR>
            <TR id="trclear"> 
              <TD nowrap>&nbsp;</TD>
              <TD nowrap colspan="3"> 
                <INPUT type="text" name="E31PRIRTR" size="36" maxlength="35" value="<%= prMant.getE31PRIRTR().trim()%>" readonly>
              </TD>
            </TR>
            <TR id="trdark"> 
              <TD nowrap> 
                <DIV align="right">Origen de Fondos :</DIV>
              </TD>
              <TD nowrap> 
                <DIV align="left"> 
                  <INPUT type="text" name="E01PRISOU" size="2" maxlength="2" value="<%= prMant.getE01PRISOU().trim()%>" readonly>
                  <A href="javascript:GetCodeCNOFC('E01PRISOU','18')"></A></DIV>
              </TD>
              <TD nowrap> 
                <DIV align="right">Destino de Fondos :</DIV>
              </TD>
              <TD nowrap> 
                <INPUT type="text" name="E01PRIDIB" size="2" maxlength="2" value="<%= prMant.getE01PRIDIB().trim()%>" readonly>
                <A href="javascript:GetCodeCNOFC('E01PRIDIB','38')"></A></TD>
            </TR>
            <TR id="trclear"> 
              <TD nowrap> 
                <DIV align="right">Nuestra Referencia :</DIV>
              </TD>
              <TD nowrap> 
                <DIV align="left"> 
                  <INPUT type="text" name="E01PRISRF" size="17" maxlength="16" value="<%= prMant.getE01PRISRF().trim()%>" readonly>
                </DIV>
              </TD>
              <TD nowrap width="17%"> 
                <DIV align="right">Detalle de Cargos  :</DIV>
              </TD><TD nowrap width="29%"> 
                <SELECT name="E01PRITTC" disabled>
				  <OPTION value="" <% if (!(prMant.getE01PRITTC().equals("B") || prMant.getE01PRITTC().equals("N"))) out.print("selected"); %>></OPTION>
				  <OPTION value="BEN" <% if (prMant.getE01PRITTC().equals("BEN")) out.print("selected"); %>>BEN</OPTION>
                  <OPTION value="OUR" <% if (prMant.getE01PRITTC().equals("OUR")) out.print("selected"); %>>OUR</OPTION>
                  <OPTION value="SHA" <% if (prMant.getE01PRITTC().equals("SHA")) out.print("selected"); %>>SHA</OPTION>
                </SELECT>
              </TD>
              
            </TR>
            <TR id="trdark"> 
              <TD nowrap> 
                <DIV align="right">Cod. Operación Bancaria :</DIV>
              </TD>
              <TD nowrap> 
                <DIV align="left"> 
                  <INPUT type="text" name="E01PRIBKO" size="6" maxlength="4" value="<%= prMant.getE01PRIBKO()%>" readonly>
                  <A href="javascript:GetCode('E01PRIBKO','STATIC_tr_operations.jsp')"></A></DIV>
              </TD>
              <TD nowrap> 
                <DIV align="right">Referencia Relacionada :</DIV>
              </TD><TD nowrap> 
                <DIV align="left"> 
                  <INPUT type="text" name="E01PRITHF" size="17" maxlength="16" value="<%= prMant.getE01PRITHF().trim()%>" readonly>
                </DIV>
              </TD>
              
            </TR>
            <TR id="trclear"> 
              <TD nowrap> 
                <DIV align="right">Ident.Beneficiario :</DIV>
              </TD>
              <TD nowrap> 
                <DIV align="left"> 
                  <INPUT type="text" name="E01PRIBID" size="15" maxlength="15" value="<%= prMant.getE01PRIBID()%>" readonly>
                 </DIV>
              </TD>
              <TD nowrap> 
                <DIV align="right">Código de Orden :</DIV>
              </TD><TD nowrap> 
                <DIV align="left"> 
                  <INPUT type="text" name="E01PRIITC" size="6" maxlength="4" value="<%= prMant.getE01PRIITC()%>" readonly>
                  <A href="javascript:GetCode('E01PRIITC','STATIC_tr_instructions.jsp')"></A> 
                  <INPUT type="text" name="E01PRIITA" size="11" maxlength="10" value="<%= prMant.getE01PRIITA()%>" readonly>
                </DIV>
              </TD>
              
            </TR>      
            <TR id="trdark"> 
              <TD nowrap> 
                <DIV align="right">Motivo de Operaci&oacute;n :</DIV>
              </TD>
              <TD nowrap> 
                <DIV align="left"> 
                  <INPUT type="text" name="E01PRIDPT" size="5" maxlength="4" value="<%= prMant.getE01PRIDPT().trim()%>" readonly>
                  <A href="javascript:GetCodeCNOFC('E01PRIDPT','YL')"></A></DIV>
              </TD>
              <TD nowrap> 
                <DIV align="right">Cuenta del Beneficiario :</DIV>
              </TD><TD nowrap> 
                <DIV align="left"> 
                  <INPUT type="text" name="E01PRIBAC" size="40" maxlength="35" value="<%= prMant.getE01PRIBAC()%>" readonly>
                </DIV>
              </TD>              
            </TR>
            
             
	</TBODY></TABLE>
	</td>
   </tr>
</table>
<br>
  <table class="tbenter" width="100%" border="0" cellspacing=0 cellpadding=0>
    <tr> 
       <td nowrap> 
           <table id="TableTab" cellspacing=0 cellpadding=2 border="0">
          		<tr> 
                  <td nowrap id="Tab0" class="tabhighlight" onClick="showTab(0,'E11PRIDTO')"> 
                            <div>Detalles para Ordenante </div>
                  </td> 
                
                  <td nowrap id="Tab1" class="tabnormal" onClick="showTab(1,'E11PRIDTP')"> 
                            <div>Detalles de Pago </div>
                  </td> 
                 
                  <td nowrap id="Tab2" class="tabnormal" onClick="showTab(2,'E11PRIBCU')"> 
                            <div >Beneficiario</div>
                  </td> 
                 
                  <td nowrap id="Tab3" class="tabnormal" onClick="showTab(3,'E11PRIBBK')"> 
                            <div>Banco del Beneficiario</div>
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
                        <div id="dataTab1" style="display: none" align=center>
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
                        <div id="dataTab2" style="display: none" align=center> 
                        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    		<tr id="trdark" > 
                      			<td nowrap align=center> 
                        
                          <input type="text" name="E11PRIBCU" size="36" maxlength="35" value="<%= prMant.getE11PRIBCU().trim()%>" readonly>
                          <br>
                          <input type="text" name="E21PRIBCU" size="36" maxlength="35" value="<%= prMant.getE21PRIBCU().trim()%>" readonly>
                          <br>
                          <input type="text" name="E31PRIBCU" size="36" maxlength="35" value="<%= prMant.getE31PRIBCU().trim()%>" readonly>
                          <br>
                          <input type="text" name="E41PRIBCU" size="36" maxlength="35" value="<%= prMant.getE41PRIBCU().trim()%>" readonly>
                        	<br>
                          <input type="text" name="E51PRIBCU" size="36" maxlength="35" value="<%= prMant.getE51PRIBCU().trim()%>" readonly>
                        	</td>
    						</tr>
 						 	</table>
                        </div>
                        <div id="dataTab3" style="display: none" align=center> 
                        		<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    		<tr id="trdark" > 
                      			<td nowrap align=center> 
                          <input type="text" name="E11PRIBBK" size="36" maxlength="35" value="<%= prMant.getE11PRIBBK().trim()%>" readonly>
                          <br>
                          <input type="text" name="E21PRIBBK" size="36" maxlength="35" value="<%= prMant.getE21PRIBBK().trim()%>" readonly>
                          <br>
                          <input type="text" name="E31PRIBBK" size="36" maxlength="35" value="<%= prMant.getE31PRIBBK().trim()%>" readonly>
                          <br>
                          <input type="text" name="E41PRIBBK" size="36" maxlength="35" value="<%= prMant.getE41PRIBBK().trim()%>" readonly>
                        	<br>
                          <input type="text" name="E51PRIBBK" size="36" maxlength="35" value="<%= prMant.getE51PRIBBK().trim()%>" readonly>
             			</td>
    						</tr>
 						 	</table>
                        </div>
        </td>
            
    </tr>
  </table>
                 
   <BR>
                    
  <table class="tbenter" width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
       <td nowrap> 
           <table id="TableTabB" cellspacing="0" cellpadding="2" border="0">
                   <tr> 
                      <td nowrap id="TabB0" class="tabhighlight" onClick="showTabB(0,'E11PRIBKB')"> 
                        	<div>Infor. Banco a Banco</div>
                      </td> 
                    
                      <td nowrap id="TabB1" class="tabnormal" onClick="showTabB(1,'E11PRIINB')"> 
                        	<div>Banco Intermediario</div>
                      </td> 
                    
                      <td nowrap id="TabB2" class="tabnormal" onClick="showTabB(2,'E11PRISCB')"> 
                        	<div>Bco. Corresponsal Emisor</div>
                      </td> 
                    
                      <td nowrap id="TabB3" class="tabnormal" onClick="showTabB(3,'E11PRIRCB')"> 
                        	<div>Bco. Corresponsal Receptor</div>
                      </td>
					 <td class="tabempty" width="100%">&nbsp;</td>
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
                          <input type="text" name="E11PRIBKB" size="36" maxlength="35" value="<%= prMant.getE11PRIBKB().trim()%>" readonly>
                          <br>
                          <input type="text" name="E21PRIBKB" size="36" maxlength="35" value="<%= prMant.getE21PRIBKB().trim()%>" readonly>
                          <br>
                          <input type="text" name="E31PRIBKB" size="36" maxlength="35" value="<%= prMant.getE31PRIBKB().trim()%>" readonly>
                          <br>
                          <input type="text" name="E41PRIBKB" size="36" maxlength="35" value="<%= prMant.getE41PRIBKB().trim()%>" readonly>
                          <br>
                          <input type="text" name="E51PRIBKB" size="36" maxlength="35" value="<%= prMant.getE51PRIBKB().trim()%>" readonly>
                          <br>
                          <input type="text" name="E61PRIBKB" size="36" maxlength="35" value="<%= prMant.getE61PRIBKB().trim()%>" readonly>
                        	</td>
    						</tr>
 						 	</table>
                        </div>
                        <div id="dataTabB1" style="display: none" align=center> 
                        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    		<tr id="trdark" > 
                      			<td nowrap align=center> 
                          <input type="text" name="E11PRIINB" size="36" maxlength="35" value="<%= prMant.getE11PRIINB().trim()%>" readonly>
                          <br>
                          <input type="text" name="E21PRIINB" size="36" maxlength="35" value="<%= prMant.getE21PRIINB().trim()%>" readonly>
                          <br>
                          <input type="text" name="E31PRIINB" size="36" maxlength="35" value="<%= prMant.getE31PRIINB().trim()%>" readonly>
                          <br>
                          <input type="text" name="E41PRIINB" size="36" maxlength="35" value="<%= prMant.getE41PRIINB().trim()%>" readonly>
                        	<br>
                          <input type="text" name="E51PRIINB" size="36" maxlength="35" value="<%= prMant.getE51PRIINB().trim()%>" readonly>
                       </td>
    						</tr>
 						 	</table>
                        </div>
                        <div id="dataTabB2" style="display: none">
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
			<INPUT type="text" name="E01PRAOP0" size="2" maxlength="1" value="<%= prMant.getE01PRAOP0().trim()%>"  readonly>
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<INPUT type="text" name="E01PRASQ0" size="2" maxlength="1" value="<%= prMant.getE01PRASQ0().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRATG0" size="5" maxlength="4" value="<%= prMant.getE01PRATG0().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="20%" valign="top">
			<INPUT type="text" name="E01TAGDS0" size="31" maxlength="30" value="<%= prMant.getE01TAGDS0().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRAOP0" size="2" maxlength="1" value="<%= prMant.getE01PRAOP0().trim()%>"  readonly>
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<INPUT type="hidden" name="E01PRAFL0" value="<%= prMant.getE01PRAFL0().trim()%>">		
	        <%
	        int row = 1;
	        int lnt = Integer.parseInt(prMant.getE01TAGLE0().trim());
	        int ln = lnt;
	        if (lnt > 35) {
	          row = lnt / 35;
	          ln = 35;
	        }  	
	        for (int ix = 1; ix <= row; ix++) {
	          int posi = (ix * ln) - ln;
	          int posf = (ix * ln) - 1;
	          String vl = "";
	          try {
	            vl =  prMant.getE01PRAFL0().substring(posi,posf);
	          } catch (Exception e) {
	          	if(posi <=  prMant.getE01PRAFL0().length()) {
				  vl = prMant.getE01PRAFL0().substring(posi);;
				}
	          }
			%>
			  <INPUT type="text" name="E01PRAFL0_<%=ix%>" size="40" maxlength="35" value="<%= vl %>"  
	  			   readonly ><BR> 
			<%}%>   
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
			<INPUT type="text" name="E01PRAOP1" size="2" maxlength="1" value="<%= prMant.getE01PRAOP1().trim()%>" readonly>
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<INPUT type="hidden" name="E01PRAFL1" value="<%= prMant.getE01PRAFL1().trim()%>">		
			<%
	        int row = 1;
	        int lnt = Integer.parseInt(prMant.getE01TAGLE1().trim());
	        int ln = lnt;
	        if (lnt > 35) {
	          row = lnt / 35;
	          ln = 35;
	        } 
	        for (int ix = 1; ix <= row; ix++) {
	          int posi = (ix * ln) - ln;
	          int posf = (ix * ln) - 1;
	          String vl = "";
	          try {
	            vl =  prMant.getE01PRAFL1().substring(posi,posf);
	          } catch (Exception e) {
	            if(posi <=  prMant.getE01PRAFL1().length()) {
				  vl = prMant.getE01PRAFL1().substring(posi);;
				} 
	          }
			%>
			  <INPUT type="text" name="E01PRAFL1_<%=ix%>" size="40" maxlength="35" value="<%= vl %>" 
			   readonly ><BR> 
			<%}%>
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
			<INPUT type="text" name="E01PRAOP2" size="2" maxlength="1" value="<%= prMant.getE01PRAOP2().trim()%>" readonly>
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<INPUT type="hidden" name="E01PRAFL2" value="<%= prMant.getE01PRAFL2().trim()%>">		
			<%
	        int row = 1;
	        int lnt = Integer.parseInt(prMant.getE01TAGLE2().trim());
	        int ln = lnt;
	        if (lnt > 35) {
	          row = lnt / 35;
	          ln = 35;
	        } 
	        for (int ix = 1; ix <= row; ix++) {
	          int posi = (ix * ln) - ln;
	          int posf = (ix * ln) - 1;
	          String vl = "";
	          try {
	            vl =  prMant.getE01PRAFL2().substring(posi,posf);
	          } catch (Exception e) {
	            if(posi <=  prMant.getE01PRAFL2().length()) {
				  vl = prMant.getE01PRAFL2().substring(posi);;
				} 
	          }
			%>
			  <INPUT type="text" name="E01PRAFL2_<%=ix%>" size="40" maxlength="35" value="<%= vl %>" 
			   readonly ><BR> 
			<%}%>
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
			<INPUT type="text" name="E01PRAOP3" size="2" maxlength="1" value="<%= prMant.getE01PRAOP3().trim()%>" readonly>
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<INPUT type="hidden" name="E01PRAFL3" value="<%= prMant.getE01PRAFL3().trim()%>">		
			<%
	        int row = 1;
	        int lnt = Integer.parseInt(prMant.getE01TAGLE3().trim());
	        int ln = lnt;
	        if (lnt > 35) {
	          row = lnt / 35;
	          ln = 35;
	        } 
	        for (int ix = 1; ix <= row; ix++) {
	          int posi = (ix * ln) - ln;
	          int posf = (ix * ln) - 1;
	          String vl = "";
	          try {
	            vl =  prMant.getE01PRAFL3().substring(posi,posf);
	          } catch (Exception e) {
	            if(posi <=  prMant.getE01PRAFL3().length()) {
				  vl = prMant.getE01PRAFL3().substring(posi);;
				} 
	          }
			%>
			  <INPUT type="text" name="E01PRAFL3_<%=ix%>" size="40" maxlength="35" value="<%= vl %>" 
			   readonly ><BR> 
			<%}%>
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
			<INPUT type="text" name="E01PRAOP4" size="2" maxlength="1" value="<%= prMant.getE01PRAOP4().trim()%>" readonly>
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<INPUT type="hidden" name="E01PRAFL4" value="<%= prMant.getE01PRAFL4().trim()%>">		
			<%
	        int row = 1;
	        int lnt = Integer.parseInt(prMant.getE01TAGLE4().trim());
	        int ln = lnt;
	        if (lnt > 35) {
	          row = lnt / 35;
	          ln = 35;
	        } 
	        for (int ix = 1; ix <= row; ix++) {
	          int posi = (ix * ln) - ln;
	          int posf = (ix * ln) - 1;
	          String vl = "";
	          try {
	            vl =  prMant.getE01PRAFL4().substring(posi,posf);
	          } catch (Exception e) {
	            if(posi <=  prMant.getE01PRAFL4().length()) {
				  vl = prMant.getE01PRAFL4().substring(posi);;
				} 
	          }
			%>
			  <INPUT type="text" name="E01PRAFL4_<%=ix%>" size="40" maxlength="35" value="<%= vl %>" 
			   readonly ><BR> 
			<%}%>
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
			<INPUT type="text" name="E01PRAOP5" size="2" maxlength="1" value="<%= prMant.getE01PRAOP5().trim()%>" readonly>
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<INPUT type="hidden" name="E01PRAFL5" value="<%= prMant.getE01PRAFL5().trim()%>">		
			<%
	        int row = 1;
	        int lnt = Integer.parseInt(prMant.getE01TAGLE5().trim());
	        int ln = lnt;
	        if (lnt > 35) {
	          row = lnt / 35;
	          ln = 35;
	        } 
	        for (int ix = 1; ix <= row; ix++) {
	          int posi = (ix * ln) - ln;
	          int posf = (ix * ln) - 1;
	          String vl = "";
	          try {
	            vl =  prMant.getE01PRAFL5().substring(posi,posf);
	          } catch (Exception e) {
	            if(posi <=  prMant.getE01PRAFL5().length()) {
				  vl = prMant.getE01PRAFL5().substring(posi);;
				} 
	          }
			%>
			  <INPUT type="text" name="E01PRAFL5_<%=ix%>" size="40" maxlength="35" value="<%= vl %>" 
			   readonly ><BR> 
			<%}%>
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
			<INPUT type="text" name="E01PRAOP6" size="2" maxlength="1" value="<%= prMant.getE01PRAOP6().trim()%>" readonly>
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<INPUT type="hidden" name="E01PRAFL6" value="<%= prMant.getE01PRAFL6().trim()%>">		
			<%
	        int row = 1;
	        int lnt = Integer.parseInt(prMant.getE01TAGLE6().trim());
	        int ln = lnt;
	        if (lnt > 35) {
	          row = lnt / 35;
	          ln = 35;
	        } 
	        for (int ix = 1; ix <= row; ix++) {
	          int posi = (ix * ln) - ln;
	          int posf = (ix * ln) - 1;
	          String vl = "";
	          try {
	            vl =  prMant.getE01PRAFL6().substring(posi,posf);
	          } catch (Exception e) {
	            if(posi <=  prMant.getE01PRAFL6().length()) {
				  vl = prMant.getE01PRAFL6().substring(posi);;
				} 
	          }
			%>
			  <INPUT type="text" name="E01PRAFL6_<%=ix%>" size="40" maxlength="35" value="<%= vl %>" 
			   readonly ><BR> 
			<%}%>
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
			<INPUT type="text" name="E01PRAOP7" size="2" maxlength="1" value="<%= prMant.getE01PRAOP7().trim()%>" readonly>
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<INPUT type="hidden" name="E01PRAFL7" value="<%= prMant.getE01PRAFL7().trim()%>">		
			<%
	        int row = 1;
	        int lnt = Integer.parseInt(prMant.getE01TAGLE7().trim());
	        int ln = lnt;
	        if (lnt > 35) {
	          row = lnt / 35;
	          ln = 35;
	        } 
	        for (int ix = 1; ix <= row; ix++) {
	          int posi = (ix * ln) - ln;
	          int posf = (ix * ln) - 1;
	          String vl = "";
	          try {
	            vl =  prMant.getE01PRAFL7().substring(posi,posf);
	          } catch (Exception e) {
	            if(posi <=  prMant.getE01PRAFL7().length()) {
				  vl = prMant.getE01PRAFL7().substring(posi);;
				} 
	          }
			%>
			  <INPUT type="text" name="E01PRAFL7_<%=ix%>" size="40" maxlength="35" value="<%= vl %>" 
			   readonly ><BR> 
			<%}%>
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
			<INPUT type="text" name="E01PRAOP8" size="2" maxlength="1" value="<%= prMant.getE01PRAOP8().trim()%>" readonly>
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<INPUT type="hidden" name="E01PRAFL8" value="<%= prMant.getE01PRAFL8().trim()%>">		
			<%
	        int row = 1;
	        int lnt = Integer.parseInt(prMant.getE01TAGLE8().trim());
	        int ln = lnt;
	        if (lnt > 35) {
	          row = lnt / 35;
	          ln = 35;
	        } 
	        for (int ix = 1; ix <= row; ix++) {
	          int posi = (ix * ln) - ln;
	          int posf = (ix * ln) - 1;
	          String vl = "";
	          try {
	            vl =  prMant.getE01PRAFL8().substring(posi,posf);
	          } catch (Exception e) {
	            if(posi <=  prMant.getE01PRAFL8().length()) {
				  vl = prMant.getE01PRAFL8().substring(posi);;
				} 
	          }
			%>
			  <INPUT type="text" name="E01PRAFL8_<%=ix%>" size="40" maxlength="35" value="<%= vl %>" 
			   readonly ><BR> 
			<%}%>
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
			<INPUT type="text" name="E01PRAOP9" size="2" maxlength="1" value="<%= prMant.getE01PRAOP9().trim()%>" readonly>
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<INPUT type="hidden" name="E01PRAFL9" value="<%= prMant.getE01PRAFL9().trim()%>">		
			<%
	        int row = 1;
	        int lnt = Integer.parseInt(prMant.getE01TAGLE9().trim());
	        int ln = lnt;
	        if (lnt > 35) {
	          row = lnt / 35;
	          ln = 35;
	        } 
	        for (int ix = 1; ix <= row; ix++) {
	          int posi = (ix * ln) - ln;
	          int posf = (ix * ln) - 1;
	          String vl = "";
	          try {
	            vl =  prMant.getE01PRAFL9().substring(posi,posf);
	          } catch (Exception e) {
	            if(posi <=  prMant.getE01PRAFL9().length()) {
				  vl = prMant.getE01PRAFL9().substring(posi);;
				} 
	          }
			%>
			  <INPUT type="text" name="E01PRAFL9_<%=ix%>" size="40" maxlength="35" value="<%= vl %>" 
			   readonly ><BR> 
			<%}%>
		</td>
	</TR>
	<% } %>  

  </TABLE>




  </form>

</body>
</html>
