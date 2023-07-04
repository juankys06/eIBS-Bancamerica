<html> 
<head>
<title>Consulta del Apoderado</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "mtListMem" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<% 
   int row = 0;
   try { row = Integer.parseInt(request.getParameter("ROW"));} catch (Exception e) {}
   mtListMem.setCurrentRow(row);
   datapro.eibs.beans.ECU000005Message msgMT = (datapro.eibs.beans.ECU000005Message) mtListMem.getRecord();
   userPO.setHeader18(msgMT.getE05CUMMAN());
   userPO.setHeader19(msgMT.getE05CUMMA1());
   userPO.setHeader20(msgMT.getE05CUMUC5());
   userPO.setHeader21(msgMT.getE05CUMUCN());
   userPO.setHeader5(request.getParameter("ROW"));
   
%>

<SCRIPT Language="Javascript">
    builtNewMenu(bastanteo_menu_inq_2);
</SCRIPT>

<SCRIPT language="javascript">
  
  function goAction(opt) {
    
    if (opt == "R") { 
    	page = "<%=request.getContextPath()%>/pages/s/ECU0000_bastanteo_attorney_inq_list.jsp";
    	CenterNamedWindow(page,'Information',650,500,2);
  	} 
  }
  
</SCRIPT>

</head>
<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }
 	 out.println("<SCRIPT> initMenu();  </SCRIPT>");
%>

<H3 align="center">Sistema de Bastanteo - Consulta del Propietario/Apoderado<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bastanteo_apoderado_inq,ECU0000"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000">
 
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= row %>">
  <input type=hidden name="E05CUMMAN" value="01"> 
  
  <table class="tableinfo">
   <tr> 
   <td>
    <TABLE cellspacing=0 cellpadding=2 width="100%" border="0">    
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">C&oacute;digo de Cliente :</DIV>
				</TD>
				<TD nowrap width="60%">
					<INPUT type="text" name="E05CUMCUN" size="10" maxlength="9" value="<%= userPO.getHeader16() %>" readonly> 
				</TD>
			</TR>
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">Nombre o Denominaci&oacute;n Social :</DIV>
				</TD>
				<TD nowrap width="60%">
					<INPUT type="text" name="E05CUSNA1" size="40" maxlength="35" value="<%= userPO.getHeader17() %>" readonly>  
				</TD>
			</TR>
 		</TABLE>
 	  </td>
   </tr>
   </table>
   <h4></h4>
   <table class="tableinfo">	
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">
         	<tr id=trdark> 
	      <td nowrap >
				<div align="right">Nombre del Propietario: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E05CUMMA3" size="40" maxlength="35" readonly value="<%= msgMT.getE05CUMMA3() %>"> 
		  </td>
     	</tr>
     	<tr id=trclear> 
            <td nowrap width="39%"> 
              <div align="right">N&uacute;mero de Identificaci&oacute;n del Propietario:</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E05CUMCTR" size="16" maxlength="15" readonly value="<%= msgMT.getE05CUMCTR().trim()%>">
            </td>
        </tr>
     	<tr id=trdark>
     		<td nowrap width="39%"> 
              <div align="right">Tipo de Identificaci&oacute;n del Propietario:</div>
            </td>
     		<td>
              <input type="text" name="E05CUMINC" size="5" maxlength="4" readonly value="<%= msgMT.getE05CUMINC().trim()%>">
              <input type="text" name="E05CUMINN" size="40" maxlength="35" readonly value="<%= msgMT.getE05CUMINN().trim()%>">
            </td>
        </tr>
        <tr id=trclear>
        	<td nowrap width="39%"> 
              <div align="right">Pa&iacute;s del Propietario:</div>
            </td>
     		<td>
              <input type="text" name="E05CUMBNC" size="5" maxlength="4" readonly value="<%= msgMT.getE05CUMBNC().trim()%>">
              <input type="text" name="E05CUMBNN" size="40" maxlength="35" readonly value="<%= msgMT.getE05CUMBNN().trim()%>">
            </td>
        </tr>
    
     	<tr id=trdark> 
	      <td nowrap >
				<div align="right">Nombre del Apoderado: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E05CUMMA1" size="40" maxlength="35" readonly value="<%= msgMT.getE05CUMMA1() %>"> 
		  </td>
     	</tr>
     	<tr id=trclear> 
            <td nowrap width="39%"> 
              <div align="right">N&uacute;mero de Identificaci&oacute;n del Apoderado:</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E05CUMBNI" size="16" maxlength="15" readonly value="<%= msgMT.getE05CUMBNI().trim()%>">
            </td>
        </tr>
     	<tr id=trdark>
     		<td nowrap width="39%"> 
              <div align="right">Tipo de Identificaci&oacute;n del Apoderado:</div>
            </td>
     		<td>
              <input type="text" name="E05CUMTID" size="5" maxlength="4" readonly value="<%= msgMT.getE05CUMTID().trim()%>">
              <input type="text" name="E05CUMTIN" size="40" maxlength="35" readonly value="<%= msgMT.getE05CUMTIN().trim()%>">
            </td>
        </tr>
        <tr id=trclear>
        	<td nowrap width="39%"> 
              <div align="right">Pa&iacute;s del Apoderado:</div>
            </td>
     		<td>
              <input type="text" name="E05CUMPID" size="5" maxlength="4" readonly value="<%= msgMT.getE05CUMPID().trim()%>">
              <input type="text" name="E05CUMPIN" size="40" maxlength="35" readonly value="<%= msgMT.getE05CUMPIN().trim()%>">
            </td>
        </tr>
    	<tr id=trdark> 
		  <td> 
		     <div align="right">Fecha de Designaci&oacute;n: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E05CUMDD1" readonly size="2" maxlength="2" value="<%= msgMT.getE05CUMDD1() %>">
	      	<input type="text" name="E05CUMDD2" readonly size="2" maxlength="2" value="<%= msgMT.getE05CUMDD2() %>">
	      	<input type="text" name="E05CUMDD3" readonly size="2" maxlength="2" value="<%= msgMT.getE05CUMDD3() %>">
	      </td>     
      	</tr>
     	<tr id=trclear> 
		  <td> 
		     <div align="right">Fecha de Vencimiento: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E05CUMD21" size="2" maxlength="2" readonly value="<%= msgMT.getE05CUMD21() %>">
	      	<input type="text" name="E05CUMD22" size="2" maxlength="2" readonly value="<%= msgMT.getE05CUMD22() %>">
	      	<input type="text" name="E05CUMD23" size="2" maxlength="2" readonly value="<%= msgMT.getE05CUMD23() %>">
      	  </td>     
      	</tr>
     	<tr id="trdark"> 
          <td> 
             <div align="right">Tiene Reeleci&oacute;n: </div>
          </td>
          <td nowrap >
              <p> 
                 <input type="text" readonly name="E05CUMFL1" size="3" maxlength="2"
				 value="<% if (msgMT.getE05CUMFL1().equals("Y")) { out.print("Si"); }
					else if (msgMT.getE05CUMFL1().equals("N")) { out.print("No"); }
					else { out.print(""); } %>" >
              </p>
          </td>
        </tr>
        <tr id="trclear"> 
          <td> 
             <div align="right">Facultades : </div>
          </td>
          <td nowrap >
              <p> 
                 <input type="text" readonly name="E05CUMFL2" size="8" maxlength="7"
				 value="<% if (msgMT.getE05CUMFL2().equals("A")) { out.print("Algunas"); }
					else if (msgMT.getE05CUMFL2().equals("T")) { out.print("Todas"); }
					else if (msgMT.getE05CUMFL2().equals("N")) { out.print("Ninguna"); }
					else { out.print(""); } %>" >
              </p>
          </td>
        </tr>
     </table>
    </td>
   </tr>

  </table>
  
  <h4>Datos del Poder</h4>
  <table class="tableinfo">
      <tr > 
        <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
          <tr id="trdark"> 
          <td> 
		     <div align="right">Registro o Notar&iacute;a: </div>        
		  </td>
            <td nowrap width="100%"> 
              <input type="text" name="E05CUMCTY" size="30" maxlength="30" readonly value="<%= msgMT.getE05CUMCTY().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
          <td> 
		     <div align="right">Circunscripci&oacute;n: </div>        
		  </td>
          
            <td nowrap width="100%"> 
              <input type="text" name="E05CUMMA2" size="35" maxlength="35" readonly value="<%= msgMT.getE05CUMMA2().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
          <td> 
		     <div align="right">N&uacute;mero de Documento: </div>        
		  </td>

            <td nowrap width="100%"> 
              <input type="text" name="E05CUMNST" size="9" maxlength="7" readonly value="<%= msgMT.getE05CUMNST().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
          <td> 
		     <div align="right">Tomo: </div>        
		  </td>

            <td nowrap width="100%"> 
              <input type="text" name="E05CUMPOB" size="10" maxlength="8" readonly value="<%= msgMT.getE05CUMPOB().trim()%>">
            </td>
          </tr>
        <tr id=trdark> 
		  <td> 
		     <div align="right">Fecha de Tomo: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E05REGRDD" readonly size="2" maxlength="2" value="<%= msgMT.getE05REGRDD() %>">
	      	<input type="text" name="E05REGRMM" readonly size="2" maxlength="2" value="<%= msgMT.getE05REGRMM() %>">
	      	<input type="text" name="E05REGRYY" readonly size="2" maxlength="2" value="<%= msgMT.getE05REGRYY() %>">
	      </td>     
      	</tr>
     	  


  		  </table>
  		  </td>
  		</tr>
  </table>
  
  <h4>Observaciones</h4>
 
  <table class="tableinfo">
      <tr > 
        <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E05CUMOB1" size="90" maxlength="80" readonly value="<%= msgMT.getE05CUMOB1().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E05CUMOB2" size="90" maxlength="80" readonly value="<%= msgMT.getE05CUMOB2().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E05CUMOB3" size="90" maxlength="80" readonly value="<%= msgMT.getE05CUMOB3().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E05CUMOB4" size="90" maxlength="80" readonly value="<%= msgMT.getE05CUMOB4().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E05CUMOB5" size="90" maxlength="80" readonly value="<%= msgMT.getE05CUMOB5().trim()%>">
            </td>
          </tr>
        </table>
      </tr>
  </table>
  
  <p align="center"> 
    <input id="EIBSBTN" type="button" name="Submit" value="Regresar" onclick="goAction('R')">
  </p>
  
</form>
</body>
</html>
