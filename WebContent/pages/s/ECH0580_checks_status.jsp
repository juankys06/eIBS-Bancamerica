<HTML>
<HEAD>
<TITLE>
Checks List
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "checks" class= "datapro.eibs.beans.JBList" scope="session" />
<jsp:useBean id="rtBasic" class="datapro.eibs.beans.ECH056503Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<%  String Action = (String) session.getAttribute("ChkAction");
    String CHKBOOKN = (String) session.getAttribute("CHECKBOOKN");
 %>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script>
  function ValidateF(){
	 if(document.forms[0].ACTION.value == "02"){
 		if( document.forms[0].E01CHMICK.value == "" || document.forms[0].E01CHMFCK.value == ""){
    	    alert("Favor ingresar Rango de cheques a modificar");
        	return false; 		
 		} 
	    var chki = parseInt(document.forms[0].E01CHMICK.value);
 		var chkf = parseInt(document.forms[0].E01CHMFCK.value);	
	    if(!confirm("Esta Seguro que desea cambiar el estado de estos cheques")){
	       return false;
	    } 
 	    if(chki< parseInt(document.forms[0].FCHK.value) || chkf> parseInt(document.forms[0].TCHK.value)){
    	    alert("Rango de cheques no pertenece a esta chequera");
        	return false;
     	}          
     	if(chki>chkf){
       		alert("Cheque inicial debe de ser menor a Cheque Final");
        	return false;
     	}
     }else{
	    if(!confirm("Esta Seguro que desea cambiar esta Chequera")){
	       return false;
	    }         
     }	
     return true;
  }
</script>
</head>

<body>

<h3 align="center">Cambio de Cheques y Chequera<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="checks_status.jsp,ECH0580"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH0580" onSubmit="return ValidateF()" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
<INPUT TYPE=HIDDEN NAME="ACTION" VALUE="<%= Action %>">

<%
	if ( checks.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
        <div align="center">
        		<font size="3"><b>
        				No hay resultados para la cuenta
        		</b></font>
        	</div>
      </TD></TR>
   		</TABLE>
<%   		
	}
	else {
%>
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E03ACMCUN" size="9" maxlength="9" value="<%= rtBasic.getE03ACMCUN().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" name="E03CUSNA1" size="45" readonly maxlength="45" value="<%= rtBasic.getE03CUSNA1().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E03ACMACC" size="12" readonly maxlength="12" value="<%= rtBasic.getE03ACMACC().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"> 
                <input type="text" name="E03ACMCCY" size="4" readonly maxlength="4" value="<%= rtBasic.getE03ACMCCY().trim()%>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"> 
                <input type="text" name="E03ACMPRO" size="4" readonly maxlength="4" value="<%= rtBasic.getE03ACMPRO().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark">
            <td  nowrap width="16%"><div align="right"><b> Numero Chequera :</b></div></td>
            <td colspan=5> <input type="text" name="E03ACMPRO" size="4" readonly maxlength="4" value="<%= CHKBOOKN %>">  </td>
          </tr>          
        </table>
      </td>
    </tr>
  </table>
<h4></h4> 
  
  <TABLE class="tableinfo" >
    <TR id ="trdark"> 
      <TH ALIGN=CENTER >Cheque</TH>
      <TH ALIGN=CENTER >Estatus</TH>
      <TH ALIGN=CENTER >Cheque</TH>
      <TH ALIGN=CENTER >Estatus</TH>
      <TH ALIGN=CENTER >Cheque</TH>
      <TH ALIGN=CENTER >Estatus</TH>
      <TH ALIGN=CENTER >Cheque</TH>
      <TH ALIGN=CENTER >Estatus</TH>
      <TH ALIGN=CENTER >Cheque</TH>
      <TH ALIGN=CENTER >Estatus</TH>
    </TR>
    <%
                checks.initRow();
                while (checks.getNextRow()) {
                    // if (checks.getFlag().equals("")) {
                    		out.println(checks.getRecord());
                    // }
                }
    %> 
  </TABLE>

<%
  }
%>
<br>
<% if(Action.trim().equals("02")){ %>
  <TABLE width="40%" align="center" class="tableinfo">
    <tr>  <td colspan="2" align="center" id="trdark" >Range</td>  </tr>
    <tr>
       <td class="trdark" align="right" width="50%">Cheque Inicial :</td>
       <td class="trlight"><input name="E01CHMICK" type="text" maxlength="5" ></td>
    </tr>
    <tr>
       <td class="trdark" align="right"> Cheque Final:</td>
       <td class="trlight"><input name="E01CHMFCK" type="text" maxlength="5" ></td>
    </tr>
    <tr>
       <td class="trdark" align="right"> Nuevo Status:</td>
       <td class="trlight"><SELECT name="E01CHMSTS">
       							<OPTION value="D">Disponible</OPTION>
       							<OPTION value="A">Anulado</OPTION>
       							<OPTION value="F">Certificado</OPTION>
       							<OPTION value="P">Pagado</OPTION>
       							<OPTION value="S">Suspendido</OPTION>
       							<OPTION value="C">Conformado</OPTION>
       							<OPTION value="X">Protestado</OPTION>
       							<OPTION value="T">Suspendido por Caja</OPTION>
       							<OPTION value="I">Inexistente</OPTION>
       						</select>	
       </td>
    </tr>    
    <tr><td colspan="2" align="center"><input id="EIBSBTN" type="submit" value="Cambiar"></td></td>    
  </table>

<% }else{ %>
  <TABLE width="40%" align="center" class="tableinfo">
    <tr>  <td colspan="2" align="center" id="trdark" >Esta seguro de cambiar esta chequera?</td>  </tr>    
    <tr><td colspan="2" align="center"><input id="EIBSBTN" type="submit" value="Cambiar"></td></td>    
  </table>
<% } %>  

<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }
%>  
</FORM>

</BODY>
</HTML>
