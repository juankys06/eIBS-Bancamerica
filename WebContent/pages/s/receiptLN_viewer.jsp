
<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Recibo de Prestamo</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="receipt" class="datapro.eibs.beans.EDL014030Message"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

function setImgVisible(val){
   if (val) IMGSUBMIT.style.display=""; else IMGSUBMIT.style.display="none";
}

function doPrint(){
	if(!window.print){
       var msg ="Debe actualizar su navegador para imprimir";
	   alert(msg);
	   return;}
	
    window.focus();
    window.onafterprint=setImgVisible(true);
	window.onbeforeprint=setImgVisible(false);
	window.print();

	return;
} 

</SCRIPT>

</head>

<body>

<% if (receipt.getE30OPECOD().trim().equals("01")) {%>
	<h3 align="center">Recibo de Apertura de Prestamo</h3>
<% } else {%>
	<h3 align="center">Recibo de Pago de Prestamo</h3>
<% } %>
<hr size="4">
<form >
<% if (receipt.getE30OPECOD().trim().equals("01")) {%>  
  <table>
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr > 
            <td nowrap> 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap> 
              <div align="left">
                <%= receipt.getE30DEACUN().trim()%>
              </div>
            </td>
            <td nowrap> 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap> 
              <div align="left">
                <%= receipt.getE30CUSNA1().trim()%>
              </div>
            </td>
          </tr>
          <tr > 
            <td nowrap> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap> 
              <div align="left">
                <%= receipt.getE30DEAACC().trim()%>
              </div>
            </td>
            <td nowrap> 
              <div align="right"><b>Tasa : </b></div>
            </td>
            <td nowrap> 
              <div align="left">
                <%= receipt.getE30DEARTE().trim()%>
              </div>
            </td>
          </tr>
          <tr > 
            <td nowrap> 
              <div align="right"><b>Fecha Vencimiento :</b></div>
            </td>
            <td nowrap> 
              <div align="left">
                <%= Util.formatDate(receipt.getE30DEAMD1(),receipt.getE30DEAMD2(),receipt.getE30DEAMD3())%>
              </div>
            </td>
            <td nowrap> 
              <div align="right"><b>Plazo : </b></div>
            </td>
            <td nowrap> 
              <div align="left">
                <% if (receipt.getE30DEATRC().trim().equals("D")) out.print(receipt.getE30DEATRM().trim() + " DIA(S)");
                else if (receipt.getE30DEATRC().trim().equals("M")) out.print(receipt.getE30DEATRM().trim() + " MES(ES)");
                else if (receipt.getE30DEATRC().trim().equals("Y")) out.print(receipt.getE30DEATRM().trim() + " ANO(S)");%>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  <h4>Distribuci&oacute;n</h4>
  <table >
    <tr > 
      <td nowrap align=center> 
        <table cellspacing=0 cellpadding=2 width="50%" border="0">
          <tr > 
            <td nowrap> 
              <div align="right">Monto Original : </div>
            </td>
            <td nowrap align= right> 
              <%= receipt.getE30DEAOAM().trim()%>
            </td>
          </tr>
          <%
          String name="";
          for (int i= 1;i<26;i++){
           if (i< 10) name="0"+i; else name="" +i;
		   if(!receipt.getField("E30PDSC"+name).getString().trim().equals("")){
		 %> 
          <tr> 
            <td nowrap align= right> 
              <div ><%= receipt.getField("E30PDSC"+name).getString().trim()%> : </div>
            </td>
            <td nowrap align= right> 
              <%= receipt.getField("E30PAMT"+name).getString().trim()%>
            </td>
          </tr>
          <%
		    }
		   }
		  %> 
          
          <tr> 
            <td nowrap> 
              <div align="right">Saldo Neto : </div>
            </td>
            <td nowrap align="right"> 
              <%= receipt.getE30TRNTOT().trim()%>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
   
<h4>Desembolso</h4>
<TABLE id="mainTable">
  <TR><TD>
  
   <table>
    <tr > 
      <td nowrap align="center">Concepto</td>      
      <td nowrap align="center">Referencia</td>
      <td nowrap align="center">Monto</td>
    </tr>
    
          <%
  				   int amount = 9;
 				   name = "";
  					for ( int i=1; i<=amount; i++ ) {
   					  name = i + "";
   			%>			 
          <tr>
            <td nowrap > 
              <div align="center" nowrap> 
                <%= receipt.getField("E30OFFCO"+name).getString().trim()%>
              </div>
            </td>            
            <td nowrap > 
              <div align="center"> 
                <%= receipt.getField("E30OFFAC"+name).getString().trim()%>
               </div>
            </td>
            <td nowrap  > 
              <div align="right"> 
                <%= receipt.getField("E30OFFAM"+name).getString().trim()%>
              </div>
            </td>
          </tr>
          
    		<%
    		}
    		%>
    	
          <tr > 
            <td nowrap colspan=2 align="right"><b>Equivalente Moneda del Préstamo : </b>
            </td>
            <td nowrap align="right">
                <%= receipt.getE30OFFEQV().trim()%>
            </td>
          </tr>
        </table>
        
        </TD>  
</TR>	
</TABLE>    
<% } else {%>
  <table>
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr > 
            <td nowrap> 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap> 
              <div align="left">
                <%= receipt.getE30DEACUN().trim()%>
              </div>
            </td>
            <td nowrap> 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap> 
              <div align="left">
                <%= receipt.getE30CUSNA1().trim()%>
              </div>
            </td>
          </tr>
          <tr > 
            <td nowrap> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap colspan=3> 
              <div align="left">
                <%= receipt.getE30DEAACC().trim()%>
              </div>
            </td>
           </tr> 
        </table>
      </td>
    </tr>
  </table>
  
  <h4>Distribuci&oacute;n del Pago</h4>
  <table >
    <tr > 
      <td nowrap align=center> 
        <table cellspacing=0 cellpadding=2 width="50%" border="0">
          <tr > 
            <td nowrap> 
              <div align="right">Principal : </div>
            </td>
            <td nowrap align= right> 
              <%= receipt.getE30TRNPRI().trim()%>
            </td>
          </tr>
          <tr > 
            <td nowrap> 
              <div align="right">Reajuste : </div>
            </td>
            <td nowrap align= right> 
              <%= receipt.getE30TRNREA().trim()%>
            </td>
          </tr>
          <tr > 
            <td nowrap> 
              <div align="right">Interes : </div>
            </td>
            <td nowrap align= right> 
              <%= receipt.getE30TRNINT().trim()%>
            </td>
          </tr>
          <tr > 
            <td nowrap> 
              <div align="right">Mora : </div>
            </td>
            <td nowrap align= right> 
              <%= receipt.getE30TRNMOR().trim()%>
            </td>
          </tr>
          <%
          String name="";
          for (int i= 1;i<26;i++){
           if (i< 10) name="0"+i; else name="" +i;
		   if(!receipt.getField("E30PDSC"+name).getString().trim().equals("")){
		 %> 
          <tr> 
            <td nowrap align= right> 
              <div ><%= receipt.getField("E30PDSC"+name).getString().trim()%> : </div>
            </td>
            <td nowrap align= right> 
              <%= receipt.getField("E30PAMT"+name).getString().trim()%>
            </td>
          </tr>
          <%
		    }
		   }
		  %> 
          
          <tr> 
            <td nowrap> 
              <div align="right">Total a Pagar : </div>
            </td>
            <td nowrap align="right"> 
              <%= receipt.getE30TRNTOT().trim()%>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
   
<h4>Desembolso</h4>
<TABLE id="mainTable">
  <TR>
   <TD>  
   <table>
    <tr > 
      <td nowrap align="center">Concepto</td>      
      <td nowrap align="center">Referencia</td>      
    </tr>         		 
    <tr>
            <td nowrap > 
              <div align="center" nowrap> 
                <%= receipt.getE30OFFCO1().trim()%>
              </div>
            </td>            
            <td nowrap > 
              <div align="center"> 
                <%= receipt.getE30OFFAC1().trim()%>
               </div>
            </td>
            
   </tr>   
   </table>       
  </TD>  
</TR>	
</TABLE>

<% } %> 

   <div align="center">
    <img  class ="imgfilter" id ="IMGSUBMIT" name="Submit" src="<%=request.getContextPath()%>/images/s/bt_imprimir.gif" OnClick="doPrint();" onMouseDown="this.className='' " onMouseUp="this.className='imgfilter' " >
  </div>

  </form>
</body>
</html>
