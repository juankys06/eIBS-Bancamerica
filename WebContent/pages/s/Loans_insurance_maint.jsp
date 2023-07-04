<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>User Profile</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<%@ page import = "datapro.eibs.beans.*,java.math.*,com.datapro.eibs.parameters.loans.access.jdbc.bean.*,com.datapro.generic.beanutil.*" %>

<jsp:useBean id="insTbl" class="com.datapro.eibs.parameters.loans.access.jdbc.bean.CNTRLINS"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "CURYEAR" class= "String"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<SCRIPT Language="Javascript">

  
  
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
<h3 align="center"><% if (request.getParameter("OPT").equals("M")) out.print("Mantenimiento"); else out.print("Nueva");%> Tabla de Seguros<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="Loans_insurance_maint.jsp"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.parameters.loans.servlet.JSInsurance" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="4">
  <INPUT TYPE=HIDDEN NAME="OPT" value="<%= request.getParameter("OPT") %>">
  <INPUT TYPE=HIDDEN NAME="SELTYP" value="<%= request.getParameter("SELTYP") %>">
  
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          
          <tr id="trdark"> 
            <td nowrap width="20%" align=right> 
              <b>Tabla :</b>
            </td>
            <td nowrap >  
               <input type="text" name="INSTYP" size="4" maxlength="2" value="<%= insTbl.getINSTYP()%>" readonly>
            </td>
            <td nowrap width="20%" align=right> 
              <b>Descripcion :</b>
            </td>
            <td nowrap >
              <input type="text" name="INSDSC" size="35" maxlength="35" value="<%= insTbl.getINSDSC()%>">
            </td>
          </tr>        
        </table>
      </td>
    </tr>
  </table>
  
  <H4>Coberturas</H4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark">
            <th nowrap align=center width=2%>
               No. 
            </th>
            <th nowrap align=center>
               Descripcion
            </th>
            <th nowrap align=center>
               Limites 
            </th>
            <th nowrap align=center> 
               Primas
            </th>
          </tr>
          <%
  				   int amount = 4;
 				   String name;
 				   BeanParser bp = new BeanParser(insTbl);
  					for ( int i=1; i<=amount; i++ ) {
   					  name = "INSC" + i;
   		  %> 		
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right"><%= i %> :</div>
            </td>
            <td nowrap > 
              <input type="text" name="<%= name %>D" value="<%= bp.getString(name+"D")%>" size="30" maxlength="30">
            </td>
            <td nowrap > 
              <input type="text" name="<%= name %>L" size="15" maxlength="15" value="<%= bp.getString(name+"L")%>">
            </td>
            <td nowrap > 
              <input type="text" name="<%= name %>P" size="15" maxlength="15" value="<%= bp.getString(name+"P")%>">
            </td>        
          </tr>
          <%
    		}
    		%> 
    </table>
    </td>
    </tr>
  </table>
 <H4>Porcientos y Cobros</H4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">   
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Porcentaje Descuento :</div>
            </td>
            <td nowrap> 
 	          <INPUT type="text" name="INSPDE" size="6" maxlength="6" value="<%= insTbl.getINSPDE()%>">
 	        </td>
 	        <td nowrap> 
              <div align="right">Porcentaje Depreciacion :</div>
            </td>
            <td nowrap> 
 	          <INPUT type="text" name="INSDEP" size="6" maxlength="6" value="<%= insTbl.getINSDEP()%>">
 	        </td> 	        
          </tr>
          <tr id="trclear">
            <td nowrap>
              <div align="right">Cobro Minimo :</div>
            </td>
            <td nowrap>
            	<INPUT type="text" name="INSMIN" size="15" maxlength="15" value="<%= insTbl.getINSMIN()%>">
 	        </td>
            <td nowrap> 
              <div align="right">Codigo Cobro Minimo :</div>
            </td>
            <td nowrap> 
 	          <INPUT type="text" name="INSMIC" size="4" maxlength="4"	value="<%= insTbl.getINSMIC()%>">
 	        </td>
          </tr>          
         </table>
       </td>
    </tr>
  </table>
  <H4>Tarifas Aplicadas a los Riesgos</H4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <th nowrap width=5%>Año</th>
            <th nowrap >Comprensivo</th>
            <th nowrap >Colision</th>
            <th nowrap >Incendio</th>
            <th nowrap >Robo</th>
          </tr>
          <%
  				amount = 8;
 				String curYear = CURYEAR;
 				int year = Integer.parseInt(curYear);
  				for ( int i=1; i<=amount; i++ ) {
   					  name = i + "";
   					  if (i>1) year = year - 1;
   		  %> 		
          <tr id="trclear">
            <td nowrap > 
              <div align="right" nowrap>
                <%= year %> 
              </div>
            </td> 
            <td nowrap > 
              <div align="center" nowrap> 
                <input type="text" name="INSP<%= name %>1" size="15" maxlength="15" value="<%= bp.getString("INSP"+name+"1")%>" onKeypress="enterDecimal()">
              </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="INSP<%= name %>2" size="15" maxlength="15" value="<%= bp.getString("INSP"+name+"2")%>" onKeypress="enterDecimal()">
              </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="INSP<%= name %>3" size="15" maxlength="15" value="<%= bp.getString("INSP"+name+"3")%>" onKeypress="enterDecimal()">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="INSP<%= name %>4" size="15" maxlength="15"  value="<%= bp.getString("INSP"+name+"4")%>" onKeypress="enterDecimal()">
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
