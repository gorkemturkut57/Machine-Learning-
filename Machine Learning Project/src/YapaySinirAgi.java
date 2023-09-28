public class YapaySinirAgi {

    // 3 farklı çiçek türü için 3 farklı nöron olacak sinir ağı sınıfımızda
    private Noron setosa;
    private Noron versicolor;
    private Noron virginica;

    public YapaySinirAgi(){ // constructorlarda bu nöronları yaratırız
        setosa = new Noron();
        versicolor = new Noron();
        virginica = new Noron();
    }

    public void noronEgit(String[][] girdiler, double lambda){ // nöronu eğitme metodumuzdur,
        // verilen girdi setinin uzunluğu kadar döner ve 1 epokunu tamamlar

        for(int i = 0; i < girdiler.length; i++){ // girdiler.length = 150;

            // mevcut olan girdi değerlerimizin değerlerini geçici olarak tutan yer
            double canakUzunlugu = Double.valueOf(girdiler[i][0]);
            double canakGenisligi = Double.valueOf(girdiler[i][1]);
            double tacUzunlugu = Double.valueOf(girdiler[i][2]);
            double tacGenisligi = Double.valueOf(girdiler[i][3]);
            String isim =girdiler[i][4];

            // nöron çıktısı bulan fonksiyona set olarak atarız sayı olarak verilen girdi değerlerini
            double[] veriSeti = {canakUzunlugu, canakGenisligi, tacUzunlugu, tacGenisligi};

            // her döngüde beklenen değerleri 0 a eşitlenir başlangıç koşulu olarak
            int setosaBeklenenDeger = 0;
            int versicolorBeklenenDeger = 0;
            int virginicaBeklenendeger = 0;

            // Nöron çıktısı üreten fonksiyona girdi seti atılarak üç çiçek nöronu için de ayrı ayrı çıktı bulunur
            double sonucSetosa = setosa.ciktiBul(veriSeti);
            double sonucVersicolor = versicolor.ciktiBul(veriSeti);
            double sonucVirginica = virginica.ciktiBul(veriSeti);

            double maxDeger = -1; // max değeri bulmak için başlangıç koşulu olarak, olmsaı imkansız bir değere atanır

            if(isim.equals("Iris-setosa")){ // veri setinden gelen isim Iris-setosa ise setosa Nöronunun en yüksek
                // değeri üretmesi gerekir
                setosaBeklenenDeger = 1;
            }
            else if (isim.equals("Iris-versicolor")) { // veri setinden gelen isim Iris-versicolor
                // ise versicolor Nöronunun en yüksek
                // değeri üretmesi gerekir
                versicolorBeklenenDeger = 1;
            }
            else if (isim.equals("Iris-virginica")) {  // veri setinden gelen isim Iris-virginica
                // ise virginica Nöronunun en yüksek
                // değeri üretmesi gerekir
                virginicaBeklenendeger = 1;
            }

            // Hangi nöronun en büyük çıktı ürettiğini bulduğumuz yer
            if(sonucSetosa >= sonucVersicolor && sonucSetosa >= sonucVirginica){
                maxDeger = sonucSetosa;
            }
            else if(sonucVersicolor >= sonucSetosa && sonucVersicolor >= sonucVirginica){
                maxDeger = sonucVersicolor;
            }
            else if(sonucVirginica >= sonucSetosa && sonucVirginica >= sonucVersicolor){
                maxDeger = sonucVirginica;
            }


            if(maxDeger == sonucSetosa && setosaBeklenenDeger != 1){ //en büyük çıktıyı setosa verirse ama
                // girdi olarak setosa harici bir şey gelirse
                if(versicolorBeklenenDeger == 1){
                    versicolor.agirlikArttir(versicolor.getAgirliklar(), veriSeti, lambda);
                }
                else if(virginicaBeklenendeger == 1){
                    virginica.agirlikArttir(virginica.getAgirliklar(), veriSeti, lambda);
                }
                setosa.agirlikAzalt(setosa.getAgirliklar(), veriSeti, lambda);
            }

            else if(maxDeger == sonucVirginica && virginicaBeklenendeger != 1){ //en büyük çıktıyı virginica verirse ama
                // girdi olarak virginica harici bir şey gelirse
                if(versicolorBeklenenDeger == 1){
                    versicolor.agirlikArttir(versicolor.getAgirliklar(), veriSeti, lambda);
                }
                else if(setosaBeklenenDeger == 1){
                    setosa.agirlikArttir(setosa.getAgirliklar(),veriSeti, lambda);
                }
                virginica.agirlikAzalt(virginica.getAgirliklar(), veriSeti, lambda);
            }
            else if(maxDeger == sonucVersicolor && versicolorBeklenenDeger != 1){ //en büyük çıktıyı versicolor verirse ama
                // girdi olarak versicolor harici bir şey gelirse
                if(setosaBeklenenDeger == 1){
                    setosa.agirlikArttir(setosa.getAgirliklar(), veriSeti, lambda);
                }
                else if(virginicaBeklenendeger == 1){
                    virginica.agirlikArttir(virginica.getAgirliklar(), veriSeti, lambda);
                }
                versicolor.agirlikAzalt(versicolor.getAgirliklar(), veriSeti, lambda);
            }
        }
    }

    public double dogrulukBul(String[][] girdiler){ // 1 epok sonucu elde edilen doğruluk oranını döndüren fonksiyondur

        int dogruSayisi = 0; // 1 epok(150 dönüş) sonrası bulunan doğru sayısını tutar

        for(int i = 0; i < girdiler.length; i++){ // girdi setimizin uzunluğu kadar işlem yapılır

            // Fonksiyonumuzun her satırı için değerler tutulur ve ilgili fonksiyona atılarak çıktı değerleri ürtilir
            double canakUzunlugu = Double.valueOf(girdiler[i][0]);
            double canakGenisligi = Double.valueOf(girdiler[i][1]);
            double tacUzunlugu = Double.valueOf(girdiler[i][2]);
            double tacGenisligi = Double.valueOf(girdiler[i][3]);
            String isim =girdiler[i][4];

            double[] veriSeti = {canakUzunlugu, canakGenisligi, tacUzunlugu, tacGenisligi};

            // beklenen değerler her döngü başında 0 olarak güncellenir
            int setosaBeklenenDeger = 0;
            int versicolorBeklenenDeger = 0;
            int virginicaBeklenendeger = 0;

            // Çıktı üreten fonksiyona veri seti atılarak 3 ayrı çiçek nöronu için değerler üretilir
            double sonucSetosa = setosa.ciktiBul(veriSeti);
            double sonucVersicolor = versicolor.ciktiBul(veriSeti);
            double sonucVirginica = virginica.ciktiBul(veriSeti);

            double maxDeger = -1; // max değeri bulmak için başlangıç koşulu olarak, olmsaı imkansız bir değere atanır
            // ve her döngüde -1 diye güncellenir

            // Girdiden gelen isime göre beklenen değer güncellenir
            if(isim.equals("Iris-setosa")){
                setosaBeklenenDeger = 1;
            }
            else if (isim.equals("Iris-versicolor")) {
                versicolorBeklenenDeger = 1;
            }
            else if (isim.equals("Iris-virginica")) {
                virginicaBeklenendeger = 1;
            }

            // en yüksek çıktı üreten nöron bulunur
            if(sonucSetosa >= sonucVersicolor && sonucSetosa >= sonucVirginica){
                maxDeger = sonucSetosa;
            }
            else if(sonucVersicolor >= sonucSetosa && sonucVersicolor >= sonucVirginica){
                maxDeger = sonucVersicolor;
            }
            else if(sonucVirginica >= sonucSetosa && sonucVirginica >= sonucVersicolor){
                maxDeger = sonucVirginica;
            }

            // Doğruluk demek, girdiden gelen bitki türü ile (beklenen değer), nöron çıktısı en büyük olan nöron aynı
            // ise doğru demektir. ağırlık güncellemeden sadece doğru sayımızı 1 arttırırız
            if(maxDeger == sonucSetosa && setosaBeklenenDeger == 1){
                dogruSayisi++;
            }
            else if(maxDeger == sonucVirginica && virginicaBeklenendeger == 1){
                dogruSayisi++;
            }
            else if(maxDeger == sonucVersicolor && versicolorBeklenenDeger == 1){
                dogruSayisi++;
            }
        }
        return  (dogruSayisi * 100.0) / girdiler.length;
    }
}