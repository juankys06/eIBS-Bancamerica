<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Información Financiera
</TITLE>

<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id="mL0734" class="datapro.eibs.beans.EDP073401Message" scope="session" />
<jsp:useBean id= "EDP073401Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT><SCRIPT language="JavaScript">

function init() 
{

}

function ShowFormula() 
{
  
  	page= prefix +language + 'STATIC_creditproposal_financial_idx_for.jsp';
	CenterWindow(page,340,400,2);
  
}

function getParams() 
{
}
function evalute(val){

val = eval(val);

}


//-->
</SCRIPT>

</HEAD>
<body onload=javascript:init()>

<h3 align="center">Indices Financieros<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="creditproposal_financial_ind_comp.jsp,EDP0730"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0730">

<table class="tableinfo">    
 <TR > 
  <TD NOWRAP >
   <TABLE id="headTable" width="100%" cellpading=0 cellspacing=0>
    <tr id="trdark"> 
      <td align="right"  >
         <b>Cliente :</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01IFMCUN" size="12" readonly value="<%=userPO.getHeader1()%>">
      </td>
      <td align="right"  >
         <b>Nombre :</b>
      </td>
      <td nowrap colspan=3> 
         <input type="text" name="NAMECUM" size="45" readonly value="<%=userPO.getHeader2()%>">
      </td>         
    </tr> 
    <tr id="trdark"> 
      <td align="right"  >
         <b>Industria :</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01IFMCIN" size="15" readonly value="<%=userPO.getHeader4()%>">
      </td>
      <td align="right"  >
         <b>Negocio :</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01IFMLNE" size="15" readonly value="<%=userPO.getHeader5()%>">
      </td> 
      <td align="right"  >
         <b>Formato :</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01IFMCFO" size="2" readonly value="<%=userPO.getHeader3()%>">-
         <input type="text" name="E01IFMFEY" size="4" readonly value="<%=userPO.getHeader6()%>">/
         <input type="text" name="E01IFMFEM" size="2" readonly value="<%=userPO.getHeader7()%>">
      </td>        
    </tr>  
  </table> 
 </td>
 </tr>  
</table>

<BR>     
 
  <p> 
    <%
	if ( EDP073401Help.getNoResult() ) {
 %>
  </p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>No hay resultados para su b&uacute;squeda</b></p>
          <table class="tbenter" width=100% align=center>
            <tr> 
              <td class=TDBKG width="30%"> 
                <div align="center"><a href="javascript:goAction(1)"><b>Nueva</b></a></div>
              </td>
              <td class=TDBKG width="30%"> 
                <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
              </td>
            </tr>
          </table>
          <p>&nbsp;</p>
          
        </div>

	  </TD>
	</TR>
    </TABLE>
	
  <%  
		}
	else {
%> <% 

String chk = "";

 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 
  <p> 

          
  <table class="tbenter" width=100% align=center>
    <tr> 
      <td class=TDBKG width="30%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
   
  <br>
  <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="5%">&nbsp;
            </th>
             <th align=CENTER nowrap width="20%"> 
            	<div align="center">Indice Financiero
            	</div>
            </th>
             <th align=CENTER nowrap width="20%"> 
            	<div align="center">Periodo 
            	<INPUT type="text" name="E01IFMFY3" size="4" readonly
					value="<%=mL0734.getE01IFMFY3().trim()%>">/
            	<INPUT type="text" name="E01IFMFM3" size="2" readonly value="<%=mL0734.getE01IFMFM3().trim()%>">
            	<INPUT type="text" name="TIPAUDIT3" size="1" readonly value="<%=mL0734.getE01IFMCA3().substring(0,1)%>">
            	</div>           	
            </th>
             <th align=CENTER nowrap width="20%"> 
            	<div align="center">Periodo
            	<INPUT type="text" name="E01IFMFY2" size="4" readonly
					value="<%=mL0734.getE01IFMFY2().trim()%>">/
            	<INPUT type="text" name="E01IFMFM2" size="2" readonly value="<%=mL0734.getE01IFMFM2().trim()%>">
            	<INPUT type="text" name="TIPAUDIT2" size="1" readonly value="<%=mL0734.getE01IFMCA3().substring(1,2)%>">
            	</div>
            </th>
             <th align=CENTER nowrap width="20%"> 
            	<div align="center">Periodo
            	<INPUT type="text" name="E01IFMFEY" size="4" readonly
					value="<%=mL0734.getE01IFMFEY().trim()%>">/
            	<INPUT type="text" name="E01IFMFEM" size="2" readonly value="<%=mL0734.getE01IFMFEM().trim()%>">
            	<INPUT type="text" name="TIPAUDIT1" size="1" readonly value="<%=mL0734.getE01IFMCA3().substring(2,3)%>">
				</DIV>	
            </th>
           </tr>
          <%
                EDP073401Help.initRow();
                chk = "checked";
                while (EDP073401Help.getNextRow()) {
                 
                  datapro.eibs.beans.EDP073401Message msgList = (datapro.eibs.beans.EDP073401Message) EDP073401Help.getRecord();
		 %>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
            </td>
			<td NOWRAP  align=CENTER width=\"20%\"><%= msgList.getE01DPIDSC() %></td>
            <td NOWRAP  align=RIGHT width=\"20%\"><%= msgList.getE01DPIAM1() %></td>
            <td NOWRAP  align=RIGHT width=\"20%\"><%= msgList.getE01DPIAM2() %></td>
            <td NOWRAP  align=RIGHT width=\"20%\"><%= msgList.getE01DPIAM3() %></td>
         </tr>
          <%
              				chk = "";     
                }
              %>
        </table>
  </table>
     
	<SCRIPT language="JavaScript">
		showChecked("CURRCODE");
	</SCRIPT>

<%}%>

</FORM>
<P><BR>
</P>
</BODY>
</HTML>
