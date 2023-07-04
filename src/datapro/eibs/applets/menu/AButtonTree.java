package datapro.eibs.applets.menu;

import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.sql.*;

public class AButtonTree extends Applet
	implements ActionListener, MouseMotionListener, MouseListener, ComponentListener {

	private int maxSize;
	protected Button buttonList[];
	protected Image downImage;
	protected Image upImage;
	protected int numberOfButtons;
	protected int selectedButton;
	protected int startIcon;
	public Image imageArray[][];
	public String stringArray[][];
	public String imageFileArray[][];
	public URL urlArray[][];
	public String targetArray[][];
	public int imageCount[];
	protected int imageWidthPercent;
	protected int buttonHeight;
	public int deltaBetweenIcons;
	public Point mousePoint;
	protected int firstImageIndex;
	protected int pressedImage;
	public boolean showDownButton;
	protected boolean smoothScroll;
	protected int scrollIndex;
	protected int sleepTime;
	private final boolean demoMode = false;
	private Vector listeners;
	protected int changeCursor;
	protected String appletFrameName;
	protected int garbageCollectorInterval;
	protected int garbageCollectorCurrent;
	protected Color buttonNormalColor;
	protected Color buttonHighlightedColor;
	protected Color labelColor;
	protected Color backgroundColor;
	protected boolean beanUsage;

	private String imageFolder = "";
	public AButtonTree() {
		maxSize = 100;
		downImage = null;
		upImage = null;
		numberOfButtons = 0;
		selectedButton = -1;
		startIcon = 0;
		imageWidthPercent = 40;
		buttonHeight = 20;
		deltaBetweenIcons = 20;
		mousePoint = null;
		firstImageIndex = 0;
		pressedImage = -1;
		showDownButton = false;
		smoothScroll = true;
		scrollIndex = 0;
		sleepTime = 0;
		listeners = new Vector();
		changeCursor = 0;
		appletFrameName = null;
		garbageCollectorInterval = 10;
		garbageCollectorCurrent = 0;
		beanUsage = true;
		checkLicense();
		buttonList = new Button[maxSize];
		imageArray = new Image[maxSize][maxSize];
		stringArray = new String[maxSize][maxSize];
		imageFileArray = new String[maxSize][maxSize];
		urlArray = new URL[maxSize][maxSize];
		targetArray = new String[maxSize][maxSize];
		imageCount = new int[maxSize];
		setLayout(null);
		addMouseMotionListener(this);
		addMouseListener(this);
		addComponentListener(this);
		// You can modified this
		buttonNormalColor = Color.decode("#D1D1D1"); 
		buttonHighlightedColor = Color.gray; 
		labelColor = Color.decode("#0B23B5");
		backgroundColor = Color.decode("#D1D1D1");
	}
	public void actionPerformed(ActionEvent actionevent) {
		int j = getNumberOfButtons();
		for(int i = 0; i < j; i++) {
			if(getButton(i) != actionevent.getSource())
				continue;
			pressButton(i);
			break;
		}

	}
	public synchronized void addAButtonTreeListener(AButtonTreeListener abuttontreelistener) {
		listeners.addElement(abuttontreelistener);
	}
	public boolean addButton(String s) {
		int i = getNumberOfButtons();
		if(i < maxSize) {
			Button button = new Button(s);
			button.setName(s);
			button.setBounds(2, 2 + i * getButtonHeight(), getWidth() - 4, getButtonHeight());
			buttonList[i] = button;
			imageCount[i] = 0;
			incrementNumberOfButtons();
			add(button);
			button.addActionListener(this);
			setSelectedButton(i);
			return true;
		} else {
			return false;
		}
	}
	public void addImageToButton(int i, String s, String s1) {
		addImageToButton(i, s, s1, null);
	}
	public void addImageToButton(int i, String s, String s1, String s2) {
		addImageToButton(i, s, s1, null, null);
	}
	public void addImageToButton(int i, String s, String s1, String s2, String s3) {
		if(downImage == null)
			downImage = loadImage(imageFolder + "buttdown.gif");
		if(upImage == null)
			upImage = loadImage(imageFolder + "buttup.gif");
		if(i < getNumberOfButtons() && imageCount[i] < maxSize) {
			Image image = loadImage(s);
			if(image == null) {
				System.err.println("Couldn't load image " + s);
				return;
			}
			imageArray[i][imageCount[i]] = image;
			stringArray[i][imageCount[i]] = s1;
			imageFileArray[i][imageCount[i]] = s;
			try {
				if(s2 != null)
					urlArray[i][imageCount[i]] = new URL(s2);
				else
					urlArray[i][imageCount[i]] = null;
			}
			catch(MalformedURLException _ex) {
				try {
					urlArray[i][imageCount[i]] = new URL(getCodeBase(), s2);
				}
				catch(MalformedURLException _ex2) {
					urlArray[i][imageCount[i]] = null;
					System.err.println("Cannot construct url from source string: " + s2);
				}
			}
			targetArray[i][imageCount[i]] = s3;
			imageCount[i]++;
			repaint();
		}
	}
	private void checkLicense() {
//	    AButtonTreeLicense abuttontreelicense = null;
		boolean flag = true;
/*
	    try {
			Class class1 = Class.forName("AButtonTreeLicense");
			abuttontreelicense = (AButtonTreeLicense)class1.newInstance();
		}
		catch(Throwable throwable) {
			flag = false;
			System.err.println("Exception " + throwable + "when trying to read license");
		}
		if(flag) {
			String s = abuttontreelicense.getLicenseString();
			if(s.compareTo(new String("ALXABUTTONTREE19990XCF4DCT")) != 0)
				flag = false;
		}
		if(!flag)
			maxSize = 2;

*/
}
	public void componentHidden(ComponentEvent componentevent) {
	}
	public void componentMoved(ComponentEvent componentevent) {
	}
	public void componentResized(ComponentEvent componentevent) {
	}
	public void componentShown(ComponentEvent componentevent) {
	}
	public void decrementNumberOfButtons() {
		numberOfButtons--;
	}
	public void deleteButton(int i) {
		if(i >= 0 && i < getNumberOfButtons()) {
			remove(buttonList[i]);
			for(int j = i + 1; j < getNumberOfButtons(); j++) {
				for(int k = 0; k < imageCount[j]; k++) {
					imageArray[j - 1][k] = imageArray[j][k];
					stringArray[j - 1][k] = stringArray[j][k];
					urlArray[j - 1][k] = urlArray[j][k];
					targetArray[j - 1][k] = targetArray[j][k];
				}

				imageCount[j - 1] = imageCount[j];
				buttonList[j - 1] = buttonList[j];
			}

			imageCount[getNumberOfButtons() - 1] = 0;
			buttonList[getNumberOfButtons() - 1] = null;
			setNumberOfButtons(getNumberOfButtons() - 1);
		}
	}
	public void deleteImageButton(int i, int j) {
		if(i >= 0 && i < getNumberOfButtons() && j >= 0 && j < imageCount[i]) {
			for(int k = j + 1; k < imageCount[i]; k++) {
				imageArray[i][k - 1] = imageArray[i][k];
				stringArray[i][k - 1] = stringArray[i][k];
				urlArray[i][k - 1] = urlArray[i][k];
				targetArray[i][k - 1] = targetArray[i][k];
			}

			imageArray[i][imageCount[i] - 1] = null;
			stringArray[i][imageCount[i] - 1] = null;
			urlArray[i][imageCount[i] - 1] = null;
			targetArray[i][imageCount[i] - 1] = null;
			imageCount[i]--;
			repaint();
		}
	}
	void drawStandardButton(int i, Rectangle rectangle, Graphics g) {
		g.setColor(getBackground());
		g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		if(getImageIndex(mousePoint) == -i - 2)
			g.setColor(getButtonHighlightedColor());
		else
			g.setColor(getButtonNormalColor());
		g.fillRect(rectangle.x + 3, rectangle.y + 3, rectangle.width - 6, rectangle.height - 6);
		g.setColor(labelColor);
		String s = getButton(i).getLabel();
		FontMetrics fontmetrics = g.getFontMetrics();
		int j = fontmetrics.stringWidth(s);
		int k = fontmetrics.getHeight();
		g.drawString(s, (rectangle.x + rectangle.width / 2) - j / 2, (rectangle.y + rectangle.height) - 4);
		g.setColor(Color.black);
		g.drawRect(rectangle.x + 2, rectangle.y + 2, rectangle.width - 4, rectangle.height - 4);
	}
	public Button getButton(int i) {
		if(i < getNumberOfButtons() && i >= 0)
			return buttonList[i];
		else
			return null;
	}
	public int getButtonHeight() {
		return buttonHeight;
	}
	public Color getButtonHighlightedColor() {
		if(buttonHighlightedColor != null)
			return buttonHighlightedColor;
		else
			return Color.lightGray;
	}
	public Color getButtonNormalColor() {
		if(buttonNormalColor != null)
			return buttonNormalColor;
		else
			return getBackground();
	}
	public int getChangeCursor() {
		return changeCursor;
	}
	public int getDeltaBetweenIcons() {
		return deltaBetweenIcons;
	}
	public Rectangle getDownButtonRect() {
		int i = getHeight() - (getNumberOfButtons() - getSelectedButton()) * getButtonHeight();
		Rectangle rectangle = new Rectangle(getWidth() - 18, i, 12, 12);
		return rectangle;
	}
	public int getFirstImageIndex() {
		return firstImageIndex;
	}
	public int getHeight() {
		Dimension dimension = getSize();
		return dimension.height;
	}
	public Rectangle getIconRectangle(int i) {
		int j = (getSelectedButton() + 1) * getButtonHeight() + (i - firstImageIndex) * (getIconWidth() + deltaBetweenIcons) + deltaBetweenIcons / 2;
		int k = (getWidth() - getIconWidth()) / 2;
		Rectangle rectangle = new Rectangle(k, j, getIconWidth(), getIconWidth());
		return rectangle;
	}
	public int getIconWidth() {
		return (getImageWidthPercent() * getWidth()) / 100;
	}
	public int getImageIndex(Point point) {
		boolean flag = false;
		if(point == null)
			return -1;
		if(numberOfButtons == 0)
			return -1;
		for(int i = 0; i < numberOfButtons; i++) {
			Rectangle rectangle = getButton(i).getBounds();
			if(rectangle.contains(point))
				return -i - 2;
		}

		for(int j = firstImageIndex; j < firstImageIndex + imageCount[getSelectedButton()]; j++) {
			Rectangle rectangle1 = getIconRectangle(j);
			if(rectangle1.contains(point))
				return j;
		}

		return -1;
	}
	public int getImageWidthPercent() {
		return imageWidthPercent;
	}
	public int getIntFromString(String s) {
		int i = 0;
		try {
			i = Integer.parseInt(s);
		}
		catch(NumberFormatException _ex) {
			i = 0;
		}
		return i;
	}
	public Dimension getMinimumSize() {
		return new Dimension(20, 50);
	}
	public int getNumberOfButtons() {
		return numberOfButtons;
	}
	public Dimension getPreferredSize() {
		return new Dimension(50, 100);
	}
	public int getSelectedButton() {
		return selectedButton;
	}
	public int getSleepTime() {
		return sleepTime;
	}
	public Rectangle getUpButtonRect() {
		int i = (getSelectedButton() + 1) * getButtonHeight() + 10;
		Rectangle rectangle = new Rectangle(getWidth() - 18, i, 12, 12);
		return rectangle;
	}
	public int getWidth() {
		Dimension dimension = getSize();
		return dimension.width;
	}
	public void incrementNumberOfButtons() {
		numberOfButtons++;
	}
	public void init() {
		beanUsage = false;
		loadFromAppletParams();
	}
	public boolean isSmoothScroll() {
		return smoothScroll;
	}
	private void loadDemo() {
		Dimension dimension = new Dimension(80, 300);
		setSize(dimension);
		setBackground(Color.gray);
		addButton(new String("Personal"));
		addImageToButton(0, new String("bmp0.gif"), new String("Main Page"), new String("index2.html"));
		addImageToButton(0, new String("bmp1.gif"), new String("Resume"), new String("cv.html"));
		addImageToButton(0, new String("bmp2.gif"), new String("JavaBeans"), new String("beans.html"));
		addImageToButton(0, new String("bmp3.gif"), new String("WebMessenger"), new String("wm.html"));
		addImageToButton(0, new String("bmp4.gif"), new String("Contact me"), new String("contact.html"));
		addButton(new String("Friends"));
		addImageToButton(1, new String("bmp0.gif"), new String("Bogdan Marin"), new String("bogdanmarin.html"));
		addButton(new String("Links"));
		addImageToButton(2, new String("bmp0.gif"), new String("JARS"), new String("http://www.jars.com"));
		addImageToButton(2, new String("bmp1.gif"), new String("Flashline"), new String("http://www.flashline.com"));
		addImageToButton(2, new String("bmp2.gif"), new String("SUN's Java"), new String("http://www.sun.com/java"));
		setSelectedButton(0);
		appletFrameName = new String("page");
	}
	protected void loadFromAppletParams() {
		boolean flag = false;
		boolean flag1 = false;
		int k = 0;
		Dimension dimension = new Dimension(180, 600);
		setSize(dimension);
		String s = getParameter("backgroundColorRed");
		String s1 = getParameter("backgroundColorGreen");
		String s3 = getParameter("backgroundColorBlue");
		imageFolder = getParameter("imagePath");
		if(s != null && s1 != null && s3 != null)
			setBackground(new Color(getIntFromString(s), getIntFromString(s1), getIntFromString(s3)));
		else
			setBackground(backgroundColor);
		s = getParameter("buttonNumber");
		if(s != null) {
			int i = getIntFromString(s);
			for(int l = 0; l < i; l++) {
				s = getParameter(new String("button") + l);
				addButton(s);
				boolean flag2 = false;
				s = getParameter("imageNumberOfButton" + l);
				if(s != null) {
					int j = getIntFromString(s);
					for(int i1 = 0; i1 < j; i1++) {
						s = imageFolder + getParameter("button" + l + "imageName" + i1);
						String s2 = getParameter("button" + l + "imageCaption" + i1);
						String s4 = getParameter("button" + l + "imageURL" + i1);
						String s5 = getParameter("button" + l + "imageTarget" + i1);
						addImageToButton(l, s, s2, s4, s5);
					}

				}
			}

			s = getParameter("selectedButton");
			if(s != null)
				k = getIntFromString(s);
			if(k < 0 || k >= i)
				k = 0;
			setSelectedButton(k);
		}
		s = getParameter("appletFrameName");
		if(s != null)
			appletFrameName = new String(s);
	}
	public Image loadImage(String s) {
		try {
			Image image = null;
			if(beanUsage) {
				image = Toolkit.getDefaultToolkit().getImage(s);
			} else {
				showStatus(new String(" Loading image ") + s);
				URL url = getDocumentBase();
				image = getImage(url, s);
			}
			if(image != null) {
				MediaTracker mediatracker = new MediaTracker(this);
				mediatracker.addImage(image, 0);
				try {
					mediatracker.waitForID(0);
				}
				catch(InterruptedException _ex) { }
			}
			return image;
		}
		catch(Exception exception) {
			System.err.println("Exception " + exception + " occurred when trying to load image " + s);
		}
		return null;
	}
	public void modifyButton(int i, String s) {
		if(i >= 0 && i < getNumberOfButtons())
			buttonList[i].setLabel(s);
	}
	public void modifyImageOfButton(int i, int j, String s, String s1, String s2) {
		if(i >= 0 && i < getNumberOfButtons() && j >= 0 && j < imageCount[i]) {
			Image image = loadImage(s);
			if(image == null) {
				System.err.println("Couldn't load image " + s);
				return;
			}
			imageArray[i][j] = image;
			stringArray[i][j] = s1;
			imageFileArray[i][j] = s;
			try {
				if(s2 != null)
					urlArray[i][j] = new URL(s1);
				else
					urlArray[i][j] = null;
			}
			catch(MalformedURLException _ex) {
				urlArray[i][j] = null;
			}
			repaint();
		}
	}
	public void modifyImageOfButton(int i, int j, String s, String s1, String s2, String s3) {
		if(i >= 0 && i < getNumberOfButtons() && j >= 0 && j < imageCount[i]) {
			Image image = loadImage(s);
			if(image == null) {
				System.err.println("Couldn't load image " + s);
				return;
			}
			imageArray[i][j] = image;
			stringArray[i][j] = s1;
			imageFileArray[i][j] = s;
			try {
				if(s2 != null)
					urlArray[i][j] = new URL(s1);
				else
					urlArray[i][j] = null;
			}
			catch(MalformedURLException _ex) {
				urlArray[i][j] = null;
			}
			targetArray[i][j] = s1;
			repaint();
		}
	}
	public void mouseClicked(MouseEvent mouseevent) {
	}
	public void mouseDragged(MouseEvent mouseevent) {
		if(pressedImage >= 0) {
			pressedImage = -1;
			repaint();
		}
	}
	public void mouseEntered(MouseEvent mouseevent) {
	}
	public void mouseExited(MouseEvent mouseevent) {
		if(getChangeCursor() != 0)
			setCursor(new Cursor(12));
		mousePoint = mouseevent.getPoint();
		repaint();
	}
	public void mouseMoved(MouseEvent mouseevent) {
		Point point = mousePoint;
		mousePoint = mouseevent.getPoint();
		if(getChangeCursor() == 1)
			if(getImageIndex(mousePoint) >= 0)
				setCursor(new Cursor(12));
			else
				setCursor(new Cursor(0));
		if(getImageIndex(point) != getImageIndex(mousePoint))
			repaint();
	}
	public void mousePressed(MouseEvent mouseevent) {
		Point point = mouseevent.getPoint();
		Rectangle rectangle = getUpButtonRect();
		if(rectangle.contains(point)) {
			onUpButtonPressed();
			return;
		}
		if(showDownButton) {
			Rectangle rectangle1 = getDownButtonRect();
			if(rectangle1.contains(point)) {
				onDownButtonPressed();
				return;
			}
		}
		int i = getImageIndex(point);
		if(i >= 0) {
			onImageButtonPressed(i);
			return;
		}
		int k = getNumberOfButtons();
		for(int j = 0; j < k; j++) {
			Rectangle rectangle2 = getButton(j).getBounds();
			if(rectangle2.contains(point)) {
				setSelectedButton(j);
				return;
			}
		}

	}
	public void mouseReleased(MouseEvent mouseevent) {
		if(pressedImage >= 0) {
			pressedImage = -1;
			repaint();
		}
	}
	public void onDownButtonPressed() {
		byte byte0 = 5;
		if(isSmoothScroll()) {
			int j = (getIconWidth() + getDeltaBetweenIcons()) / byte0;
			try {
				for(int i = 0; i < byte0; i++) {
					scrollIndex += j;
					if(sleepTime > 0) {
						Thread.currentThread();
						Thread.sleep(sleepTime);
					}
					paint(getGraphics());
				}

			}
			catch(InterruptedException _ex) {
				return;
			}
		}
		firstImageIndex++;
		scrollIndex = 0;
		repaint();
	}
	public void onImageButtonPressed(int i) {
		AButtonTreeEvent abuttontreeevent = new AButtonTreeEvent(this, stringArray[getSelectedButton()][i], getSelectedButton());
		AButtonTreeListener abuttontreelistener;
		for(Enumeration enumeration = listeners.elements(); enumeration.hasMoreElements(); abuttontreelistener.imageButtonPressed(abuttontreeevent))
			abuttontreelistener = (AButtonTreeListener)enumeration.nextElement();

		URL url = null;
		url = urlArray[getSelectedButton()][i];
		String target = null;
		target = targetArray[getSelectedButton()][i];
		if(url != null) {
			System.err.println("appletFrameName  = " + appletFrameName);
			if(target != null)
				getAppletContext().showDocument(url, target);
			else
				getAppletContext().showDocument(url);
		}
		pressedImage = i;
		repaint();
	}
	public void onUpButtonPressed() {
		if(firstImageIndex > 0) {
			byte byte0 = 5;
			if(isSmoothScroll()) {
				int j = (getIconWidth() + getDeltaBetweenIcons()) / byte0;
				try {
					for(int i = 0; i < byte0; i++) {
						scrollIndex -= j;
						if(sleepTime > 0) {
							Thread.currentThread();
							Thread.sleep(sleepTime);
						}
						paint(getGraphics());
					}

				}
				catch(InterruptedException _ex) {
					return;
				}
			}
			firstImageIndex--;
			scrollIndex = 0;
			repaint();
		}
	}
	public void paint(Graphics g) {
		recomputeButtonPositions();
		int i = getSize().width;
		int j = getSize().height;
		Color color = getBackground();
		Color color1 = getForeground();
		if(getNumberOfButtons() == 0) {
			g.setColor(color);
			g.fillRect(0, 0, i, j);
			g.setColor(color1);
			return;
		}
		int k = (((100 - getImageWidthPercent()) / 2) * getWidth()) / 100;
		int l = getIconWidth();
		int i1 = getWidth() - 4;
		int j1 = j - getNumberOfButtons() * getButtonHeight();
		Image image = createImage(i1, j1);
		Graphics g1 = image.getGraphics();
		g1.setColor(color);
		g1.fillRect(0, 0, i1, j1);
		g1.setColor(color1);
		int k1 = 0;
		int l1 = 0;
		k1 = (j1 - deltaBetweenIcons) / (getIconWidth() + deltaBetweenIcons);
		l1 = k1 + 1;
		if(l1 + firstImageIndex > imageCount[getSelectedButton()])
			l1 = imageCount[getSelectedButton()] - firstImageIndex;
		for(int i2 = firstImageIndex; i2 < firstImageIndex + l1; i2++) {
			Rectangle rectangle = getIconRectangle(i2);
			rectangle.translate(0, -((getSelectedButton() + 1) * getButtonHeight()) - scrollIndex);
			g1.drawImage(imageArray[getSelectedButton()][i2], rectangle.x, rectangle.y, rectangle.width, rectangle.height, this);
			if(getImageIndex(mousePoint) == i2) {
				if(pressedImage == -1)
					g1.setColor(Color.lightGray); // lightGray
				else
					g1.setColor(Color.black); // black
				g1.drawLine(rectangle.x, rectangle.y, rectangle.x + rectangle.width, rectangle.y);
				g1.drawLine(rectangle.x, rectangle.y, rectangle.x, rectangle.y + rectangle.height);
				if(pressedImage == -1)
					g1.setColor(Color.black); // black
				else
					g1.setColor(Color.lightGray); // lightGray
				g1.drawLine(rectangle.x + rectangle.width, rectangle.y, rectangle.x + rectangle.width, rectangle.y + rectangle.height);
				g1.drawLine(rectangle.x, rectangle.y + rectangle.height, rectangle.x + rectangle.width, rectangle.y + rectangle.height);
				if(!beanUsage)
					showStatus(stringArray[getSelectedButton()][i2]);
			}
			g1.setColor(labelColor); // Color.blue
			String s = stringArray[getSelectedButton()][i2];
			FontMetrics fontmetrics = g1.getFontMetrics();
			int k2 = fontmetrics.stringWidth(s);
			int i3 = fontmetrics.getHeight();
			g1.drawString(s, (rectangle.x + rectangle.width / 2) - k2 / 2, rectangle.y + rectangle.height + 12);
		}

		if(l1 > k1) {
			Rectangle rectangle1 = getDownButtonRect();
			rectangle1.translate(-2, -((getSelectedButton() + 1) * getButtonHeight()));
			g1.drawImage(downImage, rectangle1.x, rectangle1.y, rectangle1.width, rectangle1.height, this);
			showDownButton = true;
		} else {
			showDownButton = false;
		}
		if(firstImageIndex > 0) {
			Rectangle rectangle2 = getUpButtonRect();
			rectangle2.translate(-2, -((getSelectedButton() + 1) * getButtonHeight()));
			g1.drawImage(upImage, rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height, this);
		}
		g.drawImage(image, 2, getButtonHeight() * (getSelectedButton() + 1), this);
		g.setColor(Color.gray);
		g.drawLine(0, 0, i, 0);
		g.drawLine(0, 0, 0, j);
		g.setColor(Color.black.brighter());
		g.drawLine(1, 1, i - 1, 1);
		g.drawLine(1, 1, 1, j - 1);
		g.setColor(Color.lightGray.darker());
		g.drawLine(2, j - 1, i - 1, j - 1);
		g.drawLine(i - 1, j - 1, i - 1, 1);
		g.setColor(Color.lightGray);
		g.drawLine(2, j - 2, i - 2, j - 2);
		g.drawLine(i - 2, j - 2, i - 2, 2);
		int j2 = getNumberOfButtons();
		for(int l2 = 0; l2 < j2; l2++) {
			Rectangle rectangle3 = getButton(l2).getBounds();
			drawStandardButton(l2, rectangle3, g);
		}

		if(++garbageCollectorCurrent > garbageCollectorInterval) {
			garbageCollectorCurrent = 0;
			System.gc();
		}
	}
	public void pressButton(int i) {
		setSelectedButton(i);
	}
/**
 * Here we read the menu settings for this application.
 */
public void readMenuSettings() {

}
	public void recomputeButtonPositions() {
		int j = getNumberOfButtons();
		int k = getSelectedButton();
		for(int i = 0; i < j; i++) {
			getButton(i).setVisible(false);
			if(i <= k)
				getButton(i).setBounds(2, 2 + getButtonHeight() * i, getWidth() - 4, getButtonHeight());
			else
				getButton(i).setBounds(2, (getHeight() - (j - k) * getButtonHeight()) + getButtonHeight() * (i - k), getWidth() - 4, getButtonHeight());
		}

	}
	public synchronized void removeAButtonTreeListener(AButtonTreeListener abuttontreelistener) {
		listeners.removeElement(abuttontreelistener);
	}
	public void setButtonHeight(int i) {
		if(i > 0)
			buttonHeight = i;
	}
	public void setButtonHighlightedColor(Color color) {
		buttonHighlightedColor = color;
	}
	public void setButtonNormalColor(Color color) {
		buttonNormalColor = color;
	}
	public void setChangeCursor(int i) {
		if(i >= 0)
			changeCursor = i;
	}
	public void setDeltaBetweenIcons(int i) {
		if(i > 0) {
			deltaBetweenIcons = i;
			repaint();
		}
	}
	public void setFirstImageIndex(int i) {
		if(i >= 0 && i < maxSize - 1)
			firstImageIndex = i;
	}
	public void setImageWidthPercent(int i) {
		if(i > 0 && i <= 100)
			imageWidthPercent = i;
	}
	public void setNumberOfButtons(int i) {
		if(i >= 0 && i < maxSize) {
			numberOfButtons = i;
			repaint();
		}
	}
	public void setSelectedButton(int i) {
		int j = selectedButton;
		if(i >= 0 && i < getNumberOfButtons()) {
			selectedButton = i;
			if(j != selectedButton)
				firstImageIndex = 0;
			repaint();
		}
	}
	public void setSleepTime(int i) {
		if(i >= 0)
			sleepTime = i;
	}
	public void setSmoothScroll(boolean flag) {
		smoothScroll = flag;
	}
	public void update(Graphics g) {
		paint(g);
	}
}