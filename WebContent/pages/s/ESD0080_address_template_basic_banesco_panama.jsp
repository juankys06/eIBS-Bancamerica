<%@ page import="datapro.eibs.sockets.*, datapro.eibs.beans.*" %>


<%

  datapro.eibs.sockets.MessageRecord mr;
 
  String READ_ONLY_TAG="";
  String color1="trdark";
  String color2="trclear";
 
  // PARAMETROS
  String suffix, suffixDsc, suffixMod ;
  String addressType = "S";
  String messageName;
  String title="";
  String index;
  boolean readOnly=false ;  
  boolean basicData=false ;  
  
  //esta variable se usa para saber si se consultan los datos basicos y asi construir 
  //dinamicamente los nombres de los campos, ya que el template es el mismo para 
  // las direcciones de clientes personales, corporativos , beneficiarios y representantes legales
  // asi como para la consulta de aprobación.
  // Ver mas abajo donde se arman los nombres de los campos.
  if( request.getParameter("basic") != null ){
		basicData = 
		  	Boolean.valueOf(request.getParameter("basic").toString()).booleanValue() ;
  }
   
   //Obtiene el titulo del segmento de direccion
   if (request.getParameter("title") != null)
     title = request.getParameter("title");
   
   // Determina si es solo lectura
   if (request.getParameter("readOnly") != null ){
     readOnly=Boolean.valueOf( request.getParameter("readOnly").toString() ).
     					booleanValue() ;
     READ_ONLY_TAG=readOnly?"readonly":"";
   }
   
   // Obtiene el mensaje a desplegar
   messageName = request.getParameter("messageName");
   mr= (datapro.eibs.sockets.MessageRecord) session.getAttribute(messageName);      
   
   if (request.getParameter("suffix") != null){
   		suffix = request.getParameter("suffix") ;
   }else{
   		suffix = "E01" ;
   }
   suffixDsc = "D" + suffix.substring(1) ;
   suffixMod = "F" + suffix.substring(1) ;

   //Obtiene el color de la primera fila
   if(request.getParameter("firstColor")!= null){
      color1=request.getParameter("firstColor");
      if (color1.equals("trclear")) color2="trdark";
   }
     
  // NOMBRES DE CAMPOS
	String calle = suffix + (basicData?"NA2":"MA2")   ;
	String residencial_Edificio = suffix + (basicData?"NA3":"MA3") ;
	String noCasa_Apto = suffix + (basicData?"NA4":"N33") ;
	String provincia = suffix + (basicData?"STE":"STE") ;
	String provinciaDsc = suffixDsc + (basicData?"STE":"STE") ;
	String distrito = suffix + (basicData?"UC7":"TID") ;
	String distritoDsc = suffixDsc + (basicData?"UC7":"TID") ;
	String corregimiento = suffix + (basicData?"UC8":"PID") ;
	String corregimientoDsc = suffixDsc + (basicData?"UC8":"PID") ;
	String pais = suffix + "CTR" ;
	String paisCode = "CTYCode" + suffix;
	String apartadoPostal = suffix + "POB" ;
	String codigoPostal = suffix + "ZPC" ;
	String codigoPostalDsc = suffix + "ZPCDSC" ;
	String tipoCorreo = suffix + "MLC" ;
	String eMail = suffix + "IAD" ;

	// Flag Modificacion para consulta de aprobacion  
	String calleF = suffixMod + (basicData?"NA2":"MA2")   ;
	String residencial_EdificioF = suffixMod + (basicData?"NA3":"MA3") ;
	String noCasa_AptoF = suffixMod + (basicData?"NA4":"MA4") ;
	String provinciaF = suffixMod + (basicData?"STE":"STE") ;
	String distritoF = suffixMod + (basicData?"UC7":"TID") ;
	String corregimientoF = suffixMod + (basicData?"UC8":"PID") ;
	String paisF = suffixMod + "CTR" ;
	String apartadoPostalF = suffixMod + "POB" ;
	String codigoPostalF = suffixMod + "ZPC" ;
	String codigoPostalDscF = suffixMod + "ZPCDSC" ;
	String tipoCorreoF = suffixMod + "MLC" ;
	String eMailF = suffixMod + "IAD" ;
  
%>

    
<%-- Inicio del bloque de direccion --%> 
<% if( basicData){ %>
	<h4><%=title%></h4>
   
   <table class="tableinfo" width="100%" >
    	<tr > 
        	<td nowrap >
        		<table cellspacing="0" cellpadding="2" width="100%" border="0">
<% } %>
          			<tr id="<%=color1%>">
            			<td nowrap  ><div align="right">Calle :</div></td>
            			<td nowrap  > 
              				<input type="text" name="<%=calle%>"  size="37" maxlength="35" value="<%=mr.getField(calle).getString()%>" <%=READ_ONLY_TAG%> 
              				<% if(basicData){ out.print((mr.getField(calleF).getString().equals("Y"))?"id=\"txtchanged\"":""); } %> >
	          				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  >	
            			</td>
           			</tr>
           			<tr id="<%=color2%>">
            			<td nowrap ><div align="right">Residencial/Edificio :</div></td>
            			<td nowrap > 
              				<input type="text" name="<%=residencial_Edificio%>" size="37" maxlength="35" value="<%= mr.getField(residencial_Edificio).getString()%>" <%=READ_ONLY_TAG%> 
              				<%if(basicData){ out.print((mr.getField(residencial_EdificioF).getString().equals("Y"))?"id=\"txtchanged\"":"");} %> >
	          				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  >	
            			</td>
          			</tr>
          			<tr id="<%=color1%>"> 
            			<td nowrap ><div align="right">No. Casa/Apto :</div></td>
            			<td nowrap > 
              				<input type="text" name="<%=noCasa_Apto%>"   size="37" maxlength="35" value="<%= mr.getField(noCasa_Apto).getString()%>" <%=READ_ONLY_TAG%> 
              				<% if(basicData){ out.print((mr.getField(noCasa_AptoF).getString().equals("Y"))?"id=\"txtchanged\"":"");} %> >
	          				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  >	
            			</td>
           			</tr>
           			<tr id="<%=color2%>">
            			<td nowrap ><div align="right">Provincia :</div></td>
            			<td nowrap > 
              				<input type="hidden" name="<%=provincia%>"  size="6" maxlength="4" value="<%= mr.getField(provincia).getString()%>" <%=READ_ONLY_TAG %> >
              				<input type="text" name="<%=provinciaDsc%>"  size="37" maxlength="35" value="<%= mr.getField(provinciaDsc).getString()%>" readonly="true" 
              				<% if(basicData){ out.print((mr.getField(provinciaF).getString().equals("Y"))?"id=\"txtchanged\"":"");} %> >
              				<% if ( !readOnly ) { %>
              					<A href="javascript:GetCodeDescCNOFC('<%=provincia%>','<%=provinciaDsc%>','PV')" >
              						<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" style="cursor:hand" >
	          					</A>
	          				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  >	
              				<% } %>
      						<%--<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  >--%>
            			</td>
          			</tr>
          			<tr id="<%=color1%>"> 
            			<td nowrap ><div align="right">Distrito :</div></td>
            			<td nowrap > 
              				<input type="hidden" name="<%=distrito%>"  size="6" maxlength="4" value="<%= mr.getField(distrito).getString()%>" <%=READ_ONLY_TAG %> >
              				<input type="text" name="<%=distritoDsc%>"  size="37" maxlength="35" value="<%= mr.getField(distritoDsc).getString()%>" readonly="true"  
              				<%=(basicData && mr.getField(distritoF).getString().equals("Y"))?"id=\"txtchanged\"":"" %> >
              				<% if ( !readOnly ) { %>
              					<a href="javascript:Get2FilterCodes('<%=distrito%>','<%=distritoDsc%>','PI', document.forms[0]['<%=provincia%>'].value,' ')" >
									<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" style="cursor:hand" >
	              				</a>
	              				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  >	
              				<% } %>
           					<%--<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  >--%> 
            			</td>
           			</tr>
           			<tr id="<%=color2%>">
            			<td nowrap ><div align="right">Corregimiento :</div></td>
            			<td nowrap > 
              				<input type="hidden" name="<%=corregimiento%>"  size="6" maxlength="4" value="<%= mr.getField(corregimiento).getString()%>" <%=READ_ONLY_TAG %> >
              				<input type="text" name="<%=corregimientoDsc%>"  size="37" maxlength="35" value="<%= mr.getField(corregimientoDsc).getString()%>" readonly="true" 
              				<%= (basicData && mr.getField(corregimientoF).getString().equals("Y"))?"id=\"txtchanged\"":"" %> >
              				<% if ( !readOnly ) {%>
              					<a href="javascript:Get2FilterCodes('<%=corregimiento%>','<%=corregimientoDsc%>','PE',document.forms[0]['<%=provincia%>'].value,document.forms[0]['<%=distrito%>'].value)" >
									<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" style="cursor:hand" >
	          					</a>
	          					<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  >
              				<% } %>
           					<%--<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  >--%> 
            			</td>
          			</tr>
          			<tr id="<%=color1%>"> 
            			<td nowrap ><div align="right">Pa&iacute;s :</div></td>
            			<td nowrap > 
            				<input type="hidden" name="<%= paisCode %>" value="" <%=READ_ONLY_TAG%>>
              				<input type="text" name="<%=pais%>"  size="21" maxlength="20" value="<%= mr.getField(pais).getString()%>" readonly="true"  
              				<%=(basicData && mr.getField(paisF).getString().equals("Y"))?"id=\"txtchanged\"":"" %> >
              				<% if ( !readOnly ) {%>
								<a href="javascript:GetCodeDescCNOFC('<%= paisCode %>','<%=pais%>','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
              				<% } %>
            			</td>
           			</tr>
           			<tr id="<%=color2%>">
            			<td nowrap ><div align="right">Apartado Postal :</div></td>
            			<td nowrap > 
              				<input type="text" name="<%=apartadoPostal%>"  size="11" maxlength="10" value="<%= mr.getField(apartadoPostal).getString()%>" <%=READ_ONLY_TAG %> 
              				<%=(basicData && mr.getField(apartadoPostalF).getString().equals("Y"))?"id=\"txtchanged\"":"" %> >
            			</td>
          			</tr>
          			<tr id="<%=color1%>"> 
            			<td nowrap ><div align="right">C&oacute;digo Postal :</div></td>
            			<td nowrap > 
            				<%System.out.println( "Cod Postal=" + mr.getField(codigoPostal).getString() ) ; %>
              				<input type="hidden" name="<%=codigoPostal%>"  size="12" maxlength="11" value='<%= (mr.getField(codigoPostal) != null && !mr.getField(codigoPostal).getString().equals("") && mr.getField(codigoPostal).getString().length() > 4 ) ? mr.getField(codigoPostal).getString().substring(0,4) : "" %>' <%=READ_ONLY_TAG %> >
              				<input type="text" name="<%=codigoPostalDsc%>"  size="12" maxlength="11" value='<%= (mr.getField(codigoPostal) != null && !mr.getField(codigoPostal).getString().equals("") && mr.getField(codigoPostal).getString().length() > 5 ) ? mr.getField(codigoPostal).getString().substring(4) : "" %>' readonly="true" 
              				<%= (basicData && mr.getField(codigoPostalF).getString().equals("Y"))?"id=\"txtchanged\"":"" %> >
              				<% if ( !readOnly ) {%>
              					<a href="javascript:Get2FilterCodes('<%=codigoPostal%>','<%=codigoPostalDsc%>' ,'59', document.getElementById('<%=provincia%>').value,' ')">
									<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" style="cursor:hand" >
	          					</a>
	          					<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  >
              				<% } %>
           					<%--<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  >--%> 
            			</td>
           			</tr>
		  			<% if ( basicData ) { //Este campo solo aplica para los datos basicos  %>
	           			<tr id="<%=color2%>">
	            			<td nowrap ><div align="right">Tipo de Correo :</div></td>
	            			<td nowrap > 
	              				<input type="text" name="<%=tipoCorreo%>"  size="7" maxlength="4" value="<%= mr.getField(tipoCorreo).getString()%>" <%=READ_ONLY_TAG %> 
	              				<%= (basicData && mr.getField(tipoCorreoF).getString().equals("Y"))?"id=\"txtchanged\"":"" %> >
	            			</td>
	          			</tr>
	          			<tr id="<%=color1%>"> 
	            			<td nowrap ><div align="right">Direcci&oacute;n E-Mail :</div></td>
	            			<td nowrap > 
	              				<input type="text" name="<%=eMail%>"  size="42" maxlength="40" value="<%= mr.getField(eMail).getString()%>" <%=READ_ONLY_TAG %> 
	              				<%=(basicData && mr.getField(eMailF).getString().equals("Y"))?"id=\"txtchanged\"":"" %> >
	            			</td>
	          			</tr>
          			<% } %>

<% if( basicData ){ %>
			</table>
		</td>
	</tr>
</table>
<% } %>
    
