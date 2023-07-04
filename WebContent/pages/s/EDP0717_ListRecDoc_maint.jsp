<%@ page import ="datapro.eibs.master.Util" %>
<html>
<head>
<title>Lista Codigos de Formatos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "EDP071701Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<script language="JavaScript">

function goAction(op) {	
	document.forms[0].opt.value = op;
	i = 0;
   var recnum = document.forms[0].RECNUM.value;
   for (i = 0; i < recnum; i++) {

		}
//alert(document.forms[0].DPWFG20.value);
	document.forms[0].submit();		  	
}


function getParams(currrow,cacti,dacti) {

	document.forms[0].CURRENTROW.value = currrow;
}



</SCRIPT>  
</head>
<BODY>
<h3 align="center">Asignar Recaudo Documentos por Producto</h3>
<P align="center">
	<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ListRecDoc_maint, EDP0717">
	<INPUT type="text" name="PRD" size="5" maxlength="4" value="<%= userPO.getHeader9().trim()%>" readonly>
	<INPUT type="text" name="TYP" size="5" maxlength="4" value="<%= userPO.getHeader10().trim()%>" readonly>
	<INPUT type="text" name="DSC" size="35" maxlength="35" value="<%= userPO.getHeader11().trim()%>" readonly>
	
</P>
<HR size="4" width="100%" align="right">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0717" >
  <p> 
    <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0"> 
    <input type=HIDDEN name="SCREEN" value="200">
    <input type=HIDDEN name="opt">
    <input type=HIDDEN name="opt">
  </p>
  <p> 
    <%
	if ( EDP071701Help.getNoResult() ) {
 %>
  </p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>No hay resultados para su b&uacute;squeda</b></p>
          <table class="tbenter" width=100% align=center>
            <tr> 
              <td class=TDBKG width="30%"> 
                <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
              </td>
            </tr>
          </table>
          <p>&nbsp;</p>
        </div>
	  </TD>
	</TR>
    </TABLE>
  <%  
		}
	else {
%> <% 
String chk = "";

 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 
  <br>
  <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="10%"> <div align="center">Documento</div> </th>
            <th align=CENTER nowrap width="20%"> <div align="center">Descripcion</div> </th>
            <th align=CENTER nowrap width="10%"> <div align="center">Tipo</div> </th>
            <th align=CENTER nowrap width="10%"> <div align="center">Sujeto de Crédito</div> </th>
            <th align=CENTER nowrap width="10%"> <div align="center">Destino de Crédito</div> </th>
            <th align=CENTER nowrap width="10%"> <div align="center">Estado</div> </th>
           </tr>
          <%
                EDP071701Help.initRow();
				int recnum = 0;
                while (EDP071701Help.getNextRow()) {
                datapro.eibs.beans.EDP071701Message msgList = (datapro.eibs.beans.EDP071701Message) EDP071701Help.getRecord();
				
		 %>
          <tr> 
            <td NOWRAP  align=center width="10%" >
		   <%if (!msgList.getE01DPHDOC().equals("0000")) { ;%> 
			<INPUT type="text" name="E01DPHDOC<%= recnum %>" size="5" maxlength="4" value="<%= msgList.getE01DPHDOC()%>" readonly>
		   <% } %>
			</td>

            <td NOWRAP  align=LEFT width="20%"><%= msgList.getE01DPHDDE() %></td>

            <td NOWRAP  align=CENTER width="10%"> 
		   <%if (!msgList.getE01DPHDOC().equals("0000")) { ;%> 
			<SELECT name="DPHTDO<%= recnum %>" 
			>
		    <option value=" " 
			   <%if (msgList.getE01DPHTDO().equals(" ")) { out.print("selected"); }%>></option>
			<option value="1" 
			   <%if (msgList.getE01DPHTDO().equals("1")) { out.print("selected"); }%>>Requerido</option>
			<option value="3"
			   <%if (msgList.getE01DPHTDO().equals("3")) { out.print("selected"); }%>>Opcional</option>	
			</SELECT>
			   <% } %>
			</td>

            <td NOWRAP  align=CENTER width="20%">  			
		   <%if (!msgList.getE01DPHDOC().equals("0000")) { ;%> 
	   			<input type="text" name="DPHCN1<%= recnum %>" size="5" maxlength="4" value="<%= msgList.getE01DPHCN1().trim()%>" >
				<INPUT type="text" name="E01DPHCND<%= recnum %>" size="15" maxlength="15" value="<%= msgList.getE01DPHCND().trim()%>" readonly> 
				<A href="javascript:GetCodeDescCNOFC('DPHCN1<%= recnum %>','E01DPHCND<%= recnum %>','PD')">
				<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
				</A>
		   <% } %>

   			</td>
            <td NOWRAP  align=CENTER width="20%">  			
		   <%if (!msgList.getE01DPHDOC().equals("0000")) { ;%> 
	   			<input type="text" name="DPHCN2<%= recnum %>" size="5" maxlength="4" value="<%= msgList.getE01DPHCN2().trim()%>" >
				<INPUT type="text" name="E01DPHC2D<%= recnum %>" size="15" maxlength="15" value="<%= msgList.getE01DPHC2D().trim()%>" readonly> 
				<A href="javascript:GetCodeDescCNOFC('DPHCN2<%= recnum %>','E01DPHC2D<%= recnum %>','38')">
				<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
				</A>
		   <% } %>

   			</td>
            <td NOWRAP  align=CENTER width="10%">  			
		   <%if (!msgList.getE01DPHDOC().equals("0000")) { ;%> 
			<SELECT name="DPHEST<%= recnum %>" >
			<option value=" " 
			   <%if (msgList.getE01DPHEST().equals(" ")) { out.print("selected"); }%>></option>
			<option value="A" 
			   <%if (msgList.getE01DPHEST().equals("A")) { out.print("selected"); }%>>Activo</option>
			<option value="I"
				<%if (msgList.getE01DPHEST().equals("I")) { out.print("selected"); }%>>No Activo</option>
			</SELECT>
			   <% } %>
   			</td>

		    <input type=HIDDEN name="DPHDOC<%= recnum %>" value="<%= msgList.getE01DPHDOC()%>">
		    <input type=HIDDEN name="E01DPHTDO<%= recnum %>">
		    <input type=HIDDEN name="E01DPHUDO<%= recnum %>">
		    <input type=HIDDEN name="E01DPHEST<%= recnum %>">
		    <input type=HIDDEN name="E01DPHPRD<%= recnum %>">

          </tr>
          <%recnum += 1; }; %>
          <input type="HIDDEN" name="RECNUM" value="<%= recnum %>">
	</table>
  </table>
<%}%>
  <p> 
  <table class="tbenter" width=100% align=center>
    <tr> 
		<td class=TDBKG width="20%">
        <div align="center"><a href="javascript:goAction(2)"><b>Enviar</b></a></div> 
     </td> 
      <td class=TDBKG width="20%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>

</form>
</body>
</html>