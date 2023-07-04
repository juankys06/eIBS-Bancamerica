<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Detalle Proveedores</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="EBP0110Record" class="datapro.eibs.beans.EBP011001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
 
<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="JavaScript">

function goAction(op) {
	document.forms[0].SCREEN.value = op;
	if (op == 4) {
		if (!confirm("Esta seguro que desea borrar este registro??")) {
			return;
		}
	}
	document.forms[0].submit();
}

function goCustIns() {
    var pg = "";
    var ccy = "<%=currUser.getE01BCU()%>";
    var customer = document.getElementById("E01BPVCUN").value;
    if (customer == 0) {
		alert("Por favor ingrese un Número de Cliente para este Proveedor");
		return;
	}
 	pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=62&E11CUS=" + customer + 
 	"&E11CCY=" + ccy + "&E11TYP=1";
 	CenterWindow(pg,900,600,2);
}

</SCRIPT>  
 

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
	String read = "";
 	String disabled = "";
	if (!(userPO.getPurpose().equals("NEW") || userPO.getPurpose().equals("MAINTENANCE"))) 
		{ read = " readonly ";
		  disabled = " disabled"; }	
%>


<H3 align="center"> 
					<% if (userPO.getPurpose().equals("NEW")) { 
										out.println("  - Nuevo "); 
					   } 
					   else if (userPO.getPurpose().equals("MAINTENANCE")) {
					                               out.println(" - Mantenimiento "); 
					   }			  
					   else { out.println(" - Consulta ");
					   } 
				   %>Proveedor				
		<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="vendors.jsp, EBP0110">
</H3>

<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.bap.JSEBP0110" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="5">
  <INPUT TYPE=HIDDEN NAME="TYPE" VALUE="O">
  <INPUT TYPE=HIDDEN name="E01BPVCOD" value="<%= EBP0110Record.getE01BPVCOD().trim()%>"> 
<%--
 <table  class="tableinfo" width="100%">
--%>
 <tr bordercolor="#FFFFFF"> 
  <td nowrap> 
  	<table class="tableinfo" width="100% ">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" width="100%" border="0">
        <tr id="trdark"> 
            <td nowrap width="10%" align="right">Code : </td>
            <td nowrap width="20%" align="left"><%= EBP0110Record.getE01BPVCOD()%></td>
			<td nowrap width="10%" align="right">Número de Cliente : </td>
			<td nowrap width="10%" align="left"> 
              <INPUT type="text" name="E01BPVCUN" maxlength="9" size="10" <%= read %> value="<%= EBP0110Record.getE01BPVCUN().trim()%>" onkeypress="enterDecimal()">
			    <a href="javascript:GetCustomerDescId('E01BPVCUN','','')">
			    <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="bottom" border="0"></a></td>
            <td nowrap width="20%">Creation Date : <%= datapro.eibs.master.Util.formatDate(EBP0110Record.getE01BPVCMM(),EBP0110Record.getE01BPVCDD(),EBP0110Record.getE01BPVCYY())%></td>
			<td nowrap width="30%">Last Update Date : <%= datapro.eibs.master.Util.formatDate(EBP0110Record.getE01BPVMMM(),EBP0110Record.getE01BPVMDD(),EBP0110Record.getE01BPVMYY())%>
            </td>
		</tr>
        </table>
   	   </td>
   	 </tr>
	</table>
  <h4>Basic Information</h4>  
  <table class="tableinfo" width="100%">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
          	<td nowrap width="10%" align="right">Nombre Proveedor :</td>
			<td nowrap width="40%" align="left">
				<INPUT type="text" name="E01BPVNM1" maxlength="45" size="46" <%= read %> value="<%= EBP0110Record.getE01BPVNM1().trim()%>">
            	<B><IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Mandatory Field" align="bottom"></B>
			</td>
			 <td nowrap width="10%" align="right">Dirección Proveedor : </td>
          	<td nowrap width="40%" align="left">
                <INPUT type="text" name="E01BPVNM3" size="36" maxlength="35" <%= read %> value="<%= EBP0110Record.getE01BPVNM3().trim()%>">
				  <B><IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Mandatory Field" align="bottom"></B>
            </td>
          </tr>
          <tr id="trdark"> 
 			<td nowrap width="10%" align="right"></td>
			<td nowrap width="40%" align="left">
				<INPUT type="text" name="E01BPVNM2" maxlength="45" size="46" <%= read %> value="<%= EBP0110Record.getE01BPVNM2().trim()%>">
			</td>
			<td nowrap width="10%" align="right"></td>
          	<td nowrap width="40%" align="left">
                <INPUT type="text" name="E01BPVNM4" size="36" maxlength="35" <%= read %> value="<%= EBP0110Record.getE01BPVNM4().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="10%" align="right">Nombre Corto : </td>
            <td nowrap align="left" width="40%">
            	<INPUT type="text" name="E01BPVSNM" size="16" maxlength="15" <%= read %> value="<%= EBP0110Record.getE01BPVSNM().trim()%>">
				  <B><IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Mandatory Field" align="bottom"></B>
			<td nowrap width="10%" align="right"> </td>
            <td nowrap width="40%" align="left">
                <INPUT type="text" name="E01BPVNM5" size="36" maxlength="35" <%= read %> value="<%= EBP0110Record.getE01BPVNM5().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
        	<td nowrap width="10%" align="right">País : </td>
			<td nowrap width="40%" align="left">
              <INPUT type="text" name="E01BPVCNT" maxlength="4" size="5" <%= read %> value="<%= EBP0110Record.getE01BPVCNT().trim()%>">
              <INPUT type="text" name="E01BPVCDS" size="40" maxlength="35" value="<%= EBP0110Record.getE01BPVCDS().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E01BPVCNT','E01BPVCDS','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help"  border="0" ></a>
              <B><IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Mandatory Field" align="bottom"></B>            
            </td>    
            <td nowrap width="10%" align="right">Estado/Depto. : </td>
			<td nowrap width="40%" align="left">
              <INPUT type="text" name="E01BPVSTE" maxlength="4" size="5" <%= read %> value="<%= EBP0110Record.getE01BPVSTE().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="10%" align="right">Código Postal : </td>
			<td nowrap width="40%" align="left"> 
              <INPUT type="text" name="E01BPVZIP" maxlength="10" size="11" <%= read %> value="<%= EBP0110Record.getE01BPVZIP().trim()%>">
			</td>
			<td nowrap width="10%" align="right">Número Telefono : </td>
			<td nowrap width="40%" align="left"> 
              <INPUT type="text" name="E01BPVPHN" maxlength="20" size="21" <%= read %> value="<%= EBP0110Record.getE01BPVPHN().trim()%>">
			</td>
          </tr>
          <tr id="trclear"> 
        	<td nowrap width="10%" align="right">e-Mail : </td>
			<td nowrap width="40%" align="left">
              <INPUT type="text" name="E01BPVEMA" maxlength="60" size="61" <%= read %> value="<%= EBP0110Record.getE01BPVEMA().trim()%>">
            </td>    
            <td nowrap width="10%" align="right">Número Identificación Impuestos : </td>
			<td nowrap width="40%" align="left">
				<INPUT type="text" name="E01BPVIDF" maxlength="15" size="16" <%= read %> value="<%= EBP0110Record.getE01BPVIDF().trim()%>">
			    <B><IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Mandatory Field" align="bottom"></B>
            </td>
    	  </tr>         
          </table> 
      </td>
    </tr>
   </table> 
   <h4>Información par Pagos</h4>  
   <table  class="tableinfo" width="100%">
     <tr bordercolor="#FFFFFF"> 
   	    <td nowrap> 
          <table cellspacing="0" cellpadding="2" width="100%" border="0">
            <tr id="trdark"> 
            <td nowrap width="10%" align="right">Forma de Pago por Omisión : </td>
			<td nowrap width="40%" align="left"> 
              <SELECT name="E01BPVPVI" <%= disabled %>>
				<OPTION <%= EBP0110Record.getE01BPVPVI().trim().equals("A")?"Selected":"" %> value="A">ACH</OPTION>
				<OPTION <%= EBP0110Record.getE01BPVPVI().trim().equals("R")?"Selected":"" %> value="R">Cuenta Corriente</OPTION>
				<OPTION <%= EBP0110Record.getE01BPVPVI().trim().equals("G")?"Selected":"" %> value="G">Cuenta Contable</OPTION>
				<OPTION <%= EBP0110Record.getE01BPVPVI().trim().equals("C")?"Selected":"" %> value="C">Cheque Oficial/Gerencia</OPTION>
				<OPTION <%= EBP0110Record.getE01BPVPVI().trim().equals("S")?"Selected":"" %> value="S">Swift</OPTION>
				<OPTION <%= EBP0110Record.getE01BPVPVI().trim().equals("P")?"Selected":"" %> value="P">Caja Menor</OPTION>
			  </SELECT>
			  <B><IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Mandatory Field" align="bottom"></B>
			</td>
            <td nowrap width="10%" align="right">Metodo de Pago : </td>
            <td nowrap width="40%" align="left"> 
              <SELECT name="E01BPVPMT" <%= disabled %>>
				<OPTION <%= EBP0110Record.getE01BPVPMT().trim().equals("S")?"Selected":"" %> value="S">Un solo Pago</OPTION>
				<OPTION <%= EBP0110Record.getE01BPVPMT().trim().equals("M")?"Selected":"" %> value="M">Múltiples Facturas en un Pago</OPTION>
			  </SELECT>
			  <B><IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Mandatory Field" align="bottom"></B>
			 </td>
           </tr> 
		   <tr id="trclear"> 
             <td nowrap width="10%" align="left"><B>Información pago por ACH </B></td>
	         <td nowrap width="40%" align="left"> </td>
             <td nowrap width="10%" align="right"> </td> 
             <td nowrap width="40%" align="left"> 
			 </td>
		   </tr>	
           <tr id="trclear"> 
             <td nowrap width="10%" align="right">Nombre Beneficiario : </td>
	         <td nowrap width="40%" align="left">
	           <INPUT type="text" name="E01BPVNME" maxlength="45" size="46" <%= read %> value="<%= EBP0110Record.getE01BPVNME().trim()%>">
               </td>
             <td nowrap width="10%" align="right">Dirección : </td> 
             <td nowrap width="40%" align="left"> 
			   <INPUT type="text" name="E01BPVADD" maxlength="45" size="46" <%= read %> value="<%= EBP0110Record.getE01BPVADD().trim()%>">
			 </td>
		   </tr>	
		   <tr id="trclear"> 
             <td nowrap width="10%" align="right">Ciudad, Estado, Cód. Postal : </td>
	         <td nowrap width="40%" align="left">
	           <INPUT type="text" name="E01BPVCSZ" maxlength="45" size="46" <%= read %> value="<%= EBP0110Record.getE01BPVCSZ().trim()%>">
             </td>
             <td nowrap width="10%" align="right">Número Ruta : </td> 
             <td nowrap width="40%" align="left"> 
			   <INPUT type="text" name="E01BPVROU" maxlength="9" size="10" <%= read %> value="<%= EBP0110Record.getE01BPVROU().trim()%>" onkeypress="enterInteger()">
		     </td>
		   </tr>	
           <tr id="trclear"> 
             <td nowrap width="10%" align="right">Tipo </td>
	         <td nowrap width="40%" align="left">
	           <SELECT name="E01BPVACT" <%= disabled %>>
			     <OPTION <%= EBP0110Record.getE01BPVACT().trim().equals("C")?"Selected":"" %> value="C">Corriente</OPTION>
			     <OPTION <%= EBP0110Record.getE01BPVACT().trim().equals("S")?"Selected":"" %> value="S">Ahorros</OPTION>
			   </SELECT>
             </td>
             <td nowrap width="10%" align="right">Cuenta Beneficiario  : </td> 
             <td nowrap width="40%" align="left"> 
               <INPUT type="text" name="E01BPVACC1" maxlength="17" size="18" <%= read %> value="<%= EBP0110Record.getE01BPVACC1().trim()%>">
             </td>
		   </tr>	
		   <tr id="trdark"> 
             <td nowrap width="10%" align="left"><B>Información para Pago en Cuenta Corriente </B> </td>
             <td nowrap width="10%" align="left"></td>             
	         <td nowrap width="40%" align="left"></td>
             <td nowrap width="10%" align="right"> </td> 
             <td nowrap width="40%" align="left"> 
			 </td>
		   </tr>	
           <tr id="trdark"> 
             <td nowrap width="10%" align="right">Número de Cuenta : </td>
	         <td nowrap width="40%" align="left">
	           <INPUT type="text" name="E01BPVACC2" maxlength="12" size="15" <%= read %> value="<%= EBP0110Record.getE01BPVACC2().trim()%>">
               <A href="javascript:GetAccount('E01BPVACC2','','','')">
                	<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Account Help" border="0" align="top"></A>
             </td>
             <td nowrap width="10%" align="right"></td> 
             <td nowrap width="40%" align="left"></td>
		   </tr>		
		   <tr id="trclear"> 
             <td nowrap width="10%" align="left"><B>Información para pago en Cuenta Contable </B></td>
             <td nowrap width="10%" align="left"></td>             
	         <td nowrap width="40%" align="left"></td>
             <td nowrap width="10%" align="right"></td> 
             <td nowrap width="40%" align="left"> 
			 </td>
		   </tr>	
           <tr id="trclear"> 
             <td nowrap width="10%" align="right">Número de Cuenta : </td>
	         <td nowrap width="40%" align="left">
             	<INPUT type="text" name="E01BPVACC3" maxlength="16" size="18" <%= read %> value="<%= EBP0110Record.getE01BPVACC3().trim()%>">
               <A href="javascript:GetLedger('E01BPVACC3','','','')"> 
               		<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="GL Account Help" border="0" align="top"></A>
             </td>
             <td nowrap width="10%" align="right"></td> 
             <td nowrap width="40%" align="left"> 
             </td>
		   </tr>		

		   <tr id="trdark"> 
             <td nowrap width="10%" align="left"><B>Información para pago por SWIFT </B></td>
	         <td nowrap width="40%" align="left"> </td>
             <td nowrap width="10%" align="right"> </td> 
             <td nowrap width="40%" align="left"> </td>
		   </tr>	
           <tr id="trdark"> 
             <td nowrap width="10%" align="right">Instrucciones : </td>
	         <td nowrap width="40%" align="left">
             	<A href="javascript:goCustIns()">
             		<IMG src="<%=request.getContextPath()%>/images/1bori.gif" alt="Instrucciones de Pago del Cliente" align="middle" border="0">
				</A>
			</td>
             <td nowrap width="10%" align="right"> </td> 
             <td nowrap width="40%" align="left"> </td>
		    </tr>	

      </table>
	 </td>
   </tr>
   </table>  
   <h4>Información de Impuestos</h4>
   <table  class="tableinfo" width="100%">
   	 <tr bordercolor="#FFFFFF"> 
   	 <td nowrap> 
      <table cellspacing="0" cellpadding="2" width="100%" border="0">
        <tr id="trdark"> 
          <td nowrap width="10%" align="right">Excepción de Impuestos : </td>
	      <td nowrap width="40%" align="left"> 
            <input type="radio" name="E01BPVFTX" value="Y" <% if (EBP0110Record.getE01BPVFTX().equals("Y")) out.print("checked"); %>>
             Si 
            <input type="radio" name="E01BPVFTX" value="N" <% if (EBP0110Record.getE01BPVFTX().equals("N")) out.print("checked"); %>>
             No          
            <B><IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Mandatory Field" align="bottom"></B></td>
          <td nowrap width="10%" align="right"></td> 
          <td nowrap width="40%" align="left"></td>
        </tr>
        <tr id="trclear"> 
          <td nowrap width="10%" align="right">Forma : </td>
          <td nowrap width="40%" align="left" >
			<SELECT name="E01BPVWTH" <%= disabled %>>
	          <OPTION <%= EBP0110Record.getE01BPVWTH().trim().equals("N")?"Selected":"" %> value="N">Ningunta</OPTION>		
			  <OPTION <%= EBP0110Record.getE01BPVWTH().trim().equals("B")?"Selected":"" %> value="B">Ambos</OPTION>
			  <OPTION <%= EBP0110Record.getE01BPVWTH().trim().equals("F")?"Selected":"" %> value="F">Forma 1099</OPTION>
			  <OPTION <%= EBP0110Record.getE01BPVWTH().trim().equals("W")?"Selected":"" %> value="W">Retención</OPTION>
			</SELECT>
		  </td>	
          <td nowrap width="10%" align="right"></td> 
          <td nowrap width="40%" align="left"></td>
        </tr>
      </table>
     </td>
     </tr>
   </table>
  <h5></h5>
   <table  class="tableinfo" width="100%">
   	 <tr bordercolor="#FFFFFF"> 
   	 <td nowrap> 
      <table cellspacing="0" cellpadding="2" width="100%" border="0">
        <tr id="trdark"> 
          <td nowrap width="10%" align="right">Estado : </td>
	      <td nowrap width="40%" align="left">
	        <SELECT name="E01BPVSTA" <%= disabled %>>
			  <OPTION <%= EBP0110Record.getE01BPVSTA().trim().equals("A")?"Selected":"" %> value="A">Activo</OPTION>
			  <OPTION <%= EBP0110Record.getE01BPVSTA().trim().equals("I")?"Selected":"" %> value="I">Inactivo</OPTION>
			</SELECT>
			<B><IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Mandatory Field" align="bottom"></B>
          </td>
            <td nowrap width="10%" align="right"> </td> 
            <td nowrap width="40%" align="left"> 
			</td>
		</tr>
       </table>
	 </td>
	 </tr>
   </table>
  </td>
 </tr>
<%--
 </table>
--%>   
    <h5></h5>
    <table width="100%">		
  	<tr>
  		<td width="33%">
  		  <div align="center"> 
     		<input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="goAction(5);" <%= disabled %>>
     	  </div>	
  		</td>
  		<td width="33%"> 
  		  <div align="center"> 
     		<input id="EIBSBTN" type=button name="Delete" value="Borrar" onClick="goAction(4);" <%= disabled %>>
     	  </div>	
  		</td>
  		<td width="34%">
  		  <div align="center"> 
     		<input id="EIBSBTN" type=submit name="Exit" value="Salir" 
			<% if (userPO.getPurpose().equals("NEW") || userPO.getPurpose().equals("MAINTENANCE"))  { %>
			    onClick="goAction(1);" 
			<% } else { %>
				onClick="goAction(6);" 
			<% } %>>
     	  </div>	
  		</td>

  	</tr>	
  </table>	

  </form>
</body>
</html>
