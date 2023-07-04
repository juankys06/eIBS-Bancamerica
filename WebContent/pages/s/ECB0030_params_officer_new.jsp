<html>
<head> 
<title>Creacion de Gestiones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


<jsp:useBean id= "clientInfo" class= "datapro.eibs.beans.ECB003001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>
<jsp:useBean id= "currUser" 	class= "datapro.eibs.beans.ESS0030DSMessage"  		scope="session"/>

<SCRIPT>

//builtNewMenu(ln_i_1_opt);
builtNewMenu(opt_cob);

initMenu();

function callCust() {
var customer = document.forms[0].E01ECBCUN.value;    
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=600&E01CUN="+customer;
    	CenterNamedWindow(page,'Information',650,500,2);
}

function callPosition() {
var customer = document.forms[0].E01ECBCUN.value;    
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF010?SCREEN=5&E03CUSCUN="+customer;
    	CenterNamedWindow(page,'Information',650,500,2);

}

function callLoanInq(){
var loanNum =document.forms[0].E01ECBACC.value;  
	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0160?SCREEN=200&E01DEAACC="+loanNum;
    CenterNamedWindow(page,'Information',650,500,2);
}

</SCRIPT>



<% 
   if (clientInfo == null) clientInfo = new datapro.eibs.beans.ECB003001Message();   
%>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }
%>
</head>

<body>

<H3 align="center">Gestion de Cobranzas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="params_officer_new,ECB0030"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECB0030">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="300">
   <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="14%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="9%" > 
              <div align="left"> 
                <input type="text" name="E01ECBCUN" size="9" maxlength="9" readonly value="<%= clientInfo.getE01ECBCUN().trim()%>">
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01ECBNAM" size="45" maxlength="45" readonly value="<%= clientInfo.getE01ECBNAM().trim()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap ><b> 
              <input type="text" name="E01ECBPRO" size="4" maxlength="4" readonly value="<%= clientInfo.getE01ECBPRO().trim()%>">
              </b></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="14%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="9%"> 
              <div align="left"> 
                <input type="text" name="E01ECBACC" size="12" maxlength="12" value="<%= clientInfo.getE01ECBACC().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="12%"> 
              <div align="right"><b>Oficial :</b></div>
            </td>
            <td nowrap width="33%"> 
              <div align="left"><b> 
                <input type="text" name="E01ECBOFC" size="30" maxlength="30" readonly value="<%= clientInfo.getE01ECBOFC().trim()%>">
                </b> </div>
            </td>
            <td nowrap width="11%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="21%"> 
              <div align="left"><b> 
                <input type="text" name="E01ECBCCY" size="3" maxlength="3" value="<%= clientInfo.getE01ECBCCY().trim()%>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
   <h4>Datos del Cliente</h4>
  
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
			<tr id="trdark"> 
            <td nowrap > 
              <div align="right">Direcci&oacute;n :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01CUSNA2" size="36" maxlength="35" value="<%=  clientInfo.getE01CUSNA2().trim()%>" readonly>
            </td>
            <td nowrap > 
              <div align="right">Telefono 1 :</div>
            </td>
            <td nowrap >
           		<input type="text" name="E01CUSPHN" size="15" maxlength="15" value="<%=  clientInfo.getE01CUSPHN().trim()%>" readonly> 
           
            </td>
          </tr> 
            <tr id="trclear"> 
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap > 
              <input type="text" name="E01CUSNA3" size="36" maxlength="35" value="<%= clientInfo.getE01CUSNA3().trim()%>" readonly>
            </td>
            <td nowrap > 
              <div align="right">Telefono 2 :</div>
            </td>
            <td nowrap >
           		<input type="text" name="E01CUSHPN" size="15" maxlength="15" value="<%= clientInfo.getE01CUSHPN().trim()%>" readonly> 
           
            </td>
          </tr>        
         <tr id="trdark"> 
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap > 
              <input type="text" name="E01CUSNA4" size="36" maxlength="35" value="<%=  clientInfo.getE01CUSNA4().trim()%>" readonly>
            </td>
            <td nowrap > 
              <div align="right">Telefono 3 :</div>
            </td>
            <td nowrap >
           		<input type="text" name="E01CUSPH1" size="15" maxlength="15" value="<%=  clientInfo.getE01CUSPH1().trim()%>" readonly> 
           
            </td>
          </tr> 
        </table>
      </td>
    </tr>
  </table>
  <br/>
  <table class=tbenter>
   <tr > 
      <td nowrap> 
   		<h4>Sumario</h4>
      </td>
      <td nowrap align=right> 
   		<b>ESTADO :</b>
      </td>
      <td nowrap> 
   		<b><font color="#ff6600"><%=  clientInfo.getE01STATUS().trim()%></font></b>
      </td>
    </tr>
  </table>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
			<tr id="trdark"> 
            <td nowrap > 
              <div align="right">Monto Liquidacion :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAOAM" size="15" maxlength="15" value="<%=  clientInfo.getE01DEAOAM().trim()%>" readonly>
            </td>
            <td nowrap > 
              <div align="right">Saldo Prestamo :</div>
            </td>
            <td nowrap >
           		<input type="text" name="E01DEAMEP" size="15" maxlength="15" value="<%=  clientInfo.getE01DEAMEP().trim()%>" readonly> 
           
            </td>
          </tr> 
            <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Principal Vencido :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAPDU" size="15" maxlength="15" value="<%=  clientInfo.getE01DEAPDU()%>" readonly>
            </td>
            <td nowrap > 
              <div align="right">Interes Vencido :</div>
            </td>
            <td nowrap >
           		<input type="text" name="E01DEAIDU" size="15" maxlength="15" value="<%= clientInfo.getE01DEAIDU()%>" readonly> 
           
            </td>
          </tr>        
			<tr id="trdark"> 
             <td nowrap > 
              <div align="right">Fecha de Liquidacion :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAOD1" size="1" maxlength="2" value="<%= clientInfo.getE01DEAOD1().trim()%>" readonly>
              <input type="text" name="E01DEAOD2" size="1" maxlength="2" value="<%= clientInfo.getE01DEAOD2().trim()%>" readonly>
              <input type="text" name="E01DEAOD3" size="1" maxlength="2" value="<%= clientInfo.getE01DEAOD3().trim()%>" readonly>
            </td>
            <td nowrap > 
              <div align="right">Fecha Vencimiento :</div>
            </td>
            <td nowrap >
           		 <input type="text" name="E01DEAMD1" size="1" maxlength="2" value="<%=  clientInfo.getE01DEAMD1().trim()%>" readonly>
             	 <input type="text" name="E01DEAMD2" size="1" maxlength="2" value="<%=  clientInfo.getE01DEAMD2().trim()%>" readonly>
            	  <input type="text" name="E01DEAMD3" size="1" maxlength="2" value="<%= clientInfo.getE01DEAMD3().trim()%>" readonly>
           		
           
            </td>
          </tr>
          
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Ciclo de Pago de Capital :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAPPD" size="5" maxlength="3" value="<%=  clientInfo.getE01DEAPPD().trim()%>" readonly>
            </td>
            <td nowrap > 
              <div align="right">Ciclo de Pago de Interes :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01ACMIPD" size="5" maxlength="3" value="<%=  clientInfo.getE01DEAIPD().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Cuenta Cte/Ahorro :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEARAC" size="15" maxlength="15" value="<%=  clientInfo.getE01DEARAC().trim()%>" readonly>
            </td>
            <td nowrap > 
              <div align="right">Saldo Cuenta :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01ACMMGB" size="15" maxlength="15" value="<%=  clientInfo.getE01ACMMGB().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Monto Bloqueado :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01ACMHAM" size="15" maxlength="15" value="<%=  clientInfo.getE01ACMHAM().trim()%>" readonly>
            </td>
            <td nowrap > 
              <div align="right">Saldo Disponible :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01ACMMNB" size="15" maxlength="15" value="<%=  clientInfo.getE01ACMMNB().trim()%>" readonly>
              
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Tipo de Garantia :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01COLATR" size="15" maxlength="15" value="<%=  clientInfo.getE01COLATR().trim()%>" readonly>
            </td>
            <td nowrap > 
              <div align="right">Ultima Tasa Inter&eacute;s :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01NOWRTE" size="9" maxlength="9" value="<%=  clientInfo.getE01NOWRTE().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Linea de Credito :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEACMN" size="15" maxlength="15" value="<%=  clientInfo.getE01DEACMN().trim()%>" readonly>
            </td>
            <td nowrap > 
              <div align="right">Saldo Total :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01TOTPYM" size="15" maxlength="15" value="<%=  clientInfo.getE01TOTPYM().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap >
              <div align="right">Fecha Vcto Documento :</div>
            </td>
            <td nowrap >
               <input type="text" name="E01DEAEX1" size="1" maxlength="2" value="<%=  clientInfo.getE01DEAEX1().trim()%>" readonly>
             	<input type="text" name="E01DEAEX2" size="1" maxlength="2" value="<%=  clientInfo.getE01DEAEX2().trim()%>" readonly>
            	<input type="text" name="E01DEAEX3" size="1" maxlength="2" value="<%=  clientInfo.getE01DEAEX3().trim()%>" readonly>
           	
            </td>
            
          </tr>
         
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n Adicional</h4>
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    

      	<tr id=trdark> 
	      <td nowrap width="20%"> 
	        <div align="right">Código de Cobrador : </div>
	      </td>
	      <td nowrap >                
			<input type="text" name="E01ECBCOD" size="4" maxlength="5" value="<%= clientInfo.getE01ECBCOD().trim()%>">
			<input type="text" name="E01ECBJRN" size="35" maxlength="36" value="<%= clientInfo.getE01ECBJRN().trim()%>"readonly>
            <a href="javascript:GetCodeDescCNOFC('E01ECBCOD','E01ECBJRN','Y9')">
            <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a>   
          </td>
     	</tr>     	  	    	

      	<tr id=trclear> 
	      <td nowrap width="20%"> 
	        <div align="right">Medida y Solucion cobro : </div>
	      </td>
	      <td nowrap >                
			<input type="text" name="E01MEDCOD" size="4" maxlength="5" >
			<input type="text" name="E01MEDJRN" size="35" maxlength="36" readonly>
            <a href="javascript:GetCodeDescCNOFC('E01MEDCOD','E01MEDJRN','MS')">
            <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a>   
          </td>
     	</tr>     	  	    	

      	<tr id=trdark> 
	      <td nowrap width="20%"> 
	        <div align="right">Politica de Cobro : </div>
	      </td>
	      <td nowrap >                
			<input type="text" name="E01POLCOD" size="4" maxlength="5" >
			<input type="text" name="E01POLJRN" size="35" maxlength="36" readonly>
            <a href="javascript:GetCodeDescCNOFC('E01POLCOD','E01POLJRN','PL')">
            <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a>   
          </td>
     	</tr>     	  	    	


      	
      	<tr id=trclear> 
	      <td nowrap width="20%"> 
	        <div align="right">Gestion de Cobranza : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01ECBNA0" size="90" maxlength="60" value="<%= clientInfo.getE01ECBNA0() %>">
	      </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap width="20%"> 
	        <div align="right"></div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01ECBNA1" size="90" maxlength="60" value="<%= clientInfo.getE01ECBNA1() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap width="20%"> 
	        <div align="right"> </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01ECBNA2" size="90" maxlength="60" value="<%= clientInfo.getE01ECBNA2() %>">
	      </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap> 
	        <div align="right"></div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01ECBNA3" size="90" maxlength="60" value="<%= clientInfo.getE01ECBNA3() %>">
      	  </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right"></div>
	      </td>
	      <td nowrap>
	      <input type="text" name="E01ECBNA4" size="90" maxlength="60" value="<%= clientInfo.getE01ECBNA4() %>">
	      </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap> 
	        <div align="right"></div>
	      </td>
	      <td nowrap>
	      <input type="text" name="E01ECBNA5" size="90" maxlength="60" value="<%= clientInfo.getE01ECBNA5() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right"></div>
	      </td>
	      <td nowrap>
	      <input type="text" name="E01ECBNA6" size="90" maxlength="60" value="<%= clientInfo.getE01ECBNA6() %>">
	      </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap> 
	        <div align="right"></div>
	      </td>
	      <td nowrap>
	      <input type="text" name="E01ECBNA7" size="90" maxlength="60" value="<%= clientInfo.getE01ECBNA7() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right"></div>
	      </td>
	      <td nowrap>
	      <input type="text" name="E01ECBNA8" size="90" maxlength="60" value="<%= clientInfo.getE01ECBNA8() %>">
	      </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap> 
	        <div align="right"></div>
	      </td>
	      <td nowrap>
	      <input type="text" name="E01ECBNA9" size="90" maxlength="60" value="<%= clientInfo.getE01ECBNA9() %>">
	      </td>
     	</tr>
     	
     </table>
    </td>
   </tr>
  </table>
  
  <p align="center"> 
    <input id="EIBSBTN" type="submit" name="Submit" value="Enviar">
  </p>

</form>
</body>
</html>
