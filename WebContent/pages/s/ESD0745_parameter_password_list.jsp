<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title>Control Parámetros para Contraseña eIBS</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "ppList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">


function goAction(op) {
    document.forms[0].opt.value = op;
    document.forms[0].submit();
}

function setInfo(dsc, bnk, exp, psl, rpc, let, etm, dpi, chg, sk1, sk2, sk3) {
    document.forms[0].DSC.value = dsc;
    document.forms[0].BNK.value = bnk;
    document.forms[0].EXP.value = exp;
    document.forms[0].PSL.value = psl;
    document.forms[0].RPC.value = rpc;
	document.forms[0].LET.value = let;
	document.forms[0].ETM.value = etm;
	document.forms[0].DPI.value = dpi;
	document.forms[0].CHG.value = chg;
	document.forms[0].SK1.value = sk1;
	document.forms[0].SK2.value = sk2;
	document.forms[0].SK3.value = sk3;
}


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

%>

<h3 align="center">Control Parámetros para Contraseña eIBS<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="parameter_password_list.jsp, ESD0745"></h3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.security.JSESD0745">

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="">
  <INPUT TYPE=HIDDEN NAME="BNK" VALUE="">
  <INPUT TYPE=HIDDEN NAME="DSC" VALUE="">
  <INPUT TYPE=HIDDEN NAME="DSC" VALUE="">
  <INPUT TYPE=HIDDEN NAME="EXP" VALUE="">
  <INPUT TYPE=HIDDEN NAME="PSL" VALUE="">
  <INPUT TYPE=HIDDEN NAME="RPC" VALUE="">
  <INPUT TYPE=HIDDEN NAME="LET" VALUE="">
  <INPUT TYPE=HIDDEN NAME="ETM" VALUE="">
  <INPUT TYPE=HIDDEN NAME="DPI" VALUE="">
  <INPUT TYPE=HIDDEN NAME="CHG" VALUE="">
  <INPUT TYPE=HIDDEN NAME="SK1" VALUE="">
  <INPUT TYPE=HIDDEN NAME="SK2" VALUE="">
  <INPUT TYPE=HIDDEN NAME="SK3" VALUE="">
  <INPUT TYPE=HIDDEN NAME="actRow" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
         
<%
	if ( ppList.getNoResult() ) {
%>
 	<TABLE class="tbenter" width=100% height=30%">
 		<TR>
      <TD>         
      <div align="center"> <h4 style="text-align:center"> No hay Resultados para su criterio de Búsqueda. <br>Click Nuevo para Añadir uno</h4> 
      </div>
      </TD></TR>
   	</TABLE>
   	<TABLE class="tbenter">
    <TR>
      <TD ALIGN=CENTER class="TDBKG" width="50%">
   		<a href="javascript:goAction('N')">Nuevo</a>
      </TD>      
      <TD ALIGN=CENTER class="TDBKG" width="50%">
  		<a href="<%=request.getContextPath()%>/pages/background.jsp">Salir</a>
      </TD>
    </TR>
  </TABLE>
<%
	}
	else {
%>
  
  <TABLE class="tbenter">
    <TR>
      <TD ALIGN=CENTER class="TDBKG" width="33%">
  		<a href="javascript:goAction('N')">Nuevo</a>
      </TD>
      <TD ALIGN=CENTER class="TDBKG" width="33%">
  		<a href="javascript:goAction('M')">Mantenimiento</a>
      </TD>      
      <TD ALIGN=CENTER class="TDBKG" width="34%">
  		<a href="<%=request.getContextPath()%>/pages/background.jsp">Salir</a>
      </TD>
    </TR>
  </TABLE>
  <TABLE class="tableinfo" id="dataTable">
    <TR id="trdark">
      <TH ALIGN=CENTER  nowrap width="1%"></TH> 
      <TH ALIGN=CENTER  nowrap >Banco</TH>
      <TH ALIGN=CENTER  nowrap >Descripcion</TH>
      <TH ALIGN=CENTER  nowrap >No. Puerto<br>Inicial eIBS</TH>
      <TH ALIGN=CENTER  nowrap >No. Puerto<br>Monitor para dIBS</TH>
      <TH ALIGN=CENTER  nowrap >No. Puerto<br>Inicial dIBS</TH>      
    </TR>
    
    <%
          boolean firstTime = true;  
          String chk = "";  
          ppList.initRow(); 
          int k=1;              
          while (ppList.getNextRow()) {
               ESD074501Message msgList = (ESD074501Message) ppList.getRecord();		
               if (firstTime) {
	               	chk = "checked";
	               	firstTime = false;
               } else {
               		chk = "";
                }	 
       %>             
        <TR>
          <td align="center" nowrap > 
        	<input type="radio" name="ROW1" <%= chk %> value="<%= ppList.getCurrentRow() %>" 
        		onClick="setInfo('<%= msgList.getE01EPRDSC()%>', '<%= msgList.getE01EPRBNK()%>', '<%= msgList.getE01EPREXP()%>', 
        						 '<%= msgList.getE01EPRPSL()%>', '<%= msgList.getE01EPRRPC()%>', '<%= msgList.getE01EPRLET()%>',
        						 '<%= msgList.getE01EPRETM()%>', '<%= msgList.getE01EPRDPI()%>', '<%= msgList.getE01EPRCHG()%>',
        						 '<%= msgList.getE01EPRSK1()%>', '<%= msgList.getE01EPRSK2()%>', '<%= msgList.getE01EPRSK3()%>')" >
      	  </td>
          <td NOWRAP align=center>
          	<%=Util.formatCell(msgList.getE01EPRBNK())%>
		  </td>		  
		  <td NOWRAP >
			<%=Util.formatCell(msgList.getE01EPRDSC())%>
		  </td>		  
		  <td NOWRAP >
          	<%=Util.formatCell(msgList.getE01EPRSK1())%>
		  </td>
		  <td NOWRAP >
          	<%=Util.formatCell(msgList.getE01EPRSK2())%>          	
		  </td>
		  <td NOWRAP >
          	<%=Util.formatCell(msgList.getE01EPRSK3())%>          	
		  </td>
		  
		</TR>
		
        <% 
        	k++;
         }
         %> 
  </TABLE>
  <SCRIPT language="JavaScript">     
  	document.forms[0].totalRow.value="<%= k %>";
	 showChecked("ROW1");          
  </SCRIPT> 
<%      
  }
%> 
</form>
</body>
</html>
