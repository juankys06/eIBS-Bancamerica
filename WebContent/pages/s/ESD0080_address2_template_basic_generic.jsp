<%@ page import="datapro.eibs.sockets.*, datapro.eibs.beans.*" %>

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<%!
	datapro.eibs.sockets.MessageRecord mr;
 
	String title;
	String messageName;
	String suffix = "4"; // Este template se usa para los que no son datos básicos
	boolean readOnly=false;
	String READ_ONLY_TAG=""; 
%> 

<%
   //Obtiene el titulo del segmento de direccion
	title = request.getParameter("title");
	if (title == null ) title = "Dirección";

   // Obtiene el sufijo de los campos dependiendo del tipo de mensaje a desplegar.
   // Por ejemplo, si el mensaje es del tipo ESD008001, el sufijo enviado es E01, si el tipo es ESD008002 
   // el sufijo enviado ed E02
   

   // Obtiene el mensaje a desplegar
	messageName = request.getParameter("messageName");
	mr = (datapro.eibs.sockets.MessageRecord) session.getAttribute(messageName);
   
	// Determina si es solo lectura
	if (request.getParameter("readOnly") != null )
		if (request.getParameter("readOnly").toLowerCase().equals("true")){
			readOnly=true;
			READ_ONLY_TAG="readonly";
		} else {
			readOnly=false;
			READ_ONLY_TAG="";
		}
	else READ_ONLY_TAG="";


	String index = "0" ;
	if( request.getParameter("index") != null ){
		index = request.getParameter("index")  ;
	}  
	
%>


	<tr id="teclear"> 
		<td nowrap width="39%"><div align="right">Direcci&oacute;n Principal :</div></td>
        <td nowrap width="61%">  
			<input type="text" name="<%= "E" + index + suffix %>MA2" size="36" maxlength="35" value="<%= mr.getField("E" + index + suffix + "MA2").getString().trim()%>" <%=READ_ONLY_TAG %> >
           	<!--<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >-->
		</td>    
	</tr>
	<tr id="trdark"> 
		<td nowrap width="39%"><div align="right"></div></td>
		<td nowrap width="61%">  
			<input type="text" name="<%= "E" + index + suffix %>MA3" size="36" maxlength="35" value="<%= mr.getField("E" + index + suffix + "MA3").getString().trim()%>" <%=READ_ONLY_TAG %> >
		</td>
	</tr>
	<tr id="teclear"> 
		<td nowrap width="39%"><div align="right"></div></td>
		<td nowrap width="61%">  
        	<input type="text" name="<%= "E" + index + suffix %>MA4" size="36" maxlength="35" value="<%= mr.getField("E" + index + suffix + "MA4").getString().trim()%>" <%=READ_ONLY_TAG %>>
		</td>
	</tr>
	<tr id="trdark"> 
		<td nowrap width="39%"><div align="right">Ciudad :</div></td>
		<td nowrap width="61%">  
        	<input type="text" name="<%= "E" + index + suffix %>CTY" size="31" maxlength="30" value="<%= mr.getField("E" + index + suffix + "CTY").getString().trim()%>" <%=READ_ONLY_TAG %> >
            <!-- <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >-->
		</td>
	</tr>
	<tr id="teclear"> 
		<td nowrap width="39%"><div align="right">Estado :</div></td>
		<td nowrap width="61%">  
			<input type="text" name="<%= "E" + index + suffix %>STE" size="5" maxlength="4" value="<%= mr.getField("E" + index + suffix + "STE").getString().trim()%>" <%=READ_ONLY_TAG %> >
            <% if ( !readOnly ) { %>
	        	<a href="javascript:GetCodeCNOFC('<%= "E" + index + suffix %>STE','27')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
    	    <% } %>
			<!-- <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >-->
    	 </td>       
	</tr>
	<tr id="trdark"> 
		<td nowrap width="39%"><div align="right">Pa&iacute;s :</div></td>
		<td nowrap width="61%">  
			<input type="hidden" name="<%= "CTYCode" + index %>" value="" <%=READ_ONLY_TAG%>>
        	<input type="text" name="<%= "E" + index + suffix %>CTR" size="21" maxlength="20" value="<%= mr.getField("E" + index + suffix + "CTR").getString().trim()%>" <%=READ_ONLY_TAG %> >
            <% if( !readOnly ){ %>
				<a href="javascript:GetCodeDescCNOFC('<%= "CTYCode" + index %>','<%= "E" + index + suffix %>CTR','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
            <% } %>
			<!--<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom">--> 
        </td>
    </tr>
	<tr id="teclear"> 
		<td nowrap width="39%"><div align="right">Apartado Postal :</div></td>
		<td nowrap width="61%">  
			<input type="text" name="<%= "E" + index + suffix %>POB" size="11" maxlength="10" value="<%= mr.getField("E" + index + suffix + "POB").getString().trim()%>" <%=READ_ONLY_TAG %> >
		</td>
	</tr>
	<tr id="trdark"> 
		<td nowrap width="39%"><div align="right">C&oacute;digo Postal :</div></td>
		<td nowrap width="61%">  
        	<input type="text" name="<%= "E" + index + suffix %>ZPC" size="16" maxlength="15" value="<%= mr.getField("E" + index + suffix + "ZPC").getString().trim()%>" <%=READ_ONLY_TAG %> >
        </td>
	</tr>
