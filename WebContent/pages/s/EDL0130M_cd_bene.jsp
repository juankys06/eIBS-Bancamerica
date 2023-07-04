<html>
<head>
<title>Beneficiaries</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "bene" class= "datapro.eibs.beans.ESD000004Message"   scope="session"/>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"   scope="session"/>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"   scope="session"/>

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
   
    builtNewMenu(cdm_m_opt);
   
    initMenu();

</SCRIPT>

</head>

<body onbeforeprint="showOpt(true)" onafterprint="showOpt(false)">

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 } 
%>

<h3 align="center">Beneficiarios <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_bene.jsp, EDL0130M"></h3>
<hr size="4">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="18">  
<p>
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
                <input type="text" name="E02CUN" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text" name="E02NA1" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><font face="Arial"><font face="Arial"><font size="2"><b>Cuenta 
                :</b></font></font></font></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E04CUN" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E02PRO" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <BR>
  <div id="dataDiv" align="center" style="width:90%; padding-left:50; padding-right:10" class="scbarcolor">
<%
  int bene_amount = 10;
  String name;
  for ( int i=1; i<=bene_amount; i++ ) {
    if ( i == 10 ) {
      name = "A"; 
    }
    else {
      name = i + "";
    }
    
    out.println("<table class=\"tbenter\" border=\"0\" width=\"100%\">");
    out.println("<tr>");
    
    switch ( i ) {
        case 1 :     
%> 
 <td align="left"><h4>First</h4></td>
<%          
           break;
        case 2 : 
%> 
 <td align="left"><h4>Second</h4></td>
<%          
           break;
        case 3 : 
%> 
<td align="left"><h4>Third</h4></td>
<%          
           break;
        case 4 : 
%> 
<td align="left"><h4>Forth</h4></td>
<%          
           break;
        case 5 : 
%> 
<td align="left"><h4>Fifth</h4></td>
<%          
           break;
        case 6 : 
%> 
<td align="left"><h4>Sixth</h4></td>
<%          
           break;
        case 7 : 
%> 
<td align="left"><h4>Seventh</h4></td>
<%          
           break;
        case 8 : 
%> 
<td align="left"><h4>Eighth</h4></td>
<%          
           break;
        case 9 : 
%> 
<td align="left"><h4>Ninth</h4></td>
<%          
           break;
        case 10 : 
%> 
<td align="left"><h4>Tenth</h4></td>
<%          
           break;
     }
  out.println("<td width=\"30%\" align=\"right\">");
  out.println("<h4>");
  out.print("<A href=\"javascript:go(1)\">1</A>,<A href=\"javascript:go(2)\">2</A>,<A href=\"javascript:go(3)\">3</A>,<A href=\"javascript:go(4)\">4</A>,<A href=\"javascript:go(5)\">5</A>,");
  out.print("<A href=\"javascript:go(6)\">6</A>,<A href=\"javascript:go(7)\">7</A>,<A href=\"javascript:go(8)\">8</A>,<A href=\"javascript:go(9)\">9</A>,<A href=\"javascript:go(10)\">10</A>");
   out.println("</h4>"); 
  out.println("</td>");   
  out.println("</tr>");
  out.println("</table>"); 
%>

  <table id="mainTable<%= name %>" class="tableinfo" >
    <tr bgcolor="#FFFFFF" bordercolor="#FFFFFF"> 
      <td> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" name="TB_BENEFICIARY_CORP" bgcolor="#FFFFFF" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF" bordercolordark="#FFFFFF"  align="center">
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right">Legal Name :</div>
            </td>
            <td nowrap width="58%"> 
              <input type="text" name="E<%= name %>4MA1" maxlength="45" size="46" value="<%= bene.getField("E" + name + "4MA1").getString().trim()%>">
              </td>
          </tr>
          <tr id="trclear"> 
            <td width="42%" nowrap> 
              <div align="right">Address :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" name="E<%= name %>4MA2" maxlength="35" size="36" value="<%= bene.getField("E" + name + "4MA2").getString().trim()%>">
              </td>
          </tr>
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right"></div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" name="E<%= name %>4MA3" maxlength="35" size="36" value="<%= bene.getField("E" + name + "4MA3").getString().trim()%>">
              </td>
          </tr>
          <tr id="trclear"> 
            <td width="42%" nowrap> 
              <div align="right">City :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" name="E<%= name %>4CTY" maxlength="30" size="31" value="<%= bene.getField("E" + name + "4CTY").getString().trim()%>">
              </td>
          </tr>
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right">State :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" name="E<%= name %>4STE" maxlength="4" size="5" value="<%= bene.getField("E" + name + "4STE").getString().trim()%>">
              <a href="javascript:GetCodeCNOFC('E<%= name %>4STE','27')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absbottom" border="0"></a>	
              </td>
          </tr>
          <tr id="trclear"> 
            <td width="42%" nowrap> 
              <div align="right">Country :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" name="E<%= name %>4CTR" maxlength="20" size="21" value="<%= bene.getField("E" + name + "4CTR").getString().trim()%>">
              </td>
          </tr>
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right">P.O. Box :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" name="E<%= name %>4POB" maxlength="10" size="11" value="<%= bene.getField("E" + name + "4POB").getString().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="42%" nowrap> 
              <div align="right">Zip Code : </div>
            </td>
            <td width="58%" nowrap>
              <input type="text" name="E<%= name %>4ZPC" maxlength="15" size="16" value="<%= bene.getField("E" + name + "4ZPC").getString().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right">Citizenship:</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" name="E<%= name %>4BNC" maxlength="4" size="5" value="<%= bene.getField("E" + name + "4BNC").getString().trim()%>">
              <a href="javascript:GetCodeCNOFC('E<%= name %>4BNC','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absbottom" border="0"></a> 
              </td>
          </tr>
          <tr id="trclear"> 
            <td width="42%" nowrap> 
              <div align="right">Title :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" name="E<%= name %>4MA4" maxlength="35" size="36" value="<%= bene.getField("E" + name + "4MA4").getString().trim()%>">
              </td>
          </tr>
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right">Date :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" name="E<%= name %>4DT1" maxlength="2" size="2" value="<%= bene.getField("E" + name + "4DT1").getString().trim()%>">
              <input type="text" name="E<%= name %>4DT2" maxlength="2" size="2" value="<%= bene.getField("E" + name + "4DT2").getString().trim()%>">
              <input type="text" name="E<%= name %>4DT3" maxlength="2" size="2" value="<%= bene.getField("E" + name + "4DT3").getString().trim()%>">
              </td>
          </tr>
          <tr id="trclear"> 
            <td width="42%" nowrap > 
              <div align="right">Profesi&oacute;n/Ocupaci&oacute;n :</div>
            </td>
            <td width="58%" nowrap > 
              <p> 
                <input type="text" name="E<%= name %>4MLC" value="<%= bene.getField("E" + name + "4MLC").getString().trim()%>" size="5" maxlength="4">
                <a href="javascript:GetCodeCNOFC('E<%= name %>4MLC','29')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absbottom" border="0"  ></a>	
                </p>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right">In the Capacity of :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" name="E<%= name %>4INC" maxlength="4" size="5" value="<%= bene.getField("E" + name + "4INC").getString().trim()%>">
              </td>
          </tr>
          <tr id="trclear"> 
            <td width="42%" nowrap > 
              <div align="right">Marital Status :</div>
            </td>
            <td width="58%" nowrap > 
              <select name="E<%= name %>4BMS">
                <option value=1 <% if (bene.getField("E" + name + "4BMS").getString().equals("1")) out.print("selected"); %>>Married</option>
                <option value=2 <% if (bene.getField("E" + name + "4BMS").getString().equals("2")) out.print("selected"); %>>Single</option>
                <option value=3 <% if (bene.getField("E" + name + "4BMS").getString().equals("3")) out.print("selected"); %>>Divorced</option>
                <option value=4 <% if (bene.getField("E" + name + "4BMS").getString().equals("4")) out.print("selected"); %>>Widower</option>
                <option value=5 <% if (bene.getField("E" + name + "4BMS").getString().equals("5")) out.print("selected"); %>>Cohabitant</option>
                <option value=0 <% if ( !(bene.getField("E" + name + "4BMS").getString().equals("1") || bene.getField("E" + name + "4BMS").getString().equals("2") || bene.getField("E" + name + "4BMS").getString().equals("3") || bene.getField("E" + name + "4BMS").getString().equals("4") || bene.getField("E" + name + "4BMS").getString().equals("5"))) out.print("selected"); %>></option>
              </select>
              </td>
          </tr>
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right">Gender :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="radio" name="E<%= name %>4BSX" value="F" <% if (bene.getField("E" + name + "4BSX").getString().equals("F")) out.print("checked"); %> checked>
              Female 
              <input type="radio" name="E<%= name %>4BSX" value="M" <% if (bene.getField("E" + name + "4BSX").getString().equals("M")) out.print("checked"); %>>
              Male </td>
          </tr>
          <tr id="trclear"> 
            <td width="42%" nowrap> 
              <div align="right">Telephone :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" name="E<%= name %>4HPN" maxlength="11" size="12" value="<%= bene.getField("E" + name + "4HPN").getString().trim()%>">
              </td>
          </tr>
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right">Identification :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" name="E<%= name %>4BNI" maxlength="15" size="16" value="<%= bene.getField("E" + name + "4BNI").getString().trim()%>">
              </td>
          </tr>
          <tr id="trclear"> 
            <td width="42%" > 
              <div align="right">Type :</div>
            </td>
            <td width="58%" nowrap > 
              <input type="text" name="E<%= name %>4TID" value="<%= bene.getField("E" + name + "4TID").getString().trim()%>" size="5" maxlength="4">
              <a href="javascript:GetCodeCNOFC('E<%= name %>4TID','34')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absbottom" border="0" ></a>	
              </td>
          </tr>
          <tr id="trdark"> 
            <td width="42%" nowrap> 
              <div align="right">Country :</div>
            </td>
            <td width="58%" nowrap> 
              <input type="text" name="E<%= name %>4PID" size="5" maxlength="4" value="<%= bene.getField("E" + name + "4PID").getString().trim()%>">
              <a href="javascript:GetCodeCNOFC('E<%= name %>4PID','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absbottom" border="0" ></a> 
              </td>
          </tr>
        </table>
        
      </td>
    </tr>
  </table>
  
  <%
  }
%>
</div>
<SCRIPT Language="Javascript">
   showOpt(false);
</SCRIPT>

 <br>
  <div align="center"> 
	   <input id="EIBSBTN" type=submit name="Submit" value="Submit">
  </div>
</form>
</body>
</html>
