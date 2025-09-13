import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.io.*;

public class SimpleNotepad {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Notepad");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane);

        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        // Edit menu
        JMenu editMenu = new JMenu("Edit");
        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        menuBar.add(editMenu);

        // Help menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);
        menuBar.add(helpMenu);

        // Format menu for color chooser
        JMenu formatMenu = new JMenu("Format");
        JMenuItem colorItem = new JMenuItem("Text Color");
        formatMenu.add(colorItem);
        menuBar.add(formatMenu);

        frame.setJMenuBar(menuBar);

        // Open file
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(frame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        textArea.setText("");
                        String line;
                        while ((line = reader.readLine()) != null) {
                            textArea.append(line + "\n");
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Cannot open file!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Save file
        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showSaveDialog(frame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                        writer.write(textArea.getText());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Cannot save file!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Exit
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Edit
        cutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.cut();
            }
        });
        copyItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.copy();
            }
        });
        pasteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.paste();
            }
        });

        // Help
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Created by: your_name\nID: your_id", "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Format 
        colorItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(frame, "Choose Text Color", textArea.getForeground());
                if (selectedColor != null) {
                    textArea.setForeground(selectedColor);
                }
            }
        });

        frame.setVisible(true);
    }
}
