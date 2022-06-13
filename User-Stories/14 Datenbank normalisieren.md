### **Datenbank normalisieren**

###### User-Story
Als **Entwickler** möchte ich **eine normalisierte Datenbank**, um somit **redundanzfreie Datenspeicherung zu ermöglichen, sowie Anomalien zu entfernen**.

###### Akzeptanzkriterien
1. Die Struktur, beziehungsweise der Aufbau der Datenbank entspricht der dritten Normalform **(3NF)**.

###### Tasks
1. Tabelle `PATIENT` normalisieren:
	1. Eine weitere Tabelle `PERSON` mit den Feldern `ID`, `FIRSTNAME`, `SURNAME` und `DATEOFBIRTH` anlegen.  Das Feld `ID` ist hier der **Primärschlüssel** und wurde mit dem Attribut `AUTO_INCREMENT` versehen.
	2. In der Tabelle `PATIENT` die Felder `FIRSTNAME`, `SURNAME` und `DATEOFBIRTH` entfernen und durch die dazugehörige `ID` aus der `PERSON`-Tabelle mit dem Feld `PERSONID` ersetzen. In der Tabelle `PATIENT` ist das Feld `PERSONID` der **Fremdschlüssel** und referenziert die `ID` aus der `PERSON`-Tabelle.

2. Tabelle `TREATMENT` normalisieren:
	1. Eine weitere Tabelle `TREATMENT_TYPE` mit dem Feld `TREATMENT` als **Primärschlüssel** erstellen.
	2. Die Tabelle `TREATMENT_TYPE` mit den verschiedenen Arten von Behandlungen füllen (`Gespräch`, `Waschen` etc.).
	3. Die Tabelle `TREATMENT` bearbeiten und der Spalte `DESCRIPTION` einen **Fremdschlüssel** hinzufügen der auf das Feld `TREATMENT` in der `TREATMENT_TYPE`-Tabelle referenziert.

3. Die betroffenen Datenbankabfragen (Querys) in den DAOs anpassen.
	- `datastorage/PatientDAO.java`
	- `datastorage/TreatmentDAO.java`


###### Testfälle
1. **Datenbank wurde normalisiert**:
	- *Vorbedingung*: Der Entwickler öffnet die Übersicht der Datenbank.
	- *Auszuführende Schritte*: /
	- *Erwartetes Ergebnis*: Die Datenbank wurde nach den Regeln der **3NF** restrukturiert.
***
