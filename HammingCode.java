import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HammingCode extends JFrame {
    private int M; // Veri bit sayısı (8, 16, 32)
    private int K; // Kontrol bit sayısı
    private int[] bitPosition; // Hamming Code bitlerini tutan dizi
    private int[] originalBits;
    private boolean[] modified; // Hangi bitlerin değiştirildiğini takip eder
    private ArrayList<JButton> bitButtons; // Bit kutucukları
    private JLabel errorLabel; // Hata mesajı için etiket
    private JLabel codeLabel; // Girilen kod ve Check Bits için etiket

    public HammingCode() {
        // Arayüz oluşturma
        setTitle("Hamming Code Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(800, 300);
        getContentPane().setBackground(new Color(51, 51, 51)); // Arka plan siyaha yakın gri (#333333)

        // Kullanıcıdan bit sayısını al
        String[] options = {"8", "16", "32"};
        String selected = (String) JOptionPane.showInputDialog(this, "Kaç bit veri girmek istersiniz?",
                "Bit Sayısı Seçimi", JOptionPane.QUESTION_MESSAGE, null, options, "8");
        M = Integer.parseInt(selected);

        // Kontrol bit sayısını hesaplama
        K = 0;
        while (Math.pow(2, K) < (M + K + 1)) {
            K++;
        }

        // Toplam bit sayısı
        int totalBits = M + K;
        bitPosition = new int[totalBits];
        originalBits = new int[totalBits];
        modified = new boolean[totalBits]; // Değiştirilme durumunu takip eder
        bitButtons = new ArrayList<>();

        String input = JOptionPane.showInputDialog(this, M + " bitlik veriyi girin (örneğin: 10101010):");
        while (input.length() != M || !input.matches("[01]+")) {
            input = JOptionPane.showInputDialog(this, "Geçersiz giriş! " + M + " bitlik 0/1 dizisi girin:");
        }

        // Veriyi bitPosition dizisine yerleştir (kontrol bitleri hariç)
        int dataIndex = 0;
        for (int i = 0; i < totalBits; i++) {
            if (isPowerOfTwo(i + 1)) {
                bitPosition[i] = 0; // Kontrol bitleri için yer ayır
            } else {
                bitPosition[i] = Character.getNumericValue(input.charAt(dataIndex++));
            }
        }

        // Kontrol bitlerini hesapla
        calculateParityBits();

        // Orijinal bitleri sakla
        System.arraycopy(bitPosition, 0, originalBits, 0, totalBits);

        // Arayüzü oluştur
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(new Color(51, 51, 51)); // Panel arka planı da gri
        StringBuilder checkBitsStr = new StringBuilder("Check Bits: ");
        for (int i = 0; i < totalBits; i++) {
            if (isPowerOfTwo(i + 1)) {
                checkBitsStr.append(bitPosition[i]);
            }
        }
        codeLabel = new JLabel("Girilen Kod: " + input + " | " + checkBitsStr.toString(), SwingConstants.CENTER);
        codeLabel.setForeground(Color.WHITE); // Yazı rengi beyaz
        topPanel.add(codeLabel);
        add(topPanel, BorderLayout.NORTH);

        JPanel bitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        bitPanel.setBackground(new Color(51, 51, 51)); // Panel arka planı da gri
        for (int i = 0; i < totalBits; i++) {
            JPanel bitContainer = new JPanel(new BorderLayout());
            bitContainer.setBackground(new Color(51, 51, 51)); // Panel arka planı da gri
            JButton button = new JButton(String.valueOf(bitPosition[i])); // Hamming Code bitlerini göster
            button.setPreferredSize(new Dimension(40, 40)); // Daha büyük kutucuklar
            if (isPowerOfTwo(i + 1)) {
                button.setBackground(new Color(128, 128, 128)); // Kontrol bitleri gri
            } else {
                button.setBackground(new Color(144, 238, 144)); // Veri bitleri açık yeşil
            }
            final int index = i;
            button.addActionListener(e -> toggleBit(index, button));
            bitContainer.add(button, BorderLayout.CENTER);

            // Kutunun altına kaçıncı bit olduğunu yaz
            JLabel bitLabel = new JLabel((index + 1) + ". bit", SwingConstants.CENTER);
            bitLabel.setForeground(Color.WHITE); // Yazı rengi beyaz
            bitContainer.add(bitLabel, BorderLayout.SOUTH);

            bitButtons.add(button);
            bitPanel.add(bitContainer);
        }

        // Hata mesajı için ara panel
        JPanel errorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20)); // Yukarı almak için boşluk
        errorPanel.setBackground(new Color(51, 51, 51)); // Panel arka planı da gri
        errorLabel = new JLabel("Hata mesajları burada görünecek.", SwingConstants.CENTER);
        errorLabel.setForeground(Color.WHITE); // Yazı rengi beyaz
        errorLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Yazı tipini ve boyutunu büyüt
        errorPanel.add(errorLabel);

        add(bitPanel, BorderLayout.CENTER);
        add(errorPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    // Kontrol bitlerini hesapla
    private void calculateParityBits() {
        for (int i = 0; i < K; i++) {
            int parityPos = (int) Math.pow(2, i) - 1; // 0, 1, 3, 7, ...
            int parity = 0;
            for (int j = parityPos; j < bitPosition.length; j++) {
                if (((j + 1) >> i) % 2 == 1) { // Bu bit, i'nci kontrol bitine dahil mi?
                    parity ^= bitPosition[j];
                }
            }
            bitPosition[parityPos] = parity;
        }
    }

    // Bir bitin tersini al veya eski haline dön ve hata kontrolü yap
    private void toggleBit(int index, JButton button) {
        if (modified[index]) {
            // Eğer daha önce değiştirilmişse, eski haline dön
            bitPosition[index] = originalBits[index];
            modified[index] = false; // Değiştirilmiş durumu sıfırla
            button.setText(String.valueOf(bitPosition[index]));
            if (isPowerOfTwo(index + 1)) {
                button.setBackground(new Color(128, 128, 128)); // Kontrol bitleri gri
            } else {
                button.setBackground(new Color(144, 238, 144)); // Veri bitleri açık yeşil
            }
        } else {
            // Değiştirilmemişse, tersine çevir
            bitPosition[index] = 1 - bitPosition[index]; // 0 ↔ 1
            modified[index] = true; // Değiştirildi olarak işaretle
            button.setText(String.valueOf(bitPosition[index]));
            button.setBackground(new Color(255, 99, 71));
        }

        // Hata kontrolü (Compare adımı)
        int[] newParity = new int[K];
        for (int i = 0; i < K; i++) {
            int parityPos = (int) Math.pow(2, i) - 1;
            int parity = 0;
            for (int j = parityPos; j < bitPosition.length; j++) {
                if (((j + 1) >> i) % 2 == 1) {
                    parity ^= bitPosition[j];
                }
            }
            newParity[i] = parity;
        }

        // Hata pozisyonunu bul (Corrector adımı)
        int errorPos = 0;
        for (int i = 0; i < K; i++) {
            if (newParity[i] != 0) {
                errorPos += (int) Math.pow(2, i);
            }
        }

        if (errorPos == 0) {
            errorLabel.setText("Hata yok.");
        } else {
            errorLabel.setText("Hata tespit edildi! Hatalı bit: " + errorPos);
        }

        // Check Bits'i güncelle
        StringBuilder checkBitsStr = new StringBuilder("Check Bits: ");
        for (int i = 0; i < bitPosition.length; i++) {
            if (isPowerOfTwo(i + 1)) {
                checkBitsStr.append(bitPosition[i]);
            }
        }
        codeLabel.setText("Girilen Kod: " + inputToString(originalBits, M) + " | " + checkBitsStr.toString());
    }

    // 2'nin kuvveti mi kontrol et
    private boolean isPowerOfTwo(int n) {
        return (n & (n - 1)) == 0;
    }

    // Diziyi stringe çevir
    private String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    // Orijinal girişi stringe çevir
    private String inputToString(int[] arr, int m) {
        StringBuilder sb = new StringBuilder();
        int dataIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!isPowerOfTwo(i + 1)) {
                sb.append(arr[i]);
                dataIndex++;
                if (dataIndex == m) break;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HammingCode::new);
    }
}