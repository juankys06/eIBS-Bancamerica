<html>
<head>
<title>Lista de cuentas contables</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "cifList" class= "datapro.eibs.beans.JBObjList"  scope="request" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="javascript">
  function goAction(op) {

     document.forms[0].opt.value = op;
     var pg="";
     var bank="0";
     var ccy="VEB";
     var glAcc="0";
     var brn="0";
     // Get value from  GL Account selected
     for (i=0;i<document.form1.glAccount.length;i++){
        if (document.form1.glAccount[i].checked==true){
          var values= document.form1.glAccount[i].value.split('|');
          bank=values[0];
          ccy=values[1];
          glAcc=values[2];
          brn=values[3];
          break;
        }
     }       
     
    if (op== 1){
      pg="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEGL0340?SCREEN=300&E01GLMBNK="+bank+"&E01GLMGLN="+glAcc+"&E01GLMCCY="+ccy;
    }
    if (op== 2){
      pg="<%=request.getContextPath()%>/servlet/datapro.eibs.accounting.JSEGL0750?SCREEN=400&E02GLBBNK="+bank+"&E02GLBGLN="+glAcc+"&E02GLBCCY="+ccy+"&E02GLBBRN="+brn;
    }
    else if (op == 3) {
      pg="<%=request.getContextPath()%>/servlet/datapro.eibs.accounting.JSEGL0750?SCREEN=300&E02GLBBNK="+bank+"&E02GLBGLN="+glAcc+"&E02GLBCCY="+ccy+"&E02GLBBRN="+brn;
	   }
	else if (op == 4){
      pg="<%=request.getContextPath()%>/servlet/datapro.eibs.accounting.JSEGL0750?SCREEN=200&E02GLBBNK="+bank+"&E02GLBGLN="+glAcc+"&E02GLBCCY="+ccy+"&E02GLBBRN="+brn;
	   }
	else if (op == 5){
      showdiv('fechas');
	   }    
    else if ((op != '1') && (op != '2') && (op != '3') && (op != '4') && (op != '5')) {
	     alert("Please select an account number to continue.");	   
     }
     if(pg != '')
       CenterWindow(pg,600,500,2);

  }
  
  function searchHistorico(){
     hidediv('fechas');
     var complete=true;
     var pg = "";
     var bank="";
     var ccy="";
     var glAcc="";
     var brn="" ;
     var hiscyc="M"; 
     var valbth="B"; // Date type Flag  - process(B) o value(V)
     var frdte1=document.getElementById('E01FRDTE1').value; // From Date
     var frdte2=document.getElementById('E01FRDTE2').value;
     var frdte3=document.getElementById('E01FRDTE3').value;
     var todte1=document.getElementById('E01TODTE1').value; // To Date
     var todte2=document.getElementById('E01TODTE2').value;
     var todte3=document.getElementById('E01TODTE3').value;
     var frrefn=document.getElementById('E01FRREFN').value;
     var torefn=document.getElementById('E01TOREFN').value;
     var framnt=document.getElementById('E01FRAMNT').value;
     var toamnt=document.getElementById('E01TOAMNT').value;
     
     // Get value from  GL Account selected
     for (i=0;i<document.form1.glAccount.length;i++){
        if (document.form1.glAccount[i].checked==true){
          var values= document.form1.glAccount[i].value.split('|');
          bank=values[0];
          ccy=values[1];
          glAcc=values[2];
          brn=values[3];    
          break;
        }
     }
     
     if (bank == "" || ccy=="" || glAcc == "" || brn == "")
       complete=false;
     
     // Get Date type
     for (i=0;i<document.form1.E01VALBTH.length;i++){
        if (document.form1.E01VALBTH[i].checked==true){
          valbth=document.form1.E01VALBTH[i].value;
          break;
        }
     }
     
     pg="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSEGL0420?SCREEN=200&E01HISCYC="+hiscyc+"&E01TRABNK="+bank+"&E01TRABRN="+brn;
         pg=pg+"&E01TRACCY="+ccy+"&E01TRAGLN="+glAcc+"&E01VALBTH="+valbth+"&FRDTE1="+frdte1+"&FRDTE2="+frdte2+"&FRDTE3="+frdte3;
         pg=pg+"&E01TODTE1="+todte1+"&E01TODTE2="+todte2+"&E01TODTE3="+todte3+"&E01FRREFN="+frrefn;
         pg=pg+"&E01TOREFN="+torefn+"&E01FRAMNT="+framnt+"&E01TOAMNT="+toamnt;
     
     if(!complete)
       alert('Debe seleccionar una cuenta contable');
     else
        CenterWindow(pg,600,500,2);
  
}  
  
 function showAddInfo(idxRow){
   tbAddInfo.rows[0].cells[1].style.color="white";
   tbAddInfo.rows[0].cells[1].innerHTML=document.forms[0]["TXTDATA"+idxRow].value;
   if (tbAddInfo.rows[0].cells[1].filters[0])
   tbAddInfo.rows[0].cells[1].filters[0].apply();
   tbAddInfo.rows[0].cells[1].style.color="";
   if (tbAddInfo.rows[0].cells[1].filters[0])
   tbAddInfo.rows[0].cells[1].filters[0].Play();   
   for ( var i=0; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idxRow].className="trhighlight";
   adjustEquTables(headTable, dataTable, dataDiv1,1,false);
} 

function hidediv(id) {
	//safe function to hide an element with a specified id
	if (document.getElementById) { // DOM3 = IE5, NS6
		document.getElementById(id).style.display = 'none';
	}
	else {
		if (document.layers) { // Netscape 4
			document.id.display = 'none';
		}
		else { // IE 4
			document.all.id.style.display = 'none';
		}
	}
}

function showdiv(id) {
	//safe function to show an element with a specified id
		  
	if (document.getElementById) { // DOM3 = IE5, NS6
		document.getElementById(id).style.display = 'block';
	}
	else {
		if (document.layers) { // Netscape 4
			document.id.display = 'block';
		}
		else { // IE 4
			document.all.id.style.display = 'block';
		}
	}
}
   
</script>

</HEAD>

<BODY>

<h3 align="center">Cuentas G/L<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="inq_list.jsp,EGL0750"></h3>
<hr size="4">
<FORM name="form1" Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.accounting.JSEGL0750" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
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
%> <% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 
  <p>   

  <TABLE class="tbenter" width="100%">
    <TR> 
      <TD ALIGN=CENTER class=TDBKG> <a href="javascript:goAction(1)" ><b>Consulta</b></a> 
      </TD>
      <TD ALIGN=CENTER class=TDBKG> <a href="javascript:goAction(2)" ><b>Balances</b></a> 
      </TD>
       <TD ALIGN=CENTER class=TDBKG> <a href="javascript:goAction(3)" ><b>Promedios</b></a> 
      </TD>
       <TD ALIGN=CENTER class=TDBKG> <a href="javascript:goAction(4)" ><b>Presupuesto</b></a> 
      </TD>
      <TD ALIGN=CENTER class=TDBKG> <a href="javascript:goAction(5)" ><b>Estado de Cuenta 
        <br>
        Historico
       </b></a> </TD> 
      <TD ALIGN=CENTER class=TDBKG> <a href="<%=request.getContextPath()%>/pages/background.jsp" ><b>Salir</b></a> 
      </TD>
    </TR>
  </TABLE>

<br>
 
 <TABLE id="mainTable" class="tableinfo">      
 <TR> 
     <TD NOWRAP valign="top" width="100%" > 
     <TABLE id="headTable" width="100%">
     <TR id="trdark"> 
      <TH ALIGN=CENTER NOWRAP>Sel</TH>
      <TH ALIGN=CENTER NOWRAP>Sucursal</TH>
      <TH ALIGN=CENTER NOWRAP>Moneda</TH>
      <TH ALIGN=CENTER NOWRAP>Cuenta Contable</TH>
      <TH ALIGN=CENTER NOWRAP>Descripcion</TH>
      <TH ALIGN=CENTER NOWRAP>Clase</TH>
      <TH ALIGN=CENTER NOWRAP>Tipo</TH>
      <TH ALIGN=CENTER NOWRAP>Balance</TH>
     </TR>
     <%   
                cifList.initRow();
				boolean firstTime = true;
				String chk = "";
                while (cifList.getNextRow()) {
					if (firstTime) {
						firstTime = false;
						chk = "checked";
					} else {   
						chk = "";
					}
                 datapro.eibs.beans.EGL075001Message msgList = (datapro.eibs.beans.EGL075001Message) cifList.getRecord();

     %>           
                <TR>       
                  <TD NOWRAP ALIGN=CENTER width="10%">  
                      <input type="radio" id="glAccount" name="glAccount" value="<%=msgList.getE01GLBBNK() + "|" + msgList.getE01GLBCCY()+ "|" + msgList.getE01GLBGLN()+"|" + msgList.getE01GLBBRN() %>" <%=chk%>>
                  </TD>
                  <TD NOWRAP  ALIGN=CENTER width=\"10%\"><%= msgList.getE01GLBBRN() %></td>  
                  <TD NOWRAP  ALIGN=CENTER width=\"10%\"><%= msgList.getE01GLBCCY() %></td> 
                  <TD NOWRAP  ALIGN=CENTER width=\"10%\"><%= msgList.getE01GLBGLN() %></td> 
                  <TD NOWRAP  ALIGN=CENTER width=\"10%\"><%= msgList.getE01GLBDSC() %></td> 
                  <TD NOWRAP  ALIGN=CENTER width=\"10%\"><%= msgList.getE01GLBCLS() %></td>
                  <TD NOWRAP  ALIGN=CENTER width=\"10%\"><%= msgList.getE01GLBATY() %></td>
                  <TD NOWRAP  ALIGN=RIGHT width=\"10%\"><%= msgList.getE01GLBBAL() %></td>   
                  
                </TR>    		
    <%               
                }
    %> 
   </table>
   </TD>
   
  </TR>	
 </TABLE> 


<BR>
<TABLE class="tbenter" WIDTH="100%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
<%
        if ( cifList.getShowPrev() ) {
      			int pos = cifList.getFirstRec() - 13;
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.accounting.JSEGL0750?SCREEN=3&Pos=" + pos + "\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/previous_records.gif\" ></A>");
        }
  %>  
  </TD>
  <TD WIDTH="50%" ALIGN=RIGHT>     
 <%      
        if ( cifList.getShowNext() ) {
      			int pos = cifList.getLastRec();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.accounting.JSEGL0750?SCREEN=3&Pos=" + pos + "\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/next_records.gif\" ></A>");
        }
   %> 
   </TD>
 	</TR>
 	</TABLE> 
 <%           
  }
%>

<div id="fechas" style="display:none; position:absolute; width:200px; top:50px; left:150px;" >
  <table class="tableinfo">
    <tr > 
      <td nowrap height="143"> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="32%"> 
              <div align="right"><b>Seleccionar Transacci&oacute;n por :</b></div>
            </td>
            <td nowrap width="3%">&nbsp;</td>
            <td nowrap width="23%"> 
              <input type="radio" name="E01HISCYC" value="M" checked>
              <b>Este Ciclo</b></td>
            <td nowrap width="10%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="32%"> 
              <input type="radio" name="E01HISCYC" value="H">
              <b>Hist&oacute;rico</b></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="32%"> 
              <div align="right"><b>Tipo de Fecha :</b></div>
            </td>
            <td nowrap width="3%">&nbsp;</td>
            <td nowrap width="23%"> 
              <input type="radio" value="B" name="E01VALBTH" checked>
              <b> Fecha Proceso</b></td>
            <td nowrap width="10%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="32%"> 
              <input type="radio" value="V" name="E01VALBTH" >
              <b>Fecha Valor</b></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="32%"> 
              <div align="right"><b>Seleccionar Fechas desde :</b></div>
            </td>
            <td nowrap width="3%">&nbsp;</td>
            <td nowrap width="23%"> 
              <div align="left"> 
                <input type="text" name="E01FRDTE1" size="2" maxlength="2">
                <input type="text" name="E01FRDTE2" size="2" maxlength="2">
                <input type="text" name="E01FRDTE3" size="2" maxlength="2">
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="right"><b>hasta :</b></div>
            </td>
            <td nowrap width="32%"> 
              <div align="left"> 
                <input type="text" name="E01TODTE1" size="2" maxlength="2">
                <input type="text" name="E01TODTE2" size="2" maxlength="2">
                <input type="text" name="E01TODTE3" size="2" maxlength="2">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="32%"> 
              <div align="right"><b>Seleccionar Referencia desde :</b></div>
            </td>
            <td nowrap width="3%">&nbsp;</td>
            <td nowrap width="23%"> 
              <div align="left"> 
                <input type="text" name="E01FRREFN" size="9" maxlength="9">
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="right"><b>hasta :</b></div>
            </td>
            <td nowrap width="32%"> 
              <div align="left"> 
                <input type="text" name="E01TOREFN" size="9" maxlength="9">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="32%" height="26"> 
              <div align="right"><b>Seleccionar Montos desde :</b></div>
            </td>
            <td nowrap width="3%" height="26">&nbsp;</td>
            <td nowrap width="23%" height="26"> 
              <div align="left"> 
                <input type="text" name="E01FRAMNT" size="15" maxlength="15" onKeypress="enterDecimal()">
              </div>
            </td>
            <td nowrap width="10%" height="26"> 
              <div align="right"><b>hasta :</b></div>
            </td>
            <td nowrap width="32%" height="26"> 
              <div align="left"> 
                <input type="text" name="E01TOAMNT" size="15" maxlength="15" onKeypress="enterDecimal()">
              </div>
            </td>   
          </tr>
          <tr>
            <td colspan="4">
              <div align="center"> 
               <input id="subHistorico" type="button" name="subHistorico" value="Enviar" onclick="searchHistorico();" />
               <input id="clear" type="button" name="clear" value="Cerrar" onclick="hidediv('fechas');" />
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</div>

</FORM>

</BODY>
</HTML>
