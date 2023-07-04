<html>
<head>
<title>Beneficiarios</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "cdData" class= "datapro.eibs.beans.EDL013012Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

  <% if (userPO.getPurpose().equals("INQUIRY")) {%>
   	builtNewMenu(cd_i_opt);
  <% } else {%>
 	builtNewMenu(cd_a_opt);
  <% } %>


</SCRIPT>

</head>

<body>

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
  
    out.println("<SCRIPT> initMenu(); </SCRIPT>");
 
%>

<h3 align="center">Distribucion de Pago en Renovacion<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_renov_pay.jsp, EDL0130"></h3>
<hr size="4">
<FORM >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="300">  
  <INPUT TYPE=HIDDEN NAME="H12FLGWK1" VALUE="<%= cdData.getH12FLGWK1() %>">
  
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <input type="text" name="E02CUN" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E02NA1" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Certificado :</b></div>
            </td>
            <td nowrap width="20%"> 
              <input type="text" name="E12DEAACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <input type="text" name="E02PRO" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

  <br>
<div id="dataDiv" align="center" style="height:427; overflow-y:scroll; width:90%; padding-left:50; padding-right:10" class="scbarcolor">
<%
  int maxc = 10;
  String name;
  for ( int i=1; i<=maxc; i++ ) {
    if ( i == 10 ) {
      name = i + ""; 
    }
    else {
      name = "0" + i;
    }
    
    out.println("<table class=\"tbenter\" border=\"0\" width=\"100%\">");
    out.println("<tr>");
    
    switch ( i ) {
        case 1 :     
%> 
 <td align="left"><h4>Primero</h4></td>
<%          
           break;
        case 2 : 
%> 
 <td align="left"><h4>Segundo</h4></td>
<%          
           break;
        case 3 : 
%> 
<td align="left"><h4>Tercero</h4></td>
<%          
           break;
        case 4 : 
%> 
<td align="left"><h4>Cuarto</h4></td>
<%          
           break;
        case 5 : 
%> 
<td align="left"><h4>Quinto</h4></td>
<%          
           break;
        case 6 : 
%> 
<td align="left"><h4>Sexto</h4></td>
<%          
           break;
        case 7 : 
%> 
<td align="left"><h4>Séptimo</h4></td>
<%          
           break;
        case 8 : 
%> 
<td align="left"><h4>Octavo</h4></td>
<%          
           break;
        case 9 : 
%> 
<td align="left"><h4>Noveno</h4></td>
<%          
           break;
        case 10 : 
%> 
<td align="left"><h4>Décimo</h4></td>
<%          
           break;
     }
  out.println("<td width=\"30%\" align=\"right\">");
  out.println("<h4>");
  out.print("<A href=\"javascript:go(1)\">1</A>,<A href=\"javascript:go(2)\">2</A>,<A href=\"javascript:go(3)\">3</A>,<A href=\"javascript:go(4)\">4</A>,<A href=\"javascript:go(5)\">5</A>,");
  out.print("<A href=\"javascript:go(6)\">6</A>,<A href=\"javascript:go(7)\">7</A>,<A href=\"javascript:go(8)\">8</A>,<A href=\"javascript:go(9)\">9</A>,<A href=\"javascript:go(10)\">10</A>");
   out.println("</h4>"); 
  out.println("</td>");   
  out.println("</tr>");
  out.println("</table>"); 
%>

  <table id="mainTable<%= name %>" class="tableinfo">
    <tr> 
      <td> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" name="TB_BENEFICIARY_CORP" bgcolor="#FFFFFF" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF" bordercolordark="#FFFFFF"  align="center">
          <tr id="trdark"> 
            <td width="30%" nowrap> 
              <div align="right">Beneficiario :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E12D<%= name %>CUN" maxlength="10" size="12" value="<%= cdData.getField("E12D" + name + "CUN").getString().trim()%>" readonly>           
              <input type="text" name="E12D<%= name %>BN1" maxlength="35" size="35" value="<%= cdData.getField("E12D" + name + "BN1").getString().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Direcci&oacute;n :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E12D<%= name %>BN2" maxlength="35" size="35" value="<%= cdData.getField("E12D" + name + "BN2").getString().trim()%>" readonly>
              </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"></div>
            </td>
            <td nowrap> 
              <input type="text" name="E12D<%= name %>BN3" maxlength="35" size="35" value="<%= cdData.getField("E12D" + name + "BN3").getString().trim()%>" readonly>
              </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right"></div>
            </td>
            <td nowrap> 
              <input type="text" name="E12D<%= name %>BN4" maxlength="35" size="35" value="<%= cdData.getField("E12D" + name + "BN4").getString().trim()%>" readonly>
              </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"></div>
            </td>
            <td nowrap> 
              <input type="text" name="E12D<%= name %>BN5" maxlength="35" size="35" value="<%= cdData.getField("E12D" + name + "BN5").getString().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Monto/Porcentaje :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E12D<%= name %>AMP" maxlength="16" size="17" value="<%= cdData.getField("E12D" + name + "AMP").getString().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Monto/Porc/Retenc. :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E12D<%= name %>FLG" maxlength="1" size="2" value="<%= cdData.getField("E12D" + name + "FLG").getString().trim()%>" readonly> <b>(F,%,H)</b>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Via de Pago :</div>
            </td>
            <td nowrap > 
              <select name="E12D<%= name %>PVI" disabled>
                <option value="C" <% if (cdData.getField("E12D" + name + "PVI").getString().equals("C")) out.print("selected"); %>>Cheque Oficial</option>
                <option value="R" <% if ( !(cdData.getField("E12D" + name + "PVI").getString().equals("1") || cdData.getField("E12D" + name + "PVI").getString().equals("2") 
                || cdData.getField("E12D" + name + "PVI").getString().equals("3") || cdData.getField("E12D" + name + "PVI").getString().equals("C") 
                || cdData.getField("E12D" + name + "PVI").getString().equals("T") || cdData.getField("E12D" + name + "PVI").getString().equals("L"))) out.print("selected"); %>>Cta Detalle</option>
              	<option value="T" <% if (cdData.getField("E12D" + name + "PVI").getString().equals("T")) out.print("selected"); %>>Cert.Deposito</option>
                <option value="L" <% if (cdData.getField("E12D" + name + "PVI").getString().equals("L")) out.print("selected"); %>>Pago Prestamo</option>
                <option value="1" <% if (cdData.getField("E12D" + name + "PVI").getString().equals("1")) out.print("selected"); %>>Swift MT-100</option>
                <option value="2" <% if (cdData.getField("E12D" + name + "PVI").getString().equals("2")) out.print("selected"); %>>Swift MT-200</option>
                <option value="3" <% if (cdData.getField("E12D" + name + "PVI").getString().equals("3")) out.print("selected"); %>>Swift MT-202</option>
                </select>
              </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Cta.Credito :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E12D<%= name %>BAC" maxlength="15" size="12" value="<%= cdData.getField("E12D" + name + "BAC").getString().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Pagar a :</div>
            </td>
            <td nowrap> 
              <input type="radio" name="E12D<%= name %>PIN" value="P" <% if (cdData.getField("E12D" + name + "PIN").getString().equals("P")) out.print("checked"); %> disabled>
              Principal 
              <input type="radio" name="E12D<%= name %>PIN" value="I" <% if (!cdData.getField("E12D" + name + "PIN").getString().equals("P")) out.print("checked"); %> disabled>
              Interes </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">PO/ Referencia :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E12D<%= name %>COT" maxlength="11" size="12" value="<%= cdData.getField("E12D" + name + "COT").getString().trim()%>" readonly>
              </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Comentarios :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E12D<%= name %>RM1" maxlength="35" size="36" value="<%= cdData.getField("E12D" + name + "RM1").getString().trim()%>" readonly>
              </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"></div>
            </td>
            <td nowrap> 
              <input type="text" name="E12D<%= name %>RM2" maxlength="35" size="36" value="<%= cdData.getField("E12D" + name + "RM2").getString().trim()%>" readonly>
              </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right"></div>
            </td>
            <td nowrap> 
              <input type="text" name="E12D<%= name %>RM3" maxlength="35" size="36" value="<%= cdData.getField("E12D" + name + "RM3").getString().trim()%>" readonly>
              </td>
          </tr>
        </table>
        
      </td>
    </tr>
  </table>
  
  <%
  }
%>
</div>
<SCRIPT Language="Javascript">
   dataDiv.style.height=  mainTable1.offsetTop + mainTable1.offsetHeight +"";
</SCRIPT>
  
 	 
</form>
</body>
</html>
