### **Pfleger in Behandlungsübersicht**

###### User-Story
Als **Pflegeheimleitung** möchte ich **in der Übersicht der Behandlung den dazugehörigen Pfleger sehen**, um **bei Rückfragen nachvollziehen zu können, welcher Pfleger die Behandlung durchgeführt hat.**

###### Akzeptanzkriterien
1. Die Daten des Pflegers werden im Format: Nachname, Vorname sowie Telefonnummer in einer eigenen Spalte zu der dazugehörigen Behandlung angezeigt.

###### Tasks
1. Den TableView in der `AllTreatmentView.fxml` um eine weitere Spalte für die Daten des Pflegers erweitern (**Eine** Spalte, in der alle Daten angezeigt werden)
2. Die `readAllAndShowInTableView()` Methode im `controller/AllTreatmentController.java` erweitern, sodass die `ID` des Pflegers, die das DAO mit der `readAll()` Methode aus der Datenbank zurückgibt, in der Tabelle mit Dem Vor- und Nachnamen, sowie der Telefonnummer ersetzt wird. 


###### Testfälle
1. **Pfleger in Behandlungsübersicht anzeigen**:
    - *Vorbedingung*: Der Nutzer hat im Hauptfenster der Anwendung die Option `Behandlungen` ausgewählt und es bestehen Behandlungen mit zugewiesenem Pfleger.
    - *Auszuführende Schritte*:  /
    - *Erwartetes Ergebnis*: 
      1. In der Tabelle mit den Behandlungen ist eine Spalte für die Daten des Pflegers.
      2. Bei einer Behandlung mit zugewiesenem Pfleger wird dieser in der dafür vorgesehenen Spalte angezeigt.
      
***



