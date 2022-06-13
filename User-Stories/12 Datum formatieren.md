### **Datum nicht in "Minus-Schreibweise"**

###### User-Story
Als **Pfleger** möchte ich **das Datum in einer gewohnten Formatierung angeben und lesen können**, damit **beim Anlegen neuer Einträge keine Fehler aufgrund des ungewohnten Datumsformat auftreten**.

###### Akzeptanzkriterien
1. Die Daten werden innerhalb der Software in dem Format `DD.MM.YYYY` *(20.04.2019)* angezeigt und angegeben.

###### Tasks
1. In `utils/DateConverter.java` eine `toString()`-Methode hinzufügen. Diese nutzt die durch `LocalDate` zur Verfügung gestellte Methode `public String format(DateTimeFormatter formatter)`  um das Datum in dieses Format zu konvertieren. Hierzu muss ein Objekt vom Typ `DateTimeFormatter` erstellt und entsprechend konfiguriert werden.
2. Die Methode `getDateOfBirth()` in `model/Patient.java` sowie die `getDate()` Methode in `model/Treatment.java` müssen angepasst werden, sodass das Datum mithilfe des `DateConverters` in das korrekte Format konvertiert wird.
3. Die Methode `convertStringToLocalDate(String date)` in `utils/DateConverter.java` muss angepasst werden, damit diese mit dem neuen Format des Datums umgehen kann. 

###### Testfälle
1. **Das Geburtsdatum wird im gewünschten Format angezeigt**:
	- *Vorbedingung*: Der Nutzer hat im Hauptfenster der Anwendung die Option `Patient/innen` ausgewählt und es bestehen Patienten.
	- *Auszuführende Schritte*: /
	- *Erwartetes Ergebnis*: Das Geburtsdatum wird im gewünschten Format (DD.MM.YYYY) dargestellt.
2. **Das Datum wird im gewünschten Format angezeigt**:
	- *Vorbedingung*: Der Nutzer hat im Hauptfenster der Anwendung die Option `Behandlungen` ausgewählt und es bestehen Behandlungen.
	- *Auszuführende Schritte*: /
	- *Erwartetes Ergebnis*: Das Datum wird im gewünschten Format (DD.MM.YYYY) dargestellt.
3. **Das Datum kann im gewünschten Format angegeben werden**:
	- *Vorbedingung*: Der Nutzer hat im Hauptfenster der Anwendung die Option `Patient/innen` ausgewählt.
	- *Auszuführende Schritte*: Es werden Daten in die dafür vorgesehenen Felder eingetragen. Das Geburtsdatum wird im Format `DD.MM.YYYY` angegeben und der Patient angelegt.
	- *Erwartetes Ergebnis*: Der Patient konnte angelegt werden und das Geburtsdatum wird im gewünschten Format (DD.MM.YYYY) dargestellt.
***
