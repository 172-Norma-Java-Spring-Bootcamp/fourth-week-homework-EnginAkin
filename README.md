# fourth-week-homework

Merhaba arkadaşlar,
Bu haftaki ödeviniz, hafta sonu yaptığımız projenin eksik kalan kısımlarının implmentasyonunu yapmak olacaktır. 

Biliyorsunuz, bu hafta CustomerController kısmını yaptık. Müşteri yaratma ve müşteri id'ye göre müşteri bilgilerini getirme EP'lerini yazdık.
Müşteri için, müşteriyi silme ve tüm müşterileri getirme EP'lerini yazalım. Müşteri silinirken, müşterinin ID alanını path variable olarak geçebiliriz.
Bununla birlikte, müşteri üzerinde isDeleted alanı eklemeniz gerekecek. Müşteri silinince bu alanı true'ye çekeceksiniz. Müşteriler getirilirken de eğer müşterinin statüsü isDeleted ise bu müşteriyi hem listeleme EP'sinde hem de ID'ye göre müşteri getirme EP'sinde dikkate almalısınız. Ayrıca, silinmiş bir müşteri tekrar silinmek istendiğinde bu müşterinin zaten silindiği bilgisini vermelisiniz.

Bununla birlikte, ürün ekleme, ürün listeleme, ürün getirme ve ürün silme EP'lerini de müşteri EP'leri gibi implemente etmelisiniz.

Bu EP'leri de yaptıktan sonra, müşteri ürünler arasından seçecği ürünleri sepete ekleyecek.. Daha sonra sepete eklenen ürünler için sipariş geçilecek. 
Tüm bu operasyonlar için modellerimizi yazmıştık. Eksik olduğunu düşündüğünüz alanları modellere ekleyebilirsiniz.

Ayrıca, Exception handling yapmanızı bekliyor olacağım. Özellikle base bir Object üzerinden tüm error responslarınızı anlamlı bir şekilde dönmelisiniz.

Bir diğer önemli nokta, sipariş alınırken, siparişe indirim uygulamak. Bir siparişe birden fazla indirim uygulanabilir. Müşteriye göre de indirimle uygulanabilir veya müşteriye indirim tanımlanabilir. Bu kısımların modellerini çıkartmamıştık, dileyen bu şekilde modelleri çıakrtıp bu işlemleri de yapabilir. 

Sipariş ve sepete indirim uygulama kısmında kulanabileceğiniz bazı Design Pattern'lar var, dilerseniz bu design patternları araştırıp, uygulamanızda bunları kullanabilirsiniz. 

Ödevin bitiş tarihi, 29 Nisan 2022 olarak belirlendi. 

Hepinize iyi kodlamalar diliyorum :)
