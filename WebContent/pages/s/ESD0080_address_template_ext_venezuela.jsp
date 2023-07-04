<%@ page import="datapro.eibs.sockets.*, datapro.eibs.beans.*" %>


<%!

  // LISTA DE NOMBRES DE CAMPOS
  
  String Nombre;
  String Barrio;
  String Calle;
  String TipoLocal;   //Residencia;
  String Piso;
  String CUSNME;  // Incluye nivel/apto y numero de residencia
  String ApartadoPostal;
  String Parroquia;
  String Pais;
  String Estado;
  String Ciudad;
  String CiudadDsc;
  String ZonaPostal;   
  String Telefono;
  

  
  //LISTA DE VARIABLES CON LOS VALORES ASIGNADOS A LOS CAMPOS
  String barrioValue="";
  String avenidaValue="";
  String tipoLocalValue="";
  String nivelValue ="";
  String numCasaValue ="";
  String apartadoPostalValue="";
  String parroquiaValue="";
  String ciudadDscValue="";
  String ciudadValue="";
  String zonaPostalValue="";
  String estadoValue ="" ;
  String paisValue="";
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
   
   //Obtiene el titulo del segmento de direccion
   if (request.getParameter("title") != null)
     Title = request.getParameter("title");
   
 

   // Obtiene el sufijo de los campos dependiendo del tipo de mensaje a desplegar.
   // Por ejemplo, si el mensaje es del tipo ESD008001, el sufijo enviado es E01, si el tipo es ESD008002 
   // el sufijo enviado ed E02
   
   index = request.getParameter("index");
   suffix="E"+index+"4";
   //index= suffix.substring(1,2);
   System.out.println ("Suffix :" +suffix+" "+index);

   // Determina si es solo lectura
   if (request.getParameter("readOnly") != null ){
     readOnly=request.getParameter("readOnly");
     READ_ONLY_TAG="readonly";
   }
   
   // Obtiene el mensaje a desplegar
   messageName = request.getParameter("messageName");
   mr= (datapro.eibs.sockets.MessageRecord) session.getAttribute(messageName);      
   
   if (mr instanceof ESD000004Message){
     mr = (ESD000004Message) mr;
   }
   System.out.println("MR :"+mr.toString());

   //Obtiene el color de la primera fila
   if(request.getParameter("firstColor")!= null){
      color1=request.getParameter("firstColor");
      if (color1.equals("trclear")) color2="trdark";
   }
     
   // Resuelve el nombre de los campos
   
   Nombre=suffix+"MAN";
   Barrio=suffix+"MA1";
   Calle=suffix+"MA2";
   TipoLocal=suffix+"MA3";
   Piso=suffix+"MA4";
   CUSNME=suffix+"NME";
   ApartadoPostal=suffix+"POB";
   Parroquia=suffix+"UC4";
   Pais=suffix+"CTR";
   Estado=suffix+"STE";
   Ciudad=suffix+"UC7";
   CiudadDsc=suffix+"CTY";
   ZonaPostal=suffix="ZPC";   
   Telefono=suffix+"HPN";
   
   
   
   //Asigna el valor inicial en los campos de la forma que 
   //comparten un mismo campo en el archivo AS400.
   
   //Casa/Edificio, Piso/Nivel, Apto/Oficina
   try {
     String value = mr.getField(CUSNME).getString();
     if (value.length() <= 10)
       nivelValue = value.substring(0,value.length());
     else { 
       nivelValue= value.substring(0,10);
       numCasaValue= value.substring(10);
     }
   }
   catch (Exception e) {}    
   

%>
          <tr id="<%=color1%>">
            <td nowrap  > 
              <div align="right">Direcci&oacute;n :</div>
            </td>
            <td nowrap  > 
              <input type="text" name="<%=Barrio%>" id="<%=Barrio%>"  size="45" maxlength="35" value="<%=mr.getField(Barrio).getString()%>" readonly>
                <% if (!readOnly.equals("true")) {%>
				<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" 
				style="cursor:hand" onClick="openAddresHelp('<%=Barrio%>', '<%=Calle%>', '<%=TipoLocal%>','<%=Piso%>','<%=CUSNME%>','<%=index%>');">
	              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  > 
	              <%}%>
            </td>
          </tr>
          <tr id="<%=color1%>"> 
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap > 
              <input type="text" name="<%=Calle%>" id="<%=Calle%>"  size="45" maxlength="35" value="<%= mr.getField(Calle).getString()%>" readonly >
            </td>
          </tr>
          <tr id="<%=color1%>"> 
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap > 
              <input type="text" name="<%=TipoLocal%>" id="<%=TipoLocal%>"  size="45" maxlength="35" value="<%= mr.getField(TipoLocal).getString()%>" readonly >
            </td>
          </tr>
          <tr id="<%=color1%>"> 
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap > 
              <input type="text" name="<%=Piso%>" id="<%=Piso%>" size="45" maxlength="35" value="<%= mr.getField(Piso).getString()%>" readonly >
            </td>
          </tr>
          <tr id="<%=color1%>"> 
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap > 
              <input type="text" name="<%=CUSNME%>" id="<%=CUSNME%>" size="45" maxlength="35" value="<%= mr.getField(CUSNME).getString()%>" readonly >
            </td>
            </td>
          </tr>
 
    
    <%-- Fin del bloque de direccion --%>
         
     <%-- 
           <tr id="<%=color2%>"> 
            <td nowrap  align="right"> 
              Apartado Postal :
            </td>
            <td nowrap > 
              <input type="text" name="<%=ApartadoPostal%>" id="ApartadoPostal" size="15" maxlength="10" value="<%= mr.getField(ApartadoPostal).getString()%>"  <%=READ_ONLY_TAG%> >
            </td>
          </tr>
           <TR id="<%=color1%>" >
				<TD nowrap  align="right">Parroquia</TD>
				<TD nowrap >
				<input type="text" name="<%=Parroquia%>" id="<%=Parroquia%>" size="7" maxlength="6" value=""  <%=READ_ONLY_TAG%> >
                   <% if (!readOnly.equals("true")){ %>
                     <a href="javascript:GetCodeCNOFC('<%=Parroquia%>','80')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a>
                     <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"> 					
                   <%}%>
				</TD>
			</TR>
		  	<tr id="<%=color2%>"> 
            <td nowrap > 
              <div align="right">Ciudad :</div>
            </td>
            <td nowrap > 
              <input type="text" name="<%=Ciudad%>" id="<%=Ciudad%> size="5" maxlength="4" value="" <%=READ_ONLY_TAG%> >
              <input type="text" name="<%=CiudadDsc%>" id=<%=CiudadDsc%> size="30" maxlength="30" value="<%= mr.getField(CiudadDsc).getString()%>" <%=READ_ONLY_TAG%> >
                <% if (!readOnly.equals("true")){ %>
                <a href="javascript:GetCodeDescCNOFC('<%=Ciudad%>','<%=CiudadDsc%>','84')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a>
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"> 
                <%}%>
            </td>
          </tr>
          <tr id="<%=color1%>"> 
            <td nowrap > 
              <div align="right">Zona Postal :</div>
            </td>
            <td nowrap > 
              <input type="text" name="<%=ZonaPostal%>" id='<%=ZonaPostal%>' size="10" maxlength="15" value="" <%=READ_ONLY_TAG%> >
            </td>
          </tr>
          <tr id="<%=color2%>"> 
            <td nowrap > 
              <div align="right">Estado :</div>
            </td>
            <td nowrap > 
              <input type="text" name="<%=Estado%>" id=<%=Estado%> size="6" maxlength="4" value="<%= mr.getField(Estado).getString()%>" <%=READ_ONLY_TAG%> >
              <% if (!readOnly.equals("true")){ %>
                <a href="javascript:GetCodeCNOFC('E01STE','27')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
              <%}%>  
            </td>
          </tr>
          
          <tr id="<%=color1%>"> 
            <td nowrap > 
              <div align="right">Pa&iacute;s :</div>
            </td>
            <td nowrap >
              <input type="hidden"name="PaisCode"  id="PaisCode" value="" <%=READ_ONLY_TAG%> > 
              <input type="text" name="<%=Pais%>" id="<%=Pais%>" size="21" maxlength="20" value="<%=mr.getField(Pais).getString()%>">
              <% if (!readOnly.equals("true")){ %>
                <a href="javascript:GetCodeDescCNOFC('PaisCode','<%=Pais%>','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  > 
              <%} %>  
            </td>
          </tr>
       --%>
 

