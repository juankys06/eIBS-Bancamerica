<%@ page import="datapro.eibs.sockets.*, datapro.eibs.beans.*" %>

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<%!
  String Title;
  
  // LISTA DE NOMBRES DE CAMPOS
  String TipoPropiedad;
  String TipoVivienda;
  String Pais;
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
  String TipoCorreo;
  String Email;
  
  //LISTA DE VARIABLES CON LOS VALORES ASIGNADOS A LOS CAMPOS
  String tipoViviendaValue = "1";
  String tipoPropiedadValue = "1";
  String paisValue = "";
  String regionValue = "";
  String regionDscValue = "";
  String estadoValue = "";
  String estadoDscValue = "";
  String municipioValue = "";
  String municipioDscValue = "";
  String ciudadValue = "";
  String ciudadDscValue = "";
  String parroquiaValue = "";
  String parroquiaDscValue = "";
  String casaValue = "";
  String pisoValue = "";
  String aptoValue = "";
  String referenciaValue = "";
  String tipoCorreoValue = "";
  String emailValue = "";
  
  datapro.eibs.sockets.MessageRecord mr;
 
  // PARAMETROS
  String suffix;
  String suffix2;
  String addressType;
  String messageName;
  String readOnly = "false";
  String READ_ONLY_TAG = "";
%>

<%
   //Obtiene el titulo del segmento de direccion
   Title = request.getParameter("title");

   // Obtiene el sufijo de los campos dependiendo del tipo de mensaje a desplegar.
   // Por ejemplo, si el mensaje es del tipo ESD008001, el sufijo enviado es E01, si el tipo es ESD008002 
   // el sufijo enviado ed E02
   
   // Determina si es solo lectura
   if (request.getParameter("readOnly") != null){
   		readOnly = request.getParameter("readOnly").toLowerCase();
   		if (readOnly.equals("true"))
   			READ_ONLY_TAG = "readonly";
   		else
   			READ_ONLY_TAG = "";  
   }
   else READ_ONLY_TAG = "";
   
   System.out.println("Read Only? " + readOnly.toUpperCase() + " ReadOnlyTag: "+ READ_ONLY_TAG);

   // Obtiene el mensaje a desplegar
   messageName = request.getParameter("messageName");
   mr = (datapro.eibs.sockets.MessageRecord) session.getAttribute(messageName);      
   
   if (mr instanceof ESD008001Message){
   		suffix = "E01";
   		suffix2 = "D01";
   		mr = (ESD008001Message) mr;
   }
   else {
   		suffix= "E02";
   		suffix2 = "D02";
   		mr = (ESD008002Message) mr;
   }
     
   // Resuelve el nombre de los campos
   TipoPropiedad = suffix + "FG1";
   TipoVivienda = suffix + "FG2";
   Pais = suffix + "CTR";
   Region = suffix + "UC8";
   RegionDsc = suffix2 + "UC8";
   Estado = suffix + "STE";
   EstadoDsc = suffix2 + "STE";
   Municipio = suffix + "MUN";
   MunicipioDsc = suffix2 + "MUN";
   CTY = suffix + "CTY";
   Parroquia = suffix + "PAR";
   ParroquiaDsc = suffix2 + "PAR";
   CodigoPostal = suffix + "ZPC";
   Urbanizacion = suffix + "NA2";
   Calle = suffix + "NM4";
   Casa = suffix + "NM5";
   Piso = suffix + "N32";
   Apto = suffix + "N33";
   Referencia = suffix + "NA4";
   TipoCorreo = suffix + "MLC";
   Email = suffix + "IAD";
   
   //Asigna el valor inicial en los campos de la forma que 
   //comparten un mismo campo en el archivo AS400.
   
   try {
   		// Tipo de Propiedad
   		if (mr.getField(TipoPropiedad).getString() != null)  
   			tipoPropiedadValue = mr.getField(TipoPropiedad).getString();
   		
   		// Tipo de Vivienda
   		if (mr.getField(TipoVivienda).getString() != null)  
   			tipoViviendaValue = mr.getField(TipoVivienda).getString(); 
     
		// País
   		if (mr.getField(Pais).getString() != null)  
   			paisValue = mr.getField(Pais).getString();
		
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

		// Tipo de Correo
   		if (mr.getField(TipoCorreo).getString() != null)  
   			tipoCorreoValue = mr.getField(TipoCorreo).getString();    

		// Correo Electrónico
   		if (mr.getField(Email).getString() != null)  
   			emailValue = mr.getField(Email).getString();    
   }
   catch (Exception e) {}
%>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/CrossBrowserFunctions.js"> </SCRIPT>
 
<h4><%=Title%></h4>
    
<script language="JavaScript" >

function clearMunicipio(){
	var munCode = document.getElementById("address.Municipio");
	var munDsc = document.getElementById("address.Municipio.Dsc");
	var parrCode = document.getElementById("address.Parroquia");
	var parrDsc = document.getElementById("address.Parroquia.Dsc");
	munCode.value = "";
	munDsc.value = "";
	parrCode.value = "";
 	parrDsc.value = "";
}
    
function clearParroquia(){
	var parrCode = document.getElementById("address.Parroquia");
	var parrDsc = document.getElementById("address.Parroquia.Dsc");
	parrCode.value = "";
	parrDsc.value = "";
}    
 
function concatCTY(evt){
	evt = (evt) ? evt : ((window.event) ? window.event : "");
	var cty = document.getElementById("address.Ciudad");
	var ctyDsc = document.getElementById("address.Ciudad.Dsc");
	var output = document.getElementById("address.CTY");
	output.value = cty.value + ctyDsc.value;
} 
    
function clearAddress(country){
	if( country != "VENEZUELA" ){
		document.forms[0].<%=Region%>.value = "" ;
		document.forms[0].<%=RegionDsc%>.value = "" ;
	     	
		document.forms[0].<%=Estado%>.value = "" ;
		document.forms[0].<%=EstadoDsc%>.value = "" ;
	     	
		document.forms[0].<%=Municipio%>.value = "" ;
		document.forms[0].<%=MunicipioDsc%>.value = "" ;
			
		document.forms[0].Aux_CTYCode.value = "" ;
		document.forms[0].Aux_CTYDsc.value = "" ;
			
		document.forms[0].<%=Parroquia%>.value = "" ;
		document.forms[0].<%=ParroquiaDsc%>.value = "" ;
			
		document.forms[0].<%=CodigoPostal%>.value = "" ;
		document.forms[0].<%=Urbanizacion%>.value = "" ;
		document.forms[0].<%=Calle%>.value = "" ;
			
		document.forms[0].<%=Casa%>.value = "" ;
		document.forms[0].<%=Piso%>.value = "" ;
		document.forms[0].<%=Apto%>.value = "" ;
			
		document.forms[0].<%=Referencia%>.value = "" ;
	}
}

</script>
   
<input type="hidden" name="<%=CTY%>" id="address.CTY" >

<table class="tableinfo" width="100%">
    <tr> 
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
        	<tr id="trclear"> 
            	<td nowrap>Tipo de Propiedad</td>
            	<td nowrap>Tipo de Vivienda</td>
				<TD nowrap></TD>
			</tr>
        	<tr id="trdark"> 
            	<td nowrap >
					<%if (!readOnly.equals("true")) {%>
                	<SELECT name="<%=TipoPropiedad%>">
                    	<OPTION value="" <%if(tipoPropiedadValue.trim().equals("")){%> Selected <%}%> ></OPTION>
 						<OPTION value="1" <%if(tipoPropiedadValue.equals("1")){%> Selected <%}%> >Propia</OPTION>
						<OPTION value="2" <%if(tipoPropiedadValue.equals("2")){%> Selected <%}%> >Alquilada</OPTION>
						<OPTION value="3" <%if(tipoPropiedadValue.equals("3")){%> Selected <%}%> >Hipotecada</OPTION>
						<OPTION value="4" <%if(tipoPropiedadValue.equals("4")){%> Selected <%}%> >De un familiar</OPTION>
					</SELECT>
			  		<%}
			    	else{
			     		if (tipoPropiedadValue.equals("1")) out.println("Propia");
			     		else if(tipoPropiedadValue.equals("2")) out.println("Alquilada"); 
			     		else if(tipoPropiedadValue.equals("3")) out.println("Hipotecada");
			     		else if(tipoPropiedadValue.equals("4")) out.println("De un familiar");
			   		}%>
				</td>
            	<td nowrap >
              		<%if(!readOnly.equals("true")) {%>
                	<SELECT name="<%=TipoVivienda%>">
                    	<OPTION value="" <%if(tipoViviendaValue.trim().equals("")){%> Selected <%}%> ></OPTION>
						<OPTION value="1" <%if(tipoViviendaValue.equals("1")){%> Selected <%}%> >Casa</OPTION>
						<OPTION value="2" <%if(tipoViviendaValue.equals("2")){%> Selected <%}%> >Apartamento</OPTION>
						<OPTION value="3" <%if(tipoViviendaValue.equals("3")){%> Selected <%}%> >Edificio</OPTION>
						<OPTION value="4" <%if(tipoViviendaValue.equals("4")){%> Selected <%}%> >Quinta</OPTION>
						<OPTION value="5" <%if(tipoViviendaValue.equals("5")){%> Selected <%}%> >Torre</OPTION>
						<OPTION value="6" <%if(tipoViviendaValue.equals("6")){%> Selected <%}%> >Conjunto Residencial</OPTION>
						<OPTION value="7" <%if(tipoViviendaValue.equals("7")){%> Selected <%}%> >Centro Comercial</OPTION>
						<OPTION value="8" <%if(tipoViviendaValue.equals("8")){%> Selected <%}%> >Local</OPTION>
						<OPTION value="9" <%if(tipoViviendaValue.equals("9")){%> Selected <%}%> >Galpon</OPTION>
						<OPTION value="A" <%if(tipoViviendaValue.equals("A")){%> Selected <%}%> >Hato</OPTION>
						<OPTION value="B" <%if(tipoViviendaValue.equals("B")){%> Selected <%}%> >Finca</OPTION>
					</SELECT>
			  		<%}
			  		else{
			      		if (tipoViviendaValue.equals("1")) out.println("Casa");
			      		else if(tipoViviendaValue.equals("2")) out.println("Apartamento");
			      		else if(tipoViviendaValue.equals("3")) out.println("Edificio");
			      		else if(tipoViviendaValue.equals("4")) out.println("Quinta");
			      		else if(tipoViviendaValue.equals("5")) out.println("Torre");
			      		else if(tipoViviendaValue.equals("6")) out.println("Conjunto Residencial");
			      		else if(tipoViviendaValue.equals("7")) out.println("Centro Comercial");
			      		else if(tipoViviendaValue.equals("8")) out.println("Local");
			      		else if(tipoViviendaValue.equals("9")) out.println("Galpon");
			      		else if(tipoViviendaValue.equals("10")) out.println("Hato");
			      		else if(tipoViviendaValue.equals("11")) out.println("Finca");
     		  		}%>
			</td>
			<TD nowrap></TD>
		</tr>
        <tr id="trclear"> 
            <td nowrap>País</td>
            <td nowrap>Región</td>
			<TD nowrap>Estado</TD>
		</tr>
        <tr id="trdark"> 
            <td nowrap>
            	<input type="hidden" name="CTRCode" id="address.Pais" value="">
            	<INPUT type="text" name="<%=Pais%>" id="address.Pais.Dsc" size="21" maxlength="20" value="<%=paisValue%>" <%=READ_ONLY_TAG%> onfocus="clearAddress(this.value);"> 
            	<%if(!readOnly.equals("true")) {%>
                <a href="javascript:GetCodeDescCNOFC('CTRCode','<%=Pais%>','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >		
            	<%}%>   
			</td>		
		    <td nowrap>
            	<input type="hidden" name="<%=Region%>" id="address.Region" value="<%=regionValue%>" >
		    	<INPUT type="text" name="<%=RegionDsc%>" id="address.Region.Dsc" size="36" maxlength="35" value="<%=regionDscValue%>" <%=READ_ONLY_TAG%>>
            	<%if(!readOnly.equals("true")) {%>
				<a href="javascript:GetCodeDescCNOFC('<%=Region%>','<%=RegionDsc%>','07')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >		
            	<%}%>  
			</td>	
		    <TD nowrap>
		    	<input type="hidden" name="<%=Estado%>" id="address.Estado" value="<%=estadoValue%>" >
		    	<INPUT type="text" name="<%=EstadoDsc%>" id="address.Estado.Dsc" size="35" maxlength="36" value="<%=estadoDscValue%>" <%=READ_ONLY_TAG%>>
            	<%if(!readOnly.equals("true")) {%>
			    <a href="javascript:clearMunicipio();GetCodeDescCNOFC('<%=Estado%>','<%=EstadoDsc%>','27')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >		
            	<%}%>  
			</td>		
		<tr id="trclear"> 
            <td nowrap>Municipio</td>
            <td nowrap>Ciudad</td>
			<TD nowrap>Parroquia</TD>
		</tr>
        <tr id="trdark"> 
            <td nowrap>
            	<input type="hidden" name="<%=Municipio%>" id="address.Municipio" value="<%=municipioValue%>">
            	<input type="text" name="<%=MunicipioDsc%>" id="address.Municipio.Dsc" size="36" maxlength="35" value="<%=municipioDscValue%>" <%=READ_ONLY_TAG%>>
            	<%if(!readOnly.equals("true")) {%>
                <a href="javascript:clearParroquia();GetCNOFCFilteredCodes('<%=Municipio%>','<%=MunicipioDsc%>',document.getElementById('address.Estado').value,'85')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >
            	<%}%>
			</td>
            <td nowrap>
            	<input type="hidden" name="Aux_CTYCode"  id="address.Ciudad" value=<%=ciudadValue%> >
            	<input type="text" name="Aux_CTYDsc" id="address.Ciudad.Dsc" size="27" maxlength="26" value="<%=ciudadDscValue%>" <%=READ_ONLY_TAG%>>
            	<%if(!readOnly.equals("true")) {%>
 				<a href="javascript:GetCNOFCFilteredCodes('Aux_CTYCode','Aux_CTYDsc',document.getElementById('address.Estado').value,'84')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" >		
            	<%}%>   
			</td>
			<TD nowrap>
				<input type="hidden" name="<%=Parroquia%>" id="address.Parroquia" value="<%=parroquiaValue%>" >
				<input type="text" name="<%=ParroquiaDsc%>" id="address.Parroquia.Dsc" size="36" maxlength="35" value="<%=parroquiaDscValue%>" <%=READ_ONLY_TAG%>>
				<% if (!readOnly.toUpperCase().equals("TRUE")){ %>		
		    	<a href="javascript:GetParroquiaCodes('<%=Parroquia%>','<%=ParroquiaDsc%>',document.getElementById('address.Municipio').value,document.getElementById('address.Estado').value,'80')">
		    	<img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
            	<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >  	     		
            	<%}%>  
			</TD>
		</tr>
        <tr id="trclear"> 
            <td nowrap>Código Postal</td>
            <td nowrap>Urbanización</td>
            <td nowrap>Calle/Avenida</td>
        </tr>
        <tr id="trdark"> 
            <td nowrap>
            	<INPUT type="text" name="<%=CodigoPostal%>" id="address.CodigoPostal" size="11" maxlength="10" value="<%=mr.getField(CodigoPostal).getString()%>" <%=READ_ONLY_TAG%>>
            </td>
            <td nowrap>
            	<INPUT type="text" name="<%=Urbanizacion%>" id="address.Urbanizacion" size="36" maxlength="35" value="<%=mr.getField(Urbanizacion).getString()%>" <%=READ_ONLY_TAG%>>
            </td>
            <td nowrap>
            	<INPUT type="text" name="<%=Calle%>" id="address.Calle" size="36" maxlength="35" value="<%=mr.getField(Calle).getString()%>" <%=READ_ONLY_TAG%>>
            </td>
        </tr>
        <tr id="trclear"> 
            <td nowrap>Casa/Edificio</td>
            <td nowrap>Piso/Nivel</td>
			<TD nowrap>Apto./Ofic./Local/Dpto.</TD>
		</tr>
        <tr id="trdark"> 
            <td nowrap>
            	<INPUT type="text" name="<%=Casa%>" id="address.Casa" size="47" maxlength="45" value="<%=casaValue%>" <%=READ_ONLY_TAG%>>
            </td>
            <td nowrap>
            	<INPUT type="text" name="<%=Piso%>" id="address.Piso" size="7" maxlength="5" value="<%=pisoValue%>" <%=READ_ONLY_TAG%>>
			</td>
			<TD nowrap>
				<input type="text" name="<%=Apto%>" id="address.Apto" size="17" maxlength="15" value="<%=aptoValue%>" <%=READ_ONLY_TAG%>>
			</TD>
		</tr>
		<TR id="trclear">
			<TD nowrap>Referencia</TD>
			<TD nowrap>Tipo de Correo</TD>
			<TD nowrap>Correo Electrónico</TD>
		</TR>
		<TR id="trdark">
			<TD nowrap>
				<input type="text" name="<%=Referencia%>" id="address.Referencia" size="36" maxlength="35" value="<%=referenciaValue%>" <%=READ_ONLY_TAG%>>
			</TD>
			<TD nowrap>
				<INPUT type="text" name="<%=TipoCorreo%>" id="address.TipoCorreo" size="4" maxlength="5" value="<%=tipoCorreoValue%>">
			</TD>
			<TD nowrap>
				<INPUT type="text" name="<%=Email%>" id="address.email" size="41" maxlength="40" value="<%=emailValue%>">
			</TD>
		</TR>
      </table>
    </td>
  </tr>
</table>
    
<script language="JavaScript" >
	EventUtils.addEventListener(document.forms[0],'submit',concatCTY);
</script>