package datapro.eibs.generic;



import java.util.Vector;

/**
 * Insert the type's description here.
 * Creation date: (8/8/2001 10:41:16 AM)
 * @author: Enrique M. Almonte
 * TODO : sort items
 */
public class JBParseTree {
 
/*declare variables -
[treenode][type] type = IT - Item , FO = Folder Open , FC = Folder Close*/
private Vector links;
private Vector treeSource[];

//Tree Struct Defaults
private String treeImageUrl = "";

private String treeRootFontType = "Dialog";
private String treeRootFontStyle = "bold";
private String treeRootFontSize = "12";
private String treeRootFontColor = "0D23B5";

private String treeFolderFontType = "Dialog";
private String treeFolderFontStyle = "plain";
private String treeFolderFontSize = "10";
private String treeFolderFontColor = "000033";

private String treeItemFontType = "Small";
private String treeItemFontStyle = "plain";
private String treeItemFontSize = "10";
private String treeItemFontColor = "000033";

private String treeRootImage = "cube.gif";
private String treeRootImageOver = "cubeover.gif";

private String treeFolderImage = "cone.gif";
private String treeFolderImageOver = "coneover.gif";

private String treeItemImage = "ball.gif";
private String treeItemImageOver = "ballover.gif";

private String treeRootTitle = "";
private String treeRootDescription = "";

private String treeTargetLink = "";
/**
 * JBParseTree constructor comment.
 */
public JBParseTree() {
	super();
}


/* Method init
@@parms = 
initialize structure of vectors
*/
public void init(){
	links = new java.util.Vector();
	treeSource = new java.util.Vector[2];
	for (int i=0; i<2; i++) {
		treeSource[i] = new java.util.Vector();
	}
}

/* Method isFolder :
@@parms = String element
returns true if the element is a folder
*/
private boolean isFolder(String element)
{
	int position = treeSource[0].indexOf(element.trim());
	return ((position != -1) && (treeSource[1].elementAt(position).equals("FO")));
}
/* Method isItem :
@@parms = String element
returns true if the element is a folder
*/
private boolean isItem(String element)
{
	int position = treeSource[0].indexOf(element.trim());
	return ((position != -1) && (treeSource[1].elementAt(position).equals("IT")));
}
/* Method addElement :
@@parms = String item,folder,link
adds elements into treeSource
*/
private void addElementTree(String element, String type, String link, int position){
	try {
		if (position == 0 ) {
			links.addElement(link);
			treeSource[0].addElement(element);
			treeSource[1].addElement(type);
		}else {
			links.insertElementAt(link,position);
			treeSource[0].insertElementAt(element,position);
			treeSource[1].insertElementAt(type,position);
		}
	} catch (Exception e) {
		System.out.println("Error :" + e);
	}
}

/* Method setItemAsFolder :
@@parms = String item
sets an item as a folder
*/
private void setItemAsFolder(String item){
	treeSource[1].setElementAt("FO",treeSource[0].indexOf(item.trim()));
	addElementTree(item,"FC",(String)links.elementAt(treeSource[0].indexOf(item.trim())),treeSource[0].indexOf(item.trim()) + 1);
}
/* Method addRow
@@parms = String item, String folder, String link
adds a row to treeSource upon type of item or folder ..
*/
public void addRow(String item, String folder, String linkItem, String linkFolder){
	if (treeSource[0].indexOf(item.trim()) > -1 || treeSource[0].indexOf(folder.trim()) > -1){
		// check folder
		if (isFolder(folder)) {
			if (isFolder(item)) {
				setItemAsFolder(item);
			} else {
	 	   		addElementTree(item,"IT",linkItem,treeSource[0].indexOf(folder.trim()) + 1);
	 	   	}
 	    }else {
			    if (isItem(folder)) {
				    setItemAsFolder(folder);
				    addElementTree(item,"IT",linkItem,treeSource[0].indexOf(folder.trim())+1);
			    } else {
				    if (!(isFolder(item) && folder.trim().equals(""))){
				        addElementTree(folder,"FO",linkFolder,0);
				        addElementTree(folder,"FC",linkFolder,0);
				    }
			    }
 	    }
 	    
 	  
 	} else {
	 	if (folder.trim().equals(item.trim())) {
		 	addElementTree(item,"IT",linkItem,0);
	 	}
	 	if (treeSource[0].indexOf(folder.trim()) == -1 && !folder.trim().equals("")){
		 	addElementTree(folder,"FO",linkFolder,0);
			addElementTree(folder,"FC",linkFolder,0);
	 	}
	 	if (treeSource[0].indexOf(item.trim()) == -1){
		 	if (treeSource[0].indexOf(folder.trim()) == -1) {
			 	addElementTree(item,"IT",linkItem,0);
		 	} else {
			 	addElementTree(item,"IT",linkItem,treeSource[0].indexOf(folder.trim())+1);
		 	}
	 	}
	 	
 	}
}
/* Method setRootFont
@@parms = String type, String style, String size, String color)
sets the font for the root folder of the tree
*/
public void setRootFont(String type, String style, String size, String color)
{
 treeRootFontType = type;
 treeRootFontStyle = style;
 treeRootFontSize = size;
 treeRootFontColor = color;	
}

/* Method setFolderFont
@@parms = String type, String style, String size, String color)
sets the font for the Folder folder of the tree
*/
public void setFolderFont(String type, String style, String size, String color)
{
 treeFolderFontType = type;
 treeFolderFontStyle = style;
 treeFolderFontSize = size;
 treeFolderFontColor = color;	
}
/* Method setItemFont
@@parms = String type, String style, String size, String color)
sets the font for the Item folder of the tree
*/
public void setItemFont(String type, String style, String size, String color)
{
 treeItemFontType = type;
 treeItemFontStyle = style;
 treeItemFontSize = size;
 treeItemFontColor = color;	
}

/* Method setImageUrl
@@parms = String url
sets the url for the images tree
*/
public void setImageUrl(String url)
{
 treeImageUrl = url;
}

/* Method setRootImage
@@parms = String image, String imageOver
sets the images of the Root folder
*/
public void setRootImage(String image, String imageOver)
{
  treeRootImage = image;
  treeRootImageOver = imageOver;
}

/* Method setFolderImage
@@parms = String image, String imageOver
sets the image of the Folder folder
*/
public void setFolderImage(String image, String imageOver)
{
  treeFolderImage = image;
  treeFolderImageOver = imageOver;
}

/* Method setFolderImage
@@parms = String image , String imageOver
sets the image of the Folder folder
*/
public void setItemImage(String image, String imageOver)
{
  treeItemImage = image;
  treeItemImageOver = imageOver;
}
/* Method setRootTitle
@@parms = String url
sets the image of the Folder folder
*/
public void setRootTitle(String title, String titleDescription)
{
 treeRootTitle =title;
 treeRootDescription = titleDescription;
}
/* Method setTargetLink
@@parms = String target
sets the target of the links in tree
*/
public void setTargetLink(String target)
{
 treeTargetLink = target;
}

/* Method getTreeStruct
@@parms = String item, String folder, String link
adds a row to treeSource upon type of item or folder ..
*/
public String getTree(){
	String outString ="";
	outString  = "< treecontrol imageurl=\"" + treeImageUrl + "\">\n";
	outString += "< font face=\"" + treeRootFontType + "\"" + " style=\"" + treeRootFontStyle + "\"" + " size="+treeRootFontSize+" >\n";
	outString += "< font color=\""+ treeRootFontColor + "\">\n";
	outString += "	< rootfolder title=\"" + treeRootTitle;
	outString += "\" image=\""+treeRootImage +"\" imageTwo=\""+treeRootImageOver+"\" info=\"" + treeRootDescription;
	outString += "\" target=\""+treeTargetLink + "\">\n";
	for (int i = 0; i < links.size(); i++){
		if (treeSource[1].elementAt(i).equals("FO")){
			outString += "		< font face=\""+treeFolderFontType+"\" style=\""+treeFolderFontStyle+"\" size="+treeFolderFontSize+" color=\""+treeFolderFontColor+"\">\n";
			outString += "		< folder title=\"";
			outString += (String) treeSource[0].elementAt(i);
			outString += "\" expand image=\""+treeFolderImage+"\" imageTwo=\""+treeFolderImageOver+"\" info=\"";
			outString += (String) treeSource[0].elementAt(i);
			outString += "\" link=\"" + links.elementAt(i);
			outString += "\" target=\""+treeTargetLink+"\">\n";
			outString += "		</font>\n";
		} else {
			if (treeSource[1].elementAt(i).equals("FC")) {
				outString += "		</folder>\n";
			} else {
				if (treeSource[1].elementAt(i).equals("IT")){
					outString += "				   < font face=\""+treeItemFontType+"\" style=\""+treeItemFontStyle+"\" size="+treeItemFontSize+" color=\""+treeItemFontColor+"\">\n";
					outString += "				   < item title=\"";
					outString += (String) treeSource[0].elementAt(i);
					outString += "\" expand image=\""+treeItemImage+"\" imageTwo=\""+treeItemImageOver+"\" info=\"";
					outString += (String) treeSource[0].elementAt(i);
					outString += "\" link=\"";
					outString += links.elementAt(i);
					outString += "\" target=\""+treeTargetLink+"\">\n";
					outString += "					</font>\n";
				}
			}
		}
	}
	outString += "	</folder>\n";
	outString += "</font>\n";
	outString += "</treecontrol>";
	return outString;
}

/* Method getParents
@@parms = String element
@@return = Vector
gets the tree node parents of a specified element
*/
public Vector getParents(String element){
	int position = -1;
	position = treeSource[0].indexOf(element);
	Vector arrayParents= new java.util.Vector();
	int numberOfElements = 0;
	String elementSel = "";
	boolean folderOpen = false;
	try {
		for (int i=0 ; i<position ; i++){
			if (treeSource[1].elementAt(i).equals("FO")) {
					elementSel = (String)treeSource[0].elementAt(i);
					arrayParents.addElement(elementSel);
				}
			if (treeSource[1].elementAt(i).equals("FC")){
				arrayParents.remove(arrayParents.lastElement());
			}
		}
	} catch (Exception e) {
		System.out.println("Error : " + e);
	}
	return arrayParents;
}




  
  









	


	

}