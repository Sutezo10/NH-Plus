### **Zugriff auf die Daten via Login**

###### User-Story

Als **Pfleger** möchte ich **mich einloggen können**, um **Zugriff auf die Daten meiner Patienten zu bekommen.**

###### Akzeptanzkriterien

1. Ohne erfolgreichen Log-in kein Zugriff auf die Daten.
2. Pfleger kann sich einloggen.
3. Pfleger kann sich ausloggen.
4. Pfleger kann das Passwort ändern.


###### Tasks

1. Erstelle die Entity User
	-  `src/main/java/model/User.java`
	-  `src/main/java/datastorage/UserDAO.java`
2. Erstelle die Login View
	- `src/main/resources/LoginView.fxml`
3. Erstelle Controller der Login View
	- `src/main/java/controller/LoginController.java`
4. Startseite `MainWindowView.fxml`  durch `LoginView.fxml` ersetzen.
	- In der Methode `mainWindow()` der Klasse `Main` .
5. `MainWindowView`  mit einem Log-out Button erweitern.
6. `MainWindowController` mit Log-out Logik erweitern.


###### Testfälle

1. **Log-in ohne Daten**:

	**Vorbedingung**: 
		- Der Nutzer startet die Applikation.
	**Auszuführende Schritte**: 
		- Der Nutzer drückt auf den Log-in Button.
	**Erwartetes Ergebnis**: 
		- Es wird eine Fehlermeldung angezeigt, das der Nutzer Daten Angeben soll.

2. **Log-in mit falschen Daten**:

	**Vorbedingung**:
		- Der Nutzer startet die Applikation und gibt falsche Daten an.
	**Auszuführende Schritte**: 
		- Der Nutzer drückt auf den Log-in Button.
	**Erwartetes Ergebnis**: 
		- Fehlermeldung das der Nutzer richtige Daten Angeben soll.

3. **Log-in mit richtigen Daten**:

	**Vorbedingung**:
		- Der Nutzer startet die Applikation und gibt die richtigen Daten an.
	**Auszuführende Schritte**: 
		- Der Nutzer drückt auf den Log-in Button.
	**Erwartetes Ergebnis**: 
		- Der Nutzer wird auf die `MainWindowView` weitergeleitet.

4. **Ausloggen**:

	**Vorbedingung**: 
		- Der Nutzer startet Applikation und logt sich ein.
	**Auszuführende Schritte**:
		- Der Nutzer drückt auf den Log-out Button.
	**Erwartetes Ergebnis**: 
		- Der Nutzer wird abgemeldet und somit auf die  `LoginView` weitergeleitet.