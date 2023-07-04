<html>
<head>
<title>Información Básica</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="docList" class="datapro.eibs.beans.JBListRec" scope="session"/>
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session"/>
<jsp:useBean id= "payDoc" class= "datapro.eibs.beans.EDL082501Message"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<%
	int row;
    try {
      row = Integer.parseInt(request.getParameter("CurrRow"));
    }
    catch (Exception e) {
      row = 0;
    }
    docList.setCurrentRow(row);
%> 

</head>

<body>


<h3 align="center">Detalles del Documento<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cancel_ap_det.jsp,EDL0828"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0825">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="6">
<INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%=row%>">
<table class="tableinfo">
    <tr>
        <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
            
            <TR id="trdark">
                <TD nowrap width="14%">
                <DIV align="right"><B>Prestamo :</B></DIV>
                </TD>
                <TD nowrap>
                <DIV align="left"><%= docList.getRecord(7) %></DIV>
                </TD>
                <TD nowrap width="14%">
                <DIV align="right"><B>Cliente :</B></DIV>
                </TD>
                <TD nowrap>
                <DIV align="left"><%= docList.getRecord(28) %>-<%= docList.getRecord(9) %></DIV>
                </TD>
            </TR>
		</table>
        </td>
     </tr>
 </table>
 
 <h4>Información del Giro</h4>
            
 <table class="tableinfo">
    <tr>
        <td nowrap>
          <table cellspacing="0" cellpadding="2" width="100%" border="0">
              <tr>
                <td nowrap>
                <DIV align="right"><b>Nro. Documento :</b></DIV>
                </td>
                <td nowrap><%= docList.getRecord(0)%></td>
                <td nowrap>
                <DIV align="right"><b>Estado del Dcto.:</b></DIV>
                </td>
                <td nowrap><% if (docList.getRecord(8).equals("A")) out.print("Activo");
      			   else if (docList.getRecord(8).equals("P")) out.print("Pagado");
      			   else if (docList.getRecord(8).equals("Q")) out.print("Anulado");
      			   else if (docList.getRecord(8).equals("D")) out.print("Diferido");
      			   else if (docList.getRecord(8).equals("N")) out.print("Renovado");
      			   else if (docList.getRecord(8).equals("R")) out.print("Reversión");
      			   else out.print("");%>
      			</td>
              </tr>     
              <tr>
                <td nowrap>
                <div align="right"><b>Cuenta Contable :</b></div>
                </td>
                <td nowrap><%= docList.getRecord(10)+" - "+ docList.getRecord(11)+" - "+docList.getRecord(12)+" - "+docList.getRecord(13)%></td>
                <td nowrap>
                <div align="right"><b>Fecha de Apertura :</b></div>
                </td>
                <td nowrap><%= docList.getRecord(18)%></td>
              </tr>
              <tr >
                <td nowrap>
                <div align="right"><b>Nombre Girador :</b></div>
                </td>
                <td nowrap><%= docList.getRecord(1) +" - " + docList.getRecord(2)%></td>
                <td nowrap>
                <div align="right"><b>Fecha de Vencimiento :</b></div>
                </td>
                <td nowrap><%= docList.getRecord(4)%></td>
              </tr>
              <tr>
                <td nowrap>
                <div align="right"><b>Monto Original :</b></div>
                </td>
                <td nowrap align="right"><%= docList.getRecord(3)%></td>
                <td nowrap>
                <div align="right"><b>Tasa de Interés :</b></div>
                </td>
                <td nowrap><%= docList.getRecord(29)%></td>
              </tr>
              <tr >
                <td nowrap>
                <div align="right"><b>Monto Capital :</b></div>
                </td>
                <td nowrap align="right"><%= docList.getRecord(14)%></td>
                <td nowrap>
                <div align="right"><b>Periodo de Gracia :</b></div>
                </td>
                <td nowrap><%= docList.getRecord(10)%></td>
                
              </tr>
              <tr>
                <td nowrap>
                <div align="right"><b>Monto Interés :</b></div>
                </td>
                <td nowrap align="right"><%= docList.getRecord(15)%></td>
                <td nowrap>
                <div align="right"><b>Ruta Cobro :</b></div>
                </td>
                <td nowrap><%= docList.getRecord(20)%></td>
                
              </tr>
              <tr >
                <td nowrap>
                <div align="right"><b>Monto Mora :</b></div>
                </td>
                <td nowrap align="right"><%= docList.getRecord(5)%></td>
                <td nowrap>
                <div align="right"><b>Plaza Pago :</b></div>
                </td>
                <td nowrap><%= docList.getRecord(21)%></td>
                
              </tr>
              <tr>
                <td nowrap>
                
              <div align="right"><B>Monto Revaluaci&oacute;n :</B></div>
                </td>
                <td nowrap align="right"><%= docList.getRecord(16)%></td>
                <td nowrap>
                <div align="right"><B>Agente Cobrador :</B></div>
                </td>
                <td nowrap><%= docList.getRecord(22)%></td>
              </tr>
              <tr>
                <td nowrap>
                <div align="right"><B>Saldo Total :</B></div>
                </td>
                <td nowrap align="right"><%= docList.getRecord(17)%></td>
                <td nowrap></td>
                <td nowrap></td>
              </tr>
           </table>
        </td>
     </tr>
  </table>

<% if (docList.getRecord(6).equals("4")) { %>
<h4>Información del Cheque</h4>
            
 <table class="tableinfo">
    <tr>
        <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
            <TR>
                <TD nowrap>
                <DIV align="right"><b>Banco del Cheque :</b></DIV>
                </TD>
                <TD nowrap><%= docList.getRecord(23)%></TD>
                <TD nowrap>
                <DIV align="right"><b>Localidad Banco :</b></DIV>
                </TD>
                <TD nowrap><%= docList.getRecord(24)%></TD>
            </TR>
            <TR>
                <TD nowrap>
                <DIV align="right"><b>Número de Cuenta :</b></DIV>
                </TD>
                <TD nowrap><%= docList.getRecord(25)%></TD>
                <TD nowrap>
                <DIV align="right"><b>Número de Cheque :</b></DIV>
                </TD>
                <TD nowrap><%= docList.getRecord(26)%></TD>
            </TR>
            <TR>
                <TD nowrap>
                <DIV align="right"><b>Envio a Cámara :</b></DIV>
                </TD>
                <TD nowrap><%= docList.getRecord(27)%></TD>
                <TD nowrap></TD>
                <TD nowrap></TD>
            </TR>
		</table>
        </td>
     </tr>
  </table>            
<% } %>
<br>
<table class="tableinfo">
    <tr>
        <td nowrap>
		 <table  cellspacing="0" cellpadding="2" width="100%" border="0">
		  <tr>
            <td nowrap width="20%"> 
              <div align="right">Fecha  :</div>
            </td>  
			<td nowrap >
			  <div align="left"> 
        		<input type="text" readonly name="E01PAGVD1" size="2" maxlength="2" onKeypress="enterInteger()" value="<%= payDoc.getE01PAGVD1().trim()%>">
        		<input type="text" readonly name="E01PAGVD2" size="2" maxlength="2" onKeypress="enterInteger()" value="<%= payDoc.getE01PAGVD2().trim()%>"> 
        		<input type="text" readonly name="E01PAGVD3" size="2" maxlength="2" onKeypress="enterInteger()" value="<%= payDoc.getE01PAGVD3().trim()%>">
      		  </div>
			</td>
			<td nowrapwidth="20%"> 
              <div align="right">Comentario :</div>
            </td>  
			<td nowrap >
			  <div align="left"> 
        		<input type="text" readonly name="E01DEANR1" size="30" maxlength="30" value="<%= payDoc.getE01DEANR1().trim()%>">
              </div>
			</td>
          </tr>
		  <tr>
            <td nowrap width="20%">
            </td>  
			<td nowrap> 
			</td>
			<td nowrapwidth="20%"> 
            </td>  
			<td nowrap >
			  <div align="left"> 
                <input type="text" readonly name="E01DEANR2" size="30" maxlength="30" value="<%= payDoc.getE01DEANR2().trim()%>">               
              </div>
			</td>
          </tr>
         </table>
	   </td>
    </tr>
</table>

</form>
</body>
</html>
