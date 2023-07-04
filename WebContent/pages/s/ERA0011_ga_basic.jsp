<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Información  Básica de Garantías</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="gaMant" class= "datapro.eibs.beans.ERA001101Message"  scope="session"/>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

 <%
   if ( !userPO.getPurpose().equals("NEW") ) {
 %>
<SCRIPT Language="Javascript">
   
    builtNewMenu(colla_M_opt);
    initMenu();
    
</SCRIPT>
  <%
   }
  %> 
<SCRIPT Language="Javascript">

  builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }

</SCRIPT>

</head>

<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
   
%> 
<h3 align="center">Garant&iacute;as - Informaci&oacute;n B&aacute;sica
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ga_basic.jsp,ERA0011"></h3> 
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0011" >


<%
	if ( !userPO.getPurpose().equals("NEW") ) {
%>
  		<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<% } else {  %>
   		<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="500">
<% } %>
    
   <input type="hidden" name="E01ROCBNK" value="<%= gaMant.getE01ROCBNK().trim()%>" >               
   <input type="hidden" name="E01ROCBRN" value="<%= gaMant.getE01ROCBRN().trim()%>" >
   <input type="hidden" name="E01ROCGLN" value="<%= gaMant.getE01ROCGLN().trim()%>" >
   <input type="hidden" name="E01ROCTYP" value="<%= gaMant.getE01ROCTYP().trim()%>">
   <input type="hidden" name="E01ROCUC9" value="<%= gaMant.getE01ROCUC9().trim()%>" >
   <input type="hidden" name="E01ROCDCC" value="<%= gaMant.getE01ROCDCC().trim()%>" >
   
                                     
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark">
            <td nowrap> 
              <div align="right"><b>Cliente : </b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <%	if ( userPO.getPurpose().equals("NEW") ) { %>  
                <input type="text" name="E01ROCCUN" size="9" maxlength="9" value="<%= gaMant.getE01ROCCUN().trim()%>">
                <a href="javascript:GetCustomerDescId('E01ROCCUN','E01CUSNA1','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
                <% } else {	%> 
                <input type="text" name="E01ROCCUN" readonly size="9" maxlength="9" value="<%= gaMant.getE01ROCCUN().trim()%>">
              	<% }%>
              </div>
            </td>
            <td nowrap> 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap> 
              <div align="left">
                <input type="text" name="E01CUSNA1" readonly size="45" maxlength="45" value="<%= gaMant.getE01CUSNA1().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap > 
              <div align="right"><b>No. Referencia : </b></div>
            </td>
            <td nowrap>
             <%	if ( userPO.getPurpose().equals("NEW") ) {	%> 
              <input type="hidden" name="E01ROCREF" size="12" maxlength="12" value="<%= gaMant.getE01ROCREF().trim()%>">
              <input type="text" name="TMPREF" size="12" maxlength="12" value="<% if (gaMant.getE01ROCREF().trim().equals("999999999999")) out.print("NUEVA CUENTA"); else out.print(gaMant.getE01ROCREF().trim()); %>" readonly>
              <% } else { %> 
              <input type="text" name="E01ROCREF" size="12" maxlength="12" value="<%= gaMant.getE01ROCREF().trim()%>" readonly>
              <% } 	%>
            </td> 
            <td nowrap> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap> 
              <div align="left">
               <input type="text" name="E01ROCCCY" size="4" maxlength="3" value="<%= gaMant.getE01ROCCCY().trim()%>" readonly> 
              </div>
            </td>
           </tr>
          <tr id="trdark">             
            <td nowrap> 
              <div align="right"><b>Producto :</b> </div>
            </td>
            <td nowrap> 
              <div align="left">
                <input type="text" name="PRODUCT" size="34" value="<%= gaMant.getE01ROCPRD().trim()%> - <%= gaMant.getE01ROCDPR().trim()%>" readonly>                 
                <input type="hidden" name="E01ROCPRD" size="4" maxlength="4" value="<%= gaMant.getE01ROCPRD().trim()%>" readonly> 
                <input type="hidden" name="E01ROCDPR" size="30" maxlength="30" value="<%= gaMant.getE01ROCDPR().trim()%>" readonly> 
              </div>
            </td>
            <td nowrap> 
              <div align="right"><b>Bien :</b> </div>
            </td>
            <td nowrap> 
              <div align="left">
                <input type="text" name="BIEN" size="34" value="<%= gaMant.getE01ROCCGT().trim()%> - <%= gaMant.getE01ROCDGT().trim()%>" readonly>                 
                <input type="hidden" name="E01ROCCGT" size="4" maxlength="4" value="<%= gaMant.getE01ROCCGT().trim()%>" readonly> 
                <input type="hidden" name="E01ROCDGT" size="30" maxlength="30" value="<%= gaMant.getE01ROCDGT().trim()%>" readonly>
                <% 
                	String bien = gaMant.getE01ROCCGT().trim() + " - " + gaMant.getE01ROCDGT().trim();
                	int idxTaxi = bien.indexOf("TAXI");
                	int idxBus = bien.indexOf("BUS");
                %> 
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  <h4>Informaci&oacute;n B&aacute;sica</h4>
      
  <table class="tableinfo" width="100%">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"> Propietario : </div>
            </td>
            <td nowrap colspan=3 width="100%">
             
              <table id="headTable" >
    			<tr id="trdark">
    			    <td nowrap align="center" >Codigo</td> 
      				<td nowrap align="center" >ID</td>
      				<td nowrap align="center" >Nombre</td>
    			</tr>
    		  </table>
    
    		  <div id="dataDiv" style="height:60; overflow-y :scroll; z-index:0" >
    		    <table id="dataTable" width="100%">
          		<%
  			 		int amount = 10;
 			 		String name;
  			 		for ( int i=1; i<=amount; i++ ){
   				  		if (i<10) name = "0" + i; else name= "" + i;
   				%> 
          		<tr id="trclear"> 
            		<td nowrap>
						<div align="center"><input type="text"
							name="E01ROWN<%= name %>" size="9" maxlength="9"
							value="<%= gaMant.getField("E01ROWN"+name).getString().trim()%>"
							oncontextmenu="showPopUp(custdescidHelp,'E01ROWN<%= name %>','','','E01RNAM<%= name %>','E01RUTO<%= name %>',''); return false;">
						</div>
						</td>
            		<td nowrap >
						<div align="center"><input type="text"
							name="E01RUTO<%= name %>" size="13" maxlength="12"
							value="<%= gaMant.getField("E01RUTO"+name).getString().trim()%>"
							oncontextmenu="showPopUp(custdescidHelp,'E01ROWN<%= name %>','','','E01RNAM<%= name %>','E01RUTO<%= name %>',''); return false;">
						</div>
						</td>
            		<td nowrap width="250">
						<div align="center"><input type="text" name="E01RNAM<%= name %>"
							size="35" maxlength="35"
							value="<%= gaMant.getField("E01RNAM"+name).getString().trim()%>"
							oncontextmenu="showPopUp(custdescidHelp,'E01ROWN<%= name %>','','','E01RNAM<%= name %>','E01RUTO<%= name %>',''); return false;">
						</div>
					</td>            		            
          		</tr>
          		<%
    				}
    			%> 
       			</table>
     		 </div>
            
            </td>            
          </tr>
          <tr id="trclear">  
            <td nowrap > 
              <div align="right">Observaciones : </div>
            </td>
            <td nowrap colspan=3> 
              <input type="text" name="E01ROCSP3" size="58" maxlength="56" value="<%= gaMant.getE01ROCSP3().trim()%>" >
             </td>            
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap colspan=3> 
              <input type="text" name="E01ROCSP4" size="58" maxlength="56" value="<%= gaMant.getE01ROCSP4().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Estado del Bien : </div>
            </td>
            <td nowrap>
              <INPUT type="text" name="E01ROCEST" size="2" maxlength="1" value="<%= gaMant.getE01ROCEST().trim()%>">
              <A href="javascript:GetCode('E01ROCEST','STATIC_coll_help_benef.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"  ></a>
               <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" width="16" height="20"  > 
            </td>
            <td nowrap > 
              <div align="right">Oficina Propietaria : </div>
            </td>
            <td nowrap >
              <INPUT type="text" name="E01ROCOF2" size="6" maxlength="4" value="<%= gaMant.getE01ROCOF2().trim()%>">
              <a href="javascript:GetBranch('E01ROCOF2','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"  ></a>                         
                             <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" width="16" height="20"  > 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Valor : </div>
            </td>
            <td nowrap>            	 
            <INPUT type="text" name="E01ROCFAA" size="17" maxlength="17" value="<%= gaMant.getE01ROCFAA().trim()%>" onkeypress="enterDecimal()">
             <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" width="16" height="20"  > 
            </td>
            <td nowrap> 
              <div align="right">Valor en $ : </div>
            </td>
            <td nowrap>            	 
            <INPUT type="text" name="E01ROCAPA" size="17" maxlength="17" value="<%= gaMant.getE01ROCAPA() %>" onkeypress="enterDecimal()"></td>
          </tr>
          <tr id="trclear"> 
            <TD nowrap align="right">Valor Limite : </TD>
            <TD nowrap>
              <INPUT type="text" name="E01ROCAM2" size="17" maxlength="17" value="<%= gaMant.getE01ROCAM2().trim()%>" onkeypress="enterDecimal()" readonly="readonly">
            </TD>
            <TD nowrap align="right">&nbsp;</TD>
            <TD nowrap>&nbsp;</TD>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Limitada a Operación : </div>
            </td>
            <td nowrap>
			  <input type="radio" name="E01ROCUC2" value="S" <%if(gaMant.getE01ROCUC2().equals("S")) out.print("checked");%>>Sí 
              <input type="radio" name="E01ROCUC2" value="N" <%if(!gaMant.getE01ROCUC2().equals("S")) out.print("checked");%>>No 
            </td>
            <td nowrap> 
              <div align="right">Estado Cliente : </div>
            </td>
            <td nowrap>
              <INPUT type="text" name="E01ROCFL4" size="2" maxlength="1" value="<%= gaMant.getE01ROCFL4().trim()%>">
              <A href="javascript:GetCode('E01ROCFL4','STATIC_coll_help_benef.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"  ></a>
               <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" width="16" height="20"  > 
              
            </td>
          </tr>
                   
          <% if (!gaMant.getE01ROCTYP().equals("05")) { %>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Grado : </div>
            </td>
            <td nowrap <% if (!gaMant.getE01ROCTYP().equals("04")) out.print("colspan=3"); %>>
              <INPUT type="text" name="E01ROCUC1" size="5" maxlength="4" value="<%= gaMant.getE01ROCUC1().trim()%>">
              <a href="javascript:GetCodeCNOFC('E01ROCUC1','X0')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"  ></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Tasado por : </div>
            </td>
            <td nowrap >
             <INPUT type="text" name="E01ROCABY" size=5 maxlength=4 value="<%= gaMant.getE01ROCABY()%>" >
              <a href="javascript:GetCodeCNOFC('E01ROCABY','X8')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"  ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" width="16" height="20"  > 
            </td>
            <td nowrap >
            </td>
            <td nowrap >
            </td>
          </tr>          
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Fecha Tasación : </div>
            </td>
            <td nowrap >
             <INPUT type="text" name="E01ROCAP1" size=2 maxlength=2 value="<%= gaMant.getE01ROCAP1()%>" onKeyPress="enterInteger()">
             <INPUT type="text" name="E01ROCAP2" size=2 maxlength=2 value="<%= gaMant.getE01ROCAP2()%>" onKeyPress="enterInteger()">
             <INPUT type="text" name="E01ROCAP3" size=2 maxlength=2 value="<%= gaMant.getE01ROCAP3()%>" onKeyPress="enterInteger()">
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" width="16" height="20"  > 
            </td>
            <td nowrap >
              <div align="right">Fecha Vencimiento : </div>
            </td>
            <td nowrap >
             <INPUT type="text" name="E01ROCMT1" size=2 maxlength=2 value="<%= gaMant.getE01ROCMT1()%>" onKeyPress="enterInteger()">
             <INPUT type="text" name="E01ROCMT2" size=2 maxlength=2 value="<%= gaMant.getE01ROCMT2()%>" onKeyPress="enterInteger()">
             <INPUT type="text" name="E01ROCMT3" size=2 maxlength=2 value="<%= gaMant.getE01ROCMT3()%>" onKeyPress="enterInteger()">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Valor Inelegible : </div>
            </td>
            <td nowrap> 
              <input type="text" name="E01ROCINA" size="15" maxlength="15" value="<%= gaMant.getE01ROCINA().trim()%>" onKeypress="enterDecimal()">
            </td>
            <td nowrap> 
              <div align="right">Valor Elegible : </div>
            </td>
            <td nowrap> 
              <%= gaMant.getE01ROCLIV().trim()%>
            </td>
          </tr>  
          <% } %>
         </table>
      </td>
    </tr>
  </table>
  <% if (gaMant.getE01ROCTYP().equals("05")) { %>
   <h4>Datos Fiador</h4>
   
 <TABLE  id="mainTable1" class="tableinfo">
  <TR><TD>
   <table id="headTable1" >
    <tr id="trdark"> 
      <td nowrap align="center" >ID</td>
      <td nowrap align="center" >Nombre</td>
    </tr>
    </table>
    
    <div id="dataDiv1" style="height:60; overflow-y :scroll; z-index:0" >
        <table id="dataTable1" >
          <%
  			 //int amount = 10;
 			 //String name;
  			 for ( int i=1; i<=amount; i++ ){
   				  if (i<10) name = "0" + i; else name= "" + i;
   			%> 
          <tr id="trclear"> 
            <td nowrap > 
              <div align="center" nowrap> 
                <input type="hidden" name="E01RFIA<%= name %>" value="<%= gaMant.getField("E01RFIA"+name).getString().trim()%>">
                <input type="text" name="E01RFRT<%= name %>" size="13" maxlength="12" value="<%= gaMant.getField("E01RFRT"+name).getString().trim()%>" 
                  oncontextmenu="showPopUp(custdescidHelp,'E01RFIA<%= name %>','','','E01RFIN<%= name %>','E01RFRT<%= name %>',''); return false;">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01RFIN<%= name %>" size="35" maxlength="35" value="<%= gaMant.getField("E01RFIN"+name).getString().trim()%>"
                 oncontextmenu="showPopUp(custdescidHelp,'E01RFIA<%= name %>','','','E01RFIN<%= name %>','E01RFRT<%= name %>',''); return false;">
              </div>
            </td>
            
          </tr>
          <%
    		}
    		%> 
       </table>
     </div>
    
     
   </TD>  
</TR>	
</TABLE>    
 
  <% }%>
  <% if (gaMant.getE01ROCTYP().equals("01") || gaMant.getE01ROCTYP().equals("02") ) { %>
   <h4>Datos Propiedad</h4>   
   <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">          
   		  <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Descripción : </div>
            </td>
            <td nowrap ><INPUT type="text" name="E01ROCDES" size="36" maxlength="35" value="<%= gaMant.getE01ROCDES().trim()%>"> 
             <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" width="16" height="20"  > 
            </td>
            <td nowrap> 
              <div align="right"> </div>
            </td>
            <td nowrap ></td>
          </tr>
   		  <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Ubicación : </div>
            </td>
            <td nowrap >
             <INPUT type="text" name="E01ROCSP1" size="56" maxlength="56" value="<%= gaMant.getE01ROCSP1().trim()%>">
            </td>
            <td nowrap> 
              <div align="right">&nbsp;</div>
            </td>
            <td nowrap >&nbsp; </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Tipo Construcción : </div>
            </td>
            <td nowrap >
             <INPUT type="text" name="E01ROCUC3" size="6" maxlength="4" value="<%= gaMant.getE01ROCUC3().trim()%>">
             <INPUT type="text" readonly name="D01ROCUC3" size="20" >
             <A href="javascript:GetCodeDescCNOFC('E01ROCUC3','D01ROCUC3','2N')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></A>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" width="16" height="20"  > 
            </td>
            <td nowrap> 
              <div align="right">Rol Propiedad : </div>
            </td>
            <td nowrap >
             <INPUT type="text" name="E01ROCRF7" size="11" maxlength="10" value="<%= gaMant.getE01ROCRF7().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Construcci&oacute;n en : </div>
            </td>
            <td nowrap colspan=3> 
              <div align="left">
              <INPUT type="text" name="E01RCRF6A" size="3" maxlength="2" value="<%= gaMant.getE01RCRF6A().trim()%>"> (Unidad)
              <INPUT type="text" name="E01RCRF6B" size="7" maxlength="6" value="<%= gaMant.getE01RCRF6B().trim()%>" onKeyPress="enterInteger()"> (Superficie)
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Terreno : </div>
            </td>
            <td nowrap colspan=3> 
              <div align="left">
              <INPUT type="text" name="E01RCRF6C" size="3" maxlength="2" value="<%= gaMant.getE01RCRF6C().trim()%>"> (Unidad)
              <INPUT type="text" name="E01RCRF6D" size="7" maxlength="6" value="<%= gaMant.getE01RCRF6D().trim()%>" onKeyPress="enterInteger()"> (Superficie)
              </div>
            </td>
          </tr> 
		  <%if(currUser.getE01INT().equals("18")){%>                                    
          <tr id="trclear">
            <td nowrap> 
              <div align="right">Comuna : </div>
            </td>
            <td nowrap >
             <INPUT type="text" name="E01ROCUC8" size="7" maxlength="6" value="<%= gaMant.getE01ROCUC8().trim()%>">
             <INPUT type="text" readonly name="D01ROCUC8" size="30" >
             <A href="javascript:GetCodeDescCNOFC('E01ROCUC8','D01ROCUC8','80')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></A>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" width="16" height="20"  > 
            </td>
            <td nowrap>&nbsp;</td>
            <td nowrap>&nbsp;</td>
          </tr>
		  <%}%>
        </table>
      </td>
    </tr>
  </table>
   
  <% } else if (gaMant.getE01ROCTYP().equals("03")) {%>
    <h4>Datos Propiedad</h4>
   
   <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">          
   		  <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Descripción : </div>
            </td>
            <td nowrap >
             <INPUT type="text" name="E01ROCDES" size="36" maxlength="35" value="<%= gaMant.getE01ROCDES().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Rol Propiedad : </div>
            </td>
            <td nowrap colspan="3"> 
              <INPUT type="text" name="E01ROCRF7" size="11" maxlength="10" value="<%= gaMant.getE01ROCRF7().trim()%>">
            </td>
          </tr>
		  <%if(currUser.getE01INT().equals("18")){%>
          <tr id="trdark">
            <td nowrap> 
              <div align="right">Comuna : </div>
            </td>
            <td nowrap >
             <INPUT type="text" name="E01ROCUC8" size="7" maxlength="6" value="<%= gaMant.getE01ROCUC8().trim()%>">
             <INPUT type="text" name="D01ROCUC8" size="30">
             <A href="javascript:GetCodeDescCNOFC('E01ROCUC8','D01ROCUC8','80')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></A>            
            </td>
          </tr>
		  <%}%>          
   </table>
      </td>
    </tr>
  </table>
  <% } %>
  <% if (!gaMant.getE01ROCTYP().equals("04")) { %>
   <h4>Datos Notaria / Conservador</h4>
    <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">          
   		  <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Fecha Escritura :</div>
            </td>
				<td nowrap colspan="3"><INPUT type="text" name="E01ROCID1" size=2
					maxlength=2 value="<%= gaMant.getE01ROCID1().trim() %>"
					onKeyPress="enterInteger()"> <INPUT type="text" name="E01ROCID2"
					size=2 maxlength=2 value="<%= gaMant.getE01ROCID2().trim() %>"
					onKeyPress="enterInteger()"> <INPUT type="text" name="E01ROCID3"
					size=2 maxlength=2 value="<%= gaMant.getE01ROCID3().trim() %>"
					onKeyPress="enterInteger()"> <img
					src="<%=request.getContextPath()%>/images/Check.gif"
					alt="campo obligatorio" align="bottom" border="0" width="16"
					height="20"></td>
				<td nowrap> 
              <div align="right">Notaria :</div>
            </td>
            <td nowrap> 
              <INPUT type="text" name="E01ROCRF4" size=4 maxlength=3 value="<%= gaMant.getE01ROCRF4().trim() %>">
              <a href="javascript:GetTypeBroker('E01ROCRF4','N')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"  ></a>                        
            </td>
          </tr>
        <% if (!gaMant.getE01ROCTYP().equals("05")) { %>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Folio de Constitución :</div>
            </td>
				<td nowrap colspan="3"><INPUT type="text" name="E01ROCRF2" size="20"
					maxlength="20" value="<%= gaMant.getE01ROCRF2().trim()%>"></td>
				<td nowrap > 
              <div align="right">Número Constitución :</div>
            </td>
            <td nowrap >               
              <INPUT type="text" name="E01ROCRF3" size="20" maxlength="20" value="<%= gaMant.getE01ROCRF3().trim()%>"> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Año Constitución :</div>
            </td>
				<td nowrap colspan="3"><INPUT type="text" name="E01ROCAIN" size="5"
					maxlength="4" value="<%= gaMant.getE01ROCAIN().trim()%>"
					onKeyPress="enterInteger()"></td>
				<td nowrap > 
              <div align="right">Conserv. Constitución :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01ROCUC7" size="5" maxlength="4" value="<%= gaMant.getE01ROCUC7().trim()%>" >
              <input type="text" readonly name="D01ROCUC7" size="30" value="<%= gaMant.getD01ROCUC7().trim()%>">
              <a href="javascript:GetCodeDescCNOFC('E01ROCUC7','D01ROCUC7','2K')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Folio de Prohibición :</div>
            </td>
				<td nowrap colspan="3"><INPUT type="text" name="E01ROCFPH" size="30"
					maxlength="30" value="<%= gaMant.getE01ROCFPH().trim()%>"></td>
				<td nowrap > 
              <div align="right">Número Prohibición :</div>
            </td>
            <td nowrap >               
              <INPUT type="text" name="E01ROCNPH" size="20" maxlength="20" value="<%= gaMant.getE01ROCNPH().trim()%>"> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Año Prohibición :</div>
            </td>
				<td nowrap colspan="3"><INPUT type="text" name="E01ROCYPH" size="5"
					maxlength="4" value="<%= gaMant.getE01ROCYPH().trim()%>"
					onKeyPress="enterInteger()"></td>
				<td nowrap > 
              <div align="right">Conserv. Prohibición :</div>
            </td>
            <td nowrap >               
              <INPUT type="text" name="E01ROCCPH" size="5" maxlength="4" value="<%= gaMant.getE01ROCCPH().trim()%>"> 
              <INPUT type="text" readonly name="D01ROCCPH" size="30" value="<%= gaMant.getD01ROCCPH().trim()%>"> 
              <a href="javascript:GetCodeDescCNOFC('E01ROCCPH','D01ROCCPH','2K')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"  ></a>             
            </td>
          </tr>
		  <TR id="trclear">
          		<% if (false) { %>
					<TD nowrap align="right">Seguro :</TD>
					<TD nowrap>
						<INPUT type="radio" name="E01ROCINF" value="Y" onclick="hidePoliza(false);"<%if(gaMant.getE01ROCINF().equals("Y")) out.print("checked");%>> Sí
						<INPUT type="radio" name="E01ROCINF" value="N" onclick="hidePoliza(true);"<%if(!gaMant.getE01ROCINF().equals("Y")) out.print("checked");%>>	No
					</TD>
				<% } %>	
				<TD nowrap align="right">Rollo :</TD>
				<TD nowrap><INPUT type="text" name="E01ROCSP1" size="30" maxlength="28" value="<%= gaMant.getE01ROCSP1().trim()%>" readonly></TD>
		  </TR>
		  <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Finca :</div>
            </td>
				<td nowrap colspan="3">
					<INPUT type="text" name="E01ROCSP5" size="56" maxlength="56" value="<%= gaMant.getE01ROCSP5().trim()%>">
				</td>
			<td nowrap > 
              <div align="right">Documento (Imagen) :</div>
            </td>
            <td nowrap>               
            	<INPUT type="text" name="E01ROCSP6" size="30" maxlength="28" value="<%= gaMant.getE01ROCSP6().trim()%>">
			</td>
          </tr>
         <% }%>

         <%if( gaMant.getH01SCRCOD().equals("07")){%> 
			<tr id="trclear">
            <td nowrap> 
              <div align="right">Calle : </div>
            </td>
            <td nowrap colspan="3"> 
              <input type="text" name="E01NA2"  size="37" maxlength="35" value="<%=gaMant.getE01NA2().trim()%>">
           	</td>
				<TD nowrap align="right">Provincia : </TD>
				<TD nowrap><INPUT type="text" name="D01STE" size="37" maxlength="35"
					value="<%= gaMant.getD01STE().trim()%>"> <A
					href="javascript:GetCodeDescCNOFC('E01STE','D01STE','PV')"> <IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
					align="bottom" border="0" style="cursor:hand"></A></TD>
			</tr>
           <tr id="trdark">
            <td nowrap> 
              <div align="right">Residencial/edificio : </div>
            </td>
				<td nowrap colspan="3"><input type="text" name="E01NA3" size="37"
					maxlength="35" value="<%= gaMant.getE01NA3().trim()%>"></td>
				<TD nowrap align="right">Distrito : </TD>
				<TD nowrap><INPUT type="text" name="D01TID" size="37" maxlength="35"
					value="<%= gaMant.getD01TID().trim()%>"> <IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
					align="bottom" border="0" style="cursor:hand"
					onclick="javascript:GetCodeDescCNOFC('E01TID','D01TID','PI')"></TD>
			</tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">No. Casa/Apto :</div>
            </td>
				<td nowrap colspan="3"><input type="text" name="E01NA4" size="37"
					maxlength="35" value="<%= gaMant.getE01NA4().trim()%>"></td>
				<TD nowrap align="right">Corregimiento : </TD>
				<TD nowrap><INPUT type="text" name="D01PID" size="37" maxlength="35"
					value="<%= gaMant.getD01PID().trim()%>"> <A
					href="javascript:GetCodeDescCNOFC('E01PID','D01PID','PE')"> <IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
					align="bottom" border="0" style="cursor:hand"></A></TD>
			</tr>
           <tr id="trdark">
            <td nowrap> 
              <div align="right">Apartado Postal : </div>
            </td>
				<td nowrap colspan="3"><input type="hidden" name="E01STE" size="6"
					maxlength="4" value="<%= gaMant.getE01STE().trim()%>"> <A
					href="javascript:GetCodeDescCNOFC('E01STE','D01STE','PV')"> </A><INPUT
					type="text" name="E01POB" size="11" maxlength="10"
					value="<%= gaMant.getE01POB().trim()%>"></td>
				<TD nowrap align="right">País : </TD>
				<TD nowrap><INPUT type="text" name="E01CTR" size="21" maxlength="20"
					value="<%= gaMant.getE01CTR().trim()%>"></TD>
			</tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right"></div>
            </td>
				<td nowrap colspan="3"><input type="hidden" name="E01TID" size="6"
					maxlength="4" value="<%= gaMant.getE01TID().trim()%>"> </td>
				<TD nowrap align="right">Código Postal : </TD>
				<TD nowrap><%System.out.println( "Cod Postal=" + gaMant.getE01ZPC().trim() ) ; %>
				<INPUT type="hidden" name="E01ZPC" size="17" maxlength="15"
					value='<%= (gaMant.getE01ZPC() != null && !gaMant.getE01ZPC().trim().equals("") && gaMant.getE01ZPC().trim().length() > 4 )
              	?gaMant.getE01ZPC().substring(0,3):""%>'>
				<INPUT type="text" name="D01ZPC" size="17" maxlength="15"
					value='<%= (gaMant.getE01ZPC() != null && !gaMant.getE01ZPC().trim().equals("") && gaMant.getE01ZPC().trim().length() > 5 )
              	?gaMant.getE01ZPC().substring(4):""%>'>
				<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
					align="bottom" border="0" style="cursor:hand"
					onclick="javascript:GetCodeDescCNOFC('E01ZPC','D01ZPC' ,'59')"></TD>
			</tr>

			<%} else {%>
 
            <tr id="trclear"> 
              <td nowrap> 
                <div align="right">Dirección Principal :</div>
              </td>
				<td nowrap colspan="3"><input type="text" name="E01NA2" size="36"
					maxlength="35" value="<%= gaMant.getE01NA2().trim()%>"></td>
				<TD nowrap align="right">Ciudad : </TD>
				<TD nowrap><INPUT type="text" name="E01CTY" size="31" maxlength="30"
					value="<%= gaMant.getE01CTY().trim()%>"></TD>
			</tr>
            <tr id="trdark"> 
              <td nowrap> 
                <div align="right"></div>
              </td>
				<td nowrap colspan="3"><input type="text" name="E01NA3" size="36"
					maxlength="35" value="<%= gaMant.getE01NA3().trim()%>"></td>
				<TD nowrap align="right">Estado : </TD>
				<TD nowrap><INPUT type="text" name="E01STE" size="5" maxlength="4"
					value="<%= gaMant.getE01STE().trim()%>"> <A
					href="javascript:GetCodeCNOFC('E01STE','27')"> <IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda"
					align="bottom" border="0"></A></TD>
			</tr>
            <tr id="trclear"> 
              <td nowrap> 
                <div align="right"></div>
              </td>
				<td nowrap colspan="3"><input type="text" name="E01NA4" size="36"
					maxlength="35" value="<%= gaMant.getE01NA4().trim()%>"></td>
				<TD nowrap align="right">País : </TD>
				<TD nowrap><INPUT type="text" name="E01CTR" size="21"
					maxlength="20" value="<%= gaMant.getE01CTR().trim()%>"></TD>
			</tr>

            <tr id="trdark"> 
              <td nowrap> 
                <div align="right">Apartado Postal : </div>
              </td>
				<td nowrap colspan="3"><INPUT type="text" name="E01POB" size="11"
					maxlength="10" value="<%= gaMant.getE01POB().trim()%>"></td>
				<TD nowrap align="right">Código Postal : </TD>
				<TD nowrap><INPUT type="text" name="E01ZPC" size="16"
					maxlength="15" value="<%= gaMant.getE01ZPC().trim()%>"></TD>
			</tr>

         <%} %>   


         <% if (gaMant.getE01ROCTYP().equals("03") && currUser.getE01INT().equals("18")) { %>
           <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Fecha Pub. Diaria :</div>
            </td>
				<td nowrap colspan="3"><INPUT type="text" name="E01ROCPDM" size=2
					maxlength=2 value="<%= gaMant.getE01ROCPDM().trim()%>"
					onKeyPress="enterInteger()"> <INPUT type="text" name="E01ROCPDD"
					size=2 maxlength=2 value="<%= gaMant.getE01ROCPDD().trim()%>"
					onKeyPress="enterInteger()"> <INPUT type="text" name="E01ROCPDY"
					size=2 maxlength=2 value="<%= gaMant.getE01ROCPDY().trim()%>"
					onKeyPress="enterInteger()"></td>
				<td nowrap > 
              <div align="right">Fecha Notificación :</div>
            </td>
            <td nowrap >               
             <INPUT type="text" name="E01ROCNDM" size=2 maxlength=2 value="<%= gaMant.getE01ROCNDM().trim()%>" onKeyPress="enterInteger()">
             <INPUT type="text" name="E01ROCNDD" size=2 maxlength=2 value="<%= gaMant.getE01ROCNDD().trim()%>" onKeyPress="enterInteger()">
             <INPUT type="text" name="E01ROCNDY" size=2 maxlength=2 value="<%= gaMant.getE01ROCNDY().trim()%>" onKeyPress="enterInteger()">
            </td>
          </tr>
         <% }%>
   </table>
      </td>
    </tr>
  </table>
  <% }%>
  
  <% if (gaMant.getE01ROCTYP().equals("04")) { %>
   <h4>Individualización Mercaderia</h4>
   <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">          
   		  <tr id="trdark"> 
            <td nowrap> 
              <div align="right">No. Documento :</div>
            </td>
            <td nowrap >
             <INPUT type="text" name="E01ROCSP1" size="13" maxlength="12" value="<%= gaMant.getE01ROCSP1().trim() %>">
            </td>
            <td nowrap >
              <div align="right">Banco/Almacen :</div>
            </td>
            <td nowrap >
              <input type="text" name="E01ROCUC6" size="6" maxlength="4" value="<%= gaMant.getE01ROCUC6().trim()%>" >
              <a href="javascript:GetCodeCNOFC('E01ROCUC6','2R')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Fecha Vencimiento :</div>
            </td>
            <td nowrap >
             <INPUT type="text" name="E01ROCNDM" size=2 maxlength=2 value="<%= gaMant.getE01ROCNDM().trim() %>" onKeyPress="enterInteger()">
             <INPUT type="text" name="E01ROCNDD" size=2 maxlength=2 value="<%= gaMant.getE01ROCNDD().trim() %>" onKeyPress="enterInteger()">
             <INPUT type="text" name="E01ROCNDY" size=2 maxlength=2 value="<%= gaMant.getE01ROCNDY().trim() %>" onKeyPress="enterInteger()">
            </td>
            <td nowrap >
              <div align="right"></div>
            </td>
            <td nowrap >
            </td>
          </tr>
   </table>
      </td>
    </tr>
  </table>
  <% }%>
  
  <% if (gaMant.getE01ROCDCC().equals("09")) { %>
 
  <h4>Informaci&oacute;n del Veh&iacute;culo</h4>
  <table class="tableinfo">
    	<tr> 
      		<td nowrap> 
        		<table cellspacing="0" cellpadding="2" width="100%" border="0">
          			<tr id="trdark"> 
            			<td nowrap ><div align="right">Tipo :</div></td>
            			<td nowrap>
              				<INPUT type="text" name="E01ROCUC8" size="5" maxlength="4" value="<%= gaMant.getE01ROCUC8().trim()%>">
              				<A href="javascript:GetCodeCNOFC('E01ROCUC8', 'A5')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></A>
              				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" width="16" height="20"  > 
            			</td>
            			<td nowrap ><div align="right">Marca :</div></td>
            			<td nowrap>
              				<INPUT type="text" name="E01ROCUC5" size="5" maxlength="4" value="<%= gaMant.getE01ROCUC5().trim()%>">
              				<A href="javascript:GetCodeCNOFC('E01ROCUC5', 'A1')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></A>
              				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" width="16" height="20"  > 
            			</td>
            			<td nowrap ><div align="right">Modelo :</div></td>
            			<td nowrap>
              				<INPUT type="text" name="E01ROCU10" size="5" maxlength="4" value="<%= gaMant.getE01ROCU10().trim()%>">
              				<A href="javascript:GetCodeCNOFC('E01ROCU10', 'A2')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></A>
              				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" width="16" height="20"  > 
            			</td>
          			</tr>
          			<tr id="trclear"> 
            			<td nowrap ><div align="right">Color :</div></td>
            			<td nowrap>
              				<INPUT type="text" name="E01ROCUC6" size="5" maxlength="4" value="<%= gaMant.getE01ROCUC6().trim()%>">
              				<A href="javascript:GetCodeCNOFC('E01ROCUC6', 'A3')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></A>
              				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" width="16" height="20"  > 
            			</td>
						<td nowrap ><div align="right">Año :</div></td>
            			<td nowrap>
							<SELECT class=inputs NAME="E01ROCAYR" >
								<%
									datapro.eibs.beans.JBObjList list = datapro.eibs.master.Util.getYearList(1994, 2050);
									list.initRow();
									while (list.getNextRow()) {
										String year = (String) list.getRecord();
								%>                                                                                 
						     			<OPTION VALUE="<%= year %>" <% if (year.equals(gaMant.getE01ROCAYR().trim())) { %> SELECTED <% } %>><%= year %></OPTION>
								<%
									}
								%>	
							</SELECT>
              				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" width="16" height="20"  > 
						</td>
						<TD nowrap align="right">Vin Motor : </TD>
						<TD nowrap>
							<INPUT type="text" name="E01ROCMVN" size="28" maxlength="26" value="<%= gaMant.getE01ROCMVN().trim()%>">
              				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" width="16" height="20"  > 
						</TD>
          			</tr>
          			<tr id="trdark"> 
						<TD nowrap align="right">Vin Chasis : </TD>
						<TD nowrap>
							<INPUT type="text" name="E01ROCCVN" size="28" maxlength="26" value="<%= gaMant.getE01ROCCVN().trim()%>">
              				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" width="16" height="20"  > 
						</TD>
						<TD nowrap align="right">Placa : </TD>
						<TD nowrap>
							<INPUT type="text" name="E01ROCSTN" size="12" maxlength="10" value="<%= gaMant.getE01ROCSTN().trim()%>">
						</TD>
						<% if (idxTaxi > 0 || idxBus > 0) { %>
						<TD nowrap align="right">Nro. Certificado de Operaciones : </TD>
						<TD nowrap>
							<SELECT class=inputs name="E01ROCNC2">
								<OPTION VALUE="SEL" <% if (gaMant.getE01ROCNC2().equals("SEL")) { %> SELECTED <% } %>>SEL</OPTION>
								<OPTION VALUE="SET" <% if (gaMant.getE01ROCNC2().equals("SET")) { %> SELECTED <% } %>>SET</OPTION>
								<OPTION VALUE="B" <% if (gaMant.getE01ROCNC2().equals("B")) { %> SELECTED <% } %>>B</OPTION>
								<OPTION VALUE="BC" <% if (gaMant.getE01ROCNC2().equals("BC")) { %> SELECTED <% } %>>BC</OPTION>
								<OPTION VALUE="T" <% if (gaMant.getE01ROCNC2().equals("T")) { %> SELECTED <% } %>>T</OPTION>
								<OPTION VALUE="RI" <% if (gaMant.getE01ROCNC2().equals("RI")) { %> SELECTED <% } %>>RI</OPTION>
							</SELECT>&nbsp;&nbsp;&nbsp;&nbsp;
							<INPUT type="text" name="E01ROCNC1" size="4" maxlength="2" value="<%= gaMant.getE01ROCNC1().trim()%>">
              				<A href="javascript:GetCodeCNOFC('E01ROCNC1', 'PV')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></A>&nbsp;&nbsp;&nbsp;&nbsp;
							<INPUT type="text" name="E01ROCNCO" size="7" maxlength="6" value="<%= gaMant.getE01ROCNCO().trim()%>">
						</TD>
						<% } else {%>
							<TD nowrap></TD>
							<TD nowrap></TD>
						<% } %>
          			</tr>
        		</table>
      		</td>
    	</tr>
  </table>
  
  <% } %> 
 
  <p align=center>
  	<input class="EIBSBTN" id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
  <SCRIPT Language="Javascript">
	
   function waitSimulate() {
     document.body.style.cursor = "wait";
   }
   document.forms[0].onsubmit= waitSimulate;

  function tableresize() {
     adjustEquTables(headTable,dataTable,dataDiv,0,false);
     <% if (gaMant.getE01ROCTYP().equals("05")) { %>
     	adjustEquTables(headTable1,dataTable1,dataDiv1,0,false);
     <%    } %>
   }
  tableresize();
  window.onresize=tableresize;

  </SCRIPT> 
  </FORM>
 </BODY>
 </html>