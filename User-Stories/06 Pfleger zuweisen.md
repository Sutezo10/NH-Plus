### **Pflegekraft zuweisen**

###### User-Story
Als **Pfleger** möchte ich **bei einer neuen Behandlung meinen Namen hinzufügen können**, damit **nachvollzogen werden kann, wer welche Behandlung durchgeführt hat.**

###### Akzeptanzkriterien
1. Beim Anlegen einer neuen Behandlung soll die entsprechende Pflegekraft mithilfe einer Combobox ausgewählt werden können.

###### Tasks
1. Die Datenbanktabelle `TREATMENT` mit einer Spalte für die `ID` des Pflegers erweitern und diesen als *Fremdschlüssel* der auf die `ID` in der Pfleger-Tabelle referenziert anlegen
2. Das `datastorage/TreatmentDAO.java` sowie das `model/Treatment.java` erweitern, sodass diese den weiteren Parameter für die ID des Pflegers abbilden und in der Datenbank speichern können
3. Die `resources/NewTreatmentView.fxml` mit einer Combobox, sowie einem Label für die Auswahl des Pflegers erweitern
4. Den `controller/NewTreatmentController.java` erweitern, sodass dieser der Combobox die Namen der Pfleger hinzufügt
5. Die `handleAdd()`-Methode in `controller/NewTreatmentController.java` erweitern, sodass diese dem neu erstellten `Treatment`-Objekt die ID des Pflegers als Parameter übergibt 


###### Testfälle
1. **Anlegen einer neuen Behandlung als Pfleger**:
    - *Vorbedingung*: Der Nutzer hat im Hauptfenster der Anwendung die Option Behandlung ausgewählt und auf `neue Behandlung anlegen` geklickt.
    - *Auszuführende Schritte*: 
      1. Der User hat die Daten für die Behandlung in den dafür vorgesehenen Textfeldern angegeben.
      2. Der User hat den Button `Hinzufügen` betätigt.
    - *Erwartetes Ergebnis*: 
      1. Der neue Eintrag wird samt Pfleger in der Tabelle angezeigt.
      2. Die neue Behandlung wurde in der Datenbank gespeichert.

2. **Anlegen einer neuen Behandlung als Pflegeheimleitung**:
   - *Vorbedingung*: Der Nutzer hat im Hauptfenster der Anwendung die Option Behandlung ausgewählt und auf `neue Behandlung anlegen` geklickt.
   - *Auszuführende Schritte*:
      1. Der User hat die Daten für die Behandlung in den dafür vorgesehenen Textfelder angegeben.
      2. Für die Angabe des Pflegers muss mit einer Combobox eine Auswahl getroffen werden. 
      3. Der User hat den Button `Hinzufügen` betätigt.
   - *Erwartetes Ergebnis*:
      1. Der neue Eintrag wird samt manuell ausgewähltem Pfleger in der Tabelle angezeigt.
      2. Die neue Behandlung wurde in der Datenbank gespeichert.
***



