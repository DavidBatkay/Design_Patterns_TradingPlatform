Documentatie Design Patterns

1. Singleton Pattern
Folosit in: Market.java, UserManager.java

Motivatie:
Aveam nevoie de o singura instanta pentru Market ca sa tinem preturile si stocurile consistente peste tot. La fel si la UserManager, sa stim cine e logat in toata aplicatia. Daca faceam mai multe instante, datele nu se sincronizau.

2. Factory Method Pattern
Folosit in: TradableFactory.java

Motivatie:
Trebuia sa creez obiecte de tip Stock sau Crypto fara sa umplu codul clientului de "new Stock()" sau "new Crypto()". Factory-ul se ocupa de decizia asta pe baza unui string, si daca mai adaugam ceva pe viitor (gen Commodities), modificam doar in Factory.

3. Observer Pattern
Folosit in: Market.java, Portfolio.java , AutoTrader.java

Motivatie:
Cand se schimba pretul la un stock in Market, trebuie sa anunt automat portofoliul utilizatorului sau bot-ul de trading. Cu Observer, Market-ul doar zice ca s-a schimbat pretul si cine e abonat primeste notificarea. E bun ca decupleaza logica.

4. Command Pattern
Folosit in: Order.java, BuyOrder.java, SellOrder.java, Broker.java

Motivatie:
Cerinta zicea de istoric tranzactii. Ca sa pot salva ce s-a intamplat, am transformat fiecare actiune de Buy sau Sell intr-un obiect (Comanda). Broker-ul ia comanda, o executa si o pune intr-o lista. Asa pot sa vad oricand ce s-a executat.

5. Template Method Pattern
Folosit in: TradeValidator.java, StockValidator.java, CryptoValidator.java

Motivatie:
Validarea e cam la fel si la stock si la crypto (ai bani? ai stoc?), dar difera orarul. Stock e Luni-Vineri 9-18, Crypto e non-stop. Am facut o clasa de baza cu pasii generali si am lasat subclasele sa implementeze doar verificarea de orar.

6. Strategy Pattern
Folosit in: TradingStrategy.java, DayTradingStrategy.java, LongTermStrategy.java

Motivatie:
Pt. suport de moduri diferite de trading. Day Trading are o regula cu taxa de 5% daca nu vinzi in aceeasi zi. Strategy ne lasa sa schimbam algoritmul asta de calculare a taxei din mers, fara sa modificam clasele de baza.

7. Facade Pattern
Folosit in: TradingSystemFacade.java

Motivatie:
Sistemul are multe piese: Market, User, Broker, etc. In loc sa le initializez pe toate in Main si sa fie haos, am facut o clasa Facade care le ascunde pe toate si imi da functii simple gen "buy", "sell", "login". Main-ul e mai curat.

8. Decorator Pattern
Folosit in: FeeDecorator.java, TradableDecorator.java

Motivatie:
Am vrut sa pot adauga taxe dinamice la pretul unui stock fara sa modific clasa Stock. Decorator-ul "inveleste" obiectul original si cand ceri pretul, el iti da pretul + taxa. E util daca vrem sa bagam taxe temporare sau specifice fara sa stricam logica de baza.
