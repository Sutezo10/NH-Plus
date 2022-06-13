### **Pfleger löschen muss die 10 Jahresregelung berücksichtigen**

###### User-Story
Als **Pflegeheimleitung** möchte ich **nicht mehr aktive Pfleger sperren können**, um **den Anforderungen der DSGVO, beziehungsweise dem bremischen Krankenhausgesetz bezüglich der Aufbewahrung der Daten nachzukommen**.

###### Akzeptanzkriterien
1. Pfleger können aus der Pflegerübersicht heraus angeklickt und dann gesperrt werden. 
2. Das Sperren erfolgt über denselben Button wie das löschen. 
3. Die Entscheidung, ob ein Pfleger komplett gelöscht oder lediglich gesperrt wird hängt davon ob, ob dieser innerhalb der letzten 10 Jahre Behandlungen durchgeführt hat.
4. Hat ein Pfleger innerhalb der letzten 10 Jahren Behandlungen durchgeführt, wird dieser gesperrt und 10 Jahre nach seiner letzten Behandlung automatisch gelöscht. 
5. Sperren bedeutet in diesem Fall, dass die Daten des Pflegers nicht mehr in der Anwendung angezeigt werden, die von ihm durchgeführten Behandlungen hingegen schon. 
6. Bei den Behandlungen eines gesperrten oder gelöschten Pflegers wird im Feld für den Pfleger der entsprechende Status angezeigt. 
7. Die Prüfung, ob ein gesperrter Pfleger entgültig gelöscht wird erfolgt mit jedem Programmstart.

###### Tasks
1. Die Logik im Controller für den Pfleger um eine weitere Methode zum Sperren eines Pflegers erweitern *(Das sperren entfernt einen Pfleger aus der Pfleger-Tabelle und fügt diesen einer separaten Tabelle für gesperrte Pfleger hinzu.)*
2. Die Methode verantwortlich zum Löschen eines Pflegers in der View für alle Pfleger mit einer Methode austauschen, die prüft anhand einer Datenbankabfrage, ob der zu löschende Pfleger innerhalb der letzten 10 Jahre Behandlungen durchgeführt hat.
3. Mittels einer if-Anweisung entscheiden, ob die Methode zum löschen, oder die zum sperren ausgeführt werden muss. 
4. Die `AllTreatmentView.fxml` beziehungsweise den `controller/AllTreatmentController.java` bearbeiten und um eine Fallunterscheidung erweitern. Diese entscheidet, was im Feld für den Pfleger angezeigt wird, wenn dieser a) gesperrt, oder b) gelöscht wurde. Diese ersetzt den dazugehörigen String in der Spalte mit dem Status des Pflegers.
5. Den `controller/MainWindowController.java` um eine weitere Methode ergänzen, der eine Datenbankabfrage stellt die prüft, welche Pfleger in den letzten 10 Jahren keine Behandlungen durchgeführt haben und diese daraufhin löscht.
6. Die Methode nach Programmstart automatisch einmalig ausführen lassen.


###### Testfälle
1. **Pfleger, der in den letzten 10 Jahren Behandlungen durchgeführt hat wird gelöscht**:
	- *Vorbedingung*: Der Nutzer hat im Hauptfenster der Anwendung die Option `Pfleger/innen` ausgewählt.
	- *Auszuführende Schritte*: Es wird ein Pfleger ausgewählt, der in den letzten 10 Jahren Patienten behandelt hat und auf `Löschen` geklickt und dies bestätigt.
	- *Erwartetes Ergebnis*: 
		- Der betroffene Pfleger wurde aus der Liste in der Übersicht der Pfleger/innen entfernt. 
		- In der Datenbank wurde in einer gesonderten Tabelle für *gesperrte Konten* ein Eintrag erstellt, welcher die `ID` des gesperrten Pflegers trägt.
		- Behandlungen dieses Pflegers werden weiterhin angezeigt, jedoch wird in der Spalte für den Pfleger nun der Status ausgegeben, in diesem Fall `GESPERRT`.
2. **Pfleger, der in den letzten 10 Jahren keine Behandlungen durchgeführt hat wird gelöscht**:
	- *Vorbedingung*: Der Nutzer hat im Hauptfenster der Anwendung die Option `Pfleger/innen` ausgewählt.
	- *Auszuführende Schritte*: Es wird ein Pfleger ausgewählt, der in den letzten 10 Jahren keine Patienten behandelt hat und auf `Löschen` geklickt und dies bestätigt.
	- *Erwartetes Ergebnis*: 
		- Der betroffene Pfleger wurde aus der Liste in der Übersicht der Pfleger/innen entfernt. 
		- In der Datenbank wurden die Daten des Pflegers entfernt.
		- In einer weiteren Datenbanktabelle für *gelöschte Pfleger* wird die `ID` des Pflegers eingetragen. 
		- Behandlungen dieses Pflegers werden weiterhin angezeigt, jedoch wird in der Spalte für den Pfleger nun der Status ausgegeben, in diesem Fall `GELÖSCHT`.
***
