import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.lang.Runtime;
public class Interface {
	public Interface(){		
		JFrame window = new JFrame("Desligar Computador Simples");
		window.setSize(400,400);
		window.setLayout(null);
		window.setVisible(true);
		window.setResizable(false);
		
		JLabel textread1 = new JLabel("Digite o tempo que você deseja (SEM LETRA).");
		JLabel textread2 = new JLabel("E então seleciona se é hora ou minuto, para cancelar");
		JLabel textread3 = new JLabel("vá no cmd e digite 'shutdown -a'.");
		window.add(textread1);
		window.add(textread2);
		window.add(textread3);
		textread1.setBounds(10, -20, 300, 200);
		textread2.setBounds(10, 0, 300, 200);
		textread3.setBounds(10, 20, 300, 200);
		
		JTextField text = new JTextField();
		window.add(text);
		text.setBounds(90, 200, 200, 40);
		
		
		JButton hora = new JButton("Hora");
		JButton minuto = new JButton("Minuto");
		window.add(hora);
		window.add(minuto);
		hora.setBounds(50, 300, 95, 30);
		minuto.setBounds(235, 300, 95, 30);
		
		hora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Runtime runtime = Runtime.getRuntime();
				Shutdown desligar = new Shutdown();
				int at = desligar.hora(Integer. parseInt(text.getText()));
				try {
					runtime.exec("shutdown -s -t "+at);
					System.exit(0);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		minuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Runtime runtime = Runtime.getRuntime();
				Shutdown desligar = new Shutdown();
				int ad = desligar.minuto(Integer. parseInt(text.getText()));
				try {
					runtime.exec("shutdown -s -t "+ad);
					System.exit(0);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	
	}
}


