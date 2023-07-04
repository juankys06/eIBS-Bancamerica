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

<jsp:useBean id="msgCard" class="datapro.eibs.beans.ECC006001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT LANGUAGE="javascript">

	builtNewMenu(cc_m_opt);
	
	builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
function setShowStatus(){
	if(document.forms[0].E01CCRIST.value == "A") {
		document.forms[0].E01CCRSTS.value = "";
		causalinput.style.visibility = "hidden";
		causallabel.style.visibility = "hidden";
	} else {
		causalinput.style.visibility = "visible";
		causallabel.style.visibility = "visible";
	}
}
   
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
<h3 align="center">Tarjeta de Credito Primaria <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cc_primary_card.jsp,ECC0010"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0010" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="6">
  <INPUT TYPE=HIDDEN NAME="E01CCRTCL" VALUE="P">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="16%"> 
              	<input type="text" name="E01TARTY2" size="15" maxlength="14" value="<% if (userPO.getHeader2().equals("D")) out.print("Debit Card"); else out.print("Credit Card"); %>" readonly>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Tipo de Tarjeta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01CCRPRIM" size="21" maxlength="20" value="Primaria" readonly>
              </div>
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
              <div align="right"><b>Numero de Tarjeta: </b></div>
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
  <h4> Informacion Basica</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
           <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Tipo de Tarjeta :</div>
            </td>
            <td nowrap width="27%"> 
              <select name="E01CCRTYP">
                <option value=" " <% if (!(msgCard.getE01CCRTYP().equals("V") ||msgCard.getE01CCRTYP().equals("M")||msgCard.getE01CCRTYP().equals("P"))) out.print("selected"); %> selected></option>
                <option value="V" <% if(msgCard.getE01CCRTYP().equals("V")) out.print("selected");%>>Visa</option>
                <option value="M" <% if(msgCard.getE01CCRTYP().equals("M")) out.print("selected");%>>Master Card</option>
                <option value="P" <% if(msgCard.getE01CCRTYP().equals("P")) out.print("selected");%>>Private Label</option>
              </select>              
 			</td>
            <td nowrap width="25%"> 
              <div align="right">Banco ABA :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRABA" size="11" maxlength="10" value="<%= msgCard.getE01CCRABA().trim()%>" onKeypress="enterInteger()"> 
            </td>
          </tr>   
           <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Estado :</div>
            </td>
            <td nowrap width="23%"> 
              <select name="E01CCRIST" onchange="setShowStatus()">
                <option value="I" <% if(msgCard.getE01CCRIST().equals("I")) out.print("selected");%>>Inactiva</option>
                <option value="A" <% if(msgCard.getE01CCRIST().equals("A")) out.print("selected");%>>Activa</option>
              </select>
				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" > 
            </td>
            <td nowrap width="25%"> 
              <div id="causallabel" style="visibility:hidden;" align="right">Causal de Estado :</div>
            </td>
            <td nowrap width="27%"> 
            	<div id="causalinput" style="visibility:hidden;">
				<input type="text" name="E01CCRSTS" size="5" maxlength="4" value="<%= msgCard.getE01CCRSTS().trim()%>"> 
				<a href="javascript:GetPlasticStatus('E01CCRSTS', '')">
				<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
				</div>
 			</td>
          </tr> 
           <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Numero de Referencia:</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRNXN" size="21" maxlength="20" value="<%= msgCard.getE01CCRNXN().trim()%>" > 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Identificacion de Cuenta :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01CCRAFI" size="11" maxlength="10" value="<%= msgCard.getE01CCRAFI().trim()%>" > 
 			</td>
          </tr>                        
           <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo de Razon:</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRRNS" size="5" maxlength="4" value="<%= msgCard.getE01CCRRNS().trim()%>" > 
				<a href="javascript:GetCodeCreditCard('E01CCRRNS','07')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Codigo de Acceso :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01CCRACD" size="5" maxlength="4" value="<%= msgCard.getE01CCRACD().trim()%>" >
			  <a href="javascript:GetCodeCreditCard('E01CCRACD','08')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>               
 			</td>
          </tr>     
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo de Plastico :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRPLA" size="5" maxlength="4" value="<%= msgCard.getE01CCRPLA().trim()%>" > 
				<a href="javascript:GetCodeCreditCard('E01CCRPLA','02')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>               
            </td>
            <td nowrap width="25%"> 
              <div align="right">Fecha de Emision :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" readonly name="E01CCRISD" size="3" maxlength="2" value="<%= msgCard.getE01CCRISD().trim()%>" onkeypress="enterInteger()">
              <input type="text" readonly name="E01CCRISM" size="3" maxlength="2" value="<%= msgCard.getE01CCRISM().trim()%>" onkeypress="enterInteger()">
              <input type="text" readonly name="E01CCRISY" size="5" maxlength="4" value="<%= msgCard.getE01CCRISY().trim()%>" onkeypress="enterInteger()">
  			</td>
          </tr>   
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha de Expiracion:</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" readonly name="E01CCREXD" size="3" maxlength="2" value="<%= msgCard.getE01CCREXD().trim()%>" onkeypress="enterInteger()">
              <input type="text" readonly name="E01CCREXM" size="3" maxlength="2" value="<%= msgCard.getE01CCREXM().trim()%>" onkeypress="enterInteger()">
              <input type="text" readonly name="E01CCREXY" size="5" maxlength="4" value="<%= msgCard.getE01CCREXY().trim()%>" onkeypress="enterInteger()">
            </td>
            <td nowrap width="25%"> 
              <div align="right">Transacciones Gratis :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" name="E01CCRNFT" size="3" maxlength="2" value="<%= msgCard.getE01CCRNFT().trim()%>" >
  			</td>
          </tr>       
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Nombre :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRNAM" size="41" maxlength="40" value="<%= msgCard.getE01CCRNAM().trim()%>" >
            </td>
            <td nowrap width="25%"><div align="right"></div></td>
            <td nowrap width="27%"></td>
          </tr>  
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRNA1" size="41" maxlength="40" value="<%= msgCard.getE01CCRNA1().trim()%>" >
            </td>
            <td nowrap width="25%"> 
              
            </td>
            <td nowrap width="27%">               
  			</td>
          </tr>  
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo de Localidad:</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRUBC" size="5" maxlength="4" value="<%= msgCard.getE01CCRUBC().trim()%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Codigo Calificacion :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" name="E01CCROBR" size="5" maxlength="4" value="<%= msgCard.getE01CCROBR().trim()%>" >
  			</td>
          </tr>   
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo de Renovacion:</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRRNC" size="5" maxlength="4" value="<%= msgCard.getE01CCRRNC().trim()%>" >
				<a href="javascript:GetCodeCreditCard('E01CCRRNC','10')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Agencia de Entrega :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" name="E01CCROFE" size="5" maxlength="4" value="<%= msgCard.getE01CCROFE().trim()%>" >
				<a href="javascript:GetBranch('E01CCROFE','')">
				<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a>
				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" > 
  			</td>
          </tr>     
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Tipo de Distribucion :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRTDI" size="3" maxlength="2" value="<%= msgCard.getE01CCRTDI().trim()%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Codigo de Distribucion:</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" name="E01CCRCDI" size="5" maxlength="4" value="<%= msgCard.getE01CCRCDI().trim()%>" >
  			</td>
          </tr>   
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo de Bloqueo :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRBKD" size="5" maxlength="4" value="<%= msgCard.getE01CCRBKD().trim()%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Tipo de Acceso :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" name="E01CCRTAC" size="2" maxlength="1" value="<%= msgCard.getE01CCRTAC().trim()%>" >
  			</td>
          </tr>        
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo de Afinidad :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCRFIN" size="5" maxlength="4" value="<%= msgCard.getE01CCRFIN().trim()%>" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Codigo Premium :</div>
            </td>
            <td nowrap width="27%"> 
            	<input type="text" name="E01CCRRWD" size="2" maxlength="1" value="<%= msgCard.getE01CCRRWD().trim()%>" >
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
