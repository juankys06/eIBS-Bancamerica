<%@ page import="datapro.eibs.sockets.*, datapro.eibs.beans.*" %>

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<%!
  // LISTA DE NOMBRES DE CAMPOS
  String PaisDsc;
  String Region;  
  String RegionDsc;
  String Estado;
  String EstadoDsc;
  String Municipio;
  String MunicipioDsc;
  String CTY;
  String Parroquia;
  String ParroquiaDsc;
  String CodigoPostal;
  String Urbanizacion;
  String Calle;
  String Casa;
  String Piso;
  String Apto;
  String Referencia;
  String ApartadoPostal;
  
  //LISTA DE VARIABLES CON LOS VALORES ASIGNADOS A LOS CAMPOS
  String paisValue="";
  String regionValue="";
  String regionDscValue="";
  String estadoValue="";
  String estadoDscValue="";
  String municipioValue="";
  String municipioDscValue="";
  String ciudadValue="";
  String ciudadDscValue="";
  String parroquiaValue="";
  String parroquiaDscValue="";
  String casaValue="";
  String pisoValue="";
  String aptoValue="";
  String referenciaValue="";
  String apartadoPostalValue="";
  
  // Variables de uso interno
  String READ_ONLY_TAG="";
  
  datapro.eibs.sockets.MessageRecord mr;
 
  // PARAMETROS
  String suffix;
  String addressType = "S";
  String messageName;
  String Title;
  String index;
  String readOnly="false";  
%>

<%
   //Obtiene el titulo del segmento de direccion
   if (request.getParameter("title") != null)
   		Title = request.getParameter("title");
   
   // Obtiene el indice del campo
   if(request.getParameter("index") != null)
   		index = request.getParameter("index").trim();
   
   System.out.println ("Index: " + index);

   // Determina si es solo lectura
   if (request.getParameter("readOnly") != null ){
   		readOnly = request.getParameter("readOnly");
   		if (readOnly.toUpperCase().equals("TRUE"))
   			READ_ONLY_TAG = "readonly";
   		else
   			READ_ONLY_TAG = "";  
   }
   
   System.out.println("Read Only? " + readOnly.toUpperCase());
   
   // Obtiene el mensaje a desplegar
   messageName = request.getParameter("messageName");
   mr = (datapro.eibs.sockets.MessageRecord) session.getAttribute(messageName);      
   
   if (mr instanceof ESD000004Message){
   		mr = (ESD000004Message) mr;
   }

   // Resuelve el nombre de los campos
   PaisDsc = "E" + index + "4CTR";
   Region = "E" + index + "4UC2"; 
   RegionDsc = "D" + index +"4UC2";
   Estado = "E" + index + "4STE";
   EstadoDsc = "D" + index + "4STE";
   Municipio = "E" + index + "4MUN";
   MunicipioDsc = "D" + index +"4MUN";
   CTY = "E" + index + "4CTY";
   Parroquia = "E" + index + "4PAR";
   ParroquiaDsc = "D" + index + "4PAR";
   CodigoPostal = "E" + index + "4ZPC";
   Urbanizacion = "E" + index + "4MA2";
   Calle = "E" + index + "4NM4";
   Casa = "E" + index + "4NM5";
   Piso = "E" + index + "4N32";
   Apto = "E" + index + "4N33";
   Referencia = "E" + index + "4NME";
   ApartadoPostal = "E" + index + "4POB"; 
   
   //Asigna el valor inicial en los campos de la forma que 
   //comparten un mismo campo en el archivo AS400.
   
   try {
		// País
   		if (mr.getField(PaisDsc).getString() != null)  
   			paisValue = mr.getField(PaisDsc).getString();
		
   		// Región (código y descripción)
   		if (mr.getField(Region).getString() != null)
   			regionValue = mr.getField(Region).getString();
          
   		if (mr.getField(RegionDsc).getString() != null)
   			regionDscValue = mr.getField(RegionDsc).getString();
   		
   		// Estado (código y descripción)
		if (mr.getField(Estado).getString() != null)  
   			estadoValue = mr.getField(Estado).getString();

   		if (mr.getField(EstadoDsc).getString() != null)  
   			estadoDscValue = mr.getField(EstadoDsc).getString();

   		// Municipio (código y descripción)
		if (mr.getField(Municipio).getString() != null)  
   			municipioValue = mr.getField(Municipio).getString();

   		if (mr.getField(MunicipioDsc).getString() != null)  
   			municipioDscValue = mr.getField(MunicipioDsc).getString();

   		// Ciudad (código y descripción)
   		String value = mr.getField(CTY).getString();
   		ciudadValue = value.substring(0,4);
   		ciudadDscValue = value.substring(4);
   		
   		// Parroquia (código y descripción)
		if (mr.getField(Parroquia).getString() != null)  
   			parroquiaValue = mr.getField(Parroquia).getString();

   		if (mr.getField(ParroquiaDsc).getString() != null)  
   			parroquiaDscValue = mr.getField(ParroquiaDsc).getString();

		// Casa/Edificio
   		if (mr.getField(Casa).getString() != null)  
   			casaValue = mr.getField(Casa).getString();    

		// Piso/Nivel
   		if (mr.getField(Piso).getString() != null)  
   			pisoValue = mr.getField(Piso).getString();    

		// Apto./Ofic./Local/Dpto.
   		if (mr.getField(Apto).getString() != null)  
   			aptoValue = mr.getField(Apto).getString();    

		// Referencia
   		if (mr.getField(Referencia).getString() != null)  
   			referenciaValue = mr.getField(Referencia).getString();    

		// Apartado Postal
   		if (mr.getField(ApartadoPostal).getString() != null)  
   			apartadoPostalValue = mr.getField(ApartadoPostal).getString();    
   }
   catch (Exception e) {}
%>

<script language="JavaScript" >

function concatCTY(evt){
	evt = (evt) ? evt : ((window.event) ? window.event : "");
	var cty = document.getElementById("Aux_CTYCode<%=index%>");
	var ctyDsc = document.getElementById("Aux_CTYDsc<%=index%>");
	var output = document.getElementById("<%=CTY%>");
	output.value = cty.value + ctyDsc.value;
} 
    
</script>


	<tr id="trclear"> 
		<td nowrap>
			<div align="right">País :</div>
		</td>
        <td nowrap>
            <input type="hidden" name="CTYCode<%=index%>" id="CTYCode<%=index%>" value="" <%=READ_ONLY_TAG%>>
            <INPUT type="text" name="<%=PaisDsc%>" id="<%=PaisDsc%>" size="21" maxlength="20" value="<%=mr.getField(PaisDsc).getString().equals("")?"Venezuela":mr.getField(PaisDsc).getString()%>" <%=READ_ONLY_TAG%>>
			<% if (!readOnly.toUpperCase().equals("TRUE")){ %>		 
			<a href="javascript:GetCodeDescCNOFC('CTYCode<%=index%>','<%=PaisDsc%>','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
			<% } %>
            <!--<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom">-->		
		</td>		
	</tr>
	
	<tr id="trdark">
		<TD nowrap>
			<div align="right">Estado :</div>
		</TD>
		<TD nowrap>
			<input type="hidden" name="<%=Estado%>" id="address.Estado<%=index%>" value="<%=estadoValue%>">
			<INPUT type="text" name="<%=EstadoDsc%>" id="address.Estado.Dsc<%=index%>" size="36" maxlength="35" value="<%=estadoDscValue%>" <%=READ_ONLY_TAG%>>
			<% if (!readOnly.toUpperCase().equals("TRUE")){ %>	
			<a href="javascript:clearMunicipio('address.Municipio<%=index%>','address.Municipio.Dsc<%=index%>','address.Parroquia<%=index%>','address.Parroquia.Dsc<%=index%>');GetCodeDescCNOFC('<%=Estado%>','<%=EstadoDsc%>','27')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
			<%}%>
        	<!--<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom">-->		
		</td>		
	</tr>
			
	<tr id="trclear">
    	<td nowrap>
    		<div align="right">Región :</div>
    	</td>
    	<td nowrap>
    		<input type="hidden" name="<%=Region%>" id="address.Region<%=index%>" value="<%=regionValue%>">
			<INPUT type="text" name="<%=RegionDsc%>" id="address.Region.Dsc<%=index%>" size="36" maxlength="35" value="<%=regionDscValue%>" <%=READ_ONLY_TAG%>>
			<% if (!readOnly.toUpperCase().equals("TRUE")){ %>		
			<a href="javascript:GetCodeDescCNOFC('<%=Region%>','<%=RegionDsc%>','07')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
			<%}%>
        	<!--<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom">-->		
		</td>	
	</tr>
			
	<tr id="trdark"> 
    	<td nowrap>
    		<div align="right">Municipio :</div>
    	</td>
        <td nowrap>
        	<input type="hidden" name="<%=Municipio%>" id="address.Municipio<%=index%>" value="<%=municipioValue%>" >
        	<INPUT type="text" name="<%=MunicipioDsc%>" id="address.Municipio.Dsc<%=index%>" size="36" maxlength="35" value="<%=municipioDscValue%>" <%=READ_ONLY_TAG%>>
        	<% if (!readOnly.toUpperCase().equals("TRUE")){ %>
			<a href="javascript:clearParroquia('address.Parroquia<%=index%>','address.Parroquia.Dsc<%=index%>');GetCNOFCFilteredCodes('address.Municipio<%=index%>','address.Municipio.Dsc<%=index%>',document.getElementById('address.Estado<%=index%>').value,'85')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
			<%}%>
        	<!--<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom">-->
		</td>
	</tr>
          
	<tr id="trclear"> 
		<td nowrap>
			<div align="right">Ciudad :</div>
		</td>
        <td nowrap>
        	<input type="hidden" name="<%=CTY%>" id="<%=CTY%>">
        	<input type="hidden" name="Aux_CTYCode<%=index%>" id="Aux_CTYCode<%=index%>" value=<%=ciudadValue%>>
        	<INPUT type="text" name="Aux_CTYDsc<%=index%>" id="Aux_CTYDsc<%=index%>" size="36" maxlength="26" value="<%=ciudadDscValue%>" <%=READ_ONLY_TAG%>>
			<% if (!readOnly.toUpperCase().equals("TRUE")){ %>		
			<a href="javascript:GetCNOFCFilteredCodes('Aux_CTYCode<%=index%>','Aux_CTYDsc<%=index%>',document.getElementById('address.Estado<%=index%>').value,'84')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
           	<%}%> 
        	<!--<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom">-->
		</td>
	</tr>		
		  
	<tr id="trdark">	
		<TD nowrap>
			<div align="right">Parroquia :</div>
		</TD>
		<TD nowrap>
        	<input type="hidden" name="<%=Parroquia%>" id="address.Parroquia<%=index%>" value="<%=parroquiaValue%>"  >
			<INPUT type="text" name="<%=ParroquiaDsc%>" id="address.Parroquia.Dsc<%=index%>" size="36" maxlength="35" value="<%=parroquiaDscValue%>" <%=READ_ONLY_TAG%>>
			<% if (!readOnly.toUpperCase().equals("TRUE")){ %>		
			<a href="javascript:GetParroquiaCodes('<%=Parroquia%>','<%=ParroquiaDsc%>',document.getElementById('address.Municipio<%=index%>').value,document.getElementById('address.Estado<%=index%>').value,'80')">
			<img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
        	<%}%>  
        	<!--<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >-->  	     		
		</TD>
	</tr>
		
	<tr id="trclear"> 
    	<td nowrap>
    		<div align="right">Código Postal :</div>
    	</td>
        <td nowrap>
			<INPUT type="text" name="<%=CodigoPostal%>" id="<%=CodigoPostal%>" size="16" maxlength="15" value="<%=mr.getField(CodigoPostal).getString()%>" <%=READ_ONLY_TAG%>>
        	<!--<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom">-->  	     		
		</td>
 	</tr>

	<tr id="trdark"> 
    	<td nowrap>
    		<div align="right">Urbanización :</div>
    	</td>
        <td nowrap>
			<INPUT type="text" name="<%=Urbanizacion%>" id="<%=Urbanizacion%>" size="16" maxlength="15" value="<%=mr.getField(Urbanizacion).getString()%>" <%=READ_ONLY_TAG%>>
        	<!--<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom">-->  	     		
		</td>
 	</tr>

	<tr id="trclear"> 
		<td nowrap>
			<div align="right">Calle/Avenida :</div>
		</td>
        <td nowrap>
        	<INPUT type="text" name="<%=Calle%>" id="<%=Calle%>" size="36" maxlength="35" value="<%=mr.getField(Calle).getString()%>" <%=READ_ONLY_TAG%>>  
			<!--<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom">-->  	     		
		</td>
	</tr>
          
	<tr id="trdark"> 
    	<td nowrap>
    		<div align="right">Casa/Edificio :</div>
    	</td>
        <td nowrap>
        	<INPUT type="text" name="<%=Casa%>" id="<%=Casa%>" size="16" maxlength="15" value="<%=casaValue%>" <%=READ_ONLY_TAG%>>
        </td>
	</tr>
           
	<tr id="trclear"> 
		<td nowrap>
			<div align="right">Piso/Nivel :</div>
		</td>
        <td nowrap>
        	<INPUT type="text" name="<%=Piso%>" id="<%=Piso%>" size="6" maxlength="5" value="<%=pisoValue%>" <%=READ_ONLY_TAG%>>
	    </td>
	</tr>
	       
	<tr id="trdark">      
		<TD nowrap>
			<div align="right">Apto./Ofic./Local/Dpto. :</div>
		</TD>
        <TD nowrap>
        	<INPUT type="text" name="<%=Apto%>" id="<%=Apto%>" size="16" maxlength="15" value="<%=aptoValue%>" <%=READ_ONLY_TAG%>>
		</TD>
	</tr>
		   
	<TR id="trclear">
		<TD nowrap>
			<div align="right">Referencia :</div>
		</TD>
		<TD nowrap>
			<input type="text" name="<%=Referencia%>" id="<%=Referencia%>" size="36" maxlength="35" value="<%=referenciaValue%>" <%=READ_ONLY_TAG%>>
		</TD>
	</TR>
 
	<TR id="trdark">
		<TD nowrap>
			<div align="right">Código Postal :</div>
		</TD>
		<TD nowrap>
			<INPUT type="text" name="<%=ApartadoPostal%>" id="<%=ApartadoPostal%>" size="11" maxlength="10" value="<%=apartadoPostalValue%>" <%=READ_ONLY_TAG%>>
        </TD>
	</TR>

<script language="JavaScript" >
	EventUtils.addEventListener(document.forms[0],'submit',concatCTY);
</script>