<html>
<head>
<title>Junta Directiva</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "board" class= "datapro.eibs.beans.ESD000004Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
 
 

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/CrossBrowserFunctions.js"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/Address.js"> </SCRIPT>
<SCRIPT Language="Javascript">

   builtNewMenu(client_corp_opt);

</SCRIPT>

</head>

<body bgcolor="#FFFFFF">


<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 if ( !userPO.getPurpose().equals("NEW") ) {
    out.println("<SCRIPT> initMenu(); </SCRIPT>");
 }
%> 
<h3 align="center">Junta Directiva <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_corp_board, ESD0080" ></h3>
<hr size="4">

 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080" >
 <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="16">
  

<table class="tableinfo">
  <tr > 
    <td> 
      <table cellspacing="0" cellpadding="2" width="100%"  class="tbhead"  align="center">
        <tr>
             <td nowrap width="10%" align="right"> Cliente: 
               </td>
          <td nowrap width="12%" align="left">
      			<%= userPO.getHeader1()%>
          </td>
            <td nowrap width="6%" align="right">ID:  
            </td>
          <td nowrap width="14%" align="left">
      			<%= userPO.getHeader2()%>
          </td>
            <td nowrap width="8%" align="right"> Nombre: 
               </td>
          <td nowrap width="50%"align="left">
      			<%= userPO.getHeader3()%>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<br>
<div id="dataDiv" align="center" style="height:351; overflow-y:scroll; width:90%; padding-left:50; padding-right:5" class="scbarcolor">
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

  <table id="mainTable<%= name %>" class="tableinfo" width="100%">
    <tr bgcolor="#FFFFFF" bordercolor="#FFFFFF"> 
      <td> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" name="TB_BENEFICIARY_CORP" bgcolor="#FFFFFF" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF" bordercolordark="#FFFFFF"  align="center">
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right">Nombre del Directivo :</div>
            </td>
            <td nowrap width="58%"> 
              <input type="text" name="E<%= name %>4MA1" maxlength="45" size="46" value="<%= board.getField("E" + name + "4MA1").getString().trim()%>">
              </td>
          </tr>
          
		<%	
		System.out.println( "SCR=" + board.getH04SCR() );
		if( board.getH04SCR().equals("07")){%> 	
			<jsp:include page="ESD0080_address_template_basic_banesco_panama.jsp" flush="true">
				<jsp:param name="suffix" value='<%="E"+name+"4"%>' />
				<jsp:param name="title" value="Dirección" />
				<jsp:param name="messageName" value="board" />
				<jsp:param name="readOnly" value="false" />
				<jsp:param name="basic" value="false" />
			</jsp:include>
	
		<%} else if( board.getH04SCR().equals("03")) {%> 

           <jsp:include page="ESD0080_address_template_generic_venezuela.jsp"  flush="true">
	          <jsp:param name="index" value="<%=name%>" />
	          <jsp:param name="title" value="Direccion" />
	          <jsp:param name="messageName" value="board" />
              <jsp:param name="addressType" value="S" />
              <jsp:param name="readOnly"  value="false" />
           </jsp:include>
           
         <%} else { %>

           <jsp:include page="ESD0080_address2_template_basic_generic.jsp"  flush="true">
	          <jsp:param name="index" value="<%=name%>" />
	          <jsp:param name="title" value="Direccion" />
	          <jsp:param name="messageName" value="board" />
              <jsp:param name="addressType" value="S" />
              <jsp:param name="readOnly"  value="false" />
           </jsp:include>

		<%} %>   
          
        <%--  
          <tr id="trclear"> 
            <td width="42%" nowrap> 
              <div align="right">Direcci&oacute;n :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" name="E<%= name %>4MA2" maxlength="35" size="36" value="<%= board.getField("E" + name + "4MA2").getString().trim()%>">
              </td>
          </tr>
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right"></div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" name="E<%= name %>4MA3" maxlength="35" size="36" value="<%= board.getField("E" + name + "4MA3").getString().trim()%>">
              </td>
          </tr>
		  <%if(!currUser.getE01INT().equals("18")){%>
          <tr id="trclear"> 
            <td width="42%" nowrap> 
              <div align="right">Ciudad :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" name="E<%= name %>4CTY" maxlength="30" size="31" value="<%= board.getField("E" + name + "4CTY").getString().trim()%>">
              </td>
          </tr>
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right">Estado :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" name="E<%= name %>4STE" maxlength="4" size="5" value="<%= board.getField("E" + name + "4STE").getString().trim()%>">
              <a href="javascript:GetCodeCNOFC('E<%= name %>4STE','27')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></a>	
              </td>
          </tr>
          <%}%>
          <tr id="trclear"> 
            <td width="42%" nowrap> 
              <div align="right">Pa&iacute;s :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" name="E<%= name %>4CTR" maxlength="20" size="21" value="<%= board.getField("E" + name + "4CTR").getString().trim()%>">
              </td>
          </tr>
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right">Apartado Postal :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" name="E<%= name %>4POB" maxlength="10" size="11" value="<%= board.getField("E" + name + "4POB").getString().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="42%" nowrap> 
              <div align="right">C&oacute;digo Postal : </div>
            </td>
            <td width="58%" nowrap>
              <input type="text" name="E<%= name %>4ZPC" maxlength="15" size="16" value="<%= board.getField("E" + name + "4ZPC").getString().trim()%>">
            </td>
          </tr>
          --%>
          
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right">Nacionalidad :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" name="E<%= name %>4BNC" maxlength="4" size="5" value="<%= board.getField("E" + name + "4BNC").getString().trim()%>">
              <a href="javascript:GetCodeCNOFC('E<%= name %>4BNC','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></a> 
              </td>
          </tr>
          <tr id="trclear"> 
            <td width="42%" nowrap> 
              <div align="right">Cargo :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" name="E<%= name %>4MA4" maxlength="35" size="36" value="<%= board.getField("E" + name + "4MA4").getString().trim()%>">
              </td>
          </tr>
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right">Fecha :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" name="E<%= name %>4DT1" maxlength="2" size="2" value="<%= board.getField("E" + name + "4DT1").getString().trim()%>">
              <input type="text" name="E<%= name %>4DT2" maxlength="2" size="2" value="<%= board.getField("E" + name + "4DT2").getString().trim()%>">
              <input type="text" name="E<%= name %>4DT3" maxlength="2" size="2" value="<%= board.getField("E" + name + "4DT3").getString().trim()%>">
              </td>
          </tr>
          <tr id="trclear"> 
            <td width="42%" nowrap > 
              <div align="right">Profesi&oacute;n/Ocupaci&oacute;n :</div>
            </td>
            <td width="58%" nowrap > 
              <p> 
                <input type="text" name="E<%= name %>4MLC" value="<%= board.getField("E" + name + "4MLC").getString().trim()%>" size="5" maxlength="4">
                <a href="javascript:GetCodeCNOFC('E<%= name %>4MLC','49')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"  ></a>	
                </p>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right">En Calidad de :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" name="E<%= name %>4INC" maxlength="4" size="5" value="<%= board.getField("E" + name + "4INC").getString().trim()%>">
              </td>
          </tr>
          <tr id="trclear"> 
            <td width="42%" nowrap > 
              <div align="right">Estado Civil :</div>
            </td>
            <td width="58%" nowrap > 
              <select name="E<%= name %>4BMS">
                <option value=1 <% if (board.getField("E" + name + "4BMS").getString().equals("1")) out.print("selected"); %>>Casado(a)</option>
                <option value=2 <% if (board.getField("E" + name + "4BMS").getString().equals("2")) out.print("selected"); %>>Soltero(a)</option>
                <option value=3 <% if (board.getField("E" + name + "4BMS").getString().equals("3")) out.print("selected"); %>>Divorciado(a)</option>
                <option value=4 <% if (board.getField("E" + name + "4BMS").getString().equals("4")) out.print("selected"); %>>Viudo(a)</option>
                <option value=5 <% if (board.getField("E" + name + "4BMS").getString().equals("5")) out.print("selected"); %>>Otros</option>
                <option value=0 <% if ( !(board.getField("E" + name + "4BMS").getString().equals("1") || board.getField("E" + name + "4BMS").getString().equals("2") || board.getField("E" + name + "4BMS").getString().equals("3") || board.getField("E" + name + "4BMS").getString().equals("4") || board.getField("E" + name + "4BMS").getString().equals("5"))) out.print("selected"); %>></option>
              </select>
              </td>
          </tr>
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right">Sexo :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="radio" name="E<%= name %>4BSX" value="F" <% if (board.getField("E" + name + "4BSX").getString().equals("F")) out.print("checked"); %> checked>
              Femenino 
              <input type="radio" name="E<%= name %>4BSX" value="M" <% if (board.getField("E" + name + "4BSX").getString().equals("M")) out.print("checked"); %>>
              Masculino </td>
          </tr>
          <tr id="trclear"> 
            <td width="42%" nowrap> 
              <div align="right">Tel&eacute;fono :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" name="E<%= name %>4HPN" maxlength="11" size="12" value="<%= board.getField("E" + name + "4HPN").getString().trim()%>">
              </td>
          </tr>
          
          <%if( !board.getH04SCR().equals("07") ){ %>           
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right">Identificaci&oacute;n :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" name="E<%= name %>4BNI" maxlength="15" size="16" value="<%= board.getField("E" + name + "4BNI").getString().trim()%>">
              </td>
          </tr>
          <tr id="trclear">
            <%if(currUser.getE01INT().equals("18")){%>  
            <td width="42%" > 
              <div align="right">Código de Ciudad :</div>
            </td>
            <%} else {%>
            <td width="42%" > 
              <div align="right">Tipo :</div>
            </td>
            <%}%>
            <td width="58%" nowrap > 
              <input type="text" name="E<%= name %>4TID" value="<%= board.getField("E" + name + "4TID").getString().trim()%>" size="5" maxlength="4">
              <%if(currUser.getE01INT().equals("18")){%>  
              <a href="javascript:GetCodeCNOFC('E<%= name %>4TID','84')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>	
              <%} else {%>
              <a href="javascript:GetCodeCNOFC('E<%= name %>4TID','34')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>	
              <%}%>
              </td>
          </tr>
          <tr id="trdark"> 
             <%if(currUser.getE01INT().equals("18")){%>  
            <td width="42%" > 
              <div align="right">Comuna :</div>
            </td>
            <%} else {%>
            <td width="42%" > 
              <div align="right">País :</div>
            </td>
            <%}%>
            <td width="58%" nowrap> 
              <input type="text" name="E<%= name %>4PID" size="5" maxlength="4" value="<%= board.getField("E" + name + "4PID").getString().trim()%>">
			  <%if(currUser.getE01INT().equals("18")){%> 
              <a href="javascript:GetCodeCNOFC('E<%= name %>4PID','80')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
              <%} else {%>
			  <a href="javascript:GetCodeCNOFC('E<%= name %>4PID','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
			  <%}%> 
              </td>
          </tr>
          <% } %>
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
   EventUtils.addEventListener(document.forms[0],'submit',calculateAddress);
</SCRIPT>

  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</form>
</body>
</html>
