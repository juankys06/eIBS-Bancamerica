<html>
<head>
<title>Instrucciones Especiales</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "reqObserv" class= "datapro.eibs.beans.ESD000012Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


</head>

<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

%> 
<h3 align="center">Observaciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_ap_special_inst,EDD1000"></h3>
<hr size="4">
 <FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.platform.JSEPV0140" >
 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="22">
  
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="50%"> 
              <div align="right"><b>N&uacute;mero de Propuesta:</b></div>
            </td>
            <td nowrap width="50%"> 
              <div align="left"> 
                <input type="text" readonly name="E12ACC" size="9" maxlength="9"  value="<%= request.getParameter("ACCNUM") %>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <table class="tableinfo">
    <tr> 
      <td> 
        <p align="center"> 
          <textarea readonly name="E12DSC" cols="90" rows="10" ><%= request.getParameter("OBSERV") %></textarea>
        </p>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>
