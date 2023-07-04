<html>
<head>
<title>Clausulas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="msg" class="datapro.eibs.beans.EWP001001Message"  scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
</head>

<body bgcolor="#FFFFFF">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 
	String title = "Clausulas ["; 
	int lines = 0;
	int chars = 0;
	
	datapro.eibs.master.SwiftStructure swift = new datapro.eibs.master.SwiftStructure();
	
	if (swift.locateField(msg.getE01CLSCDE().trim())) {
		title = title + swift.getLabel("sp");
		lines = swift.getLines();
		chars = swift.getChars();
	}
	
	title = title + " (" + msg.getE01CLSCDE().trim() + ")]";
 
%> 
<h3 align="center"><%= title %>
	<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="lc_clause.jsp, EWP0010"></h3>
<hr size="4">


 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEWP0010" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="5">
   
<table class="tableinfo" width="100%">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td width="10%" align="right" nowrap>Clausula:</td>
            <td nowrap width="18%">
            	<input name="E01CLSCDE" type="text" id="E01CLSCDE" value="<%= msg.getE01CLSCDE().trim()%>" size="7" maxlength="6" readonly>
            </td>
            <td width="10%" align="right" nowrap>Texto NRW:</td>
            <td nowrap width="10%">
            	<input name="E01CLSTXN" type="text" id="E01CLSTXN" value="<%= msg.getE01CLSTXN().trim()%>" size="5" maxlength="4" readonly>
            </td>
            <td width="10%" align="right" nowrap>Descripcion:</td>
            <td nowrap width="34%">
            	<input name="E01CLSDSC" type="text" id="E01CLSDSC" value="<%= msg.getE01CLSDSC().trim()%>" size="48" maxlength="45">
            </td>
          </tr>               
        </table>
      </td>
    </tr>
  </table>
<BR>
<table class="tableinfo">
    <tr > 
      <td nowrap align="center"> 
      <div style="height:300px; overflow-y: scroll">
          <input type="text" name="E01CLSL01" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL01().trim()%>"><br>
          <input type="text" name="E01CLSL02" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL02().trim()%>"><br>
          <input type="text" name="E01CLSL03" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL03().trim()%>"><br>
      <% if (lines > 3) { %>
          <input type="text" name="E01CLSL04" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL04().trim()%>"><br>
      <% } 
         if (lines > 4) { %>
          <input type="text" name="E01CLSL05" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL05().trim()%>"><br>
          <input type="text" name="E01CLSL06" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL06().trim()%>"><br>
      <% } 
         if (lines > 6) { %>
          <input type="text" name="E01CLSL07" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL07().trim()%>"><br>
          <input type="text" name="E01CLSL08" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL08().trim()%>"><br>
          <input type="text" name="E01CLSL09" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL09().trim()%>"><br>
          <input type="text" name="E01CLSL10" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL10().trim()%>"><br>
          <input type="text" name="E01CLSL11" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL11().trim()%>"><br>
          <input type="text" name="E01CLSL12" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL12().trim()%>"><br>
      <% } 
         if (lines > 12) { %>
          <input type="text" name="E01CLSL13" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL13().trim()%>"><br>
          <input type="text" name="E01CLSL14" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL14().trim()%>"><br>
          <input type="text" name="E01CLSL15" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL15().trim()%>"><br>
          <input type="text" name="E01CLSL16" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL16().trim()%>"><br>
          <input type="text" name="E01CLSL17" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL17().trim()%>"><br>
          <input type="text" name="E01CLSL18" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL18().trim()%>"><br>
          <input type="text" name="E01CLSL19" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL19().trim()%>"><br>
          <input type="text" name="E01CLSL20" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL20().trim()%>"><br>
      <% } 
         if (lines > 20) { %>
          <input type="text" name="E01CLSL21" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL21().trim()%>"><br>
          <input type="text" name="E01CLSL22" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL22().trim()%>"><br>
          <input type="text" name="E01CLSL23" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL23().trim()%>"><br>
          <input type="text" name="E01CLSL24" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL24().trim()%>"><br>
          <input type="text" name="E01CLSL25" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL25().trim()%>"><br>
          <input type="text" name="E01CLSL26" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL26().trim()%>"><br>
          <input type="text" name="E01CLSL27" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL27().trim()%>"><br>
          <input type="text" name="E01CLSL28" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL28().trim()%>"><br>
          <input type="text" name="E01CLSL29" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL29().trim()%>"><br>
          <input type="text" name="E01CLSL30" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL30().trim()%>"><br>
          <input type="text" name="E01CLSL31" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL31().trim()%>"><br>
          <input type="text" name="E01CLSL32" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL32().trim()%>"><br>
          <input type="text" name="E01CLSL33" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL33().trim()%>"><br>
          <input type="text" name="E01CLSL34" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL34().trim()%>"><br>
          <input type="text" name="E01CLSL35" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL35().trim()%>"><br>
      <% } 
         if (lines > 35) { %>
          <input type="text" name="E01CLSL36" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL36().trim()%>"><br>
          <input type="text" name="E01CLSL37" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL37().trim()%>"><br>
          <input type="text" name="E01CLSL38" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL38().trim()%>"><br>
          <input type="text" name="E01CLSL39" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL39().trim()%>"><br>
          <input type="text" name="E01CLSL40" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL40().trim()%>"><br>
          <input type="text" name="E01CLSL41" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL41().trim()%>"><br>
          <input type="text" name="E01CLSL42" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL42().trim()%>"><br>
          <input type="text" name="E01CLSL43" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL43().trim()%>"><br>
          <input type="text" name="E01CLSL44" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL44().trim()%>"><br>
          <input type="text" name="E01CLSL45" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL45().trim()%>"><br>
          <input type="text" name="E01CLSL46" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL46().trim()%>"><br>
          <input type="text" name="E01CLSL47" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL47().trim()%>"><br>
          <input type="text" name="E01CLSL48" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL48().trim()%>"><br>
          <input type="text" name="E01CLSL49" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL49().trim()%>"><br>
          <input type="text" name="E01CLSL50" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL50().trim()%>"><br>
          <input type="text" name="E01CLSL51" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL51().trim()%>"><br>
          <input type="text" name="E01CLSL52" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL52().trim()%>"><br>
          <input type="text" name="E01CLSL53" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL53().trim()%>"><br>
          <input type="text" name="E01CLSL54" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL54().trim()%>"><br>
          <input type="text" name="E01CLSL55" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL55().trim()%>"><br>
          <input type="text" name="E01CLSL56" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL56().trim()%>"><br>
          <input type="text" name="E01CLSL57" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL57().trim()%>"><br>
          <input type="text" name="E01CLSL58" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL58().trim()%>"><br>
          <input type="text" name="E01CLSL59" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL59().trim()%>"><br>
          <input type="text" name="E01CLSL60" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL60().trim()%>"><br>
          <input type="text" name="E01CLSL61" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL61().trim()%>"><br>
          <input type="text" name="E01CLSL62" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL62().trim()%>"><br>
          <input type="text" name="E01CLSL63" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL63().trim()%>"><br>
          <input type="text" name="E01CLSL64" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL64().trim()%>"><br>
          <input type="text" name="E01CLSL65" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL65().trim()%>"><br>
          <input type="text" name="E01CLSL66" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL66().trim()%>"><br>
          <input type="text" name="E01CLSL67" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL67().trim()%>"><br>
          <input type="text" name="E01CLSL68" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL68().trim()%>"><br>
          <input type="text" name="E01CLSL69" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL69().trim()%>"><br>
          <input type="text" name="E01CLSL70" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL70().trim()%>"><br>
          <input type="text" name="E01CLSL71" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL71().trim()%>"><br>
          <input type="text" name="E01CLSL72" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL72().trim()%>"><br>
          <input type="text" name="E01CLSL73" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL73().trim()%>"><br>
          <input type="text" name="E01CLSL74" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL74().trim()%>"><br>
          <input type="text" name="E01CLSL75" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL75().trim()%>"><br>
          <input type="text" name="E01CLSL76" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL76().trim()%>"><br>
          <input type="text" name="E01CLSL77" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL77().trim()%>"><br>
          <input type="text" name="E01CLSL78" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL78().trim()%>"><br>
          <input type="text" name="E01CLSL79" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL79().trim()%>"><br>
          <input type="text" name="E01CLSL80" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL80().trim()%>"><br>
          <input type="text" name="E01CLSL81" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL81().trim()%>"><br>
          <input type="text" name="E01CLSL82" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL82().trim()%>"><br>
          <input type="text" name="E01CLSL83" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL83().trim()%>"><br>
          <input type="text" name="E01CLSL84" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL84().trim()%>"><br>
          <input type="text" name="E01CLSL85" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL85().trim()%>"><br>
          <input type="text" name="E01CLSL86" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL86().trim()%>"><br>
          <input type="text" name="E01CLSL87" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL87().trim()%>"><br>
          <input type="text" name="E01CLSL88" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL88().trim()%>"><br>
          <input type="text" name="E01CLSL89" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL89().trim()%>"><br>
          <input type="text" name="E01CLSL90" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL90().trim()%>"><br>
          <input type="text" name="E01CLSL91" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL91().trim()%>"><br>
          <input type="text" name="E01CLSL92" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL92().trim()%>"><br>
          <input type="text" name="E01CLSL93" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL93().trim()%>"><br>
          <input type="text" name="E01CLSL94" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL94().trim()%>"><br>
          <input type="text" name="E01CLSL95" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL95().trim()%>"><br>
          <input type="text" name="E01CLSL96" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL96().trim()%>"><br>
          <input type="text" name="E01CLSL97" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL97().trim()%>"><br>
          <input type="text" name="E01CLSL98" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL98().trim()%>"><br>
          <input type="text" name="E01CLSL99" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL99().trim()%>"><br>
          <input type="text" name="E01CLSL00" size="66" maxlength="<%= chars %>" value="<%= msg.getE01CLSL00().trim()%>"><br>
	  <% } %>          
          </div>
      </td>
    </tr>
  </table>
  <p align="center"> 
   <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
</form>
</body>
</html>
