### **Behandlungsdaten werden nach abgelaufener Sperrfrist automatisch gelöscht**

###### User-Story
Als **Pflegeheimleitung** möchten wir, dass **die Behandlungsdaten nach abgelaufener Sperrfrist gelöscht werden**, um nicht **gegen die DSGVO zu verstoßen.**

###### Akzeptanzkriterien
1. Es gibt ein System, welches alle Behandlungsdaten die eine abgelaufene Sperrfrist haben, automatisch vollständig löscht.


###### Tasks

1. Entferne (`DROP`) die Behandlungsdaten `TREATMENT VALUES`, falls von einer Behandlung die Sperrfrist abgelaufen ist.



###### Testfälle
1. **Behandlungsdaten werden automatisch nach abgelaufener Sperrfrist aus der Datenbank gelöscht**:
    - *Vorbedingung*: Es existieren Behandlungsdaten, welche eine abgelaufene Sperrfrist haben.
    - *Auszuführende Schritte*:  /
    - *Erwartetes Ergebnis*: Diese Behandlungsdaten werden endgültig von der Datenbank gelöscht und sich nicht mehr aufrufbar.
   
***