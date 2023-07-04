<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Mantenimiento de Detalle de Garantías</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "collList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 
        int row;
		try {
		  	row = Integer.parseInt(request.getParameter("ROW"));
		} 
		catch (Exception e) {
			row = 0;
		}
		collList.setCurrentRow(row);
   
%>


<h3 align="center">Mantenimiento de Otras Inscripciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ga_maint_detail.jsp , ERA0011"></h3> 
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0011" >
  <input type=HIDDEN name="SCREEN" value="5">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="2">
  <input type=hidden name="E02RODREF" value="<%= userPO.getIdentifier().trim()%>">
  <input type=hidden name="E02RODBNK" value="<%= userPO.getBank().trim()%>">
  <input type=hidden name="E02RODSEQ" value="<%= collList.getRecord(0).trim()%>">
                
  <input type=hidden name="ROW" value="<%= row %>">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <%= userPO.getCusNum().trim() %>
              </div>
            </td>
            <td nowrap> 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <%= userPO.getCusName().trim()%>
              </div>
            </td>
            <td nowrap > 
              <div align="right"><b>Referencia :</b></div>
            </td>
            <td nowrap> 
              <%= userPO.getIdentifier().trim()%>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
    <h4>Informaci&oacute;n B&aacute;sica</h4>
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Cliente :</div>
            </td>
            <td nowrap>
              <INPUT type="text" name="E02RODCUN" size="11" maxlength="10" value="<%= collList.getRecord(16).trim()%>">
              <A href="javascript:GetCustomerDescId('E02RODCUN','E02RODDSC','')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></A>
            </td>
            <td nowrap > 
              <div align="right">Nombre :</div>
            </td>
            <td nowrap >
              <INPUT type="text" name="E02RODDSC" size="36" maxlength="35" value="<%= collList.getRecord(2).trim()%>">
            </td>
          </tr>
          
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Valor Limite :</div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E02RODAMT" size="15" maxlength="15" onKeypress="enterDecimal()" value="<%= collList.getRecord(3) %>">
              </div>
            </td>
            <td nowrap > 
              <div align="right">Grado :</div>
            </td>
            <td nowrap>
              <input type="text" name="E02RODUC1" size="6" maxlength="4" value="<%= collList.getRecord(10) %>">
              <A href="javascript:GetCodeCNOFC('E02RODUC1','X0')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></A> </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Limitaciones :</div>
            </td>
            <td nowrap >
              <input type="text" name="E02RODUC2" size="6" maxlength="4" onKeyPress="enterDecimal()" value="<%= collList.getRecord(11) %>">
              <a href="javascript:GetCodeCNOFC('E02RODUC2','2J')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></A>
            </td>
            <td nowrap > 
              <div align="right">Estado del Beneficiario :</div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E02RODEST" size="2" maxlength="1" value="<%= collList.getRecord(17) %>">
                <A href="javascript:GetCode('E02RODEST','STATIC_coll_help_benef.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"  ></a>            
              </div>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap > 
              <div align="right">Oficina Propietaria : </div>
            </td>
            <td nowrap colspan=3>
              <INPUT type="text" name="E02RODOFN" size="6" maxlength="4" value="<%= collList.getRecord(25).trim()%>">
              <a href="javascript:GetBranch('E02RODOFN','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"  ></a>                         
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  <% if (!userPO.getType().equals("04")) { %>
   <h4>Datos Notaria / Conservador</h4>
    <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">          
   		  <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Fecha Ecritura :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E02RODOD1" size="3" maxlength="2" onKeypress="enterInteger()" value="<%= collList.getRecord(7) %>">
              <input type="text" name="E02RODOD2" size="3" maxlength="2" onKeypress="enterInteger()" value="<%= collList.getRecord(8) %>">
              <input type="text" name="E02RODOD3" size="3" maxlength="2" onKeypress="enterInteger()" value="<%= collList.getRecord(9) %>">
            </td>
            <td nowrap> 
              <div align="right">Notaria :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E02RODRF4" size="4" maxlength="3" value="<%= collList.getRecord(14) %>">
              <a href="javascript:GetTypeBroker('E02RODRF4','N')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"  ></a>                        
            </td>
          </tr>
        <% if (!userPO.getType().equals("05")) { %>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Folio de Constitución :</div>
            </td>
            <td nowrap >               
              <INPUT type="text" name="E02RODRF2" size="20" maxlength="20" value="<%= collList.getRecord(13).trim()%>" onKeyPress="enterInteger()"> 
            </td>
            <td nowrap > 
              <div align="right">Número Constitución :</div>
            </td>
            <td nowrap >               
              <INPUT type="text" name="E02RODNBR" size="20" maxlength="20" value="<%= collList.getRecord(1).trim()%>" onKeyPress="enterInteger()"> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Año Constitución :</div>
            </td>
            <td nowrap >               
              <INPUT type="text" name="E02RODAIN" size="5" maxlength="4" value="<%= collList.getRecord(15).trim()%>" onKeyPress="enterInteger()"> 
            </td>
            <td nowrap > 
              <div align="right">Conserv. Constitución :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E02RODUC7" size="5" maxlength="4" value="<%= collList.getRecord(12).trim()%>" >
              <a href="javascript:GetCodeCNOFC('E02RODUC7','2K')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"  ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Folio de Prohibición :</div>
            </td>
            <td nowrap >               
              <INPUT type="text" name="E02RODFPH" size="30" maxlength="30" value="<%= collList.getRecord(23).trim()%>"> 
            </td>
            <td nowrap > 
              <div align="right">Número Prohibición :</div>
            </td>
            <td nowrap >               
              <INPUT type="text" name="E02RODNPH" size="20" maxlength="20" value="<%= collList.getRecord(21).trim()%>"> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Año Prohibición :</div>
            </td>
            <td nowrap >               
              <INPUT type="text" name="E02RODYPH" size="5" maxlength="4" value="<%= collList.getRecord(24).trim()%>"> 
            </td>
            <td nowrap > 
              <div align="right">Conserv. Prohibición :</div>
            </td>
            <td nowrap >               
              <INPUT type="text" name="E02RODCPH" size="5" maxlength="4" value="<%= collList.getRecord(22).trim()%>"> 
              <a href="javascript:GetCodeCNOFC('E02RODCPH','2K')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"  ></a> 
            </td>
          </tr>          
         <% }%>

         <% if (userPO.getType().equals("03")) { %>
           <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Fecha Pub. Diaria :</div>
            </td>
            <td nowrap >               
             <INPUT type="text" name="E02RODPD1" size=2 maxlength=2 value="<%= collList.getRecord(4).trim()%>" onKeyPress="enterInteger()">
             <INPUT type="text" name="E02RODPD2" size=2 maxlength=2 value="<%= collList.getRecord(5).trim()%>" onKeyPress="enterInteger()">
             <INPUT type="text" name="E02RODPD3" size=2 maxlength=2 value="<%= collList.getRecord(6).trim()%>" onKeyPress="enterInteger()">
            </td>
            <td nowrap > 
              <div align="right">Fecha Notificación :</div>
            </td>
            <td nowrap >               
             <INPUT type="text" name="E02RODND1" size=2 maxlength=2 value="<%= collList.getRecord(18).trim()%>" onKeyPress="enterInteger()">
             <INPUT type="text" name="E02RODND2" size=2 maxlength=2 value="<%= collList.getRecord(19).trim()%>" onKeyPress="enterInteger()">
             <INPUT type="text" name="E02RODND3" size=2 maxlength=2 value="<%= collList.getRecord(20).trim()%>" onKeyPress="enterInteger()">
            </td>
          </tr>
         <% }%>
   </table>
      </td>
    </tr>
  </table>
  <% }%>

  <div align="center"><input id="EIBSBTN" type=submit name="Submit" value="Enviar"></div>

  </form>
</body>
</html>
