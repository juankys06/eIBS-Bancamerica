<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title>Consulta Cta. Credito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "prList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function setInfo(idx){  
  for ( var i=2; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idx].className="trhighlight";
   document.forms[0].actRow.value = idx;    
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

<h3 align="center">Consulta de Ctas. Credito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="pr_deb_list.jsp,EPR2000"></h3>
<hr size="4">

<form >

  <INPUT TYPE=HIDDEN NAME="actRow" VALUE="0">
  
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Cuenta Credito : </b></div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E01PRPCAC" size="17" maxlength="9" value="<%= userPO.getAccNum()%>" readonly>
              </div>
            </td>
            
          </tr>
        </table>
      </td>
    </tr>
  </table>
      
<%
	if ( prList.getNoResult() ) {
%>
 	<TABLE class="tbenter" width=100% height=30%">
 		<TR>
      <TD>         
      <div align="center"> <h4 style="text-align:center"> La cuenta seleccionada no posee cuentas de debito apuntandola.</h4>
      </div>
      </TD></TR>
   	</TABLE>
   	
<%
	}
	else {
%>
  
  <BR>
  <TABLE class="tableinfo" id="dataTable">
    <TR id="trdark">
      <TH ALIGN=CENTER  nowrap width="1%" rowspan=2></TH> 
      <TH ALIGN=CENTER  nowrap rowspan=2>No. Ref.</TH>
      <TH ALIGN=CENTER  nowrap rowspan=2>Cta. Debito</TH>
      <TH ALIGN=CENTER  nowrap rowspan=2>Monto</TH>
      <TH ALIGN=CENTER  nowrap rowspan=2>MDA</TH>
      <TH ALIGN=CENTER  nowrap rowspan=2>Frecuencia</TH>
      <TH ALIGN=CENTER  nowrap colspan=2>Ultimo Pago.</TH>
    </TR>
    <TR id="trdark">
      <TH ALIGN=CENTER  nowrap >Fecha</TH>
      <TH ALIGN=CENTER  nowrap >Monto</TH>
    </TR>
    <%
                
          prList.initRow();               
          while (prList.getNextRow()) {
               EPR200002Message msgList = (EPR200002Message) prList.getRecord();			 
                    
       %>             
                    
          <TR>
          <td align="center" nowrap > 
        	<input type="radio" name="ROW" value="<%= prList.getCurrentRow() %>" onClick="setInfo(<%= prList.getCurrentRow() + 2 %>)" <% if (prList.getCurrentRow() == 0) out.print("checked");%>>
      	  </td>
          <td NOWRAP align=center>
          	<%=Util.formatCell(msgList.getE02PRPNUM())%>
		  </td>
		  <td NOWRAP >
			<%=Util.formatCell(msgList.getE02PRPDAC())%>
		  </td>		  
		  <td NOWRAP >
          	<%=Util.fcolorCCY(msgList.getE02PRPAMT())%>
		  </td>
		  <td NOWRAP >
			<%=Util.formatCell(msgList.getE02PRPTCY())%>
		  </td>
		  <td NOWRAP >
			<% if(msgList.getE02PRPFRQ().equals("D")) out.print("Diario");
                else if(msgList.getE02PRPFRQ().equals("W")) out.print("Semanal");
                else if(msgList.getE02PRPFRQ().equals("M")) out.print("Mensual");
                else if(msgList.getE02PRPFRQ().equals("V")) out.print("Variable");
                else if(msgList.getE02PRPFRQ().equals("2")) out.print("Cada 2 Meses");
                else if(msgList.getE02PRPFRQ().equals("3")) out.print("Cada 3 Meses");%>
		  </td>	
		  <td NOWRAP >
          	<%=Util.formatDate(msgList.getE02PRPLPD(),msgList.getE02PRPLPM(),msgList.getE02PRPLPY())%>
		  </td>
		  <td NOWRAP align=right>
          	<%=Util.fcolorCCY(msgList.getE02PRPLPA())%>
		  </td>
		</TR>
        <%        }
              %> 
  </TABLE>
  <SCRIPT language="JavaScript">     
	 showChecked("ROW");          
  </SCRIPT> 
<%      
  }
%> 
</form>
</body>
</html>
