<html>
<head>
<title>Detalle del Pl&aacute;stico</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/dynlayer.js"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/pop_out.js"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/nav_aid.js"> </SCRIPT>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<jsp:useBean id="ecd0009" class="datapro.eibs.beans.ECD0009DSMessage"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body>
<P><BR>
</P>
<%!
  String ScreenMode="Q";  //Q - Query, E - Edit, N - New
  String action="2"; 
  String FieldEditable="";
  String KeyFieldEditable="";
 
%>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     error.setERRNUM("0");
     out.println("</SCRIPT>");
     }
     
     //Determina el modo de pantalla
     if (request.getAttribute("ScreenMode") != null && !request.getAttribute("ScreenMode").equals(""))
       ScreenMode = request.getAttribute("ScreenMode").toString();
     if (!ScreenMode.equals ("E") && !ScreenMode.equals ("N"))   
       ScreenMode="Q";
       
     //Determina el proceso a ejecutar segun el modo de pantalla
     
     if ( ScreenMode.equals("Q")){  //Consulta del detalle del plastico
       action = "2"; 
       FieldEditable="readonly";
       KeyFieldEditable="readonly";
     }
     else if (ScreenMode.equals("E")){ // Edicion del detalle del plastico
       action="5";
       FieldEditable="";
       KeyFieldEditable="readonly";
     }
     else{
       action="1";   // Creacion de un plastico.
       FieldEditable="";
       KeyFieldEditable="";
     }  
   
%>

<h3 align="center">Control de Plástico<BR>
Detalle<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="Card_Info.jsp, ECD0009"></h3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECD0009" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="<%=action%>">
  
  <table class="tableinfo">
    <tr > 
      <td> 
        <table cellspacing="0" cellpadding="2" width="100%"  class="tbhead"  align="center">
          <tr> 
            <td nowrap width="10%" align="right"> Código: </td>
            <td nowrap width="12%" align="left"> 
              <input type="text"  name="E01CDRTBL" size="3" maxlength="2" value="<%= ecd0009.getE01CDRTBL()%>" 
                <%=KeyFieldEditable%> >
            </td>
            <td nowrap width="6%" align="right">Descripci&oacute;n: </td>
            <td nowrap width="14%" align="left"> 
              <input type="text"  name="E01CDRDSC" size="31" maxlength="30" value="<%= ecd0009.getE01CDRDSC()%>" 
                <%=FieldEditable%> >
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  <h4>Informaci&oacute;n General</h4>

  <table class="tableinfo">
    <tr > 
      <td > 
        <table cellspacing="0" cellpadding="2" border="0" width="100%">
          <tr id="trdark"> 
            <td> 
              <div align="right">Tipo de Producto:</div>            </td> 
            <td> 
             <% if(ScreenMode.equals("Q")){ %>
                 <%= ecd0009.getE01CDRDOC().equals("D")?"D&eacute;bito":"Cr&eacute;dito"%> 
               <%} else { %> 
                <input type="radio"  name="E01CDRDOC" value="D" <%= ecd0009.getE01CDRDOC().equals("D")?"Checked":""%>  > D&eacute;bito
                <input type="radio"  name="E01CDRDOC" value="C" <%= ecd0009.getE01CDRDOC().equals("C")?"Checked":""%> > Cr&eacute;dito 
            <%}%>            </td>
            <td> 
              <div align="right">N&uacute;mero de BIN :</div>            </td>
            <td> 
              <input type="text"  name="E01CDRBIN" size="8" maxlength="7" value="<%= ecd0009.getE01CDRBIN()%>" <%=FieldEditable%>>            </td>
		  </tr>
          <tr id="trclear"> 
            <td> 
              <div align="right">C&oacute;digo del Emisor :</div>            </td>
            <td> 
              <input type="text"  name="E01CDREMI" size="6" maxlength="5" value="<%= ecd0009.getE01CDREMI()%>" <%=FieldEditable%>>            </td>
            <td> 
              <div align="right">Tipo de Tarjeta :</div>            </td>
            <td>
              <% if(ScreenMode.equals("Q")){ %>
                 <%= ecd0009.getE01CDRTYP().equals("A")?"Administrativa":"Venta al Cliente"%> 
               <%} else { %> 
                <input type="radio"  name="E01CDRTYP" value="A" <%= ecd0009.getE01CDRTYP().equals("A")?"Checked":""%>  > Administrativa<input type="radio"  name="E01CDRTYP" value="V" <%= ecd0009.getE01CDRTYP().equals("V")?"Checked":""%> > Venta al Cliente 
              <%}%>            </td>
	      </tr>
          <tr id="trdark"> 
            <td> 
              <div align="right"> C&oacute;digo de Activaci&oacute;n :</div>            </td>
            <td> 
              <% if(ScreenMode.equals("Q")){ %>
                 <%= ecd0009.getE01CDRACT().equals("1")?"Impl&iacute;cita":"Expl&iacute;cita"%> 
               <%} else { %> 
                <input type="radio"  name="E01CDRACT" value="1" <%= ecd0009.getE01CDRACT().equals("1")?"Checked":""%>  > Impl&iacute;cita
                <input type="radio"  name="E01CDRACT" value="2" <%= ecd0009.getE01CDRACT().equals("2")?"":"Checked"%> > Expl&iacute;cita 
            <%}%>            </td>
            <td> 
              <div align="right">Vigencia de Tarjeta :</div>            </td>
            <td> 
              <% if(ScreenMode.equals("Q")){ %>
                <%=ecd0009.getE01CDRTRM()+" "%> <%=ecd0009.getE01CDRTRC().equals("D")?"D&iacute;as":"Meses"%> 
             <%} else { %>
             
               <input type="text"  name="E01CDRTRM" size="5" maxlength="4" value="<%= ecd0009.getE01CDRTRM()%>" <%=FieldEditable%> >
               <select name="E01CDRTRC"  >
                <option value="D" <%if (ecd0009.getE01CDRTRC().equals("D")) { out.print("selected"); }%>>D&iacute;as</option>
                <option value="M" <%if (ecd0009.getE01CDRTRC().equals("M")) { out.print("selected"); }%>>Meses</option>
              </select>
              <%} %>            </td>
		  </tr>
          <tr id="trclear"> 
            <td> 
              <div align="right">Cantidad Máxima de Cuentas :</div>            </td>
            <td> 
              <input type="text"  name="E01CDRMXC" size="3" maxlength="2" value="<%= ecd0009.getE01CDRMXC().trim()%>" <%=FieldEditable%>>            </td>
            <td> 
              <div align="right">Genera CVC/CVC2 :</div>            </td>
            <td> 
             <% if(ScreenMode.equals("Q")){ %>
                 <%= ecd0009.getE01CDRCVC().equals("Y")?"Si":"No"%> 
               <%} else { %> 
                <input type="radio"  name="E01CDRCVC" value="Y" <%= ecd0009.getE01CDRCVC().equals("Y")?"Checked":""%>  > Si
                <input type="radio"  name="E01CDRCVC" value="N" <%= ecd0009.getE01CDRCVC().equals("Y")?"":"Checked"%> > No 
              <%}%>            </td>
		  </tr>
          <tr id="trdark">
            <td> 
              <div align="right">Permite Cliente Jur&iacute;dico :</div>            </td>
            <td>
             <% if(ScreenMode.equals("Q")){ %>
                 <%= ecd0009.getE01CDRJUR().equals("Y")?"Si":"No"%> 
               <%} else { %> 
                <input type="radio"  name="E01CDRJUR" value="Y" <%= ecd0009.getE01CDRJUR().equals("Y")?"Checked":""%>  > Si
                <input type="radio"  name="E01CDRJUR" value="N" <%= ecd0009.getE01CDRJUR().equals("Y")?"":"Checked"%> > No 
            <%}%>            </td>
            <td>
               <div align="right">Permite Firmas Conjuntas :</div>            </td>
            <td>
             <% if(ScreenMode.equals("Q")){ %>
                 <%= ecd0009.getE01CDRFIR().equals("Y")?"Si":"No"%> 
               <%} else { %> 
                <input type="radio"  name="E01CDRFIR" value="Y" <%= ecd0009.getE01CDRFIR().equals("Y")?"Checked":""%>  > Si
                <input type="radio"  name="E01CDRFIR" value="N" <%= ecd0009.getE01CDRFIR().equals("Y")?"":"Checked"%> > No 
              <%}%>            </td>
		  </tr>
          <tr id="trclear">
          	<td>
          		<div align="right">D&iacute;gitos de la Tarjeta :</div>          	</td>
          	<td> 
               <input type="text"  name="E01CDRDIG" size="4" maxlength="3" value="<%= ecd0009.getE01CDRDIG().trim()%>" <%=FieldEditable%>>            </td>
          	<td>
          		<div align="right">Monto a Devolver :</div>          	</td>
          	<td> 
               <input type="text"  name="E01CDRDEV" size="18" maxlength="17" value="<%= ecd0009.getE01CDRDEV().trim()%>" <%=FieldEditable%> onkeyPress="enterDecimal()">            </td>
          </tr>
          <tr id="trdark">
            <td> 
              <div align="right">Permite Offline :</div>            </td>
            <td>
             <% if(ScreenMode.equals("Q")){ %>
                 <%= ecd0009.getE01CDROFF().equals("Y")?"Si":"No"%> 
               <%} else { %> 
                <input type="radio"  name="E01CDROFF" value="Y" <%= ecd0009.getE01CDROFF().equals("Y")?"Checked":""%>  > Si
                <input type="radio"  name="E01CDROFF" value="N" <%= ecd0009.getE01CDROFF().equals("Y")?"":"Checked"%> > No 
            <%}%>            </td>
            <td>
               <div align="right">Valida PIN sin tarjeta :</div>            </td>
            <td>
             <% if(ScreenMode.equals("Q")){ %>
                 <%= ecd0009.getE01CDRPIN().equals("Y")?"Si":"No"%> 
               <%} else { %> 
                <input type="radio"  name="E01CDRPIN" value="Y" <%= ecd0009.getE01CDRPIN().equals("Y")?"Checked":""%>  > Si
                <input type="radio"  name="E01CDRPIN" value="N" <%= ecd0009.getE01CDRPIN().equals("Y")?"":"Checked"%> > No 
              <%}%>            </td>
		  </tr>
          <tr id="trclear">
            <td> 
              <div align="right">Valida CVC sin tarjeta :</div>            </td>
            <td>
             <% if(ScreenMode.equals("Q")){ %>
                 <%= ecd0009.getE01CDRCV1().equals("Y")?"Si":"No"%> 
               <%} else { %> 
                <input type="radio"  name="E01CDRCV1" value="Y" <%= ecd0009.getE01CDRCV1().equals("Y")?"Checked":""%>  > Si
                <input type="radio"  name="E01CDRCV1" value="N" <%= ecd0009.getE01CDRCV1().equals("Y")?"":"Checked"%> > No 
            <%}%>            </td>
            <td>
               <div align="right">Valida CVC2 sin tarjeta :</div>            </td>
            <td>
             <% if(ScreenMode.equals("Q")){ %>
                 <%= ecd0009.getE01CDRCV2().equals("Y")?"Si":"No"%> 
               <%} else { %> 
                <input type="radio"  name="E01CDRCV2" value="Y" <%= ecd0009.getE01CDRCV2().equals("Y")?"Checked":""%>  > Si
                <input type="radio"  name="E01CDRCV2" value="N" <%= ecd0009.getE01CDRCV2().equals("Y")?"":"Checked"%> > No 
              <%}%>            </td>
		  </tr>
        </table>
      </td>
    </tr>
  </table>
  
  <p>
  <% if (!"Q".equals(ScreenMode)){ %>
    <div align="center"> 
      <input id="EIBSBTN" type=button name="Submit" OnClick="submit()" value="Enviar">
    </div>
  <% } %>  
</form>
</body>
</html>
