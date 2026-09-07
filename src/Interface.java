import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class Interface {
	public Interface(){
		Shutdown desligar = new Shutdown();

		JFrame window = new JFrame("Desligar Computador Simples");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(400, 400);
		window.setLayout(null);
		window.setResizable(false);
		
		JLabel textread1 = new JLabel("Digite o tempo que você deseja (SEM LETRA).", SwingConstants.CENTER);
		JLabel textread2 = new JLabel("E então selecione se é hora ou minuto, para cancelar", SwingConstants.CENTER);
		JLabel textread3 = new JLabel(desligar.getCancelInstruction(), SwingConstants.CENTER);
		
		window.add(textread1);
		window.add(textread2);
		window.add(textread3);
		textread1.setBounds(10, 20, 380, 30);
		textread2.setBounds(10, 45, 380, 30);
		textread3.setBounds(10, 70, 380, 30);
		
		JTextField text = new JTextField();
		window.add(text);
		text.setBounds(90, 130, 220, 40);
		
		JButton hora = new JButton("Hora");
		JButton minuto = new JButton("Minuto");
		JButton cancelar = new JButton("Cancelar");
		
		window.add(hora);
		window.add(minuto);
		window.add(cancelar);
		
		hora.setBounds(30, 240, 100, 40);
		minuto.setBounds(145, 240, 100, 40);
		cancelar.setBounds(260, 240, 100, 40);
		
		hora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int val = Integer.parseInt(text.getText().trim());
					desligar.agendarDesligamento(val, true);
					JOptionPane.showMessageDialog(window, "Desligamento agendado para " + val + " hora(s)!");
					System.exit(0);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(window, "Por favor, digite um número inteiro válido!", "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(window, "Erro ao executar comando: " + e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});

		minuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int val = Integer.parseInt(text.getText().trim());
					desligar.agendarDesligamento(val, false);
					JOptionPane.showMessageDialog(window, "Desligamento agendado para " + val + " minuto(s)!");
					System.exit(0);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(window, "Por favor, digite um número inteiro válido!", "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(window, "Erro ao executar comando: " + e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});

		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					desligar.cancelarDesligamento();
					JOptionPane.showMessageDialog(window, "Comando de cancelamento enviado!");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(window, "Erro ao cancelar desligamento: " + e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});

		window.setVisible(true);
	}
}



