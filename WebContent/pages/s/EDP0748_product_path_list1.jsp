<%@ page import ="datapro.eibs.master.Util" %>
<html>
<head>
<title>Lista Codigos de Formatos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EDP074801Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
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


function getParams(currrow,cacti,dacti) {

	document.forms[0].CURRENTROW.value = currrow;
}
</SCRIPT>  

</head>

<BODY>
<h3 align="center">Producto Ruta</h3>
<P align="center">
	<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="product_path_list1.jsp, EDP0748">
</P>
<HR size="4" width="100%" align="right">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0748" >
  <p> 
    <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0"> 
    <input type=HIDDEN name="SCREEN" value="800">
    <input type=HIDDEN name="totalRow" value="0">
    <input type=HIDDEN name="opt">
  </p>
  <p> 
    <%
	if ( EDP074801Help.getNoResult() ) {
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
   
  <br>
  <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="5%">&nbsp;</th>
            <th align=CENTER nowrap width="5%"> 
              <div align="center">Producto</div>
            </th>
            
            <th align=CENTER nowrap width="5%"> 
              <div align="center">Tipo</div>
            </th>
            
            <th align=CENTER nowrap width="45%"> 
              <div align="center">Descripcion</div>
            </th>
                        
            <th align=CENTER nowrap width="5%"> 
              <div align="center">Ruta </div>
            </th>
            <th align=CENTER nowrap width="5%"> 
              <div align="center">Ruta 1</div>
            </th>
            <th align=CENTER nowrap width="5%"> 
              <div align="center">Ruta 2</div>
            </th>
            <th align=CENTER nowrap width="5%"> 
              <div align="center">Ruta 3</div>
            </th>
            <th align=CENTER nowrap width="5%"> 
              <div align="center">Ruta 4</div>
            </th>
            <th align=CENTER nowrap width="5%"> 
 	           <div align="center">Ruta 5</div>
            </th>
            <th align=CENTER nowrap width="5%"> 
				<div align="center">Ruta 6</div>
            </th>
            <th align=CENTER nowrap width="5%"> 
	            <div align="center">Ruta 7</div>
            </th>
           <th align=CENTER nowrap width="5%"> 
              <div align="center">Ruta 8</div>
            </th>
           <th align=CENTER nowrap width="5%"> 
              <div align="center">Ruta 9</div>
            </th> 
           </tr>
          <%
               EDP074801Help.initRow();
               chk = "checked";
               while (EDP074801Help.getNextRow()) {
                datapro.eibs.beans.EDP074801Message msgList = (datapro.eibs.beans.EDP074801Message) EDP074801Help.getRecord();
		 %>
          <tr> 
            <td NOWRAP  align=CENTER width="3%"> 
              
                <input type="radio" name="CURRCODE2" value="<%= EDP074801Help.getCurrentRow() %>" <%=chk%> onClick="getParams(this.value);">
            </td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01DPJPRO() %></td>
			<td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01DPJTYP() %></td>
			<td NOWRAP  align=CENTER width=\"35%\"><%= msgList.getE01XXXDSC() %></td>
			
			<td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01DPJRU0() %></td> 
			<td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01DPJRU1() %> <BR>
												   <%= msgList.getE01DPJGU1() %></td> 
			<td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01DPJRU2() %> <BR>
												   <%= msgList.getE01DPJGU2() %></td> 
			<td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01DPJRU3() %> <BR>
												   <%= msgList.getE01DPJGU3() %></td> 
			<td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01DPJRU4() %> <BR>
												   <%= msgList.getE01DPJGU4() %></td> 
			<td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01DPJRU5() %> <BR>
												   <%= msgList.getE01DPJGU5() %></td> 
			<td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01DPJRU6() %> <BR>
												   <%= msgList.getE01DPJGU6() %></td> 
			<td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01DPJRU7() %> <BR>
												   <%= msgList.getE01DPJGU7() %></td> 
			<td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01DPJRU8() %> <BR>
												   <%= msgList.getE01DPJGU8() %></td> 
			<td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01DPJRU9() %> <BR>
												   <%= msgList.getE01DPJGU9() %></td> 
          </tr>
          <%
              				chk = "";     
                }
              %>
        </table>
  </table>
     
  <SCRIPT language="JavaScript">
 showChecked("CURRCODE");
     
</SCRIPT>

<%}%>

  </form>

</body>
</html>
