### **Es muss nachvollziehbar sein, wer was wann geändert hat**

###### User-Story
Als **Pflegeheimleitung** möchte ich **sehen können, wer was wann geändert/gelöscht hat**, um **bei Rückfragen bezüglich warum etwas geändert/gelöscht wurde direkt den richtigen Ansprechpartner  zu finden**.

###### Akzeptanzkriterien
1. Als Pflegeheimleitung wird ein weiterer Menüpunkt für das Log angezeigt. 
2. Das Log wird in einer Tabelle dargestellt.
3. Aus der Tabelle geht hervor, *welche Pflegekraft*, *wo genau*, *welchen Wert*, *wann geändert* hat sowie der aktuelle und der vorherige Wert.
4. Die Tabelle kann mittels einer Combobox nach Pfleger gefiltert werden.
5. Die Log-Einträge lassen sich in der Übersicht nicht bearbeiten und sind generell *read-only*.

###### Tasks
1. Tabelle für das Log in der Datenbank erstellen:
	- Die Tabelle besteht aus den Spalten `ID`, `USER`, `CHANGED_AT`, `METHOD`,`WHERE`, `WHAT`, `VALUE_BEFORE` und `VALUE_NOW`.
		- `ID` ist der Primärschlüssel der Tabelle und ist ein `Autowert`.
		- `USER` ist die `ID` des Users, der die Änderung durchgeführt hat und ist somit ein **Fremdschlüssel** der auf die Spalte `ID` in der `CARETAKER` Tabelle referenziert.
		- `CHANGED_AT` ist ein *Zeitstempel (Timestamp)* als UNIX-Zeit.
		- `METHOD` bezieht sich darauf was getan wurde, also: Eintrag angelegt, Eintrag bearbeitet, oder Eintrag gelöscht.
		- `WHERE` beinhaltet den *Ort*, an dem die Änderung durchgeführt wurde (wieder als Fremdschlüssel). 
			- Hierzu wird eine weitere Tabelle mit den verschiedenen *Orten* benötigt. Orte in der Software wären beispielsweise *Pflegekräfte*, *Patienten* oder *Behandlungen*.
		- `WHAT` ist ähnlich aufgebaut wie `WHERE`, nur hier wird die Spaltenbezeichnung gespeichert, die geändert wurde. 
			- Ebenfalls muss hier eine weitere Tabelle mit den verschiedenen *Spaltenbezeichnungen* angelegt und diese als **Fremdschlüssel** mit der Spalte `WHAT` verknüpft werden.
		- `VALUE_BEFORE` speichert den Wert, der zuvor in der nun geänderten Spalte gespeichert wurde.
		- `VALUE_NOW` speichert den Wert, zu dem der vorherige Wert geändert wurde, also den aktuellen Wert.
2. Logger Klasse erstellen:
	- Eine Modelklasse für den *Log-Eintrag* erstellen.
	- Ein *DAO* für das Log erstellen, welches das `DAOimp<>` mit der soeben erstellten Modelklasse erweitert/ableitet.
	- Die `DAOFactory` um das soeben erstellte *DAO* erweitern.
3. Log-Eintrag bei Datenbankabfragen auslösen und mit den entsprechenden Daten den Eintrag in der Datenbank erstellen.
4. Übersicht und Menüpunkt erstellen:
	- Einen View mit TableView erstellen und in diesem die Felder der Logdaten aus der Datenbank anzeigen. 
	- Eine Combobox zum Auswählen eines bestimmten Pflegers hinzufügen, um die Ergebnisse nach Pfleger filtern zu können.
	- Einen Controller für die neue View mit den Log-Einträgen erstellen.
	- `MainWindowView.fxml` aktualisieren und um einen Menüpunkt für die Log-Einträgen erweitern.
	

###### Testfälle
1. **Log-Eintrag wird in der Datenbank gespeichert**:
	- *Vorbedingung*: Der Nutzer hat im Hauptfenster der Anwendung die Option `Behandlungen` ausgewählt und es bestehen Behandlungen.
	- *Auszuführende Schritte*: Der Nutzer bearbeitet einen Wert in der Tabelle mit den Behandlungen und bestätigt dies mit Enter. 
	- *Erwartetes Ergebnis*: In der Datenbanktabelle für die Log-Einträge ist ein Eintrag mit Details zu der Änderung erstellt worden.
2. **Beim löschen von Daten wird ein Log-Eintrag in der Datenbank erstellt**:
	- *Vorbedingung*: Der Nutzer hat im Hauptfenster der Anwendung die Option `Behandlungen` ausgewählt und es bestehen Behandlungen.
	- *Auszuführende Schritte*: Der Nutzer wählt einen Wert aus der Tabelle aus und betätigt den Button `Löschen`. 
	- *Erwartetes Ergebnis*: In der Datenbanktabelle für die Log-Einträge ist ein neuer Eintrag, aus dem hervor geht, was, von wem, wann gelöscht wurde.
3. **Ändern von Patientendaten wird geloggt**:
	- *Vorbedingung*: Der Nutzer hat im Hauptfenster der Anwendung die Option `Patienten/innen` ausgewählt und es bestehen Patienten.
	- *Auszuführende Schritte*: Der Nutzer bearbeitet einen Wert in der Tabelle mit den Patienten und bestätigt dies mit Enter. 
	- *Erwartetes Ergebnis*: In der Datenbanktabelle für die Log-Einträge ist ein Eintrag mit Details zu der Änderung erstellt worden.
4. **Erstellen neuer Patienten erstellt Log-Eintrag**:
	- *Vorbedingung*: Der Nutzer hat im Hauptfenster der Anwendung die Option `Patienten/innen` ausgewählt.
	- *Auszuführende Schritte*: Der Nutzer gibt alle benötigten Daten an und klickt auf den Button `Hinzufügen`. 
	- *Erwartetes Ergebnis*: In der Datenbanktabelle für die Log-Einträge ist ein Eintrag mit Details zu dem soeben erstellten Patienten erstellt worden.
5. **Erstellen neuer Behandlungen erstellt Log-Eintrag**:
	- *Vorbedingung*: Der Nutzer hat im Hauptfenster der Anwendung die Option `Behandlungen` ausgewählt und es besteht ein Patient.
	- *Auszuführende Schritte*: 
		1. Der Nutzer wählt einen Patienten in der Combobox aus und klickt auf `neue Behandlung anlegen`. 
		2. Der Nutzer gibt alle benötigten Daten an und klickt auf den Button `Anlegen`. 
	- *Erwartetes Ergebnis*: In der Datenbanktabelle für die Log-Einträge ist ein Eintrag mit Details zu der soeben erstellten Behandlung erstellt worden.
***
