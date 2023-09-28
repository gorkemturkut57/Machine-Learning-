import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

public class Main {

    public static double deneyYap(String[][] girdiler, int epokSayisi, double lambda){ // deneyleri bu fonksiyon yapar
        // yapılacak olan epoke sayısını ve ağırlıkların değiştirileceği lambda sayısını alarak güncelleme yapar

        YapaySinirAgi sinirAgi = new YapaySinirAgi(); // her deneyi yeni yapay sinir ağı üstünde, yani her nöronun
        // farklı random, başlangıç ağırlıkları olacak şekilde yaparız
        for(int i = 0; i < epokSayisi; i++){ // verilen epok sayısı kadar nöronumuz eğitilir
            sinirAgi.noronEgit(girdiler, lambda);
        }
        double dogrulukOrani = sinirAgi.dogrulukBul(girdiler); //nöron eğitildikten sonra, doğruluk bul fonksiyonu
        // çağırılarak o deneyin doğruluk oranını buluruz.
        return dogrulukOrani;
    }

    public static void main(String[] args) {

        // Girdileri dosyadan alarak 150 x 5 lik matriste tutarız tekrar tekrar kullanabilmek için.
        String[][] girdiler = new String[150][5];
        YapaySinirAgi sinirAgi1 = new YapaySinirAgi();
        Scanner inputText = null;

        try { // dosyayı açmaya yarar
            inputText = new Scanner(new FileInputStream("iris.data"));
        } catch (FileNotFoundException e) { // dosya bulunamazsa hata verir ve sistemden çıkar.
            System.out.println("Dosya bulunamadı");
            System.exit(0);
        }
        int satir=-1; // okunacak satırı tutan değişken

        while (inputText.hasNextLine()) { // dosyamızda veri bitene kadar dönmeye devam eder
            satir++;
            String[] arr = inputText.nextLine().split(",");

            for(int sutun = 0; sutun < 5; sutun++){

                if(sutun != 4){ // double değerler için böyle olacak, ismi bu kategoriye alamayız. onu ayırdığımız yer
                    double yeniDeger = Double.valueOf(arr[sutun]);
                    yeniDeger = yeniDeger / 10;  // girdi değerlerini [0, 1] arasına ölçeklendirdiğimiz yer
                    String deger = String.valueOf(yeniDeger);
                    arr[sutun] = deger;
                }
                girdiler[satir][sutun]=arr[sutun]; // girdiler matrisimizi güncelleriz, (String olarak tutulur)
            }
        }

        for(int i = 0; i < 50; i++){
            sinirAgi1.noronEgit(girdiler, 0.01);
        }

        double denemeBirelliEpokeLambda001 = deneyYap(girdiler, 50, 0.01);
        // 50 kere 0,01 lamda değeri ile nöronumuzu eğitiriz ve yazdırırız
        System.out.printf("50 epoke, lambda 0,01ken çıkan deneyin sonucu: %.2f", denemeBirelliEpokeLambda001);
        System.out.println("");
        System.out.println("");

        // ilk denememizin 27 farklı kombinasyonunu (lambda sayısı ve epok sayısı değişecek şekilde uygularız
        // ve kayıt ederiz. bu değerleri tablo oluşturmak için kullanıcağız
        double denemeBiryirmiEpokeLambda001 = deneyYap(girdiler, 20, 0.01);
        double denemeBiryüzEpokeLambda001 = deneyYap(girdiler, 100, 0.01);

        double denemeBiryirmiEpokeLambda0005 = deneyYap(girdiler, 20, 0.005);
        double denemeBiryüzEpokeLambda0005 = deneyYap(girdiler, 100, 0.005);
        double denemeBirElliEpokeLambda0005 = deneyYap(girdiler, 50, 0.005);


        double denemeBiryirmiEpokeLambda0025 = deneyYap(girdiler, 20, 0.025);
        double denemeBiryüzEpokeLambda0025 = deneyYap(girdiler, 100, 0.025);
        double denemeBirElliEpokeLambda0025 = deneyYap(girdiler, 50, 0.025);

        // ilk deneme sonuçlarını tabloda yazdırmak için deneme1 için oluşturduğumuz arraye kaydederiz
        double[][] ilkDeneySonuclari = {{denemeBiryirmiEpokeLambda0005, denemeBiryirmiEpokeLambda001,denemeBiryirmiEpokeLambda0025},
                {denemeBirElliEpokeLambda0005, denemeBirelliEpokeLambda001, denemeBirElliEpokeLambda0025},
                {denemeBiryüzEpokeLambda0005,  denemeBiryüzEpokeLambda001, denemeBiryüzEpokeLambda0025}};

        System.out.println("Birinci deneme sonuçları(sırayla)");
        // ilk deneme sonuçlarını tablo şeklinde yazdırırız
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("%.2f ", ilkDeneySonuclari[i][j]);
            }
            System.out.println("");
        }

        System.out.println("");
        System.out.println("");

        // ikinci denememizin 27 farklı kombinasyonunu (lambda sayısı ve epok sayısı değişecek şekilde uygularız
        // ve kayıt ederiz. bu değerleri tablo oluşturmak için kullanıcağız
        double denemeIkielliEpokeLambda001 = deneyYap(girdiler, 50, 0.01);
        double denemeIkiyirmiEpokeLambda001 = deneyYap(girdiler, 20, 0.01);
        double denemeIkiyüzEpokeLambda001 = deneyYap(girdiler, 100, 0.01);

        double denemeIkiyirmiEpokeLambda0005 = deneyYap(girdiler, 20, 0.005);
        double denemeIkiyüzEpokeLambda0005 = deneyYap(girdiler, 100, 0.005);
        double denemeIkiElliEpokeLambda0005 = deneyYap(girdiler, 50, 0.005);

        double denemeIkiyirmiEpokeLambda0025 = deneyYap(girdiler, 20, 0.025);
        double denemeIkiyüzEpokeLambda0025 = deneyYap(girdiler, 100, 0.025);
        double denemeIkiElliEpokeLambda0025 = deneyYap(girdiler, 50, 0.025);

        // ikinci deneme sonuçlarını tabloda yazdırmak için deneme2 için oluşturduğumuz arraye kaydederiz
        double[][] IkinciDeneySonuclari = {{denemeIkiyirmiEpokeLambda0005, denemeIkiyirmiEpokeLambda001,denemeIkiyirmiEpokeLambda0025},
                {denemeIkiElliEpokeLambda0005, denemeIkielliEpokeLambda001, denemeIkiElliEpokeLambda0025},
                {denemeIkiyüzEpokeLambda0005,  denemeIkiyüzEpokeLambda001, denemeIkiyüzEpokeLambda0025}};

        System.out.println("İkinci deneme sonuçları(sırayla)");
        // ikinci deneme sonuçlarını tablo şeklinde yazdırırız
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("%.2f ", IkinciDeneySonuclari[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("");

        // Ucuncu denememizin 27 farklı kombinasyonunu (lambda sayısı ve epok sayısı değişecek şekilde uygularız
        // ve kayıt ederiz. bu değerleri tablo oluşturmak için kullanıcağız
        double denemeUcelliEpokeLambda001 = deneyYap(girdiler, 50, 0.01);
        double denemeUcyirmiEpokeLambda001 = deneyYap(girdiler, 20, 0.01);
        double denemeUcyüzEpokeLambda001 = deneyYap(girdiler, 100, 0.01);

        double denemeUcyirmiEpokeLambda0005 = deneyYap(girdiler, 20, 0.005);
        double denemeUcyüzEpokeLambda0005 = deneyYap(girdiler, 100, 0.005);
        double denemeUcElliEpokeLambda0005 = deneyYap(girdiler, 50, 0.005);

        double denemeUcyirmiEpokeLambda0025 = deneyYap(girdiler, 20, 0.025);
        double denemeUcyüzEpokeLambda0025 = deneyYap(girdiler, 100, 0.025);
        double denemeUcElliEpokeLambda0025 = deneyYap(girdiler, 50, 0.025);

        // ucuncu deneme sonuçlarını tabloda yazdırmak için deneme3 için oluşturduğumuz arraye kaydederiz
        double[][] UcuncuDeneySonuclari = {{denemeUcyirmiEpokeLambda0005, denemeUcyirmiEpokeLambda001,denemeUcyirmiEpokeLambda0025},
                {denemeUcElliEpokeLambda0005, denemeUcelliEpokeLambda001, denemeUcElliEpokeLambda0025},
                {denemeUcyüzEpokeLambda0005,  denemeUcyüzEpokeLambda001, denemeUcyüzEpokeLambda0025}};

        System.out.println("Üçüncü deneme sonuçları(sırayla)");
        // ucuncu denememizdeki çıkan sonuçları yazdırma işlemi
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("%.2f ", UcuncuDeneySonuclari[i][j]);
            }
            System.out.println("");
        }
    }
}