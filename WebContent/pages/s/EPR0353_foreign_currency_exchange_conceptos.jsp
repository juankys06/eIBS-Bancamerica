<html> 
<head>
<title>Conceptos Contables</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "msgFrac" 	class= "datapro.eibs.beans.EPR035302Message"  	scope="session" />
<jsp:useBean id= "error" 	class= "datapro.eibs.beans.ELEERRMessage"  		scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<%
int CurrRow = 0;
try { CurrRow = Integer.parseInt(request.getParameter("CurrRow"));} catch (Exception e) {}
if (CurrRow == 1) {
msgFrac.setE02REQPOS("01");
userPO.setHeader1("1");
msgFrac.setE02OFFVA1(msgFrac.getE02OFFV11());
msgFrac.setE02OFFOP1(msgFrac.getE02OFFO11());
msgFrac.setE02OFFGL1(msgFrac.getE02OFFG11());
msgFrac.setE02OFFCO1(msgFrac.getE02OFFC11());
msgFrac.setE02OFFBK1(msgFrac.getE02OFFB11());
msgFrac.setE02OFFBR1(msgFrac.getE02OFFR11());
msgFrac.setE02OFFCY1(msgFrac.getE02OFFY11());
msgFrac.setE02OFFAC1(msgFrac.getE02OFFA11());
msgFrac.setE02OFFOP2(msgFrac.getE02OFFO12());
msgFrac.setE02OFFGL2(msgFrac.getE02OFFG12());
msgFrac.setE02OFFCO2(msgFrac.getE02OFFC12());
msgFrac.setE02OFFBK2(msgFrac.getE02OFFB12());
msgFrac.setE02OFFBR2(msgFrac.getE02OFFR12());
msgFrac.setE02OFFCY2(msgFrac.getE02OFFY12());
msgFrac.setE02OFFAC2(msgFrac.getE02OFFA12());
msgFrac.setE02OFFVA2(msgFrac.getE02OFFV12());
msgFrac.setE02OFFOP3(msgFrac.getE02OFFO13());
msgFrac.setE02OFFGL3(msgFrac.getE02OFFG13());
msgFrac.setE02OFFCO3(msgFrac.getE02OFFC13());
msgFrac.setE02OFFBK3(msgFrac.getE02OFFB13());
msgFrac.setE02OFFBR3(msgFrac.getE02OFFR13());
msgFrac.setE02OFFCY3(msgFrac.getE02OFFY13());
msgFrac.setE02OFFAC3(msgFrac.getE02OFFA13());
msgFrac.setE02OFFVA3(msgFrac.getE02OFFV13());
msgFrac.setE02OFFOP4(msgFrac.getE02OFFO14());
msgFrac.setE02OFFGL4(msgFrac.getE02OFFG14());
msgFrac.setE02OFFCO4(msgFrac.getE02OFFC14());
msgFrac.setE02OFFBK4(msgFrac.getE02OFFB14());
msgFrac.setE02OFFBR4(msgFrac.getE02OFFR14());
msgFrac.setE02OFFCY4(msgFrac.getE02OFFY14());
msgFrac.setE02OFFAC4(msgFrac.getE02OFFA14());
msgFrac.setE02OFFVA4(msgFrac.getE02OFFV14());
msgFrac.setE02OFFOP5(msgFrac.getE02OFFO15());
msgFrac.setE02OFFGL5(msgFrac.getE02OFFG15());
msgFrac.setE02OFFCO5(msgFrac.getE02OFFC15());
msgFrac.setE02OFFBK5(msgFrac.getE02OFFB15());
msgFrac.setE02OFFBR5(msgFrac.getE02OFFR15());
msgFrac.setE02OFFCY5(msgFrac.getE02OFFY15());
msgFrac.setE02OFFAC5(msgFrac.getE02OFFA15());
msgFrac.setE02OFFVA5(msgFrac.getE02OFFV15());
} 
if (CurrRow == 2) {
msgFrac.setE02REQPOS("02");
userPO.setHeader1("2");
msgFrac.setE02OFFVA1(msgFrac.getE02OFFV21());
msgFrac.setE02OFFOP1(msgFrac.getE02OFFO21());
msgFrac.setE02OFFGL1(msgFrac.getE02OFFG21());
msgFrac.setE02OFFCO1(msgFrac.getE02OFFC21());
msgFrac.setE02OFFBK1(msgFrac.getE02OFFB21());
msgFrac.setE02OFFBR1(msgFrac.getE02OFFR21());
msgFrac.setE02OFFCY1(msgFrac.getE02OFFY21());
msgFrac.setE02OFFAC1(msgFrac.getE02OFFA21());
msgFrac.setE02OFFOP2(msgFrac.getE02OFFO22());
msgFrac.setE02OFFGL2(msgFrac.getE02OFFG22());
msgFrac.setE02OFFCO2(msgFrac.getE02OFFC22());
msgFrac.setE02OFFBK2(msgFrac.getE02OFFB22());
msgFrac.setE02OFFBR2(msgFrac.getE02OFFR22());
msgFrac.setE02OFFCY2(msgFrac.getE02OFFY22());
msgFrac.setE02OFFAC2(msgFrac.getE02OFFA22());
msgFrac.setE02OFFVA2(msgFrac.getE02OFFV22());
msgFrac.setE02OFFOP3(msgFrac.getE02OFFO23());
msgFrac.setE02OFFGL3(msgFrac.getE02OFFG23());
msgFrac.setE02OFFCO3(msgFrac.getE02OFFC23());
msgFrac.setE02OFFBK3(msgFrac.getE02OFFB23());
msgFrac.setE02OFFBR3(msgFrac.getE02OFFR23());
msgFrac.setE02OFFCY3(msgFrac.getE02OFFY23());
msgFrac.setE02OFFAC3(msgFrac.getE02OFFA23());
msgFrac.setE02OFFVA3(msgFrac.getE02OFFV23());
msgFrac.setE02OFFOP4(msgFrac.getE02OFFO24());
msgFrac.setE02OFFGL4(msgFrac.getE02OFFG24());
msgFrac.setE02OFFCO4(msgFrac.getE02OFFC24());
msgFrac.setE02OFFBK4(msgFrac.getE02OFFB24());
msgFrac.setE02OFFBR4(msgFrac.getE02OFFR24());
msgFrac.setE02OFFCY4(msgFrac.getE02OFFY24());
msgFrac.setE02OFFAC4(msgFrac.getE02OFFA24());
msgFrac.setE02OFFVA4(msgFrac.getE02OFFV24());
msgFrac.setE02OFFOP5(msgFrac.getE02OFFO25());
msgFrac.setE02OFFGL5(msgFrac.getE02OFFG25());
msgFrac.setE02OFFCO5(msgFrac.getE02OFFC25());
msgFrac.setE02OFFBK5(msgFrac.getE02OFFB25());
msgFrac.setE02OFFBR5(msgFrac.getE02OFFR25());
msgFrac.setE02OFFCY5(msgFrac.getE02OFFY25());
//msgFrac.setE02OFFAC5(msgFrac.getE02OFFA25());
//msgFrac.setE02OFFVA5(msgFrac.getE02OFFV25());
} 
if (CurrRow == 3) {
msgFrac.setE02REQPOS("03");
userPO.setHeader1("3");
msgFrac.setE02OFFVA1(msgFrac.getE02OFFV31());
msgFrac.setE02OFFOP1(msgFrac.getE02OFFO31());
msgFrac.setE02OFFGL1(msgFrac.getE02OFFG31());
msgFrac.setE02OFFCO1(msgFrac.getE02OFFC31());
msgFrac.setE02OFFBK1(msgFrac.getE02OFFB31());
msgFrac.setE02OFFBR1(msgFrac.getE02OFFR31());
msgFrac.setE02OFFCY1(msgFrac.getE02OFFY31());
msgFrac.setE02OFFAC1(msgFrac.getE02OFFA31());
msgFrac.setE02OFFOP2(msgFrac.getE02OFFO32());
msgFrac.setE02OFFGL2(msgFrac.getE02OFFG32());
msgFrac.setE02OFFCO2(msgFrac.getE02OFFC32());
msgFrac.setE02OFFBK2(msgFrac.getE02OFFB32());
msgFrac.setE02OFFBR2(msgFrac.getE02OFFR32());
msgFrac.setE02OFFCY2(msgFrac.getE02OFFY32());
msgFrac.setE02OFFAC2(msgFrac.getE02OFFA32());
msgFrac.setE02OFFVA2(msgFrac.getE02OFFV32());
msgFrac.setE02OFFOP3(msgFrac.getE02OFFO33());
msgFrac.setE02OFFGL3(msgFrac.getE02OFFG33());
msgFrac.setE02OFFCO3(msgFrac.getE02OFFC33());
msgFrac.setE02OFFBK3(msgFrac.getE02OFFB33());
msgFrac.setE02OFFBR3(msgFrac.getE02OFFR33());
msgFrac.setE02OFFCY3(msgFrac.getE02OFFY33());
msgFrac.setE02OFFAC3(msgFrac.getE02OFFA33());
msgFrac.setE02OFFVA3(msgFrac.getE02OFFV33());
msgFrac.setE02OFFOP4(msgFrac.getE02OFFO34());
msgFrac.setE02OFFGL4(msgFrac.getE02OFFG34());
msgFrac.setE02OFFCO4(msgFrac.getE02OFFC34());
msgFrac.setE02OFFBK4(msgFrac.getE02OFFB34());
msgFrac.setE02OFFBR4(msgFrac.getE02OFFR34());
msgFrac.setE02OFFCY4(msgFrac.getE02OFFY34());
msgFrac.setE02OFFAC4(msgFrac.getE02OFFA34());
msgFrac.setE02OFFVA4(msgFrac.getE02OFFV34());
msgFrac.setE02OFFOP5(msgFrac.getE02OFFO35());
msgFrac.setE02OFFGL5(msgFrac.getE02OFFG35());
msgFrac.setE02OFFCO5(msgFrac.getE02OFFC35());
msgFrac.setE02OFFBK5(msgFrac.getE02OFFB35());
msgFrac.setE02OFFBR5(msgFrac.getE02OFFR35());
msgFrac.setE02OFFCY5(msgFrac.getE02OFFY35());
msgFrac.setE02OFFAC5(msgFrac.getE02OFFA35());
msgFrac.setE02OFFVA5(msgFrac.getE02OFFV35());
} 
if (CurrRow == 4) {
msgFrac.setE02REQPOS("04");
userPO.setHeader1("4");
msgFrac.setE02OFFVA1(msgFrac.getE02OFFV41());
msgFrac.setE02OFFOP1(msgFrac.getE02OFFO41());
msgFrac.setE02OFFGL1(msgFrac.getE02OFFG41());
msgFrac.setE02OFFCO1(msgFrac.getE02OFFC41());
msgFrac.setE02OFFBK1(msgFrac.getE02OFFB41());
msgFrac.setE02OFFBR1(msgFrac.getE02OFFR41());
msgFrac.setE02OFFCY1(msgFrac.getE02OFFY41());
msgFrac.setE02OFFAC1(msgFrac.getE02OFFA41());
msgFrac.setE02OFFOP2(msgFrac.getE02OFFO42());
msgFrac.setE02OFFGL2(msgFrac.getE02OFFG42());
msgFrac.setE02OFFCO2(msgFrac.getE02OFFC42());
msgFrac.setE02OFFBK2(msgFrac.getE02OFFB42());
msgFrac.setE02OFFBR2(msgFrac.getE02OFFR42());
msgFrac.setE02OFFCY2(msgFrac.getE02OFFY42());
msgFrac.setE02OFFAC2(msgFrac.getE02OFFA42());
msgFrac.setE02OFFVA2(msgFrac.getE02OFFV42());
msgFrac.setE02OFFOP3(msgFrac.getE02OFFO43());
msgFrac.setE02OFFGL3(msgFrac.getE02OFFG43());
msgFrac.setE02OFFCO3(msgFrac.getE02OFFC43());
msgFrac.setE02OFFBK3(msgFrac.getE02OFFB43());
msgFrac.setE02OFFBR3(msgFrac.getE02OFFR43());
msgFrac.setE02OFFCY3(msgFrac.getE02OFFY43());
msgFrac.setE02OFFAC3(msgFrac.getE02OFFA43());
msgFrac.setE02OFFVA3(msgFrac.getE02OFFV43());
msgFrac.setE02OFFOP4(msgFrac.getE02OFFO44());
msgFrac.setE02OFFGL4(msgFrac.getE02OFFG44());
msgFrac.setE02OFFCO4(msgFrac.getE02OFFC44());
msgFrac.setE02OFFBK4(msgFrac.getE02OFFB44());
msgFrac.setE02OFFBR4(msgFrac.getE02OFFR44());
msgFrac.setE02OFFCY4(msgFrac.getE02OFFY44());
msgFrac.setE02OFFAC4(msgFrac.getE02OFFA44());
msgFrac.setE02OFFVA4(msgFrac.getE02OFFV44());
msgFrac.setE02OFFOP5(msgFrac.getE02OFFO45());
msgFrac.setE02OFFGL5(msgFrac.getE02OFFG45());
msgFrac.setE02OFFCO5(msgFrac.getE02OFFC45());
msgFrac.setE02OFFBK5(msgFrac.getE02OFFB45());
msgFrac.setE02OFFBR5(msgFrac.getE02OFFR45());
msgFrac.setE02OFFCY5(msgFrac.getE02OFFY45());
msgFrac.setE02OFFAC5(msgFrac.getE02OFFA45());
msgFrac.setE02OFFVA5(msgFrac.getE02OFFV45());
} 
if (CurrRow == 5) {
msgFrac.setE02REQPOS("05");
userPO.setHeader1("5");
msgFrac.setE02OFFVA1(msgFrac.getE02OFFV51());
msgFrac.setE02OFFOP1(msgFrac.getE02OFFO51());
msgFrac.setE02OFFGL1(msgFrac.getE02OFFG51());
msgFrac.setE02OFFCO1(msgFrac.getE02OFFC51());
msgFrac.setE02OFFBK1(msgFrac.getE02OFFB51());
msgFrac.setE02OFFBR1(msgFrac.getE02OFFR51());
msgFrac.setE02OFFCY1(msgFrac.getE02OFFY51());
msgFrac.setE02OFFAC1(msgFrac.getE02OFFA51());
msgFrac.setE02OFFOP2(msgFrac.getE02OFFO52());
msgFrac.setE02OFFGL2(msgFrac.getE02OFFG52());
msgFrac.setE02OFFCO2(msgFrac.getE02OFFC52());
msgFrac.setE02OFFBK2(msgFrac.getE02OFFB52());
msgFrac.setE02OFFBR2(msgFrac.getE02OFFR52());
msgFrac.setE02OFFCY2(msgFrac.getE02OFFY52());
msgFrac.setE02OFFAC2(msgFrac.getE02OFFA52());
msgFrac.setE02OFFVA2(msgFrac.getE02OFFV52());
msgFrac.setE02OFFOP3(msgFrac.getE02OFFO53());
msgFrac.setE02OFFGL3(msgFrac.getE02OFFG53());
msgFrac.setE02OFFCO3(msgFrac.getE02OFFC53());
msgFrac.setE02OFFBK3(msgFrac.getE02OFFB53());
msgFrac.setE02OFFBR3(msgFrac.getE02OFFR53());
msgFrac.setE02OFFCY3(msgFrac.getE02OFFY53());
msgFrac.setE02OFFAC3(msgFrac.getE02OFFA53());
msgFrac.setE02OFFVA3(msgFrac.getE02OFFV53());
msgFrac.setE02OFFOP4(msgFrac.getE02OFFO54());
msgFrac.setE02OFFGL4(msgFrac.getE02OFFG54());
msgFrac.setE02OFFCO4(msgFrac.getE02OFFC54());
msgFrac.setE02OFFBK4(msgFrac.getE02OFFB54());
msgFrac.setE02OFFBR4(msgFrac.getE02OFFR54());
msgFrac.setE02OFFCY4(msgFrac.getE02OFFY54());
msgFrac.setE02OFFAC4(msgFrac.getE02OFFA54());
msgFrac.setE02OFFVA4(msgFrac.getE02OFFV54());
msgFrac.setE02OFFOP5(msgFrac.getE02OFFO55());
msgFrac.setE02OFFGL5(msgFrac.getE02OFFG55());
msgFrac.setE02OFFCO5(msgFrac.getE02OFFC55());
msgFrac.setE02OFFBK5(msgFrac.getE02OFFB55());
msgFrac.setE02OFFBR5(msgFrac.getE02OFFR55());
msgFrac.setE02OFFCY5(msgFrac.getE02OFFY55());
msgFrac.setE02OFFAC5(msgFrac.getE02OFFA55());
msgFrac.setE02OFFVA5(msgFrac.getE02OFFV55());
} 
if (CurrRow == 6) {
msgFrac.setE02REQPOS("06");
msgFrac.setE02OFFVA1(msgFrac.getE02OFFV61());
msgFrac.setE02OFFOP1(msgFrac.getE02OFFO61());
msgFrac.setE02OFFGL1(msgFrac.getE02OFFG61());
msgFrac.setE02OFFCO1(msgFrac.getE02OFFC61());
msgFrac.setE02OFFBK1(msgFrac.getE02OFFB61());
msgFrac.setE02OFFBR1(msgFrac.getE02OFFR61());
msgFrac.setE02OFFCY1(msgFrac.getE02OFFY61());
msgFrac.setE02OFFAC1(msgFrac.getE02OFFA61());
msgFrac.setE02OFFOP2(msgFrac.getE02OFFO62());
msgFrac.setE02OFFGL2(msgFrac.getE02OFFG62());
msgFrac.setE02OFFCO2(msgFrac.getE02OFFC62());
msgFrac.setE02OFFBK2(msgFrac.getE02OFFB62());
msgFrac.setE02OFFBR2(msgFrac.getE02OFFR62());
msgFrac.setE02OFFCY2(msgFrac.getE02OFFY62());
msgFrac.setE02OFFAC2(msgFrac.getE02OFFA62());
msgFrac.setE02OFFVA2(msgFrac.getE02OFFV62());
msgFrac.setE02OFFOP3(msgFrac.getE02OFFO63());
msgFrac.setE02OFFGL3(msgFrac.getE02OFFG63());
msgFrac.setE02OFFCO3(msgFrac.getE02OFFC63());
msgFrac.setE02OFFBK3(msgFrac.getE02OFFB63());
msgFrac.setE02OFFBR3(msgFrac.getE02OFFR63());
msgFrac.setE02OFFCY3(msgFrac.getE02OFFY63());
msgFrac.setE02OFFAC3(msgFrac.getE02OFFA63());
msgFrac.setE02OFFVA3(msgFrac.getE02OFFV63());
msgFrac.setE02OFFOP4(msgFrac.getE02OFFO64());
msgFrac.setE02OFFGL4(msgFrac.getE02OFFG64());
msgFrac.setE02OFFCO4(msgFrac.getE02OFFC64());
msgFrac.setE02OFFBK4(msgFrac.getE02OFFB64());
msgFrac.setE02OFFBR4(msgFrac.getE02OFFR64());
msgFrac.setE02OFFCY4(msgFrac.getE02OFFY64());
msgFrac.setE02OFFAC4(msgFrac.getE02OFFA64());
msgFrac.setE02OFFVA4(msgFrac.getE02OFFV64());
msgFrac.setE02OFFOP5(msgFrac.getE02OFFO65());
msgFrac.setE02OFFGL5(msgFrac.getE02OFFG65());
msgFrac.setE02OFFCO5(msgFrac.getE02OFFC65());
msgFrac.setE02OFFBK5(msgFrac.getE02OFFB65());
msgFrac.setE02OFFBR5(msgFrac.getE02OFFR65());
msgFrac.setE02OFFCY5(msgFrac.getE02OFFY65());
msgFrac.setE02OFFAC5(msgFrac.getE02OFFA65());
msgFrac.setE02OFFVA5(msgFrac.getE02OFFV65());
} 
if (CurrRow == 7) {
msgFrac.setE02REQPOS("07");
userPO.setHeader1("7");
msgFrac.setE02OFFVA1(msgFrac.getE02OFFV71());
msgFrac.setE02OFFOP1(msgFrac.getE02OFFO71());
msgFrac.setE02OFFGL1(msgFrac.getE02OFFG71());
msgFrac.setE02OFFCO1(msgFrac.getE02OFFC71());
msgFrac.setE02OFFBK1(msgFrac.getE02OFFB71());
msgFrac.setE02OFFBR1(msgFrac.getE02OFFR71());
msgFrac.setE02OFFCY1(msgFrac.getE02OFFY71());
msgFrac.setE02OFFAC1(msgFrac.getE02OFFA71());
msgFrac.setE02OFFOP2(msgFrac.getE02OFFO72());
msgFrac.setE02OFFGL2(msgFrac.getE02OFFG72());
msgFrac.setE02OFFCO2(msgFrac.getE02OFFC72());
msgFrac.setE02OFFBK2(msgFrac.getE02OFFB72());
msgFrac.setE02OFFBR2(msgFrac.getE02OFFR72());
msgFrac.setE02OFFCY2(msgFrac.getE02OFFY72());
msgFrac.setE02OFFAC2(msgFrac.getE02OFFA72());
msgFrac.setE02OFFVA2(msgFrac.getE02OFFV72());
msgFrac.setE02OFFOP3(msgFrac.getE02OFFO73());
msgFrac.setE02OFFGL3(msgFrac.getE02OFFG73());
msgFrac.setE02OFFCO3(msgFrac.getE02OFFC73());
msgFrac.setE02OFFBK3(msgFrac.getE02OFFB73());
msgFrac.setE02OFFBR3(msgFrac.getE02OFFR73());
msgFrac.setE02OFFCY3(msgFrac.getE02OFFY73());
msgFrac.setE02OFFAC3(msgFrac.getE02OFFA73());
msgFrac.setE02OFFVA3(msgFrac.getE02OFFV73());
msgFrac.setE02OFFOP4(msgFrac.getE02OFFO74());
msgFrac.setE02OFFGL4(msgFrac.getE02OFFG74());
msgFrac.setE02OFFCO4(msgFrac.getE02OFFC74());
msgFrac.setE02OFFBK4(msgFrac.getE02OFFB74());
msgFrac.setE02OFFBR4(msgFrac.getE02OFFR74());
msgFrac.setE02OFFCY4(msgFrac.getE02OFFY74());
msgFrac.setE02OFFAC4(msgFrac.getE02OFFA74());
msgFrac.setE02OFFVA4(msgFrac.getE02OFFV74());
msgFrac.setE02OFFOP5(msgFrac.getE02OFFO75());
msgFrac.setE02OFFGL5(msgFrac.getE02OFFG75());
msgFrac.setE02OFFCO5(msgFrac.getE02OFFC75());
msgFrac.setE02OFFBK5(msgFrac.getE02OFFB75());
msgFrac.setE02OFFBR5(msgFrac.getE02OFFR75());
msgFrac.setE02OFFCY5(msgFrac.getE02OFFY75());
msgFrac.setE02OFFAC5(msgFrac.getE02OFFA75());
msgFrac.setE02OFFVA5(msgFrac.getE02OFFV75());
} 
if (CurrRow == 8) {
msgFrac.setE02REQPOS("08");
userPO.setHeader1("8");
msgFrac.setE02OFFVA1(msgFrac.getE02OFFV81());
msgFrac.setE02OFFOP1(msgFrac.getE02OFFO81());
msgFrac.setE02OFFGL1(msgFrac.getE02OFFG81());
msgFrac.setE02OFFCO1(msgFrac.getE02OFFC81());
msgFrac.setE02OFFBK1(msgFrac.getE02OFFB81());
msgFrac.setE02OFFBR1(msgFrac.getE02OFFR81());
msgFrac.setE02OFFCY1(msgFrac.getE02OFFY81());
msgFrac.setE02OFFAC1(msgFrac.getE02OFFA81());
msgFrac.setE02OFFOP2(msgFrac.getE02OFFO82());
msgFrac.setE02OFFGL2(msgFrac.getE02OFFG82());
msgFrac.setE02OFFCO2(msgFrac.getE02OFFC82());
msgFrac.setE02OFFBK2(msgFrac.getE02OFFB82());
msgFrac.setE02OFFBR2(msgFrac.getE02OFFR82());
msgFrac.setE02OFFCY2(msgFrac.getE02OFFY82());
msgFrac.setE02OFFAC2(msgFrac.getE02OFFA82());
msgFrac.setE02OFFVA2(msgFrac.getE02OFFV82());
msgFrac.setE02OFFOP3(msgFrac.getE02OFFO83());
msgFrac.setE02OFFGL3(msgFrac.getE02OFFG83());
msgFrac.setE02OFFCO3(msgFrac.getE02OFFC83());
msgFrac.setE02OFFBK3(msgFrac.getE02OFFB83());
msgFrac.setE02OFFBR3(msgFrac.getE02OFFR83());
msgFrac.setE02OFFCY3(msgFrac.getE02OFFY83());
msgFrac.setE02OFFAC3(msgFrac.getE02OFFA83());
msgFrac.setE02OFFVA3(msgFrac.getE02OFFV83());
msgFrac.setE02OFFOP4(msgFrac.getE02OFFO84());
msgFrac.setE02OFFGL4(msgFrac.getE02OFFG84());
msgFrac.setE02OFFCO4(msgFrac.getE02OFFC84());
msgFrac.setE02OFFBK4(msgFrac.getE02OFFB84());
msgFrac.setE02OFFBR4(msgFrac.getE02OFFR84());
msgFrac.setE02OFFCY4(msgFrac.getE02OFFY84());
msgFrac.setE02OFFAC4(msgFrac.getE02OFFA84());
msgFrac.setE02OFFVA4(msgFrac.getE02OFFV84());
msgFrac.setE02OFFOP5(msgFrac.getE02OFFO85());
msgFrac.setE02OFFGL5(msgFrac.getE02OFFG85());
msgFrac.setE02OFFCO5(msgFrac.getE02OFFC85());
msgFrac.setE02OFFBK5(msgFrac.getE02OFFB85());
msgFrac.setE02OFFBR5(msgFrac.getE02OFFR85());
msgFrac.setE02OFFCY5(msgFrac.getE02OFFY85());
msgFrac.setE02OFFAC5(msgFrac.getE02OFFA85());
msgFrac.setE02OFFVA5(msgFrac.getE02OFFV85());
} 
if (CurrRow == 9) {
msgFrac.setE02REQPOS("09");
userPO.setHeader1("9");
msgFrac.setE02OFFVA1(msgFrac.getE02OFFV91());
msgFrac.setE02OFFOP1(msgFrac.getE02OFFO91());
msgFrac.setE02OFFGL1(msgFrac.getE02OFFG91());
msgFrac.setE02OFFCO1(msgFrac.getE02OFFC91());
msgFrac.setE02OFFBK1(msgFrac.getE02OFFB91());
msgFrac.setE02OFFBR1(msgFrac.getE02OFFR91());
msgFrac.setE02OFFCY1(msgFrac.getE02OFFY91());
msgFrac.setE02OFFAC1(msgFrac.getE02OFFA91());
msgFrac.setE02OFFOP2(msgFrac.getE02OFFO92());
msgFrac.setE02OFFGL2(msgFrac.getE02OFFG92());
msgFrac.setE02OFFCO2(msgFrac.getE02OFFC92());
msgFrac.setE02OFFBK2(msgFrac.getE02OFFB92());
msgFrac.setE02OFFBR2(msgFrac.getE02OFFR92());
msgFrac.setE02OFFCY2(msgFrac.getE02OFFY92());
msgFrac.setE02OFFAC2(msgFrac.getE02OFFA92());
msgFrac.setE02OFFVA2(msgFrac.getE02OFFV92());
msgFrac.setE02OFFOP3(msgFrac.getE02OFFO93());
msgFrac.setE02OFFGL3(msgFrac.getE02OFFG93());
msgFrac.setE02OFFCO3(msgFrac.getE02OFFC93());
msgFrac.setE02OFFBK3(msgFrac.getE02OFFB93());
msgFrac.setE02OFFBR3(msgFrac.getE02OFFR93());
msgFrac.setE02OFFCY3(msgFrac.getE02OFFY93());
msgFrac.setE02OFFAC3(msgFrac.getE02OFFA93());
msgFrac.setE02OFFVA3(msgFrac.getE02OFFV93());
msgFrac.setE02OFFOP4(msgFrac.getE02OFFO94());
msgFrac.setE02OFFGL4(msgFrac.getE02OFFG94());
msgFrac.setE02OFFCO4(msgFrac.getE02OFFC94());
msgFrac.setE02OFFBK4(msgFrac.getE02OFFB94());
msgFrac.setE02OFFBR4(msgFrac.getE02OFFR94());
msgFrac.setE02OFFCY4(msgFrac.getE02OFFY94());
msgFrac.setE02OFFAC4(msgFrac.getE02OFFA94());
msgFrac.setE02OFFVA4(msgFrac.getE02OFFV94());
msgFrac.setE02OFFOP5(msgFrac.getE02OFFO95());
msgFrac.setE02OFFGL5(msgFrac.getE02OFFG95());
msgFrac.setE02OFFCO5(msgFrac.getE02OFFC95());
msgFrac.setE02OFFBK5(msgFrac.getE02OFFB95());
msgFrac.setE02OFFBR5(msgFrac.getE02OFFR95());
msgFrac.setE02OFFCY5(msgFrac.getE02OFFY95());
msgFrac.setE02OFFAC5(msgFrac.getE02OFFA95());
msgFrac.setE02OFFVA5(msgFrac.getE02OFFV95());
} 
if (CurrRow == 0) {
msgFrac.setE02REQPOS("10");
userPO.setHeader1("0");
msgFrac.setE02OFFVA1(msgFrac.getE02OFFV01());
msgFrac.setE02OFFOP1(msgFrac.getE02OFFO01());
msgFrac.setE02OFFGL1(msgFrac.getE02OFFG01());
msgFrac.setE02OFFCO1(msgFrac.getE02OFFC01());
msgFrac.setE02OFFBK1(msgFrac.getE02OFFB01());
msgFrac.setE02OFFBR1(msgFrac.getE02OFFR01());
msgFrac.setE02OFFCY1(msgFrac.getE02OFFY01());
msgFrac.setE02OFFAC1(msgFrac.getE02OFFA01());
msgFrac.setE02OFFOP2(msgFrac.getE02OFFO02());
msgFrac.setE02OFFGL2(msgFrac.getE02OFFG02());
msgFrac.setE02OFFCO2(msgFrac.getE02OFFC02());
msgFrac.setE02OFFBK2(msgFrac.getE02OFFB02());
msgFrac.setE02OFFBR2(msgFrac.getE02OFFR02());
msgFrac.setE02OFFCY2(msgFrac.getE02OFFY02());
msgFrac.setE02OFFAC2(msgFrac.getE02OFFA02());
msgFrac.setE02OFFVA2(msgFrac.getE02OFFV02());
msgFrac.setE02OFFOP3(msgFrac.getE02OFFO03());
msgFrac.setE02OFFGL3(msgFrac.getE02OFFG03());
msgFrac.setE02OFFCO3(msgFrac.getE02OFFC03());
msgFrac.setE02OFFBK3(msgFrac.getE02OFFB03());
msgFrac.setE02OFFBR3(msgFrac.getE02OFFR03());
msgFrac.setE02OFFCY3(msgFrac.getE02OFFY03());
msgFrac.setE02OFFAC3(msgFrac.getE02OFFA03());
msgFrac.setE02OFFVA3(msgFrac.getE02OFFV03());
msgFrac.setE02OFFOP4(msgFrac.getE02OFFO04());
msgFrac.setE02OFFGL4(msgFrac.getE02OFFG04());
msgFrac.setE02OFFCO4(msgFrac.getE02OFFC04());
msgFrac.setE02OFFBK4(msgFrac.getE02OFFB04());
msgFrac.setE02OFFBR4(msgFrac.getE02OFFR04());
msgFrac.setE02OFFCY4(msgFrac.getE02OFFY04());
msgFrac.setE02OFFAC4(msgFrac.getE02OFFA04());
msgFrac.setE02OFFVA4(msgFrac.getE02OFFV04());
msgFrac.setE02OFFOP5(msgFrac.getE02OFFO05());
msgFrac.setE02OFFGL5(msgFrac.getE02OFFG05());
msgFrac.setE02OFFCO5(msgFrac.getE02OFFC05());
msgFrac.setE02OFFBK5(msgFrac.getE02OFFB05());
msgFrac.setE02OFFBR5(msgFrac.getE02OFFR05());
msgFrac.setE02OFFCY5(msgFrac.getE02OFFY05());
msgFrac.setE02OFFAC5(msgFrac.getE02OFFA05());
msgFrac.setE02OFFVA5(msgFrac.getE02OFFV05());
} 

if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
%>
<SCRIPT Language="Javascript">
        showErrors();
</SCRIPT>
<%}%>
 
</head>
<body>

<SCRIPT Language="Javascript">
  builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
function goAction(opt) {
	document.forms[0].H02FLGWK3.value = opt;
	document.forms[0].submit();
}
</SCRIPT>

<H3 align="center">Conceptos Contables - Fraccionamiento de Operaciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="foreign_currency_exchange_conceptos, EPR0353"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEPR0353">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
<INPUT TYPE=HIDDEN NAME="H02FLGWK3">
  <TABLE  id="mainTable" class="tableinfo">
  <TR><TD>
   <table id="headTable" width="100%">
    <tr id="trdark" align="center"> 
      <td nowrap align="center" >Concepto</td>
      <td nowrap align="center" >Banco</td>
      <td nowrap align="center" >Sucursal</td>
      <td nowrap align="center" >Moneda</td>
      <td nowrap align="center" >Cuenta Cliente</td>
      <td nowrap align="center" >Valor</td>
    </tr>
    <tr id="trclear" align="center"> 
      <td nowrap > 
          <div align="center" nowrap> 
          <input type="text" name="E02OFFOP1" size="3" maxlength="2" value="<%= msgFrac.getField("E02OFFOP1").getString().trim() %>" readonly>
          <input type="hidden" name="E02OFFGL1" value="<%= msgFrac.getField("E02OFFGL1").getString().trim() %>">
          <input type="text" name="E02OFFCO1" size="25" maxlength="25" readonly value="<%= msgFrac.getField("E02OFFCO1").getString().trim() %>"
          oncontextmenu="showPopUp(conceptHelp,this.name,'<%= msgFrac.getE02REQBNK() %>','','E02OFFGL1','E02OFFOP1','34'); return false;">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFBK1" size="2" maxlength="2" value="<%= msgFrac.getE02OFFBK1() %>">
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFBR1" size="3" maxlength="3" value="<%= msgFrac.getE02OFFBR1() %>"
             oncontextmenu="showPopUp(branchHelp,this.name,'<%= msgFrac.getE02REQBNK() %>','','','',''); return false;">
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFCY1" size="3" maxlength="3" value="<%= msgFrac.getE02OFFCY1() %>"
             oncontextmenu="showPopUp(currencyHelp,this.name,'<%= msgFrac.getE02REQBNK() %>','','','',''); return false;">
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFAC1" size="12" maxlength="12"  value="<%= msgFrac.getE02OFFAC1() %>"
             oncontextmenu="showPopUp(lnreferHelp,this.name,'<%= msgFrac.getE02REQBNK() %>','','<%= msgFrac.getE02REQCUS() %>','','RT'); return false;">
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFVA1" size="18" maxlength="15" value="<%= msgFrac.getE02OFFVA1() %>" onkeypress="enterDecimal()">
          </div>
       </td>
      </tr>
      <tr id="trdark" align="center"> 
      <td nowrap > 
          <div align="center" nowrap> 
          <input type="text" name="E02OFFOP2" size="3" maxlength="2" value="<%= msgFrac.getField("E02OFFOP2").getString().trim() %>" readonly>
          <input type="hidden" name="E02OFFGL2" value="<%= msgFrac.getField("E02OFFGL2").getString().trim() %>">
          <input type="text" name="E02OFFCO2" size="25" maxlength="25" readonly value="<%= msgFrac.getField("E02OFFCO2").getString().trim() %>"
          oncontextmenu="showPopUp(conceptHelp,this.name,'<%= msgFrac.getE02REQBNK() %>','','E02OFFGL2','E02OFFOP2','34'); return false;">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFBK2" size="2" maxlength="2" value="<%= msgFrac.getE02OFFBK2() %>">
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFBR2" size="3" maxlength="3" value="<%= msgFrac.getE02OFFBR2() %>"
             oncontextmenu="showPopUp(branchHelp,this.name,'<%= msgFrac.getE02REQBNK() %>','','','',''); return false;">
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFCY2" size="3" maxlength="3" value="<%= msgFrac.getE02OFFCY2() %>"
             oncontextmenu="showPopUp(currencyHelp,this.name,'<%= msgFrac.getE02REQBNK() %>','','','',''); return false;">
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFAC2" size="12" maxlength="12"  value="<%= msgFrac.getE02OFFAC2() %>"
             oncontextmenu="showPopUp(lnreferHelp,this.name,'<%= msgFrac.getE02REQBNK() %>','','<%= msgFrac.getE02REQCUS() %>','','RT'); return false;">
          </div>
        </td>
        <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFVA2" size="18" maxlength="15" value="<%= msgFrac.getE02OFFVA2() %>" onkeypress="enterDecimal()">
          </div>
        </td>
      </tr>
      <tr id="trclear" align="center"> 
      <td nowrap > 
          <div align="center" nowrap> 
          <input type="text" name="E02OFFOP3" size="3" maxlength="2" value="<%= msgFrac.getField("E02OFFOP3").getString().trim() %>" readonly>
          <input type="hidden" name="E02OFFGL3" value="<%= msgFrac.getField("E02OFFGL3").getString().trim() %>">
          <input type="text" name="E02OFFCO3" size="25" maxlength="25" readonly value="<%= msgFrac.getField("E02OFFCO3").getString().trim() %>"
          oncontextmenu="showPopUp(conceptHelp,this.name,'<%= msgFrac.getE02REQBNK() %>','','E02OFFGL3','E02OFFOP3','34'); return false;">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFBK3" size="2" maxlength="2" value="<%= msgFrac.getE02OFFBK3() %>">
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFBR3" size="3" maxlength="3" value="<%= msgFrac.getE02OFFBR3() %>"
             oncontextmenu="showPopUp(branchHelp,this.name,'<%= msgFrac.getE02REQBNK() %>','','','',''); return false;">
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFCY3" size="3" maxlength="3" value="<%= msgFrac.getE02OFFCY3() %>"
             oncontextmenu="showPopUp(currencyHelp,this.name,'<%= msgFrac.getE02REQBNK() %>','','','',''); return false;">
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFAC3" size="12" maxlength="12"  value="<%= msgFrac.getE02OFFAC3() %>"
             oncontextmenu="showPopUp(lnreferHelp,this.name,'<%= msgFrac.getE02REQBNK() %>','','<%= msgFrac.getE02REQCUS() %>','','RT'); return false;">
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFVA3" size="18" maxlength="15" value="<%= msgFrac.getE02OFFVA3() %>" onkeypress="enterDecimal()">
          </div>
       </td>
      </tr>
      <tr id="trdark" align="center"> 
      <td nowrap > 
          <div align="center" nowrap> 
          <input type="text" name="E02OFFOP4" size="3" maxlength="2" value="<%= msgFrac.getField("E02OFFOP4").getString().trim() %>" readonly>
          <input type="hidden" name="E02OFFGL4" value="<%= msgFrac.getField("E02OFFGL4").getString().trim() %>">
          <input type="text" name="E02OFFCO4" size="25" maxlength="25" readonly value="<%= msgFrac.getField("E02OFFCO4").getString().trim() %>"
          oncontextmenu="showPopUp(conceptHelp,this.name,'<%= msgFrac.getE02REQBNK() %>','','E02OFFGL4','E02OFFOP4','34'); return false;">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFBK4" size="2" maxlength="2" value="<%= msgFrac.getE02OFFBK4() %>">
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFBR4" size="3" maxlength="3" value="<%= msgFrac.getE02OFFBR4() %>"
             oncontextmenu="showPopUp(branchHelp,this.name,'<%= msgFrac.getE02REQBNK() %>','','','',''); return false;">
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFCY4" size="3" maxlength="3" value="<%= msgFrac.getE02OFFCY4() %>"
             oncontextmenu="showPopUp(currencyHelp,this.name,'<%= msgFrac.getE02REQBNK() %>','','','',''); return false;">
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFAC4" size="12" maxlength="12"  value="<%= msgFrac.getE02OFFAC4() %>"
             oncontextmenu="showPopUp(lnreferHelp,this.name,'<%= msgFrac.getE02REQBNK() %>','','<%= msgFrac.getE02REQCUS() %>','','RT'); return false;">
          </div>
        </td>
        <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFVA4" size="18" maxlength="15" value="<%= msgFrac.getE02OFFVA4() %>" onkeypress="enterDecimal()">
          </div>
        </td>
      </tr>
      <tr id="trclear" align="center"> 
      <td nowrap > 
          <div align="center" nowrap> 
          <input type="text" name="E02OFFOP5" size="3" maxlength="2" value="<%= msgFrac.getField("E02OFFOP5").getString().trim() %>" readonly>
          <input type="hidden" name="E02OFFGL5" value="<%= msgFrac.getField("E02OFFGL5").getString().trim() %>">
          <input type="text" name="E02OFFCO5" size="25" maxlength="25" readonly value="<%= msgFrac.getField("E02OFFCO5").getString().trim() %>"
          oncontextmenu="showPopUp(conceptHelp,this.name,'<%= msgFrac.getE02REQBNK() %>','','E02OFFGL5','E02OFFOP5','34'); return false;">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFBK5" size="2" maxlength="2" value="<%= msgFrac.getE02OFFBK5() %>">
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFBR5" size="3" maxlength="3" value="<%= msgFrac.getE02OFFBR5() %>"
             oncontextmenu="showPopUp(branchHelp,this.name,'<%= msgFrac.getE02REQBNK() %>','','','',''); return false;">
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFCY5" size="3" maxlength="3" value="<%= msgFrac.getE02OFFCY5() %>"
             oncontextmenu="showPopUp(currencyHelp,this.name,'<%= msgFrac.getE02REQBNK() %>','','','',''); return false;">
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFAC5" size="12" maxlength="12"  value="<%= msgFrac.getE02OFFAC5() %>"
             oncontextmenu="showPopUp(lnreferHelp,this.name,'<%= msgFrac.getE02REQBNK() %>','','<%= msgFrac.getE02REQCUS() %>','','RT'); return false;">
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFVA5" size="18" maxlength="15" value="<%= msgFrac.getE02OFFVA5() %>" onkeypress="enterDecimal()">
          </div>
       </td>
      </tr>
     </table>       
   </TD>  
</TR>	
</TABLE>    
  <p align="center"> 
    <input id="EIBSBTN" type="submit" name="Submit" value="Enviar" onclick="goAction('E')">
    <input id="EIBSBTN" type="submit" name="Submit" value="Regresar" onclick="goAction('R')">
  </p>
  <script language="JavaScript">
//	  document.forms[0].E02OFFOP1.focus();
//	  document.forms[0].E02OFFOP1.select();
  </script>
</form>
</body>
</html>			
