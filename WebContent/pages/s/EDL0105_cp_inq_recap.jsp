<html>
<head>
<title>Security Recap</title>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EDL030701Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">

builtNewMenu(cp_i_opt);
</SCRIPT>  

</head>

<BODY>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
	 error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
	 
	 
     out.println("<SCRIPT> initMenu();  </SCRIPT>");
%> 

<h3 align="center">Sumario de Transacciones para el Instrumento<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cp_inq_recap.jsp,EDL0105"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105I" >
  <input type=HIDDEN name="SCREEN" value="10">
  <input type=HIDDEN name="totalRow" value="0">
  <INPUT TYPE=HIDDEN NAME="TOTROWS" VALUE="0">
  
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>No. Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E12DEACUN" size="9" maxlength="9" readonly value="<%= userPO.getCusNum().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E12CUSNA1" size="45" maxlength="45" readonly value="<%= userPO.getCusName().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E12DEAACC" size="12" maxlength="9" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E12DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E12DEAPRO" size="4" maxlength="4" readonly value="<%= userPO.getProdCode().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4> Información Básica</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Tipo de Revalorización :</div>
            </td>
            <td nowrap > 
            	<input type="text" name="E01DEARRT" size="30" readonly 
             		value="<% if(userPO.getHeader9().equals("1")) out.print("Reval Gain & Losses Daily");
             			   else if (userPO.getHeader9().equals("2")) out.print("Reval Only Losses Daily");
             			   else if (userPO.getHeader9().equals("3")) out.print("Reval Gain & Losses Monthly");
             			   else if (userPO.getHeader9().equals("4")) out.print("Reval Only Losses Monthly");
             			   else if (userPO.getHeader9().equals("N")) out.print("No Revaluation");%>" >             
            </td>
            <td nowrap > 
              <div align="right">Precio Promedio de Inventario :</div>
            </td>
            <td nowrap > 
				<input type="text" name="E01BIDPRC" size="15" maxlength="13" value="<%= userPO.getHeader10().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Monto del Valor Nominal :</div>
            </td>
            <td nowrap > 
				<input type="text" name="E01DEAPRI" size="15" maxlength="13" value="<%= userPO.getHeader3().trim()%>" readonly>            
			</td>
            <td nowrap > 
              <div align="right">Precio Comercial :</div>
            </td>
            <td nowrap > 
				<input type="text" name="E01DEAMVL" size="15" maxlength="13" value="<%= userPO.getHeader11().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Ajustar Valor Comercial :</div>
            </td>
            <td nowrap > 
				<input type="text" name="E01MKTADJ" size="15" maxlength="13" value="<%= userPO.getHeader4().trim()%>" readonly>            
			</td>
            <td nowrap > 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAMD1" size="2" maxlength="2" value="<%= userPO.getHeader12().trim()%>" readonly>
              <input type="text" name="E01DEAMD2" size="2" maxlength="2" value="<%= userPO.getHeader13().trim()%>" readonly>
              <input type="text" name="E01DEAMD3" size="2" maxlength="2" value="<%= userPO.getHeader14().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Saldo Plusvalía/Descuento  :</div>
            </td>
            <td nowrap > 
				<input type="text" name="E01UNAMOR" size="15" maxlength="13" value="<%= userPO.getHeader5().trim()%>" readonly>            
			</td>
            <td nowrap > 
              <div align="right">Pago del Próximo Cupón :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEANX1" size="2" maxlength="2" value="<%= userPO.getHeader15().trim()%>" readonly>
              <input type="text" name="E01DEANX2" size="2" maxlength="2" value="<%= userPO.getHeader16().trim()%>" readonly>
              <input type="text" name="E01DEANX3" size="2" maxlength="2" value="<%= userPO.getHeader17().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Valor Contable :</div>
            </td>
            <td nowrap > 
				<input type="text" name="E01BOOKVL" size="15" maxlength="13" value="<%= userPO.getHeader6().trim()%>" readonly>            
			</td>
            <td nowrap > 
              <div align="right">Tasa Base del Cupón :</div>
            </td>
            <td nowrap > 
				<input type="text" name="E01DEARTE" size="15" maxlength="13" value="<%= userPO.getHeader18().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Valor Comercial :</div>
            </td>
            <td nowrap > 
				<input type="text" name="E01DEAFEE" size="15" maxlength="13" value="<%= userPO.getHeader7().trim()%>" readonly>            
			</td>
            <td nowrap > 
              <div align="right">Tasa Flotante del Cupón :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAFTB" readonly  size="2" maxlength="2" value="<%= userPO.getHeader19().trim()%>">
              <input type="text" name="E01DEAFTY" size="10" readonly 
             		value="<% if(userPO.getHeader20().equals("FP")) out.print("Primary");
              			   else if (userPO.getHeader20().equals("FS")) out.print("Secondary");%>" >
              <input type="text" name="E01DEAFRT" size="15" maxlength="13" value="<%= userPO.getHeader21().trim()%>" readonly>             
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Interés Acumulado :</div>
            </td>
            <td nowrap > 
				<input type="text" name="E01DEAIAL" size="15" maxlength="13" value="<%= userPO.getHeader8().trim()%>" readonly>            
			</td>
            <td nowrap > 
              <div align="right">Tasa Total del Cupón :</div>
            </td>
            <td nowrap > 
				<input type="text" name="E01TOTRTE" size="15" maxlength="13" value="<%= userPO.getHeader22().trim()%>" readonly>
            </td>
          </tr>
 
        </table>
      </td>
    </tr>
  </table>  
	<h4> Compras/Ventas</h4> 	
  <%
  
	if ( EDL030701Help.getNoResult() ) {
 %>
  <p>&nbsp;</p>
  <TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p><b>No hay datos que correspondan con su criterio de busqueda</b></p>
          </div>

	  </TD>
	</TR>
    </TABLE>
  <%  
		}
	else {
%>  
  <br>
  <TABLE class="tableinfo" id="dataTable">
    <TR id="trdark">
      <TH ALIGN=CENTER  nowrap >Operación</TH>
      <TH ALIGN=CENTER  nowrap >Nombre de Contraparte</TH>
      <TH ALIGN=CENTER  nowrap >Fecha<br>Valor</TH>      
      <TH ALIGN=CENTER  nowrap >Precio.<br>Trans.</TH>
      <TH ALIGN=CENTER  nowrap >Valor<br>Nominal</TH>
    </TR>
       <%
       	  int i = 0;
          EDL030701Help.initRow();               
          while (EDL030701Help.getNextRow()) {
               EDL030701Message msgList = (EDL030701Message) EDL030701Help.getRecord();			 
                    
       %>             
         <TR>
          <td NOWRAP align=left>
           	<%=Util.formatCell(msgList.getE01OPRTYP())%>
		  </td>		  
		  <td NOWRAP align=left>
			<%=Util.formatCell(msgList.getE01CUNNAM())%>
		  </td>	
		  <td NOWRAP align=center>
			<%=Util.formatDate(msgList.getE01DEAST1(),msgList.getE01DEAST2(),msgList.getE01DEAST3())%>
		  </td>		  
		  <td NOWRAP align=center>
			<%=Util.formatCell(msgList.getE01TRAPRC())%>
		  </td>		  
		  <td NOWRAP align=right>
			<%=Util.formatCell(msgList.getE01DEAFVA())%>		  
		  </td>	
		</TR>
        <% 
        	i++;
        } 
        %> 
  </TABLE>
  <SCRIPT Language="javascript">
	document.forms[0].TOTROWS.value = <%= i%>;
  </SCRIPT>
  
   <%}%>
  
  
</form>

</body>
</html>
