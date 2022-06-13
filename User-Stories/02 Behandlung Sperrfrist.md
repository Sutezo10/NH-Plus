### **Behandlungsdaten können gesperrt/entsperrt werden und bekommen eine Sperrfrist von 10 Jahren**

###### User-Story

Als **Patient** möchte ich, dass **meine Behandlungsdaten nach einer abgeschlossenen Behandlung gesperrt werden**, um 
**in zukünftigen Behandlungen auf die ursprünglichen Behandlungsdaten zurückgreifen zu können.**

###### Akzeptanzkriterien

1. Es gibt einen Knopf, der die Behandlungsdaten sperrt/entsperrt.
2. Die Behandlungsdaten haben eine Sperrfrist von 10 Jahren
3. Die Sperrfrist wird bei dem Verändern der Daten zurückgesetzt.

###### Tasks

1. Ergänze eine Variable zur Abfrage des Read-Only(Abfrage Gesperrt/Entsperrt) Status. 
    - `model/Treatment.java` 
   
2. Erstelle ein neues Handle-Event, welches die Funktion des Sperrens enthält.
3. Erstelle eine Methode für die Abfrage des Read-Only Status, die eine Meldung zum User zurückgibt, wenn die Daten gesperrt sind.
4. Nutze die vorher erstellte Methode an den notwendigen Stellen, um die Funktion des Sperrens zu integrieren.

   - `controller/AllTreatmentController.java`.


5. Erstelle ein UI-Element `Button` mit `JavaFX`, welcher das neue Event übernimmt.

6. Erstelle eine Variable mit der Sperrfrist von 10 Jahren
7. Erstelle eine Methode, die die Sperrfrist zuteilt/zurücksetzt.



###### Testfälle

1. **Gesperrte Behandlungsdaten, können nicht verändert werden.**:
    - *Vorbedingung*: Behandlungsdaten mit gesperrtem Status existieren.
    - *Auszuführende Schritte*: Es wird versucht Änderungen an den Behandlungsdaten mit gesperrtem Status vorzunehmen.
    - *Erwartetes Ergebnis*: Es kann nichts verändert werden an den Behandlungsdaten und es kommt, beim Versuch diese zu ändern eine Meldung, dass die Daten gesperrt sind.

2. **Die Sperrfrist wird nach dem Verändern der Behandlungsdaten zurückgesetzt**:
    - *Vorbedingung*: Es existierten gesperrte Behandlungsdaten.
    - *Auszuführende Schritte*: Die Behandlungsdaten werden entsperrt und verändert. Danach werden diese wieder gesperrt. 
    - *Erwartetes Ergebnis*: Die Behandlungsdaten haben wieder eine zurückgesetzte Sperrfrist.

3. **Gesperrte Behandlungsdaten bekommen eine Sperrfrist von 10 Jahren zugeteilt**:
    - *Vorbedingung*: Es existieren Behandlungsdaten.
    - *Auszuführende Schritte*:  Die Behandlunsdaten werden mit einem klick in der Übersicht gesperrt.
    - *Erwartetes Ergebnis*: Die Behandlungsdaten haben nach Abfrage 10 Jahre als Sperrfrist hinterlegt.

***