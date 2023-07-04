<html>
<head>
<title>Transacciones de Prestamos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id= "lnTransac" class= "datapro.eibs.beans.EDL015203Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<SCRIPT LANGUAGE="JavaScript">

   function UpdateTotal(){
   
   var total;  
    try{
     total= parseFloat(formatFloat(document.forms[0].AFTERPRI.value));}
    catch(e){
     total=0.00;
    }
    <% if(lnTransac.getH03FLGWK3().equals("R")){%>
    try{
     total=total+parseFloat(formatFloat(document.forms[0].AFTERREA.value));}
    catch(e){
    }
    <% } %>
    try{
     total=total+parseFloat(formatFloat(document.forms[0].AFTERINT.value));}
    catch(e){
    }
    try{
     total=total+parseFloat(formatFloat(document.forms[0].AFTERMOR.value));}
    catch(e){
    }
    
   document.forms[0].AFTERTOT.value=formatCCY(""+total);
   
  }
  
   
  function UpdateField(bfield,trfield,afield,sfield,rep){
   var trval;
   var bfval;
   var afval=0.00;
    try{
     trval= parseFloat(formatFloat(document.forms[0][trfield].value));}
    catch(e){
     trval=0.00;
    }
    try{
     if (rep) bfval=parseFloat(formatFloat(document.forms[0][afield].value));
     else bfval=parseFloat(formatFloat(document.forms[0][bfield].value));}
    catch(e){
     bfval=0.00;
    }
    if (document.forms[0][sfield].value=="0") afval=bfval+trval;
    else if (document.forms[0][sfield].value=="5") afval=bfval-trval;
    else afval=bfval;
    document.forms[0][afield].value = formatCCY(""+afval);
  }
  
 
</SCRIPT>
<SCRIPT Language="Javascript">

   builtNewMenu(ln_t_a_opt);
   initMenu();


</SCRIPT>
</head>


<body nowrap>



<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<h3 align="center">Transacciones de Pr&eacute;stamos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_ap_transac.jsp, EDL0140"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0150">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="6">
   <INPUT TYPE=HIDDEN NAME="E03DEABNK" VALUE="<%= lnTransac.getE03DEABNK().trim()%>">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left">
                <input type="text" name="E03DEACUN" size="9" maxlength="9" value="<%= lnTransac.getE03DEACUN().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text" name="E03CUSNA1" size="45" maxlength="45" value="<%= lnTransac.getE03CUSNA1().trim()%>" readonly>
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text" name="E03DEAACC" size="12" maxlength="12" value="<%= lnTransac.getE03DEAACC().trim()%>" readonly>
                </font></font></font> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E03DEACCY" size="3" maxlength="3" value="<%= lnTransac.getE03DEACCY().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E03DEAPRO" size="4" maxlength="4" value="<%= lnTransac.getE03DEAPRO().trim()%>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n Financiera</h4>
      <TABLE class="tableinfo">
     <TBODY>
        <TR> 
      <TD nowrap> 
        <TABLE cellspacing="2" cellpadding="2" width="100%" border="0">
          <TBODY>
                    <TR> 
            <TD nowrap width="20%" id="trdark"> 
              <DIV align="center"></DIV>
            </TD>
            <TD nowrap width="40%" id="trdark"> 
              <DIV align="center"><B>Antes </B></DIV>
            </TD>
            <TD nowrap width="40%" id="trdark"> 
              <DIV align="center"><B>Despues </B></DIV>
            </TD>
          </TR>
          <TR> 
            <TD nowrap id="trdark"> 
              <DIV align="right">Saldo de Principal :</DIV>
            </TD>
            <TD nowrap align="center">
             <INPUT type="text" name="E03DEAPRI" id="txtright" size="15" maxlength="13" value="<%= Util.formatCCY(lnTransac.getE03DEAPRI())%>" readonly></TD>
            <TD nowrap align="center"> 
              <INPUT type="text" id="txtright" readonly name="AFTERPRI" size="15" maxlength="13" value="" onkeypress="enterDecimal()">
            </TD>
          </TR>
          <%if(lnTransac.getH03FLGWK3().equals("R")){%>
          <tr > 
            <td id="trdark"> 
              <div align="right">Saldo de Reajuste :</div>
            </td>
            <td nowrap align="center"> 
              <input type="text" id="txtright" name="E03DEAREA" size="15" maxlength="15" value="<%= Util.formatCCY(lnTransac.getE03DEAREA().trim())%>" readonly>
            </td>
            <TD nowrap align="center"> 
              <INPUT type="text" id="txtright" readonly name="AFTERREA" size="15" maxlength="13" value="" onkeypress="enterDecimal()">
            </TD>
          </tr> 
           <%}%>
          <TR> 
            <TD nowrap id="trdark"> 
              <DIV align="right">Saldo de Interés :</DIV>
            </TD>
            <TD nowrap align="center">
            <INPUT type="text" name="E03DEAINT" id="txtright" size="15" maxlength="13" value="<%= Util.formatCCY(lnTransac.getE03DEAINT())%>" readonly></TD>
            <TD nowrap align="center"> 
              <INPUT id="txtright" type="text" readonly name="AFTERINT" size="15" maxlength="13" value="" onkeypress="enterDecimal()">
            </TD>
          </TR>
          <TR> 
            <TD nowrap id="trdark"> 
              <DIV align="right">Cargo por Mora :</DIV>
            </TD>
            <TD nowrap align="center"> 
              <INPUT id="txtright" type="text" readonly name="E03DEAMOR" size="15" maxlength="13" value="<%= Util.formatCCY(lnTransac.getE03DEAMOR())%>" readonly">
            </TD>
            <TD nowrap align="center"> 
              <INPUT id="txtright" type="text" readonly name="AFTERMOR" size="15" maxlength="13" value="" onkeypress="enterDecimal()">
            </TD>
          </TR>
          
          <TR> 
            <TD nowrap id="trdark"> 
              <DIV align="right">Total :</DIV>
            </TD>
            <TD nowrap align="center"><INPUT type="text" name="E03DEATOT" id="txtright" size="15" maxlength="13" value="<%= Util.formatCCY(lnTransac.getE03DEATOT())%>" readonly></TD>
            <TD nowrap align="center"> 
              <INPUT id="txtright" type="text" readonly name="AFTERTOT" size="15" maxlength="13" value="" onkeypress="enterDecimal()">
            </TD>
          </TR>
          <TR>
          	<TD nowrap colspan="3"> 
              <DIV align="center">Fecha de Ultimo C&aacute;lculo : 
              <INPUT type="text" name="E03DEALC1" size="2" maxlength="2" value="<%= lnTransac.getE03DEALC1().trim()%>" readonly>
              <INPUT type="text" name="E03DEALC2" size="2" maxlength="2" value="<%= lnTransac.getE03DEALC2().trim()%>" readonly>
              <INPUT type="text" name="E03DEALC3" size="2" maxlength="2" value="<%= lnTransac.getE03DEALC3().trim()%>" readonly> </DIV>
            </TD>
           </TR>
        	</TBODY>
            </TABLE>
      	</TD>
    </TR>
  </TBODY>
</TABLE>

  <h4>Datos de la Transacci&oacute;n</h4>
  <table class="tableinfo">
    <tr > 
      <td> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td width="40%"> 
              <div align="right">Principal :</div>
            </td>
            <td width="60%"> 
              <input type="text" name="E03TRNPRI" size="15" maxlength="15" value="<%= lnTransac.getE03TRNPRI().trim()%>" readonly>
              <input type="hidden" name="E03TRNPRF" value="<%= lnTransac.getE03TRNPRF()%>">
              <input type="radio" disabled name="CE03TRNPFL" value="0" disabled 
              onClick="document.forms[0].E03TRNPRF.value='0'" 
			  <%if(lnTransac.getE03TRNPRF().equals("0")) out.print("checked");%>>
              Aumento 
              <input type="radio" disabled name="CE03TRNPFL" value="5" disabled 
              onClick="document.forms[0].E03TRNPRF.value='5'" 
			  <%if(lnTransac.getE03TRNPRF().equals("5")) out.print("checked");%>>
              Disminuci&oacute;n</td>
          </tr>
          <tr id="trclear"> 
            <td width="40%"> 
              <div align="right">Reajuste :</div>
            </td>
            <td width="60%"> 
              <input type="text" name="E03TRNREA" size="15" maxlength="15" value="<%= lnTransac.getE03TRNREA().trim()%>" readonly>
              <input type="hidden" name="E03TRNREF" value="<%= lnTransac.getE03TRNREF()%>">
              <input type="radio" disabled name="CE03TRNREF" value="0" disabled 
			   onClick="document.forms[0].E03TRNREF.value='0'"
			   <%if(lnTransac.getE03TRNREF().equals("0")) out.print("checked");%>>
              Aumento 
              <input type="radio" disabled name="CE03TRNREF" value="5" disabled 
			   onClick="document.forms[0].E03TRNREF.value='5'"
			   <%if(lnTransac.getE03TRNREF().equals("5")) out.print("checked");%>>
              Disminuci&oacute;n </td>
          </tr>
          <tr id="trdark"> 
            <td width="40%" height="27"> 
              <div align="right">Intereses :</div>
            </td>
            <td width="60%" height="27"> 
              <input type="text" name="E03TRNINT" size="15" maxlength="15" value="<%= lnTransac.getE03TRNINT().trim()%>" readonly>
              <input type="hidden" name="E03TRNINF" value="<%= lnTransac.getE03TRNINF()%>">
              <input type="radio" disabled name="CE03TRNINF" value="0" disabled 
			   onClick="document.forms[0].E03TRNINF.value='0'"
			   <%if(lnTransac.getE03TRNINF().equals("0")) out.print("checked");%>>
              Aumento 
              <input type="radio" disabled name="CE03TRNINF" value="5" disabled 
			   onClick="document.forms[0].E03TRNINF.value='5'"
			   <%if(lnTransac.getE03TRNINF().equals("5")) out.print("checked");%>>
              Disminuci&oacute;n </td>
          </tr>
          <tr id="trclear"> 
            <td width="40%"> 
              <div align="right">Mora :</div>
            </td>
            <td width="60%"> 
              <input type="text" name="E03TRNMOR" size="15" maxlength="15" value="<%= lnTransac.getE03TRNMOR().trim()%>" readonly>
              <input type="hidden" name="E03TRNMOF" value="<%= lnTransac.getE03TRNMOF()%>">
              <input type="radio" disabled name="CE03TRNMOF" value="0" disabled 
              onClick="document.forms[0].E03TRNMOF.value='0'"  
			  <%if(lnTransac.getE03TRNMOF().equals("0")) out.print("checked");%>>
              Aumento 
              <input type="radio" disabled name="CE03TRNMOF" value="5" disabled 
              onClick="document.forms[0].E03TRNMOF.value='5'"  
			  <%if(lnTransac.getE03TRNMOF().equals("5")) out.print("checked");%>>
              Disminuci&oacute;n </td>
          </tr>
          <tr id="trdark"> 
            <td width="40%"> 
              <div align="right">Ajuste de Intereses :</div>
            </td>
            <td width="60%"> 
              <input type="text" name="E03TRNAIN" size="15" maxlength="15" value="<%= lnTransac.getE03TRNAIN().trim()%>" readonly>
              <input type="hidden" name="E03TRNAIF" value="<%= lnTransac.getE03TRNAIF()%>">
              <input type="radio" disabled name="CE03TRNAIF" value="0" disabled 
              onClick="document.forms[0].E03TRNAIF.value='0'" 
			  <%if(lnTransac.getE03TRNAIF().equals("0")) out.print("checked");%>>
              Aumento 
              <input type="radio" disabled name="CE03TRNAIF" value="5" disabled 
              onClick="document.forms[0].E03TRNAIF.value='5'" 
			  <%if(lnTransac.getE03TRNAIF().equals("5")) out.print("checked");%>>
              Disminuci&oacute;n </td>
          </tr>
          <tr id="trclear"> 
            <td width="40%"> 
              <div align="right">Ajuste Mora :</div>
            </td>
            <td width="60%"> 
              <input type="text" name="E03TRNAMO" size="15" maxlength="15" value="<%= lnTransac.getE03TRNAMO().trim()%>" readonly>
              <input type="hidden" name="E03TRNAMF" value="<%= lnTransac.getE03TRNAMF()%>">
              <input type="radio" disabled name="CE03TRNAMF" value="0" disabled 
              onClick="document.forms[0].E03TRNAMF.value='0'" 
			  <%if(lnTransac.getE03TRNAMF().equals("0")) out.print("checked");%>>
              Aumento 
              <input type="radio" disabled name="CE03TRNAMF" value="5" disabled 
              onClick="document.forms[0].E03TRNAMF.value='5'" 
			  <%if(lnTransac.getE03TRNAMF().equals("5")) out.print("checked");%>>
              Disminuci&oacute;n </td>
          </tr>
          <tr id="trclear"> 
            <td width="40%"> 
              <div align="right">Total :</div>
            </td>
            <td width="60%"> 
              <input type="text" name="E03TRNTOT" size="15" maxlength="15" value="<%= lnTransac.getE03TRNTOT().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

  <h4>Otros Cargos</h4>
  <table class="tableinfo">
    <tr > 
      <td> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">

          <%
           String name="";
           boolean par=true;
           for (int i=1;i<=9;i++) {
            name=""+i;
            if (par == true) par=false; else par=true;
		    if(!lnTransac.getField("E03DLSNAR"+name).getString().trim().equals("")){
		 %> 
			<tr id="<% if(lnTransac.getH03FLGWK3().equals("R") || par==false) out.print("trdark"); else out.print("trclear"); %>">
			 <td width="40%"> 
              <div align="right"><%= lnTransac.getField("E03DLSNAR"+name).getString().trim()%> : </div>
             </td>
             <td width="60%"> 
              <input type="text" size="15" maxlength="15" name="E03DLSAMT<%= name%>" value="<%= lnTransac.getField("E03DLSAMT"+name).getString().trim()%>" readonly>
              <input type="hidden" name="E03DLSPFL<%= name%>" value="<%= lnTransac.getField("E03DLSPFL"+name).getString().trim()%>">
              <input type="radio" name="CE03DLSPFL<%= name%>" value="0" disabled 
			   onClick="document.forms[0].E03DLSPFL<%= name%>.value='0';Recalculate();"
			   <%if(lnTransac.getField("E03DLSPFL"+name).getString().trim().equals("0")) out.print("checked");%>> Reversar <input type="radio" name="CE03DLSPFL<%= name%>" value="5" disabled 
			   onClick="document.forms[0].E03DLSPFL<%= name%>.value='5';Recalculate();"
			   <%if(lnTransac.getField("E03DLSPFL"+name).getString().trim().equals("5")) out.print("checked");%>> Pago 
             </td>
            </tr>
          <%
		   	}
		   }
		  %> 

          <tr id="<% if(lnTransac.getH03FLGWK3().equals("R")) out.print("trdark"); else out.print("trclear"); %>"> 
           <% if(lnTransac.getE03CNTINT().equals("07")){%>
            <td width="40%">
              <div align="right">F.E.C.I. :</div>
            </td>
			 <% } else { %> 
            <td width="40%">
              <div align="right">I.V.A. :</div>
            </td>
			 <% } %> 
            <td width="60%">
              <input type="text" name="E03TRNIVA" size="15" maxlength="15" value="<%= lnTransac.getE03TRNIVA().trim()%>" readonly>
              <input type="hidden" name="E03TRNIVF" value="<%= lnTransac.getE03TRNIVF()%>">
              <input type="radio" name="CE03TRNIVF" value="0" disabled 
			   onClick="document.forms[0].E03TRNIVF.value='0';Recalculate();"
			   <%if(lnTransac.getE03TRNIVF().equals("0")) out.print("checked");%>> Reversar <input type="radio" name="CE03TRNIVF" value="5" disabled 
			   onClick="document.forms[0].E03TRNIVF.value='5';Recalculate();"
			   <%if(lnTransac.getE03TRNIVF().equals("5")) out.print("checked");%>> Pago 
            </td>
          </tr>

        </table>
      </td>
    </tr>
  </table>


  <h4>Informacion adicional</h4>
  <table class="tableinfo">
    <tr> 
      <td> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          
          <tr id="trdark"> 
            <td width="40%"> 
              <div align="right">Fecha Valor :</div>
            </td>
            <td width="60%"> 
              <input type="text" name="E03TRNVD1" size="2" maxlength="2" value="<%= lnTransac.getE03TRNVD1().trim()%>">
              <input type="text" name="E03TRNVD2" size="2" maxlength="2" value="<%= lnTransac.getE03TRNVD2().trim()%>">
              <input type="text" name="E03TRNVD3" size="2" maxlength="2" value="<%= lnTransac.getE03TRNVD3().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="40%"> 
              <div align="right">Tasa de Cambio :</div>
            </td>
            <td width="60%"> 
              <input type="text" name="E03DEAEXR" size="11" maxlength="11" value="<%= lnTransac.getE03DEAEXR().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="40%"> 
              <div align="right">Descripci&oacute;n :</div>
            </td>
            <td width="60%"> 
              <input type="text" name="E03DEANR1" size="30" maxlength="30" value="<%= lnTransac.getE03DEANR1().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="40%"> 
              <div align="right"></div>
            </td>
            <td width="60%"> 
              <input type="text" name="E03DEANR2" size="30" maxlength="30" value="<%= lnTransac.getE03DEANR2().trim()%>">
            </td>
          </tr>
        </table>
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
           <td align="center"> <b>Cuenta Contrapartida</b> </td>
          </tr>
        </table>
        <table class="tableinfo" style="filter:''">
          <tr id="trdark"> 
            <td width="31%" > 
              <div align="center">Concepto</div>
            </td>
            <td width="5%"  > 
              <div align="center">Banco</div>
            </td>
            <td width="5%"  > 
              <div align="center">Sucursal</div>
            </td>
            <td width="5%" > 
              <div align="center">Moneda</div>
            </td>
            <td width="21%" > 
              <div align="center">Referencia</div>
            </td>
            <td nowrap> 
              <div align="center">Titular</div>
            </td>
            
          </tr>
          <tr id="trclear"> 
            <td width="31%" > 
              <div align="center"> 
                <input type=HIDDEN name="E03TRNOPC" value="<%= lnTransac.getE03TRNOPC().trim()%>">
                <input type=HIDDEN name="E03TRNGLN" value="<%= lnTransac.getE03TRNGLN().trim()%>">
                <input type="text" name="E03TRNCON" size="25" maxlength="25" readonly value="<%= lnTransac.getE03TRNCON().trim()%>">
              </div>
            </td>
            <td width="5%" > 
              <div align="center"> 
                <input type="text" name="E03TRNBNK" size="2" maxlength="2" value="<%= lnTransac.getE03TRNBNK().trim()%>">
              </div>
            </td>
            <td width="5%" > 
              <div align="center"> 
                <input type="text" name="E03TRNBRN" size="3" maxlength="3" value="<%= lnTransac.getE03TRNBRN().trim()%>">
              </div>
            </td>
            <td width="5%" > 
              <div align="center">
                <input type="text" name="E03TRNCCY" size="3" maxlength="3" value="<%= lnTransac.getE03TRNCCY().trim()%>">
              </div>
            </td>
            <td width="15%" > 
              <div align="center"> 
                <input type="text" name="E03TRNACC" size="16" maxlength="16" value="<%= lnTransac.getE03TRNACC().trim()%>">
              </div>
            </td>
             
			<td nowrap width="21%" > 
              <div align="center"> 
                <input type="text" readonly name="E03TRNNA1" size="25" maxlength="25" value="<%= lnTransac.getE03TRNNA1().trim()%>">
              </div>
            </td>
			
          </tr>
        </table>
        
      </td>
    </tr>
  </table>
  <SCRIPT language="JavaScript">
      UpdateField('E03DEAPRI','E03TRNPRI','AFTERPRI','E03TRNPRF',false);
      <% if(lnTransac.getH03FLGWK3().equals("R")){%>
      UpdateField('E03DEAREA','E03TRNREA','AFTERREA','E03TRNREF',false);
      <% } %>
      UpdateField('E03DEAINT','E03TRNINT','AFTERINT','E03TRNINF',false);
      UpdateField('E03DEAINT','E03TRNAIN','AFTERINT','E03TRNAIF',true);
      UpdateField('E03DEAMOR','E03TRNMOR','AFTERMOR','E03TRNMOF',false);
      UpdateField('E03DEAMOR','E03TRNAMO','AFTERMOR','E03TRNAMF',true);
      UpdateTotal();
  </SCRIPT>
  </form>
</body>
</html>
