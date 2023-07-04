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

   builtNewMenu(cd_m_opt);


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

<h3 align="center">Incremento Automatico<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_outo_increase.jsp, EDL0130"></h3>
<hr size="4">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0132" >
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
<div id="dataDiv" align="center" style="height:404; overflow-y:scroll; width:90%; padding-left:50; padding-right:10" class="scbarcolor">
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
              <input type="text" name="E12D<%= name %>CUN" maxlength="10" size="12" value="<%= cdData.getField("E12D" + name + "CUN").getString().trim()%>">           
              <a href="javascript:GetCustomerDescId('E12D<%= name %>CUN','E12D<%= name %>BN1','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
              <input type="text" name="E12D<%= name %>BN1" maxlength="35" size="35" value="<%= cdData.getField("E12D" + name + "BN1").getString().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Direcci&oacute;n :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E12D<%= name %>BN2" maxlength="35" size="35" value="<%= cdData.getField("E12D" + name + "BN2").getString().trim()%>">
              </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"></div>
            </td>
            <td nowrap> 
              <input type="text" name="E12D<%= name %>BN3" maxlength="35" size="35" value="<%= cdData.getField("E12D" + name + "BN3").getString().trim()%>">
              </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right"></div>
            </td>
            <td nowrap> 
              <input type="text" name="E12D<%= name %>BN4" maxlength="35" size="35" value="<%= cdData.getField("E12D" + name + "BN4").getString().trim()%>">
              </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"></div>
            </td>
            <td nowrap> 
              <input type="text" name="E12D<%= name %>BN5" maxlength="35" size="35" value="<%= cdData.getField("E12D" + name + "BN5").getString().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Monto/Porcentaje :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E12D<%= name %>AMP" maxlength="16" size="17" value="<%= cdData.getField("E12D" + name + "AMP").getString().trim()%>">
             </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Indicador de Calculo :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E12D<%= name %>FLG" maxlength="1" size="2" value="<%= cdData.getField("E12D" + name + "FLG").getString().trim()%>"> <b>(F,%)</b>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Cta.Inversion :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E12D<%= name %>BAC" maxlength="15" size="12" value="<%= cdData.getField("E12D" + name + "BAC").getString().trim()%>">
              <a href="javascript:GetAccount('E12D<%= name %>BAC','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="bottom" border="0" ></a> 
		    </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Base de Calculo :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E12D<%= name %>PIN" maxlength="1" size="2" value="<%= cdData.getField("E12D" + name + "PIN").getString().trim()%>">  <b>(1,2,3)</b>            
              </td>
		  </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Periodo en Dias :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E12D<%= name %>COT" maxlength="5" size="6" value="<%= cdData.getField("E12D" + name + "COT").getString().trim()%>">
              </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Comentarios :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E12D<%= name %>RM1" maxlength="35" size="36" value="<%= cdData.getField("E12D" + name + "RM1").getString().trim()%>">
              </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right"></div>
            </td>
            <td nowrap> 
              <input type="text" name="E12D<%= name %>RM2" maxlength="35" size="36" value="<%= cdData.getField("E12D" + name + "RM2").getString().trim()%>">
              </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"></div>
            </td>
            <td nowrap> 
              <input type="text" name="E12D<%= name %>RM3" maxlength="35" size="36" value="<%= cdData.getField("E12D" + name + "RM3").getString().trim()%>">
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
  
  
   <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
 	 
</form>
</body>
</html>
