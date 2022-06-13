### **Export Patienten Daten**

###### User-Story

Als **Pfleger** möchte ich **Daten eines Patienten exportieren können**, um sie **einem Patienten zur Verfügung zu stellen**. Somit hat er die Möglichkeit, ein Überblick zu erhalten welche Daten von ihm gespeichert sind (DSGVO).

###### Akzeptanzkriterien

1. Pfleger kann Patienten Daten im PDF Dokument exportieren.
2. Dokument enthält alle gespeicherten Daten eines einzelnen Patienten.


###### Tasks

1. Dependency `Apache PDFBox` in der `prom.xml` hinzufüge.
	-  `pdfbox.apache.org/`
2. Patientenübersicht mit einer Spalte erweitern.
	- `children`  der `GridPane` in der `AllPatientView`  mit einem Export Butten erweitern.
3. `AllPatientController`  mit einer `methodeToPdf(Patient patient)` erweitern.



###### Testfälle

1. **Export von Patienten Daten**:

	**Vorbedingung**: 
		- Der Nutzer startet Applikation.
		- Der Nutzer logt sich ein.
		- Der Nutzer öffnet Patienten Liste.
	
	**Auszuführende Schritte**: 
		- Der Nutzer drückt bei einem beliebigen Patienten auf den Export Button.
	**Erwartetes Ergebnis**: 
		- Datei wird exportiert.