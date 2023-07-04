<%@ page import="datapro.eibs.sockets.*, datapro.eibs.beans.*" %>

<% 
	// Obtiene el mensaje a desplegar
	String messageName = request.getParameter("messageName");
	datapro.eibs.sockets.MessageRecord mr = 
		(datapro.eibs.sockets.MessageRecord) session.getAttribute(messageName);

	boolean basicData = false;
	// Determina si es información de clientes o de entidades
	if (request.getParameter("basic") != null){
		basicData = Boolean.valueOf(
			request.getParameter("basic").toString()).booleanValue();
	}
	
	String READ_ONLY_TAG = " readonly";
	boolean readOnly = true;
	// Determina si es solo lectura
	if (request.getParameter("readOnly") != null){
		readOnly = Boolean.valueOf(
			request.getParameter("readOnly").toString()).booleanValue();
		READ_ONLY_TAG = readOnly ? " readonly" : "";
	}
	
	String color1 = "trdark";
	String color2 = "trclear";
	//Obtiene el color de la primera fila
	if(request.getParameter("firstColor") != null){
		color1 = request.getParameter("firstColor");
		if (color1.equals("trclear")) color2="trdark";
	}
	
	//Flag de aprobación
	boolean approval = false;
	if(request.getParameter("approval") != null){
		approval = Boolean.valueOf(
			request.getParameter("approval").toString()).booleanValue();
	}
	
	//Indice o Prefijo
	String index = "01" ;
	if (request.getParameter("index") != null){
		index = request.getParameter("index");
	}
	
	  // NOMBRES DE CAMPOS
	String addressLine1 = basicData ? "NA2" : "MA2";
	String addressLine2 = basicData ? "NA3" : "MA3";
	String addressLine3 = basicData ? "NA4" : "MA4";
	String state 		= basicData ? "STE" : "STE";
	String city			= basicData ? "CTY" : "CTY";
	String stateDiv		= basicData ? "UC7" : "UC2";
	String country 		= "CTR" ;
	String poBox 		= "POB" ;
	String zipCode 		= "ZPC" ;
	String mailCode 	= "MLC" ;
	String eMail 		= "IAD" ;
%>

   <%-- Inicio del bloque de direccion --%> 
         <tr id="<%=color1%>">
            <td nowrap="nowrap" width="40%"  > 
              <div align="right">Dirección Principal:</div>
            </td>
            <td nowrap="nowrap" width="66%"  > 
              <input type="text" name="<%="E"+index+addressLine1%>"  size="37" maxlength="35" 
              	value="<%=mr.getField("E"+index+addressLine1).getString()%>" <%=READ_ONLY_TAG%> 
              	<%if(approval){ 
              		out.print(mr.getField("F"+index+addressLine1).getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> />
           		<% if ( !readOnly ) {%>
				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"> 
				<% } %>
            </td>
           </tr>
           <tr id="<%=color2%>">
            <td nowrap="nowrap" width="40%" > 
              <div align="right"></div>
            </td>
            <td nowrap="nowrap" width="66%" > 
              <input type="text" name="<%="E"+index+addressLine2%>"  size="37" maxlength="35" 
              	value="<%=mr.getField("E"+index+addressLine2).getString()%>" <%=READ_ONLY_TAG%> 
              	<%if(approval){ 
              		out.print(mr.getField("F"+index+addressLine2).getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> />
            </td>
          </tr>
          <tr id="<%=color1%>"> 
            <td nowrap="nowrap" width="40%" > 
              <div align="right"></div>
            </td>
            <td nowrap="nowrap" width="66%" > 
              <input type="text" name="<%="E"+index+addressLine3%>"  size="37" maxlength="35" 
              	value="<%=mr.getField("E"+index+addressLine3).getString()%>" <%=READ_ONLY_TAG%> 
              	<%if(approval){ 
              		out.print(mr.getField("F"+index+addressLine3).getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> />
            </td>
           </tr>
           <tr id="<%=color2%>">
            <td nowrap="nowrap" width="40%" > 
              <div align="right">Estado :</div>
            </td>
            <td nowrap="nowrap" width="66%" > 
              <input type="hidden" name="<%="E"+index+state%>"  size="6" maxlength="4" 
              	value="<%=mr.getField("E"+index+state).getString()%>" <%=READ_ONLY_TAG%> />
              <input type="text" name="<%="D"+index+state%>"  size="37" maxlength="35" 
              	onKeypress="javascript:document.forms[0].<%="E"+index+state%>.value='';"
				onblur="clrfield('<%="D"+index+state%>','<%="E"+index+state%>');"
              	value="<%=mr.getField("D"+index+state).getString()%>" <%=READ_ONLY_TAG%> 
              	<%if(approval){ 
              		out.print(mr.getField("F"+index+state).getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> />
              <% if ( !readOnly ) {%>
              <A href="javascript:GetCodeDescCNOFC('<%="E"+index+state%>','<%="D"+index+state%>','PV')" >
				<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" style="cursor:hand"/>
	          </A>
				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"/> 
              <%}%>

            </td>
          </tr>
          <tr id="<%=color1%>"> 
            <td nowrap="nowrap" width="40%" > 
              <div align="right">Distrito :</div>
            </td>
            <td nowrap="nowrap" width="66%" > 
              <input type="hidden" name="<%="E"+index+stateDiv%>" size="6" maxlength="4" 
              	value="<%=mr.getField("E"+index+stateDiv).getString()%>" <%=READ_ONLY_TAG%> />
              <input type="text" name="<%="D"+index+stateDiv%>"  size="37" maxlength="35" 
              	onKeypress="javascript:document.forms[0].<%="E"+index+stateDiv%>.value='';"
				onblur="clrfield('<%="D"+index+stateDiv%>','<%="E"+index+stateDiv%>');"
              	value="<%=mr.getField("D"+index+stateDiv).getString()%>" <%=READ_ONLY_TAG%> 
              	<%if(approval){
              		out.print(mr.getField("F"+index+stateDiv).getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> />
              <% if ( !readOnly ) {%>
              <a href="javascript:Get2FilterCodes('<%="E"+index+stateDiv%>','<%="D"+index+stateDiv%>','PI', document.getElementById('<%="E"+index+state%>').value,' ')" >
				<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" style="cursor:hand"/>
	          </a>
				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"/> 
              <% } %>
            </td>
           </tr>
           <tr id="<%=color2%>">
            <td nowrap="nowrap" width="40%" > 
              <div align="right">Ciudad :</div>
            </td>
            <td nowrap="nowrap" width="66%" > 
              <input type="hidden" name="<%="E"+index+city%>" size="6" maxlength="4" 
              	value="<%=mr.getField("E"+index+city).getString()%>" <%=READ_ONLY_TAG%> />
              <input type="text" name="<%="D"+index+city%>"  size="35" maxlength="30" 
              	onKeypress="javascript:document.forms[0].<%="E"+index+city%>.value='';"
				onblur="clrfield('<%="D"+index+city%>','<%="E"+index+city%>');"
              	value="<%=mr.getField("D"+index+city).getString()%>" <%=READ_ONLY_TAG%> 
              	<%if(approval){
              		out.print(mr.getField("F"+index+city).getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> />
              <% if ( !readOnly ) {%>
              <a href="javascript:Get2FilterCodes('<%="E"+index+city%>','<%="D"+index+city%>','PE', document.getElementById('<%="E"+index+state%>').value,document.getElementById('<%="E"+index+stateDiv%>').value)" >
				<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" style="cursor:hand"/>
	          </a>
				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"/> 
              <% } %>
            </td>
          </tr>

          <tr id="<%=color1%>"> 
            <td nowrap="nowrap" width="40%" > 
              <div align="right">País :</div>
            </td>
            <td nowrap="nowrap" width="66%" > 
              <input type="hidden" name="<%="C"+index+country%>" onchange="javascript:addressCountryChanged(<%= index %>, this)"/>
              <input type="text" name="<%="E"+index+country%>"  size="25" maxlength="20" 
              	value="<%=mr.getField("E"+index+country).getString()%>" <%=READ_ONLY_TAG%> 
              	<%if(approval){ 
              		out.print(mr.getField("F"+index+country).getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> />
              <% if ( !readOnly ) {%>
              <A href="javascript:GetCodeDescCNOFC('<%="C"+index+country%>','<%="E"+index+country%>','03')">
				<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" style="cursor:hand"/>
	          </A>
				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"/> 
              <%}%>
             </td>
           </tr>
           <tr id="<%=color2%>">
            <td nowrap="nowrap" width="40%" > 
              <div align="right">Apartado Postal :</div>
            </td>
            <td nowrap="nowrap" width="66%" > 
              <input type="text" name="<%="E"+index+poBox%>"  size="11" maxlength="10" 
              	value="<%= mr.getField("E"+index+poBox).getString()%>" <%=READ_ONLY_TAG %> 
              	<%if(approval){
              		out.print(mr.getField("F"+index+poBox).getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> />
            </td>
          </tr>

          <tr id="<%=color1%>"> 
            <td nowrap="nowrap" width="40%" > 
              <div align="right">Código Postal :</div>
            </td>
            <td nowrap="nowrap" width="66%" > 
              <input type="text" name="<%="E"+index+zipCode%>"  size="16" maxlength="15" 
              	value="<%= mr.getField("E"+index+zipCode).getString()%>" <%=READ_ONLY_TAG %> 
              	<%if(approval){
              		out.print(mr.getField("F"+index+zipCode).getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> />
              <% if ( !readOnly ) {%>
              <a href="javascript:Get2FilterCodes('<%="E"+index+zipCode%>','' ,'59', document.getElementById('<%="E"+index+state%>').value,' ')">
				<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" style="cursor:hand"/>
	          </a>
	              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" /> 
              <%}%>
            </td>
           </tr>
		  <% if( basicData ){ //Este campo solo aplica para los datos de clientes  %>
           <tr id="<%=color2%>">
            <td nowrap="nowrap" width="40%" > 
              <div align="right">Tipo de Correo :</div>
            </td>
            <td nowrap="nowrap" width="66%" > 
              <input type="text" name="<%="E"+index+mailCode%>"  size="7" maxlength="4" 
              	value="<%= mr.getField("E"+index+mailCode).getString()%>" <%=READ_ONLY_TAG %> 
              	<%if(approval){
              		out.print(mr.getField("F"+index+mailCode).getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> />
            </td>
          </tr>

          <tr id="<%=color1%>"> 
            <td nowrap="nowrap" width="40%" > 
              <div align="right">Dirección E-Mail :</div>
            </td>
            <td nowrap="nowrap" width="66%" > 
              <input type="text" name="<%="E"+index+eMail%>"  size="42" maxlength="40" 
              	value="<%= mr.getField("E"+index+eMail).getString()%>" <%=READ_ONLY_TAG %> 
              	<%if(approval){
              		out.print(mr.getField("F"+index+eMail).getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> />
            </td>
          </tr>
 		<% } %>
