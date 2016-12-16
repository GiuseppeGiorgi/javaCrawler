# java Crawler
Progetto open source di un Crawler in java, per la scansione delle pagine viene usata la libreria jsoup
Questo progetto permette di scansionare un intero sito web seguendo le regole di navigazione del Robots.txt, 
per ogni pagina vengono salvati dei dati(esempio: tag h1, h2, title, description ecc..) su un file csv,
tutte le impostazioni vengono gestite tramite un file .properties

## Start
Per usare il progetto su un qualsiasi sito web basta configurare il file config/parametri.properties 
 
 * parametri.properties
   * url = url del sito da cui partire la scansione
   * followUrl = baseurl da seguire durante la scansione del sito, mettendo / scansiona qualsiasi url trova a meno che non sia disallow sul robots
   * timeout = tempo di attesa durante la connessione a una url
   * useragent = user agent con cui il crawler visita il sito (inserendo quello di google al server risulta che lo stia visitando il crawler google)
   * useragent-robots = serve per dire al robots le regole di quale user agent deve applicare
   * tag = elenco dei tag che devono essere salvati nel log
   * local-robots = serve se si vuole leggere le regole da un file robots.txt salvato in  locale oppure su un altro dominio 
   * word = cerca quella keyword su tutte le pagine visitate e nel file di log scrive se per quella pagina è stata trovata oppure no
 
* la liste dei tag da cercare deve essere separata da ; e deve avere questa forma: 
  * https://jsoup.org/cookbook/extracting-data/selector-syntax
  * per esempio : tag = title;meta[name=description];h1
  * vengono cercati i tag title, h1, per selezionare la meta description bisogna scrivere in quella forma cioè tutte le meta che hanno nome description


## Logica
il crawler segue questa logica:

1. inizia a scansionare la prima url, salvando in un file tutti i tag scelti e in una lista tutte le url che si vogliono scansionare
2. ogni volta che una url è stata scansionata viene salvata in un altra lista cosi da non essere piu scansionata
3. per ogni url presa dalla lista delle url da scansionare controlla se puo essere scansionata
  * controlla se la url è gia stata scansionata (la butta via)
  * controlla le regole del robots.txt 
4. per ogni pagina vengono salvati dei dati su dei file:
  * file report: url , url padre, tag, key-word (trovata o no)
  * file respons: url, url padre, response-code, tempo di caricamento
  * file : error: se ci sono degli errori, come code-response:404


## Uml

  <img src="/UML/diagram.png" width="500"/>  
