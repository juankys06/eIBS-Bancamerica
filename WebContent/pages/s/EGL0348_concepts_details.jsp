<html>
<head>
<title>Cuentas de Contrapartida</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="concept" class="datapro.eibs.beans.EGL034801Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
</SCRIPT>

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>


<H3 align="center">Cuentas de Contrapartida para M&oacute;dulos eIBS<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_overdraft.jsp, EDD0000"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEGL0348" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
  <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Banco :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01GLHB01" size="3" maxlength="2"  value="<%= concept.getE01GLHB01().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Aplicaci&oacute;n :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E01GLHA01" size="3"  maxlength="3" value="<%= concept.getE01GLHA01().trim()%>">
                <input type="text" name="E01DESACD" maxlength="35" size="36" value="<%= concept.getE01DESACD().trim()%>" >
                </font></font></font></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>&nbsp;</h4>  
  <table id="mainTable" class="tableinfo">
    <tr>
      <td> 
        <table id="headTable" >
          <tr id="trdark"> 
            <td nowrap align="center" >Sequencia</td>
            <td nowrap align="center" > Concepto</td>
            <td nowrap align="center" >Tipo</td>
            <td nowrap align="center" >Contabilidad</td>
            <td nowrap align="center" >Descripci&oacute;n</td>
          </tr>
        </table>
        <div id="dataDiv1" style="height:100; overflow-y :scroll; z-index:0" > 
          <table id="dataTable">
            <%
  				   int i = 0;
  				   int amount = 20;
 				   String name;
  					for (i=1; i<=amount; i++ ) {
                      if(i <10){
						name = "0" +i + "";
					} else {	
   					  name = i + "";
					}
   			%>
            <tr id="trclear"> 
              <td nowrap > 
                <div align="center" nowrap> 
                  <input type="text" name="E01GLHS<%= name %>" value="<%= concept.getField("E01GLHS"+name).getString().trim()%>" size="3" maxlength="2">
                
                </div>
              </td>
              <td nowrap > 
                <div align="left"> 
                  <input type="text" name="E01GLHD<%= name %>" size="31" maxlength="30" value="<%= concept.getField("E01GLHD"+name).getString().trim()%>">
                </div>
              </td>
              <td nowrap > 
                <div align="left"> 
                  <input type="text" name="E01GLHT<%= name %>" size="2" maxlength="1" value="<%= concept.getField("E01GLHT"+name).getString().trim()%>"
					oncontextmenu="showPopUp(concepttypeHelp,this.name,'','','','','')"  >
                
                </div>
              </td>
              <td nowrap > 
                <div align="center"> 
                  <input type="text" name="E01GLHG<%= name %>" size="17" maxlength="16" value="<%= concept.getField("E01GLHG"+name).getString().trim()%>"
                	oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01GLHB01.value,'','','','')">
                </div>
              </td>
              <td nowrap > 
                <div align="center"> 
                  <input type="text" name="E01DESD<%= name %>" size="31" maxlength="30"  value="<%= concept.getField("E01DESD"+name).getString().trim()%>"
                readonly>
                </div>
              </td>
            </tr>
            <%
    		}
    		%>
          </table>
        </div>
        
      </td>
    </tr>
  </table>
  <SCRIPT language="JavaScript">
     document.forms[0].totalRow.value="<%= i %>";
     function resizeDoc() {
       divResize();
       adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      }
	 //showChecked("REFNUM");
     resizeDoc();
     //tbAddInfoH.rows[0].cells[0].height = headTable.rows[0].cells[0].clientHeight;
     window.onresize=resizeDoc;
     
</SCRIPT> 
  
  <br>
          <div align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </div>
  </form>
</body>
</html>
