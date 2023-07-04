<%@ page import="datapro.eibs.sockets.*, datapro.eibs.beans.*" %>
<%!
  String Title;
  // LISTA DE NOMBRES DE CAMPOS
  String Nombre;
  String Pais;
  String RegionMunPar;  // Incluye region, municipio, parroquia
  String Estado;
  String Ciudad;
  String Calle;
  String Ubicacion;   //Incluye casa, piso, apto.
  String Ciudadania;
  String Nombramiento;
  String Fecha1, Fecha2, Fecha3;
  String Profesion;
  String EnCalidad;
  String EstadoCivil;
  String Sexo;
  String Telefono;
  String Id;
  String TipoId;
  String PaisId;
 

  
  //LISTA DE VARIABLES CON LOS VALORES ASIGNADOS A LOS CAMPOS
  String estadoValue="";
  String regionValue="";
  String municipioValue="";
  String parroquiaValue="";
  String casaValue="";
  String pisoValue="";
  String aptoValue="";
  
  
  
  datapro.eibs.sockets.MessageRecord mr;
 
  // PARAMETROS
   
  String suffix;
  String addressType = "S";
  String messageName;
  String Title;
  String index;       
%>

<%
   //Obtiene el titulo del segmento de direccion
   if (request.getParameter("title") != null)
     Title = request.getParameter("title");
   
   //Obtiene el tipo de direccion: Si es para la pantalla de
   // Referencia (R) o  Direcciones Secundarias (S)
   if (request.getParameter("addressType")!= null)
     addressType=request.getParameter("addressType");

   // Obtiene el sufijo de los campos dependiendo del tipo de mensaje a desplegar.
   // Por ejemplo, si el mensaje es del tipo ESD008001, el sufijo enviado es E01, si el tipo es ESD008002 
   // el sufijo enviado ed E02
   
   suffix = request.getParameter("suffix");
   index= suffix.substring(1,2);

   // Obtiene el mensaje a desplegar
   messageName = request.getParameter("messageName");
   mr= (datapro.eibs.sockets.MessageRecord) session.getAttribute(messageName);      
   
   if (mr instanceof ESD000004Message){
     mr = (ESD000004Message) mr;
   }

     
   // Resuelve el nombre de los campos
   Nombre=suffix+"MA1"
   Pais = suffix + "CTR";
   RegionMunPar = suffix + "POB"; // Incluye region, municipio, parroquia
   Estado = suffix + "STE";
   Ciudad = suffix + "CTY";
   CodigoPostal = suffix + "ZPC";
   Calle = suffix + "MA2";
   Ubicacion  = suffix + "MA3"; //Incluye casa, piso, apto.
   Ciudadania=suffix+BNC
   Nombramiento = suffix + "MA4";
   Fecha1 = suffix + "DT1";
   Fecha2 = suffix + "DT2";
   Fecha3 =suffix + "DT3";
   Profesion = suffix+"MLC";
   EnCalidad= suffix +"INC";
   EstadoCivil=suffix+"BMS";
   Sexo = suffix+"BSX";
   Telefono=suffix + "HPN";
   Id = suffix+ "BNI";
   TipoId = suffix + "TID";
   PaisId= suffix +"PID";
   
   
   
   //Asigna el valor inicial en los campos de la forma que 
   //comparten un mismo campo en el archivo AS400.
   
   //Region, Municipio, Parroquia
   try {
     String value = mr.getField(RegionMunPar).getString();
     regionValue= value.substring(0,3);
     municipioValue= value.substring(3,6);
     parroquiaValue= value.substring(6,10);
   }
   catch (Exception e) {}
   
   //Casa/Edificio, Piso/Nivel, Apto/Oficina
   try {
     String value = mr.getField(Ubicacion).getString();
     casaValue= value.substring(0,15);
     pisoValue= value.substring(15,20);
     aptoValue= value.substring(20,35);
   }
   catch (Exception e) {}    
   

%>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/CrossBrowserFunctions.js"> </SCRIPT>
  <script language="JavaScript" >
  
     
    function concat<%=Region%>(evt){
      evt = (evt) ? evt : ((window.event) ? window.event : "");
      var region= document.getElementById("address.Region");
      var municipio= document.getElementById("address.Municipio");
      var parroquia= document.getElementById("address.Parroquia");
      var output = document.getElementById("address.RegionMunPar");
      while (region.value.length < 3 )region.value = region.value+ ' ';
      while (municipio.value.length < 3) municipio.value = municipio.value + ' ';
      while (parroquia.length < 4) parroquia.value = parroquia.value + ' ';
      output.value=region.value +municipio.value+ parroquia.value;
    } 
    
    function concat<%=Ubicacion%>(evt){
      evt = (evt) ? evt : ((window.event) ? window.event : "");
      var CasaEdif= document.getElementById("address.CasaEdif");
      var PisoNivel= document.getElementById("address.PisoNivel");
      var AptoOfi= document.getElementById("address.AptoOfi");
      var output = document.getElementById("address.Ubicacion");
      while (CasaEdif.value.length < 15 )CasaEdif.value = CasaEdif.value+ ' ';
      while (PisoNivel.value.length < 5) PisoNivel.value = PisoNivel.value + ' ';
      while (AptoOfi.length < 15) AptoOfi.value = AptoOfi.value + ' ';
      output.value=CasaEdif.value + PisoNivel.value + AptoOfi.value;
    }       
  </script>

 <%--  <h4><%=Title%></h4> --%>
   <input type="hidden" name="<%=RegionMunPar%>" id="address.RegionMunPar" >
   <input type="hidden" name="<%=Ubicacion%>" id="address.Ubicacion" >
 
  
  <table id="mainTable<%= index %>" class="tableinfo" width="100%">
     <tr bordercolor="#FFFFFF"> 
        <td nowrap >
         <table cellspacing="0" cellpadding="2" width="100%" border="0" align="center">
          <tr id="trclear">
		    <td nowrap width="42%">
                  Nombre Legal :
               </td>			
		    <td nowrap width="58%">
		       <INPUT type="text" name="<%Nombre%>" id="address.Name"
					size="20" value="<%=regionValue%>">
			    <a href="javascript:GetCodeCNOFC('Region','07')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >		
			</td>
		   </tr>
          <tr id="trdark"> 
           <td nowrap width="42%">Pais</td>
            <td nowrap width="58%"><INPUT type="text" name="<%=Pais%>" id="address.Pais"
					size="20" value="<%=mr.getField(Pais).getString()%>"> 
			   <a href="javascript:GetCodeCNOFC('<%=Pais%>','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >		
			</td>
		   </tr>
		   <tr id="trclear">
		    <td nowrap width="42%">Región</td>			
		    <td nowrap width="58%">
		       <INPUT type="text" name="Region" id="address.Region"
					size="20" value="<%=regionValue%>">
			    <a href="javascript:GetCodeCNOFC('Region','07')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >		
			</td>
		   </tr>
		   <tr id="trdark">	
		    <TD nowrap width="42%">Estado</TD>
		    <TD nowrap width="58%">
		      <INPUT type="text" name="<%=Estado%>" id="address.Estado" 
					size="20" value="<%=mr.getField(Estado).getString()%>">
			    <a href="javascript:GetCodeCNOFC('<%=Estado%>','27')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
               <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >		
			</td>
			</tr>		
		  <tr id="trclear"> 
            <td nowrap width="42%">Municipio</td>
             <td nowrap width="58%">
            <INPUT type="text" 	name="Municipio" size="20" id="address.Municipio"
             value="<%=municipioValue%>" >
			   <a href="javascript:GetCodeCNOFC('Municipio','85')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >
			</td>
		   </tr>
		   <tr id="trdark">	
             <td nowrap width="42%">Ciudad</td>
              <td nowrap width="58%"><INPUT type="text" name="<%=Ciudad%>" id="address.Ciudad"
					size="20" value="<%=mr.getField(Ciudad).getString()%>">
				 <a href="javascript:GetCodeCNOFC('<%=Ciudad%>','84')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >		
			 </td>
		    </tr>
		    <tr id="trclear">		
			  <TD nowrap width="42%">Parroquia</TD>
			  <TD nowrap width="58%"><INPUT type="text" name="Parroquia" id="address.Parroquia"
					size="20" value="<%=parroquiaValue%>" >
		       <a href="javascript:GetCodeCNOFC('Parroquia','80')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >  	     		
			 </TD>
			</tr>
           <tr id="trdark"> 
             <td nowrap width="42%">Codigo Postal/Urbanizacion</td>
             <td nowrap width="58%"><INPUT type="text"
					name="<%=CodigoPostal%>" id="address.CodigoPostal" size="20" value="<%=mr.getField(CodigoPostal).getString()%>"></td>
           </tr> 
           <tr id="trclear">
             <td nowrap width="42%"  align="center">Calle/Avenida</td>
             <td nowrap width="58%" ><INPUT type="text"
					name="<%=Calle%>" id="address.Calle" size="54" value="<%=mr.getField(Calle).getString()%>"></td>
		   </tr>
           <tr id="trdark"> 
            <td nowrap width="42%">Casa/Edificio</td>
             <td nowrap width="58%"><INPUT type="text"
					name="CasaEdif" id="address.CasaEdif" size="20"
					 value="<%=casaValue%>" onchange="concatUbicacion();"></td>
	       </tr>
	       <tr id="trclear">
             <td nowrap width="42%">Piso/Nivel</td>
              <td nowrap width="58%"><INPUT type="text"
					name="PisoNivel" id="address.PisoNivel" size="20"
					 value="<%=pisoValue%>" onchange="concatUbicacion();">
			  </td>
			</tr>
		   <tr id="trdark">	
				<TD nowrap width="42%">Apto./Ofic./Local/Dpto.</TD>
				<TD nowrap width="58%"><INPUT type="text"
				    name="AptoOfi" id="address.AptoOfi"
					size="20" value="<%=aptoValue%>" onchange="concatUbicacion();">
			  </TD>
		   </tr>
		  <tr id="trclear">	
				<TD nowrap width="42%">Ciudadanía</TD>
				<TD nowrap width="58%"><INPUT type="text"
				    name="<%=Ciudadania%>" id="address.AptoOfi"
					size="20" value="<%=mr.getField(Ciudadania).getString()%>" onchange="concatUbicacion();">
			  </TD>
		   </tr>
            <tr id="trclear">	
				<TD nowrap width="42%">Nombramiento</TD>
				<TD nowrap width="58%"><INPUT type="text"
				    name="<%=Nombramiento%>" id="address.AptoOfi"
					size="20" value="<%=mr.getField(Nombramiento).getString()%>" onchange="concatUbicacion();">
			  </TD>
		   </tr>
		    <tr id="trclear">	
				<TD nowrap width="42%">Fecha</TD>
				<TD nowrap width="58%"><INPUT type="text" name="<%=Fecha1%>"
					id="address.AptoOfi" size="3" value="<%=mr.getField(Fecha1).getString()%>"
					onchange="concatUbicacion();" maxlength="2">
			  <INPUT type="text" name="<%=Fecha2%>" id="address.AptoOfi0"
					size="3" value="<%=mr.getField(Fecha2).getString()%>"
					onchange="concatUbicacion();" maxlength="2"><INPUT type="text"
					name="<%=Fecha3%>" id="address.AptoOfi1" size="3"
					value="<%=mr.getField(Fecha3).getString()%>"
					onchange="concatUbicacion();" maxlength="2"></TD>
		   </tr>
		    <tr id="trclear">	
				<TD nowrap width="42%">Profesión</TD>
				<TD nowrap width="58%"><INPUT type="text"
				    name="<%=Profesion%>" id="address.AptoOfi"
					size="20" value="<%=mr.getField(Profesion).getString()%>" onchange="concatUbicacion();">
			  </TD>
		   </tr>
		   <tr id="trclear">	
				<TD nowrap width="42%">En Calidad de</TD>
				<TD nowrap width="58%"><INPUT type="text"
				    name="<%=EnCalidad%>" id="address.AptoOfi"
					size="20" value="<%=mr.getField(EnCalidad).getString()%>" onchange="concatUbicacion();">
			  </TD>
		   </tr>
		   <tr id="trclear">	
				<TD nowrap width="42%">Estado Civil</TD>
				<TD nowrap width="58%"><INPUT type="text"
				    name="<%=EstadoCivil%>" id="address.AptoOfi"
					size="20" value="<%=mr.getField(EstadoCivil).getString()%>" onchange="concatUbicacion();">
			  </TD>
		   </tr>
		   <tr id="trclear">	
				<TD nowrap width="42%">Sexo</TD>
				<TD nowrap width="58%"><INPUT type="text"
				    name="AptoOfi" id="address.AptoOfi"
					size="20" value="<%=aptoValue%>" onchange="concatUbicacion();">
			  </TD>
		   </tr>
	      <TR id="trclear" >
		    <TD nowrap width="32%" align="right">Teléfono: </TD>
		      <TD nowrap width="58%" colspan="3"><INPUT type="text"
			     name="<%=Telefono%>" size="20" value="<%=mr.getField(Telefono).getString()%>"></TD>
	        </TR>
           <tr id="trclear">	
				<TD nowrap width="42%">Identificación</TD>
				<TD nowrap width="58%"><INPUT type="text"
				    name="<%=Id%>" id="address.AptoOfi"
					size="20" value="<%=mr.getField(Id).getString()%>" onchange="concatUbicacion();">
			   </TD>
		   </tr>
		   <tr id="trclear">	
				<TD nowrap width="42%">Tipo Identificación</TD>
				<TD nowrap width="58%"><INPUT type="text"
				    name="<%=TipoId%>" id="address.AptoOfi"
					size="20" value="<%=mr.getField(TipoId).getString()%><%=aptoValue%>" onchange="concatUbicacion();">
			  </TD>
		   </tr>
		   <tr id="trclear">	
				<TD nowrap width="42%">País</TD>
				<TD nowrap width="58%"><INPUT type="text"
				    name="<%=PaisId%>" id="address.AptoOfi"
					size="20" value="<%=mr.getField(PaisId).getString()%>" onchange="concatUbicacion();">
			  </TD>
		   </tr>
	     </table>
 
        </td>
      </tr>
    </table>

    <script language="JavaScript" >
       //util = new EventUtils();
       EventUtils.addEventListener(document.forms[0],'submit',concat<%=Region%>);
       EventUtils.addEventListener(document.forms[0],'submit',concat<%Ubicacion%>);
    </script>
