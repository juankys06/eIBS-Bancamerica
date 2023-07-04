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
  String MunicipioDsc;
  String Parroquia; //Codigo de parroquia, incluye codigo de estado y municipio
  String ParroquiaDsc;
  String Estado;
  String EstadoDsc;
  String CTY; // Incluye el codigo de ciudad y su descripcion
  String Calle;
  String NA3;   //Incluye casa, piso, apto.
  String Referencia;
  String CodigoPostal="";
  String TipoCorreo="";
  String Email="";
  String ApartadoPostal;
  
  //LISTA DE VARIABLES CON LOS VALORES ASIGNADOS A LOS CAMPOS
  String tipoViviendaValue ="1";
  String tipoPropiedadValue="1";
  String paisValue="";
  String regionValue="";
  String regionDscValue="";
  String estadoDscValue="";
  String estadoValue="";
  String ciudadValue="";
  String ciudadDscValue="";
  String municipioValue="";
  String municipioDscValue="";
  String parroquiaValue="";
  String parroquiaDscValue="";
 
  String casaValue="";
  String pisoValue="";
  String aptoValue="";
  String referenciaValue="";
  
  
  
  datapro.eibs.sockets.MessageRecord mr;
 
  // PARAMETROS
   
  String suffix;
  String suffix2;
  String addressType;
  String messageName;
  String readOnly="false";
  String READ_ONLY_TAG="";
         
%>

<%
    //Se inician los valores de los campos
    
  tipoViviendaValue ="1";
  tipoPropiedadValue="1";
  paisValue="";
  regionValue="";
  regionDscValue="";
  estadoDscValue="";
  estadoValue="";
  ciudadValue="";
  ciudadDscValue="";
  municipioValue="";
  municipioDscValue="";
  parroquiaValue="";
  parroquiaDscValue="";
 
  casaValue="";
  pisoValue="";
  aptoValue="";
  referenciaValue="";
    

   //Obtiene el titulo del segmento de direccion
   Title = request.getParameter("title");

   // Obtiene el sufijo de los campos dependiendo del tipo de mensaje a desplegar.
   // Por ejemplo, si el mensaje es del tipo ESD008001, el sufijo enviado es E01, si el tipo es ESD008002 
   // el sufijo enviado ed E02
   
    // Determina si es solo lectura
   if (request.getParameter("readOnly") != null ){
     readOnly=request.getParameter("readOnly").toLowerCase();
     if (readOnly.equals("true"))
       READ_ONLY_TAG="readonly";
     else
       READ_ONLY_TAG="";  
   }
   else READ_ONLY_TAG="";
   System.out.println("Read Only? " + readOnly.toUpperCase() + " ReadOnlyTag: "+ READ_ONLY_TAG);
   

   // Obtiene el mensaje a desplegar
   messageName = request.getParameter("messageName");
   mr= (datapro.eibs.sockets.MessageRecord) session.getAttribute(messageName);      
   
   if (mr instanceof ESD008001Message){
     suffix = "E01";
     suffix2="D01";
     mr = (ESD008001Message) mr;
     }
   else {
     suffix= "E02";
     suffix2="D02";
     mr = (ESD008002Message) mr;
     }
     
   // Resuelve el nombre de los campos
   TipoPropiedad = suffix + "FG1";
   TipoVivienda = suffix + "FG2";
   Pais = suffix + "CTR";
   Region = suffix + "UC8"; // Incluye region, municipio, parroquia
   RegionDsc = suffix2+"UC8";
   Estado = suffix + "STE";
   EstadoDsc=suffix2+"STE";
   MunicipioDsc = suffix2+"PI4";
   Parroquia = suffix + "UC7";
   
   if (readOnly.equals("true"))
     ParroquiaDsc = suffix2+"UC7";
   else
     ParroquiaDsc = suffix2+"UC4";
   
   CTY = suffix + "CTY";
   CodigoPostal = suffix + "ZPC";
   TipoCorreo=suffix+"MLC";
   Email = suffix + "IAD";
   Calle = suffix + "NA2";
   NA3  = suffix + "NA3"; //Incluye casa, piso, apto.
   Referencia = suffix + "NA4";;
   
   
   
   //Asigna el valor inicial en los campos de la forma que 
   //comparten un mismo campo en el archivo AS400.
   
   //Estado, Municipio y parroquia
    if (mr.getField(Estado).getString() !=null)  
       estadoValue=mr.getField(Estado).getString();
     if( mr.getField(Parroquia)!= null &&  mr.getField(Parroquia).getString().trim().length()>0){
       municipioValue=estadoValue+mr.getField(Parroquia).getString().substring(0,2);
       parroquiaValue = estadoValue+ mr.getField(Parroquia).getString();
       System.out.println("estado Value:" + estadoValue);
       System.out.println("municipio Value:" + municipioValue);
       System.out.println("parroquia Value "+ mr.getField(Parroquia).getString());
     }


   //Descripciones de Region, Municipio, Parroquia
   try {
     if (mr.getField(Region).getString() !=null)
       regionValue= mr.getField(Region).getString();
          
     if (mr.getField(RegionDsc).getString() !=null)
       regionDscValue= mr.getField(RegionDsc).getString();
       
     if (mr.getField(MunicipioDsc).getString() !=null)  
       municipioDscValue= mr.getField(MunicipioDsc).getString();
     System.out.println ("Muncipio DSC: " + mr.getField(MunicipioDsc).getString()); 
       
     if (mr.getField(ParroquiaDsc).getString() !=null)  
       parroquiaDscValue= mr.getField(ParroquiaDsc).getString();
     System.out.println ("Parroquia DSC: " + mr.getField(ParroquiaDsc).getString()); 
     
    // if (mr.getField(Estado).getString() !=null)  
      // estadoValue=mr.getField(Estado).getString();
       
     if (mr.getField(EstadoDsc).getString() !=null)  
       estadoDscValue=mr.getField(EstadoDsc).getString();
       
     if (mr.getField(TipoPropiedad).getString() !=null)  
       tipoPropiedadValue=mr.getField(TipoPropiedad).getString();
       
     if (mr.getField(TipoVivienda).getString() !=null)  
       tipoViviendaValue=mr.getField(TipoVivienda).getString(); 
     
     System.out.println("Tipo Propiedad/Vivienda :"+tipoPropiedadValue+"/"+tipoViviendaValue);
       
     if (mr.getField(Referencia).getString() !=null)  
       referenciaValue=mr.getField(Referencia).getString();    
   }
   catch (Exception e) {}

   //Casa/Edificio, Piso/Nivel, Apto/Oficina
   try {
     String value = mr.getField(NA3).getString();
     System.out.println("NA3 :"+value);
     
     if (value.length() > 0 && value.length() < 15)
       casaValue= value.substring(0,value.length());
 
     if (value.length() > 15 && value.length() < 20){
       casaValue=value.substring(0,15);
       pisoValue=value.substring(15,value.length());  
     }  
     
     if (value.length() > 20){
       casaValue=value.substring(0,15);
       pisoValue= value.substring(15,20);
       aptoValue= value.substring(20,value.length());
     }  
   }
   catch (Exception e) {} 

     //Ciudad
   try {
     String value = mr.getField(CTY).getString();
     ciudadValue = "";
     ciudadDscValue= value;
   }
   catch (Exception e) {}   


%>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/CrossBrowserFunctions.js"> </SCRIPT>
 

  <h4><%=Title%></h4>
    
<script language="JavaScript" >

    function clearMunicipio(){
    
      var munCode = document.getElementById("Aux_Municipio");
      var munDsc = document.getElementById("<%=MunicipioDsc%>");
      var parrCode=document.getElementById("Aux_Parroquia");
      var parrDsc=document.getElementById("address.ParroquiaDsc");
      munCode.value="";
      munDsc.value="";
      parrCode.value="";
      parrDsc.value="";
    }
    
    function clearParroquia(){
    
      var parrCode=document.getElementById("Aux_Parroquia");
      var parrDsc=document.getElementById("address.ParroquiaDsc");
      parrCode.value="";
      parrDsc.value="";
    }    
 
  
    function concatFields(field1,field2,field3,output){
      output.value=field1.value + field2.value + field3.value;
    }
    
    function concatCTY(evt){
      evt = (evt) ? evt : ((window.event) ? window.event : "");
      var cty= document.getElementById("address.Ciudad");
      var ctyDsc= document.getElementById("address.CiudadDsc");
      var output = document.getElementById("address.CTY");
      //output.value= cty.value + ctyDsc.value;
      output.value= ctyDsc.value;
      
      //Envia la parroquia
      var parroquiaCode= document.getElementById("Aux_Parroquia");
      var output2=document.getElementById("address.Parroquia");
      output2.value=parroquiaCode.value.substring(2);
     } 
    
    function concatNA3(evt){
      evt = (evt) ? evt : ((window.event) ? window.event : "");
      var CasaEdif= document.getElementById("address.Aux_CasaEdif");
      var PisoNivel= document.getElementById("address.Aux_PisoNivel");
      var AptoOfi= document.getElementById("address.Aux_AptoOfi");
      var output = document.getElementById("address.NA3");
      while (CasaEdif.value.length < 15 )CasaEdif.value = CasaEdif.value+ ' ';
      while (PisoNivel.value.length < 5) PisoNivel.value = PisoNivel.value + ' ';
      while (AptoOfi.length < 15) AptoOfi.value = AptoOfi.value + ' ';
       concatFields(CasaEdif,PisoNivel,AptoOfi,output);
     }       
     
     function clearAdress( country ){
     
	     if( country != "VENEZUELA" ){
	     	
	     	document.forms[0].<%=Region%>.value = "" ;
	     	document.forms[0].<%=RegionDsc%>.value = "" ;
	     	
	     	document.forms[0].<%=Estado%>.value = "" ;
	     	document.forms[0].<%=EstadoDsc%>.value = "" ;
	     	
	     	document.forms[0].Aux_Municipio.value = "" ;
			document.forms[0].<%=MunicipioDsc%>.value = "" ;
			
			document.forms[0].Aux_CTYCode.value = "" ;
			document.forms[0].Aux_CTYDsc.value = "" ;
			
			document.forms[0].Aux_Parroquia.value = "" ;
			document.forms[0].<%=ParroquiaDsc%>.value = "" ;
			
			document.forms[0].<%=CodigoPostal%>.value = "" ;
			document.forms[0].<%=Calle%>.value = "" ;
			
			document.forms[0].Aux_CasaEdif.value = "" ;
			document.forms[0].Aux_PisoNivel.value = "" ;
			
			document.forms[0].Aux_AptoOfi.value = "" ;
			
			document.forms[0].<%=Referencia%>.value = "" ;
	     	
	     }
     	
     }    
     
  </script>
   
   <input type="hidden" name="<%=CTY%>" id="address.CTY" >
   <input type="hidden" name="<%=NA3%>" id="address.NA3" >
   <input type="hidden" name="<%=Parroquia%>" id="address.Parroquia" >

  <table class="tableinfo" width="100%" >
    <tr > 
        <td nowrap >
          
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap  >Tipo de Propiedad</td>
            <td nowrap >Tipo de Vivienda</td>
				<TD nowrap >&nbsp;</TD>
			</tr>
          <tr id="trdark"> 
            <td nowrap >
              <%if (!readOnly.equals("true")) {%>
                <SELECT name="<%=TipoPropiedad%>">
                    <OPTION value=""  <%if(tipoPropiedadValue.trim().equals("")){%> Selected <%}%> ></OPTION>
 					<OPTION value="1" <%if(tipoPropiedadValue.equals("1")){%> Selected <%}%> >Propia   </OPTION>
					<OPTION value="2" <%if(tipoPropiedadValue.equals("2")){%> Selected <%}%> >Alquilada </OPTION>
					<OPTION value="3" <%if(tipoPropiedadValue.equals("3")){%> Selected <%}%> >Hipotecada  </OPTION>
					<OPTION value="4" <%if(tipoPropiedadValue.equals("4")){%> Selected <%}%> >De un familiar  </OPTION>

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
                    <OPTION value=""  <%if(tipoViviendaValue.trim().equals("")){%> Selected <%}%> ></OPTION>
					<OPTION value="1"  <%if(tipoViviendaValue.equals("1")){%> Selected <%}%> >Casa  </OPTION>
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
			<TD nowrap >&nbsp;</TD>
			</tr>
          <tr id="trclear"> 
            <td nowrap >País</td>
            <td nowrap >Región</td>
				<TD nowrap >Estado
				</TD>
			</tr>
          
          <tr id="trdark"> 
            <td nowrap >
            <input type="hidden" name="CTRCode" id="address.Pais" value="" >
            <INPUT type="text" name="<%=Pais%>" id="address.PaisDsc" size="21" value="<%=mr.getField(Pais).getString()%>" <%=READ_ONLY_TAG%> onfocus="clearAdress( this.value );" maxlength="20"> 
              <%if(!readOnly.equals("true")) {%>
                 <a href="javascript:GetCodeDescCNOFC('CTRCode','<%=Pais%>','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
                 <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >		
              <%}%>   
			</td>		
		    <td nowrap >
             <input type="hidden" name="<%=Region%>" id="address.Region" value="<%=regionValue%>" >
		     <INPUT type="text" name="<%=RegionDsc%>" id="address.RegionDsc" size="36" value="<%=regionDscValue%>" <%=READ_ONLY_TAG%> maxlength="35">
              <%if(!readOnly.equals("true")) {%>
				<a href="javascript:GetCodeDescCNOFC('<%=Region%>','<%=RegionDsc%>','07')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >		
              <%}%>  
			</td>	
		    <TD nowrap >
		      <input type="hidden" name="<%=Estado%>"  id="<%=Estado%>" value="<%=estadoValue%>" >
		      <INPUT type="text" name="<%=EstadoDsc%>" id="address.EstadoDsc" 	size="35" value="<%=estadoDscValue%>"  <%=READ_ONLY_TAG%> maxlength="36">
              <%if(!readOnly.equals("true")) {%>
			    <a href="javascript:clearMunicipio();GetCodeDescCNOFC('<%=Estado%>','<%=EstadoDsc%>','27')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >		
              <%}%>  
			</td>		
		  <tr id="trclear"> 
            <td nowrap >Municipio</td>
            <td nowrap >Ciudad</td>
				<TD nowrap >Parroquia</TD>
			</tr>
          <tr id="trdark"> 
            <td nowrap >
            <input type="hidden" name="Aux_Municipio" id="Aux_Municipio"  value="<%=municipioValue%>">
            <input type="text" 	name="<%=MunicipioDsc%>" size="36" id="address.Municipio.Dsc"  value="<%=municipioDscValue%>" <%=READ_ONLY_TAG%> maxlength="35">
              <%if(!readOnly.equals("true")) {%>
                <a href="javascript:clearParroquia();GetCNOFCFilteredCodes('Aux_Municipio','<%=MunicipioDsc%>',document.getElementById('<%=Estado%>').value,'85')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >
              <%}%>
			</td>
            <td nowrap >
            <input type="hidden" name="Aux_CTYCode"  id="address.Ciudad" value=<%=ciudadValue%> >
            <input type="text" name="Aux_CTYDsc" id="address.CiudadDsc" size="27" value="<%=ciudadDscValue%>" <%=READ_ONLY_TAG%> maxlength="26">
              <%if(!readOnly.equals("true")) {%>
 				 <a href="javascript:GetCNOFCFilteredCodes('Aux_CTYCode','Aux_CTYDsc',document.getElementById('<%=Estado%>').value,'84')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
                 <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" >		
              <%}%>   
			</td>
			<TD nowrap >
			  <input type="hidden" name="Aux_Parroquia" id="Aux_Parroquia" value="<%=parroquiaValue%>" >
			  <input type="text" name="<%=ParroquiaDsc%>" id="address.ParroquiaDsc" size="36" value="<%=parroquiaDscValue%>" <%=READ_ONLY_TAG%> maxlength="35">
			 <% if (!readOnly.toUpperCase().equals("TRUE")){ %>		
		      <a href="javascript:GetParroquiaCodes('Aux_Parroquia','<%=ParroquiaDsc%>',document.getElementById('Aux_Municipio').value,document.getElementById('<%=Estado%>').value,'80')">
		      <img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >  	     		
             <%}%>  
			</TD>
		</tr>


          <tr id="trclear"> 
            <td nowrap >Urbanizacion</td>
            <td nowrap colspan="2" align="left">Calle/Avenida</td></tr>
          <tr id="trdark"> 
            <td nowrap ><INPUT type="text"
					name="<%=CodigoPostal%>" id="address.CodigoPostal" size="11" value="<%=mr.getField(CodigoPostal).getString()%>" <%=READ_ONLY_TAG%> maxlength="10"></td>
            <td nowrap  colspan="2"><INPUT type="text"
					name="<%=Calle%>" id="address.Calle" size="36" value="<%=mr.getField(Calle).getString()%>" <%=READ_ONLY_TAG%> maxlength="35"></td></tr>
          <tr id="trclear"> 
            <td nowrap >Casa/Edificio</td>
            <td nowrap >Piso/Nivel</td>
				<TD nowrap >Apto./Ofic./Local/Dpto.</TD>
			</tr>
          <tr id="trdark"> 
            <td nowrap ><INPUT type="text"
					name="Aux_CasaEdif" id="address.Aux_CasaEdif" size="17"
					 value="<%=casaValue%>"  <%=READ_ONLY_TAG%> maxlength="15"></td>
            <td nowrap ><INPUT type="text"
					name="Aux_PisoNivel" id="address.Aux_PisoNivel" size="7"
					 value="<%=pisoValue%>" <%=READ_ONLY_TAG%> maxlength="5">
			</td>
			 <TD nowrap >
			 <input type="text"   name="Aux_AptoOfi" id="address.Aux_AptoOfi" size="17" value="<%=aptoValue%>"  <%=READ_ONLY_TAG%> maxlength="15">
			  </TD>
			</tr>
			<TR>
				<TD nowrap>Referencia</TD>
				<TD nowrap>Tipo de Correo</TD>
				<TD nowrap>Correo Electronico</TD>
			</TR>
			<TR>
				<TD nowrap>
				<input type="text" name="<%=Referencia%>"
					value="<%=referenciaValue%>" <%=READ_ONLY_TAG%> size="36"
					maxlength="35 "></TD>
				<TD nowrap><INPUT type="text" name="<%=TipoCorreo%>"
					id="address.TipoCorreo" size="4"
					value="<%=mr.getField(TipoCorreo).getString()%>" maxlength="5"></TD>
				<TD nowrap><INPUT type="text" name="<%=Email%>" id="address.email"
					size="41" value="<%=mr.getField(Email).getString()%>"
					maxlength="40"></TD>
			</TR>
			
        </table>
        </td>
      </tr>
    </table>
    
    <script language="JavaScript" >
       //util = new EventUtils();
       EventUtils.addEventListener(document.forms[0],'submit',concatNA3);
       EventUtils.addEventListener(document.forms[0],'submit',concatCTY);
    </script>

