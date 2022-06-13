### **Vermögensstand muss aus der Applikation entfernt werden**

###### User-Story
Als **Patient** muss ich **meinen Vermögensstand nicht angeben**, da dieser **irrelevant für die Behandlung ist und dies gegen die DSGVO verstößt.**

###### Akzeptanzkriterien
1. Der Vermögensstand ist nicht in der Datenbank gespeichert.
2. Es besteht kein Feld, um den Vermögensstand eines Patienten einzutragen.
3. In der Tabelle mit den Patienten existiert keine Spalte für den Vermögensstand.

###### Tasks
1. Entferne alles in Bezug auf den Vermögensstand in:
	- `model/Person.java`
	- `datastorage/PatientDAO.java` 
	- `controller/AllPatientController.java`

2. Entferne (`DROP`) die Spalte `ASSETS` von der Tabelle `PATIENT`.
3.  Entferne die zum Vermögensstand gehörigen UI-Elemente mittels `JavaFX`.


###### Testfälle
1. **Patient anlegen erfordert keinen Vermögensstand**:
	- *Vorbedingung*: Der Nutzer hat im Hauptfenster der Anwendung die Option Patienten/innen ausgewählt.
	- *Auszuführende Schritte*:  / 
	- *Erwartetes Ergebnis*: Es besteht kein Feld für den Vermögensstand.
	
2. **Patientendaten zeigen keinen Vermögensstand**:
	- *Vorbedingung*: Der Nutzer hat im Hauptfenster der Anwendung die Option Patienten/innen ausgewählt.
	- *Auszuführende Schritte*:  / 
	- *Erwartetes Ergebnis*: In der Tabelle der Patientendaten befindet sich keine Spalte für den Vermögensstand.	
3. **Vermögensstand wird nicht mehr in der Datenbank gespeichert**:
	- *Vorbedingung*: Der Nutzer öffnet die Datenbank und navigiert zu der Tabelle `PATIENT`.
	- *Auszuführende Schritte*:  / 
	- *Erwartetes Ergebnis*: In der Tabelle wird die Spalte `ASSETS` nicht mehr angezeigt.
***
