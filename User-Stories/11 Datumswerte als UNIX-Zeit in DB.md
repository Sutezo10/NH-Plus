### **Datumswerte als Unix-Zeit speichern**

###### User-Story
Als **Entwickler** möchte ich **Datumswerte generell als UNIX-Zeit speichern**, um **zeitbasierte Kalkulationen zu vereinfachen**.

###### Akzeptanzkriterien
1. Das Datum wird als UNIX-Zeit in der Datenbank gespeichert.

###### Tasks
1. Das Feld `DATEOFBIRTH` in der `PATIENT`-Tabelle auf den Datentyp `INTEGER` ändern.
2. Dem `utils/DateConverter.java` weitere Methoden hinzufügen um UNIX-Zeit in ein leserliches Datum, sowie Zeit vom Typ `LocalDate`, beziehungsweise `LocalTime` zu konvertieren und andersherum. 
3. Das `Patient`-Model und das `Treatment`-Model überarbeiten, sodass das Geburtsdatum (`dateOfBirth`) und das Datum (`date`) als UNIX-Zeit (*int*) gespeichert wird. Entsprechende Methoden, die bisher auf den Datentyp `LocalDate` basierten, anpassen. 
4. Die Methode `getDateOfBirth()` im `Patient`-Model beziehungsweise `getDate()` im `Treatment`-Model soll weiterhin einen fertig konvertierten und formatierten String ausgeben. Dieser wird für die Ausgabe in jeglichen Tabellen genutzt.


###### Testfälle
1. **Geburtsdatum beim Anlegen eines Patienten in der Datenbank**:
	- *Vorbedingung*: Der Nutzer hat im Hauptfenster der Anwendung die Option `Patient/innen` ausgewählt.
	- *Auszuführende Schritte*: Es wird ein neuer Patient mit allen dafür benötigten Werten angelegt.
	- *Erwartetes Ergebnis*: In der Datenbanktabelle `PATIENT` in der Spalte `dateOfBirth` wurde das angegebene Datum als UNIX-Zeit *(Integer)* gespeichert.
2. **Datum beim Anlegen einer Behandlung in der Datenbank**:
	- *Vorbedingung*:  Der Nutzer hat im Hauptfenster der Anwendung die Option `Behandlungen` ausgewählt und es bestehen Patienten.
	- *Auszuführende Schritte*: Es wird eine neue Behandlung mit allen dafür benötigten Werten angelegt und einem Patienten hinzugefügt.
	- *Erwartetes Ergebnis*: In der Datenbanktabelle `TREATMENT` in der Spalte `TREATMENT_DATE` wurde das angegebene Datum als UNIX-Zeit *(Integer)* gespeichert.
3. **Datum wird bei den Behandlungen korrekt angezeigt**:
	- *Vorbedingung*:  Der Nutzer hat im Hauptfenster der Anwendung die Option `Behandlungen` ausgewählt und es bestehen Behandlungen.
	- *Auszuführende Schritte*: /
	- *Erwartetes Ergebnis*: In der Spalte `Datum` wird das Datum für Menschen leserlich dargestellt.
4. **Geburtsdatum wird bei den Patient/innen korrekt angezeigt**:
	- *Vorbedingung*:  Der Nutzer hat im Hauptfenster der Anwendung die Option `Patient/innen` ausgewählt und es bestehen Patienten.
	- *Auszuführende Schritte*: /
	- *Erwartetes Ergebnis*: In der Spalte `Geburtsdatum` wird dieses für Menschen leserlich dargestellt.
***
