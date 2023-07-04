<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Lista de Cliente
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "cifList" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id="cifTotal" class="datapro.eibs.beans.ECIF17001Message" scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="javascript">
function goAction(op) {

     document.forms[0].opt.value = op;
     var formLength= document.forms[0].elements.length;
     var ok = false;
	 var oc = "";
	 var pg = "";
     for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "ACC") 
      	{
				if (document.forms[0].elements[n].checked == true) {
					oc = document.forms[0].elements[n].value;
        			ok = true;
        			break;
				}
      	}
      }
      pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF170?SCREEN=3&opt=" + op + "&ACC=" + oc;	
	
	  CenterWindow(pg,600,500,2);

  }


</SCRIPT>


</HEAD>

<BODY>

<h3 align="center">Ejecutivos de Cuenta<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cust_list.jsp,ECIF170"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF170" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
<INPUT TYPE=HIDDEN NAME="opt" VALUE="1">

<%
	if ( cifList.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=90%>
   		<TR>
      <TD> 
        
      <div align="center"> <font size="3"><b> No hay resultado que corresponda a su criterio de búsqueda. 
        </b></font> </div>
      </TD></TR>
   		</TABLE>
<%   		
	}
	else {
%>
  
  <TABLE class="tbenter" width="100%">
    <TR> 
      <TD ALIGN=CENTER class=TDBKG> <a href="javascript:goAction(1)" ><b> Totales</b></a> 
      </TD>
      <TD ALIGN=CENTER class=TDBKG> <a href="javascript:goAction(2)" ><b>Posición  
      <br>por Cuenta</b></a> 
      </TD>
      <TD ALIGN=CENTER class=TDBKG> <a href="javascript:goAction(3)" ><b>Posición 
      <br>por Producto</b></a> 
      </TD>
      <TD ALIGN=CENTER class=TDBKG> <a href="<%=request.getContextPath()%>/pages/background.jsp" ><b>Salir</b></a> 
      </TD>
    </TR>
  </TABLE>   
 
        
  <TABLE class="tableinfo" >
  <TR><TD width=100%>
   <TABLE id="headTable1">
   <TR id="trdark"> 
      <TH ALIGN=CENTER rowspan="2"></TH>
      <TH ALIGN=CENTER nowrap>Código</TH>
      <TH ALIGN=CENTER nowrap>Nombre</TH>
      <TH ALIGN=CENTER nowrap>Email</TH>
      <TH ALIGN=CENTER nowrap>Teléfono</TH>
      <TH ALIGN=CENTER nowrap>Extensión</TH>
   </TR>
  </TABLE>         
  
 <div id="dataDiv1" class="scbarcolor" style="padding:0" nowrap>
		<TABLE id="dataTable1">
			<%
                cifList.initRow();
                int i=0;
                while (cifList.getNextRow()) {
                    out.println(cifList.getRecord());              		
   					i++;                   
                }
                if ( i > 6 ) {
      %>
			<SCRIPT language="Javascript">
   				dataDiv1.style.height="120"; 
   				dataDiv1.style.overflowY="scroll";
   				</SCRIPT>
			<%	 
                }
             
   %>

		</TABLE>
		</div>
   </TD>
  </TR>	
</TABLE>


<SCRIPT language="javascript">
   function tableResize() {
     adjustEquTables(headTable1,dataTable1,dataDiv1,0,false);
   }
  tableResize();
  window.onresize=tableResize; 
</SCRIPT>

<%   		
	}
%>
</FORM>

</BODY>
</HTML>
