<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import ="datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Lista de Gestiones de Cobros
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "mtList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<SCRIPT language="javascript" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1">

function ShowRow(value){
  var addinf = "ROW" + value;
  var basinf = "BASINF" + value;
  var oldr = document.forms[0].OLDROW.value;	
  document.getElementById("AdicInfoC").innerHTML= document.getElementById(addinf).value;	
  document.getElementById(basinf).style.color = "red";
  if(oldr !="")  document.getElementById(oldr).style.color = "";		  
  document.forms[0].OLDROW.value = basinf;
}
   
</script>
</HEAD>

<BODY>


<h3 align="center">Gestion de Cobranzas
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="gestion_list.jsp,ECB0040">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/pages/s/ECB0040_gestion_enter_inq.jsp" >
<INPUT type=hidden name="OLDROW" value="">
<%
	if ( mtList.getNoResult() ) {
%> 
  <TABLE class="tbenter" width=100% height=40%>
   	<TR>
      <TD> 
        <div align="center"> 
          <p>
            <b>No existen registros por gestiones de cobranzas.</b>
          </p>          
        </div>
      </TD>
     </TR>
   </TABLE>
<%   		
   }else { 
	
%>  
 <TABLE  id="mainTable" class="tableinfo" >
  <TR> 
    <TD NOWRAP valign=top width="60%">
  	 <TABLE id="dataTable" width="100%">
  		<TR id="trdark"> 
  			<TH ALIGN=CENTER ></TH>
  			<TH ALIGN=CENTER >Cuenta</TH>
  			<TH ALIGN=CENTER >Fecha</TH>
    		<TH ALIGN=CENTER >Hora</TH>
    		<TH ALIGN=CENTER >Usuario</TH>
    		<TH ALIGN=CENTER >Cobrador</TH>  
  		</TR>
     <%
        mtList.initRow();            
        while (mtList.getNextRow()) {
           datapro.eibs.beans.ECB004001Message msgMT = (datapro.eibs.beans.ECB004001Message) mtList.getRecord();
     %>               
        <TR id="BASINF<%= mtList.getCurrentRow() %>">
			<TD NOWRAP width="2%"><input type="radio" name="ROW" onClick="ShowRow('<%= mtList.getCurrentRow() %>')"></TD>
			<TD NOWRAP ALIGN="CENTER"><%=msgMT.getE01GCMACC()%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatDate(msgMT.getE01GCMFGD(),msgMT.getE01GCMFGM(),msgMT.getE01GCMFGY())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatTime(msgMT.getE01GCMTIM())%></TD>			
			<TD NOWRAP ALIGN="LEFT"><%=Util.formatCell(msgMT.getE01GCMUSR())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgMT.getE01GCMCOD() + "-" + msgMT.getE01GCMCNAM())%>
 			   <INPUT TYPE=HIDDEN ID="ROW<%= mtList.getCurrentRow() %>" VALUE="<%= msgMT.getE01GCMMA0().trim() + "<br>" + msgMT.getE01GCMMA1().trim() + "<br>" + msgMT.getE01GCMMA2().trim() + "<br>" + msgMT.getE01GCMMA3().trim() + "<br>" + msgMT.getE01GCMMA4().trim() + "<br>" + msgMT.getE01GCMMA5().trim() + "<br>" + msgMT.getE01GCMMA6().trim() + "<br>" + msgMT.getE01GCMMA7().trim() + "<br>" + msgMT.getE01GCMMA8().trim() + "<br>" + msgMT.getE01GCMMA9().trim() %>">
 			   </TD>
		</TR>    		
    <%              
        }
    %>    
     </TABLE>
    </TD>
    <TD width="40%" valign="top">
	   <TABLE id="AdicInfo" width="100%" >
	     <TR id="trdark">   <TH   ALIGN=CENTER >Descripción</TH> </TR>
         <TR>
           <TD id="AdicInfoC"></TD>
         </TR>
       </TABLE>					    
    </TD>
   </TR>
   
</TABLE>

<%   		
	} 
 %>
<p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Cancelar">
  </div>
</p>
</FORM>

</BODY>
</HTML>
