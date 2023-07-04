<%@ page import ="datapro.eibs.master.Util" %>
<html>
<head>
<title>Lista Codigos de Formatos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "EDP072001Help" 	class= "datapro.eibs.beans.JBObjList"  			scope="session" />
<jsp:useBean id= "error" 			class= "datapro.eibs.beans.ELEERRMessage"  		scope="session" />
<jsp:useBean id= "userPO" 			class= "datapro.eibs.beans.UserPos"  			scope="session" />
<jsp:useBean id= "currUser" 		class= "datapro.eibs.beans.ESS0030DSMessage" 	scope="session" />
<jsp:useBean id= "optList746"  class= "datapro.eibs.beans.JBList"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<SCRIPT Language="Javascript">
	
//	builtHPopUp();
//	builtNewMenu(pc_option);
//		initMenu();
	
function goAction(op) {	
 document.forms[0].opt.value = op;	

 switch (op) {
  
 case  2: 

	document.forms[0].SCREEN.value="300"; 
	document.forms[0].submit();		  	
	break;
	
  };
}


</SCRIPT>  



<SCRIPT Language="Javascript">

function callCust() {
var customer = document.forms[0].CUN.value;    
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=600&E01CUN="+customer;
    	CenterNamedWindow(page,'Information',650,500,2);

}


function callForex() {
var customer = document.forms[0].CUN.value;    
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEPR0395?SCREEN=200&E01REQBRN=999&E01REQCD1=0&E01REQCD2=0&E01REQCD3=0&E01REQCH1=0&E01REQCH2=0&E01REQCH3=0&E01REQREF=0&E01REQCUS="+customer;
    	CenterNamedWindow(page,'Information',650,500,2);

}


function callCupo() {
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0110?SCREEN=1";
    	CenterNamedWindow(page,'Information',700,600,2);
}

function callPosition() {
var customer = document.forms[0].CUN.value;    
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF010?SCREEN=5&E03CUSCUN="+customer;
    	CenterNamedWindow(page,'Information',650,500,2);

}

function callGuard() {
var customer = document.forms[0].CUN.value;    
var cunam = document.forms[0].CUNAM.value;    
var PROP = document.forms[0].PROP.value
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0725?SCREEN=200&NPR="+ PROP
    														+"&CUN="
        													+ customer
        													+"&NAM="
        													+ cunam
        													+"&opt=2";
    	CenterNamedWindow(page,'Information',650,500,2);
}


function callTitul() {
var customer = document.forms[0].CUN.value;    
		page = "<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160?SCREEN=4&CUSTOMER="+ customer;
    	CenterNamedWindow(page,'Information',650,500,2);

}

function callInfF() {
var customer = document.forms[0].CUN.value;    
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0730?SCREEN=200&E01IFMCUN="+ customer;
    	CenterNamedWindow(page,'Information',650,500,2);

}


function callSegP() {
var prop = document.forms[0].PROP.value
var E01YYYFIL = document.forms[0].E01YYYFIL.value
page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0750?SCREEN=300&PROP="+ prop;
    	CenterNamedWindow(page,'Information',700,500,2);
}


function callImpP() {
var PROP = document.forms[0].PROP.value
document.forms[0].FMT.value = "L";
    document.forms[0].SCREEN.value="730";
	document.forms[0].submit();		  	       	       
}


function getParams(currrow, PROP, CUN, CUNAM, E01YYYFIL) {
    document.forms[0].CURRENTROW.value = currrow;
    document.forms[0].PROP.value = PROP;   		   
    document.forms[0].CUN.value = CUN;			   
    document.forms[0].CUNAM.value = CUNAM; 		   
    document.forms[0].E01YYYFIL.value = E01YYYFIL; 
 	document.forms[0].CONTI.value = "0";  
    
}

</SCRIPT>  
</head>

<BODY>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
//    out.println("<SCRIPT> initMenu(); </SCRIPT>");

%> 
<h3 align="center">Lista de Propuestas para Asignar Usuario<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="proposals_list.jsp, EDP0738"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0738" >
  <p>  
    <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0"> 
    <input type=HIDDEN name="SCREEN" value="300">
    <input type=HIDDEN name="totalRow" value="0">
    <INPUT TYPE=HIDDEN NAME="LAN" value="S">
    <INPUT TYPE=HIDDEN NAME="MOD" value="">
    <INPUT TYPE=HIDDEN NAME="SEC" value="">
    <INPUT TYPE=HIDDEN NAME="MOD" value="">
    <input type=HIDDEN name="opt">
    <input type=HIDDEN name="PROP"> 
    <input type=HIDDEN name="CUN">      
    <input type=HIDDEN name="CUNAM">  
    <input type=HIDDEN name="FMT"> 
	<input type=HIDDEN name="TASK"> 
    <input type=HIDDEN name="OPECOD">
    <input type=HIDDEN name="CONTI"> 
    <input type=HIDDEN name="TPROPU"> 
    <input type=HIDDEN name="E01YYYFIL"> 
    <input type=HIDDEN name="E01PLPBNK" value="<%= userPO.getBank()%>"> 
    <input type=HIDDEN name="E01PLPBRN" value="<%= userPO.getBranch()%>"> 
    <input type=HIDDEN name="E01PLPRUT" value="<%= userPO.getHeader15()%>"> 
    <input type=HIDDEN name="E01CUSCUN" value="<%= userPO.getCusNum()%>"> 
        
  </p>
  <p> 
    <%
	if ( EDP072001Help.getNoResult() ) {
 %>
  </p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>No hay resultados para su b&uacute;squeda</b></p>
          <table class="tbenter" width=100% align=center>
            <tr> 
            
             <td class=TDBKG width="30%"> 
                <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
              </td>
            </tr>
          </table>
          <p>&nbsp;</p>
          
        </div>

	  </TD>
	</TR>
    </TABLE>
	
  <%  
		}
	else {
%> <% 

String chk = "";

 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 
  <p> 
          <table class="tbenter" width=100% align=center>
            <tr> 
            
             <td class=TDBKG width="30%"> 
                <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
              </td>
            </tr>
          </table>
         
    <table cellspacing="0" cellpadding="3" class="tbenter"  border=0  width=70% align="center" >
      <tr valign="middle"> 
        <td nowrap width=20% align="right" > 
          Usuario/Grupo: 
        </td>
        <td nowrap width=40% align="left">  
            <select name="E01PLPUID">
             	<%
                optList746.initRow();
                int t=1;
                while (optList746.getNextRow()) {
                    if (optList746.getFlag().equals("")) {
                    		out.println(optList746.getRecord());
                    t++;
                    }        
                }                 
    			%> 
            </select>
        </td>
      </tr>

  </table>
   
  <br>
  <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="3%">&nbsp;</th>
         
            <th align=CENTER nowrap width="20%"> 
              <div align="center">Cliente</div>
            </th>
         
            <th align=CENTER nowrap width="20%"> 
              <div align="center">Producto/Operacion</div>
            </th>
                                 
           <th align=CENTER nowrap width="10%"> 
              <div align="center">Mnt. Solic/Amort<BR>Monto Aprob.</div>
           </th>  
         
           <th align=CENTER nowrap width="10%"> 
              <div align="center">Status</div>
           </th>  
         
           <th align=CENTER nowrap width="10%"> 
              <div align="center">Prop.</div>
            </th>
            
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Fecha</div>
            </th>
           <th align=CENTER nowrap width="10%"> 
              <div align="center">Dias Ult.Act.</div>
            </th>
                        
           <th align=CENTER nowrap width="10%"> 
              <div align="center">Ofic.</div>
            </th>
                 
            <th align=CENTER nowrap width="20%"> 
              <div align="center">Sec-Actividad</div>
            </th>     
            
            <th align=CENTER nowrap width="5%"> 
              <div align="center">Usuario</div>
            </th>
            <th align=CENTER nowrap width="5%"> 
              <div align="center">Ruta</div>
            </th>
            
           </tr>
           
          <%
                EDP072001Help.initRow();
                chk = "checked";
                int indice = 0;
                int indice2 =0;
                while (EDP072001Help.getNextRow()) {
                  datapro.eibs.beans.EDP072001Message msgList = (datapro.eibs.beans.EDP072001Message) EDP072001Help.getRecord();
		 %>
		 <% indice  = indice + 1; %>
		 <% indice2 = indice % 2; %>
          <tr id="<% if (indice2==0) {out.print("trdark");} else {out.print("trclear");} %>"> 
            <input type="HIDDEN" name="PLPNPR<%= indice %>" value="<%= msgList.getE01PLPNPR() %>">

            <td NOWRAP  align=CENTER width="3%"> 
              <input type="checkbox" name="ASIGUSR<%= indice %>" >
	         </td>
        
        	<td NOWRAP  align=CENTER width=\"25%\"><%= msgList.getE01PLPCUN()%>-<%= msgList.getE01CUSNA1()%></td>
        
        	<td NOWRAP  align=CENTER width=\"25%\"><%= msgList.getE01PLTPR0()%>-<%= msgList.getE01PLTDE0()%></td>
        
        	<td NOWRAP  align=LEFT width=\"20%\"><%= msgList.getE01PLTAMN()%><BR> <%= msgList.getE01PLTMAP()%></td>
        
        	<td NOWRAP  align=CENTER width=\"5%\"><%= msgList.getE01XXXEST()%></td>
        	
        	<td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01PLPNPR()%><BR>
			<%= msgList.getE01PLTREF()%></td>
		
			<td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01PLPIPD()%>/<%= msgList.getE01PLPIPM()%>/<%= msgList.getE01PLPPLP()%>-<%= msgList.getE01PLPHMS().substring(8,10)%>/<%= msgList.getE01PLPHMS().substring(5,7)%>/<%= msgList.getE01PLPHMS().substring(2,4)%> </td>
        	<td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01PLPUDI()%></td>
			
			<td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01PLPEJE()%><BR>
			<%= msgList.getE01EUPIDN()%></td>
        	        	
            <td NOWRAP  align=CENTER width=\"25%\"><%= msgList.getE01PLPSRU()%>-<%= msgList.getE01DESAC1()%><BR>
            <%= msgList.getE01YYYEJE()%><%= msgList.getE01PLPPSR()%>-<%= msgList.getE01DESAC2()%></td>
              
            <td NOWRAP  align=CENTER width=\"5%\"><%= msgList.getE01PLPUID()%></td>
            <td NOWRAP  align=CENTER width=\"5%\"><%= msgList.getE01PLPRUT()%></td>
         </tr>
          <%
              				chk = "";     
                }
              %>
             <input type="HIDDEN" name="RECNUM" value="<%= indice %>">
              
        </table>
  </table>
  <BR>
  <TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="33%" ALIGN=LEFT>
  <%
        if ( EDP072001Help.getShowPrev() ) {
		int pos = EDP072001Help.getFirstRec() - 21;
   			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.creditproposal.JSEDP0738?SCREEN=200&Pos=" + pos +"\"><img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
  %>
  </TD>
	<td class=TDBKG width="33%">
	<div align="center"><a href="javascript:goAction(2)"><b>Enviar</b></a></div> 
	</td> 

  <TD WIDTH="33%" ALIGN=RIGHT>
  <%
        if ( EDP072001Help.getShowNext() ) {
		int pos = EDP072001Help.getLastRec();
   			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.creditproposal.JSEDP0738?SCREEN=200&Pos=" + pos +"\"><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");
        }
  %> 
 </TD>
 </TR>
 </TABLE>
     
  <SCRIPT language="JavaScript">
 showChecked("CURRCODE");
</SCRIPT>

<%}%>

  </form>

</body>
</html>
