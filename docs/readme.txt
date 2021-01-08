link al corso https://app.pluralsight.com/library/courses/android-application-basics-understanding/table-of-contents

Creare una app semplice
	Installiamo Android Studio (AS) dal sito ufficiale
	Creiamo nuovo progetto
		scegliamo activity base (activity puo' essere vista come una schermata dell'app, una finestra)
		impostoamp il nome del progetto, il package base, linguaggio (java/kotlin), livello minimo di API
		(Android Studio scarica SDK necessario se non e' ancora presente sul PC)
		confermiamo la creazione del progetto
	Struttura progetto
		a sx di AS vediamo la cartella del nostro progetto 
		(assicuriamoci che la visualizzazione = Android)
		se apriamo un layout (file *.xml) vedremmo una preview a dx, due schermate, la prima e' la preview vera e propria, la seconda e' la visualizzazione di componenti usati
		finestra "Palette" contiene i componenti (aka widget, controlli) che possiamo usare nel nostro layout 
		finestra "Component Tree" ci fa vedere la struttura del layout, in che modo sono posizionate i componenti 
		il layout viene salvato in un file *.xml (vedi i tab CODE | SPLIT | DESIGN)
		possiamo visualizzare contemporaneamente Disegna e Blueprint o uno di due
	Modifica al layout dell'app
		ogni controllo ha un insieme di attributi/proprieta', vedi la finestra "Attributes" (e' divisa in 4 gruppi:
			- Declared attributes
			- Layout 
			- Common attributes
			- All attributes (usata raramente, di solito attributi che modifichiamo si trovano gia' negli altri gruppi)
		ogni controllo ha l'attributo ID, deve essere impostato per poter manipolare il controllo a livello di codice 
		modifichiamo layout_width = "match_constraint" (0dp) del testo nella prima schermata, ora il controllo TextView occupa tutta la larghezza della finestra 
		impostiamo il testo = "1", impostiamo textAppearance = "*Large" e textAllignment = "Center"
		(abbiamo apportato modifiche basiliari al nostro layout)
	Modifica al codice Java
		(aggiungiamo il codice che permette di raddoppiare il numero visualizzato nella prima schermata)
		il collegamento tra Activity e il Layout avviene nella riga 
			setContentView(R.layout.activity_main);
		nel nostro caso il layout di MainActivity e' la base di tutto, importa e usa il layout specificato nel file "content_main.xml" che e' gia' piu' specifico per la nostra app 
		modifichiamo il listener "fab.setOnClickListener()" aggiungendo la logica che consente di recuperare il numero stampato nella prima schermata e di moltiplicarlo x 2
		NOTA: il pattern comune e' di impostare ID usando "_", invece, per i nomi di variabili usiamo camelCase
		aggiungiamo il codice che ottiene il rigerimento al controllo contenente il numero, converte il numero in un intero, usa il metodo statico per moltiplicarlo x 2, aggiorna il 
			contenuto della TextView impostando il nuovo valore
			il gestore del click contiene anche il codice "Snackbar.make(..." - modifichiamo il testo per dare info all'utente sul cambiamento del numero 
			NOTA: snackbar e' la barra che appare in basso allo schermo per dare una info in piu' all'utente sulla azione compiuta
		lanciamo la nostra app 
			lanciamo app all'interno di emulatore (aka AVD, Virtual Device)
			se non abbiamo un virtual device configurato, lo aggiungiamo 
				NOTA: Pixel rappresenta il profilo hardware 
				se non abbiamo l'immagine del device desiderato in locale la scarichiamo cliccando DOWNLOAD
				il nome di default che viene associato ad AVD nuovo contiene il profilo hardware (ex. Pixel) e la versione di API (es. API 24)
		una volta che abbiamo AVD configurato lanciamo la nostra app 
Design and planning our app
	(in questa sezione vediamo l'app che andremmo a costruire durante il corso)
	l'app che costruiamo e' una note keeper app
		- visualizziamo la lista di note
		- modifica di una singola nota
		- creazione di una nota nuova 
		una nota ha il titolo, corpo, e il corso associato (nel nostro caso l'app traccia le note dei corsi di formazione che seguiamo)
	funzionalita' dell'app a lungo termine (altre serie del corso)
		- test automatici sia della logica che dell'UI/UX
		- card style lists
		- slide-out drawer navigation
		- tracciare i moduli (corsi) completati
		- branding-based UI theme 
		- device and language adaptability
		- support for users with accessibility needs 
		- user customizable behaviours 
		- SQLite per salvare i nostri dati 
		- rendere le note accessibili alle altre app
		- display reminder notifications (notifiche schedulate, utente apre l'app con la nota partendo dalla notifica)
		- lettura e scrittura dei dati in background
		- visualizzare le info delle note sullo schermo home
	bozza di design della nostra app
		vedi le slide o il video del corso per dettagli 
		abbiamo la view per la lista di note e la view per il dettaglio della nota
		dal dettaglio della nota, se torniamo indietro con il pulsante standard di android, la nota viene aggiornata automaticamente (NOTA: e' un comportamento standard su android)
			se vogliamo dare la possibilita' all'utente di tornare indietro (alla lista di note) senza apportare la modifica alla nota, mostriamo un pulsate dedicato nella barra in alto, per es. freccina a sx
		dal dettaglio della nota possiamo passare ad una nota successiva cliccando sempre un pulsante opportuno nella barra in alto 
	creiamo il nuovo progetto
		NOTA: android ci aiuta nella scelta di minimum SDK, sia nel wizard di creazione nuovo progetto che online (vedi https://developer.android.com/about/dashboards)
Activities and Activity Layout Interaction
	Che cos'e' una Activity
		Activity (dalla doc ufficiale) is a single, focused thing that the user can doc
		E' il "posto" dove visualizzare la UI
		fornisce le funzionalita' di una finestra 
		UI viene costruita usando le classi basi della View
	Activity' e' qualcosa di piu' che una finestra
		ha un ciclo di vita 
		il ciclo di vita viene rappresentato dalla serie di metodi chiamati 
		il metodo principale e' onCreate() chiamato quando viene creata l'Activity e dove possiamo eseguire la nostra inizializzazione
	Activity UI
		la cosa fondamentale e' la classe View 
			View - basic building block of UI
			consente disegnare la UI e gestire gli eventi
			ci sono N classi specifici (vedi sotto)
		ViewGroup 
			e' una View speciale che contiene altre View 
		Layout
			e' una ViewGroup speciale invisibile 
			gestisce il posizionamento delle View 
			ci sono N classi specifici per diversi layout (vedi sotto)
	Layout classes
		la UI di una Activity deve essere responsive 
			per poter scalare su device differenti
			UI deve addattarsi
		le classi di layout firnoscono la flessibilita' sul posizionamento 
			allineamento View figli
			(NOTA: figli possano includere altri figli)
		un comportamento specifico che riguarda il riposizionamento delle View dipende dalla classe di layout
		classi di layout
			- FrameLayout
				e' un area blocco
				tipicamente ha solo un figlio diretto
				usato per creare una area ben precisa 
			- ScrollView
				fornisce le funzionalita' di scrolling, utile quando abbiamo elementi che ci stanno su tutto lo schermo 
			- LinearLayout
				posizionamento orizzontale/verticale equo di N elementi
				lo spazio occupato da ogni elemento puo' essere distribuito in base al peso (aka importanza / necessita')
			- RelativeLayout
				posizionamento relativo
				(per es. quando una view viene posizionata in modo relativo ad un'altra view)
				(oppure relativa al parent, per esempio centrata, spostata in altro / in basso / a sx / a dx)
	Utilizzo di RelativeLayout
		si trova nella sezione Legacy della finestra "Palette"
			e' stato usato molto spesso nelle app android sviluppate prima del 2017
			si trova nella sezione Legacy perche' e' stato sostituito da un layout piu' potente 
			ma serve per manutenere le app sviluppate prima 
	ConstraintLayout class
		layout visti prima sono quelli di base previsti dalle prime versioni di Android 
		nel corso di tempo la parte di UI si e' complicata parecchio con esigenza di avere layout innestati con N livelli di profondita'
		note a livello di runtime
			- annidamento profondo/complesso impatta sulle prestazioni
		note a livello di design
			- UI puo' essere molto complessa che rende l'utilizzo di designer non praticabile
			- spesso si modificano i file *.xml di layout per ottenere l'effetto desiderato (perdita di produttivita' nella fase di creazione UI)
		nel 2017 google rilascia ConstraintLayout
			spesso e' unica classe di layout che serve anche per creare UI complessa
			forte supporto da parte di AS per il design 
			si usa il termine constraint per impostare le proprieta' di vari elementi di UI (es. posizionamento e dimensioni relativi; posizionamento e dimensioni ratio-based; 
				raggruppamento della distribuzione di dimensioni/posizionamento - aka chains/catena)
				possiamo avere per esempio 3 View che occupano lo stesso spazio in orizzontale 
				il concetto di peso per definire le relazioni
				concetto di guideline - view controls visibili a runtime per consentire la gestione di posizionamento 
			quando si usa ConstraintLayout 
				- ogni View di solito ha sempre i constraint orizzontali e verticali (se non settati, la view ha coordinate 0,0 - in alto a sx)
				- possiamo settare tutti constraint dal designer 
				- possiamo settare delle dimensioni fissi 
		demo - utilizzo di ConstraintLayout
			vedi video 
			aggiungiamo lo spinner al ConstraintLayout, servira' per far scegliere all'utente il corso della nota 
			impostiamo i constraint TOP, LEFT e RIGHT dalla finestra "Attributes", sezione "Layout", cliccando vari (+)
			aggiungiamo MultilineText sotto Spinner, impostiamo i constraint con il riferimento allo Spinner, vedi video x dettagli ....
			recap: vari elementi della UI sono posizionate uno relativo all'altro, spinner invece e' posizionato rispetto il contenitore principale che e' ConstraintLayout
	Relazione tra Activity e Layout
		possiamo creare l'UI per la nostra Activity completamente da Java (istanziare le View, creare le relazioni, posizionare le View in base ai constraint etc..)
		ma di solito viene usato il layout file (*.xml) come e' stato fatto prima
		layout file descrive la gerarchia delle View 
		non c'e' nessuna associazione implicita del layout all'activity
			una activity specifica esplicitamente il layout che vuole usare usando il metodo setContentView
			si ottiene il riferimento alla View usando il metodo findViewById
		per ottenere ID di layout o View viene usata la classe generata automaticamente - classe R (ambiente di android che genera questa classe)
			contiene classi innestate 
			per esempio classe R.layout e R.id
			la classe R.layout contiene una prop statica per ogni risorsa di tipo layout
				e' un intero che identifica univocamente la risorsa e consente ad android di caricarla a runtime 
			idem per la classe R.id
				contiene una costante per ogni View, il nome della costante e' l'ID che abbiamo dato nel momento di design, il valore e' un intero che identifica univocamente la View 
		(eliminiamo il pulsante fab - Floating Action Button dal relativo layout, non ci serve)
	Inizializzazione spinner con i dati
		spinner (aka tendina con dei valori, dropdown list)
		ha un layout dedicato per la voce selezionata e per ogni voce della lista 
		per inizializzare lo spinner dobbiamo 
			1. recuperare i dati necessario
			2. settare il layout per la voce selezionata 
			3. settare il layout per ogni voce selezionabile 
		ed e' qui che entra in gioco la classe Adapter (classe responsabile di tre step di prima)
		ci sono adapter che caricano i dati dalla memoria, adapter che recuperano i dati dal SQL 
		usiamo le classi model presenti nell'archivio del corso (CourseInfo come model per oggetto Corso, DataManager e' il manager che ritorna dati mock)
		a livello di activity creiamo ArrayAdapter e passiamo nel costruttore il riferimento all'activity corrente, il layout per la voce selezionata - usiamo layout gia' previsto da android, e passiamo array di oggetti CourseInfo
			settiamo il layout per la voce selezionabile usando il metodo setDropDownViewResource di adapter (anche qui usiamo il layout gia' esistente su android)
		associato adapter appena creato alla view di spinner 
		lanciamo l'app su AVD, vediamo la tendina con 4 voci
Lavorare con activities
	Activity interaction
		per capire iterazione tra activity e' importante comprendere il seguente 
			- android e' una piattaforma orientata a componenti 
			- ci sono N componenti in android, di cui Activity e' il componente piu' famigliare
			- Activity si differenzia da altri componenti 
				una Activity NON puo' creare direttamente un'altra activity
				si appoggia al concetto di Intent per quanto riguarda interazione 
		il concetto di Intent e' un concetto fondamentale per la piattaforma android 
			Intent e' praticamente il modo per passare da una Activity all'altra passando i dati 
			es: la finestra android che contiene la lista di app che possiamo lanciare -> quando clicchiamo sull'icona di una app android crea un Intent gestito -> android verifica se 
				esiste gia' il processo relativo all'activity di Intent, se no, lo crea e dopo crea activity richiesta
		per creare una nuova Activity all'interno della nostra app dobbiamo
			- creare un nuovo Intent specificando la classe della nuova Activity 
			- chiamare il metodo startActivity passando intent 
			in questo modo viene eseguita Activity specificata nell'Intent 
		nella nostra app abbiamo Activity della lista di note che crea l'Activity di dettaglio nota 
	demo - creazione activity dedicata alla lista di note 
		creiamo una Activity vuota con il suo layout (vuoto)
		aggiungiamo ListView della sezione Legacy (questa ListView rimane la strada piu' semplice per creare la lista di item)
			impostiamo constraint di TOP | RIGHT | BOTTOM | LEFT (per es. a 8dp)
			impostiamo layout_width e layout_height a 0dp (aka match_constraint)
			in questo modo abbiamo la ListView allineata al contenitore principale 
		usiamo DataManager visto prima per popolare la lista di note con dati mock
			anche in questo caso viene usato un ArrayAdapter (anche qui specifichiamo il contesto che e' la nostra Activity, layout di ogni singolo item, la lista di note)
		gestiamo il click sul singolo item 
			ListView prevede il metodo setOnItemClickListener che ci consente di implementare una classe anonima contenente il metodo onItemClick
			aggiungiamo il codice di creazione nuovo Intent e la sua invocazione tramite il metodo startActivity()
			NOTA: nel costruttore di Intent devo passare il riferimento al contesto (e' la nostra Activity), siamo all'interno di una classe astratta e non possiamo usare direttamente this, 
				usiamo NoteListActivity.this!)
		(in questo momento abbiamo due activity configurate per essere lanciate di default - android creera' due icone nella finestra di app - fixiamo questa situazione modificando AndroidManifest.xml)
		AndroidManifest.xml contiene la descrizione dell'app e la lista di componenti usati
			eliminiamo la sezione intent-filter dall'activity NoteActivity (e' stata impostata come activity MAIN e LAUNCHER quando abbiamo creato il progetto)
			impostiamo attributo android:label per activity NoteListActivity uguale al nome dell'app (la label dell'activity main viene usata nel nome dell'icone di app sul dispositivo)
	Descrizione dell'operazione con Intent
		Intent descrive una operazione desiderata 
		il target desiderato e' l'activity 
		spesso abbiamo bisogno di passare i dati da una activity all'altra (per esempio quando clichiamo su un item della lista e vogliamo aprire il dettaglio, dobbiamo passare l'ID/dettagli dell'item cliccato)
		qui entra in gioco il concetto di Intent Extras
			e' name value pairs (mappa di chiavi valori)
			extras sono passati all'Intent usando il metodo putExtra()
		activity target accede all'extras di intent usando il metodo getIntent() x recuperare Intent e dopo Intent.getXXExtra() per ottenere extras, 
			XX vanno sostituiti con il tipo di extra che stiamo recuperando (meccanismo di java overloading)
		NOTA: applicazione android gira all'interno di un processo
			un Intent avvia la nostra app creando il processo e l'activity principale
			la nostra app puo' passare il controllo ad un'altra app sempre usando un Itent 
		Intent che passa da un processo all'altro deve essere "cross-process friendly"
			stessa regola vale per extras, c'e' un limite da rispettare
		i tipi di extra supportati
			- tipi primitivi e String
			- array di tipi supportato 
			- qualche variazione di ArrayList 
			- un paio di tipi speciali 
			NOTA: maggior parte di tipi riferimento non sono supportati - richiede una gestione specifica (vedi sotto)
	Tipi riferimento per Intent extras
		i tipi riferimento per essere cross-process devono essere "flattened" (appiattiti)
			(aka wire friendly, convertibili in bytes)
		praticamente l'oggetto viene convertito in un stream di byte per essere passato all'altro processo 
		uno di modi per raggiungere questo e' usare serializzazione di java MA non e' consigliato - il processo e' abbastanza pesante a runtime 
		android fornisce l'alternativa - Parcelable API (parcelable - impacchettabile)
			e' molto piu' efficiente che la serializzazione
			richiede una implementazione esplicita (la serializzazione di java invece no, e' sufficiente implementare la classe Serializable)
			per usare Parcelable API dobbiamo implementare interfaccia Parcelable
				metodi da implementare
					- describeContents() - per indicare un comportamento particolare, spesso non viene usato, ritorniamo 0
					- writeToParcel() - riceve l'istanza di Parcel, che ha vari metodo di tipo writeXX(), per es. writeString(), writeTypedList()
				dobbiamo prevedere il campo "public static final CREATOR" di tipo Parcelable.Creator
					e' un interfaccia per poter costruire il nostro oggetto partendo da tipo Parcelable
					implementiamo createFromParcel()
					usiamo i metodi Parcel.readXX per leggere il contenuto 
					implementiamo anche il metodo newArray() - creazione array del nostro tipo con dimensione specificata
	demo - rendiamo NoteInfo Parcelable
		rendiamo la classe NoteInfo parcelable
		NOTA: per rendere una classe Parcelable tutte le sue proprieta' devono essere Parcelable
		NOTA: ordine usato per scrivere nel Parcel deve corrispondere all'ordine usato durante la lettura 
	demo - passiamo NoteInfo selezionata all'activity NoteActivity
		per recuperare NoteInfo selezionata usiamo la View di ListView e il metodo getItemAtPosition(), eseguiamo il cast al nostro tipo 
		ora possiamo passare NoteInfo come Extra all'activity NoteActivity
		ricordiamo che extras e una mappa di chiavi valori 
			per salvare un oggetto usiamo la chiave che possiamo specificare a livello di Activity che la andra' a leggere (es. NoteActivity) - e' una costante statica 
			la chiave deve essere univoca tra tutti i caller che possano chiamare l'activity, vedi demo, abbiamo aggiungo il nome del package come prefisso
Approfondimenti ulteriori sull'interazione di Activity 
	come e' stato riportato anche prima, android e' una piattaforma component based
	componenti sono in esecuzione all'interno di un processo 
	il ciclo di vita del processo e' legato al ciclo di vita del componente 
		creato quando arriva la richiesta di creazione del primo componente 
		termina alla chiusura (uscita) dell'ultimo componente 
		una app e' in esecuzione all'interno di un processo dedicato 
		a livello della nostra app abbiamo due activity che sono in esecuzione all'interno dello stesso processo, quindi hanno accesso allo stesso DataManager
			questo ci consente di non passare una NoteInfo per intero ma solo l'ID (indice), non siamo costretti ad usare Parcelable, e iterazione diventa piu' light 
			(demo - apportiamo la modifica al nostro codice per passare solo l'indice della nota nella lista)
		recap: sapendo che le activity sono in esecuzione nello stesso processo possiamo sfruttara il nostro DataManager (che e' un singleton)
	Late-binding components (activities)
		fino ad ora abbiamo usato un Intent esplicito, specificando l'activity target esplicitamente 
		ma esistono anche cosi detti Intent impliciti, target viene determinato in modo implicito basandosi su un set di caratteristiche di activity
		intent implicito e' noto come late binding - activity target viene determinata a runtime 
			noi scriviamo le caratteristiche e il sistema trova il match migliore 
			e' usato spesso nell'apertura di app esterni (es. mail client) 
		se il sistema trova piu' match viene chiesto all'utente di fare una scelta precisa (per es. se aprire il client gmail o outlook)
		intent implicito disacoppia sender e receiver 
		ci sono 4 caratteristiche fondamentali di un Intent implicito
			1. Action - e' una stringa che contiene la action, ci sono N costanti standard che tutti possano usare, la nostra app puo' definire costanti proprie 
				es. Intent.ACTION_VIEW 
				la action viene impostata nel costruttore di Intent 
				la action e' unica caratteristica richiesta (obbligatoria) nel caso di Intent implicito 
			2. Category - fornisce una qualifica estesa (extended qualification)
				di solito non e' sender che la specifica ma il receiver 
			3. Data - fornisce URI dei dati su cui agire 
				es. https://pluralsight.com 
				per settare viene usato il metodo Intent.setData 
			4. Mime type - il tipo di dati identificati dall'URI del punto 3
				possiamo usare mime type comuni o specifici all'applicazione 
				es. text/html 
				viene settato chiamando Intent.setType
			NOTA: puo' capitare di avere caratteristica Data senza Mime type e viceversa 
				  quando dobbiamo impostare entrambe le caratteristiche possiamo usare il metodo setDataAndType
				  usando separatamente setData e setType cancella quella precedente
			fare riferimento a https://developer.android.com/guide/components/intents-common.html per una descrizione dettagliata di caratteristiche di intent impliciti 
	Intent impliciti 
		vediamo esempio aggiungendo alla nostra app la possibilita' di inviare una nota tramite email 
			in questo caso sara' creato un nuovo processo per la mail app all'interno del quale verra' lanciata activity individuata
		ogni app ha AndroidManifest dove sono specificati i componenti dell'app
			ogni componente (es. activity) puo' avere la sezione <intent-filter> usata per poter individuare in modo implicito l'activity da caricare nel momento di un Intent implicito 
			sistema operativo android tiene traccia di tutti i file AndroidManifest.xml ed e' in grado di capire l'app da lanciare e la prima activity da caricare 
		quando la nostra app lancia un Intent implicito il controllo passa al SO android che e' in grado di capire l'app e activity target di questo Intent 
	demo - dal dettaglio della nota, cliccando nel menu in alto, creiamo un intent implicito con la action Intent.ACTION_SEND e type "message/rfc2822" (standard per messaggi email)
		in piu' specifichiamo dei parametri extra per il subject e body (vedi codice) 
		viene aperto il gmail con il sender (account di default di gmail), subject (passato come extra) e body precompilato (passato come extra)
	Activity con risultato 
		es. camera (ritorna thumbnail dell'immagine), contact (ritorna il contatto selezionato), tante altre 
		al posto si chiamare startActivity chiamiamo startActivityForResult 
			ha due parametri
				1. Intent (come nel primo caso, dobbiamo passare Intent)
				2. App defined integer identifier 
					per differenziare il risultato all'interno della nostra app
		il risultato di una activity viene ricevuto con la chiamata al metodo dell'activity sender 
			dobbiamo fare override del metodo onActivityResult
			questo metodo ha 3 parametri 
				requestCode - integer identifier passato nella chiamata a startActivityForResult
				resultCode - il codice del risultato (es. OK se tutto e' andato a buon fine)
				data: Intent - contiene il risultato dell'activity 
		come esempio vediamo l'utilizzo della camera che prevede questo iter:
			- viene visualizzata l'UI di camera 
			- salvataggio dell'immagine full quality in un file 
			- ritorno di thumbnail dell'immagine come risultato (serve per farla vedere all'utente per una conferma ulteriore)
		per avviare activity della camera abbiamo bisogno di
			- Intent con action della camera 
				la action da usare e' MediaStore.ACTION_IMAGE_CAPTURE 
				in questo caso e' sufficiente specificare solo la action di 4 caratteristiche
			- Extra 
				MediaStore.EXTRA_OUTPUT per passare il file (URI) in cui vogliamo salvare l'immagine con massima qualita' 
		per vedere l'esempio di codice java fare riferimento a https://developer.android.com/guide/components/intents-common.html#Camera
	Application experience and Tasks
		di solito la navigazione avviene tra le activity della nostra app, ma puo' succedere che vengono invocate anche le ativity delle app esterne
			in questo caso android deve essere in grado di gestire il flusso di navigazione e il passaggio tra diverse app 
			lo fa usando il concetto di un Task 
		Task (def. ufficiale) is a collection of activities that users interact with when performing a certain job
		Task, ad alto livello, e' gestito come uno Stack
			quando apriamo le activity, ogni activity viene aggiunta allo stack (task)
			quando torniamo indietro, activity viene rimossa dallo stack, e di conseguenza distrutta
			(vedi la presentazione del corso per una visualizzazione grafica)
		in che modo android gestisce lo stato persistente dell'app
			viene addottato il modello edit-in-place 
			le modifiche sono salvate senza una azione dedicata
			di norma il salvataggio avviene guando abbandoniamo la nostra activity (es. cliccando il pulsante back)
			il metodo (aka hook) da implementare e' onPause()
			se dobbiamo creare un nuovo item (es. nuova nota), lo possiamo creare inizialmente (vuoto) nel metodo onCreate() dell'activity aperta
				nel metodo onPause() riempiamo oggetto vuoto con i dati raccolti nella UI 
Activity lifecycle
	distruzione di una activity
		avviene quando utente preme back 
		programmaticamente viene chiamato il metodo finish()
		richiesto dal sistema  (system initiated)
			di solito avviene nel momento quando il sistema ha bisogno di risorse libere (reclaim resources)
			quando una activity rimane in background per un periodo di tempo lungo 
			quando sistema e' sovracarico e inizia a liberare delle risorse occupate (una activity attiva richiede piu' risorse, possano essere killate delle activity in background)
		NOTA: quando creiamo una activity, viene inserito il suo riferimento al BACK STACK (viesto prima, lo stack del task)
			sistema puo' eliminare una activity di cui abbiamo il riferimento nel BACK STACK ma che non e' attiva in un determinato momento 
			-> quando utente clicca BACK, sistema si accorge della presenza di item relativo all'activity distrutta nello stack e la ricrea da zero 
			-> dobbiamo gestire il codice dell'activity correttamente in modo da rendere questo passaggio trasparente all'utente finale, non si deve accorgersi nel momento di ritorna ad una activity eliminata..
		NOTA: la casistica peggiore che puo' capitare e' quando apriamo un'app esterna e nel frattempo tutte le activity, e di conseguenza tutto il processo delle nostra app viene eliminato dal sistema 
			-> in questo caso il sistema cmq tiene traccia di tutte le activity del nostro Task
			-> al ritorno indietro dall'app esterna, creo un nuovo processo per la nostra app e crea ultima activity presente nello stack 
			-> per es., nel caso della nostra app, viene creata NoteActivity
			-> dobbiamo tenere conto di questo comportamento e programmare la nostra activity correttamente, in modo che l'app si apre in uno stato consistente
	metodi del ciclo di vita di una activity
		il ciclo di vita (lifecycle) e' diviso in tre tempi di vita (lifetime)
			- total lifetime 
			- visible lifetime (activity e' visibile)
			- foreground lifetime (activity e' al primo piano, iterazioni con utente)
		metodi di lifecycle
			rappresentano l'inizio e fine di ogni lifetime 
			metodi addizionali per le transizioni 
		principali metodi che rappresentato l'inizio e fine di un lifetime
			(Activity Launched) 
				-> onCreate(), activity creata ma non ancora visibile 			[total lifetime]
				-> onStart(), activity diventa visibile 						[visible lifetime]
				-> onResume(), activity e' disponibile x iterazioni con lo user	[foreground lifetime]
			(Activity is running)
			NOTA: e' importante comprendere che android distingue una activity visibile da una in foreground 
			  quando apriamo una nuova activity e quella precedente rimane visibile ma in background, e' visibile ma lo user non puo' interagire con essa 
				-> onPause(), activity e' visibile ma non e' piu' accessibile da parte dell'utente
				-> onStop(), quando activity viene coperta completamente da un'altra activity
				NOTA: prima di eseguire onStop() viene sempre eseguito anche onPause(), idem per onStart() eseguito prima di onResume()
				-> onDestroy(), torniamo indietro (back button clicked)
			(Activity shutdown)
		metodi addizionali per transizioni
			(Activity diventa di nuovo visibile)
				-> onRestart(), chiamato prima di onStart() -> onResume()
		di solito facciamo override dei metodi che servono
	demo - fix, app crasha quando inseriamo nuova nota e torniamo indietro 
		in questo caso abbiamo aggiunto un nuovo item alla lista di note ma il nostro adapter non e' stato notificato 
		errore "... ArrayAdapter ...."
		implementiamo il metodo onResume in NoteListActivity aggiungendo la riga "adapterNotes.notifyDataSetChanged();"
	NOTA: nel caso della nostra app, quando apriamo gmail per inviare la nota, lo stato della nota viene salvato
		Activity corrente passa in secondo piano, il metodo onPause() viene chiamato 
	Activity State Management
		ricordiamo che quando apriamo un app terza, le activity della nostra app passano in secondo piano (nascoste) e possano essere distrutte dal sistema per 
			motivi descritti sopra (es. richiesta risorse per actovoty attive)
		quando ritorniamo indietro, dobbiamo essere in grado di ripristinare lo stato di activity aperta prima 
		per questo motivo Activity fornisce un meccanismo di gestione dello stato 
			- possiamo salvare lo stato prima che activity viene distrutta 
			- possiamo ripristinare lo stato alla sua successiva creazione (lo stato salvato viene passato in input)
		salvataggio dello stato
			- metodo chiamato onSaveInstanceState
			- scriviamo lo stato nel Bundle passato 
				(il sistema e' in carico di gestire questo Bundle)
		ripristino dello stato
			- gestito nel metodo onCreate()
			- riceviamo il Bundle salvato nel momento di restore 
		il bundle viene sempre passato all'activity, ma e' null nel momento di prima creazione 
		activity ha sempre accesso all'intent che l'ha creata 
		NOTA: quando ruotiamo il device, activity che vediamo viene distrutta e ricreata di nuovo sul nuovo orientamento 
			anche per questo motivo dobbiamo gestire lo stato dell'activity 
			la scrittura in uno store persistente e' pesante -> serve una soluzione migliore per mantenere lo stato durante i cambiamenti della configurazione 
		abbiamo la classe ViewModel che ci permette di salvare lo stato nel processo dell'app 
			lo stato viene salvato separatamente dall'activity stessa (anche perche' poi viene distrutta)
			dobbiamo implementare una classe dedicata estendendo ViewModel e aggiungere le proprieta' e metodi dedicati allo stato della nostra Activity 
		per gestire ViewModel usiamo la classe ViewModelProvider che incapsula la logica di gestione dell'istanza di ViewModel 
			iter: nel primo accesso al ViewModel tramite ViewModelProvider il ViewModel viene creato -> se activity viene distrutta e ricreata di nuovo, il prossimo accesso 
				a ViewModel usando ViewModelProvider ritornera' ViewModel esistente 
	demo -  testing activity state savings
		emulatore di android fornisce la possibilita' di distruggere una activity ogni volta che viene portata in secondo piano (per es. apriamo una nuova activity)
		sull'emulatore Settings -> System -> About emulated device -> clicchiamo N volte la voce "Build number" finche non vediamo "you are now a developer"
			-> torniamo alla sezione System e vediamo la voce "Developer options" -> troviamo area "Apps" e attiviamo l'opzioni "Don't keep activities"
			(in questo modo ogni activity che viene lasciata dall'utente viene distrutta)
		nostra app: activity NoteActivity viene creata da zero se torniamo indietro dall'app di gmail -> il metodo onCreata() salva nelle proprieta' di istanza i nuovi valori 
			(modifichiamo l'app in modo da gestire questo comportamento)
			NOTA: usiamo ViewModel per salvare i dati che non potremmo recuperare dall'Intent (es. proprieta' original*)
		creiamo classe NoteActivityViewModel estendendo ViewModel
		nel metodo onCreate() di NoteActivity creiamo ViewModelProvider 
			ViewModelProvider viewModelProvider = new ViewModelProvider(
				getViewModelStore(),														// ViewModelProvider deve sapere dove salvare ViewModel, ricordiamo che e' salvato fuori dall'Activity ma Activity 
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())		// creiamo la factory per ViewModel passando le info della nostra app 
			);
			queste righe di codice per creare ViewModelProvider sono semper le stesse, aka boilerplate code 
			creiamo ViewModel 
				viewModel = viewModelProvider.get(NoteActivityViewModel.class);
			salviamo le proprieta' necessarie nel viewModel 
		NOTA: con ViewModel abbiamo gestito il ripristino dello stato nel momento di ricreazione dell'Activity distrutta dopo una variazione di configurazione (es. ruotamento del device)
		NOTA: ViewModel non e' sufficiente quando passiamo ad una nuova activity e quella precedente viene distrutta per motivi noti 
			-> in questo caso insieme ad activity viene distrutto anche ViewModel 
	demo - salvataggio dello stato di activity
		dobbiamo implementare il metodo onSaveInstanceState()
		(vedi il codice - se il bundle non e' null, salviamo le proprieta' del ViewModel nel bundle, il sistema si occupa per salvare questi anche se activity e viewmodel sono 
			distrutti in un secondo momento)
		per fare il restore dello stato implementiamo il metodo dedicato a livello di NoteActivityViewModel, vedi il codice (recuperiamo i valori dal Bundle usato prima)
			questo metodo viene chiamato in onCreate() di NoteActivity quando il bundle in ingresso != null
			in piu' aggiungiamo un flag a livello di ViewModel che ci fa capire se ViewModel e' nuova o no 
				se e' stata appena creata e abbiamo il bundle in input != null, siamo nella casistica di creazione activity distrutta prima 
				se siamo nel caso di cambiamento di configurazione, ViewModel viene gia' ricreato con i dati di prima e possiamo evitare il restore dal bundle!!!
	recap
		usiamo cmq la classe ViewModel per salvare le proprieta' dello stato di una activity
		implementiamo onSaveInstanceState per salvare lo stato nel bundle 
		
		