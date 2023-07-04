<html>
<head>
<title>Beneficiarios</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "bene" class= "datapro.eibs.beans.ESD000004Message"   scope="session"/>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"   scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
	 builtNewMenu(inv_i_port_opt);
</SCRIPT>

</head>

<body nowrap onbeforeprint="showOpt(true)" onafterprint="showOpt(false)">

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
  
    out.println("<SCRIPT> initMenu(); </SCRIPT>");
 
%>

<h3 align="center">Beneficiarios<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="inv_port_bene, EIE0010"></h3>
<hr size="4">
<FORM METHOD="post" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="6">  

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
                <input type="text" readonly  name="E02CUN" size="9" maxlength="9"  value="<%= userPO.getCusNum().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" readonly  name="E02NA1" size="45" maxlength="45"  value="<%= userPO.getCusName().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Portafolio :</b></div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" readonly  name="E04CUN" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" >
              </div>
            </td>
            <td nowrap> 
              <div align="right"><b>Descripción : </b></div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" readonly  name="E01DEACCY" size="35" maxlength="35" value="<%= userPO.getHeader1().trim()%>" >
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <BR>
  <div id="dataDiv" align="center" style="width:90%; padding-left:50; padding-right:10" class="scbarcolor">
<%
  int bene_amount = 10;
  String name;
  for ( int i=1; i<=bene_amount; i++ ) {
    if ( i == 10 ) {
      name = "A"; 
    }
    else {
      name = i + "";
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

  <table id="mainTable<%= name %>" class="tableinfo" >
    <tr bgcolor="#FFFFFF" bordercolor="#FFFFFF"> 
      <td> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" name="TB_BENEFICIARY_CORP" bgcolor="#FFFFFF" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF" bordercolordark="#FFFFFF"  align="center">
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right">Nombre Legal  :</div>
            </td>
            <td nowrap width="58%"> 
              <input type="text" readonly  name="E<%= name %>4MA1" maxlength="45" size="46" value="<%= bene.getField("E" + name + "4MA1").getString().trim()%>">
              </td>
          </tr>
          <tr id="trclear"> 
            <td width="42%" nowrap> 
              <div align="right">Dirección :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" readonly  name="E<%= name %>4MA2" maxlength="35" size="36" value="<%= bene.getField("E" + name + "4MA2").getString().trim()%>">
              </td>
          </tr>
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right"></div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" readonly  name="E<%= name %>4MA3" maxlength="35" size="36" value="<%= bene.getField("E" + name + "4MA3").getString().trim()%>">
              </td>
          </tr>
          <tr id="trclear"> 
            <td width="42%" nowrap> 
              <div align="right">Ciudad :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" readonly  name="E<%= name %>4CTY" maxlength="30" size="31" value="<%= bene.getField("E" + name + "4CTY").getString().trim()%>">
              </td>
          </tr>
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right">Estado :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" readonly  name="E<%= name %>4STE" maxlength="4" size="5" value="<%= bene.getField("E" + name + "4STE").getString().trim()%>">
              	
              </td>
          </tr>
          <tr id="trclear"> 
            <td width="42%" nowrap> 
              <div align="right">País :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" readonly  name="E<%= name %>4CTR" maxlength="20" size="21" value="<%= bene.getField("E" + name + "4CTR").getString().trim()%>">
              </td>
          </tr>
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right">Apartado Postal :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" readonly  name="E<%= name %>4POB" maxlength="10" size="11" value="<%= bene.getField("E" + name + "4POB").getString().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="42%" nowrap> 
              <div align="right">Código Postal : </div>
            </td>
            <td width="58%" nowrap>
              <input type="text" readonly  name="E<%= name %>4ZPC" maxlength="15" size="16" value="<%= bene.getField("E" + name + "4ZPC").getString().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right">Nacionalidad :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" readonly  name="E<%= name %>4BNC" maxlength="4" size="5" value="<%= bene.getField("E" + name + "4BNC").getString().trim()%>">
              
              </td>
          </tr>
          <tr id="trclear"> 
            <td width="42%" nowrap> 
              <div align="right">Título :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" readonly  name="E<%= name %>4MA4" maxlength="35" size="36" value="<%= bene.getField("E" + name + "4MA4").getString().trim()%>">
              </td>
          </tr>
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right">Fecha :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" readonly  name="E<%= name %>4DT1" maxlength="2" size="2" value="<%= bene.getField("E" + name + "4DT1").getString().trim()%>">
              <input type="text" readonly  name="E<%= name %>4DT2" maxlength="2" size="2" value="<%= bene.getField("E" + name + "4DT2").getString().trim()%>">
              <input type="text" readonly  name="E<%= name %>4DT3" maxlength="2" size="2" value="<%= bene.getField("E" + name + "4DT3").getString().trim()%>">
              </td>
          </tr>
          <tr id="trclear"> 
            <td width="42%" nowrap > 
              <div align="right">Profesi&oacute;n/Ocupaci&oacute;n :</div>
            </td>
            <td width="58%" nowrap > 
              <p> 
                <input type="text" readonly  name="E<%= name %>4MLC" value="<%= bene.getField("E" + name + "4MLC").getString().trim()%>" size="5" maxlength="4">
                
                </p>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right">En capacidad de :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" readonly  name="E<%= name %>4INC" maxlength="4" size="5" value="<%= bene.getField("E" + name + "4INC").getString().trim()%>">
              </td>
          </tr>
          <tr id="trclear"> 
            <td width="42%" nowrap > 
              <div align="right">Estado Civil :</div>
            </td>
            <td width="58%" nowrap > 
              <select name="E<%= name %>4BMS">
                <option value=1 <% if (bene.getField("E" + name + "4BMS").getString().equals("1")) out.print("selected"); %>>Casado</option>
                <option value=2 <% if (bene.getField("E" + name + "4BMS").getString().equals("2")) out.print("selected"); %>>Soltero</option>
                <option value=3 <% if (bene.getField("E" + name + "4BMS").getString().equals("3")) out.print("selected"); %>>Divorciado</option>
                <option value=4 <% if (bene.getField("E" + name + "4BMS").getString().equals("4")) out.print("selected"); %>>Viudo</option>
                <option value=5 <% if (bene.getField("E" + name + "4BMS").getString().equals("5")) out.print("selected"); %>>Concubinato</option>
                <option value=0 <% if ( !(bene.getField("E" + name + "4BMS").getString().equals("1") || bene.getField("E" + name + "4BMS").getString().equals("2") || bene.getField("E" + name + "4BMS").getString().equals("3") || bene.getField("E" + name + "4BMS").getString().equals("4") || bene.getField("E" + name + "4BMS").getString().equals("5"))) out.print("selected"); %>></option>
              </select>
              </td>
          </tr>
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right">Sexo :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="radio" disabled name="E<%= name %>4BSX" value="F" <% if (bene.getField("E" + name + "4BSX").getString().equals("F")) out.print("checked"); %> checked>
              Femenino 
              <input type="radio" disabled name="E<%= name %>4BSX" value="M" <% if (bene.getField("E" + name + "4BSX").getString().equals("M")) out.print("checked"); %>>
              Masculino </td>
          </tr>
          <tr id="trclear"> 
            <td width="42%" nowrap> 
              <div align="right">Telefono :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" readonly  name="E<%= name %>4HPN" maxlength="11" size="12" value="<%= bene.getField("E" + name + "4HPN").getString().trim()%>">
              </td>
          </tr>
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right">Identificación :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" readonly  name="E<%= name %>4BNI" maxlength="15" size="16" value="<%= bene.getField("E" + name + "4BNI").getString().trim()%>">
              </td>
          </tr>
          <tr id="trclear"> 
            <td width="42%" > 
              <div align="right">Tipo :</div>
            </td>
            <td width="58%" nowrap > 
              <input type="text" readonly  name="E<%= name %>4TID" value="<%= bene.getField("E" + name + "4TID").getString().trim()%>" size="5" maxlength="4">
              
              </td>
          </tr>
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right">País :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" readonly  name="E<%= name %>4PID" size="5" maxlength="4" value="<%= bene.getField("E" + name + "4PID").getString().trim()%>">
              
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
   showOpt(false);
</SCRIPT>

</form>
</body>
</html>
