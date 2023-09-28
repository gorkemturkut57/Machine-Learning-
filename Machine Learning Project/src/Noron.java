import java.util.Random;
public class Noron {

    private double[] agirliklar; // Noron nesnelerinin sadece agirliklari tutulacak ve 4 tane agirligi olacak

    public Noron(){
        // Noron nesnesi ilk yaratilirken bütün agirliklari random olarak atanır
        agirliklar = new double[4];
        Random random=new Random(); // [0, 1)
        for(int i = 0; i < agirliklar.length; i++){
            agirliklar[i] = random.nextDouble();
        }
    }

    public double ciktiBul(double[] girdi){ // noron ciktisi hesaplayan metoddur. ilgili ağırlıklar ile
        //ilgili girdileri çarparak bir sonuc elde eder ve döndürür.

        double toplam = 0;
        for(int i = 0; i < 4; i++){
            toplam += girdi[i] * agirliklar[i];
        }
        return toplam;
    }

    public double[] getAgirliklar() {
        return agirliklar;
    }

    // ağırlığı arttırılması gereken noronun agırlıgını verilen lambda değeri kadar arttıran fonksiyon
    public void agirlikArttir(double[] agirliklar, double[] girdiler, double lambda) {
        for(int i = 0; i < agirliklar.length; i++){
            agirliklar[i]= agirliklar[i] + (lambda* girdiler[i]);
        }
    }

    // ağırlığı azaltılması gereken noronun agırlıgını verilen lambda değeri kadar azaltılan fonksiyon
    public void agirlikAzalt(double[] agirliklar, double[] girdiler, double lambda) {
        for(int i = 0; i < agirliklar.length; i++){
            agirliklar[i]= agirliklar[i] - (lambda* girdiler[i]);
        }
    }
}