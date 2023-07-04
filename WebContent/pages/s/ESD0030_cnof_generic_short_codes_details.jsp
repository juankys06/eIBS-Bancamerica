<html>
<head>
<title>Codigos de Referencia</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="refCodes" class="datapro.eibs.beans.ESD003002Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
</SCRIPT>

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>


<H3 align="center">C&oacute;digos de Referencia del Sistema <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cnof_generic_short_codes_details.jsp, ESD0030"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSESD0030" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
   <INPUT TYPE=HIDDEN NAME="E02CNOBNK" value="<%= currUser.getE01UBK()%>">
  <h4>Informaci&oacute;n B&aacute;sica</h4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Clasificaci&oacute;n :</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E02CNOCFL" size="3" maxlength="2" value="<%= refCodes.getE02CNOCFL().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">C&oacute;digo :</div>
            </td>
            <td nowrap height="23"> 
              <div align="left"> 
                <input type="text" name="E02CNORCD" size="6" maxlength="4" value="<%= refCodes.getE02CNORCD().trim()%>">
                <input type="text" name="E02CNODSC" size="36" maxlength="35" value="<%= refCodes.getE02CNODSC().trim()%>" >
              </div>
            </td>
          </tr>
          
          <% if (refCodes.getE02CNOCFL().trim().equals("AB")) {%>
           <tr id="trdark"> 
             <td nowrap width="16%" height="19"> 
               <div align="right">Actualiza Fecha Ultima Renovaci&oacute;n :</div>
             </td>
             <td nowrap height="19">
               <input type="hidden" name="E02CNOPAF" value="<%= refCodes.getE02CNOPAF()%>">
               <input type="radio" name="CE02CNOPAF" value="1" onClick="document.forms[0].E02CNOPAF.value='1'"
			   <%if(refCodes.getE02CNOPAF().equals("1")) out.print("checked");%>>
                S&iacute; 
               <input type="radio" name="CE02CNOPAF" value="2" onClick="document.forms[0].E02CNOPAF.value='2'"
			   <%if(refCodes.getE02CNOPAF().equals("2")) out.print("checked");%>>
               No <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0" >
              </td>
            </tr>            
          <% } else {%>
            <tr id="trdark"> 
              <td nowrap width="16%" height="19"> 
                <div align="right">N&uacute;mero de Referencia :</div>
              </td>
              <td nowrap height="19"> 
                <div align="left"> 
                  <input type="text" name="E02CNOSCG" size="17" maxlength="16" value="<%= refCodes.getE02CNOSCG().trim()%>">
                </div>
              </td>
          </tr>
          <% }%>  
          
          
          <%  if (refCodes.getE02CNOCFL().trim().equals("Y9")) {%>

          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Moneda de Control de Morosidad:</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                <input type="text" name="E02CNOSCY" size="4" maxlength="3" value="<%= refCodes.getE02CNOSCY() %>" >
                <a href="javascript:GetCurrency('E02CNOSCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a></div>
                
             </div>
            </td>
            
          </tr>

          
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Ciclo de Control de Morosidad:</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                
                <select name="E02CNOF04">
                <option value="N" <% if (!(refCodes.getE02CNOF04().equals("D") ||refCodes.getE02CNOF04().equals("W")||refCodes.getE02CNOF04().equals("M")||refCodes.getE02CNOF04().equals("Q")||refCodes.getE02CNOF04().equals("S")||refCodes.getE02CNOF04().equals("Y"))) out.print("selected"); %>> No Aplica</option>
                <option value="D" <% if(refCodes.getE02CNOF04().equals("D")) out.print("selected");%>>Diario</option>
                <option value="W" <% if(refCodes.getE02CNOF04().equals("W")) out.print("selected");%>>Semanal</option>
                <option value="M" <% if(refCodes.getE02CNOF04().equals("M")) out.print("selected");%>>Mensual</option>
                <option value="Q" <% if(refCodes.getE02CNOF04().equals("Q")) out.print("selected");%>>Trimestral</option>
                <option value="S" <% if(refCodes.getE02CNOF04().equals("S")) out.print("selected");%>>Semestral</option>
                <option value="Y" <% if(refCodes.getE02CNOF04().equals("Y")) out.print("selected");%>>Anual</option>
              </select>
          
          
          
             </div>
            </td>
            
          </tr>
          
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Monto Total de Morosidad:</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                <input type="text" name="E02CNOCHG" size="16" maxlength="15" value="<%= refCodes.getE02CNOCHG() %>" onKeypress="enterDecimal()" >
             </div>
            </td>
            
          </tr>


          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Porcentaje Total de Morosidad:</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                <input type="text" name="E02CNOCST" size="8" maxlength="7" value="<%= refCodes.getE02CNOCST() %>" onKeypress="enterDecimal()" >
             </div>
            </td>
            
          </tr>


          
          <% }%>



          
        </table>
      </td>
    </tr>
  </table>
  <div align="center">
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
  </form>
</body>
</html>
