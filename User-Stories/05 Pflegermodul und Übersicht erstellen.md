### **Pflegermodul und Übersicht erstellen**

###### User-Story
Als **Pflegeheimleitung** möchte ich **Daten der Pfleger verwalten können**, um **einen Überblick des Pflegepersonals beizubehalten.**

###### Akzeptanzkriterien
1. Die persönlichen Daten eines Pflegers bestehen aus: ID, Vor- und Nachname sowie Telefonnummer.
2. Alle Pfleger werden mit ihren vollständigen Daten tabellarisch dargestellt.
3. Die Daten eines Pflegers können direkt in der Tabelle geändert werden.
4. Es können neue Pfleger hinzugefügt werden.
5. Alle Felder zum Anlegen eines neuen Pflegers sind verpflichtend (außer ID).

###### Tasks
1. Modelklasse Pfleger `Caretaker`  erstellen: abgeleitet von Person
2. `CaretakerDAO`-Klasse implementieren, abgeleitet von `DAOimp` und CRUD Methoden implementieren
3. In der Datenbank eine Tabelle `CARETAKER` mit den Feldern für die Speicherung einzelner Pfleger erstellen
4. Eine neue View mit TableView erstellen die die in Akzeptanzkriterien 1.) beschriebenen Daten der Pfleger anzeigen kann
5. In der View werden unter der Tabelle Felder für die Daten eines Pflegers, sowie ein Button zum Anlegen eines neuen Pflegers angezeigt
6. Einen Controller erstellen und mit der View verknüpfen, der Methoden zum anzeigen, ändern und hinzufügen neuer Pfleger bereitstellt 

###### Testfälle
1. **Alle Pfleger anmelden**:
    - *Vorbedingung*: Der Nutzer hat im Hauptfenster der Anwendung die Option Pfleger/innen ausgewählt.
    - *Auszuführende Schritte*:  /
    - *Erwartetes Ergebnis*: Es werden alle Pfleger, die in der Datnebank gespeichert sind mit ID, Vor- und Nachname sowie ihrer Telefonnummer in einer Tabelle angezeigt.

2. **Pflegerdaten ändern**:
    - *Vorbedingung*: Der Nutzer hat im Hauptfenster der Anwendung die Option Pfleger/innen ausgewählt.
    - *Auszuführende Schritte*:
      1. Der User wählt per Doppelklick ein Feld (mit Pfleger) in der Tabelle aus.
      2. Der User ändert den Inhalt des Feldes.
      3. Der User bestätigt die Änderung mit der Enter-Taste.
    - *Erwartetes Ergebnis*:
      1. Das geänderte Feld wird in der Tabelle angezeigt.
      2. Das geänderte Feld wurde in der Datenbank gespeichert.
   
3. **Pfleger anlegen**:
    - *Vorbedingung*: Der Nutzer hat im Hauptfenster der Anwendung die Option Pfleger/innen ausgewählt.
    - *Auszuführende Schritte*:  
      1. Der User hat den Vor- und Nachname sowie die Telefonnummer in die dafür vorgesehenen Textfelder eingegeben.
      2. Der User drückt den Button `Hinzufügen`.
    - *Erwartetes Ergebnis*: 
      1. Der neue Pfleger wird mit den eingetragenen Daten sowie einer automatisch generierten ID als unterster Eintrag in der Tabelle angezeigt.
      2. Der neue Pfleger wurde in der Datenbank gespeichert.
***



