<%@ page import ="datapro.eibs.master.Util" %>
<html>
<head>
<title>Lista Codigos de Formatos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EDP070601Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">

function goAction(op) {	
	var msg1 = "Esta seguro que desea ";
	var msg2 = "el registro seleccionado";
	document.forms[0].opt.value = op;
	
	switch (op) { 
	case  1:  
	 break;	
	case  2:  //ok = confirm(msg1 + " Actualizar " + msg2);
	          //    document.forms[0].SCREEN.value="600";
	 break;   
	case  3: ok = confirm(msg1 + " Eliminar " + msg2);
	             document.forms[0].SCREEN.value="700";
	 break;
	};
	// alert(document.forms[0].SCREEN.value);
	document.forms[0].submit();		  	
}


function getParams(currrow,prd) {
	document.forms[0].PRD.value=prd; 
	document.forms[0].CURRENTROW.value = currrow;
}


</SCRIPT>  

</head>

<BODY>
<h3 align="center">
  <% if(userPO.getOption().equals("1")) {out.print("Registro Garantia Detalle");} %>
  <% if(userPO.getOption().equals("2")) {out.print("Rutas Alternas");} %>
</h3>

<P align="center">
	<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="registros_list1.jsp, EDP0706">
</P>
<HR size="4" width="100%" align="right">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0706" >
  <p> 
    <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0"> 
    <input type=HIDDEN name="SCREEN" value="800">
    <input type=HIDDEN name="totalRow" value="0">
    <input type=HIDDEN name="opt">
  <input type=HIDDEN name="PRD" value = "<%= userPO.getHeader13().trim()%>"> 
    
  </p>
  <p> 
    <%
	if ( EDP070601Help.getNoResult() ) {
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
                <div align="center"><a href="javascript:goAction(1)"><b>Nueva</b></a></div>
              </td>
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
  <p> 

          
  <table class="tbenter" width=100% align=center>
    <tr> 
      <td class=TDBKG width="20%"> 
        <div align="center"><a href="javascript:goAction(1)"><b>Nueva</b></a></div>

     </td>
     <td class=TDBKG width="20%"> 
        <div align="center"><a href="javascript:goAction(5)"><b>Consulta</b></a></div>
     </td>
		<td class=TDBKG width="20%">
        <div align="center"><a href="javascript:goAction(2)"><b>Mantenimiento</b></a></div> 
     </td> 

	  <td class=TDBKG width="20%"> 
        <div align="center"><a href="javascript:goAction(3)"><b>Borrado</b></a></div>
      </td>
      <td class=TDBKG width="20%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
   
  <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%">
		<table id="headTable" width="100%">
			<tr id="trdark">
				<th align=CENTER nowrap width="8%">&nbsp;</th>
				<th align=CENTER nowrap width="8%">
				<div align="center">Banco</div>
				</th>
				<th align=CENTER nowrap width="35%">
				<div align="center">Producto</div>
				</th>
				<th align=CENTER nowrap width="18%">
				<div align="center">
					 	<% if(userPO.getOption().equals("1")) {out.print("Registro Garantia");} %>
  						<% if(userPO.getOption().equals("2")) {out.print("Operaci&oacute;n Adicional");} %>
				</div>
				</th>
				<th align=CENTER nowrap width="18%">
				<div align="center">Estado</div>
				</th>
			</tr>
			<%
                EDP070601Help.initRow();
                chk = "checked";
                while (EDP070601Help.getNextRow()) {
                 
                  datapro.eibs.beans.EDP070601Message msgList = (datapro.eibs.beans.EDP070601Message) EDP070601Help.getRecord();
		 %>
			<tr>
				<td NOWRAP align=CENTER width="8%"><input type="radio"
					name="CURRCODE2" value="<%= EDP070601Help.getCurrentRow() %>"
					<%=chk%>
					onClick="getParams(this.value,'<%= msgList.getE01DPQPRD() %>');"></td>
				<td NOWRAP align=LEFT width=\"8%\"><%= msgList.getE01DPQBNK() %></td>
				<td NOWRAP align=LEFT width=\"35%\"><%= msgList.getE01DPQPRD() %>-<%= msgList.getE01DPQPDE() %></td>
				<td NOWRAP align=LEFT width=\"18%\"><%= msgList.getE01DPQREG() %>-<%= msgList.getE01DPQDDE() %></td>
				<td NOWRAP align=LEFT width=\"18%\"><%= msgList.getE01DPQEDE() %></td>
			</tr>
			<%
              				chk = "";     
                }
              %>
		</table></table>
     
  <SCRIPT language="JavaScript">
 showChecked("CURRCODE");
     
</SCRIPT>

<%}%>

  </form>

</body>
</html>
