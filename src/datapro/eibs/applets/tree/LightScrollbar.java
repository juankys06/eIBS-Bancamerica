package datapro.eibs.applets.tree;

// Processed by NMI's Java Code Viewer 4.8.0 © 1997-1999 B. Lemaire
// Website: http://njcv.htmlplanet.com	E-mail: info@njcv.htmlplanet.com
// Copy registered to Evaluation Copy
// Source File Name:   LightScrollbar.java

import java.awt.*;

class LightScrollbar extends Canvas
	implements Runnable {

	static final int NOTHING = 0;
	static final int SCROLLING_UP = 1;
	static final int SCROLLING_DOWN = 2;
	static final int TRACKING = 4;
	static final int VERTICAL = 1;
	static final int HORIZONTAL = 0;
	static final int ARROW_UP = 0;
	static final int ARROW_LEFT = 1;
	static final int ARROW_RIGHT = 2;
	static final int ARROW_DOWN = 3;
	static final long DELAY = 100L;
	Thread thread;
	int thread_state;
	int orientation;
	int line_increment;
	int maximum;
	int minimum;
	int page_increment;
	public int value;
	int visible;
	static final int arrow_width_height = 11;
	boolean top_up;
	boolean bottom_up;
	boolean bar_up;
	int bar_xy;
	int bar_height;
	int lastx;
	int lasty;
	int state;
	int track_xy;
	int old_bar_xy;
	Image scratch;
	Graphics g_scratch;
	Rectangle topleft_arrow;
	Rectangle botright_arrow;
	Rectangle bar_rect;
	int scroll_xy;
	int scroll_width;
	int shift_left;

	public LightScrollbar(int i) {
		orientation = 1;
		line_increment = 1;
		page_increment = 10;
		visible = 1;
		top_up = true;
		bottom_up = true;
		bar_up = true;
		state = 1;
		topleft_arrow = new Rectangle();
		botright_arrow = new Rectangle();
		bar_rect = new Rectangle();
		orientation = i;
		setValues(value, visible, minimum, maximum);
		setForeground(Color.lightGray);
		setBackground(Color.white);
	}
	private final void drawArrowHead(Rectangle rectangle, Graphics g, Color color, int i, boolean flag) {
		int j = rectangle.x + rectangle.width;
		int k = rectangle.y + rectangle.height;
		int l = rectangle.x;
		int i1 = rectangle.y;
		fill3DEmbossedRect(rectangle, flag, g, color);
		g.setColor(Color.black);
		switch(i) {
		case 3: // '\003'
			g.drawLine(l + 4, i1 + 4, l + 6, i1 + 4);
			g.drawLine(l + 5, i1 + 5, l + 5, i1 + 5);
			return;

		case 0: // '\0'
			g.drawLine(l + 4, k - 5, l + 6, k - 5);
			g.drawLine(l + 5, k - 6, l + 5, k - 6);
			return;

		case 1: // '\001'
			g.drawLine(j - 5, i1 + 4, j - 5, i1 + 6);
			g.drawLine(j - 6, i1 + 5, j - 6, i1 + 5);
			return;

		case 2: // '\002'
			g.drawLine(l + 4, i1 + 4, l + 4, i1 + 6);
			g.drawLine(l + 5, i1 + 5, l + 5, i1 + 5);
			return;

		}
	}
	private final void fill3DEmbossedRect(Rectangle rectangle, boolean flag, Graphics g, Color color) {
		if(flag) {
			int i = (rectangle.x + rectangle.width) - 1;
			int j = (rectangle.y + rectangle.height) - 1;
			g.setColor(color);
			g.fillRect(rectangle.x, rectangle.y, rectangle.width - 1, rectangle.height);
			g.setColor(Color.black);
			g.drawLine(rectangle.x, j, i, j);
			g.drawLine(rectangle.x, j, i, j);
			g.drawLine(i, rectangle.y, i, j);
			g.setColor(color.brighter());
			g.drawLine(rectangle.x + 1, rectangle.y + 1, rectangle.x + 1, j - 2);
			g.drawLine(rectangle.x + 1, rectangle.y + 1, i - 2, rectangle.y + 1);
			g.setColor(color.darker());
			g.drawLine(i - 1, j - 1, i - 1, rectangle.y + 1);
			g.drawLine(i - 1, j - 1, rectangle.x + 1, j - 1);
			return;
		} else {
			g.setColor(color.darker());
			g.fillRect(rectangle.x, rectangle.y, rectangle.width - 1, rectangle.height);
			g.setColor(color);
			g.fillRect(rectangle.x + 1, rectangle.y + 1, rectangle.width - 2, rectangle.height - 2);
			return;
		}
	}
	public final int getVisible() {
		return visible;
	}
	public boolean handleEvent(Event event) {
		int i = orientation != 0 ? event.y : event.x;
		size();
		size();
		switch(event.id) {
		case 501: // Event.MOUSE_DOWN
			if(topleft_arrow.inside(event.x, event.y)) {
				value -= line_increment;
				if(value < minimum)
					value = minimum;
				top_up = false;
				repaint();
				postEvent(new Event(this, 601, new Integer(value)));
				thread_state = 1;
				start();
				break;
			}
			if(botright_arrow.inside(event.x, event.y)) {
				value += line_increment;
				if(value > maximum)
					value = maximum;
				bottom_up = false;
				repaint();
				postEvent(new Event(this, 602, new Integer(value)));
				thread_state = 2;
				start();
				break;
			}
			if(i < bar_xy && i > scroll_xy) {
				state = 1;
				value -= page_increment;
				if(value < minimum)
					value = minimum;
				repaint();
				postEvent(new Event(this, 603, new Integer(value)));
				thread_state = 1;
				start();
				break;
			}
			if(i > bar_xy + bar_height && i < scroll_width + scroll_xy) {
				state = 2;
				value += page_increment;
				if(value > maximum)
					value = maximum;
				repaint();
				postEvent(new Event(this, 604, new Integer(value)));
				thread_state = 2;
				start();
			} else {
				track_xy = i;
				old_bar_xy = bar_xy;
				bar_up = false;
				state = 4;
				repaint();
			}
			break;

		case 502: // Event.MOUSE_UP
			bottom_up = true;
			top_up = true;
			bar_up = true;
			state = 0;
			repaint();
			stop();
			break;

		case 505: // Event.MOUSE_EXIT
			if(topleft_arrow.inside(lastx, lasty) || botright_arrow.inside(lastx, lasty))
				repaint();
			break;

		case 504: // Event.MOUSE_EVENT
			lastx = event.x;
			lasty = event.y;
			if(topleft_arrow.inside(event.x, event.y) || botright_arrow.inside(event.x, event.y))
				repaint();
			break;

		case 506: // Event.MOUSE_DRAG
			if(topleft_arrow.inside(event.x, event.y) != topleft_arrow.inside(lastx, lasty) || botright_arrow.inside(event.x, event.y) != botright_arrow.inside(lastx, lasty))
				repaint();
			if(bar_up)
				break;
			int j = (i - track_xy) + old_bar_xy;
			int k = scroll_width - bar_height;
			if(k == 11)
				return false;
			if(j < scroll_xy)
				j = scroll_xy;
			if(j > k)
				j = k;
			value = ((j - scroll_xy) * (maximum - minimum)) / (k - scroll_xy) + minimum;
			repaint();
			postEvent(new Event(this, 605, new Integer(value)));
			break;

		case 503: // Event.MOUSE_MOVE
		default:
			return super.handleEvent(event);

		}
		lastx = event.x;
		lasty = event.y;
		return false;
	}
	public Dimension minimumSize() {
		if(orientation == 0)
			return new Dimension(50, 5);
		else
			return new Dimension(5, 50);
	}
	public void paint(Graphics g) {
		if(g_scratch == null) {
			scratch = createImage(size().width, size().height);
			g_scratch = scratch.getGraphics();
		}
		paintSingleBuffer(g_scratch);
		g.drawImage(scratch, 0, 0, this);
	}
	private final void paintSingleBuffer(Graphics g) {
		int i = size().width;
		int j = size().height;
		Color color = getForeground();
		g.setColor(color.brighter());
		g.fill3DRect(0, 0, i, j, false);
		int k = orientation != 0 ? j : i;
		int l = (maximum - minimum) + visible;
		int i1 = k - 22 - shift_left;
		scroll_xy = 0;
		scroll_width = i1;
		if(l == 0) {
			bar_xy = 11;
			bar_height = i1;
		} else {
			bar_xy = scroll_xy + ((value - minimum) * i1) / l;
			bar_height = (visible * i1) / l;
		}
		if(bar_height < 4)
			bar_height = 4;
		if(bar_xy > k - 11 - 4) {
			bar_xy = k - 11 - 4;
			if(bar_xy < 11)
				bar_xy = 11;
		}
		switch(orientation) {
		case 1: // '\001'
			topleft_arrow = new Rectangle(0, j - (22 + shift_left), i, 11);
			botright_arrow = new Rectangle(0, j - (11 + shift_left), i, 11);
			bar_rect = new Rectangle(0, bar_xy, 11, bar_height);
			drawArrowHead(topleft_arrow, g, color, 0, top_up);
			drawArrowHead(botright_arrow, g, color, 3, bottom_up);
			fill3DEmbossedRect(bar_rect, bar_up, g, color);
			return;

		case 0: // '\0'
			topleft_arrow = new Rectangle(i - (22 + shift_left), 0, 11, j);
			botright_arrow = new Rectangle(i - (11 + shift_left), 0, 11, j);
			bar_rect = new Rectangle(bar_xy, 0, bar_height, 11);
			drawArrowHead(topleft_arrow, g, color, 1, top_up);
			drawArrowHead(botright_arrow, g, color, 2, bottom_up);
			fill3DEmbossedRect(bar_rect, bar_up, g, color);
			return;

		}
	}
	public Dimension preferredSize() {
		if(orientation == 0)
			return new Dimension(100, 11);
		else
			return new Dimension(11, 100);
	}
	public void reshape(int i, int j, int k, int l) {
		super.reshape(i, j, k, l);
		if(k > 0 && l > 0) {
			scratch = createImage(k, l);
			if(scratch != null)
				g_scratch = scratch.getGraphics();
			repaint();
		}
	}
	public void run() {
		while(thread != null)  {
			try {
				Thread.sleep(100L);
			}
			catch(InterruptedException _ex) { }
			if(thread_state == 1) {
				value -= line_increment;
				if(value < minimum)
					value = minimum;
				top_up = false;
				repaint();
				postEvent(new Event(this, 601, new Integer(value)));
			} else
			if(thread_state == 2) {
				value += line_increment;
				if(value > maximum)
					value = maximum;
				bottom_up = false;
				repaint();
				postEvent(new Event(this, 602, new Integer(value)));
			}
		}

	}
	public final void setLeft(int i) {
		shift_left = i;
		repaint();
	}
	public final void setLineIncrement(int i) {
		if(i < 0)
			i = 0;
		line_increment = i;
	}
	public final void setValue(int i) {
		if(i < minimum)
			i = minimum;
		if(i > maximum)
			i = maximum;
		value = i;
		repaint();
	}
	public final void setValues(int i, int j, int k, int l) {
		if(j < 0)
			j = 0;
		visible = j;
		if(l < k)
			l = k;
		minimum = k;
		maximum = l;
		setValue(i);
		repaint();
	}
	public final void setVisible(boolean flag) {
		show(flag);
	}
	public void start() {
		if(thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
	public void stop() {
		if(thread != null) {
			thread.stop();
			thread = null;
		}
		thread_state = 0;
	}
	public void update(Graphics g) {
		paint(g);
	}
}