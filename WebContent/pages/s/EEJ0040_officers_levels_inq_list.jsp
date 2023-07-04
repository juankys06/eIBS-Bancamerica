<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
 
<%@ page import ="datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Consulta de Niveles de Ejecutivos
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "msgMTLvl" class= "datapro.eibs.beans.EEJ004001Message"  scope="session" />
<jsp:useBean id= "mtListLvl" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<SCRIPT language="javascript" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<SCRIPT language="javascript">

  function goAction(opt) {
    
    if (opt == "I") { 
		document.forms[0].SCREEN.value = 200;	
    	document.forms[0].submit();
  	} else if (opt == "R") {   
    	document.forms[0].SCREEN.value = 300;	
    	document.forms[0].submit();
  	} 
  }
  
</SCRIPT>
</HEAD>
<BODY>

<% 
if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors();");
     out.println("</SCRIPT>");
} 
%>


<h3 align="center">Consulta de Niveles de Ejecutivos
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="officers_levels_inq_list.jsp,EEJ0040">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEEJ0040" >

 <INPUT TYPE=HIDDEN NAME="ROW" VALUE="0">
 <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="300"> 
 
 <table cellspacing=0 cellpadding=2 width="100%" border="0">
    	<tr> 
	      <td nowrap width="20%"> 
	        <div align="right">Usuario : </div>
	      </td>
	      <td nowrap width="30%"> 
	        <input type="text" name="E01EJVOF1" size="12" maxlength="10" readonly value="<%= msgMTLvl.getE01EJVOF1() %>">
	      </td>
	      <td nowrap width="20%"> 
	        <div align="right">C&oacute;digo : </div>
	      </td>
	      <td nowrap width="30%"> 
	        <input type="text" name="E01EJVOFC size="5" maxlength="4" readonly value="<%= msgMTLvl.getE01EJVOFC() %>">
	      </td>
     	</tr>
     	<tr> 
	      <td nowrap width="20%"> 
	        <div align="right">Nombre : </div>
	      </td>
	      <td nowrap width="30%"> 
	        <input type="text" name="E01EJVOFN" size="50" maxlength="45" readonly value="<%= msgMTLvl.getE01EJVOFN() %>">
	      </td>
	      <td nowrap width="20%"> 
	        <div align="right">R.U.T : </div>
	      </td>
	      <td nowrap width="30%"> 
	        <input type="text" name="E01EJVRUT" size="18" maxlength="15" readonly value="<%= msgMTLvl.getE01EJVRUT() %>">
	      </td>    
     	<tr> 
	      <td nowrap width="20%"> 
	        <div align="right">Banco : </div>
	      </td>
	      <td nowrap width="30%"> 
	        <input type="text" name="E01EJVBNK" size="3" maxlength="2" readonly value="<%= msgMTLvl.getE01EJVBNK() %>">
	        <input type="text" name="E01EJVBNN" size="40" maxlength="35" readonly value="<%= msgMTLvl.getE01EJVBNN() %>">
	      </td>
	      <td nowrap width="20%"> 
	        <div align="right">Oficina : </div>
	      </td>
	      <td nowrap width="30%">
	      	<input type="text" name="E01EJVBRN" size="4" maxlength="3" readonly value="<%= msgMTLvl.getE01EJVBRN() %>" >
	      	<input type="text" name="E01EJVBRM" size="40" maxlength="35" readonly value="<%= msgMTLvl.getE01EJVBRM() %>">
     	</tr>
     	<tr> 
		  <td nowrap width="20%"> 
		     <div align="right">Centro de Costo : </div>        
		  </td>
      	  <td nowrap width="30%"> 
      	    <input type="text" name="E01EJVCCN" size="9" maxlength="8" readonly value="<%= msgMTLvl.getE01EJVCCN().trim()%>">
          </td>     
		  <td nowrap width="20%"> 
		     <div align="right">Ejecutivo Superior : </div>        
		  </td>
      	  <td nowrap width="30%"> 
      	    <input type="text" name="E01EJVSUP" size="5" maxlength="4" readonly value="<%= msgMTLvl.getE01EJVSUP()%>">
      	    <input type="text" name="E01EJVSUN" size="50" maxlength="45" readonly value="<%= msgMTLvl.getE01EJVSUN() %>">
      	  </td>     
      	</tr>
      	<tr> 
	      <td nowrap width="20%"> 
	        <div align="right">E-Mail : </div>
	      </td>
	      <td nowrap width="30%"> 
	        <input type="text" name="E01EJVEML" size="45" maxlength="40" readonly value="<%= msgMTLvl.getE01EJVEML() %>">
	      </td>
	      <td nowrap width="20%"> 
	        <div align="right">Tel&eacute;fono : </div>
	      </td>
	      <td nowrap width="30%"> 
	        <input type="text" name="E01EJVPHN" size="13" maxlength="11" readonly value="<%= msgMTLvl.getE01EJVPHN() %>" >
	        &nbsp;&nbsp;Extensi&oacute;n :
	        <input type="text" name="E01EJVPXT" size="5" maxlength="4" readonly value="<%= msgMTLvl.getE01EJVPXT() %>" > 
	      </td>
     	</tr>
     	<tr> 
	      <td nowrap width="20%"> 
	        <div align="right">Lote Inicial : </div>
	      </td>
	      <td nowrap width="30%"> 
	        <input type="text" name="E01EJVFB1" size="5" maxlength="4" readonly value="<%= msgMTLvl.getE01EJVFB1() %>" >
	        &nbsp;&nbsp;Final :
	        <input type="text" name="E01EJVTB1" size="5" maxlength="4" readonly value="<%= msgMTLvl.getE01EJVTB1() %>" >
	        &nbsp;&nbsp;Omisi&oacute;n :
	        <input type="text" name="E01EJVDIB" size="5" maxlength="4" readonly value="<%= msgMTLvl.getE01EJVDIB() %>" >
	      </td>
	      <td nowrap width="20%"> 
	        <div align="right">C&oacute;digo de Jerarqu&iacute;a :</div>
	      </td>
	      <td nowrap width="30%">
	      	<input type="text" name="E01EJVJRQ" size="5" maxlength="4" readonly value="<%= msgMTLvl.getE01EJVJRQ() %>">
      	    <input type="text" name="E01EJVJRN" size="40" maxlength="35" readonly value="<%= msgMTLvl.getE01EJVJRN() %>" >
      	  </td>
     	</tr>
     </table>
     
<%
	if ( mtListLvl.getNoResult() ) {
%> 

  <TABLE class="tbenter" width="60%" align=center>
    <TR>
      <TD class=TDBKG width="50%"> 
        <div align="center"><a href="javascript:goAction('R')">Regresar</a> 
        </div>
      </TD> 
      <TD class=TDBKG width="50%"> 
        <div align="center"><a href="javascript:checkClose()">Salir</a> </div>
      </TD>
    </TR>
  </TABLE>
  <TABLE class="tbenter" width=100% height=40%>
   	<TR>
      <TD> 
        <div align="center"> 
          <p>
            <b>Ejecutivo sin Dependientes</b>
          </p>          
        </div>
      </TD>
     </TR>
   </TABLE>
  <%   		
	}
	else { 
	
  %>
 
 <TABLE class="tbenter" width="100%" align=center>
    <TR> 
      <TD class=TDBKG width="33%"> 
        <div align="center"><a href="javascript:goAction('I')">Consultar</a> 
        </div>
      </TD>
      <TD class=TDBKG width="33%"> 
        <div align="center"><a href="javascript:goAction('R')">Regresar</a> 
        </div>
      </TD>
      <TD class=TDBKG width="33%"> 
        <div align="center"><a href="javascript:checkClose()">Salir</a> </div>
      </TD>
    </TR>
 </TABLE>
 
 <h4><b>Ejecutivos Dependientes</b></h4>   
  
 <TABLE  id="mainTable" class="tableinfo" >
  <TR> 
    <TD NOWRAP valign=top>
  	 <TABLE id="dataTable" width="100%">
  		<TR id="trdark"> 
  			<TH ALIGN=CENTER ></TH>
    		<TH ALIGN=CENTER >Codigo</TH>
    		<TH ALIGN=CENTER >Nombre</TH>
    		<TH ALIGN=CENTER >Jerarquia</TH>
    		<TH ALIGN=CENTER >Nivel</TH>
    		<TH ALIGN=CENTER >Dependencia</TH>
    		<TH ALIGN=CENTER >Superior</TH>
  		</TR>
     	<%
             mtListLvl.initRow();
             boolean firstTime = true;
			 String chk = "";              
             while (mtListLvl.getNextRow()) {
             if (firstTime) {
				firstTime = false;
				chk = "checked";
			 } else {
				chk = "";
			 }
             datapro.eibs.beans.EEJ004001Message msgMTList = (datapro.eibs.beans.EEJ004001Message) mtListLvl.getRecord();
     	%>               
        <TR>
			<TD NOWRAP width="2%">
				<input type="radio" name="CURRCODE" value="<%= mtListLvl.getCurrentRow()%>" <%=chk%>>
			</TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMTList.getE01EJVOFC())%></TD>
			<TD NOWRAP ALIGN="LEFT"><%=Util.formatCell(msgMTList.getE01EJVOFN())%></TD>
			<TD NOWRAP ALIGN="LEFT"><%=Util.formatCell(msgMTList.getE01EJVJRQ())%>&nbsp;-&nbsp;<%=Util.formatCell(msgMTList.getE01EJVJRN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMTList.getE01EJVLVL())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMTList.getE01EJVDPN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMTList.getE01EJVSUP())%></TD>
		</TR>    		
       <%}%>
     </TABLE>
    </TD>
   </TR>	
</TABLE>

<SCRIPT language="JavaScript">
  showChecked("CURRCODE");       
</SCRIPT>
<%}%>
</FORM>
</BODY>
</HTML>
