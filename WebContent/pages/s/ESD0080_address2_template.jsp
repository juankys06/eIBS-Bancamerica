<%@ page import="datapro.eibs.sockets.*, datapro.eibs.beans.*" %>

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<%!

  // LISTA DE NOMBRES DE CAMPOS
  
  String PaisDsc;
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
  String apartadoPostalValue="";
  
  // Variables de uso interno
  
  String READ_ONLY_TAG="";
  
  
  datapro.eibs.sockets.MessageRecord mr;
 
  String color1="trdark";
  String color2="trclear";
 
  // PARAMETROS
   
  String suffix;
  String addressType = "S";
  String messageName;
  String Title;
  String index;
  String readOnly="false";  
      
%>

<%
   //Inicializan los valores de los campos
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
  apartadoPostalValue="";
  
   
   
   //Obtiene el titulo del segmento de direccion
   if (request.getParameter("title") != null)
     Title = request.getParameter("title");
   
 

   // Obtiene el indice del campo
   if(request.getParameter("index") != null)
   index= request.getParameter("index").trim();
   System.out.println ("Index: " + index);

   // Determina si es solo lectura
   if (request.getParameter("readOnly") != null ){
     readOnly=request.getParameter("readOnly");
     if (readOnly.toUpperCase().equals("TRUE"))
       READ_ONLY_TAG="readonly";
     else
       READ_ONLY_TAG="";  
   }
   System.out.println("Read Only? " + readOnly.toUpperCase());
   
   // Obtiene el mensaje a desplegar
   messageName = request.getParameter("messageName");
   mr= (datapro.eibs.sockets.MessageRecord) session.getAttribute(messageName);      
   
   if (mr instanceof ESD000004Message){
     mr = (ESD000004Message) mr;
   }
  // System.out.println("MR :"+mr.toString());

   //Obtiene el color de la primera fila
   if(request.getParameter("firstColor")!= null){
      color1=request.getParameter("firstColor");
      if (color1.equals("trclear")) color2="trdark";
   }
     
   // Resuelve el nombre de los campos
   
   PaisDsc = "E"+index+"4CTR";
   Region = "E"+index + "4UC2"; 
   RegionDsc = "D"+index +"4UC2";
   Estado = "E"+index + "4STE";
   EstadoDsc="D"+index + "4STE";
   MunicipioDsc = "D"+index +"4INC";
   CTY = "E"+index + "4CTY";  //incluye codigo de ciudad y descripcion
   Parroquia = "E"+index  + "4NST";
   ParroquiaDsc = "D"+index +"4BNC";
   CodigoPostal = "E"+index  + "4ZPC";
   Calle = "E"+index + "4MA2";
   NA3  = "E"+index + "4MA3"; //Incluye casa, piso, apto.
   Referencia = "E"+index+ "4NME";;
   ApartadoPostal="E"+index+"4POB"; 
   
   
   //Asigna el valor inicial en los campos de la forma que 
   //comparten un mismo campo en el archivo AS400.
   
   //Parroquia y Municipio
   try {
     if (mr.getField(Estado).getString() !=null)  
       estadoValue=mr.getField(Estado).getString();
     if( mr.getField(Parroquia)!= null &&  mr.getField(Parroquia).getString().trim().length()>0){
       municipioValue=mr.getField(Parroquia).getString().substring(0,4);
       parroquiaValue = mr.getField(Parroquia).getString();
       System.out.println("estado Value:" + estadoValue);
       System.out.println("municipio Value:" + municipioValue);
       System.out.println("parroquia Value "+ mr.getField(Parroquia).getString());
     }
    // parroquiaValue = mr.getField(Parroquia).getString();
    // municipioValue= parroquiaValue.substring(3,4);
   }
   catch (Exception e) {}
   
   //Descripciones de Region, Municipio, Parroquia
   try {
     if (mr.getField(Region).getString() !=null)
       regionValue= mr.getField(Region).getString();
          
     if (mr.getField(RegionDsc).getString() !=null)
       regionDscValue= mr.getField(RegionDsc).getString();
       
     if (mr.getField(MunicipioDsc).getString() !=null)  
       municipioDscValue= mr.getField(MunicipioDsc).getString();
       
     if (mr.getField(ParroquiaDsc).getString() !=null)  
       parroquiaDscValue= mr.getField(ParroquiaDsc).getString();
     
     //if (mr.getField(Estado).getString() !=null)  
     //  estadoValue=mr.getField(Estado).getString();
       
     if (mr.getField(EstadoDsc).getString() !=null)  
       estadoDscValue=mr.getField(EstadoDsc).getString();

     if (mr.getField(ApartadoPostal).getString() !=null)  
       apartadoPostalValue=mr.getField(ApartadoPostal).getString();
       
    if (mr.getField(Referencia).getString() !=null)  
       referenciaValue=mr.getField(Referencia).getString();           
   }
   catch (Exception e) {}
   
   //Casa/Edificio, Piso/Nivel, Apto/Oficina
   try {
     String value = mr.getField(NA3).getString();
     
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
     ciudadValue = value.substring(0,4);
     ciudadDscValue= value.substring(4);
   }
   catch (Exception e) {}   
   

%>


<%if(currUser.getE01INT().equals("03")){%>
    
           <tr id="trclear"> 
            <td nowrap ><div align="right">País</div></td>
            <td nowrap >
            <input type="hidden" name="CTYCode<%=index%>" id="CTYCode<%=index%>" value="" <%=READ_ONLY_TAG%> >
            <INPUT type="text" name="<%=PaisDsc%>" id="<%=PaisDsc%>" size="21" value="<%=mr.getField(PaisDsc).getString().equals("")?"Venezuela":mr.getField(PaisDsc).getString()%>" maxlength="20">
			<% if (!readOnly.toUpperCase().equals("TRUE")){ %>		 
			   <a href="javascript:GetCodeDescCNOFC('CTYCode<%=index%>','<%=PaisDsc%>','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >		
			<% }%>
			</td>		
			</tr>
			
			<tr id="trdark">
				<TD nowrap ><div align="right">Estado</div></TD>
				<TD nowrap >
		          <input type="hidden" name="<%=Estado%>" id="<%=Estado%>" value="<%=estadoValue%>"  >
		          <INPUT type="text" name="<%=EstadoDsc%>" id="EstadoDsc" size="36" value="<%=estadoDscValue%>" <%=READ_ONLY_TAG%> maxlength="35">
				<% if (!readOnly.toUpperCase().equals("TRUE")){ %>	
			      <a href="javascript:clearMunicipio('Aux_Municipio<%=index%>','<%=MunicipioDsc%>','<%=Parroquia%>','<%=ParroquiaDsc%>');GetCodeDescCNOFC('<%=Estado%>','<%=EstadoDsc%>','27')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
                 <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >		
			    <%}%>
			    </td>		
			</tr>
			
			<tr id="trclear">
            <td nowrap ><div align="right">Región</div></td>
            <td nowrap >
             <input type="hidden" name="<%=Region%>" id="address.Region<%=index%>" value="<%=regionValue%>" >
		     <INPUT type="text" name="<%=RegionDsc%>" id="address.RegionDsc<%=index%>"
					size="36" value="<%=regionDscValue%>" <%=READ_ONLY_TAG%> maxlength="35">
			 <% if (!readOnly.toUpperCase().equals("TRUE")){ %>		
			  <a href="javascript:GetCodeDescCNOFC('<%=Region%>','<%=RegionDsc%>','07')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >		
			 <%}%>
			</td>	
			</tr>
			
		  <tr id="trdark"> 
             <td nowrap ><div align="right">Municipio</div></td>
             <td nowrap >
            <input type="hidden" name="Aux_Municipio<%=index%>"  id ="Aux_Municipio<%=index%>" value="<%=municipioValue%>" >
            <INPUT type="text" 	name="<%=MunicipioDsc%>" size="36" id="<%=MunicipioDsc%>"    value="<%=municipioDscValue%>"  <%=READ_ONLY_TAG%>
             maxlength="35">
             <% if (!readOnly.toUpperCase().equals("TRUE")){ %>
			   <a href="javascript:clearParroquia('<%=Parroquia%>','<%=ParroquiaDsc%>');GetCNOFCFilteredCodes('Aux_Municipio<%=index%>','<%=MunicipioDsc%>',document.getElementById('<%=Estado%>').value,'85')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >
              
			 <%}%>
			</td>
          </tr>
          
          <tr id="trclear"> 
              <td nowrap ><div align="right">Ciudad</div></td>
              <td nowrap >
               <input type="hidden" name="<%=CTY%>" id="<%=CTY%>" >
               <input type="hidden" name="Aux_CTYCode<%=index%>"  id="Aux_CTYCode<%=index%>" value=<%=ciudadValue%> >
                <INPUT type="text" name="Aux_CTYDsc<%=index%>" id="Aux_CTYDsc<%=index%>" size="27" value="<%=ciudadDscValue%>" <%=READ_ONLY_TAG%> maxlength="26">
			  <% if (!readOnly.toUpperCase().equals("TRUE")){ %>		
				 <a href="javascript:GetCNOFCFilteredCodes('Aux_CTYCode<%=index%>','Aux_CTYDsc<%=index%>',document.getElementById('<%=Estado%>').value,'84')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" >
           		
             <%}%> 
			</td>
	      </tr>		
		  
		  <tr id="trdark">	
				<TD nowrap ><div align="right">Parroquia</div></TD>
				<TD nowrap >
             <input type="hidden" name="<%=Parroquia%>" id="<%=Parroquia%>" value="<%=parroquiaValue%>"  >
			 <%-- <input type="hidden" name="Aux_Parroquia<%=index%>" id="Aux_Parroquia<%=index%>" value="<%=parroquiaValue%>"  >--%>
			  <INPUT type="text" name="<%=ParroquiaDsc%>" id="<%=ParroquiaDsc%>" 	size="36" value="<%=parroquiaDscValue%>" <%=READ_ONLY_TAG%> maxlength="35">
			 <% if (!readOnly.toUpperCase().equals("TRUE")){ %>		
		      <a href="javascript:GetParroquiaCodes('<%=Parroquia%>','<%=ParroquiaDsc%>',document.getElementById('Aux_Municipio<%=index%>').value,document.getElementById('<%=Estado%>').value,'80')">
		      <img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >  	     		
             <%}%>  
			</TD>
		</tr>
		
		
          <tr id="trclear"> 
             <td nowrap ><div align="right">Codigo Postal/Urbanizacion</div></td>
            <td nowrap >
             <INPUT type="text"
					name="<%=CodigoPostal%>" id="<%=CodigoPostal%>" size="16" value="<%=mr.getField(CodigoPostal).getString()%>" <%=READ_ONLY_TAG%> maxlength="15">
			 <% if (!readOnly.toUpperCase().equals("TRUE")){ %>		
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >  	     		
             <%}%>  
			
			</td>
 		</tr>


          <tr id="trdark"> 
            <td nowrap   ><div align="right">Calle/Avenida</div></td>
            <td nowrap ><INPUT type="text"
					name="<%=Calle%>" id="<%=Calle%>" size="36" value="<%=mr.getField(Calle).getString()%>" <%=READ_ONLY_TAG%> maxlength="35">  
			 <% if (!readOnly.toUpperCase().equals("TRUE")){ %>		
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >  	     		
             <%}%>  
			</td>
          </tr>
          
          <tr id="trclear"> 
            
             <td nowrap ><div align="right">Casa/Edificio</div></td>
              <td nowrap >
              <input type="hidden" name="<%=NA3%>" id="<%=NA3%>" >
              <INPUT type="text"
					name="Aux_CasaEdif<%=index%>" id="Aux_CasaEdif<%=index%>" size="16"
					 value="<%=casaValue%>" <%=READ_ONLY_TAG%> maxlength="15"></td>
           </tr>
           
          <tr id="trdark"> 
               <td nowrap ><div align="right">Piso/Nivel</div></td>
                <td nowrap ><INPUT type="text"
					name="Aux_PisoNivel<%=index%>" id="Aux_PisoNivel<%=index%>" size="6"
					 value="<%=pisoValue%>" <%=READ_ONLY_TAG%> maxlength="5">
	            </td>
	       </tr>
	       
	       <tr id="trclear">      
             <TD nowrap ><div align="right">Apto./Ofic./Local/Dpto.</div></TD>
              <TD nowrap ><INPUT type="text"
				    name="Aux_AptoOfi<%=index%>" id="Aux_AptoOfi<%=index%>"
					size="16" value="<%=aptoValue%>" <%=READ_ONLY_TAG%> maxlength="15">
			  </TD>
		   </tr>
		   
  		  <TR id="trdark">
		    <TD nowrap ><div align="right">Referencia</div></TD>
		    <TD nowrap  >
			  <input type="text"  name="<%=Referencia%>" id="<%=Referencia%>"  value="<%=referenciaValue%>" <%=READ_ONLY_TAG%> size="36" maxlength="35">
			</TD>
		</TR>
 
               	<TR id="trclear">
				<TD nowrap ><div align="right">Apartado Postal</div></TD>
				<TD nowrap  ><INPUT type="text"
				    name="<%=ApartadoPostal%>" id="<%=ApartadoPostal%>"
					maxlength="10" size="11" value="<%=apartadoPostalValue%>" <%=READ_ONLY_TAG%> >
               </TD>
		</TR>

<% } else { %>
         <% String name = index; %>
         <tr> 
            <td nowrap width="42%"> 
            <%if(currUser.getE01INT().equals("04")){%> 
              <div align="right">Calle y Numero:</div>
			<% } else { %>
              <div align="right">Direcci&oacute;n 
                :</div>
			<% } %>
            </td>
            <td nowrap width="58%"> 
              <input type="text" name="E<%= name %>4MA2" maxlength="35" size="36" value="<%= mr.getField("E" + name + "4MA2").getString().trim()%>">
            </td>
          </tr>
         <tr id="trdark"> 
            <td nowrap width="42%"> 
            <%if(currUser.getE01INT().equals("04")){%> 
              <div align="right">Colonia:</div>
			<% } else { %>
                <div align="right"></div>
			<% } %>
            </td>
            <td nowrap width="58%"> 
              <input type="text" name="E<%= name %>4MA3" maxlength="35" size="36" value="<%= mr.getField("E" + name + "4MA3").getString().trim()%>">
            </td>
          </tr>
         <tr> 
            <td nowrap width="42%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="58%"> 
              <input type="text" name="E<%= name %>4MA4" maxlength="35" size="36" value="<%= mr.getField("E" + name + "4MA4").getString().trim()%>">
            </td>
          </tr>
		<%if(currUser.getE01INT().equals("18")){%>
         <tr id="trdark"> 
            <td nowrap width="42%"> 
              <div align="right">Código de Ciudad :</div>
            </td>
            <td nowrap width="58%"> 
              <input type="text"  name="E<%= name %>4TID" maxlength="4" size="5" value="<%= mr.getField("E" + name + "4TID").getString().trim()%>">
			  <a href="javascript:GetCodeCNOFC('E<%= name %>4TID','84')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></a>	
            </td>
          </tr>
         <tr id="trclear"> 
            <td nowrap width="42%"> 
              <div align="right">Comuna :</div>
            </td>
            <td nowrap width="58%"> 
              <input type="text"  name="E<%= name %>4PID" maxlength="4" size="5" value="<%= mr.getField("E" + name + "4PID").getString().trim()%>">
             <a href="javascript:GetCodeCNOFC('E<%= name %>4PID','80')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></a>	
            </td>
          </tr>
		 <%} else {%>

         <tr id="trdark"> 
            <td nowrap width="42%"> 
              <div align="right">Ciudad :</div>
            </td>
            <td nowrap width="58%"> 
              <input type="text" name="E<%= name %>4CTY" maxlength="30" size="31" value="<%= mr.getField("E" + name + "4CTY").getString().trim()%>">
            </td>
          </tr>
         <tr> 
            <td nowrap width="42%"> 
              <div align="right">Estado :</div>
            </td>
            <td nowrap width="58%"> 
              <input type="text" name="E<%= name %>4STE" maxlength="4" size="5" value="<%= mr.getField("E" + name + "4STE").getString().trim()%>">
              <a href="javascript:GetCodeCNOFC('E<%= name %>4STE','27')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></a>	
            </td>
          </tr>
         <%}%>
         <tr id="trdark"> 
            <td nowrap width="42%"> 
              <div align="right">Pa&iacute;s :</div>
            </td>
            <td nowrap width="58%"> 
              <input type="text" name="E<%= name %>4CTR" maxlength="20" size="21" value="<%= mr.getField("E" + name + "4CTR").getString().trim()%>">
            </td>
          </tr>
         <tr> 
            <td nowrap width="42%"> 
              <div align="right">Apartado Postal :</div>
            </td>
            <td nowrap width="58%"> 
              <input type="text" name="E<%= name %>4POB" maxlength="10" size="11" value="<%= mr.getField("E" + name + "4POB").getString().trim()%>">
            </td>
          </tr>
         <tr id="trdark"> 
            <td nowrap width="42%"> 
              <div align="right">C&oacute;digo Postal 
                :</div>
            </td>
            <td nowrap width="58%"> 
              <input type="text" name="E<%= name %>4ZPC" maxlength="15" size="16" value="<%= mr.getField("E" + name + "4ZPC").getString().trim()%>">
            </td>
          </tr>
<% } %>