### **Behandlungsdaten werden nach abgelaufener Sperrfrist automatisch gelöscht**

###### User-Story
Als **Pflegeheimleitung** möchten wir, dass **die Behandlungsdaten nach abgelaufener Sperrfrist gelöscht werden**, um nicht **gegen die DSGVO zu verstoßen.**

###### Akzeptanzkriterien
1. Es gibt ein System, welches alle Behandlungsdaten die eine abgelaufene Sperrfrist haben, automatisch vollständig löscht.


###### Tasks

1. Entwerfe ein Event, welches bei dem Erreichen der Sperrfrist aktiviert wird.

- `model/Treatment.java`

2. Verknüpfe das Event mit dem Aufruf die Daten aus der Datenbank zu entfernen.

- `controller/AllTreatmentController.java`.


3. Entferne (`DROP`) die Behandlungsdaten `TREATMENT VALUES`, falls von einer Behandlung die Sperrfrist abgelaufen ist.



###### Testfälle
1. **Behandlungsdaten werden automatisch nach abgelaufener Sperrfrist aus der Datenbank gelöscht**:
    - *Vorbedingung*: Es existieren Behandlungsdaten, welche eine abgelaufene Sperrfrist haben.
    - *Auszuführende Schritte*:  /
    - *Erwartetes Ergebnis*: Diese Behandlungsdaten werden endgültig von der Datenbank gelöscht und sich nicht mehr aufrufbar.
   
2. **Es wird ein Event geworfen, wenn Behandlungsdaten die Sperrfrist erreichen**:
- *Vorbedingung*: Es existieren Behandlungsdaten, welche die Sperrfrist erreichen werden.
- *Auszuführende Schritte*:  /
- *Erwartetes Ergebnis*: Es wird das Event geworfen, wenn die Sperrfrist erreicht wurde.

***