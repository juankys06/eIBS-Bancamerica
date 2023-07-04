<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
 
<%@ page import ="datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>Lista de Saldos Papel Valor por Oficina y Lote</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "mtListBranch"	class= "datapro.eibs.beans.JBObjList"  		scope="session" />
<jsp:useBean id= "mtListLot" 	class= "datapro.eibs.beans.JBObjList"  		scope="session" />
<jsp:useBean id= "error" 		class= "datapro.eibs.beans.ELEERRMessage"  	scope="session" />
<jsp:useBean id= "userPO" 		class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<SCRIPT language="javascript" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT language="javascript">
function goAction() {
    document.forms[0].SCREEN.value = 400;	
    document.forms[0].submit();	
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

<h3 align="center">Lista de Saldos de Papel Valor por Oficina y Lote
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="value_paper_brlot_bal_list.jsp,EVL0050">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.valuepaper.JSEVL0050" >
<input type=hidden name="SCREEN" value="200">  

<%if ( mtListLot.getNoResult() ) {%> 
  <TABLE class="tbenter" width=100% height=40%>
   	<TR>
      <TD> 
        <div align="center"> 
          <p>
            <b>No hay resultados que correspondan a su criterio de búsqueda</b>
          </p>          
        </div>
      </TD>
     </TR>
   </TABLE>
<%} else {%>
   <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
		<tr>
			<td nowrap width="40%">
				<div align="right">Tipo Papel Valor:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E03BALTIP" size="4" maxlength="4" value="<%= userPO.getHeader20() %>" readonly> 
				<input type="text" name="E03BALTIN" size="35" maxlength="35" value="<%= userPO.getHeader22() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Subtipo Papel Valor:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E03BALSUB" size="4" maxlength="4" value="<%= userPO.getHeader21() %>" readonly> 
				<input type="text" name="E03BALSUN" size="35" maxlength="35" value="<%= userPO.getHeader23() %>" readonly> 
			</td>
		</tr>
     </table>
    </td>
   </tr>
  </table>
  <br> 
 <TABLE class="tbenter" width="60%" align=center>
    <TR> 
      <TD class=TDBKG width="50%"> 
        <div align="center"><a href="javascript:goAction()"><b>Consulta</b></a> 
        </div>
      </TD>
      <TD class=TDBKG width="50%"> 
        <div align="center"><a href="javascript:checkClose()">Salir</a> </div>
      </TD>
    </TR>
 </TABLE>
  
 <TABLE  id="mainTable" class="tableinfo" >
  <TR> 
    <TD NOWRAP valign=top>
  	 <TABLE id="dataTable" width="100%">
  		<TR id="trdark"> 
    		<TH ALIGN=CENTER ></TH>
    		<TH ALIGN=CENTER >Banco</TH>
    		<TH ALIGN=CENTER >Oficina</TH>
    		<TH ALIGN=CENTER >Serial</TH>
    		<TH ALIGN=CENTER >Numero<BR>Inicial</TH>
    		<TH ALIGN=CENTER >Numero<BR>Final</TH>
    		<TH ALIGN=CENTER >Saldo</TH>
  		</TR>
     	<%
        	mtListLot.initRow(); 
			boolean firstTime = true;
			String chk = "";
            while (mtListLot.getNextRow()) {
				if (firstTime) {
					firstTime = false;
					chk = "checked";
				} else {
					chk = "";
				}
           datapro.eibs.beans.EVL005003Message msgMT = (datapro.eibs.beans.EVL005003Message) mtListLot.getRecord();
     	%>               
        <TR>
            <TD NOWRAP  align=CENTER width="10%"> 
              <input type="radio" name="CURRCODE" value="<%= mtListLot.getCurrentRow() %>" <%=chk%>>
            </TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE03BALBNK())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE03BALBRN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE03BALSER())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE03BALINI())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE03BALFIN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE03BALBAL())%></TD>
		</TR>    		
    	<%}%>    
     </TABLE>
    </TD>
   </TR>	
</TABLE>
<SCRIPT language="JavaScript">
  showChecked("ROW");       
</SCRIPT>
<%}%>
<p align="center"> 
  <input id="EIBSBTN" type=submit name="Submit" value="Regresar" >
</p>
</FORM>
</BODY>
</HTML>
