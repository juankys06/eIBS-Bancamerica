<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head>
<title>Pagos</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<%@ page import = "datapro.eibs.master.Util" %>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "payDoc" class= "datapro.eibs.beans.EDL082501Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>
<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">;
            showErrors();
     </SCRIPT>
 <%
 }
%>
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0828">
  
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
	<INPUT TYPE=HIDDEN NAME="TMPAMT" VALUE="">
	<INPUT TYPE=HIDDEN NAME="E01DEVINT" VALUE="">
	<INPUT TYPE=HIDDEN NAME="E01PAGANT" VALUE="<%= payDoc.getE01PAGANT() %>">

  <h3 align="left">Pagos</h3>
  <table class="tableinfo" cellpadding="2" cellspacing="0">
    <tr id="trdark">
	  <td align="right" width="20%" nowrap> <b> Cuenta : </b></td>
	  <td align="left" nowrap><%= payDoc.getE01DEAACC() %><b> - </b><%= payDoc.getE01CUSNA1() %> </td>
      <td nowrap align="left"> <b>No. Documento : </b><%= payDoc.getE01SELNDR() %> 
      </td> 
    </tr> 
	<tr id="trdark">
	  <td align="right" width="20%"> <b>Aceptante : </b></td>
	  <td align="left" nowrap colspan=2>
       <%= payDoc.getE01SELDID() %><b> - </b><%= payDoc.getE01ACPNME() %>
      </td>
	</tr>  
  </table>
  <BR>
  <table class="tableinfo" cellpadding="2" cellspacing="2">
    <tr> 
      <td nowrap>  
  <table width="100%">
    <tr> 
      <td id=trdark width="20%" nowrap>    
      </td>
	  <td id=trdark width="40%" nowrap align="center"> 
		A Pagar
      </td>
      <td id=trdark width="40%" nowrap  align="center"> 
        Pagado 
	  </td>
    </tr>
    <tr> 
      <td  width="20%" nowrap>  
        <div align="right"><b>Principal : </b></div>
      </td>
	  <td width="40%" nowrap align="right"> 
		<%= Util.formatCCY(payDoc.getE01TRNVDO()) %> 
      </td>
      <td width="40%" nowrap  align="right"> 
		<%= Util.formatCCY(payDoc.getE01PAGVDO())%>
       </td>
    </tr>
    <tr> 
      <td width="20%" nowrap>  
        <div align="right"><b>Mora : </b></div>
      </td>
	  <td width="40%" nowrap align="right"> 
		<%= Util.formatCCY(payDoc.getE01TRNMOR())%> 
      </td>
      <td width="40%" nowrap align="right">
		<%= Util.formatCCY(payDoc.getE01PAGMOR())%> 
 	  </td>
    </tr>
    <% if (payDoc.getH01FLGWK3().equals("R")) {%> 
     <tr> 
      <td width="20%" nowrap>  
        <div align="right"><b>Reajuste : </b></div>
      </td>
	  <td width="40%" nowrap align="right"> 
		<%= Util.formatCCY(payDoc.getE01TRNREA())%> 
      </td>
      <td width="40%" nowrap  align="right">
		<%= Util.formatCCY(payDoc.getE01PAGREA())%>  
       </td>
    </tr>
    <%
      }
    %>
    <% if (payDoc.getE01DEVINT().equals("Y")) {%> 
     <tr> 
      <td width="20%" nowrap>  
        <div align="right"><b>Intereses Pendientes : </b></div>
      </td>
	  <td width="40%" nowrap align="right"> 
		<%= Util.formatCCY(payDoc.getE01TRNINT())%> 
      </td>
      <td width="40%" nowrap  align="right">
      </td>
    </tr>
    <%
      }
    %>
    <tr> 
      <td  width="20%" nowrap>  
        <div align="right"><b>I.V.A. : </b></div>
      </td>
	  <td width="40%" nowrap align="right"> 
		<%= Util.formatCCY(payDoc.getE01TRNIVA()) %> 
      </td>
      <td width="40%" nowrap  align="right"> 
		<%= Util.formatCCY(payDoc.getE01PAGIVA())%>
       </td>
    </tr>
    <tr> 
      <td  width="20%" nowrap>  
        <div align="right"><b>Comisión : </b></div>
      </td>
	  <td width="40%" nowrap align="right"> 
		<%= Util.formatCCY(payDoc.getE01TRNCOM()) %> 
      </td>
      <td width="40%" nowrap  align="right"> 
		<%= Util.formatCCY(payDoc.getE01PAGCOM())%>
       </td>
    </tr>
    <tr> 
      <td  width="20%" nowrap>  
        <div align="right"><b>Impuesto : </b></div>
      </td>
	  <td width="40%" nowrap align="right"> 
		<%= Util.formatCCY(payDoc.getE01TRNIMP()) %> 
      </td>
      <td width="40%" nowrap  align="right"> 
		<%= Util.formatCCY(payDoc.getE01PAGIMP())%>
       </td>
    </tr>
    <tr id=trdark> 
      <td width="20%" nowrap>  
        <div align="right"><b>Total: </b></div>
      </td>
	  <td width="40%" nowrap align="right" > 
		<%= Util.formatCCY(payDoc.getE01TRNTOT())%> 
      </td>
      <td width="40%" nowrap  align="right">
	    <%= Util.formatCCY(payDoc.getE01PAGTOT())%>
      </td>
    </tr>
	
  </table> 
  <BR>
  <table width="100%" >
	<tr>
            <td nowrap width="20%"> 
              <div align="right">Fecha Valor :</div>
            </td>  
			<td nowrap >
			  <div align="left">
                <%= Util.formatDate(payDoc.getE01PAGVD1(),payDoc.getE01PAGVD2(),payDoc.getE01PAGVD3())%>  
        	  </div>
			</td>
			<td nowrapwidth="20%"> 
              <div align="right">Narrativa :</div>
            </td>  
			<td nowrap >
			  <div align="left">
				<%=payDoc.getE01DEANR1().trim()%> 
              </div>
			</td>
          </tr>
		<tr>
            <td nowrap width="20%">
               <div align="right">Devolver Intereses :</div>
            </td>  
			<td nowrap> 
				<% if(payDoc.getE01DEVINT().equals("Y")) out.print("Sí"); else out.print("No"); %>
			</td>
			<td nowrapwidth="20%"> 
            </td>  
			<td nowrap >
			  <div align="left">
				<%=payDoc.getE01DEANR2().trim()%>
			  </div>
			</td>
          </tr>
    </table>
  <BR>
    <table width="100%">
		 <tr id="trdark">
            <td nowrap width="30%"> 
              <div align="center">Concepto</div>
            </td>
            <td nowrap width="10%"> 
              <div align="center">Banco</div>
            </td>
            <td nowrap width="10%"> 
              <div align="center">Sucursal</div>
            </td>
            <td nowrap width="10%"> 
              <div align="center">Moneda</div>
            </td>
            <td nowrap width="20%"> 
              <div align="center">Referencia</div>
            </td>
            
          </tr>
		  <tr id"trclear">
             <td nowrap width="40%"> 
              <div align="center"> 
                <%=payDoc.getE01PAGOPC().trim()%><b> - </b><%=payDoc.getE01PAGCON().trim()%>
			  </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center">
				<%=payDoc.getE01PAGOBK().trim()%> 
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
				<%=payDoc.getE01PAGOBR().trim()%>
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <%=payDoc.getE01PAGOCY().trim()%></div>
            </td>
            <td nowrap width="30%"> 
              <div align="center">
				<%=payDoc.getE01PAGOAC().trim()%> 
              </div>
            </td>
			
          </tr>
        </table>
   </td>
   </tr>
  </table>
  <br>
  
  
</form>
</body>
</html>			
