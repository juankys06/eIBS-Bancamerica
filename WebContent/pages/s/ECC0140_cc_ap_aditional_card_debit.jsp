<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 
<html>
<head>
<title>Aditional Card</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "msgCC" class= "datapro.eibs.beans.ECC006001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>
<body>
<h3 align="center">Tarjeta de Débito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cc_ap_aditional_card_debit.jsp,ECC0140"> 
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
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Tipo de Tarjeta : </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01CCRPRIM" size="21" maxlength="20" value="Secundaria" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="16%"> 
              	<input type="text" name="E01TARTY2" size="15" maxlength="14" value="Tarjeta de Debito" readonly>
            </td>
          </tr>    
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente primario : </b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01PRICUN" size="9" maxlength="9" readonly value="<%=  userPO.getHeader6().trim()%>">
			  </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre cliente primario : </b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01PRINA1" size="45" maxlength="45" readonly value="<%= userPO.getHeader7().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta : </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" readonly name="E01CCRCRA" size="12" maxlength="9" value="<%= userPO.getHeader5().trim() %>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Numero de tarjeta : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" readonly name="E01CCRNUM" size="21" maxlength="20" value="<%= userPO.getHeader8().trim()%>">
                </b> </div>
            </td>
          </tr>    
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente secundario : </b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" readonly name="E01SECCUN" size="9" maxlength="9" value="<%= msgCC.getE01SECCUN().trim()%>">
                
			  </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre cliente secundario : </b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01SECNA1" size="45" maxlength="45" readonly value="<%= msgCC.getE01SECNA1().trim()%>">
              </div>
            </td>
          </tr>            
        </table>
      </td>
    </tr>
  </table>
  <h4> Informacion basica</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
           <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Tipo de tarjeta :</div>
            </td>
            <td nowrap width="27%"> 
	           <input type="text" readonly name="E01CCRTYP" size="31" maxlength="30" 
	           	value="<% if (msgCC.getE01CCRTYP().equals("V")) out.print("Visa");
	           	else if (msgCC.getE01CCRTYP().equals("M")) out.print("Master Card");
	           	else if (msgCC.getE01CCRTYP().equals("P")) out.print("Private Label");
	           	else out.print("");	%>" >	                          
 			</td>
            <td nowrap width="25%"> 
              <div align="right">Banco ABA :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text"  readonly name="E01CCRABA" size="11" maxlength="10" value="<%= msgCC.getE01CCRABA().trim()%>" > 
            </td>
          </tr>   
           <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Estado :</div>
            </td>
            <td nowrap width="23%"> 
	           <input type="text" readonly name="E01CCRIST" size="31" maxlength="30" 
	           	value="<% 
				if		(msgCC.getE01CCRIST().equals("I")) out.print("Inactiva");
				else if	(msgCC.getE01CCRIST().equals("A")) out.print("Activa");
	           	else out.print("");	%>" >	
	        </td>
            <td nowrap width="25%"> 
              <div align="right">Causal de Estado :</div>
            </td>
            <td nowrap width="27%"> 
				<input type="text" name="E01CCRDSC" size="17" maxlength="15" value="<%= msgCC.getE01CCRDSC().trim()%>" readonly>
 			</td>
          </tr> 
           <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Cuenta a terceros :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" readonly name="E01CCRNXN" size="21" maxlength="20" value="<%= msgCC.getE01CCRNXN().trim()%>" > 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Identificacion de cuenta :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" readonly name="E01CCRAFI" size="11" maxlength="10" value="<%= msgCC.getE01CCRAFI().trim()%>" > 
 			</td>
          </tr>                        
           <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo de razon :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" readonly  name="E01CCRRNS" size="5" maxlength="4" value="<%= msgCC.getE01CCRRNS().trim()%>" > 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Codigo de acceso :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" readonly name="E01CCRACD" size="5" maxlength="4" value="<%= msgCC.getE01CCRACD().trim()%>" >
 			</td>
          </tr>     
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo de plastico :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" readonly name="E01CCRPLA" size="5" maxlength="4" value="<%= msgCC.getE01CCRPLA().trim()%>" > 
			
            </td>
            <td nowrap width="25%"> 
              <div align="right">Fecha de emision :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" readonly name="E01CCRISD" size="3" maxlength="2" value="<%= msgCC.getE01CCRISD().trim()%>" >
              <input type="text" readonly name="E01CCRISM" size="3" maxlength="2" value="<%= msgCC.getE01CCRISM().trim()%>" >
              <input type="text" readonly name="E01CCRISY" size="5" maxlength="4" value="<%= msgCC.getE01CCRISY().trim()%>" >

  			</td>
          </tr>   
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha de expiracion :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" readonly name="E01CCREXD" size="3" maxlength="2" value="<%= msgCC.getE01CCREXD().trim()%>" >
              <input type="text" readonly name="E01CCREXM" size="3" maxlength="2" value="<%= msgCC.getE01CCREXM().trim()%>" >
              <input type="text" readonly name="E01CCREXY" size="5" maxlength="4" value="<%= msgCC.getE01CCREXY().trim()%>" >

            </td>
            <td nowrap width="25%"> 
              <div align="right">Transaccion gratis :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" readonly name="E01CCRNFT" size="3" maxlength="2" value="<%= msgCC.getE01CCRNFT().trim()%>" >
  			</td>
          </tr>       
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Nombre :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" readonly name="E01CCRNAM" size="41" maxlength="40" value="<%= msgCC.getE01CCRNAM().trim()%>" >
            </td>
            <td nowrap width="25%"><div align="right"></div></td>
            <td nowrap width="27%"></td>
          </tr>  
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" readonly name="E01CCRNA1" size="41" maxlength="40" value="<%= msgCC.getE01CCRNA1().trim()%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="27%"></td>
          </tr> 
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo de localidad :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" readonly name="E01CCRUBC" size="5" maxlength="4" value="<%= msgCC.getE01CCRUBC().trim()%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Codigo de calificacion :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" readonly name="E01CCROBR" size="5" maxlength="4" value="<%= msgCC.getE01CCROBR().trim()%>" >
  			</td>
          </tr>   
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo de renovacion :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" readonly name="E01CCRRNC" size="5" maxlength="4" value="<%= msgCC.getE01CCRRNC().trim()%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Agencia de entrega :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" readonly name="E01CCROFE" size="5" maxlength="4" value="<%= msgCC.getE01CCROFE().trim()%>" >
  			</td>
          </tr>     
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Tipo de distribucion :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" readonly name="E01CCRTDI" size="3" maxlength="2" value="<%= msgCC.getE01CCRTDI().trim()%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Codigo de distribucion :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" readonly name="E01CCRCDI" size="5" maxlength="4" value="<%= msgCC.getE01CCRCDI().trim()%>" >
  			</td>
          </tr>   
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo de bloqueo :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" readonly name="E01CCRBKD" size="5" maxlength="4" value="<%= msgCC.getE01CCRBKD().trim()%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Tipo de acceso :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" readonly name="E01CCRTAC" size="2" maxlength="1" value="<%= msgCC.getE01CCRTAC().trim()%>" >
  			</td>
          </tr>        
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo de afinidad :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" readonly name="E01CCRFIN" size="5" maxlength="4" value="<%= msgCC.getE01CCRFIN().trim()%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Codigo Premium :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" readonly name="E01CCRRWD" size="2" maxlength="1" value="<%= msgCC.getE01CCRRWD().trim()%>" >
  			</td>
          </tr>                                                                                           
        </table>
      </td>
    </tr>
  </table>  
  <br>
  </form>
</body>
</html>

