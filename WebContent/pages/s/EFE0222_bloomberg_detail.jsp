<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Bloomberg Format</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="bloom" class="datapro.eibs.beans.EFE0222DSMessage"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
       
 function deleteCode(){
    
 if(confirm("Are you sure you want delete this Bloomberg Code")){
  
   document.forms[0].DELACTION.value = "D";
   document.forms[0].submit();
 }
else {

}
}
   </SCRIPT>





</head>
<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
 
%> 
<h3 align="center">Bloomberg Interface</h3>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="bloomberg_detail.jsp, EFE0222"> 
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEWD0342" >
<input type="hidden" name="SCREEN"  value="4" >
<input type="hidden" name="DELACTION"  value="" >
  <h4>
    
    Basic Information </h4>
  <table  class="tableinfo" width="736">
    <tr id="trdark"> 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Bloomberg Code :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01FLOBLM" size="21" maxlength="20" value="<%= bloom.getE01FLOBLM()%>" 
			  >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Type :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01FLOTYP" size="5" maxlength="4" value="<%= bloom.getE01FLOTYP()%>" 
			  >
              <a href="javascript:GetCode('E01FLOTYP','STATIC_bloomberg_type.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="21%" > 
              <div align="right">Bloomberg Field Name :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01FLOFLD" size="21" maxlength="20" value="<%= bloom.getE01FLOFLD()%>" 
			  >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">IBS Code :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01FLOIBS" size="11" maxlength="10" value="<%= bloom.getE01FLOIBS()%>" 
			  >
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="21%" >
              <div align="right">Description :</div>
            </td>
            <td nowrap width="23%" >
              <input type="text" name="E01RATDSC" size="40" maxlength="35" value="<%= bloom.getE01RATDSC()%>" 
			  >
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td> 
        <div align="center"> </div>
      </td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"> 
          <input id="EIBSBTN" type=submit name="Submit" value="Submit" >
        </div>
      </td>
      <td width="33%">
        <div align="center">
          <input id="EIBSBTN" type=button name="Delete" value="Delete" onClick="deleteCode()">
        </div>
      </td>
    </tr>
  </table>
</form>

</body>
</html>
