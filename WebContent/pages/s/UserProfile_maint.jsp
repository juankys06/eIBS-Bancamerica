<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>User Profile</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<%@ page import = "java.io.*,java.net.*,datapro.eibs.beans.*,java.math.*,com.datapro.eibs.security.bean.*" %>

<jsp:useBean id="userInfo" class="com.datapro.eibs.security.bean.USERBean"  scope="session" />

<jsp:useBean id="groupList" class="datapro.eibs.beans.JBObjList"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


<SCRIPT Language="Javascript">

  var user_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/com.datapro.eibs.security.servlet.JSUserProfile?SCREEN=5&userid=<%= userInfo.getBTHKEY()%>&defbank=<%= userInfo.getBTHUBK()%>&defbranch=<%= userInfo.getBTHUBR()%>>Sucursales</A><BR>"+ 	  	
 "<A HREF= <%=request.getContextPath()%>/servlet/com.datapro.eibs.security.servlet.JSUserProfile?SCREEN=3&userid=<%= userInfo.getBTHKEY()%>>Privilegios</A><BR>"+ 	  	
 "<A HREF= javascript:checkClose()>Salir</A>"; 	  	
  
  builtNewMenu(user_opt);
  initMenu();
  
  function tableresize() {
     adjustEquTables(headTable,dataTable,dataDiv,0,false);
  }
  
  function showTab(index){  
  	for(var i=0;i<3;i++){
   		document.all["Tab"+i].className="tabnormal";
   		document.all["dataTab"+i].style.display="none";
   	}
  
    document.all["Tab"+index].className="tabhighlight";  
  	document.all["dataTab"+index].style.display="";
  	if (index == 2) tableresize();
  }

  window.onresize=tableresize;
</SCRIPT>

</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.security.servlet.JSUserProfile" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="20">
  
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="20%"> 
              <b>Usuario :</b>
            </td>
            <td nowrap > 
               <input type="hidden" name="EUPUSR" size="14" maxlength="10" value="<%= userInfo.getEUPUSR()%>">
               <input type="text" name="BTHKEY" size="10" maxlength="10" value="<%= userInfo.getBTHKEY()%>">
            </td>
          </tr>          
        </table>
      </td>
    </tr>
  </table>
  
  <H4>Perfil e-IBS</H4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap>
		<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">

			<TBODY>
				<TR id="trdark">
					<TD nowrap>
					<DIV align="right">Status :</DIV>
					</TD>
					<TD nowrap><SELECT name="EUPSTS">
						<OPTION value="1"
							<% if (!(userInfo.getEUPSTS().equals("2") ||userInfo.getEUPSTS().equals("3")||userInfo.getEUPSTS().equals("4"))) out.print("selected"); %>>Activo</OPTION>
						<OPTION value="2"
							<% if (userInfo.getEUPSTS().equals("2")) out.print("selected"); %>>Inactivado</OPTION>
						<OPTION value="3"
							<% if (userInfo.getEUPSTS().equals("3")) out.print("selected"); %>>Suspendido</OPTION>
						<OPTION value="4"
							<% if (userInfo.getEUPSTS().equals("4")) out.print("selected"); %>>Pendiente
						Activacion</OPTION>
					</SELECT></TD>
				</TR>
				<TR id="trclear">
					<TD nowrap>
					<DIV align="right">Identificacion :</DIV>
					</TD>
					<TD nowrap><INPUT type="text" name="EUPIDN" size="15"
						maxlength="15" value="<%= userInfo.getEUPIDN()%>"></TD>
				</TR>
				<TR id="trdark">
					<TD nowrap>
					<DIV align="right">Nombre :</DIV>
					</TD>
					<TD nowrap><INPUT type="text" name="EUPNME" size="30"
						maxlength="45" value="<%= userInfo.getEUPNME()%>"></TD>
				</TR>
				<TR id="trclear">
					<TD nowrap>
					<DIV align="right">Direccion Internet :</DIV>
					</TD>
					<TD nowrap><INPUT type="text" name="EUPEML" size="30"
						maxlength="40" value="<%= userInfo.getEUPEML()%>"></TD>
				</TR>
				<TR id="trdark">
					<TD nowrap>
					<DIV align="right">Telefono :</DIV>
					</TD>
					<TD nowrap><INPUT type="text" name="EUPPHN" size="11"
						maxlength="11" value="<%= userInfo.getEUPPHN()%>"
						onkeypress="enterInteger()"></TD>
				</TR>
				<TR id="trclear">
					<TD nowrap width="26%">
					<DIV align="right">Tel. Extension :</DIV>
					</TD>
					<TD nowrap width="26%"><INPUT type="text" name="EUPPXT" size="4"
						maxlength="4" value="<%= userInfo.getEUPPXT()%>"
						onkeypress="enterInteger()"></TD>
				</TR>
				<!--<tr id="trdark">
            <td nowrap> 
              <div align="right">Oficina Admin. :</div>
            </td>
            <td nowrap>
              <input type="text" name="EUPOAF" size="4" maxlength="4" value="">
            </td>
          </tr>-->
				<TR id="trdark">
					<TD nowrap>
					<DIV align="right">Usuario Nivel Sup. :</DIV>
					</TD>
					<TD nowrap><INPUT type="text" name="EUPROT" size="11"
						maxlength="10" value="<%= userInfo.getEUPROT()%>"> <A
						href="javascript:GetUserLevel('EUPROT')"><IMG
						src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom"
						border="0"></A></TD>
				</TR>
				<TR id="trclear">
					<TD nowrap>
					<DIV align="right">Unidad de Negocios :</DIV>
					</TD>
					<TD nowrap><INPUT type="text" name="EUPSEG" size="5" maxlength="4"
						value="<%= userInfo.getEUPSEG()%>"> <A
						href="javascript:GetCodeDescCNOFC('EUPSEG','','2D')"><IMG
						src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom"
						border="0"></A></TD>
				</TR>
				<TR id="trdark">
					<TD nowrap>
					<DIV align="right">Area de Trabajo :</DIV>
					</TD>
					<TD nowrap><SELECT name="EUPLNA">
						<OPTION value="C"
							<% if (userInfo.getEUPLNA().equals("C")) out.print("selected"); %>>Comercial</OPTION>
						<OPTION value="R"
							<% if (userInfo.getEUPLNA().equals("R")) out.print("selected"); %>>Riesgo</OPTION>
					</SELECT></TD>
				</TR>
				<TR id="trclear">
					<TD nowrap>
					<DIV align="right">Tipo usuario :</DIV>
					</TD>
					<TD nowrap><SELECT name="EUPF04">
						<OPTION value="01"
							<% if (userInfo.getEUPF04().equals("01")) out.print("selected"); %>>Presidente</OPTION>
						<OPTION value="02"
							<% if (userInfo.getEUPF04().equals("02")) out.print("selected"); %>>Junta
						Directiva</OPTION>
						<OPTION value="03"
							<% if (userInfo.getEUPF04().equals("03")) out.print("selected"); %>>Vicepresidente</OPTION>
						<OPTION value="04"
							<% if (userInfo.getEUPF04().equals("04")) out.print("selected"); %>>Gerente</OPTION>
						<OPTION value="05"
							<% if (userInfo.getEUPF04().equals("05")) out.print("selected"); %>>Directores</OPTION>
						<OPTION value="06"
							<% if (userInfo.getEUPF04().equals("06")) out.print("selected"); %>>Subgerentes</OPTION>
						<OPTION value="07"
							<% if (userInfo.getEUPF04().equals("07")) out.print("selected"); %>>Supervisores</OPTION>
						<OPTION value="08"
							<% if (userInfo.getEUPF04().equals("08")) out.print("selected"); %>>Operadores</OPTION>
						<OPTION value="09"
							<% if (userInfo.getEUPF04().equals("09")) out.print("selected"); %>>Vendedores</OPTION>
						<OPTION value="10"
							<% if (userInfo.getEUPF04().equals("10")) out.print("selected"); %>>Secretarias</OPTION>
						<OPTION value="11"
							<% if (userInfo.getEUPF04().equals("11")) out.print("selected"); %>>Auxiliares</OPTION>
						<OPTION value="12"
							<% if (userInfo.getEUPF04().equals("12")) out.print("selected"); %>>Auditores</OPTION>
						<OPTION value="13"
							<% if (userInfo.getEUPF04().equals("13")) out.print("selected"); %>>Cajeros</OPTION>
						<OPTION value="99"
							<% if (userInfo.getEUPF04().equals("99")) out.print("selected"); %>>Externos</OPTION>
					</SELECT></TD>

				</TR>
			</TBODY>
		</TABLE>
		</td>
    </tr>
  </table>
  <H4>Perfil IBS</H4>
  <table class="tbenter" width="100%" border="0" cellspacing=0 cellpadding=0>
    <tr> 
       <td nowrap> 
           <table id="TableTab" cellspacing=0 cellpadding=2 border="0">
                <tr> 
                    <td nowrap id="Tab0" class="tabhighlight" onClick="showTab(0)"> 
                            <div nowrap>Mnto. a Cuentas</div>
                    </td> 
                    <td nowrap id="Tab1" class="tabnormal" onClick="showTab(1)"> 
                            <div nowrap>Lotes Contab.</div>
                    </td> 
                    <td nowrap id="Tab2" class="tabnormal" onClick="showTab(2)"> 
                            <div nowrap>Otras Autorizaciones</div>
                    </td> 
                    <td class="tabempty" width="100%">&nbsp;                       
                    </td> 
                 </tr>
            </table>
       </td>
    </tr>
  </table>
  <table class="tabdata" width="100%">
    <tr>
     <td nowrap>
  		<div id="dataTab0">
  			<table width="100%" border="0" cellspacing="0" cellpadding="0">
  				<tr id="trdark">
  				    <td nowrap> 
              			<div align="right">Banco/Suc./C.Costo :</div>
            		</td>
            		<td nowrap>
              			<input type="text" name="BTHUBK" size="3" maxlength="2" value="<%= userInfo.getBTHUBK()%>" readonly>
              			<input type="text" name="BTHUBR" size="4" maxlength="3" value="<%= userInfo.getBTHUBR()%>" readonly>
              		    <input type="text" name="BTHCCN" size="8" maxlength="8" value="<%= userInfo.getBTHCCN()%>" onkeypress="enterInteger()">
              			<a href="javascript:GetCostCenter('BTHCCN',document.forms[0].BTHUBK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absmiddle" border="0" ></a> 
            		</td>
  				</tr>
  				<tr id="trclear">
  				    <td nowrap> 
              			<div align="right">Contraseña :</div>
            		</td>
            		<td nowrap>
              			<input type="password" name="BTHPSW" size="5" maxlength="4" value="<%= userInfo.getBTHPSW()%>">
            		</td>
  				</tr>
  				<tr id="trdark">
  				    <td nowrap> 
              			<div align="right">Id. Grupo :</div>
            		</td>
            		<td nowrap>
              			<input type="hidden" name="BTHF03" size="10" value="<%= userInfo.getBTHF03()%>">
            			<% if (userInfo.getBTHF03().equals("")) out.print("SIN DEFINICION"); else out.print(userInfo.getBTHF03());%>
            		</td>
  				</tr>
  				<tr id="trclear">
  				    <td nowrap> 
              			<div align="right">Numero Cliente :</div>
            		</td>
            		<td nowrap>
              			<input type="text" name="BTHCUN" size="10" maxlength="9" value="<%= userInfo.getBTHCUN()%>" onkeypress="enterInteger()">
            		</td>
  				</tr>
  				<tr id="trdark">
  					<td nowrap> 
              			<div align="right">Oficial :</div>
            		</td>
            		<td nowrap>
              			<input type="text" name="BTHOFC" size="5" maxlength="4" value="<%= userInfo.getBTHOFC()%>">
              			<a href="javascript:GetCodeDescCNOFC('BTHOFC','','15')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a>
            		</td>
  				</tr>
  				<tr id="trclear">
  				    <td nowrap> 
              			<div align="right">MDA/Monto x Aprob. :</div>
            		</td>
            		<td nowrap>
              			<input type="text" name="BTHCCY" size="3" maxlength="3" value="<%= userInfo.getBTHCCY()%>">
              			<input type="text" name="BTHALM" size="15" maxlength="15" value="<%= userInfo.getBTHALM()%>" onkeypress="enterDecimal()">
            		</td>
  				</tr>
  				<tr id="trdark">
  					<td nowrap> 
              			<div align="right">Ptos. Tolerancia/Tipo :</div>
            		</td>
            		<td nowrap>
              			<input type="text" name="BTHPRT" size="5" maxlength="5" value="<%= userInfo.getBTHPRT()%>" onkeypress="enterDecimal()">
              			<input type="text" name="BTHTFL" size="2" maxlength="1" value="<%= userInfo.getBTHTFL()%>">
            		</td>
  				</tr>
  				<tr id="trclear">
  					<td nowrap> 
              			<div align="right">Tipo Usuario :</div>
            		</td>
            		<td nowrap>
            		    <select name="BTHAUT">
                			<option value="S" <% if (!(userInfo.getBTHAUT().equals("O") ||userInfo.getBTHAUT().equals("B")||userInfo.getBTHAUT().equals("I"))) out.print("selected"); %>>Supervisor</option>
                			<option value="O" <% if (userInfo.getBTHAUT().equals("O")) out.print("selected"); %>>Operador</option>
                			<option value="B" <% if (userInfo.getBTHAUT().equals("B")) out.print("selected"); %>>Superv/Operad</option>
                			<option value="I" <% if (userInfo.getBTHAUT().equals("I")) out.print("selected"); %>>Consulta</option>
              			</select>
              		</td>
  				</tr>
  				<tr id="trdark">
  					<td nowrap> 
              			<div align="right">Idioma :</div>
            		</td>
            		<td nowrap>
              			<select name="BTHLAN">
                			<option value="S" <% if (!userInfo.getBTHLAN().equals("E")) out.print("selected"); %>>Espanol</option>
                			<option value="E" <% if (userInfo.getBTHLAN().equals("E")) out.print("selected"); %>>Ingles</option>
                		</select>
                	</td>
  				</tr>
  				<tr id="trclear">
  				    <td nowrap> 
              			<div align="right">Nivel de Consulta :</div>
            		</td>
            		<td nowrap>
              			<input type="text" name="BTHINL" size="2" maxlength="1" value="<%= userInfo.getBTHINL()%>" onkeypress="enterInteger()"> (0 - 9)
            		</td>
  				</tr>
  				<tr id="trdark">
  				    <td nowrap> 
              			<div align="right">Nivel de Contabilizacion :</div>
            		</td>
            		<td nowrap>
              			<input type="text" name="BTHACL" size="2" maxlength="1" value="<%= userInfo.getBTHACL()%>" onkeypress="enterInteger()"> (0 - 9)
            		</td>
  				</tr>
  				<tr id="trclear">
  				    <td nowrap> 
              			<div align="right">Nivel Aprob. Cliente :</div>
            		</td>
            		<td nowrap>
              			<select name="BTHCAF">
                			<option value="N" <% if (!(userInfo.getBTHCAF().equals("1") ||userInfo.getBTHCAF().equals("2"))) out.print("selected"); %>>No Autorizado</option>
                			<option value="1" <% if (userInfo.getBTHCAF().equals("1")) out.print("selected"); %>>Regular</option>
                			<option value="2" <% if (userInfo.getBTHCAF().equals("2")) out.print("selected"); %>>Cambio de ID</option>
                		</select>
            		</td>
  				</tr>
  			</table>
  		</div>
        <div id="dataTab1" style="display: none">
        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
        		<tr id="trdark">
  				    <th nowrap> 
              			<div align="center">Rango de Lotes</div>
            		</th>
            		<th nowrap> 
              			<div align="center">Inicial</div>
            		</th>
            		<th nowrap> 
              			<div align="center">Final</div>
            		</th>
  				</tr>
  				<tr>
  				    <td nowrap> 
              			<div align="right">Rango Lotes 1 :</div>
            		</td>
            		<td nowrap align="center">
              			<input type="text" name="BTHFB1" size="5" maxlength="4" value="<%= userInfo.getBTHFB1()%>" onkeypress="enterInteger()">
            		</td>
            		<td nowrap align="center">
              			<input type="text" name="BTHTB1" size="5" maxlength="4" value="<%= userInfo.getBTHTB1()%>" onkeypress="enterInteger()">
            		</td>
  				</tr>
  				<tr>
  				    <td nowrap> 
              			<div align="right">Rango Lotes  2 :</div>
            		</td>
            		<td nowrap align="center">
              			<input type="text" name="BTHFB2" size="5" maxlength="4" value="<%= userInfo.getBTHFB2()%>" onkeypress="enterInteger()">
            		</td>
            		<td nowrap align="center">
              			<input type="text" name="BTHTB2" size="5" maxlength="4" value="<%= userInfo.getBTHTB2()%>" onkeypress="enterInteger()">
            		</td>
  				</tr>
  				<tr>
  				    <td nowrap> 
              			<div align="right">Negociacion L/C :</div>
            		</td>
            		<td nowrap align="center">
              			<input type="text" name="BTHFB3" size="5" maxlength="4" value="<%= userInfo.getBTHFB3()%>" onkeypress="enterInteger()">
            		</td>
            		<td nowrap align="center">
              			<input type="text" name="BTHTB3" size="5" maxlength="4" value="<%= userInfo.getBTHTB3()%>" onkeypress="enterInteger()">
            		</td>
  				</tr>
  				<tr>
  				    <td nowrap> 
              			<div align="right">Lote x Omision :</div>
            		</td>
            		<td nowrap align="center">
              			<input type="text" name="BTHDIB" size="5" maxlength="4" value="<%= userInfo.getBTHDIB()%>" onkeypress="enterInteger()">
            		</td>
            		<td nowrap align="center">
              
            		</td>
  				</tr>
  				<tr>
  				    <td nowrap> 
              			<div align="right">Lote para P & R :</div>
            		</td>
            		<td nowrap align="center">
              			<input type="text" name="BTHPRB" size="5" maxlength="4" value="<%= userInfo.getBTHPRB()%>" onkeypress="enterInteger()">
            		</td>
            		<td nowrap align="center">
              
            		</td>
  				</tr>
  				
  			</table>
  		</div>
        <div id="dataTab2" style="display: none">
          <TABLE  id="mainTable" width="100%">
			<TR> 
    		<TD NOWRAP width="100%" >
    			
        	<table id="headTable">
    			<tr id="trdark">  
      				<th ALIGN=CENTER nowrap>Autorizar a</th>
      				<th ALIGN=CENTER nowrap>SI</th>
      				<th ALIGN=CENTER nowrap>NO</th>
    			</tr>
   			</table>
   			<div id="dataDiv" style="overflow-Y:scroll; height:280; padding:0" class="scbarcolor" NOWRAP>
    			<table id="dataTable">
    				<tr>
  				    	<td nowrap> 
              				<div align="right">Cambiar Nro. de Clientes :</div>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHCCU" value="Y" <%if(userInfo.getBTHCCU().equals("Y")) out.print("checked");%>>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHCCU" value="N" <%if(!userInfo.getBTHCCU().equals("Y")) out.print("checked");%>>
            			</td>
  					</tr>			
  					<tr>
  				    	<td nowrap> 
              				<div align="right">Cambiar Estado de la Cuenta :</div>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHCAC" value="Y" <%if(userInfo.getBTHCAC().equals("Y")) out.print("checked");%>>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHCAC" value="N" <%if(!userInfo.getBTHCAC().equals("Y")) out.print("checked");%>>
            			</td>
  					</tr>
  					<tr>
  				    	<td nowrap> 
              				<div align="right">Cambiar Retenciones y Garantias :</div>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHCHL" value="Y" <%if(userInfo.getBTHCHL().equals("Y")) out.print("checked");%>>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHCHL" value="N" <%if(!userInfo.getBTHCHL().equals("Y")) out.print("checked");%>>
            			</td>
  					</tr>
  					<tr>
  				    	<td nowrap> 
              				<div align="right">Aprobar Nuevos Prestamos (Activo) :</div>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHLNA" value="Y" <%if(userInfo.getBTHLNA().equals("Y")) out.print("checked");%>>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHLNA" value="N" <%if(!userInfo.getBTHLNA().equals("Y")) out.print("checked");%>>
            			</td>
  					</tr>
  					<tr>
  				    	<td nowrap> 
              				<div align="right">Aprobar Nuevos Cert.Depos. (Pasivo) :</div>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHCDA" value="Y" <%if(userInfo.getBTHCDA().equals("Y")) out.print("checked");%>>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHCDA" value="N" <%if(!userInfo.getBTHCDA().equals("Y")) out.print("checked");%>>
            			</td>
  					</tr>
  					<tr>
  				    	<td nowrap> 
              				<div align="right">Aprobar Sobregiro Pagos y Recibo :</div>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHAOP" value="Y" <%if(userInfo.getBTHAOP().equals("Y")) out.print("checked");%>>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHAOP" value="N" <%if(!userInfo.getBTHAOP().equals("Y")) out.print("checked");%>>
            			</td>
  					</tr>
  					<tr>
  				    	<td nowrap> 
              				<div align="right">Aprobar S/giro x Oper.Comerc.Exter. :</div>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHF02" value="Y" <%if(userInfo.getBTHF02().equals("Y")) out.print("checked");%>>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHF02" value="N" <%if(!userInfo.getBTHF02().equals("Y")) out.print("checked");%>>
            			</td>
  					</tr>
  					<tr>
  				    	<td nowrap> 
              				<div align="right">Aprobar Cartas de Credito :</div>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHLEC" value="Y" <%if(userInfo.getBTHLEC().equals("Y")) out.print("checked");%>>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHLEC" value="N" <%if(!userInfo.getBTHLEC().equals("Y")) out.print("checked");%>>
            			</td>
  					</tr>
  					<tr>
  				    	<td nowrap> 
              				<div align="right">Aprobar Lineas de Credito:</div>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHLIC" value="Y" <%if(userInfo.getBTHLIC().equals("Y")) out.print("checked");%>>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHLIC" value="N" <%if(!userInfo.getBTHLIC().equals("Y")) out.print("checked");%>>
            			</td>
  					</tr>
  					<tr>
  				    	<td nowrap> 
              				<div align="right">Aprobar S/Giro Lineas/Ocacional :</div>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHODL" value="Y" <%if(userInfo.getBTHODL().equals("Y")) out.print("checked");%>>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHODL" value="N" <%if(!userInfo.getBTHODL().equals("Y")) out.print("checked");%>>
            			</td>
  					</tr>
  					<tr>
  				    	<td nowrap> 
              				<div align="right">Aprobar Contabilidad Mda. Ext. :</div>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHAFE" value="Y" <%if(userInfo.getBTHAFE().equals("Y")) out.print("checked");%>>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHAFE" value="N" <%if(!userInfo.getBTHAFE().equals("Y")) out.print("checked");%>>
            			</td>
  					</tr>
  					<tr>
  				    	<td nowrap> 
              				<div align="right">Trabajar con Inversiones :</div>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHFL2" value="Y" <%if(userInfo.getBTHFL2().equals("Y")) out.print("checked");%>>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHFL2" value="N" <%if(!userInfo.getBTHFL2().equals("Y")) out.print("checked");%>>
            			</td>
  					</tr>
  					<tr>
  				    	<td nowrap> 
              				<div align="right">Asientos Contables:</div>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHCGL" value="Y" <%if(userInfo.getBTHCGL().equals("Y")) out.print("checked");%>>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHCGL" value="N" <%if(!userInfo.getBTHCGL().equals("Y")) out.print("checked");%>>
            			</td>
  					</tr>
  					<tr>
  				    	<td nowrap> 
              				<div align="right">Asientos Ctas. Detalles :</div>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHRTL" value="Y" <%if(userInfo.getBTHRTL().equals("Y")) out.print("checked");%>>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHRTL" value="N" <%if(!userInfo.getBTHRTL().equals("Y")) out.print("checked");%>>
            			</td>
  					</tr>
  					<tr>
  				    	<td nowrap> 
              				<div align="right">Asientos Contratos Activos :</div>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHASS" value="Y" <%if(userInfo.getBTHASS().equals("Y")) out.print("checked");%>>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHASS" value="N" <%if(!userInfo.getBTHASS().equals("Y")) out.print("checked");%>>
            			</td>
  					</tr>
  					<tr>
  				    	<td nowrap> 
              				<div align="right">Asientos Contratos Pasivos :</div>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHLIB" value="Y" <%if(userInfo.getBTHLIB().equals("Y")) out.print("checked");%>>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHLIB" value="N" <%if(!userInfo.getBTHLIB().equals("Y")) out.print("checked");%>>
            			</td>
  					</tr>
  					<tr>
  				    	<td nowrap> 
              				<div align="right">Asientos Cartas Credito :</div>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHLCR" value="Y" <%if(userInfo.getBTHLCR().equals("Y")) out.print("checked");%>>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHLCR" value="N" <%if(!userInfo.getBTHLCR().equals("Y")) out.print("checked");%>>
            			</td>
  					</tr>
  					<tr>
  				    	<td nowrap> 
              				<div align="right">Asientos Reembolsos :</div>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHRBM" value="Y" <%if(userInfo.getBTHRBM().equals("Y")) out.print("checked");%>>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHRBM" value="N" <%if(!userInfo.getBTHRBM().equals("Y")) out.print("checked");%>>
            			</td>
  					</tr>
  					<tr>
  				    	<td nowrap> 
              				<div align="right">Asientos Hipotecas :</div>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHMLF" value="Y" <%if(userInfo.getBTHMLF().equals("Y")) out.print("checked");%>>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHMLF" value="N" <%if(!userInfo.getBTHMLF().equals("Y")) out.print("checked");%>>
            			</td>
  					</tr>
  					<tr>
  				    	<td nowrap> 
              				<div align="right">Asientos Varios :</div>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHOTH" value="Y" <%if(userInfo.getBTHOTH().equals("Y")) out.print("checked");%>>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHOTH" value="N" <%if(!userInfo.getBTHOTH().equals("Y")) out.print("checked");%>>
            			</td>
  					</tr>			
  					<tr>
  				    	<td nowrap> 
              				<div align="right">Asientos Ptmos a Plazo :</div>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHILF" value="Y" <%if(userInfo.getBTHILF().equals("Y")) out.print("checked");%>>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHILF" value="N" <%if(!userInfo.getBTHILF().equals("Y")) out.print("checked");%>>
            			</td>
  					</tr>
  					<tr>
  				    	<td nowrap> 
              				<div align="right">Aprobar Garant&iacute;as :</div>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHCLP" value="Y" <%if(userInfo.getBTHCLP().equals("Y")) out.print("checked");%>>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHCLP" value="N" <%if(!userInfo.getBTHCLP().equals("Y")) out.print("checked");%>>
            			</td>
  					</tr>
  					<tr>
  				    	<td nowrap> 
              				<div align="right">Propuesta Cr. S/Aproba. :</div>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHFL1" value="Y" <%if(userInfo.getBTHFL1().equals("Y")) out.print("checked");%>>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHFL1" value="N" <%if(!userInfo.getBTHFL1().equals("Y")) out.print("checked");%>>
            			</td>
  					</tr>
  					<tr>
  				    	<td nowrap> 
              				<div align="right">Aprobar Excede Tasa Int. :</div>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHFL4" value="Y" <%if(userInfo.getBTHFL4().equals("Y")) out.print("checked");%>>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHFL4" value="N" <%if(!userInfo.getBTHFL4().equals("Y")) out.print("checked");%>>
            			</td>
  					</tr>
  					<tr>
  				    	<td nowrap> 
              				<div align="right">Autorizar Pagos Juducial :</div>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHFL9" value="Y" <%if(userInfo.getBTHFL9().equals("Y")) out.print("checked");%>>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHFL9" value="N" <%if(!userInfo.getBTHFL9().equals("Y")) out.print("checked");%>>
            			</td>
  					</tr>
  					<tr>
  				    	<td nowrap> 
              				<div align="right">Ignorar Control de C&aacute;mara :</div>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHFL8" value="Y" <%if(userInfo.getBTHFL8().equals("Y")) out.print("checked");%>>
            			</td>
            			<td nowrap align="center">
              				<input type="radio" name="BTHFL8" value="N" <%if(!userInfo.getBTHFL8().equals("Y")) out.print("checked");%>>
            			</td>
  					</tr>
    			</table>
   			</div>
   			</TD>
  			</TR>
    	  </TABLE>	
        </div>                         
     </td>
    </tr>
  </table>
  <p align="center"> 
        <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
 
  </form>
</body>
</html>
