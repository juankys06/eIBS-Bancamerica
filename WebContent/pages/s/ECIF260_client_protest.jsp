<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Promedios de Cuentas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<%@ page import = "datapro.eibs.master.Util,datapro.eibs.beans.*" %>

<jsp:useBean id="listProt" class="datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id="userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id="header" class= "datapro.eibs.beans.ECIF26001Message"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

	function showProtDet(t1,t2){
	    var pg= "<%=request.getContextPath()%>/pages/s/ECIF260_client_protest_det.jsp?TYP1="+t1+"&TYP2="+t2;
		CenterNamedWindow(pg,'protdetail',600,560,2);
	}
    
    builtNewMenu(cr_i_opt);
	initMenu();
	
</SCRIPT>

</head>

<body>
<h3 align="center">Comportamiento<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="averages_rt.jsp,ECIF250"></h3>
<hr size="4">
<form >  
  <INPUT TYPE=HIDDEN NAME="ACCIDX" VALUE="0">
  <table class="tableinfo">
    <tr > 
      <td nowrap > 
        <table cellspacing="0" cellpadding="2" width="100%" class="tbhead" align="center">
          <tr id=trdark> 
            <td nowrap width="9%" align="right"> Cliente : </td>
            <td nowrap width="15%" align="left"> <%= userPO.getCusNum()%> </td>
            <td nowrap width="11%" align="right"> 
              <div align="right">RUT : </div>
            </td>
            <td nowrap width="10%" align="left"> <%= userPO.getID()%> </td>
            <td nowrap width="14%" align="right"> 
              <div align="right">Nombre: </div>
            </td>
            <td nowrap width="41%"align="left"> <%= userPO.getCusName()%> </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <% if (listProt.getNoResult()) {%>
  	  <BR>
      <h3 align=center>No existen protestos para este cliente</h3>
  <% } else { %>
  <h4>Castigos</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap colspan=2>
              <div align="center"><b>SBIF</b></div>
            </td>
            <td nowrap width="50%"> 
              <div align="center"><b>BNS</b></div>              
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap >
              <div align="right"><b>Fecha</b></div>
            </td>
            <td nowrap> 
               <input type="text" name="DATE" size="15" readonly value="<%= header.getE01INQMTH() %>/<%= header.getE01INQYEAR() %>" >				                       
            </td>
            <td nowrap align="center"> 
               <input type="text" name="BNS" size="15" readonly value="<%= header.getE01BNKTCA() %>" >				                       
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap >
              <div align="right"><b>Directa</b></div>
            </td>
            <td nowrap> 
               <input type="text" name="DATE" size="15" readonly value="<%= header.getE01SBITDI() %>" >				                       
            </td>
            <td nowrap> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap >
              <div align="right"><b>Indirecta</b></div>
            </td>
            <td nowrap> 
               <input type="text" name="DATE" size="15" readonly value="<%= header.getE01SBITIN() %>" >				                       
            </td>
            <td nowrap> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Indices de Reciprocidad</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap >
              <div align="right"><b>% Dep./Ventas(Anual)</b></div>
            </td>
            <td nowrap> 
               <input type="text" name="IND1" size="7" readonly value="<%= header.getE01BNKIND1() %>" >				                                   
            </td>          
            <td nowrap >
              <div align="right"><b>% Deuda BNS/Deuda SBIF</b></div>
            </td>
            <td nowrap> 
               <input type="text" name="IND2" size="7" readonly value="<%= header.getE01BNKIND2() %>" >				                                   
            </td>
  		</tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Protestos</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap>
        <table valign=top cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap> 
            <div align="right"><b>Sistema :</b></div>
            </td>
            
            <td nowrap> 
            <div align="right"><a href="javascript:showProtDet('S','V')">Cant. Vigentes</a></div>
            </td>
            <td nowrap> 
            	<input type="text" name="TSVIG" size="7" readonly value="<%= header.getE01SBITVI() %>" >				                                   
            </td>
            <td nowrap> 
            <div align="right"><a href="javascript:showProtDet('S','A')">Cant. Aclarados</a></div>
            </td>
            <td nowrap> 
            	<input type="text" name="TSACL" size="7" readonly value="<%= header.getE01SBITAC() %>" >				                                   
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
            	<div align="right"><b>Internos :</b></div>
            </td>
            <td nowrap> 
            <div align="right"><a href="javascript:showProtDet('B','V')">Cant. Vigentes</a></div>
            </td>
            <td nowrap> 
            	<input type="text" name="TIVIG" size="7" readonly value="<%= header.getE01BNKTVI() %>" >				                                   
            </td>
            <td nowrap> 
            <div align="right"><a href="javascript:showProtDet('B','A')">Cant. Aclarados</a></div>
            </td>
            <td nowrap> 
            	<input type="text" name="TIACL" size="7" readonly value="<%= header.getE01BNKACL() %>" >				                                   
            </td>
          </tr>
        </table>
        <TABLE id="headTable" >
		    <TR id="trdark">  
		      <TH ALIGN=CENTER nowrap>Nro Protesto</TH>
		      <TH ALIGN=CENTER nowrap>Motivo</TH>
		      <TH ALIGN=CENTER nowrap>Fecha Prot.Int</TH>
		      <TH ALIGN=CENTER nowrap>Fecha Aclarac.</TH>
		      <TH ALIGN=CENTER nowrap>Monto</TH>
		    </TR>
		</TABLE>  
   		<div id="dataDiv1" class="scbarcolor" style="padding:0" NOWRAP>
    		<table id="dataTable"  >
               <%             
          		
          		listProt.initRow();
   		  		while (listProt.getNextRow()) {
          			ECIF26001Message protBean = (ECIF26001Message) listProt.getRecord();
          		%>
          		
          			  <tr> 
				          <td nowrap><%= protBean.getE01CHQNUM()%></td>
				          <td nowrap><%= protBean.getE01PROTSN()%></td>
				          <td nowrap><%= Util.formatDate(protBean.getE01PRODTD(),protBean.getE01PRODTM(),protBean.getE01PRODTY())%></td>
				          <td nowrap><%= Util.formatDate(protBean.getE01ACLDTD(),protBean.getE01ACLDTM(),protBean.getE01ACLDTY())%></td>
				     	  <td nowrap><%= Util.formatCCY(protBean.getE01CHQAMT())%></td>
				     </tr>
				 <%
          		   }         
                %>         
		     </table>   
  		</div>	
          		           
            </td>              
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <SCRIPT Language="Javascript">
    function resizeDoc() {
       adjustEquTables(headTable, dataTable, dataDiv1,1,false);
    }
  	resizeDoc();
  	window.onresize=resizeDoc;
  </SCRIPT>
  <% } %>
  </form>
</body>
</html>
