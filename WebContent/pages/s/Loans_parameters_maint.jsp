<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>User Profile</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<%@ page import = "java.io.*,java.net.*,datapro.eibs.beans.*,java.math.*,com.datapro.eibs.parameters.loans.access.jdbc.bean.*,com.datapro.generic.beanutil.*" %>

<jsp:useBean id="lnParam" class="com.datapro.eibs.parameters.loans.access.jdbc.bean.DLSPARAMBean"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<SCRIPT Language="Javascript">

  function checkValues() {
       
       return true;
    }
  
</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }       
%>

</STYLE>
</head>
<body>
<h3 align="center"><% if (request.getParameter("OPT").equals("M")) out.print("Mantenimiento"); else out.print("Nueva");%> Tabla de Parametros<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="Loans_parameters_maint.jsp"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.parameters.loans.servlet.JSLoansParameters" onsubmit="return(checkValues())">
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="4">
  <INPUT TYPE=HIDDEN NAME="OPT" value="<%= request.getParameter("OPT") %>">
  <INPUT TYPE=HIDDEN NAME="SELTYP" value="<%= request.getParameter("SELTYP") %>">
  
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="20%" align=right> 
              <b>Banco :</b>
            </td>
            <td nowrap > 
               <input type="text" name="SELBNK" size="4" maxlength="2" value="<%= lnParam.getDLSKEY().substring(0,2)%>" readonly>
            </td>
            <td nowrap width="20%" align=right> 
              <b>Producto :</b>
            </td>
            <td nowrap > 
               <input type="text" name="SELPRD" size="5" maxlength="4" value="<%= lnParam.getDLSKEY().substring(4,8)%>" readonly>
               <input type="text" name="PRDDSC" size="25" maxlength="25" value="" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%" align=right> 
              <b>Tabla :</b>
            </td>
            <td nowrap colspan=3>  
               <input type="text" name="SELTBL" size="4" maxlength="2" value="<%= lnParam.getDLSKEY().substring(2,4)%>">
               <input type="text" name="DLSDSC" size="35" maxlength="35" value="<%= lnParam.getDLSDSC()%>">
            </td>
          </tr>
          <% if (request.getParameter("SELTYP").equals("2")) {%>
          <tr id="trdark"> 
            <td nowrap width="20%" align=right> 
              <b>Cliente :</b>
            </td>
            <td nowrap colspan=3>  
               <input type="text" name="SELCUN" size="4" maxlength="2" value="<%= lnParam.getDLSCUN()%>">
               <input type="text" name="DSCCUN" size="35" maxlength="35" value="">
            </td>
          </tr>
          <% } %>           
        </table>
      </td>
    </tr>
  </table>
  
  <H4>Datos B&aacute;sicos</H4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark">
            <td nowrap> 
            </td>
            <td nowrap> 
            </td>
            <td nowrap> 
            </td>
            <td nowrap colspan=4> 
            </td>
            <td nowrap colspan=2> 
              Factor	        
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap> 
              <div align="right">Periodo Base (360/65/66) :</div>
            </td>
            <td nowrap> 
              <INPUT type="text" name="DLSDAB" size="4" maxlength="3" value="<%= lnParam.getDLSDAB()%>" onkeypress="enterInteger()">	        
            </td>
            <td nowrap> 
              <div align="right">Tasa Baseo Puntos :</div>
            </td>
            <td nowrap colspan=4> 
              <INPUT type="text" name="DLSRTF" size="10" maxlength="10"	value="<%= lnParam.getDLSRTF()%>" onkeypress="enterInteger()">	        
            </td>
            <td nowrap colspan=2> 
              <INPUT type="text" name="DLSRFT" size="2" maxlength="1"	value="<%= lnParam.getDLSRFT()%>" onkeypress="enterInteger()">	        
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Tasa Minima Permitida :</div>
            </td>
            <td nowrap> 
 	          <INPUT type="text" name="DLSMIN" size="10" maxlength="10"	value="<%= lnParam.getDLSMIN()%>">
 	        </td>
 	        <td nowrap> 
              <div align="right">Tasa Maxima Permitida :</div>
            </td>
            <td nowrap colspan=4> 
 	          <INPUT type="text" name="DLSMAX" size="10" maxlength="10"	value="<%= lnParam.getDLSMAX()%>">
 	        </td>
 	        <td nowrap colspan=2> 
              <INPUT type="text" name="DLSMMT" size="2" maxlength="1"	value="<%= lnParam.getDLSMMT()%>" onkeypress="enterInteger()">	        
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap>
              <div align="right">Tipo de Interes :</div>
            </td>
            <td nowrap>
            	<INPUT type="text" name="DLSICT" size="2" maxlength="1"	value="<%= lnParam.getDLSICT()%>">
            </td>
            <td nowrap> 
              <div align="right">Cargo o Tasa x Mora :</div>
            </td>
            <td nowrap colspan=4> 
 	          <INPUT type="text" name="DLSIR1" size="10" maxlength="10"	value="<%= lnParam.getDLSIR1()%>">
 	        </td>
            <td nowrap colspan=2>        
              <INPUT type="text" name="DLSFL2" size="2" maxlength="1" value="<%= lnParam.getDLSFL2()%>">
			</td>
          </tr>
          <tr id="trclear">
            <td nowrap>
              <div align="right">Permite Pagos Parciales :</div>
            </td>
            <td nowrap>
              <INPUT type="text" name="DLSPPR" size="2" maxlength="1"	value="<%= lnParam.getDLSPPR()%>">
            </td>
            <td nowrap> 
              <div align="right">Cargo Minimo de Mora :</div>
            </td>
            <td nowrap colspan=6> 
 	          <INPUT type="text" name="DLSMPA" size="15" maxlength="15"	value="<%= lnParam.getDLSMPA()%>">
 	        </td>            
          </tr>
          <tr id="trdark">
            <td nowrap>
              <div align="right">Cntrl. Adelanto Capital :</div>
            </td>
			<td nowrap>
  				<input type="radio" name="DLSFL1" value="Y" <%if(lnParam.getDLSFL1().equals("Y")) out.print("checked");%>>Si
  				<input type="radio" name="DLSFL1" value="N" <%if(lnParam.getDLSFL1().equals("N")) out.print("checked");%>>No
			</td>
			<td nowrap>
              <div align="right">Gracia Mora :</div>
            </td>
			<td nowrap colspan=6>
  				<input type="text" name="DLSGR1" size="3" maxlength="2" value="<%=lnParam.getDLSGR1()%>">
  			</td>
		  </tr>
		  <tr id="trclear">
            <td nowrap>
            </td>
			<td nowrap>
  			</td>
			<td nowrap>
              <div align="right">Retiro Giro Cust. :</div>
            </td>
			<td nowrap colspan=6>
  				<input type="text" name="DLSCUP" size="3" maxlength="2" value="<%=lnParam.getDLSCUP()%>">
  			</td>
		  </tr>
		  <tr id="trdark">
			<td nowrap>
              <div align="right">Vencimiento Feriados :</div>
            </td>
			<td nowrap>
  				<input type="text" name="DLSFCO" size="2" maxlength="1" value="<%=lnParam.getDLSFCO()%>">(1/2)
  			</td>
  			<td nowrap>
              <div align="right">Dias de Paso a Vencido :</div>
            </td>
			<td nowrap colspan=6>
  				<input type="text" name="DLSPMD" size="4" maxlength="4" value="<%=lnParam.getDLSPMD()%>">
  			</td>
          </tr>
          <tr id="trclear">
			<td nowrap>
              <div align="right">Forma Cambio Contable :</div>
            </td>
			<td nowrap>
  				<input type="text" name="DLSTCG" size="2" maxlength="1" value="<%=lnParam.getDLSTCG()%>">(0/1-5/M)
  			</td>
  			<td nowrap>
              <div align="right">Paso Castigo sin Garant. :</div>
            </td>
			<td nowrap colspan=6>
  				<input type="text" name="DLSPC1" size="4" maxlength="4" value="<%=lnParam.getDLSPC1()%>">
  			</td>
          </tr>
          <tr id="trdark">
			<td nowrap>
              <div align="right">Moneda Cobro Cargos :</div>
            </td>
			<td nowrap>
  				<input type="text" name="DLSCCR" size="4" maxlength="3" value="<%=lnParam.getDLSCCR()%>">
  			</td>
  			<td nowrap>
              <div align="right">Paso Castigo con Garant. :</div>
            </td>
			<td nowrap colspan=6>
  				<input type="text" name="DLSPC2" size="4" maxlength="4" value="<%=lnParam.getDLSPC2()%>">
  			</td>
          </tr>
          <tr id="trclear">
			<td nowrap>
			  <% if (request.getParameter("SELTYP").equals("2")) {%>
              <div align="right">Fecha Validez :</div>
              <% } %>
            </td>
			<td nowrap>
			   <% if (request.getParameter("SELTYP").equals("2")) {%>
  				<input type="text" name="DLSMAM" size="3" maxlength="2" value="<%=lnParam.getDLSMAM()%>">
  				<input type="text" name="DLSMAD" size="3" maxlength="2" value="<%=lnParam.getDLSMAD()%>">
  				<input type="text" name="DLSMAY" size="3" maxlength="2" value="<%=lnParam.getDLSMAY()%>">
  			   <% } %>
  			</td>
  			<td nowrap>
              <div align="right">Orden Prior. en Pagos :</div>
            </td>
            <td nowrap>
  				<input type="text" name="DLSPP1" size="2" maxlength="1" value="<%=lnParam.getDLSPP1()%>" onkeypress="enterInteger()">
  			</td>
  			<td nowrap>
  				<input type="text" name="DLSPP2" size="2" maxlength="1" value="<%=lnParam.getDLSPP2()%>" onkeypress="enterInteger()">
  			</td>
  			<td nowrap>
  				<input type="text" name="DLSPP3" size="2" maxlength="1" value="<%=lnParam.getDLSPP3()%>" onkeypress="enterInteger()">
  			</td>
  			<td nowrap>
  				<input type="text" name="DLSPP4" size="2" maxlength="1" value="<%=lnParam.getDLSPP4()%>" onkeypress="enterInteger()">
  			</td>
  			<td nowrap>
  				<input type="text" name="DLSPP5" size="2" maxlength="1" value="<%=lnParam.getDLSPP5()%>" onkeypress="enterInteger()">
  			</td>
  			<td nowrap>
  				<input type="text" name="DLSPP6" size="2" maxlength="1" value="<%=lnParam.getDLSPP6()%>" onkeypress="enterInteger()">
  			</td>	   
          </tr>
          <tr id="trclear">
			<td nowrap>
            </td>
			<td nowrap>
  			</td>
  			<td nowrap>
            </td>
            <td nowrap>
  				<div align="center">P</div>
  			</td>
  			<td nowrap>
  				<div align="center">I</div>
  			</td>
  			<td nowrap>
  				<div align="center">M</div>
  			</td>
  			<td nowrap>
  				<div align="center">T</div>
  			</td>
  			<td nowrap>
  				<div align="center">C</div>
  			</td>
  			<td nowrap>
  				<div align="center">D</div>
  			</td>		
          </tr>
          <tr id="trdark">
			<td nowrap colspan=9>
			   <table cellspacing="0" cellpadding="2" width="100%" border="0">
		          <tr id="trdark">
		            <td nowrap> 
		            	<div align="right">Dias Cambio Contable :</div>
		            </td>
		            <td nowrap> 
		                (1)<input type="text" name="DLSPL1" size="4" maxlength="4" value="<%=lnParam.getDLSPL1()%>">
		            </td>
		            <td nowrap> 
		            	(2)<input type="text" name="DLSPL2" size="4" maxlength="4" value="<%=lnParam.getDLSPL2()%>">
		            </td>
		            <td nowrap> 
		            	(3)<input type="text" name="DLSPL3" size="4" maxlength="4" value="<%=lnParam.getDLSPL3()%>">
		            </td>
		            <td nowrap> 
		              	(4)<input type="text" name="DLSPL4" size="4" maxlength="4" value="<%=lnParam.getDLSPL4()%>">
		            </td>
		            <td nowrap> 
		              	(5)<input type="text" name="DLSPL5" size="4" maxlength="4" value="<%=lnParam.getDLSPL5()%>">
		            </td>
		          </tr>
		          <tr id="trdark">
		            <td nowrap> 
		            	<div align="right">% Capital Vencido :</div>
		            </td>
		            <td nowrap> 
		                <input type="text" name="DLSPR1" size="10" maxlength="10" value="<%=lnParam.getDLSPR1()%>">
		            </td>
		            <td nowrap> 
		            	<input type="text" name="DLSPR2" size="10" maxlength="10" value="<%=lnParam.getDLSPR2()%>">
		            </td>
		            <td nowrap> 
		            	<input type="text" name="DLSPR3" size="10" maxlength="10" value="<%=lnParam.getDLSPR3()%>">
		            </td>
		            <td nowrap> 
		              	<input type="text" name="DLSPR4" size="10" maxlength="10" value="<%=lnParam.getDLSPR4()%>">
		            </td>
		            <td nowrap> 
		              	<input type="text" name="DLSPR5" size="10" maxlength="10" value="<%=lnParam.getDLSPR5()%>">
		            </td>
		          </tr>
			   </table>
            </td>			
          </tr>
         </table>
       </td>
    </tr>
  </table>
  <H4>Comisiones o Impuestos</H4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <th nowrap >Descripcion</th>
            <th nowrap >Fac</th>
            <th nowrap >Monto</th>
            <th nowrap >Fre</th>
            <th nowrap >Minimo</th>
            <th nowrap >Maximo</th>
            <th nowrap >Mda</th>
            <th nowrap >Cta/Contable</th>
            <th nowrap >Iva</th>
          </tr>
          <%
  				   int amount = 9;
 				   String name;
 				   BeanParser bp = new BeanParser(lnParam);
  					for ( int i=1; i<=amount; i++ ) {
   					  name = i + "";
   			%> 
          <tr id="trclear"> 
            <td nowrap > 
              <div align="center" nowrap> 
                <input type="text" name="DLSDE<%= name %>" value="<%= bp.getString("DLSDE"+name)%>" size="30" maxlength="30">
              </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="DLSFA<%= name %>" size="2" maxlength="1" value="<%= bp.getString("DLSFA"+name)%>">
              </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="DLSAM<%= name %>" size="15" maxlength="15" value="<%= bp.getString("DLSAM"+name)%>">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="DLSAP<%= name %>" size="2" maxlength="1"  value="<%= bp.getString("DLSAP"+name)%>" onKeypress="enterDecimal()">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="DLSMN<%= name %>" size="15" maxlength="15" value="<%= bp.getString("DLSMN"+name)%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="DLSMX<%= name %>" size="15" maxlength="15"  value="<%= bp.getString("DLSMX"+name)%>">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="DLSGB<%= name %>" size="2" maxlength="1"  value="<%= bp.getString("DLSGB"+name)%>" onKeypress="enterDecimal()">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="DLSGL<%= name %>" size="18" maxlength="17"  value="<%= bp.getString("DLSGL"+name)%>" onKeypress="enterDecimal()">
              </div>
            </td>
            
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="DLSWH<%= name %>" size="2" maxlength="1"  value="<%= bp.getString("DLSWH"+name)%>">
              </div>
            </td>
          </tr>
          <%
    		}
    		%> 
    </table>
    </td>
    </tr>
  </table>
  <p align="center"> 
        <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
 
  </form>
</body>
</html>
