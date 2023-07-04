<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Entrada Transacciones Reconciliacion</title>
 <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
 
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
 


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<%@page import="datapro.eibs.beans.ERC000001Message"%>
<%@page import="java.util.ArrayList"%>

</head>
<body>
 

<SCRIPT LANGUAGE="javascript">


function submitThis(option) {
var okdel = false;
  if ( option==1) {
	document.forms[0].SCREEN.value = 200;
	document.forms[0].option.value = 'N';
	document.forms[0].submit();
  }
  else if ( option==2) {
	document.forms[0].SCREEN.value = 300;
	document.forms[0].submit();
  }
 }
</SCRIPT>
<%
	ERC000001Message msgList = new ERC000001Message();
	if(request.getSession().getAttribute("msgList")!=null){
		msgList = (ERC000001Message)request.getSession().getAttribute("msgList");
	}
	
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }
%>
 

<h3 align="center">Entrada Transacciones Reconciliacion <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="reconc_new_ent.jsp,ERC0000"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSERC0000" >
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="10%" > 
              <div align="right"><b>Banco :</b></div>
            </td>
            <td nowrap width="10%"> 
              <div align="left">                
                <input type="text" readonly name="E01RCIBNK" size="2" maxlength="2" value="<%= userPO.getHeader1().trim()%>">                
                 
                </div>
            </td>
            <td nowrap width="10%" > 
              <div align="right"><b>Moneda :</b></div>
            </td>
            <td nowrap colspan="3" width="10%"> 
              <div align="left">  
                <input type="text" name="E01RCICCY" size="3" maxlength="3" value="<%= msgList.getE01RCICCY().trim()%>">
                <a href="javascript:GetCurrency('E01RCICCY',document.forms[0].E01RCIBNK.value)"> 
              		<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" >
              		<input type="hidden" name="E01TRABNK">
              	</a>
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0" > 
              </div>
            </td>              
            <td nowrap width="10%" > 
              <div align="right"><b>Cuenta Contable : </b></div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E01RCIGLN" size="16" maxlength="16" value="<% if (msgList.getE01RCIGLN().trim().equals("999999999")) out.print("Nueva Cuenta Contable"); else out.print(msgList.getE01RCIGLN().trim()); %>">
                <a href="javascript:GetLedger('E01RCIGLN',document.forms[0].E01RCIBNK.value,document.forms[0].E01RCICCY.value,'')"> 
              		<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" > 
             	 </a>
             	 <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0" > 
              </div>
            </td>
          </tr>
          
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Cuenta : </b></div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E01RCIACC" size="12" maxlength="9" value="<% if (msgList.getE01RCIACC().trim().equals("999999999")) out.print("Nueva Cuenta"); else out.print(msgList.getE01RCIACC().trim()); %>">
                <a href="javascript:GetAccount('E01RCIACC','','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
              </div>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Fecha Estado de Cuenta :</div>
            </td>
            <td nowrap width="23%">
             
              <input type="text" name="E01RCISD1" size="2" maxlength="2" value="<%= msgList.getE01RCISD1().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01RCISD2" size="2" maxlength="2" value="<%= msgList.getE01RCISD2().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01RCISD3" size="2" maxlength="2" value="<%= msgList.getE01RCISD3().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E01RCISD1,document.forms[0].E01RCISD2,document.forms[0].E01RCISD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="ayuda" align="absmiddle" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0" > 
            </td>
            <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0" >  
          </tr>
        </table>
      </td>
    </tr>
  </table>  
  <table class="tableinfo">
    <tr > 
      <td nowrap width="420"> 
        <table cellpadding=2 cellspacing=0 border="0">
           
          <tr id="trclear">                         
            <td nowrap width="40%"> 
              <div align="center"><b>  (*)    Use el codigo 940 o 950 para el estado de cuenta via SWIFT   (*)</b></div>
            </td>
             
          	 
         </tr>   
        </table>
      </td>
    </tr>
  </table>  
  <h4> Saldos </h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
           
          <tr id="trdark">              
            <td nowrap width="25%" height="37"> 
              <div align="right">Inicial :</div>
            </td>
            <td nowrap width="27%" height="37">
              <input type="text" name="E01RCISTI" size="15" maxlength="13" value="<%= msgList.getE01RCISTI().trim()%>" onkeypress="enterDecimal()">
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0" >           
            </td>
           
          	
            <td nowrap width="25%" > 
              <div align="right">Final :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01RCISTF" size="15" maxlength="13" value="<%= msgList.getE01RCISTF().trim()%>" onkeypress="enterDecimal()">
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0" ></td>
         </tr>   
        </table>
      </td>
    </tr>
  </table>  

                
    
     <table class="tbenter" width="100%" >
    <tr>
      <td align="left">
      	<h4>Transacciones</h4>
      </td>
      <td   width="91%"> 
        <div align="right">Cantidad de Registros a A&ntilde;adir : 
          <input type="text" name="TEMP_ROW" size="4" maxlength="3" value="0" onKeypress="enterInteger()">
        </div>
      </td>
      <td bordercolor="#FFFFFF" bgcolor="#FFFFFF" width="8%"><a href="javascript:submitThis(1)"><img src="<%=request.getContextPath()%>/images/s/applicar_on.gif" alt="aplicar" align="absmiddle" border="0" ></a></td>
    </tr>
  </table>
  <TABLE class="tableinfo" align="center">
  <TR><TD>
  
   <table id="headTable">
    <tr id="trdark"> 
      <td nowrap align="center" >Fecha<BR> Cuenta</td>
      <td nowrap align="center" >Referencia </td>
      <td nowrap align="center" >Debitos/<BR>Cargos</td>
      <td nowrap align="center" >Creditos/<br>Depositos</td>
      <td nowrap align="center" >Saldos</td>
       
    </tr>
    </table> 
      
    <div id="dataDiv" class="scbarcolor" style="height:170; overflow-y :scroll; z-index:0" >
     <table id="dataTable" >
     <%
     	int count=0;
	    int total=0;
	    if(request.getSession().getAttribute("total")!=null){
	    	total=Integer.parseInt((String)request.getSession().getAttribute("total"));
	    }
	    else{
	    	total=4;
	    }
     	ArrayList variableParameters = null;
     	if(request.getSession().getAttribute("variableParameters")!=null){
     		variableParameters = (ArrayList)request.getSession().getAttribute("variableParameters");
     	}
    	String rows = "";
    	int amount=0;
    	if(request.getSession().getAttribute("rows")!=null){
    		rows = (String)request.getSession().getAttribute("rows");
    		 amount = Integer.parseInt(rows);
    	}
  		for (int i=0; i<total; i++ ) {
  			if(i<total-amount){
   	%> 
    <tr id="trclear">      
       
       <td nowrap align="center" width="27%" height="17">
         <input type="text" name="E01RCIBD1<%=i%>1" size="2" maxlength="2" <%if (variableParameters!=null){%> value="<%=((String)variableParameters.get(count)).trim()%>" <%}%>  onkeypress="enterInteger()">
         <input type="text" name="E01RCIBD2<%=i%>2" size="2" maxlength="2" <%if (variableParameters!=null){%> value="<%=((String)variableParameters.get(count+1)).trim()%>" <%}%> onkeypress="enterInteger()">
         <input type="text" name="E01RCIBD3<%=i%>3" size="2" maxlength="2" <%if (variableParameters!=null){%> value="<%=((String)variableParameters.get(count+2)).trim()%>" <%}%> onkeypress="enterInteger()">
         <%
         	String dates = "javascript:DatePicker(document.forms[0].E01RCIBD1"+i+"1,document.forms[0].E01RCIBD2"+i+"2,document.forms[0].E01RCIBD3"+i+"3)";
         %>
         <a href="<%=dates%>"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="ayuda" align="absmiddle" border="0"></a> 
       </td> 
       
      <td nowrap align="center" width="20%" height="17">         
          <input type="text" name="E01RCICKN<%=i%>4" size="6" maxlength="6" <%if (variableParameters!=null){%> value="<%=((String)variableParameters.get(count+3)).trim()%>" <%}%> onkeypress="enterInteger()">           
      </td>
      <td nowrap align="center" width="27%" height="17">
         <input type="text" name="E01RCIAMD<%=i%>5" size="15" maxlength="13" <%if (variableParameters!=null){%> value="<%=((String)variableParameters.get(count+4)).trim()%>" <%}%> onkeypress="enterDecimal()">
      </td>
      <td nowrap align="center" width="27%" height="17">
         <input type="text" name="E01RCIAMC<%=i%>6" size="15" maxlength="13" <%if (variableParameters!=null){%> value="<%=((String)variableParameters.get(count+5)).trim()%>" <%}%> onkeypress="enterDecimal()">
      </td>
      <td nowrap align="center" width="27%" height="17">
         <input type="text" readonly name="E01RCISAT<%=i%>7" size="15" maxlength="13" <%if (variableParameters!=null){%> value="<%=((String)variableParameters.get(count+6)).trim()%>" <%}%> >
      </td>
    </tr>
    <%
    		}else{
    %>
    <tr id="trclear">      
       
       <td nowrap align="center" width="27%" height="17">
         <input type="text" name="E01RCIBD1<%=i%>1" size="2" maxlength="2" onkeypress="enterInteger()">
         <input type="text" name="E01RCIBD2<%=i%>2" size="2" maxlength="2" onkeypress="enterInteger()">
         <input type="text" name="E01RCIBD3<%=i%>3" size="2" maxlength="2" onkeypress="enterInteger()">
         <%
         	String dates = "javascript:DatePicker(document.forms[0].E01RCIBD1"+i+"1,document.forms[0].E01RCIBD2"+i+"2,document.forms[0].E01RCIBD3"+i+"3)";
         %>
         <a href="<%=dates%>"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="ayuda" align="absmiddle" border="0"></a> 
       </td> 
       
      <td nowrap align="center" width="20%" height="17">         
          <input type="text" name="E01RCICKN<%=i%>4" size="6" maxlength="6" onkeypress="enterInteger()">           
      </td>
      <td nowrap align="center" width="27%" height="17">
         <input type="text" name="E01RCIAMD<%=i%>5" size="15" maxlength="13" onkeypress="enterDecimal()">
      </td>
      <td nowrap align="center" width="27%" height="17">
         <input type="text" name="E01RCIAMC<%=i%>6" size="15" maxlength="13" onkeypress="enterDecimal()">
      </td>
      <td nowrap align="center" width="27%" height="17">
         <input type="text" readonly name="E01RCISAT<%=i%>7" size="15" maxlength="13" >
      </td>
    </tr>
    <%
    	}
    	count = count+7;
    	}
    %>
    		<input type="hidden" name="total" value="<%=total%>">
     </table>
        </div>
    <table id="footTable" align="right"> 				
    <tr id="trdark"> 
            <td nowrap align="right"><b>Saldo      Final :</b> </td>
      <td nowrap align="center"><b><i><strong> 
          <input type="text" name="E01RCISAF" size="15" maxlength="13" readonly value="<%= msgList.getE01RCISAF().trim()%>">
          </strong></i></b>
      </td>
    </tr>
  </table>
  </TD>  
</TR>	
</TABLE>    

     
 <SCRIPT language="javascript">
    function tableresize() {
     adjustEquTables(headTable,dataTable,dataDiv,1,false);
     //adjustEquTables(headTable1,dataTable1,dataDiv1,0,false);
   }
  tableresize();
  window.onresize=tableresize;  
  </SCRIPT>
   
   
  <div align="center"> 
	   <input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="submitThis(2)">
  </div>  
  
  <INPUT TYPE=HIDDEN NAME="SCREEN">
  <INPUT TYPE=HIDDEN NAME="option">
  
  
</form>
</body>
</html>
