<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Payment and Receiving</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="prBasic" class="datapro.eibs.beans.EPR200001Message"  scope="session" />
<jsp:useBean id="error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
  
  function showTab(index, name){  
  	for(var i=0;i<5;i++){
   		document.all["Tab"+i].className="tabnormal";
   		document.all["dataTab"+i].style.display="none";
   	}
  
    document.all["Tab"+index].className="tabhighlight";  
  	document.all["dataTab"+index].style.display="";
  	document.all[name].focus();
  }
  
  builtHPopUp();
  
  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
    init(opth,field,bank,ccy,field1,field2,opcod);
    showPopupHelp();
  }
  
  function setHelp() {
    var val = document.forms[0].E01PRPVIA.options[document.forms[0].E01PRPVIA.selectedIndex].value;
    if (val=="1" || val=="5") {
    	document.all.recivHelp.href="javascript:GetFedId('E01PRPRPD')";
    	document.all.recivHelp.style.display="";
    }
    else if (val=="2" || val=="3") {
  		document.all.recivHelp.href="javascript:GetSwiftId('E01PRPRPD')";
  		document.all.recivHelp.style.display="";
  	} else document.all.recivHelp.style.display="none";
  }
  
</SCRIPT>

</head>

<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
	 error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 
 String title = "";
 title = (userPO.getPurpose().equals("NEW")) ? "Apertura" : "Mantenimiento";
 
%>

<h3 align="center"><%=title%> Ordenes de Pago <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="pr_maint.jsp,EPR2000" ></h3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.transfer.JSEPR2000">

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  	
  <table class="tableinfo" >
    <tr> 
      <td > 
      <table  cellspacing=0 cellpadding=0 width="100%">
      <tr id=trdark>
      	<td nowrap width="30%" > 
        <div align="right">Cuenta :</div>
      	</td>
      	<td nowrap width="10%">  
        <% if (userPO.getPurpose().equals("NEW")) {%>
          <input type="text" name="E01PRPDAC" size="12" maxlength="12" value="<%= userPO.getAccNum()%>">
        <% } else {%>
          <input type="text" name="E01PRPDAC" size="12" maxlength="12" value="<%= prBasic.getE01PRPDAC()%>">
        <% } %>
        </td>
      	<td nowrap width="20%"> 
        <div align="right">Referencia : </div>
      	</td>
      	<% if (userPO.getPurpose().equals("NEW")) {%>
      	<td nowrap width="40%" >
      	  <input type="hidden" name="E01PRPNUM" size="9" maxlength="9" value="999999999">
      	  <B>NUEVA</B>                              
      	</td>
      	<% } else {%>
      	<td nowrap width="40%" >
      	  <input type="text" name="E01PRPNUM" size="9" maxlength="9" value="<%= prBasic.getE01PRPNUM()%>">                              
      	</td>
      	<% } %>
      	</tr>
  	  </table>
  	  </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n B&aacute;sica </h4>
  <table class="tableinfo">
    <tr> 
      <td align="center"> 
  			<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr id="trdark" > 
                      <td nowrap width="31%" > 
                        <div align="right">Via de Pago :</div>
                      </td>
                      <td nowrap width="25%"> 
                        <select name="E01PRPVIA" onchange="setHelp()">
                          <option value="1" <% if(prBasic.getE01PRPVIA().equals("1")) out.print("selected");%>>FED</option>
                          <option value="2" <% if(prBasic.getE01PRPVIA().equals("2")) out.print("selected");%>>Swift MT-100/103</option>
                          <option value="3" <% if(prBasic.getE01PRPVIA().equals("3")) out.print("selected");%>>Swift MT-200/202</option>
                          <option value="4" <% if(prBasic.getE01PRPVIA().equals("4")) out.print("selected");%>>Chips</option>
                          <option value="5" <% if(prBasic.getE01PRPVIA().equals("5")) out.print("selected");%>>ACH</option>
                          <option value="6" <% if(prBasic.getE01PRPVIA().equals("6")) out.print("selected");%>>Vale Vista</option>
                          <option value="7" <% int via = 7;
                          try { via = Integer.parseInt(prBasic.getE01PRPVIA()); } catch (Exception e) {	via = 7;}				
                          if (via >= 7) out.print("selected");%>>Interna</option>                          
                        </select>
                      </td>
                      <td nowrap width="27%"> 
                        <div align="right">Tabla Comision :</div>
                      </td>
                      <td nowrap width="17%"> 
                        <input type="text" name="E01PRPCOT" size="4" maxlength="3" value="<%= prBasic.getE01PRPCOT().trim()%>">                      
                        <input type="hidden" name="AMTCOT">                      
                        <a href="javascript:GetCNTRLPRF('E01PRPCOT','AMTCOT')"><img src="<%=request.getContextPath()%>/images/1b.gif" align="bottom" border="0" alt="help" ></a>                  
                      </td>
                    </tr>
                    <tr id="trclear"> 
                      <td nowrap width="31%"> 
                        <div align="right">Monto Transferencia :</div>
                      </td>
                      <td nowrap > 
                        <input type="text" name="E01PRPAMT" size="17" maxlength="17" value="<%= prBasic.getE01PRPAMT().trim()%>" onkeypress="enterDecimal()">
                      </td>
                      <td nowrap> 
                        <div align="right">Moneda :</div>
                      </td>
                      <td nowrap> 
                        <input type="text" name="E01PRPTCY" size="4" maxlength="3" value="<%= prBasic.getE01PRPTCY().trim()%>">
                        <a href="javascript:GetCurrency('E01PRPTCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" align="bottom" border="0" alt="help" ></a>                  
                      </td>
                    </tr>
                    <tr id="trdark" > 
                      <td nowrap width="31%"> 
                        <div align="right">Ref. Beneficiario :</div>
                      </td>
                      <td nowrap width="25%"> 
                        <input type="text" name="E01PRPTHF" size="9" maxlength="9" value="<%= prBasic.getE01PRPTHF().trim()%>">
                      </td>
                      <td nowrap width="27%"> 
                        <div align="right">Ref. Ordenante :</div>
                      </td>
                      <td nowrap width="17%"> 
                        <input type="text" name="E01PRPSRF" size="9" maxlength="9" value="<%= prBasic.getE01PRPSRF().trim()%>">
                      </td>
                    </tr>
                    <tr id="trclear" > 
                      <td nowrap width="31%" >
                      <div align="right">Ident.Recibidor :</div>
                      </td>
                      <td nowrap width="25%"> 
                        <input type="text" name="E01PRPRPD" size="9" maxlength="9" value="<%= prBasic.getE01PRPRPD().trim()%>">
                        <a id="recivHelp" style="display:none"><img src="<%=request.getContextPath()%>/images/1b.gif" align="bottom" border="0" alt="help" ></a>                  
                      </td>
                      <td nowrap width="27%"> 
                        <div align="right">Nombre Corto Recibidor :</div>
                      </td>
                      <td nowrap width="17%"> 
                        <input type="text" name="E01PRPRSH" size="20" maxlength="15" value="<%= prBasic.getE01PRPRSH().trim()%>">
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

                      <td nowrap id="Tab0" class="tabnormal" onClick="showTab(0,'E11PRPINB')"> 
                        	<div nowrap >Banco Intermediario</div>
                      </td> 

                  <td nowrap id="Tab1" class="tabnormal" onClick="showTab(1,'E11PRPBBK')"> 
                            <div nowrap >Banco Pagador</div>
                  </td>

                  <td nowrap id="Tab2" class="tabnormal" onClick="showTab(2,'E11PRPBCU')"> 
                            <div >Beneficiario</div>
                  </td> 

               
                  <td nowrap id="Tab3" class="tabnormal" onClick="showTab(3,'E11PRPDTP')"> 
                            <div nowrap >Detalles de Pago </div>
                  </td> 
                 
                      <td nowrap id="Tab4" class="tabnormal" onClick="showTab(4,'E11PRPBKB')"> 
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
		                          	<div><a href="javascript:GetSwiftIdDesc('E11PRPINB','E21PRPINB','E31PRPINB','E41PRPINB')">
		                          	<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0"></a></div>
                          <input type="text" name="E11PRPINB" size="45" maxlength="35" value="<%= prBasic.getE11PRPINB().trim()%>">
                          <br>
                          <input type="text" name="E21PRPINB" size="45" maxlength="35" value="<%= prBasic.getE21PRPINB().trim()%>">
                          <br>
                          <input type="text" name="E31PRPINB" size="45" maxlength="35" value="<%= prBasic.getE31PRPINB().trim()%>">
                          <br>
                          <input type="text" name="E41PRPINB" size="45" maxlength="35" value="<%= prBasic.getE41PRPINB().trim()%>">
                        	</td>
    						</tr>
 						 	</table>
                        </div>

                        <div id="dataTab1" style="display: none" align=center> 
                        		<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    		<tr id="trdark" > 
                      			<td nowrap align=center> 
		                          	<div><a href="javascript:GetSwiftIdDesc('E11PRPBBK','E21PRPBBK','E31PRPBBK','E41PRPBBK')">
		                          	<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0"></a></div>
                          <input type="text" name="E11PRPBBK" size="45" maxlength="35" value="<%= prBasic.getE11PRPBBK().trim()%>">
                          <br>
                          <input type="text" name="E21PRPBBK" size="45" maxlength="35" value="<%= prBasic.getE21PRPBBK().trim()%>">
                          <br>
                          <input type="text" name="E31PRPBBK" size="45" maxlength="35" value="<%= prBasic.getE31PRPBBK().trim()%>">
                          <br>
                          <input type="text" name="E41PRPBBK" size="45" maxlength="35" value="<%= prBasic.getE41PRPBBK().trim()%>">
                        	</td>
    						</tr>
 						 	</table>
                        </div>

                        <div id="dataTab2" style="display: none" align=center> 
                        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    		<tr id="trdark" > 
                      			<td nowrap align=center> 
		                          	<div><a href="javascript:GetSwiftIdDesc('E11PRPBCU','E21PRPBCU','E31PRPBCU','E41PRPBCU')">
		                          	<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0"></a></div>                        
                          <input type="text" name="E11PRPBCU" size="45" maxlength="35" value="<%= prBasic.getE11PRPBCU().trim()%>">
                          <br>
                          <input type="text" name="E21PRPBCU" size="45" maxlength="35" value="<%= prBasic.getE21PRPBCU().trim()%>">
                          <br>
                          <input type="text" name="E31PRPBCU" size="45" maxlength="35" value="<%= prBasic.getE31PRPBCU().trim()%>">
                          <br>
                          <input type="text" name="E41PRPBCU" size="45" maxlength="35" value="<%= prBasic.getE41PRPBCU().trim()%>">
                        	</td>
    						</tr>
 						 	</table>
                        </div>

                      
                        <div id="dataTab3" style="display: none" align=center>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    		<tr id="trdark" > 
                      			<td nowrap align=center> 
		                          	<div><a href="javascript:GetSwiftIdDesc('E11PRPDTP','E21PRPDTP','E31PRPDTP','E41PRPDTP')">
		                          	<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0"></a></div>
                          		<input type="text" name="E11PRPDTP" size="45" maxlength="35" value="<%= prBasic.getE11PRPDTP().trim()%>">
                          <br>
                          <input type="text" name="E21PRPDTP" size="45" maxlength="35" value="<%= prBasic.getE21PRPDTP().trim()%>">
                          <br>
                          <input type="text" name="E31PRPDTP" size="45" maxlength="35" value="<%= prBasic.getE31PRPDTP().trim()%>">
                          <br>
                          <input type="text" name="E41PRPDTP" size="45" maxlength="35" value="<%= prBasic.getE41PRPDTP().trim()%>">
                        	</td>
    						</tr>
 						 	</table>
                        </div>
                        
                        <div id="dataTab4" style="display: none" align=center>
                       <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    	<tr id="trdark" > 
                      	<td nowrap align=center>  
						<div><a href="javascript:GetSwiftIdDesc('E11PRPBKB','E21PRPBKB','E31PRPBKB','E41PRPBKB')">
		                  	<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0"></a></div>
                          <input type="text" name="E11PRPBKB" size="45" maxlength="35" value="<%= prBasic.getE11PRPBKB().trim()%>">
                          <br>
                          <input type="text" name="E21PRPBKB" size="45" maxlength="35" value="<%= prBasic.getE21PRPBKB().trim()%>">
                          <br>
                          <input type="text" name="E31PRPBKB" size="45" maxlength="35" value="<%= prBasic.getE31PRPBKB().trim()%>">
                          <br>
                          <input type="text" name="E41PRPBKB" size="45" maxlength="35" value="<%= prBasic.getE41PRPBKB().trim()%>">
                        	</td>
    						</tr>
 						 	</table>
                        </div>
                        
        </td>
            
    </tr>
  </table>
  
  <BR>
</table>
    <h4>Cuenta de D&eacute;bito</h4>
    
    <TABLE class="tableinfo">
      <tr id="trdark"> 
        <td nowrap align="center" >Concepto</td>
        <td nowrap align="center" >Banco</td>
        <td nowrap align="center" >Agencia</td>
        <td nowrap align="center" >Moneda</td>
        <td nowrap align="center" >Cuenta Contable</td>
        <td nowrap align="center" >Cuenta</td>
        <td nowrap align="center" >Centro de Costo</td>
      </tr>
      <tr id="trclear"> 
        <td nowrap > 
          <div align="center"> 
            <input readonly type="text" name="E01PRPDRS" size="2" maxlength="2"  value="<%= prBasic.getE01PRPDRS().trim()%>" onkeypress="enterInteger()" readonly>
            <input readonly type="text" name="E01PRIDPD" size="17" maxlength="16"  value="<%= prBasic.getE01PRIDPD().trim()%>" readonly>
          </div>
        </td>
        <td nowrap > 
          <div align="center"> 
            <input readonly type="text" name="E01PRPDBK" size="2" maxlength="2" value="<%= prBasic.getE01PRPDBK().trim()%>" >
          </div>
        </td>
        <td nowrap > 
          <div align="center"> 
            <input readonly type="text" name="E01PRPDBR" size="4" maxlength="3"  value="<%= prBasic.getE01PRPDBR().trim()%>">
          </div>
        </td>
        <td nowrap > 
          <div align="center"> 
            <input readonly type="text" name="E01PRPDCY" size="3" maxlength="3" value="<%= prBasic.getE01PRPDCY().trim()%>">
          </div>
        </td>
        <td nowrap > 
          <div align="center"> 
            <input readonly type="text" name="E01PRPDGL" size="17" maxlength="16" value="<%= prBasic.getE01PRPDGL().trim()%>" onkeypress="enterInteger()">
          </div>
        </td>
        <td nowrap  > 
          <div align="center"> 
            <input readonly type="text" name="E01PRPDAC" size="17" maxlength="16" value="<%= prBasic.getE01PRPDAC().trim()%>">
          </div>
        </td>
        <td nowrap > 
          <div align="center"> 
            <input readonly type="text" name="E01PRPDCC" size="7" maxlength="6"  value="<%= prBasic.getE01PRPDCC().trim()%>"
			oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E01PRPDBK.value,'','','','')">
          </div>
        </td>
      </tr>
    </table>
    <h4>Cuenta de Cr&eacute;dito</h4>
    <table class="tableinfo" >
      <tr id="trdark"> 
        <td nowrap align="center" >Concepto</td>
        <td nowrap align="center" >Banco </td>
        <td nowrap align="center" >Agencia</td>
        <td nowrap align="center" >Moneda</td>
        <td nowrap align="center" >Cuenta Contable</td>
        <td nowrap align="center" >Cuenta</td>
        <td nowrap align="center" >Centro de Costo</td>
      </tr>
      <tr id="trclear"> 
        <td nowrap > 
          <div align="center"> 
            <input type="text" name="E01PRPCRS" size="2" maxlength="2"  value="<%= prBasic.getE01PRPCRS().trim()%>" onKeyPress="enterInteger()" readonly>
            <input type="text" name="E01PRICPD" size="17" maxlength="16"  value="<%= prBasic.getE01PRICPD().trim()%>" readonly
			 oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E01PRPCBK.value,'','E01PRPCGL','E01PRPCRS','93'); return false;">
          </div>
        </td>
        <td nowrap > 
          <div align="center"> 
            <input type="text" name="E01PRPCBK" size="2" maxlength="2" value="<%= prBasic.getE01PRPCBK().trim()%>" >
          </div>
        </td>
        <td nowrap > 
          <div align="center"> 
            <input type="text" name="E01PRPCBR" size="4" maxlength="3"  value="<%= prBasic.getE01PRPCBR().trim()%>"
			oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01PRPCBK.value,'','','','')" onkeypress="enterInteger()">
          </div>
        </td>
        <td nowrap > 
          <div align="center"> 
            <input type="text" name="E01PRPCCY" size="3" maxlength="3" value="<%= prBasic.getE01PRPCCY().trim()%>" 
			oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01PRPCBK.value,'','','','')">
          </div>
        </td>
        <td nowrap > 
          <div align="center"> 
            <input type="text" name="E01PRPCGL" size="17" maxlength="16" value="<%= prBasic.getE01PRPCGL().trim()%>" onkeypress="enterInteger()"
            oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01PRPCBK.value,document.forms[0].E01PRPCCY.value,'','','')">
          </div>
        </td>
        <td nowrap  > 
          <div align="center"> 
            <input type="text" name="E01PRPCAC" size="17" maxlength="16" value="<%= prBasic.getE01PRPCAC().trim()%>"
		  oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01PRPCBK.value,'','','','RT')"  onkeypress="enterInteger()">
          </div>
        </td>
        <td nowrap > 
          <div align="center"> 
            <input type="text" name="E01PRPCCC" size="7" maxlength="6"  value="<%= prBasic.getE01PRPCCC().trim()%>"
			oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E01PRPCBK.value,'','','','')">
          </div>
        </td>
      </tr>
    </table>
    <BR>
  <table class="tableinfo">
    <tr> 
      <td align="center"> 
  			<table width="100%" border="0" cellspacing="0" cellpadding="0"> 
                    <tr id="trdark" > 
                      <td nowrap > 
                        <div align="right">Descripcion :</div>
                      </td>
                      <td nowrap colspan=7> 
                        <input type="text" name="E01PRPDSC" size="35" maxlength="35" value="<%= prBasic.getE01PRPDSC()%>"> 
					  </td>
					 </tr>	
                    <tr id="trclear"> 
                      <td nowrap > 
                        <div align="right">Frecuencia de Pago :</div>
                      </td>
                      <td nowrap colspan=7> 
                        <select name="E01PRPFRQ">
                          <option value="D" <% if(prBasic.getE01PRPFRQ().equals("D")) out.print("selected");%>>Diario</option>
                          <option value="W" <% if(prBasic.getE01PRPFRQ().equals("W")) out.print("selected");%>>Semanal</option>
                          <option value="M" <% if(prBasic.getE01PRPFRQ().equals("M")) out.print("selected");%>>Mensual</option>
                          <option value="V" <% if(prBasic.getE01PRPFRQ().equals("V")) out.print("selected");%>>Variable</option>
                          <option value="2" <% if(prBasic.getE01PRPFRQ().equals("2")) out.print("selected");%>>Cada 2 Meses</option>
                          <option value="3" <% if(prBasic.getE01PRPFRQ().equals("3")) out.print("selected");%>>Cada 3 Meses</option>
                        </select>
                      </td>
                    </tr>
                    <tr id=trdark> 
                      <td nowrap > 
                        <div align="right"> D&iacute;a de Pago :</div>
                      </td>
                      <td nowrap colspan=7> 
                        <input type="text" name="E01PRPPMD" size="3" maxlength="2" value="<%= prBasic.getE01PRPPMD()%>" >
                        <a href="javascript:GetCode('E01PRPPMD','STATIC_rt_ciclo.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" align="bottom" border="0" alt="help" ></a> 
                      </td>
                    </tr>
                    <tr id="trclear"> 
                      <td nowrap > 
                        <div align="right"> o N&uacute;mero de D&iacute;as :</div>
                      </td>
                      <td nowrap colspan=7> 
                        <input type="text" name="E01PRPDYS" size="4" maxlength="3" value="<%= prBasic.getE01PRPDYS()%>" >
                        (Para frecuencia de pagos variable solamente) </td>
                    </tr>
                    <tr id="trdark" > 
                      <td nowrap > 
                        <div align="right">N&uacute;mero de Pagos :</div>
                      </td>
                      <td nowrap colspan=7> 
                        <input type="text" name="E01PRPNPM" size="6" maxlength="4" value="<%= prBasic.getE01PRPNPM()%>" >
                        (9999 = Indefinido) </td>
                    </tr>
                    <tr id="trclear" > 
                      <td nowrap > 
                        <div align="right">Tipo de Pago :</div>
                      </td>
                      <td nowrap colspan=7> 
                        <input type="radio" name="E01PRPPMT" value="F" 
              <%if(!prBasic.getE01PRPPMT().equals("V")) out.print("checked");%>>
                        Fijo 
                        <input type="radio" name="E01PRPPMT" value="V" 
              <%if(prBasic.getE01PRPPMT().equals("V")) out.print("checked");%>>
                        Variable </td>
                    </tr>
                    <tr id="trdark" > 
                      <td nowrap > 
                        <div align="right">Autoriza Sobregiros :</div>
                      </td>
                      <td nowrap colspan=7> 
                        <input type="radio" name="E01PRPODA" value="Y" 
              <%if(prBasic.getE01PRPODA().equals("Y")) out.print("checked");%>>
                        S&iacute; 
                        <input type="radio" name="E01PRPODA" value="N" 
              <%if(!prBasic.getE01PRPODA().equals("Y")) out.print("checked");%>>
                        No </td>
                    </tr>
                    <tr id="trclear"> 
                      <td nowrap > 
                        <div align="right">Suspender Pago :</div>
                      </td>
                      <td nowrap colspan=7> 
                        <input type="radio" name="E01PRPSPM" value="Y" 
              <%if(prBasic.getE01PRPSPM().equals("Y")) out.print("checked");%>>
                        S&iacute; 
                        <input type="radio" name="E01PRPSPM" value="N" 
              <%if(!prBasic.getE01PRPSPM().equals("Y")) out.print("checked");%>>
                        No </td>
                    </tr>
             
                  </table>
  		 </td>            
    </tr>
  </table>
 <h4>Ultimo Pago</h4>
 <table class="tableinfo">
    <tr> 
      <td align="center"> 
  			<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr id="trdark">                        
                      <td nowrap width="31%"> 
                        <div align="right">Fecha :</div>
                      </td>
                      <td nowrap > 
                        <input type="text" name="E01PRPLPD" size="3" maxlength="2" value="<%= prBasic.getE01PRPLPD().trim()%>" readonly>                        
                        <input type="text" name="E01PRPLPM" size="3" maxlength="2" value="<%= prBasic.getE01PRPLPM().trim()%>" readonly>
                        <input type="text" name="E01PRPLPY" size="3" maxlength="2" value="<%= prBasic.getE01PRPLPY().trim()%>" readonly>
                      </td>
                      <td nowrap> 
                        <div align="right">Monto :</div>
                      </td>
                      <td nowrap> 
                        <input type="text" name="E01PRPLPA" size="12" maxlength="12" value="<%= prBasic.getE01PRPLPA().trim()%>" readonly>
                      </td>
                    </tr>
                    <tr id="trclear" > 
                      <td nowrap width="31%"> 
                        <div align="right">No. Referencia :</div>
                      </td>
                      <td nowrap width="25%"> 
                        <input type="text" name="E01PRPLRF" size="10" maxlength="9" value="<%= prBasic.getE01PRPLRF().trim()%>" readonly>
                      </td>
                      <td nowrap width="27%"> 
                        <div align="right">No. Pago :</div>
                      </td>
                      <td nowrap width="17%"> 
                        <input type="text" name="E01PRPLPN" size="5" maxlength="5" value="<%= prBasic.getE01PRPLPN().trim()%>" readonly>
                      </td>
                    </tr>
                                     
                  </table>
      </td>
    </tr>
  </table>
  <p align="center"> 
  	<input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
  <SCRIPT Language="Javascript">
  	setHelp();
  </SCRIPT>
</form>
</body>
</html>
