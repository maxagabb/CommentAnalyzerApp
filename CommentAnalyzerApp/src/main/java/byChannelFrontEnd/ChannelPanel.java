package byChannelFrontEnd;

import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

import business.Content;
import business.ContentPanel;

public class ChannelPanel extends ContentPanel{

	public ChannelPanel(Channel content) {
		super(content);
	}
	public String getName() {
		return content.getName();
	}
	
	public void setPanel() {
		JLabel label = new JLabel(String.format(html, 400,content.getName()));
		this.add(label);
		this.setBorder(new EtchedBorder(EtchedBorder.RAISED));
	}
	private final String html = "<html><body style='width: %1spx'>%1s";
}
