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

<jsp:useBean id="bankNew" class="datapro.eibs.beans.ESD000501Message" 	scope="session" />

<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" 	scope="session" />

<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" 	scope="session" />

<jsp:useBean id="currUser" class="datapro.eibs.beans.ESS0030DSMessage" 	scope="session" />

 

</head>

<body bgcolor="#FFFFFF">

<%if (!error.getERRNUM().equals("0")) {
	error.setERRNUM("0");
	out.println("<SCRIPT Language=\"Javascript\">");
	out.println("       showErrors()");
	out.println("</SCRIPT>");
}
 
%>

<h3 align="center">Parametros Generales de Control<img
	src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" ALT="bank_setup_basic_new, ESD0005"></h3>
<hr size="4">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSESD0005M">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="300"> 
 
<h4>Informaci&oacute;n Basica</h4>
<div align="left">

<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%"	border="0">

	<tr id="trclear">
		<td nowrap width="25%">
		<div align="right">Numero del Banco:</div>
		</td>
		<td nowrap width="20%">
		<input type="text" name="E01CNTBNK" size="3"  maxlength="2" value="<%=bankNew.getE01CNTBNK().trim()%>">
	  </td>
		<td nowrap width="30%">
		<div align="right"></div>
		</td>
        <td nowrap width="25%">
           <p> </p>
        </td> 
	</tr>
	<tr id="trdark">
		<td nowrap width="25%">
		<div align="right">Nombre del Banco:</div>
		</td>
		<td nowrap width="20%">
		<input type="text" name="E01CNTNME" size="40"  maxlength="35" value="<%=bankNew.getE01CNTNME().trim()%>">
	  </td>
		<td nowrap width="30%">
		<div align="right">Multimoneda :</div>
		</td>
        <td nowrap width="25%">
           <p>
           <input type="radio" name="E01CNTMCU" value="Y"
	                 <%if (bankNew.getE01CNTMCU().equals("Y")) 	out.print("checked");%>> Si
           <input type="radio" name="E01CNTMCU" value="N" 
                     <%if (bankNew.getE01CNTMCU().equals("N")) 	out.print("checked");%>> No</p>
        </td> 
	</tr>
	<tr id="trclear">
	   <td width="25%">
		<div align="right">Direcci&oacute;n :</div>
		</td>
		<td nowrap width="20%"><input type="text" name="E01CNTADR" size="36" maxlength="35" value="<%=bankNew.getE01CNTADR().trim()%>">  
		</td>
		<td nowrap width="30%">
		<div align="right">Entidad Multibanco :</div>
		</td>

        <td nowrap width="25%">
           <p>
           <input type="radio" name="E01CNTMUL" value="Y"
	                              <%if (bankNew.getE01CNTMUL().equals("Y")) 	out.print("checked");%>> Si
           <input type="radio" name="E01CNTMUL" value="N" 
                                  <%if (bankNew.getE01CNTMUL().equals("N")) 	out.print("checked");%>> No</p>
        </td>

	</tr>
	<tr id="trdark">
		<td nowrap width="25%">
		<div align="right">Telefono :</div>
		</td>
		<td nowrap width="20%"><input type="text" name="E01FNA" size="12" maxlength="11" value="<%=bankNew.getE01CNTPHN().trim()%>">
	   </td>
		<td nowrap width="30%">
		<div align="right">Entidad Multi-Agencia :</div>
		</td>
        <td nowrap width="25%">
           <p>
           <input type="radio" name="E01CNTMBR" value="Y"
	                  <%if (bankNew.getE01CNTMBR().equals("Y")) 	out.print("checked");%>> Si
           <input type="radio" name="E01CNTMBR" value="N" 
                     <%if (bankNew.getE01CNTMBR().equals("N")) 	out.print("checked");%>> No</p>
        </td>
	</tr>
	<tr id="trclear">
		<td nowrap width="25%">
		<div align="right">Identificaci&oacute;n :</div>
		</td>
		<td nowrap width="20%">
		   <input type="text" name="E01CNTBID" size="16"  maxlength="15" value="<%=bankNew.getE01CNTBID().trim()%>">
		</td>
		<td nowrap width="30%">
		<div align="right">Facilidades de IBF :</div>
		</td>
        <td nowrap width="25%">
           <p>
           <input type="radio" name="E01CNTIBF" value="Y"
	                  <%if (bankNew.getE01CNTIBF().equals("Y")) 	out.print("checked");%>> Si
           <input type="radio" name="E01CNTIBF" value="N" 
                      <%if (bankNew.getE01CNTIBF().equals("N")) 	out.print("checked");%>> No</p>
        </td>
	</tr>
	<tr id="trdark">
		<td nowrap width="25%">
		    <div align="right">No.  Identificaci&oacute;n tributaria:</div>
		</td>
		<td nowrap width="20%"><input type="text" name="E01CNTBI2" size="16" maxlength="15" value="<%=bankNew.getE01CNTBI2().trim()%>">
		</td>
		<td nowrap width="30%">
		<div align="right">Seguridad IBS activa :</div>
		</td>
        <td nowrap width="25%">
           <p>
           <input type="radio" name="E01CNTSEC" value="Y"
	                   <%if (bankNew.getE01CNTSEC().equals("Y")) 	out.print("checked");%>> Si
           <input type="radio" name="E01CNTSEC" value="N" 
                       <%if (bankNew.getE01CNTSEC().equals("N")) 	out.print("checked");%>> No</p>
        </td>
	</tr>
	<tr id="trclear">
		<td nowrap width="25%">
		    <div align="right">Cierre Año Fiscal :</div>
		</td>
		<td nowrap width="20%">Mes &nbsp;<input type="text" name="E01CNTFYM" size="3" maxlength="2" value="<%=bankNew.getE01CNTFYM().trim()%>"  onKeyPress="enterInteger()">
		   &nbsp;&nbsp; A&ntilde;o &nbsp;<input type="text" name="E01CNTFYY" size="3" maxlength="2" value="<%=bankNew.getE01CNTFYY().trim()%>"  onKeyPress="enterInteger()">
		</td>	
		<td nowrap width="30%">
		<div align="right">Lengua Nativa :</div>
		</td> 
		<td>
        <select name="E01CNTLAN">
            <option value=" "
	           <% if (!(bankNew.getE01CNTLAN().equals("S")
                    	|| bankNew.getE01CNTLAN().equals("E")))
                	    out.print("selected"); %>>
            </option> 
            <option value="S" <%if (bankNew.getE01CNTLAN().equals("S"))  out.print("selected");%>>Espa&ntilde;ol</option>
		    <option value="E" <%if (bankNew.getE01CNTLAN().equals("E"))  out.print("selected");%>>Ingles</option>
        </select>			
		</td> 
	</tr>
	<tr id="trdark">
        <td nowrap width="25%">
		   <div align="right">Respaldo a Utilizar :</div>
		</td>
		<td nowrap width="20%">
		      <input type="text" name="E01CNTBUM" size="11"  maxlength="10" value="<%=bankNew.getE01CNTBUM().trim()%>"> 
		</td>		
        <td>  
		<div align="right">Acceso a los Clientes :</div>
		</td>
		<td nowrap width="25%">
		  <select name="E01CNTCAT">
		    <option value=" "
	           <% if (!(bankNew.getE01CNTCAT().equals("1")
                    	|| bankNew.getE01CNTCAT().equals("2")))
                	    out.print("selected"); %>>
            </option> 
            <option value="1" <%if (bankNew.getE01CNTCAT().equals("1"))  out.print("selected");%>>Por Numero del Cliente</option>
		    <option value="2" <%if (bankNew.getE01CNTCAT().equals("2"))  out.print("selected");%>>Numero de Identificacion</option>		  
		  </select>
	</tr>
	<tr id="trclear">
		<td nowrap width="25%">
		<div align="right">Microfichas/Reportes :</div>
		</td>
		<td nowrap width="20%">
		<select name="E01CNTMFP">  
		  <option value=" "
				<% if (!(bankNew.getE01CNTMFP().equals("M")
                	|| bankNew.getE01CNTMFP().equals("R")
                	|| bankNew.getE01CNTMFP().equals("B")
                  	|| bankNew.getE01CNTMFP().equals("N")))
                	out.print("selected"); %>>
          </option> 
		  <option value="M" <%if (bankNew.getE01CNTMFP().equals("M"))  out.print("selected");%>>Salva Microficha</option>
		  <option value="R" <%if (bankNew.getE01CNTMFP().equals("R"))  out.print("selected");%>>Salva Reportes del Cierre</option>
	      <option value="B" <%if (bankNew.getE01CNTMFP().equals("B"))  out.print("selected");%>>Microfichas y Reportes</option>  
          <option value="N" <%if (bankNew.getE01CNTMFP().equals("N"))  out.print("selected");%>>Ninguna</option>  
        </select> 
		</td> 
	    <td nowrap width="30%">	 
		   <div align="right">Saldo C&aacute;lculo Sobregiro :</div>
		</td>
		<td nowrap width="25%">
		<select name="E01CNTOCT">  
		  <option value=" "
				<% if (!(bankNew.getE01CNTOCT().equals("G")
                	|| bankNew.getE01CNTOCT().equals("N")
                  	|| bankNew.getE01CNTOCT().equals("A")))
                	out.print("selected"); %>>
          </option> 
		  <option value="G" <%if (bankNew.getE01CNTOCT().equals("G"))  out.print("selected");%>>Saldo en Libros</option>
		  <option value="N" <%if (bankNew.getE01CNTOCT().equals("N"))  out.print("selected");%>>Saldo Disponible</option>
	      <option value="A" <%if (bankNew.getE01CNTOCT().equals("A"))  out.print("selected");%>>Saldo a Nivel de Cuenta</option>     
		</select>
		</td>
	</tr>
	<tr id="trdark">
	    <td nowrap width="25%">
		   <div align="right">Provisi&oacute;n Interes 1er Dia :</div>
		</td>
		<td nowrap width="20%">	 
		<select name="E01CNTFLA">
			 <option value="F" <%if (bankNew.getE01CNTFLA().equals("F")) out.print("selected");%>>Calculo incluye 1er Dia</option> 
             <option value=" " <%if (bankNew.getE01CNTFLA().equals("") || bankNew.getE01CNTFLA().equals(" ") )  out.print("selected");%>>Calculo incluye Ult. Dia</option> 	          	
        </select> 
        </td> 
		<td nowrap width="30%">	 
		<div align="right">D&iacute;gito Chequeo Cta Nueva :</div>
		</td>
		<td nowrap width="25%">
		<select name="E01CNTMOD">
		  <option value=" "
				<% if (!(bankNew.getE01CNTMOD().equals("N")
                   	  || bankNew.getE01CNTMOD().equals("0")
                  	  || bankNew.getE01CNTMOD().equals("1")))
                	out.print("selected"); %>>
          </option>      	
		  <option value="N" <%if (bankNew.getE01CNTMOD().equals("N"))  out.print("selected");%>>No chequear digito en cuenta</option>
		  <option value="0" <%if (bankNew.getE01CNTMOD().equals("0"))  out.print("selected");%>>Modulo-10</option>
	      <option value="1" <%if (bankNew.getE01CNTMOD().equals("1"))  out.print("selected");%>>Modulo-11</option>  
		</select>
		<td nowrap width="25%">
	</tr>
	<tr id="trclear">
		<td>
		<div align="right">Tipo de Fecha :</div>
		</td>
		<td nowrap width="30%"> 
     	<select name="E01CNTDTF">
     		<option value=" "
				<% if (!(bankNew.getE01CNTDTF().equals("MDY")
                	|| bankNew.getE01CNTDTF().equals("DMY")
                  	|| bankNew.getE01CNTDTF().equals("YMD")))
                	out.print("selected"); %>>                	
            </option>
          <option value="MDY" <%if (bankNew.getE01CNTDTF().equals("MDY"))  out.print("selected");%>>MDY=Mes Dia A&ntilde;o</option>
		  <option value="DMY" <%if (bankNew.getE01CNTDTF().equals("DMY"))  out.print("selected");%>>DMY=Dia Mes A&ntilde;o</option>
	      <option value="YMD" <%if (bankNew.getE01CNTDTF().equals("YMD"))  out.print("selected");%>>YMD=A&ntilde;o Mes Dia</option>  
        </select>
		</td>
		
	    <td nowrap width="25%">
		   <div align="right">Lote Especial Contable :</div>
		</td>
		<td nowrap width="20%">
		    &nbsp;<input type="text" name="E01CNTGRF" size="5" maxlength="4" value="<%=bankNew.getE01CNTGRF().trim()%>"  onKeyPress="enterInteger()">
		    &nbsp;&nbsp; A &nbsp; 
		    &nbsp;<input type="text" name="E01CNTGRT" size="5" maxlength="4" value="<%=bankNew.getE01CNTGRT().trim()%>"  onKeyPress="enterInteger()">		    	 			
	    </td>		 
	</tr>

</table>
<h4>Informaci&oacute;n Adicional</h4>
<table  class="tableinfo" cellspacing="0" cellpadding="2" width="100%"	border="0">
 
	<tr id="trdark">
		<td nowrap width="25%">
		<div align="right">Requiere Cupo Prestamo :</div>
		</td>
		<td nowrap width="20%">
           <p>
           <input type="radio" name="E01CNTCLN" value="Y"
	                 <%if (bankNew.getE01CNTCLN().equals("Y")) 	out.print("checked");%>> Si
           <input type="radio" name="E01CNTCLN" value="N" 
                     <%if (bankNew.getE01CNTCLN().equals("N")) 	out.print("checked");%>> No</p>
		</td>
		
		<td nowrap width="30%">
		<div align="right">Requiere Cupo Carta/Credito :</div>
		</td>
		<td nowrap width="25%">
           <p>
           <input type="radio" name="E01CNTCLC" value="Y"
	                 <%if (bankNew.getE01CNTCLC().equals("Y")) 	out.print("checked");%>> Si
           <input type="radio" name="E01CNTCLC" value="N" 
                     <%if (bankNew.getE01CNTCLC().equals("N")) 	out.print("checked");%>> No</p>
        </td>
	</tr>
	<tr id="trclear">
		<td nowrap width="25%">
		<div align="right">Requiere Cupo Sobregiro :</div>
		</td>
		<td nowrap width="20%">
           <p>
           <input type="radio" name="E01CNTCOD" value="Y"
	                 <%if (bankNew.getE01CNTCOD().equals("Y")) 	out.print("checked");%>> Si
           <input type="radio" name="E01CNTCOD" value="N" 
                     <%if (bankNew.getE01CNTCOD().equals("N")) 	out.print("checked");%>> No</p>                     
		</td>
	    <td nowrap width="25%">
		<div align="right">Generar Facturaci&oacute;n  :</div>
		</td>
		<td nowrap width="20%">
		   <input type="text" name="E01CNTFL3" size="2" maxlength="1" 	value="<%= bankNew.getE01CNTFL3().trim()%>">	    
          <A href="javascript:GetCode('E01CNTFL3','STATIC_par_gen_fact.jsp')">
          <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a> 
	    </td>
	</tr>
	<tr id="trdark">
		<td nowrap width="25%">
		<div align="right">M&aacute;ximo de Dias Fecha Valor :</div>
		</td>
		<td nowrap width="20%">
		 <input type="text" name="E01CNTMDB" size="4" maxlength="3" value="<%=bankNew.getE01CNTMDB().trim()%>">
		</td> 
		<td nowrap width="30%">
		<div align="right">M&aacute;ximo Dias Fecha Futura :</div>
		</td>
		<td nowrap width="25%">
		  <input type="text" name="E01CNTMDF" size="4" maxlength="3" value="<%=bankNew.getE01CNTMDF().trim()%>"> 
        </td>
	</tr>
	<tr id="trclear">
		<td nowrap width="25%">
		<div align="right">Generar Numero Automatico</div>
		</td>
		<td nowrap width="20%">
		<INPUT type="text" name="E01CNTANG" size="2" maxlength="1" value="<%= bankNew.getE01CNTANG().trim()%>">
		<A href="javascript:GetCode('E01CNTANG','STATIC_par_gen_num.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a>
		</td>
		<td nowrap width="30%">
		<div align="right">Pa&iacute;s de la Instituci&oacute;n :</div>
		</td>
		<td nowrap width="25%">
		   <INPUT type="text" name="E01CNTINT" size="3" maxlength="2" value="<%= bankNew.getE01CNTINT().trim()%>">
           <A href="javascript:GetCode('E01CNTINT','STATIC_par_country.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a>	 
	</tr>

	<tr id="trdark">
		<td nowrap width="25%">
		<div align="right">Aprobaci&oacute;n Chg de Caja :</div>
		</td>
        <td nowrap width="20%">
           <p>
           <input type="radio" name="E01CNTFL1" value="1" <%if (bankNew.getE01CNTFL1().equals("1")) 	out.print("checked");%>> Si
           <input type="radio" name="E01CNTFL1" value="0" <%if (bankNew.getE01CNTFL1().equals("0")) 	out.print("checked");%>> No</p>
        </td>
	    <td nowrap width="30%">
		<div align="right">M&oacute;dulo Desglose :</div>
		</td>
		<td nowrap width="25%"> 
		<select name="E01CNTTLM">
			<option value=" "
				<%if (!(bankNew.getE01CNTTLM().equals("1")
	                 || bankNew.getE01CNTTLM().equals("2")	 
	                 || bankNew.getE01CNTTLM().equals("3")))
                     	out.print("selected");%>>
			</option>
			<option value="1" <%if (bankNew.getE01CNTTLM().equals("1")) out.print("selected");%>>Diferidos del Deposito</option>
			<option value="2" <%if (bankNew.getE01CNTTLM().equals("2")) out.print("selected");%>>Desglose IBS Nativo</option>
			<option value="3" <%if (bankNew.getE01CNTTLM().equals("3")) out.print("selected");%>>Desglose IBSBRANCH</option> 
		</select>
	    </td>
	</tr>
	<tr id="trclear">

	    <td nowrap width="25%">
		<div align="right">P/Renovaci&oacute;n Prox D&iacute;a :</div>
		</td>
		<td nowrap width="20%"> 	
    		 <INPUT type="text" name="E01CNTGLR" size="2" maxlength="1" value="<%= bankNew.getE01CNTGLR().trim()%>" onKeyPress="enterInteger()">    
    		 <A href="javascript:GetCode('E01CNTGLR','STATIC_ren_pay_day.jsp')">
    		 <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a>			
	    </td>  
 
	 	
	    <td nowrap width="30%">
		<div align="right">Dias de Gracia Renovaci&oacute;n :</div>
		</td>
		<td nowrap width="25%"><input type="text" name="E01CNTGPD" size="3" maxlength="2" value="<%=bankNew.getE01CNTGPD().trim()%>"  onKeyPress="enterInteger()">
		</td>
 
	</tr>
    <tr id="trdark">

	    <td nowrap width="25%">
	      	<div align="right">No. Dias Anticipados para Enviar Aviso de Pago :</div>
	    </td>
		<td nowrap width="20%"> 	
    		 <INPUT type="text" name="E01CNTNPA" size="3" maxlength="2" value="<%= bankNew.getE01CNTNPA().trim()%>" onKeyPress="enterInteger()">    
	    </td>   
	    
		<td nowrap width="30%">
		<div align="right">Nivel Estado Financieros :</div>
		</td>
		<td nowrap width="25%">
		    <input type="text" name="E01CNTFSL" size="2" maxlength="1" value="<%=bankNew.getE01CNTFSL().trim()%>">
			<A href="javascript:GetCode('E01CNTFSL','STATIC_par_estd_finan.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a>			
		</td>	    
    </tr>
	<tr id="trclear">
	    <td nowrap width="25%">	 
		   <div align="right">Forma de Cobro para las Cuotas Vencidas :</div>
		</td>
        <td nowrap width="20%">
		   <input type="text" name="E01CNTPDP" size="2" maxlength="1" value="<%= bankNew.getE01CNTPDP().trim()%>">
 	   	   <A href="javascript:GetCode('E01CNTPDP','STATIC_par_cuota_venc.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a>	   
        </td>
 	    <td nowrap width="30%">
		<div align="right">Tipo de Previsión :</div>
		</td>    
		<td nowrap width="25%">
 		<select name="E01CNTPVF">		  		
		  <option value=" "
				<% if (!(bankNew.getE01CNTPVF().equals("N")
                      || bankNew.getE01CNTPVF().equals("C")
                  	  || bankNew.getE01CNTPVF().equals("G")))
                	out.print("selected"); %>>
          </option> 
		  <option value="N" <%if (bankNew.getE01CNTPVF().equals("N"))  out.print("selected");%>>No Previsión</option>
		  <option value="C" <%if (bankNew.getE01CNTPVF().equals("C"))  out.print("selected");%>>A Nivel de Cliente</option>
	      <option value="G" <%if (bankNew.getE01CNTPVF().equals("G"))  out.print("selected");%>>A Nivel de Grupo</option>     
		</select>  
	    </td>     	 
	 </tr>	    
	 <tr id="trdark">
	 	<td nowrap width="25%">	 
		    <div align="right">Forma de Contabl. de los Cargos por Mora :</div>
		</td>
        <td nowrap width="20%">
     	<select name="E01CNTAPI">
		    <option value=" "
				<% if (!(bankNew.getE01CNTAPI().equals("1")
                      || bankNew.getE01CNTAPI().equals("2")
                  	  || bankNew.getE01CNTAPI().equals("N")))
                	out.print("selected"); %>>
             </option>       	
			 <option value="1" <%if (bankNew.getE01CNTAPI().equals("1")) out.print("selected");%>>Contabiliza Diariamente</option> 
             <option value="2" <%if (bankNew.getE01CNTAPI().equals("2")) out.print("selected");%>>Contabiliza Cuando Cobra</option> 
             <option value="N" <%if (bankNew.getE01CNTAPI().equals("N")) out.print("selected");%>>No Calcula Mora</option> 	          	
        </select>  
        </td>

	    <td nowrap width="30%">
	    </td>
		<td nowrap width="25%"> 	    
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

