<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Detalle de Garantías</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "collDet" class= "datapro.eibs.beans.ERA001102Message"  scope="session"/>
<jsp:useBean id= "collList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<body>


<% 
	if ( !error.getERRNUM().equals("0")  ) {
    	error.setERRNUM("0");
     	out.println("<SCRIPT Language=\"Javascript\">");
     	out.println("       showErrors()");
     	out.println("</SCRIPT>");
 	}
 	int row;
	try {
		row = Integer.parseInt(request.getParameter("ROW"));
		collList.setCurrentRow(row);		 
		collDet = (datapro.eibs.beans.ERA001102Message) collList.getRecord();		
	} catch (Exception e) {
		row = 0;
	}
%>


<H3 align="center">Otras Inscripciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ga_detail_inquiry.jsp , ERA0011"></H3> 
<hr size="4">
<form method="post" action="" >
  <input type=hidden name="E02RODREF" value="<%= userPO.getIdentifier().trim()%>">
  <input type=hidden name="E02RODBNK" value="<%= userPO.getBank().trim()%>">
  <input type=hidden name="E02RODSEQ" value="<%= collDet.getE02RODSEQ().trim()%>">
  <input type=hidden name="ROW" value="<%= row %>">
                        
  <table class="tableinfo">
    	<tr > 
      		<td nowrap> 
        		<table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
		          <tr id="trdark"> 
		            <td nowrap><div align="right"><b>Cliente :</b></div></td>
		            <td nowrap><div align="left"><%=userPO.getCusNum().trim()%></div></td>
		            <td nowrap><div align="right"><b>Nombre :</b></div></td>
		            <td nowrap><div align="left"><%= userPO.getCusName().trim()%></div></td>
		            <td nowrap><div align="right"><b>Referencia :</b></div></td>
		            <td nowrap><div align="left"><%= userPO.getIdentifier().trim()%></div></td>
		          </tr>
        		</table>
      		</td>
    	</tr>
  </table>
  <h4>Informaci&oacute;n B&aacute;sica</h4>
  <table class="tableinfo">
    	<tr> 
      		<td nowrap> 
        		<table cellspacing="0" cellpadding="2" width="100%" border="0">
          			<tr id="trdark"> 
            			<td nowrap ><div align="right">Cliente :</div></td>
            			<td nowrap><INPUT type="text" name="E02RODCUN" readonly size="11" maxlength="10" value="<%= collDet.getE02RODCUN().trim()%>"></td>
            			<td nowrap ><div align="right">Nombre :</div></td>
            			<td nowrap ><INPUT type="text" name="E02RODDSC" readonly size="36" maxlength="35" value="<%= collDet.getE02RODDSC().trim()%>"></td>
          			</tr>
          			<tr id="trclear"> 
            			<td nowrap ><div align="right">Valor Limite :</div></td>
            			<td nowrap ><div align="left"><input type="text" name="E02RODAMT" readonly size="15" maxlength="15" value="<%= collDet.getE02RODAMT() %>"></div></td>
            			<td nowrap ><div align="right">Grado :</div></td>
            			<td nowrap><input type="text" name="E02RODUC1" readonly size="6" maxlength="4" value="<%= collDet.getE02RODUC1() %>"></td>
          			</tr>
          			<tr id="trdark"> 
            			<td nowrap><div align="right">Limitada a Operación :</div></td>
            			<td nowrap ><input type="hidden" name="E02RODUC2" readonly value="<%= collDet.getE02RODUC2()%>">
									<input type="radio" name="CE02RODUC2" disabled value="S" <%if (collDet.getE02RODUC2().equals("S")) out.print("checked");%>>S&iacute;
									<input type="radio" name="CE02RODUC2" disabled value="N" <%if (collDet.getE02RODUC2().equals("N")) out.print("checked");%>>No
            			</td>
            			<td nowrap><div align="right">Estado del Beneficiario :</div></td>
            			<td nowrap ><div align="left"><input type="text" name="E02RODEST" readonly size="2" maxlength="1" value="<%= collDet.getE02RODEST() %>"></div></td>
          			</tr>
          			<tr id="trclear">
            			<td nowrap ><div align="right">Oficina Propietaria : </div></td>
            			<td nowrap colspan=3><INPUT type="text" name="E02RODOFN" readonly size="6" maxlength="4" value="<%= collDet.getE02RODOFN().trim()%>"></td>
          			</tr>
        		</table>
      		</td>
    	</tr>
  </table>
  
  <% if (!userPO.getType().equals("04")) { %>
   		<h4>Datos Notaria / Conservador</h4>
    	<table class="tableinfo">
    		<tr > 
      			<td nowrap> 
        			<table width="100%" border="0">          
   		  				<tr id="trdark"> 
            				<td nowrap><div align="right">Fecha Escritura :</div></td>
            				<td nowrap > 
				            	<input type="text" name="E02RODOD1" readonly size="3" maxlength="2" value="<%= collDet.getE02RODOD1() %>">
				              	<input type="text" name="E02RODOD2" readonly size="3" maxlength="2" value="<%= collDet.getE02RODOD2() %>">
				              	<input type="text" name="E02RODOD3" readonly size="3" maxlength="2" value="<%= collDet.getE02RODOD3() %>">
            				</td>
            				<td nowrap><div align="right">Notaria :</div></td>
            				<td nowrap><input type="text" name="E02RODRF4" readonly size="4" maxlength="3" value="<%= collDet.getE02RODRF4() %>"></td>
          				</tr>
        			<% if (!userPO.getType().equals("05")) { %>
          				<tr id="trclear"> 
            				<td nowrap ><div align="right">Folio de Constitución :</div></td>
            				<td nowrap><INPUT type="text" name="E02RODRF2" readonly size="20" maxlength="20" value="<%= collDet.getE02RODRF2().trim()%>"></td>
            				<td nowrap ><div align="right">Número Constitución :</div></td>
            				<td nowrap ><INPUT type="text" name="E02RODNBR" readonly size="20" maxlength="20" value="<%= collDet.getE02RODNBR().trim()%>"></td>
          				</tr>
          				<tr id="trdark"> 
            				<td nowrap ><div align="right">Año Constitución :</div></td>
            				<td nowrap ><INPUT type="text" name="E02RODAIN" readonly size="5" maxlength="4" value="<%= collDet.getE02RODAIN().trim()%>"></td>
            				<td nowrap ><div align="right">Conserv. Constitución :</div></td>
            				<td nowrap><input type="text" name="E02RODUC7" readonly size="5" maxlength="4" value="<%= collDet.getE02RODUC7().trim()%>" ></td>
          				</tr>
          				<tr id="trclear"> 
            				<td nowrap ><div align="right">Número Folio :</div></td>
            				<td nowrap ><INPUT type="text" name="E02RODFPH" readonly size="30" maxlength="30" value="<%= collDet.getE02RODFPH().trim()%>"></td>
            				<td nowrap ><div align="right">Número Prohibición :</div></td>
            				<td nowrap ><INPUT type="text" name="E02RODNPH" readonly size="20" maxlength="20" value="<%= collDet.getE02RODNPH().trim()%>"></td>
          				</tr>
          				<tr id="trdark"> 
            				<td nowrap ><div align="right">Año :</div></td>
            				<td nowrap ><INPUT type="text" name="E02RODYPH" readonly size="5" maxlength="4" value="<%= collDet.getE02RODYPH().trim()%>"></td>
            				<td nowrap ><div align="right">Conserv. Prohibición :</div></td>
            				<td nowrap ><INPUT type="text" name="E02RODCPH" readonly size="5" maxlength="4" value="<%= collDet.getE02RODCPH().trim()%>"></td>
          				</tr>
		  				<TR id="trclear">
							<TD nowrap align="right"></TD>
							<TD nowrap></TD>
							<TD nowrap align="right">Rollo :</TD>
							<TD nowrap><INPUT type="text" name="E02RODSP1" readonly size="30" maxlength="28" value="<%= collDet.getE02RODSP1().trim()%>"></TD>
		  				</TR>
		  				<tr id="trdark"> 
            				<td nowrap ><div align="right">Finca :</div></td>
							<td nowrap><INPUT type="text" name="E02RODSP5" readonly size="56" maxlength="56" value="<%= collDet.getE02RODSP5().trim()%>"></td>
							<td nowrap ><div align="right">Documento (Imagen) :</div></td>
            				<td nowrap><INPUT type="text" name="E02RODSP6" readonly size="30" maxlength="28" value="<%= collDet.getE02RODSP6().trim()%>"></td>
          				</tr>
         			<% } %>

         			<% if (collDet.getH02SCRCOD().equals("07")) { %> 
						<tr id="trclear">
            				<td nowrap><div align="right">Calle : </div></td>
            				<td nowrap><input type="text" name="E02NA2" readonly size="37" maxlength="35" value="<%=collDet.getE02NA2().trim()%>"></td>
							<TD nowrap align="right">Provincia : </TD>
							<TD nowrap><INPUT type="text" name="D02STE" readonly size="37" maxlength="35" value="<%= collDet.getD02STE().trim()%>"></TD>
						</tr>
           				<tr id="trdark">
            				<td nowrap><div align="right">Residencial/edificio : </div></td>
							<td nowrap><input type="text" name="E02NA3" readonly size="37" maxlength="35" value="<%= collDet.getE02NA3().trim()%>"></td>
							<TD nowrap align="right">Distrito : </TD>
							<TD nowrap><INPUT type="text" name="D02TID" readonly size="37" maxlength="35" value="<%= collDet.getD02TID().trim()%>"></TD>
						</tr>
          				<tr id="trclear"> 
            				<td nowrap><div align="right">No. Casa/Apto :</div></td>
							<td nowrap><input type="text" name="E02NA4" readonly size="37" maxlength="35" value="<%= collDet.getE02NA4().trim()%>"></td>
							<TD nowrap align="right">Corregimiento : </TD>
							<TD nowrap><INPUT type="text" name="D02PID" readonly size="37" maxlength="35" value="<%= collDet.getD02PID().trim()%>"></TD>
						</tr>
           				<tr id="trdark">
            				<td nowrap><div align="right">Apartado Postal : </div></td>
							<td nowrap>
								<input type="hidden" name="E02STE" readonly size="6" maxlength="4" value="<%= collDet.getE02STE().trim()%>">
								<INPUT type="text" name="E02POB" readonly size="11" maxlength="10" value="<%= collDet.getE02POB().trim()%>">
							</td>
							<TD nowrap align="right">País : </TD>
							<TD nowrap><INPUT type="text" name="E02CTR" readonly size="21" maxlength="20" value="<%= collDet.getE02CTR().trim()%>"></TD>
						</tr>
          				<tr id="trclear"> 
            				<td nowrap><div align="right"></div></td>
							<td nowrap><input type="hidden" name="E02TID" readonly size="6"	maxlength="4" value="<%= collDet.getE02TID().trim()%>"></td>
							<TD nowrap align="right">Código Postal : </TD>
							<TD nowrap><%System.out.println( "Cod Postal=" + collDet.getE02ZPC().trim() ) ; %>
								<INPUT type="hidden" name="E02ZPC" readonly size="17" maxlength="15"	value="<%= (collDet.getE02ZPC() != null && !collDet.getE02ZPC().trim().equals("") && collDet.getE02ZPC().trim().length() > 4 ) ? collDet.getE02ZPC().substring(0,3) : "" %>">
								<INPUT type="text" name="D02ZPC" readonly size="17" maxlength="15" value="<%= (collDet.getE02ZPC() != null && !collDet.getE02ZPC().trim().equals("") && collDet.getE02ZPC().trim().length() > 5 ) ? collDet.getE02ZPC().substring(4) : "" %>">
							</TD>
						</tr>

					<% } else { %>
 
            			<tr id="trclear"> 
              				<td nowrap><div align="right">Dirección Principal :</div></td>
							<td nowrap><input type="text" name="E02NA2" readonly size="36" maxlength="35" value="<%= collDet.getE02NA2().trim()%>"></td>
							<TD nowrap align="right">Ciudad : </TD>
							<TD nowrap><INPUT type="text" name="E02CTY" readonly size="31" maxlength="30" value="<%= collDet.getE02CTY().trim()%>"></TD>
						</tr>
            			<tr id="trdark"> 
              				<td nowrap><div align="right"></div></td>
							<td nowrap><input type="text" name="E02NA3" readonly size="36" maxlength="35" value="<%= collDet.getE02NA3().trim()%>"></td>
							<TD nowrap align="right">Estado : </TD>
							<TD nowrap><INPUT type="text" name="E02STE" readonly size="5" maxlength="4" value="<%= collDet.getE02STE().trim()%>"></TD>
						</tr>
            			<tr id="trclear"> 
              				<td nowrap><div align="right"></div></td>
							<td nowrap><input type="text" name="E02NA4" readonly size="36" maxlength="35" value="<%= collDet.getE02NA4().trim()%>"></td>
							<TD nowrap align="right">País : </TD>
							<TD nowrap><INPUT type="text" name="E02CTR" readonly size="21" maxlength="20" value="<%= collDet.getE02CTR().trim()%>"></TD>
						</tr>
            			<tr id="trdark"> 
              				<td nowrap><div align="right">Apartado Postal : </div></td>
							<td nowrap><INPUT type="text" name="E02POB" readonly size="11" maxlength="10" value="<%= collDet.getE02POB().trim()%>"></td>
							<TD nowrap align="right">Código Postal : </TD>
							<TD nowrap><INPUT type="text" name="E02ZP0" readonly size="16" maxlength="15" value="<%= collDet.getE02ZPC().trim()%>"></TD>
						</tr>

         			<% } %>   

         			<% if (userPO.getType().equals("03")) { %>
           				<tr id="trclear"> 
            				<td nowrap ><div align="right">Fecha Pub. Diaria :</div></td>
            				<td nowrap >               
             					<INPUT type="text" name="E02RODPD1" readonly size=2 maxlength=2 value="<%= collDet.getE02RODPD1().trim()%>">
             					<INPUT type="text" name="E02RODPD2" readonly size=2 maxlength=2 value="<%= collDet.getE02RODPD2().trim()%>">
             					<INPUT type="text" name="E02RODPD3" readonly size=2 maxlength=2 value="<%= collDet.getE02RODPD3().trim()%>">
            				</td>
            				<td nowrap ><div align="right">Fecha Notificación :</div></td>
            				<td nowrap >               
             					<INPUT type="text" name="E02RODND1" readonly size=2 maxlength=2 value="<%= collDet.getE02RODND1().trim()%>">
             					<INPUT type="text" name="E02RODND2" readonly size=2 maxlength=2 value="<%= collDet.getE02RODND2().trim()%>">
             					<INPUT type="text" name="E02RODND3" readonly size=2 maxlength=2 value="<%= collDet.getE02RODND3().trim()%>">
            				</td>
          				</tr>
         			<% } %>
   					</table>
      			</td>
    		</tr>
  		</table>
  <% } %>
</form>  
</body>
</html>
