### **Löschen einer Pflegekraft**

###### User-Story
Als **Pflegeheimleitung** möchte ich **Pflegekräfte, die keine Behandlungen mehr durchführen, löschen können**, um **diese aus dem System zu entfernen**.

###### Akzeptanzkriterien
1. Pflegekräfte können in der `Pflegekräfte` Übersicht gelöscht werden.
2. In der Übersicht der Pflegekräfte besteht ein Button zum Löschen des ausgewählten Pflegers.
3. Das Löschen eines Pflegers muss nach Betätigung des Buttons noch mal bestätigt werden.
4. Behandlungen, die von der zu löschenden Pflegekraft durchgeführt wurden bleiben bestehen.

###### Tasks
1. Die View für die Anzeige aller Pfleger um einen weiteren Button zum Löschen eines Pflegers erweitern
2. Dem Button mittels SceneBuilder die Funktion geben, eine Alert-Box zu öffnen, die durch den Nutzer mit einem `OK` bestätigt wird.
3. Diesen `OK`-Button mit der Delete-Logik aus den CRUD-Methoden des dafür vorab erstellten Controllers verknüpfen.
4. Den `controller/AllTreatmentController.java` überarbeiten, sodass die `readAllAndShowInTableView()` Methode keinen Fehler wirft, wenn diese den Pflegernamen nicht auffinden kann. Hier soll anstelle des Pflegernamens dann ein `/` oder `GELÖSCHT` ausgegeben werden


###### Testfälle
1. **Pfleger wird gelöscht**:
	- *Vorbedingung*: Die Pflegeheimleitung hat im Hauptfenster der Anwendung die Option `Pflegekräfte` ausgewählt.
	- *Auszuführende Schritte*: 
		1. Es wird eine Pflegekraft angeklickt.
		2. Es wird auf den Knopf `Löschen` geklickt und dies bestätigt.
	- *Erwartetes Ergebnis*: 
		1. Der gelöschte Pfleger wurde in der Übersicht aus der Tabelle entfernt.
		2. Der dazugehörige Eintrag wurde aus der Datenbank entfernt.
		
2. **Das Löschen erfordert eine Bestätigung**:
	- *Vorbedingung*: Der User hat im Hauptfenster der Anwendung die Option `Pflegekräfte` ausgewählt.
	- *Auszuführende Schritte*: 
		1. Es wird eine Pflegekraft angeklickt.
		2. Es wird auf den Knopf `Löschen` geklickt.
	- *Erwartetes Ergebnis*: 
		1. Ein Pop-up Fenster mit Infotext wird geöffnet, in welchem die Pflegeheimleitung das Löschen mit einem weiteren Klick bestätigen muss.
***
