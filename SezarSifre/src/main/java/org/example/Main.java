package org.example;

import javax.swing.*;
import java.awt.*;

public class Main{
    public static void main(String[] args) {
        JFrame pencere = new JFrame("Caesar Cipher");
        pencere.setSize(420, 280);
        pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pencere.setLayout(new FlowLayout());

        JLabel kEtiket = new JLabel("Şifre Anahtarı:");
        JTextField kGirisi = new JTextField(5);
        JLabel mEtiket = new JLabel("Metin:");
        JTextField mGirisi = new JTextField(15);
        JButton sifrele = new JButton("Dönüştür");
        JButton coz = new JButton("Geri Al");
        JTextArea sonuc = new JTextArea(5, 25);
        sonuc.setEditable(false);

        sifrele.addActionListener(e -> islemYap(kGirisi, mGirisi, sonuc, true));
        coz.addActionListener(e -> islemYap(kGirisi, mGirisi, sonuc, false));

        pencere.add(kEtiket);
        pencere.add(kGirisi);
        pencere.add(mEtiket);
        pencere.add(mGirisi);
        pencere.add(sifrele);
        pencere.add(coz);
        pencere.add(new JScrollPane(sonuc));

        pencere.setVisible(true);
    }

    private static void islemYap(JTextField kGirisi, JTextField mGirisi, JTextArea sonuc, boolean ileri) {
        String metin = mGirisi.getText();
        String anahtarStr = kGirisi.getText();

        if (!anahtarStr.matches("\\d+") || metin.isEmpty()) {
            sonuc.setText("Anahtar ve metin doğru girilmeli.");
            return;
        }

        int anahtar = Integer.parseInt(anahtarStr);
        if (anahtar < 0 || anahtar > 25) {
            sonuc.setText("Anahtar 0-25 arasında olmalı.");
            return;
        }

        StringBuilder yeniMetin = new StringBuilder();
        for (char karakter : metin.toCharArray()) {
            if ((karakter >= 'A' && karakter <= 'Z') || (karakter >= 'a' && karakter <= 'z')) {
                char taban = (karakter >= 'A' && karakter <= 'Z') ? 'A' : 'a';
                int kaydirma = ileri ? anahtar : -anahtar;
                yeniMetin.append((char) ((karakter - taban + kaydirma + 26) % 26 + taban));
            } else {
                yeniMetin.append(karakter);
            }
        }

        sonuc.setText(yeniMetin.toString());
    }
}
