# java Crawler
Progetto open source di un Crawler in java.
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
   * local-robots = serve se si vuole leggere le regole da un file robots.txt salvato in  locale oppure su un altro dominio 
   * word = cerca quella keyword su tutte le pagine visitate e nel file di log scrive se per quella pagina è stata trovata oppure no





## Uml

  <img src="/UML/diagram.png" width="500"/>  
