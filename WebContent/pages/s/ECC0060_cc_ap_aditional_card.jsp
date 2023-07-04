<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Tarjeta Adicional</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>
<body>
<h3 align="center">Tarjeta Adicional<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cc_aditional_card.jsp,ECC0060"> 
</h3>
<hr size="4">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 
%> 
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0060" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="E01CCRTCL" VALUE="S">
  <INPUT TYPE=HIDDEN NAME="E01TARTYP" VALUE="<%= userPO.getHeader2().trim()%>">
  <%
        int row;
		  try{row = Integer.parseInt(request.getParameter("ROW"));}catch(Exception e){row = 0;}
		  appList.setCurrentRow(row);
  %>   
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= row%>">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="16%"> 
              	<input type="text" name="E01TARTY2" size="15" maxlength="14" value="<% if (userPO.getHeader2().equals("D")) out.print("Tarjeta de Débito"); else out.print("Tarjeta de Crédito"); %>" readonly>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Tipo Tarjeta:</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01CCRPRIM" size="21" maxlength="20" value="Secundaria" readonly>
              </div>
            </td>
          </tr>    
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente Primario: </b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01PRICUN" size="9" maxlength="9" readonly value="<%= userPO.getCusNum().trim()%>">
			  </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre Primario: </b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01PRINA1" size="45" maxlength="45" readonly value="<%= userPO.getCusName().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta: </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" readonly name="E01CCRCRA" size="12" maxlength="9" value="<%= userPO.getAccNum().trim() %>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Numero Tarjeta: </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" readonly name="E01CCRNUM" size="21" maxlength="20" value="<%= appList.getRecord(0).trim()%>">
                </b> </div>
            </td>
          </tr>    
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente Secundario: </b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01SECCUN" size="9" maxlength="9" value="<%= appList.getRecord(1).trim()%>">
                <a href="javascript:GetCustomerDescId('E01SECCUN','E01SECNA1','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a>
			  </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre Secundario: </b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01SECNA1" size="45" maxlength="45" readonly value="<%= appList.getRecord(39).trim()%>">
              </div>
            </td>
          </tr>            
        </table>
      </td>
    </tr>
  </table>
  <h4> Informacion Basica</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
           <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Banco ABA :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRABA" size="11" maxlength="10" value="<%= appList.getRecord(8).trim()%>" onKeypress="enterInteger()"> 
            </td>
            <td nowrap width="25%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="27%"> </td>
          </tr>   
           <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Cuenta de Terceros :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRNXN" size="21" maxlength="20" value="<%= appList.getRecord(9).trim()%>" > 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Identificacion Cuenta :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01CCRAFI" size="11" maxlength="10" value="<%= appList.getRecord(10).trim()%>" > 
 			</td>
          </tr>                        
           <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo de Razon:</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRRNS" size="5" maxlength="4" value="<%= appList.getRecord(11).trim()%>" > 
				<a href="javascript:GetCodeCreditCard('E01CCRRNS','07')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Codigo de Acceso :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01CCRACD" size="5" maxlength="4" value="<%= appList.getRecord(12).trim()%>" >
			  <a href="javascript:GetCodeCreditCard('E01CCRACD','08')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>               
 			</td>
          </tr>     
           <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Estado :</div>
            </td>
            <td nowrap width="23%"> 
              <select name="E01CCRIST">
                <option value=" " <% if (!(
		                	appList.getRecord(13).equals("T") || 
		                	appList.getRecord(13).equals("V") || 
                			appList.getRecord(13).equals("R") || 
                			appList.getRecord(13).equals("P") || 
                			appList.getRecord(13).equals("C") || 
                			appList.getRecord(13).equals("I") || 
                			appList.getRecord(13).equals("A"))) 
                			out.print("selected"); %> selected></option>
                <option value="R" <% if(appList.getRecord(13).equals("T")) out.print("selected");%>>Retenida</option>
                <option value="R" <% if(appList.getRecord(13).equals("V")) out.print("selected");%>>Vencida</option>                			
                <option value="R" <% if(appList.getRecord(13).equals("R")) out.print("selected");%>>Robada</option>
                <option value="P" <% if(appList.getRecord(13).equals("P")) out.print("selected");%>>Perdida</option>
                <option value="C" <% if(appList.getRecord(13).equals("C")) out.print("selected");%>>Cancelada</option>
                <option value="I" <% if(appList.getRecord(13).equals("I")) out.print("selected");%>>Inactiva</option>
                <option value="A" <% if(appList.getRecord(13).equals("A")) out.print("selected");%>>Activa</option>        
              </select>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Tipo de Tarjeta :</div>
            </td>
            <td nowrap width="27%"> 
              <select name="E01CCRTYP">
                <option value=" " <% if (!(appList.getRecord(14).equals("V") || appList.getRecord(14).equals("M")||appList.getRecord(14).equals("P"))) out.print("selected"); %> selected></option>
                <option value="V" <% if(appList.getRecord(14).equals("V")) out.print("selected");%>>Visa</option>
                <option value="M" <% if(appList.getRecord(14).equals("M")) out.print("selected");%>>Master Card</option>
                <option value="P" <% if(appList.getRecord(14).equals("P")) out.print("selected");%>>Private Label</option>
              </select>              
 			</td>
          </tr>           
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo de Plastico :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRPLA" size="5" maxlength="4" value="<%= appList.getRecord(15).trim()%>" > 
				<a href="javascript:GetCodeCreditCard('E01CCRPLA','02')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>               
            </td>
            <td nowrap width="25%"> 
              <div align="right">Fecha de Emision :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01CCRISM" size="2" maxlength="2" value="<%= appList.getRecord(2).trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01CCRISD" size="2" maxlength="2" value="<%= appList.getRecord(3).trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01CCRISY" size="2" maxlength="2" value="<%= appList.getRecord(4).trim()%>" onkeypress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E01CCRISM,document.forms[0].E01CCRISD,document.forms[0].E01CCRISY)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
  			</td>
          </tr>   
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha Expiracion:</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01CCREXM" size="2" maxlength="2" value="<%= appList.getRecord(5).trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01CCREXD" size="2" maxlength="2" value="<%= appList.getRecord(6).trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01CCREXY" size="2" maxlength="2" value="<%= appList.getRecord(7).trim()%>" onkeypress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E01CCREXM,document.forms[0].E01CCREXD,document.forms[0].E01CCREXY)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Transaccion Gratis :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" name="E01CCRNFT" size="3" maxlength="2" value="<%= appList.getRecord(16).trim()%>" >
  			</td>
          </tr>       
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Nombre :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRNAM" size="41" maxlength="40" value="<%= appList.getRecord(17).trim()%>" >
            </td>
            <td nowrap width="25%"><div align="right"></div></td>
            <td nowrap width="27%"></td>
          </tr>  
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRNA1" size="41" maxlength="40" value="<%= appList.getRecord(18).trim()%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">PIN Compensacion:</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" name="E01CCRPIO" size="17" maxlength="16" value="<%= appList.getRecord(19).trim()%>" >
				<a href="javascript:GetCode('E01CCRPIO','STATIC_pin_offset.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>               
  			</td>
          </tr>  
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo de Localidad:</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRUBC" size="5" maxlength="4" value="<%= appList.getRecord(20).trim()%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Codigo Calificacion :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" name="E01CCROBR" size="5" maxlength="4" value="<%= appList.getRecord(21).trim()%>" >
  			</td>
          </tr>   
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo de Renovacion:</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRRNC" size="5" maxlength="4" value="<%= appList.getRecord(22).trim()%>" >
				<a href="javascript:GetCodeCreditCard('E01CCRRNC','10')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Agencia de entrega :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" name="E01CCROFE" size="5" maxlength="4" value="<%= appList.getRecord(23).trim()%>" >
  			</td>
          </tr>     
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Tipo de Distribucion :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRTDI" size="3" maxlength="2" value="<%= appList.getRecord(24).trim()%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Codigo de distribucion :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" name="E01CCRCDI" size="5" maxlength="4" value="<%= appList.getRecord(25).trim()%>" >
  			</td>
          </tr>   
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo de Bloqueo :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRBKD" size="5" maxlength="4" value="<%= appList.getRecord(26).trim()%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Tipo de Acceso :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" name="E01CCRTAC" size="2" maxlength="1" value="<%= appList.getRecord(27).trim()%>" >
  			</td>
          </tr>        
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo de Afinidad :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRFIN" size="5" maxlength="4" value="<%= appList.getRecord(28).trim()%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Codigo Premium :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" name="E01CCRRWD" size="2" maxlength="1" value="<%= appList.getRecord(29).trim()%>" >
  			</td>
          </tr>                                                                                           
        </table>
      </td>
    </tr>
  </table>  
  <h4> Informacion de montos</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Limite fondos respaldo:</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRFBL" size="10" maxlength="9" value="<%= appList.getRecord(30).trim()%>" onkeypress="enterInteger()">
            </td>
            <td nowrap width="25%"> 
              <div align="right">Limite Sobregiro :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" name="E01CCROVL" size="10" maxlength="9" value="<%= appList.getRecord(31).trim()%>" onkeypress="enterInteger()">
  			</td>
          </tr>      
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Limite retiro efectivo :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRWHL" size="10" maxlength="9" value="<%= appList.getRecord(32).trim()%>" onkeypress="enterInteger()">
            </td>
            <td nowrap width="25%"> 
              <div align="right">Limite de Frecuencia :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" name="E01CCRLFR" size="3" maxlength="2" value="<%= appList.getRecord(33).trim()%>" >
  			</td>
          </tr>      
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Limite en Linea :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRONL" size="17" maxlength="16" value="<%= appList.getRecord(34).trim()%>" onkeypress="enterDecimal()">
            </td>
            <td nowrap width="25%"> 
              <div align="right">Disponible en Linea :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" name="E01CCROLD" size="17" maxlength="16" value="<%= appList.getRecord(35).trim()%>" onkeypress="enterDecimal()">
  			</td>
          </tr>    
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Limite fuera de linea:</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCROFL" size="17" maxlength="16" value="<%= appList.getRecord(36).trim()%>" onkeypress="enterDecimal()">
            </td>
            <td nowrap width="25%"> 
              <div align="right">Disponible fuera de Linea :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" name="E01CCROFD" size="17" maxlength="16" value="<%= appList.getRecord(37).trim()%>" onkeypress="enterDecimal()">
  			</td>
          </tr>      
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Limite retiros POS :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRPWL" size="10" maxlength="9" value="<%= appList.getRecord(41).trim()%>" onkeypress="enterInteger()">
            </td>
            <td nowrap width="25%"> 
              <div align="right">Limite de Pago  :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" name="E01CCRBPL" size="10" maxlength="9" value="<%= appList.getRecord(42).trim()%>" onkeypress="enterInteger()">
  			</td>
          </tr>   
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Limite de tarjeta :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRLIM" size="10" maxlength="9" value="<%= appList.getRecord(43).trim()%>" onkeypress="enterInteger()">
            </td>
            <td nowrap width="25%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="27%"> 

  			</td>
          </tr>                                                                                                                           
        </table>
      </td>
    </tr>
  </table>   
  <br>
  <div align="center"> 
	   <input id="EIBSBTN" type=submit name="Submit" value="Aceptar">
  </div>
  </form>
</body>
</html>

