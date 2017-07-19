import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * SecretComp.java for assignment 09 - AES key encription/decription + GUI
 * @Author Trung Kien Nguyen - 100284963	
 * @CoAuthor Professor Jeremy Hilliker
 * @course CPSC 1181
 * @version 1.0
 * @date June 28th, 2017
 */
public class SecretComp extends JComponent {
	Secrets factory; 
	JButton A; 
	JButton B; 
	JButton C;
	JButton encript;
	JButton decript; 
	JLabel key;
	JLabel msg;
	JTextField keyField; 
	JTextField msgField; 
	JTextField textArea;

	/**
	 * Constructor: set the constructor for the GUI
	 */
	public SecretComp() {
		factory = SecretsFactory.makeSecrets();
		setLayout(new BorderLayout());

		//set the initial layout
		JPanel south = new JPanel(); 
		textArea = new JTextField();

		textArea.setBorder(new LineBorder(Color.LIGHT_GRAY));
		textArea.setEditable(false);
		south.setLayout(new GridLayout(3,1));
		south.setBorder(new LineBorder(Color.LIGHT_GRAY));

		//1st row
		JPanel row1st = new JPanel(); 
		encript = new JButton();
		key = new JLabel();
		msg = new JLabel();

		row1st.setLayout(new GridLayout(1,3));
		key.setText("<html><p>Key</p></html>");
		msg.setText("<html><p>Msg</p></hmtl>"); 
		encript.setText("Encript");
		
		encript.addActionListener(e -> {
			textArea.setText(factory.encrypt(keyField.getText(), msgField.getText()));
		});

		row1st.add(key);
		row1st.add(msg); 
		row1st.add(encript);
		south.add(row1st);

		//2nd row
		JLabel row2nd = new JLabel(); 
		keyField = new JTextField(); 
		msgField = new JTextField(); 
		decript = new JButton(); 

		row2nd.setLayout(new GridLayout(1,3));
		decript.setText("Decript");

		keyField.addActionListener(e -> {
			if (factory.getState() == Secrets.State.LOCKED){
				factory.unlock(keyField.getText());
			}
			textArea.setText(factory.getMessage());
		});
		decript.addActionListener(e -> {
			textArea.setText(factory.decrypt(keyField.getText(), msgField.getText()));
		});

		row2nd.add(keyField); 
		row2nd.add(msgField); 
		row2nd.add(decript);
		south.add(row2nd);

		//3rd row
		JLabel row3rd = new JLabel(); 
		A = new JButton(); 
		B = new JButton(); 
		C = new JButton(); 

		row3rd.setLayout(new GridLayout(1,3));
		A.setText("A"); 
		B.setText("B");
		C.setText("C");

		A.addActionListener(e -> {
			factory.unlock(A.getText());
			textArea.setText(factory.getMessage());
		});
		B.addActionListener(e -> {
			factory.unlock(B.getText());
			textArea.setText(factory.getMessage());
		});
		C.addActionListener(e -> {
			factory.unlock(C.getText());
			textArea.setText(factory.getMessage());
		});

		row3rd.add(A);
		row3rd.add(B);
		row3rd.add(C);
		south.add(row3rd);

		//add to Content Pane
		add(textArea, BorderLayout.CENTER);
		add(south,BorderLayout.SOUTH);
	}
}

/*
 * STEP 09 * Message from assignment.
            key: The.Secret.Key
    cipher-text: cgmMbvbyDONppeM2paS+7A==
     plain-text: You did it!

 * STEP 10 * Message sent to classmate.
            key: Ken.ClassMate
    cipher-text: 2nPRUAPF5u3h5Pe/wsxIJQ==
     plain-text: You did it?

 * STEP 11 * Message received from classmate.
            key:
    cipher-text:
     plain-text:

 * STEP 12 * Message for marker.
            key: Ken.Jeremy
    cipher-text: uMjFpftzDNxAZQzDntv9nLqMew+7MJqhxDExNGX/tuo=
 */
