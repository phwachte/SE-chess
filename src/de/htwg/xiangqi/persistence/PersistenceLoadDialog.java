package de.htwg.xiangqi.persistence;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;

import de.htwg.xiangqi.controller.IBoardManager;
import de.htwg.xiangqi.model.Board;

public class PersistenceLoadDialog extends JDialog implements ActionListener{
	
	private JButton ok = new JButton("ok"), cancel = new JButton("cancel");
	private JComboBox<Board> combobox = new JComboBox<Board>();
	private IBoardManager bm;

	public PersistenceLoadDialog(JFrame f, final IBoardManager bm, List<Board> list){
		super(f);
		
		this.bm = bm;
		
		setSize(new Dimension(400, 200));
		setTitle("Load Dialog");
		setResizable(true);
		setModal(true);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		for(Board sgw : list){
			combobox.addItem(sgw);
		}

		ok.addActionListener(this);
		cancel.addActionListener(this);
		
		
		add(combobox);
		add(cancel);
		add(ok);
		
		pack();
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if((JButton)e.getSource() == ok){
			bm.setBoard((Board) ((Board)combobox.getSelectedItem()).clone());
			bm.notifyObservers();
			this.dispose();
		}else{
			this.dispose();
		}
	}
}
