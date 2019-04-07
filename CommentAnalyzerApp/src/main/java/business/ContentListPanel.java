package business;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

import byVideoFrontEnd.SearchByPage;
import byVideoFrontEnd.TaskBar;
import byVideoFrontEnd.VideoListPanel;
import byVideoFrontEnd.VideoPanel;
import commentsFrontEnd.CommentPage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class ContentListPanel extends GridPane implements Runnable{
	public void emptyList() {};
	public ContentListPanel(Stage stage) {
		this.stage = stage;
		}

	@SuppressWarnings("unchecked")
	public void setPanel(){
		int rowIndex = 0;
		for(ContentPanel panel: (ArrayList<ContentPanel>)panels) {
			panel.setPanel();
			panel.getStyleClass().add("raisedBorder");
			ContentListPanel self  = this;
			
			panel.setOnMouseClicked(e->{
				panel.setStyle("-fx-background-color: #f2f2f2;");
				panel.getStyleClass().add("etchedBorder");
				self.panel = panel;
				Thread thread = new Thread(self);
				thread.start();
			});
			
			panel.setOnMouseEntered(e->{
				panel.setStyle("-fx-background-color: white;");
			});
			
			panel.setOnMouseExited(e->{
				panel.setStyle("-fx-background-color: #f2f2f2;");
			});
			this.add(panel, 1, rowIndex, 400, 300);
			rowIndex++;
		}

	}

	public void run() {
		/*makeSearchByPage(frame, new TaskBar(stage), panel);
		page.setPage();
		JPanel borderPage = new JPanel();
		page.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
		borderPage.add(page);
		borderPage.setBorder(BorderFactory.createRaisedBevelBorder());
		JPanel finalPage = new JPanel();
		finalPage.add(borderPage);
		JScrollPane pane = new JScrollPane(finalPage);
		frame.getContentPane().removeAll();
		frame.getContentPane().revalidate();
		frame.add(pane);
		frame.repaint();*/
	}
	protected abstract void makeSearchByPage(JFrame frame2, TaskBar taskBar, ContentPanel panel);
	public abstract void addPanel(Content content);
	protected ArrayList panels = new ArrayList();
	protected Stage stage;
	protected ContentPanel panel;
	protected SearchByPage page;
}