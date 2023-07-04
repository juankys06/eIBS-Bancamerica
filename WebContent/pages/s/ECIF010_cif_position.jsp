<html>
<head>
<title>Resumen de Operaciones por Cliente</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "cifPos" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

  function goAction(op) {

     document.forms[0].opt.value = op;
     var formLength= document.forms[0].elements.length;
     var ok = false;
     for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "ACCNUM") 
      	{
        		ok = true;
        		break;
      	}
      }
	  if ( ok ) {
          document.forms[0].submit();
     }
     else {
			alert("Seleccione una cuenta antes de realizar esta operación");	   
     }

  }
  
  function CallMes(){
 	document.forms[0].Mess.value="";
 	document.forms[0].Ano.value="";
 	 muestra("Ppal"); 
	oculta("PMes");
  
  }

	 builtNewMenu(ecif10_i_opt);

</SCRIPT>


</head>

<BODY>

<SCRIPT> initMenu(); </SCRIPT>
<div id="Ppal">
<p>

<h3 align="center">Posición del Cliente<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cif_position.jsp,ECIF010"></h3>
<hr size="4">
  <form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF010" >
    <INPUT TYPE=HIDDEN NAME="SCREEN" value="10">
	 <INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
<%
	if ( cifPos.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
        <div align="center">
        		<font size="3"><b>
        				No hay resultados que correspondan a su criterio de búsqueda 
        		</b></font>
        	</div>
      </TD></TR>
   		</TABLE>
<%   		
	}
	else {
%>

   <TABLE class="tbenter">
    <TR>
      <TD ALIGN=CENTER width="25%">
  			<a href="javascript:goAction(1)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1','','<%=request.getContextPath()%>/images/s/INQUIRY_OVER.gif',1)"><img name="Image1" border="0" src="<%=request.getContextPath()%>/images/s/INQUIRY.gif" ></a>
      </TD>
      <TD ALIGN=CENTER width="25%">
  			<a href="javascript:goAction(2)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image2','','<%=request.getContextPath()%>/images/s/STATEMENT_ACCOUNT_OVER.gif',1)"><img name="Image2" border="0" src="<%=request.getContextPath()%>/images/s/STATEMENT_ACCOUNT.gif" ></a>
      </TD>
      <TD ALIGN=CENTER width="25%">
  			<a href="javascript:goAction(3)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image3','','<%=request.getContextPath()%>/images/s/AVERAGE_OVER.gif',1)"><img name="Image3" border="0" src="<%=request.getContextPath()%>/images/s/AVERAGE.gif" ></a>
      </TD>
      <TD ALIGN=CENTER width="25%">
  			<a href="javascript:muestra('PMes'); oculta('Ppal')" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image3','','<%=request.getContextPath()%>/images/s/AVERAGE_OVER_diario.gif',1)"><img name="Image3" border="0" src="<%=request.getContextPath()%>/images/s/AVERAGE_Diario.gif"  ></a>
      </TD>
    </TR>
  </TABLE>
        
<p>
<table class="tableinfo">
  <tr id="trdark"> 
      <td nowrap > 
        <table class=tbhead cellspacing="0" cellpadding="2" width="100%"   align="center">
        <tr>
             <td nowrap width="10%" align="left">  
         			<b><%= userPO.getHeader2()%></b>
             </td>
             <td nowrap width="10%" align="right"> Cliente: </td>
             <td nowrap width="12%" align="left">
         			<%= userPO.getCusNum()%>
             </td>
             <td nowrap width="6%" align="right">Nombre:  </td>
             <td nowrap width="14%" align="left">
         			<%= userPO.getHeader1()%>
             </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<h4>Activos (En Moneda Base)</h4>
 <TABLE class="tableinfo" ALIGN=CENTER>
  <TR><TD>
   <TABLE id="headTable1">
     <TR id ="trdark">
      <TH ALIGN=CENTER  rowspan="2" nowrap></TH>
      <TH ALIGN=CENTER colspan="4" nowrap>Cuenta</TH>
      <TH ALIGN=CENTER rowspan="2" nowrap>MDA</TH>
      <TH ALIGN=CENTER rowspan="2" nowrap>Apertura/<br>Vencimiento</TH>
      <TH ALIGN=CENTER rowspan="2" nowrap>Principal</TH>
      <TH ALIGN=CENTER rowspan="2" nowrap>Interes</TH>
      <TH ALIGN=CENTER rowspan="2" nowrap>Otros</TH>
      <TH ALIGN=CENTER rowspan="2"nowrap>Total</TH>
    </TR>
    <TR id ="trdark"> 
      <TH ALIGN=CENTER nowrap>Número</TH>
      <TH ALIGN=CENTER nowrap>Tipo</TH>
      <TH ALIGN=CENTER nowrap>Descripción</TH>
      <TH ALIGN=CENTER nowrap>Status</TH>
    </TR>
  </TABLE>  
     <div id="dataDiv1" class="scbarcolor" style="padding:0">
      <table id="dataTable1">
    <%
                cifPos.initRow();
                int i=0;
                while (cifPos.getNextRow()) {
                    if (cifPos.getFlag().equals("1")) {
                    		out.println(cifPos.getRecord());              		
   							i++;
                    }
                }
                if ( i > 6 ) {
      %>
                    		 <SCRIPT Language="Javascript">
   								 dataDiv1.style.height="120"; 
   								 dataDiv1.style.overflowY="scroll";
   							</SCRIPT>	 
  			<%	 
                          }
             
    %> 
    </table>
  </div>
  </TD>  
</TR>	
</TABLE>

  
<h4>Pasivos (En Moneda Base)</h4>
  <TABLE class="tableinfo">
  <TR>
  <TD width=100%>
   <TABLE id="headTable2">
     <TR id ="trdark">
      <TH ALIGN=CENTER rowspan="2"></TH>
      <TH ALIGN=CENTER colspan="4">Cuenta</TH>      
      <TH ALIGN=CENTER rowspan="2">MDA</TH>      
      <TH ALIGN=CENTER rowspan="2">Apertura/<br>Vencimiento</TH>
      <TH ALIGN=CENTER rowspan="2">Principal</TH>
      <TH ALIGN=CENTER rowspan="2">Interes</TH>
      <TH ALIGN=CENTER rowspan="2">Otros</TH>
      <TH ALIGN=CENTER rowspan="2">Total</TH>
    </TR>
    <TR id ="trdark"> 
      <TH ALIGN=CENTER>Número</TH>
      <TH ALIGN=CENTER>Tipo</TH>
      <TH ALIGN=CENTER>Descripción</TH>
      <TH ALIGN=CENTER>Status</TH>
    </TR>
   </TABLE>   
    <div id="dataDiv2" class="scbarcolor" style="padding:0">
      <table id="dataTable2">
    <%
                cifPos.initRow();
                int j=0;
                while (cifPos.getNextRow()) {
                    if (cifPos.getFlag().equals("2")) {
                    		out.println(cifPos.getRecord());              		
   							j++;
                    }
                }
                if ( j > 6 ) {
      %>
                    		 <SCRIPT Language="Javascript">
   								 dataDiv2.style.height="120"; 
   								 dataDiv2.style.overflowY="scroll";
   							</SCRIPT>	 
  			<%	 
                          }
                
    %> 
    </table>
  </div>
  </TD>  
</TR>	
</TABLE>
  
<h4>Totales (En Moneda Base)</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap>
      <table width="100%">
        <tr id="trclear">
			<td width="14%">
			<div align="right"><b>Activos : </b></div>
			</td>
			<td width="16%"><%= userPO.getHeader3() %></td>
			<td width="14%">
			<div align="right"><b>Pasivos : </b></div>
			</td>
			<td width="16%"><%= userPO.getHeader4() %></td>
			<td width="18%">
			<div align="right"><b>Posici&oacute;n Neta : </b></div>
			</td>
			<td width="22%"><%= userPO.getHeader5() %></td>
		</tr>
      </table>
      </td>
    </tr>
  </table>
</p>  
</div>
<div id="PMes"  align="center">
<p>
<table id="TBHELP" align="center" width="100%" height="60%" border="1">
<th id="THHELP">Seleccione el mes y el año de busqueda: 

 				<INPUT type="Hidden" name="diat" size="2" maxlength="2" value="" readonly> 
				 <INPUT type="text" name="Mess" size="2" onchange="juan" maxlength="2" value="" readonly> 
				 <INPUT type="text" name="Ano" size="2" maxlength="2" value="" readonly>
<a href="javascript:DatePicker(document.forms[0].diat,document.forms[0].Mess,document.forms[0].Ano)">
				<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="ayuda" align="absmiddle" border="0"></a>
</th>

				  
				  
     
	
</table>
<a onclick="CallMes()" onMouseOut="MM_swapImgRestore()"  >Atras     </a>
<a onclick="showAvgDiario()" onMouseOut="MM_swapImgRestore()"  >Enviar</a>
</p>   
</div>			

 


<SCRIPT language="javascript">
CallMes();
   function tableResize() {
     adjustDifTables(headTable1,dataTable1,dataDiv1,1,1);
     adjustDifTables(headTable2,dataTable2,dataDiv2,1,1);
   }
   function oculta(id){
	 var elDiv = document.getElementById(id); //se define la variable "elDiv" igual a nuestro div
	 elDiv.style.display='none'; //damos un atributo display:none que oculta el div	 
	}
 
function muestra(id){
	
	 var elDiv = document.getElementById(id); //se define la variable "elDiv" igual a nuestro div
	 elDiv.style.display='';//damos un atributo display:block que  el div	 
	}
function showAvgDiario(){
		if(document.forms[0].Mess.value=="" && document.forms[0].Mess.value==""){
			alert("Debe Ingresar Mes y Año para Visualizar los Promedios Diarios");
		}else{
			//document.forms[0].SCREEN.value=6;
			goAction(3);
		}
	}
	
  tableResize();
  window.onresize=tableResize; 
</SCRIPT>
<%
  }
%>

  </form>
</body>
</html>

