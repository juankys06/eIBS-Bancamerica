<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Consulta de Saldos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="Microsoft FrontPage 4.0">

<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</head>

<jsp:useBean id="rtBasic" class="datapro.eibs.beans.ECH030501Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<jsp:useBean id="rtBasic_1" class="datapro.eibs.beans.EDD000001Message"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) 
 {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }


%> 
<H3 align="center">Solicitud de Chequeras <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ECH0000_rt_Solicitud.jsp, ECH0000"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH0000" onsubmit="return(valida_contenido())">
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="30">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01CHPCUN" size="9" maxlength="9" value="<%= rtBasic.getE01CHPCUN().trim()%>" readonly>
			</div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01CHPNA1" size="45" maxlength="45" readonly value="<%= rtBasic.getE01CHPNA1().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01CHPACC" size="12" maxlength="12" value="<%= rtBasic.getE01CHPACC().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01CHPCCY" size="3" maxlength="3" value="<%= rtBasic.getE01CHPCCY().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01CHPPRO" size="4" maxlength="4" readonly value="<%= rtBasic.getE01CHPPRO().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>

      </td>
    </tr>
  </table>
  <h4>Información relevante</h4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Ultimo Pedido :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01CHPNTC" size="15" maxlength="15" value="<%= rtBasic.getE01CHPNTC().trim()%>" readonly>
            </td>
            <td nowrap> 
              <div align="right">Saldo Contable :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01CHMMGB" readonly value="<%= rtBasic.getE01CHMMGB().trim()%>" size="15" maxlength="15">
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Stock Mínimo :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01CHPMSK" size="15" maxlength="15" value="<%= rtBasic.getE01CHPMSK().trim()%>" readonly>
            </td>
            <td nowrap> 
              <div align="right">Saldo Disponible :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01CHMMNB" size="15" maxlength="15" value="<%= rtBasic.getE01CHMMNB().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Stock Actual :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01CHPASK" size="15" maxlength="15" value="<%= rtBasic.getE01CHPASK().trim()%>" readonly>
            </td>
            <td nowrap> 
              <div align="right">Fecha Sobregiro :</div>
            </td>
            <td nowrap>
              <input type="text" maxlength="2" size="2" name="E01LSTOD1" value="<%= rtBasic.getE01LSTOD1().trim()%>" readonly>
              <input type="text" maxlength="2" size="2" name="E01LSTOD2" value="<%= rtBasic.getE01LSTOD2().trim()%>" readonly>
              <input type="text" maxlength="2" size="2" name="E01LSTOD3" value="<%= rtBasic.getE01LSTOD3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Con Cobro :</div>
            </td>
            <td nowrap> 
            	<SELECT name="E01CHPCBC" disabled>
                            <OPTION value="Y" <% if (rtBasic.getE01CHPCBC().equals("S")) out.print("selected"); %>="" selected>SI</OPTION>
                            <OPTION value="N" <% if (rtBasic.getE01CHPCBC().equals("N")) out.print("selected"); %>="">NO</OPTION>
                        </SELECT></td>
            <td nowrap> 
              <div align="right">Fecha Apertura :</div>
            </td>
            <td nowrap>
              <input type="text" maxlength="2" size="2" name="E01OPNDT1" value="<%= rtBasic.getE01OPNDT1().trim()%>" readonly>
              <input type="text" maxlength="2" size="2" name="E01OPNDT2" value="<%= rtBasic.getE01OPNDT2().trim()%>" readonly>
              <input type="text" maxlength="2" size="2" name="E01OPNDT3" value="<%= rtBasic.getE01OPNDT3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Tipo Chequera :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01CHPTCB" size="3" maxlength="2" value="<%= rtBasic.getE01CHPTCB().trim()%>">
				<A href="javascript:GetTypCHK('E01CHPTCB','','<%= rtBasic_1.getE01ACMATY().trim()%>','<%= rtBasic_1.getE01ACMCCY().trim()%>')">
				<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0"></A> 
              
              
            </td>
            <td nowrap>
              <div align="right">Veces Sobregiro :</div>
            </td>
            <td nowrap>
              <input type="text" name="E01CHMTNS" size="15" maxlength="15" value="<%= rtBasic.getE01CHMTNS().trim()%>" readonly>
             </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Fecha Solicitud :</div>
            </td>
            <td nowrap>
              <input type="text" maxlength="2" size="2" name="E01CHPDT1" value="<%= rtBasic.getE01CHPDT1().trim()%>" readonly>
              <input type="text" maxlength="2" size="2" name="E01CHPDT2" value="<%= rtBasic.getE01CHPDT2().trim()%>" readonly>
              <input type="text" maxlength="2" size="2" name="E01CHPDT3" value="<%= rtBasic.getE01CHPDT3().trim()%>" readonly>
            </td>
            <td nowrap>
              <div align="right">Monto Ultimo Depósito :</div>
            </td>
            <td nowrap>
              <input type="text" name="E01CHMLDA" size="15" maxlength="15" value="<%= rtBasic.getE01CHMLDA().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>Pedido</H4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Cantidad de Chequeras</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01CHMNCH" size="15" maxlength="15" value="<%= rtBasic.getE01CHMNCH().trim()%>">
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap>
              <div align="right">Se envia a imprenta</div>
            </td>
            <td nowrap>
                <select name="E01CHMENV">
                   <OPTION value="Y" <% if (!(rtBasic.getE01CHMENV().equals("N") || rtBasic.getE01CHMENV().equals("1"))) out.print("selected"); %>>SI - Ciclo Corto</OPTION>
                   <OPTION value="N" <% if (rtBasic.getE01CHMENV().equals("N")) out.print("selected"); %>>NO</OPTION>
                   <OPTION value="1" <% if (rtBasic.getE01CHMENV().equals("1")) out.print("selected"); %>>SI - Ciclo Largo</OPTION>
                </select>
            </td>
          </tr>
          <tr id="trdark">
		    <td nowrap> 
              <div align="right">Sucursal Destino :</div>
            </td>
            <td nowrap>
            	<INPUT type="text" name="E01CHMDBR" size="3" maxlength="3" value="<%= rtBasic.getE01CHMDBR().trim()%>" onkeypress="enterInteger()">
                <a href="javascript:GetBranch('E01CHMDBR','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
      		</td>
      	  </tr>
            <input type="hidden" name="E01CHMTCH" size="15" maxlength="15">            
			<INPUT size="25" type="hidden" name="DESNJMOET" maxlength="20" value="">
          
        </table>
      </td>
    </tr>
  </table>
  
  
   <p align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
   </p>
   
  <SCRIPT Language="Javascript">
  
  function valida_contenido()
  {
		document.forms[0].E01CHMTCH.value    = document.forms[0].E01CHPTCB.value ;
		return valida_cheq();
  }
  
  function valida_cheq()
  {
   	var stock = trim(document.forms[0].E01CHMNCH.value);
   
   	if (stock > 10) {
   		alert("Cantidad de Chequeras debe ser menor o igual a 10");
   		document.forms[0].E01CHMNCH.focus();
   		return false;
   	}
   	else return true;	  	   
  }
  

</SCRIPT>
  
  </form>
</body>
</html>
