<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Change Attribute</title>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "chkList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "dataCR" class= "datapro.eibs.beans.DataCheckReject"   scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<% 
    String m = "";
	String type = "";
    m = request.getParameter("CurrRow");
	type = request.getParameter("TYPE");
	if (type == null) {type="";}
    if (m != null) {
       int recnum = 0;
       try { recnum = Integer.parseInt(m); } catch (Exception e) {};
	chkList.setCurrentRow(recnum);
    }
    else {
	m = "";
	chkList.setCurrentRow(0);
    }
%>

<SCRIPT LANGUAGE="JavaScript" >

function enterInfo() {
  var row=<%= m %>;
  var form = top.opener.document.forms[0];
  form["NEWACC_" + row].value = trim(document.forms[0].NEWACC.value);
  form["NEWCHK_" + row].value = trim(document.forms[0].NEWCHK.value);
  form["NEWRS1_" + row].value = trim(document.forms[0].NEWRS1.value);
  form["NEWRS2_" + row].value = trim(document.forms[0].NEWRS2.value);
  form["NEWRS3_" + row].value = trim(document.forms[0].NEWRS3.value);
  form["NEWRS4_" + row].value = trim(document.forms[0].NEWRS4.value);
  form["NEWGL_" + row].value = trim(document.forms[0].NEWGL.value);
  top.close();
}

</SCRIPT>
 
</head>
<body>
<form>

  <h4 style="text-align:center">Cambio de Atributos</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap > 
        <table cellspacing=2 cellpadding=2 width="100%" border="0">
          <tr id="trdark">
			<td nowrap > 
              <div align="center">Atributo</div>
            </td> 
            <td nowrap > 
              <div align="center">Actual</div>
            </td>
            <td nowrap > 
              <div align="center">Nuevo</div>
            </td>
          </tr>
          <tr id="trclear"> 
			<td nowrap id="trdark"> 
              <div align="center"><b>Mayor General</b></div>
            </td>
            <td nowrap > 
			<div align="left">
              <input type="text" name="CURGL" size="17" maxlength="16" value="<%= chkList.getRecord(29) %>" readonly>
			</div>
            </td>
            <td nowrap > 
			<div align="left">
              <input type="text" name="NEWGL" size="17" maxlength="16" value="<%= request.getParameter("GL") %>" onKeypress="enterInteger()" >
              <a href="javascript:GetLedger('NEWGL','<%= chkList.getRecord(18) %>','<%= chkList.getRecord(25) %>','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absbottom" border="0"  ></a>
			</div>
            </td>
          </tr>
		<tr id="trclear">
			<td nowrap id="trdark"> 
              <div align="center"><b>Referencia/Cuenta</b></div>
            </td>
            <td nowrap> 
			<div align="left">
              <input type="text" name="CURACC" size="13" maxlength="12" value="<%= chkList.getRecord(0) %>" readonly>
			</div>
            </td>
            <td nowrap > 
			<div align="left">
              <input type="text" name="NEWACC" size="13" maxlength="12" value="<%= request.getParameter("Acc") %>" onKeypress="enterInteger()" >
              <a href="javascript:GetAccount('NEWACC','<%= chkList.getRecord(18) %>','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absbottom" border="0"  ></a>
			</div>
            </td>
          </tr>
		<tr id="trclear"> 
            <td nowrap id="trdark">
			  <div align="center"><b>Número Cheque </b></div>
			</td>
			<td nowrap  > 
			<div align="left">
              <input type="text" name="CURCHK" size="12" maxlength="9" value="<%= chkList.getRecord(2) %>" readonly>
			</div>
            </td>
            <td nowrap > 
			<div align="left">
              <input type="text" name="NEWCHK" size="12" maxlength="9" value="<%= request.getParameter("Chk") %>" onKeypress="enterInteger()" >
			</div>
            </td>
		</tr>
		<tr id="trclear">
 			<td nowrap id="trdark" rowspan=4> 
              <div align="center"><b>Causal</b></div>
            </td>
            <td nowrap  > 
			<div align="left">
              <input type="text" name="CURRS1" size="35" maxlength="30" value="<%= chkList.getRecord(5) %>" readonly>
			</div>
            </td>
            <td nowrap > 
			<div align="left">
              <input type="text" name="NEWRS1" size="5" maxlength="4" value="<%= request.getParameter("Rs1") %>">
              <a href="javascript:GetCasualTable('NEWRS1','CURRS1')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"  ></a>
			</div>
            </td>
          </tr>
          <tr id="trclear">
			<td nowrap > 
			<div align="left">
              <input type="text" name="CURRS2" size="35" maxlength="30" value="<%= chkList.getRecord(6) %>" readonly>
			</div>
            </td>
            <td nowrap > 
			<div align="left">
              <input type="text" name="NEWRS2" size="5" maxlength="4" value="<%= request.getParameter("Rs2") %>">
              <a href="javascript:GetCasualTable('NEWRS2','CURRS2')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"  ></a>
			</div>
            </td>
          </tr>
          <tr id="trclear"> 
			
            <td nowrap > 
			<div align="left">
              <input type="text" name="CURRS3" size="35" maxlength="30" value="<%= chkList.getRecord(7) %>" readonly>
			</div>
            </td>
            <td nowrap > 
			<div align="left">
              <input type="text" name="NEWRS3" size="5" maxlength="4" value="<%= request.getParameter("Rs3") %>">
              <a href="javascript:GetCasualTable('NEWRS3','CURRS3')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"  ></a>
			</div>
            </td>
          </tr>
          <tr id="trclear">
			
            <td nowrap > 
			<div align="left">
              <input type="text" name="CURRS4" size="35" maxlength="30" value="<%= chkList.getRecord(8) %>" readonly>
			</div>
            </td>
            <td nowrap > 
			<div align="left">
              <input type="text" name="NEWRS4" size="5" maxlength="4" value="<%= request.getParameter("Rs4") %>">
              <a href="javascript:GetCasualTable('NEWRS4','CURRS4')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"  ></a>
			</div>
            </td>
          </tr> 
	 </table> 
	</td>
    </tr>
  </table>
  <p>
    <div align="center"> 
      <input id="EIBSBTN" type=button name="Submit" OnClick="enterInfo()" value="Enviar">
    </div>
  </p>
<SCRIPT>
		document.forms[0].NEWGL.focus();
   		document.forms[0].NEWGL.select();
</SCRIPT>  
</form>
</body>
</html>			
