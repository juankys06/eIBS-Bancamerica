
<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Transacciones en Prestamos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="lnGenTran" class="datapro.eibs.beans.EDL015230Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
   builtNewMenu(ln_a_opt);

function InquiryCheck(chk,lns1,ridx1) {
		var ckn = chk;
		var loan1  = lns1;
		InqCheck(chk,lns1);

 }      
</SCRIPT>


</head>

<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

  out.println("<SCRIPT> initMenu(); </SCRIPT>");

%> 
<h3 align="center">Transacciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ln_ap_new_transac,EDL0140"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0150" >
  <input type=HIDDEN name="SCREEN" value="26">
  <input type=HIDDEN name="E30DEABNK"  value="<%= lnGenTran.getE30DEABNK().trim()%>">
  <input type=HIDDEN name="E30DEACCY"  value="<%= lnGenTran.getE30DEACCY().trim()%>">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" readonly  name="E30DEACUN" size="9" maxlength="9" value="<%= lnGenTran.getE30DEACUN().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" readonly  name="E30CUSNA1" size="45" maxlength="45" value="<%= lnGenTran.getE30CUSNA1().trim()%>" >
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" readonly  name="E30DEAACC" size="12" maxlength="12" value="<%= lnGenTran.getE30DEAACC().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" readonly  name="E30DEACCY2" size="3" maxlength="3" value="<%= lnGenTran.getE30DEACCY().trim()%>" >
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" readonly  name="E30DEAPRO" size="4" maxlength="4" value="<%= lnGenTran.getE30DEAPRO().trim()%>" >
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <%
	String flag = lnGenTran.getH30FLGWK3();
%> <%@ include file="ESD0840_reevaluation_inquiry.jsp" %> 
  <h4>Distribuci&oacute;n del Pago</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="right">VALOR DEL PRESTAMO :</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" readonly  size="15" maxlength="13" name="E30TRNPRI" value="<%= lnGenTran.getE30TRNPRI().trim()%>" onKeyPress="enterDecimal()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="right">INTERESES PREPAGADOS :</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" readonly  size="15" maxlength="13" name="E30TRNINT" value="<%= lnGenTran.getE30TRNINT().trim()%>" onKeyPress="enterDecimal()">
            </td>
          </tr>
          <%
          String name="";
          int j=1;
          for (int i= 1;i<26;i++){
           if (i< 10) name="0"+i; else name="" +i;
		   if(!lnGenTran.getField("E30PDSC"+name).getString().trim().equals("")){
		     j=i-j;
		 %> 
          <tr id="<% if (j==0) out.print("trdark"); else { j=i+1; out.print("trclear");}%>"> 
            <td nowrap colspan="2"> 
              <div align="right"><%= lnGenTran.getField("E30PDSC"+name).getString().trim()%> 
                : </div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" readonly  size="15" maxlength="13" name="E30PAMT<%=name%>" value="<%= lnGenTran.getField("E30PAMT"+name).getString().trim()%>">
            Oficina:
              <input type="text" id="txtleft" readonly size="4" maxlength="3" name="E30PBRN<%=name%>" value="<%= lnGenTran.getField("E30PBRN"+name).getString().trim()%>">            
            </td>
          </tr>
          <%
		    }
		   }
		  %> 
		  <% if (!lnGenTran.getE30DEDFIN().trim().equals("0")) { %>
		  <tr id="trdark">
            <td nowrap colspan="2"> 
              <div align="right"><b>Total Cargos :</b></div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" id="txtleft" style="color:red;" readonly name="E30SUBTOT" size="15" maxlength="15" value="<%= lnGenTran.getE30SUBTOT().trim()%>">
            </td>
            <td nowrap>&nbsp; </td>		  
		  </tr>    	  
    	  
		  <tr id="trdark">
            <td nowrap colspan="2"> 
              <div align="right"><b>Cargos Financiados :</b></div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" id="txtleft" style="color:red;" readonly name="E30DEDFIN" size="15" maxlength="15" value="<%= lnGenTran.getE30DEDFIN().trim()%>">
            </td>
            <td nowrap>&nbsp; </td>		  
		  </tr>    	  
		  <% } %>
		  
		  
          <tr id="<% if (j==0) out.print("trclear"); else out.print("trdark");%>"> 
            <td nowrap colspan="2"> 
              <div align="right"><b>Total Desembolso :</b></div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" readonly  size="15" maxlength="13" name="E30TRNTOT" value="<%= lnGenTran.getE30TRNTOT().trim()%>" onKeyPress="enterDecimal()">
            </td>
          </tr>
           <tr id="trdark" > 
            <td nowrap colspan="3" colspan="2"> 
              <div align="right"><b>Total Desembolso mas Cargos Financiados :</b></div>
            </td>
            <td nowrap width="18%">    
             <div align="center">         	
              	<% out.println(
              		Util.formatCCY(lnGenTran.getBigDecimalE30TRNTOT().add(lnGenTran.getBigDecimalE30DEDFIN()).toString()));%> 
			</div>
            </td>
            <td nowrap>&nbsp; </td>
          </tr>
          
          
        </table>
      </td>
    </tr>
  </table>
  <h4> 
    <h4>Cuenta de Desembolso</h4>
  </h4>
  <table id="mainTable" class="tableinfo"  >
    <tr  >
      <td  > 
        <table id="headTable" >
          <tr id="trdark"> 
            <td nowrap align="center" > Concepto</td>
            <td nowrap align="center" >Banco </td>
            <td nowrap align="center" >Sucursal</td>
            <td nowrap align="center" >Moneda</td>
            <td nowrap align="center" >Referencia</td>
            <td nowrap align="center" >Titular</td>
            <td nowrap align="center" >Monto</td>
          </tr>
        </table>
        <div id="dataDiv" style="height:60; overflow-y :scroll; z-index:0" > 
          <table id="dataTable" >
            <%
  				   int amount = 9;
 				   name = "";
  					for ( int i=1; i<=amount; i++ ) {
   					  name = i + "";
   			%> 
            <tr id="trclear"> 
              <td nowrap > 
                <div align="center" nowrap> 
                  <input type="text" readonly  name="E30OFFOP<%= name %>" value="<%= lnGenTran.getField("E30OFFOP"+name).getString().trim()%>" size="3" maxlength="3">
                  <input type="hidden" name="E30OFFGL<%= name %>" value="<%= lnGenTran.getField("E30OFFGL"+name).getString().trim()%>">
                  <input type="text" readonly  name="E30OFFCO<%= name %>" size="25" maxlength="25" bene value="<%= lnGenTran.getField("E30OFFCO"+name).getString().trim()%>" 
                  oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E30DEABNK.value,'','E30OFFGL<%= name %>','E30OFFOP<%= name %>','10')">
                </div>
              </td>
              <td nowrap > 
                <div align="center"> 
                  <input type="text" readonly  name="E30OFFBK<%= name %>" size="2" maxlength="2" value="<%= lnGenTran.getField("E30OFFBK"+name).getString().trim()%>">
                </div>
              </td>
              <td nowrap > 
                <div align="center"> 
                  <input type="text" readonly  name="E30OFFBR<%= name %>" size="3" maxlength="3" value="<%= lnGenTran.getField("E30OFFBR"+name).getString().trim()%>"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E30DEABNK.value,'','','','')">
                </div>
              </td>
              <td nowrap  > 
                <div align="center"> 
                  <input type="text" readonly  name="E30OFFCY<%= name %>" size="3" maxlength="3" value="<%= lnGenTran.getField("E30OFFCY"+name).getString().trim()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E30DEABNK.value,'','','','')">
                </div>
              </td>
              <td nowrap > 
                <div align="center"> 
                  <input type="text" readonly  name="E30OFFAC<%= name %>" size="12" maxlength="12"  value="<%= lnGenTran.getField("E30OFFAC"+name).getString().trim()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E30DEABNK.value,'','','','RT')">
                </div>
              </td>
              
              <td nowrap  > 
                <div align="center"> 
                  <input type="text" readonly  name="E30OFFNA<%= name %>" size="25" maxlength="25"  value="<%= lnGenTran.getField("E30OFFNA"+name).getString().trim()%>">
                </div>
              </td>
              


              <td nowrap  > 
                <div align="center"> 
                  <input type="text" readonly  name="E30OFFAM<%= name %>" size="15" maxlength="15"  value="<%= lnGenTran.getField("E30OFFAM"+name).getString().trim()%>" onKeyPress="enterDecimal()">
                </div>
              </td>
            </tr>
            <%
    		}
    		%> 
          </table>
        </div>
        <table id="footTable" align="center" width="100%" >
          <tr id="trdark" > 
            <td align="right" ><b>Equivalente Moneda del Préstamo : </b> 
              <input type="text" readonly  name="E30OFFEQV" size="15" maxlength="15" bene value="<%= lnGenTran.getE30OFFEQV().trim()%>">
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <script language="javascript">
   function tableresize() {
     adjustEquTables( headTable, dataTable, dataDiv, 0, false );
   }
  tableresize();
  window.onresize=tableresize; 
  </script>



<%if(lnGenTran.getField("E30OFFCHC").getString().trim().equals("Y")){ %>
    <h4>Cheques de Gerencia Originados en Desembolso</h4>
  
 
 <TABLE  id="mainTableChk" class="tableinfo" >
 <TR> 
   <TD NOWRAP valign="top" width="100%">
    <TABLE id="headTableChk" >
    <TR id="trdark"> 
      <td nowrap align="center">Consultar</td>
      <td nowrap align="center">Numero de Cheque</td>
      <td nowrap align="center">Beneficiario</td>
      <td nowrap align="center">Monto</td>
      <td nowrap align="center">Estado</td>
    </TR>
    </TABLE>
  
    <div id="dataDiv1Chk" class="scbarcolor" style="padding:0">
   
   
   
    <table id="dataTableChk"  >

<%--<TABLE class="tbenter">
  <TR> 
    <TD ALIGN=CENTER CLASS=tdbkg><a href="javascript:MaintenanceCheck('<%= lnGenTran.getField("E31DLONC"+name).getString().trim()%>','<%= lnGenTran.getE30DEAACC().trim()%>','name')">Modificar</a></TD>
    <TD ALIGN=CENTER CLASS=tdbkg><a href="javascript:goAction('4')">Eliminar</a></TD>
  </TR>
</TABLE>--%>


	<%	int limit = 9;
		String idx="";
		for ( int z=1; z<=limit; z++ ) {
		idx = z + "";
	%>  
	<%if(!lnGenTran.getField("E31DLOBN"+idx).getString().trim().equals("")){ %>
        <TR> 
            <TD ALIGN=CENTER NOWRAP> 
                <INPUT TYPE="radio" NAME="INQCHK" onclick="InquiryCheck('<%= lnGenTran.getField("E31DLONC"+idx).getString().trim()%>','<%= lnGenTran.getE30DEAACC().trim()%>','name')">
            </TD>
	
		    <td nowrap  > 
              <div align="center"> 
                <input type="text" readonly name="E31DLONC<%= idx %>" size="10" maxlength="9"  value="<%= lnGenTran.getField("E31DLONC"+idx).getString().trim()%>" onKeypress="enterInteger()">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" readonly name="E31DLOBN<%= idx %>" size="31" maxlength="30"  value="<%= lnGenTran.getField("E31DLOBN"+idx).getString().trim()%>">
              </div>
            </td>
            <td nowrap  > 
              <div align="center"> 
                <input type="text" readonly name="E31DLOMC<%= idx %>" size="15" maxlength="15"  value="<%= lnGenTran.getField("E31DLOMC"+idx).getString().trim()%>" onKeypress="enterDecimal()">
              </div>
            </td>
            <td nowrap  > 
              <div align="center"> 
                <input type="text" readonly name="E31DLOCO<%= idx %>" size="1" maxlength="1"  value="<%= lnGenTran.getField("E31DLOCO"+idx).getString().trim()%>">
              </div>
            </td>
		</TR>
    	<%}%>
   	<%}%>
    </table>
    </div>
        


      <table id="footTableChk" > 
	  <%if(lnGenTran.getField("E30OFFCHC").getString().trim().equals("Y")){ %>
      <tr id="trdark"> 
        <td nowrap align="center"> 
            <input type=HIDDEN name="RECALC" value="<%= lnGenTran.getH30FLGWK2().trim()%>">
        </td>
      </tr>
	  <%} else {%>
		  <INPUT TYPE=HIDDEN NAME="H30FLGWK2" VALUE=" ">
	  <%}%>
    </table>


   </TD> 
  </TR>	
 </TABLE>
 
  <SCRIPT language="JavaScript">  	
  	function resizeDocChk() {
       adjustEquTables( headTableChk, dataTableChk, dataDiv1Chk,0, false ) ;
    }
    resizeDocChk();
    window.onresize=resizeDocChk;     
  </SCRIPT>
  <%
  }
 %>
 




</form>


</body>
</html>
