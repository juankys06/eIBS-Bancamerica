<html>
<head>
<title>Security Recap</title>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EDL030701Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id="msgRec" class="datapro.eibs.beans.EDL030701Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">
	builtNewMenu(plpt_i_opt);
	initMenuT();
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

<h3 align="center">Sumario de Transacciones para el Instrumento<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cp_inq_recap.jsp,EDL0160T"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130IT" >
  <input type=HIDDEN name="SCREEN" value="10">
  <input type=HIDDEN name="totalRow" value="0">
  <INPUT TYPE=HIDDEN NAME="TOTROWS" VALUE="0">
  
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="14%" > 
              <div align="right"><b>No. Cliente :</b></div>
            </td>
            <td nowrap width="9%" > 
              <div align="left"> 
                <input type="text" name="E02CUN2" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E02NA12" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap ><b> 
              <input type="text" name="E02PRO2" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
              </b></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="14%"> 
              <div align="right"><b>Contrato :</b></div>
            </td>
            <td nowrap width="9%"> 
              <div align="left"> 
                <input type="text" name="E02ACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="12%"> 
              <div align="right"><b>Oficial :</b></div>
            </td>
            <td nowrap width="33%"> 
              <div align="left"><b> 
                <input type="text" name="E02NA122" size="30" maxlength="30" readonly value="<%= userPO.getOfficer().trim()%>">
                </b> </div>
            </td>
            <td nowrap width="11%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="21%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
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
             		value="<% if(msgRec.getE01DEARRT().equals("1")) out.print("Ganancia & Perdida Diaria");
             			   else if (msgRec.getE01DEARRT().equals("2")) out.print("Solo Perdida Diaria");
             			   else if (msgRec.getE01DEARRT().equals("3")) out.print("Ganancia & Perdida Mensual");
             			   else if (msgRec.getE01DEARRT().equals("4")) out.print("Solo Perdidadas Mensual");
             			   else if (msgRec.getE01DEARRT().equals("N")) out.print("No Revaloriza");%>" >             
            </td>
            <td nowrap > 
              <div align="right">Precio Promedio de Inventario :</div>
            </td>
            <td nowrap > 
				<input type="text" name="E01BIDPRC" size="15" maxlength="13" value="<%= msgRec.getE01BIDPRC().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Monto del Valor Nominal :</div>
            </td>
            <td nowrap > 
				<input type="text" name="E01DEAPRI" size="15" maxlength="13" value="<%= msgRec.getE01DEAPRI().trim()%>" readonly>            
			</td>
            <td nowrap > 
              <div align="right">Precio Comercial :</div>
            </td>
            <td nowrap > 
				<input type="text" name="E01DEAMVL" size="15" maxlength="13" value="<%= msgRec.getE01DEAMVL().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Ajustar Valor Comercial :</div>
            </td>
            <td nowrap > 
				<input type="text" name="E01MKTADJ" size="15" maxlength="13" value="<%= msgRec.getE01MKTADJ().trim()%>" readonly>            
			</td>
            <td nowrap > 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAMD1" size="2" maxlength="2" value="<%= msgRec.getE01DEAMD1().trim()%>" readonly>
              <input type="text" name="E01DEAMD2" size="2" maxlength="2" value="<%= msgRec.getE01DEAMD2().trim()%>" readonly>
              <input type="text" name="E01DEAMD3" size="2" maxlength="2" value="<%= msgRec.getE01DEAMD3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Saldo Plusvalía/Descuento :</div>
            </td>
            <td nowrap > 
				<input type="text" name="E01UNAMOR" size="15" maxlength="13" value="<%= msgRec.getE01UNAMOR().trim()%>" readonly>            
			</td>
            <td nowrap > 
              <div align="right">Pago del Próximo Cupón :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEANX1" size="2" maxlength="2" value="<%= msgRec.getE01DEANX1().trim()%>" readonly>
              <input type="text" name="E01DEANX2" size="2" maxlength="2" value="<%= msgRec.getE01DEANX2().trim()%>" readonly>
              <input type="text" name="E01DEANX3" size="2" maxlength="2" value="<%= msgRec.getE01DEANX3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Valor Contable :</div>
            </td>
            <td nowrap > 
				<input type="text" name="E01BOOKVL" size="15" maxlength="13" value="<%= msgRec.getE01BOOKVL().trim()%>" readonly>            
			</td>
            <td nowrap > 
              <div align="right">Tasa Base del Cupón :</div>
            </td>
            <td nowrap > 
				<input type="text" name="E01DEARTE" size="15" maxlength="13" value="<%= msgRec.getE01DEARTE().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Valor Comercial :</div>
            </td>
            <td nowrap > 
				<input type="text" name="E01DEAFEE" size="15" maxlength="13" value="<%= msgRec.getE01DEAFEE().trim()%>" readonly>            
			</td>
            <td nowrap > 
              <div align="right">Tasa Flotante del Cupón :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEAFTB" readonly  size="2" maxlength="2" value="<%= msgRec.getE01DEAFTB().trim()%>">
              <input type="text" name="E01DEAFTY" size="10" readonly 
             		value="<% if(msgRec.getE01DEAFTY().equals("FP")) out.print("Primaria");
              			   else if (msgRec.getE01DEAFTY().equals("FS")) out.print("Secundaria");%>" >
              <input type="text" name="E01DEAFRT" size="15" maxlength="13" value="<%= msgRec.getE01DEAFRT().trim()%>" readonly>             
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Interés Acumulado :</div>
            </td>
            <td nowrap > 
				<input type="text" name="E01DEAIAL" size="15" maxlength="13" value="<%= msgRec.getE01DEAIAL().trim()%>" readonly>            
			</td>
            <td nowrap > 
              <div align="right">Tasa Total del Cupón :</div>
            </td>
            <td nowrap > 
				<input type="text" name="E01TOTRTE" size="15" maxlength="13" value="<%= msgRec.getE01TOTRTE().trim()%>" readonly>
            </td>
          </tr>
 
        </table>
      </td>
    </tr>
  </table>  
	<h4>Compras/Ventas</h4> 	
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
          <p><b>No hay datos que correspondan con su criterio de busqueda </b></p>
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
