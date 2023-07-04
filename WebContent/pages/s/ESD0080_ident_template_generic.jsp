<%@ page import="datapro.eibs.sockets.*, datapro.eibs.beans.*" %>

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<%!
	datapro.eibs.sockets.MessageRecord mr;
 
	String title;
	String messageName;
	boolean readOnly=false;
	String READ_ONLY_TAG=""; 
%> 

<%
   //Obtiene el titulo del segmento de direccion
	title = request.getParameter("title");
	if (title == null ) title = "Identificación";

   // Obtiene el sufijo de los campos dependiendo del tipo de mensaje a desplegar.
   // Por ejemplo, si el mensaje es del tipo ESD008001, el sufijo enviado es E01, si el tipo es ESD008002 
   // el sufijo enviado ed E02
   

   // Obtiene el mensaje a desplegar
	messageName = request.getParameter("messageName");
	mr = (datapro.eibs.sockets.MessageRecord) session.getAttribute(messageName);
    System.out.println(mr.toString()) ;
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


	String suffix = "01" ;
	if( request.getParameter("suffix") != null ){
		suffix = request.getParameter("suffix")  ;
	}  
	%>

  <h4><%=title%></h4>

  <table class="tableinfo">
    <tr > 
      <td nowrap > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="15%"> 
              <div align="right">Ident. No. :</div>
            </td>
            <td nowrap width="18%"> 
              <input type="text" name="<%= "E" + suffix %>IDN" size="20" maxlength="15" value="<%= mr.getField("E" + suffix + "IDN").getString().trim()%>" <%=READ_ONLY_TAG %> <% if (mr.getField("F" + suffix + "IDN").getString().equals("Y")) out.print("id=\"txtchanged\""); %>>
               <% if( !readOnly){ %>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" >  	     		
               <% } %>
            </td>
            <td nowrap width="11%"> 
              <div align="right">Tipo :</div>
            </td>
            <td nowrap width="20%"> 
              <input type="text" name="<%= "E" + suffix %>TID" size="8" maxlength="4" value="<%= mr.getField("E" + suffix + "TID").getString().trim()%>" <%=READ_ONLY_TAG %> <% if (mr.getField("F" + suffix + "TID").getString().equals("Y")) out.print("id=\"txtchanged\""); %>>
               <% if( !readOnly){ %>
              <a href="javascript:GetCodeAuxCNOFC('<%= "E" + suffix %>TID','34','<%=mr.getField("H" + suffix + "SCR").getString().trim()%>_2' )"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" >  	     		
               <% }%>
            </td>
            <td nowrap width="11%"> 
              <div align="right">Pa&iacute;s :</div>
            </td>
            <td nowrap width="25%"> 
              <input type="text" name="<%= "E" + suffix %>PID" size="8" maxlength="4" value="<%= mr.getField("E" + suffix + "PID").getString().trim()%>" <%=READ_ONLY_TAG %> <% if (mr.getField("F" + suffix + "PID").getString().equals("Y")) out.print("id=\"txtchanged\""); %>>
               <% if( !readOnly){ %>
              <a href="javascript:GetCodeCNOFC('<%= "E" + suffix %>PID','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
               <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" >  	     		
                <% } %>
             </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%"> 
              <div align="right">Ident. No. :</div>
            </td>
            <td nowrap width="18%"> 
              <input type="text" name="<%= "E" + suffix %>IDF" size="20" maxlength="15" value="<%= mr.getField("E" + suffix + "IDF").getString().trim()%>" <%=READ_ONLY_TAG %> <% if (mr.getField("F" + suffix + "IDF").getString().equals("Y")) out.print("id=\"txtchanged\""); %>>
            </td>
            <td nowrap width="11%"> 
              <div align="right">Tipo :</div>
            </td>
            <td nowrap width="20%"> 
              <input type="text" name="<%= "E" + suffix %>TIF" size="8" maxlength="4" value="<%= mr.getField("E" + suffix + "TIF").getString().trim()%>" <%=READ_ONLY_TAG %> <% if (mr.getField("F" + suffix + "TIF").getString().equals("Y")) out.print("id=\"txtchanged\""); %>>
               <% if( !readOnly){ %>
              <a href="javascript:GetCodeAuxCNOFC('<%= "E" + suffix %>TIF','34','<%=mr.getField("H" + suffix + "SCR").getString().trim()%>_2')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
               <% } %>
            </td>
            <td nowrap width="11%"> 
              <div align="right">Pa&iacute;s :</div>
            </td>
            <td nowrap width="25%"> 
              <input type="text" name="<%= "E" + suffix %>PIF" size="8" maxlength="4" value="<%= mr.getField("E" + suffix + "PIF").getString().trim()%>" <%=READ_ONLY_TAG %> <% if (mr.getField("F" + suffix + "PIF").getString().equals("Y")) out.print("id=\"txtchanged\""); %>>
               <% if( !readOnly){ %>
              <a href="javascript:GetCodeCNOFC('<%= "E" + suffix %>PIF','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
               <% }%>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="15%" > 
              <div align="right">Ident. No. :</div>
            </td>
            <td nowrap width="18%" > 
              <input type="text" name="<%= "E" + suffix %>ID3" size="20" maxlength="15" value="<%= mr.getField("E" + suffix + "ID3").getString().trim()%>" <%=READ_ONLY_TAG %> <% if (mr.getField("F" + suffix + "ID3").getString().equals("Y")) out.print("id=\"txtchanged\""); %>>
            </td>
            <td nowrap width="11%" > 
              <div align="right"> Tipo :</div>
            </td>
            <td nowrap width="20%" > 
              <input type="text" name="<%= "E" + suffix %>TI3" size="8" maxlength="4" value="<%= mr.getField("E" + suffix + "TI3").getString().trim()%>" <%=READ_ONLY_TAG %> <% if (mr.getField("F" + suffix + "TI3").getString().equals("Y")) out.print("id=\"txtchanged\""); %>>
               <% if( !readOnly){ %>
              <a href="javascript:GetCodeAuxCNOFC('<%= "E" + suffix %>TI3','34','<%=mr.getField("H" + suffix + "SCR").getString().trim()%>_2')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
               <% } %>
            </td>
            <td nowrap width="11%" > 
              <div align="right">Pa&iacute;s :</div>
            </td>
            <td nowrap width="25%" > 
              <input type="text" name="<%= "E" + suffix %>PI3" size="8" maxlength="4" value="<%= mr.getField("E" + suffix + "PI3").getString().trim()%>" <%=READ_ONLY_TAG %> <% if (mr.getField("F" + suffix + "PI3").getString().equals("Y")) out.print("id=\"txtchanged\""); %>>
               <% if( !readOnly){ %>
              <a href="javascript:GetCodeCNOFC('<%= "E" + suffix %>PI3','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
               <% }%>
            </td>
          </tr>

        </table>
      </td>
    </tr>
  </table>