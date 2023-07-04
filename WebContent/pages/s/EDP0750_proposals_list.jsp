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
		case  5: 
				document.forms[0].SCREEN.value="300"; 
				document.forms[0].submit();
				break;
				//OTROS	
	   case  9: 
	   
	 		    document.forms[0].CONTI.value = "0";	
	 		//
	 		//alert("SEC "+document.forms[0].SEC.value)
	 		//alert("TPPROC "+document.forms[0].TPROC.value)
	 		//alert("TACC "+document.forms[0].TACC.value)
	 		//alert("OPCION"+document.forms[0].OPCION.value)
	 		//alert("UID" + document.forms[0].UID.value)
	 		//alect("SWNODE"+document.forms[0].SWNODE.value)
	 		if (document.forms[0].SEC.value == "Y") {
	 			if (document.forms[0].TPROC.value =="3") {
					if (document.forms[0].LAN.value == 'S') {
						alert("Proceso no puede ser ejecutado por ningun Usuario");
					} else {
						alert("Process cannot be executed for any User");
					}
				} else {
				if (document.forms[0].SUSP.value =="*") {
					if (document.forms[0].LAN.value == 'S') {
						alert("Usuario Suspendido no puede procesar");
					} else {
						alert("User Suspended cannot process");
					}
				} else {
				//
					switch (document.forms[0].TACC.value) { 
					case  "1": 
						//Caso Flujo Ejecutivo
						if ((document.forms[0].OPCION.value) == ("1")){
							if ((document.forms[0].UID.value) != (document.forms[0].USER.value)){
								if (document.forms[0].LAN.value == 'S') {
									alert("Usuario no autorizado para procesar esta propuesta");
								} else {
									alert("User not authorized to process this proposal");
								};
							} else {
								document.forms[0].CONTI.value = "1";	
							};
						} else {
							alert("Su requerimiento no puede ser atendido");
						};
						break;
					case  "2":
						//Caso banca especializada 
						if ((document.forms[0].OPCION.value) == ("1")){
							if ((document.forms[0].UESP.value) != (document.forms[0].PESP.value)){
								if (document.forms[0].LAN.value == 'S') {
									alert("Usuario no autorizado a esta Banca Especializada");
								} else {
									alert("User not authorized to Banking Especialized");
								};
							} else {
								document.forms[0].CONTI.value = "1";	
							};
						} else {
						   alert("Su requerimiento no puede ser atendido");  
						};	
						break;
					case  "3":
						//Caso Post Comite
						if ((document.forms[0].OPCION.value) == ("2")){
							if ((document.forms[0].SWNODE.value) != ("1")){
								if (document.forms[0].LAN.value == 'S') {
									alert("Usuario no autorizado para ejecutar esta opcion");
								} else {
									alert("User not authorized to execute this option");
								}
							} else {
								document.forms[0].CONTI.value = "1";	
							};
						} else {
						   alert("Su requerimiento no puede ser atendido");  
						};		
						break;
					};
				  }; 
				  };
				//
						
				} // no security
				else {
				 document.forms[0].CONTI.value ="1"
				};
				
			    if (document.forms[0].CONTI.value =="1") { 
					if (document.forms[0].TASK.value !="") {
						document.forms[0].SCREEN.value="500"; 
						document.forms[0].submit();		  	
					} else {
						if (document.forms[0].LAN.value == 'S') {
							alert("Error en proceso");
						} else {
							alert("Error in Process");
						};
					};
			    };	
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
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0730?SCREEN=100&CUSTOMER="+ customer;
    	CenterNamedWindow(page,'Information',650,500,2);

}


function callSegP() {
var prop = document.forms[0].PROP.value
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0750?SCREEN=200&RUT="+ prop
    														+"&DSC= ";
    	CenterNamedWindow(page,'Information',700,600,2);
}


function callImpP() {
var PROP = document.forms[0].PROP.value
document.forms[0].FMT.value = "L";
    document.forms[0].SCREEN.value="730";
	document.forms[0].submit();		  	       	       
}


function getParams(currrow, PROP, CUN, CUNAM) {
    document.forms[0].CURRENTROW.value = currrow;
    document.forms[0].PROP.value = PROP;   		   
    document.forms[0].CUN.value = CUN;			   
    document.forms[0].CUNAM.value = CUNAM; 		   
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
<h3 align="center">Lista de Propuestas <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="proposals_list.jsp, EDP0750"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0750" >
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
    <input type=HIDDEN name="FMT"> 
    <input type=HIDDEN name="CUN">      
    <input type=HIDDEN name="PSEC">
    <input type=HIDDEN name="PACT">
	<input type=HIDDEN name="TASK"> 
    <input type=HIDDEN name="PARAM"> 
    <input type=HIDDEN name="OPECOD">
    <input type=HIDDEN name="CUNAM">          
    <input type=HIDDEN name="UID">      
        
    <input type=HIDDEN name="ESTADO"> 
    <input type=HIDDEN name="USER"> 
    <input type=HIDDEN name="TACC"> 
    <input type=HIDDEN name="SUSP"> 
    <input type=HIDDEN name="TPROC"> 
    <input type=HIDDEN name="SWNODE"> 
    <input type=HIDDEN name="PESP"> 
    <input type=HIDDEN name="UESP"> 
    <input type=HIDDEN name="REN">
    <input type=HIDDEN name="OPCION">
    <input type=HIDDEN name="CONTI"> 

    <input type=HIDDEN name="E01PLPBNK" value="<%= userPO.getBank()%>"> 
    <input type=HIDDEN name="E01PLPBRN" value="<%= userPO.getBranch()%>"> 
    <input type=HIDDEN name="E01PLPRUT" value="<%= userPO.getHeader15()%>"> 
    <input type=HIDDEN name="E01CUSCUN" value="<%= userPO.getCusNum()%>"> 

  <input type=HIDDEN name="BNK" value="<%= userPO.getBank()%>"> 
  <input type=HIDDEN name="BRN" value="<%= userPO.getBranch()%>"> 
  <input type=HIDDEN name="RUT" value="<%= userPO.getHeader15()%>"> 
  <input type=HIDDEN name="CUS" value="<%= userPO.getCusNum()%>"> 

        
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
             <td class=TDBKG width="50%"> 
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
     
		<td class=TDBKG width="16%">
        	<div align="center">
        		<a href="javascript:goAction(5)"><b>Consulta</b></a>
        	</div> 
     	</td>
     			
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
            
            <th align=CENTER nowrap width="5%"> 
              <div align="center">Ruta</div>
            </th>
                 
            <th align=CENTER nowrap width="20%"> 
              <div align="center">Sec-Actividad</div>
            </th>     
                        
           <th align=CENTER nowrap width="10%"> 
              <div align="center">Prop.</div>
            </th>
            
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Fecha</div>
            </th>
            
            <th align=CENTER nowrap width="20%"> 
              <div align="center">Cliente</div>
            </th>
                        
           <th align=CENTER nowrap width="10%"> 
              <div align="center">Ofic.</div>
            </th>
            
            <th align=CENTER nowrap width="20%"> 
              <div align="center">Producto/Operacion</div>
            </th>
                          
           <th align=CENTER nowrap width="10%"> 
              <div align="center">Status</div>
           </th>  
                          
           <th align=CENTER nowrap width="10%"> 
              <div align="center">Monto Solic.<BR>Monto Aprob.</div>
           </th>  
            
           </tr>
           
           
          <%
                EDP072001Help.initRow();
                chk = "checked";
                while (EDP072001Help.getNextRow()) {
                  datapro.eibs.beans.EDP072001Message msgList = (datapro.eibs.beans.EDP072001Message) EDP072001Help.getRecord();
		 %>
          <tr> 
            <td NOWRAP  align=CENTER width="3%"> 
              <input type="radio" name="CURRCODE" 
              value="<%= EDP072001Help.getCurrentRow() %>" 
              		 <%=chk%> onClick="getParams(this.value, 
              		   '<%= msgList.getE01PLPNPR() %>',
              		   '<%= msgList.getE01PLPCUN() %>',
               		   '<%= msgList.getE01CUSNA1() %>');">
         </td>
            <td NOWRAP  align=CENTER width=\"5%\"><%= msgList.getE01PLPRUT()%></td>
            <td NOWRAP  align=CENTER width=\"25%\"><%= msgList.getE01PLPSRU()%>-<%= msgList.getE01DESAC1()%><BR>
            <%= msgList.getE01YYYEJE()%><%= msgList.getE01PLPPSR()%>-<%= msgList.getE01DESAC2()%></td>
			<td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01PLPNPR()%><BR>
			<%= msgList.getE01PLTREF()%></td>
			<td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01PLPIPD()%>/<%= msgList.getE01PLPIPM()%>/<%= msgList.getE01PLPPLP()%></td>
			<td NOWRAP  align=CENTER width=\"25%\"><%= msgList.getE01PLPCUN()%>-<%= msgList.getE01CUSNA1()%></td>
			<td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01PLPEJE()%><BR>
			<%= msgList.getE01EUPIDN()%></td>
			<td NOWRAP  align=CENTER width=\"25%\"><%= msgList.getE01PLTPR0()%>-<%= msgList.getE01PLTDE0()%></td>
			<td NOWRAP  align=CENTER width=\"5%\"><%= msgList.getE01XXXEST()%></td>
			<td NOWRAP  align=LEFT width=\"20%\"><%= msgList.getE01PLTAMN()%><BR>
			<%= msgList.getE01PLTMAP()%></td>
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
		int pos = EDP072001Help.getFirstRec() - 20;
   			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.creditproposal.JSEDP0750?SCREEN=200&Pos=" + pos +"\"><img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
  %>
  </TD>
  <TD WIDTH="50%" ALIGN=RIGHT>
  <%
        if ( EDP072001Help.getShowNext() ) {
		int pos = EDP072001Help.getLastRec();
   			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.creditproposal.JSEDP0750?SCREEN=200&Pos=" + pos +"\"><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");
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
