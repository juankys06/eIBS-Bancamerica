<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1"> 

<META name="GENERATOR" content="IBM WebSphere Studio">
<TITLE>Linea de Credito</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<jsp:useBean id= "prodList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT LANGUAGE="JavaScript">

function setItemInfo(code,idx){  
  for ( var i=0; i<dataTable1.rows.length; i++ ) {
       dataTable1.rows[i].className="trnormal";
    }
   dataTable1.rows[idx].className="trhighlight";
   document.forms[0].PRODCOD.value = code; 
}

</SCRIPT>
</HEAD>
<body> 

<h3 align="center">Línea de Crédito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ga_enter_new.jsp,ERA0010"></h3>
<hr>
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0430" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="9">
  <INPUT TYPE=HIDDEN NAME="PRODCOD" VALUE="">
  
  <% if (prodList.getNoResult()) { %>
   <TABLE class="tbenter" height="60%">
   <TR>
      <TD ALIGN=CENTER VALIGN=MIDDLE>
  		 <H4 style="text-align: center">No existe ningun Producto creado para asociarlo a la cuenta <%= userPO.getIdentifier()%>. <br> Consulte a su Administrador de Sistemas</h4>
      </TD>
    </TR>
   </TABLE>
  <% } else {%>
  
 <H4 style="text-align: center">
  Seleccione un Producto para asociarlo a la cuenta <%= userPO.getIdentifier()%>.
 </h4>
          
 <TABLE  id="mainTable" class="tableinfo">
 <TR > 
  <TD NOWRAP valign="top" width="100%" >
      
  <TABLE id="headTable1" >
  	<TR id="trdark"> 
      <TH ALIGN=CENTER NOWRAP></TH>
      <TH ALIGN=CENTER NOWRAP>Código</TH>
	  <TH ALIGN=CENTER NOWRAP>Descripción</TH>
    </TR>
   </TABLE>
  
   <div id="dataDiv1" class="scbarcolor">                 
    <table id="dataTable1">
    <%
                int j=0;
                String itemChk="";
                boolean firstTime=true;
                while (prodList.getNextRow()) {
                    itemChk=(firstTime) ? "checked":"";
                    firstTime=false;
    %>
                <TR> 
      			    <TD ALIGN=Left NOWRAP>
                	 <input type="radio" name="PRODITEM" value="<%= prodList.getRecord(0) %>" <%=itemChk%> onClick="setItemInfo(this.value,<%= j %>)">                
			  	    </TD>
      				<TD ALIGN=LEFT NOWRAP><div nowrap><a href="javascript:radioClick('PRODITEM',<%= j %>)"><%= prodList.getRecord(0) %></a></DIV></TD>
      				<TD ALIGN=LEFT NOWRAP><div nowrap><a href="javascript:radioClick('PRODITEM',<%= j %>)"><%= prodList.getRecord(1) %></a></DIV></TD>      
      			</TR>  
    <%                 
                     j++;       
                }
    %> 
   </table>
   </div>
   </TD>
   
  </TR>	
</TABLE>

  <p align="center">
          <div align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </div>
  </p>

<SCRIPT language="JavaScript">
     function resizeDoc() {
       adjustEquTables(headTable1, dataTable1, dataDiv1,1,false);
      }
	 showChecked("PRODITEM");
     resizeDoc();
     window.onresize=resizeDoc;
     
</SCRIPT> 
<% } %> 
 </FORM>
</BODY>
</HTML>
 