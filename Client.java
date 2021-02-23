
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	Socket socket = null;
	BufferedReader in = null;
	PrintWriter out = null;
	boolean connected = false;

	/**
	 * Creates new form NewJFrame
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public Client() throws UnknownHostException, IOException {

		initComponents();
	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jTextField2 = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jSeparator1 = new javax.swing.JSeparator();
		jButton3 = new javax.swing.JButton();
		jTextField3 = new javax.swing.JTextField();
		jButton4 = new javax.swing.JButton();
		jLabel4 = new javax.swing.JLabel();
		jButton5 = new javax.swing.JButton();
		jButton6 = new javax.swing.JButton();
		jButton7 = new javax.swing.JButton();
		jLabel5 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Noteboard Client");
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setLocationByPlatform(true);
		setMaximizedBounds(new java.awt.Rectangle(361, 304, 304, 304));
		setMaximumSize(null);
		setResizable(false);
		setSize(new java.awt.Dimension(361, 304));

		jLabel1.setText("IP Address");

		jLabel2.setText("Port Number");

		jTextField1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1ActionPerformed(evt);
			}
		});

		jTextField2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField2ActionPerformed(evt);
			}
		});

		jButton1.setText("Connect");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					jButton1ActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		jButton2.setText("Disconnect");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					jButton2ActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		jButton3.setText("Post");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					jButton3ActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		jTextField3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField3ActionPerformed(evt);
			}
		});

		jButton4.setText("Get");
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					jButton4ActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		jLabel4.setText("Input:");

		jButton5.setText("Pin");
		jButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					jButton5ActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		jButton6.setText("UnPin");
		jButton6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					jButton6ActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		jButton7.setText("Clear");
		jButton7.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					jButton7ActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		jLabel5.setText("Output:");

		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jSeparator1)
						.addGroup(layout
								.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(
										javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel5).addGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addGroup(layout.createSequentialGroup().addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE,
																152, Short.MAX_VALUE)
														.addComponent(jTextField2)).addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE,
																84, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addComponent(
														jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGroup(layout.createSequentialGroup().addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(layout
																.createSequentialGroup().addGap(162, 162, 162)
																.addComponent(jLabel2,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 84,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(jScrollPane1,
																javax.swing.GroupLayout.PREFERRED_SIZE, 242,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jTextField3,
																javax.swing.GroupLayout.PREFERRED_SIZE, 242,
																javax.swing.GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING,
																		false)
																.addComponent(
																		jButton3, javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(jButton2,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(jButton4,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(jButton5,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(jButton6,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(jButton7,
																		javax.swing.GroupLayout.Alignment.TRAILING,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)))))
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(17, 17, 17)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton1))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel2).addComponent(jButton2))
						.addPreferredGap(
								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addGroup(layout.createSequentialGroup().addGap(4, 4, 4).addComponent(jLabel4)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jLabel5))
								.addGroup(
										javax.swing.GroupLayout.Alignment.TRAILING,
										layout.createSequentialGroup().addGap(18, 18, 18).addComponent(jButton5)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jButton6)))
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButton7)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButton3)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButton4))
								.addGroup(layout.createSequentialGroup().addGap(12, 12, 12).addComponent(jScrollPane1,
										javax.swing.GroupLayout.PREFERRED_SIZE, 106,
										javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(24, Short.MAX_VALUE)));

		pack();
	}
	/*
	 * GET button pressed.. action listener
	 */

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
		// TODO add your handling code here:
		System.out.println("Get button pressed");
		if (connected == true) {
			out.println("GET " + jTextField3.getText());
			int serverInput = Integer.parseInt(in.readLine());
			jTextArea1.setText("Showing: " + serverInput + " items:");
			for (int x = 0; x < serverInput; x++) {
				jTextArea1.append("\n" + in.readLine());
			}

		} else {
			jTextArea1.setText("You're not connected to any Server!");
		}

	}

	private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:.
	}

	/*
	 * pin button action performed
	 */
	private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
		if (connected == true) {
			String input = jTextField3.getText();
			// System.out.println(jTextField3.getText());
			if (input.isEmpty() || (input.split(" ").length != 2)) {
				jTextArea1.setText("Specify where to PIN");
				jTextArea1.append("\nUse format:\n");
				jTextArea1.append("<xPosition> <yPosition>");
			} else {
				out.println("PIN " + input);
				jTextArea1.setText(in.readLine());
			}

		} else {
			jTextArea1.setText("You're not connected to any Server!");
		}

	}

	/*
	 * Unpin button action listenner
	 */
	private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
		if (connected == true) {
			String input = jTextField3.getText();
			// System.out.println(jTextField3.getText());
			if (input.isEmpty() || (input.split(" ").length != 2)) {
				jTextArea1.setText("Specify where to UNPIN");
				jTextArea1.append("\nUse format:\n");
				jTextArea1.append("<xPosition> <yPosition>");
			} else {
				out.println("UNPIN " + input);
				jTextArea1.setText(in.readLine());
			}

		} else {
			jTextArea1.setText("You're not connected to any Server!");
		}
	}

	private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
		// System.out.println("Clear button pressed");
		if (connected == true) {

			out.println("CLEAR " + jTextField3.getText());
			jTextArea1.setText(in.readLine());

		} else {
			jTextArea1.setText("You're not connected to any Server!");
		}

	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
		System.out.println("Post button pressed");
		if (connected == true) {
			String input = jTextField3.getText();
			if (!input.isEmpty()) {
				out.println("POST " + input);
				jTextArea1.setText(in.readLine());
			} else {
				jTextArea1.setText("Invalid POST command");
				jTextArea1.append("\nUse the following format:\n");
				jTextArea1.append("<xPosition> <yPosition> <height> <width> <color> <note message>");
			}

		} else {
			jTextArea1.setText("You're not connected to any Server!");
		}
	}

	/*
	 * connect button action performed
	 */
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws UnknownHostException, IOException {
		// TODO add your handling code here:
		if (connected == false) {
			String ip = jTextField1.getText();
			Integer port = Integer.valueOf(jTextField2.getText());
			jTextArea1.setText("Connected to server: " + ip + ": " + port);
			// "192.168.0.10"
			socket = new Socket(ip, port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			System.out.println(in.readLine());
			connected = true;
		}

	}
	/*
	 * disconnect button action performed
	 */

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
		if (connected == true) {
			System.out.println("Disconnect Pressed");
			jTextArea1.setText("Disconnecting from server");
			socket.close();
			connected = false;
		}

	}

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
	}

	private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {

	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Client().setVisible(true);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	// Variables declaration
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JButton jButton7;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	// End of variables declaration
}
