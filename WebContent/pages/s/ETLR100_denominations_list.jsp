<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title>Denominaciones de Moneda</title>
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

function setInfo(ccy, dsc, nme, val, paq, typ) {
    document.forms[0].CCY.value = ccy;
    document.forms[0].DSC.value = dsc;
    document.forms[0].NME.value = nme;
    document.forms[0].VAL.value = val;
    document.forms[0].PAQ.value = paq;
    document.forms[0].TYP.value = typ;
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

<h3 align="center">Denominaciones de Moneda<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="denominations_list.jsp, ETLR100"></h3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSETLR100">

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="">
  <INPUT TYPE=HIDDEN NAME="CCY" VALUE="">
  <INPUT TYPE=HIDDEN NAME="DSC" VALUE="">
  <INPUT TYPE=HIDDEN NAME="NME" VALUE="">
  <INPUT TYPE=HIDDEN NAME="VAL" VALUE="">
  <INPUT TYPE=HIDDEN NAME="PAQ" VALUE="">
  <INPUT TYPE=HIDDEN NAME="TYP" VALUE="">
  <INPUT TYPE=HIDDEN NAME="actRow" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
  
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right"><b>Moneda :</b></div>
            </td>
            <td nowrap width="75%" > 
              <div align="left"> 
                <input type="text" name="E01TLDCCY" size="4" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                <input type="text" name="E01RATDSC" size="40" maxlength="35" value="<%= userPO.getHeader1().trim()%>" readonly>
			</div>
            </td>
            
          </tr>
        </table>
      </td>
    </tr>
  </table>
         
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
   		<a href="javascript:goAction('1')">Nuevo</a>
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
  		<a href="javascript:goAction('1')">Nuevo</a>
      </TD>
      <TD ALIGN=CENTER class="TDBKG" width="33%">
  		<a href="javascript:goAction('2')">Mantenimiento</a>
      </TD>      
      <TD ALIGN=CENTER class="TDBKG" width="34%">
  		<a href="<%=request.getContextPath()%>/pages/background.jsp">Salir</a>
      </TD>
    </TR>
  </TABLE>
  <TABLE class="tableinfo" id="dataTable">
    <TR id="trdark">
      <TH ALIGN=CENTER  nowrap width="5%"></TH> 
      <TH ALIGN=CENTER  nowrap >Nombre</TH>
      <TH ALIGN=CENTER  nowrap >Valor</TH>
      <TH ALIGN=CENTER  nowrap >Paquete</TH>
      <TH ALIGN=CENTER  nowrap >Tipo</TH>      
    </TR>
    
    <%
          boolean firstTime = true;  
          String chk = "";  
          ppList.initRow(); 
          int k=1;              
          while (ppList.getNextRow()) {
               ETLR10001Message msgList = (ETLR10001Message) ppList.getRecord();		
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
        		onClick="setInfo('<%= msgList.getE01TLDCCY()%>', '<%= msgList.getE01RATDSC()%>', '<%= msgList.getE01TLDNME()%>', '<%= msgList.getE01TLDVLU()%>', '<%= msgList.getE01TLDPCK()%>', '<%= msgList.getE01TLDTYP()%>')" >
      	  </td>  
		  <td NOWRAP >
			<%=Util.formatCell(msgList.getE01TLDNME())%>
		  </td>		  
		  <td NOWRAP ALIGN=RIGHT>
          	<%=Util.formatCCY(msgList.getE01TLDVLU())%>
		  </td>
		  <td NOWRAP ALIGN=CENTER>
          	<%=Util.formatCell(msgList.getE01TLDPCK())%>          	
		  </td>
		  <td NOWRAP >
		  	<% if (msgList.getE01TLDTYP().equals("B")) {
		  		out.print("Billete");
		  	   } else if (msgList.getE01TLDTYP().equals("C")) {
		  		out.print("Moneda");
		  	   } else {
		  	   	out.print("");
		  	   }
		  	  %>     	
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
