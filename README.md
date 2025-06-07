# Hamming Code Simülatörü

Java Swing kullanılarak geliştirilmiş bu uygulama, Hamming Kodlama (SEC - Single Error Correction) yöntemini görsel ve etkileşimli bir şekilde simüle eder.

##  Amaç

Bu uygulamanın amacı, kullanıcıya Hamming kodlamanın nasıl çalıştığını deneyimleyerek öğretmek ve tek bitlik hataları nasıl tespit ettiğini göstermektir.

##  Özellikler

- 8, 16 veya 32 bit uzunluğunda veri girişi
- Kontrol bitlerinin otomatik hesaplanması
- Tüm bitlerin arayüzde kutucuklar şeklinde gösterimi
- Her bitin kullanıcı tarafından tıklanarak değiştirilmesi
- Hatalı bitin tespiti ve kullanıcıya gösterilmesi

##  Nasıl Çalışır?

1. Uygulama başlatıldığında kullanıcıdan **8, 16 veya 32 bitlik** veri seçmesi istenir.
2. Kullanıcı belirtilen uzunlukta **ikili bir veri** girer (örnek: `10101010`).
3. Uygulama Hamming kodunu oluşturur ve **kontrol bitlerini** hesaplayarak dizide uygun yerlere ekler.
4. Tüm bitler (veri + kontrol) arayüzde gösterilir.
5. Kullanıcı herhangi bir bit kutusuna tıklayarak bitin değerini tersine çevirebilir.
6. Değişiklik yapıldığında sistem kontrol bitlerini yeniden hesaplayarak **hatalı bitin pozisyonunu** gösterir.

## Arayüz Bileşenleri

-  **Bit Kutuları:**
  - Veri bitleri: Açık yeşil (`#90EE90`)
  - Kontrol bitleri: Gri (`#808080`)
  - Değiştirilen bitler: Kırmızımsı (`#FF6347`)
  
-  **Üst Panel:** Girilen veri ve hesaplanan kontrol bitlerini gösterir.
-  **Alt Panel:** Hatalı bit olup olmadığına dair mesaj verir.

##  Örnek Kullanım

1. Bit sayısı olarak `8` seçildi.
2. Veri olarak `11010101` girildi.
3. Hamming kodu hesaplandı ve ekrana yansıtıldı.
4. Kullanıcı 5. bit’e tıkladı ve değeri değiştirildi.
5. Ekranda şu mesaj göründü:

```
Hata tespit edildi! Hatalı bit: 5
```
##  Teknolojiler

- **Java**
- **Swing GUI**
- **Hamming Code (SEC) algoritması**

##  Notlar

- Uygulama yalnızca **tek bitlik hataları** tespit eder.
- Giriş verisi yalnızca `0` ve `1` içermelidir.
- Kontrol bitleri 2'nin kuvveti olan konumlara yerleştirilir (1, 2, 4, 8, ...).
