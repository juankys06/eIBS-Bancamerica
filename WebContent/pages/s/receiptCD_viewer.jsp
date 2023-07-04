
<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Transacciones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="receipt" class="datapro.eibs.beans.EDL014030Message"  scope="session" />
<jsp:useBean id="receiptH" class="datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id="receiptB" class="datapro.eibs.beans.JBListRec"  scope="session" />

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

<% if (receiptH.getNoResult()) {%>
	<h3 align="center">Recibo de Pago de Certificado</h3>
<% } else {%>
	<h3 align="center">Recibo de Apertura de Certificados</h3>
<% } %>
<hr size="4">
<form >
<% if (!receiptH.getNoResult()) {%>  
  <table>
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr > 
            <td nowrap> 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap  colspan=3> 
              <div align="left">
                <%= receiptH.getRecord(0).trim()%>
              </div>
            </td>
            
          </tr>
          <tr > 
            <td nowrap> 
              <div align="right"><b>Tasa : </b></div>
            </td>
            <td nowrap colspan=3> 
              <div align="left">
                <%= receiptH.getRecord(6).trim()%>
              </div>
            </td>
          </tr>
          <tr > 
            <td nowrap> 
              <div align="right"><b>Fecha Vencimiento :</b></div>
            </td>
            <td nowrap> 
              <div align="left">
                <%= Util.formatDate(receiptH.getRecord(1),receiptH.getRecord(2),receiptH.getRecord(3))%>
              </div>
            </td>
            <td nowrap> 
              <div align="right"><b>Plazo : </b></div>
            </td>
            <td nowrap> 
              <div align="left">
                <% if (receiptH.getRecord(5).trim().equals("D")) out.print(receiptH.getRecord(4).trim() + " DIA(S)");
                else if (receiptH.getRecord(5).trim().equals("M")) out.print(receiptH.getRecord(4).trim() + " MES(ES)");
                else if (receiptH.getRecord(5).trim().equals("Y")) out.print(receiptH.getRecord(4).trim() + " ANO(S)");%>
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
              <div align="right">Certificado</div>
            </td>
            <td nowrap align= right> 
              <div align="right">Monto</div>
            </td>
          </tr>
          <%
           receiptB.initRow();
           while (receiptB.getNextRow()) {
              if (receiptB.getFlag().equals("")) {
		 %> 
          <tr> 
            <td nowrap align= center> 
              <%= receiptB.getRecord(0).trim()%>
            </td>
            <td nowrap align= right> 
              <%= receiptB.getRecord(1).trim()%>
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
              <%= receiptH.getRecord(0).trim()%>
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
  			int amount = 16; //9 records			  
  			for ( int i=7; i< amount; i++ ) {
  				if (!receiptH.getRecord(i).equals("")) {
   		%>			 
          <tr>
            <td nowrap > 
              <div align="center" nowrap> 
                <%= receiptH.getRecord(i).trim()%>
              </div>
            </td>            
            <td nowrap > 
              <div align="center"> 
                <%= receiptH.getRecord(i + 9).trim()%>
               </div>
            </td>
            <td nowrap  > 
              <div align="right"> 
                <%= receiptH.getRecord(i + 18).trim()%>
              </div>
            </td>
          </tr>
          
    		<%
    			}
    		}
    		%>
    	
          <tr > 
            <td nowrap colspan=2 align="right"><b>Total : </b>
            </td>
            <td nowrap align="right">
                <%= receiptH.getRecord(0).trim()%>
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
