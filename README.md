# HammingCode

Genel Bakış
Bu uygulama, Hamming Kodlama (Hamming Code) yöntemini kullanarak tek bitlik hataları algılayan ve kullanıcıya gösteren bir simülatördür. Java Swing ile geliştirilmiş olan bu arayüzde, kullanıcı veriyi girer, uygulama kontrol bitlerini hesaplar ve hataları tespit etmeyi sağlar.

Özellikler
Kullanıcıdan 8, 16 veya 32 bit uzunluğunda ikili veri alır.

Girilen veriye uygun olarak Hamming kontrol bitlerini hesaplar.

Tüm bitleri (veri + kontrol bitleri) ekranda kutucuklar olarak gösterir.

Her bir bit tıklanarak değiştirilebilir.

Hata analizi yapılır ve hatalı bit pozisyonu ekranda gösterilir.

Bitlerin renkleri ile anlamları belirtilir:

Veri bitleri: Açık yeşil

Kontrol bitleri: Gri

Değiştirilen bitler: Kırmızı

Kullanım Adımları
Uygulama başlatıldığında kullanıcıdan bit sayısı seçmesi istenir (8, 16 veya 32).

Ardından bu uzunlukta bir ikili veri girişi yapılır (örnek: 10101010).

Uygulama, girilen veriye kontrol bitlerini ekleyerek Hamming kodlamasını oluşturur.

Her bir bit bir kutucukla temsil edilir. Kutucuklara tıklanarak bitler değiştirilebilir.

Bit değiştirildiğinde, uygulama hatayı tespit eder ve kullanıcıya hangi bitin hatalı olduğunu bildirir.

Arayüz Öğeleri
Üst Bilgi: Girilen veri ve hesaplanan kontrol bitleri gösterilir.

Orta Panel: Bit kutucukları yatay olarak sıralanır.

Alt Bilgi: Hata mesajları burada görüntülenir.

Örnek Senaryo
Kullanıcı 8 bit veri girdi: 10101010

Uygulama gerekli kontrol bitlerini ekledi ve bitleri ekranda gösterdi.

Kullanıcı 5. bit'e tıkladı ve değeri değiştirdi.

Alt panelde şu mesaj göründü: Hata tespit edildi! Hatalı bit: 5

Amaç
Bu simülatörün amacı, Hamming Kodlama mantığını görsel ve etkileşimli şekilde göstermek ve kullanıcıya bu algoritmanın nasıl çalıştığını deneyimleyerek öğretmektir.

Geliştirici Notları
Uygulama yalnızca tek bit hatalarını tespit eder (SEC - Single Error Correction).

Kontrol bitleri dinamik olarak hesaplanır (2^k ≥ m + k + 1 formülüne göre).

GUI bileşenleri Java Swing ile oluşturulmuştur.
