<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Informacion Basica</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="<%=request.getContextPath()%>/pages/style.css"
	rel="stylesheet">


<script language="Javascript1.1"
	src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> 
</SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
                                                  
<jsp:useBean id="bankOld" class="datapro.eibs.beans.ESD000502Message" 	scope="session" />

<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" 	scope="session" />

<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" 	scope="session" />

<jsp:useBean id="currUser" class="datapro.eibs.beans.ESS0030DSMessage"
	scope="session" />



</head>

<body bgcolor="#FFFFFF">

<%if (!error.getERRNUM().equals("0")) {
	error.setERRNUM("0");
	out.println("<SCRIPT Language=\"Javascript\">");
	out.println("       showErrors()");
	out.println("</SCRIPT>");
}
 
%>

<h3 align="center">Parametros Generales de Control Mantenimiento-CONTINUACION-<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" ALT="bank_setup_basic_maint_add, ESD0005"></h3>
<hr size="4">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSESD0005M">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="500">  
<h4>Informaci&oacute;n Basica</h4>
<div align="left">

<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">


	<tr id="trdark">
		<td nowrap width="25%">
		<div align="right">Transfe P&G en Mda/Ext:</div>
		</td>
		<td nowrap width="20%">		 
		<select name="E02CNTPRF">
			<option value=" "
				<%if (!(bankOld.getE02CNTPRF().equals("B")
	                 || bankOld.getE02CNTPRF().equals("D")	 
	                 || bankOld.getE02CNTPRF().equals("M")	
	                 || bankOld.getE02CNTPRF().equals("N")))
	             out.print("selected");%>>
			</option>
			<option value="B" <%if (bankOld.getE02CNTPRF().equals("B")) out.print("selected");%>>Misma Cta Mayor Mda Base</option>
			<option value="D" <%if (bankOld.getE02CNTPRF().equals("D")) out.print("selected");%>>Revaluacion Diaria</option>
			<option value="M" <%if (bankOld.getE02CNTPRF().equals("M")) out.print("selected");%>>Revaluacion Mensual</option>
			<option value="N" <%if (bankOld.getE02CNTPRF().equals("N")) out.print("selected");%>>No Revaluacion PyG</option> 
		</select>		   
	  </td>
		<td nowrap width="30%">
		<div align="right">Moneda Base :</div>
		</td>        
		<td nowrap width="25%">
		   <input type="text" name="E02CNTBCU" size="4" maxlength="3" value="<%=bankOld.getE02CNTBCU().trim()%>">
		   <a href="javascript:GetCurrency('E02CNTBCU','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absmiddle" border="0" ></a> 
        </td> 
	</tr>
	<tr id="trclear">
	    <td nowrap width="25%">
		  <div align="right">Cta Transfe P&G M/E :</div>
		</td>
		<td nowrap width="20%">
		  <input type="text" name="E02CNTPRG" size="17" maxlength="16" value="<%=bankOld.getE02CNTPRG().trim()%>">  
		</td>
		<td nowrap width="30%">
		   <div align="right">Moneda Base Casa Matriz :</div>
		</td>
        <td nowrap width="25%">
		     <input type="text" name="E02CNTHCU" size="4" maxlength="3" value="<%=bankOld.getE02CNTHCU().trim()%>"> 
		     <a href="javascript:GetCurrency('E02CNTHCU','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absmiddle" border="0" ></a> 
        </td>

	</tr>
	<tr id="trdark">
		<td nowrap width="25%">
		<div align="right">Cuenta Suspenso Balance:</div>
		</td>
		<td nowrap width="20%"><input type="text" name="E02CNTDGA" size="17" maxlength="16" value="<%=bankOld.getE02CNTDGA().trim()%>">
	   </td>
		<td nowrap width="30%">
		<div align="right">Frecuencia Revaluacion :</div>
		</td>
        <td nowrap width="25%">
		<select name="E02CNTRVF">
			<option value=" "
				<%if (!(bankOld.getE02CNTRVF().equals("1")
	                 || bankOld.getE02CNTRVF().equals("2")	
	                 || bankOld.getE02CNTRVF().equals("3") 	
	                 || bankOld.getE02CNTRVF().equals("N")))
	           out.print("selected");%>>
			</option>
			<option value="1" <%if (bankOld.getE02CNTRVF().equals("1")) out.print("selected");%>>Posicion Global Diaria</option>
			<option value="2" <%if (bankOld.getE02CNTRVF().equals("2")) out.print("selected");%>>Posicion Global Mensual</option>
			<option value="3" <%if (bankOld.getE02CNTRVF().equals("3")) out.print("selected");%>>Reval. por Cuenta Diaria</option>
			<option value="N" <%if (bankOld.getE02CNTRVF().equals("N")) out.print("selected");%>>No Maneja Revaluacion</option> 
		</select>
		</td>     
	</tr>
	<tr id="trclear">
		<td nowrap width="25%">
		<div align="right">Cuenta Suspenso Contin :</div>
		</td>
		<td nowrap width="20%">
	    	<input type="text" name="E02CNTDCA" size="17" maxlength="16" value="<%=bankOld.getE02CNTDCA().trim()%>">
		</td>
	 
	    <td nowrap width="30%">
		   <div align="right"></div>
		</td>
		<td nowrap width="25%">
	    </td>		

	</tr>
	
	<tr id="trdark">
		<td nowrap width="25%">
		    <div align="right">Revaluación P/G No-IBF :</div>
		</td>
		<td nowrap width="20%">
		   <input type="text" name="E02CNTPLA" size="17" maxlength="16" value="<%=bankOld.getE02CNTPLA().trim()%>">
		</td>
		 
	 
		<td nowrap width="30%">
		    <div align="right">Revaluación P/G IBF :</div>
		</td>
		<td  nowrap width="25%">
        	 <input type="text" name="E02CNTPLI" size="17" maxlength="16" value="<%=bankOld.getE02CNTPLI().trim()%>">
		</td>	
		 
	</tr>
	<tr id="trclear">
		<td nowrap width="25%">
	    	<div align="right">Cta a Cobrar (IBF) :</div>
		</td>
		<td nowrap width="20%">		    
		   <input type="text" name="E02CNTDFA" size="17" maxlength="16" value="<%=bankOld.getE02CNTDFA().trim()%>">
		</td>	
		<td nowrap width="30%">
		<div align="right">Cta a Pagar (No IBF) :</div>
		</td>
		<td nowrap width="25%">
	       <input type="text" name="E02CNTDTI" size="17" maxlength="16" value="<%=bankOld.getE02CNTDTI().trim()%>">
		</td>	
	    
	</tr>
	<tr id="trdark">
		<td nowrap width="25%">
		<div align="right">Contable Canje Salida :</div>
		</td>
		<td nowrap width="20%">
            <input type="text" name="E02CNTFDO" size="17" maxlength="16" value="<%=bankOld.getE02CNTFDO().trim()%>">
		</td>
	    <td nowrap width="30%">
		   <div align="right">Contable Canje Entrada :</div>
		</td>
		<td nowrap width="25%">
  	        <input type="text" name="E02CNTFDI" size="17" maxlength="16" value="<%=bankOld.getE02CNTFDI().trim()%>">		
	    </td>
 
		
	</tr>
	<tr id="trclear">
	    <td nowrap width="25%">
		   <div align="right">Lote Especial Contable :</div>
		</td>
		<td nowrap width="20%">
		    &nbsp;<input type="text" name="E02CNTGRF" size="5" maxlength="4" value="<%=bankOld.getE02CNTGRF().trim()%>"  onKeyPress="enterInteger()">
		    &nbsp;&nbsp; A &nbsp; 
		    &nbsp;<input type="text" name="E02CNTGRT" size="5" maxlength="4" value="<%=bankOld.getE02CNTGRT().trim()%>"  onKeyPress="enterInteger()">		    	 			
	    </td>
		<td nowrap width="30%">
		<div align="right">Moneda Control Efectivo :</div>
		</td>
        <td nowrap width="25%">
        	<input type="text" name="E02CNTCLM" size="4" maxlength="3" value="<%=bankOld.getE02CNTCLM().trim()%>">
        	<a href="javascript:GetCurrency('E02CNTCLM','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absmiddle" border="0" ></a> 
        </td>	 
 	</tr>			
	<tr id="trdark">
	    <td nowrap width="30%">
		</td>
		<td nowrap width="25%">   			
	    </td>
	    <td nowrap width="30%">
		   <div align="right">Limite Control/Efectiv :</div>
		</td>
		<td nowrap width="25%">
         <input type="text" name="E02CNTCSL" size="17" maxlength="16" value="<%=bankOld.getE02CNTCSL().trim()%>">
		</td>
 	</tr> 	
</table>
<h4>Informaci&oacute;n Adicional</h4>
<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
	<tr id="trdark">
		<td nowrap width="25%">
		<div align="right">Tipo de Institución :</div>
		</td>
		<td nowrap width="20%">
		<select name="E02CNTTYP">
			<option value=" "
				<%if (!(bankOld.getE02CNTTYP().equals("B")	       	
	                 || bankOld.getE02CNTTYP().equals("R")))
	             out.print("selected");%>>
			</option>
			<option value="B" <%if (bankOld.getE02CNTTYP().equals("B")) out.print("selected");%>>Institucion Base</option>
			<option value="R" <%if (bankOld.getE02CNTTYP().equals("R")) out.print("selected");%>>Institucion Consolidar</option> 
		</select>
		</td>
		<td nowrap width="30%">
		<div align="right">Instituci&oacute;n a Consolidar :</div>
		</td>
        <td nowrap width="25%">
            <input type="text" name="E02CNTREG" size="3" maxlength="2" value="<%=bankOld.getE02CNTREG().trim()%>">
        </td>
	</tr>
	<tr id="trclear">
		<td nowrap width="25%">
		<div align="right">Balance Usado Mda/Extr :</div>
		</td>
		<td nowrap width="20%">
           <input type="text" name="E02CNTIEB" size="2" maxlength="1" value="<%=bankOld.getE02CNTIEB().trim()%>">
           <A href="javascript:GetCode('E02CNTIEB','STATIC_par_bal_mond_extrg.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a>			
		</td>
		<td nowrap width="30%">
		<div align="right">Inst. en Donde Duplicar :</div>
		</td>
        <td nowrap width="25%">
            <input type="text" name="E02CNTBDB" size="17" maxlength="16" value="<%=bankOld.getE02CNTBDB().trim()%>">
        </td>
	</tr>
	<tr id="trdark">
		<td nowrap width="25%">
		<div align="right">Ingreso x Compra/Venta :</div>
		</td>
		<td nowrap width="20%">
         <input type="text" name="E02CNTFXI" size="17" maxlength="16" value="<%=bankOld.getE02CNTFXI().trim()%>">
		</td>
		<td nowrap width="30%">
		<div align="right">Nivel Estado Financieros :</div>
		</td>
		<td nowrap width="25%">
		    <input type="text" name="E02CNTFSL" size="2" maxlength="1" value="<%=bankOld.getE02CNTFSL().trim()%>">
			<A href="javascript:GetCode('E02CNTFSL','STATIC_par_estd_finan.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a>			
		</td>
	</tr>
	<tr id="trclear">
		<td nowrap width="25%">
		<div align="right">Egreso x Compra/Venta :</div>
		</td>
		<td nowrap width="20%">
		    <input type="text" name="E02CNTFXO" size="17" maxlength="16" value="<%=bankOld.getE02CNTFXO().trim()%>">
		</td>
		<td nowrap width="30%">
		<div align="right">Numeración de Chequeras :</div>
		</td>
		<td nowrap width="25%">
		<select name="E02CNTFL4"> 
			<option value=" " <%if (bankOld.getE02CNTFL4().equals(" ")) out.print("selected");%>>Numeracion por cuenta</option>
			<option value="1" <%if (bankOld.getE02CNTFL4().equals("1")) out.print("selected");%>>Numeracion por Banco</option>
 
		</select> 
		</td>
	</tr>
	<tr id="trdark">
		<td nowrap width="25%">
		<div align="right">% Máximo a Garantizar :</div>
		</td>
		<td nowrap width="20%">
 			<input type="text" name="E02CNTCPE" size="9" maxlength="8" value="<%=bankOld.getE02CNTCPE().trim()%>">  
	    </td>
		<td nowrap width="30%">
		<div align="right">Dias Paso Int.Sobrg.Susp :</div>
		</td>
		<td nowrap width="25%">
		   <input type="text" name="E02CNTIPA" size="3" maxlength="2" 	value="<%= bankOld.getE02CNTIPA().trim()%>">
		</td>	    

	</tr>
	<tr id="trclear">
		<td nowrap width="25%">
		<div align="right">Est.Cta.Giros/Val.Cobr :</div>
		</td>
		<td nowrap width="20%">		 
		<select name="E02CNTFL7">		  		
		  <option value=" "
				<% if (!(bankOld.getE02CNTFL7().equals("D")
                	  || bankOld.getE02CNTFL7().equals("M")
                  	  || bankOld.getE02CNTFL7().equals("N")))
                	out.print("selected"); %>>
          </option> 
		  <option value="D" <%if (bankOld.getE02CNTFL7().equals("D"))  out.print("selected");%>>Dia</option>
		  <option value="M" <%if (bankOld.getE02CNTFL7().equals("M"))  out.print("selected");%>>Mes</option>
	      <option value="N" <%if (bankOld.getE02CNTFL7().equals("N"))  out.print("selected");%>>No genera</option>     
		</select>   
		</td>
	    <td nowrap width="30%">
		<div align="right">Tipo de Previsión :</div>
		</td>
		<td nowrap width="25%">
 		<select name="E02CNTPVF">		  		
		  <option value=" "
				<% if (!(bankOld.getE02CNTPVF().equals("N")
                      || bankOld.getE02CNTPVF().equals("C")
                  	  || bankOld.getE02CNTPVF().equals("G")))
                	out.print("selected"); %>>
          </option> 
		  <option value="N" <%if (bankOld.getE02CNTPVF().equals("N"))  out.print("selected");%>>No Previsión</option>
		  <option value="C" <%if (bankOld.getE02CNTPVF().equals("C"))  out.print("selected");%>>A Nivel de Cliente</option>
	      <option value="G" <%if (bankOld.getE02CNTPVF().equals("G"))  out.print("selected");%>>A Nivel de Grupo</option>     
		</select>  
	    </td>		

	</tr>

	<tr id="trdark">
		<td nowrap width="25%">
		<div align="right">Forma Contbl Construct :</div>
		</td>
        <td nowrap width="20%">
 		    <input type="text" name="E02CNTFL8" size="2" maxlength="1" value="<%=bankOld.getE02CNTFL8().trim()%>">
			<A href="javascript:GetCode('E02CNTFL8','STATIC_par_contbl_contruc.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a>	
        </td>
	    <td nowrap width="30%">
		<div align="right"> Dias traspaso sobregiro :</div>
		</td>
        <td nowrap width="25%">
            <input type="text" name="E02CNTOVD" size="5" maxlength="4" 	value="<%= bankOld.getE02CNTOVD().trim()%>">
        </td>        

	</tr>
	<tr id="tclear">
		<td nowrap width="25%">
		<div align="right">Aceptantes Niv.Cliente :</div>
		</td>
		<td nowrap width="20%"> 
	       <p>
           <input type="radio" name="E02CNTFG5" value="1"   <%if (bankOld.getE02CNTFG5().equals("1")) 	out.print("checked");%>> Si
           <input type="radio" name="E02CNTFG5" value="0"   <%if (bankOld.getE02CNTFG5().equals("0")) 	out.print("checked");%>> No</p>		    
	    </td>
	    <td nowrap width="30%" align="right">Código de Banco   :</td>
	    <td nowrap width="25%"><input type="text" name="E02CNTRTC" size="5" maxlength="4" value="<%=bankOld.getE02CNTRTC().trim()%>"></td> 
	</tr>	 
</table>


<h4>Notificaciones Ciclicas por Concepto de Pagos Vencidos</h4>
<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
	<tr id="trdark">
		<td nowrap width="25%">
		<div align="right">No. Dias para Imprimir Nivel 1 de Notificación Vencida :</div>
		</td>
		<td nowrap width="25%">
            <input type="text" name="E02CNTML2" size="3" maxlength="2" value="<%=bankOld.getE02CNTML2().trim()%>" onKeyPress="enterInteger()">  
		</td>		 
	</tr>
	<tr id="trclear">
		<td nowrap width="25%">
		<div align="right">No. Dias para Imprimir Nivel 2 de Notificación Después de 1ra :</div>
		</td>
		<td nowrap width="25%">
             <input type="text" name="E02CNTML3" size="3" maxlength="2" value="<%=bankOld.getE02CNTML3().trim()%>" onKeyPress="enterInteger()">   
		</td>
		
	</tr>
	<tr id="trdark">
		<td nowrap width="25%">
		<div align="right">No. Dias para Imprimir Nivel 3 de Notificación Después de 2da :</div>
		</td>
		<td nowrap width="25%">
            <input type="text" name="E02CNTML4" size="3" maxlength="2" value="<%=bankOld.getE02CNTML4().trim()%>" onKeyPress="enterInteger()">   
		</td>
		
	</tr>
	<tr id="trclear">
		<td nowrap width="25%">
		<div align="right">No. Dias para Imprimir Nivel 4 de Notificación Después de 3ra :</div>
		</td>
		<td nowrap width="25%">
		     <input type="text" name="E02CNTML5" size="3" maxlength="2" value="<%=bankOld.getE02CNTML5().trim()%>" onKeyPress="enterInteger()">   
		</td>
		
	</tr>
	<tr id="trdark">
		<td nowrap width="25%">
		<div align="right">No. Dias para Imprimir Nivel 5 de Notificación Después de 4ta :</div>
		</td>
		<td nowrap width="25%">
		    <input type="text" name="E02CNTML6" size="3" maxlength="2" value="<%=bankOld.getE02CNTML6().trim()%>" onKeyPress="enterInteger()">   
	    </td>

	</tr>
	<tr id="trclear">
		<td nowrap width="25%">
		<div align="right">No. Dias Anticipados para Enviar Aviso de Pago (Facturación) :</div>
		</td>
		<td nowrap width="25%">
		   <INPUT type="text" name="E02CNTNPA" size="3" maxlength="2" value="<%= bankOld.getE02CNTNPA().trim()%>" onKeyPress="enterInteger()">
		 		
		</td>
	   
	</tr>

	<tr id="trdark">
		<td nowrap width="25%">
		<div align="right">Actualización Estadisticas al Vencimiento o Período de Gracia :</div>
		</td>
        <td nowrap width="25%">
    	<select name="E02CNTUDS">
    	     <option value=" "
				<% if (!(bankOld.getE02CNTUDS().equals("D")                 
                  	  || bankOld.getE02CNTUDS().equals("G")))
                	out.print("selected"); %>>
             </option>  	
			 <option value="D" <%if (bankOld.getE02CNTUDS().equals("D")) out.print("selected");%>>Actualizar en Fecha de Mora</option> 
             <option value="G" <%if (bankOld.getE02CNTUDS().equals("G")) out.print("selected");%>>Actualizar en Periodo de Gracia</option> 	          	
        </select>  
        </td>
	
	</tr>
	<tr id="trclear">
		<td nowrap width="25%">
		<div align="right">No. Dias para Enviar a Protesto un Documento :</div>
		</td>
		<td nowrap width="25%"> 
			<INPUT type="text" name="E02CNTGPD" size="2" maxlength="1" value="<%= bankOld.getE02CNTGPD().trim()%>">		     		
	    </td>
	</tr>	 
</table>
<h4>Notificaciones C&iacute;clicas para Requisici&oacute;n de seguros de Garant&iacute;a</h4>
<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
	<tr id="trdark">
		<td nowrap width="25%">
		<div align="right">No. Dias para Imprimir 1er Ciclo Notificaciones de Seguro. :</div>
		</td>
    	<td nowrap width="25%">
    		<input type="text" name="E02CNTIC2" size="3" maxlength="2" value="<%=bankOld.getE02CNTIC2().trim()%>" onKeyPress="enterInteger()">  
    	</td>
	</tr>
	<tr id="trclear">
		<td nowrap width="25%">
		<div align="right">No. Dias para Imprimir 2do Ciclo Notificaciones de Seguro :</div>
		</td>
		<td nowrap width="25%">
            <input type="text" name="E02CNTIC3" size="3" maxlength="2" value="<%=bankOld.getE02CNTIC3().trim()%>" onKeyPress="enterInteger()">   
		</td>
	</tr>
	<tr id="trdark">
		<td nowrap width="25%">
		<div align="right">No. Dias para Imprimir 3er Ciclo Notificaciones de Seguro :</div>
		</td>
		<td nowrap width="31%">
            <input type="text" name="E02CNTIC4" size="3" maxlength="2" value="<%=bankOld.getE02CNTIC4().trim()%>" onKeyPress="enterInteger()">   
		</td>
	</tr>
	<tr id="trclear">
		<td nowrap width="25%">
		<div align="right">Nro Consecutivo de Dias S/G para Contabilizar Intrs en Suspenso :</div>
		</td>
		<td nowrap width="25%">
		    <input type="text" name="E02CNTIPA" size="4" maxlength="3" value="<%=bankOld.getE02CNTIPA().trim()%>">
		</td>
	</tr>
	<tr id="trdark">
		<td nowrap width="25%">
		<div align="right">Forma de Cobro para las Cuotas Vencidas :</div>
		</td>
		<td nowrap width="25%">	
		   <input type="text" name="E02CNTPDP" size="2" maxlength="1" value="<%= bankOld.getE02CNTPDP().trim()%>">
 	   	   <A href="javascript:GetCode('E02CNTPDP','STATIC_par_cuota_venc.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a>	  
	    </td>
	</tr>
	<tr id="trclear">
		<td nowrap width="25%">
		<div align="right">Provisión de Intereses de Mora (Cuotas e Hipotecas) :</div>
		</td>
		<td nowrap width="25%">
		  <p>
           <input type="radio" name="E02CNTMIL" value="Y"
	                 <%if (bankOld.getE02CNTMIL().equals("Y")) 	out.print("checked");%>> Si
           <input type="radio" name="E02CNTMIL" value="N" 
                     <%if (bankOld.getE02CNTMIL().equals("N")) 	out.print("checked");%>> No
          </p>
		</td>
	</tr>
	<tr id="trdark">
		<td nowrap width="25%">
		<div align="right">Forma de Contabilización de los Cargos por Mora :</div>
		</td>
        <td nowrap width="25%">
     	<select name="E02CNTAPI">
		    <option value=" "
				<% if (!(bankOld.getE02CNTAPI().equals("1")
                      || bankOld.getE02CNTAPI().equals("2")
                  	  || bankOld.getE02CNTAPI().equals("N")))
                	out.print("selected"); %>>
             </option>       	
			 <option value="1" <%if (bankOld.getE02CNTAPI().equals("1")) out.print("selected");%>>Contabiliza Diariamente</option> 
             <option value="2" <%if (bankOld.getE02CNTAPI().equals("2")) out.print("selected");%>>Contabiliza Cuando Cobra</option> 
             <option value="N" <%if (bankOld.getE02CNTAPI().equals("N")) out.print("selected");%>>No Calcula Mora</option> 	          	
        </select> 
        </td>
	</tr>
	<tr id="trclear">
		<td nowrap width="25%">
		<div align="right">Al Cambiar de Cuenta Contable, Cambia de Cuenta los Ingresos :</div>
		</td>
		<td nowrap width="25%"> 	
		  <p>	 
           <input type="radio" name="E02CNTCGL" value="Y"
	                 <%if (bankOld.getE02CNTCGL().equals("Y")) 	out.print("checked");%>> Si
           <input type="radio" name="E02CNTCGL" value="N" 
                     <%if (bankOld.getE02CNTCGL().equals("N")) 	out.print("checked");%>> No
          </p>			
	    </td>
	</tr>
	<tr id="trdark">
		<td nowrap width="25%">
		<div align="right">Tasa Máxima de Penalización :</div>
		</td>
		<td nowrap width="25%">
 		   <INPUT type="text" name="E02CNTMPI" size="11" maxlength="10" value="<%= bankOld.getE02CNTMPI().trim()%>">
	    </td>
	</tr>
</table>
<p>
<div align="center"><input id="EIBSBTN" type=submit name="Submit"
	value="Enviar"></div>
</p>
</form>
</body>
</html>

