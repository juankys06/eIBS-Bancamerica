<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Placement of Collaterals</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id="gaBasic" class="datapro.eibs.beans.ERA000501Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


<SCRIPT LANGUAGE="javascript">
	builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   

 function getAmount(num)
  {
   if (num == 1) {
    if (trim(document.forms[0].E01OFFAM1.value)=="0.00" || trim(document.forms[0].E01OFFAM1.value)=="") {
     document.forms[0].E01OFFAM1.value = document.forms[0].E01DEAOAM.value;}
  }
}

function showCollaterals(){
  CenterWindow('<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0005P?SCREEN=3&NUMACC=<%= userPO.getHeader20()%>',600,500,4);
}

function showAssets(){
  CenterWindow('<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0005P?SCREEN=5&NUMACC=<%= userPO.getHeader20()%>',600,500,4);
}

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
<h3 align="center"> 

		<%
			if (gaBasic.getE01RCLINDE().equals("5")) {
		%>
		 	   Pignoracion		  			 
		<%
			}
			else {
		%>
			    Despignoracion
		<%
			}		 
		%>

				de Garantias a Cuentas del Cliente<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ga_basic_pigdes.jsp, ERA0005"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0005P" >
  <p>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
    <INPUT TYPE=HIDDEN NAME="E01RCLTIPO" VALUE="<%= gaBasic.getE01RCLTIPO()%>">
    <INPUT TYPE=HIDDEN NAME="E01RCLAACD" VALUE="<%= gaBasic.getE01RCLAACD()%>">
    <INPUT TYPE=HIDDEN NAME="E01RCLATYP" VALUE="<%= gaBasic.getE01RCLATYP()%>">
    <INPUT TYPE=HIDDEN NAME="E01RCLABNK" VALUE="<%= gaBasic.getE01RCLABNK()%>">
  </p>
  <h4>Operaciones a Garantizar</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right">Cliente<b> :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01RCLACUN" size="9" maxlength="9" value="<%= gaBasic.getE01RCLACUN()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Nombre :</div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01CUSNA1A" size="45" maxlength="45" value="<%= gaBasic.getE01CUSNA1A()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="16%" height="17"> 
              <div align="right">Cuenta : </div>
            </td>
            <td nowrap width="20%" height="17"> 
              <input type="text" name="E01RCLAACC" size="10" maxlength="9" value="<%= gaBasic.getE01RCLAACC()%>" onKeyPress="enterDecimal()" readonly>
            </td>
            <td nowrap width="16%" height="17"> 
              <div align="right">Moneda :</div>
            </td>
            <td nowrap width="16%" height="17">
              <input type="text" name="E01RCLACCY" size="3" maxlength="3" value="<%= gaBasic.getE01RCLACCY()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Tipo de Producto :</div>
            </td>
            <td nowrap width="20%">
              <input type="text" name="E01RCLATYP0" size="6" maxlength="4" readonly value="<%= gaBasic.getE01RCLATYP()%>">
            </td>
            <td nowrap width="16%"> 
              <div align="right">Monto Original :</div>
            </td>
            <td nowrap width="16%">
              <input type="text" name="E01RCLAORA1" size="25" maxlength="20" value="<%= gaBasic.getE01RCLAORA()%>" onKeyPress="enterDecimal()" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%"> 
              <div align="right">Fecha de Apertura:</div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E01RCLAOD1" size="2" maxlength="2" value="<%= gaBasic.getE01RCLAOD1()%>" onKeyPress="enterInteger()" readonly>
                <input type="text" name="E01RCLAOD2" size="2" maxlength="2" value="<%= gaBasic.getE01RCLAOD2()%>" onKeyPress="enterInteger()" readonly>
                <input type="text" name="E01RCLAOD3" size="2" maxlength="2" value="<%= gaBasic.getE01RCLAOD3()%>" onKeyPress="enterInteger()" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right">Monto Actual :</div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"> 
                <input type="text" name="E01RCLABAL" size="25" maxlength="20" value="<%= gaBasic.getE01RCLABAL()%>" onKeyPress="enterDecimal()" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap width="20%">
              <input type="text" name="E01RCLAMD1" size="2" maxlength="2" value="<%= gaBasic.getE01RCLAMD1()%>" onKeyPress="enterInteger()" readonly>
              <input type="text" name="E01RCLAMD2" size="2" maxlength="2" value="<%= gaBasic.getE01RCLAMD2()%>" onKeyPress="enterInteger()" readonly>
              <input type="text" name="E01RCLAMD3" size="2" maxlength="2" value="<%= gaBasic.getE01RCLAMD3()%>" onKeyPress="enterInteger()" readonly>
            </td>
            <td nowrap width="16%"> 
              <div align="right">Monto Garantizado :</div>
            </td>
            <td nowrap width="16%"> 
              <input type="text" name="E01RCLAGAN" size="25" maxlength="20" value="<%= gaBasic.getE01RCLAGAN()%>" onKeyPress="enterDecimal()" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="20%">&nbsp; </td>
            <td nowrap width="16%"> 
              <div align="right">Monto Pendiente :</div>
            </td>
            <td nowrap width="16%"> 
              <input type="text" name="E01RCLAAVL" size="25" maxlength="20" value="<%= gaBasic.getE01RCLAAVL()%>" onKeyPress="enterDecimal()" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Asignación de Garantía</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="20%"> 
              <div align="right">Número de Garantía :</div>
            </td>
            <td nowrap width="30%"> 
              <input type="text" name="E01RCLBACC" size="12" maxlength="12" value="<%= gaBasic.getE01RCLBACC()%>">
              <a href="javascript:GetAccount('E01RCLBACC','','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
            </td>
            <td nowrap width="22%"> 
              <div align="right">Tipo de Cuenta:</div>
            </td>
            <td nowrap width="7%"> 
              <input type="text" name="E01RCLBTYP" size="6" maxlength="4" value="<%= gaBasic.getE01RCLBTYP()%>" readonly >
            </td>
            <td nowrap width="9%"> 
              <div align="right">Estado :</div>
            </td>
            <td nowrap width="12%"> 
              <input type="text" name="E01RCLBSTA" size="10" maxlength="9" value="<%= gaBasic.getE01RCLBSTA()%>" onKeyPress="enterDecimal()" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" height="37"> 
              <div align="right">Cliente :</div>
            </td>
            <td nowrap width="30%" height="37"> 
              <input type="text" name="E01RCLBCUN" size="9" maxlength="9" value="<%= gaBasic.getE01RCLBCUN()%>" readonly>
            </td>
            <td nowrap width="22%" height="37"> 
              <div align="right">Nombre :</div>
            </td>
            <td nowrap height="37" colspan="3"> 
              <div align="left"> 
                <input type="text" name="E01CUSNA1B" size="45" maxlength="45" value="<%= gaBasic.getE01CUSNA1B()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%" > 
              <div align="right">Monto :</div>
            </td>
            <td nowrap width="30%" > 
              <input type="text" name="E01RCLBOPE" size="25" maxlength="20" value="<%= gaBasic.getE01RCLBOPE()%>" onKeyPress="enterDecimal()">
              <INPUT TYPE=HIDDEN NAME="E01RCLINDE" VALUE="<%= gaBasic.getE01RCLINDE()%>">

              <input type="radio" name="CE01RCLINDE" value="5" onClick="document.forms[0].E01RCLINDE.value='5'"
			  <%if(gaBasic.getE01RCLINDE().equals("5")) out.print("checked");%> readonly disabled>             
			   Aumento
			    
			 <input type="radio" name="CE01RCLINDE" value="0" onClick="document.forms[0].E01RCLINDE.value='0'"
			  <%if(gaBasic.getE01RCLINDE().equals("0")) out.print("checked");%> readonly disabled>
			   Disminución </td>
			   
            <td nowrap width="22%" > 
              <div align="right">Monto de la Garantía:</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01RCLBALT" size="25" maxlength="20" value="<%= gaBasic.getE01RCLBALT()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" > 
              <div align="right">Vencimiento Garantía :</div>
            </td>
            <td nowrap width="30%" > 
              <input type="text" name="E01RCLEXP1" size="2" maxlength="2" value="<%= gaBasic.getE01RCLEXP1()%>" onkeypress="enterInteger()">
              <input type="text" name="E01RCLEXP2" size="2" maxlength="2" value="<%= gaBasic.getE01RCLEXP2()%>" onKeyPress="enterInteger()">
              <input type="text" name="E01RCLEXP3" size="2" maxlength="2" value="<%= gaBasic.getE01RCLEXP3()%>" onKeyPress="enterInteger()">
            </td>
            <td nowrap width="22%" > 
              <div align="right">Monto no Elegible :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01RCLBEAM" size="25" maxlength="20" value="<%= gaBasic.getE01RCLBEAM()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%" > 
              <div align="right">Código de Moneda :</div>
            </td>
            <td nowrap width="30%" > 
              <input type="text" name="E01RCLBCCY" size="4" maxlength="3" value="<%= gaBasic.getE01RCLBCCY()%>" onKeyPress="enterDecimal()" readonly>
            </td>
            <td nowrap width="22%" > 
              <div align="right">Monto Dado en Garantía :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01RCLBAUS" size="25" maxlength="20" readonly value="<%= gaBasic.getE01RCLBAUS()%>" onkeypress="enterDecimal()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" > 
              <div align="right">Tasa de Cambio :</div>
            </td>
            <td nowrap width="30%" > 
              <input type="text" name="E01RCLEXR " size="11" maxlength="9" value="<%= gaBasic.getE01RCLEXR ()%>" readonly>
            </td>
            <td nowrap width="22%" > 
              <div align="right">Monto Disponible :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01RCLBAVL" size="25" maxlength="20"  value="<%= gaBasic.getE01RCLBAVL()%>" onkeypress="enterInteger()" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%" > 
              <div align="right">Comentarios :</div>
            </td>
            <td nowrap width="30%" > 
              <input type="text" name="E01RCLSP1" size="45" maxlength="35" value="<%= gaBasic.getE01RCLSP1()%>" >
            </td>
            <td nowrap width="22%" > 
              <div align="right">Monto de esta Operación :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01RCLBOPE" size="25" maxlength="20" value="<%= gaBasic.getE01RCLBOPE()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="30%" > 
              <input type="text" name="E01RCLSP2" size="45" maxlength="35" value="<%= gaBasic.getE01RCLSP2()%>" >
            </td>
            <td nowrap width="22%" > 
              <div align="right">Saldo Final a Garantizar :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01RCLBFIN" size="15" maxlength="13" value="<%= gaBasic.getE01RCLBFIN()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4> 
 
  </h4>
  <p align="center">&nbsp; </p>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"> 
          <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
        </div>
      </td>
    </tr>
  </table>
  
  </form>
</body>
</html>
