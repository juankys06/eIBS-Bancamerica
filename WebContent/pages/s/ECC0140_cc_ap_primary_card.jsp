<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Primary Credit Card</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id="ccPrim" class="datapro.eibs.beans.ECC006001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT LANGUAGE="javascript">

	builtNewMenu(cc_a_opt);
   
</SCRIPT>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 
  out.println("<SCRIPT> initMenu();  </SCRIPT>");

%> 
</head>
<body>
<h3 align="center">Tarjeta de credito primaria<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cc_ap_primary_card.jsp, ECC0140"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0010I" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="6">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Tipo de tarjeta : </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01CCRPRIM" size="21" maxlength="20" value="Primaria" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="16%"> 
              	<input type="text" name="E01TARTYP" size="15" maxlength="14" value="<% if (userPO.getHeader2().equals("D")) out.print("Tarjeta de Débito"); else out.print("Tarjeta de Crédito"); %>" readonly>
            </td>
          </tr>                
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente : </b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01PRICUN" size="9" maxlength="9" readonly value="<%= userPO.getCusNum().trim()%>">
			  </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre : </b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01PRINA1" size="45" maxlength="45" readonly value="<%= userPO.getCusName().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta : </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" readonly name="E01CCRCRA" size="12" maxlength="9" value="<%= userPO.getAccNum().trim() %>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Numero de tarjeta : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" readonly name="E01CCRNUM" size="21" maxlength="20" value="<%= userPO.getHeader1().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4> Informacion de tarjeta primaria</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
           <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Banco ABA :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" readonly name="E01CCRABA" size="11" maxlength="10" value="<%= ccPrim.getE01CCRABA().trim()%>" > 
            </td>
            <td nowrap width="25%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="27%"> </td>
          </tr>   
           <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Cuenta de terceros :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" readonly name="E01CCRNXN" size="21" maxlength="20" value="<%= ccPrim.getE01CCRNXN().trim()%>" > 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Identificacion de cuenta :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" readonly name="E01CCRAFI" size="11" maxlength="10" value="<%= ccPrim.getE01CCRAFI().trim()%>" > 
 			</td>
          </tr>                        
           <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo de razon :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" readonly name="E01CCRRNS" size="5" maxlength="4" value="<%= ccPrim.getE01CCRRNS().trim()%>" > 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Codigo de acceso :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" readonly name="E01CCRACD" size="5" maxlength="4" value="<%= ccPrim.getE01CCRACD().trim()%>" >
 			</td>
          </tr>     
           <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Estado :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" readonly name="E01CCRIST" size="21" maxlength="20" 
              value="<% 
	           	if 		(ccPrim.getE01CCRIST().equals("T")) out.print("Retenida");
				else if	(ccPrim.getE01CCRIST().equals("V")) out.print("Vencida");
				else if	(ccPrim.getE01CCRIST().equals("R")) out.print("Robada");
				else if	(ccPrim.getE01CCRIST().equals("P")) out.print("Perdida");
				else if	(ccPrim.getE01CCRIST().equals("C")) out.print("Cancelada");
				else if	(ccPrim.getE01CCRIST().equals("I")) out.print("Inactiva");
				else if	(ccPrim.getE01CCRIST().equals("A")) out.print("Activa");
              else out.print("");%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Tipo de tarjeta :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" readonly name="E01CCRTYP" size="21" maxlength="20" value="<% if(ccPrim.getE01CCRTYP().equals("V")) out.print("Visa");
              else if(ccPrim.getE01CCRTYP().equals("M")) out.print("Master Card");
              else if(ccPrim.getE01CCRTYP().equals("P")) out.print("Private Label");
              else out.print("");%>" >        
 			</td>
          </tr>           
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo de plastico :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" readonly name="E01CCRPLA" size="5" maxlength="4" value="<%= ccPrim.getE01CCRPLA().trim()%>" > 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Fecha de emision :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" readonly name="E01CCRISM" size="2" maxlength="2" value="<%= ccPrim.getE01CCRISM().trim()%>" >
              <input type="text" readonly name="E01CCRISD" size="2" maxlength="2" value="<%= ccPrim.getE01CCRISD().trim()%>" >
              <input type="text" readonly name="E01CCRISY" size="2" maxlength="2" value="<%= ccPrim.getE01CCRISY().trim()%>" >
  			</td>
          </tr>   
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha de expiracion :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" readonly name="E01CCREXM" size="2" maxlength="2" value="<%= ccPrim.getE01CCREXM().trim()%>" >
              <input type="text" readonly name="E01CCREXD" size="2" maxlength="2" value="<%= ccPrim.getE01CCREXD().trim()%>" >
              <input type="text" readonly name="E01CCREXY" size="2" maxlength="2" value="<%= ccPrim.getE01CCREXY().trim()%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Transaccion gratis :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" readonly name="E01CCRNFT" size="3" maxlength="2" value="<%= ccPrim.getE01CCRNFT().trim()%>" >
  			</td>
          </tr>       
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Nombre :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" readonly name="E01CCRNAM" size="41" maxlength="40" value="<%= ccPrim.getE01CCRNAM().trim()%>" >
            </td>
            <td nowrap width="25%"><div align="right"></div></td>
            <td nowrap width="27%"></td>
          </tr>  
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" readonly name="E01CCRNA1" size="41" maxlength="40" value="<%= ccPrim.getE01CCRNA1().trim()%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">PIN compensacion :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" readonly name="E01CCRPIO" size="17" maxlength="16" value="<%= ccPrim.getE01CCRPIO().trim()%>" >
  			</td>
          </tr>  
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo de localidad :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" readonly name="E01CCRUBC" size="5" maxlength="4" value="<%= ccPrim.getE01CCRUBC().trim()%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Codigo de calificacion :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" readonly name="E01CCROBR" size="5" maxlength="4" value="<%= ccPrim.getE01CCROBR().trim()%>" >
  			</td>
          </tr>   
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right"> Codigo de renovacion :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" readonly name="E01CCRRNC" size="5" maxlength="4" value="<%= ccPrim.getE01CCRRNC().trim()%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Agencia de entrega :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" readonly name="E01CCROFE" size="5" maxlength="4" value="<%= ccPrim.getE01CCROFE().trim()%>" >
  			</td>
          </tr>     
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Tipo de distribucion :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" readonly name="E01CCRTDI" size="3" maxlength="2" value="<%= ccPrim.getE01CCRTDI().trim()%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Codigo de distribucion :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" readonly name="E01CCRCDI" size="5" maxlength="4" value="<%= ccPrim.getE01CCRCDI().trim()%>" >
  			</td>
          </tr>   
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo de bloqueo :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" readonly name="E01CCRBKD" size="5" maxlength="4" value="<%= ccPrim.getE01CCRBKD().trim()%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Tipo de acceso :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" readonly name="E01CCRTAC" size="2" maxlength="1" value="<%= ccPrim.getE01CCRTAC().trim()%>" >
  			</td>
          </tr>        
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo de afinidad :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" readonly name="E01CCRFIN" size="5" maxlength="4" value="<%= ccPrim.getE01CCRFIN().trim()%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Codigo Premium  :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" readonly name="E01CCRRWD" size="2" maxlength="1" value="<%= ccPrim.getE01CCRRWD().trim()%>" >
  			</td>
          </tr>                                                                                           
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo de privilegio :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" readonly name="E01CCRPRV" size="5" maxlength="4" value="<%= ccPrim.getE01CCRPRV().trim()%>" >
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
  <h4> Informacion de montos de tarjeta primaria</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Limite fondos de reserva :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" readonly name="E01CCRFBL" size="17" maxlength="16" value="<%= ccPrim.getE01CCRFBL().trim()%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Limite sobregiro :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" readonly name="E01CCROVL" size="17" maxlength="16" value="<%= ccPrim.getE01CCROVL().trim()%>" >
  			</td>
          </tr>      
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Limite de retiros :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" readonly name="E01CCRWHL" size="17" maxlength="16" value="<%= ccPrim.getE01CCRWHL().trim()%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Limite de Frecuencia :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" readonly name="E01CCRLFR" size="3" maxlength="2" value="<%= ccPrim.getE01CCRLFR().trim()%>" >
  			</td>
          </tr>      
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Limite en linea :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" readonly name="E01CCRONL" size="17" maxlength="16" value="<%= ccPrim.getE01CCRONL().trim()%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Disponible en linea :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" readonly name="E01CCROLD" size="17" maxlength="16" value="<%= ccPrim.getE01CCROLD().trim()%>" >
  			</td>
          </tr>    
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Limite fuera de linea :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" readonly name="E01CCROFL" size="17" maxlength="16" value="<%= ccPrim.getE01CCROFL().trim()%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Disponible fuera de linea :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" readonly name="E01CCROFD" size="17" maxlength="16" value="<%= ccPrim.getE01CCROFD().trim()%>" >
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

