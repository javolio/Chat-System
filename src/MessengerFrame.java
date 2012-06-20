import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class MessengerFrame extends JFrame implements ActionListener,Runnable {
	
	private JPanel contentPane;
	private JTextArea textArea;
	private JTextField textField;
	private GraphicalSender sender;
	private GraphicalReceiver receiver;
	private JScrollPane scrollPane;
	
	public MessengerFrame(GraphicalSender s,GraphicalReceiver r) {
		super("Chat");
		setPreferredSize(new Dimension(500,400));
		setMinimumSize(new Dimension(100,100));
		sender=s;
		receiver=r;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,450,300);
		contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0,0));
		
		textField=new JTextField();
		contentPane.add(textField,BorderLayout.SOUTH);
		textField.setColumns(10);
		
		scrollPane=new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane,BorderLayout.CENTER);
		
		textArea=new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textField.addActionListener(this);
		
		pack();
		setVisible(true);
		new Thread(this).start();
	}
	
	public synchronized void addMessage(String message) {
		textArea.append("\n"+message);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String text=textField.getText();
		while (!sender.send(text));
		addMessage("> "+text);
		textField.setText("");
	}
	
	@Override
	public void run() {
		while (true) {
			if (receiver.hasNext()) {
				addMessage(receiver.read());
			} else {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
			}
		}
	}
}
