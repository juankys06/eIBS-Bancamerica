<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Detalle de Garantías</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "collDet" class= "datapro.eibs.beans.ERA001102Message"  scope="session"/>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "collList" class= "datapro.eibs.beans.JBObjList"  scope="session" />

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


<H3 align="center">Otras Inscripciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ga_detail.jsp , ERA0011"></H3> 
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0011" >
  <input type=HIDDEN name="SCREEN" value="5">
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
            			<td nowrap>
              				<INPUT type="text" name="E02RODCUN" size="11" maxlength="10" value="<%= collDet.getE02RODCUN().trim()%>">
              				<A href="javascript:GetCustomerDescId('E02RODCUN','E02RODDSC','')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></A>
            			</td>
            			<td nowrap ><div align="right">Nombre :</div></td>
            			<td nowrap >
              				<INPUT type="text" name="E02RODDSC" size="36" maxlength="35" value="<%= collDet.getE02RODDSC().trim()%>">
            			</td>
          			</tr>
          			<tr id="trclear"> 
            			<td nowrap ><div align="right">Valor Limite :</div></td>
            			<td nowrap ><div align="left">
            				<input type="text" name="E02RODAMT" size="15" maxlength="15" onKeypress="enterDecimal()" value="<%= collDet.getE02RODAMT() %>">
            			</div></td>
            			<td nowrap ><div align="right">Grado :</div></td>
            			<td nowrap>
              				<input type="text" name="E02RODUC1" size="6" maxlength="4" value="<%= collDet.getE02RODUC1() %>">
              				<A href="javascript:GetCodeCNOFC('E02RODUC1','X0')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></A>
            			</td>
          			</tr>
          			<tr id="trdark"> 
            			<td nowrap><div align="right">Limitada a Operación :</div></td>
            			<td nowrap >
							<input type="hidden" name="E02RODUC2" value="<%= collDet.getE02RODUC2()%>">
							<input type="radio" name="CE02RODUC2" value="S" onClick="document.forms[0].E02RODUC2.value='S'" 
							<%if(collDet.getE02RODUC2().equals("S")) out.print("checked");%>>S&iacute;
							<input type="radio" name="CE02RODUC2" value="N" onClick="document.forms[0].E02RODUC2.value='N'"
							<%if(collDet.getE02RODUC2().equals("N")) out.print("checked");%>>No
              				<!--input type="text" name="E02RODUC2" size="6" maxlength="4" onKeyPress="enterDecimal()" value="<%= collDet.getE02RODUC2() %>">
              				<a href="javascript:GetCodeCNOFC('E02RODUC2','2J')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></A-->
            			</td>
            			<td nowrap><div align="right">Estado del Beneficiario :</div></td>
            			<td nowrap ><div align="left">
            				<input type="text" name="E02RODEST" size="2" maxlength="1" value="<%= collDet.getE02RODEST() %>">
                			<A href="javascript:GetCode('E02RODEST','STATIC_coll_help_benef.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"  ></a>            
              			</div></td>
          			</tr>
          			<tr id="trclear">
            			<td nowrap ><div align="right">Oficina Propietaria : </div></td>
            			<td nowrap colspan=3>
              				<INPUT type="text" name="E02RODOFN" size="6" maxlength="4" value="<%= collDet.getE02RODOFN().trim()%>">
              				<a href="javascript:GetBranch('E02RODOFN','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"  ></a>                         
            			</td>
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
				            	<input type="text" name="E02RODOD1" size="3" maxlength="2" onKeypress="enterInteger()" value="<%= collDet.getE02RODOD1() %>">
				              	<input type="text" name="E02RODOD2" size="3" maxlength="2" onKeypress="enterInteger()" value="<%= collDet.getE02RODOD2() %>">
				              	<input type="text" name="E02RODOD3" size="3" maxlength="2" onKeypress="enterInteger()" value="<%= collDet.getE02RODOD3() %>">
            				</td>
            				<td nowrap><div align="right">Notaria :</div></td>
            				<td nowrap> 
              					<input type="text" name="E02RODRF4" size="4" maxlength="3" value="<%= collDet.getE02RODRF4() %>">
              					<a href="javascript:GetTypeBroker('E02RODRF4','N')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"  ></a>                        
            				</td>
          				</tr>
        			<% if (!userPO.getType().equals("05")) { %>
          				<tr id="trclear"> 
            				<td nowrap ><div align="right">Folio de Constitución :</div></td>
            				<td nowrap>               
              					<INPUT type="text" name="E02RODRF2" size="20" maxlength="20" value="<%= collDet.getE02RODRF2().trim()%>" onKeyPress="enterInteger()"> 
            				</td>
            				<td nowrap ><div align="right">Número Constitución :</div></td>
            				<td nowrap >               
              					<INPUT type="text" name="E02RODNBR" size="20" maxlength="20" value="<%= collDet.getE02RODNBR().trim()%>" onKeyPress="enterInteger()"> 
            				</td>
          				</tr>
          				<tr id="trdark"> 
            				<td nowrap ><div align="right">Año Constitución :</div></td>
            				<td nowrap >               
              					<INPUT type="text" name="E02RODAIN" size="5" maxlength="4" value="<%= collDet.getE02RODAIN().trim()%>" onKeyPress="enterInteger()"> 
            				</td>
            				<td nowrap ><div align="right">Conserv. Constitución :</div></td>
            				<td nowrap> 
              					<input type="text" name="E02RODUC7" size="5" maxlength="4" value="<%= collDet.getE02RODUC7().trim()%>" >
              					<a href="javascript:GetCodeCNOFC('E02RODUC7','2K')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"  ></a> 
            				</td>
          				</tr>
          				<tr id="trclear"> 
            				<td nowrap ><div align="right">Número Folio :</div></td>
            				<td nowrap >               
              					<INPUT type="text" name="E02RODFPH" size="30" maxlength="30" value="<%= collDet.getE02RODFPH().trim()%>"> 
            				</td>
            				<td nowrap ><div align="right">Número Prohibición :</div></td>
            				<td nowrap >               
              					<INPUT type="text" name="E02RODNPH" size="20" maxlength="20" value="<%= collDet.getE02RODNPH().trim()%>"> 
            				</td>
          				</tr>
          				<tr id="trdark"> 
            				<td nowrap ><div align="right">Año :</div></td>
            				<td nowrap >               
              					<INPUT type="text" name="E02RODYPH" size="5" maxlength="4" value="<%= collDet.getE02RODYPH().trim()%>"> 
            				</td>
            				<td nowrap ><div align="right">Conserv. Prohibición :</div></td>
            				<td nowrap >               
              					<INPUT type="text" name="E02RODCPH" size="5" maxlength="4" value="<%= collDet.getE02RODCPH().trim()%>">             
              					<a href="javascript:GetCodeCNOFC('E02RODCPH','2K')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"  ></a> 
            				</td>
          				</tr>
		  				<TR id="trclear">
							<TD nowrap align="right"></TD>
							<TD nowrap></TD>
							<TD nowrap align="right">Rollo :</TD>
							<TD nowrap>
								<INPUT type="text" name="E02RODSP1" size="30" maxlength="28" value="<%= collDet.getE02RODSP1().trim()%>">
							</TD>
		  				</TR>
		  				<tr id="trdark"> 
            				<td nowrap ><div align="right">Finca :</div></td>
							<td nowrap>
								<INPUT type="text" name="E02RODSP5" size="56" maxlength="56" value="<%= collDet.getE02RODSP5().trim()%>">
							</td>
							<td nowrap ><div align="right">Documento (Imagen) :</div></td>
            				<td nowrap>               
            					<INPUT type="text" name="E02RODSP6" size="30" maxlength="28" value="<%= collDet.getE02RODSP6().trim()%>">
							</td>
          				</tr>
         			<% } %>

         			<% if (collDet.getH02SCRCOD().equals("07")) { %> 
						<tr id="trclear">
            				<td nowrap><div align="right">Calle : </div></td>
            				<td nowrap> 
              					<input type="text" name="E02NA2"  size="37" maxlength="35" value="<%=collDet.getE02NA2().trim()%>">
           					</td>
							<TD nowrap align="right">Provincia : </TD>
							<TD nowrap>
								<INPUT type="text" name="D02STE" size="37" maxlength="35" value="<%= collDet.getD02STE().trim()%>"> 
								<A href="javascript:GetCodeDescCNOFC('E02STE','D02STE','PV')"> <IMG	src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" style="cursor:hand"></A>
							</TD>
						</tr>
           				<tr id="trdark">
            				<td nowrap><div align="right">Residencial/edificio : </div></td>
							<td nowrap>
								<input type="text" name="E02NA3" size="37" maxlength="35" value="<%= collDet.getE02NA3().trim()%>">
							</td>
							<TD nowrap align="right">Distrito : </TD>
							<TD nowrap>
								<INPUT type="text" name="D02TID" size="37" maxlength="35" value="<%= collDet.getD02TID().trim()%>">
								<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" style="cursor:hand" onclick="javascript:GetCodeDescCNOFC('E02TID','D02TID','PI')">
							</TD>
						</tr>
          				<tr id="trclear"> 
            				<td nowrap><div align="right">No. Casa/Apto :</div></td>
							<td nowrap>
								<input type="text" name="E02NA4" size="37" maxlength="35" value="<%= collDet.getE02NA4().trim()%>">
							</td>
							<TD nowrap align="right">Corregimiento : </TD>
							<TD nowrap>
								<INPUT type="text" name="D02PID" size="37" maxlength="35" value="<%= collDet.getD02PID().trim()%>"> 
								<A href="javascript:GetCodeDescCNOFC('E02PID','D02PID','PE')"> <IMG	src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" style="cursor:hand"></A>
							</TD>
						</tr>
           				<tr id="trdark">
            				<td nowrap><div align="right">Apartado Postal : </div></td>
							<td nowrap>
								<input type="hidden" name="E02STE" size="6" maxlength="4" value="<%= collDet.getE02STE().trim()%>"> 
								<A href="javascript:GetCodeDescCNOFC('E02STE','D02STE','PV')"> </A>
								<INPUT type="text" name="E02POB" size="11" maxlength="10" value="<%= collDet.getE02POB().trim()%>">
							</td>
							<TD nowrap align="right">País : </TD>
							<TD nowrap>
								<INPUT type="text" name="E02CTR" size="21" maxlength="20" value="<%= collDet.getE02CTR().trim()%>">
							</TD>
						</tr>
          				<tr id="trclear"> 
            				<td nowrap><div align="right"></div></td>
							<td nowrap>
								<input type="hidden" name="E02TID" size="6"	maxlength="4" value="<%= collDet.getE02TID().trim()%>"> 
							</td>
							<TD nowrap align="right">Código Postal : </TD>
							<TD nowrap><%System.out.println( "Cod Postal=" + collDet.getE02ZPC().trim() ) ; %>
								<INPUT type="hidden" name="E02ZPC" size="17" maxlength="15"	value="<%= (collDet.getE02ZPC() != null && !collDet.getE02ZPC().trim().equals("") && collDet.getE02ZPC().trim().length() > 4 ) ? collDet.getE02ZPC().substring(0,3) : "" %>">
								<INPUT type="text" name="D02ZPC" size="17" maxlength="15" value="<%= (collDet.getE02ZPC() != null && !collDet.getE02ZPC().trim().equals("") && collDet.getE02ZPC().trim().length() > 5 ) ? collDet.getE02ZPC().substring(4) : "" %>">
								<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" style="cursor:hand" onclick="javascript:GetCodeDescCNOFC('E02ZPC','D02ZPC' ,'59')">
							</TD>
						</tr>

					<% } else { %>
 
            			<tr id="trclear"> 
              				<td nowrap><div align="right">Dirección Principal :</div></td>
							<td nowrap>
								<input type="text" name="E02NA2" size="36" maxlength="35" value="<%= collDet.getE02NA2().trim()%>">
							</td>
							<TD nowrap align="right">Ciudad : </TD>
							<TD nowrap>
								<INPUT type="text" name="E02CTY" size="31" maxlength="30" value="<%= collDet.getE02CTY().trim()%>">
							</TD>
						</tr>
            			<tr id="trdark"> 
              				<td nowrap><div align="right"></div></td>
							<td nowrap>
								<input type="text" name="E02NA3" size="36" maxlength="35" value="<%= collDet.getE02NA3().trim()%>">
							</td>
							<TD nowrap align="right">Estado : </TD>
							<TD nowrap>
								<INPUT type="text" name="E02STE" size="5" maxlength="4" value="<%= collDet.getE02STE().trim()%>"> 
								<A href="javascript:GetCodeCNOFC('E02STE','27')"> <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0"></A>
							</TD>
						</tr>
            			<tr id="trclear"> 
              				<td nowrap><div align="right"></div></td>
							<td nowrap>
								<input type="text" name="E02NA4" size="36" maxlength="35" value="<%= collDet.getE02NA4().trim()%>">
							</td>
							<TD nowrap align="right">País : </TD>
							<TD nowrap>
								<INPUT type="text" name="E02CTR" size="21" maxlength="20" value="<%= collDet.getE02CTR().trim()%>">
							</TD>
						</tr>
            			<tr id="trdark"> 
              				<td nowrap><div align="right">Apartado Postal : </div></td>
							<td nowrap>
								<INPUT type="text" name="E02POB" size="11" maxlength="10" value="<%= collDet.getE02POB().trim()%>">
							</td>
							<TD nowrap align="right">Código Postal : </TD>
							<TD nowrap>
								<INPUT type="text" name="E02ZP0" size="16" maxlength="15" value="<%= collDet.getE02ZPC().trim()%>">
							</TD>
						</tr>

         			<% } %>   

         			<% if (userPO.getType().equals("03")) { %>
           				<tr id="trclear"> 
            				<td nowrap ><div align="right">Fecha Pub. Diaria :</div></td>
            				<td nowrap >               
             					<INPUT type="text" name="E02RODPD1" size=2 maxlength=2 value="<%= collDet.getE02RODPD1().trim()%>" onKeyPress="enterInteger()">
             					<INPUT type="text" name="E02RODPD2" size=2 maxlength=2 value="<%= collDet.getE02RODPD2().trim()%>" onKeyPress="enterInteger()">
             					<INPUT type="text" name="E02RODPD3" size=2 maxlength=2 value="<%= collDet.getE02RODPD3().trim()%>" onKeyPress="enterInteger()">
            				</td>
            				<td nowrap ><div align="right">Fecha Notificación :</div></td>
            				<td nowrap >               
             					<INPUT type="text" name="E02RODND1" size=2 maxlength=2 value="<%= collDet.getE02RODND1().trim()%>" onKeyPress="enterInteger()">
             					<INPUT type="text" name="E02RODND2" size=2 maxlength=2 value="<%= collDet.getE02RODND2().trim()%>" onKeyPress="enterInteger()">
             					<INPUT type="text" name="E02RODND3" size=2 maxlength=2 value="<%= collDet.getE02RODND3().trim()%>" onKeyPress="enterInteger()">
            				</td>
          				</tr>
         			<% } %>
   					</table>
      			</td>
    		</tr>
  		</table>
  <% } %>
  
  <div align="center"><input id="EIBSBTN" type=submit name="Submit" value="Enviar"></div>
  
</form>
</body>
</html>
