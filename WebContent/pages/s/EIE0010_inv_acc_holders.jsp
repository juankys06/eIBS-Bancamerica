<html>
<head>
<title>Titulares</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "rtTit" class= "datapro.eibs.beans.ESD000006Message"  scope="session"/>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>



</head>


<body bgcolor="#FFFFFF">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
	 error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors();");
     out.println("</SCRIPT>");
 }
    
%> 
<h3 align="center">Titulares <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_inq_tit.jsp,EDD0000"></h3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130" >
  <h4>Titulares para la cuenta número <%= userPO.getAccNum()%></h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <td nowrap width="9%"> 
              <div align="center">Número</div>
            </td>
            <td nowrap width="46%"> 
              <div align="center">Nombre y Apellidos</div>
            </td>
            <td nowrap width="27%"> 
              <div align="center"> Identificación </div>
            </td>
            <td nowrap width="18%"> 
              <div align="center">Relación</div>
            </td>
          </tr>
          <%
  				   int amount = 9;
 				   String name;
  					for ( int i=1; i<=amount; i++ ) {
   					  name = i + "";
   			%> 
          <tr id="trclear"> 
            <td nowrap width="9%"> 
              <div align="center"> 
                <input type="text" name="E06CU<%= name %>" size="9" maxlength="9" value="<%= rtTit.getField("E06CU"+name).getString().trim()%>" 
				readonly>
              </div>
            </td>
            <td nowrap width="46%"> 
              <div align="center"> 
                <input type="text" name="E06MA<%= name %>" size="40" maxlength="45" value="<%= rtTit.getField("E06MA"+name).getString().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="27%"> 
              <div align="center"> 
                <input type="text" name="E06ID<%= name %>" size="15" maxlength="15" value="<%= rtTit.getField("E06ID"+name).getString().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="18%"> 
              <div align="center"> 
                <input type="text" name="E06TY<%= name %>" size="20"  maxlength="15" 
				  value="<% if (rtTit.getField("E06TY"+name).getString().trim().equals("B")) out.print("Beneficiario");
							else if (rtTit.getField("E06TY"+name).getString().trim().equals("C")) out.print("Custodio");
							else if (rtTit.getField("E06TY"+name).getString().trim().equals("D")) out.print("Protector");
							else if (rtTit.getField("E06TY"+name).getString().trim().equals("E")) out.print("Representante");
							else if (rtTit.getField("E06TY"+name).getString().trim().equals("G")) out.print("Guardian");
							else if (rtTit.getField("E06TY"+name).getString().trim().equals("M")) out.print("Menor");
							else if (rtTit.getField("E06TY"+name).getString().trim().equals("N")) out.print("Agente");
							else if (rtTit.getField("E06TY"+name).getString().trim().equals("S")) out.print("Personal Rep.");
							else if (rtTit.getField("E06TY"+name).getString().trim().equals("T")) out.print("Principal");
							else if (rtTit.getField("E06TY"+name).getString().trim().equals("U")) out.print("Ejecutor");
							else if (rtTit.getField("E06TY"+name).getString().trim().equals("X")) out.print("Ejecutante");
							else if (rtTit.getField("E06TY"+name).getString().trim().equals("V")) out.print("Conservador");
							else if (rtTit.getField("E06TY"+name).getString().trim().equals("A")) out.print("AND");
							else if (rtTit.getField("E06TY"+name).getString().trim().equals("I")) out.print("ITF");
							else if (rtTit.getField("E06TY"+name).getString().trim().equals("L")) out.print("POA");
							else if (rtTit.getField("E06TY"+name).getString().trim().equals("O")) out.print("OR");
							else if (rtTit.getField("E06TY"+name).getString().trim().equals("1")) out.print("Trustee");
							else if (rtTit.getField("E06TY"+name).getString().trim().equals("2")) out.print("F/B/O");
							else if (rtTit.getField("E06TY"+name).getString().trim().equals("P")) out.print("POD");
							else if (rtTit.getField("E06TY"+name).getString().trim().equals("M")) out.print("IRA");
							else if (rtTit.getField("E06TY"+name).getString().trim().equals("M")) out.print("WARD"); %>" 
				readonly>
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
  <p align="center">&nbsp; </p>
</form>
</body>
</html>
