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
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<SCRIPT Language="Javascript">
	
	builtHPopUp();
	builtNewMenu(pc_option);
		initMenu();
	
function goAction(op) {	
 document.forms[0].opt.value = op;	

 switch (op) {
  
 case  1: 
	document.forms[0].SCREEN.value="301"; 
	document.forms[0].submit();		  	
	break;
	
 case  5: 
	document.forms[0].SCREEN.value="500"; 
	document.forms[0].submit();
	break;
		
 case  6: 
	document.forms[0].SCREEN.value="300"; 
	document.forms[0].submit();		  	
	break;
	
 case  7: 
     //forzar, solo se acciona si usuario esta suspendido
    if (document.forms[0].SUSP.value =="*") {
		        	document.forms[0].SCREEN.value="500"; 
					document.forms[0].submit();
    } else{	if (document.forms[0].LAN.value == 'S') {
						alert("9106A Propuesta no esta asignada a usuario suspendido");
			} else {
						alert("9106A Proposal is not asigned to susspend user");
			};
	};
	break;
			
 case  8: 
	 //SOLO SI LA PROPUESTA PERMITE RENOVACION X			
	 if (document.forms[0].REN.value =="1") {; 
				document.forms[0].SCREEN.value="300"; 
				document.forms[0].submit();
	 }else{
		if (document.forms[0].LAN.value == 'S') {
					alert("9106B Esta propuesta no permite ser renovada. Revise parametrizacion Producto-Ruta");
		} else {
					alert("9106B This proposal does not permit to be renewed. Revise Product-Route parameters");
		};
	  };
	  break;
	  
 case  9: 	
	document.forms[0].SCREEN.value="500"; 
	document.forms[0].submit();
	break;
  };		        
} //End Funcion	


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
var customer = document.forms[0].CUN.value ;    
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0110?SCREEN=1&customer="+customer;
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
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0730?SCREEN=200&OPT=5"+"&E01IFMCUN="+ customer;
    	CenterNamedWindow(page,'Information',650,500,2);

}


function callSegP() {
var prop = document.forms[0].PROP.value
var E01YYYFIL = document.forms[0].E01YYYFIL.value
page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0750?SCREEN=300&PROP="+ prop;
    	CenterNamedWindow(page,'Information',700,500,2);
}

function callPayS() {
var prop = document.forms[0].PROP.value
var customer = document.forms[0].CUN.value;    
var cunam = document.forms[0].CUNAM.value;    
var prod = document.forms[0].PROD.value;    
page = "<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0160?SCREEN=8&Type=P&PROP="+prop
    														+"&CUN="
        													+ customer
        													+"&NAM="
        													+ cunam
        													+"&PROD="
        													+ prod;
    	CenterNamedWindow(page,'Information',700,500,2);
}


  "<A HREF= >Plan de Pagos</A><BR>" + 


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


function GetCpForm() 
{
	var prop = document.forms[0].E01PLPNPR.value
	page = webapp + "/servlet/datapro.eibs.reports.JSEFRM000?SCREEN=7&E01PLTNPR=" + prop;
	CenterWindow(page,700,500,2);
}

function callDebtOthers() {
var customer = document.forms[0].CUN.value;    
var cunam = document.forms[0].CUNAM.value;    
var PROP = document.forms[0].PROP.value;
//var option = document.forms[0].OPTION.value
var option = '5';
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0800?SCREEN=200&NPR="+ PROP
    														+"&E01DUECUN="
        													+ customer
        													+"&E01DUENA1="
        													+ cunam
        													+"&OPTION="
        													+ option;
    	CenterNamedWindow(page,'Information',1000,500,2);
}

function callCreditScor() {

var customer = document.forms[0].CUN.value;    
var cunam = document.forms[0].CUNAM.value;    
var PROP = document.forms[0].PROP.value
var option = document.forms[0].OPTION.value
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0883?SCREEN=100&NPR="+ PROP
    														+"&CUN="
        													+ customer
        													+"&CUNAM="
        													+ cunam
        													+"&OPTION="
        													+ option;
    	CenterNamedWindow(page,'Information',700,600,2);
}

function callAmountCreditScor() {
var customer = document.forms[0].CUN.value;    
var cunam = document.forms[0].CUNAM.value;    
var PROP = document.forms[0].PROP.value
var option = document.forms[0].OPTION.value
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0882?SCREEN=100&NPR="+ PROP
    														+"&CUN="
        													+ customer
        													+"&CUNAM="
        													+ cunam
        													+"&OPTION="
        													+ option;
    	CenterNamedWindow(page,'Information',700,600,2);
}

function callSummaryCreditScor() {
var customer = document.forms[0].CUN.value;    
var cunam = document.forms[0].CUNAM.value;    
var PROP = document.forms[0].PROP.value
var option = document.forms[0].OPTION.value
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0886?SCREEN=100&NPR="+ PROP
    														+"&CUN="
        													+ customer
        													+"&CUNAM="
        													+ cunam
        													+"&OPTION="
        													+ option;
    	CenterNamedWindow(page,'Information',700,600,2);
}

function callWarrGen() {
var customer = document.forms[0].CUN.value;    
var cunam = document.forms[0].CUNAM.value;    
var PROP = document.forms[0].PROP.value
var option = document.forms[0].OPTION.value
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0901?SCREEN=100&NPR="+ PROP
    														+"&CUN="
        													+ customer
        													+"&CUNAM="
        													+ cunam
        													+"&OPTION="
        													+ option
        													+"&TYPE=1";
    	CenterNamedWindow(page,'Information',700,600,2);
}

function callWarrEsp() {
var customer = document.forms[0].CUN.value;    
var cunam = document.forms[0].CUNAM.value;    
var PROP = document.forms[0].PROP.value
var option = document.forms[0].OPTION.value
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0901?SCREEN=100&NPR="+ PROP
    														+"&CUN="
        													+ customer
        													+"&CUNAM="
        													+ cunam
        													+"&OPTION="
        													+ option
        													+"&TYPE=2";
    	CenterNamedWindow(page,'Information',700,600,2);
}

function callRiskRecom(tiprec) {
var customer = document.forms[0].CUN.value;    
var cunam = document.forms[0].CUNAM.value;    
var PROP = document.forms[0].PROP.value
var option = document.forms[0].OPTION.value
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0724?SCREEN=100&NPR="+ PROP
    														+"&CUN="
        													+ customer
        													+"&CUNAM="
        													+ cunam
        													+"&OPTION="
        													+ option
        													+"&TYPE=2"
        													+"&opt="
        													+ tiprec;
    	CenterNamedWindow(page,'Information',700,600,2);
}

function callCualAnalysis() {
var customer = document.forms[0].CUN.value;    
var cunam = document.forms[0].CUNAM.value;    
var PROP = document.forms[0].PROP.value
var option = document.forms[0].OPTION.value
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0903?SCREEN=100&NPR="+ PROP
    														+"&CUN="
        													+ customer
        													+"&CUNAM="
        													+ cunam
        													+"&OPTION="
        													+ option
        													+"&TYPE=2";
    	CenterNamedWindow(page,'Information',700,600,2);
}


function callAccumulates(tiprec) {
var customer = document.forms[0].CUN.value;    
var cunam = document.forms[0].CUNAM.value;    
var PROP = document.forms[0].PROP.value
var option = document.forms[0].OPTION.value
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0905?SCREEN=100&NPR="+ PROP
    														+"&CUN="
        													+ customer
        													+"&CUNAM="
        													+ cunam
        													+"&OPTION="
        													+ option
        													+"&TYPE="
        													+ tiprec;
    	CenterNamedWindow(page,'Information',1000,500,2);
}

function callPosCons() {
var customer = document.forms[0].CUN.value;    
var cunam = document.forms[0].CUNAM.value;    
var PROP = document.forms[0].PROP.value
var option = document.forms[0].OPTION.value
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0906?SCREEN=100&NPR="+ PROP
    														+"&CUN="
        													+ customer
        													+"&CUNAM="
        													+ cunam
        													+"&OPTION="
        													+ option;
    	CenterNamedWindow(page,'Information',1000,500,2);
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
<h3 align="center">Lista de Propuestas <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="proposals_list.jsp, EDP0720"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0720" >
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
    <input type=HIDDEN name="E01YYYFIL" value="<%= userPO.getHeader14()%>"> 
    <input type=HIDDEN name="OPTION" value="<%= userPO.getOption()%>"> 
    <input type=HIDDEN name="Type" value="I"> 
    
    <input type=HIDDEN name="E01PLPBNK" value="<%= userPO.getBank()%>"> 
    <input type=HIDDEN name="E01PLPBRN" value="<%= userPO.getBranch()%>"> 
    <input type=HIDDEN name="E01FILBRN" value="<%= userPO.getBranch()%>"> 
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
            
			<% if(userPO.getOption().equals("1")) { %>
              <td class=TDBKG width="30%"> 
                <div align="center">
                <a href="javascript:goAction(1)"><b>Apertura</b></a>
                </div>
              </td>
			<%};%>
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
		<% if(userPO.getOption().equals("1")) { %>
      	<td class=TDBKG width="16%"> 
        	<div align="center">
        		<a href="javascript:goAction(1)"><b>Apertura</b></a>
        	</div>
     	</td>
		<%};%>
		
		<%-- 
		<% if(userPO.getOption().equals("3")) { %>
		<td class=TDBKG width="16%">
        	<div align="center">
        		<a href="javascript:goAction(8)"><b>Renovación</b></a>
        	</div> 
     	</td> 
		<%};%>
     	--%>
     
		<td class=TDBKG width="16%">
        	<div align="center">
        		<a href="javascript:goAction(5)"><b>Consulta</b></a>
        	</div> 
     	</td>
     			
     	<% if ((userPO.getOption().equals("1")) || (userPO.getOption().equals("2"))) { %>		
		<td class=TDBKG width="16%">
        	<div align="center">
        		<a href="javascript:goAction(9)"><b>Pr&oacute;xima Actividad</b></a>
        	</div> 
     	</td>
     	<%};%>

        <%-- 
		<% if(userPO.getOption().equals("3")) { %>
		<td class=TDBKG width="16%">
        	<div align="center">
        		<a href="javascript:goAction(6)"><b>Otras</b></a>
        	</div> 
     	</td> 
		<%};%>
        --%>

		<%-- <% if ((userPO.getOption().equals("1")) || (userPO.getOption().equals("2"))) { %> --%>
<%--	
		<% if (userPO.getOption().equals("1")) { %>
		<td class=TDBKG width="16%">
        	<div align="center">
        		<a href="javascript:goAction(7)"><b>Forzar</b></a>
        	</div> 
     	</td> 
		<%};%>
--%>      
     	<td class=TDBKG width="16%"> 
        	<div align="center">
        		<a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a>
      	</div>
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
         
            <%--
         	<th align=CENTER nowrap width="1%"> 
              <div align="center">TP</div>
            </th>
             --%>
             
            <th align=CENTER nowrap width="20%"> 
              <div align="center">Producto/Operacion</div>
            </th>
                                 
           <th align=CENTER nowrap width="10%"> 
              <div align="center">Monto Solicitado/<BR>Aprobado</div>
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
            <td NOWRAP  align=CENTER width="3%"> 
              <input type="radio" name="CURRCODE" 
              value="<%= EDP072001Help.getCurrentRow() %>" 
              		 <%=chk%> onClick="getParams(this.value, 
              		   '<%= msgList.getE01PLPNPR() %>',
              		   '<%= msgList.getE01PLPCUN() %>',
              		   '<%= msgList.getE01CUSNA1() %>',
              		   '<%= msgList.getE01YYYFIL() %>');">
         </td>
        
        	<td NOWRAP  align=CENTER width=\"25%\"><%= msgList.getE01PLPCUN()%>-<%= msgList.getE01CUSNA1()%></td>
        
        	<%--
            <td NOWRAP  align=CENTER width=\"25%\"><%= msgList.getE01PLPPTY()%></td>
            --%>
        
        	<td NOWRAP  align=CENTER width=\"25%\"><%= msgList.getE01PLTPR0()%>-<%= msgList.getE01PLTDE0()%></td>
        
        	<td NOWRAP  align=LEFT width=\"20%\"><%= msgList.getE01PLTAMN()%><BR> <%= msgList.getE01PLPAM2()%></td>
        
        	<td NOWRAP  align=CENTER width=\"5%\"><%= msgList.getE01XXXEST()%></td>
        	
        	<td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01PLPNPR()%><BR>
			<%= msgList.getE01PLTREF()%></td>
		
			<td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01PLPIPD()%>/<%= msgList.getE01PLPIPM()%>/<%= msgList.getE01PLPPLP()%>-<%= msgList.getE01PLPHMS().substring(8,10)%>/<%= msgList.getE01PLPHMS().substring(5,7)%>/<%= msgList.getE01PLPHMS().substring(2,4)%> </td>

        	<td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01PLPUDI()%></td>
			
			<td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01PLPEJE()%><BR>
			<%= msgList.getE01EUPIDN()%></td>
			
        	        	
            <td NOWRAP  align=CENTER width=\"25%\"><%= msgList.getE01PLPSRU()%>-<%= msgList.getE01DESAC1()%><BR>
            <%= msgList.getE01YYYEJE()%><%= msgList.getE01PLPPSR()%>-<%= msgList.getE01DESAC2()%></td>
              
            <td NOWRAP  align=CENTER width=\"5%\"><%= msgList.getE01PLPRUT()%></td>
       		
			
         </tr>
          <%
              				chk = "";     
                }
              %>
        </table>
  </table>
  <BR>
  <TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
  <%
        if ( EDP072001Help.getShowPrev() ) {
		int pos = EDP072001Help.getFirstRec() - 21;
   			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.creditproposal.JSEDP0720?SCREEN=200&Pos=" + pos +"\"><img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
  %>
  </TD>
  <TD WIDTH="50%" ALIGN=RIGHT>
  <%
        if ( EDP072001Help.getShowNext() ) {
		int pos = EDP072001Help.getLastRec();
   			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.creditproposal.JSEDP0720?SCREEN=200&Pos=" + pos +"\"><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");
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
