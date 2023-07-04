<%@ page contentType="application/x-javascript" %>

// Global variable for english
var fieldName;
var fieldDesc;
var fieldAux1;
var fieldAux2;
var fieldAux3;
var language = "s/";
var prefix = "<%=request.getContextPath()%>/pages/";
var webapp = "<%=request.getContextPath()%>";

function GetFeRef(name,typ,prd,desc){

    page = webapp + "/servlet/datapro.eibs.helps.JSEWD0325";
	fieldName=name;
	fieldAux1=typ;
	fieldAux2=prd;
	fieldAux3=desc;
	CenterWindow(page,500,230,3);
}

function GetCreditLineT(name,custnum)
{
	page = webapp + "/servlet/datapro.eibs.forex.JSEWD0015T?CustNum=" + custnum;
	fieldName=name;
	CenterWindow(page,630,90,1);
}

function GetBrokerT(name,desc){

    page = webapp + "/servlet/datapro.eibs.helps.JSEWD0306";
	fieldName=name;
	fieldAux1=desc;
	CenterWindow(page,500,230,3);
}

function GetFexAcc(name){

    page = webapp + "/servlet/datapro.eibs.helps.JSEWD0326";
	fieldName=name;
	CenterWindow(page,500,230,3);
}

function GetFraAcc(name){

    page = webapp + "/servlet/datapro.eibs.helps.JSEWD0326F";
	fieldName=name;
	CenterWindow(page,500,230,3);
}

function GetDealsAcc(name){

    page = webapp + "/servlet/datapro.eibs.helps.JSEWD0326D";
	fieldName=name;
	CenterWindow(page,500,230,3);
}

function showCCYDetail(ccy)
{
	page = webapp + "/servlet/datapro.eibs.forex.JSEWD0327?Currency=" + ccy ;
	fieldName=name;
	CenterWindow(page,560,500,2);
}

function GetTreasuryProdType(name)
{
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0301";
	fieldName=name;
	CenterWindow(page,430,200,3);
}

function GetClassFex(clas,opt,ccy){

    page = webapp + "/servlet/datapro.eibs.helps.JSEWD0320?opt=" + opt + "&ccy=" + ccy;
	fieldName=clas;
	CenterWindow(page,500,230,3);
}

function showFxRang(ref, useid)
{
	page = webapp + "/servlet/datapro.eibs.forex.JSEFE0130A?SCREEN=2&E01FESREF=" + ref + "&E01FESDID=" + useid;
	CenterWindow(page,560,500,2);
}

function GetAccountT(acc,bnk,app,cun)
{
    page = webapp + "/servlet/datapro.eibs.forex.JSEWD0005T?sel=N&bnk=" + bnk + "&app=" + app + "&cun=" + cun;
	fieldName=acc;
	CenterWindow(page,500,230,3); 
}


function showTodDealsFr(ref,dealer,typ,acd)
{
	page = webapp + "/servlet/datapro.eibs.forex.JSEWD0325BI?SCREEN=2&ref=" + ref + "&dealer=" + dealer + "&typ=" + typ + "&acd=" + acd;
	CenterWindow(page,560,500,2);
}

function showFxDeals(acc,prog)
{
	page = webapp + "/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=2200&REFERENCE=" + acc + "&PROGRAM=" + prog;
	CenterWindow(page,560,500,2);
}

function showFxAcc(ref)
{
	page = webapp + "/servlet/datapro.eibs.forex.JSEFE0160?SCREEN=40&E01FEMACC=" + ref;
	CenterWindow(page,560,500,2);
}

function showFRAAcc(ref)
{
	page = webapp + "/servlet/datapro.eibs.forex.JSETR0160?SCREEN=100&E01FRAACC=" + ref;
	CenterWindow(page,560,500,2);
}

function showFRAAccInq(ref)
{
	page = webapp + "/servlet/datapro.eibs.forex.JSETR0160I?SCREEN=100&E01FRAACC=" + ref;
	CenterWindow(page,560,500,2);
}
function showFRAAcc_Stl(ref)
{
	page = webapp + "/servlet/datapro.eibs.forex.JSETR0150?SCREEN=70&E01FRAACC=" + ref;
}
function showDeaAcc(ref)
{
	page = webapp + "/servlet/datapro.eibs.forex.JSEDL0130IT?SCREEN=600&E01DEAACC=" + ref;
	CenterWindow(page,560,500,2);
}

function showInqApprovalT(app, account, type, cus) {
var formLength= document.forms[0].elements.length;
   for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	var elementValue= document.forms[0].elements[n].value;
      	if(elementName == "ACCNUM" && elementValue== account)
      	{
        		document.forms[0].elements[n].click();
        		break;
      	}
      }
    if ( app == '13' && type == '2') {
    	page = webapp + "/servlet/datapro.eibs.forex.JSEDL0108B?SCREEN=200&E02DEAACC=" + account + "&E02DEACUN=" + cus;
    } else {
		page = webapp + "/servlet/datapro.eibs.forex.JSEDL0140T?SCREEN=3&ACCNUM=" + account + "&appCode=" + app + "&typeCode=" + type;
	}
	CenterWindow(page,600,500,2);

}

function GetProductT(name,desc,ataken,atype,appcode)
{
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0008T?ATAKEN=" + ataken +"&ATYPE="+ atype +"&FLAG=X&APPCODE=" + appcode;
	fieldName=name;
	fieldAux1 = desc;
	ATAKEN = ataken;
	ATYPE = atype;
	APPCODE = appcode;
	CenterWindow(page,600,400,2);
}

function GetUserT(name){
    page = webapp + "/servlet/datapro.eibs.helps.JSEWD0330";
	fieldName=name;
	CenterWindow(page,500,230,3);
}


function goOpenAction(opt) {
var myopener = top.opener.document;
 top.close();
 if ( opt=="A" ) {
   myopener.anchors("linkApproval").click();
 } else {
   myopener.anchors("linkReject").click();
 }
}


// Treasury OPTIONS
//   Foreign Exchange

//CDS
var cd_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=1>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=63>Renovación</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=65>Lavado de Dinero</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=61>Códigos Especiales</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=51>Titulares</A><BR> "  +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=57>Beneficiarios</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=55>Instrucciones Especiales</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=31>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=41>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var cd_bo_act_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=1>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=63>Renovación</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=65>Lavado de Dinero</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=61>Codigos Especiales</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=51>Titulares</A><BR> "  +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=57>Beneficiarios</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=55>Instrucciones Especiales</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=41>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//TDS
var td_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=3>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=63>Renovación</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=65>Lavado de Dinero</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=61>Códigos Especiales</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=51>Titulares</A><BR> "  +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=57>Beneficiarios</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=55>Instrucciones Especiales</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=33>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=43>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var td_bo_act_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=3>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=63>Renovación</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=65>Lavado de Dinero</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=61>Códigos Especiales</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=51>Titulares</A><BR> "  +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=57>Beneficiarios</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=55>Instrucciones Especiales</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=43>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//FFS
var ff_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=5>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=63>Renovación</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=65>Lavado de Dinero</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=61>Códigos Especiales</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=51>Titulares</A><BR> "  +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=57>Beneficiarios</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=55>Instrucciones Especiales</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=37>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=47>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var ff_bo_act_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=5>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=63>Renovación</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=65>Lavado de Dinero</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=61>Códigos Especiales</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=51>Titulares</A><BR> "  +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=57>Beneficiarios</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=55>Instrucciones Especiales</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=47>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//TPS
var tp_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=7>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=63>Renovación</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=65>Lavado de Dinero</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=61>Códigos Especiales</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=51>Titulares</A><BR> "  +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=57>Beneficiarios</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=55>Instrucciones Especiales</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=35>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=45>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var tp_bo_act_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=7>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=63>Renovación</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=65>Lavado de Dinero</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=61>Códigos Especiales</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=51>Titulares</A><BR> "  +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=57>Beneficiarios</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=55>Instrucciones Especiales</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=45>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";


//ACC
var ac_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=9>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=63>Renovación</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=65>Lavado de Dinero</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=61>Códigos Especiales</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=51>Titulares</A><BR> "  +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=57>Beneficiarios</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=55>Instrucciones Especiales</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=39>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=49>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var ac_bo_act_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=9>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=63>Renovación</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=65>Lavado de Dinero</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=61>Códigos Especiales</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=51>Titulares</A><BR> "  +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=57>Beneficiarios</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=55>Instrucciones Especiales</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=49>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";


//Spot
var sf_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120B?SCREEN=1>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120B?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=31>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=41>Mensaje de Confirmación</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=73>Notificación</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Forward
var fw_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120B?SCREEN=3>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120B?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=43>Mensaje de Confirmación</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=75>Notificación</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//NDF
var nd_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120B?SCREEN=5>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120B?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=45>Mensaje de Confirmación</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Option
var op_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120B?SCREEN=7>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120B?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=47>Mensaje de Confirmación</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=77>Notificación</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Swap
var sw_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120B?SCREEN=9>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120B?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=39>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=49>Confirmation Spot</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=59>Confirmation Forward</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=79>Notificación</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//PLP
var cp_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0120B?SCREEN=1>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0120B?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=7>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=11>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//FRA
var fra_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSETR0130?SCREEN=1>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0340?SCREEN=1>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0340?SCREEN=3>Mensaje de Confirmación</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";


//Maintenance

//Swap
var swm_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140?SCREEN=9>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100M?SCREEN=39>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100M?SCREEN=49>Mensaje de Confirmación</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100M?SCREEN=87>Notificación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Option
var opm_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140?SCREEN=7>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100M?SCREEN=47>Mensaje de Confirmación</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100M?SCREEN=85>Notificación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//NDF
var ndm_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140?SCREEN=5>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100M?SCREEN=45>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Forward
var fwm_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140?SCREEN=3>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100M?SCREEN=43>Mensaje de Confirmación</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100M?SCREEN=85>Notificación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>" ;

//Spot
var sfm_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140?SCREEN=1>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100M?SCREEN=31>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100M?SCREEN=41>Mensaje de Confirmación</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100M?SCREEN=81>Notificación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Approval
//Swap
var swa_bo_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140A?SCREEN=9>Información Básica</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=39>Mensaje Swift</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=49>Confirmation Spot<BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=59>Confirmation Forward</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=67>Notificación</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= \"javascript:appPrint();\" >Imprimir</A><BR>" +
  "<A HREF= \"javascript:goOpenAction('A')\" >Aprobar</A><BR>" +
  "<A HREF= \"javascript:goOpenAction('R')\" >Rechazar</A><BR>" +
   "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Option
var opa_bo_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140A?SCREEN=7>Información Básica</A><BR>"+
    "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=37>Mensaje Swift</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=47>Mensaje de Confirmación</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=65>Notificación</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= \"javascript:appPrint();\" >Imprimir</A><BR>" +
  "<A HREF= \"javascript:goOpenAction('A')\" >Aprobar</A><BR>" +
  "<A HREF= \"javascript:goOpenAction('R')\" >Rechazar</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//NDF
var nda_bo_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140A?SCREEN=5>Información Básica</A><BR>"+
    "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=35>Mensaje Swift</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=45>Mensaje de Confirmación</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= \"javascript:appPrint();\" >Imprimir</A><BR>" +
  "<A HREF= \"javascript:goOpenAction('A')\" >Aprobar</A><BR>" +
  "<A HREF= \"javascript:goOpenAction('R')\" >Rechazar</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Forward
var fwa_bo_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140A?SCREEN=3>Información Básica</A><BR>"+
    "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=33>Mensaje Swift</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=43>Mensaje de Confirmación</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=63>Notificación</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= \"javascript:appPrint();\" >Imprimir</A><BR>" +
  "<A HREF= \"javascript:goOpenAction('A')\" >Aprobar</A><BR>" +
  "<A HREF= \"javascript:goOpenAction('R')\" >Rechazar</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Spot
var sfa_bo_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140A?SCREEN=1>Información Básica</A><BR>"+
    "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=31>Mensaje Swift</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=41>Mensaje de Confirmación</A><BR>"+
    "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=61>Notificación</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= \"javascript:appPrint();\" >Imprimir</A><BR>" +
  "<A HREF= \"javascript:goOpenAction('A')\" >Aprobar</A><BR>" +
  "<A HREF= \"javascript:goOpenAction('R')\" >Rechazar</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//FRA

//FRA
var fra_app_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140A?SCREEN=80>Información Básica</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0340?SCREEN=5>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0340?SCREEN=7>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= \"javascript:appPrint();\" >Imprimir</A><BR>" +
 "<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
 "<A HREF= \"javascript:goOpenerAction('R')\" >Rechazar</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

 //Foreign Exchange Inquiry

//Option
var opi_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0160?SCREEN=7>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0160?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100I?SCREEN=37>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100I?SCREEN=47>Mensaje de Confirmación</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100I?SCREEN=65>Notificación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//NDF
var ndi_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0160?SCREEN=5>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0160?SCREEN=55>Instrucciones Especiales</A><BR>"+
    "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100I?SCREEN=35>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100I?SCREEN=45>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Forward
var fwi_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0160?SCREEN=3>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0160?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100I?SCREEN=39>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100I?SCREEN=59>Mensaje de Confirmación</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100I?SCREEN=63>Notificación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Spot
var sfi_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0160?SCREEN=1>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0160?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100I?SCREEN=31>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100I?SCREEN=41>Mensaje de Confirmación</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100I?SCREEN=61>Notificación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

 //Treasury Deals Inquiry
var cdt_i_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130IT?SCREEN=57>Account Statement</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130IT?SCREEN=13>Balances </A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130IT?SCREEN=41>Información Básica </A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130IT?SCREEN=27>Códigos Especiales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130IT?SCREEN=31>Instrucciones Especiales</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130I?SCREEN=61>Mensaje Swift</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130I?SCREEN=63>Mensaje de Confirmación</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Treasury PLPs Inquiry
var plpt_i_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130IT?SCREEN=57>Account Statement</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130IT?SCREEN=13>Balances </A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130IT?SCREEN=41>Información Básica </A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130IT?SCREEN=56>Sumario</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130IT?SCREEN=27>Códigos Especiales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130IT?SCREEN=31>Instrucciones Especiales</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130I?SCREEN=61>Mensaje Swift</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130I?SCREEN=63>Mensaje de Confirmación</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

 // CP maintenance

var cp_m_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105?SCREEN=3>Información Básica </A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105?SCREEN=5>Cambio de Tasa</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105?SCREEN=9>Precio de Mercado </A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105?SCREEN=7>Instrucciones de Pago</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// CP Inquiry

var cp_i_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105I?SCREEN=13>Estado de Cuenta</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105I?SCREEN=3>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105I?SCREEN=11>Resumen de Transacciones</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105I?SCREEN=9>Precio de Mercado</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105I?SCREEN=7>Instrucciones de Pago</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Approval

// CP approvals

var cp_a_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105A?SCREEN=3>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105A?SCREEN=9>Precio de Mercado</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105A?SCREEN=7>Instrucciones de Pago</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">" +
  "<A HREF= \"javascript:appPrint();\" >Imprimir</A><BR>" +
  "<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
  "<A HREF= \"javascript:goOpenerAction('R')\" >Rechazar</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Deals back office maintenance

var cdm_m_opt =
	 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=3>Información Básica </A><BR>"+
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=33>Renovación</A><BR>" +
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=52>Lavado de Dinero</A><BR>" +
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=21>Códigos Especiales</A><BR>" +
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=7>Titulares</A><BR> "  +
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=17>Beneficiarios</A><BR>" +
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=15>Instrucciones Especiales</A><BR>" +
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=19>Rate Change</A><BR> " +
	 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130M?SCREEN=61>Mensaje Swift</A><BR>"+
  	 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130M?SCREEN=63>Mensaje de Confirmación</A><BR>"+
  	 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
     "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var cdm_m_act_opt =
	 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=3>Información Básica </A><BR>"+
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=33>Renovación</A><BR>" +
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=52>Lavado de Dinero</A><BR>" +
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=21>Códigos Especiales</A><BR>" +
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=7>Titulares</A><BR> "  +
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=17>Beneficiarios</A><BR>" +
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=15>Instrucciones Especiales</A><BR>" +
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=19>Rate Change</A><BR> " +
	 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  	 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130M?SCREEN=63>Mensaje de Confirmación</A><BR>"+
  	 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
     "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Deals

var cdt_a_opt =
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=3>Información Básica</A><BR>"+
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=33>Renovación</A><BR>" +
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=21>Códigos Especiales</A><BR>" +
	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130A?SCREEN=61>Mensaje Swift</A><BR>"+
  	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130A?SCREEN=63>Mensaje de Confirmación</A><BR>"+
	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
	"<A HREF= \"javascript:appPrint();\" >Imprimir</A><BR>" +
  	"<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
  	"<A HREF= \"javascript:goOpenerAction('R')\" >Rechazar</A><BR>" +
  	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
   	"<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var cdt_a_act_opt =
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=3>Información Básica</A><BR>"+
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=33>Renovación</A><BR>" +
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=21>Códigos Especiales</A><BR>" +
	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130A?SCREEN=63>Mensaje de Confirmación</A><BR>"+
	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
	"<A HREF= \"javascript:appPrint();\" >Imprimir</A><BR>" +
  	"<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
  	"<A HREF= \"javascript:goOpenerAction('R')\" >Rechazar</A><BR>" +
  	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
   	"<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";
